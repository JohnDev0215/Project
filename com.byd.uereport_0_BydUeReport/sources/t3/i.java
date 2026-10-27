package t3;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: compiled from: MVCLog.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f8036a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f8037b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Map<String, Long> f8038c;

    public i() {
        this.f8038c = new HashMap();
        this.f8036a = "MVC";
    }

    public void a(String str) {
        e(CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2, null, str);
    }

    public void b(Throwable th) {
        e(CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2, null, Log.getStackTraceString(th));
    }

    public String c() {
        return this.f8036a;
    }

    public void d(String str) {
        e(256, null, str);
    }

    public final void e(int i8, String str, String str2) {
        if (this.f8037b) {
            i8 = 65536;
        }
        if (str == null) {
            str = this.f8036a;
        }
        if (str == null && i8 == 65536) {
            str = "MVC-ERROR";
        }
        if (str == null || (69905 & i8) == 0) {
            return;
        }
        String str3 = "[MVC] " + str2;
        if (i8 == 1) {
            Log.v(str, str3);
            return;
        }
        if (i8 == 16) {
            Log.d(str, str3);
            return;
        }
        if (i8 == 256) {
            Log.i(str, str3);
        } else if (i8 == 4096) {
            Log.w(str, str3);
        } else {
            if (i8 != 65536) {
                return;
            }
            Log.e(str, str3);
        }
    }

    public void f(String str) {
        this.f8036a = str;
    }

    public i(String str) {
        this.f8038c = new HashMap();
        this.f8036a = str;
    }
}
