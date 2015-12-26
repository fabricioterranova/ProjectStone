package com.fabriciooliveira.projectstone;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fabriciooliveira.projectstone.Util.Constants;
import com.fabriciooliveira.projectstone.Util.Mask;
import com.fabriciooliveira.projectstone.Util.MensagemUtil;
import com.fabriciooliveira.projectstone.database.CardTransactionDB;
import com.fabriciooliveira.projectstone.domain.CardTransaction;
import com.fabriciooliveira.projectstone.domain.Product;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fabriciooliveira on 12/25/15.
 */
public class CreditCardActivity extends AppCompatActivity {

    private static final String PRODUCT_EXTRA = "product";
    private static final String URL_MOCK_APIARY = "http://polls.apiblueprint.org/questions";

    private TextView mTotal;
    private EditText mCCName, mCCNumber, mCCExpirationMonth, mCCExpirationYear, mCCV;
    private Button mButtonSend;
    private Product mProduct;
    private ProgressDialog mProgressDialog;
    private CardTransactionDB mCardTransactionDB;
    private CardTransaction mCardTransaction;
    private RadioGroup rbgFlag;
    private String mFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_creditcard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProduct = getIntent().getParcelableExtra(PRODUCT_EXTRA);

        mProgressDialog = new ProgressDialog(CreditCardActivity.this);

        mCardTransactionDB = new CardTransactionDB(CreditCardActivity.this);

        loadUI();

    }

    private void loadUI() {

        mTotal = (TextView) findViewById(R.id.txt_amount);
        mCCName = (EditText) findViewById(R.id.cc_owner);
        mCCNumber = (EditText) findViewById(R.id.cc_number);
        mCCExpirationMonth = (EditText) findViewById(R.id.cc_expiration_month);
        mCCExpirationYear = (EditText) findViewById(R.id.cc_expiration_year);
        mCCV = (EditText) findViewById(R.id.cc_validation_code);
        rbgFlag = (RadioGroup) findViewById(R.id.rbg_flag);
        mButtonSend = (Button) findViewById(R.id.btn_send);

        if(mProduct.getValue() != 0.0) {
            mTotal.setText("R$ " + String.valueOf(mProduct.getValue()));
        }

        mCCNumber.addTextChangedListener(Mask.insert("####.####.####.####", mCCNumber));
        mCCExpirationMonth.addTextChangedListener(Mask.insert("##", mCCExpirationMonth));
        mCCExpirationYear.addTextChangedListener(Mask.insert("####", mCCExpirationYear));
        mCCV.addTextChangedListener(Mask.insert("####", mCCV));

        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatingFields();
            }
        });

    }

    private void validatingFields(){

        boolean validation = true;

        final String cc_hold_name = checkIfEditTextIsEmpty(mCCName);
        if(cc_hold_name == null) {
            validation = false;
        }

        final String cc_number = checkIfEditTextIsEmpty(mCCNumber, true);
        if(cc_number == null) {
            validation = false;
        }

        final String cc_expiration_month = checkIfEditTextIsEmpty(mCCExpirationMonth, true);
        if(cc_expiration_month == null) {
            validation = false;
        }

        final String cc_expiration_year = checkIfEditTextIsEmpty(mCCExpirationYear, true);
        if(cc_expiration_year == null) {
            validation = false;
        }

        final String cc_cvv = checkIfEditTextIsEmpty(mCCV);
        if(cc_cvv == null) {
            validation = false;
        }

        switch (rbgFlag.getCheckedRadioButtonId()) {
            case R.id.rbt_dinersclub:
                mFlag = Constants.DINERSCLUB;
                break;
            case R.id.rbt_mastercard:
                mFlag = Constants.MASTERCARD;
                break;
            case R.id.rbt_visa:
                mFlag = Constants.VISA;
                break;
            case R.id.rbt_amex:
                mFlag = Constants.AMEX;
                break;
        }

        mProgressDialog.setMessage(getString(R.string.label_payment));
        mProgressDialog.show();

        if(validation == false){
            MensagemUtil.addMsg(CreditCardActivity.this, getString(R.string.label_required));
            mProgressDialog.dismiss();
            return;
        }

        mCardTransaction = new CardTransaction(cc_hold_name, cc_number,cc_expiration_year, cc_expiration_month, mFlag, cc_cvv, mProduct.getValue());

        sendPostRequestToMockApiary();

//        List<CardTransaction> lista = mCardTransactionDB.getCardTransactionList();
//
//        MensagemUtil.addMsg(this, lista.toString());
    }

    private void sendPostRequestToMockApiary() {
        StringRequest verificaReq = new StringRequest(Request.Method.GET, URL_MOCK_APIARY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //SUCCESS
                        mCardTransactionDB.insertTransaction(mCardTransaction);
                        mProgressDialog.dismiss();
                        finish();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //ERROR
                MensagemUtil.addMsg(CreditCardActivity.this, error.getMessage());
                mProgressDialog.dismiss();

            }
        });

        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        verificaReq.setRetryPolicy(policy);

        AppController.getInstance().addToRequestQueue(verificaReq);
    }


    public String checkIfEditTextIsEmpty(EditText field){
        return checkIfEditTextIsEmpty(field, false);
    }

    public String checkIfEditTextIsEmpty(EditText field, boolean clear){
        if(TextUtils.isEmpty(field.getText().toString())){
            field.setError(getString(R.string.label_required));
            return null;
        }

        if(clear == false){
            return field.getText().toString();
        }else{
            if(!TextUtils.isEmpty(field.getText().toString())){
                return field.getText().toString().replaceAll("[^0-9a-zA-Z]+", "");
            }
        }

        return null;
    }
}
