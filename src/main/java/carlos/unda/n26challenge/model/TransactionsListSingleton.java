package carlos.unda.n26challenge.model;

import carlos.unda.n26challenge.Utils.CustomComparator;

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

    public List<Transaction> getSixtySecondsOlderTransactions(){
        List<Transaction> transactions = new ArrayList<Transaction>();

        for (Transaction transaction : transactionsList) {
            if (new Date().getTime() - transaction.getTimestamp() <= 60000) {
                transactions.add(transaction);
            }
        }
        Collections.sort(transactions, new CustomComparator());

        return transactions;
    }

    public Statistics getStatistics(){
        List<Transaction> transactions = TransactionsListSingleton.getInstance().getSixtySecondsOlderTransactions();

        if (transactions.size() == 0){
            return new Statistics(0,0,0,0,0);
        } else{
            int count = transactions.size();
            double min = transactions.get(0).getAmount();
            double max = transactions.get(count-1).getAmount();
            double sum = 0;

            for (Transaction transaction : transactions){
                sum +=transaction.getAmount();
            }

            double avg = sum / count;
            return new Statistics(sum,avg,max,min,count);
        }
    }
}
