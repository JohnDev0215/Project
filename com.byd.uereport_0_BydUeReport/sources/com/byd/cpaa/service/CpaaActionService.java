package com.byd.cpaa.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.text.TextUtils;
import com.byd.cpaa.androidauto.AndroidAutoManager;
import com.byd.cpaa.carplay.CarPlayManager;
import com.byd.uereport.R;
import com.byd.uereport.dialog.screencast.AndroidAutoNoticeDialog;
import com.byd.uereport.dialog.screencast.AndroidAutoPairDialog;
import com.byd.uereport.dialog.screencast.AndroidAutoStartDialog;
import com.byd.uereport.dialog.screencast.CarPlayNeedDialog;
import com.byd.uereport.dialog.screencast.CarPlayStartDialog;
import com.byd.uereport.dialog.screencast.CastProtectionNoticeDialog;
import com.byd.uereport.dialog.screencast.CastSwitchDeviceDialog;
import g5.m;
import h3.c;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: loaded from: classes.dex */
public class CpaaActionService extends BydIntentService {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f2705h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f2706i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f2707j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final String f2708k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f2709l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f2710m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f2711n;

    public class a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Context f2712d;

        public a(Context context) {
            this.f2712d = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            CpaaActionService.this.k(this.f2712d);
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Context f2714d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f2715e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f2716f;

        public b(Context context, String str, String str2) {
            this.f2714d = context;
            this.f2715e = str;
            this.f2716f = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            CarPlayStartDialog carPlayStartDialog = new CarPlayStartDialog(this.f2714d);
            carPlayStartDialog.h(this.f2715e);
            carPlayStartDialog.setDeviceName(this.f2716f);
            carPlayStartDialog.show();
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Context f2718d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f2719e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f2720f;

        public c(Context context, int i8, String str) {
            this.f2718d = context;
            this.f2719e = i8;
            this.f2720f = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            AndroidAutoStartDialog androidAutoStartDialog = new AndroidAutoStartDialog(this.f2718d);
            androidAutoStartDialog.f(this.f2719e);
            androidAutoStartDialog.setDeviceName(this.f2720f);
            androidAutoStartDialog.show();
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Context f2722d;

        public d(Context context) {
            this.f2722d = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            AndroidAutoNoticeDialog androidAutoNoticeDialog = new AndroidAutoNoticeDialog(this.f2722d);
            androidAutoNoticeDialog.d(1);
            androidAutoNoticeDialog.show();
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Context f2724d;

        public e(Context context) {
            this.f2724d = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            new CastProtectionNoticeDialog(this.f2724d).show();
        }
    }

    public class f implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Context f2726d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f2727e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f2728f;

        public f(Context context, int i8, String str) {
            this.f2726d = context;
            this.f2727e = i8;
            this.f2728f = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            CastSwitchDeviceDialog castSwitchDeviceDialog = new CastSwitchDeviceDialog(this.f2726d);
            castSwitchDeviceDialog.l(this.f2727e);
            castSwitchDeviceDialog.k(this.f2728f);
            castSwitchDeviceDialog.show();
        }
    }

    public class g implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Context f2730d;

        public g(Context context) {
            this.f2730d = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            new AndroidAutoPairDialog(this.f2730d).show();
        }
    }

    public class h implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Context f2732d;

        public h(Context context) {
            this.f2732d = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            new CarPlayNeedDialog(this.f2732d).show();
        }
    }

    public class i implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Context f2734d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f2735e;

