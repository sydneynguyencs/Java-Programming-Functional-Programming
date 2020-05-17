package ch.zhaw.prog2.functional.streaming.finance;

import ch.zhaw.prog2.functional.streaming.humanresource.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * Encapsulates Payments. Only one Payment for every beneficiary is allowed
 */
public class Payroll {
    private final List<Payment> paymentList = new ArrayList<>();

    public List<Payment> getPaymentList() {
        return Collections.unmodifiableList(paymentList);
    }

    public void addPayments(List<Payment> morePayments) {
        if (hasSameBeneficiaryInefficient(morePayments)) {
            throw new IllegalArgumentException("Duplicate Beneficiary detected");
        } else {
            paymentList.addAll(morePayments);
        }
    }

    private boolean hasSameBeneficiaryInefficient(List<Payment> paymentListToVerify) {
        boolean res = false;
        for (Payment payment : paymentListToVerify) {
            Person beneficiary = payment.getBeneficiary();
            for (Payment checkPayment : paymentList) {
                if (beneficiary.equals(checkPayment.getBeneficiary())) {
                    res = true;
                }
            }
        }
        return res;
    }

    public Stream<Payment> stream() {
        return paymentList.stream();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Payroll.class.getSimpleName() + "[", "]")
            .add("paymentList=" + paymentList)
            .toString();
    }
}
