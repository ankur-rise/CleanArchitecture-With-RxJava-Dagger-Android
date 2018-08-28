package news.agoda.com.sample;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import news.agoda.com.sample.data.entity.NewsEntity;
import news.agoda.com.sample.domain.mapper.NewsEntityDataModelMapper;
import news.agoda.com.sample.domain.models.MultiMediumDataModel;
import news.agoda.com.sample.domain.models.NewsDataModel;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class NewsEntityDataModelMapperTest {

    private NewsEntityDataModelMapper mapper;
    private NewsEntity entity;

    @Before
    public void setup() {
        mapper = new NewsEntityDataModelMapper();
        entity = DummyNewsEntitiy.parseResponse(DummyResponse.NEWS_LIST_RESPONSE);
    }

    @Test
    public void testParse() {
        NewsEntity entity = DummyNewsEntitiy.parseResponse(DummyResponse.NEWS_LIST_RESPONSE);
        assertNotNull(entity);
    }

    @Test
    public void testGetNewsDataModel() {
        List<NewsDataModel> list = mapper.getNewsDataModel(entity.getResults());
        assert (!list.isEmpty());
        assertEquals(24, list.size());
    }

    @Test
    public void testGetMultiMediumList() {
        List<MultiMediumDataModel> list = mapper.getMultiMediumList(entity.getResults().get(0).getMultimedia());
        assertNotNull(list);
        assertEquals(4, list.size());
    }


}
