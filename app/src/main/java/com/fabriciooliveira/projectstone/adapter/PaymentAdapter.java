package com.fabriciooliveira.projectstone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fabriciooliveira.projectstone.CreditCardActivity;
import com.fabriciooliveira.projectstone.R;
import com.fabriciooliveira.projectstone.database.CardTransactionDB;
import com.fabriciooliveira.projectstone.domain.CardTransaction;
import com.fabriciooliveira.projectstone.domain.Payment;

import java.util.ArrayList;

/**
 * Created by fabriciooliveira on 12/26/15.
 */
public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolderPayment>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Payment> mListPayment = new ArrayList<Payment>();
    private CardTransactionDB mCardTransactionDB;


    public PaymentAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mCardTransactionDB = new CardTransactionDB(mContext);
        mListPayment = mCardTransactionDB.getPaymentList();
    }


    @Override
    public ViewHolderPayment onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.payment_line, parent, false);
        ViewHolderPayment viewHolderPayment = new ViewHolderPayment(view);

        return viewHolderPayment;
    }

    @Override
    public void onBindViewHolder(ViewHolderPayment holder, final int position) {
        final Payment currentPayment = mListPayment.get(position);

        holder.mName.setText("Nome: " + currentPayment.getName());
        holder.mValue.setText("Valor da Compra: " + currentPayment.getValue());

    }

    @Override
    public int getItemCount() {
        return mListPayment.size();
    }

    static class ViewHolderPayment extends RecyclerView.ViewHolder{

        private TextView mName;
        private TextView mValue;

        public ViewHolderPayment(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.name);
            mValue = (TextView) itemView.findViewById(R.id.value);
        }

    }
}
