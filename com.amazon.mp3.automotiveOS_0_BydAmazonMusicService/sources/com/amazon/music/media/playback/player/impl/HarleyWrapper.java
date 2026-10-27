package com.amazon.music.media.playback.player.impl;

import android.content.Context;
import android.net.Uri;
import android.util.LruCache;
import android.util.SparseArray;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.digitalmusicplayback.AudioAttributes;
import com.amazon.digitalmusicplayback.AudioFormat;
import com.amazon.digitalmusicplayback.AuthenticationListener;
import com.amazon.digitalmusicplayback.ClientInfo;
import com.amazon.digitalmusicplayback.CustomerBenefitFlags;
import com.amazon.digitalmusicplayback.DMLSConfig;
import com.amazon.digitalmusicplayback.DownloadCoordinator;
import com.amazon.digitalmusicplayback.DownloadCoordinatorConfig;
import com.amazon.digitalmusicplayback.DownloadCoordinatorListener;
import com.amazon.digitalmusicplayback.EngineError;
import com.amazon.digitalmusicplayback.EqualizerFormat;
import com.amazon.digitalmusicplayback.EqualizerSettings;
import com.amazon.digitalmusicplayback.EqualizerUpdate;
import com.amazon.digitalmusicplayback.ErrorCode;
import com.amazon.digitalmusicplayback.ErrorScope;
import com.amazon.digitalmusicplayback.FrequencySettings;
import com.amazon.digitalmusicplayback.LoggingListener;
import com.amazon.digitalmusicplayback.MTSAttributes;
import com.amazon.digitalmusicplayback.MetricsListener;
import com.amazon.digitalmusicplayback.OutputDeviceAttributes;
import com.amazon.digitalmusicplayback.PlaybackListener;
import com.amazon.digitalmusicplayback.Player;
import com.amazon.digitalmusicplayback.PlayerAuthStrategy;
import com.amazon.digitalmusicplayback.PlayerConfig;
import com.amazon.digitalmusicplayback.PlayerFactory;
import com.amazon.digitalmusicplayback.PlayerLogLevel;
import com.amazon.digitalmusicplayback.QualitySetting;
import com.amazon.digitalmusicplayback.SequenceEntry;
import com.amazon.digitalmusicplayback.StreamingStatusResponse;
import com.amazon.digitalmusicplayback.Weblab;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.NetworkUnavailableException;
import com.amazon.music.media.playback.OnFrameChangedListener;
import com.amazon.music.media.playback.OnPlaybackMetricsListener;
import com.amazon.music.media.playback.OnPrebufferChangedListener;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.media.playback.config.BitRateSelection;
import com.amazon.music.media.playback.config.PlayerAuthentication;
import com.amazon.music.media.playback.player.PositionTracker;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.DomainModelTranslator;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.WeblabProvider;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.ShortCompanionObject;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class HarleyWrapper<REQUEST> {
    private static final long AUTO_END_SEEK_MILLIS;
    private static final QualitySetting DEFAULT_AUDIO_QUALITY_SETTING;
    private static final EqualizerFormat EQUALIZER_FORMAT;
    private static final long MIN_TIME_FOR_SCRUBBING_REBUFFER_MILLIS;
    private static final long MIN_TIME_FOR_SCRUBBING_TERMINATION_MILLIS;
    private static final long MIN_TRACK_STREAMED_MILLIS;
    private static final Set<String> VALID_EXTENSIONS;
    private static final Logger logger;
    private static final Logger nativeLogger;
    private PositionTracker actualPosition;
    private final int[] bandGainCache;
    private int bassGainMillibels;
    private PlaybackAttributes bestAvailableAttributes;
    private final HarleyCallback<? super REQUEST> callback;
    private volatile boolean clearingData;
    private ClientInfo clientInfo;
    private final Context context;
    private PlaybackAttributes currentPlaybackAttributes;
    private PlayerCapabilities currentPlayerCapabilities;
    private EnumSet<CustomerBenefitFlags> customerBenefitFlags;
    private boolean debugHarley;
    private long desiredSeekPosition;
    private DMLSConfig dmlsConfig;
    private boolean downloaded;
    private final List<SequenceEntry> entryQueue;
    private final AndroidUtils.ForegroundListener foregroundListener;
    private OnFrameChangedListener frameChangedListener;
    private REQUEST harleyRequest;
    private int harleySequenceId;
    private volatile boolean harleyStalled;
    private boolean ignoreTrackStreamed;
    private volatile InitStatus initStatus;
    private final Object initStatusLock = new Object();
    private boolean loudnessNormalizationEnabled;
    private String mricToken;
    private QualitySetting otaStreamingQualitySetting;
    private final SparseArray<REQUEST> playRequestMap;
    private HarleyWrapper<REQUEST>.PBListener playbackListener;
    private OnPlaybackMetricsListener playbackMetricsListener;
    private Player player;
    private PlayerAuthentication playerAuthentication;
    private OnPrebufferChangedListener prebufferChangedListener;
    private boolean prioritizeDownloads;
    private LruCache<Integer, REQUEST> recentlyPlayed;
    private final List<REQUEST> requestQueue;
    private long scrubbingTimestamp;
    private long seekEndTime;
    private long seekPosition;
    private boolean seeking;
    private volatile boolean shouldClearData;
    private volatile Status status;
    private File storageDir;
    private Boolean streamOnWifiOnly;
    private File tempDir;
    private boolean threeDMode;
    private int trebleGainMillibels;
    private final List<REQUEST> unmodifiableRequestQueue;
    private float volume;
    private QualitySetting wifiStreamingQualitySetting;

    public interface HarleyCallback<REQUEST> {
        MediaItem getMediaItem(REQUEST request);

        Uri getUri(REQUEST request);

        void onAmountBufferedChanged(REQUEST request, long j);

        void onDurationChanged(REQUEST request, long j);

        void onError(REQUEST request, PlaybackException playbackException);

        void onInactivityThresholdSecondsReceived(long j);

        void onMaximumConcurrencyReached(List<StreamingStatusResponse> list);

        void onPlaybackTerminated(REQUEST request, long j, MTSAttributes mTSAttributes);

        void onPositionChanged(long j);

        void onStatusChanged();

        void onStreamingInitiated(REQUEST request, MTSAttributes mTSAttributes);

        void onStreamingInitiationFailed(REQUEST request, MTSAttributes mTSAttributes);

        void onStreamingRequiredRebuffering(REQUEST request, float f, MTSAttributes mTSAttributes);

        void onTrackChanged();

        void onTrackFinished(REQUEST request, long j, MTSAttributes mTSAttributes, boolean z);

        void playbackAttributesChanged();
    }

    public enum InitStatus {
        UNINITIALIZED,
        INITIALIZING,
        INITIALIZING_WITH_WRONG_VALUES,
        INITIALIZED
    }

    public enum Status {
        RENDERING,
        PAUSED,
        UNSTARTED,
        PENDING
    }

    private short convertIntToShort(int i) {
        return i < -32768 ? ShortCompanionObject.MIN_VALUE : i > 32767 ? ShortCompanionObject.MAX_VALUE : (short) i;
    }

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        MIN_TIME_FOR_SCRUBBING_TERMINATION_MILLIS = timeUnit.toMillis(2L);
        MIN_TIME_FOR_SCRUBBING_REBUFFER_MILLIS = timeUnit.toMillis(1L);
        logger = LoggerFactory.getLogger("HarleyWrapper");
        nativeLogger = LoggerFactory.getLogger("DMPPlayer");
        AUTO_END_SEEK_MILLIS = timeUnit.toMillis(1L);
        MIN_TRACK_STREAMED_MILLIS = timeUnit.toMillis(1L);
        DEFAULT_AUDIO_QUALITY_SETTING = QualitySetting.BESTAVAILABLE;
        EQUALIZER_FORMAT = EqualizerFormat.BANDSIZE5;
        HashSet hashSet = new HashSet();
        VALID_EXTENSIONS = hashSet;
        hashSet.add(HlsSegmentFormat.AAC);
        hashSet.add("aif");
        hashSet.add("aiff");
        hashSet.add("flac");
        hashSet.add("m4a");
        hashSet.add(HlsSegmentFormat.MP3);
        hashSet.add("mpg");
        hashSet.add("ogg");
        hashSet.add("wav");
        hashSet.add("wma");
        try {
            int i = PlayerFactory.$r8$clinit;
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public HarleyWrapper(Context context, HarleyCallback<? super REQUEST> harleyCallback) {
        PlaybackAttributes playbackAttributes = PlaybackAttributes.UNKNOWN;
        this.currentPlaybackAttributes = playbackAttributes;
        this.bestAvailableAttributes = playbackAttributes;
        this.playRequestMap = new SparseArray<>();
        this.entryQueue = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.requestQueue = arrayList;
        this.unmodifiableRequestQueue = Collections.unmodifiableList(arrayList);
        this.harleySequenceId = -1;
        this.debugHarley = false;
        this.status = Status.UNSTARTED;
        this.initStatus = InitStatus.UNINITIALIZED;
        this.harleyStalled = true;
        this.actualPosition = new PositionTracker();
        this.recentlyPlayed = new LruCache<>(24);
        this.volume = 1.0f;
        QualitySetting qualitySetting = DEFAULT_AUDIO_QUALITY_SETTING;
        this.wifiStreamingQualitySetting = qualitySetting;
        this.otaStreamingQualitySetting = qualitySetting;
        this.bassGainMillibels = Integer.MIN_VALUE;
        this.trebleGainMillibels = Integer.MIN_VALUE;
        this.foregroundListener = new AndroidUtils.ForegroundListener() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.1
            @Override // com.amazon.music.media.playback.util.AndroidUtils.ForegroundListener
            public void onApplicationInForegroundChanged(boolean z) {
                if (z) {
                    HarleyWrapper.this.createPlayerIfNeeded();
                }
            }
        };
        this.context = (Context) Validate.notNull(context);
        this.callback = (HarleyCallback) Validate.notNull(harleyCallback);
        int[] iArr = new int[getEqualizerBandCount()];
        this.bandGainCache = iArr;
        Arrays.fill(iArr, Integer.MIN_VALUE);
    }

    public void setMricTokenForChildUser(String str) {
        this.mricToken = str;
    }

    public void setIgnoreTrackStreamed(boolean z) {
        this.ignoreTrackStreamed = z;
    }

    public REQUEST getCurrentRequest() {
        if (this.requestQueue.isEmpty()) {
            return null;
        }
        return this.requestQueue.get(0);
    }

    public REQUEST getNextRequest() {
        if (this.requestQueue.size() < 2) {
            return null;
        }
        return this.requestQueue.get(1);
    }

    public DownloadCoordinator getDownloadCoordinator(DownloadCoordinatorListener downloadCoordinatorListener, DownloadCoordinatorConfig downloadCoordinatorConfig) {
        if (this.initStatus != InitStatus.INITIALIZED) {
            return null;
        }
        return this.player.getDownloadCoordinator(downloadCoordinatorListener, downloadCoordinatorConfig);
    }

    private boolean isNewConfig(DMLSConfig dMLSConfig, ClientInfo clientInfo) {
        if (dMLSConfig == null || clientInfo == null) {
            return false;
        }
        if (this.dmlsConfig == null || this.clientInfo == null) {
            return true;
        }
        return !dMLSConfig.equals(r4);
    }

    @MainThread
    public void updateConfiguration(DMLSConfig dMLSConfig, ClientInfo clientInfo, File file, File file2, Boolean bool) throws PlaybackException {
        if (isNewConfig(dMLSConfig, clientInfo) || this.debugHarley != bool.booleanValue()) {
            this.debugHarley = bool.booleanValue();
            this.dmlsConfig = dMLSConfig;
            this.clientInfo = clientInfo;
            this.storageDir = file;
            this.tempDir = file2;
            synchronized (this.initStatusLock) {
                int i = AnonymousClass6.$SwitchMap$com$amazon$music$media$playback$player$impl$HarleyWrapper$InitStatus[this.initStatus.ordinal()];
                if (i != 1 && i != 2) {
                    if (i == 3) {
                        logger.info("updateConfiguration called while player initialized, calling on close(), shutdown(false)");
                        recreatePlayer();
                        return;
                    } else {
                        createPlayerIfNeeded();
                        return;
                    }
                }
                logger.info("updateConfiguration while initStatus is " + this.initStatus);
                this.initStatus = InitStatus.INITIALIZING_WITH_WRONG_VALUES;
            }
        }
    }

    public File getCrashDirectory() {
        File file = this.tempDir;
        if (file == null || !file.canRead()) {
            return null;
        }
        return new File(this.tempDir, "harleyTemp");
    }

    public void setCustomerBenefits(EnumSet<CustomerBenefitFlags> enumSet) {
        this.customerBenefitFlags = enumSet;
        if (this.initStatus == InitStatus.INITIALIZED) {
            this.player.setCustomerBenefits(enumSet);
        }
    }

    private boolean canCreatePlayer() {
        PlayerAuthentication playerAuthentication;
        return (this.dmlsConfig == null || (playerAuthentication = this.playerAuthentication) == null || StringUtils.isEmpty(playerAuthentication.getAdpToken()) || StringUtils.isEmpty(this.playerAuthentication.getSigningKey()) || StringUtils.isEmpty(this.dmlsConfig.getDeviceID())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createPlayerIfNeeded() {
        synchronized (this.initStatusLock) {
            if (this.initStatus == InitStatus.UNINITIALIZED && !this.clearingData && canCreatePlayer()) {
                if (!AndroidUtils.isApplicationInForeground() && this.status == Status.UNSTARTED) {
                    AndroidUtils.addForegroundListener(this.foregroundListener);
                    return;
                }
                AndroidUtils.removeForegroundListener(this.foregroundListener);
                this.initStatus = InitStatus.INITIALIZING;
                ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.2
                    @Override // java.lang.Runnable
                    public void run() {
                        HarleyWrapper harleyWrapper = HarleyWrapper.this;
                        harleyWrapper.createPlayer(harleyWrapper.dmlsConfig);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recreatePlayer() {
        synchronized (this.initStatusLock) {
            boolean z = this.player != null && this.shouldClearData;
            DMLSConfig dMLSConfig = this.dmlsConfig;
            ClientInfo clientInfo = this.clientInfo;
            PlayerAuthentication playerAuthentication = this.playerAuthentication;
            close();
            this.dmlsConfig = dMLSConfig;
            this.clientInfo = clientInfo;
            this.playerAuthentication = playerAuthentication;
            if (!z) {
                createPlayerIfNeeded();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createPlayer(DMLSConfig dMLSConfig) {
        final PlaybackException playbackException;
        logger.info("createPlayer()");
        String strCreateFilePath = createFilePath(this.storageDir, "harleyDRM");
        String strCreateFilePath2 = createFilePath(this.tempDir, "harleyTemp");
        File file = new File(this.context.getFilesDir().getAbsolutePath(), "harleyConfig");
        File file2 = new File(this.storageDir, "harleyConfig");
        if (file.exists()) {
            file.renameTo(file2);
        }
        PlayerConfig playerConfig = new PlayerConfig(file2.getAbsolutePath(), dMLSConfig, strCreateFilePath2, strCreateFilePath, false, createFilePath(this.storageDir, "harleyDataStorage"), PlayerAuthStrategy.ADP, this.wifiStreamingQualitySetting, this.otaStreamingQualitySetting, this.threeDMode);
        HarleyWrapper<REQUEST>.PBListener pBListener = new PBListener();
        this.playbackListener = pBListener;
        try {
            if (this.debugHarley) {
                Player playerCreate = HarleyTracePlayer.create(this.context, pBListener, new LogListener(), new MtrxListener(), new AuthListener(), new ConcurrencyListener(), new WeblabClient(), new MetricsAttributesProvider());
                this.player = playerCreate;
                playerCreate.setLogLevel(PlayerLogLevel.DEBUG);
            } else {
                Player playerCreate2 = PlayerFactory.create(this.context, pBListener, new LogListener(), new MtrxListener(), new AuthListener(), new ConcurrencyListener(), new WeblabClient(), new MetricsAttributesProvider());
                this.player = playerCreate2;
                playerCreate2.setLogLevel(PlayerLogLevel.INFO);
            }
            EngineError engineErrorInitialize = this.player.initialize(playerConfig, this.clientInfo, EQUALIZER_FORMAT);
            if (engineErrorInitialize.getCode() != ErrorCode.NOERROR) {
                PlaybackException.Config configPlaybackError = new PlaybackException.Config(PlaybackException.Recoverability.CONTROLLER).metricsName("CreateHarleyPlayer-" + engineErrorInitialize.getCode()).playbackError(getPlaybackError(engineErrorInitialize.getCode()));
                configPlaybackError.description("EngineError - Code: " + engineErrorInitialize.getCode() + ", Scope: " + engineErrorInitialize.getScope() + ", Descriptor: " + engineErrorInitialize.getDescriptor());
                throw new PlaybackException(configPlaybackError);
            }
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.4
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (HarleyWrapper.this.initStatusLock) {
                        boolean z = HarleyWrapper.this.initStatus == InitStatus.INITIALIZING_WITH_WRONG_VALUES || HarleyWrapper.this.shouldClearData;
                        HarleyWrapper.this.initStatus = InitStatus.INITIALIZED;
                        if (z) {
                            HarleyWrapper.logger.info("createPlayer: redo -> recreating the player");
                            HarleyWrapper.this.recreatePlayer();
                        } else {
                            try {
                                HarleyWrapper.this.setupPlayer();
                            } catch (Exception e) {
                                HarleyWrapper.logger.error("Error setting up Harley", (Throwable) e);
                            }
                            if (HarleyWrapper.this.status == Status.PENDING) {
                                HarleyWrapper.this.startPlayback();
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            logger.error("Error initializing Harley", (Throwable) e);
            if (e instanceof PlaybackException) {
                playbackException = (PlaybackException) e;
            } else {
                playbackException = new PlaybackException(new PlaybackException.Config(PlaybackException.Recoverability.CONTROLLER).metricsName("CreateHarleyPlayer-" + e.getClass().getSimpleName()).description("Exception initializing Harley").source(e));
            }
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (HarleyWrapper.this.initStatusLock) {
                        HarleyWrapper.this.initStatus = InitStatus.INITIALIZED;
                        HarleyWrapper.logger.error("Exception creating Player, callin on HarleyWrapper close() -> shutdown(false)");
                        HarleyWrapper.this.close();
                    }
                    HarleyWrapper.this.callback.onError(HarleyWrapper.this.getCurrentRequest(), playbackException);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupPlayer() {
        this.player.setVolume(this.volume);
        QualitySetting qualitySetting = this.wifiStreamingQualitySetting;
        if (qualitySetting != null) {
            this.player.setWifiAudioQualitySetting(qualitySetting);
        }
        QualitySetting qualitySetting2 = this.otaStreamingQualitySetting;
        if (qualitySetting2 != null) {
            this.player.setOTAAudioQualitySetting(qualitySetting2);
        }
        this.player.setLoudnessNormalizationMode(this.loudnessNormalizationEnabled);
        if (this.playerAuthentication != null) {
            this.player.refreshRequestSigningCredentials();
        }
        ArrayList<EqualizerUpdate> arrayList = new ArrayList<>(this.bandGainCache.length);
        int i = 0;
        while (true) {
            int[] iArr = this.bandGainCache;
            if (i >= iArr.length) {
                break;
            }
            int i2 = iArr[i];
            if (i2 != Integer.MIN_VALUE) {
                arrayList.add(new EqualizerUpdate(convertIntToShort(i2), this.player.getEqualizerSettings().getEqualizerBandList().get(i).getBandIndex()));
            }
            i++;
        }
        this.player.updateEqualizerSettings(arrayList);
        int i3 = this.bassGainMillibels;
        if (i3 != Integer.MIN_VALUE) {
            this.player.updateBassSettings(convertIntToShort(i3));
        }
        int i4 = this.trebleGainMillibels;
        if (i4 != Integer.MIN_VALUE) {
            this.player.updateTrebleSettings(convertIntToShort(i4));
        }
        Boolean bool = this.streamOnWifiOnly;
        if (bool != null) {
            this.player.setStreamOnWifiOnly(bool.booleanValue());
        }
        this.player.setCustomerBenefits(this.customerBenefitFlags);
        this.player.setThreeDMode(this.threeDMode);
        this.player.setPrioritizeDownloadsPlayback(this.prioritizeDownloads);
    }

    private String createFilePath(File file, String str) {
        File file2 = new File(file, str);
        file2.mkdirs();
        String absolutePath = file2.getAbsolutePath();
        logger.debug("File path created: " + absolutePath);
        return absolutePath;
    }

    public String getVersion() {
        return Player.libraryVersion();
    }

    @NonNull
    public PlaybackAttributes getCurrentPlaybackAttributes() {
        if (!isHarleyCurrent()) {
            return PlaybackAttributes.UNKNOWN;
        }
        return this.currentPlaybackAttributes;
    }

    @NonNull
    public PlaybackAttributes getBestAvailablePlaybackAttributes() {
        if (!isHarleyCurrent()) {
            return PlaybackAttributes.UNKNOWN;
        }
        return this.bestAvailableAttributes;
    }

    @Nullable
    public PlayerCapabilities getPlayerCapabilities() {
        if (isHarleyCurrent()) {
            return this.currentPlayerCapabilities;
        }
        return null;
    }

    public boolean isDownloaded() {
        return this.downloaded && isHarleyCurrent();
    }

    public void setPlayerAuthentication(PlayerAuthentication playerAuthentication) {
        logger.info("setPlayerAuthentication()");
        this.playerAuthentication = playerAuthentication;
        if (this.initStatus == InitStatus.INITIALIZED) {
            this.player.refreshRequestSigningCredentials();
        } else {
            createPlayerIfNeeded();
        }
    }

    public boolean isHarleyDebugModeEnabled() {
        return this.debugHarley;
    }

    public void close() {
        shutdown(false);
    }

    public void clearData() {
        shutdown(true);
    }

    public void clearCache() {
        if (this.initStatus == InitStatus.INITIALIZED) {
            this.player.clearCache();
        }
    }

    public boolean canPlay(Uri uri) {
        String lastPathSegment;
        int iLastIndexOf;
        if (uri == null || (lastPathSegment = uri.getLastPathSegment()) == null || (iLastIndexOf = lastPathSegment.lastIndexOf(46)) < 0) {
            return false;
        }
        return VALID_EXTENSIONS.contains(lastPathSegment.substring(iLastIndexOf + 1).toLowerCase());
    }

    private void shutdown(boolean z) {
        synchronized (this.initStatusLock) {
            logger.info("shutdown(" + z + ") - initStatus: " + this.initStatus + " " + StringUtils.getStackTrace(5));
            if (this.clearingData) {
                return;
            }
            if (z || this.shouldClearData) {
                this.shouldClearData = true;
            }
            AndroidUtils.removeForegroundListener(this.foregroundListener);
            if (this.initStatus != InitStatus.INITIALIZED) {
                return;
            }
            setQueue(Collections.emptyList(), false);
            if (this.player != null) {
                if (this.shouldClearData) {
                    this.clearingData = true;
                    this.shouldClearData = false;
                    clearDataInBackground();
                } else {
                    this.player.shutdown(Boolean.FALSE);
                    this.player = null;
                }
            }
            this.initStatus = InitStatus.UNINITIALIZED;
            this.playbackListener = null;
            this.dmlsConfig = null;
            this.clientInfo = null;
            this.playerAuthentication = null;
        }
    }

    private void clearDataInBackground() {
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.5
            @Override // java.lang.Runnable
            public void run() {
                HarleyWrapper.this.player.shutdown(Boolean.TRUE);
                synchronized (HarleyWrapper.this.initStatusLock) {
                    HarleyWrapper.this.clearingData = false;
                    HarleyWrapper.this.shouldClearData = false;
                    HarleyWrapper.this.createPlayerIfNeeded();
                }
            }
        });
    }

    public void setQueue(List<REQUEST> list, boolean z) {
        logger.info("setQueue(" + Arrays.toString(list.toArray()) + ", " + z + ")");
        boolean zIsEmpty = this.requestQueue.isEmpty();
        this.seeking = false;
        this.desiredSeekPosition = 0L;
        this.seekEndTime = 0L;
        this.playRequestMap.clear();
        this.entryQueue.clear();
        this.requestQueue.clear();
        this.requestQueue.addAll(list);
        if (this.initStatus == InitStatus.INITIALIZED && !zIsEmpty && (this.requestQueue.isEmpty() || !z)) {
            this.player.setPaused(true);
        }
        if (z && !this.requestQueue.isEmpty()) {
            startPlayback();
        } else {
            this.status = Status.UNSTARTED;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlayback() {
        if (this.initStatus != InitStatus.INITIALIZED) {
            this.status = Status.PENDING;
            createPlayerIfNeeded();
            return;
        }
        ArrayList arrayList = new ArrayList(this.requestQueue);
        this.requestQueue.clear();
        ArrayList<String> arrayListFilterUris = filterUris(convertToUris(arrayList));
        ArrayList<SequenceEntry> sequenceEntries = this.player.play(arrayListFilterUris).getSequenceEntries();
        Logger logger2 = logger;
        logger2.debug("entries: " + Arrays.toString(sequenceEntries.toArray()));
        if (sequenceEntries.isEmpty()) {
            logger2.info("setting status to UNSTARTED from startPlayback()");
            this.status = Status.UNSTARTED;
            return;
        }
        this.status = Status.PENDING;
        logger2.info("setting status to " + this.status + " from setQueue()");
        append(arrayList, arrayListFilterUris, sequenceEntries);
        if (this.desiredSeekPosition <= 0 || arrayList.get(0) != getCurrentRequest()) {
            return;
        }
        seekTo(this.desiredSeekPosition);
    }

    public void addToQueue(REQUEST request, List<REQUEST> list) {
        logger.info("addToQueue(" + request + ", " + Arrays.toString(list.toArray()) + ")");
        int iIndexOf = request == null ? 0 : this.requestQueue.indexOf(request);
        if (iIndexOf < 0) {
            iIndexOf = this.requestQueue.size() == 0 ? -1 : this.requestQueue.size() - 1;
        }
        addToQueue(iIndexOf + 1, list);
    }

    public void addToQueue(int i, List<REQUEST> list) {
        logger.info("addToQueue(" + i + ", " + Arrays.toString(list.toArray()) + ")");
        if (i < 0 || i > this.requestQueue.size()) {
            throw new IndexOutOfBoundsException("replaceFromIndex: " + i + ", size: " + this.requestQueue.size());
        }
        if (this.status == Status.UNSTARTED || this.initStatus != InitStatus.INITIALIZED) {
            int size = this.requestQueue.size();
            if (i > size) {
                throw new IndexOutOfBoundsException("replaceFromIndex: " + i + ", size: " + size);
            }
            this.requestQueue.subList(i, size).clear();
            this.requestQueue.addAll(list);
            return;
        }
        int i2 = i - 1;
        while (i2 >= 0 && this.entryQueue.get(i2) == null) {
            i2--;
        }
        if (i2 < 0) {
            setQueue(list, (this.status == Status.PAUSED || this.status == Status.UNSTARTED) ? false : true);
            return;
        }
        List<String> listConvertToUris = convertToUris(list);
        ArrayList<SequenceEntry> sequenceEntries = this.player.enqueue(this.entryQueue.get(i2), filterUris(listConvertToUris)).getSequenceEntries();
        int size2 = this.entryQueue.size();
        while (true) {
            size2--;
            if (size2 >= i) {
                SequenceEntry sequenceEntryRemove = this.entryQueue.remove(size2);
                this.requestQueue.remove(size2);
                if (sequenceEntryRemove != null) {
                    this.playRequestMap.remove(sequenceEntryRemove.getSequenceID());
                }
            } else {
                append(list, listConvertToUris, sequenceEntries);
                return;
            }
        }
    }

    private void append(List<REQUEST> list, List<String> list2, List<SequenceEntry> list3) {
        int size = list3.size();
        int size2 = list.size();
        boolean z = size == size2;
        if (size > size2) {
            StrictMode.crashIfStrict("Too many entries returned! (requested " + size2 + " and returned " + size + ")");
        }
        this.requestQueue.addAll(list);
        boolean z2 = z;
        int i = 0;
        for (int i2 = 0; i2 < size2; i2++) {
            if (!z2 && size - i == size2 - i2) {
                z2 = true;
            }
            if (i >= size) {
                this.entryQueue.add(null);
            } else {
                SequenceEntry sequenceEntry = list3.get(i);
                if (z2 || list2.get(i2).equals(sequenceEntry.getURI())) {
                    this.entryQueue.add(sequenceEntry);
                    this.playRequestMap.put(sequenceEntry.getSequenceID(), list.get(i2));
                    i++;
                } else {
                    this.entryQueue.add(null);
                }
            }
        }
        addSoonToPlayToRecents();
    }

    private void addSoonToPlayToRecents() {
        int iMin = Math.min(this.requestQueue.size(), this.entryQueue.size());
        int i = 8;
        for (int i2 = 0; i2 < iMin && i > 0; i2++) {
            SequenceEntry sequenceEntry = this.entryQueue.get(i2);
            REQUEST request = this.requestQueue.get(i2);
            if (sequenceEntry != null && request != null) {
                this.recentlyPlayed.put(Integer.valueOf(sequenceEntry.getSequenceID()), request);
                i--;
            }
        }
    }

    private static PlaybackException.PlaybackError getPlaybackError(ErrorCode errorCode) {
        if (errorCode == null) {
            return PlaybackException.PlaybackError.GenericStreaming;
        }
        if (ErrorCode.DMLSUNKNOWNERROR.equals(errorCode)) {
            return PlaybackException.PlaybackError.DMLS;
        }
        if (ErrorCode.CONTENTNOTELIGIBLE.equals(errorCode)) {
            return PlaybackException.PlaybackError.ContentNotEligible;
        }
        if (ErrorCode.CUSTOMERNOTELIGIBLE.equals(errorCode)) {
            return PlaybackException.PlaybackError.CustomerNotEligible;
        }
        if (ErrorCode.BADREQUEST.equals(errorCode) || ErrorCode.FRAGMENTREQUESTFAILED.equals(errorCode) || ErrorCode.RETRYFAILURE.equals(errorCode) || ErrorCode.NETWORKREQUESTFAILED.equals(errorCode) || ErrorCode.SERVICEUNAVAILABLE.equals(errorCode) || ErrorCode.INTERNALERROR.equals(errorCode) || ErrorCode.THROTTLINGERROR.equals(errorCode) || ErrorCode.DEPENDENCYSERVICEERROR.equals(errorCode) || ErrorCode.SYSTEMOTHERERROR.equals(errorCode) || ErrorCode.VALIDATIONERROR.equals(errorCode)) {
            return PlaybackException.PlaybackError.NetworkFailure;
        }
        ErrorCode errorCode2 = ErrorCode.AUDIODEVICEUNAVAILABLE;
        if (errorCode2.equals(errorCode) || ErrorCode.ENGINENOTINITIALIZED.equals(errorCode) || errorCode2.equals(errorCode) || ErrorCode.OUTOFMEMORYERROR.equals(errorCode) || ErrorCode.AUDIODRIVERFAILURE.equals(errorCode) || ErrorCode.INVALIDCONFIGURATION.equals(errorCode)) {
            return PlaybackException.PlaybackError.GenericStreaming;
        }
        if (ErrorCode.FILEFORMAT.equals(errorCode) || ErrorCode.FILEPERMISSION.equals(errorCode) || ErrorCode.FILEOPENFAILED.equals(errorCode) || ErrorCode.FILESEEKFAILED.equals(errorCode) || ErrorCode.FILEREADFAILED.equals(errorCode)) {
            return PlaybackException.PlaybackError.FileNotFound;
        }
        if (ErrorCode.FILEEXCEPTION.equals(errorCode)) {
            return PlaybackException.PlaybackError.InvalidFile;
        }
        if (ErrorCode.UNRECOGNIZEDRESPONSESTRUCTURE.equals(errorCode) || ErrorCode.JSONPARSEERROR.equals(errorCode)) {
            return PlaybackException.PlaybackError.InvalidParameter;
        }
        if (ErrorCode.CONTENTNOTAVAILABLE.equals(errorCode) || ErrorCode.STALLING.equals(errorCode) || ErrorCode.MANIFESTPARSEFAILED.equals(errorCode) || ErrorCode.MANIFESTFETCHFAILED.equals(errorCode) || ErrorCode.EMPTYMEDIALIST.equals(errorCode) || ErrorCode.MANIFESTEXPIRED.equals(errorCode) || ErrorCode.DECODEFAILED.equals(errorCode) || ErrorCode.FRAGMENTBUILDERFAILED.equals(errorCode) || ErrorCode.FRAGMENTPARSERFAILED.equals(errorCode) || ErrorCode.FRAGMENTNOTBUILT.equals(errorCode) || ErrorCode.AVCODECFAILED.equals(errorCode) || ErrorCode.AUDIOSTREAMNOTFOUND.equals(errorCode) || ErrorCode.STREAMINGSTATUSINVALID.equals(errorCode) || ErrorCode.UNKNOWNMARKETPLACE.equals(errorCode)) {
            return PlaybackException.PlaybackError.StreamingPlayback;
        }
        if (ErrorCode.DRMSESSIONNOTINITIALIZED.equals(errorCode) || ErrorCode.DRMDECRYPTIONFAILED.equals(errorCode) || ErrorCode.DRMLICENSEACQUISITIONFAILED.equals(errorCode)) {
            return PlaybackException.PlaybackError.GenericDrm;
        }
        if (ErrorCode.NOTAUTHENTICATED.equals(errorCode) || ErrorCode.CUSTOMERNOTVALID.equals(errorCode) || ErrorCode.DEVICENOTVALID.equals(errorCode) || ErrorCode.CLIENTNOTVALID.equals(errorCode) || ErrorCode.DEVICENOTAUTHORIZED.equals(errorCode) || ErrorCode.DEVICEIDMISMATCH.equals(errorCode) || ErrorCode.UNSUPPORTEDDEVICETYPE.equals(errorCode) || ErrorCode.ACCOUNTNOTFOUND.equals(errorCode)) {
            return PlaybackException.PlaybackError.SignedOut;
        }
        if (ErrorCode.MAXCONTENTLIMIT.equals(errorCode) || ErrorCode.MULTIPLECONTENTREQUESTNOTSUPPORTED.equals(errorCode) || ErrorCode.MAXCONCURRENCYERROR.equals(errorCode)) {
            return PlaybackException.PlaybackError.ConnectionLimit;
        }
        if (ErrorCode.REQUIREDCOOKIESMISSING.equals(errorCode)) {
            return PlaybackException.PlaybackError.Cirrus;
        }
        if (ErrorCode.CONTENTNOTVALID.equals(errorCode) || ErrorCode.APPMETADATANOTVALID.equals(errorCode) || ErrorCode.PROTOCOLNOTVALID.equals(errorCode) || ErrorCode.APPVERSIONNOTVALID.equals(errorCode) || ErrorCode.EMPTYCONTENTLIST.equals(errorCode) || ErrorCode.INVALIDTRACKID.equals(errorCode) || ErrorCode.INVALIDEXPIRATIONTIME.equals(errorCode) || ErrorCode.INVALIDCONTENTDURATION.equals(errorCode) || ErrorCode.INVALIDBITRATE.equals(errorCode) || ErrorCode.INVALIDMANIFESTTYPE.equals(errorCode) || ErrorCode.INVALIDHLSVERSION.equals(errorCode) || ErrorCode.INVALIDANCHOR.equals(errorCode) || ErrorCode.UNSUPPORTEDURI.equals(errorCode)) {
            return PlaybackException.PlaybackError.StreamingServiceError;
        }
        if (ErrorCode.DOWNLOADINPROGRESS.equals(errorCode) || ErrorCode.FILEWRITE.equals(errorCode) || ErrorCode.DEVICENOTELIGIBLE.equals(errorCode)) {
            return PlaybackException.PlaybackError.SecondaryStorageNotFound;
        }
        if (ErrorCode.STORAGEFULL.equals(errorCode)) {
            return PlaybackException.PlaybackError.InsufficientStorage;
        }
        if (ErrorCode.WIFIREQUIRED.equals(errorCode)) {
            return PlaybackException.PlaybackError.StreamingRestriction;
        }
        if (ErrorCode.DRMSESSIONEXPIRED.equals(errorCode)) {
            return PlaybackException.PlaybackError.DrmLicenseExpired;
        }
        return PlaybackException.PlaybackError.GenericStreaming;
    }

    public int skipTo(REQUEST request) {
        logger.info("skipTo(" + request + ") - requests: " + getRequestQueue());
        if (this.status == Status.UNSTARTED || this.initStatus != InitStatus.INITIALIZED) {
            int iIndexOf = this.requestQueue.indexOf(request);
            if (iIndexOf <= 0) {
                return 0;
            }
            this.desiredSeekPosition = 0L;
            this.requestQueue.subList(0, iIndexOf).clear();
            return iIndexOf;
        }
        int iIndexOf2 = this.requestQueue.indexOf(request);
        if (iIndexOf2 <= 0) {
            return 0;
        }
        this.seeking = false;
        this.seekEndTime = 0L;
        int size = this.entryQueue.size();
        int i = iIndexOf2;
        while (i < size && this.entryQueue.get(iIndexOf2) == null) {
            i++;
        }
        if (i < size) {
            int i2 = i;
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                if (this.entryQueue.get(i2) != null) {
                    this.playRequestMap.remove(this.entryQueue.get(i2).getSequenceID());
                }
            }
            this.entryQueue.subList(0, i).clear();
            this.requestQueue.subList(0, i).clear();
            this.player.playNext(this.entryQueue.get(0));
        }
        addSoonToPlayToRecents();
        return i;
    }

    public void seekTo(long j) {
        if ((this.status == Status.UNSTARTED || this.initStatus != InitStatus.INITIALIZED) && !this.requestQueue.isEmpty()) {
            this.desiredSeekPosition = j;
            return;
        }
        if (this.entryQueue.isEmpty()) {
            throw new IllegalStateException("Trying to seek when nothing is playing!");
        }
        if (this.entryQueue.get(0) == null) {
            return;
        }
        this.desiredSeekPosition = j;
        if (this.seeking) {
            return;
        }
        this.seeking = true;
        this.seekPosition = j;
        if (this.status == Status.RENDERING) {
            logger.info("setting status to PENDING from seekTo()");
            this.status = Status.PENDING;
        }
        this.seekEndTime = Clock.now() + AUTO_END_SEEK_MILLIS;
        this.player.seek(this.entryQueue.get(0), j);
    }

    public void setVolume(float f) {
        if (this.initStatus == InitStatus.INITIALIZED && this.volume != f) {
            this.player.setVolume(f);
        }
        this.volume = f;
    }

    public void setPlaybackSpeed(double d) {
        if (this.initStatus == InitStatus.INITIALIZED) {
            this.player.setTempo(d);
        }
    }

    public void setPlaybackMetricsListener(OnPlaybackMetricsListener onPlaybackMetricsListener) {
        this.playbackMetricsListener = onPlaybackMetricsListener;
    }

    public void setOnFrameChangedListener(OnFrameChangedListener onFrameChangedListener) {
        this.frameChangedListener = onFrameChangedListener;
    }

    public void setOnPrebufferChangedListener(OnPrebufferChangedListener onPrebufferChangedListener) {
        this.prebufferChangedListener = onPrebufferChangedListener;
    }

    public float getVolume() {
        return this.volume;
    }

    public void setStreamOnWifiOnly(boolean z) {
        this.streamOnWifiOnly = Boolean.valueOf(z);
        if (this.initStatus == InitStatus.INITIALIZED) {
            this.player.setStreamOnWifiOnly(z);
        }
    }

    public void setBitRateSelectionForWifi(BitRateSelection bitRateSelection) {
        this.wifiStreamingQualitySetting = getQualitySettingsFromBitrate(bitRateSelection);
        if (this.initStatus == InitStatus.INITIALIZED) {
            this.player.setWifiAudioQualitySetting(this.wifiStreamingQualitySetting);
        }
    }

    public void setBitRateSelectionForOTA(BitRateSelection bitRateSelection) {
        this.otaStreamingQualitySetting = getQualitySettingsFromBitrate(bitRateSelection);
        if (this.initStatus == InitStatus.INITIALIZED) {
            this.player.setOTAAudioQualitySetting(this.otaStreamingQualitySetting);
        }
    }

    private QualitySetting getQualitySettingsFromBitrate(BitRateSelection bitRateSelection) {
        int i = AnonymousClass6.$SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[bitRateSelection.ordinal()];
        if (i == 1) {
            return QualitySetting.HD;
        }
        if (i == 2 || i == 3) {
            return QualitySetting.STANDARD;
        }
        if (i == 4) {
            return QualitySetting.SAVER;
        }
        return QualitySetting.BESTAVAILABLE;
    }

    public boolean threeDMode() {
        if (this.initStatus == InitStatus.INITIALIZED) {
            return this.player.threeDMode();
        }
        return false;
    }

    public void setThreeDMode(boolean z) {
        this.threeDMode = z;
        if (this.initStatus == InitStatus.INITIALIZED) {
            this.player.setThreeDMode(z);
        }
    }

    public void setPrioritizeDownloadsPlayback(boolean z) {
        this.prioritizeDownloads = z;
        if (this.initStatus == InitStatus.INITIALIZED) {
            this.player.setPrioritizeDownloadsPlayback(z);
        }
    }

    public void setLoudnessNormalizationEnabled(boolean z) {
        this.loudnessNormalizationEnabled = z;
        if (this.initStatus == InitStatus.INITIALIZED) {
            this.player.setLoudnessNormalizationMode(z);
        }
    }

    public boolean isLoudnessNormalizationEnabled() {
        return this.loudnessNormalizationEnabled;
    }

    public void play(String str) {
        logger.info("play() - requestQueue size: " + this.requestQueue.size() + ", status: " + this.status);
        if (str == null && this.status == Status.PAUSED) {
            this.player.setPaused(false);
        } else if (this.status == Status.UNSTARTED) {
            startPlayback();
        }
        if (str != null && this.initStatus == InitStatus.INITIALIZED) {
            this.player.resumeAndTerminateRemoteStream(str);
        }
        this.status = Status.PENDING;
    }

    public void pause() {
        Status status = this.status;
        Status status2 = Status.PAUSED;
        if (status != status2) {
            Status status3 = this.status;
            Status status4 = Status.UNSTARTED;
            if (status3 == status4) {
                return;
            }
            if (this.initStatus != InitStatus.INITIALIZED) {
                this.status = status4;
                return;
            }
            logger.info("setting status to PAUSED from pause()");
            this.status = status2;
            this.player.setPaused(true);
        }
    }

    public Status getStatus() {
        return this.status;
    }

    public List<REQUEST> getRequestQueue() {
        return this.unmodifiableRequestQueue;
    }

    private List<String> convertToUris(List<REQUEST> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<REQUEST> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(StringUtils.toString(this.callback.getUri(it.next()), (String) null));
        }
        return arrayList;
    }

    private ArrayList<String> filterUris(List<String> list) {
        ArrayList<String> arrayList = new ArrayList<>(list.size());
        for (String str : list) {
            if (!StringUtils.isEmpty(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private boolean isHarleySequenceId(int i) {
        return this.harleyRequest != null && this.harleySequenceId == i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public REQUEST getRequest(int i) {
        REQUEST request = this.recentlyPlayed.get(Integer.valueOf(i));
        if (request != null) {
            return request;
        }
        if (isHarleySequenceId(i)) {
            return this.harleyRequest;
        }
        return this.playRequestMap.get(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setHarleySequenceId(int i) {
        if (!isHarleySequenceId(i)) {
            REQUEST request = this.playRequestMap.get(i);
            this.harleyRequest = request;
            this.harleySequenceId = request == null ? -1 : i;
            PlaybackAttributes playbackAttributes = PlaybackAttributes.UNKNOWN;
            this.currentPlaybackAttributes = playbackAttributes;
            this.bestAvailableAttributes = playbackAttributes;
            this.downloaded = false;
            if (this.entryQueue.isEmpty()) {
                logger.debug("entryQueue was empty for trackId: " + i);
            }
            if (this.harleyRequest != null && !this.entryQueue.isEmpty()) {
                this.recentlyPlayed.put(Integer.valueOf(i), this.harleyRequest);
                int i2 = 0;
                while (!this.entryQueue.isEmpty() && this.entryQueue.get(0).getSequenceID() != i) {
                    SequenceEntry sequenceEntryRemove = this.entryQueue.remove(0);
                    if (sequenceEntryRemove != null) {
                        this.requestQueue.remove(0);
                        this.playRequestMap.remove(sequenceEntryRemove.getSequenceID());
                        i2++;
                    }
                }
                if (i2 > 0) {
                    this.callback.onTrackChanged();
                }
                addSoonToPlayToRecents();
            }
        }
        return this.harleyRequest != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHarleyCurrent() {
        if (this.requestQueue.size() == 0) {
            if (this.harleyRequest == null) {
                return true;
            }
        } else if (this.harleyRequest == this.requestQueue.get(0)) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFirstInQueue(int i) {
        return (this.entryQueue.isEmpty() || this.entryQueue.get(0) == null || this.entryQueue.get(0).getSequenceID() != i) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int indexOf(int i) {
        int size = this.entryQueue.size();
        for (int i2 = 0; i2 < size; i2++) {
            SequenceEntry sequenceEntry = this.entryQueue.get(i2);
            if (sequenceEntry != null && sequenceEntry.getSequenceID() == i) {
                return i2;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endSeek(long j) {
        if (this.seekPosition != this.desiredSeekPosition && !this.entryQueue.isEmpty() && this.entryQueue.get(0) != null) {
            this.seekPosition = this.desiredSeekPosition;
            this.seekEndTime = Clock.now() + AUTO_END_SEEK_MILLIS;
            this.player.seek(this.entryQueue.get(0), this.desiredSeekPosition);
            return;
        }
        this.seeking = false;
        this.seekEndTime = 0L;
        if (!this.harleyStalled && this.status == Status.PENDING) {
            this.status = Status.RENDERING;
            this.callback.onStatusChanged();
        }
        this.callback.onPositionChanged(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSeeking() {
        if (this.seeking && Clock.now() > this.seekEndTime) {
            endSeek(this.seekPosition);
        }
        return this.seeking;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDuration(int i) {
        REQUEST request;
        MediaItem mediaItem;
        if (this.initStatus == InitStatus.INITIALIZED && (mediaItem = this.callback.getMediaItem((request = getRequest(i)))) != null && mediaItem.getDurationMillis() <= 0) {
            long trackDuration = this.player.getTrackDuration();
            if (trackDuration > 0) {
                this.callback.onDurationChanged(request, trackDuration);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0016. Please report as an issue. */
    public PlaybackException createPlaybackException(int i, EngineError engineError) {
        PlaybackException.Recoverability recoverability;
        boolean z;
        PlaybackException.PlaybackError playbackError = getPlaybackError(engineError.getCode());
        boolean z2 = false;
        switch (AnonymousClass6.$SwitchMap$com$amazon$digitalmusicplayback$ErrorScope[engineError.getScope().ordinal()]) {
            case 1:
                recoverability = PlaybackException.Recoverability.CONTROLLER;
                z = z2;
                break;
            case 2:
                recoverability = PlaybackException.Recoverability.MEDIA_ITEM;
                z2 = true;
                z = z2;
                break;
            case 3:
                if (playbackError == PlaybackException.PlaybackError.DrmLicenseExpired) {
                    recoverability = PlaybackException.Recoverability.MEDIA_ITEM;
                    z = true;
                } else {
                    recoverability = PlaybackException.Recoverability.COLLECTION;
                    z2 = true;
                    z = z2;
                }
                break;
            case 4:
                recoverability = PlaybackException.Recoverability.COLLECTION;
                z = true;
                break;
            case 5:
                recoverability = PlaybackException.Recoverability.COLLECTION;
                z = true;
                break;
            case 6:
                StrictMode.crashIfStrict("trackInitFailed(" + i + ", " + engineError + ")");
                recoverability = PlaybackException.Recoverability.MEDIA_ITEM;
                z2 = true;
                z = z2;
                break;
            default:
                recoverability = PlaybackException.Recoverability.MEDIA_ITEM;
                z2 = true;
                z = z2;
                break;
        }
        PlaybackException.Config configMetricsName = new PlaybackException.Config(recoverability).autoRetry(z2).description("trackInitFailed: " + engineError).playbackError(playbackError).metricsName(engineError.getCode() + "-" + engineError.getScope());
        if (z) {
            configMetricsName.mediaItem(this.callback.getMediaItem(getRequest(i)));
        }
        int i2 = AnonymousClass6.$SwitchMap$com$amazon$digitalmusicplayback$ErrorCode[engineError.getCode().ordinal()];
        if (i2 == 1 || i2 == 2) {
            return new NetworkUnavailableException(configMetricsName);
        }
        return new PlaybackException(configMetricsName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHarleyStalled(boolean z) {
        this.harleyStalled = z;
        this.actualPosition.setRunSpeed((z || this.status == Status.PAUSED || this.status == Status.UNSTARTED) ? 0.0f : 1.0f);
    }

    @Nullable
    public FrequencySettings getBassSettings() {
        if (this.initStatus == InitStatus.INITIALIZED) {
            return this.player.getBassSettings();
        }
        return null;
    }

    public void setBassGainMillibels(int i) {
        this.bassGainMillibels = i;
        if (this.initStatus == InitStatus.INITIALIZED) {
            this.player.updateBassSettings(convertIntToShort(i));
        }
    }

    @Nullable
    public FrequencySettings getTrebleSettings() {
        if (this.initStatus == InitStatus.INITIALIZED) {
            return this.player.getTrebleSettings();
        }
        return null;
    }

    public void setTrebleGainMillibels(int i) {
        this.trebleGainMillibels = i;
        if (this.initStatus == InitStatus.INITIALIZED) {
            this.player.updateTrebleSettings(convertIntToShort(i));
        }
    }

    @Nullable
    public EqualizerSettings getEqualizerSettings() {
        if (this.initStatus == InitStatus.INITIALIZED) {
            return this.player.getEqualizerSettings();
        }
        return null;
    }

    public void setEqualizerBandGain(int i, int i2) {
        if (i >= 0) {
            int[] iArr = this.bandGainCache;
            if (i < iArr.length) {
                iArr[i] = i2;
                if (this.initStatus == InitStatus.INITIALIZED) {
                    ArrayList<EqualizerUpdate> arrayList = new ArrayList<>(1);
                    arrayList.add(new EqualizerUpdate(convertIntToShort(i2), this.player.getEqualizerSettings().getEqualizerBandList().get(i).getBandIndex()));
                    this.player.updateEqualizerSettings(arrayList);
                }
            }
        }
    }

    int getEqualizerBandCount() {
        return AnonymousClass6.$SwitchMap$com$amazon$digitalmusicplayback$EqualizerFormat[EQUALIZER_FORMAT.ordinal()] != 1 ? 0 : 5;
    }

    private class AuthListener extends AuthenticationListener {
        private AuthListener() {
        }

        @Override // com.amazon.digitalmusicplayback.AuthenticationListener
        public String getToken() {
            if (HarleyWrapper.this.playerAuthentication == null) {
                return null;
            }
            return HarleyWrapper.this.playerAuthentication.getAdpToken();
        }

        @Override // com.amazon.digitalmusicplayback.AuthenticationListener
        public String getSigningKeyForPlayer() {
            if (HarleyWrapper.this.playerAuthentication == null) {
                return null;
            }
            return HarleyWrapper.this.playerAuthentication.getSigningKey();
        }
    }

    private class LogListener extends LoggingListener {
        private LogListener() {
        }

        @Override // com.amazon.digitalmusicplayback.LoggingListener
        public void logMessage(long j, PlayerLogLevel playerLogLevel, String str) {
            switch (AnonymousClass6.$SwitchMap$com$amazon$digitalmusicplayback$PlayerLogLevel[playerLogLevel.ordinal()]) {
                case 1:
                    HarleyWrapper.nativeLogger.trace(str);
                    break;
                case 2:
                    HarleyWrapper.nativeLogger.debug(str);
                    break;
                case 3:
                    HarleyWrapper.nativeLogger.info(str);
                    break;
                case 4:
                    HarleyWrapper.nativeLogger.warn(str);
                    break;
                case 5:
                case 6:
                    HarleyWrapper.nativeLogger.error(str);
                    break;
                default:
                    if (HarleyWrapper.this.debugHarley) {
                        HarleyWrapper.nativeLogger.info(str);
                    } else {
                        HarleyWrapper.nativeLogger.debug(str);
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.player.impl.HarleyWrapper$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$digitalmusicplayback$EqualizerFormat;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$digitalmusicplayback$ErrorCode;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$digitalmusicplayback$ErrorScope;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$digitalmusicplayback$PlayerLogLevel;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$digitalmusicplayback$QualitySetting;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$player$impl$HarleyWrapper$InitStatus;

        static {
            int[] iArr = new int[PlayerLogLevel.values().length];
            $SwitchMap$com$amazon$digitalmusicplayback$PlayerLogLevel = iArr;
            try {
                iArr[PlayerLogLevel.TRACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerLogLevel[PlayerLogLevel.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerLogLevel[PlayerLogLevel.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerLogLevel[PlayerLogLevel.WARNING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerLogLevel[PlayerLogLevel.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerLogLevel[PlayerLogLevel.FATAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$PlayerLogLevel[PlayerLogLevel.DEFAULT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[EqualizerFormat.values().length];
            $SwitchMap$com$amazon$digitalmusicplayback$EqualizerFormat = iArr2;
            try {
                iArr2[EqualizerFormat.BANDSIZE5.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr3 = new int[ErrorCode.values().length];
            $SwitchMap$com$amazon$digitalmusicplayback$ErrorCode = iArr3;
            try {
                iArr3[ErrorCode.NETWORKUNAVAILABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$ErrorCode[ErrorCode.NETWORKREQUESTFAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            int[] iArr4 = new int[ErrorScope.values().length];
            $SwitchMap$com$amazon$digitalmusicplayback$ErrorScope = iArr4;
            try {
                iArr4[ErrorScope.SYSTEMCATASTROPHIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$ErrorScope[ErrorScope.SYSTEMTEMPORARY.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$ErrorScope[ErrorScope.TRACK.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$ErrorScope[ErrorScope.STREAMING.ordinal()] = 4;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$ErrorScope[ErrorScope.STREAMINGCATEGORY.ordinal()] = 5;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$ErrorScope[ErrorScope.ACTION.ordinal()] = 6;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$ErrorScope[ErrorScope.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused17) {
            }
            int[] iArr5 = new int[QualitySetting.values().length];
            $SwitchMap$com$amazon$digitalmusicplayback$QualitySetting = iArr5;
            try {
                iArr5[QualitySetting.HD.ordinal()] = 1;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$QualitySetting[QualitySetting.STANDARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$QualitySetting[QualitySetting.SAVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$QualitySetting[QualitySetting.BESTAVAILABLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused21) {
            }
            int[] iArr6 = new int[BitRateSelection.values().length];
            $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection = iArr6;
            try {
                iArr6[BitRateSelection.KATANA.ordinal()] = 1;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.HIGH.ordinal()] = 2;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.MEDIUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.LOW.ordinal()] = 4;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.AUTO.ordinal()] = 5;
            } catch (NoSuchFieldError unused26) {
            }
            int[] iArr7 = new int[InitStatus.values().length];
            $SwitchMap$com$amazon$music$media$playback$player$impl$HarleyWrapper$InitStatus = iArr7;
            try {
                iArr7[InitStatus.INITIALIZING.ordinal()] = 1;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$impl$HarleyWrapper$InitStatus[InitStatus.INITIALIZING_WITH_WRONG_VALUES.ordinal()] = 2;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$player$impl$HarleyWrapper$InitStatus[InitStatus.INITIALIZED.ordinal()] = 3;
            } catch (NoSuchFieldError unused29) {
            }
        }
    }

    private class MtrxListener extends MetricsListener {
        private MtrxListener() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long getTrackProgressInMilliseconds(MTSAttributes mTSAttributes) {
            if (mTSAttributes.getTrackProgress() == null) {
                return 0L;
            }
            return TimeUnit.SECONDS.toMillis(mTSAttributes.getTrackProgress().longValue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        private boolean reportMetricsForPodcasts(int i) {
            return HarleyWrapper.this.callback.getMediaItem(HarleyWrapper.this.getRequest(i)).getType() == MediaItem.Type.PODCAST_CONTENT;
        }

        @Override // com.amazon.digitalmusicplayback.MetricsListener
        public void streamingInitiated(final int i, final MTSAttributes mTSAttributes) {
            if (!reportMetricsForPodcasts(i) || HarleyWrapper.this.playbackMetricsListener == null) {
                HarleyWrapper.logger.debug("streamingInitiated callback from Harley");
                ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.MtrxListener.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.lang.Runnable
                    public void run() {
                        Object request = HarleyWrapper.this.getRequest(i);
                        if (request != null) {
                            HarleyWrapper.logger.debug("streamingInitiated(" + request + ", " + mTSAttributes + ")");
                            HarleyWrapper.this.callback.onStreamingInitiated(request, mTSAttributes);
                            return;
                        }
                        StrictMode.crashIfStrict("Unrecognized ID in streamingInitiated(" + i + ", " + mTSAttributes + ")");
                    }
                });
            } else {
                HarleyWrapper.this.playbackMetricsListener.streamingInitiated(i, mTSAttributes);
            }
        }

        private void handleTrackFinished(final int i, final MTSAttributes mTSAttributes, final boolean z) {
            if (reportMetricsForPodcasts(i) && HarleyWrapper.this.playbackMetricsListener != null) {
                HarleyWrapper.this.playbackMetricsListener.onTrackFinished(i, mTSAttributes);
            } else {
                ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.MtrxListener.2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.lang.Runnable
                    public void run() {
                        Object request = HarleyWrapper.this.getRequest(i);
                        long trackProgressInMilliseconds = MtrxListener.this.getTrackProgressInMilliseconds(mTSAttributes);
                        if (HarleyWrapper.this.ignoreTrackStreamed || trackProgressInMilliseconds < HarleyWrapper.MIN_TRACK_STREAMED_MILLIS) {
                            HarleyWrapper.logger.debug("ignoring handleTrackFinished(" + request + ", " + mTSAttributes + ", " + z + "), trackProgress: " + trackProgressInMilliseconds);
                            return;
                        }
                        if (request != null) {
                            HarleyWrapper.logger.debug("handleTrackFinished(" + request + ", " + mTSAttributes + ", " + z + "), trackProgress: " + trackProgressInMilliseconds);
                            HarleyWrapper.this.callback.onTrackFinished(request, trackProgressInMilliseconds, mTSAttributes, z);
                            return;
                        }
                        StrictMode.crashIfStrict("Unrecognized ID in handleTrackFinished(" + i + ", " + mTSAttributes + ", " + z + "), trackProgress: " + trackProgressInMilliseconds);
                    }
                });
            }
        }

        @Override // com.amazon.digitalmusicplayback.MetricsListener
        public void localPlaybackFinished(int i, MTSAttributes mTSAttributes) {
            handleTrackFinished(i, mTSAttributes, false);
        }

        @Override // com.amazon.digitalmusicplayback.MetricsListener
        public void trackStreamed(int i, MTSAttributes mTSAttributes) {
            handleTrackFinished(i, mTSAttributes, true);
        }

        @Override // com.amazon.digitalmusicplayback.MetricsListener
        public void trackPlayedLocally(int i, MTSAttributes mTSAttributes) {
            handleTrackFinished(i, mTSAttributes, true);
        }

        @Override // com.amazon.digitalmusicplayback.MetricsListener
        public void streamingInitFailed(final int i, final MTSAttributes mTSAttributes) {
            if (reportMetricsForPodcasts(i) && HarleyWrapper.this.playbackMetricsListener != null) {
                HarleyWrapper.this.playbackMetricsListener.streamingInitFailed(i, mTSAttributes);
            } else {
                ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.MtrxListener.3
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.lang.Runnable
                    public void run() {
                        Object request = HarleyWrapper.this.getRequest(i);
                        if (request != null) {
                            HarleyWrapper.logger.debug("streamingInitFailed(" + request + ", " + mTSAttributes + ")");
                            HarleyWrapper.this.callback.onStreamingInitiationFailed(request, mTSAttributes);
                            return;
                        }
                        StrictMode.crashIfStrict("Unrecognized ID in streamingInitFailed(" + i + ", " + mTSAttributes + ")");
                    }
                });
            }
        }

        @Override // com.amazon.digitalmusicplayback.MetricsListener
        public void playbackTerminated(final int i, final MTSAttributes mTSAttributes) {
            if (reportMetricsForPodcasts(i) && HarleyWrapper.this.playbackMetricsListener != null) {
                HarleyWrapper.this.playbackMetricsListener.playbackTerminated(i, mTSAttributes);
                return;
            }
            if (TerminationReason.TRACK_SCRUB.getMetricsValue().equals(mTSAttributes.getTerminationReason())) {
                long j = HarleyWrapper.this.scrubbingTimestamp;
                HarleyWrapper.this.scrubbingTimestamp = mTSAttributes.getTimestamp();
                if ((HarleyWrapper.this.scrubbingTimestamp - j) - mTSAttributes.getInitialPlaybackDelay() < HarleyWrapper.MIN_TIME_FOR_SCRUBBING_TERMINATION_MILLIS) {
                    return;
                }
            }
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.MtrxListener.4
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    Object request = HarleyWrapper.this.getRequest(i);
                    if (request != null) {
                        HarleyWrapper.logger.debug("playbackTerminated(" + request + ", " + mTSAttributes + ")");
                        HarleyWrapper.this.callback.onPlaybackTerminated(request, MtrxListener.this.getTrackProgressInMilliseconds(mTSAttributes), mTSAttributes);
                        return;
                    }
                    StrictMode.crashIfStrict("Unrecognized ID in playbackTerminated(" + i + ", " + mTSAttributes + ")");
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.MetricsListener
        public void streamingRequiredRebuffering(final int i, final float f, final MTSAttributes mTSAttributes) {
            if ((mTSAttributes.getTimestamp() - HarleyWrapper.this.scrubbingTimestamp) - mTSAttributes.getDurationMS().longValue() < HarleyWrapper.MIN_TIME_FOR_SCRUBBING_REBUFFER_MILLIS) {
                return;
            }
            if (reportMetricsForPodcasts(i) && HarleyWrapper.this.playbackMetricsListener != null) {
                HarleyWrapper.this.playbackMetricsListener.streamingRequiredRebuffering(i, f, mTSAttributes);
            } else {
                ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.MtrxListener.5
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.lang.Runnable
                    public void run() {
                        Object request = HarleyWrapper.this.getRequest(i);
                        if (request != null) {
                            HarleyWrapper.logger.debug("streamingRequiredRebuffering(" + request + ", " + f + ", " + mTSAttributes + ")");
                            HarleyWrapper.this.callback.onStreamingRequiredRebuffering(request, f, mTSAttributes);
                            return;
                        }
                        StrictMode.crashIfStrict("Unrecognized ID in streamingRequiredRebuffering(" + i + ", " + f + ", " + mTSAttributes + ")");
                    }
                });
            }
        }
    }

    private class PBListener extends PlaybackListener {
        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void bassSettingsChanged(FrequencySettings frequencySettings, boolean z) {
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void enable24BitPlaybackChanged(boolean z, boolean z2, EngineError engineError) {
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void equalizerSettingsChanged(EqualizerSettings equalizerSettings, boolean z) {
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void outputDeviceAttributesChanged(OutputDeviceAttributes outputDeviceAttributes) {
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void playbackMuted(boolean z) {
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void playbackVolumeChanged(double d) {
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void prioritizeDownloadsChanged(boolean z, EngineError engineError) {
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void tempoChanged(double d, boolean z) {
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void trebleSettingsChanged(FrequencySettings frequencySettings, boolean z) {
        }

        private PBListener() {
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void startedPlayback(final int i) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.1
                @Override // java.lang.Runnable
                public void run() {
                    PBListener pBListener = HarleyWrapper.this.playbackListener;
                    PBListener pBListener2 = PBListener.this;
                    if (pBListener != pBListener2) {
                        return;
                    }
                    HarleyWrapper.this.setHarleySequenceId(i);
                    HarleyWrapper.this.callback.playbackAttributesChanged();
                    HarleyWrapper.this.setHarleyStalled(false);
                    if (HarleyWrapper.this.isHarleyCurrent() && HarleyWrapper.this.getStatus() == Status.PENDING && !HarleyWrapper.this.isSeeking()) {
                        HarleyWrapper.this.status = Status.RENDERING;
                        HarleyWrapper.this.callback.onStatusChanged();
                    }
                    HarleyWrapper.this.updateDuration(i);
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void playbackPaused(boolean z) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.2
                @Override // java.lang.Runnable
                public void run() {
                    PBListener pBListener = HarleyWrapper.this.playbackListener;
                    PBListener pBListener2 = PBListener.this;
                    if (pBListener != pBListener2) {
                        return;
                    }
                    HarleyWrapper.this.actualPosition.setRunSpeed(HarleyWrapper.this.harleyStalled ? 0.0f : 1.0f);
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void skippedToNextTrack(final int i, int i2) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.3
                @Override // java.lang.Runnable
                public void run() {
                    PBListener pBListener = HarleyWrapper.this.playbackListener;
                    PBListener pBListener2 = PBListener.this;
                    if (pBListener != pBListener2) {
                        return;
                    }
                    HarleyWrapper.this.actualPosition.setPosition(0L);
                    HarleyWrapper.this.setHarleySequenceId(i);
                    HarleyWrapper.this.callback.playbackAttributesChanged();
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void audioAttributesChanged(final AudioAttributes audioAttributes) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.4
                @Override // java.lang.Runnable
                public void run() {
                    if (HarleyWrapper.this.playbackListener != PBListener.this) {
                        return;
                    }
                    HarleyWrapper.logger.info("audio attributes changed to " + audioAttributes);
                    HarleyWrapper.this.currentPlaybackAttributes = DomainModelTranslator.translateCurrrentPlaybackAttributes(audioAttributes);
                    HarleyWrapper.this.bestAvailableAttributes = DomainModelTranslator.translateBestAvailablePlaybackAttributes(audioAttributes);
                    HarleyWrapper.this.currentPlayerCapabilities = DomainModelTranslator.translate(audioAttributes.getDeviceCapability());
                    HarleyWrapper.this.downloaded = audioAttributes.getIsDownloaded();
                    HarleyWrapper.this.callback.playbackAttributesChanged();
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void frameReady(int i, int i2, AudioFormat audioFormat, ArrayList<ByteBuffer> arrayList, int i3) {
            if (HarleyWrapper.this.frameChangedListener != null) {
                HarleyWrapper.this.frameChangedListener.frameReady(i, i2, audioFormat, arrayList);
            }
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void finishedPlayback(final int i) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.5
                @Override // java.lang.Runnable
                public void run() {
                    PBListener pBListener = HarleyWrapper.this.playbackListener;
                    PBListener pBListener2 = PBListener.this;
                    if (pBListener != pBListener2) {
                        return;
                    }
                    HarleyWrapper.this.setHarleyStalled(true);
                    boolean zIsFirstInQueue = HarleyWrapper.this.isFirstInQueue(i);
                    HarleyWrapper.this.setHarleySequenceId(-1);
                    if (zIsFirstInQueue) {
                        HarleyWrapper.this.entryQueue.clear();
                        HarleyWrapper.this.requestQueue.clear();
                        HarleyWrapper.this.status = Status.UNSTARTED;
                        HarleyWrapper.this.seeking = false;
                        HarleyWrapper.this.seekEndTime = 0L;
                        HarleyWrapper.this.callback.onTrackChanged();
                        HarleyWrapper.this.callback.onStatusChanged();
                    }
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void playbackStalled(final boolean z, final int i) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.6
                /* JADX WARN: Removed duplicated region for block: B:20:0x0059  */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void run() {
                    /*
                        r6 = this;
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.access$3200(r0)
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.this
                        if (r0 == r1) goto Ld
                        return
                    Ld:
                        com.amazon.music.media.playback.player.impl.HarleyWrapper r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.this
                        boolean r1 = r2
                        com.amazon.music.media.playback.player.impl.HarleyWrapper.access$3400(r0, r1)
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.this
                        int r1 = r3
                        int r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.access$5000(r0, r1)
                        if (r0 >= 0) goto L21
                        return
                    L21:
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$Status r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.access$1700(r1)
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$Status r2 = com.amazon.music.media.playback.player.impl.HarleyWrapper.Status.PAUSED
                        r3 = 1
                        r4 = 0
                        if (r1 == r2) goto L59
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$Status r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.access$1700(r1)
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$Status r2 = com.amazon.music.media.playback.player.impl.HarleyWrapper.Status.UNSTARTED
                        if (r1 == r2) goto L59
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.this
                        boolean r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.access$3600(r1)
                        if (r1 != 0) goto L59
                        boolean r1 = r2
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r2 = com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper r2 = com.amazon.music.media.playback.player.impl.HarleyWrapper.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$Status r2 = com.amazon.music.media.playback.player.impl.HarleyWrapper.access$1700(r2)
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$Status r5 = com.amazon.music.media.playback.player.impl.HarleyWrapper.Status.PENDING
                        if (r2 != r5) goto L55
                        r2 = r3
                        goto L56
                    L55:
                        r2 = r4
                    L56:
                        if (r1 == r2) goto L59
                        goto L5a
                    L59:
                        r3 = r4
                    L5a:
                        if (r3 == 0) goto L92
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.this
                        boolean r2 = r2
                        if (r2 == 0) goto L67
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$Status r2 = com.amazon.music.media.playback.player.impl.HarleyWrapper.Status.PENDING
                        goto L69
                    L67:
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$Status r2 = com.amazon.music.media.playback.player.impl.HarleyWrapper.Status.RENDERING
                    L69:
                        com.amazon.music.media.playback.player.impl.HarleyWrapper.access$1702(r1, r2)
                        org.slf4j.Logger r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.access$1200()
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder
                        r2.<init>()
                        java.lang.String r4 = "setting status to "
                        r2.append(r4)
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r4 = com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper r4 = com.amazon.music.media.playback.player.impl.HarleyWrapper.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$Status r4 = com.amazon.music.media.playback.player.impl.HarleyWrapper.access$1700(r4)
                        r2.append(r4)
                        java.lang.String r4 = " from playbackStalled()"
                        r2.append(r4)
                        java.lang.String r2 = r2.toString()
                        r1.info(r2)
                    L92:
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper r1 = com.amazon.music.media.playback.player.impl.HarleyWrapper.this
                        int r2 = r3
                        com.amazon.music.media.playback.player.impl.HarleyWrapper.access$3300(r1, r2)
                        if (r0 <= 0) goto La8
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$HarleyCallback r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.access$1300(r0)
                        r0.onTrackChanged()
                    La8:
                        if (r3 == 0) goto Lb5
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$PBListener r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.this
                        com.amazon.music.media.playback.player.impl.HarleyWrapper$HarleyCallback r0 = com.amazon.music.media.playback.player.impl.HarleyWrapper.access$1300(r0)
                        r0.onStatusChanged()
                    Lb5:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.AnonymousClass6.run():void");
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void trackInitFailed(final int i, final EngineError engineError) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.7
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    if (HarleyWrapper.this.playbackListener != PBListener.this) {
                        return;
                    }
                    HarleyWrapper.logger.warn("trackInitFailed(" + i + ", " + engineError + ")");
                    Object request = HarleyWrapper.this.getRequest(i);
                    if (request != null) {
                        if (request == HarleyWrapper.this.getNextRequest() || request == HarleyWrapper.this.getCurrentRequest()) {
                            if (HarleyWrapper.this.indexOf(i) > 0) {
                                HarleyWrapper.this.setHarleySequenceId(i);
                                HarleyWrapper.this.callback.onTrackChanged();
                            }
                            HarleyWrapper.this.playRequestMap.clear();
                            HarleyWrapper.this.entryQueue.clear();
                            HarleyWrapper.this.requestQueue.clear();
                            HarleyWrapper.this.status = Status.UNSTARTED;
                            HarleyWrapper.this.seeking = false;
                            HarleyWrapper.this.seekEndTime = 0L;
                            HarleyWrapper.this.actualPosition.pause(0L);
                            HarleyWrapper.this.callback.onError(request, HarleyWrapper.this.createPlaybackException(i, engineError));
                        }
                    }
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void seekFinished(final long j) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.8
                @Override // java.lang.Runnable
                public void run() {
                    PBListener pBListener = HarleyWrapper.this.playbackListener;
                    PBListener pBListener2 = PBListener.this;
                    if (pBListener != pBListener2) {
                        return;
                    }
                    HarleyWrapper.this.actualPosition.setPosition(j);
                    if (HarleyWrapper.this.isHarleyCurrent() && HarleyWrapper.this.seeking) {
                        HarleyWrapper.this.endSeek(j);
                    }
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void seekFailed(EngineError engineError) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.9
                @Override // java.lang.Runnable
                public void run() {
                    PBListener pBListener = HarleyWrapper.this.playbackListener;
                    PBListener pBListener2 = PBListener.this;
                    if (pBListener == pBListener2 && HarleyWrapper.this.seeking) {
                        HarleyWrapper harleyWrapper = HarleyWrapper.this;
                        harleyWrapper.endSeek(harleyWrapper.actualPosition.getCurrentPosition());
                    }
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void liveStreamUpdated(long j) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.10
                @Override // java.lang.Runnable
                public void run() {
                    Long liveStreamCurrentTime;
                    PBListener pBListener = HarleyWrapper.this.playbackListener;
                    PBListener pBListener2 = PBListener.this;
                    if (pBListener == pBListener2 && HarleyWrapper.this.harleyRequest != null && HarleyWrapper.this.isHarleyCurrent() && (liveStreamCurrentTime = HarleyWrapper.this.player.getLiveStreamCurrentTime()) != null) {
                        long jLongValue = liveStreamCurrentTime.longValue();
                        HarleyWrapper.this.actualPosition.setPosition(jLongValue);
                        HarleyWrapper.this.callback.onPositionChanged(jLongValue);
                    }
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void trackPlaybackFailed(final int i, final EngineError engineError) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.11
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    if (HarleyWrapper.this.playbackListener != PBListener.this) {
                        return;
                    }
                    HarleyWrapper.logger.warn("trackPlaybackFailed(" + i + ", " + engineError + ")");
                    Object request = HarleyWrapper.this.getRequest(i);
                    if (request != null) {
                        if (request == HarleyWrapper.this.getNextRequest() || request == HarleyWrapper.this.getCurrentRequest()) {
                            HarleyWrapper.this.playRequestMap.clear();
                            HarleyWrapper.this.entryQueue.clear();
                            HarleyWrapper.this.requestQueue.clear();
                            HarleyWrapper.this.status = Status.UNSTARTED;
                            HarleyWrapper.this.seeking = false;
                            HarleyWrapper.this.seekEndTime = 0L;
                            HarleyWrapper.this.actualPosition.setRunSpeed(0.0f);
                            HarleyWrapper.this.callback.onError(request, HarleyWrapper.this.createPlaybackException(i, engineError));
                        }
                    }
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void trackProgressed(final int i, final long j) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.12
                @Override // java.lang.Runnable
                public void run() {
                    PBListener pBListener = HarleyWrapper.this.playbackListener;
                    PBListener pBListener2 = PBListener.this;
                    if (pBListener != pBListener2) {
                        return;
                    }
                    if (HarleyWrapper.this.isFirstInQueue(i)) {
                        HarleyWrapper.this.actualPosition.setPosition(j);
                        if (!HarleyWrapper.this.isSeeking()) {
                            HarleyWrapper.this.callback.onPositionChanged(j);
                        }
                    }
                    HarleyWrapper.this.updateDuration(i);
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.PlaybackListener
        public void prebufferChanged(final int i, final long j, final long j2) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.PBListener.13
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    PBListener pBListener = HarleyWrapper.this.playbackListener;
                    PBListener pBListener2 = PBListener.this;
                    if (pBListener != pBListener2) {
                        return;
                    }
                    if (HarleyWrapper.this.prebufferChangedListener != null) {
                        HarleyWrapper.this.prebufferChangedListener.prebufferChanged(i, j, j2);
                    }
                    Object obj = HarleyWrapper.this.playRequestMap.get(i);
                    if (obj != null) {
                        HarleyWrapper.this.callback.onAmountBufferedChanged(obj, j2);
                    }
                }
            });
        }
    }

    private class ConcurrencyListener extends com.amazon.digitalmusicplayback.ConcurrencyListener {
        private ConcurrencyListener() {
        }

        @Override // com.amazon.digitalmusicplayback.ConcurrencyListener
        public void inactivityThresholdReceived(final long j) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.ConcurrencyListener.1
                @Override // java.lang.Runnable
                public void run() {
                    HarleyWrapper.this.callback.onInactivityThresholdSecondsReceived(j);
                }
            });
        }

        @Override // com.amazon.digitalmusicplayback.ConcurrencyListener
        public void maximumConcurrencyReached(int i, final ArrayList<StreamingStatusResponse> arrayList) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.player.impl.HarleyWrapper.ConcurrencyListener.2
                @Override // java.lang.Runnable
                public void run() {
                    if (HarleyWrapper.this.status != Status.UNSTARTED) {
                        Status status = HarleyWrapper.this.status;
                        Status status2 = Status.PAUSED;
                        if (status != status2) {
                            HarleyWrapper.this.status = status2;
                            HarleyWrapper.this.callback.onMaximumConcurrencyReached(arrayList);
                        }
                    }
                }
            });
        }
    }

    private class WeblabClient extends com.amazon.digitalmusicplayback.WeblabClient {
        @Override // com.amazon.digitalmusicplayback.WeblabClient
        public void declareWeblabs(ArrayList<Weblab> arrayList) {
        }

        private WeblabClient() {
        }

        @Override // com.amazon.digitalmusicplayback.WeblabClient
        public String getTreatmentAndRecordTrigger(String str, boolean z) {
            try {
                WeblabProvider weblabProvider = (WeblabProvider) Providers.INSTANCE.get(WeblabProvider.class);
                if (weblabProvider == null) {
                    throw new Exception("Weblab provider found to be null!");
                }
                return weblabProvider.getTreatment(str, z).toString();
            } catch (Exception e) {
                HarleyWrapper.logger.error("getTreatmentAndRecordTrigger(" + str + ") failed: " + e.toString());
                return "";
            }
        }
    }

    private class MetricsAttributesProvider extends com.amazon.digitalmusicplayback.MetricsAttributesProvider {
        private MetricsAttributesProvider() {
        }

        @Override // com.amazon.digitalmusicplayback.MetricsAttributesProvider
        public String getMusicRequestIdentityContextToken() {
            return HarleyWrapper.this.mricToken;
        }
    }

    public void nativeCrash() {
        this.player.nativeCrash();
    }
}
