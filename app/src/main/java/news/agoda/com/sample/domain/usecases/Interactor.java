package news.agoda.com.sample.domain.usecases;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface Interactor<T> {
    Observable<T> execute();
}
