package news.agoda.com.sample.view.ui.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.agoda.com.sample.R;
import news.agoda.com.sample.di.components.DaggerFragmentComponent;
import news.agoda.com.sample.di.components.FragmentComponent;
import news.agoda.com.sample.di.modules.ContextModule;
import news.agoda.com.sample.di.modules.NewsFeedModule;
import news.agoda.com.sample.domain.models.NewsDataModel;
import news.agoda.com.sample.utils.Utils;
import news.agoda.com.sample.view.adapters.NewsListAdapter;
import news.agoda.com.sample.view.ui.presenters.NewsListPresenter;

public class NewsListFragment extends Fragment implements NewsListView, NewsListAdapter.ListItemClickListener {

    @BindView(R.id.rlNews)
    public RecyclerView rlNews;
    @BindView(R.id.pb)
    public ProgressBar progressBar;

    @Inject
    NewsListPresenter mPresenter;

    @Inject
    NewsListAdapter newsListAdapter;
    private List<NewsDataModel> newsList;

    private OnFragmentInteractionListener listener;

    public static NewsListFragment getInstance(Bundle arg) {

        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(getActivity() instanceof OnFragmentInteractionListener)) {
            throw new IllegalStateException("Implement OnFragmentInteractionListener for communication");
        }

        listener = (OnFragmentInteractionListener) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (listener != null) {
            listener = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        ButterKnife.bind(this, view);

        FragmentComponent component = DaggerFragmentComponent.builder().newsFeedModule(new NewsFeedModule()).
                contextModule(new ContextModule(getActivity())).build();
        component.inject(this);

        mPresenter.bindView(this);

        if (Utils.checkInternetPermission(getActivity())) {
            mPresenter.getNewsFeeds();
        }

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unbind();
    }

    @Override
    public void newsResponse(List<NewsDataModel> news) {
        newsList = news;
        initAdapter();
    }

    private void initAdapter() {
        newsListAdapter.setListener(this);
        newsListAdapter.setNews(newsList);
        rlNews.setAdapter(newsListAdapter);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListItemClick(int index) {
        if(newsList!=null){
            listener.onListItemClick(newsList.get(index));
        }
    }

    /* Communication Callback between fragment and Activity*/
    public interface OnFragmentInteractionListener {
        void onListItemClick(NewsDataModel model);
    }
}
