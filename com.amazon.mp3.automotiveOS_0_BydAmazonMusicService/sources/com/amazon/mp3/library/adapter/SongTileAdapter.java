package com.amazon.mp3.library.adapter;

import android.animation.Animator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.animation.AddButtonAnimation;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.TileAdapter;
import com.amazon.mp3.library.dialog.DownloadedFileNotFoundActivityDialog;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.TrackStateProvider;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.search.ArtworkLoader;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.BadgeView;
import com.amazon.mp3.view.DownloadProgressBadgeView;
import com.amazon.mp3.view.PlayNotificationIcon;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class SongTileAdapter extends TileAdapter<PrimeTrack, ViewHolder> {
    private static final String TAG = SongTileAdapter.class.getSimpleName();
    protected final TileAdapter.ItemButtonListener mAddButtonListener;
    private final View.OnClickListener mAddClickListener;
    protected final ArtworkLoader mArtworkLoader;
    private final boolean mFreeTierUserPlaylistEditingEnabled;
    private final boolean mIsNightwingOnly;
    protected final View.OnClickListener mMissingSDCardClickListener;
    private final boolean mPrefixNumbers;
    protected boolean mShouldShowPrimeBadging;
    private final boolean mShowRecommendationReason;
    protected TrackStateProvider mStateProvider;
    private final Map<PrimeTrack, TrackStatus> mTrackStatuses;

    public interface TrackStateListener {
        void onTrackStateUpdated(PrimeTrack track);
    }

    public static class ViewHolder extends TileAdapter.TileViewHolder {
        public TextView mTrackTitle = null;
        public TextView mArtistName = null;
        public TextView mRecommendationReason = null;
        public View mAddButtonHolder = null;
        public ImageView mAddButton = null;
        public ImageView mAddDoneView = null;
        public ImageView mMissingSDCardIcon = null;
        public ImageView mOverflowButton = null;
        public DownloadProgressBadgeView mDownloadProgress = null;
        public ImageView mAlbumArtwork = null;
        public ImageView mAlbumBadge = null;
        public PrimeTrack mPrimeTrack = null;
        public Animator mAddButtonAnimation = null;
        public BadgeView mContentEncodingBadge = null;
        public BadgeView mImmersiveContentBadge = null;
    }

    private static class TrackStatus {
        private int mDownloadState;
        private ViewHolder mViewHolder;

        private TrackStatus() {
        }

        public void setDownloadState(final int downloadState) {
            this.mDownloadState = downloadState;
            ViewHolder viewHolder = this.mViewHolder;
            if (viewHolder != null) {
                viewHolder.mDownloadProgress.setDownloadState(downloadState);
            }
        }

        public void setViewHolder(final ViewHolder viewHolder) {
            this.mViewHolder = viewHolder;
            viewHolder.mDownloadProgress.setDownloadState(this.mDownloadState);
        }

        public int getDownloadState() {
            return this.mDownloadState;
        }
    }

    private class TrackStateProviderListener implements StateProvider.Listener<PrimeTrack> {
        private final PrimeTrack mPrimeTrack;
        private final TrackStateListener mTrackStateListener;

        public TrackStateProviderListener(final PrimeTrack primeTrack, final TrackStateListener trackStateListener) {
            this.mPrimeTrack = primeTrack;
            this.mTrackStateListener = trackStateListener;
        }

        @Override // com.amazon.mp3.library.provider.StateProvider.Listener
        public void onStateChanged(final PrimeTrack track, final int ownershipStatus, final int downloadState) {
            SongTileAdapter.this.applyTrackStatus(this.mPrimeTrack);
            SongTileAdapter.this.mStateProvider.monitorDownloadProgress(this.mPrimeTrack);
            TrackStateListener trackStateListener = this.mTrackStateListener;
            if (trackStateListener != null) {
                trackStateListener.onTrackStateUpdated(this.mPrimeTrack);
            }
        }
    }

    public SongTileAdapter(Context context, TrackStateProvider provider, TileAdapter.ItemButtonListener addClickListener, TileAdapter.ItemButtonListener overflowClickListener, int artworkSize, boolean prefixNumbers, boolean showRecommendationReason, boolean shouldShowPrimeBadging) {
        super(context, 0, overflowClickListener);
        this.mTrackStatuses = new WeakHashMap();
        this.mAddClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.SongTileAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                int iIntValue = ((Integer) v.getTag()).intValue();
                PrimeTrack primeTrack = (PrimeTrack) SongTileAdapter.this.getItem(iIntValue);
                if (ChildUserUtil.INSTANCE.isChildUser(SongTileAdapter.this.getContext()) && primeTrack.getIsExplicit()) {
                    return;
                }
                SongTileAdapter.this.mAddButtonListener.onClick(v, iIntValue);
                if (ContentAccessUtil.getCatalogContentUnavailableReason(primeTrack, ContentAccessUtil.ContentAccessOperation.INTERACT) == null) {
                    final ViewHolder viewHolder = ((TrackStatus) SongTileAdapter.this.mTrackStatuses.get(primeTrack)).mViewHolder;
                    viewHolder.mAddButtonAnimation = AddButtonAnimation.startAnimation(SongTileAdapter.this.getContext(), viewHolder.mAddButtonHolder, viewHolder.mAddButton, viewHolder.mAddDoneView, new AddButtonAnimation.AnimationFinishedListener() { // from class: com.amazon.mp3.library.adapter.SongTileAdapter.1.1
                        @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
                        public void onAnimationFinished() {
                            viewHolder.mAddButtonAnimation = null;
                        }

                        @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
                        public void onAnimationCanceled() {
                            viewHolder.mAddButtonAnimation = null;
                        }
                    });
                }
            }
        };
        this.mMissingSDCardClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.SongTileAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                PrimeTrack primeTrack = (PrimeTrack) v.getTag();
                SongTileAdapter.this.getContext().startActivity(DownloadedFileNotFoundActivityDialog.getStartIntent(SongTileAdapter.this.getContext(), primeTrack.isAllOwned(), primeTrack.getLocalUri()));
            }
        };
        this.mAddButtonListener = addClickListener;
        this.mStateProvider = provider;
        this.mArtworkLoader = new ArtworkLoader.Builder(getContext()).setAllowServerToScale(true).setArtworkSize(artworkSize).build();
        this.mPrefixNumbers = prefixNumbers;
        this.mShowRecommendationReason = showRecommendationReason;
        this.mShouldShowPrimeBadging = shouldShowPrimeBadging;
        this.mIsNightwingOnly = UserSubscriptionUtil.isNightwingOnly();
        this.mFreeTierUserPlaylistEditingEnabled = AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if (this.mShowEmptyLayout) {
            View view = this.mEmptyView;
            view.setTag(null);
            view.setPadding(0, getContext().getResources().getDimensionPixelOffset(R.dimen.album_tab_inside_padding), 0, 0);
            return view;
        }
        PrimeTrack primeTrack = (PrimeTrack) getItem(position);
        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_tile, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mTrackTitle = (TextView) convertView.findViewById(R.id.TrackTitle);
            viewHolder.mArtistName = (TextView) convertView.findViewById(R.id.ArtistName);
            viewHolder.mRecommendationReason = (TextView) convertView.findViewById(R.id.recommendation_reason);
            View viewFindViewById = convertView.findViewById(R.id.add_button_holder);
            viewHolder.mAddButtonHolder = viewFindViewById;
            viewHolder.mAddButton = (ImageView) viewFindViewById.findViewById(R.id.AddButton);
            viewHolder.mAddDoneView = (ImageView) convertView.findViewById(R.id.add_done_icon);
            viewHolder.mDownloadProgress = (DownloadProgressBadgeView) convertView.findViewById(R.id.DownloadProgress);
            viewHolder.mOverflowButton = (ImageView) convertView.findViewById(R.id.overflow_button_open);
            viewHolder.mAlbumArtwork = (ImageView) convertView.findViewById(R.id.AlbumArtwork);
            viewHolder.mAlbumBadge = (ImageView) convertView.findViewById(R.id.artwork_badge);
            viewHolder.mPlaystateIcon = (PlayNotificationIcon) convertView.findViewById(R.id.PlaystateIcon);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.missing_sd_card);
            viewHolder.mMissingSDCardIcon = imageView;
            imageView.setOnClickListener(this.mMissingSDCardClickListener);
            viewHolder.mMissingSDCardIcon.setTag(primeTrack);
            convertView.setTag(viewHolder);
            this.mCreatedViews.add(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            this.mTrackStatuses.remove(viewHolder.mPrimeTrack);
        }
        TrackStatus trackStatus = this.mTrackStatuses.get(primeTrack);
        if (trackStatus == null) {
            trackStatus = new TrackStatus();
            updateTrackStatus(primeTrack);
            this.mTrackStatuses.put(primeTrack, trackStatus);
        }
        trackStatus.setViewHolder(viewHolder);
        if (primeTrack != viewHolder.mPrimeTrack) {
            viewHolder.mPrimeTrack = primeTrack;
            viewHolder.mAddButtonHolder.setVisibility(0);
            if (this.mPrefixNumbers) {
                viewHolder.mTrackTitle.setText((position + 1) + ". " + primeTrack.getTitle());
            } else {
                viewHolder.mTrackTitle.setText(primeTrack.getTitle());
            }
            viewHolder.mArtistName.setText(primeTrack.getArtist());
            if (this.mShowRecommendationReason && primeTrack.getRecommendationReason() != null) {
                TextView textView = (TextView) viewHolder.mRecommendationReason.findViewById(R.id.recommendation_reason);
                if (!StringUtil.isNullOrWhiteSpaces(primeTrack.getRecommendationReason().getReason())) {
                    textView.setText(primeTrack.getRecommendationReason().getReason());
                    textView.setVisibility(0);
                } else {
                    textView.setVisibility(8);
                }
            }
            Animator animator = viewHolder.mAddButtonAnimation;
            if (animator != null) {
                animator.cancel();
                viewHolder.mAddButtonAnimation = null;
            }
            this.mArtworkLoader.fetchInto(primeTrack, viewHolder.mAlbumArtwork);
            viewHolder.mAddButton.setOnClickListener(this.mAddClickListener);
            viewHolder.mAddButton.setTag(Integer.valueOf(position));
            BadgeView badgeView = (BadgeView) convertView.findViewById(R.id.ContentEncodingBadge);
            viewHolder.mContentEncodingBadge = badgeView;
            badgeView.populate(ContentEncodingDecoder.getEncodingBadgeModel(primeTrack.getContentEncoding(), getContext()));
            BadgeView badgeView2 = (BadgeView) convertView.findViewById(R.id.ImmersiveBadge);
            viewHolder.mImmersiveContentBadge = badgeView2;
            badgeView2.populate(ContentEncodingDecoder.getImmersiveContentBadgeModel(primeTrack.getContentEncoding(), getContext(), 8));
            tagButtons(position, viewHolder.mOverflowButton);
        }
        applyTrackStatus(primeTrack);
        checkContentEnabled(convertView, primeTrack.getIsAvailable());
        return convertView;
    }

    public void setAdded(PrimeTrack track) {
        TrackStatus trackStatus = this.mTrackStatuses.get(track);
        if (trackStatus == null || trackStatus.mViewHolder.mAddButtonAnimation != null) {
            return;
        }
        trackStatus.mViewHolder.mAddButton.setVisibility(8);
        trackStatus.mViewHolder.mAddButtonHolder.setVisibility(8);
    }

    public void updateTrackStatus(final PrimeTrack track, final TrackStateListener listener) {
        this.mStateProvider.requestState(track, new TrackStateProviderListener(track, listener));
    }

    public void updateTrackStatus(final PrimeTrack track) {
        updateTrackStatus(track, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyTrackStatus(PrimeTrack track) {
        TrackStatus trackStatus = this.mTrackStatuses.get(track);
        if (trackStatus == null || trackStatus.mViewHolder == null) {
            return;
        }
        ViewHolder viewHolder = trackStatus.mViewHolder;
        DownloadProgressBadgeView downloadProgressBadgeView = viewHolder.mDownloadProgress;
        View view = viewHolder.mAddButtonHolder;
        ImageView imageView = viewHolder.mAddButton;
        updatePlaystateIcon(viewHolder);
        if (this.mIsNightwingOnly && !ContentCatalogStatus.NON_CATALOG.equals(track.getMaxNonPreviousContentCatalogStatus())) {
            viewHolder.mAlbumBadge.setVisibility(0);
            viewHolder.mAddButton.setVisibility(8);
            viewHolder.mAddButtonHolder.setVisibility(8);
            if (!this.mFreeTierUserPlaylistEditingEnabled) {
                viewHolder.mOverflowButton.setVisibility(8);
            }
        }
        if (trackStatus.getDownloadState() == -2) {
            view.setVisibility(0);
            imageView.setVisibility(8);
            downloadProgressBadgeView.setVisibility(0);
            return;
        }
        boolean zIsAddingToLibrary = this.mStateProvider.isAddingToLibrary(track);
        boolean zIsInLibrary = this.mStateProvider.isInLibrary(track);
        boolean zIsDownloading = this.mStateProvider.isDownloading(track);
        boolean zIsDownloaded = this.mStateProvider.isDownloaded(track);
        viewHolder.mMissingSDCardIcon.setVisibility(8);
        ContentUnavailableReason playCatalogContentUnavailableReason = ContentAccessUtil.getPlayCatalogContentUnavailableReason(track);
        if (!zIsDownloading && playCatalogContentUnavailableReason == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK) {
            view.setVisibility(0);
            imageView.setVisibility(8);
            downloadProgressBadgeView.setVisibility(8);
            downloadProgressBadgeView.setDownloadState(-1);
            viewHolder.mMissingSDCardIcon.setVisibility(0);
            return;
        }
        if (!zIsInLibrary && !zIsAddingToLibrary && !this.mIsNightwingOnly) {
            view.setVisibility(0);
            imageView.setVisibility(0);
            downloadProgressBadgeView.setVisibility(8);
            downloadProgressBadgeView.setDownloadState(-1);
            return;
        }
        if (zIsDownloading) {
            view.setVisibility(0);
            imageView.setVisibility(8);
            downloadProgressBadgeView.setVisibility(0);
            downloadProgressBadgeView.setDownloadState(4);
            return;
        }
        if (zIsDownloaded) {
            view.setVisibility(0);
            imageView.setVisibility(8);
            downloadProgressBadgeView.setVisibility(0);
            downloadProgressBadgeView.setDownloadState(0);
            return;
        }
        if (viewHolder.mAddButtonAnimation == null) {
            view.setVisibility(8);
            imageView.setVisibility(8);
            downloadProgressBadgeView.setVisibility(8);
            downloadProgressBadgeView.setDownloadState(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.library.adapter.TileAdapter
    public boolean isItemPlaying(ViewHolder holder) {
        MediaItemId mediaItemId;
        MediaItemId mediaItemId2;
        MediaItem currentMediaItem = PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentMediaItem();
        if (currentMediaItem == null) {
            return false;
        }
        String asin = holder.mPrimeTrack.getAsin();
        if (asin != null && (mediaItemId2 = currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN)) != null) {
            return asin.equals(mediaItemId2.getId());
        }
        String luid = holder.mPrimeTrack.getLuid();
        if (luid == null || (mediaItemId = currentMediaItem.getMediaItemId(MediaItemId.Type.LUID)) == null) {
            return false;
        }
        return luid.equals(mediaItemId.getId());
    }

    @Override // com.amazon.mp3.library.adapter.TileAdapter, com.amazon.mp3.library.cache.image.IArtCache.IArtLoadedListener
    public void onDrawableLoaded(Drawable d, String source, String id) {
        Iterator<View> it = this.mCreatedViews.iterator();
        while (it.hasNext()) {
            ViewHolder viewHolder = (ViewHolder) it.next().getTag();
            if (viewHolder.mPrimeTrack.getArtworkId() != null && String.valueOf(viewHolder.mPrimeTrack.getArtworkId()).equals(id)) {
                viewHolder.mAlbumArtwork.setImageDrawable(d);
            }
        }
    }
}
