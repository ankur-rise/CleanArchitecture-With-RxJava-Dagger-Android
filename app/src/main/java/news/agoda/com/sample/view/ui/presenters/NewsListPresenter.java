package news.agoda.com.sample.view.ui.presenters;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import news.agoda.com.sample.data.entity.NewsEntity;
import news.agoda.com.sample.domain.mapper.NewsEntityDataModelMapper;
import news.agoda.com.sample.domain.usecases.NewsInteractorImpl;
import news.agoda.com.sample.view.ui.fragments.NewsListView;

public class NewsListPresenter extends BasePresenter<NewsListView> {
    private NewsInteractorImpl interactor;
    private NewsEntityDataModelMapper mapper;

    @Inject
    public NewsListPresenter(NewsInteractorImpl interactor, NewsEntityDataModelMapper mapper) {
        this.interactor = interactor;
        this.mapper = mapper;

    }

    public void getNewsFeeds() {
        view.showProgress();
        interactor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<NewsEntity>() {
            @Override
            public void accept(NewsEntity newsEntity) throws Exception {
                if (view != null) {
                    view.hideProgress();
                    view.newsResponse(mapper.getNewsDataModel(newsEntity.getResults()));
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                view.hideProgress();
                view.onError(throwable.getMessage());
            }
        });
    }
}
