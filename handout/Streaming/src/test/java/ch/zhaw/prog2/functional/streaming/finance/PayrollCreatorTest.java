package ch.zhaw.prog2.functional.streaming.finance;

import ch.zhaw.prog2.functional.streaming.Company;
import ch.zhaw.prog2.functional.streaming.CompanySupplier;
import ch.zhaw.prog2.functional.streaming.humanresource.Employee;
import ch.zhaw.prog2.functional.streaming.humanresource.EmployeeSupplier;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PayrollCreatorTest {
    // not private, so they can be used student test class PayRollCreatorTestStudent
    static final long RANDOM_SEED = 5113L;
    static final int EMPLOYEE_COUNT = 400;

    @Test
    void getPayrollForAll() {
        Company testCompany = new CompanySupplier(new Random(RANDOM_SEED), EMPLOYEE_COUNT).get();
        EmployeeSupplier employeeSupplier = new EmployeeSupplier(new Random(RANDOM_SEED));
        // non working employee
        Employee employee = employeeSupplier.get();
        employee.setWorkingForCompany(true);
        PayrollCreator payrollCreatorOneEmployee = new PayrollCreator(new Company(List.of(employee)));
        Payroll payroll = payrollCreatorOneEmployee.getPayrollForAll();
        assertEquals(1, payroll.getPaymentList().size(), "one working employee, one payment");
        employee.setWorkingForCompany(true);
        payroll = payrollCreatorOneEmployee.getPayrollForAll();
        assertEquals(1, payroll.getPaymentList().size(), "no working employees, no payments");
    }

    @Test
    void payrollValueCHF() {
        Company testCompany = new CompanySupplier(new Random(RANDOM_SEED), EMPLOYEE_COUNT).get();
        PayrollCreator payrollCreator = new PayrollCreator(testCompany);
        Payroll payroll = payrollCreator.getPayrollForAll();
        int paysum = PayrollCreator.payrollValueCHF(payroll);
        assertTrue(paysum > 100000);
    }

    @Test
    void payrollAmountByCurrency(){
        Company testCompany = new CompanySupplier(new Random(RANDOM_SEED), EMPLOYEE_COUNT).get();
        PayrollCreator payrollCreator = new PayrollCreator(testCompany);
        int sumToBeChecked = PayrollCreator.payrollAmountByCurrency(payrollCreator.getPayrollForAll()).stream()
            .mapToInt(CurrencyAmount::getAmount)
            .sum();
        int sumReal = payrollCreator.getPayrollForAll().getPaymentList().stream()
            .mapToInt(Payment -> Payment.getCurrencyAmount().getAmount())
            .sum();
        assertEquals(sumReal, sumToBeChecked);
    }
}

