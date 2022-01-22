package com.emlakjet.exam.views.employeelist;

import com.emlakjet.exam.constants.EmlakJetConstants;
import com.emlakjet.exam.data.entity.AccountingSpecialist;
import com.emlakjet.exam.data.entity.Driver;
import com.emlakjet.exam.data.entity.Employee;
import com.emlakjet.exam.data.entity.SalesExpert;
import com.emlakjet.exam.data.entity.SoftwareSpecialist;
import com.emlakjet.exam.data.service.EmployeeService;
import com.emlakjet.exam.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

import org.aspectj.apache.bcel.generic.SwitchBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("employeeList")
@Route(value = "employeeList/:employeeID?/:action?(edit)", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class EmployeeListView extends Div implements BeforeEnterObserver {

    private final String EMPLOYEE_ID = "employeeID";
    private final String EMPLOYEE_EDIT_ROUTE_TEMPLATE = "employeeList/%d/edit";

    private Grid<Employee> grid = new Grid<>(Employee.class, false);
    

    private TextField name;
    private TextField surName;
    private TextField email;
    private DatePicker beginDate;
    private Label employeeLabel; 
    private TextField softwareRole;
    private TextField projectName;
    private TextField workArea;
    private TextField manager;
    private TextField usedApplicationName;
    private TextField certificateName;
    private TextField carBrand;
    private TextField carModel;
    private TextField price;
    private Label primResult;

    private Button calculatePrim = new Button("Prim",  new Icon(VaadinIcon.CALC));
    
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    
    

    private BeanValidationBinder<Employee> binder;


    private Employee employee;
    private SoftwareSpecialist softwareSpecialist;

    private EmployeeService employeeService;

    public EmployeeListView(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
        addClassNames("employee-list-view", "flex", "flex-col", "h-full");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        HorizontalLayout addButtonLayout = createAddButtonLayout();
        add(addButtonLayout);  
        
        add(splitLayout);

        // Configure Grid
        
        grid.addColumn("name").setAutoWidth(true);
        grid.addColumn("surName").setAutoWidth(true);
        grid.addColumn("email").setAutoWidth(true);
        grid.addColumn("beginDate").setAutoWidth(true);
        grid.addColumn(e ->  getEmployeTypeStr(e.getEmployeeType())).setAutoWidth(true);
    
        /*
        grid.addColumn(e ->{
        	if(e instanceof Driver) 
        		return ((Driver)e).getCarModel();
        	return "";
        }).setAutoWidth(true);
        
        grid.addColumn("softwareRole").setAutoWidth(true);
        grid.addColumn("projectName").setAutoWidth(true);
        
        grid.addColumn("workArea").setAutoWidth(true);
        grid.addColumn("manager").setAutoWidth(true);
        
        grid.addColumn("usedApplicationName").setAutoWidth(true);
        grid.addColumn("certificateName").setAutoWidth(true);
        
        grid.addColumn("carBrand").setAutoWidth(true);
        grid.addColumn("carModel").setAutoWidth(true);
        */
        
        grid.addColumn("price").setAutoWidth(true);
        grid.setItems(query -> employeeService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(EMPLOYEE_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(EmployeeListView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Employee.class);
        


        // Bind fields. This where you'd define e.g. validation rules
//         binder.forField(employeeLabel).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
//               .bind("employeeType");
        
        binder.forField(price).withConverter(new StringToBigDecimalConverter("Only numbers are allowed")).bind("price");

        binder.bindInstanceFields(this);

        
        calculatePrim.addClickListener(e ->{
        	
        	 String result = "0";
        	 if (this.employee != null) 
        	 {
        		 try {
					binder.writeBean(this.employee);
				} catch (ValidationException e1) {
					e1.printStackTrace();
				}
        		 BigDecimal value =  this.employee.getPrice().multiply(getCoefByEmployeeType(this.employee.getEmployeeType())).multiply(getWorkDuration(this.employee.getBeginDate()));
        		 value = new  BigDecimal(value.toString()).divide(BigDecimal.ONE, 2, BigDecimal.ROUND_HALF_UP);
        		 result = value.toPlainString().trim();
        	 }
        	 
        	primResult.setText(result + " TL");
        });
        
        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.employee == null)
                	return;
                
                binder.writeBean(this.employee);

                employeeService.update(this.employee);
                clearForm();
                refreshGrid();
                Notification.show("Employee details stored.");
                UI.getCurrent().navigate(EmployeeListView.class);
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the employee details.");
            }
        });
        
        delete.addClickListener(e -> {
            try {
                if (this.employee == null)
                	return;
                
                employeeService.delete(this.employee.getId());
                clearForm();
                refreshGrid();
                Notification.show("Employee details stored.");
                UI.getCurrent().navigate(EmployeeListView.class);
            } catch (Exception e1) {
                Notification.show("An exception happened while trying to store the employee details. " + e1.getMessage());
            }
        });

    }

	private HorizontalLayout createAddButtonLayout() 
	{
		MenuBar menuBar = new MenuBar();
        menuBar.addThemeVariants(MenuBarVariant.LUMO_ICON, MenuBarVariant.LUMO_PRIMARY);
        menuBar.addItem("Add");
        MenuItem item = menuBar.addItem(new Icon(VaadinIcon.CHEVRON_DOWN));
       
        SubMenu subItems = item.getSubMenu();
        subItems.addItem("Add Driver").addClickListener(e -> {populateForm(new Driver());});
        subItems.addItem("Add AccountingSpecialist").addClickListener(e -> {populateForm(new AccountingSpecialist());});
        subItems.addItem("Add SalesExpert").addClickListener(e -> {populateForm(new SalesExpert());});
        subItems.addItem("Add SoftwareSpecialist").addClickListener(e -> {populateForm(new SoftwareSpecialist());});
       
        HorizontalLayout addButtonLayout = new HorizontalLayout();
        addButtonLayout.add(menuBar);
        addButtonLayout.setAlignItems(Alignment.END);
       
        addButtonLayout.setWidth(100, Unit.PERCENTAGE);
		return addButtonLayout;
	}
    
    public String getEmployeTypeStr(Integer employeeType)
    {
    	switch(employeeType)
		{
			case EmlakJetConstants.EMPLOYEE_TYPE_DRIVER:
		        return "Driver";
			case EmlakJetConstants.EMPLOYEE_TYPE_ACCOUNTINGSPECIALIST:
				return "Accounting Specialist";
			case EmlakJetConstants.EMPLOYEE_TYPE_SALESEXPERT:
				return "Sales Expert";
			case EmlakJetConstants.EMPLOYEE_TYPE_SOFTWARESPECIALIST:
				return "Software Specialist";
		}
    	return "";
    }
    
    public BigDecimal getWorkDuration(LocalDate beginDate)
    {
    	if(beginDate != null)
    	{
    		Calendar now = Calendar.getInstance();
    		return new BigDecimal(now.get(Calendar.YEAR) - beginDate.getYear());
    	}
    	return BigDecimal.ONE;
    }
    
    public BigDecimal getCoefByEmployeeType(Integer employeeType)
    {
    	switch(employeeType)
		{
			case EmlakJetConstants.EMPLOYEE_TYPE_DRIVER:
		        return EmlakJetConstants.EMPLOYEE_COEF_DRIVER;
			case EmlakJetConstants.EMPLOYEE_TYPE_ACCOUNTINGSPECIALIST:
				return EmlakJetConstants.EMPLOYEE_COEF_DRIVER;
			case EmlakJetConstants.EMPLOYEE_TYPE_SALESEXPERT:
				return EmlakJetConstants.EMPLOYEE_COEF_DRIVER;
			case EmlakJetConstants.EMPLOYEE_TYPE_SOFTWARESPECIALIST:
				return EmlakJetConstants.EMPLOYEE_COEF_DRIVER;
		}
    	return BigDecimal.ONE;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Integer> employeeId = event.getRouteParameters().getInteger(EMPLOYEE_ID);
        if (employeeId.isPresent()) {
            Optional<Employee> employeeFromBackend = employeeService.get(employeeId.get());
            if (employeeFromBackend.isPresent()) {
                populateForm(employeeFromBackend.get());
            } else {
                Notification.show(String.format("The requested employee was not found, ID = %d", employeeId.get()),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(EmployeeListView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("flex flex-col");
        editorLayoutDiv.setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        name = new TextField("Name");
        surName = new TextField("Sur Name");
        email = new TextField("Email");
        beginDate = new DatePicker("Begin Date");
        employeeLabel = new Label("");
        softwareRole = new TextField("Software Role");
        projectName = new TextField("Project Name");
        workArea = new TextField("Work Area");
        manager = new TextField("Manager");
        usedApplicationName = new TextField("Used Application Name");
        certificateName = new TextField("Certificate Name");
        carBrand = new TextField("Car Brand");
        carModel = new TextField("Car Model");
        price = new TextField("Price");
        Component[] fields = new Component[]{name, surName, email, beginDate, softwareRole, projectName,
                workArea, manager, usedApplicationName, certificateName, carBrand, carModel, price};
        
        employeeLabel.setEnabled(false);
        
        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        editorDiv.add(employeeLabel);
        formLayout.add(fields);
        editorDiv.add(formLayout);
        
        primResult = new Label("");
        editorDiv.add(calculatePrim); 
        editorDiv.add(primResult);
    
        
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
        
        hideAllEditorDetails();
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("w-full flex-wrap bg-contrast-5 py-s px-l");
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonLayout.add(save, cancel, delete);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getLazyDataView().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Employee value) {
        this.employee = value;
        binder.readBean(this.employee);
        primResult.setText("");
        if(value != null)
        {
        	hideFields(value.getEmployeeType());
        	employeeLabel.setText(getEmployeTypeStr(value.getEmployeeType()));
        }
    }

    
    private void hideAllEditorDetails()
    {
    	 softwareRole.setVisible(false);
         projectName.setVisible(false);
         workArea.setVisible(false);
         manager.setVisible(false);
         usedApplicationName.setVisible(false);
         certificateName.setVisible(false);
         carBrand.setVisible(false);
         carModel.setVisible(false);
    }
    
	private void hideFields(Integer employeeType) 
	{
		hideAllEditorDetails();
        
		switch(employeeType)
		{
			case EmlakJetConstants.EMPLOYEE_TYPE_DRIVER:
		        carBrand.setVisible(true);
		        carModel.setVisible(true);
				break;
			case EmlakJetConstants.EMPLOYEE_TYPE_ACCOUNTINGSPECIALIST:
		        usedApplicationName.setVisible(true);
		        certificateName.setVisible(true);
				break;
			case EmlakJetConstants.EMPLOYEE_TYPE_SALESEXPERT:
			    workArea.setVisible(true);
			    manager.setVisible(true);
				break;
			case EmlakJetConstants.EMPLOYEE_TYPE_SOFTWARESPECIALIST:
				softwareRole.setVisible(true);
				projectName.setVisible(true);
				break;
		}
	}
}
