package news.agoda.com.sample.view.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.agoda.com.sample.R;
import news.agoda.com.sample.domain.models.MultiMediumDataModel;
import news.agoda.com.sample.domain.models.NewsDataModel;

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public interface ListItemClickListener {
        void onListItemClick(int index);
    }

    private Context mContext;
    private List<NewsDataModel> mNewsFeedResults;
    private ListItemClickListener mClickListener;

    @Inject
    public NewsListAdapter(Context context) {
        this.mContext = context;
    }

    public void setNews(List<NewsDataModel> results) {
        mNewsFeedResults = results;
    }

    public void setListener(ListItemClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.news_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        NewsDataModel mediaEntity = mNewsFeedResults.get(position);
        viewHolder.newsTitle.setText(mediaEntity.getTitle());
        List<MultiMediumDataModel> multiMediumDataModels = mediaEntity.getMultimedia();
        if (multiMediumDataModels != null && !multiMediumDataModels.isEmpty()) {
            String thumbnailURL;
            thumbnailURL = multiMediumDataModels.get(0).getUrl();
            Uri uri = Uri.parse(thumbnailURL);
            viewHolder.imageView.setImageURI(uri);
        }
    }

    @Override
    public int getItemCount() {
        return mNewsFeedResults != null ? mNewsFeedResults.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        @BindView(R.id.tv_title)
        TextView newsTitle;
        @BindView(R.id.iv)
        SimpleDraweeView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mView = itemView;
            this.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onListItemClick(getAdapterPosition());
                }
            });
        }
    }
}
