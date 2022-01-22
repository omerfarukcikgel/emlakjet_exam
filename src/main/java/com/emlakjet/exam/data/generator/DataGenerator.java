package com.emlakjet.exam.data.generator;

import com.emlakjet.exam.data.entity.Employee;
import com.emlakjet.exam.data.service.EmployeeRepository;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.time.LocalDateTime;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(EmployeeRepository employeeRepository) {
    	return null;
//        return args -> {
//            Logger logger = LoggerFactory.getLogger(getClass());
//            if (employeeRepository.count() != 0L) {
//                logger.info("Using existing database");
//                return;
//            }
//            int seed = 123;
//
//            logger.info("Generating demo data");
//
//            logger.info("... generating 100 Employee entities...");
//            ExampleDataGenerator<Employee> employeeRepositoryGenerator = new ExampleDataGenerator<>(Employee.class,
//                    LocalDateTime.of(2022, 1, 21, 0, 0, 0));
//            employeeRepositoryGenerator.setData(Employee::setId, DataType.ID);
//            employeeRepositoryGenerator.setData(Employee::setName, DataType.FIRST_NAME);
//            employeeRepositoryGenerator.setData(Employee::setSurName, DataType.LAST_NAME);
//            employeeRepositoryGenerator.setData(Employee::setEmail, DataType.EMAIL);
//            employeeRepositoryGenerator.setData(Employee::setBeginDate, DataType.DATE_OF_BIRTH);
//            employeeRepositoryGenerator.setData(Employee::setEmployeeType, DataType.NUMBER_UP_TO_10);
//            employeeRepositoryGenerator.setData(Employee::setSoftwareRole, DataType.WORD);
//            employeeRepositoryGenerator.setData(Employee::setProjectName, DataType.WORD);
//            employeeRepositoryGenerator.setData(Employee::setWorkArea, DataType.WORD);
//            employeeRepositoryGenerator.setData(Employee::setManager, DataType.WORD);
//            employeeRepositoryGenerator.setData(Employee::setUsedApplicationName, DataType.WORD);
//            employeeRepositoryGenerator.setData(Employee::setCertificateName, DataType.WORD);
//            employeeRepositoryGenerator.setData(Employee::setCarBrand, DataType.WORD);
//            employeeRepositoryGenerator.setData(Employee::setCarModel, DataType.WORD);
//            employeeRepositoryGenerator.setData(Employee::setPrice, new DataType<BigDecimal>() {
//
//				@Override
//				public BigDecimal getValue(Random random, int seed, LocalDateTime referenceTime) {
//					return BigDecimal.ZERO;
//				}
//			});
//            employeeRepository.saveAll(employeeRepositoryGenerator.create(100, seed));
//
//            logger.info("Generated demo data");
//        };
    }

}