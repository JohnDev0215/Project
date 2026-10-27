package com.byd.vrassistant.conversation;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.CheckCPAAconnectionParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.CloseFSDeviceParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlBluetoothParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlCameraRecordParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlCameraSwitchParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlCameraTakePhotoParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlHotspotParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.ControlWifiParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetFSDeviceStateParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.OpenFSDeviceParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckCPAAconnectionResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetFSDeviceStateResult;
import cerence.ark.assistant.controller.enumeration.CameraAreaEnum;
import cerence.ark.assistant.controller.enumeration.FSDeviceEnum;
import cerence.ark.assistant.controller.enumeration.MediaStateEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.enumeration.VCDeviceStatusEnum;
import cerence.ark.assistant.library.Utils;
import com.android.dx.stock.ProxyBuilder;
import com.byd.voicelibrary.VoiceControllerForCamera;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.manager.MediaSessionManager;
import com.byd.vrassistant.manager.SpotifyManager;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.CameraUtils;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SystemPropertiesUtils;
import com.byd.vrassistant.utils.TagUtil;
import com.ts.car.core.ServiceProvider;
import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoDeviceListManager;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.ICarPlayDeviceListManager;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: loaded from: classes.dex */
public class VehicleSettingsHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String PACKAGE_ANDROID_AUTO = "com.ts.androidauto.app";
    public static final String PACKAGE_CAR_PLAY = "com.ts.carplay.app";
    private static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    private static final String SERVICE_PKG = "com.byd.carsettings";
    private static VoiceControllerForCamera cameraDevice = null;
    private static final String schemeBluetooth = "byd_pad://byd_car_settings:2933/launcher?activity=Bluetooth";
    private static final String schemeHotSpot = "byd_pad://byd_car_settings:2933/launcher?activity=Hotspot";
    private static final String schemeWifi = "byd_pad://byd_car_settings:2933/launcher?activity=WiFi";
    private PackageManager packageManager;
    private static final String TAG = TagUtil.getTagName(VehicleSettingsHelper.class);
    private static final String[] MUSICS_PACKAGE = {LocalMediaManager.SPOTIFY_PACKAGE_NAME, SpotifyManager.SPOTIFY_APK_PACKAGE_NAME, LocalMediaManager.LOCALMEDIA_PACKAGE_NAME, LocalMediaManager.AMAZON_PACKAGE_NAME, "com.android.car.media", LocalMediaManager.KARAOKE_PACKAGE_NAME};
    private static final String[] MUSICS_PACKAGE_TWO = {OnlineMediaHelper.DEEZER_PACKAGE_NAME, OnlineMediaHelper.TIDAL_PACKAGE_NAME, OnlineMediaHelper.MELON_PACKAGE_NAME, OnlineMediaHelper.MUSICMATE_PACKAGE_NAME, OnlineMediaHelper.TUNEIN_PACKAGE_NAME, OnlineMediaHelper.POCKETCASTS_PACKAGE_NAME, OnlineMediaHelper.AUDIALS_PACKAGE_NAME, OnlineMediaHelper.IHEARTRADIO_PACKAGE_NAME, OnlineMediaHelper.RADIOLINE_PACKAGE_NAME, OnlineMediaHelper.AUDIBLE_PACKAGE_NAME, OnlineMediaHelper.LIBBY_PACKAGE_NAME, OnlineMediaHelper.BLOOMBERG_PACKAGE_NAME};

    static /* synthetic */ Object lambda$startTethering$0(Object obj, Method method, Object[] objArr) throws Throwable {
        return null;
    }

    private static final class SingletonHolder {
        private static final VehicleSettingsHelper INSTANCE = new VehicleSettingsHelper();

        private SingletonHolder() {
        }
    }

    public static VehicleSettingsHelper getInstance() {
        RLog.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private VehicleSettingsHelper() {
        try {
            RLog.i(TAG, "VehicleSettingsHelper()");
        } catch (Exception e) {
            RLog.i(TAG, "VehicleSettingsHelper() Exception ", e);
        }
        this.packageManager = BydVoiceApplication.getContext().getPackageManager();
        cameraDevice = VoiceControllerForCamera.getInstance(BydVoiceApplication.getContext());
    }

    public void controlWifi(ControlWifiParam controlWifiParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            RLog.i(TAG, "controlWifi: json =" + controlWifiParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlWifi】");
            onOffWifi("ON".equals(controlWifiParam.getAction()), iResponseSender);
        } catch (Exception e) {
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlWifi】,result【ERROR】");
            Log.i(TAG, "controlWifi: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    private void onOffWifi(boolean z, IResponseSender<BaseResult> iResponseSender) throws InterruptedException {
        String str = TAG;
        RLog.i(str, "onOffWifi: isOn = " + z);
        WifiManager wifiManager = (WifiManager) BydVoiceApplication.getContext().getSystemService("wifi");
        int wifiState = wifiManager.getWifiState();
        RLog.i(str, "onOffWifi: status = " + wifiState);
        ConnectivityManager connectivityManager = (ConnectivityManager) BydVoiceApplication.getContext().getSystemService("connectivity");
        if (!z) {
            if (wifiState == 1) {
                RLog.i(str, "onOffWifi: ALREADY_EXPECTED");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlWifi】,result【ALREADY_EXPECTED】");
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                return;
            } else {
                wifiManager.setWifiEnabled(false);
                RLog.i(str, "onOffWifi: OK");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlWifi】,result【OK】");
                iResponseSender.send(new Response("OK"));
                return;
            }
        }
        stopAppByForce("com.byd.avm");
        if (wifiState == 3) {
            RLog.i(str, "onOffWifi: ALREADY_EXPECTED");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || (activeNetworkInfo.isConnected() && activeNetworkInfo.getType() != 1)) {
                RLog.i(str, "wifi disconnect ,open systemsettings");
                openWifiPage();
            }
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlWifi】,result【ALREADY_EXPECTED】");
            iResponseSender.send(new Response("ALREADY_EXPECTED"));
            return;
        }
        disconnectCPAA();
        wifiManager.setWifiEnabled(true);
        openWifiPage();
        RLog.i(str, "onOffWifi: OK");
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlWifi】,result【OK】");
        iResponseSender.send(new Response("OK"));
    }

    private static void disconnectCPAA() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        disconnectCarPlayOrAndroidAutoAndOpenWifi(countDownLatch);
        countDownLatch.await(4L, TimeUnit.SECONDS);
    }

    private static void disconnectCarPlayOrAndroidAutoAndOpenWifi(final CountDownLatch countDownLatch) {
        try {
            boolean carplayConnectStatus = SystemSettingManager.get().getCarplayConnectStatus();
            String str = TAG;
            RLog.i(str, "disconnectCarPlayOrAndroidAuto: carplayConnected = " + carplayConnectStatus);
            if (carplayConnectStatus) {
                final ICarPlayDeviceListManager iCarPlayDeviceListManager = (ICarPlayDeviceListManager) ServiceProvider.get(ICarPlayDeviceListManager.class, BydVoiceApplication.getContext());
                RLog.i(str, "disconnectCarPlayOrAndroidAuto: carPlayDeviceListManager = " + iCarPlayDeviceListManager);
                iCarPlayDeviceListManager.registerConnectionListener(new IServiceConnectionListener() { // from class: com.byd.vrassistant.conversation.VehicleSettingsHelper.1
                    public void onServiceConnected(IPlatformService iPlatformService) {
                        RLog.i(VehicleSettingsHelper.TAG, "onServiceConnected");
                        ICarPlayDeviceListManager iCarPlayDeviceListManager2 = iCarPlayDeviceListManager;
                        if (iCarPlayDeviceListManager2 != null) {
                            try {
                                List<CarPlayDevice> carPlayDeviceList = iCarPlayDeviceListManager2.getCarPlayDeviceList();
                                RLog.i(VehicleSettingsHelper.TAG, "disconnectCarPlayOrAndroidAuto: carPlayDeviceList = " + carPlayDeviceList);
                                for (CarPlayDevice carPlayDevice : carPlayDeviceList) {
                                    if (carPlayDevice.getDeviceStatus() == 4) {
                                        String btAddr = carPlayDevice.getBtAddr();
                                        RLog.i(VehicleSettingsHelper.TAG, "disconnectCarPlayOrAndroidAuto: btAddr = " + btAddr);
                                        iCarPlayDeviceListManager.disconnectCarPlay(btAddr);
                                    }
                                }
                            } catch (Exception unused) {
                                RLog.i(VehicleSettingsHelper.TAG, "disconnectCarPlayOrAndroidAuto: Exception");
                            }
                        }
                        countDownLatch.countDown();
                    }

                    public void onServiceDisconnected(IPlatformService iPlatformService) {
                        RLog.i(VehicleSettingsHelper.TAG, "onServiceDisconnected");
                        countDownLatch.countDown();
                    }
                });
            } else {
                boolean androidAutoConnectStatus = SystemSettingManager.get().getAndroidAutoConnectStatus();
                RLog.i(str, "disconnectCarPlayOrAndroidAuto: aaConnected = " + androidAutoConnectStatus);
                if (androidAutoConnectStatus) {
                    final IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager = (IAndroidAutoDeviceListManager) ServiceProvider.get(IAndroidAutoDeviceListManager.class, BydVoiceApplication.getContext());
                    RLog.i(str, "disconnectCarPlayOrAndroidAuto: androidAutoDeviceListManager = " + iAndroidAutoDeviceListManager);
                    iAndroidAutoDeviceListManager.registerConnectionListener(new IServiceConnectionListener() { // from class: com.byd.vrassistant.conversation.VehicleSettingsHelper.2
                        public void onServiceConnected(IPlatformService iPlatformService) {
                            try {
                                IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager2 = iAndroidAutoDeviceListManager;
                                if (iAndroidAutoDeviceListManager2 != null) {
                                    List<AndroidAutoDevice> androidAutoDeviceList = iAndroidAutoDeviceListManager2.getAndroidAutoDeviceList();
                                    RLog.i(VehicleSettingsHelper.TAG, "disconnectCarPlayOrAndroidAuto: androidAutoDeviceList = " + androidAutoDeviceList.size());
                                    for (AndroidAutoDevice androidAutoDevice : androidAutoDeviceList) {
                                        if (androidAutoDevice.getDeviceStatus() == 4) {
                                            String btAddr = androidAutoDevice.getBtAddr();
                                            RLog.i(VehicleSettingsHelper.TAG, "disconnectCarPlayOrAndroidAuto: btAddr = " + btAddr);
                                            iAndroidAutoDeviceListManager.disconnectAndroidAuto(btAddr);
                                        }
                                    }
                                }
                            } catch (Exception unused) {
                                RLog.i(VehicleSettingsHelper.TAG, "disconnectCarPlayOrAndroidAuto Exception");
                            }
                            countDownLatch.countDown();
                        }

                        public void onServiceDisconnected(IPlatformService iPlatformService) {
                            RLog.i(VehicleSettingsHelper.TAG, "onServiceDisconnected");
                            countDownLatch.countDown();
                        }
                    });
                } else {
                    countDownLatch.countDown();
                }
            }
        } catch (Exception unused) {
            RLog.i(TAG, "disconnectCarPlayOrAndroidAuto:  Exception");
        }
    }

    private void openWifiPage() {
        RLog.i(TAG, "openWifiPage() called");
        boolean carplayConnectStatus = SystemSettingManager.get().getCarplayConnectStatus();
        boolean androidAutoConnectStatus = SystemSettingManager.get().getAndroidAutoConnectStatus();
        if (carplayConnectStatus || androidAutoConnectStatus) {
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(schemeWifi));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        if (this.packageManager.queryIntentServices(intent, 0).isEmpty()) {
            return;
        }
        BydVoiceApplication.getContext().startService(intent);
    }

    public void controlBluetooth(ControlBluetoothParam controlBluetoothParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "controlBluetooth: json = " + controlBluetoothParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlBluetooth】");
            String action = controlBluetoothParam.getAction();
            RLog.i(str, "controlBluetooth: action" + action);
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            boolean zIsEnabled = defaultAdapter.isEnabled();
            RLog.i(str, "controlBluetooth: enabled = " + zIsEnabled);
            if (!"ON".equals(action)) {
                if (zIsEnabled) {
                    defaultAdapter.disable();
                    RLog.i(str, "controlBluetooth: OK");
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlBluetooth】,result【OK】");
                    iResponseSender.send(new Response("OK"));
                    return;
                }
                RLog.i(str, "controlBluetooth: ALREADY_EXPECTED");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlBluetooth】,result【ALREADY_EXPECTED】");
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                return;
            }
            stopAppByForce("com.byd.avm");
            if (zIsEnabled) {
                RLog.i(str, "controlBluetooth: ALREADY_EXPECTED");
                if (!PhoneHelper.getInstance().getConnectionState()) {
                    RLog.i(str, " Bluetooth disconnection,open btsetting.");
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(schemeBluetooth));
                    intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
                    List<ResolveInfo> listQueryIntentServices = this.packageManager.queryIntentServices(intent, 0);
                    RLog.i(str, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
                    if (!listQueryIntentServices.isEmpty()) {
                        BydVoiceApplication.getContext().startService(intent);
                    }
                }
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlBluetooth】,result【ALREADY_EXPECTED】");
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                return;
            }
            defaultAdapter.enable();
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(schemeBluetooth));
            intent2.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
            List<ResolveInfo> listQueryIntentServices2 = this.packageManager.queryIntentServices(intent2, 0);
            RLog.i(str, "----startIntentByPath----resolveInfos = " + listQueryIntentServices2);
            if (!listQueryIntentServices2.isEmpty()) {
                BydVoiceApplication.getContext().startService(intent2);
            }
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlBluetooth】,result【OK】");
            RLog.i(str, "controlBluetooth: OK");
            iResponseSender.send(new Response("OK"));
        } catch (Exception unused) {
            RLog.i(TAG, "controlBluetooth: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlBluetooth】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void controlHotspot(ControlHotspotParam controlHotspotParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "controlHotspot: json = " + controlHotspotParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlHotspot】");
            String action = controlHotspotParam.getAction();
            RLog.i(str, "controlHotspot: action" + action);
            boolean zIsWifiApEnabled = isWifiApEnabled();
            if ("ON".equals(action)) {
                stopAppByForce("com.byd.avm");
                if (zIsWifiApEnabled) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(schemeHotSpot));
                    intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
                    List<ResolveInfo> listQueryIntentServices = this.packageManager.queryIntentServices(intent, 0);
                    RLog.i(str, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
                    if (!listQueryIntentServices.isEmpty()) {
                        BydVoiceApplication.getContext().startService(intent);
                    }
                    RLog.i(str, "controlHotspot: ALREADY_EXPECTED");
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlHotspot】,result【ALREADY_EXPECTED】");
                    iResponseSender.send(new Response("ALREADY_EXPECTED"));
                    return;
                }
                startTethering();
                try {
                    RLog.i(str, "sleep 300ms");
                    Thread.sleep(500L);
                } catch (Exception e) {
                    RLog.i(TAG, "sleep Exception ", e);
                }
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(schemeHotSpot));
                intent2.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
                List<ResolveInfo> listQueryIntentServices2 = this.packageManager.queryIntentServices(intent2, 0);
                RLog.i(TAG, "----startIntentByPath----resolveInfos = " + listQueryIntentServices2);
                if (!listQueryIntentServices2.isEmpty()) {
                    BydVoiceApplication.getContext().startService(intent2);
                }
            } else if (zIsWifiApEnabled) {
                disconnectCPAA();
                stopTethering();
            } else {
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlHotspot】,result【ALREADY_EXPECTED】");
                RLog.i(str, "controlBluetooth: ALREADY_EXPECTED");
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                return;
            }
            RLog.i(TAG, "controlHotspot: OK");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlHotspot】,result【OK】");
            iResponseSender.send(new Response("OK"));
        } catch (Exception unused) {
            RLog.i(TAG, "controlHotspot: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlHotspot】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    private static boolean isWifiApEnabled() {
        try {
            WifiManager wifiManager = (WifiManager) Utils.getContext().getApplicationContext().getSystemService("wifi");
            if (((Integer) wifiManager.getClass().getDeclaredMethod("getWifiApState", new Class[0]).invoke(wifiManager, new Object[0])).intValue() == ((Integer) wifiManager.getClass().getDeclaredField("WIFI_AP_STATE_ENABLED").get(wifiManager)).intValue()) {
                RLog.i(TAG, "isWifiApEnabled: hot当前开启");
                return true;
            }
            RLog.i(TAG, "isWifiApEnabled: hot当前关闭");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void startTethering() {
        RLog.i(TAG, "startTethering: ");
        ConnectivityManager connectivityManager = (ConnectivityManager) BydVoiceApplication.getContext().getSystemService("connectivity");
        try {
            File codeCacheDir = BydVoiceApplication.getContext().getCodeCacheDir();
            Class<?> cls = Class.forName("android.net.ConnectivityManager$OnStartTetheringCallback");
            connectivityManager.getClass().getDeclaredMethod("startTethering", Integer.TYPE, Boolean.TYPE, cls).invoke(connectivityManager, 0, false, ProxyBuilder.forClass(cls).dexCache(codeCacheDir).handler(new InvocationHandler() { // from class: com.byd.vrassistant.conversation.VehicleSettingsHelper$$ExternalSyntheticLambda1
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) {
                    return VehicleSettingsHelper.lambda$startTethering$0(obj, method, objArr);
                }
            }).build());
        } catch (Exception e) {
            RLog.i(TAG, "startTethering: 打开热点失败");
            e.printStackTrace();
        }
    }

    private static void stopTethering() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getContext().getSystemService("connectivity");
        try {
            connectivityManager.getClass().getDeclaredMethod("stopTethering", Integer.TYPE).invoke(connectivityManager, 0);
        } catch (Exception e) {
            RLog.e(TAG, "stopTethering: 关闭热点失败");
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:141:0x04e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void openApp(cerence.ark.assistant.controller.domain.vehiclecontrol.param.OpenAppParam r40, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r41) {
        /*
            Method dump skipped, instruction units count: 2258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleSettingsHelper.openApp(cerence.ark.assistant.controller.domain.vehiclecontrol.param.OpenAppParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    private boolean get12MusicApps(final String str) {
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.e(TAG, "OnlineGetPlayState 1");
            try {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.byd.vrassistant.conversation.VehicleSettingsHelper$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaSessionManager.get().connectToMediaService(str, countDownLatch);
                    }
                });
                countDownLatch.await(6L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, "openApp: serviceConnected = " + MediaSessionManager.get().isServiceConnected(str));
        return MediaStateEnum.PLAYING.equals(MediaSessionManager.get().getPlayStateInfo(str));
    }

    public static void openScheme(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("bydzoom://zoom.us?voiceAction=openZoom"));
            intent.setFlags(272629760);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
                Log.i(TAG, "Scheme opened successfully: ");
            } else {
                Log.e(TAG, "No activity found to handle the scheme: ");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error opening scheme: ", e);
        }
    }

    private void startCarPlay(Context context) {
        stopAppByForce("com.byd.avm");
        Intent intent = new Intent("com.byd.card.open.carplay");
        intent.setPackage("com.byd.uereport");
        context.sendBroadcast(intent);
    }

    private void startAndroidAuto(Context context) {
        stopAppByForce("com.byd.avm");
        Intent intent = new Intent("com.byd.card.open.auto");
        intent.setPackage("com.byd.uereport");
        context.sendBroadcast(intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x0463 A[Catch: Exception -> 0x0738, TRY_LEAVE, TryCatch #1 {Exception -> 0x0738, blocks: (B:3:0x004a, B:6:0x0097, B:8:0x00a6, B:10:0x00c6, B:12:0x00d5, B:14:0x00ef, B:16:0x0100, B:18:0x010a, B:20:0x010e, B:22:0x0141, B:24:0x0147, B:26:0x015d, B:29:0x018f, B:31:0x01a6, B:33:0x01bb, B:36:0x01c8, B:38:0x01d0, B:41:0x01da, B:43:0x01e4, B:45:0x01f3, B:49:0x01fe, B:52:0x0221, B:55:0x02d1, B:57:0x02db, B:59:0x02e5, B:62:0x02f0, B:64:0x02fb, B:66:0x030a, B:122:0x0463, B:125:0x046b, B:127:0x0471, B:130:0x0481, B:133:0x0488, B:135:0x0496, B:141:0x04dd, B:143:0x04e3, B:152:0x0504, B:154:0x0510, B:156:0x0535, B:158:0x0539, B:160:0x0542, B:162:0x0546, B:163:0x054e, B:165:0x0565, B:170:0x0591, B:171:0x05a5, B:173:0x05bf, B:174:0x05d0, B:178:0x05de, B:181:0x05f1, B:183:0x05f7, B:185:0x05fd, B:187:0x060c, B:189:0x0617, B:191:0x061d, B:195:0x0631, B:199:0x0681, B:201:0x0698, B:203:0x069e, B:205:0x06ad, B:207:0x06c7, B:209:0x06e0, B:212:0x06f7, B:214:0x0716, B:213:0x0706, B:188:0x0612, B:147:0x04ed, B:150:0x04f4, B:136:0x04a5, B:138:0x04bc, B:140:0x04d0, B:67:0x0319, B:69:0x0323, B:70:0x0337, B:72:0x033d, B:74:0x0347, B:88:0x0391, B:90:0x03a1, B:76:0x0351, B:78:0x035c, B:80:0x0366, B:82:0x0371, B:84:0x037b, B:86:0x0387, B:92:0x03b1, B:94:0x03c8, B:96:0x03d2, B:110:0x041c, B:112:0x0426, B:114:0x0436, B:116:0x0446, B:98:0x03dc, B:100:0x03e7, B:102:0x03f1, B:104:0x03fc, B:106:0x0406, B:108:0x0412, B:215:0x071f), top: B:222:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x04ff  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0510 A[Catch: Exception -> 0x0738, TryCatch #1 {Exception -> 0x0738, blocks: (B:3:0x004a, B:6:0x0097, B:8:0x00a6, B:10:0x00c6, B:12:0x00d5, B:14:0x00ef, B:16:0x0100, B:18:0x010a, B:20:0x010e, B:22:0x0141, B:24:0x0147, B:26:0x015d, B:29:0x018f, B:31:0x01a6, B:33:0x01bb, B:36:0x01c8, B:38:0x01d0, B:41:0x01da, B:43:0x01e4, B:45:0x01f3, B:49:0x01fe, B:52:0x0221, B:55:0x02d1, B:57:0x02db, B:59:0x02e5, B:62:0x02f0, B:64:0x02fb, B:66:0x030a, B:122:0x0463, B:125:0x046b, B:127:0x0471, B:130:0x0481, B:133:0x0488, B:135:0x0496, B:141:0x04dd, B:143:0x04e3, B:152:0x0504, B:154:0x0510, B:156:0x0535, B:158:0x0539, B:160:0x0542, B:162:0x0546, B:163:0x054e, B:165:0x0565, B:170:0x0591, B:171:0x05a5, B:173:0x05bf, B:174:0x05d0, B:178:0x05de, B:181:0x05f1, B:183:0x05f7, B:185:0x05fd, B:187:0x060c, B:189:0x0617, B:191:0x061d, B:195:0x0631, B:199:0x0681, B:201:0x0698, B:203:0x069e, B:205:0x06ad, B:207:0x06c7, B:209:0x06e0, B:212:0x06f7, B:214:0x0716, B:213:0x0706, B:188:0x0612, B:147:0x04ed, B:150:0x04f4, B:136:0x04a5, B:138:0x04bc, B:140:0x04d0, B:67:0x0319, B:69:0x0323, B:70:0x0337, B:72:0x033d, B:74:0x0347, B:88:0x0391, B:90:0x03a1, B:76:0x0351, B:78:0x035c, B:80:0x0366, B:82:0x0371, B:84:0x037b, B:86:0x0387, B:92:0x03b1, B:94:0x03c8, B:96:0x03d2, B:110:0x041c, B:112:0x0426, B:114:0x0436, B:116:0x0446, B:98:0x03dc, B:100:0x03e7, B:102:0x03f1, B:104:0x03fc, B:106:0x0406, B:108:0x0412, B:215:0x071f), top: B:222:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x05de A[Catch: Exception -> 0x0738, TRY_LEAVE, TryCatch #1 {Exception -> 0x0738, blocks: (B:3:0x004a, B:6:0x0097, B:8:0x00a6, B:10:0x00c6, B:12:0x00d5, B:14:0x00ef, B:16:0x0100, B:18:0x010a, B:20:0x010e, B:22:0x0141, B:24:0x0147, B:26:0x015d, B:29:0x018f, B:31:0x01a6, B:33:0x01bb, B:36:0x01c8, B:38:0x01d0, B:41:0x01da, B:43:0x01e4, B:45:0x01f3, B:49:0x01fe, B:52:0x0221, B:55:0x02d1, B:57:0x02db, B:59:0x02e5, B:62:0x02f0, B:64:0x02fb, B:66:0x030a, B:122:0x0463, B:125:0x046b, B:127:0x0471, B:130:0x0481, B:133:0x0488, B:135:0x0496, B:141:0x04dd, B:143:0x04e3, B:152:0x0504, B:154:0x0510, B:156:0x0535, B:158:0x0539, B:160:0x0542, B:162:0x0546, B:163:0x054e, B:165:0x0565, B:170:0x0591, B:171:0x05a5, B:173:0x05bf, B:174:0x05d0, B:178:0x05de, B:181:0x05f1, B:183:0x05f7, B:185:0x05fd, B:187:0x060c, B:189:0x0617, B:191:0x061d, B:195:0x0631, B:199:0x0681, B:201:0x0698, B:203:0x069e, B:205:0x06ad, B:207:0x06c7, B:209:0x06e0, B:212:0x06f7, B:214:0x0716, B:213:0x0706, B:188:0x0612, B:147:0x04ed, B:150:0x04f4, B:136:0x04a5, B:138:0x04bc, B:140:0x04d0, B:67:0x0319, B:69:0x0323, B:70:0x0337, B:72:0x033d, B:74:0x0347, B:88:0x0391, B:90:0x03a1, B:76:0x0351, B:78:0x035c, B:80:0x0366, B:82:0x0371, B:84:0x037b, B:86:0x0387, B:92:0x03b1, B:94:0x03c8, B:96:0x03d2, B:110:0x041c, B:112:0x0426, B:114:0x0436, B:116:0x0446, B:98:0x03dc, B:100:0x03e7, B:102:0x03f1, B:104:0x03fc, B:106:0x0406, B:108:0x0412, B:215:0x071f), top: B:222:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0573 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void closeApp(cerence.ark.assistant.controller.domain.vehiclecontrol.param.CloseAppParam r34, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.vehiclecontrol.result.CloseAppResult> r35) {
        /*
            Method dump skipped, instruction units count: 1873
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleSettingsHelper.closeApp(cerence.ark.assistant.controller.domain.vehiclecontrol.param.CloseAppParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x013f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void closeMusic(cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.vehiclecontrol.result.CloseAppResult> r9) {
        /*
            Method dump skipped, instruction units count: 341
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleSettingsHelper.closeMusic(cerence.ark.assistant.api.IResponseSender):void");
    }

    public static boolean hasIntersection(List<?> list, Set<?> set) {
        new ArrayList(list).retainAll(set);
        return !r0.isEmpty();
    }

    private static boolean stopAppByForce(String str, boolean z) {
        if (z) {
            return true;
        }
        stopAppByForce(str);
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean stopAppByForce(java.lang.String r7) {
        /*
            Method dump skipped, instruction units count: 506
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleSettingsHelper.stopAppByForce(java.lang.String):boolean");
    }

    private static void toHome() {
        if (getInstance().isDockState()) {
            VoiceAssistantManager.get().sendExitSplitScreen();
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        SystemSettingManager.get().returnToHomePage();
    }

    public boolean isDockState() {
        try {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) ActivityTaskManager.getService().getFilteredTasks(1, false).get(0);
            if (runningTaskInfo != null) {
                int windowingMode = runningTaskInfo.getConfiguration().windowConfiguration.getWindowingMode();
                if (windowingMode == 3 || windowingMode == 4) {
                    return true;
                }
            }
            return false;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public int isAppSupportSplit(String str) {
        ApplicationInfo applicationInfo;
        Intent intent = new Intent();
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setAction("android.intent.action.MAIN");
        if (!TextUtils.isEmpty(str)) {
            intent.setPackage(str);
        }
        Iterator<ResolveInfo> it = BydVoiceApplication.getContext().getPackageManager().queryIntentActivities(intent, 0).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && (applicationInfo = activityInfo.applicationInfo) != null) {
                String str2 = applicationInfo.packageName;
                String str3 = TAG;
                RLog.i(str3, "isSupportSplitScreen: pkgName = " + str2);
                if (!TextUtils.isEmpty(str2) && str2.equals(str)) {
                    RLog.i(str3, "isSupportSplitScreen: resizeMode = " + activityInfo.resizeMode);
                    if (activityInfo.resizeMode == 0 || activityInfo.resizeMode == 6 || activityInfo.resizeMode == 5) {
                        RLog.i(str3, "isSupportSplitScreen: return -1");
                        return -1;
                    }
                    RLog.i(str3, "isSupportSplitScreen: return 1");
                    return 1;
                }
            }
        }
        RLog.i(TAG, "isSupportSplitScreen: return 0 ");
        return 0;
    }

    public String getAppPkgByDirection(boolean z) {
        try {
            String str = ActivityTaskManager.getService().getStackInfo(z ? 3 : 4, 0).taskNames[r6.length - 1];
            String str2 = TAG;
            RLog.i(str2, "getStackInfo: taskName = " + str);
            String[] strArrSplit = str.split("/");
            RLog.i(str2, "getStackInfo: task[0] = " + strArrSplit[0]);
            return strArrSplit[0];
        } catch (RemoteException | IllegalArgumentException e) {
            e.printStackTrace();
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public void getFSDeviceState(GetFSDeviceStateParam getFSDeviceStateParam, IResponseSender<GetFSDeviceStateResult> iResponseSender) {
        boolean zEquals;
        try {
            String str = TAG;
            RLog.i(str, "getFSDeviceState: json = " + getFSDeviceStateParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getFSDeviceState】");
            if (FSDeviceEnum.DEVICE_USB.equals(getFSDeviceStateParam.getDeviceType())) {
                zEquals = TextUtils.equals(SystemPropertiesUtils.get("sys.byd.isUSBExist", "false"), "true");
            } else {
                zEquals = TextUtils.equals(SystemPropertiesUtils.get(BYDAutoSysPropertiesDevice.SD_EXIST, "false"), "true");
            }
            RLog.i(str, "getFSDeviceState: equals = " + zEquals);
            GetFSDeviceStateResult getFSDeviceStateResult = new GetFSDeviceStateResult();
            if (zEquals) {
                getFSDeviceStateResult.setState("CONNECTED");
            } else {
                getFSDeviceStateResult.setState("DISCONNECTED");
            }
            iResponseSender.send(new Response("OK", getFSDeviceStateResult));
            RLog.i(str, "getFSDeviceState: OK  " + getFSDeviceStateResult.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getFSDeviceState】,result【OK】");
        } catch (Exception e) {
            RLog.i(TAG, "getFSDeviceState: ERROR", e);
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getFSDeviceState】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void openFSDevice(OpenFSDeviceParam openFSDeviceParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "openFSDevice: json = " + openFSDeviceParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【openFSDevice】");
            String deviceType = openFSDeviceParam.getDeviceType();
            Intent intent = new Intent();
            intent.setClassName("com.byd.filemanager", "com.byd.filemanager.ui.MainActivity");
            Bundle bundle = new Bundle();
            bundle.putInt("tab_type", 0);
            if (FSDeviceEnum.DEVICE_USB.equals(deviceType)) {
                bundle.putInt("root_path", 2);
            } else {
                bundle.putInt("root_path", 1);
            }
            intent.putExtras(bundle);
            intent.setFlags(268435456);
            BydVoiceApplication.getContext().startActivity(intent);
            iResponseSender.send(new Response("OK"));
            RLog.i(str, "openFSDevice: OK");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【openFSDevice】,result【OK】");
        } catch (Exception e) {
            RLog.i(TAG, "openFSDevice: ERROR", e);
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【openFSDevice】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void closeFSDevice(CloseFSDeviceParam closeFSDeviceParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "closeFSDevice: json = " + closeFSDeviceParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【closeFSDevice】");
            stopAppByForce("com.byd.filemanager");
            iResponseSender.send(new Response("OK"));
            RLog.i(str, "closeFSDevice: OK");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【closeFSDevice】,result【OK】");
        } catch (Exception e) {
            RLog.i(TAG, "closeFSDevice: ERROR", e);
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【closeFSDevice】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void checkCPAAConnection(CheckCPAAconnectionParam checkCPAAconnectionParam, IResponseSender<CheckCPAAconnectionResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "checkCPAAConnection: json = " + checkCPAAconnectionParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【checkCPAAConnection】");
            CheckCPAAconnectionResult checkCPAAconnectionResult = new CheckCPAAconnectionResult();
            boolean carplayConnectStatus = SystemSettingManager.get().getCarplayConnectStatus();
            boolean androidAutoConnectStatus = SystemSettingManager.get().getAndroidAutoConnectStatus();
            if (carplayConnectStatus) {
                checkCPAAconnectionResult.setState(VCDeviceStatusEnum.CP_WIRELESS_CONNECTED);
            } else if (androidAutoConnectStatus) {
                checkCPAAconnectionResult.setState(VCDeviceStatusEnum.AA_WIRELESS_CONNECTED);
            } else {
                checkCPAAconnectionResult.setState("UNKNOWN");
            }
            iResponseSender.send(new Response("OK", checkCPAAconnectionResult));
            RLog.i(str, "checkCPAAConnection: OK");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【checkCPAAConnection】,result【OK】");
        } catch (Exception e) {
            RLog.i(TAG, "checkCPAAConnection: ERROR", e);
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【checkCPAAConnection】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getDeviceState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetDeviceStateParam r10, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r11) {
        /*
            Method dump skipped, instruction units count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleSettingsHelper.getDeviceState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetDeviceStateParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public void controlCameraTakePhoto(ControlCameraTakePhotoParam controlCameraTakePhotoParam, IResponseSender<BaseResult> iResponseSender) {
        int i;
        try {
            String str = TAG;
            RLog.i(str, "controlCameraTakePhoto: json = " + controlCameraTakePhotoParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlCameraTakePhoto】");
            if (CameraAreaEnum.CAMERA_INTERNAL.equals(controlCameraTakePhotoParam.getName())) {
                i = 0;
            } else {
                i = CameraAreaEnum.CAMERA_EXTERNAL.equals(controlCameraTakePhotoParam.getName()) ? 1 : -1;
            }
            if (!CameraUtils.checkHasCamera()) {
                RLog.i(str, "controlCameraTakePhoto: UNSUPPORTED");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlCameraTakePhoto】,result【UNSUPPORTED】");
                iResponseSender.send(new Response("UNSUPPORTED"));
            } else {
                takePicture(i);
                RLog.i(str, "controlCameraTakePhoto: OK");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlCameraTakePhoto】,result【OK】");
                iResponseSender.send(new Response("OK"));
            }
        } catch (Exception unused) {
            RLog.i(TAG, "controlCameraTakePhoto: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlCameraTakePhoto】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void controlCameraSwitch(ControlCameraSwitchParam controlCameraSwitchParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            RLog.i(TAG, "controlCameraSwitch: json = " + controlCameraSwitchParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlCameraSwitch】");
            iResponseSender.send(new Response("UNSUPPORTED"));
        } catch (Exception unused) {
            RLog.i(TAG, "controlCameraSwitch: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlCameraSwitch】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void controlCameraRecord(ControlCameraRecordParam controlCameraRecordParam, IResponseSender<BaseResult> iResponseSender) {
        int i;
        try {
            String str = TAG;
            RLog.i(str, "controlCameraRecord: json = " + controlCameraRecordParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlCameraRecord】");
            if (!CameraUtils.checkHasCamera()) {
                RLog.i(str, "controlCameraRecord: UNSUPPORTED");
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlCameraRecord】,result【UNSUPPORTED】");
                iResponseSender.send(new Response("UNSUPPORTED"));
                return;
            }
            if (CameraAreaEnum.CAMERA_INTERNAL.equals(controlCameraRecordParam.getName())) {
                i = 0;
            } else {
                i = CameraAreaEnum.CAMERA_EXTERNAL.equals(controlCameraRecordParam.getName()) ? 1 : -1;
            }
            Log.i(str, "controlCameraRecord: face = " + i);
            if ("ON".equals(controlCameraRecordParam.getState())) {
                startRecord(i);
            } else {
                stopRecord();
            }
            RLog.i(str, "controlCameraRecord: OK");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlCameraRecord】,result【OK】");
            iResponseSender.send(new Response("OK"));
        } catch (Exception unused) {
            RLog.i(TAG, "controlCameraRecord: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【controlCameraRecord】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public boolean closeCameraApp() {
        Log.i(TAG, "closeCameraApp() called");
        cameraDevice.closeCameraApp();
        return true;
    }

    public boolean openCamera() {
        Log.i(TAG, "openCamera() called");
        cameraDevice.openCamera(1);
        return true;
    }

    public boolean takePicture(int i) {
        Log.i(TAG, "takePicture() called");
        cameraDevice.takePicture(i, 0, 1, false);
        return true;
    }

    public boolean startRecord(int i) {
        Log.i(TAG, "startRecord() called");
        cameraDevice.startRecord(i, 0, false);
        return true;
    }

    public boolean stopRecord() {
        Log.i(TAG, "stopRecord() called");
        cameraDevice.stopRecord(false);
        return true;
    }
}
