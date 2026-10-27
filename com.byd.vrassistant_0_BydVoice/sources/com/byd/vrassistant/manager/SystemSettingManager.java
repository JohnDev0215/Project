package com.byd.vrassistant.manager;

import android.app.Instrumentation;
import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.media.AudioManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import cerence.ark.assistant.api.notification.DialogEvent;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckCPAAInForegroundResult;
import cerence.ark.assistant.controller.enumeration.CPAAStateEnum;
import cerence.ark.assistant.controller.enumeration.RadioBandEnum;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import cerence.ark.assistant.controller.enumeration.YNUEnum;
import com.blankj.utilcode.util.StringUtils;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.AudioHelper;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.conversation.VehicleSettingsHelper;
import com.byd.vrassistant.conversation.ZoomCarHelper;
import com.byd.vrassistant.eventbus.SystemStatusEvent;
import com.byd.vrassistant.hint.bean.WakeUpFreeWordsSwitch;
import com.byd.vrassistant.receiver.ScreenObserver8155;
import com.byd.vrassistant.utils.CallingReceiver8155;
import com.byd.vrassistant.utils.ContactReceiver8155;
import com.byd.vrassistant.utils.DataUtils;
import com.byd.vrassistant.utils.DevicePairingReceiver;
import com.byd.vrassistant.utils.ExecutorUtils;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SharedPreferencesUtil;
import com.byd.vrassistant.utils.SystemPropertiesUtils;
import com.byd.vrassistant.utils.XCallToolReceiver8155;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ts.avm.sdk.AvmManager;
import com.ts.avm.sdk.IAvmServiceConnectionListener;
import com.ts.car.core.ServiceProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import org.greenrobot.eventbus.EventBus;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.carplay.ICallStateListener;
import ts.car.service.carplay.ICarPlayAppListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayStateListener;
import ts.car.service.carplay.data.CallStateInfo;

