package news.agoda.com.sample.view.ui.presenters;

import android.net.Uri;

import javax.inject.Inject;

import news.agoda.com.sample.view.ui.fragments.NewsDetailView;

public class NewsDetailPresenter extends BasePresenter<NewsDetailView>{

    @Inject
    public NewsDetailPresenter() {
    }

    public void onFullStoryButtonClicked(String storyURL) {
        Uri storyUri = null;
        if (storyURL != null){
            storyUri = Uri.parse(storyURL);
        }
        if(view != null){
            view.openFullStory(storyUri);
        }
    }
}
