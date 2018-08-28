package news.agoda.com.sample.di.modules;

import dagger.Module;
import dagger.Provides;
import news.agoda.com.sample.di.scopes.Singleton;
import news.agoda.com.sample.network.APIInterface;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ContextModule.class)
public class NewsFeedModule {

    private static final String BASE_URL = "https://api.myjson.com/bins/";

    @Provides
    @Singleton
    public APIInterface apiClient(Retrofit retrofit){
        return retrofit.create(APIInterface.class);
    }

    @Singleton
    @Provides
    public Retrofit retrofit(GsonConverterFactory jacksonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(jacksonConverterFactory)
                .build();
    }

    @Singleton
    @Provides
    public RxJava2CallAdapterFactory rxJava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public GsonConverterFactory jacksonConverterFactory(){
        return GsonConverterFactory.create();
    }

}
