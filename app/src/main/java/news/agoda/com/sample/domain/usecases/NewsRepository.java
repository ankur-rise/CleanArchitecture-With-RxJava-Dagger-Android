package news.agoda.com.sample.domain.usecases;

import io.reactivex.Observable;
import news.agoda.com.sample.data.entity.NewsEntity;
import okhttp3.ResponseBody;

public interface NewsRepository {
    Observable<NewsEntity> getNewsFeed();
}
