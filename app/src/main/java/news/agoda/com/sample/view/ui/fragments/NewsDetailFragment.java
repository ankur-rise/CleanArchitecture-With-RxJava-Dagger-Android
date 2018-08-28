package news.agoda.com.sample.view.ui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import news.agoda.com.sample.R;
import news.agoda.com.sample.di.components.DaggerFragmentComponent;
import news.agoda.com.sample.di.components.FragmentComponent;
import news.agoda.com.sample.di.modules.ContextModule;
import news.agoda.com.sample.di.modules.NewsFeedModule;
import news.agoda.com.sample.utils.Constants;
import news.agoda.com.sample.view.ui.presenters.NewsDetailPresenter;

public class NewsDetailFragment extends Fragment implements NewsDetailView {

    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.news_image)
    DraweeView imageView;
    @BindView(R.id.summary_content)
    TextView summaryView;

    @Inject
    NewsDetailPresenter newsDetailPresenter;

    private String storyURL;
    private String imageURL;
    private String title;
    private String summary;

    public NewsDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(Constants.KEY_FULL_STORY)) {
            storyURL = getArguments().getString(Constants.KEY_FULL_STORY);
            title = getArguments().getString(Constants.KEY_TITLE);
            summary = getArguments().getString(Constants.KEY_SUMMARY);
            imageURL = getArguments().getString(Constants.KEY_IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_detail, container, false);
        ButterKnife.bind(this, rootView);

        FragmentComponent component = DaggerFragmentComponent.builder().newsFeedModule(new NewsFeedModule()).
                contextModule(new ContextModule(getActivity())).build();
        component.inject(this);

        titleView.setText(title);
        summaryView.setText(summary);
        if(imageURL != null) {
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(ImageRequest.fromUri(Uri.parse(imageURL)))
                    .setOldController(imageView.getController()).build();
            imageView.setController(draweeController);
        }
        newsDetailPresenter.bindView(this);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        newsDetailPresenter.unbind();
    }

    @OnClick(R.id.full_story_link)
    public void onFullStoryClicked(View view) {
        newsDetailPresenter.onFullStoryButtonClicked(storyURL);
    }

    @Override
    public void openFullStory(Uri storyURI){
        if(storyURI != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(storyURI);
            startActivity(intent);
        }else{
            Toast.makeText(getActivity(), "Incorrect url", Toast.LENGTH_SHORT).show();
        }
    }

}
