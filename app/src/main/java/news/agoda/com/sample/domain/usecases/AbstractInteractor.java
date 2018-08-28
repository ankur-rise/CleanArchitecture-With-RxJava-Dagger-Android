package news.agoda.com.sample.domain.usecases;

import io.reactivex.Observable;

public abstract class AbstractInteractor<T> implements Interactor<T> {

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public AbstractInteractor() {
    }

    public abstract Observable<T> run();

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public Observable<T> execute() {

        // mark this interactor as running
        this.mIsRunning = true;

        // start running this interactor in a background thread
        // run the main logic
        Observable<T> result = run();
        // mark it as finished
        onFinished();
        return result;
    }

}
