package com.android.car.radio.platform;

import android.app.job.JobInfo;
import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.hardware.radio.RadioMetadata;
import android.hardware.radio.RadioTuner;
import android.os.Handler;
import android.os.Looper;
import com.byd.music.utils.LogUtil;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
class TunerCallbackAdapterExt extends RadioTuner.Callback {
    private static final int INIT_TIMEOUT_MS = 10000;
    private static final String TAG = "BcRadioApp.tunerext";
    private RadioManager.ProgramInfo mCachedProgramInfo;
    private final RadioTuner.Callback mCallback;
    private final Handler mHandler;
    private ProgramInfoCallback mProgramInfoCallback;
    private final Object mInitLock = new Object();
    private boolean mIsInitialized = false;
    private final AtomicReference<TuneFailedCallback> mTuneFailedCallback = new AtomicReference<>();
    private final Object mProgramInfoLock = new Object();

    interface ProgramInfoCallback {
        void onProgramInfoChanged(RadioManager.ProgramInfo programInfo);
    }

    interface TuneFailedCallback {
        void onTuneFailed(int i, ProgramSelector programSelector);
    }

    TunerCallbackAdapterExt(RadioTuner.Callback callback, Handler handler) {
        this.mCallback = (RadioTuner.Callback) Objects.requireNonNull(callback);
        if (handler == null) {
            this.mHandler = new Handler(Looper.getMainLooper());
        } else {
            this.mHandler = handler;
        }
    }

    public boolean waitForInitialization() {
        synchronized (this.mInitLock) {
            if (this.mIsInitialized) {
                return true;
            }
            try {
                this.mInitLock.wait(JobInfo.MIN_BACKOFF_MILLIS);
            } catch (InterruptedException unused) {
            }
            return this.mIsInitialized;
        }
    }

    void setTuneFailedCallback(TuneFailedCallback tuneFailedCallback) {
        this.mTuneFailedCallback.set(tuneFailedCallback);
    }

    void setProgramInfoCallback(ProgramInfoCallback programInfoCallback) {
        synchronized (this.mProgramInfoLock) {
            this.mProgramInfoCallback = programInfoCallback;
            if (programInfoCallback != null && this.mCachedProgramInfo != null) {
                LogUtil.d(TAG, "Invoking callback with cached ProgramInfo");
                this.mProgramInfoCallback.onProgramInfoChanged(this.mCachedProgramInfo);
                this.mCachedProgramInfo = null;
            }
        }
    }

