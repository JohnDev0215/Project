package android.hardware.radio;

import android.hardware.radio.ITunerCallback;
import android.hardware.radio.ProgramList;
import android.hardware.radio.RadioManager;
import android.hardware.radio.RadioTuner;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
class TunerCallbackAdapter extends ITunerCallback.Stub {
    private static final String TAG = "BroadcastRadio.TunerCallbackAdapter";
    private final RadioTuner.Callback mCallback;
    RadioManager.ProgramInfo mCurrentProgramInfo;
    private final Handler mHandler;
    List<RadioManager.ProgramInfo> mLastCompleteList;
    ProgramList mProgramList;
    private final Object mLock = new Object();
    boolean mIsAntennaConnected = true;
    private boolean mDelayedCompleteCallback = false;

    TunerCallbackAdapter(RadioTuner.Callback callback, Handler handler) {
        this.mCallback = callback;
        if (handler == null) {
            this.mHandler = new Handler(Looper.getMainLooper());
        } else {
            this.mHandler = handler;
        }
    }

    void close() {
        synchronized (this.mLock) {
            ProgramList programList = this.mProgramList;
            if (programList != null) {
                programList.close();
            }
        }
    }

    void setProgramListObserver(final ProgramList programList, final ProgramList.OnCloseListener onCloseListener) {
        Objects.requireNonNull(onCloseListener);
        synchronized (this.mLock) {
            if (this.mProgramList != null) {
                Log.w(TAG, "Previous program list observer wasn't properly closed, closing it...");
                this.mProgramList.close();
            }
            this.mProgramList = programList;
            if (programList == null) {
                return;
            }
            programList.setOnCloseListener(new ProgramList.OnCloseListener() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$LNpk8qph_S8JW0wUVJhMghOv4Ko
                @Override // android.hardware.radio.ProgramList.OnCloseListener
                public final void onClose() {
                    this.f$0.lambda$setProgramListObserver$0$TunerCallbackAdapter(programList, onCloseListener);
                }
            });
            programList.addOnCompleteListener(new ProgramList.OnCompleteListener() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$_WwPhxlBFK3tIJ5GUpJ1MstGiQA
                @Override // android.hardware.radio.ProgramList.OnCompleteListener
                public final void onComplete() {
                    this.f$0.lambda$setProgramListObserver$1$TunerCallbackAdapter(programList);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setProgramListObserver$0$TunerCallbackAdapter(ProgramList programList, ProgramList.OnCloseListener onCloseListener) {
        synchronized (this.mLock) {
            if (this.mProgramList != programList) {
                return;
            }
            this.mProgramList = null;
            this.mLastCompleteList = null;
            onCloseListener.onClose();
        }
    }

    public /* synthetic */ void lambda$setProgramListObserver$1$TunerCallbackAdapter(ProgramList programList) {
        synchronized (this.mLock) {
            if (this.mProgramList != programList) {
                return;
            }
            this.mLastCompleteList = programList.toList();
            if (this.mDelayedCompleteCallback) {
                Log.d(TAG, "Sending delayed onBackgroundScanComplete callback");
                sendBackgroundScanCompleteLocked();
            }
        }
    }

    List<RadioManager.ProgramInfo> getLastCompleteList() {
        List<RadioManager.ProgramInfo> list;
        synchronized (this.mLock) {
            list = this.mLastCompleteList;
        }
        return list;
    }

    void clearLastCompleteList() {
        synchronized (this.mLock) {
            this.mLastCompleteList = null;
        }
    }

    RadioManager.ProgramInfo getCurrentProgramInformation() {
        RadioManager.ProgramInfo programInfo;
        synchronized (this.mLock) {
            programInfo = this.mCurrentProgramInfo;
        }
        return programInfo;
    }

    boolean isAntennaConnected() {
        return this.mIsAntennaConnected;
    }

    public /* synthetic */ void lambda$onError$2$TunerCallbackAdapter(int i) {
        this.mCallback.onError(i);
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onError(final int i) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$CUOSDZdP2RHpTFRtvjZz4Rw93cI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onError$2$TunerCallbackAdapter(i);
            }
        });
    }

