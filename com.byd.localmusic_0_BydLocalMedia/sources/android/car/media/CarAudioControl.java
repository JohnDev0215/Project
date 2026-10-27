package android.car.media;

import android.car.Car;
import android.car.CarManagerBase;
import android.car.media.ICarAudioControlCallback;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public class CarAudioControl extends CarManagerBase {
    public static final int CONTROL_FLAG_CALLBACKABLE = 2;
    public static final int CONTROL_FLAG_NONE = 0;
    public static final int CONTROL_FLAG_PERSISTENT = 1;
    public static final int CONTROL_TYPE_BOOL = 1;
    public static final int CONTROL_TYPE_ENUM = 3;
    public static final int CONTROL_TYPE_FLOAT = 2;
    public static final int CONTROL_TYPE_INTEGER = 0;
    public static final int CONTROL_TYPE_INVALID = -1;
    public static final int INVALID_CONTROL_INDEX = -1;
    public static final String INVALID_CONTROL_NAME = "Invalid";
    private CarAudioControlCallback mCallback;
    private final ICarAudioControlCallback mCarAudioControlCallbackImpl;
    private ICarAudioControl mControl;
    private CarAudioControlFilter mFilter;

    public static abstract class CarAudioControlCallback {
        public void onAudioControlChanged(int i) {
        }
    }

    public CarAudioControl(Car car, CarEnhancedAudioManager carEnhancedAudioManager) {
        super(car);
        this.mControl = null;
        this.mFilter = new CarAudioControlFilter();
        this.mCarAudioControlCallbackImpl = new ICarAudioControlCallback.Stub() { // from class: android.car.media.CarAudioControl.1
            @Override // android.car.media.ICarAudioControlCallback
            public void onControlChanged(int i) {
                if (CarAudioControl.this.mCallback != null) {
                    CarAudioControl.this.mCallback.onAudioControlChanged(i);
                }
            }
        };
        this.mControl = carEnhancedAudioManager.getCarAudioControlInstance();
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
        if (this.mControl != null) {
            unregisterAudioControlCallback();
        }
    }

    public void setCarAudioControlCallback(CarAudioControlCallback carAudioControlCallback) {
        this.mCallback = carAudioControlCallback;
    }

    public int getCount() {
        try {
            return this.mControl.getCount();
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    public String getName(int i) {
        try {
            return this.mControl.getName(i);
        } catch (RemoteException e) {
            return (String) handleRemoteExceptionFromCarService(e, "Invalid");
        }
    }

    public int getType(int i) {
        try {
            return this.mControl.getType(i);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, -1)).intValue();
        }
    }

    public int getFlags(int i) {
        try {
            return this.mControl.getFlags(i);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, 0)).intValue();
        }
    }

    public void setIntegerValue(int i, int i2) {
        try {
            this.mControl.setIntegerValue(i, i2);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public int getIntegerValue(int i, int i2) {
        try {
            return this.mControl.getIntegerValue(i);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, Integer.valueOf(i2))).intValue();
        }
    }

    public int[] getIntegerValueRange(int i) {
        try {
            return this.mControl.getIntegerValueRange(i);
        } catch (RemoteException e) {
            return (int[]) handleRemoteExceptionFromCarService(e, new int[2]);
        }
    }

    public void setFloatValue(int i, float f) {
        try {
            this.mControl.setFloatValue(i, f);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public float getFloatValue(int i, int i2) {
        try {
            return this.mControl.getFloatValue(i);
        } catch (RemoteException e) {
            return ((Integer) handleRemoteExceptionFromCarService(e, Integer.valueOf(i2))).intValue();
        }
    }

    public float[] getFloatValueRange(int i) {
        try {
            return this.mControl.getFloatValueRange(i);
        } catch (RemoteException e) {
            return (float[]) handleRemoteExceptionFromCarService(e, new float[2]);
        }
    }

    public void checkAndUpdateCarAudioControlCallback(int i, boolean z) {
        if (z && this.mFilter.isEmpty()) {
            registerAudioControlCallback();
        }
        boolean z2 = false;
        if (!z || this.mFilter.contains(i) ? !(z || !this.mFilter.contains(i) || !this.mFilter.remove(i)) : this.mFilter.add(i)) {
            z2 = true;
        }
        if (z2) {
            if (this.mFilter.isEmpty()) {
                unregisterAudioControlCallback();
            } else {
                updateAudioControlCallbackFilter();
            }
        }
    }

    public static String getTypeFriendly(int i) {
        if (i == 0) {
            return "Integer";
        }
        if (i == 1) {
            return "Bool";
        }
        if (i == 2) {
            return "Float";
        }
        if (i == 3) {
            return "Enum";
        }
        return "Unsupported control type " + i;
    }

    public static String getFlagFriendly(int i) {
        if (i == 0) {
            return "None";
        }
        if (i == 1) {
            return "Persistent";
        }
        if (i == 2) {
            return "Callbackable";
        }
        return "Unsupported control flag " + i;
    }

    private void registerAudioControlCallback() {
        try {
            this.mControl.registerAudioControlCallback(this.mCarAudioControlCallbackImpl.asBinder());
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    private void unregisterAudioControlCallback() {
        try {
            this.mControl.unregisterAudioControlCallback(this.mCarAudioControlCallbackImpl.asBinder());
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    private void updateAudioControlCallbackFilter() {
        try {
            this.mControl.updateAudioControlCallbackFilter(this.mCarAudioControlCallbackImpl.asBinder(), this.mFilter);
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }
}
