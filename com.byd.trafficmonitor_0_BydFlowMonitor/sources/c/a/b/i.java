package c.a.b;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: MVCLog.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3464a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f3465b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Map<String, Long> f3466c;

    public i() {
        this.f3466c = new HashMap();
        this.f3464a = "MVC";
    }

    public void a(String str) {
        e(65536, null, str);
    }

    public void b(Throwable th) {
        e(65536, null, Log.getStackTraceString(th));
    }

    public String c() {
        return this.f3464a;
    }

    public void d(String str) {
        e(256, null, str);
    }

    public final void e(int i2, String str, String str2) {
        if (this.f3465b) {
            i2 = 65536;
        }
        if (str == null) {
            str = this.f3464a;
        }
        if (str == null && i2 == 65536) {
            str = "MVC-ERROR";
        }
        if (str == null || (69905 & i2) == 0) {
            return;
        }
        String str3 = "[MVC] " + str2;
        if (i2 == 1) {
            Log.v(str, str3);
            return;
        }
        if (i2 == 16) {
            Log.d(str, str3);
            return;
        }
        if (i2 == 256) {
            Log.i(str, str3);
        } else if (i2 == 4096) {
            Log.w(str, str3);
        } else {
            if (i2 != 65536) {
                return;
            }
            Log.e(str, str3);
        }
    }

    public void f(String str) {
        this.f3464a = str;
    }

    public i(String str) {
        this.f3466c = new HashMap();
        this.f3464a = str;
    }
}
