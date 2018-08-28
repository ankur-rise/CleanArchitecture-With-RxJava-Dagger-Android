package news.agoda.com.sample.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import news.agoda.com.sample.data.entity.MultiMediumEntity;
import news.agoda.com.sample.data.entity.ResultEntity;
import news.agoda.com.sample.domain.models.MultiMediumDataModel;
import news.agoda.com.sample.domain.models.NewsDataModel;

public class NewsEntityDataModelMapper {

    @Inject
    public NewsEntityDataModelMapper(){

    }

    public List<NewsDataModel> getNewsDataModel(List<ResultEntity> results) {
        List<NewsDataModel> newsDataModelList = new ArrayList<>();
        if (results != null && results.size() > 0) {
            for (ResultEntity resultEntity : results) {
                NewsDataModel newsDataModel = new NewsDataModel();
                newsDataModel.setTitle(resultEntity.getTitle());
                newsDataModel.setUrl(resultEntity.getUrl());
                newsDataModel.setAbstract(resultEntity.getAbstract());
                newsDataModel.setPublishedDate(resultEntity.getPublishedDate());
                List<MultiMediumDataModel> mediumDomain = getMultiMediumList(resultEntity.getMultimedia());
                newsDataModel.setMultimedia(mediumDomain);
                newsDataModelList.add(newsDataModel);
            }
        }
        return newsDataModelList;
    }

    public List<MultiMediumDataModel> getMultiMediumList(List<MultiMediumEntity> media) {
        List<MultiMediumDataModel> mediumDomains = new ArrayList<>();
        if (media != null && media.size() > 0) {
            for (MultiMediumEntity mediaEntity : media) {
                MultiMediumDataModel mediumDomain = new MultiMediumDataModel();
                mediumDomain.setUrl(mediaEntity.getUrl());
                mediumDomain.setFormat(mediaEntity.getFormat());
                mediumDomain.setHeight(mediaEntity.getHeight());
                mediumDomain.setWidth(mediaEntity.getWidth());
                mediumDomain.setCaption(mediaEntity.getCaption());
                mediumDomain.setCopyright(mediaEntity.getCopyright());
                mediumDomains.add(mediumDomain);
            }
        }
        return mediumDomains;
    }

}
