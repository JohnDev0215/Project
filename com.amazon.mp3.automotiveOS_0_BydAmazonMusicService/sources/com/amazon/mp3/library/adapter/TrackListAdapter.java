package com.amazon.mp3.library.adapter;

import android.animation.Animator;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.animation.AddButtonAnimation;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.library.DownloadFileUtil;
import com.amazon.mp3.library.adapter.BadgeableListAdapter;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.db.VirtualizedCursor;
import com.amazon.mp3.library.dialog.DownloadedFileNotFoundActivityDialog;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.cta.TrackMetadata;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.DefaultStringType;
import com.amazon.mp3.util.ImageProcessor;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.RoundedCornerImageProcessor;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.WindowedAlphabetIndexer;
import com.amazon.mp3.view.BadgeView;
import com.amazon.mp3.view.DownloadButtonView;
import com.amazon.mp3.view.IDownloadProgressView;
import com.amazon.mp3.view.IPrimeMarkableView;
import com.amazon.mp3.view.TrackPopularityBar;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class TrackListAdapter extends BadgeableListAdapter implements IArtCache.IArtLoadedListener {
    private static final String TAG = TrackListAdapter.class.getSimpleName();
    private AddAllAnimationFinishedListener mAddAllAnimationFinishedListener;
    protected int mAlbumIdIndex;
    private String mAlphabet;
    private WindowedAlphabetIndexer mAlphabetIndexer;
    protected boolean mAlwaysShowTrackArtist;
    protected int mArtistIdIndex;
    protected int mAsinIndex;
    private final View.OnClickListener mBadgeClickListener;
    protected int mContentCatalogStatusIndex;
    protected int mContentOwnershipStatusIndex;
    private Uri mContentUri;
    protected WeakHashMap<View, Object> mCreatedViews;
    private DiscIndexer mDiscIndexer;
    protected int mDiscNumIndex;
    protected boolean mFreeTierUserPlaylistEditingEnabled;
    protected ImageLoaderFactory.ItemType mImageItemType;
    private ImageProcessor mImageProcessor;
    protected boolean mIsCirrus;
    protected boolean mIsLocal;
    protected boolean mIsNightwingOnlyUser;
    protected boolean mIsPlaylist;
    protected boolean mIsSingleAlbum;
    protected int mLuidIndex;
    private boolean mLyricsSupported;
    protected int mMatchHashIndex;
    protected final View.OnClickListener mMissingSDCardClickListener;
    private OnPrimeAddButtonClickListener mOnPrimeAddButtonClickListener;
    private final VirtualizedCursor.OnRowsCachedListener mOnRowsCachedListener;
    private Cursor mOriginalCursor;
    protected final View.OnClickListener mOverflowMenuClickListener;
    protected boolean mPopularityBarSupported;
    private final View.OnClickListener mPrimeAddViewClickListener;
    private boolean mShouldShowDownloadButton;
    public boolean mShouldShowLyricsBadge;
    protected boolean mShouldShowPopularityBar;
    protected final boolean mShouldShowPrimeBadging;
    protected boolean mShouldShowStationIcon;
    private boolean mShouldShowTrackDuration;
    protected int mSortIndex;
    private String mSourceId;
    private BauhausTextStyler mStyler;
    protected String mTrackAsinHighlight;
    private TrackRowDownloadClickListener mTrackRowDownloadClickListener;
    protected Map<String, TrackMetadata> mTracksMetadata;
    private VirtualizedCursor mVirtualizedCursor;

    public interface OnPrimeAddButtonClickListener {
        void onClick(MusicTrack track);
    }

    public interface TrackRowDownloadClickListener {
        void onDownloadingRowClick(RowViewHolder holder);

        void onFailedRowClick(RowViewHolder holder);
    }

    public static String getDefaultSelection() {
        return "ownership_status< ? ";
    }

    public static String getPlaylistSelection() {
        return "ownership_status< ? OR playlist_track_status > ?";
    }

    protected int getDefaultRowLayoutId() {
        return R.layout.library_track_row;
    }

    @Override // com.amazon.mp3.library.adapter.CursorListAdapter
    public void setupNewCursor(Cursor c) {
    }

    protected boolean shouldShowDownloadBadging() {
        return true;
    }

    protected boolean shouldShowTrackDownloadProgress() {
        return true;
    }

    protected View.OnClickListener getOverflowMenuClickListener() {
        return this.mOverflowMenuClickListener;
    }

    public void setTrackRowDownloadClickListener(TrackRowDownloadClickListener listener) {
        this.mTrackRowDownloadClickListener = listener;
    }

    public void setOnPrimeAddButtonClickListener(OnPrimeAddButtonClickListener listener) {
        this.mOnPrimeAddButtonClickListener = listener;
    }

    public View.OnClickListener getBadgeClickListener() {
        return this.mBadgeClickListener;
    }

    public TrackListAdapter(Context context, @NonNull Uri contentUri, IArtCache artCache, @NonNull ImageLoaderFactory.ItemType itemType, boolean shouldShowPrimeBadging) {
        super(context, null, false);
        this.mShouldShowStationIcon = false;
        this.mShouldShowLyricsBadge = true;
        this.mShouldShowTrackDuration = true;
        this.mAddAllAnimationFinishedListener = null;
        this.mShouldShowDownloadButton = false;
        this.mCreatedViews = new WeakHashMap<>();
        this.mBadgeClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.TrackListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
                Log.debug(TrackListAdapter.TAG, "holder state is: " + rowViewHolder.mDownloadState);
                if (TrackListAdapter.this.mTrackRowDownloadClickListener != null) {
                    int i = rowViewHolder.mDownloadState;
                    if (i != 1) {
                        if (i == 2) {
                            TrackListAdapter.this.mTrackRowDownloadClickListener.onFailedRowClick(rowViewHolder);
                            return;
                        } else if (i != 3 && i != 4) {
                            return;
                        }
                    }
                    TrackListAdapter.this.mTrackRowDownloadClickListener.onDownloadingRowClick(rowViewHolder);
                }
            }
        };
        this.mPrimeAddViewClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.TrackListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                if (TrackListAdapter.this.mOnPrimeAddButtonClickListener != null) {
                    final RowViewHolder rowViewHolder = (RowViewHolder) v.getTag();
                    View view = (View) v.getParent();
                    rowViewHolder.mAddAnimator = AddButtonAnimation.startAnimation(TrackListAdapter.this.mContext, view, v, view.findViewById(R.id.add_done_icon), new AddButtonAnimation.AnimationFinishedListener() { // from class: com.amazon.mp3.library.adapter.TrackListAdapter.2.1
                        @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
                        public void onAnimationFinished() {
                            TrackListAdapter.this.mOnPrimeAddButtonClickListener.onClick((MusicTrack) rowViewHolder.mLibraryItem);
                        }

                        @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
                        public void onAnimationCanceled() {
                            TrackListAdapter.this.mOnPrimeAddButtonClickListener.onClick((MusicTrack) rowViewHolder.mLibraryItem);
                        }
                    });
                    MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.ADD_TRACK_TO_LIBRARY).withInteractionType(InteractionType.TAP).withEntityId(rowViewHolder.mLibraryItem.getAsin()).withEntityPos(rowViewHolder.mPosition).withEntityIdType(EntityIdType.ASIN).withEventTime(System.currentTimeMillis()).build());
                }
            }
        };
        this.mMissingSDCardClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.TrackListAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                MusicTrack musicTrack = (MusicTrack) ((RowViewHolder) v.getTag()).mLibraryItem;
                Context context2 = TrackListAdapter.this.mContext;
                context2.startActivity(DownloadedFileNotFoundActivityDialog.getStartIntent(context2, musicTrack));
            }
        };
        this.mOverflowMenuClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.TrackListAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                v.performLongClick();
            }
        };
        this.mOnRowsCachedListener = new VirtualizedCursor.OnRowsCachedListener() { // from class: com.amazon.mp3.library.adapter.TrackListAdapter.5
            @Override // com.amazon.mp3.library.db.VirtualizedCursor.OnRowsCachedListener
            public void onAllRowsCached() {
                TrackListAdapter.this.checkEnableAlphabetIndexer();
            }
        };
        this.mArtCache = artCache;
        setContentUri(contentUri);
        this.mAlphabet = context.getString(R.string.dmusic_library_songs_alphabet);
        this.mResetOnTrackFinished = true;
        this.mImageItemType = itemType;
        this.mShouldShowPrimeBadging = shouldShowPrimeBadging;
        this.mLyricsSupported = AmazonApplication.getCapabilities().isLyricsSupported();
        this.mPopularityBarSupported = AmazonApplication.getCapabilities().isTrackPopularityBarSupported();
        this.mImageProcessor = new RoundedCornerImageProcessor(context);
        this.mFreeTierUserPlaylistEditingEnabled = AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
        this.mStyler = new BauhausTextStyler();
    }

    public TrackListAdapter(Context context, @NonNull Uri contentUri, IArtCache artCache, boolean shouldShowPrimeBadging) {
        this(context, contentUri, artCache, ImageLoaderFactory.ItemType.ALBUM, shouldShowPrimeBadging);
    }

    public void setContentUri(@NonNull final Uri contentUri) {
        this.mContentUri = contentUri;
        this.mIsCirrus = CirrusMediaSource.match(contentUri);
        this.mSourceId = MediaProvider.getSource(this.mContentUri);
        int iMatch = DefaultUriMatcher.match(this.mContentUri);
        this.mIsSingleAlbum = iMatch == 4 || iMatch == 5 || iMatch == 26 || iMatch == 9;
        this.mAlwaysShowTrackArtist = iMatch == 1 || iMatch == 16 || iMatch == 15 || iMatch == 26;
        this.mIsLocal = "cirrus-local".equals(this.mSourceId);
        this.mIsPlaylist = MediaProvider.Playlists.isPlaylist(contentUri);
    }

    private boolean isRecentlyPlayedPlaylist() {
        return MediaProvider.isSmartPlaylist(this.mContentUri) && 3 == MediaProvider.SmartPlaylists.getPlaylistId(this.mContentUri);
    }

    public static String[] getDefaultProjection() {
        return AmazonApplication.getLibraryItemFactory().getTrackProjection();
    }

    public static String[] getDefaultSelectionArgs() {
        return new String[]{String.valueOf(300)};
    }

    public static String[] getPlaylistSelectionArgs() {
        return new String[]{String.valueOf(300), String.valueOf(MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue())};
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter, com.amazon.mp3.library.adapter.CursorListAdapter, android.widget.CursorAdapter
    public void changeCursor(Cursor c) {
        this.mOriginalCursor = c;
        VirtualizedCursor virtualizedCursor = this.mVirtualizedCursor;
        if (virtualizedCursor != null) {
            virtualizedCursor.setOnRowsCachedListener(null);
            this.mVirtualizedCursor = null;
        }
        if (c instanceof VirtualizedCursor) {
            VirtualizedCursor virtualizedCursor2 = (VirtualizedCursor) c;
            this.mVirtualizedCursor = virtualizedCursor2;
            virtualizedCursor2.setOnRowsCachedListener(this.mOnRowsCachedListener);
        }
        super.changeCursor(c);
        if (c == null) {
            this.mAlphabetIndexer = null;
            this.mDiscIndexer = null;
            return;
        }
        this.mSortIndex = getSortIndexColumn(c);
        this.mAlbumIdIndex = c.getColumnIndexOrThrow("album_id");
        this.mDiscNumIndex = c.getColumnIndex("disc_num");
        this.mLuidIndex = c.getColumnIndexOrThrow("luid");
        this.mContentCatalogStatusIndex = c.getColumnIndex("prime_status");
        this.mContentOwnershipStatusIndex = c.getColumnIndex("ownership_status");
        this.mAsinIndex = c.getColumnIndex("asin");
        this.mMatchHashIndex = c.getColumnIndex("match_hash");
        if (this.mIsSingleAlbum) {
            this.mDiscIndexer = new DiscIndexer(c);
        }
        checkEnableAlphabetIndexer();
    }

    protected int getSortIndexColumn(Cursor c) {
        return c.getColumnIndexOrThrow("sort_title");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkEnableAlphabetIndexer() {
        if (enableAlphabetIndexer()) {
            this.mAlphabetIndexer = new WindowedAlphabetIndexer(this.mOriginalCursor, this.mSortIndex, this.mAlphabet);
        } else {
            this.mAlphabetIndexer = null;
        }
    }

    public boolean isRowLoaded(Cursor cursor) {
        VirtualizedCursor virtualizedCursor = this.mVirtualizedCursor;
        if (virtualizedCursor != null) {
            return virtualizedCursor.rowCached(cursor.getPosition());
        }
        return true;
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache.IArtLoadedListener
    public void onDrawableLoaded(Drawable d, String source, String id) {
        Iterator<View> it = this.mCreatedViews.keySet().iterator();
        while (it.hasNext()) {
            RowViewHolder rowViewHolder = (RowViewHolder) it.next().getTag();
            String str = rowViewHolder.mAsin;
            if (str != null && id != null && str.equals(id)) {
                this.mImageProcessor.process((BitmapDrawable) d, rowViewHolder.mAlbumArt);
            }
        }
    }

    public void onDrawableLoaded(Drawable d, String source, long id) {
        Iterator<View> it = this.mCreatedViews.keySet().iterator();
        while (it.hasNext()) {
            RowViewHolder rowViewHolder = (RowViewHolder) it.next().getTag();
            if (rowViewHolder.mAlbumId == id) {
                this.mImageProcessor.process((BitmapDrawable) d, rowViewHolder.mAlbumArt);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x025b  */
    @Override // android.widget.CursorAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void bindView(android.view.View r19, android.content.Context r20, android.database.Cursor r21) {
        /*
            Method dump skipped, instruction units count: 1060
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.adapter.TrackListAdapter.bindView(android.view.View, android.content.Context, android.database.Cursor):void");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.adapter.TrackListAdapter$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType;

        static {
            int[] iArr = new int[DeluxeContentUtil.ContentType.values().length];
            $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType = iArr;
            try {
                iArr[DeluxeContentUtil.ContentType.AUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType[DeluxeContentUtil.ContentType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$item$DeluxeContentUtil$ContentType[DeluxeContentUtil.ContentType.DOCUMENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    protected CharSequence getDiscHeaderText(int position) {
        int sectionForPosition = this.mDiscIndexer.getSectionForPosition(position);
        if (sectionForPosition >= 0) {
            return this.mDiscIndexer.getTitleForSection(sectionForPosition);
        }
        return null;
    }

    protected boolean isDiscHeader(int position) {
        DiscIndexer discIndexer = this.mDiscIndexer;
        if (discIndexer != null && discIndexer.getCount() > 1) {
            if (this.mDiscIndexer.getPositionForSection(this.mDiscIndexer.getSectionForPosition(position)) == position) {
                return true;
            }
        }
        return false;
    }

    private void bindDownloadButton(RowViewHolder holder, MusicTrack track) {
        if (holder.mDownloadButtonView == null) {
            return;
        }
        int i = 8;
        if ("cirrus-local".equals(this.mSourceId)) {
            holder.mDownloadButtonView.setVisibility(8);
            return;
        }
        if (!this.mShouldShowDownloadButton) {
            holder.mDownloadButtonView.setVisibility(8);
            return;
        }
        boolean z = ContentAccessUtil.getCatalogContentUnavailableReason(track, ContentAccessUtil.ContentAccessOperation.DOWNLOAD) == null;
        boolean zIsContentDownloadable = DownloadFileUtil.isContentDownloadable(track.getExtension());
        int i2 = holder.mDownloadState;
        boolean z2 = i2 == 0;
        DownloadButtonView downloadButtonView = holder.mDownloadButtonView;
        if (z && zIsContentDownloadable && i2 != 4 && !z2) {
            i = 0;
        }
        downloadButtonView.setVisibility(i);
    }

    private void bindPrimeAddButton(RowViewHolder holder, MusicTrack track) {
        if (holder.mPrimeAddView == null) {
            return;
        }
        if ("cirrus-local".equals(this.mSourceId) || this.mIsNightwingOnlyUser) {
            holder.mPrimeAddView.setVisibility(4);
        } else {
            Animator animator = holder.mAddAnimator;
            boolean z = animator != null && animator.isRunning();
            if (holder.mLibraryItem != track || !z) {
                holder.mPrimeAddView.setVisibility((!(ContentAccessUtil.getCatalogContentUnavailableReason(track, ContentAccessUtil.ContentAccessOperation.INTERACT) == null) || !track.getContentCatalogStatus().isHawkfire() || holder.mDownloadState == 4 || track.isInLibrary() || !track.isAddableToLibrary().booleanValue() || holder.mAddSongToPlaylistIcon.getVisibility() == 0 || holder.mAddDoneIcon.getVisibility() == 0) ? 4 : 0);
                if (this.mShouldShowDownloadButton && holder.mPrimeAddView.getVisibility() == 4) {
                    holder.mPrimeAddView.setVisibility(8);
                }
            }
        }
        holder.mPrimeAddView.setTag(holder);
    }

    protected void bindArtistLabel(final RowViewHolder holder, final String albumArtist, final String trackArtist) {
        if (!TextUtils.isEmpty(trackArtist)) {
            holder.mArtistLabel = trackArtist;
        } else if (!TextUtils.isEmpty(albumArtist)) {
            holder.mArtistLabel = albumArtist;
        } else {
            holder.mArtistLabel = "";
        }
        DefaultStringType defaultStringType = DefaultStringType.ARTIST;
        if (defaultStringType.isPlatformOrCirrusDefault(holder.mArtistLabel)) {
            holder.mArtistLabel = defaultStringType.getDefault();
        }
    }

    private void bindMissingSDCardIcon(final RowViewHolder holder) {
        holder.mDuration.setVisibility(8);
        holder.mPrimeAddView.setVisibility(8);
        holder.mAddDoneIcon.setVisibility(8);
        holder.mDownloadProgress.setVisibility(8);
        holder.mMissingSDCardIcon.setVisibility(0);
        holder.mMissingSDCardIcon.setTag(holder);
    }

    protected void bindDownloadProgress(RowViewHolder holder) {
        holder.mDownloadId = !TextUtils.isEmpty(holder.mAsin) ? holder.mAsin : holder.mLuid;
        int i = holder.mDownloadState;
        if (i == 4 || i == 3) {
            holder.mDownloadProgressView.setDownloadState(4);
            addProgress(holder);
            holder.mDownloadProgressView.setProgress(getProgress(holder));
        } else {
            holder.mDownloadProgressView.setDownloadState(i);
            holder.mDownloadProgressView.setProgress(0);
        }
        bindBadgeContainer(holder.mBadgeContainer, holder.mDownloadState);
    }

    protected void bindPrimeView(final RowViewHolder holder, MusicTrack track) {
        IPrimeMarkableView iPrimeMarkableView = holder.mIPrimeMarkableView;
        if (iPrimeMarkableView != null) {
            iPrimeMarkableView.markAsPrime(this.mShouldShowPrimeBadging && track.isPurePrime());
        }
    }

    protected void bindDuration(final RowViewHolder holder, long duration) {
        if (holder.mDuration != null) {
            holder.mDuration.setText(StringUtil.getCachedFormattedTime(TimeUnit.SECONDS.toMillis(duration)));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewInflate = View.inflate(context, getDefaultRowLayoutId(), null);
        IDownloadProgressView iDownloadProgressView = (IDownloadProgressView) viewInflate;
        BadgeView badgeView = (BadgeView) viewInflate.findViewById(R.id.LyricsView);
        TrackPopularityBar trackPopularityBar = (TrackPopularityBar) viewInflate.findViewById(R.id.PopularityBar);
        BadgeView badgeView2 = (BadgeView) viewInflate.findViewById(R.id.KatanaBadge);
        BadgeView badgeView3 = (BadgeView) viewInflate.findViewById(R.id.ImmersiveBadge);
        RowViewHolder rowViewHolder = new RowViewHolder();
        rowViewHolder.mDownloadProgressView = iDownloadProgressView;
        rowViewHolder.mIPrimeMarkableView = (IPrimeMarkableView) viewInflate;
        rowViewHolder.mLyricsBadgeView = badgeView;
        rowViewHolder.mPopularityBarView = trackPopularityBar;
        rowViewHolder.mKatanaBadge = badgeView2;
        rowViewHolder.mImmersiveBadge = badgeView3;
        rowViewHolder.mTrackCount = (TextView) viewInflate.findViewById(R.id.TrackCount);
        rowViewHolder.mPlaylistTrackCount = (TextView) viewInflate.findViewById(R.id.PlaylistTrackCount);
        rowViewHolder.mPlaylistTrackCountFrame = (FrameLayout) viewInflate.findViewById(R.id.PlaylistTrackCountFrame);
        rowViewHolder.mTitle = (TextView) viewInflate.findViewById(R.id.TrackTitle);
        rowViewHolder.mAlbumArt = (ImageView) viewInflate.findViewById(R.id.AlbumArtwork);
        rowViewHolder.mArtist = (TextView) viewInflate.findViewById(R.id.ArtistName);
        rowViewHolder.mHeaderContainer = viewInflate.findViewById(R.id.SectionHeaderContainer);
        rowViewHolder.mHeader = (TextView) viewInflate.findViewById(R.id.SectionHeader);
        rowViewHolder.mLeftBadgeView = (ImageView) viewInflate.findViewById(R.id.LeftRowBadge);
        rowViewHolder.mDownloadProgress = viewInflate.findViewById(R.id.DownloadProgress);
        rowViewHolder.mAlbumArtTouchMask = viewInflate.findViewById(R.id.AlbumArtTouchOverlay);
        rowViewHolder.mDimAlbumArtOverlay = (ImageView) viewInflate.findViewById(R.id.DimAlbumArtOverlay);
        rowViewHolder.mPlaystateIcon = (ImageView) viewInflate.findViewById(R.id.PlaystateIcon);
        rowViewHolder.mBadgeContainer = (ViewGroup) viewInflate.findViewById(R.id.BadgeContainer);
        rowViewHolder.mDuration = (TextView) viewInflate.findViewById(R.id.Duration);
        DownloadButtonView downloadButtonView = (DownloadButtonView) viewInflate.findViewById(R.id.DownloadButton);
        rowViewHolder.mDownloadButtonView = downloadButtonView;
        if (!this.mShouldShowDownloadButton) {
            downloadButtonView.setVisibility(8);
        }
        View viewFindViewById = viewInflate.findViewById(R.id.PrimeAddButton);
        rowViewHolder.mPrimeAddView = viewFindViewById;
        viewFindViewById.setOnClickListener(this.mPrimeAddViewClickListener);
        View viewFindViewById2 = viewInflate.findViewById(R.id.overflow_button_open);
        rowViewHolder.mOverflowButtonView = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this.mOverflowMenuClickListener);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.missing_sd_card);
        rowViewHolder.mMissingSDCardIcon = imageView;
        imageView.setOnClickListener(this.mMissingSDCardClickListener);
        rowViewHolder.mAddDoneIcon = (ImageView) viewInflate.findViewById(R.id.add_done_icon);
        rowViewHolder.mAddSongToPlaylistIcon = (ImageView) viewInflate.findViewById(R.id.AddIcon);
        rowViewHolder.mStationIconInRightViews = (ImageView) viewInflate.findViewById(R.id.station_icon_in_right_views);
        if (this.mIsNightwingOnlyUser && !this.mFreeTierUserPlaylistEditingEnabled) {
            rowViewHolder.mOverflowButtonView.setVisibility(4);
            viewInflate.findViewById(R.id.RightViews).setVisibility(4);
        }
        if (rowViewHolder.mDuration != null) {
            if (ScreenUtil.isNormalOrSmallScreen() || !this.mShouldShowTrackDuration) {
                rowViewHolder.mDuration.setVisibility(8);
            } else {
                rowViewHolder.mDuration.setVisibility(0);
            }
        }
        View clickableBadgeView = iDownloadProgressView.getClickableBadgeView();
        if (clickableBadgeView != null) {
            clickableBadgeView.setTag(rowViewHolder);
            clickableBadgeView.setOnClickListener(this.mBadgeClickListener);
        }
        updateBadgeContainer(rowViewHolder.mBadgeContainer, false);
        rowViewHolder.mLibraryItem = new MusicTrack(AmazonApplication.getLibraryItemFactory(), null);
        viewInflate.setTag(rowViewHolder);
        this.mCreatedViews.put(viewInflate, null);
        return viewInflate;
    }

    private void updateBadgeContainerVisibility(RowViewHolder holder) {
        holder.mBadgeContainer.setVisibility(holder.mDuration.getVisibility() != 0 && holder.mDownloadProgress.getVisibility() != 0 && holder.mPrimeAddView.getVisibility() != 0 && holder.mAddDoneIcon.getVisibility() != 0 && holder.mMissingSDCardIcon.getVisibility() != 0 && holder.mAddSongToPlaylistIcon.getVisibility() != 0 && holder.mDownloadButtonView.getVisibility() != 0 && holder.mStationIconInRightViews.getVisibility() != 0 ? 8 : 0);
    }

    public void onAddAllPrimeTracks(AddButtonAnimation.AnimationFinishedListener animationFinishedListener) {
        Iterator<View> it = this.mCreatedViews.keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((RowViewHolder) it.next().getTag()).mPrimeAddView.getVisibility() == 0) {
                i++;
            }
        }
        this.mAddAllAnimationFinishedListener = new AddAllAnimationFinishedListener(i, animationFinishedListener);
        for (View view : this.mCreatedViews.keySet()) {
            RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
            if (rowViewHolder.mPrimeAddView.getVisibility() == 0) {
                View viewFindViewById = view.findViewById(R.id.RightViews);
                AddButtonAnimation.startAnimation(this.mContext, viewFindViewById, rowViewHolder.mPrimeAddView, viewFindViewById.findViewById(R.id.add_done_icon), this.mAddAllAnimationFinishedListener);
            }
        }
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    public void onPauseOrDestroy(final Context context) {
        AddAllAnimationFinishedListener addAllAnimationFinishedListener = this.mAddAllAnimationFinishedListener;
        if (addAllAnimationFinishedListener != null) {
            addAllAnimationFinishedListener.onAnimationCanceled();
        }
        super.onPauseOrDestroy(context);
    }

    private class AddAllAnimationFinishedListener implements AddButtonAnimation.AnimationFinishedListener {
        private final AddButtonAnimation.AnimationFinishedListener mAddAllAnimationFinishedNotifier;
        private int mCount;

        public AddAllAnimationFinishedListener(int count, AddButtonAnimation.AnimationFinishedListener addAllAnimationFinishedNotifier) {
            this.mCount = count;
            this.mAddAllAnimationFinishedNotifier = addAllAnimationFinishedNotifier;
        }

        @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
        public void onAnimationFinished() {
            int i = this.mCount - 1;
            this.mCount = i;
            if (i <= 0) {
                AddButtonAnimation.AnimationFinishedListener animationFinishedListener = this.mAddAllAnimationFinishedNotifier;
                if (animationFinishedListener != null) {
                    animationFinishedListener.onAnimationFinished();
                }
                TrackListAdapter.this.mAddAllAnimationFinishedListener = null;
            }
        }

        @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
        public void onAnimationCanceled() {
            AddButtonAnimation.AnimationFinishedListener animationFinishedListener = this.mAddAllAnimationFinishedNotifier;
            if (animationFinishedListener != null) {
                animationFinishedListener.onAnimationCanceled();
            }
            TrackListAdapter.this.mAddAllAnimationFinishedListener = null;
        }
    }

    @Override // com.amazon.mp3.library.adapter.CursorListAdapter, android.widget.SectionIndexer
    public int getPositionForSection(int sectionIndex) {
        DiscIndexer discIndexer;
        Cursor cursor = getCursor();
        if (!((cursor == null || cursor.isClosed()) ? false : true)) {
            return -1;
        }
        if (this.mIsSingleAlbum && (discIndexer = this.mDiscIndexer) != null) {
            return discIndexer.getPositionForSection(sectionIndex);
        }
        WindowedAlphabetIndexer windowedAlphabetIndexer = this.mAlphabetIndexer;
        if (windowedAlphabetIndexer != null) {
            return windowedAlphabetIndexer.getPositionForSection(sectionIndex);
        }
        return -1;
    }

    @Override // com.amazon.mp3.library.adapter.CursorListAdapter, android.widget.SectionIndexer
    public int getSectionForPosition(int position) {
        DiscIndexer discIndexer;
        if (!isCursorReady()) {
            return -1;
        }
        if (this.mIsSingleAlbum && (discIndexer = this.mDiscIndexer) != null) {
            return discIndexer.getSectionForPosition(position);
        }
        WindowedAlphabetIndexer windowedAlphabetIndexer = this.mAlphabetIndexer;
        if (windowedAlphabetIndexer != null) {
            return windowedAlphabetIndexer.getSectionForPosition(position);
        }
        return -1;
    }

    @Override // com.amazon.mp3.library.adapter.CursorListAdapter, android.widget.SectionIndexer
    public Object[] getSections() {
        DiscIndexer discIndexer;
        if (this.mIsSingleAlbum && (discIndexer = this.mDiscIndexer) != null) {
            return discIndexer.mDiscs.toArray(new Object[this.mDiscIndexer.mDiscs.size()]);
        }
        WindowedAlphabetIndexer windowedAlphabetIndexer = this.mAlphabetIndexer;
        return windowedAlphabetIndexer != null ? windowedAlphabetIndexer.getSections() : new Object[0];
    }

    protected boolean enableAlphabetIndexer() {
        VirtualizedCursor virtualizedCursor = this.mVirtualizedCursor;
        if (virtualizedCursor == null || virtualizedCursor.allRowsCached()) {
            return !this.mIsPlaylist;
        }
        return false;
    }

    public void showPopularityBar(final boolean show) {
        this.mShouldShowPopularityBar = show;
    }

    public void showStationIcon(final boolean show) {
        this.mShouldShowStationIcon = show;
    }

    public void setTracksMetadata(final Map<String, TrackMetadata> tracksMetadata) {
        this.mTracksMetadata = tracksMetadata;
    }

    public void showDownloadButton(final boolean show) {
        this.mShouldShowDownloadButton = show;
    }

    public static class RowViewHolder extends BadgeableListAdapter.BaseRowViewHolder {
        public Animator mAddAnimator;
        public ImageView mAddDoneIcon;
        public ImageView mAddSongToPlaylistIcon;
        public ImageView mAlbumArt;
        public View mAlbumArtTouchMask;
        public long mAlbumId;
        public TextView mArtist;
        public String mArtistLabel;
        public ViewGroup mBadgeContainer;
        public DownloadButtonView mDownloadButtonView;
        public View mDownloadProgress;
        public IDownloadProgressView mDownloadProgressView;
        public int mDownloadState;
        public TextView mDuration;
        public boolean mHasLyrics;
        public TextView mHeader;
        public View mHeaderContainer;
        public IPrimeMarkableView mIPrimeMarkableView;
        public BadgeView mImmersiveBadge;
        public BadgeView mKatanaBadge;
        public ImageView mLeftBadgeView;
        public BadgeView mLyricsBadgeView;
        public DeluxeContentUtil.ContentType mMediaType;
        public ImageView mMissingSDCardIcon;
        public View mOverflowButtonView;
        public TextView mPlaylistTrackCount;
        public FrameLayout mPlaylistTrackCountFrame;
        public TrackPopularityBar mPopularityBarView;
        public View mPrimeAddView;
        public ImageView mStationIconInRightViews;
        public TextView mTitle;
        public TextView mTrackCount;
        public Uri mTrackUri;

        @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter.BaseRowViewHolder
        public void updateProgress(int percent) {
            IDownloadProgressView iDownloadProgressView = this.mDownloadProgressView;
            if (iDownloadProgressView != null) {
                iDownloadProgressView.setProgress(percent);
            }
        }
    }

    private class DiscIndexer {
        private ArrayList<Disc> mDiscs = new ArrayList<>();

        private class Disc {
            public int mDiscNum;
            public int mStart;
            public String mTitle;

            private Disc() {
            }

            public String toString() {
                return String.valueOf(this.mDiscNum);
            }
        }

        public DiscIndexer(Cursor c) {
            if (c == null || c.getCount() == 0) {
                return;
            }
            String string = TrackListAdapter.this.mContext.getString(R.string.dmusic_library_disc_num_header);
            int columnIndex = c.getColumnIndex("disc_num");
            int columnIndex2 = c.getColumnIndex("album_id");
            int i = -1;
            c.moveToPosition(-1);
            int i2 = -1;
            while (c.moveToNext()) {
                int i3 = c.getInt(columnIndex);
                int i4 = c.getInt(columnIndex2);
                if (TrackListAdapter.this.mIsLocal && i3 >= 1000) {
                    i3 /= 1000;
                }
                if (i3 != i || i4 != i2) {
                    Disc disc = new Disc();
                    disc.mStart = c.getPosition();
                    disc.mDiscNum = i3;
                    disc.mTitle = String.format(string, Integer.valueOf(i3));
                    this.mDiscs.add(disc);
                    i = i3;
                    i2 = i4;
                }
            }
        }

        public int getCount() {
            return this.mDiscs.size();
        }

        public int getPositionForSection(int section) {
            if (this.mDiscs.size() < 2 || section < 0) {
                return -1;
            }
            if (section >= this.mDiscs.size()) {
                section = this.mDiscs.size() - 1;
            }
            return this.mDiscs.get(section).mStart;
        }

        public int getSectionForPosition(int position) {
            if (this.mDiscs.size() >= 2) {
                int size = this.mDiscs.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    Disc disc = this.mDiscs.get(size);
                    if (disc.mStart <= position) {
                        if (disc.mDiscNum > 1) {
                            return size;
                        }
                        if (size >= this.mDiscs.size() - 1 || this.mDiscs.get(size + 1).mDiscNum <= 1) {
                            break;
                        }
                        return size;
                    }
                    size--;
                }
            }
            return -1;
        }

        public String getTitleForSection(int section) {
            return this.mDiscs.get(section).mTitle;
        }
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected int getImageIdColumn() {
        return this.mAlbumIdIndex;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected ImageLoaderFactory.ItemType getImageType() {
        return this.mImageItemType;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected boolean isTrackBeingPlayed(BadgeableListAdapter.BaseRowViewHolder holder) {
        MediaItem currentMediaItem = PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentMediaItem();
        if (currentMediaItem == null) {
            return false;
        }
        MediaItemId.Type type = MediaItemId.Type.LUID;
        String id = currentMediaItem.getMediaItemId(type) != null ? currentMediaItem.getMediaItemId(type).getId() : "";
        MediaItemId.Type type2 = MediaItemId.Type.ASIN;
        String id2 = currentMediaItem.getMediaItemId(type2) != null ? currentMediaItem.getMediaItemId(type2).getId() : "";
        if (TextUtils.isEmpty(holder.mLuid) || !holder.mLuid.equals(id)) {
            return !TextUtils.isEmpty(holder.mAsin) && holder.mAsin.equals(id2);
        }
        return true;
    }

    protected void setAlbumArtworkVisibility(RowViewHolder holder, boolean show) {
        if (show) {
            holder.mAlbumArt.setVisibility(0);
            holder.mAlbumArtTouchMask.setVisibility(0);
        } else {
            holder.mAlbumArt.setVisibility(8);
            holder.mAlbumArtTouchMask.setVisibility(8);
            holder.mDimAlbumArtOverlay.setVisibility(8);
        }
    }

    public void setTrackAsinHighlight(final String trackAsinHighlight) {
        this.mTrackAsinHighlight = trackAsinHighlight;
    }

    protected boolean isLyricsSupported() {
        return this.mLyricsSupported;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected String getSource() {
        return this.mSourceId;
    }

    @Override // android.widget.CursorAdapter, android.widget.Adapter
    public int getCount() {
        return super.getCount();
    }

    public void setIsNightwingOnlyUser(boolean mIsNightwingOnlyUser) {
        this.mIsNightwingOnlyUser = mIsNightwingOnlyUser;
    }

    public void initNightwingSettings(boolean showCompressedView, int tracksCount) {
        setIsNightwingOnlyUser(true);
        showStationIcon(true);
    }

    public void initNightwingSettings() {
        if (UserSubscriptionUtil.isNightwingOnly() && this.mFreeTierUserPlaylistEditingEnabled) {
            setIsNightwingOnlyUser(true);
            showStationIcon(true);
        }
    }

    public ImageProcessor getImageProcessor() {
        return this.mImageProcessor;
    }

    @WorkerThread
    public List<MusicTrack> getAllTracks() {
        Cursor cursor = getCursor();
        int count = getCursor().getCount();
        int position = getCursor().getPosition();
        ArrayList arrayList = new ArrayList(count);
        if (count == 0) {
            return arrayList;
        }
        LibraryItemFactory libraryItemFactory = AmazonApplication.getLibraryItemFactory();
        try {
            if (!cursor.moveToFirst()) {
                return arrayList;
            }
            do {
                arrayList.add(libraryItemFactory.getTrack(cursor));
            } while (cursor.moveToNext());
            cursor.moveToPosition(position);
        } catch (Exception e) {
            Log.error(TAG, e.getMessage());
        }
        return arrayList;
    }

    private Boolean isBauhausLibraryEnabled() {
        return Boolean.valueOf(AmazonApplication.getCapabilities().isBauhausLibraryEnabled());
    }
}
