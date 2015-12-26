package com.fabriciooliveira.projectstone.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fabriciooliveira.projectstone.Util.Constants;
import com.fabriciooliveira.projectstone.domain.CardTransaction;
import com.fabriciooliveira.projectstone.domain.Payment;
import com.fabriciooliveira.projectstone.domain.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabriciooliveira on 12/25/15.
 */
public class CardTransactionDB extends SQLiteOpenHelper {

    public CardTransactionDB(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS TB_TRANSACTION ( ");
        query.append("ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        query.append("OWNER TEXT(45) NOT NULL, ");
        query.append("CARD_NUMBER TEXT(45) NOT NULL, ");
        query.append("EXPIRATION_YEAR TEXT(15) NOT NULL, ");
        query.append("EXPIRATION_MONTH TEXT(15) NOT NULL, ");
        query.append("CARD_FLAG TEXT(45) NOT NULL, ");
        query.append("CVV TEXT(4) NOT NULL, ");
        query.append("TOTAL INTEGER(4) NOT NULL)");

        db.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void insertTransaction(CardTransaction cardTransaction){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("OWNER", cardTransaction.getOwner());
        values.put("CARD_NUMBER", cardTransaction.getCardNumber());
        values.put("EXPIRATION_YEAR", cardTransaction.getExpirationYear());
        values.put("EXPIRATION_MONTH", cardTransaction.getExpirationMonth());
        values.put("CARD_FLAG", cardTransaction.getCardFlag());
        values.put("CVV", cardTransaction.getcVV());
        values.put("TOTAL", String.valueOf(cardTransaction.getTotal()));

        db.insert("TB_TRANSACTION", null, values);

    }

    public ArrayList<Payment> getPaymentList(){
        ArrayList<Payment> paymentList = new ArrayList<Payment>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(true, "TB_TRANSACTION", null, null, null, null, null, "ID", null, null);

        while(cursor.moveToNext()){
            Payment payment = new Payment();
            payment.setName(cursor.getString(1));
            payment.setValue(cursor.getString(7));

            paymentList.add(payment);
        }

        return paymentList;
    }

//    public List<CardTransaction> getCardTransactionList(){
//        List<CardTransaction> cardTransactionList = new ArrayList<CardTransaction>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(true, "TB_TRANSACTION", null, null, null, null, null, "ID", null, null);
//
//        while(cursor.moveToNext()){
//            CardTransaction cardTransaction = new CardTransaction();
//            cardTransaction.setOwner(cursor.getString(1));
//            cardTransaction.setCardNumber(cursor.getString(2));
//            cardTransaction.setExpirationYear(cursor.getString(2));
//            cardTransaction.setExpirationMonth(cursor.getString(3));
//            cardTransaction.setCardFlag(cursor.getString(4));
//            cardTransaction.setcVV(cursor.getString(5));
//            cardTransaction.setTotal(cursor.getInt(6));
//
//            cardTransactionList.add(cardTransaction);
//        }
//
//        return cardTransactionList;
//    }


}
