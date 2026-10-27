package b.b.g.n.q;

import android.content.Context;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BYDAutoTboxDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1292a = "persist.mobile.network.switch";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static p f1293b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public f.a.a.a.c f1294c;

    public static synchronized p c() {
        if (f1293b == null) {
            f1293b = new p();
        }
        return f1293b;
    }

    public String a(Context context) {
        f.a.a.a.c cVar = this.f1294c;
        if (cVar == null) {
            return "";
        }
        try {
            return cVar.m();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String b(Context context) {
        f.a.a.a.c cVar = this.f1294c;
        if (cVar == null) {
            return "";
        }
        try {
            return cVar.l();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public boolean d(Context context) {
        String string = Settings.Global.getString(context.getContentResolver(), f1292a);
        if (this.f1294c != null && TextUtils.isEmpty(string)) {
            try {
                boolean z = this.f1294c.f() == 0;
                b.b.m.c.a.a("BYDAutoTboxDeviceService", "getMobileNetworkState return:" + z);
                return z;
            } catch (Exception e2) {
                e2.printStackTrace();
                b.b.m.c.a.c("BYDAutoTboxDeviceService", "getMobileNetworkState:" + e2.getMessage());
            }
        }
        b.b.m.c.a.a("BYDAutoTboxDeviceService", "GET SYS_PRO_MOBILE_SWITCH:" + string);
        return "0".equals(string);
    }

    public void e(Context context) {
        try {
            b.b.m.c.a.a("BYDAutoTboxDeviceService", "init ITbox start:" + this.f1294c);
            this.f1294c = f.a.a.a.c.p();
            b.b.m.c.a.a("BYDAutoTboxDeviceService", "init ITbox end:" + this.f1294c);
            EventBus.getDefault().post(new b.b.a.c.a(230012));
        } catch (Exception unused) {
        }
    }

    public void f(f.a.a.a.d dVar) {
        f.a.a.a.c cVar = this.f1294c;
        if (cVar == null) {
            return;
        }
        try {
            cVar.i(dVar);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    public void g(Context context, boolean z) {
        int i = !z ? 1 : 0;
        Settings.Global.putString(context.getContentResolver(), f1292a, i + "");
        b.b.m.c.a.a("BYDAutoTboxDeviceService", "PUT SYS_PRO_MOBILE_SWITCH:" + i);
    }

    public void h(f.a.a.a.d dVar) {
        f.a.a.a.c cVar = this.f1294c;
        if (cVar == null) {
            return;
        }
        try {
            cVar.b(dVar);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }
}
