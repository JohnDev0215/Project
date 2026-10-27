package com.amazon.mp3.library.provider.source.nowplaying;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingTrack;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.service.PlaybackService;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.widget.PlayerWidgetController;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.playback.event.CollectionLoadEndEvent;
import com.amazon.music.playback.event.CollectionLoadStartEvent;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class NowPlayingCollectionHandler implements TrackProviderListener {
    private static final int[] DEFAULT_REPEAT_MODES = {0, 2, 1};
    private static final String TAG = "NowPlayingCollectionHandler";
    private MusicTrack lastTrackCheckedForAvailability;
    private final Context mApplicationContext;
    private String mCollectionName;
    private int mCurrentPosition;
    private boolean mLoading;
    private PlaybackMetricInformation mMetricInformation;
    private NowPlayingQueueHelper mNowPlayingQueueHelper;
    private TrackProvider mProvider;
    private int mRepeatMode;
    private boolean mSelectInitialTrackRandomly;
    private boolean mShuffle;
    private MusicTrack mTrack;
    private TrackOrder mTrackOrder;
    private Uri mUri;
    private CollectionSource mCollectionSource = CollectionSource.LIBRARY;
    private int[] mAvailableRepeatModes = DEFAULT_REPEAT_MODES;
    private AtomicInteger mRequestId = new AtomicInteger();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final CopyOnWriteArraySet<NowPlayingListener> mNowPlayingListeners = new CopyOnWriteArraySet<>();

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadCollection$1(Void r0) {
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.provider.source.nowplaying.NowPlayingCollectionHandler$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$provider$source$nowplaying$NowPlayingCollectionHandler$CollectionSource;

        static {
            int[] iArr = new int[CollectionSource.values().length];
            $SwitchMap$com$amazon$mp3$library$provider$source$nowplaying$NowPlayingCollectionHandler$CollectionSource = iArr;
            try {
                iArr[CollectionSource.STORE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public enum CollectionSource {
        STORE,
        LIBRARY;

        public ResourceType toResourceType() {
            if (AnonymousClass8.$SwitchMap$com$amazon$mp3$library$provider$source$nowplaying$NowPlayingCollectionHandler$CollectionSource[ordinal()] == 1) {
                return ResourceType.STORE;
            }
            return ResourceType.CLOUD_PLAYER;
        }
    }

    public NowPlayingCollectionHandler(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mApplicationContext = applicationContext;
        this.mNowPlayingQueueHelper = new NowPlayingQueueHelper(applicationContext);
    }

    public void registerListener(NowPlayingListener listener) {
        this.mNowPlayingListeners.add(listener);
    }

    public void loadCollection(final TrackProvider provider, final int position, final boolean startPlayback, final boolean shuffleAllTracks, final PlaybackMetricInformation metricInformation, final boolean restoreFromPlayQueue, final boolean restoreFromCasting, final boolean refreshCollection) {
        final String str;
        final String[] strArr;
        final String sortOrder;
        if ((position < 0 && !shuffleAllTracks) || provider == null || provider.getUri() == null) {
            Log.warning(TAG, "Quick Exit: Position required if not shuffling, or provider is null, or collection Uri is not available");
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        Uri uri = provider.getUri();
        String str2 = TAG;
        Object[] objArr = new Object[2];
        objArr[0] = Long.valueOf(jCurrentTimeMillis);
        objArr[1] = uri != null ? uri.toString() : uri;
        Log.verbose(str2, "PlaybackMetricsEventBus: Collection started loading and Posting start = %d, mUri = %s", objArr);
        EventBus.getDefault().post(new CollectionLoadStartEvent(jCurrentTimeMillis, uri));
        PlaybackMetricInformation playbackMetricInformation = this.mMetricInformation;
        if (metricInformation.getPageType() == null) {
            if (playbackMetricInformation != null) {
                metricInformation.setPageType(playbackMetricInformation.getPageType());
            } else {
                metricInformation.setPageType(PlaybackPageType.UNKNOWN);
            }
        }
        boolean z = !restoreFromPlayQueue;
        if (uri != null && !hasContentChanged(provider, position)) {
            if (startPlayback) {
                broadcastMutation(16, position, metricInformation, z);
                return;
            }
            return;
        }
        this.mSelectInitialTrackRandomly = position < 0 && shuffleAllTracks;
        if (MediaProvider.Casting.isCastingCollection(provider.getUri())) {
            clear(false, false);
        } else {
            clear(startPlayback, true);
        }
        this.mMetricInformation = metricInformation;
        this.mCurrentPosition = -2;
        this.mProvider = provider;
        setLoading(true);
        setShuffle(shuffleAllTracks);
        this.mProvider.registerListener(this);
        setCollectionName(this.mProvider.getCollectionName());
        setCollectionSource(CollectionSource.LIBRARY);
        if (restoreFromPlayQueue || !NowPlayingQueueUtil.getInstance().isUriSupported(uri)) {
            loadCollectionProvider(provider, position, shuffleAllTracks, restoreFromCasting, refreshCollection);
            return;
        }
        final Uri contentUri = MediaProvider.PrimeBrowseTracksCollection.isCollection(uri) ? this.mTrack.getContentUri() : uri;
        if (provider instanceof LibraryTrackProvider) {
            LibraryTrackProvider libraryTrackProvider = (LibraryTrackProvider) provider;
            String selection = libraryTrackProvider.getSelection();
            String[] selectionArgs = libraryTrackProvider.getSelectionArgs();
            sortOrder = libraryTrackProvider.getSortOrder();
            strArr = selectionArgs;
            str = selection;
        } else {
            str = null;
            strArr = null;
            sortOrder = null;
        }
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.provider.source.nowplaying.NowPlayingCollectionHandler$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$loadCollection$0(contentUri, str, strArr, sortOrder, position, provider, shuffleAllTracks, restoreFromCasting, refreshCollection, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.library.provider.source.nowplaying.NowPlayingCollectionHandler$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NowPlayingCollectionHandler.lambda$loadCollection$1((Void) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.library.provider.source.nowplaying.NowPlayingCollectionHandler$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NowPlayingCollectionHandler.lambda$loadCollection$2((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCollection$0(final Uri uri, final String str, final String[] strArr, final String str2, final int i, final TrackProvider trackProvider, final boolean z, final boolean z2, final boolean z3, Subscriber subscriber) {
        NowPlayingQueueUtil.getInstance().getRxPlayQueue().replace(NowPlayingQueueUtil.getInstance().getCursorFromUri(uri, str, strArr, str2), uri, i, getShuffle()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).doOnNext(new Action1<Void>() { // from class: com.amazon.mp3.library.provider.source.nowplaying.NowPlayingCollectionHandler.1
            @Override // rx.functions.Action1
            public void call(Void aVoid) {
                NowPlayingCollectionHandler.this.loadCollectionProvider(trackProvider, NowPlayingCollectionHandler.this.getPositionForCollection(NowPlayingQueueUtil.getInstance().getCursorFromUri(uri, str, strArr, str2), uri, i), z, z2, z3);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new NowPlayingQueueObserver());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadCollection$2(Throwable th) {
        Log.error(TAG, "Received error while loading collection", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadCollectionProvider(final TrackProvider provider, final int position, final boolean shuffleAllTracks, final boolean restoreFromCasting, final boolean refreshCollection) {
        provider.loadCollection(new LoadCookie(this.mRequestId.incrementAndGet(), position, position, this.mMetricInformation, shuffleAllTracks, restoreFromCasting, refreshCollection));
        provider.loadCollectionName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPositionForCollection(final Cursor tracksCursor, final Uri tracksUri, final int position) {
        int nextAvailablePosition = 0;
        try {
            if (!getShuffle() && !this.mNowPlayingQueueHelper.isUnlimitedCollection(tracksUri)) {
                nextAvailablePosition = getNextAvailablePosition(tracksCursor, position);
            }
        } catch (Exception e) {
            Log.error(TAG, "Unable to getNextAvailablePosition ", e);
        }
        if (tracksCursor != null) {
            tracksCursor.close();
        }
        return nextAvailablePosition;
    }

    private int getNextAvailablePosition(final Cursor cursor, final int trackPosition) {
        if (CastingUtil.isCasting()) {
            return trackPosition;
        }
        int position = cursor.getPosition();
        int position2 = 0;
        cursor.moveToPosition(trackPosition);
        while (true) {
            if (cursor.isAfterLast()) {
                break;
            }
            MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(cursor);
            ContentUnavailableReason playCatalogContentUnavailableReason = ContentAccessUtil.getPlayCatalogContentUnavailableReason(track);
            this.lastTrackCheckedForAvailability = track;
            if (playCatalogContentUnavailableReason != null) {
                cursor.moveToNext();
            } else {
                position2 = cursor.getPosition();
                break;
            }
        }
        cursor.moveToPosition(position);
        return position2;
    }

    public void setCollectionSource(CollectionSource source) {
        this.mCollectionSource = source;
    }

    public void refreshCollection() {
        if (this.mProvider != null) {
            setLoading(true);
            this.mProvider.refresh(new LoadCookie(this.mRequestId.incrementAndGet(), getPlaybackPosition(), getCollectionTrackPosition(), this.mMetricInformation, getShuffle(), false, true));
        }
    }

    public synchronized Uri getUri() {
        TrackProvider trackProvider = this.mProvider;
        if (trackProvider == null) {
            return null;
        }
        return trackProvider.getUri();
    }

    public synchronized String getCollectionName() {
        return this.mCollectionName;
    }

    public synchronized void setShuffle(boolean shuffle) {
        TrackProvider trackProvider = this.mProvider;
        if ((trackProvider == null || trackProvider.supportsShuffle()) && this.mShuffle != shuffle) {
            this.mShuffle = shuffle;
            SettingsUtil.setBooleanPref(R.string.setting_key_playback_service_shuffle, shuffle);
            if (this.mProvider == null) {
                return;
            }
            initTrackOrder();
            if (getShuffle()) {
                NowPlayingQueueUtil.getInstance().getRxPlayQueue().shuffle(getCollectionTrackPosition()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new NowPlayingQueueObserver<Void>() { // from class: com.amazon.mp3.library.provider.source.nowplaying.NowPlayingCollectionHandler.2
                    @Override // com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueObserver, rx.Observer
                    public void onNext(Void aVoid) {
                        NowPlayingCollectionHandler.this.refreshCollection();
                    }
                });
            } else {
                onCollectionChanged();
            }
            Iterator<NowPlayingListener> it = this.mNowPlayingListeners.iterator();
            while (it.hasNext()) {
                it.next().onShuffleSet(this.mShuffle, this.mTrack);
            }
        }
    }

    public synchronized boolean getShuffle() {
        boolean z;
        TrackProvider trackProvider = this.mProvider;
        z = false;
        if (trackProvider == null || trackProvider.supportsShuffle()) {
            if (this.mShuffle) {
                z = true;
            }
        }
        return z;
    }

    public synchronized void setRepeatMode(int mode) {
        TrackProvider trackProvider = this.mProvider;
        if (trackProvider == null || trackProvider.supportsRepeatMode()) {
            this.mRepeatMode = mode;
            Iterator<NowPlayingListener> it = this.mNowPlayingListeners.iterator();
            while (it.hasNext()) {
                it.next().onRepeatModeChanged(this.mRepeatMode, getTrack());
            }
            persistRepeatMode();
        }
    }

    private void persistRepeatMode() {
        int repeatMode = getRepeatMode();
        if (repeatMode != 1) {
            SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(this.mApplicationContext).edit();
            editorEdit.putInt(this.mApplicationContext.getString(R.string.setting_key_playback_service_repeat_mode), repeatMode);
            editorEdit.apply();
        }
        if (this.mTrackOrder != null) {
            this.mTrackOrder.setRepeat(isRepeatMode());
        }
    }

    public synchronized int getRepeatMode() {
        TrackProvider trackProvider;
        trackProvider = this.mProvider;
        return trackProvider == null || trackProvider.supportsRepeatMode() ? this.mRepeatMode : 0;
    }

    public synchronized MusicTrack getTrack() {
        return this.mTrack;
    }

    public synchronized int getTrackCount() {
        TrackProvider trackProvider = this.mProvider;
        if (trackProvider != null && !trackProvider.isLoading()) {
            return this.mProvider.getCount();
        }
        return 0;
    }

    public synchronized int getPlaybackPosition() {
        TrackOrder trackOrder = this.mTrackOrder;
        if (trackOrder == null) {
            return -1;
        }
        return trackOrder.getCurrentPlaybackPosition();
    }

    public synchronized int getCollectionTrackPosition() {
        TrackOrder trackOrder = this.mTrackOrder;
        if (trackOrder == null) {
            return this.mCurrentPosition;
        }
        return trackOrder.getCurrentCollectionPosition();
    }

    private void broadcastTrackChanged(int position) {
        Iterator<NowPlayingListener> it = this.mNowPlayingListeners.iterator();
        while (it.hasNext()) {
            it.next().onTrackChanged(position, this.mTrack);
        }
        PlayerWidgetController.getInstance().updateWidget();
    }

    public synchronized void moveToTrackNumber(int position) {
        this.mCurrentPosition = position;
        TrackOrder trackOrder = this.mTrackOrder;
        if (trackOrder != null) {
            if (trackOrder.getMax() <= position) {
                this.mCurrentPosition = 0;
            }
            this.mTrackOrder.setCurrentPosition(this.mCurrentPosition);
        }
        updateTrack();
    }

    public synchronized boolean isLoading() {
        return this.mLoading;
    }

    private synchronized void clear(final boolean startPlayback, final boolean clearPlaybackState) {
        this.mMetricInformation = null;
        TrackProvider trackProvider = this.mProvider;
        if (trackProvider != null) {
            trackProvider.cancel();
            this.mProvider.deregisterListener(this);
            this.mProvider.close();
            this.mProvider = null;
        }
        this.mTrack = null;
        TrackOrder trackOrder = this.mTrackOrder;
        if (trackOrder != null) {
            trackOrder.reset();
            this.mTrackOrder = null;
        }
        this.mCollectionName = null;
        if (!startPlayback) {
            broadcastMutation(64, -1, null, false);
        }
        if (clearPlaybackState) {
            clearPlaybackState();
        }
    }

    public synchronized void clear(final boolean clearPlayState) {
        clear(false, clearPlayState);
    }

    private synchronized void clearPlaybackState() {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(this.mApplicationContext).edit();
        editorEdit.remove(this.mApplicationContext.getString(R.string.setting_key_playback_service_shuffle));
        editorEdit.remove(this.mApplicationContext.getString(R.string.setting_key_playback_service_repeat_mode));
        editorEdit.remove(this.mApplicationContext.getString(R.string.setting_key_now_playing_state));
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setLoading(boolean loading) {
        this.mLoading = loading;
    }

    private synchronized void updateTrack() {
        int i;
        TrackProvider trackProvider = this.mProvider;
        if (trackProvider != null && !trackProvider.isLoading() && (i = this.mCurrentPosition) != -2 && i != -1) {
            MusicTrack track = this.mProvider.getTrack(i);
            if (track == null) {
                return;
            }
            this.mTrack = track;
            this.lastTrackCheckedForAvailability = track;
            if (track instanceof CastingTrack) {
                saveCastingTrackInfo((CastingTrack) track);
            }
            Log.info(TAG, "updateTrack: Track LUID = %s", this.mTrack.getLuid());
            broadcastTrackChanged(this.mCurrentPosition);
            AsyncTask.execute(new Runnable() { // from class: com.amazon.mp3.library.provider.source.nowplaying.NowPlayingCollectionHandler.3
                @Override // java.lang.Runnable
                public void run() {
                    NowPlayingCollectionHandler.this.save();
                }
            });
            return;
        }
        this.mTrack = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void initTrackOrder() {
        if (!isLoading()) {
            TrackOrder trackOrderCreateTrackOrder = createTrackOrder(isRepeatMode());
            TrackOrder trackOrder = this.mTrackOrder;
            if (trackOrder != null) {
                trackOrder.reset();
            }
            this.mTrackOrder = trackOrderCreateTrackOrder;
        }
    }

    private boolean isRepeatMode() {
        TrackProvider trackProvider = this.mProvider;
        return (trackProvider == null || trackProvider.supportsRepeatMode()) && this.mRepeatMode != 0;
    }

    private TrackOrder createTrackOrder(boolean repeat) {
        return new SequentialTrackOrder(getCollectionTrackPosition(), getTrackCount(), repeat);
    }

    private synchronized void setCollectionName(String collectionName) {
        this.mCollectionName = collectionName;
    }

    private boolean hasContentChanged(TrackProvider provider, int position) {
        boolean z = true;
        if (getTrack() == null) {
            Log.debug(TAG, "NO CURRENT TRACK");
            return true;
        }
        Uri uri = this.mUri;
        boolean z2 = (uri != null && uri.equals(provider.getUri()) && position == getCollectionTrackPosition()) ? false : true;
        if (provider instanceof StationTrackProvider) {
            return z2;
        }
        if (!z2 && provider.getTrack(position) != null && provider.getTrack(position).getMatchHash() == getTrack().getMatchHash()) {
            z = false;
        }
        return z;
    }

    private void broadcastMutation(final int flags, final int trackPosition, final PlaybackMetricInformation metricInformation, final boolean showNotification) {
        Intent intent = new Intent(this.mApplicationContext, (Class<?>) PlaybackService.class);
        intent.setAction("com.amazon.mp3.library.provider.NowPlaying.ACTION_NOW_PLAYING_MUTATED");
        intent.putExtra("com.amazon.mp3.library.provider.NowPlaying.EXTRA_MUTATION_FLAGS", flags);
        intent.putExtra("com.amazon.mp3.library.provider.NowPlaying.EXTRA_SHOW_NOTIFICATION", showNotification);
        if (trackPosition != -1) {
            intent.putExtra("com.amazon.mp3.library.provider.NowPlaying.EXTRA_CURRENT_TRACK_ID", trackPosition);
        }
        if (metricInformation != null) {
            intent.putExtra("com.amazon.mp3.library.provider.NowPlaying.EXTRA_PLAYBACK_METRIC_BUNDLE", metricInformation.writeToBundle());
        }
        ServiceUtil.startService(this.mApplicationContext, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastRefresh(int flags, int trackPosition) {
        Intent intent = new Intent("com.amazon.mp3.library.provider.NowPlaying.ACTION_NOW_PLAYING_REFRESH");
        intent.putExtra("com.amazon.mp3.library.provider.NowPlaying.EXTRA_MUTATION_FLAGS", flags);
        if (trackPosition != -1) {
            intent.putExtra("com.amazon.mp3.library.provider.NowPlaying.EXTRA_CURRENT_TRACK_ID", trackPosition);
        }
        if (CastingUtil.isCastingToAlexa()) {
            intent.putExtra("com.amazon.mp3.library.provider.NowPlaying.EXTRA_REFRESH_METADATA", true);
        }
        this.mApplicationContext.sendOrderedBroadcast(intent, null);
    }

    private synchronized boolean hasTrackOrder() {
        return this.mTrackOrder != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int getTrackOrderCurrentCursorPosition() {
        if (!hasTrackOrder()) {
            return -1;
        }
        return this.mTrackOrder.getCurrentCollectionPosition();
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
    public void onCollectionLoaded(final LoadCookie cookie) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.provider.source.nowplaying.NowPlayingCollectionHandler.4
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingCollectionHandler nowPlayingCollectionHandler = NowPlayingCollectionHandler.this;
                nowPlayingCollectionHandler.mUri = nowPlayingCollectionHandler.getUri();
                if (NowPlayingCollectionHandler.this.mUri == null) {
                    Log.error(NowPlayingCollectionHandler.TAG, "Collection Loaded but uri is null");
                    return;
                }
                Log.verbose(NowPlayingCollectionHandler.TAG, "PlaybackMetricsEventBus: Collection Loaded and Posting end = %d, mUri = %s", Long.valueOf(jCurrentTimeMillis), NowPlayingCollectionHandler.this.mUri.toString());
                EventBus.getDefault().post(new CollectionLoadEndEvent(jCurrentTimeMillis, NowPlayingCollectionHandler.this.mUri));
                NowPlayingCollectionHandler.this.setLoading(false);
                if (!cookie.shouldRestoreFromCasting()) {
                    if (NowPlayingCollectionHandler.this.mSelectInitialTrackRandomly) {
                        NowPlayingCollectionHandler.this.initTrackOrder();
                        NowPlayingCollectionHandler nowPlayingCollectionHandler2 = NowPlayingCollectionHandler.this;
                        nowPlayingCollectionHandler2.moveToTrackNumber(nowPlayingCollectionHandler2.getTrackOrderCurrentCursorPosition());
                    } else {
                        NowPlayingCollectionHandler.this.moveToTrackNumber(cookie.getCollectionPosition());
                        NowPlayingCollectionHandler.this.initTrackOrder();
                    }
                } else {
                    NowPlayingCollectionHandler nowPlayingCollectionHandler3 = NowPlayingCollectionHandler.this;
                    nowPlayingCollectionHandler3.moveToTrackNumber(nowPlayingCollectionHandler3.getPositionFromCastingTrackInfo());
                    NowPlayingCollectionHandler.this.initTrackOrder();
                    NowPlayingCollectionHandler.this.clearCastingTrackInfo();
                }
                if (cookie.shouldRefreshCollection()) {
                    NowPlayingCollectionHandler nowPlayingCollectionHandler4 = NowPlayingCollectionHandler.this;
                    nowPlayingCollectionHandler4.broadcastRefresh(16, nowPlayingCollectionHandler4.getPlaybackPosition());
                }
                Iterator it = NowPlayingCollectionHandler.this.mNowPlayingListeners.iterator();
                while (it.hasNext()) {
                    ((NowPlayingListener) it.next()).onCollectionLoaded(NowPlayingCollectionHandler.this.mTrack);
                }
            }
        });
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
    public void onCollectionNameLoaded(String collectionName) {
        setCollectionName(collectionName);
        Iterator<NowPlayingListener> it = this.mNowPlayingListeners.iterator();
        while (it.hasNext()) {
            it.next().onCollectionNameLoaded(this.mCollectionName);
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
    public void onCollectionChanged() {
        this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.library.provider.source.nowplaying.NowPlayingCollectionHandler.6
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingCollectionHandler.this.refreshCollection();
            }
        }, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
    public void onCollectionRefreshed(boolean hasChanged) {
        if (!hasChanged) {
            setLoading(false);
            return;
        }
        TrackOrder trackOrder = this.mTrackOrder;
        if (trackOrder != null) {
            trackOrder.setMax(this.mProvider.getCount());
        } else {
            Log.error(TAG, "mTrackOrder is null in onCollectionRefreshed(), not able to set max()");
        }
        moveToTrackNumber(getTrackOrderCurrentCursorPosition());
        setLoading(false);
        synchronized (this) {
            broadcastRefresh(16, getTrackOrderCurrentCursorPosition());
        }
        Iterator<NowPlayingListener> it = this.mNowPlayingListeners.iterator();
        while (it.hasNext()) {
            it.next().onMetaDataChanged(getTrack());
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
    public void onCountChanged(int newCount) {
        TrackOrder trackOrder = this.mTrackOrder;
        if (trackOrder != null) {
            trackOrder.setMax(newCount);
        }
        Iterator<NowPlayingListener> it = this.mNowPlayingListeners.iterator();
        while (it.hasNext()) {
            it.next().onCollectionCountChanged(newCount);
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProviderListener
    public void onPositionChanged(int newPosition) {
        TrackOrder trackOrder = this.mTrackOrder;
        if (trackOrder == null) {
            return;
        }
        trackOrder.onPositionChanged(newPosition);
    }

    public void save() {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(this.mApplicationContext).edit();
        TrackProvider trackProvider = this.mProvider;
        if (trackProvider != null) {
            if (!MediaProvider.Casting.isCastingCollection(trackProvider.getUri())) {
                JSONObject jSONObject = new JSONObject();
                save(jSONObject);
                editorEdit.putString(this.mApplicationContext.getString(R.string.setting_key_now_playing_state), jSONObject.toString());
            }
        } else {
            Log.debug(TAG, "Save called when provider is null, removing now playing state");
            editorEdit.remove(this.mApplicationContext.getString(R.string.setting_key_now_playing_state));
        }
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int getPositionFromCastingTrackInfo() {
        if (this.mProvider == null) {
            Log.error(TAG, "Cannot get saved casting track position since provider is null");
            return 0;
        }
        SharedPreferences prefs = SettingsUtil.getPrefs(this.mApplicationContext);
        String string = prefs.getString(this.mApplicationContext.getString(R.string.setting_key_last_casting_track_asin), null);
        String string2 = prefs.getString(this.mApplicationContext.getString(R.string.setting_key_last_casting_track_luid), null);
        if (!TextUtils.isEmpty(string)) {
            for (int i = 0; i < this.mProvider.getCount(); i++) {
                if (string.equals(this.mProvider.getTrack(i).getAsin())) {
                    return i;
                }
            }
        } else if (!TextUtils.isEmpty(string2)) {
            for (int i2 = 0; i2 < this.mProvider.getCount(); i2++) {
                if (string2.equals(this.mProvider.getTrack(i2).getLuid())) {
                    return i2;
                }
            }
        }
        return 0;
    }

    private synchronized void saveCastingTrackInfo(final CastingTrack track) {
        if (track == null) {
            Log.error(TAG, "Cannot save casting track since it's null");
            return;
        }
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(this.mApplicationContext).edit();
        String asin = track.getAsin();
        String luid = track.getLuid();
        if (!TextUtils.isEmpty(asin)) {
            editorEdit.putString(this.mApplicationContext.getString(R.string.setting_key_last_casting_track_asin), track.getAsin());
        } else if (!TextUtils.isEmpty(luid)) {
            editorEdit.putString(this.mApplicationContext.getString(R.string.setting_key_last_casting_track_luid), track.getAsin());
        }
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void clearCastingTrackInfo() {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(this.mApplicationContext).edit();
        editorEdit.remove(this.mApplicationContext.getString(R.string.setting_key_last_casting_track_asin));
        editorEdit.remove(this.mApplicationContext.getString(R.string.setting_key_last_casting_track_luid));
        editorEdit.apply();
    }

    private JSONObject saveCollectionState(JSONObject outSaveState) throws JSONException {
        Uri uri = this.mUri;
        if (uri != null && MediaProvider.Tracks.isTrack(uri)) {
            outSaveState.put("position", 0);
        } else {
            outSaveState.put("position", getCollectionTrackPosition());
        }
        outSaveState.put("repeat", getRepeatMode());
        outSaveState.put("shuffle", getShuffle());
        MusicTrack musicTrack = this.mTrack;
        outSaveState.put("track_luid", musicTrack == null ? null : musicTrack.getLuid());
        String name = this.mProvider.getClass().getName();
        if (this.mProvider instanceof AlexaTrackProvider) {
            name = LibraryTrackProvider.class.getName();
        }
        outSaveState.put("provider_class", name);
        return outSaveState;
    }

    public void save(JSONObject outSaveState) {
        save(outSaveState, this.mMetricInformation);
    }

    private synchronized void save(JSONObject outSaveState, PlaybackMetricInformation metricInformation) {
        if (this.mProvider != null) {
            try {
                saveCollectionState(outSaveState);
                JSONObject jSONObject = new JSONObject();
                this.mProvider.populateState(jSONObject);
                outSaveState.put("provider", jSONObject);
                if (metricInformation == null) {
                    Log.error(TAG, "Attempted to save playing state with null metric information.  Proceeding with unknown data.");
                    metricInformation = new PlaybackMetricInformation(DirectedPlayStatus.UNKNOWN, PlaybackPageType.UNKNOWN);
                }
                metricInformation.writeToJSONObject(outSaveState);
            } catch (JSONException e) {
                Log.warning(TAG, "Unable to save Now Playing State", e);
            }
        } else {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Attempted to save playing state with a null provider, while metric information was: ");
            sb.append(metricInformation == null ? "null" : "not null");
            Log.error(str, sb.toString());
        }
    }

    public boolean restore(final JSONObject blob, final boolean startPlayback, final boolean restoreFromPlayQueue, final boolean restoreFromCasting) {
        try {
            setRepeatMode(blob.getInt("repeat"));
            final boolean z = blob.getBoolean("shuffle");
            setShuffle(z);
            final Class<?> cls = Class.forName(blob.getString("provider_class"));
            final JSONObject jSONObject = blob.getJSONObject("provider");
            final int i = StationTrackProvider.class.isAssignableFrom(cls) ? 0 : blob.getInt("position");
            final PlaybackMetricInformation playbackMetricInformationCreateFromJSONObject = PlaybackMetricInformation.createFromJSONObject(blob);
            if (startPlayback) {
                Intent intent = new Intent("com.amazon.mp3.playerservicecommand.stop");
                intent.putExtra("com.amazon.mp3.playstate_mutation_reason", PlayStateMutationReason.NEW_COLLECTION);
                this.mApplicationContext.sendBroadcast(intent);
            }
            this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.provider.source.nowplaying.NowPlayingCollectionHandler.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        TrackProvider trackProvider = (TrackProvider) cls.newInstance();
                        trackProvider.restore(NowPlayingCollectionHandler.this.mApplicationContext, jSONObject);
                        Uri uri = trackProvider.getUri();
                        if (DefaultUriMatcher.match(uri) == 36 || DefaultUriMatcher.match(uri) == 38 || DefaultUriMatcher.match(uri) == 2) {
                            NowPlayingCollectionHandler.this.loadCollection(trackProvider, i, startPlayback, z, playbackMetricInformationCreateFromJSONObject, false, restoreFromCasting, true);
                        } else {
                            NowPlayingCollectionHandler.this.loadCollection(trackProvider, i, startPlayback, z, playbackMetricInformationCreateFromJSONObject, restoreFromPlayQueue, restoreFromCasting, true);
                        }
                    } catch (SQLiteException e) {
                        Log.error(NowPlayingCollectionHandler.TAG, "SQL query failed when attempting to restore state with msg: ", e);
                    } catch (IllegalAccessException e2) {
                        Log.error(NowPlayingCollectionHandler.TAG, "Provider cannot instantiate", e2);
                    } catch (InstantiationException e3) {
                        Log.error(NowPlayingCollectionHandler.TAG, "Provider cannot instantiate", e3);
                    } catch (JSONException e4) {
                        Log.warning(NowPlayingCollectionHandler.TAG, "Unable to restore Now Playing as the provider format changed", e4);
                    }
                }
            });
            return true;
        } catch (ClassNotFoundException e) {
            Log.info(TAG, "Unable to restore Now Playing as the provider no longer exists", e);
            return false;
        } catch (JSONException e2) {
            Log.info(TAG, "Unable to restore Now Playing as the format changed", e2);
            return false;
        }
    }
}
