package pl.store.geekmarket;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("GeekMarket ");
        actionBar.setIcon(R.mipmap.geek_market_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId()) {
            case R.id.products:
                Toast.makeText(this, "Clicked Products!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ProductListActivity.class));
                break;

            case R.id.manager:
                Toast.makeText(this, "Clicked Manager!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ManagerActivity.class));
                break;

            case R.id.map:
                Toast.makeText(this, "Clicked Map!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MapsActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
