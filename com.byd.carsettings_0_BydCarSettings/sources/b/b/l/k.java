package b.b.l;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: MVCLog.java */
/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Map<String, Long> f1993c;

    public k() {
        this.f1993c = new HashMap();
        this.f1991a = "MVC";
    }

    public void a(String str) {
        e(65536, null, str);
    }

    public void b(Throwable th) {
        e(65536, null, Log.getStackTraceString(th));
    }

    public String c() {
        return this.f1991a;
    }

    public void d(String str) {
        e(256, null, str);
    }

    public final void e(int i, String str, String str2) {
        if (this.f1992b) {
            i = 65536;
        }
        if (str == null) {
            str = this.f1991a;
        }
        if (str == null && i == 65536) {
            str = "MVC-ERROR";
        }
        if (str == null || (69905 & i) == 0) {
            return;
        }
        String str3 = "[MVC] " + str2;
        if (i == 1) {
            Log.v(str, str3);
            return;
        }
        if (i == 16) {
            Log.d(str, str3);
            return;
        }
        if (i == 256) {
            Log.i(str, str3);
        } else if (i == 4096) {
            Log.w(str, str3);
        } else {
            if (i != 65536) {
                return;
            }
            Log.e(str, str3);
        }
    }

    public void f(String str) {
        this.f1991a = str;
    }

    public k(String str) {
        this.f1993c = new HashMap();
        this.f1991a = str;
    }
}
