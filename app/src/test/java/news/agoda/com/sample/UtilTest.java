package news.agoda.com.sample;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import news.agoda.com.sample.data.entity.NewsEntity;
import news.agoda.com.sample.domain.mapper.NewsEntityDataModelMapper;
import news.agoda.com.sample.domain.models.NewsDataModel;
import news.agoda.com.sample.utils.Utils;

import static junit.framework.Assert.assertEquals;

public class UtilTest {

    private NewsDataModel model;
    private String image_url = "http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-articleInline.jpg";
    @Before
    public void setup() {
        NewsEntityDataModelMapper mapper = new NewsEntityDataModelMapper();
        NewsEntity entity = DummyNewsEntitiy.parseResponse(DummyResponse.NEWS_LIST_RESPONSE);
        model = mapper.getNewsDataModel(entity.getResults()).get(0);
    }


    @Test
    public void testImageUrl() {

        String url = Utils.getImageUrlFromResult(model);
        assertEquals(url, image_url);

    }

}