        public i(Context context, String str) {
            this.f2734d = context;
            this.f2735e = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            m.e(this.f2734d, this.f2735e, 0);
        }
    }

    public CpaaActionService() {
        super("CpaaActionService");
        this.f2705h = "com.byd.card.open.carplay";
        this.f2706i = "com.byd.carplay.data.protection";
        this.f2707j = "com.byd.card.open.auto";
        this.f2708k = "com.byd.uereport.xcall.test";
        this.f2709l = "carsettings_device_connect_carplay_action";
        this.f2710m = "carsettings_device_connect_androidauto_action";
        this.f2711n = 500L;
    }

    public static void o(Context context, Intent intent) {
        g5.a.i("CpaaActionService--startCpaaService--action: " + intent.getAction());
        if (TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        intent.setComponent(new ComponentName(context, (Class<?>) CpaaActionService.class));
        context.startService(intent);
    }

    @Override // com.byd.cpaa.service.BydIntentService
    public void a(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        g5.a.i("CpaaActionService, onHandleIntent broadcast action:" + intent.getAction());
        String action = intent.getAction();
        action.hashCode();
        switch (action) {
            case "android.hardware.usb.action.USB_DEVICE_ATTACHED":
                q(intent);
                break;
            case "com.byd.carplay.data.protection":
                e(intent);
                break;
            case "android.hardware.usb.action.USB_DEVICE_DETACHED":
                r(intent);
                break;
            case "carsettings_device_connect_androidauto_action":
                f(intent);
                break;
            case "com.byd.card.open.carplay":
                d(intent);
                break;
            case "carsettings_device_connect_carplay_action":
                g(intent);
                break;
            case "com.byd.card.open.auto":
                c(intent);
                break;
            case "com.byd.uereport.xcall.test":
                s(intent);
                break;
        }
    }

    public final void c(Intent intent) {
        g5.a.b("CpaaActionService", "ACTION_OPEN_AUTO");
        h3.c cVarA = c.a.a();
        int iB = cVarA.b();
        g5.a.b("CpaaActionService", " androidAutoStatus :" + iB);
        boolean z7 = false;
        if (iB == 4) {
            if (intent.getExtras() != null && intent.getExtras().getInt("playStatus", -1) == 1) {
                g5.a.b("CpaaActionService", "show AA now playing .");
                z7 = true;
            }
            h();
            if (z7) {
                cVarA.i();
                return;
            } else {
                cVarA.h();
                return;
            }
        }
        if (iB == 3) {
            AndroidAutoManager.getInstance(this).sendMsgShowConnectingDialog(0);
            return;
        }
        List<AndroidAutoDevice> listC = cVarA.c();
        ArrayList arrayList = new ArrayList();
        if (listC != null) {
            for (int i8 = 0; i8 < listC.size(); i8++) {
                AndroidAutoDevice androidAutoDevice = listC.get(i8);
                g5.a.b("CpaaActionService", "androidAutoDevice status:" + androidAutoDevice.getDeviceStatus());
                if (androidAutoDevice.getDeviceStatus() == 1) {
                    arrayList.add(androidAutoDevice);
                }
            }
        }
        int size = arrayList.size();
        g5.a.b("CpaaActionService", " available AndroidAuto device size :" + size);
        if (size > 1) {
            g5.d.d(this);
            return;
        }
        if (size != 1) {
            i(this);
            return;
        }
        AndroidAutoDevice androidAutoDevice2 = (AndroidAutoDevice) arrayList.get(0);
        g5.a.b("CpaaActionService", " androidAutoDevice :" + androidAutoDevice2.getDeviceName());
        if (CarPlayManager.getInstance(this).isCarPlayConnected()) {
            n(this, 1, !TextUtils.isEmpty(androidAutoDevice2.getUsbSerialNumber()) ? androidAutoDevice2.getUsbSerialNumber() : androidAutoDevice2.getBtAddr());
            return;
        }
        if (CarPlayManager.getInstance(this).getCarPlayStatus() == 1) {
            p(getString(R.string.toast_other_device_connecting));
        } else if (androidAutoDevice2.isFirstConntect()) {
            j(!TextUtils.isEmpty(androidAutoDevice2.getUsbSerialNumber()) ? androidAutoDevice2.getUsbSerialNumber() : androidAutoDevice2.getBtAddr(), androidAutoDevice2.getProjectionConnectType());
        } else {
            AndroidAutoManager.getInstance(getApplicationContext()).connectAndroidAuto(androidAutoDevice2.getBtAddr(), 0);
        }
    }

    public final void d(Intent intent) {
        g5.a.b("CpaaActionService", " ACTION_OPEN_CARPLAY");
        int carPlayStatus = CarPlayManager.getInstance(this).getCarPlayStatus();
        g5.a.b("CpaaActionService", ", carPlayStatus :" + carPlayStatus);
        boolean z7 = false;
        if (carPlayStatus == 2) {
            if (w2.a.p().o() >= 1) {
                g5.a.b("CpaaActionService", " bluetooth call state.");
                return;
            }
            if (intent.getExtras() != null && intent.getExtras().getInt("playStatus", -1) == 1) {
                g5.a.b("CpaaActionService", " show CP now playing .");
                z7 = true;
            }
            h();
            if (z7) {
                CarPlayManager.getInstance(this).showNowPlaying();
                return;
            } else {
                CarPlayManager.getInstance(this).startCarPlayDisPlay();
                return;
            }
        }
        if (carPlayStatus == 1) {
            CarPlayManager.getInstance(this).sendMsgShowConnectingDialog(0);
            return;
        }
        List<CarPlayDevice> carPlayDevices = CarPlayManager.getInstance(this).getCarPlayDevices();
        ArrayList arrayList = new ArrayList();
        if (carPlayDevices != null) {
            for (int i8 = 0; i8 < carPlayDevices.size(); i8++) {
                CarPlayDevice carPlayDevice = carPlayDevices.get(i8);
                if (carPlayDevice.getDeviceStatus() == 1) {
                    arrayList.add(carPlayDevice);
                }
            }
        }
        int size = arrayList.size();
        g5.a.b("CpaaActionService", " available carplay device size :" + size);
        if (size > 1) {
            g5.d.d(this);
            return;
        }
        if (size != 1) {
            l(this);
            return;
        }
        CarPlayDevice carPlayDevice2 = (CarPlayDevice) arrayList.get(0);
        g5.a.b("CpaaActionService", " available carplay is first :" + carPlayDevice2.isFirstConntect());
        if (AndroidAutoManager.getInstance(this).isAndroidAutoConnected()) {
            n(this, 0, carPlayDevice2.getBtAddr());
            return;
        }
        if (AndroidAutoManager.getInstance(this).getAndroidAutoConnectionStatus() == 3) {
            p(getString(R.string.toast_other_device_connecting));
        } else if (carPlayDevice2.isFirstConntect()) {
            m(carPlayDevice2.getBtAddr(), carPlayDevice2.getDeviceName());
        } else {
            CarPlayManager.getInstance(this).connectCarPlay(carPlayDevice2.getBtAddr());
        }
    }

    public final void e(Intent intent) {
        j3.a.a(new a(getApplicationContext()));
    }

    public final void f(Intent intent) {
        String stringExtra = intent.getStringExtra("deviceId");
        int intExtra = intent.getIntExtra("conType", 0);
        if (stringExtra == null) {
            return;
        }
        g5.a.b("CpaaActionService", "handle Connect AndroidAuto:deviceId:****" + g5.a.h(stringExtra) + ",conType:" + intExtra);
        List<AndroidAutoDevice> listC = c.a.a().c();
        if (listC != null) {
            for (int i8 = 0; i8 < listC.size(); i8++) {
                AndroidAutoDevice androidAutoDevice = listC.get(i8);
                if (stringExtra.equals(androidAutoDevice.getUsbSerialNumber()) || stringExtra.equals(androidAutoDevice.getBtAddr())) {
                    if (androidAutoDevice.isFirstConntect()) {
                        j(stringExtra, intExtra);
                        return;
                    } else {
                        AndroidAutoManager.getInstance(getApplicationContext()).connectAndroidAuto(stringExtra, intExtra);
                        return;
                    }
                }
            }
        }
    }

    public final void g(Intent intent) {
        String stringExtra = intent.getStringExtra("deviceId");
        if (stringExtra == null) {
            return;
        }
        g5.a.b("CpaaActionService", "handle Connect Carplay :deviceId:****" + g5.a.h(stringExtra));
        List<CarPlayDevice> listD = c.a.a().d();
        if (listD != null) {
            for (int i8 = 0; i8 < listD.size(); i8++) {
                CarPlayDevice carPlayDevice = listD.get(i8);
                if (stringExtra.equals(carPlayDevice.getBtAddr())) {
                    if (carPlayDevice.isFirstConntect()) {
                        m(stringExtra, carPlayDevice.getDeviceName());
                        return;
                    } else {
                        CarPlayManager.getInstance(getApplicationContext()).connectCarPlay(stringExtra);
                        return;
                    }
                }
            }
        }
    }

    public final void h() {
        g5.a.b("CpaaActionService", "send card start CPAA broadcast .");
        sendBroadcast(new Intent("com.byd.system.ACTION_START_CPAA"));
    }

    public final void i(Context context) {
        g5.a.b("CpaaActionService", " show AndroidAuto pair dialog .");
        j3.a.b(new g(context), CarPlayManager.getInstance(this).isCarPlayConnected() ? 500L : 0L);
    }

    public final void j(String str, int i8) {
        g5.a.b("CpaaActionService", "Android Start Dialog, deviceId:" + g5.a.h(str) + ",conType:" + i8);
        j3.a.b(new c(getApplicationContext(), i8, str), CarPlayManager.getInstance(this).isCarPlayConnected() ? 500L : 0L);
    }

    public final void k(Context context) {
        g5.a.b("CpaaActionService", " show CP protection data dialog .");
        j3.a.a(new e(context));
    }

    public final void l(Context context) {
        g5.a.b("CpaaActionService", " show need CarPlay dialog .");
        j3.a.a(new h(context));
    }

    public final void m(String str, String str2) {
        g5.a.b("CpaaActionService", "CarPlay Start Dialog, deviceId:" + g5.a.h(str) + ",deviceName:" + str2);
        j3.a.b(new b(getApplicationContext(), str, str2), CarPlayManager.getInstance(this).isCarPlayConnected() ? 500L : 0L);
    }

    public final void n(Context context, int i8, String str) {
        g5.a.b("CpaaActionService", "show switch device dialog , type :" + i8);
        j3.a.b(new f(context, i8, str), CarPlayManager.getInstance(this).isCarPlayConnected() ? 500L : 0L);
    }

    public final void p(String str) {
        j3.a.a(new i(getApplicationContext(), str));
    }

    public final void q(Intent intent) {
    }

    public final void r(Intent intent) {
        String serialNumber = ((UsbDevice) intent.getParcelableExtra("device")).getSerialNumber();
        g5.a.b("CpaaActionService", "serialNumber :" + g5.a.h(serialNumber));
        CarPlayManager.getInstance(this).clearUsbSerialNumber(serialNumber);
        Intent intent2 = new Intent();
        intent2.setAction("cpaa_usb_device_detach");
        intent2.putExtra("device", serialNumber);
        a1.a.b(this).d(intent2);
    }

    public final void s(Intent intent) {
        j3.a.a(new d(getApplicationContext()));
    }
}
