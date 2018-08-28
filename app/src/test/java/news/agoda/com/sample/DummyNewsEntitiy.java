package news.agoda.com.sample;

import com.google.gson.Gson;

import news.agoda.com.sample.data.entity.NewsEntity;

public class DummyNewsEntitiy {

    public static NewsEntity parseResponse(String response){
        Gson gson = new Gson();
        return gson.fromJson(response, NewsEntity.class);
    }

}
