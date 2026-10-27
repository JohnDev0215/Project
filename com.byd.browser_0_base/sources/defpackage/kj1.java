package defpackage;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.Display;
import defpackage.jf1;
import defpackage.tj1;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.display.DisplayAndroidManager;

/* JADX INFO: compiled from: MultiInstanceManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class kj1 implements hy1, w92, bx, hk1, tj1.a, v80, jf1.a {
    public static int s;
    public static Class t;
    public Boolean e;
    public jj1 f;
    public final Activity g;
    public final zp1<v13> h;
    public final tj1 i;
    public final q2 j;
    public final jf1 k;
    public int l;
    public boolean m;
    public a n;
    public boolean o;
    public boolean p;
    public int q;
    public boolean r;

    /* JADX INFO: compiled from: MultiInstanceManager.java */
    public class a implements DisplayManager.DisplayListener {
        public a() {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayAdded(int i) {
            if (kj1.c(kj1.this, i)) {
                kj1.t = null;
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayChanged(int i) {
            kj1 kj1Var = kj1.this;
            if (i == kj1Var.q || !kj1.c(kj1Var, i)) {
                return;
            }
            ArrayList arrayListD = c6.d(kj1Var.g);
            if (arrayListD.size() == 1 && ((Integer) arrayListD.get(0)).equals(Integer.valueOf(kj1Var.q))) {
                kj1Var.n();
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayRemoved(int i) {
            kj1 kj1Var = kj1.this;
            if (kj1.c(kj1Var, i)) {
                if (i == kj1Var.q) {
                    if (kj1Var.j.g() == 3) {
                        kj1Var.o = true;
                    }
                } else if (kj1Var.g() == null) {
                    kj1Var.n();
                }
            }
        }
    }

    public kj1(Activity activity, aq1 aq1Var, uj1 uj1Var, r2 r2Var, jf1 jf1Var) {
        this.g = activity;
        this.h = aq1Var;
        this.i = uj1Var;
        uj1Var.b.a(this);
        this.j = r2Var;
        r2Var.f(this);
        this.k = jf1Var;
        ((ChromeActivity) jf1Var).R0.add(this);
    }

    public static boolean c(kj1 kj1Var, int i) {
        Display display;
        if (i != 0) {
            DisplayManager displayManager = (DisplayManager) kj1Var.g.getSystemService("display");
            if (displayManager != null) {
                Display[] displays = displayManager.getDisplays();
                int length = displays.length;
                for (int i2 = 0; i2 < length; i2++) {
                    display = displays[i2];
                    if (display.getDisplayId() == i) {
                        break;
                    }
                }
                display = null;
                return display != null ? false : false;
            }
            display = null;
            if (display != null || (display.getFlags() & 8) == 0) {
            }
        } else {
            kj1Var.getClass();
        }
        return true;
    }

    @Override // defpackage.w92
    public final void E() {
        this.p = true;
    }

    @Override // defpackage.hk1
    public final void G0() {
        this.m = true;
        Activity activity = this.g;
        DisplayManager displayManager = (DisplayManager) activity.getSystemService("display");
        if (displayManager == null) {
            return;
        }
        this.q = DisplayAndroidManager.b(activity).getDisplayId();
        a aVar = new a();
        this.n = aVar;
        displayManager.registerDisplayListener(aVar, null);
    }

    @Override // jf1.a
    public boolean a(int i, boolean z) {
        a23 a23Var;
        Tab tabI;
        if (i != R$id.move_to_other_window_menu_id) {
            if (i != R$id.new_window_menu_id) {
                return false;
            }
            p("MobileMenuNewWindow");
            return true;
        }
        v13 v13Var = this.h.get();
        if (v13Var != null && (a23Var = v13Var.b) != null && (tabI = a23Var.i()) != null) {
            o(tabI);
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [jj1, org.chromium.base.ApplicationStatus$c] */
    @Override // tj1.a
    public final void b(boolean z) {
        if (k() && this.m && !z) {
            if (this.j.g() != 3) {
                this.e = Boolean.TRUE;
                return;
            }
            ChromeTabbedActivity chromeTabbedActivityG = g();
            if (chromeTabbedActivityG == null) {
                n();
                return;
            }
            jj1 jj1Var = this.f;
            if (jj1Var != null) {
                ApplicationStatus.i(jj1Var);
                this.f = null;
            }
            ?? r0 = new ApplicationStatus.c() { // from class: jj1
                @Override // org.chromium.base.ApplicationStatus.c
                public final void d(Activity activity, int i) {
                    kj1 kj1Var = this.e;
                    if (i != 4) {
                        kj1Var.getClass();
                        return;
                    }
                    jj1 jj1Var2 = kj1Var.f;
                    if (jj1Var2 != null) {
                        ApplicationStatus.i(jj1Var2);
                        kj1Var.f = null;
                    }
                    kj1Var.n();
                }
            };
            this.f = r0;
            ApplicationStatus.f(r0, chromeTabbedActivityG);
        }
    }

    public int e(int i, int i2, boolean z) {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    @Override // defpackage.hy1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f() {
        /*
            r3 = this;
            boolean r0 = r3.k()
            if (r0 == 0) goto L49
            tj1 r0 = r3.i
            uj1 r0 = (defpackage.uj1) r0
            r0.getClass()
            vj1 r1 = defpackage.vj1.h
            r1.getClass()
            android.app.Activity r2 = r0.a
            boolean r2 = defpackage.vj1.o(r2)
            if (r2 != 0) goto L2b
            r0.getClass()
            r1.getClass()
            android.app.Activity r0 = r0.a
            boolean r0 = defpackage.vj1.n(r0)
            if (r0 == 0) goto L29
            goto L2b
        L29:
            r0 = 0
            goto L2c
        L2b:
            r0 = 1
        L2c:
            if (r0 != 0) goto L3c
            java.lang.Boolean r1 = r3.e
            if (r1 == 0) goto L3c
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L3c
            r3.n()
            goto L45
        L3c:
            if (r0 != 0) goto L45
            java.lang.Boolean r0 = r3.e
            if (r0 != 0) goto L45
            r3.m()
        L45:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r3.e = r0
        L49:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kj1.f():void");
    }

    public final ChromeTabbedActivity g() {
        uj1 uj1Var = (uj1) this.i;
        uj1Var.getClass();
        Class<? extends Activity> clsI = vj1.h.i(uj1Var.a);
        for (Activity activity : ApplicationStatus.b()) {
            if (activity.getClass().equals(clsI) && ApplicationStatus.c(activity) == 3) {
                return (ChromeTabbedActivity) activity;
            }
        }
        return null;
    }

    @Override // defpackage.hy1
    public final void h() {
        jj1 jj1Var = this.f;
        if (jj1Var != null) {
            ApplicationStatus.i(jj1Var);
            this.f = null;
        }
    }

    public boolean k() {
        return !iv.e().g("disable-tab-merging");
    }

    public final void m() {
        if (k()) {
            uj1 uj1Var = (uj1) this.i;
            uj1Var.getClass();
            Class<? extends Activity> clsI = vj1.h.i(uj1Var.a);
            ActivityManager.AppTask appTask = null;
            for (ActivityManager.AppTask appTask2 : ((ActivityManager) this.g.getSystemService("activity")).getAppTasks()) {
                if (vj1.e(appTask2).equals(clsI.getName())) {
                    appTask = appTask2;
                }
            }
            if (appTask != null) {
                Iterator it = ApplicationStatus.b().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Activity activity = (Activity) it.next();
                    if (activity.getClass().equals(clsI)) {
                        ((ChromeTabbedActivity) activity).y3();
                        break;
                    }
                }
                appTask.finishAndRemoveTask();
            }
            s = this.l;
        }
    }

    public final void n() {
        if (!k() || this.r) {
            return;
        }
        m();
        n3.X0("Android.MergeState.Live");
        z23 z23Var = this.h.get().a;
        if (z23Var.x) {
            return;
        }
        q23 q23Var = z23Var.c;
        if (q23Var.f() || !z23Var.i.isEmpty()) {
            return;
        }
        z23Var.o = false;
        z23Var.p = false;
        z23Var.q = new SparseIntArray();
        z23Var.r = new SparseIntArray();
        try {
            for (String str : q23Var.j()) {
                oi2 oi2Var = z23Var.s;
                r23 r23Var = new r23(z23Var, str);
                r23Var.d(oi2Var);
                DataInputStream dataInputStreamG = r23Var.g();
                if (dataInputStreamG != null) {
                    z23Var.v.add(str);
                    q23Var.c(true);
                    z23.m(dataInputStreamG, new y23(z23Var, true, z23Var.d.r()), null);
                }
            }
        } catch (Exception e) {
            e.toString();
        }
        z23Var.k();
    }

    public void o(Tab tab) {
        Intent intent;
        uj1 uj1Var = (uj1) this.i;
        uj1Var.getClass();
        vj1 vj1Var = vj1.h;
        Activity activity = uj1Var.a;
        Class<? extends Activity> clsI = vj1Var.i(activity);
        if (clsI == null) {
            intent = null;
        } else {
            intent = new Intent(activity, clsI);
            vj1.s(intent, activity, clsI);
        }
        if (intent == null) {
            return;
        }
        s = 0;
        bb2 bb2VarB = bb2.b(tab);
        Bundle bundleJ = vj1.j(uj1Var.a);
        bb2VarB.c(intent, null);
        this.g.startActivity(intent, bundleJ);
        n3.X0("MobileMenuMoveToOtherWindow");
    }

    @Override // defpackage.bx
    public final void onConfigurationChanged(Configuration configuration) {
        if (this.o) {
            if (this.p) {
                t = this.g.getClass();
            } else {
                t = null;
                n();
            }
            this.o = false;
        }
    }

    @Override // defpackage.v80
    public void onDestroy() {
        a aVar;
        this.r = true;
        ((uj1) this.i).b.c(this);
        ((ChromeActivity) this.k).R0.remove(this);
        this.j.e(this);
        jj1 jj1Var = this.f;
        if (jj1Var != null) {
            ApplicationStatus.i(jj1Var);
            this.f = null;
        }
        DisplayManager displayManager = (DisplayManager) this.g.getSystemService("display");
        if (displayManager == null || (aVar = this.n) == null) {
            return;
        }
        displayManager.unregisterDisplayListener(aVar);
    }

    public void p(String str) {
        Intent intent;
        uj1 uj1Var = (uj1) this.i;
        uj1Var.getClass();
        vj1 vj1Var = vj1.h;
        Activity activity = uj1Var.a;
        Class<? extends Activity> clsI = vj1Var.i(activity);
        if (clsI == null) {
            intent = null;
        } else {
            Intent intent2 = new Intent(activity, clsI);
            vj1.s(intent2, activity, clsI);
            intent = intent2;
        }
        if (intent == null) {
            return;
        }
        intent.addFlags(268435456);
        intent.addFlags(4096);
        s = 0;
        this.g.startActivity(intent, vj1.j(uj1Var.a));
        n3.X0("MobileMenuNewWindow");
    }

    public void i(int i, int i2) {
    }
}
