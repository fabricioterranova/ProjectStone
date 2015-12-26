package com.fabriciooliveira.projectstone;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fabriciooliveira.projectstone.adapter.ProductAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mProductAdapter = new ProductAdapter(MainActivity.this);

        mRecyclerView.setAdapter(mProductAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Projeto Stone");

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.payments:
                Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.payment, menu);

        MenuItem item = menu.findItem(R.id.payments);

        return true;
    }


}
