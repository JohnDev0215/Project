package com.ts.ota.otasdkmgr;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.engine.tbox.TboxManager;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.carbody.CarVendorCarbodyMgr;
import com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager;
import com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyMgr;
import com.ts.lib.car.hardware.digitalkey.DigitalkeyEventChangeValue;
import com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager;
import com.ts.lib.car.hardware.doorlock.CarVendorDoorlockMgr;
import com.ts.lib.car.hardware.doorlock.DoorlockEventChangeValue;
import com.ts.lib.car.hardware.ota.CarVendorOtaManager;
import com.ts.lib.car.hardware.ota.CarVendorOtaMgr;
import com.ts.lib.car.hardware.ota.OtaEventChangeValue;
import com.ts.lib.power.data.CarPowerData;
import com.ts.lib.power.data.CarPowerSignalStatus;
import com.ts.lib.power.data.CarPowerWorkModeStatus;
import com.ts.lib.power.listener.CarPowerEventListener;
import com.ts.lib.power.manager.CarPowerManager;
import com.ts.ota.otasdkmgr.adapter.TSOtaMasterAdapter;
import com.ts.ota.otasdkmgr.impl.OtaTsUpdateServiceImpl;

/* JADX INFO: loaded from: classes.dex */
public class OtaTsUpdateService extends Service {
    private static final String TAG = "dota.OtaTsUpdateService";
    private Car mCarClient;
    private CarPowerManager mCarPowerManager;
    private OtaTsUpdateServiceImpl mOtaTsUpdateServiceImpl = null;
    private TSOtaMasterAdapter otaMasterAdapter = null;
    private TboxManager mTboxManager = null;
    private CarVendorOtaMgr mCarVendorOtaMgr = null;
    private CarVendorCarbodyMgr mCarbodyMgr = null;
    private CarVendorDoorlockMgr mCarDoorMgr = null;
    private CarVendorDigitalkeyMgr mCarDigitalkeyMgr = null;
    private IOtaTsUpdateServiceProxy proxy = new OtaTsUpdateServiceProxy();
    private final CarConnectionCallback mCarConnectionCallback = new CarConnectionCallback() { // from class: com.ts.ota.otasdkmgr.OtaTsUpdateService.1
        public void onConnected(Car car) {
            Log.e(OtaTsUpdateService.TAG, "Car connected in onServiceConnected start");
            try {
                OtaTsUpdateService.this.mCarVendorOtaMgr = (CarVendorOtaMgr) car.getCarManager("ota");
                OtaTsUpdateService.this.mCarbodyMgr = (CarVendorCarbodyMgr) car.getCarManager("carbody");
                OtaTsUpdateService.this.mCarDoorMgr = (CarVendorDoorlockMgr) car.getCarManager("doorlock");
                OtaTsUpdateService.this.mCarDigitalkeyMgr = (CarVendorDigitalkeyMgr) car.getCarManager("digitalkey");
                OtaTsUpdateService.this.mCarVendorOtaMgr.addCarOtaEventListener(OtaTsUpdateService.this.mOnCarOtaEventListener);
                OtaTsUpdateService.this.mCarDoorMgr.addCarDoorlockEventListener(OtaTsUpdateService.this.mOnCarDoorlockEventListener);
                OtaTsUpdateService.this.mCarDigitalkeyMgr.addCarDigitalkeyEventListener(OtaTsUpdateService.this.mOnCarDigitalkeyEventListener);
                if (OtaTsUpdateService.this.mOtaTsUpdateServiceImpl != null) {
                    OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.initCarManager();
                } else {
                    Log.e(OtaTsUpdateService.TAG, "onConnected: mOtaTsUpdateServiceImpl == null");
                }
            } catch (CarNotConnectedException exception) {
                Log.e(OtaTsUpdateService.TAG, "CarConnectionCallback  error");
                exception.printStackTrace();
            }
        }

        public void onDisconnected(Car car) {
            OtaTsUpdateService.this.clearTsManager();
        }
    };
    private CarVendorDigitalkeyManager.OnCarDigitalkeyEventListener mOnCarDigitalkeyEventListener = new CarVendorDigitalkeyManager.OnCarDigitalkeyEventListener() { // from class: com.ts.ota.otasdkmgr.OtaTsUpdateService.2
        public void onDigitalkeyEventChange(DigitalkeyEventChangeValue value) {
            if (value == null || OtaTsUpdateService.this.mOtaTsUpdateServiceImpl == null) {
                Log.e(OtaTsUpdateService.TAG, "onDigitalkeyEventChange: value is " + value + ", mOtaTsUpdateServiceImpl is " + OtaTsUpdateService.this.mOtaTsUpdateServiceImpl);
                return;
            }
            int propertyId = value.getPropertyId();
            Log.i(OtaTsUpdateService.TAG, "onDigitalkeyEventChange: propertyId = " + propertyId);
            if (propertyId == 557846289) {
                int cmd = 0;
                try {
                    cmd = OtaTsUpdateService.this.mCarDigitalkeyMgr.getNfcOperationCommand();
                } catch (CarNotConnectedException e) {
                    Log.e(OtaTsUpdateService.TAG, "onDigitalkeyEventChange: car not connect");
                }
                Log.i(OtaTsUpdateService.TAG, "onDigitalkeyEventChange: ID_NFC_OPERATION_COMMAND cmd = " + cmd);
                OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onNfcOperationCommand(cmd);
            }
        }

        public void onDigitalkeyErrorEvent(int propertyId, int zone) {
        }
    };
    private CarVendorDoorlockManager.OnCarDoorlockEventListener mOnCarDoorlockEventListener = new CarVendorDoorlockManager.OnCarDoorlockEventListener() { // from class: com.ts.ota.otasdkmgr.OtaTsUpdateService.3
        public void onDoorlockEventChange(DoorlockEventChangeValue value) {
            if (value != null) {
                if (OtaTsUpdateService.this.mOtaTsUpdateServiceImpl == null) {
                    Log.e(OtaTsUpdateService.TAG, "onDoorlockEventChange: mOtaTsUpdateServiceImpl is null");
                    return;
                }
                int propertyId = value.getPropertyId();
                Log.i(OtaTsUpdateService.TAG, "onDoorlockEventChange: propertyId = " + propertyId);
                return;
            }
            Log.e(OtaTsUpdateService.TAG, "onDoorlockEventChange: onDoorlockEventChange is null");
        }

        public void onDoorlockErrorEvent(int propertyId, int zone) {
            Log.e(OtaTsUpdateService.TAG, "onDoorlockErrorEvent propertyId = " + propertyId + ", zone = " + zone);
        }
    };
    private CarVendorOtaManager.OnCarOtaEventListener mOnCarOtaEventListener = new CarVendorOtaManager.OnCarOtaEventListener() { // from class: com.ts.ota.otasdkmgr.OtaTsUpdateService.4
        public void onOtaEventChange(OtaEventChangeValue otaEventChangeValue) {
            if (otaEventChangeValue != null) {
                if (OtaTsUpdateService.this.mOtaTsUpdateServiceImpl == null) {
                    Log.e(OtaTsUpdateService.TAG, "onOtaEventChange: mOtaTsUpdateServiceImpl is null");
                    return;
                }
                int propertyId = otaEventChangeValue.getPropertyId();
                Log.i(OtaTsUpdateService.TAG, "onOtaEventChange: propertyId = " + propertyId);
                switch (propertyId) {
                    case 557860354:
                        int turnOffState = otaEventChangeValue.getTurnOffState();
                        Log.i(OtaTsUpdateService.TAG, "onOtaEventChange: TURN_OFF_STATE turnOffState = " + turnOffState);
                        OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onTurnOffState(turnOffState);
                        break;
                    case 557860360:
                        int smartPowerState = otaEventChangeValue.getSmartPowerState();
                        Log.i(OtaTsUpdateService.TAG, "onOtaEventChange: SMART_POWER_STATE smartPowerState = " + smartPowerState);
                        OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onSmartPowerState(smartPowerState);
                        break;
                    case 557860362:
                        int dcState = otaEventChangeValue.getDcState();
                        Log.i(OtaTsUpdateService.TAG, "onOtaEventChange: DC_STATE dcState = " + dcState);
                        OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onDcState(dcState);
                        break;
                    case 557860369:
                        int ig3RelayState = otaEventChangeValue.getIg3RelayState();
                        Log.i(OtaTsUpdateService.TAG, "onOtaEventChange: IG3_RELAY_STATE ig3RelayState = " + ig3RelayState);
                        OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onIg3RelayState(ig3RelayState);
                        break;
                    case 557860370:
                        int bcmOtaState = otaEventChangeValue.getBcmOtaState();
                        Log.i(OtaTsUpdateService.TAG, "onOtaEventChange: ID_BCM_OTA_STATE bcmOtaState = " + bcmOtaState);
                        OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onBcmOtaState(bcmOtaState);
                        break;
                    case 557860385:
                        int backupAreaDownload = otaEventChangeValue.getBackupAreaDownloadStateR();
                        Log.i(OtaTsUpdateService.TAG, "onOtaEventChange: ID_BACKUP_AREA_DOWNLOAD_STATER backupAreaDownload = " + backupAreaDownload);
                        OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onBackupAreaDownloadState(backupAreaDownload);
                        break;
                    case 557860386:
                        int socValue = otaEventChangeValue.getSocValueR();
                        Log.i(OtaTsUpdateService.TAG, "otaEventChangeValue: ID_SOC_VALUER areaLeftFront = " + socValue);
                        OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onSocValueChanged(socValue);
                        break;
                    case 557860388:
                        int keyStateR = otaEventChangeValue.getStartKeyStateR();
                        Log.i(OtaTsUpdateService.TAG, "onOtaEventChange: ID_START_KEY_STATE_R keyStateR = " + keyStateR);
                        OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onStartKeyState(keyStateR);
                        break;
                    case 557860389:
                        int onStateR = otaEventChangeValue.getProhibitPowerOnStateR();
                        Log.i(OtaTsUpdateService.TAG, "onOtaEventChange: ID_PROHIBIT_POWER_ON_STATE_R onStateR = " + onStateR);
                        OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onProhibitPowerOnState(onStateR);
                        break;
                    case 557860391:
                        int areaLeftFront = otaEventChangeValue.getDoorLockStatusAreaLeftFront();
                        Log.i(OtaTsUpdateService.TAG, "otaEventChangeValue: ID_DOOR_LOCK_AREA_LEFT_FRONT areaLeftFront = " + areaLeftFront);
                        OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onLFDoorLockStatus(areaLeftFront);
                        break;
                    case 557860392:
                        int ig4RelayState = otaEventChangeValue.getIg4RelayStateR();
                        Log.i(OtaTsUpdateService.TAG, "onOtaEventChange: ID_IG4_RELAY_STATE_R ig4RelayState = " + ig4RelayState);
                        OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onIg4RelayState(ig4RelayState);
                        break;
                    case 557860393:
                        int ig1RelayState = otaEventChangeValue.getIg1RelayStateR();
                        Log.i(OtaTsUpdateService.TAG, "onOtaEventChange: ID_IG1_RELAY_STATE_R ig1RelayState = " + ig1RelayState);
                        OtaTsUpdateService.this.mOtaTsUpdateServiceImpl.onIg1RelayState(ig1RelayState);
                        break;
                }
            }
            Log.e(OtaTsUpdateService.TAG, "onOtaEventChange: otaEventChangeValue is null");
        }

        public void onOtaErrorEvent(int i, int i1) {
            Log.e(OtaTsUpdateService.TAG, "onOtaErrorEvent i = " + i + ", i1 = " + i1);
        }
    };
    private CarPowerEventListener mCarPowerEventListener = new CarPowerEventListener() { // from class: com.ts.ota.otasdkmgr.OtaTsUpdateService.5
        public void onCarPowerWorkModeChangeEvent(CarPowerWorkModeStatus carPowerWorkModeStatus) {
        }

        public void onCarPowerSignalChangeEvent(CarPowerSignalStatus carPowerSignalStatus) {
        }

        public void onCarPowerServiceConnected() {
        }

        public void onCarPowerServiceDisconnected() {
        }

        public void onCarPowerEvent(int i, int i1, CarPowerData carPowerData) {
        }

        public void onCarPowerDisplayEvent(int type, int displayPanelId, int value) {
            Log.e(OtaTsUpdateService.TAG, " onCarPowerDisplayEvent type : " + type + " displayPanelId : " + displayPanelId + " value : " + value);
        }
    };

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate start");
        initCarPowerManager();
        Log.e(TAG, "onCreate  initTSAdapter");
        initTSAdapter();
        if (this.mOtaTsUpdateServiceImpl == null) {
            this.mOtaTsUpdateServiceImpl = new OtaTsUpdateServiceImpl(getApplicationContext(), this.proxy);
        }
        initCar();
        initTBoxManger();
        Log.e(TAG, "onCreate, mBinder:" + this.mOtaTsUpdateServiceImpl);
        Log.e(TAG, "onCreate end");
    }

    private void initTSAdapter() {
        TSOtaMasterAdapter tSOtaMasterAdapter = new TSOtaMasterAdapter(getApplicationContext());
        this.otaMasterAdapter = tSOtaMasterAdapter;
        tSOtaMasterAdapter.init(this.proxy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initTBoxManger() {
        TboxManager tboxManager = TboxManager.getInstance(getApplicationContext());
        this.mTboxManager = tboxManager;
        OtaTsUpdateServiceImpl otaTsUpdateServiceImpl = this.mOtaTsUpdateServiceImpl;
        if (otaTsUpdateServiceImpl != null) {
            otaTsUpdateServiceImpl.setTboxManager(tboxManager);
        }
    }

    private void initCarPowerManager() {
        CarPowerManager carPowerManager = CarPowerManager.getInstance(getApplicationContext(), this.mCarPowerEventListener);
        this.mCarPowerManager = carPowerManager;
        carPowerManager.connect();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (this.otaMasterAdapter == null) {
            initTSAdapter();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        TSOtaMasterAdapter tSOtaMasterAdapter = this.otaMasterAdapter;
        if (tSOtaMasterAdapter != null) {
            tSOtaMasterAdapter.releaseAdapter();
            this.otaMasterAdapter = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTsManager() {
        try {
            if (this.mCarVendorOtaMgr != null) {
                this.mCarVendorOtaMgr.removeCarOtaEventListener(this.mOnCarOtaEventListener);
                this.mCarVendorOtaMgr = null;
            }
        } catch (CarNotConnectedException except) {
            Log.e(TAG, "Car not connected in onServiceConnected");
            except.printStackTrace();
        }
    }

    private void initCar() {
        Log.e(TAG, "initCar start");
        Car carCreateCar = Car.createCar(getApplication(), this.mCarConnectionCallback);
        this.mCarClient = carCreateCar;
        carCreateCar.connect();
        Log.e(TAG, "initCar end");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return this.mOtaTsUpdateServiceImpl;
    }

    private class OtaTsUpdateServiceProxy implements IOtaTsUpdateServiceProxy {
        private OtaTsUpdateServiceProxy() {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaTsUpdateServiceProxy
        public TboxManager getTboxManager() {
            if (OtaTsUpdateService.this.mTboxManager == null) {
                OtaTsUpdateService.this.initTBoxManger();
            }
            return OtaTsUpdateService.this.mTboxManager;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaTsUpdateServiceProxy
        public CarVendorOtaMgr getCarVendorOtaMgr() {
            return OtaTsUpdateService.this.mCarVendorOtaMgr;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaTsUpdateServiceProxy
        public TSOtaMasterAdapter getTSOtaMasterAdapter() {
            return OtaTsUpdateService.this.otaMasterAdapter;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaTsUpdateServiceProxy
        public CarPowerManager getCarPowerManager() {
            return OtaTsUpdateService.this.mCarPowerManager;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaTsUpdateServiceProxy
        public CarVendorCarbodyMgr getCarBodyMgr() {
            return OtaTsUpdateService.this.mCarbodyMgr;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaTsUpdateServiceProxy
        public CarVendorDoorlockMgr getCarDoorMgr() {
            return OtaTsUpdateService.this.mCarDoorMgr;
        }
    }
}
