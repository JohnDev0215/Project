package x4;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.telephony.TelephonyManager;

/* JADX INFO: compiled from: DeviceInfoUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile c f9083a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f9084b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BYDAutoBodyworkDevice f9085c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TelephonyManager f9086d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BYDAutoVersionDevice f9087e;

    public c(Context context) {
        this.f9084b = context.getApplicationContext();
    }

    public static c a(Context context) {
        if (f9083a == null) {
            synchronized (c.class) {
                if (f9083a == null) {
                    f9083a = new c(context);
                }
            }
        }
        return f9083a;
    }

    public String b() {
        return this.f9087e.getBydSystemVersion();
    }

    public void c() {
        if (this.f9085c == null) {
            this.f9085c = BYDAutoBodyworkDevice.getInstance(this.f9084b);
        }
        if (this.f9086d == null) {
            this.f9086d = (TelephonyManager) this.f9084b.getSystemService("phone");
        }
        if (this.f9087e == null) {
            this.f9087e = BYDAutoVersionDevice.getInstance(this.f9084b);
        }
    }
}
