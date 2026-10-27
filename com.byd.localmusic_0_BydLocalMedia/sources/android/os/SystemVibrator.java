package android.os;

import android.content.Context;
import android.media.AudioAttributes;
import android.os.IVibratorService;
import android.os.IVibratorStateListener;
import android.os.VibrationAttributes;
import android.os.Vibrator;
import android.util.ArrayMap;
import android.util.Log;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class SystemVibrator extends Vibrator {
    private static final String TAG = "Vibrator";
    private final Context mContext;
    private final ArrayMap<Vibrator.OnVibratorStateChangedListener, OnVibratorStateChangedListenerDelegate> mDelegates;
    private final IVibratorService mService;
    private final Binder mToken;

    public SystemVibrator() {
        this.mToken = new Binder();
        this.mDelegates = new ArrayMap<>();
        this.mContext = null;
        this.mService = IVibratorService.Stub.asInterface(ServiceManager.getService(Context.VIBRATOR_SERVICE));
    }

    public SystemVibrator(Context context) {
        super(context);
        this.mToken = new Binder();
        this.mDelegates = new ArrayMap<>();
        this.mContext = context;
        this.mService = IVibratorService.Stub.asInterface(ServiceManager.getService(Context.VIBRATOR_SERVICE));
    }

    @Override // android.os.Vibrator
    public boolean hasVibrator() {
        IVibratorService iVibratorService = this.mService;
        if (iVibratorService == null) {
            Log.w(TAG, "Failed to vibrate; no vibrator service.");
            return false;
        }
        try {
            return iVibratorService.hasVibrator();
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.os.Vibrator
    public boolean isVibrating() {
        IVibratorService iVibratorService = this.mService;
        if (iVibratorService == null) {
            Log.w(TAG, "Failed to vibrate; no vibrator service.");
            return false;
        }
        try {
            return iVibratorService.isVibrating();
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class OnVibratorStateChangedListenerDelegate extends IVibratorStateListener.Stub {
        private final Executor mExecutor;
        private final Vibrator.OnVibratorStateChangedListener mListener;

        OnVibratorStateChangedListenerDelegate(Vibrator.OnVibratorStateChangedListener onVibratorStateChangedListener, Executor executor) {
            this.mExecutor = executor;
            this.mListener = onVibratorStateChangedListener;
        }

        public /* synthetic */ void lambda$onVibrating$0$SystemVibrator$OnVibratorStateChangedListenerDelegate(boolean z) {
            this.mListener.onVibratorStateChanged(z);
        }

        @Override // android.os.IVibratorStateListener
        public void onVibrating(final boolean z) {
            this.mExecutor.execute(new Runnable() { // from class: android.os.-$$Lambda$SystemVibrator$OnVibratorStateChangedListenerDelegate$TagAqaiuke_42S8UkSIiiGTN_8Y
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onVibrating$0$SystemVibrator$OnVibratorStateChangedListenerDelegate(z);
                }
            });
        }
    }

    @Override // android.os.Vibrator
    public void addVibratorStateListener(Executor executor, Vibrator.OnVibratorStateChangedListener onVibratorStateChangedListener) {
        Objects.requireNonNull(onVibratorStateChangedListener);
        Objects.requireNonNull(executor);
        if (this.mService == null) {
            Log.w(TAG, "Failed to add vibrate state listener; no vibrator service.");
            return;
        }
        synchronized (this.mDelegates) {
            if (this.mDelegates.containsKey(onVibratorStateChangedListener)) {
                Log.w(TAG, "Listener already registered.");
                return;
            }
            try {
                OnVibratorStateChangedListenerDelegate onVibratorStateChangedListenerDelegate = new OnVibratorStateChangedListenerDelegate(onVibratorStateChangedListener, executor);
                if (!this.mService.registerVibratorStateListener(onVibratorStateChangedListenerDelegate)) {
                    Log.w(TAG, "Failed to register vibrate state listener");
                } else {
                    this.mDelegates.put(onVibratorStateChangedListener, onVibratorStateChangedListenerDelegate);
                }
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @Override // android.os.Vibrator
    public void addVibratorStateListener(Vibrator.OnVibratorStateChangedListener onVibratorStateChangedListener) {
        Objects.requireNonNull(onVibratorStateChangedListener);
        Context context = this.mContext;
        if (context == null) {
            Log.w(TAG, "Failed to add vibrate state listener; no vibrator context.");
        } else {
            addVibratorStateListener(context.getMainExecutor(), onVibratorStateChangedListener);
        }
    }

    @Override // android.os.Vibrator
    public void removeVibratorStateListener(Vibrator.OnVibratorStateChangedListener onVibratorStateChangedListener) {
        Objects.requireNonNull(onVibratorStateChangedListener);
        if (this.mService == null) {
            Log.w(TAG, "Failed to remove vibrate state listener; no vibrator service.");
            return;
        }
        synchronized (this.mDelegates) {
            if (this.mDelegates.containsKey(onVibratorStateChangedListener)) {
                try {
                    if (!this.mService.unregisterVibratorStateListener(this.mDelegates.get(onVibratorStateChangedListener))) {
                        Log.w(TAG, "Failed to unregister vibrate state listener");
                        return;
                    }
                    this.mDelegates.remove(onVibratorStateChangedListener);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }
    }

    @Override // android.os.Vibrator
    public boolean hasAmplitudeControl() {
        IVibratorService iVibratorService = this.mService;
        if (iVibratorService == null) {
            Log.w(TAG, "Failed to check amplitude control; no vibrator service.");
            return false;
        }
        try {
            return iVibratorService.hasAmplitudeControl();
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.os.Vibrator
    public boolean setAlwaysOnEffect(int i, String str, int i2, VibrationEffect vibrationEffect, AudioAttributes audioAttributes) {
        if (this.mService == null) {
            Log.w(TAG, "Failed to set always-on effect; no vibrator service.");
            return false;
        }
        try {
            return this.mService.setAlwaysOnEffect(i, str, i2, vibrationEffect, new VibrationAttributes.Builder(audioAttributes, vibrationEffect).build());
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to set always-on effect.", e);
            return false;
        }
    }

    @Override // android.os.Vibrator
    public void vibrate(int i, String str, VibrationEffect vibrationEffect, String str2, AudioAttributes audioAttributes) {
        if (this.mService == null) {
            Log.w(TAG, "Failed to vibrate; no vibrator service.");
            return;
        }
        if (audioAttributes == null) {
            try {
                audioAttributes = new AudioAttributes.Builder().build();
            } catch (RemoteException e) {
                Log.w(TAG, "Failed to vibrate.", e);
                return;
            }
        }
        this.mService.vibrate(i, str, vibrationEffect, new VibrationAttributes.Builder(audioAttributes, vibrationEffect).build(), str2, this.mToken);
    }

    @Override // android.os.Vibrator
    public int[] areEffectsSupported(int... iArr) {
        try {
            return this.mService.areEffectsSupported(iArr);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to query effect support");
            throw e.rethrowAsRuntimeException();
        }
    }

    @Override // android.os.Vibrator
    public boolean[] arePrimitivesSupported(int... iArr) {
        try {
            return this.mService.arePrimitivesSupported(iArr);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to query effect support");
            throw e.rethrowAsRuntimeException();
        }
    }

    @Override // android.os.Vibrator
    public void cancel() {
        IVibratorService iVibratorService = this.mService;
        if (iVibratorService == null) {
            return;
        }
        try {
            iVibratorService.cancelVibrate(this.mToken);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to cancel vibration.", e);
        }
    }
}
