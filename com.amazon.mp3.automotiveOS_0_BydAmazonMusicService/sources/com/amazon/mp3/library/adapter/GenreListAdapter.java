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
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.adapter.BadgeableListAdapter;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Genre;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class GenreListAdapter extends BadgeableListAdapter implements IArtCache.IArtLoadedListener {
    private static final String TAG = GenreListAdapter.class.getSimpleName();
    private final View.OnClickListener mAddAllClickListener;
    private View.OnClickListener mBadgeClickListener;
    protected int mContentCatalogStatusMaxIndex;
    protected int mContentCatalogStatusMaxNonPreviousIndex;
    protected int mContentCatalogStatusMinIndex;
    protected int mContentOwnershipStatusMinIndex;
    private HashMap<Long, String> mCountCache;
    private WeakHashMap<View, Object> mCreatedViews;
    private int mDownloadStateIndex;
    private int mDownloadStatusIndex;
    private int mGenreIdIndex;
    private boolean mIsLocal;
    private int mLayoutId;
    private final View.OnClickListener mOverflowMenuClickListener;
    private OnPlaylistAddAllButtonClickListener mPlaylistAddAllClickListener;
    private Uri mPlaylistUri;
    private boolean mShouldShowDownloadBadging;
    private String mSource;
    private BauhausTextStyler mStyler;
    private boolean mUseGridView;

    public interface OnPlaylistAddAllButtonClickListener {
        void onClick(long genreId);
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache.IArtLoadedListener
    public void onDrawableLoaded(Drawable d, String source, String id) {
    }

    public static final class RowViewHolder extends BadgeableListAdapter.BaseRowViewHolder {
        public View mAddAllButton;
        public TextView mCounts;
        public int mDownloadGroupState;
        public IDownloadProgressView mDownloadProgressView;
        public int mDownloadState;
        public long mGenreId;
        public TextView mName;
        public View mOverflowButtonView;
        public Uri mUri;
        public boolean mUseGridView;

        @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter.BaseRowViewHolder
        public void updateProgress(int percent) {
            this.mDownloadProgressView.setProgress(percent);
            Log.debug(GenreListAdapter.TAG, "progress: " + percent);
        }
    }

    public GenreListAdapter(Context context, Uri contentUri, Uri playlistUri, boolean useGridView, IArtCache artCache) {
        super(context, null, false);
        this.mCreatedViews = new WeakHashMap<>();
        this.mBadgeClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.GenreListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
                Log.debug(GenreListAdapter.TAG, "holder state is: " + rowViewHolder.mDownloadState);
                int i = rowViewHolder.mDownloadState;
                if (i == 1 || i == 2 || i == 3 || i == 4) {
                    MusicDownloader.getInstance(GenreListAdapter.this.mContext).cancel(rowViewHolder.mDownloadId);
                    GenreListAdapter.this.removeProgress(rowViewHolder);
                }
            }
        };
        this.mAddAllClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.GenreListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                RowViewHolder rowViewHolder = (RowViewHolder) v.getTag();
                if (GenreListAdapter.this.mPlaylistAddAllClickListener != null) {
                    GenreListAdapter.this.mPlaylistAddAllClickListener.onClick(rowViewHolder.mGenreId);
                }
            }
        };
        this.mOverflowMenuClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.GenreListAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                v.performLongClick();
            }
        };
        this.mUseGridView = useGridView;
        this.mCountCache = new HashMap<>();
        this.mArtCache = artCache;
        setContentUri(context, contentUri);
        this.mPlaylistUri = playlistUri;
        this.mShouldShowDownloadBadging = playlistUri == null;
        this.mLayoutId = this.mUseGridView ? R.layout.library_genre_griditem : R.layout.library_genre_row;
        this.mStyler = new BauhausTextStyler();
    }

    public void setContentUri(Context context, Uri contentUri) {
        this.mSource = MediaProvider.getSource(contentUri);
        this.mIsLocal = CirrusMediaSource.matchLocal(contentUri);
    }

    private int getArtSize() {
        return AmazonApplication.getContext().getResources().getDimensionPixelSize(this.mUseGridView ? R.dimen.default_gridview_art_size : R.dimen.default_listview_art_size);
    }

    public void setPlaylistAddAllButtonClickListener(OnPlaylistAddAllButtonClickListener listener) {
        this.mPlaylistAddAllClickListener = listener;
    }

    @Override // com.amazon.mp3.library.adapter.CursorListAdapter
    public void setupNewCursor(Cursor c) {
        this.mCountCache.clear();
        if (c != null) {
            this.mIndexer = null;
            this.mNameIndex = c.getColumnIndexOrThrow("name");
            this.mGenreIdIndex = c.getColumnIndexOrThrow("_id");
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
            if (rowViewHolder.mGenreId == id) {
                rowViewHolder.mDownloadProgressView.setArtworkDrawable(d);
            }
        }
    }

    @Override // android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor c) {
        RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
        Genre genrePopulateGenre = AmazonApplication.getLibraryItemFactory().populateGenre(c, (Genre) rowViewHolder.mLibraryItem);
        String name = genrePopulateGenre.getName();
        DefaultStringType defaultStringType = DefaultStringType.GENRE;
        if (defaultStringType.isPlatformOrCirrusDefault(name)) {
            name = defaultStringType.getDefault();
        }
        rowViewHolder.mGenreId = c.getLong(this.mGenreIdIndex);
        genrePopulateGenre.setContentUri(MediaProvider.Genres.Tracks.getContentUri(this.mIsLocal ? "cirrus-local" : "cirrus", Long.parseLong(genrePopulateGenre.getId())));
        genrePopulateGenre.setMinContentCatalogStatusValue(c.getInt(this.mContentCatalogStatusMinIndex));
        genrePopulateGenre.setMaxNonPreviousContentCatalogStatusValue(c.getInt(this.mContentCatalogStatusMaxNonPreviousIndex));
        genrePopulateGenre.setMaxContentCatalogStatusValue(c.getInt(this.mContentCatalogStatusMaxIndex));
        genrePopulateGenre.setMinContentOwnershipStatusValue(c.getInt(this.mContentOwnershipStatusMinIndex));
        rowViewHolder.mUseGridView = this.mUseGridView;
        rowViewHolder.mDownloadProgressView.setArtworkDrawable(this.mArtCache.getDrawable(ImageLoaderFactory.ItemType.GENRE, this.mIsLocal ? "cirrus-local" : "cirrus", rowViewHolder.mGenreId));
        String countText = getCountText((int) genrePopulateGenre.getAlbumCount(), (int) genrePopulateGenre.getTrackCount(), rowViewHolder.mGenreId);
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            rowViewHolder.mCounts.setText(this.mStyler.styleText(countText));
            rowViewHolder.mName.setText(this.mStyler.styleText(name));
        } else {
            rowViewHolder.mCounts.setText(countText);
            rowViewHolder.mName.setText(name);
        }
        rowViewHolder.mUri = genrePopulateGenre.getContentUri();
        bindPlaystateIcon(context, rowViewHolder);
        if (this.mPlaylistUri == null) {
            if (this.mIsLocal) {
                rowViewHolder.mDownloadProgressView.setDownloadState(-1);
            } else {
                rowViewHolder.mDownloadId = MediaProvider.Genres.Tracks.getContentUri("cirrus", rowViewHolder.mGenreId).toString();
                rowViewHolder.mDownloadGroupState = c.getInt(this.mDownloadStatusIndex);
                int i = this.mShouldShowDownloadBadging ? c.getInt(this.mDownloadStateIndex) : -1;
                rowViewHolder.mDownloadState = i;
                if (i == 0 && genrePopulateGenre.isAllPreviouslyCatalog() && genrePopulateGenre.isNotOwned()) {
                    rowViewHolder.mDownloadProgressView.setDownloadState(5);
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
            return;
        }
        if (UserSubscriptionUtil.isNightwingOnly()) {
            rowViewHolder.mAddAllButton.setVisibility(8);
            rowViewHolder.mOverflowButtonView.setVisibility(8);
        } else {
            rowViewHolder.mAddAllButton.setVisibility(0);
            if (this.mUseGridView) {
                return;
            }
            rowViewHolder.mOverflowButtonView.setVisibility(8);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewInflate = View.inflate(context, this.mLayoutId, null);
        RowViewHolder rowViewHolder = new RowViewHolder();
        rowViewHolder.mDownloadProgressView = (IDownloadProgressView) viewInflate;
        rowViewHolder.mName = (TextView) viewInflate.findViewById(R.id.GenreName);
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
            view.setTag(rowViewHolder);
            rowViewHolder.mAddAllButton.setOnClickListener(this.mAddAllClickListener);
        }
        if (!this.mUseGridView) {
            View viewFindViewById = viewInflate.findViewById(R.id.overflow_button_open);
            rowViewHolder.mOverflowButtonView = viewFindViewById;
            viewFindViewById.setOnClickListener(this.mOverflowMenuClickListener);
        }
        rowViewHolder.mLibraryItem = new Genre(AmazonApplication.getLibraryItemFactory(), null);
        this.mCreatedViews.put(viewInflate, null);
        return viewInflate;
    }

    private String getCountText(int albumCount, int trackCount, long genreId) {
        String str = this.mCountCache.get(Long.valueOf(genreId));
        return str == null ? formatCountText(this.mContext, albumCount, trackCount) : str;
    }

    public static String formatCountText(Context context, int albumCount, int trackCount) {
        Resources resources = context.getResources();
        return resources.getQuantityString(R.plurals.dmusic_library_albums_count, albumCount, Integer.valueOf(albumCount)) + ", " + resources.getQuantityString(R.plurals.dmusic_library_songs_count, trackCount, Integer.valueOf(trackCount));
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected int getImageIdColumn() {
        return this.mGenreIdIndex;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected ImageLoaderFactory.ItemType getImageType() {
        return ImageLoaderFactory.ItemType.GENRE;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected String getSource() {
        return this.mSource;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected boolean isTrackBeingPlayed(final BadgeableListAdapter.BaseRowViewHolder holder) {
        return MediaItemHelper.isCurrentlyPlayingCollection(((RowViewHolder) holder).mUri);
    }
}
