package com.byd.systemsettings.ui.dialog;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Parcel;
import android.os.UserHandle;
import com.byd.bt.model.BondDevice;
import com.byd.systemsettings.R;
import com.byd.systemsettings.btsetting.BtImgUtil;
import com.byd.systemsettings.btsetting.BtSettingsPager;
import com.byd.systemsettings.btsetting.view.dialogpager.BtPairCodePager;
import com.byd.systemsettings.cpaa.AndroidAutoManager;
import com.byd.systemsettings.cpaa.CarPlayManager;
import com.byd.systemsettings.cpaa.CarPlayPager;
import com.byd.systemsettings.cpaa.ChangeCpaaDevicePager;
import com.byd.systemsettings.hotspot.PerHotspotPager;
import com.byd.systemsettings.screensaver.AudioScreenPager;
import com.byd.systemsettings.screensaver.ScreenOffPager;
import com.byd.systemsettings.system.BydSystemSettings;
import com.byd.systemsettings.system.BydXCallDevice;
import com.byd.systemsettings.system.SystemSettingsModule;
import com.byd.systemsettings.system.model.BtPairCodeParams;
import com.byd.systemsettings.system.model.ClockStyle;
import com.byd.systemsettings.ui.pager.BtnLeftHighlightPager;
import com.byd.systemsettings.ui.pager.IdentificationPager;
import com.byd.systemsettings.wifi2.WifiSettingsPager;
import java.util.HashMap;
import java.util.List;
import o2.b;
import o2.c;
import p2.g;
import y2.b;
import z3.a;
import z3.d;

/* JADX INFO: loaded from: classes2.dex */
public class SystemDialog extends ServiceDialogHolder {
    private static final String ACTION = "action";
    public static final String DISMISS_HOTSPOT = "dismiss_hotspot";
    public static final String HOTSPOT = "Hotspot";
    private static final long SCREEN_OFF_ANIM_TIME = 500;
    private static final long SCREEN_SAVER_ANIM_TIME = 200;
    private static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    private static final String SERVICE_PKG = "com.byd.carsettings";
    private static final String TAG = "SystemDialog";
    public static volatile Intent btPairIntent = null;
    public static volatile boolean cpaaDisplaying = false;
    private static Context mContext;
    public static final Class<? extends a>[] ALL_DIALOG_ARR = {BtSettingsPager.class, WifiSettingsPager.class, PerHotspotPager.class, CarPlayPager.class, ScreenOffPager.class, AudioScreenPager.class, BtPairCodePager.class};
    public static String SYS_BT_DIALOG_CHANGE_ACTION = "com.byd.carsettings.bt.dialog.state.change";
    public static String SYS_WIFI_DIALOG_CHANGE_ACTION = "com.byd.carsettings.wifi.dialog.state.change";
    public static String SYS_HOTSPOT_DIALOG_CHANGE_ACTION = "com.byd.carsettings.hotspot.dialog.state.change";
    public static String SYS_CONNECT_DEVICE_DIALOG_CHANGE_ACTION = "com.byd.carsettings.connect_device.dialog.state.change";

    public static void dismissChangeCpaaDevicePager() {
        ServiceDialogHolder.dismiss(ChangeCpaaDevicePager.class);
    }

    public static void dismissIdentification() {
        ServiceDialogHolder.dismiss(IdentificationPager.class);
    }

    public static void finishBtPairCode() {
        ServiceDialogHolder.dismiss(BtPairCodePager.class);
    }

