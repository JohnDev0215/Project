package com.byd.logtools.apkinstall.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: UsbStateManager.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile f f3071a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3072b = HttpUrl.FRAGMENT_ENCODE_SET;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<com.byd.logtools.apkinstall.a.b> f3073c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private a f3074d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3075e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Context f3076f;

    /* JADX INFO: compiled from: UsbStateManager.java */
    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String dataString = intent.getDataString();
            String action = intent.getAction();
            BydLogHelper.logD("onReceive: data = " + dataString + ", action = " + action);
            if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
                BydLogHelper.logD("receive ACTION_MEDIA_MOUNTED, mount = U盘插入了");
                if (f.this.g()) {
                    f.this.f3073c.forEach(new Consumer() { // from class: com.byd.logtools.apkinstall.c.d
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((com.byd.logtools.apkinstall.a.b) obj).g();
                        }
                    });
                    return;
                }
                return;
            }
            if ("android.intent.action.MEDIA_EJECT".equals(action)) {
                BydLogHelper.logD("receive ACTION_MEDIA_EJECT, unMount: U盘移除了");
                if (dataString == null || !dataString.startsWith("file:///")) {
                    return;
                }
                f.this.f3075e = false;
                f.this.f3072b = HttpUrl.FRAGMENT_ENCODE_SET;
                f.this.f3073c.forEach(new Consumer() { // from class: com.byd.logtools.apkinstall.c.a
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((com.byd.logtools.apkinstall.a.b) obj).c();
                    }
                });
            }
        }
    }

    private f(Context context) {
        this.f3076f = context.getApplicationContext();
    }

    public static f e(Context context) {
        if (f3071a == null) {
            synchronized (f.class) {
                if (f3071a == null) {
                    f3071a = new f(context);
                }
            }
        }
        return f3071a;
    }

    public void d(com.byd.logtools.apkinstall.a.b bVar) {
        if (bVar == null || this.f3073c.contains(bVar)) {
            return;
        }
        this.f3073c.add(bVar);
    }

    public String f() {
        return this.f3072b;
    }

    public boolean g() {
        try {
            List<VolumeInfo> volumes = ((StorageManager) this.f3076f.getSystemService("storage")).getVolumes();
            boolean z = SystemProperties.getBoolean("sys.byd.isUSBExist", false);
            BydLogHelper.logD("isUDiskMount:isUSBExist = " + z);
            if (volumes != null && z) {
                BydLogHelper.logD("volumes size = " + volumes.size());
                for (VolumeInfo volumeInfo : volumes) {
                    try {
                        BydLogHelper.logD("addDisk volumeInfo = " + volumeInfo);
                        if ("usbotg".equals(volumeInfo.getDeviceType())) {
                            this.f3072b = volumeInfo.path;
                            this.f3075e = true;
                            BydLogHelper.logD("USBOTG mUDiskFilePath = " + this.f3072b);
                            return true;
                        }
                        if ("sdcard".equals(volumeInfo.getDeviceType())) {
                            BydLogHelper.logD("SDCARD mUDiskFilePath");
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        } catch (Throwable th2) {
            BydLogHelper.logE("isUDiskMount: " + th2.getMessage());
        }
        return false;
    }

    public void h() {
        BydLogHelper.logD("registerUsbListener");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addDataScheme("file");
        if (this.f3074d == null) {
            this.f3074d = new a();
        }
        this.f3076f.registerReceiver(this.f3074d, intentFilter);
    }

    public void i(com.byd.logtools.apkinstall.a.b bVar) {
        this.f3073c.remove(bVar);
    }

    public void j() {
        BydLogHelper.logD("unregisterUsbReceiver");
        this.f3076f.unregisterReceiver(this.f3074d);
        this.f3074d = null;
    }
}
