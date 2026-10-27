package android.media.tv.tuner;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class Lnb implements AutoCloseable {
    public static final int EVENT_TYPE_DISEQC_RX_OVERFLOW = 0;
    public static final int EVENT_TYPE_DISEQC_RX_PARITY_ERROR = 2;
    public static final int EVENT_TYPE_DISEQC_RX_TIMEOUT = 1;
    public static final int EVENT_TYPE_LNB_OVERLOAD = 3;
    public static final int POSITION_A = 1;
    public static final int POSITION_B = 2;
    public static final int POSITION_UNDEFINED = 0;
    private static final String TAG = "Lnb";
    public static final int TONE_CONTINUOUS = 1;
    public static final int TONE_NONE = 0;
    public static final int VOLTAGE_11V = 2;
    public static final int VOLTAGE_12V = 3;
    public static final int VOLTAGE_13V = 4;
    public static final int VOLTAGE_14V = 5;
    public static final int VOLTAGE_15V = 6;
    public static final int VOLTAGE_18V = 7;
    public static final int VOLTAGE_19V = 8;
    public static final int VOLTAGE_5V = 1;
    public static final int VOLTAGE_NONE = 0;
    LnbCallback mCallback;
    Executor mExecutor;
    int mId;
    private Boolean mIsClosed = false;
    private final Object mLock = new Object();
    private long mNativeContext;
    Tuner mTuner;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EventType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Position {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Tone {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Voltage {
    }

    private native int nativeClose();

    private native int nativeSendDiseqcMessage(byte[] bArr);

    private native int nativeSetSatellitePosition(int i);

    private native int nativeSetTone(int i);

    private native int nativeSetVoltage(int i);

    private Lnb(int i) {
        this.mId = i;
    }

    void setCallback(Executor executor, LnbCallback lnbCallback, Tuner tuner) {
        this.mCallback = lnbCallback;
        this.mExecutor = executor;
        this.mTuner = tuner;
    }

    private void onEvent(final int i) {
        Executor executor = this.mExecutor;
        if (executor == null || this.mCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Lnb$IV6NQ1_DZcILU-MY88njae06xhs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onEvent$0$Lnb(i);
            }
        });
    }

    public /* synthetic */ void lambda$onEvent$0$Lnb(int i) {
        this.mCallback.onEvent(i);
    }

    private void onDiseqcMessage(final byte[] bArr) {
        Executor executor = this.mExecutor;
        if (executor == null || this.mCallback == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.-$$Lambda$Lnb$FxxuwmgmY3zg5Qj8CbdUkPw-Lnk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDiseqcMessage$1$Lnb(bArr);
            }
        });
    }

    public /* synthetic */ void lambda$onDiseqcMessage$1$Lnb(byte[] bArr) {
        this.mCallback.onDiseqcMessage(bArr);
    }

    boolean isClosed() {
        boolean zBooleanValue;
        synchronized (this.mLock) {
            zBooleanValue = this.mIsClosed.booleanValue();
        }
        return zBooleanValue;
    }

    public int setVoltage(int i) {
        int iNativeSetVoltage;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed.booleanValue());
            iNativeSetVoltage = nativeSetVoltage(i);
        }
        return iNativeSetVoltage;
    }

    public int setTone(int i) {
        int iNativeSetTone;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed.booleanValue());
            iNativeSetTone = nativeSetTone(i);
        }
        return iNativeSetTone;
    }

    public int setSatellitePosition(int i) {
        int iNativeSetSatellitePosition;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed.booleanValue());
            iNativeSetSatellitePosition = nativeSetSatellitePosition(i);
        }
        return iNativeSetSatellitePosition;
    }

    public int sendDiseqcMessage(byte[] bArr) {
        int iNativeSendDiseqcMessage;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed.booleanValue());
            iNativeSendDiseqcMessage = nativeSendDiseqcMessage(bArr);
        }
        return iNativeSendDiseqcMessage;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        synchronized (this.mLock) {
            if (this.mIsClosed.booleanValue()) {
                return;
            }
            int iNativeClose = nativeClose();
            if (iNativeClose != 0) {
                TunerUtils.throwExceptionForResult(iNativeClose, "Failed to close LNB");
            } else {
                this.mIsClosed = true;
                this.mTuner.releaseLnb();
            }
        }
    }
}
