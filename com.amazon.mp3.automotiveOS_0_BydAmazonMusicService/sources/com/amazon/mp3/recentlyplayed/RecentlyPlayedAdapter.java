package com.amazon.mp3.recentlyplayed;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.home.BrowseRecentItemPlaybackProvider;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.util.ImageUrlModifier;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.DownloadProgressBadgeView;
import com.amazon.mp3.view.RecentlyPlayedItemView;
import com.amazon.music.picassoimageloader.CircleTransformation;
import com.amazon.music.picassoimageloader.ImageRoundCornerTransformation;
import com.amazon.music.ui.model.ArtworkFrameModel$PlayState;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedAdapter extends ArrayAdapter<RecentlyPlayedItem> implements View.OnClickListener {
    private Context mContext;
    private Map<Uri, Integer> mDownloadStateMap;
    private int mImageWidth;
    private OnCancelDownloadButtonClickListener mOnCancelDownloadClickListener;
    private final RecentlyPlayedItemBinder mRecentlyPlayedItemBinder;
    private final boolean mShouldShowPrimeBadging;

    public interface OnCancelDownloadButtonClickListener {
        void onCancelDownloadClick(RecentlyPlayedItem item);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public RecentlyPlayedAdapter(Context context, List<RecentlyPlayedItem> items, RecentlyPlayedItemBinder recentlyPlayedItemBinder, int imageWidth, boolean shouldShowPrimeBadging) {
        super(context, -1, items);
        this.mContext = context;
        this.mImageWidth = imageWidth;
        this.mShouldShowPrimeBadging = shouldShowPrimeBadging;
        this.mRecentlyPlayedItemBinder = recentlyPlayedItemBinder;
    }

    public void setDownloadStateMap(Map<Uri, Integer> downloadStateMap) {
        this.mDownloadStateMap = downloadStateMap;
    }

    public void setOnCancelDownloadClickListener(OnCancelDownloadButtonClickListener onCancelDownloadClickListener) {
        this.mOnCancelDownloadClickListener = onCancelDownloadClickListener;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.recently_played_item, parent, false);
        }
        if (convertView instanceof RecentlyPlayedItemView) {
            RecentlyPlayedItemView recentlyPlayedItemView = (RecentlyPlayedItemView) convertView;
            RecentlyPlayedItem recentlyPlayedItem = (RecentlyPlayedItem) getItem(position);
            if (recentlyPlayedItem != ((RecentlyPlayedItem) recentlyPlayedItemView.getTag())) {
                recentlyPlayedItemView.setTag(recentlyPlayedItem);
                initView(recentlyPlayedItem, recentlyPlayedItemView);
                DownloadProgressBadgeView downloadBadgeView = recentlyPlayedItemView.getDownloadBadgeView();
                downloadBadgeView.setTag(Integer.valueOf(position));
                downloadBadgeView.setOnClickListener(this);
            }
            updatePlaystateIcon(recentlyPlayedItem, recentlyPlayedItemView);
        }
        return convertView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v instanceof DownloadProgressBadgeView) {
            int downloadState = ((DownloadProgressBadgeView) v).getDownloadState();
            if ((downloadState == 4 || downloadState == 3 || downloadState == 1) && this.mOnCancelDownloadClickListener != null) {
                this.mOnCancelDownloadClickListener.onCancelDownloadClick((RecentlyPlayedItem) getItem(((Integer) v.getTag()).intValue()));
            }
        }
    }

    private void initView(RecentlyPlayedItem item, RecentlyPlayedItemView view) {
        Drawable drawable;
        if (RecentlyPlayedItem.Type.ALL_SONGS == item.getItemType() && item.isShuffleOn()) {
            view.getTitleTextView().setText(R.string.dmusic_recently_played_various);
        } else {
            view.setTitle(item.getTitle());
        }
        view.setSecondaryTitle(this.mRecentlyPlayedItemBinder.getSecondaryTitle(item));
        boolean z = true;
        view.showPrimeSash(this.mShouldShowPrimeBadging && item.isPrime() && item.getItemType() != RecentlyPlayedItem.Type.STATION);
        if (item.getContentUnavailableReason() != null && (item.getContentUnavailableReason() != ContentUnavailableReason.CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION || !UserSubscriptionUtil.isNightwingOnly())) {
            z = false;
        }
        view.setContentEnabled(z);
        updateDownloadBadgeView(view, item.getContentUri());
        ImageView thumbnailImageView = view.getThumbnailImageView();
        if (item.getItemType() == RecentlyPlayedItem.Type.STATION) {
            drawable = this.mContext.getResources().getDrawable(R.drawable.station_outline);
            thumbnailImageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.station_placeholder));
        } else {
            if (UserSubscriptionUtil.isNightwingOnly()) {
                if (item.getItemType() == RecentlyPlayedItem.Type.PRIME_PLAYLIST && item.getCatalogStatusTiers().getIsFree() && item.getCatalogStatusTiers().getIsFreeOnDemandPlayable()) {
                    drawable = this.mContext.getResources().getDrawable(R.drawable.ic_shuffle_ring);
                } else if (item.isAllOwned()) {
                    drawable = this.mContext.getResources().getDrawable(R.drawable.play_outline);
                } else {
                    drawable = this.mContext.getResources().getDrawable(R.drawable.station_outline);
                }
            } else {
                drawable = this.mContext.getResources().getDrawable(R.drawable.play_outline);
            }
            thumbnailImageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.placeholder_sm));
        }
        view.setPlayIconDrawable(drawable);
        initArtwork(item, view);
        initContentDescription(item, view);
    }

    private void initArtwork(RecentlyPlayedItem item, RecentlyPlayedItemView view) {
        ImageView thumbnailImageView = view.getThumbnailImageView();
        String artworkImageUrl = item.getArtworkImageUrl();
        if (artworkImageUrl != null) {
            if (!ImageUrlModifier.containsModifiers(artworkImageUrl)) {
                artworkImageUrl = ImageUrlModifier.with(artworkImageUrl).scaleToLongest(this.mImageWidth).toUrl();
            }
            thumbnailImageView.setTag(artworkImageUrl);
            int i = item.getItemType() == RecentlyPlayedItem.Type.STATION ? R.drawable.station_placeholder : R.drawable.placeholder_sm;
            if (item.getItemType() == RecentlyPlayedItem.Type.ARTIST || item.getItemType() == RecentlyPlayedItem.Type.PRIME_ARTIST) {
                thumbnailImageView.setBackgroundColor(0);
                Picasso.get().load(artworkImageUrl).transform(new CircleTransformation()).into(thumbnailImageView);
                return;
            } else {
                RequestCreator requestCreatorLoad = Picasso.get().load(artworkImageUrl);
                int i2 = this.mImageWidth;
                requestCreatorLoad.resize(i2, i2).placeholder(i).transform(new ImageRoundCornerTransformation()).into(thumbnailImageView);
                return;
            }
        }
        Picasso.get().cancelRequest(thumbnailImageView);
        this.mRecentlyPlayedItemBinder.setArtworkDrawable(thumbnailImageView, this.mImageWidth, item);
    }

    private void updateDownloadBadgeView(RecentlyPlayedItemView view, Uri contentUri) {
        DownloadProgressBadgeView downloadBadgeView = view.getDownloadBadgeView();
        Map<Uri, Integer> map = this.mDownloadStateMap;
        if (map != null) {
            Integer num = map.get(contentUri);
            if (num == null) {
                num = -1;
            }
            downloadBadgeView.setDownloadState(num.intValue());
            boolean z = (num.intValue() == 5 || num.intValue() == -1 || num.intValue() == 0) ? false : true;
            view.showPlayIcon(!z);
            downloadBadgeView.setVisibility(z ? 0 : 8);
        }
    }

    private void initContentDescription(RecentlyPlayedItem item, RecentlyPlayedItemView view) {
        String title = item.getItemType() == RecentlyPlayedItem.Type.ALL_SONGS ? "" : item.getTitle();
        view.setContentDescription(getContext().getString(R.string.dmusic_content_description_recently_played_item, this.mRecentlyPlayedItemBinder.getSecondaryTitle(item)) + title);
    }

    private void updatePlaystateIcon(RecentlyPlayedItem item, RecentlyPlayedItemView itemView) {
        ArtworkFrameModel$PlayState playstate = BrowseRecentItemPlaybackProvider.getPlaystate(item);
        if (playstate == ArtworkFrameModel$PlayState.STOPPED) {
            itemView.showPlaystateIcon(false);
            itemView.showPlayIcon(true);
            return;
        }
        itemView.showPlaystateIcon(true);
        itemView.setIsPlaying(playstate == ArtworkFrameModel$PlayState.PLAYING);
        itemView.showPlayIcon(false);
        if (item.getItemType() == RecentlyPlayedItem.Type.ARTIST || item.getItemType() == RecentlyPlayedItem.Type.PRIME_ARTIST) {
            itemView.setCircularBackground();
        }
    }
}
