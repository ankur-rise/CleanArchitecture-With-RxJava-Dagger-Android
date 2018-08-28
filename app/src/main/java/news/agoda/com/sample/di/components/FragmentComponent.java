package news.agoda.com.sample.di.components;

import dagger.Component;
import news.agoda.com.sample.di.modules.ContextModule;
import news.agoda.com.sample.di.modules.NewsFeedModule;
import news.agoda.com.sample.di.scopes.Singleton;
import news.agoda.com.sample.network.APIInterface;
import news.agoda.com.sample.view.ui.fragments.NewsDetailFragment;
import news.agoda.com.sample.view.ui.fragments.NewsListFragment;

@Singleton
@Component(modules = {ContextModule.class, NewsFeedModule.class})
public interface FragmentComponent {

    void inject(NewsListFragment fragment);
    void inject(NewsDetailFragment fragment);

}
