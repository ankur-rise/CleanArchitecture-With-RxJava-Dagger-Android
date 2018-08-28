package news.agoda.com.sample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;
import news.agoda.com.sample.data.entity.NewsEntity;
import news.agoda.com.sample.domain.mapper.NewsEntityDataModelMapper;
import news.agoda.com.sample.domain.usecases.NewsInteractorImpl;
import news.agoda.com.sample.view.ui.fragments.NewsListView;
import news.agoda.com.sample.view.ui.presenters.NewsListPresenter;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NewsListPresenterTest {


    private NewsListPresenter presenter;

    @Mock
    private NewsListView view;
    @Mock
    private NewsInteractorImpl interactor;

    private NewsEntityDataModelMapper mapper;

    private TestScheduler testScheduler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mapper = new NewsEntityDataModelMapper();
        presenter = new NewsListPresenter(interactor, mapper);
        presenter.bindView(view);

        testScheduler = new TestScheduler();
        RxJavaPlugins.setInitIoSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return testScheduler;
            }
        });
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return testScheduler;
            }
        });
    }

    @Test
    public void testGetNewsFeeds() {
        NewsEntity newsEntity = DummyNewsEntitiy.parseResponse(DummyResponse.NEWS_LIST_RESPONSE);
        Observable<NewsEntity> responseBodyObservable = Observable.just(newsEntity);
        when(interactor.execute()).thenReturn(responseBodyObservable);


        presenter.getNewsFeeds();
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS);
        verify(view).showProgress();
        verify(view).hideProgress();
        verify(view).newsResponse(anyList());


    }

}
