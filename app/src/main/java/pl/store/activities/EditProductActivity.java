package pl.store.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import pl.store.R;
import pl.store.model.Product;

public class EditProductActivity extends AppCompatActivity {

    TextView productID;
    EditText etProductEditName, etProductEditPrice, etProductEditDesc;
    Spinner categoryEditSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_product_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" GeekMarket - Edytuj produkt ");
        actionBar.setIcon(R.mipmap.geek_market_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        productID = (TextView) findViewById(R.id.productID);
        etProductEditName = (EditText) findViewById(R.id.etProductEditName);
        etProductEditPrice = (EditText) findViewById(R.id.etProductEditPrice);
        etProductEditDesc = (EditText) findViewById(R.id.etProductEditDesc);
        categoryEditSpinner = (Spinner) findViewById(R.id.categoryEditSpinner);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Product itemToEdit = (Product) bundle.getSerializable("itemToEdit");

        productID.setText(String.valueOf(itemToEdit.getId()));
        etProductEditName.setText(itemToEdit.getProductName());
        etProductEditPrice.setText(itemToEdit.getProductPrice().toString());
        etProductEditDesc.setText(itemToEdit.getProductDesc());




    }

    public void btnSubmit(View v) {

    }
}
