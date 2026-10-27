package com.amazon.avod.dash;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.DownloadConfig;
import com.amazon.avod.content.dash.quality.heuristic.DownloadStatus;
import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicAlgorithm;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackState;
import com.amazon.avod.content.dash.quality.heuristic.StreamBehavior;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.urlvending.QoeConfigInterface;
import com.amazon.avod.dash.config.miyagi.HeuristicsConfig;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.capability.DeviceResources;
import com.amazon.avod.smoothstream.dash.Mp4FragmentJni;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.nio.ByteBuffer;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class DashHeuristicsJni implements Heuristics {
    private static String EXPECTED_LIBRARY_VERSION = "1.10";
    private static int INVALID_CONSUMPTION_HEAD = -1;
    private static int INVALID_QUALITY_INDEX = -1;
    private final Object mCallbackSyncObject;
    private HeuristicsCallbacks mCallbacks;
    private final HeuristicsConfig mConfig;
    private final DeviceResources mDeviceResources;
    private final boolean mEnableDualRequest;
    private boolean mEngineInitialized;
    private HeuristicAlgorithm mHeuristicAlgorithm;
    private final MobileWeblab mHeuristicCallbackChangeWeblab;
    private long mHeuristicsHandle;
    private final boolean mIsHeuristicsCallbackChangeWebLabEnabled;
    private boolean mLibraryInitialized;
    private final PlaybackNativeLibrariesLoader mLibraryLoader;
    private int mNumStreams;
    private final Object mSyncObject;
    private StreamBehavior mVideoStreamBehavior;

    private native void bufferStateChanged(long j) throws ContentException;

    private native long createHeuristics(int i, @Nonnull HeuristicsConfig heuristicsConfig, long j, int i2, @Nullable ByteBuffer byteBuffer, int i3) throws ContentException;

    private native void downloadPaused(long j) throws ContentException;

    private native void downloadResumed(long j) throws ContentException;

    private native long getBandwidthAverage(long j) throws ContentException;

    private native long getBandwidthStandardDeviation(long j) throws ContentException;

    private native ByteBuffer getHeuristicsState(long j) throws ContentException;

    private native long getLatencyAverage(long j) throws ContentException;

    private native long getLatencyStandardDeviation(long j) throws ContentException;

    private native String getNativeCodeCompileTime();

    private native String getNativeLibraryVersion();

    private native float getVersionInfo(long j) throws ContentException;

    private native void onPlaybackStateChange(long j, int i) throws ContentException;

    private native void pauseDashHeuristics(long j, int i) throws ContentException;

    private native void release(long j) throws ContentException;

    private native void reportDownloadProgress(long j, int i, long j2, long j3, long j4, int i2, long j5) throws ContentException;

    private native void resumeDashHeuristics(long j, int i) throws ContentException;

    private native void setDashStreamHandle(long j, int i, long j2) throws ContentException;

    private native void setDashStreamIndex(long j, int i, int i2) throws ContentException;

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public long getBandwidthPredictionErrorAvgBytesPerSecond() {
        return 0L;
    }

    public DashHeuristicsJni(@Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull HeuristicsConfig heuristicsConfig) {
        this(playbackNativeLibrariesLoader, heuristicsConfig, DeviceResources.getInstance(), PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_HEURISTICS_CALLBACKS_CHANGE_255240"), LiveStreamingPlaybackConfig.getInstance().getEnableLiveStreamingDualRequest().booleanValue(), LiveStreamingPlaybackConfig.getInstance().isHeuristicsCallbackChangeWebLabEnabled());
    }

    DashHeuristicsJni(@Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull HeuristicsConfig heuristicsConfig, @Nonnull DeviceResources deviceResources, @Nullable MobileWeblab mobileWeblab, boolean z, boolean z2) {
        this.mHeuristicsHandle = 0L;
        this.mLibraryInitialized = false;
        this.mEngineInitialized = false;
        this.mVideoStreamBehavior = StreamBehavior.DEFAULT;
        this.mSyncObject = new Object();
        this.mCallbackSyncObject = new Object();
        this.mLibraryLoader = (PlaybackNativeLibrariesLoader) Preconditions.checkNotNull(playbackNativeLibrariesLoader, "nativeLibrariesLoader");
        this.mConfig = (HeuristicsConfig) Preconditions.checkNotNull(heuristicsConfig, "config");
        this.mDeviceResources = (DeviceResources) Preconditions.checkNotNull(deviceResources, "deviceResources");
        this.mHeuristicCallbackChangeWeblab = mobileWeblab;
        this.mEnableDualRequest = z;
        this.mIsHeuristicsCallbackChangeWebLabEnabled = z2;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void initialize(@Nonnull HeuristicsCallbacks heuristicsCallbacks, ContentSessionContext contentSessionContext) throws ContentException {
        synchronized (this.mSyncObject) {
            Preconditions.checkState(!this.mEngineInitialized, "The engine instance has been already initialized. Need to release the previous instance.");
            if (!this.mLibraryInitialized) {
                initializeLibrary();
            }
            Manifest manifest = contentSessionContext.getManifest();
            this.mNumStreams = manifest.getNumStreams();
            ByteBuffer heuristicsState = contentSessionContext.getHeuristicsState();
            int iCapacity = heuristicsState != null ? heuristicsState.capacity() : 0;
            this.mCallbacks = heuristicsCallbacks;
            ByteBuffer directByteBuffer = Mp4FragmentJni.getDirectByteBuffer(heuristicsState);
            selectHeuristicAlgorithm(contentSessionContext);
            updateHeuristicInfo(contentSessionContext);
            this.mHeuristicsHandle = createHeuristicsEngine(this.mHeuristicAlgorithm.ordinal(), this.mConfig, manifest.getManifestHandle(), directByteBuffer, iCapacity);
            this.mEngineInitialized = true;
        }
    }

    long createHeuristicsEngine(int i, @Nonnull HeuristicsConfig heuristicsConfig, long j, @Nonnull ByteBuffer byteBuffer, int i2) throws ContentException {
        MobileWeblab mobileWeblab;
        if (this.mIsHeuristicsCallbackChangeWebLabEnabled && (mobileWeblab = this.mHeuristicCallbackChangeWeblab) != null && mobileWeblab.getCurrentTreatment() == WeblabTreatment.T1) {
            DLog.logf("DashHeuristicsJni: Sending Null manifestHandle - WeblabTreatment %s", this.mHeuristicCallbackChangeWeblab.getCurrentTreatment());
            return createHeuristics(i, heuristicsConfig, 0L, this.mNumStreams, byteBuffer, i2);
        }
        DLog.logf("DashHeuristicsJni: Sending valid manifestHandle");
        return createHeuristics(i, heuristicsConfig, j, this.mNumStreams, byteBuffer, i2);
    }

    private void selectHeuristicAlgorithm(ContentSessionContext contentSessionContext) {
        VideoSpecification specification = contentSessionContext.getSpecification();
        boolean zIsLiveStream = specification.isLiveStream();
        HeuristicsPlaybackConfig heuristicsPlaybackConfig = this.mConfig.getHeuristicsPlaybackConfig();
        HeuristicAlgorithm heuristicAlgorithmLookupHeuristicAlgorithm = HeuristicAlgorithm.INSTANCE.lookupHeuristicAlgorithm(heuristicsPlaybackConfig != null ? heuristicsPlaybackConfig.getHeuristicAlgorithm(zIsLiveStream) : null, zIsLiveStream);
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$dash$quality$heuristic$HeuristicAlgorithm[heuristicAlgorithmLookupHeuristicAlgorithm.ordinal()];
        if (i == 1) {
            this.mHeuristicAlgorithm = heuristicAlgorithmLookupHeuristicAlgorithm;
            this.mVideoStreamBehavior = StreamBehavior.DEFAULT;
        } else if (i == 2 && contentSessionContext.getBandwidthStats().checkTrafficStatsSupport()) {
            this.mHeuristicAlgorithm = heuristicAlgorithmLookupHeuristicAlgorithm;
            this.mVideoStreamBehavior = StreamBehavior.DEFAULT;
        } else {
            this.mHeuristicAlgorithm = HeuristicAlgorithm.MAGNITUDE;
            this.mVideoStreamBehavior = (zIsLiveStream && this.mEnableDualRequest) ? StreamBehavior.DUAL_REQUEST : StreamBehavior.DEFAULT;
        }
        DLog.logf("HeuristicAlgorithm - selecting %s for %s/%s", this.mHeuristicAlgorithm, specification.getContentType(), contentSessionContext.getSessionType());
    }

    /* JADX INFO: renamed from: com.amazon.avod.dash.DashHeuristicsJni$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$HeuristicAlgorithm;

        static {
            int[] iArr = new int[HeuristicAlgorithm.values().length];
            $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$HeuristicAlgorithm = iArr;
            try {
                iArr[HeuristicAlgorithm.BOLA_V1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$HeuristicAlgorithm[HeuristicAlgorithm.SHORT_BUFFER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$HeuristicAlgorithm[HeuristicAlgorithm.MAGNITUDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$HeuristicAlgorithm[HeuristicAlgorithm.LEGACY_SBH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void updateHeuristicInfo(ContentSessionContext contentSessionContext) {
        if (ContentSessionType.isStreamingSession(contentSessionContext.getSessionType())) {
            if (contentSessionContext.getSpecification().isLiveStream()) {
                this.mDeviceResources.setContentType(ContentType.LiveStreaming);
            }
            this.mDeviceResources.setHeuristicAlgorithm(this.mHeuristicAlgorithm);
            int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$dash$quality$heuristic$HeuristicAlgorithm[this.mHeuristicAlgorithm.ordinal()];
            if (i == 1) {
                this.mDeviceResources.setTimeoutStrategy(this.mConfig.getBolaConfig().getTimeoutCalculationStrategy());
                this.mDeviceResources.setReviewProgressStrategy(this.mConfig.getBolaConfig().getReviewDownloadProgressStrategy());
            } else if (i == 2 || i == 4) {
                this.mDeviceResources.setTimeoutStrategy(this.mConfig.getShortBufferHeuristicsConfig().getTimeoutCalculationStrategy());
                this.mDeviceResources.setReviewProgressStrategy(this.mConfig.getShortBufferHeuristicsConfig().getReviewDownloadProgressStrategy());
            } else {
                this.mDeviceResources.setTimeoutStrategy(this.mConfig.getDownloadConfig().getTimeoutCalculationStrategy());
                this.mDeviceResources.setReviewProgressStrategy(this.mConfig.getDownloadConfig().getReviewDownloadProgressStrategy());
            }
        }
    }

    public boolean isInitialized() {
        boolean z;
        synchronized (this.mSyncObject) {
            z = this.mEngineInitialized;
        }
        return z;
    }

    void setLibraryInitialized(boolean z) {
        this.mLibraryInitialized = z;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    @Nonnull
    public HeuristicAlgorithm getAlgorithm() {
        return this.mHeuristicAlgorithm;
    }

    boolean isEngineInitialized() {
        return this.mEngineInitialized;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public float getVersionInfo() {
        synchronized (this.mSyncObject) {
            if (!this.mEngineInitialized) {
                return 0.0f;
            }
            try {
                return getVersionInfo(this.mHeuristicsHandle);
            } catch (ContentException unused) {
                DLog.warnf("getVersionInfo threw ContentException, returning 0");
                return 0.0f;
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void release() throws ContentException {
        synchronized (this.mSyncObject) {
            if (this.mEngineInitialized) {
                release(this.mHeuristicsHandle);
                this.mEngineInitialized = false;
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public ByteBuffer getState() throws ContentException {
        synchronized (this.mSyncObject) {
            if (!this.mEngineInitialized) {
                return null;
            }
            return getHeuristicsState(this.mHeuristicsHandle);
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void setStreamIndex(int i, int i2) throws ContentException {
        synchronized (this.mSyncObject) {
            if (this.mEngineInitialized) {
                setDashStreamIndex(this.mHeuristicsHandle, i, i2);
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void setStreamHandle(int i, long j) throws ContentException {
        synchronized (this.mSyncObject) {
            if (this.mEngineInitialized) {
                setDashStreamHandle(this.mHeuristicsHandle, i, j);
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void pause() throws ContentException {
        synchronized (this.mSyncObject) {
            if (this.mEngineInitialized) {
                for (int i = 0; i < this.mNumStreams; i++) {
                    pause(i);
                }
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void pause(int i) throws ContentException {
        synchronized (this.mSyncObject) {
            if (this.mEngineInitialized) {
                pauseDashHeuristics(this.mHeuristicsHandle, i);
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void resume() throws ContentException {
        synchronized (this.mSyncObject) {
            if (this.mEngineInitialized) {
                for (int i = 0; i < this.mNumStreams; i++) {
                    resume(i);
                }
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void resume(int i) throws ContentException {
        synchronized (this.mSyncObject) {
            if (this.mEngineInitialized) {
                resumeDashHeuristics(this.mHeuristicsHandle, i);
            }
        }
    }

    public void downloadPaused() throws ContentException {
        synchronized (this.mSyncObject) {
            if (this.mEngineInitialized) {
                downloadPaused(this.mHeuristicsHandle);
            }
        }
    }

    public void downloadResumed() throws ContentException {
        synchronized (this.mSyncObject) {
            if (this.mEngineInitialized) {
                downloadResumed(this.mHeuristicsHandle);
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void bufferStateChanged() throws ContentException {
        synchronized (this.mSyncObject) {
            if (this.mEngineInitialized) {
                bufferStateChanged(this.mHeuristicsHandle);
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void reportDownloadProgress(int i, int i2, long j, long j2, long j3, DownloadStatus downloadStatus, long j4) throws ContentException {
        synchronized (this.mSyncObject) {
            if (this.mEngineInitialized) {
                reportDownloadProgress(this.mHeuristicsHandle, i, j, j2, j3, downloadStatus.getStatusCode(), j4);
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public long getLatencyAverageMillis() {
        synchronized (this.mSyncObject) {
            if (!this.mEngineInitialized) {
                return 0L;
            }
            try {
                return getLatencyAverage(this.mHeuristicsHandle);
            } catch (ContentException unused) {
                DLog.warnf("getLatencyAverage threw ContentException, returning 0");
                return 0L;
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public long getBandwidthAverageBytesPerSecond() {
        synchronized (this.mSyncObject) {
            if (!this.mEngineInitialized) {
                return 0L;
            }
            try {
                return getBandwidthAverage(this.mHeuristicsHandle);
            } catch (ContentException unused) {
                DLog.warnf("getBandwidthAverage threw ContentException, returning 0");
                return 0L;
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public long getLatencyStandardDeviationMillis() {
        synchronized (this.mSyncObject) {
            if (!this.mEngineInitialized) {
                return 0L;
            }
            try {
                return getLatencyStandardDeviation(this.mHeuristicsHandle);
            } catch (ContentException unused) {
                DLog.warnf("getLatencyStandardDeviation threw ContentException, returning 0");
                return 0L;
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public long getBandwidthStandardDeviationBytesPerSecond() {
        synchronized (this.mSyncObject) {
            if (!this.mEngineInitialized) {
                return 0L;
            }
            try {
                return getBandwidthStandardDeviation(this.mHeuristicsHandle);
            } catch (ContentException unused) {
                DLog.warnf("getBandwidthStandardDeviation threw ContentException, returning 0");
                return 0L;
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    @Nonnull
    public String getSettingsId() {
        return this.mConfig.getHeuristicsSettingsId();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    @Nullable
    public HeuristicsPlaybackConfig getHeuristicsPlaybackConfig() {
        return this.mConfig.getHeuristicsPlaybackConfig();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    @Nonnull
    public DownloadConfig getDownloadConfig() {
        return this.mConfig.getDownloadConfig();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    @Nonnull
    public QoeConfigInterface getQoeConfig() {
        return this.mConfig.getQoeConfig();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void onPlaybackStateChange(HeuristicsPlaybackState heuristicsPlaybackState) {
        synchronized (this.mSyncObject) {
            if (this.mEngineInitialized) {
                try {
                    onPlaybackStateChange(this.mHeuristicsHandle, heuristicsPlaybackState.getValue());
                } catch (ContentException unused) {
                    DLog.warnf("getBandwidthStandardDeviation threw ContentException, returning 0");
                }
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    @Nonnull
    public StreamBehavior getStreamBehavior() {
        StreamBehavior streamBehavior;
        synchronized (this.mSyncObject) {
            streamBehavior = this.mVideoStreamBehavior;
        }
        return streamBehavior;
    }

    private long bufferedDurationCallback(int i) {
        long jBufferedDuration;
        if (this.mCallbacks == null) {
            DLog.errorf("Buffered duration callback is invoked before initialization");
            return 0L;
        }
        synchronized (this.mCallbackSyncObject) {
            jBufferedDuration = this.mCallbacks.bufferedDuration(i);
        }
        return jBufferedDuration;
    }

    private long maxAvailableBufferDurationCallback(int i) {
        long jMaxAvailableBufferDuration;
        if (this.mCallbacks == null) {
            DLog.errorf("max Downloadable Fragment Duration callback is invoked before initialization");
            return 0L;
        }
        synchronized (this.mCallbackSyncObject) {
            jMaxAvailableBufferDuration = this.mCallbacks.maxAvailableBufferDuration(i);
        }
        return jMaxAvailableBufferDuration;
    }

    private long availableBufferSizeCallback(int i) {
        long jAvailableBufferSize;
        if (this.mCallbacks == null) {
            DLog.errorf("Available buffer size callback is invoked before initialization");
            return 0L;
        }
        synchronized (this.mCallbackSyncObject) {
            jAvailableBufferSize = this.mCallbacks.availableBufferSize(i);
        }
        return jAvailableBufferSize;
    }

    private int missingFragmentCallback(int i, int i2) {
        int iMissingFragment;
        if (this.mCallbacks == null) {
            DLog.errorf("Missing fragment callback is invoked before initialization");
            return 0;
        }
        synchronized (this.mCallbackSyncObject) {
            iMissingFragment = this.mCallbacks.missingFragment(i, i2);
        }
        return iMissingFragment;
    }

    private boolean cancelDownloadCallback(int i, int i2, long j) {
        boolean zCancelDownload;
        if (this.mCallbacks == null) {
            DLog.errorf("Cancel download callback is invoked before initialization");
            return false;
        }
        synchronized (this.mCallbackSyncObject) {
            zCancelDownload = this.mCallbacks.cancelDownload(i, i2, j);
        }
        return zCancelDownload;
    }

    private long startDownloadCallback(int i, int i2, int i3, long j, long j2, int i4) {
        long jStartDownload;
        if (this.mCallbacks == null) {
            DLog.errorf("Start download callback is invoked before initialization");
            return -5L;
        }
        synchronized (this.mCallbackSyncObject) {
            jStartDownload = this.mCallbacks.startDownload(i, i2, i3, j, j2, FragmentType.valueOf(i4));
        }
        return jStartDownload;
    }

    private int highestQualityObtainedCallback(int i, int i2) {
        int iHighestQualityObtained;
        if (this.mCallbacks == null) {
            DLog.errorf("highestQualityObtainedCallback is invoked before initialization");
            return INVALID_QUALITY_INDEX;
        }
        synchronized (this.mCallbackSyncObject) {
            iHighestQualityObtained = this.mCallbacks.highestQualityObtained(i, i2);
        }
        return iHighestQualityObtained;
    }

    private int consumptionHeadCallBack(int i) {
        int iConsumptionHead;
        if (this.mCallbacks == null) {
            DLog.errorf("consumptionHeadCallBack is invoked before initialization");
            return INVALID_CONSUMPTION_HEAD;
        }
        synchronized (this.mCallbackSyncObject) {
            iConsumptionHead = this.mCallbacks.consumptionHead(i);
        }
        return iConsumptionHead;
    }

    private int streamBehaviorCallBack(int i) {
        int iStreamBehavior;
        Preconditions.checkState(this.mCallbacks != null, "streamBehaviorCallBack is invoked before initialization!");
        synchronized (this.mCallbackSyncObject) {
            iStreamBehavior = this.mCallbacks.streamBehavior(i);
        }
        return iStreamBehavior;
    }

    private int averageBandwidthBpsCallback() {
        int iAverageBandwidthBps;
        Preconditions.checkState(this.mCallbacks != null, "averageBandwidthBpsCallback is invoked before initialization!");
        synchronized (this.mCallbackSyncObject) {
            iAverageBandwidthBps = this.mCallbacks.averageBandwidthBps();
        }
        return iAverageBandwidthBps;
    }

    private int streamTypeCallback(int i) {
        int iStreamType;
        Preconditions.checkState(this.mCallbacks != null, "averageBandwidthBpsCallback is invoked before initialization!");
        synchronized (this.mCallbackSyncObject) {
            iStreamType = this.mCallbacks.streamType(i);
        }
        return iStreamType;
    }

    private void overridePlaybackConfigsCallback(@Nonnull String[] strArr, @Nonnull String[] strArr2) {
        Preconditions.checkState(this.mCallbacks != null, "overrideBufferLengthForPlaybackStartCallback is invoked before initialization!");
        Preconditions.checkNotNull(strArr, "overrideKeys cannot be null!");
        Preconditions.checkNotNull(strArr2, "overrideValues cannot be null!");
        Preconditions.checkState(strArr.length == strArr2.length, "overrideKeys and overrideValues length much match!!");
        synchronized (this.mCallbackSyncObject) {
            HashMap mapNewHashMap = Maps.newHashMap();
            for (int i = 0; i < strArr.length; i++) {
                mapNewHashMap.put(strArr[i], strArr2[i]);
            }
            this.mCallbacks.overridePlaybackConfigsCallback(mapNewHashMap);
        }
    }

    private long fragmentStartTimeCallback(int i, int i2) {
        long jFragmentStartTime;
        Preconditions.checkState(this.mCallbacks != null, "fragmentStartTimeCallback is invoked before initialization!");
        synchronized (this.mCallbackSyncObject) {
            jFragmentStartTime = this.mCallbacks.fragmentStartTime(i, i2);
        }
        return jFragmentStartTime;
    }

    private long fragmentDurationTimeCallback(int i, int i2) {
        long jFragmentDurationTime;
        Preconditions.checkState(this.mCallbacks != null, "fragmentDurationTimeCallback is invoked before initialization!");
        synchronized (this.mCallbackSyncObject) {
            jFragmentDurationTime = this.mCallbacks.fragmentDurationTime(i, i2);
        }
        return jFragmentDurationTime;
    }

    private int fragmentQualityCountCallback(int i) {
        int iFragmentQualityCount;
        Preconditions.checkState(this.mCallbacks != null, "fragmentQualityCountCallback is invoked before initialization!");
        synchronized (this.mCallbackSyncObject) {
            iFragmentQualityCount = this.mCallbacks.fragmentQualityCount(i, 0);
        }
        return iFragmentQualityCount;
    }

    private int fragmentBitrateCallback(int i, int i2, int i3) {
        int iFragmentBitrate;
        Preconditions.checkState(this.mCallbacks != null, "fragmentBitrateCallback is invoked before initialization!");
        synchronized (this.mCallbackSyncObject) {
            iFragmentBitrate = this.mCallbacks.fragmentBitrate(i, i2, i3);
        }
        return iFragmentBitrate;
    }

    private long fragmentSizeCallback(int i, int i2, int i3) {
        long jFragmentSize;
        Preconditions.checkState(this.mCallbacks != null, "fragmentSizeCallback is invoked before initialization!");
        synchronized (this.mCallbackSyncObject) {
            jFragmentSize = this.mCallbacks.fragmentSize(i, i2, i3);
        }
        return jFragmentSize;
    }

    private int fragmentCountCallback(int i) {
        int iFragmentCount;
        Preconditions.checkState(this.mCallbacks != null, "fragmentCountCallback is invoked before initialization!");
        synchronized (this.mCallbackSyncObject) {
            iFragmentCount = this.mCallbacks.fragmentCount(i);
        }
        return iFragmentCount;
    }

    private void initializeLibrary() throws ContentException {
        if (!this.mLibraryLoader.waitForInitialization()) {
            throw new ContentException(ContentException.ContentError.ERROR_LOADING_NATIVE_LIBRARIES, "Failed to load the playback native libraries!");
        }
        String nativeLibraryVersion = getNativeLibraryVersion();
        DLog.logf("%s library: version %s, compile time %s", "AIVHeuristics", nativeLibraryVersion, getNativeCodeCompileTime());
        Preconditions.checkState(nativeLibraryVersion.equals(EXPECTED_LIBRARY_VERSION), "FATAL DEPLOYMENT ERROR: This AIV app is built to run with version %s of the lib%s.so library, but version %s was found on this device", EXPECTED_LIBRARY_VERSION, "AIVHeuristics", nativeLibraryVersion);
        this.mLibraryInitialized = true;
    }
}
