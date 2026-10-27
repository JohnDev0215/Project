package com.amazon.mp3.library.adapter;

import android.content.Context;
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
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.DefaultStringType;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.BadgeView;
import com.amazon.mp3.view.IBadgeableView;
import com.amazon.mp3.view.IContentDisabledMarkableView;
import com.amazon.mp3.view.IDownloadProgressView;
import com.amazon.mp3.view.IPrimeMarkableView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumListAdapter extends BadgeableListAdapter implements IArtCache.IArtLoadedListener {
    private static final String TAG = AlbumListAdapter.class.getSimpleName();
    private View.OnClickListener mAddAllClickListener;
    private int mAlbumIdIndex;
    private final View.OnClickListener mBadgeClickListener;
    private WeakHashMap<View, Object> mCreatedViews;
    private int mDownloadStateIndex;
    private int mDownloadStatusIndex;
    private boolean mIsLocal;
    private int mLayoutId;
    private final View.OnClickListener mOverflowMenuClickListener;
    private OnPlaylistAddAllButtonClickListener mPlaylistAddAllClickListener;
    private Uri mPlaylistUri;
    private final boolean mShouldShowDownloadBadging;
    private final boolean mShouldShowPrimeBadging;
    private BauhausTextStyler mStyler;
    private HashMap<Integer, String> mTrackCountCache;
    private boolean mUseGridView;

    public interface OnPlaylistAddAllButtonClickListener {
        void onClick(long albumId);
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache.IArtLoadedListener
    public void onDrawableLoaded(Drawable d, String source, String id) {
    }

    public static class RowViewHolder extends BadgeableListAdapter.BaseRowViewHolder {
        public View mAddAllButton;
        public long mAlbumId;
        public View mBadgeContainer;
        public TextView mCreator;
        public int mDownloadGroupState;
        public IDownloadProgressView mDownloadProgressView;
        public int mDownloadState;
        public BadgeView mImmersiveBadge;
        public BadgeView mKatanaBadge;
        public View mOverflowButtonView;
        public IPrimeMarkableView mPrimeMarkableView;
        public TextView mTitle;
        public TextView mTrackCount;
        public Uri mUri;
        public boolean mUseGridView;

        @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter.BaseRowViewHolder
        public void updateProgress(int percent) {
            this.mDownloadProgressView.setProgress(percent);
            Log.debug(AlbumListAdapter.TAG, "progress: " + percent);
        }
    }

    public AlbumListAdapter(Context context, Uri contentUri, Uri playlistUri, boolean useGridView, IArtCache artCache, boolean shouldShowPrimeBadging) {
        super(context, null, false);
        this.mCreatedViews = new WeakHashMap<>();
        this.mBadgeClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.AlbumListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
                Log.debug(AlbumListAdapter.TAG, "holder state is: %d", Integer.valueOf(rowViewHolder.mDownloadState));
                int i = rowViewHolder.mDownloadState;
                if (i != 1) {
                    if (i == 2) {
                        MusicDownloader.showRetryDialog(AlbumListAdapter.this.mContext, rowViewHolder.mUri, rowViewHolder.mDownloadId);
                        return;
                    } else if (i != 3 && i != 4) {
                        return;
                    }
                }
                Log.info(AlbumListAdapter.TAG, "User cancel from library page with item " + rowViewHolder.mDownloadId + " with state " + rowViewHolder.mDownloadState);
                MusicDownloader.getInstance(AlbumListAdapter.this.mContext).cancel(rowViewHolder.mDownloadId);
                AlbumListAdapter.this.removeProgress(rowViewHolder);
            }
        };
        this.mAddAllClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.AlbumListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (UserSubscriptionUtil.isNightwingOnly()) {
                    return;
                }
                RowViewHolder rowViewHolder = (RowViewHolder) v.getTag();
                if (AlbumListAdapter.this.mPlaylistAddAllClickListener != null) {
                    AlbumListAdapter.this.mPlaylistAddAllClickListener.onClick(rowViewHolder.mAlbumId);
                }
            }
        };
        this.mOverflowMenuClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.AlbumListAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                v.performLongClick();
            }
        };
        this.mUseGridView = useGridView;
        this.mTrackCountCache = new HashMap<>();
        this.mIsLocal = CirrusMediaSource.matchLocal(contentUri);
        this.mArtCache = artCache;
        this.mPlaylistUri = playlistUri;
        this.mShouldShowDownloadBadging = playlistUri == null;
        this.mLayoutId = this.mUseGridView ? R.layout.library_album_griditem : R.layout.library_album_row;
        this.mShouldShowPrimeBadging = shouldShowPrimeBadging;
        this.mStyler = new BauhausTextStyler();
    }

    public void setContentUri(final Uri contentUri) {
        this.mIsLocal = CirrusMediaSource.matchLocal(contentUri);
    }

    public void setPlaylistAddAllButtonClickListener(OnPlaylistAddAllButtonClickListener listener) {
        this.mPlaylistAddAllClickListener = listener;
    }

    @Override // com.amazon.mp3.library.adapter.CursorListAdapter
    public void setupNewCursor(Cursor c) {
        this.mTrackCountCache.clear();
        if (c != null) {
            this.mIndexer = null;
            this.mAlbumIdIndex = c.getColumnIndexOrThrow("_id");
            this.mNameIndex = c.getColumnIndexOrThrow("sort_title");
            this.mDownloadStatusIndex = c.getColumnIndexOrThrow("download_status");
            this.mDownloadStateIndex = c.getColumnIndexOrThrow("download_state");
        }
        checkCreateAlphabetIndexer(c);
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache.IArtLoadedListener
    public void onDrawableLoaded(Drawable d, String source, long id) {
        Iterator<View> it = this.mCreatedViews.keySet().iterator();
        while (it.hasNext()) {
            RowViewHolder rowViewHolder = (RowViewHolder) it.next().getTag();
            if (rowViewHolder.mAlbumId == id) {
                rowViewHolder.mDownloadProgressView.setArtworkDrawable(d);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
        Album albumPopulateAlbum = AmazonApplication.getLibraryItemFactory().populateAlbum(cursor, (Album) rowViewHolder.mLibraryItem);
        if (albumPopulateAlbum == null) {
            Log.warning(TAG, "Album is null.  Do not bind the view.");
            return;
        }
        rowViewHolder.mAlbumId = Long.parseLong(albumPopulateAlbum.getId());
        rowViewHolder.mUseGridView = this.mUseGridView;
        String title = albumPopulateAlbum.getTitle();
        DefaultStringType defaultStringType = DefaultStringType.ALBUM;
        if (defaultStringType.isPlatformOrCirrusDefault(title)) {
            title = defaultStringType.getDefault();
        }
        if (ChildUserUtil.INSTANCE.isChildUser(context)) {
            rowViewHolder.mTitle.setText(StringUtil.removeExplicitLabelFromAlbumOrTrackTitle(title));
        } else {
            rowViewHolder.mTitle.setText(title);
        }
        String artistName = albumPopulateAlbum.getArtistName();
        DefaultStringType defaultStringType2 = DefaultStringType.ARTIST;
        if (defaultStringType2.isPlatformOrCirrusDefault(artistName)) {
            artistName = defaultStringType2.getDefault();
        }
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            rowViewHolder.mTitle.setText(this.mStyler.styleText(title));
            rowViewHolder.mCreator.setText(this.mStyler.styleText(artistName));
        } else {
            rowViewHolder.mTitle.setText(title);
            rowViewHolder.mCreator.setText(artistName);
        }
        rowViewHolder.mPrimeMarkableView.markAsPrime(this.mShouldShowPrimeBadging && albumPopulateAlbum.isPurePrime());
        rowViewHolder.mDownloadProgressView.setArtworkDrawable(this.mArtCache.getDrawable(ImageLoaderFactory.ItemType.ALBUM, (String) null, rowViewHolder.mAlbumId));
        albumPopulateAlbum.setContentUri(MediaProvider.Albums.Tracks.getContentUri(this.mIsLocal ? "cirrus-local" : "cirrus", rowViewHolder.mAlbumId));
        String string = "";
        rowViewHolder.mUri = albumPopulateAlbum.getContentUri();
        bindPlaystateIcon(context, rowViewHolder);
        if (this.mPlaylistUri == null) {
            int i = -1;
            if (this.mIsLocal) {
                rowViewHolder.mDownloadProgressView.setDownloadState(-1);
                updateBadgeContainer(rowViewHolder.mBadgeContainer, false);
            } else {
                int i2 = cursor.getInt(this.mDownloadStatusIndex);
                int i3 = this.mShouldShowDownloadBadging ? cursor.getInt(this.mDownloadStateIndex) : -1;
                rowViewHolder.mDownloadGroupState = i2;
                rowViewHolder.mDownloadState = i3;
                rowViewHolder.mDownloadId = MediaProvider.Albums.Tracks.getContentUri("cirrus", rowViewHolder.mAlbumId).toString();
                if (i3 == 4 && MusicDownloader.getInstance(this.mContext).getGroup(rowViewHolder.mDownloadId) == null) {
                    rowViewHolder.mDownloadState = -1;
                    rowViewHolder.mDownloadGroupState = -1;
                } else {
                    i = i3;
                }
                if (i == 1) {
                    updateBadgeContainer(rowViewHolder.mBadgeContainer, true);
                    string = context.getResources().getString(R.string.dmusic_download_state_dash_waiting);
                } else if (i == 2) {
                    updateBadgeContainer(rowViewHolder.mBadgeContainer, true);
                } else if (i == 3) {
                    updateBadgeContainer(rowViewHolder.mBadgeContainer, true);
                    string = context.getResources().getString(R.string.dmusic_download_state_dash_paused);
                } else if (i == 4) {
                    updateBadgeContainer(rowViewHolder.mBadgeContainer, true);
                    string = context.getResources().getString(R.string.dmusic_download_state_dash_downloading);
                    addProgress(rowViewHolder);
                } else {
                    updateBadgeContainer(rowViewHolder.mBadgeContainer, false);
                }
                bindBadgeContainer(rowViewHolder.mBadgeContainer, i);
                if (i == 0 && albumPopulateAlbum.isAllPreviouslyCatalog() && albumPopulateAlbum.isNotOwned()) {
                    rowViewHolder.mDownloadProgressView.setDownloadState(5);
                } else {
                    rowViewHolder.mDownloadProgressView.setDownloadState(i);
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
        if (!this.mUseGridView && rowViewHolder.mTrackCount != null) {
            if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
                rowViewHolder.mTrackCount.setText(this.mStyler.styleText(getPluralizedString(R.plurals.dmusic_library_songs_count, (int) albumPopulateAlbum.getTrackCount()) + ' ' + string));
            } else {
                rowViewHolder.mTrackCount.setText(getPluralizedString(R.plurals.dmusic_library_songs_count, (int) albumPopulateAlbum.getTrackCount()) + ' ' + string);
            }
        }
        if (view instanceof IContentDisabledMarkableView) {
            if (UserSubscriptionUtil.isNightwingOnly() && AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled()) {
                ((IContentDisabledMarkableView) view).setContentEnabled((AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() && albumPopulateAlbum.isExplicit()) ? false : true);
            } else {
                ((IContentDisabledMarkableView) view).setContentEnabled(albumPopulateAlbum.isAvailable());
            }
        }
        if (AmazonApplication.getCapabilities().isAlbumBadgingInLibrarySupported()) {
            rowViewHolder.mKatanaBadge.populate(ContentEncodingDecoder.getEncodingBadgeModel(albumPopulateAlbum.getContentEncodings(), this.mContext, 8));
            rowViewHolder.mImmersiveBadge.populate(ContentEncodingDecoder.getImmersiveContentBadgeModel(albumPopulateAlbum.getContentEncodings(), this.mContext, 8));
        }
    }

    private String getPluralizedString(int pluralsId, int count) {
        String str = this.mTrackCountCache.get(Integer.valueOf(count));
        if (str != null) {
            return str;
        }
        String quantityString = this.mContext.getResources().getQuantityString(pluralsId, count, Integer.valueOf(count));
        this.mTrackCountCache.put(Integer.valueOf(count), quantityString);
        return quantityString;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewInflate = View.inflate(context, this.mLayoutId, null);
        RowViewHolder rowViewHolder = new RowViewHolder();
        rowViewHolder.mDownloadProgressView = (IDownloadProgressView) viewInflate;
        rowViewHolder.mPrimeMarkableView = (IPrimeMarkableView) viewInflate;
        rowViewHolder.mTitle = (TextView) viewInflate.findViewById(R.id.AlbumItemTitle);
        rowViewHolder.mKatanaBadge = (BadgeView) viewInflate.findViewById(R.id.KatanaBadge);
        rowViewHolder.mImmersiveBadge = (BadgeView) viewInflate.findViewById(R.id.ImmersiveBadge);
        rowViewHolder.mCreator = (TextView) viewInflate.findViewById(R.id.AlbumItemCreator);
        KeyEvent.Callback callbackFindViewById = viewInflate.findViewById(R.id.cover);
        if (callbackFindViewById instanceof IBadgeableView) {
            rowViewHolder.mBadgeableCoverView = (IBadgeableView) callbackFindViewById;
        }
        rowViewHolder.mTrackCount = (TextView) viewInflate.findViewById(R.id.TrackCount);
        rowViewHolder.mDimAlbumArtOverlay = (ImageView) viewInflate.findViewById(R.id.DimAlbumArtOverlay);
        rowViewHolder.mPlaystateIcon = (ImageView) viewInflate.findViewById(R.id.PlaystateIcon);
        rowViewHolder.mBadgeContainer = viewInflate.findViewById(R.id.BadgeContainer);
        rowViewHolder.mAddAllButton = viewInflate.findViewById(R.id.AddAllButton);
        View clickableBadgeView = rowViewHolder.mDownloadProgressView.getClickableBadgeView();
        if (clickableBadgeView != null) {
            clickableBadgeView.setOnClickListener(this.mBadgeClickListener);
            clickableBadgeView.setTag(rowViewHolder);
        }
        updateBadgeContainer(rowViewHolder.mBadgeContainer, false);
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
        rowViewHolder.mLibraryItem = new Album(AmazonApplication.getLibraryItemFactory(), null);
        viewInflate.setTag(rowViewHolder);
        this.mCreatedViews.put(viewInflate, null);
        return viewInflate;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter, android.widget.CursorAdapter
    protected void onContentChanged() {
        super.onContentChanged();
        checkCreateAlphabetIndexer(getCursor());
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected int getImageIdColumn() {
        return this.mAlbumIdIndex;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected ImageLoaderFactory.ItemType getImageType() {
        return ImageLoaderFactory.ItemType.ALBUM;
    }

    @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter
    protected boolean isTrackBeingPlayed(final BadgeableListAdapter.BaseRowViewHolder holder) {
        return MediaItemHelper.isCurrentlyPlayingCollection(((RowViewHolder) holder).mUri);
    }
}
