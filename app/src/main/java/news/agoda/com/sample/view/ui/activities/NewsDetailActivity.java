package news.agoda.com.sample.view.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import news.agoda.com.sample.R;
import news.agoda.com.sample.view.ui.fragments.NewsDetailFragment;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle arguments = getIntent().getExtras();
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(arguments);
        getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}