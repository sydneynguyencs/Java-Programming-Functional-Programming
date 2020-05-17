package ch.zhaw.prog2.functional.streaming.finance;

import ch.zhaw.prog2.functional.streaming.Company;

import java.util.List;

public class PayrollCreator {
    private final Company company;

    public PayrollCreator(Company company) {
        this.company = company;
    }

    /*
     * Aufgabe d) - Test dazu exisitert in PayrollCreatorTest
     */
    public Payroll getPayrollForAll() {
        return new Payroll();
    }

    /*
     * Aufgabe e) - Test dazu existiert in PayrollCreatorTest
     */
    public static int payrollValueCHF(Payroll payroll) {
        return 0;
    }

    /*
     * Aufgabe f) - schreiben Sie einen eigenen Test in PayrollCreatorTestStudent
     */
    public static List<CurrencyAmount> payrollAmountByCurrency(Payroll payroll) {
        return null;
    }

}
