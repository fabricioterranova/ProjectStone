package com.fabriciooliveira.projectstone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.fabriciooliveira.projectstone.adapter.PaymentAdapter;
import com.fabriciooliveira.projectstone.adapter.ProductAdapter;
import com.fabriciooliveira.projectstone.database.CardTransactionDB;
import com.fabriciooliveira.projectstone.domain.CardTransaction;
import com.fabriciooliveira.projectstone.domain.Payment;

import java.util.ArrayList;

/**
 * Created by fabriciooliveira on 12/26/15.
 */
public class PaymentActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PaymentAdapter mPaymentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle("Consulta dos Pagamentos");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_payment_id);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(PaymentActivity.this));
        mPaymentAdapter = new PaymentAdapter(PaymentActivity.this);

        mRecyclerView.setAdapter(mPaymentAdapter);


    }
}
