package news.agoda.com.sample.domain.usecases;

import javax.inject.Inject;

import io.reactivex.Observable;
import news.agoda.com.sample.data.entity.NewsEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsInteractorImpl extends AbstractInteractor<NewsEntity>{
    private NewsRepository mFeedRepository;

    @Inject
    public NewsInteractorImpl(NewsRepositoryImpl feedRepository) {
        super();
        mFeedRepository = feedRepository;
    }

    @Override
    public Observable<NewsEntity> run() {
        Observable<NewsEntity> obj = mFeedRepository.getNewsFeed();
        return obj;
    }
}
