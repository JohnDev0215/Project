package com.amazon.music.connect.feed;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.connect.R$id;
import com.amazon.music.connect.R$layout;
import com.amazon.music.connect.feed.FeedEvent;
import com.amazon.music.connect.feed.FeedItem;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.picassoimageloader.CircleTransformation;
import com.amazon.music.picassoimageloader.ImageRoundCornerTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class FeedItemAdapter extends PagedListAdapter<FeedItem, RowViewHolder> {
    private static final String TAG = "FeedItemAdapter";
    public static DiffUtil.ItemCallback<FeedItem> diffCallback = new DiffUtil.ItemCallback<FeedItem>() { // from class: com.amazon.music.connect.feed.FeedItemAdapter.1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(FeedItem feedItem, FeedItem feedItem2) {
            return feedItem.id == feedItem2.id;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(FeedItem feedItem, FeedItem feedItem2) {
            return feedItem.toString().equals(feedItem2.toString());
        }
    };
    private ViewGroup.LayoutParams feedItemRowLayoutParams;
    private final FeedViewModel feedViewModel;

    public static class RowViewHolder extends RecyclerView.ViewHolder {
        public ImageView actionIcon;
        public ImageView image;
        public TextView tag;
        public TextView timeAgo;
        public TextView title;
        public View view;

        public RowViewHolder(View view) {
            super(view);
            this.view = view;
            this.title = (TextView) view.findViewById(R$id.title);
            this.tag = (TextView) view.findViewById(R$id.tag);
            this.timeAgo = (TextView) view.findViewById(R$id.time_ago);
            this.image = (ImageView) view.findViewById(R$id.main_image);
            this.actionIcon = (ImageView) view.findViewById(R$id.action_icon);
        }
    }

    public FeedItemAdapter(FeedViewModel feedViewModel) {
        super(diffCallback);
        this.feedViewModel = feedViewModel;
        this.feedItemRowLayoutParams = new ViewGroup.LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RowViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RowViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.feed_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RowViewHolder rowViewHolder, int i) {
        try {
            FeedItem item = getItem(i);
            if (item == null) {
                return;
            }
            rowViewHolder.view.setOnClickListener(null);
            rowViewHolder.tag.setVisibility(8);
            rowViewHolder.itemView.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = this.feedItemRowLayoutParams;
            layoutParams.height = -2;
            rowViewHolder.itemView.setLayoutParams(layoutParams);
            if (item.linkUrl != null && item.title != null) {
                drawGenericEvent(rowViewHolder, item);
            } else {
                FeedItem.Type type = item.type;
                if (type == FeedItem.Type.FRIEND_FOLLOW) {
                    drawCustomerFollowEvent(rowViewHolder, item);
                } else if (type == FeedItem.Type.ARTIST_NEW_RELEASE) {
                    drawArtistNewReleaseEvent(rowViewHolder, item);
                } else {
                    Log.e(TAG, "Unsupported FeedItemType");
                }
            }
            if (item.viewed) {
                return;
            }
            FeedMetrics.emitItemViewMetric(item.baseItemId, String.valueOf(item.typeId), i + 1);
            item.viewed = true;
        } catch (Exception e) {
            Log.e(TAG, "Encountered exception while binding FeedItem.", e);
        }
    }

    private void drawCustomerFollowEvent(RowViewHolder rowViewHolder, final FeedItem feedItem) throws JSONException {
        JSONObject jSONObject = feedItem.entityMetaData;
        String strOptString = "Someone";
        if (jSONObject != null) {
            strOptString = jSONObject.optString("name", "Someone");
            final String strOptString2 = jSONObject.optString("profileId", null);
            if (strOptString2 != null) {
                rowViewHolder.view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.connect.feed.FeedItemAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        FeedItem feedItem2 = feedItem;
                        FeedMetrics.emitItemClickMetric(feedItem2.baseItemId, feedItem2.id, String.valueOf(feedItem2.typeId));
                        FeedItemAdapter.this.feedViewModel.getFeedEventLiveData().postValue(new FeedEvent(FeedEvent.Type.FOLLOW_ITEM_CLICKED, strOptString2, null));
                    }
                });
            }
        }
        Picasso.get().load(feedItem.imageUrl).transform(new CircleTransformation()).into(rowViewHolder.image);
        rowViewHolder.timeAgo.setText(feedItem.timeAgo);
        rowViewHolder.title.setText(strOptString + " started following you.");
    }

    private void drawArtistNewReleaseEvent(RowViewHolder rowViewHolder, final FeedItem feedItem) throws JSONException {
        String strOptString;
        JSONObject jSONObject = feedItem.baseItemMetaData;
        JSONObject jSONObject2 = feedItem.entityMetaData;
        String strOptString2 = "Album";
        if (jSONObject != null) {
            final String strOptString3 = jSONObject.optString("albumAsin", null);
            strOptString2 = jSONObject.optString("albumName", "Album");
            final String strOptString4 = jSONObject.optString(ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, null);
            strOptString = jSONObject.optString("trackName", "Track");
            if (strOptString3 != null) {
                rowViewHolder.view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.connect.feed.FeedItemAdapter.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        FeedItem feedItem2 = feedItem;
                        FeedMetrics.emitItemClickMetric(feedItem2.baseItemId, feedItem2.id, String.valueOf(feedItem2.typeId));
                        FeedItemAdapter.this.feedViewModel.getFeedEventLiveData().postValue(new FeedEvent(FeedEvent.Type.RELEASE_ITEM_CLICKED, strOptString3, strOptString4));
                    }
                });
            }
        } else {
            strOptString = "Track";
        }
        String strOptString5 = jSONObject2 != null ? jSONObject2.optString("name", "Artist") : "Artist";
        if (strOptString == "Track") {
            strOptString = strOptString2;
        }
        Picasso.get().load(feedItem.imageUrl).transform(new ImageRoundCornerTransformation()).into(rowViewHolder.image);
        rowViewHolder.timeAgo.setText(feedItem.timeAgo);
        rowViewHolder.tag.setText("New Release");
        rowViewHolder.tag.setVisibility(0);
        rowViewHolder.title.setText(strOptString5 + " - " + strOptString);
    }

    private void drawGenericEvent(RowViewHolder rowViewHolder, final FeedItem feedItem) throws JSONException {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.music.connect.feed.FeedItemAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FeedItem feedItem2 = feedItem;
                FeedMetrics.emitItemClickMetric(feedItem2.baseItemId, feedItem2.id, String.valueOf(feedItem2.typeId));
                FeedItemAdapter.this.feedViewModel.getFeedEventLiveData().postValue(new FeedEvent(FeedEvent.Type.GENERIC_URL_ITEM_CLICKED, feedItem.linkUrl, null));
            }
        };
        String str = feedItem.tag;
        if (str != null) {
            rowViewHolder.tag.setText(str);
            rowViewHolder.tag.setVisibility(0);
        }
        Transformation circleTransformation = new CircleTransformation();
        FeedItem.Type type = feedItem.type;
        if (type == FeedItem.Type.ARTIST_NEW_RELEASE || type == FeedItem.Type.FOLLOWED_CUSTOMER_PLAYLIST_UPDATE || type == FeedItem.Type.FULL_CATALOG_NEW_PLAYLIST_NOTIFICATION) {
            circleTransformation = new ImageRoundCornerTransformation();
        }
        Picasso.get().load(feedItem.imageUrl).transform(circleTransformation).into(rowViewHolder.image);
        rowViewHolder.timeAgo.setText(feedItem.timeAgo);
        rowViewHolder.title.setText(feedItem.title);
        rowViewHolder.view.setOnClickListener(onClickListener);
    }
}
