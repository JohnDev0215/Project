package com.android.car.radio.platform;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.radio.ProgramList;
import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.hardware.radio.RadioTuner;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.HwAudioSource;
import android.text.TextUtils;
import android.util.Log;
import com.android.car.radio.platform.RadioTunerExt;
import com.android.car.radio.platform.TunerCallbackAdapterExt;
import com.blankj.utilcode.util.Utils;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.common.AppExecutors;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.ToastUtil;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class RadioTunerExt {
    private static final String TAG = "BcRadioApp.tunerext";
    private long finalTuneTimer;
    private HwAudioSource mHwAudioSource;
    private TuneCallback mOperationResultCb;
    private ProgramSelector mOperationSelector;
    public final RadioTuner mTuner;
    private final Object mLock = new Object();
    private final Object mTuneLock = new Object();

    public interface TuneCallback {
        void onFinished(boolean z);

        default TuneCallback alsoCall(final TuneCallback tuneCallback) {
            return new TuneCallback() { // from class: com.android.car.radio.platform.-$$Lambda$RadioTunerExt$TuneCallback$MHT64i5QoOeUY-iUxi08sgGNyS4
                @Override // com.android.car.radio.platform.RadioTunerExt.TuneCallback
                public final void onFinished(boolean z) {
                    RadioTunerExt.TuneCallback.lambda$alsoCall$0(this.f$0, tuneCallback, z);
                }
            };
        }

        static /* synthetic */ void lambda$alsoCall$0(TuneCallback _this, TuneCallback tuneCallback, boolean z) {
            _this.onFinished(z);
            tuneCallback.onFinished(z);
        }
    }

    RadioTunerExt(Context context, RadioTuner radioTuner, TunerCallbackAdapterExt tunerCallbackAdapterExt) {
        this.mTuner = (RadioTuner) Objects.requireNonNull(radioTuner);
        tunerCallbackAdapterExt.setTuneFailedCallback(new TunerCallbackAdapterExt.TuneFailedCallback() { // from class: com.android.car.radio.platform.-$$Lambda$RadioTunerExt$HS3C5PeB4X2vTBtg7Qt3CQbOo7s
            @Override // com.android.car.radio.platform.TunerCallbackAdapterExt.TuneFailedCallback
            public final void onTuneFailed(int i, ProgramSelector programSelector) {
                this.f$0.onTuneFailed(i, programSelector);
            }
        });
        tunerCallbackAdapterExt.setProgramInfoCallback(new TunerCallbackAdapterExt.ProgramInfoCallback() { // from class: com.android.car.radio.platform.-$$Lambda$RadioTunerExt$ZRaHwfgCzcp7T2MZSbqlmtqtLEQ
            @Override // com.android.car.radio.platform.TunerCallbackAdapterExt.ProgramInfoCallback
            public final void onProgramInfoChanged(RadioManager.ProgramInfo programInfo) {
                this.f$0.onProgramInfoChanged(programInfo);
            }
        });
        AudioDeviceInfo audioDeviceInfoFindTunerDevice = findTunerDevice(context, null);
        if (audioDeviceInfoFindTunerDevice == null) {
            LogUtil.e(TAG, "No TUNER_DEVICE found on board");
        } else {
            this.mHwAudioSource = new HwAudioSource.Builder().setAudioDeviceInfo(audioDeviceInfoFindTunerDevice).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).build()).build();
        }
    }

    public void setServiceFollow(boolean z) {
        LogUtil.d(TAG, "setServiceFollow enter with enable = " + z);
        this.mTuner.setConfigFlag(6, z);
        this.mTuner.setConfigFlag(7, z);
        LogUtil.d(TAG, "setServiceFollow leave with enable = " + z);
    }

    public void setAlternativeFrequency(final boolean z) {
        LogUtil.d(TAG, "setAlternativeFrequency enter with enable = " + z);
        if (OnLineMusic.VENDOR_CODE == 2) {
            AppExecutors.getInstance().networkIO().execute(new Runnable() { // from class: com.android.car.radio.platform.-$$Lambda$RadioTunerExt$PSE7XuUSkCbtKtX3SAHlLG7flfE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setAlternativeFrequency$0$RadioTunerExt(z);
                }
            });
        } else {
            this.mTuner.setConfigFlag(4, z);
        }
        LogUtil.d(TAG, "setAlternativeFrequency leave with enable = " + z);
    }

    public /* synthetic */ void lambda$setAlternativeFrequency$0$RadioTunerExt(boolean z) {
        try {
            HashMap map = new HashMap();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("RdsSettings", z ? 2 : 1);
            map.put("com.desaysv.rds_settings", jSONObject.toString());
            this.mTuner.setParameters(map);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public Bitmap getMetadataImage(int i) {
        return this.mTuner.getMetadataImage(i);
    }

    public boolean setMuted(boolean z) {
        if (this.mHwAudioSource == null) {
            LogUtil.e(TAG, "No TUNER_DEVICE found on board");
            return false;
        }
        synchronized (this.mLock) {
            if (z) {
                this.mHwAudioSource.stop();
            } else {
                this.mHwAudioSource.start();
            }
        }
        return true;
    }

    public void seek(boolean z, TuneCallback tuneCallback) {
        synchronized (this.mTuneLock) {
            synchronized (this.mLock) {
                markOperationFinishedLocked(false);
                this.mOperationResultCb = tuneCallback;
            }
            try {
                int iScan = this.mTuner.scan(z ? 0 : 1, false);
                if (iScan != 0) {
                    throw new RuntimeException("Seek failed with result of " + iScan);
                }
            } catch (Throwable th) {
                ToastUtil.showCenter(Utils.getApp().getResources().getString(R.string.radio_seek_failed));
                LogUtil.e("Error! Seek fail");
                th.printStackTrace();
            }
        }
    }

    public void step(boolean z, TuneCallback tuneCallback) {
        synchronized (this.mTuneLock) {
            markOperationFinishedLocked(false);
            this.mOperationResultCb = tuneCallback;
        }
        int iStep = this.mTuner.step(!z ? 1 : 0, false);
        if (iStep == 0) {
            return;
        }
        throw new RuntimeException("Step failed with result of " + iStep);
    }

    public void tune(ProgramSelector programSelector, TuneCallback tuneCallback) {
        if (!this.mTuner.hasControl()) {
            LogUtil.w("tune: RadioTunerExt mTuner hasControl false, just return");
            this.mOperationSelector = null;
            this.mOperationResultCb = null;
            return;
        }
        synchronized (this.mTuneLock) {
            synchronized (this.mLock) {
                long jCurrentTimeMillis = System.currentTimeMillis() - this.finalTuneTimer;
                if (jCurrentTimeMillis > 1000) {
                    markOperationFinishedLocked(false);
                } else {
                    LogUtil.d("tune: RadioTunerExt mTuner do not markOperationFinishedLocked, space = " + jCurrentTimeMillis);
                }
                if (!RadioServiceWrapper.getInstance().isRadioHalReady) {
                    LogUtil.e("tune: RadioTunerExt mTuner do not tune, isRadioHalReady is false, just return.");
                    return;
                }
                this.mOperationSelector = programSelector;
                this.mOperationResultCb = tuneCallback;
                this.finalTuneTimer = System.currentTimeMillis();
                this.mTuner.tune(programSelector);
            }
        }
    }

    private AudioDeviceInfo findTunerDevice(Context context, String str) {
        for (AudioDeviceInfo audioDeviceInfo : ((AudioManager) context.getSystemService("audio")).getDevices(1)) {
            if (audioDeviceInfo.getType() == 16 && (TextUtils.isEmpty(str) || str.equals(audioDeviceInfo.getAddress()))) {
                return audioDeviceInfo;
            }
        }
        return null;
    }

    private void markOperationFinishedLocked(boolean z) {
        if (this.mOperationResultCb == null) {
            return;
        }
        if (Log.isLoggable(TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Tune operation for ");
            sb.append(this.mOperationSelector);
            sb.append(z ? " succeeded" : " failed");
            Log.d(TAG, sb.toString());
        }
        TuneCallback tuneCallback = this.mOperationResultCb;
        this.mOperationSelector = null;
        this.mOperationResultCb = null;
        tuneCallback.onFinished(z);
        if (this.mOperationSelector != null) {
            throw new IllegalStateException("Can't tune in callback's failed branch. It might interfere with tune operation that requested current one cancellation");
        }
    }

    private boolean isMatching(ProgramSelector programSelector, ProgramSelector programSelector2) {
        final ProgramSelector.Identifier primaryId = programSelector.getPrimaryId();
        return Stream.of((Object[]) programSelector2.getAllIds(primaryId.getType())).anyMatch(new Predicate() { // from class: com.android.car.radio.platform.-$$Lambda$RadioTunerExt$4xT8qH6xqbbluCed-FgZzntYA1M
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return primaryId.equals((ProgramSelector.Identifier) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProgramInfoChanged(RadioManager.ProgramInfo programInfo) {
        synchronized (this.mLock) {
            if (this.mOperationResultCb == null) {
                return;
            }
            if (this.mOperationSelector == null || isMatching(this.mOperationSelector, programInfo.getSelector())) {
                markOperationFinishedLocked(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTuneFailed(int i, ProgramSelector programSelector) {
        synchronized (this.mLock) {
            if (this.mOperationResultCb == null) {
                return;
            }
            boolean z = true;
            boolean z2 = this.mOperationSelector == null;
            if (programSelector != null) {
                z = false;
            }
            if (z2 != z) {
                return;
            }
            if (this.mOperationSelector == null || isMatching(this.mOperationSelector, programSelector)) {
                markOperationFinishedLocked(false);
            }
        }
    }

    public void cancel() {
        synchronized (this.mTuneLock) {
            LogUtil.e(TAG, "Tuner cancel enter.");
            synchronized (this.mLock) {
                markOperationFinishedLocked(false);
            }
            int iCancel = this.mTuner.cancel();
            if (iCancel != 0) {
                LogUtil.e(TAG, "Cancel failed with result of " + iCancel);
            }
        }
    }

    public ProgramList getDynamicProgramList(ProgramList.Filter filter) {
        return this.mTuner.getDynamicProgramList(filter);
    }

    public void close() {
        synchronized (this.mLock) {
            markOperationFinishedLocked(false);
        }
        HwAudioSource hwAudioSource = this.mHwAudioSource;
        if (hwAudioSource != null) {
            hwAudioSource.stop();
            this.mHwAudioSource = null;
        }
        this.mTuner.close();
    }
}