    public /* synthetic */ void lambda$onTuneFailed$3$TunerCallbackAdapter(int i, ProgramSelector programSelector) {
        this.mCallback.onTuneFailed(i, programSelector);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0024  */
    @Override // android.hardware.radio.ITunerCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onTuneFailed(final int r3, final android.hardware.radio.ProgramSelector r4) {
        /*
            r2 = this;
            android.os.Handler r0 = r2.mHandler
            android.hardware.radio.-$$Lambda$TunerCallbackAdapter$BQDVbK6gD_umyXRtRkFl19cT3wg r1 = new android.hardware.radio.-$$Lambda$TunerCallbackAdapter$BQDVbK6gD_umyXRtRkFl19cT3wg
            r1.<init>()
            r0.post(r1)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 == r4) goto L24
            r4 = -38
            if (r3 == r4) goto L24
            r4 = -32
            if (r3 == r4) goto L22
            r4 = -22
            if (r3 == r4) goto L24
            r4 = -19
            if (r3 == r4) goto L24
            r4 = -1
            if (r3 == r4) goto L22
            goto L42
        L22:
            r3 = 1
            goto L43
        L24:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "Got an error with no mapping to the legacy API ("
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r4 = "), doing a best-effort conversion to ERROR_SCAN_TIMEOUT"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "BroadcastRadio.TunerCallbackAdapter"
            android.util.Log.i(r4, r3)
        L42:
            r3 = 3
        L43:
            android.os.Handler r4 = r2.mHandler
            android.hardware.radio.-$$Lambda$TunerCallbackAdapter$7dly-eHM4KQiDBgmI6zoB4tgzBM r0 = new android.hardware.radio.-$$Lambda$TunerCallbackAdapter$7dly-eHM4KQiDBgmI6zoB4tgzBM
            r0.<init>()
            r4.post(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.radio.TunerCallbackAdapter.onTuneFailed(int, android.hardware.radio.ProgramSelector):void");
    }

    public /* synthetic */ void lambda$onTuneFailed$4$TunerCallbackAdapter(int i) {
        this.mCallback.onError(i);
    }

    public /* synthetic */ void lambda$onConfigurationChanged$5$TunerCallbackAdapter(RadioManager.BandConfig bandConfig) {
        this.mCallback.onConfigurationChanged(bandConfig);
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onConfigurationChanged(final RadioManager.BandConfig bandConfig) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$ulckZ0tjum3sKE_lWiMDe7-0EPQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onConfigurationChanged$5$TunerCallbackAdapter(bandConfig);
            }
        });
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onCurrentProgramInfoChanged(final RadioManager.ProgramInfo programInfo) {
        if (programInfo == null) {
            Log.e(TAG, "ProgramInfo must not be null");
            return;
        }
        synchronized (this.mLock) {
            this.mCurrentProgramInfo = programInfo;
        }
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$Gb3YJs4zpiwcq0kVaFtZekEmlJY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCurrentProgramInfoChanged$6$TunerCallbackAdapter(programInfo);
            }
        });
    }

    public /* synthetic */ void lambda$onCurrentProgramInfoChanged$6$TunerCallbackAdapter(RadioManager.ProgramInfo programInfo) {
        this.mCallback.onProgramInfoChanged(programInfo);
        RadioMetadata metadata = programInfo.getMetadata();
        if (metadata != null) {
            this.mCallback.onMetadataChanged(metadata);
        }
    }

    public /* synthetic */ void lambda$onTrafficAnnouncement$7$TunerCallbackAdapter(boolean z) {
        this.mCallback.onTrafficAnnouncement(z);
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onTrafficAnnouncement(final boolean z) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$Zw4r01meXpyw5TtW5TaaCm44Z0Q
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onTrafficAnnouncement$7$TunerCallbackAdapter(z);
            }
        });
    }

    public /* synthetic */ void lambda$onEmergencyAnnouncement$8$TunerCallbackAdapter(boolean z) {
        this.mCallback.onEmergencyAnnouncement(z);
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onEmergencyAnnouncement(final boolean z) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$y8QSpJi1hpOC-qcHt0PjErzAb1Q
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onEmergencyAnnouncement$8$TunerCallbackAdapter(z);
            }
        });
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onAntennaState(final boolean z) {
        this.mIsAntennaConnected = z;
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$_nXq4CJdGS05Qht3koabJ4em5TY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAntennaState$9$TunerCallbackAdapter(z);
            }
        });
    }

    public /* synthetic */ void lambda$onAntennaState$9$TunerCallbackAdapter(boolean z) {
        this.mCallback.onAntennaState(z);
    }

    public /* synthetic */ void lambda$onBackgroundScanAvailabilityChange$10$TunerCallbackAdapter(boolean z) {
        this.mCallback.onBackgroundScanAvailabilityChange(z);
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onBackgroundScanAvailabilityChange(final boolean z) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$h8Y16uvkyRnXKewOXuG9ymwMNpo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onBackgroundScanAvailabilityChange$10$TunerCallbackAdapter(z);
            }
        });
    }

    private void sendBackgroundScanCompleteLocked() {
        this.mDelayedCompleteCallback = false;
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$maj4j6ZUUdoIxqHvepjZqgkjUWg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$sendBackgroundScanCompleteLocked$11$TunerCallbackAdapter();
            }
        });
    }

    public /* synthetic */ void lambda$sendBackgroundScanCompleteLocked$11$TunerCallbackAdapter() {
        this.mCallback.onBackgroundScanComplete();
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onBackgroundScanComplete() {
        synchronized (this.mLock) {
            if (this.mLastCompleteList == null) {
                Log.i(TAG, "Got onBackgroundScanComplete callback, but the program list didn't get through yet. Delaying it...");
                this.mDelayedCompleteCallback = true;
            } else {
                sendBackgroundScanCompleteLocked();
            }
        }
    }

    public /* synthetic */ void lambda$onProgramListChanged$12$TunerCallbackAdapter() {
        this.mCallback.onProgramListChanged();
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onProgramListChanged() {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$USfUZqhuMGob_UDqR4U5L53hTgA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onProgramListChanged$12$TunerCallbackAdapter();
            }
        });
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onProgramListUpdated(final ProgramList.Chunk chunk) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$XcAA8AahEex7dKMqAXUxP9RFt3U
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onProgramListUpdated$13$TunerCallbackAdapter(chunk);
            }
        });
    }

    public /* synthetic */ void lambda$onProgramListUpdated$13$TunerCallbackAdapter(ProgramList.Chunk chunk) {
        synchronized (this.mLock) {
            ProgramList programList = this.mProgramList;
            if (programList == null) {
                return;
            }
            programList.apply((ProgramList.Chunk) Objects.requireNonNull(chunk));
        }
    }

    public /* synthetic */ void lambda$onParametersUpdated$14$TunerCallbackAdapter(Map map) {
        this.mCallback.onParametersUpdated(map);
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onParametersUpdated(final Map map) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.-$$Lambda$TunerCallbackAdapter$NCJgYjSzxbe02sY1TQh1AZa0UdY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onParametersUpdated$14$TunerCallbackAdapter(map);
            }
        });
    }
}
