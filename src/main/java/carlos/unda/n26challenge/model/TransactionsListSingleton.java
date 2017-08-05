package carlos.unda.n26challenge.model;

import carlos.unda.n26challenge.utils.CustomTransactionComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TransactionsListSingleton {
    private static TransactionsListSingleton instance;
    private static ArrayList<Transaction> transactionsList = null;

    public static TransactionsListSingleton getInstance() {
        if(transactionsList == null){
            instance = new TransactionsListSingleton();
        }
        return instance;
    }

    private TransactionsListSingleton(){
        transactionsList = new ArrayList<Transaction>();
    }

    public ArrayList<Transaction> getTransactionsList(){
        return transactionsList;
    }

    public void addToList(Transaction value){
        transactionsList.add(value);
    }

    public Statistics calculateStatistics(){
        List<Transaction> transactions = new ArrayList<Transaction>();
        double sum = 0;
        for (Transaction transaction : transactionsList) {
            if (new Date().getTime() - transaction.getTimestamp() <= 60000) {
                transactions.add(transaction);
                sum += transaction.getAmount();
            }
        }
        if (transactions.size() == 0) {
            return new Statistics(0,0,0,0,0);
        }else{
            Collections.sort(transactions, new CustomTransactionComparator());

            int count = transactions.size();
            double min = transactions.get(0).getAmount();
            double max = transactions.get(count - 1).getAmount();
            double avg = sum / count;
            return new Statistics(sum, avg, max, min, count);
        }
    }
}
