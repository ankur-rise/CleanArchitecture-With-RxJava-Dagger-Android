package news.agoda.com.sample.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import news.agoda.com.sample.di.scopes.Singleton;

@Module
public class ContextModule {
    Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context getContext(){
        return context;
    }

}
