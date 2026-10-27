package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.adapter.BadgeableListAdapter;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.DefaultStringType;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.IBadgeableView;
import com.amazon.mp3.view.IDownloadProgressView;
import com.amazon.music.find.data.database.SqliteLibrarySearchDao;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class ArtistListAdapter extends BadgeableListAdapter implements IArtCache.IArtLoadedListener {
    private static final String TAG = ArtistListAdapter.class.getSimpleName();
    private boolean comesFromFollowedArtistsFragment;
    private final View.OnClickListener mAddAllClickListener;
    private int mArtistIdIndex;
    private final View.OnClickListener mBadgeClickListener;
    protected int mContentCatalogStatusMaxIndex;
    protected int mContentCatalogStatusMaxNonPreviousIndex;
    protected int mContentCatalogStatusMinIndex;
    protected int mContentOwnershipStatusMinIndex;
    private final HashMap<Long, String> mCountCache;
    private final WeakHashMap<View, Object> mCreatedViews;
    private int mDownloadStateIndex;
    private int mDownloadStatusIndex;
    private boolean mIsLocal;
    private final int mLayoutId;
    private final View.OnClickListener mOverflowMenuClickListener;
    private OnPlaylistAddAllButtonClickListener mPlaylistAddAllClickListener;
    private final Uri mPlaylistUri;
    private final boolean mShouldShowDownloadBadging;
    private StyleSheet mStyleSheet;
    private BauhausTextStyler mStyler;
    private final boolean mUseGridView;

    public interface OnPlaylistAddAllButtonClickListener {
        void onClick(long artistId);
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache.IArtLoadedListener
    public void onDrawableLoaded(Drawable d, String source, String id) {
    }

    public static final class RowViewHolder extends BadgeableListAdapter.BaseRowViewHolder {
        public View mAddAllButton;
        public long mArtistId;
        public TextView mCounts;
        public int mDownloadGroupState;
        public IDownloadProgressView mDownloadProgressView;
        public int mDownloadState;
        public TextView mName;
        public View mOverflowButtonView;
        public Uri mUri;
        public boolean mUseGridView;

        @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter.BaseRowViewHolder
        public void updateProgress(int percent) {
            this.mDownloadProgressView.setProgress(percent);
            Log.debug(ArtistListAdapter.TAG, "progress: " + percent);
        }
    }

    public ArtistListAdapter(Context context, Uri contentUri, Uri playlistUri, boolean useGridView, IArtCache artCache) {
        super(context, null, false);
        this.comesFromFollowedArtistsFragment = false;
        this.mCreatedViews = new WeakHashMap<>();
        this.mBadgeClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.ArtistListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
                Log.debug(ArtistListAdapter.TAG, "holder state is: " + rowViewHolder.mDownloadState);
                int i = rowViewHolder.mDownloadState;
                if (i != 1) {
                    if (i == 2) {
                        MusicDownloader.showRetryDialog(ArtistListAdapter.this.mContext, rowViewHolder.mUri, rowViewHolder.mDownloadId);
                        return;
                    } else if (i != 3 && i != 4) {
                        return;
                    }
                }
                MusicDownloader.getInstance(ArtistListAdapter.this.mContext).cancel(rowViewHolder.mDownloadId);
                ArtistListAdapter.this.removeProgress(rowViewHolder);
            }
        };
        this.mAddAllClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.ArtistListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                RowViewHolder rowViewHolder = (RowViewHolder) v.getTag();
                if (ArtistListAdapter.this.mPlaylistAddAllClickListener != null) {
                    ArtistListAdapter.this.mPlaylistAddAllClickListener.onClick(rowViewHolder.mArtistId);
                }
            }
        };
        this.mOverflowMenuClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.ArtistListAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                v.performLongClick();
            }
        };
        this.mUseGridView = useGridView;
        this.mArtCache = artCache;
        this.mCountCache = new HashMap<>();
        setContentUri(context, contentUri);
        this.mPlaylistUri = playlistUri;
        this.mShouldShowDownloadBadging = playlistUri == null;
        this.mLayoutId = useGridView ? R.layout.library_artist_griditem : R.layout.library_artist_row;
        this.mStyler = new BauhausTextStyler();
    }

    public void setContentUri(Context context, Uri contentUri) {
        this.mIsLocal = CirrusMediaSource.matchLocal(contentUri);
    }

    private int getArtSize() {
        Resources resources = AmazonApplication.getContext().getResources();
        if (this.mUseGridView) {
            return resources.getDimensionPixelSize(R.dimen.default_gridview_art_size);
        }
        return resources.getDimensionPixelSize(R.dimen.default_listview_art_size);
    }

    public void setPlaylistAddAllButtonClickListener(OnPlaylistAddAllButtonClickListener listener) {
        this.mPlaylistAddAllClickListener = listener;
    }

    @Override // com.amazon.mp3.library.adapter.CursorListAdapter
    public void setupNewCursor(Cursor c) {
        this.mCountCache.clear();
        if (c != null) {
            this.mIndexer = null;
            this.mNameIndex = c.getColumnIndexOrThrow("sort_name");
            this.mArtistIdIndex = c.getColumnIndexOrThrow("_id");
            this.mDownloadStateIndex = c.getColumnIndexOrThrow("download_state");
            this.mDownloadStatusIndex = c.getColumnIndexOrThrow("download_status");
            this.mContentOwnershipStatusMinIndex = c.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN);
            this.mContentCatalogStatusMinIndex = c.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN);
            this.mContentCatalogStatusMaxNonPreviousIndex = c.getColumnIndexOrThrow("content_catalog_status_max_non_previous");
            this.mContentCatalogStatusMaxIndex = c.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX);
        }
        checkCreateAlphabetIndexer(c);
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache.IArtLoadedListener
    public void onDrawableLoaded(Drawable d, String source, long id) {
        Iterator<View> it = this.mCreatedViews.keySet().iterator();
        while (it.hasNext()) {
            RowViewHolder rowViewHolder = (RowViewHolder) it.next().getTag();
            if (rowViewHolder.mArtistId == id) {
                rowViewHolder.mDownloadProgressView.setArtworkDrawable(d);
            }
        }
    }

    @Override // android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor c) {
        RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
        Artist artistPopulateArtist = AmazonApplication.getLibraryItemFactory().populateArtist(c, (Artist) rowViewHolder.mLibraryItem);
        long j = Long.parseLong(artistPopulateArtist.getId());
        rowViewHolder.mArtistId = j;
        rowViewHolder.mDownloadProgressView.setArtworkDrawable(this.mArtCache.getDrawable(ImageLoaderFactory.ItemType.ARTIST, (String) null, j));
        artistPopulateArtist.setContentUri(MediaProvider.Artists.Tracks.getContentUri(this.mIsLocal ? "cirrus-local" : "cirrus", Long.parseLong(artistPopulateArtist.getId())));
        artistPopulateArtist.setMinContentCatalogStatusValue(c.getInt(this.mContentCatalogStatusMinIndex));
        artistPopulateArtist.setMaxNonPreviousContentCatalogStatusValue(c.getInt(this.mContentCatalogStatusMaxNonPreviousIndex));
        artistPopulateArtist.setMaxContentCatalogStatusValue(c.getInt(this.mContentCatalogStatusMaxIndex));
        artistPopulateArtist.setMinContentOwnershipStatusValue(c.getInt(this.mContentOwnershipStatusMinIndex));
        String name = artistPopulateArtist.getName();
        DefaultStringType defaultStringType = DefaultStringType.ARTIST;
        if (defaultStringType.isPlatformOrCirrusDefault(name)) {
            name = defaultStringType.getDefault();
        }
        rowViewHolder.mUseGridView = this.mUseGridView;
        String countText = getCountText((int) artistPopulateArtist.getAlbumCount(), (int) artistPopulateArtist.getTrackCount(), rowViewHolder.mArtistId);
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            rowViewHolder.mCounts.setText(this.mStyler.styleText(countText));
            rowViewHolder.mName.setText(this.mStyler.styleText(name));
        } else {
            rowViewHolder.mCounts.setText(countText);
            rowViewHolder.mName.setText(name);
        }
        rowViewHolder.mUri = artistPopulateArtist.getContentUri();
        bindPlaystateIcon(context, rowViewHolder);
        if (this.mPlaylistUri == null) {
            if (this.mIsLocal) {
                rowViewHolder.mDownloadProgressView.setDownloadState(-1);
            } else {
                rowViewHolder.mDownloadId = MediaProvider.Artists.Tracks.getContentUri("cirrus", rowViewHolder.mArtistId).toString();
                rowViewHolder.mDownloadGroupState = c.getInt(this.mDownloadStatusIndex);
                int i = this.mShouldShowDownloadBadging ? c.getInt(this.mDownloadStateIndex) : -1;
                rowViewHolder.mDownloadState = i;
                if (i == 0 && artistPopulateArtist.isAllPreviouslyCatalog() && artistPopulateArtist.isNotOwned()) {
                    rowViewHolder.mDownloadProgressView.setDownloadState(-1);
                } else {
                    rowViewHolder.mDownloadProgressView.setDownloadState(rowViewHolder.mDownloadState);
                }
                if (rowViewHolder.mDownloadState == 4 && MusicDownloader.getInstance(this.mContext).getGroup(rowViewHolder.mDownloadId) == null) {
                    rowViewHolder.mDownloadState = -1;
                    rowViewHolder.mDownloadGroupState = -1;
                    rowViewHolder.mDownloadProgressView.setDownloadState(-1);
                }
                int i2 = rowViewHolder.mDownloadState;
                if (i2 == 1 || i2 == 3 || i2 == 4) {
                    addProgress(rowViewHolder);
                }
            }
            rowViewHolder.mAddAllButton.setVisibility(8);
        } else if (UserSubscriptionUtil.isNightwingOnly()) {
            rowViewHolder.mAddAllButton.setVisibility(8);
            rowViewHolder.mOverflowButtonView.setVisibility(8);
        } else {
            rowViewHolder.mAddAllButton.setVisibility(0);
            if (!this.mUseGridView) {
                rowViewHolder.mOverflowButtonView.setVisibility(8);
            }
        }
        if (!this.comesFromFollowedArtistsFragment || this.mUseGridView) {
            return;
        }
        rowViewHolder.mOverflowButtonView.setVisibility(8);
        rowViewHolder.mDownloadProgressView.getClickableBadgeView().setVisibility(8);
        rowViewHolder.mCounts.setVisibility(8);
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet != null) {
            Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.BASE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rowViewHolder.mName.getLayoutParams();
            if (spacerInPixels != null) {
                layoutParams.setMarginStart(spacerInPixels.intValue());
                layoutParams.setMarginEnd(spacerInPixels.intValue());
            }
            rowViewHolder.mName.setLayoutParams(layoutParams);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewInflate = View.inflate(context, this.mLayoutId, null);
        RowViewHolder rowViewHolder = new RowViewHolder();
        rowViewHolder.mDownloadProgressView = (IDownloadProgressView) viewInflate;
        rowViewHolder.mName = (TextView) viewInflate.findViewById(R.id.ArtistName);
        rowViewHolder.mCounts = (TextView) viewInflate.findViewById(R.id.Counts);
        rowViewHolder.mAddAllButton = viewInflate.findViewById(R.id.AddAllButton);
        rowViewHolder.mDimAlbumArtOverlay = (ImageView) viewInflate.findViewById(R.id.DimAlbumArtOverlay);
        rowViewHolder.mPlaystateIcon = (ImageView) viewInflate.findViewById(R.id.PlaystateIcon);
        KeyEvent.Callback callbackFindViewById = viewInflate.findViewById(R.id.cover);
        if (callbackFindViewById instanceof IBadgeableView) {
            rowViewHolder.mBadgeableCoverView = (IBadgeableView) callbackFindViewById;
        }
        viewInflate.setTag(rowViewHolder);
        View clickableBadgeView = rowViewHolder.mDownloadProgressView.getClickableBadgeView();
        if (clickableBadgeView != null) {
            clickableBadgeView.setTag(rowViewHolder);
            clickableBadgeView.setOnClickListener(this.mBadgeClickListener);
        }
        View view = rowViewHolder.mAddAllButton;
        if (view != null) {
            view.setOnClickListener(this.mAddAllClickListener);
            rowViewHolder.mAddAllButton.setTag(rowViewHolder);
        }
        if (!this.mUseGridView) {
            View viewFindViewById = viewInflate.findViewById(R.id.overflow_button_open);
            rowViewHolder.mOverflowButtonView = viewFindViewById;
            viewFindViewById.setOnClickListener(this.mOverflowMenuClickListener);
        }
        rowViewHolder.mLibraryItem = new Artist(AmazonApplication.getLibraryItemFactory(), null);
        this.mCreatedViews.put(viewInflate, null);
        return viewInflate;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter, android.widget.CursorAdapter
    protected void onContentChanged() {
        super.onContentChanged();
        checkCreateAlphabetIndexer(getCursor());
    }

    private String getCountText(int albumCount, int trackCount, long artistId) {
        String str = this.mCountCache.get(Long.valueOf(artistId));
        if (str != null) {
            return str;
        }
        Resources resources = this.mContext.getResources();
        String str2 = resources.getQuantityString(R.plurals.dmusic_library_albums_count, albumCount, Integer.valueOf(albumCount)) + ", " + resources.getQuantityString(R.plurals.dmusic_library_songs_count, trackCount, Integer.valueOf(trackCount));
        this.mCountCache.put(Long.valueOf(artistId), str2);
        return str2;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected int getImageIdColumn() {
        return this.mArtistIdIndex;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected ImageLoaderFactory.ItemType getImageType() {
        return ImageLoaderFactory.ItemType.ARTIST;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected boolean isTrackBeingPlayed(final BadgeableListAdapter.BaseRowViewHolder holder) {
        return MediaItemHelper.isCurrentlyPlayingCollection(((RowViewHolder) holder).mUri);
    }

    public void setLaunchedFromFollowedArtistsFragment(final boolean launchedFromFollowedArtistsFragment) {
        this.comesFromFollowedArtistsFragment = launchedFromFollowedArtistsFragment;
    }

    public void setStyleSheet(final StyleSheet styleSheet) {
        this.mStyleSheet = styleSheet;
    }
}
