package ch.zhaw.prog2.functional.streaming.finance;

import ch.zhaw.prog2.functional.streaming.Company;
import ch.zhaw.prog2.functional.streaming.CompanySupplier;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayrollCreatorTestStudent {
    // not private, so they can be used student test class PayRollCreatorTestStudent
    static final long RANDOM_SEED = 5113L;
    static final int EMPLOYEE_COUNT = 400;

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
