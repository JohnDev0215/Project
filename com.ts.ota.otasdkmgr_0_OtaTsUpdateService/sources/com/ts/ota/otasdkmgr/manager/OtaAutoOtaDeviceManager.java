package com.ts.ota.otasdkmgr.manager;

import android.os.Bundle;
import android.util.Log;
import com.byd.ota.master.interfaces.IAutoOtaDeviceManager;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.ota.CarVendorOtaMgr;
import com.ts.lib.power.manager.CarPowerManager;
import com.ts.ota.otasdkmgr.IOtaTsUpdateServiceProxy;
import com.ts.qnxmanager.QnxManager;

/* JADX INFO: loaded from: classes.dex */
public class OtaAutoOtaDeviceManager implements IAutoOtaDeviceManager {
    private static final int HEART_BEAT_OTA_STATE = 16;
    private static final int ONE = 1;
    private static final String TAG = "OtaAutoOtaDeviceManager";
    private static final int TYPE_OTA = 8;
    private static final int ZERO = 0;
    private CarPowerManager mCarPowerManager;
    private CarVendorOtaMgr mCarVendorOtaMgr;
    private QnxManager mQnxMgr = QnxManager.getInstance();
    private IOtaTsUpdateServiceProxy proxy;

    public OtaAutoOtaDeviceManager(IOtaTsUpdateServiceProxy proxy) {
        this.proxy = null;
        this.proxy = proxy;
    }

    public int getDcState() {
        int result = -1;
        Log.i(TAG, "getDcState in");
        if (this.proxy.getCarVendorOtaMgr() == null) {
            Log.e(TAG, "getDcState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            result = this.proxy.getCarVendorOtaMgr().getDcState();
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "getDcState out result is " + result);
        return result;
    }

    public float getBatteryVoltage(int batteryType) {
        float rstate = -1.0f;
        Log.i(TAG, " getBatteryVoltage.in");
        CarVendorOtaMgr carVendorOtaMgr = this.proxy.getCarVendorOtaMgr();
        this.mCarVendorOtaMgr = carVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getBatteryVoltage CarVendorOtaMgr is null");
            return -1.0f;
        }
        try {
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        if (batteryType == 0) {
            rstate = carVendorOtaMgr.getBatteryVoltageSecond();
        } else {
            if (batteryType == 1) {
                rstate = carVendorOtaMgr.getBatteryVoltage();
            }
            Log.i(TAG, " getBatteryVoltage.out=" + rstate);
            return rstate;
        }
        Log.i(TAG, " getBatteryVoltage.out=" + rstate);
        return rstate;
    }

    public int getIg3RelayState() {
        int result = -1;
        Log.i(TAG, "getIg3RelayState in");
        if (this.proxy.getCarVendorOtaMgr() == null) {
            Log.e(TAG, "getIg3RelayState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            result = this.proxy.getCarVendorOtaMgr().getIg3RelayState();
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "getIg3RelayState out result is " + result);
        return result;
    }

    public int getBcmOtaState() {
        int result = -1;
        Log.i(TAG, "getBcmOtaState in");
        if (this.proxy.getCarVendorOtaMgr() == null) {
            Log.e(TAG, "getBcmOtaState CarVendorOtaMgr is null");
            return -1;
        }
        try {
            result = this.proxy.getCarVendorOtaMgr().getBcmOtaState();
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "getBcmOtaState out result is " + result);
        return result;
    }

    public boolean setOtaModel(int i) {
        if (this.mQnxMgr == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("heartOtaState", i);
        this.mQnxMgr.sendMessage(8, 16, null, bundle);
        return true;
    }

    public boolean setHighVoltageCmd(int i) {
        boolean result = false;
        Log.i(TAG, "setHighVoltageCmd in i is " + i);
        if (this.proxy.getCarVendorOtaMgr() == null) {
            Log.e(TAG, "setHighVoltageCmd CarVendorOtaMgr is null");
            return false;
        }
        try {
            this.proxy.getCarVendorOtaMgr().setHighVoltageCmd(i);
            result = true;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "setHighVoltageCmd out result is " + result);
        return result;
    }

    public boolean setBackupAreaDownload(int i) {
        Log.i(TAG, "setBackupAreaDownload in i is " + i);
        if (this.proxy.getCarVendorOtaMgr() == null) {
            Log.e(TAG, "setBackupAreaDownload CarVendorOtaMgr is null");
            return false;
        }
        try {
            this.proxy.getCarVendorOtaMgr().setBackupAreaDownload(i);
            return true;
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getDischargeMainContactorState() {
        int rstate = -1;
        Log.i(TAG, " getDischargeMainContactorState.in");
        CarVendorOtaMgr carVendorOtaMgr = this.proxy.getCarVendorOtaMgr();
        this.mCarVendorOtaMgr = carVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " mCarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getDischargeMainContactorState();
        } catch (CarNotConnectedException ex) {
            ex.printStackTrace();
        }
        Log.i(TAG, " getDischargeMainContactorState.out=" + rstate);
        return rstate;
    }

    public int getEnergyType() {
        int rstate = -1;
        Log.i(TAG, " getEnergyType.in");
        CarVendorOtaMgr carVendorOtaMgr = this.proxy.getCarVendorOtaMgr();
        this.mCarVendorOtaMgr = carVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " mCarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getOtaEnergyTypeR();
        } catch (CarNotConnectedException ex) {
            ex.printStackTrace();
        }
        Log.i(TAG, " getEnergyType.out=" + rstate);
        return rstate;
    }

    public int getPowerLevel() {
        int rstate = -1;
        Log.i(TAG, " getPowerLevel.in");
        CarVendorOtaMgr carVendorOtaMgr = this.proxy.getCarVendorOtaMgr();
        this.mCarVendorOtaMgr = carVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " mCarVendorOtaMgr is null");
            return -1;
        }
        try {
            rstate = carVendorOtaMgr.getOtaPowerLevel();
        } catch (CarNotConnectedException ex) {
            ex.printStackTrace();
        }
        Log.i(TAG, " getPowerLevel.out=" + rstate);
        return rstate;
    }

    public int getBatteryTemp(int batteryType) {
        int rstate = -1;
        Log.i(TAG, " getBatteryTemp.in");
        CarVendorOtaMgr carVendorOtaMgr = this.proxy.getCarVendorOtaMgr();
        this.mCarVendorOtaMgr = carVendorOtaMgr;
        if (carVendorOtaMgr == null) {
            Log.e(TAG, " getBatteryTemp CarVendorOtaMgr is null");
            return -1;
        }
        try {
        } catch (CarNotConnectedException ex) {
            ex.printStackTrace();
        }
        if (batteryType == 0) {
            rstate = carVendorOtaMgr.getEnvironmentTempSecond();
        } else {
            if (batteryType == 1) {
                rstate = carVendorOtaMgr.getEnvironmentTemp();
            }
            Log.i(TAG, " getBatteryTemp.out=" + rstate);
            return rstate;
        }
        Log.i(TAG, " getBatteryTemp.out=" + rstate);
        return rstate;
    }

    public int rebootQNX() {
        Log.i(TAG, " rebootQNX.in");
        CarPowerManager carPowerManager = this.proxy.getCarPowerManager();
        this.mCarPowerManager = carPowerManager;
        if (carPowerManager == null) {
            Log.e(TAG, " rebootQNX: CarPowerManager is null");
            return 0;
        }
        boolean zMcuReset = carPowerManager.mcuReset();
        Log.i(TAG, " rebootQNX.out=" + zMcuReset);
        return zMcuReset ? 1 : 0;
    }
}
