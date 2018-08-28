package news.agoda.com.sample.view.ui.fragments;

import android.net.Uri;

import news.agoda.com.sample.view.ui.view.BaseView;

public interface NewsDetailView extends BaseView{
    void openFullStory(Uri storyUri);
}
