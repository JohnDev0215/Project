package android.media.tv.tuner;

import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.content.Context;
import android.media.tv.tuner.dvr.DvrPlayback;
import android.media.tv.tuner.dvr.DvrRecorder;
import android.media.tv.tuner.dvr.OnPlaybackStatusChangedListener;
import android.media.tv.tuner.dvr.OnRecordStatusChangedListener;
import android.media.tv.tuner.filter.Filter;
import android.media.tv.tuner.filter.FilterCallback;
import android.media.tv.tuner.filter.TimeFilter;
import android.media.tv.tuner.frontend.Atsc3PlpInfo;
import android.media.tv.tuner.frontend.FrontendInfo;
import android.media.tv.tuner.frontend.FrontendSettings;
import android.media.tv.tuner.frontend.FrontendStatus;
import android.media.tv.tuner.frontend.OnTuneEventListener;
import android.media.tv.tuner.frontend.ScanCallback;
import android.media.tv.tunerresourcemanager.ResourceClientProfile;
import android.media.tv.tunerresourcemanager.TunerDemuxRequest;
import android.media.tv.tunerresourcemanager.TunerDescramblerRequest;
import android.media.tv.tunerresourcemanager.TunerFrontendInfo;
import android.media.tv.tunerresourcemanager.TunerFrontendRequest;
import android.media.tv.tunerresourcemanager.TunerLnbRequest;
import android.media.tv.tunerresourcemanager.TunerResourceManager;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.internal.util.FrameworkStatsLog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class Tuner implements AutoCloseable {
    private static final boolean DEBUG = false;
    public static final int DVR_TYPE_PLAYBACK = 1;
    public static final int DVR_TYPE_RECORD = 0;
    public static final int INVALID_AV_SYNC_ID = -1;
    public static final int INVALID_FILTER_ID = -1;
    public static final int INVALID_STREAM_ID = 65535;
    public static final long INVALID_TIMESTAMP = -1;
    public static final int INVALID_TS_PID = 65535;
    private static final int MSG_ON_FILTER_EVENT = 2;
    private static final int MSG_ON_FILTER_STATUS = 3;
    private static final int MSG_ON_LNB_EVENT = 4;
    private static final int MSG_RESOURCE_LOST = 1;
    public static final int RESULT_INVALID_ARGUMENT = 4;
    public static final int RESULT_INVALID_STATE = 3;
    public static final int RESULT_NOT_INITIALIZED = 2;
    public static final int RESULT_OUT_OF_MEMORY = 5;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_UNAVAILABLE = 1;
    public static final int RESULT_UNKNOWN_ERROR = 6;
    public static final int SCAN_TYPE_AUTO = 1;
    public static final int SCAN_TYPE_BLIND = 2;
    public static final int SCAN_TYPE_UNDEFINED = 0;
    private static final String TAG = "MediaTvTuner";
    private final int mClientId;
    private final Context mContext;
    private Integer mDemuxHandle;
    private Frontend mFrontend;
    private Integer mFrontendHandle;
    private FrontendInfo mFrontendInfo;
    private EventHandler mHandler;
    private Lnb mLnb;
    private Integer mLnbHandle;
    private long mNativeContext;
    private OnResourceLostListener mOnResourceLostListener;
    private Executor mOnResourceLostListenerExecutor;
    private OnTuneEventListener mOnTuneEventListener;
    private Executor mOnTunerEventExecutor;
    private ScanCallback mScanCallback;
    private Executor mScanCallbackExecutor;
    private final TunerResourceManager mTunerResourceManager;
    private int mUserId;
    private int mFrontendType = 0;
    private Map<Integer, Descrambler> mDescramblers = new HashMap();
    private List<Filter> mFilters = new ArrayList();
    private final TunerResourceManager.ResourcesReclaimListener mResourceListener = new TunerResourceManager.ResourcesReclaimListener() { // from class: android.media.tv.tuner.Tuner.1
        @Override // android.media.tv.tunerresourcemanager.TunerResourceManager.ResourcesReclaimListener
        public void onReclaimResources() {
            if (Tuner.this.mFrontend != null) {
                FrameworkStatsLog.write(276, Tuner.this.mUserId, 0);
            }
            Tuner.this.mHandler.sendMessage(Tuner.this.mHandler.obtainMessage(1));
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface DvrType {
    }

    public interface OnResourceLostListener {
        void onResourceLost(Tuner tuner);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScanType {
    }

    private native int nativeClose();

    private native int nativeCloseDemux(int i);

    private native int nativeCloseFrontend(int i);

    private native int nativeCloseFrontendByHandle(int i);

    private native int nativeConnectCiCam(int i);

    private native int nativeDisconnectCiCam();

    private native Integer nativeGetAvSyncHwId(Filter filter);

    private native Long nativeGetAvSyncTime(int i);

    private native DemuxCapabilities nativeGetDemuxCapabilities();

    private native List<Integer> nativeGetFrontendIds();

    private native FrontendInfo nativeGetFrontendInfo(int i);

    private native FrontendStatus nativeGetFrontendStatus(int[] iArr);

    private native int[] nativeGetLnbIds();

    private static native void nativeInit();

    private native int nativeOpenDemuxByhandle(int i);

    private native Descrambler nativeOpenDescramblerByHandle(int i);

    private native DvrPlayback nativeOpenDvrPlayback(long j);

    private native DvrRecorder nativeOpenDvrRecorder(long j);

    private native Filter nativeOpenFilter(int i, int i2, long j);

    private native Frontend nativeOpenFrontendByHandle(int i);

    private native Lnb nativeOpenLnbByHandle(int i);

    private native Lnb nativeOpenLnbByName(String str);

    private native TimeFilter nativeOpenTimeFilter();

    private native int nativeScan(int i, FrontendSettings frontendSettings, int i2);

    private native int nativeSetLna(boolean z);

    private native int nativeSetLnb(int i);

    private native void nativeSetup();

    private native int nativeStopScan();

    private native int nativeStopTune();

    private native int nativeTune(int i, FrontendSettings frontendSettings);

    static {
        try {
            System.loadLibrary("media_tv_tuner");
            nativeInit();
        } catch (UnsatisfiedLinkError unused) {
            Log.d(TAG, "tuner JNI library not found!");
        }
    }

    public Tuner(Context context, String str, int i) {
        nativeSetup();
        this.mContext = context;
        this.mTunerResourceManager = (TunerResourceManager) context.getSystemService(Context.TV_TUNER_RESOURCE_MGR_SERVICE);
        if (this.mHandler == null) {
            this.mHandler = createEventHandler();
        }
        this.mHandler = createEventHandler();
        int[] iArr = new int[1];
        this.mTunerResourceManager.registerClientProfile(new ResourceClientProfile(str, i), new HandlerExecutor(this.mHandler), this.mResourceListener, iArr);
        this.mClientId = iArr[0];
        this.mUserId = ActivityManager.getCurrentUser();
        setFrontendInfoList();
        setLnbIds();
    }

    private void setFrontendInfoList() {
        List<Integer> frontendIds = getFrontendIds();
        if (frontendIds == null) {
            return;
        }
        TunerFrontendInfo[] tunerFrontendInfoArr = new TunerFrontendInfo[frontendIds.size()];
        for (int i = 0; i < frontendIds.size(); i++) {
            int iIntValue = frontendIds.get(i).intValue();
            FrontendInfo frontendInfoById = getFrontendInfoById(iIntValue);
            if (frontendInfoById != null) {
                tunerFrontendInfoArr[i] = new TunerFrontendInfo(iIntValue, frontendInfoById.getType(), frontendInfoById.getExclusiveGroupId());
            }
        }
        this.mTunerResourceManager.setFrontendInfoList(tunerFrontendInfoArr);
    }

    public List<Integer> getFrontendIds() {
        return nativeGetFrontendIds();
    }

    private void setLnbIds() {
        int[] iArrNativeGetLnbIds = nativeGetLnbIds();
        if (iArrNativeGetLnbIds == null) {
            return;
        }
        this.mTunerResourceManager.setLnbInfoList(iArrNativeGetLnbIds);
    }

    public void setResourceLostListener(Executor executor, OnResourceLostListener onResourceLostListener) {
        Objects.requireNonNull(executor, "OnResourceLostListener must not be null");
        Objects.requireNonNull(onResourceLostListener, "executor must not be null");
        this.mOnResourceLostListener = onResourceLostListener;
        this.mOnResourceLostListenerExecutor = executor;
    }

    public void clearResourceLostListener() {
        this.mOnResourceLostListener = null;
        this.mOnResourceLostListenerExecutor = null;
    }

    public void shareFrontendFromTuner(Tuner tuner) {
        this.mTunerResourceManager.shareFrontend(this.mClientId, tuner.mClientId);
        Integer num = tuner.mFrontendHandle;
        this.mFrontendHandle = num;
        this.mFrontend = nativeOpenFrontendByHandle(num.intValue());
    }

    public void updateResourcePriority(int i, int i2) {
        this.mTunerResourceManager.updateClientPriority(this.mClientId, i, i2);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Integer num = this.mFrontendHandle;
        if (num != null) {
            int iNativeCloseFrontend = nativeCloseFrontend(num.intValue());
            if (iNativeCloseFrontend != 0) {
                TunerUtils.throwExceptionForResult(iNativeCloseFrontend, "failed to close frontend");
            }
            this.mTunerResourceManager.releaseFrontend(this.mFrontendHandle.intValue(), this.mClientId);
            FrameworkStatsLog.write(276, this.mUserId, 0);
            this.mFrontendHandle = null;
            this.mFrontend = null;
        }
        Lnb lnb = this.mLnb;
        if (lnb != null) {
            lnb.close();
        }
        if (!this.mDescramblers.isEmpty()) {
            for (Map.Entry<Integer, Descrambler> entry : this.mDescramblers.entrySet()) {
                entry.getValue().close();
                this.mTunerResourceManager.releaseDescrambler(entry.getKey().intValue(), this.mClientId);
            }
            this.mDescramblers.clear();
        }
        if (!this.mFilters.isEmpty()) {
            Iterator<Filter> it = this.mFilters.iterator();
            while (it.hasNext()) {
                it.next().close();
            }
            this.mFilters.clear();
        }
        Integer num2 = this.mDemuxHandle;
        if (num2 != null) {
            int iNativeCloseDemux = nativeCloseDemux(num2.intValue());
            if (iNativeCloseDemux != 0) {
                TunerUtils.throwExceptionForResult(iNativeCloseDemux, "failed to close demux");
            }
            this.mTunerResourceManager.releaseDemux(this.mDemuxHandle.intValue(), this.mClientId);
            this.mFrontendHandle = null;
        }
        TunerUtils.throwExceptionForResult(nativeClose(), "failed to close tuner");
    }

    private EventHandler createEventHandler() {
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            return new EventHandler(looperMyLooper);
        }
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null) {
            return new EventHandler(mainLooper);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class EventHandler extends Handler {
        private EventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                if (Tuner.this.mOnResourceLostListener == null || Tuner.this.mOnResourceLostListenerExecutor == null) {
                    return;
                }
                Tuner.this.mOnResourceLostListenerExecutor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$EventHandler$O98cW0HuxUDT27bFMy40ViHTsqc
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleMessage$0$Tuner$EventHandler();
                    }
                });
                return;
            }
            if (i != 3) {
                return;
            }
            Filter filter = (Filter) message.obj;
            if (filter.getCallback() != null) {
                filter.getCallback().onFilterStatusChanged(filter, message.arg1);
            }
        }

        public /* synthetic */ void lambda$handleMessage$0$Tuner$EventHandler() {
            Tuner.this.mOnResourceLostListener.onResourceLost(Tuner.this);
        }
    }

    private class Frontend {
        private int mId;

        private Frontend(int i) {
            this.mId = i;
        }
    }

    public void setOnTuneEventListener(Executor executor, OnTuneEventListener onTuneEventListener) {
        this.mOnTuneEventListener = onTuneEventListener;
        this.mOnTunerEventExecutor = executor;
    }

    public void clearOnTuneEventListener() {
        this.mOnTuneEventListener = null;
        this.mOnTunerEventExecutor = null;
    }

    public int tune(FrontendSettings frontendSettings) {
        Log.d(TAG, "Tune to " + frontendSettings.getFrequency());
        this.mFrontendType = frontendSettings.getType();
        if (!checkResource(0)) {
            return 1;
        }
        this.mFrontendInfo = null;
        Log.d(TAG, "Write Stats Log for tuning.");
        FrameworkStatsLog.write(276, this.mUserId, 1);
        return nativeTune(frontendSettings.getType(), frontendSettings);
    }

    public int cancelTuning() {
        return nativeStopTune();
    }

    public int scan(FrontendSettings frontendSettings, int i, Executor executor, ScanCallback scanCallback) {
        if (this.mScanCallback != null || this.mScanCallbackExecutor != null) {
            throw new IllegalStateException("Scan already in progress.  stopScan must be called before a new scan can be started.");
        }
        this.mFrontendType = frontendSettings.getType();
        if (!checkResource(0)) {
            return 1;
        }
        this.mScanCallback = scanCallback;
        this.mScanCallbackExecutor = executor;
        this.mFrontendInfo = null;
        FrameworkStatsLog.write(276, this.mUserId, 5);
        return nativeScan(frontendSettings.getType(), frontendSettings, i);
    }

    public int cancelScanning() {
        FrameworkStatsLog.write(276, this.mUserId, 6);
        int iNativeStopScan = nativeStopScan();
        this.mScanCallback = null;
        this.mScanCallbackExecutor = null;
        return iNativeStopScan;
    }

    private boolean requestFrontend() {
        int[] iArr = new int[1];
        boolean zRequestFrontend = this.mTunerResourceManager.requestFrontend(new TunerFrontendRequest(this.mClientId, this.mFrontendType), iArr);
        if (zRequestFrontend) {
            Integer numValueOf = Integer.valueOf(iArr[0]);
            this.mFrontendHandle = numValueOf;
            this.mFrontend = nativeOpenFrontendByHandle(numValueOf.intValue());
        }
        return zRequestFrontend;
    }

    private int setLnb(Lnb lnb) {
        return nativeSetLnb(lnb.mId);
    }

    public int setLnaEnabled(boolean z) {
        return nativeSetLna(z);
    }

    public FrontendStatus getFrontendStatus(int[] iArr) {
        if (this.mFrontend == null) {
            throw new IllegalStateException("frontend is not initialized");
        }
        return nativeGetFrontendStatus(iArr);
    }

    public int getAvSyncHwId(Filter filter) {
        Integer numNativeGetAvSyncHwId;
        if (checkResource(1) && (numNativeGetAvSyncHwId = nativeGetAvSyncHwId(filter)) != null) {
            return numNativeGetAvSyncHwId.intValue();
        }
        return -1;
    }

    public long getAvSyncTime(int i) {
        Long lNativeGetAvSyncTime;
        if (checkResource(1) && (lNativeGetAvSyncTime = nativeGetAvSyncTime(i)) != null) {
            return lNativeGetAvSyncTime.longValue();
        }
        return -1L;
    }

    public int connectCiCam(int i) {
        if (checkResource(1)) {
            return nativeConnectCiCam(i);
        }
        return 1;
    }

    public int disconnectCiCam() {
        if (checkResource(1)) {
            return nativeDisconnectCiCam();
        }
        return 1;
    }

    public FrontendInfo getFrontendInfo() {
        if (!checkResource(0)) {
            return null;
        }
        Frontend frontend = this.mFrontend;
        if (frontend == null) {
            throw new IllegalStateException("frontend is not initialized");
        }
        if (this.mFrontendInfo == null) {
            this.mFrontendInfo = getFrontendInfoById(frontend.mId);
        }
        return this.mFrontendInfo;
    }

    public FrontendInfo getFrontendInfoById(int i) {
        return nativeGetFrontendInfo(i);
    }

    public DemuxCapabilities getDemuxCapabilities() {
        return nativeGetDemuxCapabilities();
    }

    private void onFrontendEvent(final int i) {
        Log.d(TAG, "Got event from tuning. Event type: " + i);
        Executor executor = this.mOnTunerEventExecutor;
        if (executor != null && this.mOnTuneEventListener != null) {
            executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$W0RSFCXzyS-Nm5JhUoyHO4-gtHc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFrontendEvent$0$Tuner(i);
                }
            });
        }
        Log.d(TAG, "Wrote Stats Log for the events from tuning.");
        if (i == 0) {
            FrameworkStatsLog.write(276, this.mUserId, 2);
        } else if (i == 1) {
            FrameworkStatsLog.write(276, this.mUserId, 3);
        } else if (i == 2) {
            FrameworkStatsLog.write(276, this.mUserId, 4);
        }
    }

    public /* synthetic */ void lambda$onFrontendEvent$0$Tuner(int i) {
        this.mOnTuneEventListener.onTuneEvent(i);
    }

    private void onLocked() {
        Log.d(TAG, "Wrote Stats Log for locked event from scanning.");
        FrameworkStatsLog.write(276, this.mUserId, 2);
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$7LyXqscGIcs4FBunPKfBAgBWBPc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onLocked$1$Tuner();
            }
        });
    }

    public /* synthetic */ void lambda$onLocked$1$Tuner() {
        this.mScanCallback.onLocked();
    }

    private void onScanStopped() {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$iNLvb_AT8Ni5vDrCZXOvhTYUE1U
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onScanStopped$2$Tuner();
            }
        });
    }

    public /* synthetic */ void lambda$onScanStopped$2$Tuner() {
        this.mScanCallback.onScanStopped();
    }

    private void onProgress(final int i) {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$EpB-abkPzm4k9y4f_ckGHE7hk-Y
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onProgress$3$Tuner(i);
            }
        });
    }

    public /* synthetic */ void lambda$onProgress$3$Tuner(int i) {
        this.mScanCallback.onProgress(i);
    }

    private void onFrequenciesReport(final int[] iArr) {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$cO3VZA6A_FG0wouIFcyFrkppVKQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onFrequenciesReport$4$Tuner(iArr);
            }
        });
    }

    public /* synthetic */ void lambda$onFrequenciesReport$4$Tuner(int[] iArr) {
        this.mScanCallback.onFrequenciesReported(iArr);
    }

    private void onSymbolRates(final int[] iArr) {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$qLBwtqwgHR4JTsJuxl0FSPNFPuA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onSymbolRates$5$Tuner(iArr);
            }
        });
    }

    public /* synthetic */ void lambda$onSymbolRates$5$Tuner(int[] iArr) {
        this.mScanCallback.onSymbolRatesReported(iArr);
    }

    private void onHierarchy(final int i) {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$kZB5DmQFvVQLdFFRG1ICjIXzxIg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onHierarchy$6$Tuner(i);
            }
        });
    }

    public /* synthetic */ void lambda$onHierarchy$6$Tuner(int i) {
        this.mScanCallback.onHierarchyReported(i);
    }

    private void onSignalType(final int i) {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$bvxMLDowJ_umoo-_NEeXgXWz0Gw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onSignalType$7$Tuner(i);
            }
        });
    }

    public /* synthetic */ void lambda$onSignalType$7$Tuner(int i) {
        this.mScanCallback.onSignalTypeReported(i);
    }

    private void onPlpIds(final int[] iArr) {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$NJZQL7FtM6GTMGzFfc6hPmY8a-A
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onPlpIds$8$Tuner(iArr);
            }
        });
    }

    public /* synthetic */ void lambda$onPlpIds$8$Tuner(int[] iArr) {
        this.mScanCallback.onPlpIdsReported(iArr);
    }

    private void onGroupIds(final int[] iArr) {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$Br0QGgsEJkvWiWGajrBIp9UhcI4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onGroupIds$9$Tuner(iArr);
            }
        });
    }

    public /* synthetic */ void lambda$onGroupIds$9$Tuner(int[] iArr) {
        this.mScanCallback.onGroupIdsReported(iArr);
    }

    private void onInputStreamIds(final int[] iArr) {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$97hY_kdBewG88N1-oDJVJ-0tzGU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onInputStreamIds$10$Tuner(iArr);
            }
        });
    }

    public /* synthetic */ void lambda$onInputStreamIds$10$Tuner(int[] iArr) {
        this.mScanCallback.onInputStreamIdsReported(iArr);
    }

    private void onDvbsStandard(final int i) {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$6JzdHyaum-wXts_YXYS69zQbClY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDvbsStandard$11$Tuner(i);
            }
        });
    }

    public /* synthetic */ void lambda$onDvbsStandard$11$Tuner(int i) {
        this.mScanCallback.onDvbsStandardReported(i);
    }

    private void onDvbtStandard(final int i) {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$0UUd7kSDwJbNOtYJHcznNHjL2vI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDvbtStandard$12$Tuner(i);
            }
        });
    }

    public /* synthetic */ void lambda$onDvbtStandard$12$Tuner(int i) {
        this.mScanCallback.onDvbtStandardReported(i);
    }

    private void onAnalogSifStandard(final int i) {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$H2MeyMgstu2-FkYFyyhEuqcpaOM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAnalogSifStandard$13$Tuner(i);
            }
        });
    }

    public /* synthetic */ void lambda$onAnalogSifStandard$13$Tuner(int i) {
        this.mScanCallback.onAnalogSifStandardReported(i);
    }

    private void onAtsc3PlpInfos(final Atsc3PlpInfo[] atsc3PlpInfoArr) {
        Executor executor = this.mScanCallbackExecutor;
        if (executor == null || this.mScanCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Tuner$vA8r09mS8B2Xp9N33dOsBZ4Y_0g
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAtsc3PlpInfos$14$Tuner(atsc3PlpInfoArr);
            }
        });
    }

    public /* synthetic */ void lambda$onAtsc3PlpInfos$14$Tuner(Atsc3PlpInfo[] atsc3PlpInfoArr) {
        this.mScanCallback.onAtsc3PlpInfosReported(atsc3PlpInfoArr);
    }

    public Filter openFilter(int i, int i2, long j, Executor executor, FilterCallback filterCallback) {
        if (!checkResource(1)) {
            return null;
        }
        Filter filterNativeOpenFilter = nativeOpenFilter(i, TunerUtils.getFilterSubtype(i, i2), j);
        if (filterNativeOpenFilter != null) {
            filterNativeOpenFilter.setType(i, i2);
            filterNativeOpenFilter.setCallback(filterCallback, executor);
            if (this.mHandler == null) {
                this.mHandler = createEventHandler();
            }
            this.mFilters.add(filterNativeOpenFilter);
        }
        return filterNativeOpenFilter;
    }

    public Lnb openLnb(Executor executor, LnbCallback lnbCallback) {
        Lnb lnb;
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(lnbCallback, "LnbCallback must not be null");
        Lnb lnb2 = this.mLnb;
        if (lnb2 != null) {
            lnb2.setCallback(executor, lnbCallback, this);
            return this.mLnb;
        }
        if (!checkResource(3) || (lnb = this.mLnb) == null) {
            return null;
        }
        lnb.setCallback(executor, lnbCallback, this);
        setLnb(this.mLnb);
        return this.mLnb;
    }

    public Lnb openLnbByName(String str, Executor executor, LnbCallback lnbCallback) {
        Objects.requireNonNull(str, "LNB name must not be null");
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(lnbCallback, "LnbCallback must not be null");
        Lnb lnbNativeOpenLnbByName = nativeOpenLnbByName(str);
        if (lnbNativeOpenLnbByName != null) {
            Lnb lnb = this.mLnb;
            if (lnb != null) {
                lnb.close();
                this.mLnbHandle = null;
            }
            this.mLnb = lnbNativeOpenLnbByName;
            lnbNativeOpenLnbByName.setCallback(executor, lnbCallback, this);
            setLnb(this.mLnb);
        }
        return this.mLnb;
    }

    private boolean requestLnb() {
        int[] iArr = new int[1];
        boolean zRequestLnb = this.mTunerResourceManager.requestLnb(new TunerLnbRequest(this.mClientId), iArr);
        if (zRequestLnb) {
            Integer numValueOf = Integer.valueOf(iArr[0]);
            this.mLnbHandle = numValueOf;
            this.mLnb = nativeOpenLnbByHandle(numValueOf.intValue());
        }
        return zRequestLnb;
    }

    public TimeFilter openTimeFilter() {
        if (checkResource(1)) {
            return nativeOpenTimeFilter();
        }
        return null;
    }

    public Descrambler openDescrambler() {
        if (checkResource(1)) {
            return requestDescrambler();
        }
        return null;
    }

    public DvrRecorder openDvrRecorder(long j, Executor executor, OnRecordStatusChangedListener onRecordStatusChangedListener) {
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(onRecordStatusChangedListener, "OnRecordStatusChangedListener must not be null");
        if (!checkResource(1)) {
            return null;
        }
        DvrRecorder dvrRecorderNativeOpenDvrRecorder = nativeOpenDvrRecorder(j);
        dvrRecorderNativeOpenDvrRecorder.setListener(executor, onRecordStatusChangedListener);
        return dvrRecorderNativeOpenDvrRecorder;
    }

    public DvrPlayback openDvrPlayback(long j, Executor executor, OnPlaybackStatusChangedListener onPlaybackStatusChangedListener) {
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(onPlaybackStatusChangedListener, "OnPlaybackStatusChangedListener must not be null");
        if (!checkResource(1)) {
            return null;
        }
        DvrPlayback dvrPlaybackNativeOpenDvrPlayback = nativeOpenDvrPlayback(j);
        dvrPlaybackNativeOpenDvrPlayback.setListener(executor, onPlaybackStatusChangedListener);
        return dvrPlaybackNativeOpenDvrPlayback;
    }

    private boolean requestDemux() {
        int[] iArr = new int[1];
        boolean zRequestDemux = this.mTunerResourceManager.requestDemux(new TunerDemuxRequest(this.mClientId), iArr);
        if (zRequestDemux) {
            Integer numValueOf = Integer.valueOf(iArr[0]);
            this.mDemuxHandle = numValueOf;
            nativeOpenDemuxByhandle(numValueOf.intValue());
        }
        return zRequestDemux;
    }

    private Descrambler requestDescrambler() {
        int[] iArr = new int[1];
        if (!this.mTunerResourceManager.requestDescrambler(new TunerDescramblerRequest(this.mClientId), iArr)) {
            return null;
        }
        int i = iArr[0];
        Descrambler descramblerNativeOpenDescramblerByHandle = nativeOpenDescramblerByHandle(i);
        if (descramblerNativeOpenDescramblerByHandle != null) {
            this.mDescramblers.put(Integer.valueOf(i), descramblerNativeOpenDescramblerByHandle);
        } else {
            this.mTunerResourceManager.releaseDescrambler(i, this.mClientId);
        }
        return descramblerNativeOpenDescramblerByHandle;
    }

    private boolean checkResource(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 3) {
                    return false;
                }
                if (this.mLnb == null && !requestLnb()) {
                    return false;
                }
            } else if (this.mDemuxHandle == null && !requestDemux()) {
                return false;
            }
        } else if (this.mFrontendHandle == null && !requestFrontend()) {
            return false;
        }
        return true;
    }

    void releaseLnb() {
        Integer num = this.mLnbHandle;
        if (num != null) {
            this.mTunerResourceManager.releaseLnb(num.intValue(), this.mClientId);
            this.mLnbHandle = null;
        }
        this.mLnb = null;
    }
}
