package pl.store.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.store.R;

public class ManagerActivity extends AppCompatActivity {

    Button addProductButton, editProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" GeekMarket - Manager ");
        actionBar.setIcon(R.mipmap.geek_market_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        addProductButton = (Button) findViewById(R.id.addProductButton);
        editProductButton = (Button) findViewById(R.id.editProductButton);

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });

        editProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, EditProductActivity.class);
                startActivity(intent);
            }
        });

    }
}
