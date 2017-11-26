package pl.store.geekmarket;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import pl.store.database.DatabaseManager;

public class ProductListActivity extends AppCompatActivity {

    TextView tvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" GeekMarket - Lista produktów ");
        actionBar.setIcon(R.mipmap.geek_market_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvProducts = (TextView) findViewById(R.id.textView);
        try {
            DatabaseManager db = new DatabaseManager(this);
            db.open();
            tvProducts.setText(db.getData());
            db.close();
        } catch (Exception e ) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


}
