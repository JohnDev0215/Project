package com.neusoft.na.byd.sdk;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.properties.CarSettings;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.LauncherManager;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.byd.bydnaviapi.AbsBYDAutoNaviListener;
import com.byd.bydnaviapi.BydAutoNaviDevice;
import com.neusoft.na.byd.Byd8155fSystem;
import com.neusoft.na.byd.BydAppWidget;
import com.neusoft.na.byd.broadcast.HardKeyBroadcastReceiver;
import com.neusoft.na.byd.broadcast.OtaBroadcastReceiver;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.common.util.SystemUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NaviService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000k\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006*\u0001\n\u0018\u0000 22\u00020\u0001:\u0003234B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000fJ\u0006\u0010\u0015\u001a\u00020\rJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0014\u001a\u00020\u000fJ\b\u0010\u0018\u001a\u00020\u0011H\u0002J\b\u0010\u0019\u001a\u00020\u0011H\u0002J\b\u0010\u001a\u001a\u00020\u000fH\u0002J\b\u0010\u001b\u001a\u00020\u0011H\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0011H\u0017J\b\u0010$\u001a\u00020\u0011H\u0016J\b\u0010%\u001a\u00020\u0011H\u0014J\b\u0010&\u001a\u00020\u0011H\u0002J\u0010\u0010'\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\rH\u0002J\b\u0010)\u001a\u00020\u0011H\u0002J\b\u0010*\u001a\u00020\u0011H\u0002J*\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u00010/2\u0006\u0010\u0014\u001a\u00020\u000fJ\b\u00101\u001a\u00020\u0011H\u0003R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/neusoft/na/byd/sdk/NaviService;", "Lcom/neusoft/na/common/ServiceBase;", "()V", "bydAutoNaviDevice", "Lcom/byd/bydnaviapi/BydAutoNaviDevice;", "bydAutoSettingDevice", "Landroid/hardware/bydauto/setting/BYDAutoSettingDevice;", "hardKeyBroadcastReceiver", "Lcom/neusoft/na/byd/broadcast/HardKeyBroadcastReceiver;", "mServiceConnection", "com/neusoft/na/byd/sdk/NaviService$mServiceConnection$1", "Lcom/neusoft/na/byd/sdk/NaviService$mServiceConnection$1;", "mUiMode", "", "presentationBydAutoNaviDeviceCanUse", "", "backToHome", "", "getActivityOptions", "Landroid/app/ActivityOptions;", "isPrimary", "getNaviAppPosition", "getStackInfo", "Landroid/app/ActivityManager$StackInfo;", "initBydAutoNaviDevice", "initSendNaviPresentationState", "isNavigationLauncher", "notifyDiLinkStarted", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "onDestroy", "onStart", "resetWidget", "sendNaviPresentationState", ServiceBase.PAYLOAD_MODE, "sendNotifyDiLinkStarted", "start", "startActivity", "context", "Landroid/content/Context;", "packageName", "", "className", "switchToWallpaperDesktop", "Companion", "MyBroadCast", "SettingsObserver", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NaviService extends ServiceBase {
    private static final String ACTION = "com.neusoft.na.navigation.action.restart.split";
    private static final String ACTION_NOTIFY_DI_LINK_STARTED = "com.neusoft.na.navigation.notifyDiLinkStarted";
    private static final String ACTION_PRESENTATION_BYDAUTONAVIDEVICE_CAN_USE = "com.neusoft.na.navigation.presentation.interface.can.use";
    private static final String ACTION_SWITCH_TO_WALLPAPER_DESKTOP = "com.neusoft.na.navigation.switchToWallpaperDesktop";
    private static final String EXIT_COMPLETE_ACTIVITY = "com.neusoft.na.mots.closeAndKillMainActivity";
    private static final String EXIT_WITHOUT_RESTARTING = "com.neusoft.na.mots.exitWithoutRestarting";
    private static final String TAG = "XJNaviService";
    private BydAutoNaviDevice bydAutoNaviDevice;
    private BYDAutoSettingDevice bydAutoSettingDevice;
    private final HardKeyBroadcastReceiver hardKeyBroadcastReceiver;
    private final NaviService$mServiceConnection$1 mServiceConnection;
    private int mUiMode;
    private boolean presentationBydAutoNaviDeviceCanUse;

    @Override // com.neusoft.na.common.ServiceBase
    protected void onStart() {
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.neusoft.na.byd.sdk.NaviService$mServiceConnection$1] */
    public NaviService() {
        super("bydservice");
        this.mUiMode = 32;
        this.mServiceConnection = new ServiceConnection() { // from class: com.neusoft.na.byd.sdk.NaviService$mServiceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("XJNaviService", "onServiceConnected");
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                Log.i("XJNaviService", "onServiceDisconnected");
                this.this$0.resetWidget();
            }
        };
        this.hardKeyBroadcastReceiver = new HardKeyBroadcastReceiver();
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        NaviBinder naviBinder = NaviBinder.getInstance(this);
        Intrinsics.checkNotNullExpressionValue(naviBinder, "getInstance(this)");
        return naviBinder;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged");
        Intent intent = new Intent(Byd8155fSystem.ACTION_WIDGET_SCREEN_CHANGED);
        intent.setComponent(new ComponentName(this, "com.neusoft.na.byd.BydAppWidget"));
        int i = newConfig.uiMode & 48;
        Log.i(TAG, "onConfigurationChanged systemUiMode == " + i);
        if (this.mUiMode != i) {
            this.mUiMode = i;
            intent.putExtra(ServiceBase.PAYLOAD_CONFIG, newConfig);
        }
        sendBroadcast(intent);
    }

    /* JADX INFO: compiled from: NaviService.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/neusoft/na/byd/sdk/NaviService$MyBroadCast;", "Landroid/content/BroadcastReceiver;", "(Lcom/neusoft/na/byd/sdk/NaviService;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private final class MyBroadCast extends BroadcastReceiver {
        public MyBroadCast() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) throws InterruptedException {
            Log.i(NaviService.TAG, "onReceive: " + (intent != null ? intent.getAction() : null));
            if (NaviService.ACTION.equals(intent != null ? intent.getAction() : null)) {
                NaviService.this.start();
                return;
            }
            if (!Intrinsics.areEqual(intent != null ? intent.getAction() : null, NaviService.ACTION_SWITCH_TO_WALLPAPER_DESKTOP)) {
                if (!"com.neusoft.na.mots.exitWithoutRestarting".equals(intent != null ? intent.getAction() : null)) {
                    if (!"com.neusoft.na.mots.closeAndKillMainActivity".equals(intent != null ? intent.getAction() : null)) {
                        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, NaviService.ACTION_NOTIFY_DI_LINK_STARTED)) {
                            NaviService.this.notifyDiLinkStarted();
                            return;
                        }
                        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, NaviService.ACTION_PRESENTATION_BYDAUTONAVIDEVICE_CAN_USE)) {
                            Log.i(NaviService.TAG, "presentationBydAutoNaviDeviceCanUse == true");
                            NaviService.this.presentationBydAutoNaviDeviceCanUse = true;
                            return;
                        }
                        return;
                    }
                }
                NaviService.this.resetWidget();
                return;
            }
            Log.i(NaviService.TAG, "receive switchToWallpaperDesktop");
            NaviService.this.switchToWallpaperDesktop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetWidget() {
        Intent intent = new Intent(Byd8155fSystem.ACTION_WIDGET_RESET);
        intent.setComponent(new ComponentName(this, (Class<?>) BydAppWidget.class));
        sendBroadcast(intent);
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        startForeground();
        NaviService naviService = this;
        SystemUtils.INSTANCE.setSystemProperties(naviService, "persist.sys.automap_onekey_home", "1");
        registerReceiver(this.hardKeyBroadcastReceiver, new IntentFilter("CUSTOM_NAVI_STANDARD_BROADCAST_RECV"));
        resetWidget();
        this.mUiMode = getResources().getConfiguration().uiMode & 48;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        intentFilter.addAction(ACTION_SWITCH_TO_WALLPAPER_DESKTOP);
        intentFilter.addAction(ACTION_NOTIFY_DI_LINK_STARTED);
        intentFilter.addAction("com.neusoft.na.mots.exitWithoutRestarting");
        intentFilter.addAction("com.neusoft.na.mots.closeAndKillMainActivity");
        intentFilter.addAction(ACTION_PRESENTATION_BYDAUTONAVIDEVICE_CAN_USE);
        registerReceiver(new MyBroadCast(), intentFilter);
        getContentResolver().registerContentObserver(CarSettings.UserTableData.CONTENT_URI, true, new SettingsObserver(naviService, null));
        if (FileUtils.getInstance(naviService).isBydHasAll()) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.neusoft.na.navigation", "com.neusoft.na.mots.MotsMainService"));
            Intent intent2 = new Intent();
            intent2.setComponent(new ComponentName("com.neusoft.na.navigation", "com.neusoft.na.ocservice.MotsService"));
            startForegroundService(intent);
            startForegroundService(intent2);
            bindService(intent2, this.mServiceConnection, 1);
        }
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(OtaBroadcastReceiver.OTA_IN);
        intentFilter2.addAction(OtaBroadcastReceiver.OTA_OUT);
        registerReceiver(new OtaBroadcastReceiver(), intentFilter2);
        BYDAutoPowerDevice.getInstance(naviService).registerListener(new AbsBYDAutoPowerListener() { // from class: com.neusoft.na.byd.sdk.NaviService.onCreate.1
            @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
            public void onPowerSTRChanged(int state) {
                super.onPowerSTRChanged(state);
                Log.i(NaviService.TAG, "onPowerSTRChanged: " + state);
                if (state == 16) {
                    Log.i(NaviService.TAG, "STR_SUSPEND_EXIT --> 16");
                    if (ContextCompat.checkSelfPermission(NaviService.this, BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) == 0) {
                        BydAutoNaviDevice bydAutoNaviDevice = NaviService.this.bydAutoNaviDevice;
                        Intrinsics.checkNotNull(bydAutoNaviDevice);
                        int insNavDisplayMode = bydAutoNaviDevice.getInsNavDisplayMode();
                        Log.i(NaviService.TAG, "initNavDisplayMode == " + insNavDisplayMode);
                        NaviService.this.sendNaviPresentationState(insNavDisplayMode);
                        return;
                    }
                    Log.w(NaviService.TAG, "has no BYDAUTO_INSTRUMENT_COMMON permission");
                    return;
                }
                if (state != 19) {
                    return;
                }
                if (NaviService.this.bydAutoNaviDevice != null) {
                    BydAutoNaviDevice bydAutoNaviDevice2 = NaviService.this.bydAutoNaviDevice;
                    Intrinsics.checkNotNull(bydAutoNaviDevice2);
                    int focusMapType = bydAutoNaviDevice2.getFocusMapType();
                    BydAutoNaviDevice bydAutoNaviDevice3 = NaviService.this.bydAutoNaviDevice;
                    Intrinsics.checkNotNull(bydAutoNaviDevice3);
                    int insNavDisplayMode2 = bydAutoNaviDevice3.getInsNavDisplayMode();
                    Log.i(NaviService.TAG, "mapType == " + focusMapType + ", navDisplayMode == " + insNavDisplayMode2);
                    if ((focusMapType == 0 || focusMapType == 1) && insNavDisplayMode2 != 1) {
                        BydAutoNaviDevice bydAutoNaviDevice4 = NaviService.this.bydAutoNaviDevice;
                        Log.i(NaviService.TAG, "[sendNaviPresentationState] naviPresentationState == 1, code == " + (bydAutoNaviDevice4 != null ? Integer.valueOf(bydAutoNaviDevice4.sendNaviPresentationState(focusMapType, 1)) : null));
                    }
                } else {
                    Log.w("AbsBYDAutoPowerListener", "bydAutoNaviDevice is null, can't send message!");
                }
                NaviService.this.sendBroadcast(new Intent("com.neusoft.na.navigation.action.stop"));
                NaviBinder.getInstance(NaviService.this).shutdown();
                if (NaviService.this.isNavigationLauncher()) {
                    Log.i(NaviService.TAG, "into STR shutdown prepare, restart app...");
                    NaviService.this.sendBroadcast(new Intent("com.neusoft.na.mots.closeAndKillMainActivity"));
                    return;
                }
                Log.i(NaviService.TAG, "into STR shutdown prepare, kill app...");
                Object systemService = NaviService.this.getSystemService("activity");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
                    if (runningAppProcessInfo.pid != Process.myPid()) {
                        String str = runningAppProcessInfo.processName;
                        Intrinsics.checkNotNullExpressionValue(str, "runningAppProcessInfo.processName");
                        String packageName = NaviService.this.getPackageName();
                        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                        if (StringsKt.startsWith$default(str, packageName, false, 2, (Object) null)) {
                            Process.killProcess(runningAppProcessInfo.pid);
                        }
                    }
                }
                Log.i(NaviService.TAG, "onPowerSTRChanged: kill done");
                NaviService.this.presentationBydAutoNaviDeviceCanUse = false;
                Log.i(NaviService.TAG, "presentationBydAutoNaviDeviceCanUse reset false");
            }
        });
        initBydAutoNaviDevice();
    }

    private final void initBydAutoNaviDevice() {
        Log.i(TAG, "initBydAutoNaviDevice");
        NaviService naviService = this;
        if (ContextCompat.checkSelfPermission(naviService, BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) == 0) {
            Log.i(TAG, "has BYDAUTO_INSTRUMENT_COMMON permission");
            if (this.bydAutoNaviDevice == null) {
                this.bydAutoNaviDevice = BydAutoNaviDevice.getInstance(naviService);
                Log.i(TAG, "bydAutoNaviDevice instance.");
                BydAutoNaviDevice bydAutoNaviDevice = this.bydAutoNaviDevice;
                Intrinsics.checkNotNull(bydAutoNaviDevice);
                bydAutoNaviDevice.registerListener(new AbsBYDAutoNaviListener() { // from class: com.neusoft.na.byd.sdk.NaviService.initBydAutoNaviDevice.1
                    @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
                    public void onINSNavDisplayMode(int value) {
                        super.onINSNavDisplayMode(value);
                        Log.i(NaviService.TAG, "[onINSNavDisplayMode] --> value == " + value + ", presentationBydAutoNaviDeviceCanUse == " + NaviService.this.presentationBydAutoNaviDeviceCanUse);
                        NaviService.this.sendNaviPresentationState(value);
                    }

                    @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
                    public void onMapFocusChanged(int mapType) {
                        super.onMapFocusChanged(mapType);
                        Log.i(NaviService.TAG, "[onMapFocusChanged] --> mapType == " + mapType + ", presentationBydAutoNaviDeviceCanUse == " + NaviService.this.presentationBydAutoNaviDeviceCanUse);
                        if (mapType == 0 || mapType == 1) {
                            NaviService.this.initSendNaviPresentationState();
                        }
                    }
                });
                initSendNaviPresentationState();
                return;
            }
            Log.i(TAG, "BydAutoNaviDevice is instance, do not instance again.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initSendNaviPresentationState() {
        Log.i(TAG, "initSendNaviPresentationState");
        BydAutoNaviDevice bydAutoNaviDevice = this.bydAutoNaviDevice;
        Intrinsics.checkNotNull(bydAutoNaviDevice);
        int insNavDisplayMode = bydAutoNaviDevice.getInsNavDisplayMode();
        Log.i(TAG, "initNavDisplayMode == " + insNavDisplayMode);
        sendNaviPresentationState(insNavDisplayMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendNaviPresentationState(int mode) {
        Log.i(TAG, "sendNaviPresentationState --> mode == " + mode);
        BydAutoNaviDevice bydAutoNaviDevice = this.bydAutoNaviDevice;
        if (bydAutoNaviDevice != null) {
            int focusMapType = bydAutoNaviDevice.getFocusMapType();
            Log.i(TAG, "mapType == " + focusMapType);
            if ((focusMapType == 0 || focusMapType == 1) && !this.presentationBydAutoNaviDeviceCanUse) {
                if (mode == 1) {
                    Log.i(TAG, "[sendNAV_CLOSE] --> sendNaviPresentationState NAV_CLOSE, value == 1, code == " + bydAutoNaviDevice.sendNaviPresentationState(1, 1));
                } else {
                    Log.i(TAG, "[sendPrivacyPolicy] --> sendNaviPresentationState MAP_STATE_NOT_AGREE_AGREEMENT, value == 9, code == " + bydAutoNaviDevice.sendNaviPresentationState(1, 9));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isNavigationLauncher() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = getPackageManager().resolveActivity(intent, 0);
        if (resolveInfoResolveActivity == null) {
            return false;
        }
        Log.i(TAG, "resolveInfo.activityInfo.packageName == " + resolveInfoResolveActivity.activityInfo.packageName);
        if (Intrinsics.areEqual(resolveInfoResolveActivity.activityInfo.packageName, getPackageName())) {
            Log.i(TAG, "navigation is launcher and top.");
            return true;
        }
        Log.i(TAG, "navigation is not launcher.");
        return false;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        unregisterReceiver(this.hardKeyBroadcastReceiver);
        unbindService(this.mServiceConnection);
        resetWidget();
        super.onDestroy();
    }

    /* JADX INFO: compiled from: NaviService.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J*\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/neusoft/na/byd/sdk/NaviService$SettingsObserver;", "Landroid/database/ContentObserver;", "mContext", "Landroid/content/Context;", "handler", "Landroid/os/Handler;", "(Landroid/content/Context;Landroid/os/Handler;)V", "onChange", "", "selfChange", "", "uri", "Landroid/net/Uri;", "key", "", "operationFlag", "", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private static final class SettingsObserver extends ContentObserver {
        private final Context mContext;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SettingsObserver(Context mContext, Handler handler) {
            super(handler);
            Intrinsics.checkNotNullParameter(mContext, "mContext");
            this.mContext = mContext;
        }

        public void onChange(boolean selfChange, Uri uri, String key, int operationFlag) {
            Intrinsics.checkNotNullParameter(key, "key");
            super.onChange(selfChange, uri, key, operationFlag);
            if (Intrinsics.areEqual(CarSettings.UserTableData.BYD_APP_THEME, key) && operationFlag == 3) {
                Log.i(NaviService.TAG, "SettingsObserver onChange: BYD_APP_THEME");
                int i = CarSettings.UserTableData.getInt(this.mContext.getContentResolver(), CarSettings.UserTableData.BYD_APP_THEME);
                Log.i(NaviService.TAG, "onChange: " + i);
                Intent intent = new Intent(Byd8155fSystem.ACTION_WIDGET_UUID);
                intent.putExtra(Byd8155fSystem.WIDGET_UUID_KEY, i);
                intent.setComponent(new ComponentName(this.mContext, (Class<?>) BydAppWidget.class));
                this.mContext.sendBroadcast(intent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void start() {
        Log.i(TAG, "start activity");
        startActivity(this, getPackageName(), "com.neusoft.na.mots.MainActivity", true);
        Log.i(TAG, "start activity done");
    }

    public final ActivityManager.StackInfo getStackInfo(boolean isPrimary) {
        try {
            return ActivityTaskManager.getService().getStackInfo(isPrimary ? 3 : 4, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public final ActivityOptions getActivityOptions(boolean isPrimary) {
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchWindowingMode(isPrimary ? 3 : 4);
        makeBasic.setSplitScreenCreateMode(!isPrimary ? 1 : 0);
        Intrinsics.checkNotNullExpressionValue(makeBasic, "makeBasic");
        return makeBasic;
    }

    public final void startActivity(Context context, String packageName, String className, boolean isPrimary) {
        Intrinsics.checkNotNullParameter(context, "context");
        Log.i(TAG, "startActivity: " + isPrimary);
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNull(packageName);
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage != null) {
            if (className != null) {
                launchIntentForPackage.setClassName(packageName, className);
            }
            ActivityOptions activityOptions = getActivityOptions(isPrimary);
            launchIntentForPackage.setFlags(807403520);
            context.startActivity(launchIntentForPackage, activityOptions.toBundle());
        }
    }

    public final int getNaviAppPosition() {
        Log.i(TAG, "getNaviAppPosition");
        try {
            ActivityManager.StackInfo stackInfo = getStackInfo(true);
            if (stackInfo != null && stackInfo.visible && stackInfo.topActivity != null) {
                String packageName = stackInfo.topActivity.getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "stackInfo.topActivity.getPackageName()");
                if (Intrinsics.areEqual("com.neusoft.na.navigation", packageName)) {
                    return 3;
                }
            }
            ActivityManager.StackInfo stackInfo2 = getStackInfo(false);
            if (stackInfo2 != null && stackInfo2.visible && stackInfo2.topActivity != null) {
                String packageName2 = stackInfo2.topActivity.getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName2, "stackInfo.topActivity.getPackageName()");
                if (Intrinsics.areEqual("com.neusoft.na.navigation", packageName2)) {
                    return 4;
                }
            }
            return 0;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchToWallpaperDesktop() {
        Log.i(TAG, "[switchToWallpaperDesktop]");
        Object systemService = getSystemService("launcher");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.LauncherManager");
        ((LauncherManager) systemService).setDefaultLauncher("com.android.launcher3");
        backToHome();
    }

    private final void backToHome() {
        Log.i(TAG, "[backToHome]");
        sendBroadcast(new Intent("com.neusoft.na.navigation.backToHome"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyDiLinkStarted() throws InterruptedException {
        Log.i(TAG, "[notifyDiLinkStarted]");
        BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this);
        this.bydAutoSettingDevice = bYDAutoSettingDevice;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.registerListener(new AbsBYDAutoSettingListener() { // from class: com.neusoft.na.byd.sdk.NaviService.notifyDiLinkStarted.1
                @Override // android.hardware.IBYDAutoListener
                public void isConnected(boolean isBinding) {
                    super.isConnected(isBinding);
                    Log.i(NaviService.TAG, "[isConnected] --> isBinding == " + isBinding);
                    if (isBinding) {
                        NaviService.this.sendNotifyDiLinkStarted();
                    }
                }
            });
        }
        Thread.sleep(300L);
        sendNotifyDiLinkStarted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendNotifyDiLinkStarted() {
        Log.i(TAG, "[sendNotifyDiLinkStarted]");
        BYDAutoSettingDevice bYDAutoSettingDevice = this.bydAutoSettingDevice;
        if (bYDAutoSettingDevice != null) {
            bYDAutoSettingDevice.notifyDilinkStarted();
        }
    }
}