    public /* synthetic */ void lambda$onError$0$TunerCallbackAdapterExt(int i) {
        this.mCallback.onError(i);
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onError(final int i) {
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$9n_9FaOFOC7THPOkk6WGr41O87k
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onError$0$TunerCallbackAdapterExt(i);
            }
        });
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onTuneFailed(final int i, final ProgramSelector programSelector) {
        TuneFailedCallback tuneFailedCallback = this.mTuneFailedCallback.get();
        if (tuneFailedCallback != null) {
            tuneFailedCallback.onTuneFailed(i, programSelector);
        }
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$vlXFcix0L2azhlrhMF7_olqZmjc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onTuneFailed$1$TunerCallbackAdapterExt(i, programSelector);
            }
        });
    }

    public /* synthetic */ void lambda$onTuneFailed$1$TunerCallbackAdapterExt(int i, ProgramSelector programSelector) {
        this.mCallback.onTuneFailed(i, programSelector);
    }

    public /* synthetic */ void lambda$onConfigurationChanged$2$TunerCallbackAdapterExt(RadioManager.BandConfig bandConfig) {
        this.mCallback.onConfigurationChanged(bandConfig);
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onConfigurationChanged(final RadioManager.BandConfig bandConfig) {
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$u0_B5tGiNLF-o-NFY83zypAOqNs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onConfigurationChanged$2$TunerCallbackAdapterExt(bandConfig);
            }
        });
        if (this.mIsInitialized) {
            return;
        }
        synchronized (this.mInitLock) {
            this.mIsInitialized = true;
            this.mInitLock.notifyAll();
        }
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onProgramInfoChanged(final RadioManager.ProgramInfo programInfo) {
        synchronized (this.mProgramInfoLock) {
            if (this.mProgramInfoCallback == null) {
                LogUtil.d(TAG, "ProgramInfo callback is not set yet; caching ProgramInfo");
                this.mCachedProgramInfo = programInfo;
            } else {
                this.mProgramInfoCallback.onProgramInfoChanged(programInfo);
            }
        }
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$bgwF6xv5TuA3p94qSHVP4nF5t9o
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onProgramInfoChanged$3$TunerCallbackAdapterExt(programInfo);
            }
        });
    }

    public /* synthetic */ void lambda$onProgramInfoChanged$3$TunerCallbackAdapterExt(RadioManager.ProgramInfo programInfo) {
        this.mCallback.onProgramInfoChanged(programInfo);
    }

    public /* synthetic */ void lambda$onMetadataChanged$4$TunerCallbackAdapterExt(RadioMetadata radioMetadata) {
        this.mCallback.onMetadataChanged(radioMetadata);
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onMetadataChanged(final RadioMetadata radioMetadata) {
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$Bii3auUnCMEgmYhMgyjhu71zDBg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onMetadataChanged$4$TunerCallbackAdapterExt(radioMetadata);
            }
        });
    }

    public /* synthetic */ void lambda$onTrafficAnnouncement$5$TunerCallbackAdapterExt(boolean z) {
        this.mCallback.onTrafficAnnouncement(z);
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onTrafficAnnouncement(final boolean z) {
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$NNikiUaxnecR27BphvM-a2pMrZ0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onTrafficAnnouncement$5$TunerCallbackAdapterExt(z);
            }
        });
    }

    public /* synthetic */ void lambda$onEmergencyAnnouncement$6$TunerCallbackAdapterExt(boolean z) {
        this.mCallback.onEmergencyAnnouncement(z);
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onEmergencyAnnouncement(final boolean z) {
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$YteIiQoLSPYNK7065IU1-P4ULqA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onEmergencyAnnouncement$6$TunerCallbackAdapterExt(z);
            }
        });
    }

    public /* synthetic */ void lambda$onAntennaState$7$TunerCallbackAdapterExt(boolean z) {
        this.mCallback.onAntennaState(z);
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onAntennaState(final boolean z) {
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$grnpBPLf_-H8cdUzNqYeZBZEmDE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAntennaState$7$TunerCallbackAdapterExt(z);
            }
        });
    }

    public /* synthetic */ void lambda$onControlChanged$8$TunerCallbackAdapterExt(boolean z) {
        this.mCallback.onControlChanged(z);
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onControlChanged(final boolean z) {
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$w4Zm7lzFAJx0-GvKKFF6ewR0jy8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onControlChanged$8$TunerCallbackAdapterExt(z);
            }
        });
    }

    public /* synthetic */ void lambda$onBackgroundScanAvailabilityChange$9$TunerCallbackAdapterExt(boolean z) {
        this.mCallback.onBackgroundScanAvailabilityChange(z);
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onBackgroundScanAvailabilityChange(final boolean z) {
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$IVGjqx0YR5z-1PpC-mBazDJo8rE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onBackgroundScanAvailabilityChange$9$TunerCallbackAdapterExt(z);
            }
        });
    }

    public /* synthetic */ void lambda$onBackgroundScanComplete$10$TunerCallbackAdapterExt() {
        this.mCallback.onBackgroundScanComplete();
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onBackgroundScanComplete() {
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$S-aog4WgjzLPGO1j4t5RvM8fEzE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onBackgroundScanComplete$10$TunerCallbackAdapterExt();
            }
        });
    }

    public /* synthetic */ void lambda$onProgramListChanged$11$TunerCallbackAdapterExt() {
        this.mCallback.onProgramListChanged();
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onProgramListChanged() {
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$BVo4QYB8P88MV43GnRQ1bHzxtCY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onProgramListChanged$11$TunerCallbackAdapterExt();
            }
        });
    }

    public /* synthetic */ void lambda$onParametersUpdated$12$TunerCallbackAdapterExt(Map map) {
        this.mCallback.onParametersUpdated(map);
    }

    @Override // android.hardware.radio.RadioTuner.Callback
    public void onParametersUpdated(final Map<String, String> map) {
        this.mHandler.post(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$TunerCallbackAdapterExt$P7ske45ZfhN8pSedxbarXS0-Xd8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onParametersUpdated$12$TunerCallbackAdapterExt(map);
            }
        });
    }
}
