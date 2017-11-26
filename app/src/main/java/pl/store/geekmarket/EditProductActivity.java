package pl.store.geekmarket;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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


    }

    public void btnSubmit(View v) {

    }
}
