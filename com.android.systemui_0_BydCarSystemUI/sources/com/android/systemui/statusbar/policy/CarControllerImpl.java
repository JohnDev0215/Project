package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.database.ContentObserver;
import android.hardware.IBYDAutoEvent;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.hardware.bydauto.doorlock.BYDAutoDoorLockDevice;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.hardware.bydauto.light.BYDAutoLightDevice;
import android.hardware.bydauto.mic.BYDAutoMicDevice;
import android.hardware.bydauto.mic.DeviceEventListener;
import android.hardware.bydauto.panorama.AbsBYDAutoPanoramaListener;
import android.hardware.bydauto.panorama.BYDAutoPanoramaDevice;
import android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener;
import android.hardware.bydauto.pm2p5.BYDAutoPM2p5Device;
import android.hardware.bydauto.radar.AbsBYDAutoRadarListener;
import android.hardware.bydauto.radar.BYDAutoRadarDevice;
import android.hardware.bydauto.seat.AbsBYDAutoSeatListener;
import android.hardware.bydauto.seat.BYDAutoSeatDevice;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.showcarmode.BYDAutoShowCarDevice;
import android.hardware.bydauto.showcarmode.IShowCarModelChangeListener;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.Constants;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.util.SystemUIException;
import com.android.systemui.wireless.CarPlayAndroidAutoTipDialog;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.ts.avm.sdk.AvmManager;
import com.ts.avm.sdk.IAvmServiceConnectionListener;
import com.ts.car.core.ServiceProvider;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.IAndroidAutoDeviceListManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.ICarPlayAppListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayDeviceListManager;
import ts.car.service.carplay.ICarPlayServiceListener;
import ts.car.service.carplay.ICarPlayServiceManager;
import ts.car.service.carplay.ICarPlayStateListener;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: loaded from: classes.dex */
public class CarControllerImpl implements CarController {
    public static final int INSTRUMENT_BRIGHTNESS_MAX = 11;
    public static final int INSTRUMENT_BRIGHTNESS_MIN = 1;
    public static final int NEW_BLIND_MONITORY_TYPE = 1;
    public static final int OLD_BLIND_MONITORY_TYPE = 0;
    private static final int SPRING_BACK_TIME = 2000;
    private static final String TAG = "CarControllerImpl";
    public static final int TRAFFIC_SIGN_RECOGNIZE_2B4 = 2;
    public static final int TRAFFIC_SIGN_RECOGNIZE_2D5 = 1;
    public static final int TRAFFIC_SIGN_RECOGNIZE_316 = 3;
    private BYDAutoPM2p5Device mAutoPM2p5Device;
    private boolean mAvmConnected;
    private AvmManager mAvmManager;
    private AvmNaviBarListener mAvmNaviBarListener;
    private BYDAutoAcDevice mBYDAutoAcDevice;
    private BYDAutoAudioDevice mBYDAutoAudioDevice;
    private BYDAutoBodyworkDevice mBYDAutoBodyworkDevice;
    private BYDAutoChargingDevice mBYDAutoChargingDevice;
    private BYDAutoDiPilotDevice mBYDAutoDiPilotDevice;
    private BYDAutoDoorLockDevice mBYDAutoDoorLockDevice;
    private BYDAutoEnergyDevice mBYDAutoEnergyDevice;
    private BYDAutoInstrumentDevice mBYDAutoInstrumentDevice;
    private BYDAutoLightDevice mBYDAutoLightDevice;
    private BYDAutoRadarDevice mBYDAutoRadarDevice;
    private BYDAutoSeatDevice mBYDAutoSeatDevice;
    private BYDAutoSettingDevice mBYDAutoSettingDevice;
    private BYDAutoStatisticDevice mBYDAutoStatisticDevice;
    private BYDAutoStudy mBYDAutoStudy;
    private BYDAutoVehicleHealthDevice mBYDAutoVehicleHealthDevice;
    private BYDAutoShowCarDevice mBYDShowCarModel;
    private BYDAutoGearboxDevice mBydAutoGearboxDevice;
    private BYDAutoMicDevice mBydAutoMicDevice;
    private BYDAutoPanoramaDevice mBydAutoPanoramaDevice;
    private BydAutoPanoramaListener mBydAutoPanoramaListener;
    private BydDeviceEventListener mBydDeviceEventListener;
    private BydInstrumentListener mBydInstrumentListener;
    private final CarControllerHandler mCarControllerHandler;
    private int mCarPlayConnectionStatus;
    private boolean mCarPlayIsPhoneCall;
    private boolean mChargeListenerConnected;
    private final Context mContext;
    private boolean mIsAndroidAutoAppConnected;
    private boolean mIsAndroidAutoAppRegistering;
    private boolean mIsAndroidAutoFocus;
    private boolean mIsCarPlayAppConnected;
    private boolean mIsCarPlayAppRegistering;
    private boolean mIsCarPlayFocus;
    private int valueInOld;
    private int valueOutOld;
    private final ArrayList<CarController.Callback> mCallbacks = new ArrayList<>();
    private IAndroidAutoDeviceListManager mAndroidAutoDeviceManager = null;
    private ICarPlayDeviceListManager mCarPlayDeviceManager = null;
    private ICarPlayAppManager mCarPlayAppManager = null;
    private ICarPlayServiceManager mCarPlayServiceManager = null;
    private IAndroidAutoAppManager mAndroidAutoAppManager = null;
    private int mAndroidAutoConnectionStatus = 1;
    private int mCntTrafficSignRecognize = 1;
    private IAndroidAutoAppListener mAndroidAutoAppListener = new IAndroidAutoAppListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.1
        @Override // ts.car.service.androidauto.IAndroidAutoAppListener
        public void onVideoFocusStatusChanged(int videoFocusStatus) {
            Log.i(CarControllerImpl.TAG, "onVideoFocusStatusChanged videoFocusStatus:" + videoFocusStatus);
            if (videoFocusStatus == 1) {
                CarControllerImpl.this.mIsAndroidAutoFocus = true;
                EventBus.getDefault().post(new CarPlayAndroidAutoTipDialog.DismissEvent());
            } else {
                CarControllerImpl.this.mIsAndroidAutoFocus = false;
            }
        }
    };
    private ICarPlayAppListener mCarPlayAppListener = new ICarPlayAppListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.2
        @Override // ts.car.service.carplay.ICarPlayAppListener
        public void onCarPlayDisplayStatusChanged(boolean isShow) {
            CarControllerImpl.this.mIsCarPlayFocus = isShow;
            if (isShow) {
                EventBus.getDefault().post(new CarPlayAndroidAutoTipDialog.DismissEvent());
            }
        }
    };
    private IAndroidAutoSessionStatusListener mAndroidAutoSessionStatusListener = new IAndroidAutoSessionStatusListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.3
        @Override // ts.car.service.androidauto.IAndroidAutoSessionStatusListener
        public void onSessionStatusChanged(int linkStatus, int errorCode, String message) {
            BydLogHelper.logD("CarControllerImpl", "IAndroidAutoSessionStatusListener status=" + linkStatus + " errorCode=" + errorCode + " message=" + message);
            CarControllerImpl.this.mAndroidAutoConnectionStatus = linkStatus;
            boolean z = linkStatus == 4;
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onSessionStatusChangedOnAndroidAuto(z);
                }
            }
        }
    };
    private ICarPlayStateListener mCarPlayStateListener = new ICarPlayStateListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.4
        @Override // ts.car.service.carplay.ICarPlayStateListener
        public void onSessionStateChanged(int status) {
            BydLogHelper.logD("CarControllerImpl", "ICarPlayStateListener status=" + status);
            CarControllerImpl.this.mCarPlayConnectionStatus = status;
            boolean z = status == 2;
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onSessionStatusChangedOnCarPlay(z);
                }
            }
        }

        @Override // ts.car.service.carplay.ICarPlayStateListener
        public void onAppStateChanged(int appState, boolean enabled, int speechMode) {
            BydLogHelper.logD("CarControllerImpl", "onAppStateChanged appState=" + appState + " , enabled=" + enabled + " , speechMode=" + speechMode);
            if (appState == 0) {
                CarControllerImpl.this.mCarPlayIsPhoneCall = enabled;
            }
        }
    };
    private IServiceConnectionListener mAndroidAutoAppConnectionListener = new IServiceConnectionListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.6
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService service) {
            SystemProperties.set("sys.sysui.cpaa.connect", "1");
            CarControllerImpl.this.mIsAndroidAutoAppConnected = true;
            CarControllerImpl.this.mIsAndroidAutoAppRegistering = false;
            Log.i(CarControllerImpl.TAG, "mIsAndroidAutoAppConnected  true");
            if (CarControllerImpl.this.mAndroidAutoAppManager != null) {
                try {
                    CarControllerImpl.this.mAndroidAutoAppManager.registerAndroidAutoSessionStatusListener(CarControllerImpl.this.mAndroidAutoSessionStatusListener);
                    CarControllerImpl.this.mAndroidAutoAppManager.registerAndroidAutoAppListener(CarControllerImpl.this.mAndroidAutoAppListener);
                } catch (RemoteException e) {
                    BydLogHelper.logE("registerAndroidAutoSessionStatusListener", e.toString());
                    e.printStackTrace();
                }
                boolean androidAutoConnectionStatus = CarControllerImpl.this.getAndroidAutoConnectionStatus();
                synchronized (CarControllerImpl.this.mCallbacks) {
                    Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                    while (it.hasNext()) {
                        ((CarController.Callback) it.next()).onSessionStatusChangedOnAndroidAuto(androidAutoConnectionStatus);
                    }
                }
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService service) {
            CarControllerImpl.this.mIsAndroidAutoAppConnected = false;
            CarControllerImpl.this.mIsAndroidAutoAppRegistering = false;
            CarControllerImpl.this.mIsAndroidAutoFocus = false;
            Log.i(CarControllerImpl.TAG, "AndroidAuto onServiceDisconnected");
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onSessionStatusChangedOnAndroidAuto(false);
                }
            }
        }
    };
    private IServiceConnectionListener mCarPlayAppConnectionListener = new IServiceConnectionListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.7
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            SystemProperties.set("sys.sysui.cpaa.connect", "1");
            CarControllerImpl.this.mIsCarPlayAppConnected = true;
            CarControllerImpl.this.mIsCarPlayAppRegistering = false;
            Log.i(CarControllerImpl.TAG, "mIsCarPlayAppConnected  true");
            if (CarControllerImpl.this.mCarPlayAppManager != null) {
                try {
                    CarControllerImpl.this.mCarPlayAppManager.registerCarPlayStateListener(CarControllerImpl.this.mCarPlayStateListener);
                    CarControllerImpl.this.mCarPlayAppManager.registerCarPlayAppListener(CarControllerImpl.this.mCarPlayAppListener);
                } catch (RemoteException e) {
                    Log.e(CarControllerImpl.TAG, "registerCarPlayStateListener:" + e.toString());
                    e.printStackTrace();
                }
                boolean carPlayConnectionStatus = CarControllerImpl.this.getCarPlayConnectionStatus();
                synchronized (CarControllerImpl.this.mCallbacks) {
                    Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                    while (it.hasNext()) {
                        ((CarController.Callback) it.next()).onSessionStatusChangedOnCarPlay(carPlayConnectionStatus);
                    }
                }
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            CarControllerImpl.this.mIsCarPlayAppConnected = false;
            CarControllerImpl.this.mIsCarPlayAppRegistering = false;
            CarControllerImpl.this.mCarPlayIsPhoneCall = false;
            Log.i(CarControllerImpl.TAG, "CarPlay onServiceDisconnected");
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onSessionStatusChangedOnCarPlay(false);
                }
            }
        }
    };
    private IServiceConnectionListener mServiceConnectionListener = new IServiceConnectionListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.8
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            try {
                CarControllerImpl.this.mCarPlayServiceManager.registerCarPlayServiceListener(CarControllerImpl.this.mCarPlayServiceListener);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e(CarControllerImpl.TAG, "mServiceConnectionListener RemoteException : " + e.getMessage());
            }
        }
    };
    private ICarPlayServiceListener mCarPlayServiceListener = new ICarPlayServiceListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.9
        @Override // ts.car.service.carplay.ICarPlayServiceListener
        public void onRequestVideoFocusChanged(int requestType) {
            BydLogHelper.logV("onRequestVideoFocusChanged", "requestType = " + requestType);
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onRequestVideoFocusChanged(requestType);
                }
            }
        }
    };
    private final IShowCarModelChangeListener showCarModelChangeListener = new IShowCarModelChangeListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.10
        @Override // android.hardware.bydauto.showcarmode.IShowCarModelChangeListener
        public void showCarModelChange(int value) {
            Log.i(IShowCarModelChangeListener.TAG, "showCarModelChange value : " + value);
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).showCarModelChange(value);
                }
            }
        }

        @Override // android.hardware.IBYDAutoListener
        public void isConnected(boolean isBinding) {
            Log.i(IShowCarModelChangeListener.TAG, "CarModelChangeListener isBinding:" + isBinding);
            if (isBinding) {
                synchronized (CarControllerImpl.this.mCallbacks) {
                    Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                    while (it.hasNext()) {
                        ((CarController.Callback) it.next()).showCarModelChange(CarControllerImpl.this.getShowCarModelType());
                    }
                }
            }
        }

        @Override // android.hardware.bydauto.showcarmode.IShowCarModelChangeListener
        public void showCarGearChange(int value) {
            BydLogHelper.logD("showCarGearChange", "value : " + value);
        }
    };
    private final AbsBYDAutoPM2p5Listener pm2p5DeviceCallback = new AbsBYDAutoPM2p5Listener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.11
        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onPM2p5ValueChanged(int valueIn, int valueOut) {
            if (CarControllerImpl.this.valueInOld == valueIn && CarControllerImpl.this.valueOutOld == valueOut) {
                return;
            }
            BydLogHelper.logD("onPM2p5ValueChanged", "valueIn : " + valueIn + " , valueOut : " + valueOut);
            CarControllerImpl.this.valueInOld = valueIn;
            CarControllerImpl.this.valueOutOld = valueOut;
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onPM2p5ValueChanged(valueIn, valueOut);
                }
            }
        }

        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onPM2p5DualChannelDetectChanged(int state) {
            BydLogHelper.logD("onPM2p5DualChannelDetectChanged state:" + state);
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onPM2p5DualChannelDetectChanged(state);
                }
            }
        }

        @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
        public void onPM2p5CheckStateChanged(int inState, int outState) {
            BydLogHelper.logD("onPM2p5CheckStateChanged inState:" + inState + " ,outState:" + outState);
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onPM2p5CheckStateChanged(inState, outState);
                }
            }
        }
    };
    private final AbsBYDAutoAcListener acListener = new AbsBYDAutoAcListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.12
        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcDefrostStateChanged(int area, int state) {
            BydLogHelper.logW("AbsBYDAutoAcListener", "onAcDefrostStateChanged", "state : " + state);
            if (area == 2) {
                CarControllerImpl.this.notifyAcDefrostStateChanged(area, state);
            }
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcRearPanelLockStateChanged(int state) {
            BydLogHelper.logW("AbsBYDAutoAcListener", "onAcRearPanelLockStateChanged", "state : " + state);
            CarControllerImpl.this.notifyAcRearPanelLockStateChanged(state);
        }
    };
    private final AbsBYDAutoSettingListener settingListener = new AbsBYDAutoSettingListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.13
        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onDynamicSuspensionModeChanged(int value) {
            BydLogHelper.logD("onDynamicSuspensionModeChanged", "value : " + value);
            CarControllerImpl.this.notifyDynamicSuspensionModeChanged(value);
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onFdDynamicSuspensionMode(int value) {
            BydLogHelper.logD("onFdDynamicSuspensionMode", "value : " + value);
            CarControllerImpl.this.notifyDynamicSuspensionModeChanged(value);
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onEnergyFeedbackStrengthChanged(int value) {
            BydLogHelper.logD("onEnergyFeedbackLevelChanged", "value : " + value);
            CarControllerImpl.this.notifyEnergyFeedbackStrengthChanged(value);
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onSteeringWheelHeatingStateChanged(int state) {
            BydLogHelper.logD("onSteeringWheelHeatingStateChanged", "state : " + state);
            CarControllerImpl.this.notifySteeringWheelHeatingStateChanged(state);
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onInsideLightDoorStateChanged(int state) {
            BydLogHelper.logD("onInsideLightDoorStateChanged", "state : " + state);
            CarControllerImpl.this.notifyInsideLightDoorStateChanged(state);
        }

        @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
        public void onHudStateChanged(int state) {
            BydLogHelper.logD("onHudStateChanged", "state : " + state);
            CarControllerImpl.this.notifyHudStateChanged(state);
        }
    };
    private int mCurrentOperationMode = -1;
    private final AbsBYDAutoEnergyListener energyListener = new AbsBYDAutoEnergyListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.14
        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onOperationModeChanged(int mode) {
            Log.i("CarControlImpl", "onOperationModeChanged  mode : " + mode);
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onOperationModeChanged(mode);
                }
            }
        }

        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onOperationModeNormalChanged(int mode) {
            Log.i("CarControlImpl", "onOperationModeNormalChanged  mode : " + mode);
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onOperationModeNormalChanged(mode);
                }
            }
        }
    };
    private final AbsBYDAutoSeatListener seatListener = new AbsBYDAutoSeatListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.15
        @Override // android.hardware.bydauto.seat.AbsBYDAutoSeatListener
        public void onSteeringWheelWarmStateChanged(int state) {
            BydLogHelper.logD("onSteeringWheelWarmStateChanged", "state : " + state);
            CarControllerImpl.this.notifySteeringWheelHeatingStateChanged(state);
        }
    };
    private final AbsBYDAutoChargingListener chargingListener = new AbsBYDAutoChargingListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.16
        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirelessChargingPowerFLStateChanged(int state) {
            BydLogHelper.logD("onWirelessChargingPowerFLStateChanged", "state : " + state);
            CarControllerImpl.this.notifyWirelessChargingPowerFLStateChanged(state);
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirelessChargingPowerFRStateChanged(int state) {
            BydLogHelper.logD("onWirelessChargingPowerFRStateChanged", "state : " + state);
            CarControllerImpl.this.notifyWirelessChargingPowerFRStateChanged(state);
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirelessChargingSwitchStateChanged(int state) {
            BydLogHelper.logD("onWirelessChargingSwitchStateChanged", "state : " + state);
            CarControllerImpl.this.notifyWirelessChargingSwitchStateChanged(state);
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirelessChargingFRSwitchStateChanged(int state) {
            BydLogHelper.logD("onWirelessChargingFRSwitchStateChanged", "state : " + state);
            CarControllerImpl.this.notifyWirelessChargingFRSwitchStateChanged(state);
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirelessChargingStateChanged(int state) {
            BydLogHelper.logD("onWirelessChargingStateChanged", "state : " + state);
            CarControllerImpl.this.mChargeListenerConnected = true;
            CarControllerImpl.this.notifyWirelessChargingStateChanged(state);
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onWirelessChargingFRStateChanged(int state) {
            BydLogHelper.logD("onWirelessChargingFRStateChanged", "state : " + state);
            CarControllerImpl.this.mChargeListenerConnected = true;
            CarControllerImpl.this.notifyWirelessChargingFRStateChanged(state);
        }

        @Override // android.hardware.IBYDAutoListener
        public void isConnected(final boolean isBinding) {
            BydLogHelper.logD("AbsBYDAutoChargingListener isBinding:" + isBinding);
            CarControllerImpl.this.mChargeListenerConnected = isBinding;
            CarControllerImpl.this.onChargingListenerConnected(isBinding);
        }
    };
    private final AbsBYDAutoLightListener lightListener = new AbsBYDAutoLightListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.17
        @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
        public void onDoorLightStateChanged(int state) {
            BydLogHelper.logD("onDoorLightStateChanged", "state : " + state);
            CarControllerImpl.this.notifyDoorLightStateChanged(state);
        }

        @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
        public void onBacklightLevelsChanged(int level) {
            BydLogHelper.logD("onBacklightLevelsChanged level : " + level);
            CarControllerImpl.this.notifyBacklightLevelsChanged(level);
        }
    };
    private final AbsBYDAutoDiPilotListener diPilotListener = new AbsBYDAutoDiPilotListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.18
        @Override // android.hardware.IBYDAutoListener
        public void onDataChanged(IBYDAutoEvent ibydAutoEvent) {
        }

        @Override // android.hardware.IBYDAutoListener
        public void onDataEventChanged(int eventType, BYDAutoEventValue bydAutoEventValue) {
        }

        @Override // android.hardware.IBYDAutoListener
        public void onError(int errCode, String errMessage) {
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onLaneAssistTypeChanged(int value) {
            BydLogHelper.logD("value : " + value);
            if (CarControllerImpl.this.mCarControllerHandler.hasMessages(4123)) {
                return;
            }
            CarControllerImpl.this.notifyLaneAssistTypeChanged(value);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onEmergencyLaneAssistance32FStateChanged(int state) {
            BydLogHelper.logD("state : " + state);
            if (CarControllerImpl.this.mCarControllerHandler.hasMessages(4123)) {
                return;
            }
            CarControllerImpl.this.notifyEmergencyLaneAssistance32FStateChanged(state);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onEspOffKeyStatusChanged(int state) {
            BydLogHelper.logD("state : " + state);
            CarControllerImpl.this.notifyEspOffKeyStateChanged(state);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAiDistanceLight2B4StateChanged(int state) {
            int aiDistanceLightState = CarControllerImpl.this.getAiDistanceLightState();
            BydLogHelper.logD("onAiDistanceLight2B4StateChanged", "value : " + aiDistanceLightState);
            CarControllerImpl.this.notifyAiDistanceLightStateChanged(aiDistanceLightState);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAiDistanceLight316StateChanged(int state) {
            int aiDistanceLightState = CarControllerImpl.this.getAiDistanceLightState();
            BydLogHelper.logD("onAiDistanceLight316StateChanged", "value : " + aiDistanceLightState);
            CarControllerImpl.this.notifyAiDistanceLightStateChanged(aiDistanceLightState);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onElecCarBodyStablySysStateChanged(int state) {
            BydLogHelper.logD("onElecCarBodyStablySysStateChanged", "state : " + state);
            CarControllerImpl.this.notifyElecCarBodyStablySysStateChanged(state);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAutoParkingStateChanged(int state) {
            BydLogHelper.logD("onAutoParkingStateChanged", "state : " + state);
            CarControllerImpl.this.notifyAutoParkingStateChanged(state);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onSteepDescentSlowDownStateChanged(int state) {
            BydLogHelper.logD("onSteepDescentSlowDownStateChanged", "state : " + state);
            CarControllerImpl.this.notifySteepDescentSlowDownStateChanged(state);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onBlindMonitorStateChanged1(int state) {
            BydLogHelper.logD("onBlindMonitorStateChanged1", "state : " + state);
            if (CarControllerImpl.this.getBlindMonitorType() == 1) {
                CarControllerImpl.this.notifyBlindMonitorStateChanged(1, state);
            }
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onBlindMonitorStateChanged2(int state) {
            BydLogHelper.logD("onBlindMonitorStateChanged2", "state : " + state);
            if (CarControllerImpl.this.getBlindMonitorType() == 0) {
                CarControllerImpl.this.notifyBlindMonitorStateChanged(0, state);
            }
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficSignRecognize2D5StateChanged(int state) {
            BydLogHelper.logD("onTrafficSignRecognize2D5StateChanged", "state : " + state);
            CarControllerImpl.this.notifyTrafficSignRecognizeStateChanged(1, state);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficSignRecognize2B4StateChanged(int state) {
            int iHasMessage = CarControllerImpl.this.hasMessage(141);
            BydLogHelper.logD("onTrafficSignRecognize2B4StateChanged", "state : " + state + " , hasMessage2D5 : " + iHasMessage);
            if (iHasMessage == 1) {
                return;
            }
            CarControllerImpl.this.notifyTrafficSignRecognizeStateChanged(2, state);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTrafficSignRecognize316StateChanged(int state) {
            int iHasMessage = CarControllerImpl.this.hasMessage(141);
            int iHasMessage2 = CarControllerImpl.this.hasMessage(137);
            BydLogHelper.logD("onTrafficSignRecognize316StateChanged", "state : " + state + " , hasMessage2D5 : " + iHasMessage + " , hasMessage2B4 : " + iHasMessage2);
            if (iHasMessage == 1 || iHasMessage2 == 1) {
                return;
            }
            CarControllerImpl.this.notifyTrafficSignRecognizeStateChanged(3, state);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAINavigationStateChanged(int state) {
            BydLogHelper.logD("onAINavigationStateChanged", "state : " + state);
            CarControllerImpl.this.notifyAINavigationStateChanged(state);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onTsrFault(byte fault) {
            BydLogHelper.logD("onTsrFault fault : " + ((int) fault));
            CarControllerImpl.this.notifyTsrFaultStateChanged(fault);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onBlindFault(byte fault) {
            BydLogHelper.logD("onBlindFault fault : " + ((int) fault));
            CarControllerImpl.this.notifyBlindFaultStateChanged(fault);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onAdaptiveFrontLightFault(byte fault) {
            BydLogHelper.logD("onAdaptiveFrontLightFault fault : " + ((int) fault));
            CarControllerImpl.this.notifyAdaptiveFrontLightFaultStateChanged(fault);
        }

        @Override // android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener
        public void onISLIWarningLevelSwitchStatusChanged(int status) {
            super.onISLIWarningLevelSwitchStatusChanged(status);
            BydLogHelper.logD("onISLIWarningLevelSwitchStatusChanged status : " + status);
            CarControllerImpl.this.notifyISLIWarningLevelSwitchStatusChanged(status);
        }
    };
    private final AbsBYDAutoRadarListener radarListener = new AbsBYDAutoRadarListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.19
        @Override // android.hardware.bydauto.radar.AbsBYDAutoRadarListener
        public void onReverseRadarSwitchStateChanged(int state) {
            BydLogHelper.logD("onReverseRadarSwitchStateChanged", "state : " + state);
            CarControllerImpl.this.notifyReverseRadarSwitchStateChanged(state);
        }
    };
    private final AbsBYDAutoDoorLockListener doorLockListener = new AbsBYDAutoDoorLockListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.20
        @Override // android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener
        public void onDoorLockStatusChanged(int area, int state) {
            BydLogHelper.logD("onDoorLockStatusChanged", "area : " + area + ", state : " + state);
            CarControllerImpl.this.notifyDoorLockStatusChanged(area, state);
        }
    };
    private final AbsBYDAutoBodyworkListener bodyworkDeviceCallback = new AbsBYDAutoBodyworkListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.21
        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onDoorStateChanged(int area, int state) {
            BydLogHelper.logD("onDoorStateChanged", "area : " + area + ", state : " + state);
            CarControllerImpl.this.notifyDoorStateChanged(area, state);
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onMessage5sOnlineStateChanged(int id, int state) {
            CarControllerImpl.this.notifyMessage5sOnlineStateChanged(id, state);
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onHasMessageChanged(int id, int state) {
            CarControllerImpl.this.notifyHasMessageChanged(id, state);
        }
    };
    private final AbsBYDAutoVehicleHealthListener healthListener = new AbsBYDAutoVehicleHealthListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.22
        @Override // android.hardware.IBYDAutoListener
        public void onDataChanged(IBYDAutoEvent ibydAutoEvent) {
        }

        @Override // android.hardware.IBYDAutoListener
        public void onDataEventChanged(int i, BYDAutoEventValue bydAutoEventValue) {
        }

        @Override // android.hardware.IBYDAutoListener
        public void onError(int i, String s) {
        }

        @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onTransportModeChanged(int state) {
            BydLogHelper.logD("onTransportModeChanged", "state : " + state);
            CarControllerImpl.this.notifyTransportModeChanged(state);
        }
    };
    private final AbsBYDAutoStatisticListener statisticListener = new AbsBYDAutoStatisticListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.23
        @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onTotalMileageValueChanged(float value) {
            int transportMode = CarControllerImpl.this.mBYDAutoVehicleHealthDevice.getTransportMode();
            BydLogHelper.logD("AbsBYDAutoStatisticListener", "onTotalMileageValueChanged value : " + value + " , transportMode : " + transportMode);
            if (value < 60.0f || transportMode == 3) {
                return;
            }
            CarControllerImpl.this.mBYDAutoVehicleHealthDevice.setTransportMode(3);
            CarControllerImpl.this.notifyTransportModeChanged(3);
        }
    };
    private final AbsBYDAutoGearboxListener gearboxListener = new AbsBYDAutoGearboxListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.24
        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onEPBStateChanged(int state) {
            BydLogHelper.logD("AbsBYDAutoGearboxListener", "onEPBStateChanged state : " + state);
            if (CarControllerImpl.this.mCarControllerHandler.hasMessages(4105)) {
                return;
            }
            CarControllerImpl.this.notifyEpbStateChanged(state);
        }
    };
    private final AbsBYDAutoAudioListener autoAudioListener = new AbsBYDAutoAudioListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.25
        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onVSPStateChanged(int state) {
            BydLogHelper.logD("AbsBYDAutoAudioListener", "onVSPStateChanged state : " + state);
            CarControllerImpl.this.notifyVSPStateChanged(state);
        }
    };

    public interface CarPlayAAConstant {
        public static final int AA = 1;
        public static final int CP = 0;
        public static final int CP_AA_DEVICE_CONNECTED = 4;
    }

    @Override // com.android.systemui.Dumpable
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean getAutoTBoxNetworkSupport() {
        return false;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void getMusicPlayState() {
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean isAndroidAutoFocus() {
        if (!this.mIsAndroidAutoAppConnected) {
            BydLogHelper.logD(TAG, "isAndroidAutoFocus aa  not connect");
            return false;
        }
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null && !iAndroidAutoAppManager.isServiceConnected()) {
            BydLogHelper.logD(TAG, "isAndroidAutoFocus  isServiceConnected is false");
            return false;
        }
        BydLogHelper.logD("isAndroidAutoFocus", "mIsAndroidAutoFocus:" + this.mIsAndroidAutoFocus);
        return this.mIsAndroidAutoFocus;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean isCarPlayFocus() {
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager == null || !iCarPlayAppManager.isServiceConnected()) {
            return false;
        }
        BydLogHelper.logD(TAG, "isCarPlayFocus:" + this.mIsCarPlayFocus);
        return this.mIsCarPlayFocus;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean isCarPlayPhoneCall() {
        if (!this.mIsCarPlayAppConnected) {
            BydLogHelper.logD(TAG, "isCarPlayPhoneCall cp  not connect");
            return false;
        }
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager != null && !iCarPlayAppManager.isServiceConnected()) {
            BydLogHelper.logD(TAG, "isCarPlayPhoneCall  isServiceConnected  is false");
            return false;
        }
        BydLogHelper.logD("CarControllerImpl", "mCarPlayIsPhoneCall:" + this.mCarPlayIsPhoneCall);
        return this.mCarPlayIsPhoneCall;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void initAvm() {
        if (this.mAvmConnected) {
            BydLogHelper.logD(TAG, "initAvm mAvmConnected true");
            return;
        }
        this.mAvmNaviBarListener = new AvmNaviBarListener();
        AvmManager avmManager = AvmManager.getInstance();
        this.mAvmManager = avmManager;
        if (avmManager == null) {
            BydLogHelper.logD(TAG, "initAvm mAvmManager null");
        } else {
            avmManager.init(this.mContext, new IAvmServiceConnectionListener() { // from class: com.android.systemui.statusbar.policy.CarControllerImpl.5
                @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
                public void onServiceConnected() {
                    CarControllerImpl.this.mAvmConnected = true;
                    BydLogHelper.logD(CarControllerImpl.TAG, "avm onServiceConnected: ");
                    if (CarControllerImpl.this.mAvmNaviBarListener != null) {
                        AvmManager.getInstance().registerAvmDockerStatusListener(CarControllerImpl.this.mAvmNaviBarListener);
                    }
                }

                @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
                public void onServiceDisConnected() {
                    CarControllerImpl.this.mAvmConnected = false;
                    BydLogHelper.logD(CarControllerImpl.TAG, "avm onServiceDisConnected: ");
                    if (CarControllerImpl.this.mAvmNaviBarListener != null) {
                        AvmManager.getInstance().unregisterAvmDockerStatusListener(CarControllerImpl.this.mAvmNaviBarListener);
                    }
                }

                @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
                public void onError(int code, String message) {
                    BydLogHelper.logD(CarControllerImpl.TAG, "avm onError: ");
                }
            });
        }
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean isAvmForeground() {
        if (!this.mAvmConnected) {
            BydLogHelper.logD(TAG, "isAvmForeground mAvmConnected false");
            return false;
        }
        AvmManager avmManager = this.mAvmManager;
        if (avmManager == null) {
            BydLogHelper.logD(TAG, "isAvmForeground mAvmManager null");
            return false;
        }
        int avmWorkState = avmManager.getAvmWorkState();
        BydLogHelper.logD("avm status:" + avmWorkState);
        return avmWorkState == 2;
    }

    public CarControllerImpl(Context context) {
        String str = TAG;
        BydLogHelper.logD(str, "CarControllerImpl context");
        this.mContext = context.getApplicationContext();
        initCarControl();
        this.mCarControllerHandler = new CarControllerHandler(Looper.getMainLooper());
        String str2 = SystemProperties.get("sys.boot_completed");
        Log.i(str, "boot_completed:" + str2);
        if (TextUtils.equals(SystemProperties.get("sys.sysui.boot.receive"), "1") || TextUtils.equals(SystemProperties.get("sys.sysui.cpaa.connect"), "1") || TextUtils.equals("1", str2)) {
            Log.i(str, "init cpaa");
            initCarplay(false);
            initAndroidAuto(false);
        }
    }

    public final void initCarControl() {
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor(Constants.SYS_OTA_UPDATE_FLAG), false, new SettingsObserver(null));
        this.mBYDAutoInstrumentDevice = BYDAutoInstrumentDevice.getInstance(this.mContext);
        BydInstrumentListener bydInstrumentListener = new BydInstrumentListener();
        this.mBydInstrumentListener = bydInstrumentListener;
        this.mBYDAutoInstrumentDevice.registerListener((AbsBYDAutoInstrumentListener) bydInstrumentListener);
        this.mAutoPM2p5Device = BYDAutoPM2p5Device.getInstance(this.mContext);
        this.mBYDAutoAcDevice = BYDAutoAcDevice.getInstance(this.mContext);
        this.mBYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.mContext);
        this.mBYDAutoEnergyDevice = BYDAutoEnergyDevice.getInstance(this.mContext);
        this.mBYDAutoSeatDevice = BYDAutoSeatDevice.getInstance(this.mContext);
        this.mBYDAutoChargingDevice = BYDAutoChargingDevice.getInstance(this.mContext);
        this.mBYDAutoLightDevice = BYDAutoLightDevice.getInstance(this.mContext);
        this.mBYDAutoDiPilotDevice = BYDAutoDiPilotDevice.getInstance(this.mContext);
        this.mBYDAutoRadarDevice = BYDAutoRadarDevice.getInstance(this.mContext);
        this.mBYDShowCarModel = BYDAutoShowCarDevice.getInstance(this.mContext);
        this.mBYDAutoDoorLockDevice = BYDAutoDoorLockDevice.getInstance(this.mContext);
        this.mBYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.mContext);
        this.mBydAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(this.mContext);
        this.mBYDAutoStudy = BYDAutoStudy.getInstance(this.mContext);
        this.mBYDAutoAudioDevice = BYDAutoAudioDevice.getInstance(this.mContext);
        this.mBydAutoPanoramaDevice = BYDAutoPanoramaDevice.getInstance(this.mContext);
        BydAutoPanoramaListener bydAutoPanoramaListener = new BydAutoPanoramaListener();
        this.mBydAutoPanoramaListener = bydAutoPanoramaListener;
        this.mBydAutoPanoramaDevice.registerListener((AbsBYDAutoPanoramaListener) bydAutoPanoramaListener);
        this.mBydAutoMicDevice = BYDAutoMicDevice.getInstance(this.mContext);
        BydDeviceEventListener bydDeviceEventListener = new BydDeviceEventListener();
        this.mBydDeviceEventListener = bydDeviceEventListener;
        this.mBydAutoMicDevice.registerDeviceEventListener(bydDeviceEventListener);
        this.mBYDAutoVehicleHealthDevice = BYDAutoVehicleHealthDevice.getInstance(this.mContext);
        this.mBYDAutoStatisticDevice = BYDAutoStatisticDevice.getInstance(this.mContext);
        registerCabinCallback();
    }

    private void registerCabinCallback() {
        this.mAutoPM2p5Device.registerListener(this.pm2p5DeviceCallback);
        this.mBYDAutoAcDevice.registerListener(this.acListener);
        this.mBYDAutoChargingDevice.registerListener(this.chargingListener);
        this.mBYDAutoDiPilotDevice.registerListener(this.diPilotListener);
        this.mBYDAutoEnergyDevice.registerListener(this.energyListener);
        this.mBYDAutoLightDevice.registerListener(this.lightListener);
        this.mBYDAutoRadarDevice.registerListener(this.radarListener);
        this.mBYDAutoSeatDevice.registerListener(this.seatListener);
        this.mBYDAutoSettingDevice.registerListener(this.settingListener);
        this.mBYDShowCarModel.registerListener(this.showCarModelChangeListener);
        this.mBYDAutoDoorLockDevice.registerListener(this.doorLockListener);
        this.mBYDAutoBodyworkDevice.registerListener(this.bodyworkDeviceCallback);
        this.mBYDAutoVehicleHealthDevice.registerListener(this.healthListener);
        this.mBYDAutoStatisticDevice.registerListener(this.statisticListener);
        this.mBydAutoGearboxDevice.registerListener(this.gearboxListener);
        this.mBYDAutoAudioDevice.registerListener(this.autoAudioListener);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public String getMicPackageName() {
        BYDAutoMicDevice bYDAutoMicDevice = this.mBydAutoMicDevice;
        if (bYDAutoMicDevice == null) {
            return null;
        }
        String micUsePackageName = bYDAutoMicDevice.getMicUsePackageName();
        BydLogHelper.logD(TAG, "micPkgName:" + micUsePackageName);
        return micUsePackageName;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public String getCameraPackageName() {
        BYDAutoPanoramaDevice bYDAutoPanoramaDevice = this.mBydAutoPanoramaDevice;
        if (bYDAutoPanoramaDevice == null) {
            return null;
        }
        String cameraUsePackageName = bYDAutoPanoramaDevice.getCameraUsePackageName();
        BydLogHelper.logD("cameraUsePackageName:" + cameraUsePackageName);
        return cameraUsePackageName;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public final void initAndroidAuto(boolean flagFrom) {
        String str = TAG;
        Log.i(str, "initAndroidAuto");
        this.mAndroidAutoDeviceManager = (IAndroidAutoDeviceListManager) ServiceProvider.get(IAndroidAutoDeviceListManager.class, this.mContext);
        if (this.mIsAndroidAutoAppConnected) {
            Log.i(str, "mIsAndroidAutoAppConnected is true");
            return;
        }
        if (flagFrom) {
            this.mIsAndroidAutoAppRegistering = false;
            Log.i(str, "flagFrom :" + flagFrom + ", register AndroidAutoApp again");
        }
        if (this.mIsAndroidAutoAppRegistering) {
            Log.i(str, "mIsAndroidAutoApp Registering");
            return;
        }
        this.mIsAndroidAutoAppRegistering = true;
        try {
            if (this.mAndroidAutoAppManager == null) {
                this.mAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this.mContext);
            }
            IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
            if (iAndroidAutoAppManager != null) {
                iAndroidAutoAppManager.registerConnectionListener(this.mAndroidAutoAppConnectionListener);
            } else {
                this.mIsAndroidAutoAppRegistering = false;
                Log.i(str, "initAndroidAuto mAndroidAutoAppManager is null");
            }
        } catch (Exception e) {
            Log.e(TAG, "initAndroidAuto:" + e.toString());
            this.mIsAndroidAutoAppRegistering = false;
        }
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public final void initCarplay(boolean flagFrom) {
        String str = TAG;
        Log.i(str, "initCarplay");
        this.mCarPlayDeviceManager = (ICarPlayDeviceListManager) ServiceProvider.get(ICarPlayDeviceListManager.class, this.mContext);
        if (this.mIsCarPlayAppConnected) {
            Log.i(str, "mIsCarPlayAppConnected is true");
            return;
        }
        if (flagFrom) {
            this.mIsCarPlayAppRegistering = false;
            Log.i(str, "flagFrom:" + flagFrom + ",register CarPlayApp again");
        }
        if (this.mIsCarPlayAppRegistering) {
            Log.i(str, "mIsCarPlayApp Registering");
            return;
        }
        this.mIsCarPlayAppRegistering = true;
        try {
            if (this.mCarPlayAppManager == null) {
                this.mCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, this.mContext);
            }
            ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
            if (iCarPlayAppManager != null) {
                iCarPlayAppManager.registerConnectionListener(this.mCarPlayAppConnectionListener);
            } else {
                this.mIsCarPlayAppRegistering = false;
                Log.i(str, "initCarplay mCarPlayAppManager is null");
            }
            if (this.mCarPlayServiceManager == null) {
                this.mCarPlayServiceManager = (ICarPlayServiceManager) ServiceProvider.get(ICarPlayServiceManager.class, this.mContext);
            }
            ICarPlayServiceManager iCarPlayServiceManager = this.mCarPlayServiceManager;
            if (iCarPlayServiceManager != null) {
                iCarPlayServiceManager.registerConnectionListener(this.mServiceConnectionListener);
            } else {
                Log.i(str, "initCarplay CarPlayServiceManager is null");
            }
        } catch (Exception e) {
            Log.e(TAG, "initCarplay:" + e.toString());
            this.mIsCarPlayAppRegistering = false;
        }
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean getAndroidAutoConnectionStatus() {
        if (!this.mIsAndroidAutoAppConnected) {
            BydLogHelper.logD(TAG, "AndroidAuto  not connect");
            return false;
        }
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            if (!iAndroidAutoAppManager.isServiceConnected()) {
                BydLogHelper.logD(TAG, "AndroidAuto  isServiceConnected is false");
                return false;
            }
            try {
                this.mAndroidAutoConnectionStatus = this.mAndroidAutoAppManager.getAndroidAutoSessionStatus();
                BydLogHelper.logD(TAG, "mAndroidAutoConnectionStatus:" + this.mAndroidAutoConnectionStatus);
                if (this.mAndroidAutoConnectionStatus == 4) {
                    return true;
                }
            } catch (Exception e) {
                BydLogHelper.logE(e.toString());
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean getCarPlayConnectionStatus() {
        if (!this.mIsCarPlayAppConnected) {
            BydLogHelper.logD(TAG, "CarPlay  not connect");
            return false;
        }
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            if (!iCarPlayAppManager.isServiceConnected()) {
                BydLogHelper.logD(TAG, "CarPlay isServiceConnected  is false");
                return false;
            }
            try {
                this.mCarPlayConnectionStatus = this.mCarPlayAppManager.getCarPlaySessionStatus();
                BydLogHelper.logD(TAG, "mCarPlayConnectionStatus:" + this.mCarPlayConnectionStatus);
                if (this.mCarPlayConnectionStatus == 2) {
                    return true;
                }
            } catch (Exception e) {
                BydLogHelper.logE(e.toString());
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getCarPlaySessionStatusStatus() {
        try {
            ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
            if (iCarPlayAppManager != null) {
                return iCarPlayAppManager.getCarPlaySessionStatus();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return this.mCarPlayConnectionStatus;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getCurrentOperationMode() {
        BydLogHelper.logD(TAG, "getCurrentOperationMode:" + this.mCurrentOperationMode);
        return this.mCurrentOperationMode;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setCurrentOperationMode(int mode) {
        BydLogHelper.logD(TAG, "setCurrentOperationMode:" + this.mCurrentOperationMode);
        this.mCurrentOperationMode = mode;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean isChargingListenerConnected() {
        return this.mChargeListenerConnected;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onChargingListenerConnected(boolean isBinding) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onChargingListenerConnected(isBinding);
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean isRMode() {
        int gearboxAutoModeType = this.mBydAutoGearboxDevice.getGearboxAutoModeType();
        BydLogHelper.logD(TAG, "gearbox = " + gearboxAutoModeType);
        return gearboxAutoModeType == 2;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean getUpdateFlag() {
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), Constants.SYS_OTA_UPDATE_FLAG, 0);
        BydLogHelper.logD(TAG, "updateFlag = " + i);
        return i == 1;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int setPadRotation(int orientation) {
        BydLogHelper.logD(TAG, "setPadRotation orientation:" + orientation);
        return this.mBYDAutoSettingDevice.setScreenRotation(orientation);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean getScreenRotationOverHotState() {
        int screenRotationOverHotState = this.mBYDAutoSettingDevice.getScreenRotationOverHotState();
        BydLogHelper.logD(TAG, "getScreenRotationOverHotState state:" + screenRotationOverHotState);
        return screenRotationOverHotState == 2;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getScreenAngle() {
        int screenAngle = this.mBYDAutoSettingDevice.getScreenAngle();
        BydLogHelper.logD(TAG, "angleValue:" + screenAngle);
        return screenAngle;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getChargingScheduleState() {
        return this.mBYDAutoChargingDevice.getChargingScheduleState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getShowCarModelType() {
        int showCarModelType = this.mBYDShowCarModel.getShowCarModelType();
        BydLogHelper.logD(TAG, "carModelType:" + showCarModelType);
        return showCarModelType;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int[] getPM2p5Value() {
        BYDAutoPM2p5Device bYDAutoPM2p5Device = this.mAutoPM2p5Device;
        if (bYDAutoPM2p5Device == null) {
            BydLogHelper.logD(TAG, "getPM2p5Value mAutoPM2p5Device == null !!! ");
            return INVALID_ARRAY;
        }
        int[] pM2p5Value = bYDAutoPM2p5Device.getPM2p5Value();
        BydLogHelper.logD(TAG, "getPM2p5Value pm2p5Value = " + Arrays.toString(pM2p5Value));
        return pM2p5Value;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getPowerLevel() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.mBYDAutoBodyworkDevice;
        if (bYDAutoBodyworkDevice == null) {
            return 0;
        }
        int powerLevel = bYDAutoBodyworkDevice.getPowerLevel();
        BydLogHelper.logD(TAG, "PowerLevel:" + powerLevel);
        return powerLevel;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setDynamicSuspensionMode(int value) {
        this.mBYDAutoSettingDevice.setDynamicSuspensionMode(value);
        this.mCarControllerHandler.removeMessages(4099);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4099, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getDynamicSuspensionMode() {
        int fdDynamicSuspensionMode;
        boolean z = BYDAutoStudy.getInstance(this.mContext).getDynamicSuspensionModeByCan() == 1;
        if (z) {
            fdDynamicSuspensionMode = this.mBYDAutoSettingDevice.getDynamicSuspensionMode();
        } else {
            fdDynamicSuspensionMode = this.mBYDAutoSettingDevice.getFdDynamicSuspensionMode();
        }
        BydLogHelper.logD("getDynamicSuspensionMode", "canEnable : " + z + " , dynamicValue : " + fdDynamicSuspensionMode);
        return fdDynamicSuspensionMode;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setAcDefrostState(int value) {
        this.mBYDAutoAcDevice.setAcDefrostState(0, 2, value);
        this.mCarControllerHandler.removeMessages(4100);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4100, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getAcDefrostState(int area) {
        return this.mBYDAutoAcDevice.getAcDefrostState(area);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setAcRearPanelLockState(int value) {
        this.mBYDAutoAcDevice.setAcRearPanelLockState(value);
        this.mCarControllerHandler.removeMessages(4116);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4116, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getAcRearPanelLockState() {
        return this.mBYDAutoAcDevice.getAcRearPanelLockState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setEnergyFeedback(int value) {
        this.mBYDAutoSettingDevice.setEnergyFeedback(value);
        this.mCarControllerHandler.removeMessages(4106);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4106, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getEnergyFeedback() {
        return this.mBYDAutoSettingDevice.getEnergyFeedback();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getEnergyFeedbackSupport() {
        int energyFeedbackSupport = this.mBYDAutoStudy.getEnergyFeedbackSupport();
        BydLogHelper.logD(TAG, "getEnergyFeedbackSupport state:" + energyFeedbackSupport);
        return energyFeedbackSupport;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setSteeringWheelHeatingState(int value) {
        this.mBYDAutoSettingDevice.setSteeringWheelHeatingState(value);
        this.mCarControllerHandler.removeMessages(4107);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4107, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSteeringWheelHeatingState() {
        return this.mBYDAutoSettingDevice.getSteeringWheelHeatingState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setWirelessChargingSwitchState(int value) {
        this.mBYDAutoChargingDevice.setWirelessChargingSwitchState(value);
        this.mCarControllerHandler.removeMessages(4108);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4108, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setWirelessChargingFRSwitchState(int value) {
        this.mBYDAutoChargingDevice.setWirelessChargingFRSwitchState(value);
        this.mCarControllerHandler.removeMessages(4119);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4119, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getWirelessChargingPowerFLState() {
        int wirelessChargingPowerFLState = this.mBYDAutoChargingDevice.getWirelessChargingPowerFLState();
        BydLogHelper.logD(TAG, "getWirelessChargingPowerFLState state:" + wirelessChargingPowerFLState);
        return wirelessChargingPowerFLState;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getWirelessChargingPowerFRState() {
        int wirelessChargingPowerFRState = this.mBYDAutoChargingDevice.getWirelessChargingPowerFRState();
        BydLogHelper.logD(TAG, "getWirelessChargingPowerFRState state:" + wirelessChargingPowerFRState);
        return wirelessChargingPowerFRState;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getWirelessChargingSwitchState() {
        int wirelessChargingSwitchState = this.mBYDAutoChargingDevice.getWirelessChargingSwitchState();
        BydLogHelper.logD(TAG, "getWirelessChargingSwitchState state:" + wirelessChargingSwitchState);
        return wirelessChargingSwitchState;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getWirelessChargingFRSwitchState() {
        int wirelessChargingFRSwitchState = this.mBYDAutoChargingDevice.getWirelessChargingFRSwitchState();
        BydLogHelper.logD(TAG, "getWirelessChargingFRSwitchState state:" + wirelessChargingFRSwitchState);
        return wirelessChargingFRSwitchState;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getWirelessChargingStatus() {
        int wirelessChargingState = this.mBYDAutoChargingDevice.getWirelessChargingState();
        BydLogHelper.logD(TAG, "getWirelessChargingStatus state:" + wirelessChargingState);
        return wirelessChargingState;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getWirelessChargingFRState() {
        int wirelessChargingFRState = this.mBYDAutoChargingDevice.getWirelessChargingFRState();
        BydLogHelper.logD(TAG, "getWirelessChargingFRState state:" + wirelessChargingFRState);
        return wirelessChargingFRState;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setDoorLightState(int value) {
        this.mBYDAutoLightDevice.setDoorLightState(value);
        this.mCarControllerHandler.removeMessages(4109);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4109, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getDoorLightState() {
        return this.mBYDAutoLightDevice.getDoorLightState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setAiDistanceLightState(int value) {
        this.mBYDAutoDiPilotDevice.setAiDistanceLightState(value);
        this.mCarControllerHandler.removeMessages(4098);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4098, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getAiDistanceLightState() {
        int aiDistanceLight2B4State = this.mBYDAutoDiPilotDevice.getAiDistanceLight2B4State();
        int trafficSignRecognize2B4State = this.mBYDAutoDiPilotDevice.getTrafficSignRecognize2B4State();
        BydLogHelper.logD("getAiDistanceLightState", "aiDistanceLight2B4State : " + aiDistanceLight2B4State + " , trafficSignRecognize2B4State : " + trafficSignRecognize2B4State);
        if (aiDistanceLight2B4State != 7) {
            BydLogHelper.logD("getAiDistanceLightState", "return aiDistanceLight2B4State : " + aiDistanceLight2B4State);
            return aiDistanceLight2B4State;
        }
        if (aiDistanceLight2B4State == 7 && trafficSignRecognize2B4State == 7) {
            int aiDistanceLight316State = this.mBYDAutoDiPilotDevice.getAiDistanceLight316State();
            BydLogHelper.logD("getAiDistanceLightState", "return aiDistanceLight316State : " + aiDistanceLight316State);
            return aiDistanceLight316State;
        }
        BydLogHelper.logD("getAiDistanceLightState", "return aiDistanceLight2B4State : " + aiDistanceLight2B4State);
        return aiDistanceLight2B4State;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getAdaptiveFrontLightFault() {
        byte adaptiveFrontLightFault = this.mBYDAutoDiPilotDevice.getAdaptiveFrontLightFault();
        BydLogHelper.logD("getAiDistanceLightState adaptiveFrontLightFault : " + ((int) adaptiveFrontLightFault));
        return adaptiveFrontLightFault;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setInsideLightDoorState(int value) {
        this.mBYDAutoSettingDevice.setInsideLightDoorState(value);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getIndoorLightSwitchState() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "inside_light_state", 1);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setElecCarBodyStablySysState(int value) {
        this.mBYDAutoDiPilotDevice.setElecCarBodyStablySysState(value);
        this.mCarControllerHandler.removeMessages(4097);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4097, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getElecCarBodyStablySysState() {
        return this.mBYDAutoDiPilotDevice.getElecCarBodyStablySysState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setReverseRadarSwitchState(int value) {
        this.mBYDAutoRadarDevice.setReverseRadarSwitchState(value);
        this.mCarControllerHandler.removeMessages(4111);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4111, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getReverseRadarSwitchState() {
        return this.mBYDAutoRadarDevice.getReverseRadarSwitchState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setAutoParkingState(int value) {
        this.mBYDAutoDiPilotDevice.setAutoParkingState(value);
        this.mCarControllerHandler.removeMessages(4112);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4112, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getAutoParkingState() {
        return this.mBYDAutoDiPilotDevice.getAutoParkingState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setSteepDescentSlowDownState(int value) {
        this.mBYDAutoDiPilotDevice.setSteepDescentSlowDownState(value);
        this.mCarControllerHandler.removeMessages(4096);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4096, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSteepDescentSlowDownState() {
        return this.mBYDAutoDiPilotDevice.getSteepDescentSlowDownState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setBlindMonitorState(int value) {
        this.mBYDAutoDiPilotDevice.setBlindMonitorState(value);
        this.mCarControllerHandler.removeMessages(4113);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4113, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getBlindMonitorState() {
        int blindMonitorType = getBlindMonitorType();
        BydLogHelper.logD(TAG, "getBlindMonitorState blindMonitorType : " + blindMonitorType);
        if (blindMonitorType == 1) {
            return this.mBYDAutoDiPilotDevice.getBlindMonitorState1();
        }
        if (blindMonitorType == 0) {
            return this.mBYDAutoDiPilotDevice.getBlindMonitorState2();
        }
        return this.mBYDAutoDiPilotDevice.getBlindMonitorState1();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getBlindFault() {
        byte blindFault = this.mBYDAutoDiPilotDevice.getBlindFault();
        BydLogHelper.logD("getBlindFault blindFault : " + ((int) blindFault));
        return blindFault;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getBlindMonitorType() {
        int iHasMessage = hasMessage(179);
        String str = TAG;
        BydLogHelper.logD(str, "getBlindMonitorType hasMessage453 : " + iHasMessage);
        if (iHasMessage == 1) {
            int supportBlindSpotAssist = this.mBYDAutoStudy.getSupportBlindSpotAssist();
            BydLogHelper.logD(str, "getBlindMonitorType getSupportBlindSpotAssist : " + supportBlindSpotAssist);
            if (supportBlindSpotAssist == 1) {
                return 1;
            }
        } else {
            int iHasMessage2 = hasMessage(11);
            BydLogHelper.logD(str, "getBlindMonitorType hasMessage418 : " + iHasMessage2);
            if (iHasMessage2 == 1) {
                return 0;
            }
        }
        return 1;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setTrafficSignRecognizeState(int value) {
        this.mBYDAutoDiPilotDevice.setTrafficSignRecognizeState(value);
        this.mCarControllerHandler.removeMessages(4114);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4114, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getTrafficSignRecognizeState() {
        int iHasMessage = hasMessage(141);
        int iHasMessage2 = hasMessage(137);
        String str = TAG;
        BydLogHelper.logD(str, "getTrafficSignRecognizeState hasMessage2D5 : " + iHasMessage + " , hasMessage2B4 : " + iHasMessage2);
        if (iHasMessage == 1) {
            this.mCntTrafficSignRecognize = 1;
            int trafficSignRecognize2D5State = this.mBYDAutoDiPilotDevice.getTrafficSignRecognize2D5State();
            BydLogHelper.logD(str, "getTrafficSignRecognizeState trafficSignRecognize2D5State : " + trafficSignRecognize2D5State);
            return trafficSignRecognize2D5State;
        }
        if (iHasMessage2 == 1) {
            this.mCntTrafficSignRecognize = 2;
            int trafficSignRecognize2B4State = this.mBYDAutoDiPilotDevice.getTrafficSignRecognize2B4State();
            BydLogHelper.logD(str, "getTrafficSignRecognizeState trafficSignRecognize2B4State : " + trafficSignRecognize2B4State);
            return trafficSignRecognize2B4State;
        }
        this.mCntTrafficSignRecognize = 3;
        int trafficSignRecognize316State = this.mBYDAutoDiPilotDevice.getTrafficSignRecognize316State();
        BydLogHelper.logD(str, "getTrafficSignRecognizeState trafficSignRecognize316State : " + trafficSignRecognize316State);
        return trafficSignRecognize316State;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getTsrFault() {
        byte tsrFault = this.mBYDAutoDiPilotDevice.getTsrFault();
        BydLogHelper.logD("getTsrFault tsrFault : " + ((int) tsrFault));
        return tsrFault;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setAINavigationState(int value) {
        this.mBYDAutoDiPilotDevice.setAINavigationState(value);
        this.mCarControllerHandler.removeMessages(4115);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4115, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getAINavigationState() {
        return this.mBYDAutoDiPilotDevice.getAINavigationState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getLuggageDoorState() {
        return this.mBYDAutoBodyworkDevice.getDoorState(6);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void settingLuggage(int state) {
        this.mBYDAutoBodyworkDevice.settingLuggage(state);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getDoorLockStatus(int area) {
        return this.mBYDAutoDoorLockDevice.getDoorLockStatus(area);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setDoorLockStatus(int areas, int state) {
        BydLogHelper.logD(TAG, "setDoorLockStatus areas : " + areas + " , state : " + state);
        this.mBYDAutoDoorLockDevice.setDoorLockStatus(areas, state);
        if (areas == 6) {
            this.mCarControllerHandler.removeMessages(4103);
            this.mCarControllerHandler.sendEmptyMessageDelayed(4103, 2000L);
        } else if (areas == 7) {
            this.mCarControllerHandler.removeMessages(4104);
            this.mCarControllerHandler.sendEmptyMessageDelayed(4104, 2000L);
        }
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getInstrumentBrightness() {
        int backlightBrightness = this.mBYDAutoInstrumentDevice.getBacklightBrightness();
        if (backlightBrightness <= 0) {
            backlightBrightness = 7;
        }
        return backlightBrightness > 11 ? backlightBrightness - 11 : backlightBrightness;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setInstrumentBrightness(int value) {
        try {
            this.mBYDAutoInstrumentDevice.setBacklightBrightness(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getLightStatus(int type) {
        return this.mBYDAutoLightDevice.getLightStatus(type);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getEPBState() {
        return this.mBydAutoGearboxDevice.getEPBState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setEPBState(int state) {
        this.mBydAutoGearboxDevice.setEPBState(state);
        this.mCarControllerHandler.removeMessages(4105);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4105, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getTransportMode() {
        return this.mBYDAutoVehicleHealthDevice.getTransportMode();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setTransportMode(int mode) {
        this.mBYDAutoVehicleHealthDevice.setTransportMode(mode);
        setTransportModeSpringBack();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void voiceCtlBackDoor(int cmd) {
        this.mBYDAutoSettingDevice.voiceCtlBackDoor(cmd);
        this.mCarControllerHandler.removeMessages(4118);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4118, 1000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getLeftStrutMorotRunningState() {
        return this.mBYDAutoBodyworkDevice.getLeftStrutMorotRunningState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getRightStrutMorotRunningState() {
        return this.mBYDAutoBodyworkDevice.getRightStrutMorotRunningState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getStrutMotorState() {
        int leftStrutMorotRunningState = getLeftStrutMorotRunningState();
        String str = TAG;
        BydLogHelper.logD(str, "leftStrutMotorRunningState : " + leftStrutMorotRunningState);
        int rightStrutMorotRunningState = getRightStrutMorotRunningState();
        BydLogHelper.logD(str, "rightStrutMotorRunningState : " + rightStrutMorotRunningState);
        if (leftStrutMorotRunningState == 0 && rightStrutMorotRunningState == 0) {
            this.mCarControllerHandler.removeMessages(4101);
            this.mCarControllerHandler.sendEmptyMessageDelayed(4101, 3000L);
        }
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setRearWindowLock(int state) {
        this.mBYDAutoDoorLockDevice.setRearWindowLock(state);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setPowerOffChargedState(int state) {
        BydLogHelper.logW(TAG, "setPowerOffChargedState : " + state);
        this.mBYDAutoVehicleHealthDevice.setPowerOffChargedState(state);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setTransportModeSpringBack() {
        this.mCarControllerHandler.removeMessages(4117);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4117, 2000L);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int setVSPState(int state) {
        this.mCarControllerHandler.removeMessages(4120);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4120, 2000L);
        return this.mBYDAutoAudioDevice.setVSPState(state);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getVSPState() {
        return this.mBYDAutoAudioDevice.getVSPState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setHudState(int state) {
        this.mCarControllerHandler.removeMessages(4121);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4121, 2000L);
        this.mBYDAutoSettingDevice.setHudState(state);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getHudState() {
        return this.mBYDAutoSettingDevice.getHudState();
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getISLIWarningLevelSwitchStatus() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = this.mBYDAutoDiPilotDevice;
        if (bYDAutoDiPilotDevice == null) {
            BydLogHelper.logD(TAG, "getISLIWarningLevelSwitchStatus mBYDAutoDiPilotDevice == null !!! ");
            return 0;
        }
        int iSLIWarningLevelSwitchStatus = bYDAutoDiPilotDevice.getISLIWarningLevelSwitchStatus();
        BydLogHelper.logD(TAG, "getISLIWarningLevelSwitchStatus slwState: " + iSLIWarningLevelSwitchStatus);
        return iSLIWarningLevelSwitchStatus;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setISLIWarningLevelSwitchStatus(int state) {
        BydLogHelper.logD(TAG, "setISLIWarningLevelSwitchStatus state: " + state);
        this.mCarControllerHandler.removeMessages(4122);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4122, 2000L);
        this.mBYDAutoDiPilotDevice.setISLIWarningLevelSwitchStatus(state);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getTrafficSignRecognize2B4State() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = this.mBYDAutoDiPilotDevice;
        if (bYDAutoDiPilotDevice == null) {
            BydLogHelper.logD(TAG, "getTrafficSignRecognize2B4State mBYDAutoDiPilotDevice == null !!! ");
            return 0;
        }
        int trafficSignRecognize2B4State = bYDAutoDiPilotDevice.getTrafficSignRecognize2B4State();
        BydLogHelper.logD("trafficSignRecognize2B4State：" + trafficSignRecognize2B4State);
        return trafficSignRecognize2B4State;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getIntelligentSpeedLimitAlert2B4State() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = this.mBYDAutoDiPilotDevice;
        if (bYDAutoDiPilotDevice == null) {
            BydLogHelper.logD(TAG, "getIntelligentSpeedLimitAlert2B4State mBYDAutoDiPilotDevice == null !!! ");
            return 0;
        }
        int intelligentSpeedLimitAlert2B4State = bYDAutoDiPilotDevice.getIntelligentSpeedLimitAlert2B4State();
        BydLogHelper.logD("getIntelligentSpeedLimitAlert2B4State：" + intelligentSpeedLimitAlert2B4State);
        return intelligentSpeedLimitAlert2B4State;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSupportTSRFlow() {
        BYDAutoStudy bYDAutoStudy = this.mBYDAutoStudy;
        if (bYDAutoStudy == null) {
            BydLogHelper.logD(TAG, "getSupportTSRFlow mBYDAutoStudy == null !!! ");
            return 0;
        }
        int supportTSRFlow = bYDAutoStudy.getSupportTSRFlow();
        BydLogHelper.logD("getSupportTSRFlow：" + supportTSRFlow);
        return supportTSRFlow;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getDiSusMode() {
        int diSusShowStateByCan = this.mBYDAutoStudy.getDiSusShowStateByCan();
        int diSusShowStateByCanFD = this.mBYDAutoStudy.getDiSusShowStateByCanFD();
        BydLogHelper.logD("getDiSusMode isCanShow：" + diSusShowStateByCan + " -- isCanFdShow：" + diSusShowStateByCanFD);
        int i = 1;
        if (diSusShowStateByCan != 1 && diSusShowStateByCanFD != 1) {
            i = 2;
            if (diSusShowStateByCan != 2 && diSusShowStateByCanFD != 2) {
                return 0;
            }
        }
        return i;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getBacklightLevels() {
        BYDAutoLightDevice bYDAutoLightDevice = this.mBYDAutoLightDevice;
        if (bYDAutoLightDevice == null) {
            BydLogHelper.logD("yxf mBYDAutoLightDevice == null !!! ");
            return 0;
        }
        int backlightLevels = bYDAutoLightDevice.getBacklightLevels();
        BydLogHelper.logD("yxf backlightLevels：" + backlightLevels);
        return backlightLevels;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int hasMessage(int id) {
        return this.mBYDAutoBodyworkDevice.hasMessage(id);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int syncHvacIcon2Qnx(int value) {
        return this.mAvmManager.syncHvacIcon2Qnx(value);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getPM2p5DualChannelDetectState() {
        BYDAutoPM2p5Device bYDAutoPM2p5Device = this.mAutoPM2p5Device;
        if (bYDAutoPM2p5Device == null) {
            BydLogHelper.logD(TAG, "getPM2p5DualChannelDetectState mAutoPM2p5Device == null !!! ");
            return 0;
        }
        int pM2p5DualChannelDetectState = bYDAutoPM2p5Device.getPM2p5DualChannelDetectState();
        BydLogHelper.logD("pM2p5DualChannelDetectState：" + pM2p5DualChannelDetectState);
        return pM2p5DualChannelDetectState;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int[] getPM2p5CheckState() {
        BYDAutoPM2p5Device bYDAutoPM2p5Device = this.mAutoPM2p5Device;
        if (bYDAutoPM2p5Device == null) {
            BydLogHelper.logD(TAG, "getPM2p5CheckState mAutoPM2p5Device == null !!! ");
            return null;
        }
        return bYDAutoPM2p5Device.getPM2p5CheckState();
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void addCallback(CarController.Callback callback) {
        synchronized (this.mCallbacks) {
            if (callback != null) {
                if (!this.mCallbacks.contains(callback)) {
                    this.mCallbacks.add(callback);
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void removeCallback(CarController.Callback callback) {
        synchronized (this.mCallbacks) {
            if (callback == null) {
                return;
            }
            this.mCallbacks.remove(callback);
        }
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSeatVentilatingState(int area) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.mBYDAutoSettingDevice;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logD("mBYDAutoSettingDevice == null !!! ");
            return 0;
        }
        int seatVentilatingState = bYDAutoSettingDevice.getSeatVentilatingState(area);
        BydLogHelper.logD("state：" + seatVentilatingState);
        return seatVentilatingState;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSeatVentilatingState2(int area) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.mBYDAutoSettingDevice;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logD("mBYDAutoSettingDevice == null !!! ");
            return 0;
        }
        int seatVentilatingState2 = bYDAutoSettingDevice.getSeatVentilatingState2(area);
        BydLogHelper.logD("state：" + seatVentilatingState2);
        return seatVentilatingState2;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSeatVentilatingNotL1PState() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.mBYDAutoSettingDevice;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logD("mBYDAutoSettingDevice == null !!! ");
            return 0;
        }
        int seatVentilatingNotL1PState = bYDAutoSettingDevice.getSeatVentilatingNotL1PState();
        BydLogHelper.logD("state：" + seatVentilatingNotL1PState);
        return seatVentilatingNotL1PState;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSeatHeatingState2(int area) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.mBYDAutoSettingDevice;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logD("mBYDAutoSettingDevice == null !!! ");
            return 0;
        }
        int seatHeatingState2 = bYDAutoSettingDevice.getSeatHeatingState2(area);
        BydLogHelper.logD("state：" + seatHeatingState2);
        return seatHeatingState2;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSeatHeatingNotL1PState() {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.mBYDAutoSettingDevice;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logD("mBYDAutoSettingDevice == null !!! ");
            return 0;
        }
        int seatHeatingNotL1PState = bYDAutoSettingDevice.getSeatHeatingNotL1PState();
        BydLogHelper.logD("state：" + seatHeatingNotL1PState);
        return seatHeatingNotL1PState;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSeatHeatingState(int area) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.mBYDAutoSettingDevice;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logD("mBYDAutoSettingDevice == null !!! ");
            return 0;
        }
        int seatHeatingState = bYDAutoSettingDevice.getSeatHeatingState(area);
        BydLogHelper.logD("state：" + seatHeatingState);
        return seatHeatingState;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int get5LevelSeatHeatingState(int area) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.mBYDAutoSettingDevice;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logD("mBYDAutoSettingDevice == null !!! ");
            return 0;
        }
        int i = bYDAutoSettingDevice.get5LevelSeatHeatingState(area);
        BydLogHelper.logD("state：" + i);
        return i;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int get5LevelSeatVentilatingState(int area) {
        BYDAutoSettingDevice bYDAutoSettingDevice = this.mBYDAutoSettingDevice;
        if (bYDAutoSettingDevice == null) {
            BydLogHelper.logD("mBYDAutoSettingDevice == null !!! ");
            return 0;
        }
        int i = bYDAutoSettingDevice.get5LevelSeatVentilatingState(area);
        BydLogHelper.logD("state：" + i);
        return i;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSupportDoubleConfirm() {
        BYDAutoStudy bYDAutoStudy = this.mBYDAutoStudy;
        if (bYDAutoStudy == null) {
            BydLogHelper.logD("mBYDAutoStudy == null !!! ");
            return 0;
        }
        int supportDoubleConfirm = bYDAutoStudy.getSupportDoubleConfirm();
        BydLogHelper.logD("state：" + supportDoubleConfirm);
        return supportDoubleConfirm;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getLaneAssistType() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = this.mBYDAutoDiPilotDevice;
        if (bYDAutoDiPilotDevice == null) {
            BydLogHelper.logD("mBYDAutoDiPilotDevice == null !!! ");
            return 0;
        }
        int laneAssistType = bYDAutoDiPilotDevice.getLaneAssistType();
        BydLogHelper.logD("type：" + laneAssistType);
        return laneAssistType;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void setLaneAssistType(int value) {
        BydLogHelper.logD(TAG, "setLaneAssistType value: " + value);
        this.mCarControllerHandler.removeMessages(4123);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4123, 2000L);
        this.mBYDAutoDiPilotDevice.setLaneAssistType(value);
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getIntelligentSpeedLimitMapVersionDisplay() {
        BYDAutoStudy bYDAutoStudy = this.mBYDAutoStudy;
        if (bYDAutoStudy == null) {
            BydLogHelper.logD("mBYDAutoStudy == null !!! ");
            return 0;
        }
        int intelligentSpeedLimitMapVersionDisplay = bYDAutoStudy.getIntelligentSpeedLimitMapVersionDisplay();
        BydLogHelper.logD("state：" + intelligentSpeedLimitMapVersionDisplay);
        return intelligentSpeedLimitMapVersionDisplay;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSupportLaneDepartureWarning() {
        BYDAutoStudy bYDAutoStudy = this.mBYDAutoStudy;
        if (bYDAutoStudy == null) {
            BydLogHelper.logD("mBYDAutoStudy == null !!! ");
            return 0;
        }
        int supportLaneDepartureWarning = bYDAutoStudy.getSupportLaneDepartureWarning();
        BydLogHelper.logD("state：" + supportLaneDepartureWarning);
        return supportLaneDepartureWarning;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSupportLaneDepartureStop() {
        BYDAutoStudy bYDAutoStudy = this.mBYDAutoStudy;
        if (bYDAutoStudy == null) {
            BydLogHelper.logD("mBYDAutoStudy == null !!! ");
            return 0;
        }
        int supportLaneDepartureStop = bYDAutoStudy.getSupportLaneDepartureStop();
        BydLogHelper.logD("state：" + supportLaneDepartureStop);
        return supportLaneDepartureStop;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getSupportEmergencyLaneKeepAssist() {
        BYDAutoStudy bYDAutoStudy = this.mBYDAutoStudy;
        if (bYDAutoStudy == null) {
            BydLogHelper.logD("mBYDAutoStudy == null !!! ");
            return 0;
        }
        int supportEmergencyLaneKeepAssist = bYDAutoStudy.getSupportEmergencyLaneKeepAssist();
        BydLogHelper.logD("state：" + supportEmergencyLaneKeepAssist);
        return supportEmergencyLaneKeepAssist;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int setEmergencyLaneAssistanceState(int state) {
        BydLogHelper.logD(TAG, "setEmergencyLaneAssistanceState state: " + state);
        this.mCarControllerHandler.removeMessages(4123);
        this.mCarControllerHandler.sendEmptyMessageDelayed(4123, 2000L);
        this.mBYDAutoDiPilotDevice.setEmergencyLaneAssistanceState(state);
        return 0;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getEmergencyLaneAssistance32FState() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDevice = this.mBYDAutoDiPilotDevice;
        if (bYDAutoDiPilotDevice == null) {
            BydLogHelper.logD("mBYDAutoDiPilotDevice == null !!! ");
            return 0;
        }
        int emergencyLaneAssistance32FState = bYDAutoDiPilotDevice.getEmergencyLaneAssistance32FState();
        BydLogHelper.logD("state：" + emergencyLaneAssistance32FState);
        return emergencyLaneAssistance32FState;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getTempRegulatingAccuracy() {
        BYDAutoAcDevice bYDAutoAcDevice = this.mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logD("getTempRegulatingAccuracy mBYDAutoAcDevice == null !!! ");
            return -1;
        }
        int tempRegulatingAccuracy = bYDAutoAcDevice.getTempRegulatingAccuracy();
        BydLogHelper.logD("getTempRegulatingAccuracy tempRegulatingAccuracy = " + tempRegulatingAccuracy);
        return tempRegulatingAccuracy;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getAutoType() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.mBYDAutoBodyworkDevice;
        if (bYDAutoBodyworkDevice == null) {
            BydLogHelper.logD("getAutoType mBYDAutoBodyworkDevice == null !!! ");
            return -1;
        }
        int autoType = bYDAutoBodyworkDevice.getAutoType();
        BydLogHelper.logD("getAutoType autoType = " + autoType);
        return autoType;
    }

    private class BydInstrumentListener extends AbsBYDAutoInstrumentListener {
        private BydInstrumentListener() {
        }

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onBacklightBrightnessChanged(int value) {
            BydLogHelper.logD("onBacklightBrightnessChanged", "value : " + value);
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onInstrumentBrightnessChanged(value);
                }
            }
        }

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onMusicInfoUrlChange(String musicName, String artistName, int playState, int musicSource, String imgUrl) {
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onMusicInfoUrlChange(musicName, artistName, playState, musicSource, imgUrl);
                }
            }
        }
    }

    private class BydAutoPanoramaListener extends AbsBYDAutoPanoramaListener {
        private BydAutoPanoramaListener() {
        }

        @Override // android.hardware.bydauto.panorama.AbsBYDAutoPanoramaListener
        public void onCameraUseChange(String packageName, boolean state) {
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onCameraUseChange(packageName, state);
                }
            }
        }
    }

    private class BydDeviceEventListener extends DeviceEventListener {
        private BydDeviceEventListener() {
        }

        @Override // android.hardware.bydauto.mic.DeviceEventListener
        public void onMicUseChange(String packageName, boolean state) {
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onMicUseChange(packageName, state);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAcDefrostStateChanged(int area, int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onAcDefrostStateChanged(area, state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAcRearPanelLockStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onAcRearPanelLockStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDoorStateChanged(int area, int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onDoorStateChanged(area, state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDoorLockStatusChanged(int area, int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onDoorLockStatusChanged(area, state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyMessage5sOnlineStateChanged(int id, int state) {
        BydLogHelper.logV("notifyMessage5sOnlineStateChanged", "id = " + id + " , state : " + state);
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onMessage5sOnlineStateChanged(id, state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyHasMessageChanged(int id, int state) {
        BydLogHelper.logV("notifyHasMessageChanged", "id = " + id + " , state : " + state);
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onHasMessageChanged(id, state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDynamicSuspensionModeChanged(int value) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onDynamicSuspensionModeChanged(value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyEnergyFeedbackStrengthChanged(int value) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onEnergyFeedbackLevelChanged(value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySteeringWheelHeatingStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onSteeringWheelWarmStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWirelessChargingSwitchStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onWirelessChargingSwitchStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWirelessChargingFRSwitchStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onWirelessChargingFRSwitchStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWirelessChargingPowerFLStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onWirelessChargingPowerFLStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWirelessChargingPowerFRStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onWirelessChargingPowerFRStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWirelessChargingStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onWirelessChargingStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWirelessChargingFRStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onWirelessChargingFRStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyInsideLightDoorStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onInsideLightDoorStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDoorLightStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onDoorLightStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAiDistanceLightStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onAiDistanceLightStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBacklightLevelsChanged(int level) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onBacklightLevelsChanged(level);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyLaneAssistTypeChanged(int value) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onLaneAssistTypeChanged(value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyEmergencyLaneAssistance32FStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onEmergencyLaneAssistance32FStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyEspOffKeyStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onEspOffKeyStatusChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAdaptiveFrontLightFaultStateChanged(byte fault) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onAdaptiveFrontLightFault(fault);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyElecCarBodyStablySysStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onElecCarBodyStablySysStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAutoParkingStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onAutoParkingStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySteepDescentSlowDownStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onSteepDescentSlowDownStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBlindMonitorStateChanged(int type, int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onBlindMonitorStateChanged(type, state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBlindFaultStateChanged(byte fault) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onBlindFault(fault);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyTrafficSignRecognizeStateChanged(int signType, int state) {
        this.mCntTrafficSignRecognize = signType;
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onTrafficSignRecognizeStateChanged(signType, state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyTsrFaultStateChanged(byte fault) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onTsrFault(fault);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAINavigationStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onAINavigationStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyReverseRadarSwitchStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onReverseRadarSwitchStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyEpbStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onEpbStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyVSPStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onVSPStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyHudStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onHudStateChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySLWStateChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onISLIWarningLevelSwitchStatusChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyTransportModeChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onTransportModeChanged(state);
            }
        }
        notifyTransportModeSwitchChanged(state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyTransportModeSwitchChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onTransportModeSwitchChanged(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyISLIWarningLevelSwitchStatusChanged(int state) {
        synchronized (this.mCallbacks) {
            Iterator<CarController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onISLIWarningLevelSwitchStatusChanged(state);
            }
        }
    }

    private final class CarControllerHandler extends Handler {
        public static final int AI_DISTANCE_LIGHT_STATE = 4098;
        public static final int AI_NAVIGATION = 4115;
        public static final int AUTO_PARKING = 4112;
        public static final int BLIND_MONITOR = 4113;
        public static final int CAR_WINDOW_LOCK = 4102;
        public static final int DEFROST_STATE = 4100;
        public static final int DOOR_LIGHT = 4109;
        public static final int DYNAMIC_SUSPENSION_MODE = 4099;
        public static final int ELEC_CAR_BODY_STABLY_STATE = 4097;
        public static final int ENERGY_FEEDBACK_STRENGTH = 4106;
        public static final int EPB_STATE = 4105;
        public static final int HUD_STATE = 4121;
        public static final int INSIDE_LIGHT_DOOR = 4110;
        public static final int LDA_TYPE = 4123;
        public static final int LEFT_CHILD_LOCK = 4103;
        public static final int REAR_CONTROL_LOCK = 4116;
        public static final int REVERSE_RADAR = 4111;
        public static final int RIGHT_CHILD_LOCK = 4104;
        public static final int SLW_STATE = 4122;
        public static final int STEEP_DESCENT_SLOWDOWN_STATE = 4096;
        public static final int STEERING_WHEEL_HEATING_AUTO = 4107;
        public static final int STRUT_MOTOR = 4118;
        public static final int TRAFFIC_SIGN_RECOGNIZE = 4114;
        public static final int TRANSPORT_MODE = 4117;
        public static final int TRUNK_DOOR_OPEN = 4101;
        public static final int VSP_STATE = 4120;
        public static final int WIRELESS_CHARGING = 4108;
        public static final int WIRELESS_CHARGING_FR = 4119;

        public CarControllerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            BydLogHelper.logD(CarControllerImpl.TAG, "CarControllerHandler handleMessage : " + msg.what);
            switch (msg.what) {
                case 4096:
                    CarControllerImpl carControllerImpl = CarControllerImpl.this;
                    carControllerImpl.notifySteepDescentSlowDownStateChanged(carControllerImpl.getSteepDescentSlowDownState());
                    break;
                case 4097:
                    CarControllerImpl carControllerImpl2 = CarControllerImpl.this;
                    carControllerImpl2.notifyElecCarBodyStablySysStateChanged(carControllerImpl2.getElecCarBodyStablySysState());
                    break;
                case 4098:
                    CarControllerImpl carControllerImpl3 = CarControllerImpl.this;
                    carControllerImpl3.notifyAiDistanceLightStateChanged(carControllerImpl3.getAiDistanceLightState());
                    break;
                case 4099:
                    CarControllerImpl carControllerImpl4 = CarControllerImpl.this;
                    carControllerImpl4.notifyDynamicSuspensionModeChanged(carControllerImpl4.getDynamicSuspensionMode());
                    break;
                case 4100:
                    CarControllerImpl carControllerImpl5 = CarControllerImpl.this;
                    carControllerImpl5.notifyAcDefrostStateChanged(2, carControllerImpl5.getAcDefrostState(2));
                    break;
                case 4101:
                    CarControllerImpl carControllerImpl6 = CarControllerImpl.this;
                    carControllerImpl6.notifyDoorStateChanged(6, carControllerImpl6.getLuggageDoorState());
                    break;
                case 4102:
                default:
                    BydLogHelper.logE("CarControllerHandler handleMessage no this msg : " + msg.what);
                    break;
                case 4103:
                    CarControllerImpl carControllerImpl7 = CarControllerImpl.this;
                    carControllerImpl7.notifyDoorLockStatusChanged(6, carControllerImpl7.getDoorLockStatus(6));
                    break;
                case 4104:
                    CarControllerImpl carControllerImpl8 = CarControllerImpl.this;
                    carControllerImpl8.notifyDoorLockStatusChanged(7, carControllerImpl8.getDoorLockStatus(7));
                    break;
                case 4105:
                    CarControllerImpl carControllerImpl9 = CarControllerImpl.this;
                    carControllerImpl9.notifyEpbStateChanged(carControllerImpl9.getEPBState());
                    break;
                case 4106:
                    CarControllerImpl carControllerImpl10 = CarControllerImpl.this;
                    carControllerImpl10.notifyEnergyFeedbackStrengthChanged(carControllerImpl10.getEnergyFeedback());
                    break;
                case 4107:
                    CarControllerImpl carControllerImpl11 = CarControllerImpl.this;
                    carControllerImpl11.notifySteeringWheelHeatingStateChanged(carControllerImpl11.getSteeringWheelHeatingState());
                    break;
                case 4108:
                    CarControllerImpl carControllerImpl12 = CarControllerImpl.this;
                    carControllerImpl12.notifyWirelessChargingSwitchStateChanged(carControllerImpl12.getWirelessChargingSwitchState());
                    break;
                case 4109:
                    CarControllerImpl carControllerImpl13 = CarControllerImpl.this;
                    carControllerImpl13.notifyDoorLightStateChanged(carControllerImpl13.getDoorLightState());
                    break;
                case 4110:
                    CarControllerImpl carControllerImpl14 = CarControllerImpl.this;
                    carControllerImpl14.notifyInsideLightDoorStateChanged(carControllerImpl14.getIndoorLightSwitchState());
                    break;
                case 4111:
                    CarControllerImpl carControllerImpl15 = CarControllerImpl.this;
                    carControllerImpl15.notifyReverseRadarSwitchStateChanged(carControllerImpl15.getReverseRadarSwitchState());
                    break;
                case 4112:
                    CarControllerImpl carControllerImpl16 = CarControllerImpl.this;
                    carControllerImpl16.notifyAutoParkingStateChanged(carControllerImpl16.getAutoParkingState());
                    break;
                case 4113:
                    CarControllerImpl carControllerImpl17 = CarControllerImpl.this;
                    carControllerImpl17.notifyBlindMonitorStateChanged(carControllerImpl17.getBlindMonitorType(), CarControllerImpl.this.getBlindMonitorState());
                    break;
                case 4114:
                    CarControllerImpl carControllerImpl18 = CarControllerImpl.this;
                    carControllerImpl18.notifyTrafficSignRecognizeStateChanged(carControllerImpl18.mCntTrafficSignRecognize, CarControllerImpl.this.getTrafficSignRecognizeState());
                    break;
                case 4115:
                    CarControllerImpl carControllerImpl19 = CarControllerImpl.this;
                    carControllerImpl19.notifyAINavigationStateChanged(carControllerImpl19.getAINavigationState());
                    break;
                case 4116:
                    CarControllerImpl carControllerImpl20 = CarControllerImpl.this;
                    carControllerImpl20.notifyAcRearPanelLockStateChanged(carControllerImpl20.getAcRearPanelLockState());
                    break;
                case 4117:
                    CarControllerImpl carControllerImpl21 = CarControllerImpl.this;
                    carControllerImpl21.notifyTransportModeSwitchChanged(carControllerImpl21.getTransportMode());
                    break;
                case 4118:
                    CarControllerImpl.this.getStrutMotorState();
                    break;
                case 4119:
                    CarControllerImpl carControllerImpl22 = CarControllerImpl.this;
                    carControllerImpl22.notifyWirelessChargingFRSwitchStateChanged(carControllerImpl22.getWirelessChargingFRSwitchState());
                    break;
                case 4120:
                    CarControllerImpl carControllerImpl23 = CarControllerImpl.this;
                    carControllerImpl23.notifyVSPStateChanged(carControllerImpl23.getVSPState());
                    break;
                case 4121:
                    CarControllerImpl carControllerImpl24 = CarControllerImpl.this;
                    carControllerImpl24.notifyHudStateChanged(carControllerImpl24.getHudState());
                    break;
                case 4122:
                    CarControllerImpl carControllerImpl25 = CarControllerImpl.this;
                    carControllerImpl25.notifySLWStateChanged(carControllerImpl25.getISLIWarningLevelSwitchStatus());
                    break;
                case 4123:
                    CarControllerImpl carControllerImpl26 = CarControllerImpl.this;
                    carControllerImpl26.notifyLaneAssistTypeChanged(carControllerImpl26.getLaneAssistType());
                    break;
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public int getCntTrafficSignRecognize() {
        return this.mCntTrafficSignRecognize;
    }

    private class AvmNaviBarListener implements AvmManager.AvmDockerStatusListener {
        private AvmNaviBarListener() {
        }

        @Override // com.ts.avm.sdk.AvmManager.AvmDockerStatusListener
        public void onAvmDockerStatusChanged(int value) {
            synchronized (CarControllerImpl.this.mCallbacks) {
                Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((CarController.Callback) it.next()).onAvmDockerStatusChanged(value);
                }
            }
        }
    }

    private class SettingsObserver extends ContentObserver {
        public SettingsObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            String lastPathSegment = uri.getLastPathSegment();
            BydLogHelper.logD("onChange selfChange : " + selfChange + " , option : " + lastPathSegment);
            if (TextUtils.equals(lastPathSegment, Constants.SYS_OTA_UPDATE_FLAG)) {
                synchronized (CarControllerImpl.this.mCallbacks) {
                    Iterator it = CarControllerImpl.this.mCallbacks.iterator();
                    while (it.hasNext()) {
                        ((CarController.Callback) it.next()).onUpdateFlag(CarControllerImpl.this.getUpdateFlag());
                    }
                }
            }
        }
    }

    private AndroidAutoDevice getConnectedAndroidAutoDevice() {
        List<AndroidAutoDevice> androidAutoDevices = getAndroidAutoDevices();
        if (androidAutoDevices == null) {
            return null;
        }
        for (AndroidAutoDevice androidAutoDevice : androidAutoDevices) {
            if (androidAutoDevice != null && androidAutoDevice.getDeviceStatus() == 4) {
                return androidAutoDevice;
            }
        }
        return null;
    }

    private List<AndroidAutoDevice> getAndroidAutoDevices() {
        IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager = this.mAndroidAutoDeviceManager;
        if (iAndroidAutoDeviceListManager == null) {
            return null;
        }
        try {
            return iAndroidAutoDeviceListManager.getAndroidAutoDeviceList();
        } catch (Throwable th) {
            BydLogHelper.logD(TAG, "getAndroidAutoDevices,RemoteException:" + th);
            return null;
        }
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean isAaVrActive() {
        if (this.mAndroidAutoDeviceManager == null) {
            return false;
        }
        try {
            boolean androidAutoAppStatus = this.mAndroidAutoAppManager.getAndroidAutoAppStatus(1);
            BydLogHelper.logD(TAG, "isAaVrActive: " + androidAutoAppStatus);
            return androidAutoAppStatus;
        } catch (Throwable th) {
            BydLogHelper.logD(TAG, "isAaVrActive,RemoteException:" + th);
            return false;
        }
    }

    private CarPlayDevice getConnectedCarPlayDevice() {
        List<CarPlayDevice> carPlayDevices = getCarPlayDevices();
        if (carPlayDevices == null) {
            return null;
        }
        for (CarPlayDevice carPlayDevice : carPlayDevices) {
            if (carPlayDevice != null && carPlayDevice.getDeviceStatus() == 4) {
                return carPlayDevice;
            }
        }
        return null;
    }

    private List<CarPlayDevice> getCarPlayDevices() {
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager == null) {
            return null;
        }
        try {
            return iCarPlayDeviceListManager.getCarPlayDeviceList();
        } catch (RemoteException | SystemUIException e) {
            e.printStackTrace();
            BydLogHelper.logE(TAG, "getCarPlayDevices,RemoteException:" + e);
            return null;
        }
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean isWirelessAndroidAutoConnected() {
        AndroidAutoDevice connectedAndroidAutoDevice = getConnectedAndroidAutoDevice();
        return (connectedAndroidAutoDevice == null || connectedAndroidAutoDevice.isUsbDevice()) ? false : true;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean isAndroidAutoConnected() {
        return getConnectedAndroidAutoDevice() != null;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean isCarPlayConnected() {
        return getConnectedCarPlayDevice() != null;
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public boolean isWirelessCarPlayConnected() {
        CarPlayDevice connectedCarPlayDevice = getConnectedCarPlayDevice();
        return (connectedCarPlayDevice == null || connectedCarPlayDevice.isUsbDevice()) ? false : true;
    }

    private void disconnectCarPlay(String deviceId) {
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager != null) {
            try {
                iCarPlayDeviceListManager.disconnectCarPlay(deviceId);
                BydLogHelper.logD(TAG, "disconnectCarPlay deviceId:" + deviceId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void disconnectAndroidAuto(String deviceId) {
        IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager = this.mAndroidAutoDeviceManager;
        if (iAndroidAutoDeviceListManager != null) {
            try {
                iAndroidAutoDeviceListManager.disconnectAndroidAuto(deviceId);
                BydLogHelper.logD(TAG, "disconnectAndroidAuto deviceId:" + deviceId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CarController
    public void disconnectDevice(int deviceType) {
        if (deviceType == 0) {
            CarPlayDevice connectedCarPlayDevice = getConnectedCarPlayDevice();
            if (connectedCarPlayDevice != null) {
                String btAddr = connectedCarPlayDevice.getBtAddr();
                if (TextUtils.isEmpty(btAddr)) {
                    btAddr = connectedCarPlayDevice.getUsbSerialNumber();
                }
                disconnectCarPlay(btAddr);
                return;
            }
            return;
        }
        AndroidAutoDevice connectedAndroidAutoDevice = getConnectedAndroidAutoDevice();
        if (connectedAndroidAutoDevice != null) {
            String btAddr2 = connectedAndroidAutoDevice.getBtAddr();
            if (TextUtils.isEmpty(btAddr2)) {
                btAddr2 = connectedAndroidAutoDevice.getUsbSerialNumber();
            }
            disconnectAndroidAuto(btAddr2);
        }
    }
}
