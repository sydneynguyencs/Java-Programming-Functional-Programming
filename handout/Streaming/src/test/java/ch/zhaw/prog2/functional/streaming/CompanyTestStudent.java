package ch.zhaw.prog2.functional.streaming;

import ch.zhaw.prog2.functional.streaming.finance.Payment;
import ch.zhaw.prog2.functional.streaming.humanresource.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        List<Employee> femaleList = testCompany.getEmployeesByPredicate(Employee::isFemale);
        List<Employee> maleList = testCompany.getEmployeesByPredicate(Employee -> !Employee.isFemale());

        assertEquals(testCompany.getAllEmployees().size(), femaleList.size()+maleList.size());

        //dieser Codeteil dient nur zum Ausprobieren für mich
        List<Employee> list = new ArrayList<>();
        Employee jojomina = new Employee("Jojomina", "Female");
        Employee binturong = new Employee("Binturong", "Female");
        jojomina.setFemale(true);
        binturong.setFemale(true);
        list.add(new Employee("Jojom", "Male"));
        list.add(jojomina);
        list.add(binturong);
        list.add(new Employee("Binturong", "Male"));

        Company c = new Company(list);
        List<Employee> femList = c.getEmployeesByPredicate(Employee::isFemale);
        assertEquals(2, femList.size());

    }

    /*
     * Optionaler test für g)
     */
    @Test
    void getPayments() {
        Payment pJan = testCompany.getPayments(Employee::isWorkingForCompany, Company.paymentForEmployeeJanuary).get(0);
        Payment pDec = testCompany.getPayments(Employee::isWorkingForCompany, Company.paymentForEmployeeDecember).get(0);
        int salaryJan = pJan.getCurrencyAmount().getAmount();
        int salaryDec = pDec.getCurrencyAmount().getAmount();
        assertEquals(salaryJan*2, salaryDec);
        int yearlySalary = testCompany.getEmployeesByPredicate(Employee::isWorkingForCompany).get(0).getYearlySalary().getAmount();
        //assertEquals(yearlySalary, salaryJan*11+salaryDec);

    }


}
