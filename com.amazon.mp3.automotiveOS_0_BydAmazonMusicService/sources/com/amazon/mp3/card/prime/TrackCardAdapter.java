package com.amazon.mp3.card.prime;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.animation.AddButtonAnimation;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.ArrayCardAdapter;
import com.amazon.mp3.library.adapter.DefaultTrackStateProvider;
import com.amazon.mp3.library.adapter.SongTileAdapter;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.TrackStateProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.view.lyrics.TextViewMarkerDecorator;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.search.ArtworkLoader;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.RoundedCornerImageProcessor;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.BadgeView;
import com.amazon.mp3.view.PlayNotificationIcon;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class TrackCardAdapter extends ArrayCardAdapter<PrimeTrack, ViewHolder> implements PrimeCardAdapter<PrimeTrack> {
    protected final ItemButtonListener mAddButtonListener;
    private final View.OnClickListener mAddClickListener;
    private final ArtworkLoader mArtworkLoader;
    private final boolean mFreeTierUserPlaylistEditingEnabled;
    private final boolean mIsNightwingOnly;
    private final boolean mLyricsSupported;
    private final boolean mShouldShowPrimeBadging;
    private final TrackStateProvider mStateProvider;

    public interface ItemButtonListener {
        void onClick(final View v, final PrimeTrack track, final int position);
    }

    @Override // com.amazon.mp3.card.ArrayCardAdapter, com.amazon.mp3.card.CardAdapter
    public int getOverflowMenuButtonResId() {
        return R.id.overflow_button_open;
    }

    public TrackCardAdapter(Context context, List<PrimeTrack> data, ItemButtonListener listener, boolean prefixNumbers, boolean showRecommendationReason, boolean shouldShowPrimeBadging) {
        super(context, data, R.layout.prime_search_track_line, prefixNumbers, showRecommendationReason);
        this.mAddClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.card.prime.TrackCardAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                PrimeTrack primeTrack = (PrimeTrack) v.getTag();
                ViewHolder viewHolder = (ViewHolder) ((ArrayCardAdapter) TrackCardAdapter.this).mHolders.get(primeTrack);
                if (ChildUserUtil.INSTANCE.isChildUser(((ArrayCardAdapter) TrackCardAdapter.this).mContext) && primeTrack.getIsExplicit()) {
                    return;
                }
                TrackCardAdapter.this.mAddButtonListener.onClick(v, primeTrack, viewHolder.mPosition);
                if (ContentAccessUtil.getCatalogContentUnavailableReason(primeTrack, ContentAccessUtil.ContentAccessOperation.INTERACT) == null) {
                    AddButtonAnimation.startAnimation(((ArrayCardAdapter) TrackCardAdapter.this).mContext, viewHolder.mAddButtonHolder, viewHolder.mAddButton, viewHolder.mAddDoneView, null);
                }
            }
        };
        this.mArtworkLoader = new ArtworkLoader.Builder(this.mContext).setAllowServerToScale(true).setArtworkSize(context.getResources().getDimensionPixelSize(R.dimen.song_tile_art_size)).setImageProcessor(new RoundedCornerImageProcessor(this.mContext)).build();
        this.mStateProvider = new DefaultTrackStateProvider(this.mContext);
        this.mAddButtonListener = listener;
        this.mShouldShowPrimeBadging = shouldShowPrimeBadging;
        this.mLyricsSupported = AmazonApplication.getCapabilities().isLyricsSupported();
        this.mIsNightwingOnly = UserSubscriptionUtil.isNightwingOnly();
        this.mFreeTierUserPlaylistEditingEnabled = AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
    }

    protected static class ViewHolder extends ArrayCardAdapter.CardViewHolder {
        public TextView mTrackTitle = null;
        public TextView mArtistName = null;
        public TextView mRecommendationReason = null;
        public View mAddButtonHolder = null;
        public ImageView mAddButton = null;
        public ImageView mAddDoneView = null;
        public ImageView mAlbumArtwork = null;
        public ImageView mAlbumArtworkBadge = null;
        public PrimeTrack mPrimeTrack = null;
        public int mPosition = -1;
        public BadgeView mContentEncodingBadge = null;
        public BadgeView mImmersiveContentBadge = null;

        protected ViewHolder() {
        }
    }

    @Override // com.amazon.mp3.card.CardAdapter
    public void bindView(int position, final PrimeTrack item, final View itemView) {
        ViewHolder viewHolder = new ViewHolder();
        updateItemStatus(item);
        viewHolder.mTrackTitle = (TextView) itemView.findViewById(R.id.TrackTitle);
        viewHolder.mArtistName = (TextView) itemView.findViewById(R.id.ArtistName);
        viewHolder.mRecommendationReason = (TextView) itemView.findViewById(R.id.recommendation_reason);
        if (this.mPrefixNumbers) {
            viewHolder.mTrackTitle.setText((position + 1) + ". " + item.getTitle());
        } else {
            viewHolder.mTrackTitle.setText(item.getTitle());
        }
        if (this.mShowRecommendationReason && item.getRecommendationReason() != null && !StringUtil.isNullOrWhiteSpaces(item.getRecommendationReason().getReason())) {
            viewHolder.mRecommendationReason.setText(item.getRecommendationReason().getReason());
            viewHolder.mRecommendationReason.setVisibility(0);
        }
        viewHolder.mArtistName.setText(item.getArtist());
        viewHolder.mAddButtonHolder = itemView.findViewById(R.id.add_button_holder);
        viewHolder.mAddButton = (ImageView) itemView.findViewById(R.id.AddButton);
        viewHolder.mAddDoneView = (ImageView) itemView.findViewById(R.id.add_done_icon);
        viewHolder.mAddButton.setVisibility(0);
        viewHolder.mAddButton.setOnClickListener(this.mAddClickListener);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.AlbumArtwork);
        viewHolder.mAlbumArtwork = imageView;
        this.mArtworkLoader.fetchInto(item, imageView);
        viewHolder.mAlbumArtworkBadge = (ImageView) itemView.findViewById(R.id.artwork_badge);
        viewHolder.mPrimeTrack = item;
        viewHolder.mAddButton.setTag(item);
        viewHolder.mPosition = position;
        TextViewMarkerDecorator textViewMarkerDecorator = new TextViewMarkerDecorator(viewHolder.mTrackTitle);
        textViewMarkerDecorator.showPrimeMarker(this.mShouldShowPrimeBadging && item.isPrime());
        textViewMarkerDecorator.showLyricsMarker(item.hasLyrics() && this.mLyricsSupported);
        textViewMarkerDecorator.decorate();
        applyTrackStatus(item);
        itemView.setTag(item);
        if (this.mIsNightwingOnly) {
            viewHolder.mAlbumArtworkBadge.setVisibility(0);
            if (!this.mFreeTierUserPlaylistEditingEnabled) {
                itemView.findViewById(getOverflowMenuButtonResId()).setVisibility(8);
            }
        }
        BadgeView badgeView = (BadgeView) itemView.findViewById(R.id.ContentEncodingBadge);
        viewHolder.mContentEncodingBadge = badgeView;
        badgeView.populate(ContentEncodingDecoder.getEncodingBadgeModel(item.getContentEncoding(), this.mContext));
        BadgeView badgeView2 = (BadgeView) itemView.findViewById(R.id.ImmersiveBadge);
        viewHolder.mImmersiveContentBadge = badgeView2;
        badgeView2.populate(ContentEncodingDecoder.getImmersiveContentBadgeModel(item.getContentEncoding(), this.mContext, 8));
        viewHolder.mPlaystateIcon = (PlayNotificationIcon) itemView.findViewById(R.id.PlayNotificationIcon);
        updatePlaystateIcon(item, viewHolder);
        checkContentEnabled(itemView, item.getIsAvailable());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.card.ArrayCardAdapter
    public boolean isItemPlaying(ViewHolder holder) {
        String asin = holder.mPrimeTrack.getAsin();
        if (!asin.isEmpty()) {
            return MediaItemHelper.isCurrentlyPlayingItem(asin);
        }
        String luid = holder.mPrimeTrack.getLuid();
        if (luid.isEmpty()) {
            return false;
        }
        return MediaItemHelper.isCurrentlyPlayingOfflineOrNonPrimeItem(luid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyTrackStatus(PrimeTrack track) {
        ViewHolder viewHolder = (ViewHolder) this.mHolders.get(track);
        if (viewHolder == null) {
            return;
        }
        if (this.mStateProvider.isInLibrary(track) || this.mStateProvider.isAddingToLibrary(track) || this.mIsNightwingOnly) {
            viewHolder.mAddButton.setVisibility(8);
            viewHolder.mAddButtonHolder.setVisibility(8);
        }
    }

    @Override // com.amazon.mp3.card.prime.PrimeCardAdapter
    public StateProvider<PrimeTrack> getStateProvider() {
        return this.mStateProvider;
    }

    @Override // com.amazon.mp3.card.prime.PrimeCardAdapter
    public void updateItemStatus(final PrimeTrack item) {
        updateItemStatus(item, (StateProvider.Listener<PrimeTrack>) null);
    }

    @Override // com.amazon.mp3.card.prime.PrimeCardAdapter
    public void updateItemStatus(final PrimeTrack item, final StateProvider.Listener<PrimeTrack> listener) {
        this.mStateProvider.requestState(item, new TrackStateProviderListener(item, (SongTileAdapter.TrackStateListener) listener));
    }

    public class TrackStateProviderListener implements StateProvider.Listener<PrimeTrack> {
        private final PrimeTrack mPrimeTrack;
        private final SongTileAdapter.TrackStateListener mTrackStateListener;

        public TrackStateProviderListener(final PrimeTrack primeTrack, final SongTileAdapter.TrackStateListener trackStateListener) {
            this.mPrimeTrack = primeTrack;
            this.mTrackStateListener = trackStateListener;
        }

        @Override // com.amazon.mp3.library.provider.StateProvider.Listener
        public void onStateChanged(final PrimeTrack track, final int ownershipStatus, final int downloadState) {
            TrackCardAdapter.this.applyTrackStatus(track);
            SongTileAdapter.TrackStateListener trackStateListener = this.mTrackStateListener;
            if (trackStateListener != null) {
                trackStateListener.onTrackStateUpdated(this.mPrimeTrack);
            }
        }
    }
}
