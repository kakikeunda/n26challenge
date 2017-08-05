package carlos.unda.n26challenge.utils;

import carlos.unda.n26challenge.model.Transaction;

import java.util.Comparator;

public class CustomTransactionComparator implements Comparator<Transaction> {

    public int compare(Transaction o1, Transaction o2) {
        return o1.getAmount().compareTo(o2.getAmount());
    }
}

