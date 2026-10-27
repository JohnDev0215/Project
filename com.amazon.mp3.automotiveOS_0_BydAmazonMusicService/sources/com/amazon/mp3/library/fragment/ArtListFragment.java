package com.amazon.mp3.library.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.CursorAdapter;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.adapter.BadgeableListAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ArtListFragment extends CursorListFragment implements IArtCache {
    private static final String TAG = ArtListFragment.class.getSimpleName();
    protected int mAlbumArtSize;
    private InputMethodManager mImm;
    private int mLastPrefetchPosition;
    private boolean mPrefetchComplete;
    private boolean mResetCacheOnResume;
    private boolean mSavedIsPlaying;
    private MediaCollectionInfo mSavedMediaCollectionInfo;
    private MediaItem mSavedMediaItem;
    protected PlaybackController mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
    protected boolean mListScrolling = false;
    private int mFirstVisible = 0;
    private int mLastVisible = 0;
    private long mPrefetchArtJob = -1;
    private boolean mFastScrollActive = false;
    private boolean mFastScrollVisible = false;
    private int mPreviousFirstVisibleItem = Integer.MIN_VALUE;
    private Runnable mStartPrefetchRunnable = new Runnable() { // from class: com.amazon.mp3.library.fragment.ArtListFragment.1
        @Override // java.lang.Runnable
        public void run() {
            ArtListFragment.this.startPrefetch();
        }
    };
    protected Runnable mOnScrollSettledRunnable = new Runnable() { // from class: com.amazon.mp3.library.fragment.ArtListFragment.2
        @Override // java.lang.Runnable
        public void run() {
            ArtListFragment artListFragment = ArtListFragment.this;
            artListFragment.mListScrolling = false;
            if (artListFragment.albumArtCacheEnabled() && !ArtListFragment.this.isPaused()) {
                ArtListFragment.this.getAlbumArtCache().unpause();
                ArtListFragment.this.startPrefetch();
            }
            CursorAdapter adapter = ArtListFragment.this.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    };
    private BroadcastReceiver mCacheClearedReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.fragment.ArtListFragment.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (ArtListFragment.this.isPaused()) {
                ArtListFragment.this.mResetCacheOnResume = true;
            } else {
                ArtListFragment.this.checkedResetCache();
            }
        }
    };
    private BroadcastReceiver mArtLoadedReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.fragment.ArtListFragment.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ArtListFragment.this.onArtReceived(context, intent);
        }
    };
    private Runnable mDelayedFastScrollFixRunnable = new Runnable() { // from class: com.amazon.mp3.library.fragment.ArtListFragment.6
        @Override // java.lang.Runnable
        public void run() {
            AbsListView listView = ArtListFragment.this.getListView();
            if (listView == null || !ArtListFragment.this.mFastScrollVisible) {
                return;
            }
            listView.setFastScrollEnabled(false);
            listView.setFastScrollAlwaysVisible(false);
            ArtListFragment.this.mFastScrollVisible = false;
        }
    };

    protected boolean albumArtCacheEnabled() {
        return true;
    }

    protected abstract CacheManager getAlbumArtCache();

    protected abstract String getArtColumnName();

    protected abstract ImageLoaderFactory.ItemType getImageType();

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected abstract PageType getPageType();

    protected boolean shouldEmitDefaultUiPageViewMetric() {
        return true;
    }

    protected boolean shouldPrefetchArt() {
        return true;
    }

    protected boolean shouldUseFastScroll() {
        return true;
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        registerReceiver(this.mCacheClearedReceiver, new IntentFilter("com.amazon.mp3.ACTION_CACHE_CLEARED"));
        mediaCollectionUpdated();
        playStateUpdated();
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        unregisterReceiver(this.mCacheClearedReceiver);
        super.onDestroy();
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.fragment.BaseFragment
    public void onDeactivated() {
        super.onDeactivated();
        unregisterReceiver(this.mArtLoadedReceiver);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment
    public void onActivated() {
        super.onActivated();
        registerReceiver(this.mArtLoadedReceiver, new IntentFilter("com.amazon.mp3.library.cache.image.CacheManager.ACTION_ALBUM_ARTWORK_CACHED"));
        if (this.mResetCacheOnResume) {
            checkedResetCache();
            this.mResetCacheOnResume = false;
        }
        if (albumArtCacheEnabled()) {
            getAlbumArtCache().unpause();
        }
        if (shouldEmitDefaultUiPageViewMetric()) {
            sendUiPageViewMetric();
        }
        init();
    }

    public void sendUiPageViewMetric() {
        PageType pageType = getPageType();
        if (pageType != null) {
            sendUiPageViewMetric(pageType);
        }
    }

    public void sendUiPageViewMetric(final PageType pageType) {
        sendUiPageViewMetric(pageType, null, null);
    }

    public void sendUiPageViewMetric(final PageType pageType, final String itemIdentifier, final String itemIdentifierType) {
        String str;
        String string;
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string2 = arguments.getString(DeeplinkIntentKey.KEY_REF_MARKER.toString());
            string = arguments.getString(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString());
            str = string2;
        } else {
            str = null;
            string = null;
        }
        sendUiPageViewMetric(pageType, itemIdentifier, itemIdentifierType, str, string);
    }

    public void sendUiPageViewMetric(final PageType pageType, final String itemIdentifier, final String itemIdentifierType, final String refMarker, final String associateTag) {
        UiPageViewEvent uiPageViewEventBuild = UiPageViewEvent.builder(pageType.getMetricsValue()).withViewType(ScreenUtil.getScreenViewType(getContext())).withDetailPageItemIdType(itemIdentifierType).withDetailPageItemId(itemIdentifier).withAssociateTag(associateTag).withExternalReference(refMarker).build();
        if (uiPageViewEventBuild != null) {
            MetricsHolder.getManager().handleEvent(uiPageViewEventBuild);
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem == this.mPreviousFirstVisibleItem || visibleItemCount <= 0) {
            return;
        }
        super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        if (getAdapter() instanceof BadgeableListAdapter) {
            ((BadgeableListAdapter) getAdapter()).onScroll(firstVisibleItem, visibleItemCount, totalItemCount);
        }
        this.mHandler.removeCallbacks(this.mOnScrollSettledRunnable);
        this.mFirstVisible = firstVisibleItem;
        this.mHandler.postDelayed(this.mOnScrollSettledRunnable, 1000L);
        this.mPreviousFirstVisibleItem = firstVisibleItem;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        super.onScrollStateChanged(view, scrollState);
        if (scrollState != 0) {
            this.mHandler.removeCallbacks(this.mOnScrollSettledRunnable);
            if (!this.mListScrolling) {
                this.mListScrolling = true;
            }
        } else {
            this.mHandler.postDelayed(this.mOnScrollSettledRunnable, 75L);
            this.mFirstVisible = view.getFirstVisiblePosition();
            this.mLastVisible = view.getLastVisiblePosition();
        }
        if (this.mFastScrollActive) {
            if (scrollState == 0) {
                if (this.mFastScrollVisible) {
                    this.mHandler.postDelayed(this.mDelayedFastScrollFixRunnable, 200L);
                    return;
                }
                return;
            }
            this.mHandler.removeCallbacks(this.mDelayedFastScrollFixRunnable);
            view.setFastScrollEnabled(true);
            view.setFastScrollAlwaysVisible(true);
            this.mFastScrollVisible = true;
            InputMethodManager inputMethodManager = this.mImm;
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    protected int getAlbumArtSize() {
        int dimensionPixelSize;
        if (this.mAlbumArtSize == 0) {
            if (this.mUseGridView) {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.default_gridview_art_size);
            } else {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.default_listview_art_size);
            }
            this.mAlbumArtSize = dimensionPixelSize;
        }
        return this.mAlbumArtSize;
    }

    private void init() {
        setGridViewMaximumSpeed(8000);
        this.mImm = (InputMethodManager) getActivity().getSystemService("input_method");
    }

    private void setGridViewMaximumSpeed(int maxSpeed) {
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mMaximumVelocity");
            declaredField.setAccessible(true);
            declaredField.set(getListView(), Integer.valueOf(maxSpeed));
            e = null;
        } catch (IllegalAccessException e) {
            e = e;
        } catch (IllegalArgumentException e2) {
            e = e2;
        } catch (NoSuchFieldException e3) {
            e = e3;
        }
        if (e != null) {
            Log.info(TAG, "Failed to limit library fling velocity: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkedResetCache() {
        CacheManager albumArtCache = getAlbumArtCache();
        if (albumArtCache != null) {
            albumArtCache.reset();
            CursorAdapter adapter = getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    protected void onArtReceived(Context context, Intent intent) {
        boolean z;
        if (getActivity() == null || intent == null || intent.getExtras() == null || !shouldLoadArt(intent)) {
            return;
        }
        int intExtra = intent.getIntExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_TYPE", -1);
        String stringExtra = intent.getStringExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_SOURCE");
        String stringExtra2 = intent.getStringExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_ID");
        if (stringExtra2 == null) {
            return;
        }
        if (getAdapter() instanceof IArtCache.IArtLoadedListener) {
            IArtCache.IArtLoadedListener iArtLoadedListener = (IArtCache.IArtLoadedListener) getAdapter();
            if (TextUtils.isDigitsOnly(stringExtra2) || (TextUtils.lastIndexOf(stringExtra2, '-') == 0 && TextUtils.isDigitsOnly(stringExtra2.substring(1, stringExtra2.length() - 1)))) {
                long j = Long.parseLong(stringExtra2);
                Drawable drawable = getDrawable(getImageItemType(intExtra), stringExtra, j);
                if (drawable != null) {
                    iArtLoadedListener.onDrawableLoaded(drawable, stringExtra, j);
                    z = true;
                }
                z = false;
            } else {
                Drawable drawable2 = getDrawable(getImageItemType(intExtra), stringExtra, stringExtra2);
                if (drawable2 != null) {
                    iArtLoadedListener.onDrawableLoaded(drawable2, stringExtra, stringExtra2);
                    z = true;
                }
                z = false;
            }
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        Log.warning(TAG, "Can not load image type [%s] with id of [%s]", getImageType(), stringExtra2);
    }

    private ImageLoaderFactory.ItemType getImageItemType(final int value) {
        ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.getItemType(value);
        return itemType != null ? itemType : getImageType();
    }

    protected boolean shouldLoadArt(final Intent intent) {
        return getImageType().intValue() == intent.getIntExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_TYPE", -1) && getAlbumArtSize() == intent.getIntExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_SIZE", -1);
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment
    protected void onCursorChanged(Cursor newCursor, Cursor oldCursor) {
        super.onCursorChanged(newCursor, oldCursor);
        this.mPrefetchComplete = false;
        this.mLastPrefetchPosition = 0;
        if (this.mListScrolling || newCursor == null) {
            return;
        }
        this.mHandler.postDelayed(this.mStartPrefetchRunnable, 500L);
    }

    protected void startPrefetch() {
        if (getActivity() != null && shouldPrefetchArt() && this.mPrefetchArtJob == -1) {
            this.mPrefetchArtJob = addJob(new PrefetchArtJob());
        }
    }

    private class PrefetchArtJob extends Job {
        ConcurrentLinkedQueue<String> mImageQueue = new ConcurrentLinkedQueue<>();

        public PrefetchArtJob() {
            int iMax = Math.max(ArtListFragment.this.mLastVisible, ArtListFragment.this.mLastPrefetchPosition);
            if (!AmazonApplication.onMainThread()) {
                Log.error(ArtListFragment.TAG, "PrefetchArtJob not on main thread");
                throw new IllegalArgumentException();
            }
            Cursor cursor = ArtListFragment.this.getCursor();
            if (cursor == null) {
                return;
            }
            int count = cursor.getCount() - iMax;
            if (count <= 0) {
                ArtListFragment.this.mPrefetchComplete = true;
                return;
            }
            int iMin = Math.min(count, CacheManager.getMaxPrefetchCount());
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow(ArtListFragment.this.getArtColumnName());
            cursor.moveToPosition(iMax);
            ArtListFragment.this.mLastPrefetchPosition = iMax;
            while (true) {
                int i = iMin - 1;
                if (iMin <= 0) {
                    return;
                }
                String string = cursor.getString(columnIndexOrThrow);
                if (!TextUtils.isEmpty(string)) {
                    this.mImageQueue.add(string);
                }
                if (!cursor.moveToNext()) {
                    i = 0;
                }
                iMin = i;
            }
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            ArtListFragment.this.prefetchArt(this.mImageQueue);
            return 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prefetchArt(ConcurrentLinkedQueue<String> queue) {
        if (this.mPrefetchComplete) {
            return;
        }
        int albumArtSize = getAlbumArtSize();
        CacheManager cacheManager = CacheManager.getInstance();
        while (!queue.isEmpty() && !this.mListScrolling) {
            cacheManager.precache(getImageType(), null, albumArtSize, queue.poll());
            this.mLastPrefetchPosition++;
        }
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        if (jobId == this.mPrefetchArtJob) {
            this.mPrefetchArtJob = -1L;
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache
    public Drawable getDrawable(ImageLoaderFactory.ItemType itemType, String source, String id) {
        return getAlbumArtCache().get(itemType, source, getAlbumArtSize(), id);
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache
    public Drawable getDrawable(ImageLoaderFactory.ItemType itemType, String source, long id) {
        return getAlbumArtCache().get(itemType, source, getAlbumArtSize(), Long.toString(id));
    }

    protected final void startDownload(final String requestId, final Uri contentUri, final boolean downloadNonAddedPrimeTracks, final NotificationInfo notificationInfo) {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            showNetworkErrorDialog(new NetworkErrorDialog.INetworkErrorDialogHandler() { // from class: com.amazon.mp3.library.fragment.ArtListFragment.5
                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onConnectionErrorDialogCancel() {
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onDialogDismiss() {
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onRetryConnection() {
                    ArtListFragment.this.startDownload(requestId, contentUri, downloadNonAddedPrimeTracks, notificationInfo);
                }
            });
        } else if (DownloadSettingDialogFragment.shouldShowDownloadSettingsDialogFragment(getActivity())) {
            DownloadSettingDialogFragment.showSettingsDialog(getActivity(), requestId, contentUri, notificationInfo, downloadNonAddedPrimeTracks);
        } else {
            MusicDownloader.getInstance(getApplication()).download(requestId, contentUri, notificationInfo, downloadNonAddedPrimeTracks, true);
        }
    }

    protected void checkFastScrollerRequired(Cursor cursor, int upperLimit) {
        if (getListView() == null || !shouldUseFastScroll()) {
            return;
        }
        monitorScrolling(cursor != null && cursor.getCount() > upperLimit);
    }

    private void monitorScrolling(boolean monitorScroll) {
        this.mFastScrollActive = monitorScroll;
    }

    protected boolean playStateUpdated() {
        boolean zIsPlayingChanged = NowPlayingUtil.isPlayingChanged(this.mSavedMediaItem, this.mSavedIsPlaying);
        this.mSavedMediaItem = this.mPlaybackController.getCurrentMediaItem();
        this.mSavedIsPlaying = this.mPlaybackController.getPlayStatus().shouldBePlaying();
        return zIsPlayingChanged;
    }

    protected boolean mediaCollectionUpdated() {
        boolean z = this.mSavedMediaCollectionInfo != this.mPlaybackController.getMediaCollectionInfo();
        this.mSavedMediaCollectionInfo = this.mPlaybackController.getMediaCollectionInfo();
        return z;
    }
}
