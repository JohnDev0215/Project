package com.ts.appservice.engineeringmode.d;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static h f51b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.b.a.a.a f52a;

    public h() {
        try {
            this.f52a = b.b.a.a.a.d();
            com.ts.appservice.engineeringmode.e.a.g("UsbConnectManager", "mIUsbext : " + this.f52a);
        } catch (Exception e) {
            com.ts.appservice.engineeringmode.e.a.d("UsbConnectManager", "UsbConnectManager.IUsbext.getService ", e);
        }
    }

    public static synchronized h a() {
        if (f51b == null) {
            f51b = new h();
        }
        return f51b;
    }

    public int b(int i) {
        com.ts.appservice.engineeringmode.e.a.g("UsbConnectManager", "getPowerOc portName : " + i);
        b.b.a.a.a aVar = this.f52a;
        int iK = 0;
        if (aVar == null) {
            com.ts.appservice.engineeringmode.e.a.g("UsbConnectManager", "getPowerOc.mIUsbext == null");
            return 0;
        }
        try {
            iK = aVar.k(i);
            com.ts.appservice.engineeringmode.e.a.g("UsbConnectManager", "getPowerOc.isSuccess : " + iK);
            return iK;
        } catch (Exception e) {
            com.ts.appservice.engineeringmode.e.a.d("UsbConnectManager", "mIUsbext.GetPowerOc : ", e);
            return iK;
        }
    }

    public boolean c(int i) {
        com.ts.appservice.engineeringmode.e.a.g("UsbConnectManager", "getUsbConnectStatus portName : " + i);
        b.b.a.a.a aVar = this.f52a;
        boolean zI = false;
        if (aVar == null) {
            com.ts.appservice.engineeringmode.e.a.g("UsbConnectManager", "getUsbConnectStatus.mIUsbext == null");
            return false;
        }
        try {
            zI = aVar.i(i);
            com.ts.appservice.engineeringmode.e.a.g("UsbConnectManager", "getUsbConnectStatus.isSuccess : " + zI);
            return zI;
        } catch (Exception e) {
            com.ts.appservice.engineeringmode.e.a.d("UsbConnectManager", "mIUsbext.GetUsbConnectStatus : ", e);
            return zI;
        }
    }
}
