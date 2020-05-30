package ch.zhaw.prog2.functional.streaming;

import ch.zhaw.prog2.functional.streaming.humanresource.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class is for all test methods written by students for easier review by lecturers.
 * In a real application these test would be in the class CompanyTest.
 */
public class CompanyTestStudent {
    private Company testCompany;

    @BeforeEach
    void setUp() {
        Random random = new Random(CompanyTest.RANDOM_SEED);
        CompanySupplier companySupplier = new CompanySupplier(random, CompanyTest.EMPLOYEE_COUNT);
        testCompany = companySupplier.get();
    }

    /*
     * Aufgabe c)
     */
    @Test
    void getEmployeesByPredicate() {
        /*List<Employee> list = new ArrayList<>();
        Employee jojomina = new Employee("Jojomina", "Female");
        Employee binturong = new Employee("Binturong", "Female");
        jojomina.setFemale(true);
        binturong.setFemale(true);
        list.add(new Employee("Jojom", "Male"));
        list.add(jojomina);
        list.add(binturong);
        list.add(new Employee("Binturong", "Male"));

        Company c = new Company(list);
        List<Employee> femaleList = c.getEmployeesByPredicate(Employee::isFemale);
        List<Employee> maleList = c.getEmployeesByPredicate(Employee -> !Employee.isFemale());
        */
        List<Employee> femaleList = testCompany.getEmployeesByPredicate(Employee::isFemale);
        List<Employee> maleList = testCompany.getEmployeesByPredicate(Employee -> !Employee.isFemale());

        assertEquals(testCompany.getAllEmployees().size(), femaleList.size()+maleList.size());
    }

}