/* JADX INFO: loaded from: classes2.dex */
public class SystemSettingManager {
    private static final String ACTIVATED = "activated";
    public static final String BOOT_BUSINESS = "sys.byd.boot_business";
    private static final String Field_CarModelCode = "carModelCode";
    private static final String Field_MIC12 = "dspMIC12Gain";
    private static final String Field_MIC34 = "dspMIC34Gain";
    private static final String Field_MIC_Ref = "dspMicXFRefGain";
    private static final String GUIDE = "guide";
    private static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    private static final String SERVICE_PKG = "com.byd.carsettings";
    private static final String TAG = "NEU_SystemSettingManager";
    private static String[] activeCountryCode = {"CN", "SG", "HK", "MO", "NP", "AU", "TH", "JP", "LK", "PK", "IN", "MY", "ID", "MM", "LA", "KH", "PH", "PF", "NC", "NZ", "VNM", "CL", "UY", "CO", "DO", "CR", "PE", "US", "EC", "PY", "BR", "BOL", "BS ", "PA", "CW", "MX", "CA", "GT", "JM", "SA", "AE", "EG", "GH", "QA", "MA", "BH", "OM", "KW", YNUEnum.NO, "DK", "NL", "GB", "PT", "BE", "SE", "CH", "AT", "IT", "ES", "FI", "IE", "GR", "DE", "FR", "BG", "CY", "HR", "CZ", "EE", "HU", "LV", "LT", "LU", "MT", "PL", "RO", "SK", "SI", "IS", "LI", "TR", "UA", "IL", "ME", "RS", "BA", "MK", "AL", RadioBandEnum.AM, "AZ", "BY", "MD", "KZ", "KG", "TJ", "UZ", "MC", "RU", "VA", "MH", "MN", "KR", "BT", "TW", "KP", "CV", "TN", "LB", "JO", "ZA", "RE", "ZW", "ZM", "MU", "MG", "AO", "BRN", "BW"};
    public static int carplayCallSendingState = 0;
    private static volatile SystemSettingManager mInstance = null;
    public static final String schemeADASLaneAssist = "byd_pad://byd_car_settings:2933/launcher?activity=adas_lane_assist";
    private static final String schemeCarSetting = "byd_pad://byd_car_settings:2933/launcher?action=car_setting";
    public AudioManager audioManager;
    CountDownTimer countDownAndroidAutoTimer;
    CountDownTimer countDownTimer;
    int currentGearBoxValue;
    boolean isAAConnect;
    boolean isAndroidAutoForeground;
    boolean isCPConnect;
    volatile boolean isSendingHomeKey;
    CallingReceiver8155 mCallingReceiver8155;
    ContactReceiver8155 mContactReceiver8155;
    DevicePairingReceiver mDevicePairingReceiver;
    public int volumeValue;
    private Context context = BydVoiceApplication.getContext();
    String scheme = "byd_pad://byd_car_settings:2933/launcher?action=screen_saver";
    String scheme2 = "byd_pad://byd_car_settings:2933/launcher?action=finish_screen_saver";
    String schemeWifi = "byd_pad://byd_car_settings:2933/launcher?activity=WiFi";
    String schemeHotSpot = "byd_pad://byd_car_settings:2933/launcher?activity=Hotspot";
    String schemeBluetooth = "byd_pad://byd_car_settings:2933/launcher?activity=Bluetooth";
    String schema_celluardata = "byd_pad://byd_car_settings:2933/launcher?action=cellular_data";
    List<Integer> rightDiverAry = Arrays.asList(65, 852, 853, 977, 61, 66, 81, 94, 92, 91, 60, 62, 64, 975, 1242, 1876, 27, 263, 260, 230, 44, 353, 357, 356);
    List<Integer> spotifyRegionAry = Arrays.asList(66, 81, 65, 61, 91, 60, 64, 56, 598, 57, 51, 1, 593, 595, 55, 52, 966, 971, 965, 968, 974, 973, 47, 45, 31, 44, 351, 32, 46, 41, 43, 39, 34, 358, 353, 30, 49, 33, 352, 48, 90, 377);
    List<Integer> disGPSRegionAry = Arrays.asList(82);
    List<Integer> disGPSICCIDRegionAry = Arrays.asList(450);
    public int maxVolume = 39;
    private int mVechicleType = 0;
    boolean carplayCallRinging = false;
    boolean carplayCallSending = false;
    boolean carplayCallOthers = false;
    boolean acc_on_switched = false;
    boolean lastCarplayShow = false;
    private ICarPlayAppManager carPlayAppManager = null;
    private ICarPlayAppListener iCarPlayAppListener = new AnonymousClass1();
    private String mVirtualVin = null;
    private boolean mPTTMode = false;
    private AvmManager mAvmManager = null;
    AbsBYDAutoGearboxListener bydAutoGearboxListener = new AbsBYDAutoGearboxListener() { // from class: com.byd.vrassistant.manager.SystemSettingManager.2
        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearChanged(int i) {
            super.onGearChanged(i);
            RLog.i(SystemSettingManager.TAG, "onGearChanged : " + i);
            SystemSettingManager.this.currentGearBoxValue = i;
            if (SystemSettingManager.this.currentGearBoxValue == 2) {
                RLog.e(SystemSettingManager.TAG, "car is in reverse :" + SystemSettingManager.this.currentGearBoxValue);
                VoiceAssistantManager.get().sendCancelPTTEvent();
            }
        }
    };
    Handler avmHandle = new Handler(BydVoiceApplication.getContext().getMainLooper());
    Runnable avmRunnerable = new Runnable() { // from class: com.byd.vrassistant.manager.SystemSettingManager.3
        @Override // java.lang.Runnable
        public void run() {
            if (VoiceAssistantManager.get().getDialogEvent() == DialogEvent.Event.CONVERSATION_END) {
                VoiceAssistantManager.get().stopVoiceAssistant();
                VoiceAssistantManager.get().startVoiceAssistant();
            }
        }
    };
    Runnable carplayShowRunnerable = new Runnable() { // from class: com.byd.vrassistant.manager.SystemSettingManager.4
        @Override // java.lang.Runnable
        public void run() {
            try {
                if (SystemSettingManager.this.carPlayAppManager == null || !SystemSettingManager.this.carPlayAppManager.getCarPlayDisplayStatus()) {
                    return;
                }
                RLog.i(SystemSettingManager.TAG, "carplayShowRunnerable : " + SystemSettingManager.this.carPlayAppManager.getCarPlayDisplayStatus());
                VoiceAssistantManager.get().sendCancelPTTEvent();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
    IAndroidAutoAppManager mAndroidAutoAppManager = null;
    int mVideoFocusStatus = 0;
    private final IServiceConnectionListener mServiceConnectionListener = new AnonymousClass8();
    private IAndroidAutoAppListener mAndroidAutoAppListener = new AnonymousClass9();
    ICallStateListener iCarPlayCallStateListener = new AnonymousClass10();
    IServiceConnectionListener serviceConnectionListener = new AnonymousClass11();
    boolean isCountDownTimerRunning = false;
    boolean isCountAADownTimerRunning = false;
    final int BODYWORK_POWER_LEVEL_OFF = 0;
    final int BODYWORK_POWER_LEVEL_ACC = 1;
    final int BODYWORK_POWER_LEVEL_ON = 2;
    private XCallToolReceiver8155 mXCallToolReceiver8155 = new XCallToolReceiver8155();

    public boolean getAudioScreenStatus() {
        return false;
    }

    public void sendCloseAudioScreenBroad() {
    }

    public void setNaviMuteState(boolean z) {
    }

    public void setTrackVolumeDuck(boolean z) {
    }

    public boolean isCarplayCallSending() {
        return this.carplayCallSending;
    }

    public boolean isCarplayCallOthers() {
        return this.carplayCallOthers;
    }

    public ICarPlayAppManager getCarPlayAppManager() {
        return this.carPlayAppManager;
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.manager.SystemSettingManager$1, reason: invalid class name */
    class AnonymousClass1 implements ICarPlayAppListener {
        AnonymousClass1() {
        }

        public void onCarPlayDisplayStatusChanged(final boolean z) {
            ExecutorUtils.execute(new Runnable() { // from class: com.byd.vrassistant.manager.SystemSettingManager$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m137xabe8655c(z);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onCarPlayDisplayStatusChanged$0$com-byd-vrassistant-manager-SystemSettingManager$1, reason: not valid java name */
        /* synthetic */ void m137xabe8655c(boolean z) {
            super.onCarPlayDisplayStatusChanged(z);
            RLog.i(SystemSettingManager.TAG, "onCarPlayDisplayStatusChanged isShow: " + z);
            RLog.i(SystemSettingManager.TAG, "lastCarplayShow : " + SystemSettingManager.this.lastCarplayShow);
            if (z) {
                if (!SystemSettingManager.this.lastCarplayShow) {
                    VoiceAssistantManager.get().sendCancelPTTEvent();
                } else {
                    SystemSettingManager.this.avmHandle.removeCallbacks(SystemSettingManager.this.carplayShowRunnerable);
                    SystemSettingManager.this.avmHandle.postDelayed(SystemSettingManager.this.carplayShowRunnerable, 200L);
                }
            }
            SystemSettingManager.this.lastCarplayShow = z;
        }
    }

    public static SystemSettingManager get() {
        if (mInstance == null) {
            synchronized (SystemSettingManager.class) {
                if (mInstance == null) {
                    mInstance = new SystemSettingManager();
                }
            }
        }
        return mInstance;
    }

    public boolean isCarplayCallRinging() {
        return this.carplayCallRinging;
    }

    private SystemSettingManager() {
        long j = 20000;
        long j2 = 2000;
        this.countDownTimer = new CountDownTimer(j, j2) { // from class: com.byd.vrassistant.manager.SystemSettingManager.12
            @Override // android.os.CountDownTimer
            public void onTick(long j3) {
                if (!SystemSettingManager.this.isCPConnect) {
                    SystemSettingManager.this.isCountDownTimerRunning = true;
                    RLog.e(SystemSettingManager.TAG, "linkCPServiceTimer : test linkCPService");
                    SystemSettingManager.this.linkCPService();
                } else {
                    SystemSettingManager.this.countDownTimer.cancel();
                    SystemSettingManager.this.isCountDownTimerRunning = false;
                    RLog.e(SystemSettingManager.TAG, "linkCPServiceTimer : CPService==link==ok");
                }
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                SystemSettingManager.this.isCountDownTimerRunning = false;
                RLog.e(SystemSettingManager.TAG, "linkCPServiceTimer : CPService==link==onFinish==noConnect");
            }
        };
        this.countDownAndroidAutoTimer = new CountDownTimer(j, j2) { // from class: com.byd.vrassistant.manager.SystemSettingManager.13
            @Override // android.os.CountDownTimer
            public void onTick(long j3) {
                if (!SystemSettingManager.this.isAAConnect) {
                    SystemSettingManager.this.isCountAADownTimerRunning = true;
                    RLog.e(SystemSettingManager.TAG, "linkAAServiceTimer : test linkAAService");
                    SystemSettingManager.this.androidAutoLink();
                } else {
                    SystemSettingManager.this.countDownAndroidAutoTimer.cancel();
                    SystemSettingManager.this.isCountAADownTimerRunning = false;
                    RLog.e(SystemSettingManager.TAG, "linkAAServiceTimer : AAService==link==ok");
                }
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                SystemSettingManager.this.isCountAADownTimerRunning = false;
                RLog.e(SystemSettingManager.TAG, "linkCPServiceTimer : CPService==link==onFinish==noConnect");
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_XCALLSHOW_ACTION_8155);
        intentFilter.addAction(Constant.ACTION_XCALLDISMISS_ACTION_8155);
        this.context.registerReceiver(this.mXCallToolReceiver8155, intentFilter);
        this.mCallingReceiver8155 = new CallingReceiver8155();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(Constant.ACTION_OUTGOING_ACTION_8155);
        this.context.registerReceiver(this.mCallingReceiver8155, intentFilter2);
        this.mContactReceiver8155 = new ContactReceiver8155();
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction(Constant.ACTION_CONTACT_ACTION_8155);
        this.context.registerReceiver(this.mContactReceiver8155, intentFilter3);
        this.mDevicePairingReceiver = new DevicePairingReceiver();
        IntentFilter intentFilter4 = new IntentFilter();
        intentFilter4.addAction(Constant.ACTION_DEVICE_PAIRING_8155);
        this.context.registerReceiver(this.mDevicePairingReceiver, intentFilter4);
        RLog.i(TAG, "mAutoYunDevice create & registerListener");
    }

    public boolean isRightDriver() {
        int i = SystemProperties.getInt("persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
        RLog.e(TAG, "isRightDriver:" + (i == 1));
        return i == 1;
    }

    public boolean isSpotifySupportRegion() {
        String countryCode = CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext());
        String str = TAG;
        RLog.e(str, "isSpotifySupportRegion : " + countryCode);
        try {
            if (!countryCode.isEmpty()) {
                RLog.e(str, "isSpotifySupportRegion : true");
                return this.spotifyRegionAry.contains(Integer.valueOf(Integer.parseInt(countryCode.trim())));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        RLog.e(TAG, "isSpotifySupportRegion : false");
        return false;
    }

    public boolean isDisGPSCountryCode() {
        return isCountryCodeIn(this.disGPSRegionAry);
    }

    public boolean isDisGPSICCID() {
        return isICCIDRegion(this.disGPSICCIDRegionAry);
    }

    public boolean isCountryCodeIn(List<Integer> list) {
        String countryCode = CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext());
        String str = TAG;
        RLog.e(str, "isCountryCodeIn : " + countryCode);
        boolean zContains = false;
        try {
            try {
                if (!TextUtils.isEmpty(countryCode) && TextUtils.isDigitsOnly(countryCode)) {
                    zContains = list.contains(Integer.valueOf(Integer.parseInt(countryCode.trim())));
                    RLog.e(str, "isCountryCodeIn : " + zContains);
                }
                RLog.e(str, "isCountryCodeIn : " + zContains);
                return zContains;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                RLog.e(TAG, "isCountryCodeIn : " + zContains);
                return zContains;
            }
        } catch (Throwable unused) {
            RLog.e(TAG, "isCountryCodeIn : " + zContains);
            return zContains;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isICCIDRegion(java.util.List<java.lang.Integer> r10) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.manager.SystemSettingManager.isICCIDRegion(java.util.List):boolean");
    }

    public void uninit() {
        try {
            BYDAutoGearboxDevice.getInstance(this.context).unregisterListener(this.bydAutoGearboxListener);
            this.context.unregisterReceiver(this.mCallingReceiver8155);
            this.context.unregisterReceiver(this.mContactReceiver8155);
            this.context.unregisterReceiver(this.mXCallToolReceiver8155);
            this.context.unregisterReceiver(this.mDevicePairingReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchAvmMode() {
        this.avmHandle.removeCallbacks(this.avmRunnerable);
        this.avmHandle.post(this.avmRunnerable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runOnUiThreadEx(Runnable runnable) {
        new Handler(this.context.getMainLooper()).post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void avmRegisterListener() {
        this.mAvmManager.registerAvmStatusListener(new AvmManager.AvmStatusListener() { // from class: com.byd.vrassistant.manager.SystemSettingManager.5
            @Override // com.ts.avm.sdk.AvmManager.AvmStatusListener
            public void onStatusChanged(int i, String str) {
                RLog.e(SystemSettingManager.TAG, "mAvmManager onStatusChanged: " + i + " " + str);
                if (i == 2) {
                    if (VoiceAssistantManager.get().isAvmForground()) {
                        return;
                    }
                    VoiceAssistantManager.get().setAvmForground(true);
                } else if (VoiceAssistantManager.get().isAvmForground()) {
                    VoiceAssistantManager.get().setAvmForground(false);
                }
            }
        });
    }

    private void avmManagerInit() {
        AvmManager avmManager = AvmManager.getInstance();
        this.mAvmManager = avmManager;
        if (avmManager != null) {
            avmManager.init(this.context, new IAvmServiceConnectionListener() { // from class: com.byd.vrassistant.manager.SystemSettingManager.6
                @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
                public void onServiceConnected() {
                    RLog.e(SystemSettingManager.TAG, "mAvmManager onServiceConnected");
                    SystemSettingManager.this.avmRegisterListener();
                }

                @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
                public void onServiceDisConnected() {
                    RLog.e(SystemSettingManager.TAG, "mAvmManager onServiceDisConnected");
                }

                @Override // com.ts.avm.sdk.IAvmServiceConnectionListener
                public void onError(int i, String str) {
                    RLog.e(SystemSettingManager.TAG, "mAvmManager onError " + str);
                }
            });
        }
    }

    public boolean isAvmForeground() {
        return this.mAvmManager.getAvmWorkState() == 2;
    }

    public void init() {
        AudioManager audioManager = (AudioManager) this.context.getSystemService("audio");
        this.audioManager = audioManager;
        this.volumeValue = audioManager.getStreamVolume(3);
        this.maxVolume = this.audioManager.getStreamMaxVolume(3);
        BYDAutoGearboxDevice.getInstance(this.context).registerListener(this.bydAutoGearboxListener);
        avmManagerInit();
        try {
            linkCPServiceTimer();
            linkAAServiceTimer();
            BYDAutoPowerDevice.getInstance(this.context).registerListener(new AbsBYDAutoPowerListener() { // from class: com.byd.vrassistant.manager.SystemSettingManager.7
                @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
                public void onPowerLevelChanged(int i) {
                    super.onPowerLevelChanged(i);
                    RLog.e(SystemSettingManager.TAG, "onPowerLevelChanged->level=" + i);
                    if (i == 0) {
                        SystemSettingManager.this.acc_on_switched = false;
                        RLog.e(SystemSettingManager.TAG, "POWER_CTL_STATE_OFF");
                        EventBus.getDefault().postSticky(new SystemStatusEvent("", 1));
                    } else if ((i == 2 || i == 3) && !SystemSettingManager.this.acc_on_switched) {
                        SystemSettingManager.this.acc_on_switched = true;
                        RLog.e(SystemSettingManager.TAG, "POWER_CTL_STATE_ON");
                        EventBus.getDefault().postSticky(new SystemStatusEvent("", 0));
                    }
                }
            });
            this.context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(ScreenObserver8155.NAVIBAR_MODE_ON_SCREENSAVER), false, new ScreenObserver8155(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getVideoFocusStatus() {
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            try {
                this.mVideoFocusStatus = iAndroidAutoAppManager.getVideoFocusStatus();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void androidAutoLink() {
        RLog.e(TAG, "linkAAServiceTimer====androidAutoLink");
        IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this.context);
        this.mAndroidAutoAppManager = iAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.unregisterConnectionListener(this.mServiceConnectionListener);
            this.mAndroidAutoAppManager.registerConnectionListener(this.mServiceConnectionListener);
        }
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.manager.SystemSettingManager$8, reason: invalid class name */
    class AnonymousClass8 implements IServiceConnectionListener {
        AnonymousClass8() {
        }

        public void onServiceConnected(IPlatformService iPlatformService) {
            SystemSettingManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.SystemSettingManager$8$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m141x5719a09f();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onServiceConnected$0$com-byd-vrassistant-manager-SystemSettingManager$8, reason: not valid java name */
        /* synthetic */ void m141x5719a09f() {
            RLog.e(SystemSettingManager.TAG, "linkAAServiceTimer====onServiceConnected");
            SystemSettingManager.this.isAAConnect = true;
            try {
                if (SystemSettingManager.this.mAndroidAutoAppManager.isServiceConnected()) {
                    SystemSettingManager.this.mAndroidAutoAppManager.unregisterAndroidAutoAppListener(SystemSettingManager.this.mAndroidAutoAppListener);
                    SystemSettingManager.this.mAndroidAutoAppManager.registerAndroidAutoAppListener(SystemSettingManager.this.mAndroidAutoAppListener);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                RLog.e(SystemSettingManager.TAG, "linkAAServiceTimer====onServiceConnected==registerAndroidAutoAppListener====RemoteException");
            }
        }

        public void onServiceDisconnected(IPlatformService iPlatformService) {
            SystemSettingManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.SystemSettingManager$8$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m142xc789d3a();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onServiceDisconnected$1$com-byd-vrassistant-manager-SystemSettingManager$8, reason: not valid java name */
        /* synthetic */ void m142xc789d3a() {
            RLog.e(SystemSettingManager.TAG, "linkAAServiceTimer====onServiceDisconnected");
            SystemSettingManager.this.isAAConnect = false;
            SystemSettingManager.this.linkAAServiceTimer();
        }
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.manager.SystemSettingManager$9, reason: invalid class name */
    class AnonymousClass9 implements IAndroidAutoAppListener {
        AnonymousClass9() {
        }

        public void onVideoFocusStatusChanged(final int i) {
            SystemSettingManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.SystemSettingManager$9$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m143xc2e55547(i);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onVideoFocusStatusChanged$0$com-byd-vrassistant-manager-SystemSettingManager$9, reason: not valid java name */
        /* synthetic */ void m143xc2e55547(int i) {
            RLog.e(SystemSettingManager.TAG, "linkAAServiceTimer====onVideoFocusStatusChanged====" + i);
            super.onVideoFocusStatusChanged(i);
            SystemSettingManager.this.isAndroidAutoForeground = 1 == i;
            if (SystemSettingManager.this.isAndroidAutoForeground) {
                VoiceAssistantManager.get().sendCancelPTTEvent();
                VoiceAssistantManager.get().clearAllView();
            }
        }

        public void onClusterVideoFocusStatusChanged(int i) {
            super.onClusterVideoFocusStatusChanged(i);
        }

        public void onProjectionBluetoothAddressChanged(String str) {
            super.onProjectionBluetoothAddressChanged(str);
        }
    }

    public boolean isAndroidAutoForeground() {
        return this.isAndroidAutoForeground;
    }

    public boolean isCPConnect() {
        return this.isCPConnect;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerCarPlayAppListener() {
        RLog.e(TAG, "registerCarPlayAppListener");
        try {
            this.carPlayAppManager.unregisterCarPlayAppListener(this.iCarPlayAppListener);
            this.carPlayAppManager.registerCarPlayAppListener(this.iCarPlayAppListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.manager.SystemSettingManager$10, reason: invalid class name */
    class AnonymousClass10 implements ICallStateListener {
        AnonymousClass10() {
        }

        public void onCallStateInfoChanged(final CallStateInfo callStateInfo) {
            SystemSettingManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.SystemSettingManager$10$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m138x9677ed7(callStateInfo);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onCallStateInfoChanged$0$com-byd-vrassistant-manager-SystemSettingManager$10, reason: not valid java name */
        /* synthetic */ void m138x9677ed7(CallStateInfo callStateInfo) {
            int status = callStateInfo.getStatus();
            SystemSettingManager.carplayCallSendingState = status;
            RLog.e(SystemSettingManager.TAG, "linkCPServiceTimer====cpcallstate==status:" + status);
            if (status != -1) {
                if (status == 0) {
                    SystemSettingManager.this.carplayCallRinging = false;
                    SystemSettingManager.this.carplayCallSending = false;
                    SystemSettingManager.this.carplayCallOthers = false;
                    VoiceAssistantManager.get().delShortcutCommand(VoiceAssistantManager.get().getShortCutListByLanguage(Constant.KEY_TEL_CONTROL));
                    return;
                }
                if (status == 1) {
                    if (VoiceAssistantManager.get().isInitialited()) {
                        VoiceAssistantManager.get().processTelShortCutCommand();
                        VoiceAssistantManager.get().sendCancelPTTEvent();
                        VoiceAssistantManager.get().clearAllView();
                    }
                    SystemSettingManager.this.carplayCallSending = true;
                    return;
                }
                if (status == 2) {
                    if (VoiceAssistantManager.get().isInitialited()) {
                        VoiceAssistantManager.get().processTelShortCutCommand();
                        VoiceAssistantManager.get().sendCancelPTTEvent();
                        VoiceAssistantManager.get().clearAllView();
                    }
                    SystemSettingManager.this.carplayCallRinging = true;
                    return;
                }
                if (status != 3 && status != 4 && status != 6) {
                    return;
                }
            }
            SystemSettingManager.this.carplayCallOthers = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerCPCallListener() {
        RLog.e(TAG, "registerCPCallListener");
        try {
            this.carPlayAppManager.unregisterCallStateListener(this.iCarPlayCallStateListener);
            this.carPlayAppManager.registerCallStateListener(this.iCarPlayCallStateListener);
        } catch (RemoteException e) {
            e.printStackTrace();
            RLog.e(TAG, "linkCPServiceTimer====cpcallstate==RemoteException====1");
        }
    }

    public boolean getCarplayConnectStatus() {
        String str = TAG;
        RLog.e(str, "getCarplayConnectStatus");
        try {
            try {
                ICarPlayAppManager iCarPlayAppManager = this.carPlayAppManager;
                if (iCarPlayAppManager == null || iCarPlayAppManager.getCarPlaySessionStatus() != 2) {
                    return false;
                }
                RLog.e(str, "getCarPlaySessionStatus==SESSION_STATUS_ACTIVATED");
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public boolean getAndroidAutoConnectStatus() {
        String str = TAG;
        RLog.e(str, "getAndroidAutoConnectStatus");
        try {
            try {
                IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
                if (iAndroidAutoAppManager == null || iAndroidAutoAppManager.getAndroidAutoSessionStatus() != 4) {
                    return false;
                }
                RLog.e(str, "getAndroidAutoSessionStatus==LINK_STATUS_ACTIVATED");
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.manager.SystemSettingManager$11, reason: invalid class name */
    class AnonymousClass11 implements IServiceConnectionListener {
        AnonymousClass11() {
        }

        public void onServiceConnected(IPlatformService iPlatformService) {
            SystemSettingManager.this.isCPConnect = true;
            RLog.e(SystemSettingManager.TAG, "carPlayAppManager onServiceConnected");
            try {
                if (SystemSettingManager.this.carPlayAppManager.getCarPlaySessionStatus() == 2) {
                    RLog.e(SystemSettingManager.TAG, "getCarPlaySessionStatus==SESSION_STATUS_ACTIVATED");
                    SystemSettingManager.this.registerCPCallListener();
                    SystemSettingManager.this.registerCarPlayAppListener();
                }
                SystemSettingManager.this.carPlayAppManager.registerCarPlayStateListener(new AnonymousClass1());
            } catch (RemoteException e) {
                e.printStackTrace();
                RLog.e(SystemSettingManager.TAG, "linkCPServiceTimer====cpcallstate==RemoteException====2");
            }
        }

        /* JADX INFO: renamed from: com.byd.vrassistant.manager.SystemSettingManager$11$1, reason: invalid class name */
        class AnonymousClass1 implements ICarPlayStateListener {
            AnonymousClass1() {
            }

            public void onSessionStateChanged(final int i) {
                SystemSettingManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.SystemSettingManager$11$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m140x158f33(i);
                    }
                });
            }

            /* JADX INFO: renamed from: lambda$onSessionStateChanged$0$com-byd-vrassistant-manager-SystemSettingManager$11$1, reason: not valid java name */
            /* synthetic */ void m140x158f33(int i) {
                RLog.e(SystemSettingManager.TAG, "linkCPServiceTimer==2ok==onSessionStateChanged==status" + i);
                super.onSessionStateChanged(i);
                if (i == 2) {
                    SystemSettingManager.this.isCPConnect = true;
                    SystemSettingManager.this.registerCPCallListener();
                    SystemSettingManager.this.registerCarPlayAppListener();
                } else if (i == 4) {
                    SystemSettingManager.this.isCPConnect = false;
                    RLog.e(SystemSettingManager.TAG, "carPlayAppManager SESSION_STATUS_DEACTIVATED isCPConnect:" + SystemSettingManager.this.isCPConnect);
                }
                RLog.e(SystemSettingManager.TAG, "carPlayAppManager onSessionStateChanged :" + i);
            }
        }

        public void onServiceDisconnected(IPlatformService iPlatformService) {
            SystemSettingManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.SystemSettingManager$11$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m139xf56057dd();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onServiceDisconnected$0$com-byd-vrassistant-manager-SystemSettingManager$11, reason: not valid java name */
        /* synthetic */ void m139xf56057dd() {
            SystemSettingManager.this.isCPConnect = false;
            RLog.e(SystemSettingManager.TAG, "carPlayAppManager onServiceDisconnected");
            SystemSettingManager.this.carplayCallRinging = false;
            SystemSettingManager.this.carplayCallSending = false;
            SystemSettingManager.this.carplayCallOthers = false;
            SystemSettingManager.this.linkCPServiceTimer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void linkCPService() {
        ICarPlayAppManager iCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, this.context);
        this.carPlayAppManager = iCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            iCarPlayAppManager.unregisterConnectionListener(this.serviceConnectionListener);
            this.carPlayAppManager.registerConnectionListener(this.serviceConnectionListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void linkCPServiceTimer() {
        if (this.isCountDownTimerRunning) {
            return;
        }
        RLog.e(TAG, "linkCPServiceTimer : start linkCPService");
        this.countDownTimer.start();
        this.isCountDownTimerRunning = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void linkAAServiceTimer() {
        if (this.isCountAADownTimerRunning) {
            return;
        }
        RLog.e(TAG, "linkAAServiceTimer : start linkCPService");
        this.countDownAndroidAutoTimer.start();
        this.isCountAADownTimerRunning = true;
    }

    public void volumeTo(int i) {
        this.audioManager.setStreamVolume(3, i, 0);
    }

    public void setMuteState(boolean z) {
        RLog.e(TAG, "setMuteState : " + z);
    }

    public int getPowerScreenStatus() {
        int i = Settings.Global.getInt(this.context.getContentResolver(), ScreenObserver8155.NAVIBAR_MODE_ON_SCREENSAVER, 0);
        RLog.e(TAG, "getPowerScreenStatus:" + i);
        return i;
    }

    public void turnBackLightOff() {
        String str = TAG;
        RLog.e(str, "turnBackLightOff:" + getPowerScreenStatus());
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.scheme));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = this.context.getPackageManager().queryIntentServices(intent, 0);
        RLog.i(str, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        this.context.startService(intent);
    }

    public void turnBackLightOn() {
        String str = TAG;
        RLog.e(str, "turnBackLightOn:" + getPowerScreenStatus());
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.scheme2));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = this.context.getPackageManager().queryIntentServices(intent, 0);
        RLog.i(str, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        this.context.startService(intent);
    }

    public int getTTsVolume() {
        return BYDAutoAudioDevice.getInstance(this.context).getVoiceAssistVolume();
    }

    public void TtsVolumeTo(int i) {
        BYDAutoAudioDevice.getInstance(this.context).setVoiceAssistVolume(i);
    }

    public void mute() {
        AudioHelper.getInstance(BydVoiceApplication.getContext()).setMuteAll(true);
    }

    public void unmute() {
        AudioHelper.getInstance(BydVoiceApplication.getContext()).setMuteAll(false);
    }

    public void openScreenByName(String str, String str2) {
        RLog.caller(TAG, "openScreenByName: ");
        openScreenByName(str, str2, null);
    }

    public void openScreenByName(String str, String str2, Map<String, String> map) {
        RLog.caller(TAG, "openScreenByNameEx component:" + str + ",activity:" + str2);
        try {
            final Intent intent = new Intent("android.intent.action.MAIN");
            ComponentName componentName = new ComponentName(str, str2);
            intent.setComponent(componentName);
            intent.setFlags(268435456);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setComponent(componentName);
            if (map != null && !map.isEmpty()) {
                map.forEach(new BiConsumer() { // from class: com.byd.vrassistant.manager.SystemSettingManager$$ExternalSyntheticLambda6
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        intent.putExtra((String) obj, (String) obj2);
                    }
                });
            }
            ForegroundAppUtil.startActivity(BydVoiceApplication.getContext(), intent);
        } catch (Exception e) {
            RLog.e(TAG, "openScreenByNameEx err:" + e.getMessage());
        }
    }

    public void startActivityByUrl(String str) {
        String str2 = TAG;
        RLog.i(str2, "----startActivityByUrl----scheme = " + str);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = this.context.getPackageManager().queryIntentServices(intent, 0);
        RLog.i(str2, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        this.context.startService(intent);
    }

    public void openAppByPackage(String str) {
        RLog.e(TAG, "openAppByPackage:" + str);
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            launchIntentForPackage.addFlags(268435456);
            ForegroundAppUtil.startActivity(BydVoiceApplication.getContext(), launchIntentForPackage);
        }
    }

    public boolean isSendingHomeKey() {
        return this.isSendingHomeKey;
    }

    public void setSendingHomeKey(boolean z) {
        this.isSendingHomeKey = z;
    }

    public void returnToHomePage() {
        RLog.e(TAG, "returnToHomePage");
        this.isSendingHomeKey = true;
        new Thread(new Runnable() { // from class: com.byd.vrassistant.manager.SystemSettingManager.14
            @Override // java.lang.Runnable
            public void run() {
                new Instrumentation().sendKeyDownUpSync(3);
            }
        }).start();
    }

    public String getVIN(Context context) {
        return BYDAutoBodyworkDevice.getInstance(context).getAutoVIN();
    }

    public int getCurrentGearBoxValue() {
        this.currentGearBoxValue = BYDAutoGearboxDevice.getInstance(this.context).getGear();
        RLog.e(TAG, "getCurrentGearBoxValue:" + this.currentGearBoxValue);
        return this.currentGearBoxValue;
    }

    public int getVehicleType(Context context) {
        String str = SystemProperties.get("persist.sys.AutoType");
        if (StringUtils.isEmpty(str)) {
            str = "0";
        } else {
            try {
                Integer.parseInt(str);
            } catch (Exception unused) {
                str = "0";
            }
        }
        int i = Integer.parseInt(str);
        String str2 = TAG;
        RLog.e(str2, "BuildConfig.VersionType:sop");
        RLog.e(str2, "getVehicleType==real==:" + i);
        if (i == 255 || i == 137 || i == 196 || i == 182 || i == 208 || i == 143 || i == 180) {
            return i;
        }
        return 137;
    }

    public boolean getPowerCtlStatus() {
        int powerLevel = BYDAutoBodyworkDevice.getInstance(this.context).getPowerLevel();
        RLog.i(TAG, "getPowerCtlStatus value=" + powerLevel);
        return powerLevel != 0;
    }

    public int getSmartVoiceLimit() {
        String str = TAG;
        RLog.i(str, "enter getSmartVoiceLimit>>>");
        int smartVoiceLimit = BYDAutoBodyworkDevice.getInstance(this.context).getSmartVoiceLimit();
        RLog.i(str, "getSmartVoiceLimit = " + smartVoiceLimit);
        return smartVoiceLimit;
    }

    public int getHUForbidStatus() {
        String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_PRIVACY_POLICY");
        if (string == null) {
            string = "false";
        }
        if (get().getCurrentGearBoxValue() == 2) {
            RLog.i(TAG, "getHUForbidStatus ret = 1");
            return 1;
        }
        String str = TAG;
        RLog.i(str, "getHUForbidStatus isAvmForground = " + VoiceAssistantManager.get().isAvmForground());
        RLog.i(str, "getHUForbidStatus getCurrentArea = " + SharedPreferencesUtil.getCurrentArea(this.context));
        if (VoiceAssistantManager.get().isAvmForground()) {
            if (!SharedPreferencesUtil.getCurrentArea(this.context).equalsIgnoreCase(get().isRightDriver() ? VehicleAreaEnum.SEAT_ROW_1_RIGHT : VehicleAreaEnum.SEAT_ROW_1_LEFT)) {
                RLog.i(str, "getHUForbidStatus ret = 8");
                return 8;
            }
        }
        RLog.i(str, "PhoneHelper.getInstance().getCallState():" + PhoneHelper.getInstance().getCallState() + ",isCPConnect:" + this.isCPConnect + ",carplayCallRinging:" + this.carplayCallRinging);
        if (PhoneHelper.getInstance().getCallState() == PhoneHelper.phoneState.CALL_STATE_OFFHOOK || PhoneHelper.getInstance().getCallState() == PhoneHelper.phoneState.CALL_STATE_RINGING || (this.isCPConnect && this.carplayCallRinging)) {
            RLog.i(str, "getHUForbidStatus ret = 2");
            return 2;
        }
        if (getSmartVoiceLimit() == 1) {
            RLog.i(str, "getHUForbidStatus ret = 6");
            return 6;
        }
        String str2 = SystemProperties.get("sys.byd.boot_business", "activated");
        if (str2.equals("activated")) {
            RLog.i(str, "sys.byd.boot_business:" + str2);
            if (string.equals("false")) {
                RLog.i(str, "getHUForbidStatus ret = 5");
                return 5;
            }
            ZoomCarHelper.getInstance().waitServiceRes();
            if (ZoomCarHelper.getInstance().isServiceConnect()) {
                if (ZoomCarHelper.getInstance().getMeetingStatus() != 1) {
                    return 0;
                }
                Log.i(str, "ZoomCarHelper.getInstance().getMeetingStaus() == 1");
                return 9;
            }
            Log.i(str, "ZoomCarHelper  Service is not connect");
            return 0;
        }
        RLog.i(str, "sys.byd.boot_business:" + str2);
        return 3;
    }

    public int getStreamType() {
        int volumeGroupIdForUsage = BYDAutoAudioDevice.getInstance(this.context).getVolumeGroupIdForUsage(18);
        RLog.i(TAG, "getCurrentAudioFocusStream =" + volumeGroupIdForUsage);
        return volumeGroupIdForUsage;
    }

    public int getCurrentAudioFocusStreamNoChange() {
        return 3;
    }

    public int getSkinType() {
        int i = this.context.getResources().getConfiguration().uiMode & 48;
        String str = TAG;
        RLog.i(str, "getSkinType nightMode= " + i);
        int i2 = i == 32 ? 2 : 1;
        RLog.i(str, "getSkinType skinType= " + i2);
        return i2;
    }

    public int getViewMode() {
        int nightMode = ((UiModeManager) BydVoiceApplication.getContext().getSystemService("uimode")).getNightMode();
        RLog.i(TAG, "getSkinType skinType= " + nightMode);
        return nightMode;
    }

    public int getAmplifierType(Context context) {
        return BYDAutoStudy.getInstance(context).getAmplifierConfiguration();
    }

    public String getDiLinkUi(Context context) {
        String systemPersist = BYDAutoSysPropertiesDevice.getInstance(context).getSystemPersist("ro.vehicle.type");
        RLog.i(TAG, "getDiLinkUi = " + systemPersist);
        return systemPersist;
    }

    public synchronized void checkVoiceAssistantAudioFocusChange(int i) {
        RLog.i(TAG, "Current VolumeGroupId =>" + getStreamType());
        VoiceAssistantManager.get().sendCancelPTTEvent();
        this.mPTTMode = true;
    }

    public int getMicType(Context context) {
        Boolean boolValueOf = Boolean.valueOf(SystemPropertiesUtils.getBoolean("vendor.audio.six.mic", true));
        RLog.i(TAG, "vendor.audio.six.mic ==========>" + boolValueOf);
        return boolValueOf.booleanValue() ? 1 : 0;
    }

    public void setAudioMicGainReference() throws Throwable {
        String file2String = DataUtils.readFile2String("/data/data/com.byd.vrassistant/files/config/cerence_mcu_gain_reference.json");
        if (TextUtils.isEmpty(file2String)) {
            file2String = DataUtils.readAssets2String(this.context, "cerence_mcu_gain_reference.json");
            RLog.i(TAG, "read cerence_mcu_gain_reference from assets");
        } else {
            RLog.i(TAG, "read cerence_mcu_gain_reference from /data/data/com.byd.vrassistant/files/config");
        }
        if (TextUtils.isEmpty(file2String)) {
            RLog.i(TAG, "cerence_mcu_gain_reference not exist or empty");
            return;
        }
        byte[] bArr = {0, 0, 0};
        try {
            for (JsonElement jsonElement : new JsonParser().parse(file2String).getAsJsonObject().get("8155_platform").getAsJsonArray()) {
                if (jsonElement.isJsonObject()) {
                    JsonObject asJsonObject = jsonElement.getAsJsonObject();
                    if (asJsonObject.has(Field_CarModelCode)) {
                        int asInt = asJsonObject.get(Field_CarModelCode).getAsInt();
                        int amplifierType = getAmplifierType(this.context);
                        int asInt2 = asJsonObject.get("paType").getAsInt();
                        int micType = getMicType(this.context);
                        int asInt3 = asJsonObject.get("micType").getAsInt();
                        int asInt4 = asJsonObject.get("dspType").getAsInt();
                        if (asInt == this.mVechicleType && amplifierType == asInt2 && micType == asInt3 && asInt4 == 0) {
                            if (asJsonObject.has(Field_MIC12) && asJsonObject.has(Field_MIC34) && asJsonObject.has(Field_MIC_Ref)) {
                                bArr[0] = asJsonObject.get(Field_MIC12).getAsByte();
                                bArr[1] = asJsonObject.get(Field_MIC34).getAsByte();
                                bArr[2] = asJsonObject.get(Field_MIC_Ref).getAsByte();
                                RLog.i(TAG, "read AudioMicGainRef value = " + ((int) bArr[0]) + "," + ((int) bArr[1]) + "," + ((int) bArr[2]));
                                return;
                            }
                            RLog.i(TAG, "not found match filed dspMIC12Gain|dspMIC34Gain|dspMicXFRefGain");
                        } else {
                            RLog.i(TAG, "cerence_mcu_gain_reference data no match  =>" + this.mVechicleType + "," + asInt2 + "," + asInt3);
                        }
                    } else {
                        RLog.i(TAG, "cerence_mcu_gain_reference format error =>" + file2String);
                        return;
                    }
                } else {
                    RLog.i(TAG, "cerence_mcu_gain_reference format error =>" + file2String);
                    return;
                }
            }
            RLog.i(TAG, " setAudioMicGainReference not found match vehicleType " + this.mVechicleType);
        } catch (Exception e) {
            RLog.e(TAG, "cerence_mcu_gain_reference parse error ,cannot find 8155_platform", e);
        }
    }

    public void setVrAssistantStatus(boolean z) {
        SystemPropertiesUtils.set(Constant.SYS_VRASSISTANT_STATUS, z ? "1" : "0");
    }

    private String getPrefixKey() {
        String language = VoiceAssistantManager.get().getLanguage();
        if (TextUtils.isEmpty(language)) {
            return "";
        }
        String[] strArrSplit = language.split("/");
        RLog.e(TAG, "temp:" + strArrSplit[strArrSplit.length - 1]);
        String[] strArrSplit2 = strArrSplit[strArrSplit.length - 1].replace("_", "-").split("-");
        if (strArrSplit2.length != 3) {
            return "";
        }
        if (strArrSplit2[1].equals("por") || strArrSplit2[1].equals("spa")) {
            return strArrSplit2[1] + "_" + strArrSplit2[2];
        }
        return strArrSplit2[1];
    }

    public boolean isSetSwitchControl(final String str) {
        Optional optionalFindAny;
        final String prefixKey = getPrefixKey();
        String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), com.byd.vrassistant.hint.Constant.FREE_WORDS_SWITCH_STATUS);
        String str2 = TAG;
        RLog.e(str2, "isSetSwitchControl key:" + str + ",language:" + prefixKey + ",getFreeWakeupWordsValue:" + string);
        List list = (List) new Gson().fromJson(string, new TypeToken<ArrayList<WakeUpFreeWordsSwitch>>() { // from class: com.byd.vrassistant.manager.SystemSettingManager.15
        }.getType());
        if (list != null && (optionalFindAny = list.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.SystemSettingManager$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((WakeUpFreeWordsSwitch) obj).getLanguage().equals(prefixKey);
            }
        }).findAny()) != null && optionalFindAny.isPresent()) {
            Optional<WakeUpFreeWordsSwitch.SwitchStatus> optionalFindAny2 = ((WakeUpFreeWordsSwitch) optionalFindAny.get()).getSwichList().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.SystemSettingManager$$ExternalSyntheticLambda5
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((WakeUpFreeWordsSwitch.SwitchStatus) obj).getKey().equals(str);
                }
            }).findAny();
            if (optionalFindAny2 != null && optionalFindAny2.isPresent()) {
                RLog.e(str2, "isSetSwitchControl:true");
                return true;
            }
            RLog.e(str2, "isSetSwitchControl:false");
            return false;
        }
        RLog.e(str2, "isSetTelControl:false");
        return false;
    }

    public boolean getFreeWakeupWordsValue(final String str) {
        Optional optionalFindAny;
        Optional<WakeUpFreeWordsSwitch.SwitchStatus> optionalFindAny2;
        final String prefixKey = getPrefixKey();
        String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), com.byd.vrassistant.hint.Constant.FREE_WORDS_SWITCH_STATUS);
        String str2 = TAG;
        RLog.e(str2, "language:" + prefixKey + ",key:" + str + ",getFreeWakeupWordsValue:" + string);
        List list = (List) new Gson().fromJson(string, new TypeToken<ArrayList<WakeUpFreeWordsSwitch>>() { // from class: com.byd.vrassistant.manager.SystemSettingManager.16
        }.getType());
        if (list == null || (optionalFindAny = list.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.SystemSettingManager$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((WakeUpFreeWordsSwitch) obj).getLanguage().equals(prefixKey);
            }
        }).findAny()) == null || !optionalFindAny.isPresent() || (optionalFindAny2 = ((WakeUpFreeWordsSwitch) optionalFindAny.get()).getSwichList().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.SystemSettingManager$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((WakeUpFreeWordsSwitch.SwitchStatus) obj).getKey().equals(str);
            }
        }).findAny()) == null || !optionalFindAny2.isPresent()) {
            return false;
        }
        RLog.e(str2, "getFreeWakeupWordsValue:" + string + ",language:" + prefixKey);
        return optionalFindAny2.get().getStatus();
    }

    public void setFreeWakeupWordsValue(String str) {
        String string = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), str);
        RLog.e(TAG, "setFreeWakeupWordsValue key:" + str + ",value:" + string);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        saveFreeSwitchStatus(str, Boolean.parseBoolean(string));
    }

    public void saveFreeSwitchStatus(final String str, boolean z) {
        final String prefixKey = getPrefixKey();
        List list = (List) new Gson().fromJson(Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), com.byd.vrassistant.hint.Constant.FREE_WORDS_SWITCH_STATUS), new TypeToken<ArrayList<WakeUpFreeWordsSwitch>>() { // from class: com.byd.vrassistant.manager.SystemSettingManager.17
        }.getType());
        if (list != null) {
            Optional optionalFindAny = list.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.SystemSettingManager$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((WakeUpFreeWordsSwitch) obj).getLanguage().equals(prefixKey);
                }
            }).findAny();
            if (optionalFindAny != null && optionalFindAny.isPresent()) {
                List<WakeUpFreeWordsSwitch.SwitchStatus> swichList = ((WakeUpFreeWordsSwitch) optionalFindAny.get()).getSwichList();
                Optional<WakeUpFreeWordsSwitch.SwitchStatus> optionalFindAny2 = swichList.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.SystemSettingManager$$ExternalSyntheticLambda1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ((WakeUpFreeWordsSwitch.SwitchStatus) obj).getKey().equals(str);
                    }
                }).findAny();
                if (optionalFindAny2 != null && optionalFindAny2.isPresent()) {
                    optionalFindAny2.get().setStatus(z);
                } else {
                    WakeUpFreeWordsSwitch.SwitchStatus switchStatus = new WakeUpFreeWordsSwitch.SwitchStatus();
                    switchStatus.setStatus(z);
                    switchStatus.setKey(str);
                    swichList.add(switchStatus);
                }
                String json = new Gson().toJson(list);
                Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), com.byd.vrassistant.hint.Constant.FREE_WORDS_SWITCH_STATUS, json);
                RLog.e(TAG, "save :" + json);
                return;
            }
            WakeUpFreeWordsSwitch wakeUpFreeWordsSwitch = new WakeUpFreeWordsSwitch();
            wakeUpFreeWordsSwitch.setLanguage(prefixKey);
            ArrayList arrayList = new ArrayList();
            WakeUpFreeWordsSwitch.SwitchStatus switchStatus2 = new WakeUpFreeWordsSwitch.SwitchStatus();
            switchStatus2.setStatus(z);
            switchStatus2.setKey(str);
            arrayList.add(switchStatus2);
            wakeUpFreeWordsSwitch.setSwichList(arrayList);
            list.add(wakeUpFreeWordsSwitch);
            String json2 = new Gson().toJson(list);
            Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), com.byd.vrassistant.hint.Constant.FREE_WORDS_SWITCH_STATUS, json2);
            RLog.e(TAG, "add :" + json2);
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        WakeUpFreeWordsSwitch wakeUpFreeWordsSwitch2 = new WakeUpFreeWordsSwitch();
        wakeUpFreeWordsSwitch2.setLanguage(prefixKey);
        ArrayList arrayList3 = new ArrayList();
        WakeUpFreeWordsSwitch.SwitchStatus switchStatus3 = new WakeUpFreeWordsSwitch.SwitchStatus();
        switchStatus3.setStatus(z);
        switchStatus3.setKey(str);
        arrayList3.add(switchStatus3);
        wakeUpFreeWordsSwitch2.setSwichList(arrayList3);
        arrayList2.add(wakeUpFreeWordsSwitch2);
        String json3 = new Gson().toJson(arrayList2);
        Settings.System.putString(BydVoiceApplication.getContext().getContentResolver(), com.byd.vrassistant.hint.Constant.FREE_WORDS_SWITCH_STATUS, json3);
        RLog.e(TAG, "new :" + json3);
    }

    public CheckCPAAInForegroundResult checkCPAAInForeground(String str) {
        CheckCPAAInForegroundResult checkCPAAInForegroundResult = new CheckCPAAInForegroundResult();
        try {
            String topActivityPackage = ForegroundAppUtil.getTopActivityPackage();
            boolean carplayConnectStatus = get().getCarplayConnectStatus();
            String str2 = TAG;
            RLog.i(str2, "checkCPAAInForeground: carplayConnected = " + carplayConnectStatus);
            boolean androidAutoConnectStatus = get().getAndroidAutoConnectStatus();
            RLog.i(str2, "checkCPAAInForeground: aaConnected = " + androidAutoConnectStatus);
            RLog.i(str2, "checkCPAAInForeground: topAppPkg = " + str);
            if ((VehicleSettingsHelper.PACKAGE_ANDROID_AUTO.equals(str) || VehicleSettingsHelper.PACKAGE_ANDROID_AUTO.equals(topActivityPackage)) && androidAutoConnectStatus) {
                checkCPAAInForegroundResult.setState(CPAAStateEnum.AA_FOREGROUND);
            } else if ((VehicleSettingsHelper.PACKAGE_CAR_PLAY.equals(str) || VehicleSettingsHelper.PACKAGE_CAR_PLAY.equals(topActivityPackage)) && carplayConnectStatus) {
                checkCPAAInForegroundResult.setState(CPAAStateEnum.CP_FOREGROUND);
            } else if (androidAutoConnectStatus) {
                checkCPAAInForegroundResult.setState(CPAAStateEnum.AA_BACKGROUND);
            } else if (carplayConnectStatus) {
                checkCPAAInForegroundResult.setState(CPAAStateEnum.CP_BACKGROUND);
            } else {
                checkCPAAInForegroundResult.setState("UNKNOWN");
            }
            RLog.i(str2, "checkCPAAInForeground: OK result = " + checkCPAAInForegroundResult.toJsonString());
        } catch (Exception unused) {
            RLog.i(TAG, "checkCPAAInForeground: ERROR");
            checkCPAAInForegroundResult.setState("UNKNOWN");
        }
        return checkCPAAInForegroundResult;
    }

    public void openWifiSetting() {
        String str = TAG;
        RLog.d(str, "openWifiSetting: ");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.schemeWifi));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = BydVoiceApplication.getContext().getPackageManager().queryIntentServices(intent, 0);
        RLog.i(str, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        BydVoiceApplication.getContext().startService(intent);
    }

    public void openBluetoothSetting() {
        String str = TAG;
        RLog.d(str, "openBluetoothSetting: ");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.schemeBluetooth));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = BydVoiceApplication.getContext().getPackageManager().queryIntentServices(intent, 0);
        RLog.i(str, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        BydVoiceApplication.getContext().startService(intent);
    }

    public void openHotSpotSetting() {
        String str = TAG;
        RLog.d(str, "openHotSpotSetting: ");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.schemeHotSpot));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = BydVoiceApplication.getContext().getPackageManager().queryIntentServices(intent, 0);
        RLog.i(str, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        BydVoiceApplication.getContext().startService(intent);
    }

    public void openVehicleSetting() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(schemeCarSetting));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = BydVoiceApplication.getContext().getPackageManager().queryIntentServices(intent, 0);
        RLog.i(TAG, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        BydVoiceApplication.getContext().startService(intent);
    }

    public void openCelluarSetting() {
        String str = TAG;
        RLog.d(str, "openCelluarSetting: ");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.schema_celluardata));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = BydVoiceApplication.getContext().getPackageManager().queryIntentServices(intent, 0);
        RLog.i(str, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        BydVoiceApplication.getContext().startService(intent);
    }
}
