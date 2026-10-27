package android.hardware;

import android.content.Context;
import android.hardware.ISensorPrivacyListener;
import android.hardware.ISensorPrivacyManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.ArrayMap;

/* JADX INFO: loaded from: classes.dex */
public final class SensorPrivacyManager {
    private static SensorPrivacyManager sInstance;
    private static final Object sInstanceLock = new Object();
    private final Context mContext;
    private final ArrayMap<OnSensorPrivacyChangedListener, ISensorPrivacyListener> mListeners = new ArrayMap<>();
    private final ISensorPrivacyManager mService;

    public interface OnSensorPrivacyChangedListener {
        void onSensorPrivacyChanged(boolean z);
    }

    private SensorPrivacyManager(Context context, ISensorPrivacyManager iSensorPrivacyManager) {
        this.mContext = context;
        this.mService = iSensorPrivacyManager;
    }

    public static SensorPrivacyManager getInstance(Context context) {
        SensorPrivacyManager sensorPrivacyManager;
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                try {
                    sInstance = new SensorPrivacyManager(context, ISensorPrivacyManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.SENSOR_PRIVACY_SERVICE)));
                } catch (ServiceManager.ServiceNotFoundException e) {
                    throw new IllegalStateException(e);
                }
            }
            sensorPrivacyManager = sInstance;
        }
        return sensorPrivacyManager;
    }

    public void setSensorPrivacy(boolean z) {
        try {
            this.mService.setSensorPrivacy(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addSensorPrivacyListener(final OnSensorPrivacyChangedListener onSensorPrivacyChangedListener) {
        synchronized (this.mListeners) {
            ISensorPrivacyListener iSensorPrivacyListener = this.mListeners.get(onSensorPrivacyChangedListener);
            if (iSensorPrivacyListener == null) {
                iSensorPrivacyListener = new ISensorPrivacyListener.Stub() { // from class: android.hardware.SensorPrivacyManager.1
                    @Override // android.hardware.ISensorPrivacyListener
                    public void onSensorPrivacyChanged(boolean z) {
                        onSensorPrivacyChangedListener.onSensorPrivacyChanged(z);
                    }
                };
                this.mListeners.put(onSensorPrivacyChangedListener, iSensorPrivacyListener);
            }
            try {
                this.mService.addSensorPrivacyListener(iSensorPrivacyListener);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void removeSensorPrivacyListener(OnSensorPrivacyChangedListener onSensorPrivacyChangedListener) {
        synchronized (this.mListeners) {
            ISensorPrivacyListener iSensorPrivacyListener = this.mListeners.get(onSensorPrivacyChangedListener);
            if (iSensorPrivacyListener != null) {
                this.mListeners.remove(iSensorPrivacyListener);
                try {
                    this.mService.removeSensorPrivacyListener(iSensorPrivacyListener);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }
    }

    public boolean isSensorPrivacyEnabled() {
        try {
            return this.mService.isSensorPrivacyEnabled();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
