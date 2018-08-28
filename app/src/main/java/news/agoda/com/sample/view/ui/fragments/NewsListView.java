package news.agoda.com.sample.view.ui.fragments;

import java.util.List;

import news.agoda.com.sample.domain.models.NewsDataModel;
import news.agoda.com.sample.view.ui.view.BaseView;

public interface NewsListView extends BaseView {

    void newsResponse(List<NewsDataModel> news);

    void showProgress();

    void hideProgress();

    void onError(String message);

}
