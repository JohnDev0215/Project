package android.car.media;

import android.car.Car;
import android.car.CarManagerBase;
import android.car.media.ICarEqualizerCallback;
import android.os.RemoteException;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class CarEqualizer extends CarManagerBase {
    public static final int INVALID_BAND_INDEX = -1;
    public static final int INVALID_FREQUENCY = -1;
    public static final int INVALID_PRESET = -1;
    public static final String INVALID_PRESET_NAME = "Invalid";
    private static final String TAG = "CarEqualizer";
    private final ICarEqualizerCallback mCarEqualizerCallbackImpl;
    private final CopyOnWriteArrayList<CarEqualizerCallback> mCarEqualizerCallbacks;
    private ICarEqualizer mEqualizer;

    public static abstract class CarEqualizerCallback {
        public void onBandLevelChanged(int i) {
        }

        public void onPresetChanged() {
        }
    }

    public CarEqualizer(Car car, CarEnhancedAudioManager carEnhancedAudioManager) {
        super(car);
        this.mCarEqualizerCallbackImpl = new ICarEqualizerCallback.Stub() { // from class: android.car.media.CarEqualizer.1
            @Override // android.car.media.ICarEqualizerCallback
            public void onPresetChanged() {
                Iterator it = CarEqualizer.this.mCarEqualizerCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarEqualizerCallback) it.next()).onPresetChanged();
                }
            }

            @Override // android.car.media.ICarEqualizerCallback
            public void onBandLevelChanged(int i) {
                Iterator it = CarEqualizer.this.mCarEqualizerCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarEqualizerCallback) it.next()).onBandLevelChanged(i);
                }
            }
        };
        this.mEqualizer = carEnhancedAudioManager.getCarEqualizerInstance();
        this.mCarEqualizerCallbacks = new CopyOnWriteArrayList<>();
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        if (this.mEqualizer != null) {
            unregisterEqualizerCallback();
        }
    }

    public int getNumberOfBands() {
        try {
            return this.mEqualizer.getNumberOfBands();
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    public int[] getBandLevelRange(int i) {
        try {
            return this.mEqualizer.getBandLevelRange(i);
        } catch (RemoteException e) {
            return (int[]) handleRemoteExceptionFromCarService(e, new int[2]);
        }
    }

    public void setBandLevel(int i, int i2) {
        try {
            this.mEqualizer.setBandLevel(i, i2);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public int getBandLevel(int i) {
        try {
            return this.mEqualizer.getBandLevel(i);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    public int getCenterFreq(int i) {
        try {
            return this.mEqualizer.getCenterFreq(i);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, -1)).intValue();
        }
    }

    public int[] getBandFreqRange(int i) {
        try {
            return this.mEqualizer.getBandFreqRange(i);
        } catch (RemoteException e) {
            return (int[]) handleRemoteExceptionFromCarService(e, new int[2]);
        }
    }

    public int getBand(int i) {
        try {
            return this.mEqualizer.getBand(i);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, -1)).intValue();
        }
    }

    public int getCurrentPreset() {
        try {
            return this.mEqualizer.getCurrentPreset();
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, -1)).intValue();
        }
    }

    public void usePreset(int i) {
        try {
            this.mEqualizer.usePreset(i);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public int getNumberOfPresets() {
        try {
            return this.mEqualizer.getNumberOfPresets();
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    public String getPresetName(int i) {
        try {
            return this.mEqualizer.getPresetName(i);
        } catch (RemoteException e) {
            return (String) handleRemoteExceptionFromCarService(e, "Invalid");
        }
    }

    public boolean isPresetSupported() {
        try {
            return this.mEqualizer.isPresetSupported();
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public boolean isBandSupported() {
        try {
            return this.mEqualizer.isBandSupported();
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, false)).booleanValue();
        }
    }

    public void registerCarEqualizerCallback(CarEqualizerCallback carEqualizerCallback) {
        Objects.requireNonNull(carEqualizerCallback);
        if (this.mCarEqualizerCallbacks.isEmpty()) {
            registerEqualizerCallback();
        }
        this.mCarEqualizerCallbacks.add(carEqualizerCallback);
    }

    public void unregisterCarEqualizerCallback(CarEqualizerCallback carEqualizerCallback) {
        Objects.requireNonNull(carEqualizerCallback);
        if (this.mCarEqualizerCallbacks.remove(carEqualizerCallback) && this.mCarEqualizerCallbacks.isEmpty()) {
            unregisterEqualizerCallback();
        }
    }

    private void registerEqualizerCallback() {
        try {
            this.mEqualizer.registerEqualizerCallback(this.mCarEqualizerCallbackImpl.asBinder());
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    private void unregisterEqualizerCallback() {
        try {
            this.mEqualizer.unregisterEqualizerCallback(this.mCarEqualizerCallbackImpl.asBinder());
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }
}
