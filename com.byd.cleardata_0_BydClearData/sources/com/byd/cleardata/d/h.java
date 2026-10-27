package com.byd.cleardata.d;

import android.os.storage.VolumeInfo;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f2605a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected String f2606b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected String f2607c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected String f2608d;
    protected boolean e = true;

    public h(int i, String str, String str2, String str3) {
        this.f2605a = i;
        this.f2606b = str3;
        this.f2607c = str;
        this.f2608d = str2;
    }

    public static h f(VolumeInfo volumeInfo) {
        if (volumeInfo != null) {
            try {
                if (volumeInfo.type == 0) {
                    String deviceType = volumeInfo.getDeviceType();
                    com.byd.cleardata.i.c.b("deviceType = " + deviceType);
                    if ("usbotg".equals(deviceType)) {
                        com.byd.cleardata.i.c.b("add usb");
                        return new h(2, volumeInfo.path, volumeInfo.internalPath, volumeInfo.fsUuid);
                    }
                    if ("sdcard".equals(deviceType)) {
                        com.byd.cleardata.i.c.b("add sdcard");
                        return new h(1, volumeInfo.path, volumeInfo.internalPath, volumeInfo.fsUuid);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        com.byd.cleardata.i.c.b("getVolume failed " + volumeInfo);
        return null;
    }

    public int a() {
        return this.f2605a;
    }

    public String b() {
        return this.f2608d;
    }

    public String c() {
        return this.f2606b;
    }

    public String d() {
        return this.f2607c;
    }

    public boolean e() {
        return this.e;
    }

    public String toString() {
        return "Volume{id=" + this.f2605a + ", path='" + this.f2607c + "', internalPath='" + this.f2608d + "'}";
    }
}
