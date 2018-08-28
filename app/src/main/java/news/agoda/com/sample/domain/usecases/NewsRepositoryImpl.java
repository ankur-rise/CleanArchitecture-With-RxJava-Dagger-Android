package news.agoda.com.sample.domain.usecases;

import javax.inject.Inject;

import io.reactivex.Observable;
import news.agoda.com.sample.data.entity.NewsEntity;
import news.agoda.com.sample.network.APIInterface;

public class NewsRepositoryImpl implements NewsRepository {

    APIInterface apiInterface;
    @Inject
    public NewsRepositoryImpl(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    @Override
    public Observable<NewsEntity> getNewsFeed() {
        return apiInterface.getNewsFeed();
    }

}
