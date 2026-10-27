package com.amazon.mp3.prime.cta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.external.api.uri.FindUriByAsinJob;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPlaybackComponent;
import com.amazon.mp3.library.activity.AddToPlaylistPopupActivity;
import com.amazon.mp3.library.fragment.AlbumRemoteMetadataListener;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.playback.PlaybackError;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.playback.handler.PlayWithContentUriPlaybackUtil;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.PrimeTracksCache;
import com.amazon.mp3.prime.TrackRequester;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeCollectionTask {
    private static final String TAG = "PrimeCollectionTask";
    private boolean isUserInitiated;
    private AlbumRemoteMetadataListener mAlbumRemoteMetadataListener;
    private String mCollectionArtUrl;
    private final String mCollectionAsin;
    protected Uri mCollectionUri;
    private Context mContext;
    private final boolean mForceCTARefresh;
    private boolean mHasPrimeContent;
    private boolean mIsVideo;
    private MediaCollectionId mMediaCollectionId;
    private CTAPrimeCache.AlbumMetadataChangeListener mMetadataChangeListener;
    private PlaybackMetricInformation mMetricInformation;
    protected PlaybackPageType mPageType;
    private ControlSource mPlaybackControlSource;
    protected PlaybackListener mPlaybackListener;
    private final CTAPrimeCache mPrimeCache;
    private int mPrimeCacheTrackCount;
    private PrimeTracksCache.PrimeUIState mPrimeUIState;
    private FindUriByAsinJob.QueryByAsin mQueryByAsin;
    private boolean mRefreshCollection;
    private long mStartTime;
    protected final String mStartingTrackAsin;
    private int mStartingTrackPosition;
    private Task mTask;
    private OnUriReadyCallback mUriReadyCallback;

    public interface OnUriReadyCallback {
        void onAlbumUriReady(Uri uri);

        void onError(Exception e);
    }

    public enum Task {
        NONE,
        PLAYBACK,
        PLAYBACK_SHUFFLE,
        PLAYBACK_FULLSCREEN,
        ADD_TO_PLAYLIST,
        ADD_TO_PLAYQUEUE,
        ADD_TO_PLAYQUEUE_NO_NOTIFICATIONS,
        ADD_TO_PLAYQUEUE_SHUFFLE_NO_NOTIFICATIONS,
        PLAY_NEXT,
        PLAYBACK_DEEPLINK,
        PLAYBACK_FULLSCREEN_DEEPLINK
    }

    public static PrimeCollectionTask createPlaybackTask(Context context, String collectionAsin, String trackAsin, PlaybackPageType pageType, FindUriByAsinJob.QueryByAsin queryByAsin, PlaybackListener playbackListener, CTAPrimeCache.AlbumMetadataChangeListener metadataChangeListener) {
        PrimeCollectionTask primeCollectionTaskCreatePlaybackTask = createPlaybackTask(context, collectionAsin, trackAsin, pageType, Task.PLAYBACK, queryByAsin, (OnUriReadyCallback) null);
        primeCollectionTaskCreatePlaybackTask.mPlaybackListener = playbackListener;
        primeCollectionTaskCreatePlaybackTask.mMetadataChangeListener = metadataChangeListener;
        return primeCollectionTaskCreatePlaybackTask;
    }

    public static PrimeCollectionTask createPlaybackTask(Context context, String collectionAsin, String trackAsin, PlaybackPageType pageType, FindUriByAsinJob.QueryByAsin queryByAsin, PlaybackListener playbackListener) {
        PrimeCollectionTask primeCollectionTaskCreatePlaybackTask = createPlaybackTask(context, collectionAsin, trackAsin, pageType, Task.PLAYBACK, queryByAsin, (OnUriReadyCallback) null);
        primeCollectionTaskCreatePlaybackTask.mPlaybackListener = playbackListener;
        return primeCollectionTaskCreatePlaybackTask;
    }

    public static PrimeCollectionTask createPlaybackTask(Context context, String collectionAsin, String trackAsin, PlaybackPageType pageType, Task playbackTask, FindUriByAsinJob.QueryByAsin queryByAsin, OnUriReadyCallback callback) {
        PrimeCollectionTask primeCollectionTask = new PrimeCollectionTask(context, collectionAsin, trackAsin, false, null, playbackTask, queryByAsin, Clock.now(), callback);
        primeCollectionTask.mPageType = pageType;
        return primeCollectionTask;
    }

    public static PrimeCollectionTask createPlaybackTask(Context context, String collectionAsin, String trackAsin, boolean forceRefresh, PlaybackPageType pageType, Task playbackTask, FindUriByAsinJob.QueryByAsin queryByAsin, OnUriReadyCallback callback) {
        PrimeCollectionTask primeCollectionTask = new PrimeCollectionTask(context, collectionAsin, trackAsin, forceRefresh, null, playbackTask, queryByAsin, Clock.now(), callback);
        primeCollectionTask.mPageType = pageType;
        return primeCollectionTask;
    }

    public static PrimeCollectionTask createPlaybackTask(Context context, String collectionAsin, PlaybackPageType pageType, FindUriByAsinJob.QueryByAsin queryByAsin) {
        return createPlaybackTask(context, collectionAsin, pageType, queryByAsin, Task.PLAYBACK_FULLSCREEN);
    }

    public static PrimeCollectionTask createPlaybackTask(Context context, String collectionAsin, PlaybackPageType pageType, FindUriByAsinJob.QueryByAsin queryByAsin, Task taskType) {
        PrimeCollectionTask primeCollectionTask = new PrimeCollectionTask(context, collectionAsin, false, taskType, queryByAsin, Clock.now(), null);
        primeCollectionTask.mPageType = pageType;
        return primeCollectionTask;
    }

    public static PrimeCollectionTask createAddToPlaylistTask(Context context, String collectionAsin, FindUriByAsinJob.QueryByAsin queryByAsin) {
        return new PrimeCollectionTask(context, collectionAsin, queryByAsin, Clock.now());
    }

    public PrimeCollectionTask(Context context, String collectionAsin, String trackAsin, boolean forceCTARefresh, PlaybackMetricInformation metricInformation, Task task, FindUriByAsinJob.QueryByAsin queryByAsin, long startTime, OnUriReadyCallback callback, boolean refreshCollection, final AlbumRemoteMetadataListener albumRemoteMetadataListener) {
        this.mPrimeUIState = PrimeTracksCache.PrimeUIState.HIDE;
        this.mPlaybackControlSource = ControlSource.APP_UI;
        this.isUserInitiated = true;
        this.mContext = context;
        this.mCollectionAsin = collectionAsin;
        this.mStartingTrackAsin = trackAsin;
        this.mStartingTrackPosition = 0;
        this.mPrimeCache = new CTAPrimeCache(context, 900000);
        this.mForceCTARefresh = forceCTARefresh;
        this.mMetricInformation = metricInformation;
        this.mTask = task;
        this.mQueryByAsin = queryByAsin;
        this.mStartTime = startTime;
        this.mUriReadyCallback = callback;
        this.mRefreshCollection = refreshCollection;
        this.mAlbumRemoteMetadataListener = albumRemoteMetadataListener;
    }

    public PrimeCollectionTask(Context context, String collectionAsin, String trackAsin, boolean forceRefresh, PlaybackMetricInformation metricInformation, Task task, FindUriByAsinJob.QueryByAsin queryByAsin, long startTime, OnUriReadyCallback callback) {
        this(context, collectionAsin, trackAsin, forceRefresh, metricInformation, task, queryByAsin, startTime, callback, true, null);
    }

    public PrimeCollectionTask(Context context, String collectionAsin, boolean forceRefresh, Task task, FindUriByAsinJob.QueryByAsin queryByAsin, long startTime, final AlbumRemoteMetadataListener albumRemoteMetadataListener) {
        this(context, collectionAsin, null, forceRefresh, null, task, queryByAsin, startTime, null, true, albumRemoteMetadataListener);
    }

    public PrimeCollectionTask(Context context, String collectionAsin, FindUriByAsinJob.QueryByAsin queryByAsin, long startTime) {
        this(context, collectionAsin, false, Task.NONE, queryByAsin, startTime, null);
    }

    public void withStartIndex(int index) {
        this.mStartingTrackPosition = index;
    }

    public void withPlaybackControlSource(final ControlSource source) {
        this.mPlaybackControlSource = source;
    }

    public void withMediaCollectionId(final MediaCollectionId mediaCollectionId) {
        this.mMediaCollectionId = mediaCollectionId;
    }

    public void withUserInitiatedValue(final boolean userInitiatedValue) {
        this.isUserInitiated = userInitiatedValue;
    }

    public Integer fetchCollection() {
        int iFindTrackIndexInCollectionByAsin;
        try {
            List<PrimeTrack> arrayList = new ArrayList<>();
            if (this.mIsVideo) {
                new TrackRequester(this.mContext).getTrackFromAsin(this.mStartingTrackAsin);
            } else {
                CtaAlbumRequester ctaAlbumRequester = new CtaAlbumRequester(this.mContext, this.mForceCTARefresh, this.mMetadataChangeListener, null);
                List<PrimeTrack> listRequestTracksForAsin = ctaAlbumRequester.requestTracksForAsin(this.mCollectionAsin);
                if (this.mAlbumRemoteMetadataListener != null) {
                    this.mAlbumRemoteMetadataListener.onAlbumMetadataAvailable(ctaAlbumRequester.requestAlbumRemoteMetadataForAsin(this.mCollectionAsin));
                }
                arrayList = listRequestTracksForAsin;
            }
            Uri uriFindCollectionUriByAsin = this.mQueryByAsin.findCollectionUriByAsin(getAsinToQuery());
            this.mCollectionUri = uriFindCollectionUriByAsin;
            if (uriFindCollectionUriByAsin == null) {
                SyncService.waitForSync(this.mContext, 1563, 15L);
                Uri uriFindCollectionUriByAsin2 = this.mQueryByAsin.findCollectionUriByAsin(this.mCollectionAsin);
                this.mCollectionUri = uriFindCollectionUriByAsin2;
                if (uriFindCollectionUriByAsin2 == null) {
                    PlaybackListener playbackListener = this.mPlaybackListener;
                    if (playbackListener != null) {
                        playbackListener.onPlaybackFailure(PlaybackError.DATABASE_NO_CONTENT, null);
                    }
                    return 3;
                }
            }
            OnUriReadyCallback onUriReadyCallback = this.mUriReadyCallback;
            if (onUriReadyCallback != null) {
                onUriReadyCallback.onAlbumUriReady(this.mCollectionUri);
            }
            String artworkUrlForAsin = this.mPrimeCache.getArtworkUrlForAsin(this.mCollectionAsin);
            this.mCollectionArtUrl = artworkUrlForAsin;
            if (artworkUrlForAsin == null && arrayList.size() > 0) {
                this.mCollectionArtUrl = arrayList.get(0).getArtworkUri();
            }
            if (this.mStartingTrackAsin != null && (iFindTrackIndexInCollectionByAsin = this.mQueryByAsin.findTrackIndexInCollectionByAsin(getTracksUri(), this.mStartingTrackAsin)) != -1) {
                this.mStartingTrackPosition = iFindTrackIndexInCollectionByAsin;
            }
            boolean zHasPrimeContent = this.mPrimeCache.hasPrimeContent(this.mCollectionAsin);
            this.mHasPrimeContent = zHasPrimeContent;
            if (zHasPrimeContent) {
                this.mPrimeCacheTrackCount = this.mPrimeCache.getNonLibPrimeTrackCount("album_asin", this.mCollectionAsin);
                this.mPrimeUIState = this.mPrimeCache.getPrimeUIState(this.mCollectionAsin);
            } else {
                this.mPrimeCacheTrackCount = 0;
            }
            return 1;
        } catch (Exception e) {
            OnUriReadyCallback onUriReadyCallback2 = this.mUriReadyCallback;
            if (onUriReadyCallback2 != null) {
                onUriReadyCallback2.onError(e);
            }
            PlaybackListener playbackListener2 = this.mPlaybackListener;
            if (playbackListener2 != null) {
                playbackListener2.onPlaybackFailure(PlaybackError.FETCH_CONTENT_ERROR, e);
            }
            Log.error(TAG, "PrimeCollectionTask failed", e);
            return 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @SuppressLint({"CheckResult"})
    public void onCollectionFetched(Integer integer) {
        boolean z;
        boolean z2;
        if (integer.intValue() == 1) {
            final Uri tracksUri = getTracksUri();
            boolean z3 = AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled() && AmazonApplication.getCapabilities().isCloudQueueEnabledForAOM(this.mPlaybackControlSource) && Feature.cloud_queue_content.isEnabled();
            boolean z4 = (z3 && CloudQueueSequencer.isCloudQueuePlaying()) ? false : true;
            if (this.mMetricInformation == null) {
                this.mMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.NOT_DIRECTED, this.mPageType);
            }
            switch (AnonymousClass4.$SwitchMap$com$amazon$mp3$prime$cta$PrimeCollectionTask$Task[this.mTask.ordinal()]) {
                case 1:
                    z = false;
                    if (z3) {
                        StartPlaybackUtil.startCollectionPlayback(this.mContext, null, this.mCollectionUri, null, this.mStartingTrackPosition, false, false, null, this.mPageType, false, this.mPlaybackControlSource, this.mMediaCollectionId, false, this.mPlaybackListener, null, false, this.isUserInitiated, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.prime.cta.PrimeCollectionTask.1
                            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                            public void handlePlayback() {
                                PlayQueueSequencer.getInstance().setAsCurrentSequencer(PrimeCollectionTask.this.mPlaybackControlSource, tracksUri, null, null, null, PrimeCollectionTask.this.mStartingTrackPosition, false, true, PrimeCollectionTask.this.mMetricInformation, PrimeCollectionTask.this.mMediaCollectionId, null, PrimeCollectionTask.this.mStartTime);
                            }
                        });
                    } else {
                        PlayQueueSequencer.getInstance().setAsCurrentSequencer(this.mPlaybackControlSource, tracksUri, null, null, null, this.mStartingTrackPosition, false, true, this.mMetricInformation, this.mMediaCollectionId, null, this.mStartTime);
                    }
                    z2 = z;
                    break;
                case 2:
                    z = false;
                    if (z3) {
                        StartPlaybackUtil.startCollectionPlayback(this.mContext, null, this.mCollectionUri, null, this.mStartingTrackPosition, true, false, null, this.mPageType, false, this.mPlaybackControlSource, this.mMediaCollectionId, false, this.mPlaybackListener, null, false, false, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.prime.cta.PrimeCollectionTask.2
                            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                            public void handlePlayback() {
                                PlayQueueSequencer.getInstance().setAsCurrentSequencer(PrimeCollectionTask.this.mPlaybackControlSource, tracksUri, null, null, null, PrimeCollectionTask.this.mStartingTrackPosition, true, true, PrimeCollectionTask.this.mMetricInformation, PrimeCollectionTask.this.mMediaCollectionId, null, PrimeCollectionTask.this.mStartTime);
                            }
                        });
                    } else {
                        PlayQueueSequencer.getInstance().setAsCurrentSequencer(this.mPlaybackControlSource, tracksUri, null, null, null, this.mStartingTrackPosition, false, true, this.mMetricInformation, this.mMediaCollectionId, null, this.mStartTime);
                    }
                    z2 = z;
                    break;
                case 3:
                    if (z3) {
                        ContextMenuPlaybackComponent.startPlayback(this.mContext, tracksUri, this.mStartingTrackPosition, null, this.mMetricInformation, this.mPageType, this.mStartTime, true, this.mIsVideo, this.mCollectionUri);
                        z = false;
                        z2 = z;
                    } else {
                        z = false;
                        PlayWithContentUriPlaybackUtil.INSTANCE.startPlayback(this.mContext, this.mPlaybackControlSource, tracksUri, this.mStartingTrackPosition, null, this.mMetricInformation, this.mMediaCollectionId, this.mPageType, this.mStartTime, true, false);
                        z2 = z;
                    }
                    break;
                case 4:
                    Context context = this.mContext;
                    context.startActivity(AddToPlaylistPopupActivity.getStartIntent(context, tracksUri, true, this.mCollectionAsin, null));
                    z2 = this.mRefreshCollection;
                    break;
                case 5:
                    if (z4) {
                        PlayQueueSequencer.getInstance().addToPlayQueue(tracksUri, null, null, false, true);
                    } else {
                        CloudQueuePlaybackUtil.INSTANCE.addTrackToCurrentQueue(tracksUri, false, true);
                    }
                    z2 = this.mRefreshCollection;
                    break;
                case 6:
                    if (z4) {
                        PlayQueueSequencer.getInstance().addToPlayQueue(tracksUri, null, null, false, false);
                    } else {
                        CloudQueuePlaybackUtil.INSTANCE.addTrackToCurrentQueue(tracksUri, false, false);
                    }
                    z2 = this.mRefreshCollection;
                    break;
                case 7:
                    if (z4) {
                        PlayQueueSequencer.getInstance().addToPlayQueue(tracksUri, null, null, false, false);
                        PlaybackControllerProvider.getController(ControlSource.APP_UI).setShuffled(true);
                    } else {
                        CloudQueuePlaybackUtil.INSTANCE.addTrackToCurrentQueue(tracksUri, false, false);
                    }
                    z2 = this.mRefreshCollection;
                    break;
                case 8:
                    if (z4) {
                        PlayQueueSequencer.getInstance().addToPlayQueue(tracksUri, null, null, true, true);
                    } else {
                        CloudQueuePlaybackUtil.INSTANCE.addTrackToCurrentQueue(tracksUri, true, true);
                    }
                    z2 = this.mRefreshCollection;
                    break;
                case 9:
                    StartPlaybackUtil.startCollectionPlayback(this.mContext, null, this.mCollectionUri, null, this.mStartingTrackPosition, false, false, null, this.mPageType, false, this.mPlaybackControlSource, this.mMediaCollectionId, false, this.mPlaybackListener, null, false, false, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.prime.cta.PrimeCollectionTask.3
                        @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                        public void handlePlayback() {
                            PlayQueueSequencer.getInstance().setAsCurrentSequencer(PrimeCollectionTask.this.mPlaybackControlSource, tracksUri, null, null, null, PrimeCollectionTask.this.mStartingTrackPosition, false, true, PrimeCollectionTask.this.mMetricInformation, PrimeCollectionTask.this.mMediaCollectionId, null, PrimeCollectionTask.this.mStartTime);
                        }
                    });
                    z = false;
                    z2 = z;
                    break;
                case 10:
                    ContextMenuPlaybackComponent.startPlayback(this.mContext, tracksUri, this.mStartingTrackPosition, null, this.mMetricInformation, this.mPageType, this.mStartTime, true, this.mIsVideo, this.mCollectionUri);
                    z = false;
                    z2 = z;
                    break;
                default:
                    z = false;
                    z2 = z;
                    break;
            }
            if (z2 && CastingUtil.isCastingToAlexa() && CastingUtil.isSupportedContent(PlayQueueSequencer.getInstance().getCurrentMediaItem()) && z4) {
                PlayQueueSequencer.getInstance().getBackgroundExecutor().execute(new Runnable() { // from class: com.amazon.mp3.prime.cta.PrimeCollectionTask$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onCollectionFetched$1();
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.cta.PrimeCollectionTask$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$cta$PrimeCollectionTask$Task;

        static {
            int[] iArr = new int[Task.values().length];
            $SwitchMap$com$amazon$mp3$prime$cta$PrimeCollectionTask$Task = iArr;
            try {
                iArr[Task.PLAYBACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$cta$PrimeCollectionTask$Task[Task.PLAYBACK_SHUFFLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$cta$PrimeCollectionTask$Task[Task.PLAYBACK_FULLSCREEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$cta$PrimeCollectionTask$Task[Task.ADD_TO_PLAYLIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$cta$PrimeCollectionTask$Task[Task.ADD_TO_PLAYQUEUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$cta$PrimeCollectionTask$Task[Task.ADD_TO_PLAYQUEUE_NO_NOTIFICATIONS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$cta$PrimeCollectionTask$Task[Task.ADD_TO_PLAYQUEUE_SHUFFLE_NO_NOTIFICATIONS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$cta$PrimeCollectionTask$Task[Task.PLAY_NEXT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$cta$PrimeCollectionTask$Task[Task.PLAYBACK_DEEPLINK.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$cta$PrimeCollectionTask$Task[Task.PLAYBACK_FULLSCREEN_DEEPLINK.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCollectionFetched$1() {
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.mp3.prime.cta.PrimeCollectionTask$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCollectionFetched$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCollectionFetched$0() {
        ((SequencerPlaybackProvider) Playback.getInstance().getPlaybackProvider()).startSequencer(PlayQueueSequencer.getInstance(), this.mPlaybackControlSource, this.mStartTime);
    }

    public Uri getTracksUri() {
        Uri uri = this.mCollectionUri;
        if (uri != null) {
            return uri.buildUpon().appendEncodedPath("tracks").build();
        }
        return null;
    }

    public String getAsinToQuery() {
        return this.mCollectionAsin;
    }

    public boolean hasPrimeContent() {
        return this.mHasPrimeContent;
    }

    public int getPrimeTrackCacheCount() {
        return this.mPrimeCacheTrackCount;
    }

    public PrimeTracksCache.PrimeUIState getPrimeUIState() {
        return this.mPrimeUIState;
    }

    public String getCollectionArtUrl() {
        return this.mCollectionArtUrl;
    }

    public Uri getUri() {
        return this.mCollectionUri;
    }

    public void setIsVideo(final boolean video) {
        this.mIsVideo = video;
    }

    public Subscription execute() {
        return Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.prime.cta.PrimeCollectionTask$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.fetchCollection();
            }
        }).subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.prime.cta.PrimeCollectionTask$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.onCollectionFetched((Integer) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.prime.cta.PrimeCollectionTask$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PrimeCollectionTask.lambda$execute$2((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$execute$2(Throwable th) {
        Log.error(TAG, "Error fetching collection", th);
    }
}
