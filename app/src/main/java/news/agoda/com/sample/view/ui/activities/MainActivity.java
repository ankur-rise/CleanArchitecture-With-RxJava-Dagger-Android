package news.agoda.com.sample.view.ui.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import news.agoda.com.sample.R;
import news.agoda.com.sample.domain.models.NewsDataModel;
import news.agoda.com.sample.utils.Constants;
import news.agoda.com.sample.utils.Utils;
import news.agoda.com.sample.view.ui.fragments.NewsListFragment;

public class MainActivity extends AppCompatActivity implements NewsListFragment.OnFragmentInteractionListener
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addFragment();


    }

    private void addFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.container, NewsListFragment.getInstance(null)).commit();

    }

    @Override
    public void onListItemClick(NewsDataModel model) {

        Intent detailIntent = new Intent(this, NewsDetailActivity.class);
        detailIntent.putExtra(Constants.KEY_TITLE, model.getTitle());
        detailIntent.putExtra(Constants.KEY_SUMMARY, model.getAbstract());
        detailIntent.putExtra(Constants.KEY_IMAGE_URL, Utils.getImageUrlFromResult(model));
        detailIntent.putExtra(Constants.KEY_FULL_STORY, model.getUrl());
        startActivity(detailIntent);

    }
}
