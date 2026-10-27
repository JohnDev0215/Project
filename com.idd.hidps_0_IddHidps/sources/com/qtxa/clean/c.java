package com.qtxa.clean;

import a.a.a.a;
import android.os.ServiceManager;
import com.qtxa.qtxadefend.w;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static c f24b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a.a.a.a f25a;

    private c() {
    }

    public static c a() {
        if (f24b == null) {
            synchronized (c.class) {
                f24b = new c();
            }
        }
        return f24b;
    }

    private a.a.a.a h() {
        a.a.a.a aVar = this.f25a;
        if (aVar == null || !aVar.asBinder().isBinderAlive()) {
            this.f25a = a.AbstractBinderC0000a.f(ServiceManager.getService("idd-metrics_service"));
        }
        return this.f25a;
    }

    public String b() {
        a.a.a.a aVarH = h();
        String strU = null;
        if (aVarH != null) {
            try {
                strU = aVarH.u();
            } catch (Exception e) {
                w.d("getBusyboxIostat:" + e.toString());
            }
        }
        return strU == null ? "" : strU;
    }

    public String c(String str) {
        a.a.a.a aVarH = h();
        String strL = null;
        if (aVarH != null) {
            try {
                strL = aVarH.l(str);
            } catch (Exception e) {
                w.d("getInfoFromNative:" + e.toString());
            }
        }
        return strL == null ? "" : strL;
    }

    public int d(String str) {
        int iM;
        a.a.a.a aVarH = h();
        if (aVarH != null) {
            try {
                iM = aVarH.m(str);
            } catch (Exception e) {
                w.d("getMemoryUsageFromProcessName:" + e.toString());
                iM = -1;
            }
        } else {
            iM = -1;
        }
        if (iM == -1) {
            return -1;
        }
        return iM;
    }

    public int e(String str) {
        int iR;
        a.a.a.a aVarH = h();
        if (aVarH != null) {
            try {
                iR = aVarH.r(str);
            } catch (Exception e) {
                w.d("getPidFromProcessName:" + e.toString());
                iR = -1;
            }
        } else {
            iR = -1;
        }
        if (iR == -1) {
            return -1;
        }
        return iR;
    }

    public String f() {
        a.a.a.a aVarH = h();
        String strE = null;
        if (aVarH != null) {
            try {
                strE = aVarH.e();
            } catch (Exception e) {
                w.d("getProcMeminfo:" + e.toString());
            }
        }
        return strE == null ? "" : strE;
    }

    public String g(int i) {
        a.a.a.a aVarH = h();
        w.d("getProcStat:" + aVarH);
        String strG = null;
        if (aVarH != null) {
            try {
                strG = aVarH.g(i);
            } catch (Throwable th) {
                w.d("getProcStat:" + th.toString());
            }
        }
        return strG == null ? "" : strG;
    }

    public String i(int i) {
        a.a.a.a aVarH = h();
        String strH = null;
        if (aVarH != null) {
            try {
                strH = aVarH.h("", i);
            } catch (Exception e) {
                w.d("getTopInfo:" + e.toString());
            }
        }
        return strH == null ? "" : strH;
    }
}
