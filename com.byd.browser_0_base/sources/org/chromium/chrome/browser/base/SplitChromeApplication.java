package org.chromium.chrome.browser.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import defpackage.ap2;
import defpackage.bo2;
import defpackage.co2;
import defpackage.cp2;
import defpackage.d73;
import defpackage.fo2;
import defpackage.fp2;
import defpackage.no2;
import defpackage.oj2;
import defpackage.p11;
import defpackage.xz;
import defpackage.yq2;
import org.chromium.base.BundleUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.base.SplitPreloader;
import org.chromium.chrome.browser.base.SplitPreloader.b;

/* JADX INFO: loaded from: classes.dex */
public class SplitChromeApplication extends SplitCompatApplication {

    @SuppressLint({"StaticFieldLeak"})
    public static SplitPreloader j;
    public final String h = "pp";
    public Resources i;

    public class a implements SplitPreloader.a {
        public final /* synthetic */ Context a;

        public a(Context context) {
            this.a = context;
        }
    }

    public static void f(String str) {
        SplitPreloader.b bVarRemove;
        SplitPreloader splitPreloader = j;
        if (splitPreloader != null) {
            TraceEvent traceEventH0 = TraceEvent.H0("SplitPreloader.wait", null);
            try {
                synchronized (splitPreloader.a) {
                    bVarRemove = splitPreloader.a.remove(str);
                }
                if (bVarRemove != null) {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    bVarRemove.p();
                    oj2.b0(SystemClock.uptimeMillis() - jUptimeMillis, "Android.IsolatedSplits.PreloadWaitTime." + str);
                }
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatApplication, android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        Log.i("cr_SplitChromeApp", "attachBaseContext start");
        if (SplitCompatApplication.d()) {
            this.e = new yq2(0, this);
        } else {
            this.e = new p11(2, this);
        }
        Log.i("cr_SplitChromeApp", "attachBaseContext end");
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Context createContextForSplit(String str) {
        Context contextCreateContextForSplit;
        TraceEvent traceEventH0 = TraceEvent.H0("SplitChromeApplication.createContextForSplit", null);
        try {
            f(str);
            long jUptimeMillis = SystemClock.uptimeMillis();
            synchronized (BundleUtils.a) {
                contextCreateContextForSplit = super.createContextForSplit(str);
            }
            oj2.b0(SystemClock.uptimeMillis() - jUptimeMillis, "Android.IsolatedSplits.ContextCreateTime." + str);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return contextCreateContextForSplit;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatApplication
    public final void e(Context context) {
        SplitPreloader splitPreloader = new SplitPreloader(context);
        j = splitPreloader;
        a aVar = new a(context);
        BundleUtils.d("chrome");
        SplitPreloader.b bVar = splitPreloader.new b(aVar);
        bVar.e(d73.k);
        synchronized (splitPreloader.a) {
            splitPreloader.a.put("chrome", bVar);
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        Resources resources = this.i;
        return resources != null ? resources : getBaseContext().getResources();
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatApplication, android.app.Application
    public final void onCreate() {
        Context context;
        String processName;
        Log.i("cr_SplitChromeApp", "onCreate start");
        f("chrome");
        super.onCreate();
        try {
            context = xz.a;
            processName = Application.getProcessName();
        } catch (Exception unused) {
        }
        boolean zEquals = (TextUtils.isEmpty(processName) || context == null) ? false : context.getPackageName().equals(processName);
        if (zEquals) {
            if (no2.o == null) {
                synchronized (no2.class) {
                    if (no2.o == null) {
                        no2.o = new no2(this);
                    }
                }
            }
            if (fp2.c == null) {
                synchronized (fp2.class) {
                    if (fp2.c == null) {
                        fp2.c = new fp2(getApplicationContext());
                    }
                }
            }
            if (bo2.d == null) {
                synchronized (bo2.class) {
                    if (bo2.d == null) {
                        bo2.d = new bo2(this);
                    }
                }
            }
            no2 no2Var = no2.o;
            no2Var.f(new co2());
            no2Var.f(new cp2());
            no2Var.f(new ap2());
            no2Var.f(new fo2());
            no2Var.n = false;
            String string = fp2.c.a.getString("skin-name", "");
            int i = fp2.c.a.getInt("skin-strategy", -1);
            if (!TextUtils.isEmpty(string) && i != -1) {
                no2Var.h(string, i);
            }
            no2.o.h(oj2.m(), 1);
        }
        Log.i("cr_SplitChromeApp", "onCreate end");
    }
}
