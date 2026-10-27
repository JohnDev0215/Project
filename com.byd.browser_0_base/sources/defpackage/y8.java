package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import defpackage.al3;
import defpackage.xz;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.components.variations.firstrun.VariationsSeedBridge;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;

/* JADX INFO: compiled from: AsyncInitTaskRunner.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class y8 {
    public boolean a;
    public int b;

    /* JADX INFO: compiled from: AsyncInitTaskRunner.java */
    public class a implements Runnable {
        public final String e;
        public final String f = Integer.toString(4);
        public final String g = "stable";

        /* JADX INFO: renamed from: y8$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AsyncInitTaskRunner.java */
        public class RunnableC0172a implements Runnable {
            public RunnableC0172a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                y8.this.a(null);
            }
        }

        public a(String str) {
            this.e = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            al3 al3Var;
            Object obj = al3.b;
            synchronized (obj) {
                if (al3.c == null) {
                    al3.c = new al3();
                }
                al3Var = al3.c;
            }
            String str = this.e;
            String str2 = this.f;
            String str3 = this.g;
            al3Var.getClass();
            synchronized (obj) {
                SharedPreferences sharedPreferences = xz.a.a;
                if (!sharedPreferences.getBoolean("variations_initialized", false) && !VariationsSeedBridge.hasNativePref()) {
                    al3.b bVar = al3Var.a(str, str2, str3).b;
                    if (bVar != null) {
                        VariationsSeedBridge.setVariationsFirstRunSeed(bVar.e, bVar.a, bVar.b, bVar.c, bVar.d);
                    }
                    sharedPreferences.edit().putBoolean("variations_initialized", true).apply();
                }
            }
            PostTask.c(rh3.a, new RunnableC0172a());
        }
    }

    public final void a(p52 p52Var) {
        Object obj = ThreadUtils.a;
        int i = this.b;
        if (i < 0) {
            return;
        }
        if (p52Var != null) {
            ik1.this.a.q(p52Var);
            this.b = -1;
            return;
        }
        int i2 = i - 1;
        this.b = i2;
        if (i2 == 0) {
            if (CachedFeatureFlags.b("network_service_warm_up_enabled", false)) {
                Context context = xz.a;
                o90 o90Var = ChildProcessLauncherHelperImpl.m;
                LauncherThread.a(new vn(context, false));
            }
            if (this.a) {
                Context context2 = xz.a;
                o90 o90Var2 = ChildProcessLauncherHelperImpl.m;
                LauncherThread.a(new vn(context2, true));
            }
            Boolean bool = Boolean.TRUE;
            ik1 ik1Var = ik1.this;
            ik1Var.f = bool;
            ik1Var.a();
            this.b = -1;
        }
    }
}
