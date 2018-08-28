package news.agoda.com.sample.view.ui.presenters;

import news.agoda.com.sample.view.ui.view.BaseView;

public class BasePresenter<V extends BaseView> {
    V view;

    public void bindView(V view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

}