    public static void finishBtPairCodeOnMain() {
        x3.a.a(TAG, "-----finishBtPairCodeOnMain----");
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.ui.dialog.SystemDialog.3
            @Override // java.lang.Runnable
            public void run() {
                ServiceDialogHolder.dismiss(BtPairCodePager.class);
            }
        });
    }

    public static void finishBtSettings() {
        ServiceDialogHolder.dismiss(BtSettingsPager.class);
    }

    public static void finishCpaaSettings() {
        ServiceDialogHolder.dismiss(CarPlayPager.class);
    }

    public static void finishHotspotSettings() {
        ServiceDialogHolder.dismiss(PerHotspotPager.class);
    }

    public static void finishScreensaver() {
        boolean z7 = BydSystemSettings.Singleton.get().getScreenSaverClockStyle(mContext) == ClockStyle.CLOSE;
        x3.a.a(TAG, "-----finishScreensaver----isScreenOff: " + z7);
        if (z7) {
            ServiceDialogHolder.dismiss(ScreenOffPager.class);
        } else {
            ServiceDialogHolder.dismiss(AudioScreenPager.class);
        }
    }

    public static void finishWifiSettings() {
        ServiceDialogHolder.dismiss(WifiSettingsPager.class);
    }

    public static Context getContext() {
        return mContext;
    }

    public static boolean hasSystemDialogShowing() {
        return ServiceDialogHolder.hasDialogShowing(ALL_DIALOG_ARR);
    }

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        x3.a.a(TAG, "----init-----");
        mContext = context.getApplicationContext();
    }

    @SuppressLint({"MissingPermission"})
    public static void sendBroadcastBtDialogShow(Context context, boolean z7) {
        Intent intent = new Intent(SYS_BT_DIALOG_CHANGE_ACTION);
        intent.putExtra("show", z7);
        Parcel parcelObtain = Parcel.obtain();
        context.sendBroadcastAsUser(intent, UserHandle.readFromParcel(parcelObtain));
        parcelObtain.recycle();
    }

    @SuppressLint({"MissingPermission"})
    public static void sendBroadcastConnectDeviceDialogShow(Context context, boolean z7) {
        Intent intent = new Intent(SYS_CONNECT_DEVICE_DIALOG_CHANGE_ACTION);
        intent.putExtra("show", z7);
        Parcel parcelObtain = Parcel.obtain();
        context.sendBroadcastAsUser(intent, UserHandle.readFromParcel(parcelObtain));
        parcelObtain.recycle();
    }

    @SuppressLint({"MissingPermission"})
    public static void sendBroadcastHotspotDialogShow(Context context, boolean z7) {
        Intent intent = new Intent(SYS_HOTSPOT_DIALOG_CHANGE_ACTION);
        intent.putExtra("show", z7);
        Parcel parcelObtain = Parcel.obtain();
        context.sendBroadcastAsUser(intent, UserHandle.readFromParcel(parcelObtain));
        parcelObtain.recycle();
    }

    @SuppressLint({"MissingPermission"})
    private static void sendBroadcastToCard(Context context, String str) {
        Intent intent = new Intent(str);
        intent.setPackage("com.byd.card");
        Parcel parcelObtain = Parcel.obtain();
        context.sendBroadcastAsUser(intent, UserHandle.readFromParcel(parcelObtain));
        parcelObtain.recycle();
    }

    @SuppressLint({"MissingPermission"})
    public static void sendBroadcastWifiDialogShow(Context context, boolean z7) {
        Intent intent = new Intent(SYS_WIFI_DIALOG_CHANGE_ACTION);
        intent.putExtra("show", z7);
        Parcel parcelObtain = Parcel.obtain();
        context.sendBroadcastAsUser(intent, UserHandle.readFromParcel(parcelObtain));
        parcelObtain.recycle();
    }

    private static void setDialogStateOnDismiss(final a aVar) {
        if (aVar == null) {
            return;
        }
        aVar.setOnBackPressCallback(new d() { // from class: com.byd.systemsettings.ui.dialog.SystemDialog.5
            @Override // z3.d
            public void onBackPress() {
                ServiceDialogHolder.dismiss(aVar.getClass());
            }
        });
    }

    public static void showBtPairCode(Context context, Intent intent, boolean z7) {
        btPairIntent = intent;
        cpaaDisplaying = z7;
        showBtPairCodeDialog(context);
    }

    private static void showBtPairCodeDialog(Context context) {
        if (!b.o.a().Y() && ServiceDialogHolder.checkDialogLevel(context, BtPairCodePager.class)) {
            x3.a.a(TAG, "-----showBtPairCodeDialog----");
            BtPairCodePager btPairCodePager = new BtPairCodePager(context, cpaaDisplaying);
            btPairCodePager.updateData(new BtPairCodeParams(context.getResources().getString(R.string.title_bt_pair_request), btPairIntent));
            if (cpaaDisplaying) {
                ServiceDialogHolder.showOnStatusbarBgTrans(context, btPairCodePager);
            } else {
                ServiceDialogHolder.showOnStatusbar(context, btPairCodePager);
            }
            setDialogStateOnDismiss(btPairCodePager);
            cpaaDisplaying = false;
        }
    }

    public static synchronized void showBtSettings(Context context) {
        if (!ServiceDialogHolder.checkDialogLevel(context, BtSettingsPager.class)) {
            x3.a.b(TAG, "showBtSettings", "checkDialogLevel return!!");
            return;
        }
        x3.a.a(TAG, "-----showBtSettings----");
        BtSettingsPager btSettingsPager = new BtSettingsPager(context);
        ServiceDialogHolder.showOnStatusbar(context, btSettingsPager);
        setDialogStateOnDismiss(btSettingsPager);
        sendBroadcastBtDialogShow(context, true);
    }

    public static e4.b showBtnLeftHighlight(Context context, String str, String str2, String str3, e4.d dVar) {
        if (context == null) {
            return null;
        }
        x3.a.a(TAG, "---showBtnLeftHighlight---");
        BtnLeftHighlightPager btnLeftHighlightPager = new BtnLeftHighlightPager(context);
        btnLeftHighlightPager.setTitleText(str);
        btnLeftHighlightPager.setMessageText(str2);
        btnLeftHighlightPager.setBtnOkText(str3);
        btnLeftHighlightPager.setOnOKClickedListener(dVar);
        ServiceDialogHolder.showOnStatusbar(context, btnLeftHighlightPager, false);
        return ServiceDialogHolder.getOperateCarDialog(BtnLeftHighlightPager.class);
    }

    public static ChangeCpaaDevicePager showChangeCpaaDeviceDialog(boolean z7, boolean z8, Context context, w3.a aVar) {
        if (!ServiceDialogHolder.checkDialogLevel(context, ChangeCpaaDevicePager.class)) {
            x3.a.b(TAG, "showChangeCpaaDeviceDialog", "checkDialogLevel return!!");
            return null;
        }
        x3.a.a(TAG, "-----showChangeCpaaDeviceDialog----");
        ChangeCpaaDevicePager changeCpaaDevicePager = new ChangeCpaaDevicePager(context, z7, z8);
        changeCpaaDevicePager.initData(context);
        ServiceDialogHolder.showOnStatusbar(context, changeCpaaDevicePager);
        changeCpaaDevicePager.setBtnClickedCallback(aVar);
        setDialogStateOnDismiss(changeCpaaDevicePager);
        return changeCpaaDevicePager;
    }

    private static void showConfirmWifiDialog(final Context context) {
        boolean zIsCarPlayConnected = CarPlayManager.getInstance(context).isCarPlayConnected();
        Resources resources = context.getResources();
        int i8 = R.string.wifi_confirm_msg_2;
        Object[] objArr = new Object[1];
        objArr[0] = zIsCarPlayConnected ? "Apple CarPlay" : "Android Auto";
        c.b().c().jump2OffHotspotWarningDialog(3, resources.getString(i8, objArr), new b.a() { // from class: com.byd.systemsettings.ui.dialog.SystemDialog.4
            @Override // o2.b.a
            public void onOkResult(Object obj) {
                if (w2.a.p().o() > 0) {
                    q2.d.b(context, R.string.operation_prohibited_during_call);
                } else {
                    SystemDialog.showWifiPage(context);
                }
            }
        });
    }

    public static synchronized CarPlayPager showCpaaSettings(Context context) {
        if (!ServiceDialogHolder.checkDialogLevel(context, CarPlayPager.class)) {
            return null;
        }
        x3.a.a(TAG, "-----showCarPlay----");
        CarPlayPager carPlayPager = new CarPlayPager(context);
        ServiceDialogHolder.showOnStatusbar(context, carPlayPager);
        setDialogStateOnDismiss(carPlayPager);
        sendBroadcastConnectDeviceDialogShow(context, true);
        return carPlayPager;
    }

    public static synchronized void showHotspotSettings(Context context) {
        if (ServiceDialogHolder.checkDialogLevel(context, PerHotspotPager.class)) {
            x3.a.a(TAG, "-----showHotspotSettings----");
            PerHotspotPager perHotspotPager = new PerHotspotPager(context);
            ServiceDialogHolder.showOnStatusbar(context, perHotspotPager);
            setDialogStateOnDismiss(perHotspotPager);
            sendBroadcastHotspotDialogShow(context, true);
        }
    }

    public static e4.b showIdentification(Context context, String str, String str2, String str3, BondDevice bondDevice, e4.d dVar, DialogInterface.OnDismissListener onDismissListener) {
        if (context == null || bondDevice == null) {
            return null;
        }
        x3.a.a(TAG, "---showIdentification---d: " + bondDevice);
        IdentificationPager identificationPager = new IdentificationPager(context);
        identificationPager.setTypeImgIcon(BtImgUtil.getBtTypeImgId(bondDevice.getType(), bondDevice.getPhoneType(), bondDevice.isCpConnected()));
        identificationPager.setTypeName(bondDevice.getName());
        identificationPager.setTitleText(str);
        identificationPager.setMessageText(str2);
        identificationPager.setBtnOkText(str3);
        identificationPager.setOnOKClickedListener(dVar);
        ServiceDialogHolder.showOnStatusbar(context, identificationPager, false);
        e4.b operateCarDialog = ServiceDialogHolder.getOperateCarDialog(IdentificationPager.class);
        if (operateCarDialog != null) {
            operateCarDialog.D(onDismissListener);
        }
        return operateCarDialog;
    }

    public static synchronized void showPrivacyManage(Context context) {
    }

    public static synchronized void showScreenOff(final Context context) {
        if (ServiceDialogHolder.checkDialogLevel(context, ScreenOffPager.class)) {
            x3.a.a(TAG, "-----showScreenOff----");
            ScreenOffPager screenOffPager = new ScreenOffPager(context);
            e4.b dialog = ServiceDialogHolder.getDialog(screenOffPager, true);
            if (dialog != null) {
                dialog.s(SystemSettingsModule.getInstance().isShowScreensaverFromAvm() ? SCREEN_SAVER_ANIM_TIME : SCREEN_OFF_ANIM_TIME);
                dialog.F(true);
                dialog.u(R.color.black);
                dialog.D(new DialogInterface.OnDismissListener() { // from class: com.byd.systemsettings.ui.dialog.SystemDialog.1
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        BydSystemSettings.Singleton.get().setNavibarModeScreensaver(context, 1);
                    }
                });
            }
            ServiceDialogHolder.showScreenoffOnNavibar(context, dialog, screenOffPager);
            setDialogStateOnDismiss(screenOffPager);
        }
    }

    public static synchronized void showScreensaver(final Context context) {
        if (ServiceDialogHolder.checkDialogLevel(context, AudioScreenPager.class)) {
            x3.a.a(TAG, "-----showScreensaver----");
            AudioScreenPager audioScreenPager = new AudioScreenPager(context);
            e4.b dialog = ServiceDialogHolder.getDialog(audioScreenPager, true);
            if (dialog != null) {
                dialog.s(SCREEN_SAVER_ANIM_TIME);
                dialog.D(new DialogInterface.OnDismissListener() { // from class: com.byd.systemsettings.ui.dialog.SystemDialog.2
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        BydSystemSettings.Singleton.get().setNavibarModeScreensaver(context, 1);
                    }
                });
            }
            ServiceDialogHolder.showScreensaverOnStatusbar(context, dialog, audioScreenPager);
            setDialogStateOnDismiss(audioScreenPager);
            x3.a.a(TAG, "showScreensaver", ", dialog: " + dialog);
        }
    }

    public static synchronized void showScreensaverOrScreenOff(Context context) {
        x3.a.c(TAG, "showScreensaverOrScreenOff", ", CallState: " + w2.a.p().o());
        BydXCallDevice bydXCallDevice = BydXCallDevice.Singleton.get();
        bydXCallDevice.init(context);
        if (bydXCallDevice.isOnECalling(context)) {
            q2.d.b(context, R.string.operation_prohibited_during_call);
            return;
        }
        if (w2.a.p().o() > 0 && context != null) {
            q2.d.b(context, R.string.operation_prohibited_during_call);
            return;
        }
        if (BydSystemSettings.Singleton.get().getScreenSaverClockStyle(context) == ClockStyle.CLOSE) {
            showScreenOff(context);
        } else {
            showScreensaver(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showWifiPage(Context context) {
        x3.a.a(TAG, "-----showWifiSettings----");
        WifiSettingsPager wifiSettingsPager = new WifiSettingsPager(context);
        ServiceDialogHolder.showOnStatusbar(context, wifiSettingsPager);
        setDialogStateOnDismiss(wifiSettingsPager);
        sendBroadcastWifiDialogShow(context, true);
    }

    public static synchronized void showWifiSettings(Context context) {
        x3.a.c(TAG, "showWifiSettings", ", CallState: " + w2.a.p().o());
        if (w2.a.p().o() > 0 && context != null) {
            q2.d.b(context, R.string.operation_prohibited_during_call);
            return;
        }
        if (ServiceDialogHolder.checkDialogLevel(context, WifiSettingsPager.class)) {
            boolean zIsCarPlayConnected = CarPlayManager.getInstance(context).isCarPlayConnected();
            boolean zIsAndroidAutoWifiConnected = AndroidAutoManager.getInstance(context).isAndroidAutoWifiConnected();
            if (((WifiManager) context.getSystemService("wifi")).isWifiEnabled() || (!zIsCarPlayConnected && !zIsAndroidAutoWifiConnected)) {
                showWifiPage(context);
            } else {
                showConfirmWifiDialog(context);
            }
        }
    }

    private static int startServiceApi(String str) {
        return startServiceApi(str, null);
    }

    private static int startServiceApi(String str, HashMap<String, String> map) {
        Context context = mContext;
        if (context == null) {
            return -1;
        }
        PackageManager packageManager = context.getPackageManager();
        StringBuffer stringBuffer = new StringBuffer("byd_pad://byd_car_settings:2933/launcher");
        stringBuffer.append("?");
        stringBuffer.append(ACTION);
        stringBuffer.append("=");
        stringBuffer.append(str);
        if (map != null && map.size() > 0) {
            for (String str2 : map.keySet()) {
                stringBuffer.append("&");
                stringBuffer.append(str2);
                stringBuffer.append("=");
                stringBuffer.append(map.get(str2));
            }
        }
        x3.a.a(TAG, "----startServiceApi----scheme = " + ((Object) stringBuffer));
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        x3.a.a(TAG, "----startServiceApi----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return -1;
        }
        mContext.startService(intent);
        return 0;
    }

    public static e4.b showBtnLeftHighlight(Context context, String str, String str2, String str3, String str4, e4.d dVar) {
        if (context == null) {
            return null;
        }
        x3.a.a(TAG, "---showBtnLeftHighlight---");
        BtnLeftHighlightPager btnLeftHighlightPager = new BtnLeftHighlightPager(context);
        btnLeftHighlightPager.setTitleText(str);
        btnLeftHighlightPager.setMessageText(str2);
        btnLeftHighlightPager.setBtnOkText(str3);
        btnLeftHighlightPager.setBtnCancelText(str4);
        btnLeftHighlightPager.setOnOKClickedListener(dVar);
        ServiceDialogHolder.showOnStatusbar(context, btnLeftHighlightPager, false);
        return ServiceDialogHolder.getOperateCarDialog(BtnLeftHighlightPager.class);
    }
}
