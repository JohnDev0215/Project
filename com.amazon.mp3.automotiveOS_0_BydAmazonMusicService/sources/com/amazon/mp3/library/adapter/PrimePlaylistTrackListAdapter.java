package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.animation.AddButtonAnimation;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.cta.TrackMetadata;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.view.BadgeView;
import com.amazon.mp3.view.ContentDisabledHelper;
import com.amazon.mp3.view.IPrimeMarkableView;
import com.amazon.mp3.view.TextSashImageView;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class PrimePlaylistTrackListAdapter extends TrackListAdapter {
    private boolean isGoldenPlaylist;
    private int mDefaultNumOfTracks;
    private int mIsNewIndex;
    private boolean mIsOnDemandGoldenPlaylist;
    private final boolean mLyricsSupported;
    private final PrimePlaylistAddCallback mPrimePlaylistAddCallback;
    private boolean mShowCompressedView;

    public static final class PrimePlaylistRowViewHolder extends TrackListAdapter.RowViewHolder {
        ImageView mAddPrimeTrackToLibraryButton;
        TextSashImageView mNewSash;

        @Override // com.amazon.mp3.library.adapter.TrackListAdapter.RowViewHolder, com.amazon.mp3.library.adapter.BadgeableListAdapter.BaseRowViewHolder
        public void updateProgress(int percent) {
        }
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter
    protected int getDefaultRowLayoutId() {
        return R.layout.library_prime_playlist_track_row;
    }

    public PrimePlaylistTrackListAdapter(Context context, Uri contentUri, PrimePlaylistAddCallback callback, IArtCache artCache, ImageLoaderFactory.ItemType itemType, boolean shouldShowPrimeBadging, boolean showCompressedView) {
        super(context, contentUri, artCache, itemType, shouldShowPrimeBadging);
        this.isGoldenPlaylist = false;
        this.mIsOnDemandGoldenPlaylist = false;
        this.mPrimePlaylistAddCallback = callback;
        this.mLyricsSupported = AmazonApplication.getCapabilities().isLyricsSupported();
        this.mDefaultNumOfTracks = 3;
        this.mShowCompressedView = showCompressedView;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View viewInflate = View.inflate(context, getDefaultRowLayoutId(), null);
        PrimePlaylistRowViewHolder primePlaylistRowViewHolder = new PrimePlaylistRowViewHolder();
        primePlaylistRowViewHolder.mTitle = (TextView) viewInflate.findViewById(R.id.TrackTitle);
        primePlaylistRowViewHolder.mAlbumArt = (ImageView) viewInflate.findViewById(R.id.AlbumArtwork);
        primePlaylistRowViewHolder.mArtist = (TextView) viewInflate.findViewById(R.id.ArtistName);
        primePlaylistRowViewHolder.mKatanaBadge = (BadgeView) viewInflate.findViewById(R.id.KatanaBadge);
        primePlaylistRowViewHolder.mImmersiveBadge = (BadgeView) viewInflate.findViewById(R.id.ImmersiveBadge);
        primePlaylistRowViewHolder.mTrackCount = (TextView) viewInflate.findViewById(R.id.TrackCount);
        primePlaylistRowViewHolder.mAddPrimeTrackToLibraryButton = (ImageView) viewInflate.findViewById(R.id.AddToLibraryButton);
        primePlaylistRowViewHolder.mDimAlbumArtOverlay = (ImageView) viewInflate.findViewById(R.id.DimAlbumArtOverlay);
        primePlaylistRowViewHolder.mPlaystateIcon = (ImageView) viewInflate.findViewById(R.id.PlaystateIcon);
        primePlaylistRowViewHolder.mNewSash = (TextSashImageView) viewInflate.findViewById(R.id.NewSash);
        primePlaylistRowViewHolder.mLyricsBadgeView = (BadgeView) viewInflate.findViewById(R.id.LyricsView);
        View viewFindViewById = viewInflate.findViewById(R.id.overflow_button_open);
        primePlaylistRowViewHolder.mOverflowButtonView = viewFindViewById;
        viewFindViewById.setOnClickListener(getOverflowMenuClickListener());
        if (this.mIsNightwingOnlyUser) {
            if (!this.mFreeTierUserPlaylistEditingEnabled) {
                primePlaylistRowViewHolder.mOverflowButtonView.setVisibility(4);
                viewInflate.findViewById(R.id.RightViews).setVisibility(4);
            }
            if (this.isGoldenPlaylist && !this.mIsOnDemandGoldenPlaylist) {
                ((ImageView) viewInflate.findViewById(R.id.station_icon)).setImageResource(R.drawable.shuffle_outline_thumb_badge);
                ((ImageView) viewInflate.findViewById(R.id.station_icon_in_right_views)).setImageResource(R.drawable.shuffle_outline_thumb_badge);
            }
        }
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.missing_sd_card);
        primePlaylistRowViewHolder.mMissingSDCardIcon = imageView;
        imageView.setOnClickListener(this.mMissingSDCardClickListener);
        this.mIsNewIndex = cursor.getColumnIndexOrThrow("is_new");
        viewInflate.setTag(primePlaylistRowViewHolder);
        this.mCreatedViews.put(viewInflate, null);
        return viewInflate;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        boolean zIsAvailable;
        Drawable drawable;
        MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(cursor);
        PrimePlaylistRowViewHolder primePlaylistRowViewHolder = (PrimePlaylistRowViewHolder) view.getTag();
        primePlaylistRowViewHolder.mLibraryItem = track;
        primePlaylistRowViewHolder.mTrackUri = track.getContentUri();
        primePlaylistRowViewHolder.mLuid = track.getLuid();
        primePlaylistRowViewHolder.mAsin = track.getAsin();
        primePlaylistRowViewHolder.mDownloadState = track.getDownloadState();
        primePlaylistRowViewHolder.mDownloadId = "primeplaylist:" + primePlaylistRowViewHolder.mAsin;
        primePlaylistRowViewHolder.mHasLyrics = track.hasLyrics() && isLyricsSupported();
        bindPrimeView(primePlaylistRowViewHolder, track);
        bindAddTrackButton(primePlaylistRowViewHolder, track, cursor.getPosition());
        initText(primePlaylistRowViewHolder, track, String.valueOf(cursor.getPosition() + 1));
        bindPlaystateIcon(context, primePlaylistRowViewHolder);
        ContentUnavailableReason offlinePlaybackUnavailabilityReason = null;
        if (this.mArtCache != null) {
            if (this.mImageItemType == ImageLoaderFactory.ItemType.PRIME_PLAYLIST_TRACK) {
                primePlaylistRowViewHolder.mAsin = track.getAsin();
                primePlaylistRowViewHolder.mAlbumId = r11.hashCode();
                drawable = this.mArtCache.getDrawable(this.mImageItemType, track.getSource(), primePlaylistRowViewHolder.mAlbumId);
            } else {
                long albumId = track.getAlbumId();
                primePlaylistRowViewHolder.mAlbumId = albumId;
                drawable = this.mArtCache.getDrawable(this.mImageItemType, (String) null, albumId);
            }
            super.getImageProcessor().process((BitmapDrawable) drawable, primePlaylistRowViewHolder.mAlbumArt);
        }
        if (cursor.getInt(this.mIsNewIndex) == Integer.parseInt("1")) {
            primePlaylistRowViewHolder.mNewSash.setText(view.getResources().getString(R.string.dmusic_prime_playlist_new_track_sash));
            primePlaylistRowViewHolder.mNewSash.setVisibility(0);
        }
        boolean zEquals = "cirrus-local".equals(getSource());
        if (this.mIsNightwingOnlyUser) {
            zIsAvailable = (AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() && track.isExplicit()) ? false : true;
            if (this.mShouldShowStationIcon && this.mFreeTierUserPlaylistEditingEnabled && track.isNotOwned() && !this.mIsOnDemandGoldenPlaylist) {
                view.findViewById(R.id.station_icon_in_right_views).setVisibility(0);
            } else {
                view.findViewById(R.id.station_icon_in_right_views).setVisibility(8);
            }
            if ((!this.mShouldShowStationIcon || this.mFreeTierUserPlaylistEditingEnabled || this.mIsOnDemandGoldenPlaylist) ? false : true) {
                view.findViewById(R.id.station_icon).setVisibility(0);
            } else {
                view.findViewById(R.id.station_icon).setVisibility(8);
            }
        } else if (zEquals) {
            offlinePlaybackUnavailabilityReason = track.getOfflinePlaybackUnavailabilityReason();
            zIsAvailable = offlinePlaybackUnavailabilityReason == null;
        } else {
            zIsAvailable = track.isAvailable();
        }
        boolean z = zEquals && offlinePlaybackUnavailabilityReason == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK && !this.mIsNightwingOnlyUser;
        ContentDisabledHelper.setContentEnabled(view, zIsAvailable);
        if (z) {
            bindMissingSDCardIcon(primePlaylistRowViewHolder);
        } else {
            primePlaylistRowViewHolder.mMissingSDCardIcon.setVisibility(8);
        }
        BadgeView badgeView = primePlaylistRowViewHolder.mLyricsBadgeView;
        if (badgeView != null) {
            badgeView.setBadgeVisiblility(primePlaylistRowViewHolder.mHasLyrics && this.mShouldShowLyricsBadge);
        }
        Map<String, TrackMetadata> map = this.mTracksMetadata;
        if (map == null || map.get(track.getAsin()) == null) {
            return;
        }
        primePlaylistRowViewHolder.mKatanaBadge.populate(ContentEncodingDecoder.getEncodingBadgeModel(this.mTracksMetadata.get(track.getAsin()).getContentEncoding(), this.mContext, 8));
        primePlaylistRowViewHolder.mImmersiveBadge.populate(ContentEncodingDecoder.getImmersiveContentBadgeModel(this.mTracksMetadata.get(track.getAsin()).getContentEncoding(), this.mContext, 8));
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected ImageLoaderFactory.ItemType getImageType() {
        return ImageLoaderFactory.ItemType.PRIME_PLAYLIST_TRACK;
    }

    private void bindPrimeView(final PrimePlaylistRowViewHolder holder, MusicTrack track) {
        IPrimeMarkableView iPrimeMarkableView = holder.mIPrimeMarkableView;
        if (iPrimeMarkableView != null) {
            iPrimeMarkableView.markAsPrime(this.mShouldShowPrimeBadging && track.isPurePrime());
        }
    }

    private void bindAddTrackButton(final PrimePlaylistRowViewHolder holder, MusicTrack track, int position) {
        if (shouldShowAddPrimeTrackButton(track)) {
            holder.mAddPrimeTrackToLibraryButton.setOnClickListener(initAddPrimeTrackListener(position));
            holder.mAddPrimeTrackToLibraryButton.setVisibility(0);
            return;
        }
        holder.mAddPrimeTrackToLibraryButton.setVisibility(8);
    }

    private void bindMissingSDCardIcon(final PrimePlaylistRowViewHolder holder) {
        if (holder.mDownloadState == 0) {
            holder.mAddPrimeTrackToLibraryButton.setVisibility(8);
            holder.mMissingSDCardIcon.setVisibility(0);
            holder.mMissingSDCardIcon.setTag(holder);
        }
    }

    private View.OnClickListener initAddPrimeTrackListener(final int position) {
        return new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.PrimePlaylistTrackListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                View view = (View) v.getParent();
                AddButtonAnimation.startAnimation(PrimePlaylistTrackListAdapter.this.mContext, view, v, view.findViewById(R.id.add_done_icon), new AddButtonAnimation.AnimationFinishedListener() { // from class: com.amazon.mp3.library.adapter.PrimePlaylistTrackListAdapter.1.1
                    @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
                    public void onAnimationFinished() {
                        PrimePlaylistTrackListAdapter.this.mPrimePlaylistAddCallback.onClick(position);
                    }

                    @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
                    public void onAnimationCanceled() {
                        PrimePlaylistTrackListAdapter.this.mPrimePlaylistAddCallback.onClick(position);
                    }
                });
            }
        };
    }

    private void initText(PrimePlaylistRowViewHolder holder, MusicTrack track, String position) {
        holder.mTitle.setText(track.getTitle());
        String albumArtistName = track.getAlbumArtistName();
        String artistName = track.getArtistName();
        if (!TextUtils.isEmpty(artistName)) {
            holder.mArtist.setText(artistName);
        } else if (!TextUtils.isEmpty(albumArtistName)) {
            holder.mArtist.setText(albumArtistName);
        } else {
            holder.mArtist.setText("");
        }
        holder.mTrackCount.setText(position);
    }

    private boolean shouldShowAddPrimeTrackButton(MusicTrack track) {
        return !track.getOwnershipStatus().isInLibrary() && track.isAddableToLibrary().booleanValue() && track.getContentCatalogStatus().isHawkfire() && track.isAvailable() && !this.mIsLocal && !this.mIsNightwingOnlyUser;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter, android.widget.Adapter
    public int getCount() {
        return super.getCount();
    }

    public void setIsGoldenPlaylist(boolean isGoldenPlaylist) {
        this.isGoldenPlaylist = isGoldenPlaylist;
    }

    public void setIsOnDemandGoldenPlaylist(boolean mIsOnDemandGoldenPlaylist) {
        this.mIsOnDemandGoldenPlaylist = mIsOnDemandGoldenPlaylist;
    }
}
