package defpackage;

import J.N;
import android.app.ActivityManager;
import android.os.Build;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.a;

/* JADX INFO: compiled from: ProcessInitializationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class b62 {
    public static final Object d = new Object();
    public static b62 e;
    public boolean a;
    public boolean b;
    public boolean c;

    public static void a(b62 b62Var) {
        b62Var.getClass();
        synchronized (d) {
            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
            if (!sharedPreferencesManager.readBoolean("snapshot_database_removed", false)) {
                xz.a.deleteDatabase("snapshots.db");
                sharedPreferencesManager.l("snapshot_database_removed", true);
            }
        }
    }

    public static b62 b() {
        ThreadUtils.b();
        if (e == null) {
            AppHooks.get().getClass();
            e = new b62();
        }
        return e;
    }

    public static void c() {
        if (!wj.b) {
            wj.b = true;
            d73 d73Var = sh3.a;
            PostTask.e.set(1, new wj());
        }
        AppHooks.get().getClass();
        a aVar = new a(new by2());
        AtomicReference<AccountManagerFacade> atomicReference = AccountManagerFacadeProvider.a;
        Object obj = ThreadUtils.a;
        if (AccountManagerFacadeProvider.b != null) {
            throw new IllegalStateException("AccountManagerFacade is already initialized!");
        }
        AccountManagerFacadeProvider.b = aVar;
        AccountManagerFacadeProvider.a.set(aVar);
        e(false);
    }

    public static void e(boolean z) {
        String str;
        if (Build.VERSION.SDK_INT >= 30) {
            ActivityManager activityManager = (ActivityManager) xz.a.getSystemService("activity");
            if (z) {
                str = "4.1.1.0," + N.MqpkjiSK();
            } else {
                str = "4.1.1.0";
            }
            activityManager.setProcessStateSummary(str.getBytes(StandardCharsets.UTF_8));
        }
    }

    public final void d() {
        TraceEvent traceEventH0 = TraceEvent.H0("ProcessInitializationHandler.initializePreNative()", null);
        try {
            ThreadUtils.b();
            if (this.a) {
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } else {
                c();
                this.a = true;
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
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
