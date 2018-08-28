package news.agoda.com.sample.network;

import io.reactivex.Observable;
import news.agoda.com.sample.data.entity.NewsEntity;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("nl6jh")
    Observable<NewsEntity> getNewsFeed();
}
