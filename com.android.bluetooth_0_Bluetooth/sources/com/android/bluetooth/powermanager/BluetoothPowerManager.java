package com.android.bluetooth.powermanager;

import android.bluetooth.BluetoothAdapter;
import android.car.Car;
import android.car.hardware.power.CarPowerManager;
import android.content.Context;
import android.hardware.bluetooth.V1_0.IBluetoothHci;
import android.os.RemoteException;
import android.util.Log;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPowerManager implements CarPowerManager.CarPowerStateListener {
    private static final boolean DBG = true;
    private static final String TAG = "BluetoothPowerManager";
    private static BluetoothPowerManager mBluetoothPowerManager = null;
    private final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private Car mCar;
    private CarPowerManager mCarPowerManager;
    private Context mContext;

    public BluetoothPowerManager(Context context) {
        this.mContext = context;
    }

    public static BluetoothPowerManager createInstance(Context context) {
        if (mBluetoothPowerManager == null) {
            BluetoothPowerManager bluetoothPowerManager = new BluetoothPowerManager(context);
            mBluetoothPowerManager = bluetoothPowerManager;
            bluetoothPowerManager.init();
        }
        return mBluetoothPowerManager;
    }

    public void init() {
        logd("init");
        initCar();
    }

    public void deinit() {
        logd("deinit");
        deinitCar();
    }

    private void initCar() {
        logd("initCar");
        deinitCar();
        this.mCar = Car.createCar(this.mContext, null, -1L, new Car.CarServiceLifecycleListener() { // from class: com.android.bluetooth.powermanager.-$$Lambda$BluetoothPowerManager$dT-jcU2-obVAzqXCvQxDtZ6-Sy4
            @Override // android.car.Car.CarServiceLifecycleListener
            public final void onLifecycleChanged(Car car, boolean z) {
                this.f$0.lambda$initCar$0$BluetoothPowerManager(car, z);
            }
        });
        logd("initCar done, mCar: " + this.mCar);
    }

    public /* synthetic */ void lambda$initCar$0$BluetoothPowerManager(Car car, boolean ready) {
        if (ready) {
            initCarPowerManager(car);
        }
    }

    private void initCarPowerManager(Car car) {
        logd("initCarPowerManager");
        CarPowerManager carPowerManager = (CarPowerManager) car.getCarManager("power");
        this.mCarPowerManager = carPowerManager;
        if (carPowerManager != null) {
            try {
                carPowerManager.setListener(this);
            } catch (IllegalStateException e) {
                loge("Can't set CarPowerManager listener");
            }
        } else {
            loge("Fail to get CarPowerManager");
        }
        logd("initCarPowerManager done, mCarPowerManager: " + this.mCarPowerManager);
    }

    private void deinitCarPowerManager() {
        logd("deinitCarPowerManager, mCarPowerManager: " + this.mCarPowerManager);
        CarPowerManager carPowerManager = this.mCarPowerManager;
        if (carPowerManager != null) {
            carPowerManager.clearListener();
            this.mCarPowerManager = null;
        }
    }

    private void deinitCar() {
        deinitCarPowerManager();
        Car car = this.mCar;
        if (car != null) {
            if (car.isConnected()) {
                this.mCar.disconnect();
            }
            this.mCar = null;
        }
    }

    private int getBluetoothState() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.getState();
        }
        return Integer.MIN_VALUE;
    }

    public void onStateChanged(int state) {
        logd("onStateChanged state: " + state);
        if (state == 2) {
            logd("CPM state: SUSPEND_ENTER");
            handleSuspendEnter();
            return;
        }
        if (state == 3) {
            logd("CPM state: SUSPEND_EXIT");
            return;
        }
        if (state == 5) {
            logd("CPM state: SHUTDOWN_ENTER");
        } else if (state == 7) {
            logd("CPM state: SHUTDOWN_PREPARE");
        } else if (state == 8) {
            logd("CPM state: SHUTDOWN_CANCELLED");
        }
    }

    private void handleSuspendEnter() {
        int state = getBluetoothState();
        logd("handleSuspendEnter, bluetooth state: " + state + "(" + mapBluetoothState2String(state) + ")");
        closeHci();
    }

    private void closeHci() {
        IBluetoothHci bluetoothHci = getBluetoothHci();
        if (bluetoothHci == null) {
            loge("IBluetoothHci null");
            return;
        }
        try {
            bluetoothHci.close();
            logd("Bluetooth hci closed");
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to close Bluetooth hci", e);
        }
    }

    private IBluetoothHci getBluetoothHci() {
        try {
            return IBluetoothHci.getService("default");
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get IBluetoothHci service", e);
            return null;
        } catch (NoSuchElementException e2) {
            Log.e(TAG, "IBluetoothHci service not registered yet", e2);
            return null;
        }
    }

    private String mapBluetoothState2String(int state) {
        switch (state) {
            case 10:
                return "STATE_OFF";
            case 11:
                return "STATE_TURNING_ON";
            case 12:
                return "STATE_ON";
            case 13:
                return "STATE_TURNING_OFF";
            case 14:
                return "STATE_BLE_TURNING_ON";
            case 15:
                return "STATE_BLE_ON";
            case 16:
                return "STATE_BLE_TURNING_OFF";
            default:
                return "Unknown Bluetooth state";
        }
    }

    private void logd(String msg) {
        Log.d(TAG, msg);
    }

    private void logw(String msg) {
        Log.w(TAG, msg);
    }

    private void loge(String msg) {
        Log.e(TAG, msg);
    }
}
