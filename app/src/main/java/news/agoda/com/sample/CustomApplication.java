package news.agoda.com.sample;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
