package com.amazon.mp3.library.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.adapter.BadgeableListAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.IBadgeableView;
import com.amazon.mp3.view.IContentDisabledMarkableView;
import com.amazon.mp3.view.IDownloadProgressView;
import com.amazon.mp3.view.IPrimeMarkableView;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistsListAdapter extends BadgeableListAdapter {
    private static final String TAG = PlaylistsListAdapter.class.getSimpleName();
    private final View.OnClickListener mAddAllClickListener;
    private int mArtworkSize;
    private View.OnClickListener mBadgeClickListener;
    private CacheManager mCacheManager;
    private View mCreatePlaylistView;
    private int mDownloadStateIndex;
    private int mIdIndex;
    private boolean mIsLocal;
    private Bitmap mNewPlaylistGridCoverBitmap;
    private final View.OnClickListener mOverflowMenuClickListener;
    private OnPlaylistAddAllButtonClickListener mPlaylistAddAllClickListener;
    private Uri mPlaylistUri;
    private int mPrimeIndex;
    private int mPrimePlaylistUpdatedIndex;
    private final boolean mShouldShowDownloadBadging;
    private final boolean mShouldShowPrimeBadging;
    private String mSource;
    private BauhausTextStyler mStyler;
    private int mTrackCountIndex;
    private int mTypeIndex;

    public interface OnPlaylistAddAllButtonClickListener {
        void onClick(int position);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 2;
    }

    @Override // com.amazon.mp3.library.adapter.CursorListAdapter
    public void setupNewCursor(Cursor c) {
    }

    public static class RowViewHolder extends BadgeableListAdapter.BaseRowViewHolder {
        public View mAddAllButton;
        public IDownloadProgressView mDownloadProgressView;
        public int mDownloadState;
        public TextView mHeader;
        public boolean mIsCatalogPlaylist;
        public boolean mIsCatalogPlaylistUpdated;
        public boolean mIsEntityPlaylist;
        public boolean mIsMusicSubscription;
        public boolean mIsPrime;
        public boolean mIsSmartPlaylist;
        public TextView mName;
        public View mOverflowButtonView;
        public long mPlaylistId;
        public String mPlaylistType;
        public IPrimeMarkableView mPrimeMarkableView;
        public TextView mTrackCount;
        public Uri mUri;
        public int mViewType;

        @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter.BaseRowViewHolder
        public void updateProgress(int percent) {
            this.mDownloadProgressView.setProgress(percent);
            Log.debug(PlaylistsListAdapter.TAG, "progress: " + percent);
        }
    }

    public PlaylistsListAdapter(Activity hostActivity, Uri contentUri, Uri playlistUri, IArtCache artCache, boolean shouldShowPrimeBadging) {
        super(hostActivity, null, false);
        this.mCacheManager = null;
        this.mBadgeClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.PlaylistsListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
                int i = rowViewHolder.mDownloadState;
                Log.debug(PlaylistsListAdapter.TAG, "holder state is: " + i);
                int i2 = rowViewHolder.mDownloadState;
                if (i2 != 1) {
                    if (i2 == 2) {
                        MusicDownloader.showRetryDialog(PlaylistsListAdapter.this.mContext, rowViewHolder.mUri, rowViewHolder.mDownloadId);
                        return;
                    } else if (i2 != 3 && i2 != 4) {
                        return;
                    }
                }
                MusicDownloader.getInstance(PlaylistsListAdapter.this.mContext).cancel(rowViewHolder.mDownloadId);
                PlaylistsListAdapter.this.removeProgress(rowViewHolder);
            }
        };
        this.mAddAllClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.PlaylistsListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                if (UserSubscriptionUtil.isNightwingOnly()) {
                    return;
                }
                RowViewHolder rowViewHolder = (RowViewHolder) v.getTag();
                if (PlaylistsListAdapter.this.mPlaylistAddAllClickListener != null) {
                    PlaylistsListAdapter.this.mPlaylistAddAllClickListener.onClick(rowViewHolder.mPosition);
                }
            }
        };
        this.mOverflowMenuClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.PlaylistsListAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                v.performLongClick();
            }
        };
        this.mArtworkSize = calculateArtworkSize(hostActivity);
        setContentUri(contentUri);
        this.mPlaylistUri = playlistUri;
        this.mArtCache = artCache;
        this.mShouldShowDownloadBadging = playlistUri == null;
        this.mShouldShowPrimeBadging = shouldShowPrimeBadging;
        this.mStyler = new BauhausTextStyler();
    }

    public void setContentUri(Uri contentUri) {
        this.mSource = MediaProvider.getSource(contentUri);
        this.mIsLocal = CirrusMediaSource.matchLocal(contentUri);
    }

    public void setPlaylistAddAllButtonClickListener(OnPlaylistAddAllButtonClickListener listener) {
        this.mPlaylistAddAllClickListener = listener;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter, com.amazon.mp3.library.adapter.CursorListAdapter, android.widget.CursorAdapter
    public void changeCursor(Cursor c) {
        super.changeCursor(c);
        if (c != null) {
            this.mIdIndex = c.getColumnIndexOrThrow("_id");
            this.mNameIndex = c.getColumnIndexOrThrow("name");
            this.mTypeIndex = c.getColumnIndexOrThrow(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            this.mTrackCountIndex = c.getColumnIndex("track_count");
            this.mPrimeIndex = c.getColumnIndex("is_prime");
            this.mPrimePlaylistUpdatedIndex = c.getColumnIndex("is_new");
            this.mDownloadStateIndex = c.getColumnIndexOrThrow("download_state");
        }
    }

    @Override // android.widget.CursorAdapter, android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (this.mPlaylistUri == null) {
            if (position == 0 && getIdForRow(position) == -1) {
                if (this.mCreatePlaylistView == null || AccessibilityUtil.isAccessibilityEnabled(this.mContext)) {
                    this.mCreatePlaylistView = LayoutInflater.from(this.mContext).inflate(R.layout.library_create_playlist_row, parent, false);
                }
                convertView = this.mCreatePlaylistView;
                if (convertView.getTag() == null) {
                    RowViewHolder rowViewHolder = new RowViewHolder();
                    rowViewHolder.mName = (TextView) convertView.findViewById(R.id.PlaylistName);
                    rowViewHolder.mHeader = (TextView) convertView.findViewById(R.id.SectionHeader);
                    KeyEvent.Callback callbackFindViewById = convertView.findViewById(R.id.cover);
                    if (callbackFindViewById != null && (callbackFindViewById instanceof IBadgeableView)) {
                        rowViewHolder.mBadgeableCoverView = (IBadgeableView) callbackFindViewById;
                        rowViewHolder.mBadgeableCoverView.setCoverDrawable(new BitmapDrawable(parent.getResources(), this.mNewPlaylistGridCoverBitmap));
                    }
                    convertView.setTag(rowViewHolder);
                }
            } else if (convertView == this.mCreatePlaylistView) {
                convertView = null;
            }
        }
        return super.getView(position, convertView, parent);
    }

    private int getArtSize() {
        return this.mContext.getResources().getDimensionPixelSize(R.dimen.default_listview_art_size);
    }

    @Override // android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
        rowViewHolder.mPlaylistType = cursor.getString(this.mTypeIndex);
        int i = rowViewHolder.mViewType;
        if (i == 0) {
            bindPlaylistView(view, context, cursor);
        } else {
            if (i != 1) {
                return;
            }
            bindHeaderView(view, context);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void bindPlaylistView(View view, Context context, Cursor cursor) {
        Uri contentUri;
        Drawable drawable;
        RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
        String string = cursor.getString(this.mTypeIndex);
        String string2 = cursor.getString(this.mNameIndex);
        rowViewHolder.mIsSmartPlaylist = string.equals("vnd.android.cursor.item/vnd.amazonmp3.smartplaylist");
        TextView textView = rowViewHolder.mHeader;
        if (textView != null) {
            textView.setVisibility(8);
        }
        rowViewHolder.mIsCatalogPlaylist = string.equals("vnd.android.cursor.item/vnd.amazonmp3.primeplaylist");
        long j = cursor.getLong(this.mIdIndex);
        int position = cursor.getPosition();
        rowViewHolder.mPlaylistId = j;
        rowViewHolder.mPosition = position;
        if (this.mPlaylistUri == null && j == -1 && position == 0) {
            return;
        }
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            rowViewHolder.mName.setText(this.mStyler.styleText(string2));
        } else {
            rowViewHolder.mName.setText(string2);
        }
        if (this.mCacheManager == null) {
            this.mCacheManager = CacheManager.getInstance();
        }
        if (rowViewHolder.mIsCatalogPlaylist) {
            ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL;
            String string3 = cursor.getString(cursor.getColumnIndex("asin"));
            rowViewHolder.mAsin = string3;
            rowViewHolder.mIsEntityPlaylist = cursor.getInt(cursor.getColumnIndex("is_entity_playlist")) == Integer.parseInt("1");
            drawable = this.mArtCache.getDrawable(itemType, this.mSource, string3);
            contentUri = MediaProvider.PrimePlaylists.getContentUri(this.mSource, string3);
            rowViewHolder.mDownloadId = MediaProvider.PrimePlaylists.Tracks.getContentUri(this.mSource, string3).toString();
            int i = this.mPrimeIndex;
            boolean z = i < 0 || cursor.getInt(i) == Integer.parseInt("1");
            rowViewHolder.mIsPrime = z;
            if (rowViewHolder.mIsCatalogPlaylist && !z) {
                rowViewHolder.mIsMusicSubscription = true;
            }
            boolean z2 = cursor.getInt(this.mPrimePlaylistUpdatedIndex) == Integer.parseInt("1");
            rowViewHolder.mIsCatalogPlaylistUpdated = z2;
            rowViewHolder.mPrimeMarkableView.markAsNew(z2);
            rowViewHolder.mPrimeMarkableView.markAsPrime(this.mShouldShowPrimeBadging && z);
        } else if (rowViewHolder.mIsSmartPlaylist) {
            ImageLoaderFactory.ItemType itemType2 = ImageLoaderFactory.ItemType.PLAYLIST_SMART;
            Uri contentUri2 = MediaProvider.SmartPlaylists.getContentUri(this.mSource, j);
            rowViewHolder.mDownloadId = MediaProvider.SmartPlaylists.Tracks.getContentUri(this.mSource, j).toString();
            drawable = this.mCacheManager.get(itemType2, this.mSource, getArtSize(), Long.toString(j));
            contentUri = contentUri2;
        } else {
            Uri contentUri3 = MediaProvider.UdoPlaylists.getContentUri(this.mSource, j);
            rowViewHolder.mDownloadId = MediaProvider.UdoPlaylists.Tracks.getContentUri(this.mSource, j).toString();
            Drawable drawable2 = this.mCacheManager.get(ImageLoaderFactory.ItemType.PLAYLIST_UDO_THUMBNAIL, this.mSource, getArtSize(), Long.toString(j));
            if (drawable2 == null) {
                drawable2 = this.mCacheManager.get(ImageLoaderFactory.ItemType.PLAYLIST_UDO, this.mSource, getArtSize(), Long.toString(j));
            }
            contentUri = contentUri3;
            drawable = drawable2;
        }
        rowViewHolder.mUri = contentUri;
        rowViewHolder.mDownloadProgressView.setArtworkDrawable(drawable);
        bindPlaystateIcon(context, rowViewHolder);
        if (this.mIsLocal) {
            rowViewHolder.mDownloadProgressView.setDownloadState(-1);
        } else {
            int i2 = this.mShouldShowDownloadBadging ? cursor.getInt(this.mDownloadStateIndex) : -1;
            int i3 = (i2 == 4 && MusicDownloader.getInstance(this.mContext).getGroup(rowViewHolder.mDownloadId) == null) ? -1 : i2;
            rowViewHolder.mDownloadState = i3;
            rowViewHolder.mDownloadProgressView.setDownloadState(i3);
            if (i3 == 1 || i3 == 3 || i3 == 4) {
                addProgress(rowViewHolder);
                rowViewHolder.mDownloadProgressView.setProgress(getProgress(rowViewHolder));
            }
        }
        View view2 = rowViewHolder.mAddAllButton;
        if (view2 != null) {
            view2.setVisibility((this.mPlaylistUri == null || UserSubscriptionUtil.isNightwingOnly()) ? 8 : 0);
        }
        if (this.mPlaylistUri != null) {
            rowViewHolder.mOverflowButtonView.setVisibility(8);
        }
        Playlist playlistPopulatePlaylist = AmazonApplication.getLibraryItemFactory().populatePlaylist(cursor, (Playlist) rowViewHolder.mLibraryItem, null);
        playlistPopulatePlaylist.setSource(this.mSource);
        setTrackCount(cursor, rowViewHolder);
        if (rowViewHolder.mIsCatalogPlaylist) {
            playlistPopulatePlaylist.setAsin(rowViewHolder.mAsin);
            playlistPopulatePlaylist.setType(string);
            playlistPopulatePlaylist.setCatalogStatusForCatalogOnlyContent(rowViewHolder.mIsPrime, rowViewHolder.mIsMusicSubscription);
        }
        if (contentUri != null) {
            playlistPopulatePlaylist.setContentUri(contentUri);
        }
        if (view instanceof IContentDisabledMarkableView) {
            ((IContentDisabledMarkableView) view).setContentEnabled(playlistPopulatePlaylist.isAvailable());
        }
        View view3 = rowViewHolder.mOverflowButtonView;
        if (view3 != null && rowViewHolder.mIsSmartPlaylist && rowViewHolder.mDownloadState == 0) {
            view3.setVisibility(4);
        }
    }

    private void setTrackCount(final Cursor cursor, final RowViewHolder holder) {
        int playlistTracksCount;
        if ((holder.mIsCatalogPlaylist || holder.mIsSmartPlaylist || holder.mDownloadState != 0) ? false : true) {
            playlistTracksCount = PlaylistUtil.getPlaylistTracksCount(this.mContext, holder.mPlaylistId, getSource());
        } else {
            playlistTracksCount = cursor.getInt(this.mTrackCountIndex);
        }
        String str = this.mContext.getResources().getQuantityString(R.plurals.dmusic_library_songs_count, playlistTracksCount, Integer.valueOf(playlistTracksCount)) + " ";
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            holder.mTrackCount.setText(this.mStyler.styleText(str));
        } else {
            holder.mTrackCount.setText(str);
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int position) {
        String playlistType = getPlaylistType(position);
        if (TextUtils.isEmpty(playlistType)) {
            return -1;
        }
        return ("vnd.android.cursor.item/vnd.amazonmp3.smartplaylist.header".equals(playlistType) || "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist.header".equals(playlistType) || "vnd.android.cursor.item/vnd.amazonmp3.primeplaylist.header".equals(playlistType)) ? 1 : 0;
    }

    private String getPlaylistType(int position) {
        Cursor cursor = getCursor();
        if (cursor == null || cursor.isClosed() || cursor.getCount() <= 0 || cursor.getCount() <= position) {
            return null;
        }
        int position2 = cursor.getPosition();
        if (position == position2) {
            return cursor.getString(this.mTypeIndex);
        }
        cursor.moveToPosition(position);
        String string = cursor.getString(this.mTypeIndex);
        cursor.moveToPosition(position2);
        return string;
    }

    private void bindHeaderView(View view, Context context) {
        String string;
        RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
        if ("vnd.android.cursor.item/vnd.amazonmp3.smartplaylist.header".equals(rowViewHolder.mPlaylistType)) {
            string = context.getString(R.string.dmusic_library_smart_playlists_header);
        } else if ("vnd.android.cursor.item/vnd.amazonmp3.udoplaylist.header".equals(rowViewHolder.mPlaylistType)) {
            string = context.getString(R.string.dmusic_library_udo_playlists_listview_header);
        } else {
            string = "vnd.android.cursor.item/vnd.amazonmp3.primeplaylist.header".equals(rowViewHolder.mPlaylistType) ? context.getString(R.string.dmusic_library_prime_playlists_listview_header) : null;
        }
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            rowViewHolder.mName.setText(this.mStyler.styleText(string));
        } else {
            rowViewHolder.mName.setText(string);
        }
    }

    @Override // android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int itemViewType = getItemViewType(cursor.getPosition());
        if (itemViewType == 0) {
            return createPlaylistView(context);
        }
        if (itemViewType != 1) {
            return null;
        }
        return createHeaderView(parent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View createPlaylistView(Context context) {
        View viewInflate = View.inflate(context, R.layout.library_playlist_row, null);
        RowViewHolder rowViewHolder = new RowViewHolder();
        rowViewHolder.mViewType = 0;
        rowViewHolder.mDownloadProgressView = (IDownloadProgressView) viewInflate;
        rowViewHolder.mPrimeMarkableView = (IPrimeMarkableView) viewInflate;
        rowViewHolder.mName = (TextView) viewInflate.findViewById(R.id.PlaylistName);
        rowViewHolder.mHeader = (TextView) viewInflate.findViewById(R.id.SectionHeader);
        rowViewHolder.mTrackCount = (TextView) viewInflate.findViewById(R.id.TrackCount);
        rowViewHolder.mAddAllButton = viewInflate.findViewById(R.id.AddAllButton);
        rowViewHolder.mDimAlbumArtOverlay = (ImageView) viewInflate.findViewById(R.id.DimAlbumArtOverlay);
        rowViewHolder.mPlaystateIcon = (ImageView) viewInflate.findViewById(R.id.PlaystateIcon);
        KeyEvent.Callback callbackFindViewById = viewInflate.findViewById(R.id.cover);
        if (callbackFindViewById instanceof IBadgeableView) {
            rowViewHolder.mBadgeableCoverView = (IBadgeableView) callbackFindViewById;
        }
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
        rowViewHolder.mLibraryItem = new Playlist(AmazonApplication.getLibraryItemFactory(), null);
        View viewFindViewById = viewInflate.findViewById(R.id.overflow_button_open);
        rowViewHolder.mOverflowButtonView = viewFindViewById;
        viewFindViewById.setOnClickListener(this.mOverflowMenuClickListener);
        viewInflate.setTag(rowViewHolder);
        return viewInflate;
    }

    private View createHeaderView(ViewGroup parent) {
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.library_playlist_row_header, parent, false);
        RowViewHolder rowViewHolder = new RowViewHolder();
        rowViewHolder.mViewType = 1;
        rowViewHolder.mName = (TextView) viewInflate.findViewById(R.id.PlaylistName);
        viewInflate.setTag(rowViewHolder);
        return viewInflate;
    }

    public void notifyImageCached(String source, int size) {
        if (source != null && source.equals(this.mSource) && size == getArtSize()) {
            notifyDataSetChanged();
        }
    }

    private long getIdForRow(int position) {
        Cursor cursor = getCursor();
        int position2 = getCursor().getPosition();
        cursor.moveToPosition(position);
        long j = cursor.getLong(this.mIdIndex);
        cursor.moveToPosition(position2);
        return j;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected int getImageIdColumn() {
        return this.mIdIndex;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected ImageLoaderFactory.ItemType getImageType() {
        return ImageLoaderFactory.ItemType.PLAYLIST_UDO;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected String getSource() {
        return this.mSource;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected boolean isTrackBeingPlayed(final BadgeableListAdapter.BaseRowViewHolder holder) {
        return MediaItemHelper.isCurrentlyPlayingCollection(((RowViewHolder) holder).mUri);
    }

    private int calculateArtworkSize(Activity activity) {
        Resources resources = activity.getResources();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int integer = resources.getInteger(R.integer.grid_tile_num_columns);
        return (displayMetrics.widthPixels - (((integer - 1) * resources.getDimensionPixelSize(R.dimen.album_tab_inside_padding)) + (resources.getDimensionPixelSize(R.dimen.album_tab_outside_padding) * 2))) / integer;
    }
}
