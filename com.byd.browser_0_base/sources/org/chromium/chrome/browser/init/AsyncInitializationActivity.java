package org.chromium.chrome.browser.init;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import defpackage.bq;
import defpackage.bx;
import defpackage.g3;
import defpackage.gn0;
import defpackage.hk1;
import defpackage.hy1;
import defpackage.ik1;
import defpackage.jp;
import defpackage.kr2;
import defpackage.ku2;
import defpackage.lt3;
import defpackage.n3;
import defpackage.nj;
import defpackage.nx0;
import defpackage.oj2;
import defpackage.p52;
import defpackage.ql0;
import defpackage.r2;
import defpackage.sp;
import defpackage.sz0;
import defpackage.tj1;
import defpackage.uj1;
import defpackage.v80;
import defpackage.vd2;
import defpackage.w92;
import defpackage.xz;
import defpackage.y2;
import defpackage.y70;
import defpackage.ya0;
import defpackage.zm0;
import defpackage.zo3;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.R$anim;
import org.chromium.chrome.browser.ChromeBaseAppCompatActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.searchwidget.SearchActivity;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.base.d;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public abstract class AsyncInitializationActivity extends ChromeBaseAppCompatActivity implements jp, nj {
    public static final /* synthetic */ int b0 = 0;
    public long N;
    public g3 O;
    public Bundle P;
    public boolean Q;
    public long R;
    public boolean S;
    public boolean T;
    public boolean U;
    public boolean V;
    public boolean X;
    public boolean Y;
    public Runnable Z;
    public boolean a0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final ik1 f68J = new ik1(this);
    public final r2 K = new r2(this);
    public final uj1 L = new uj1(this);
    public boolean W = true;
    public final Handler I = new Handler();
    public final d M = new d(new a(this));

    public class a extends org.chromium.ui.base.a {
        public a(Activity activity) {
            super(activity);
        }

        @Override // org.chromium.ui.base.c.a
        public final boolean a(String str) {
            return AsyncInitializationActivity.this.X1(str);
        }
    }

    public class b implements View.OnLayoutChangeListener {
        public b() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            int i9 = AsyncInitializationActivity.b0;
            AsyncInitializationActivity asyncInitializationActivity = AsyncInitializationActivity.this;
            WindowManager windowManager = asyncInitializationActivity.getWindowManager();
            if (windowManager == null || windowManager.getDefaultDisplay() == null) {
                return;
            }
            int i10 = asyncInitializationActivity.getResources().getConfiguration().orientation;
        }
    }

    public static int N1(Context context) {
        float fMin;
        float f;
        int i;
        ya0 ya0VarA = ya0.a(context);
        if (bq.A.a() && (i = Build.VERSION.SDK_INT) >= 30 && i < 33 && ya0VarA.c() != null) {
            context = ya0VarA.c();
        }
        if (Build.VERSION.SDK_INT >= 30) {
            Rect bounds = ((WindowManager) context.getSystemService("window")).getMaximumWindowMetrics().getBounds();
            fMin = Math.min(bounds.right - bounds.left, bounds.bottom - bounds.top);
            f = ya0VarA.d;
        } else {
            Point point = ya0VarA.c;
            int i2 = point.x;
            int i3 = point.y;
            if (i2 >= i3) {
                i2 = i3;
            }
            fMin = i2;
            f = ya0VarA.d;
        }
        return (int) ((fMin / f) + 0.5f);
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final boolean D1(Context context, Configuration configuration) {
        super.D1(context, configuration);
        configuration.smallestScreenWidthDp = N1(context);
        return true;
    }

    public void K() {
        WindowManager windowManager = getWindowManager();
        if (windowManager != null && windowManager.getDefaultDisplay() != null) {
            int i = getResources().getConfiguration().orientation;
        }
        findViewById(R.id.content).addOnLayoutChangeListener(new b());
        ik1 ik1Var = this.f68J;
        ik1Var.h = true;
        if (ik1Var.b) {
            ik1Var.b = false;
            ik1Var.c();
        }
        if (ik1Var.c) {
            ik1Var.c = false;
            if (ik1Var.h) {
                ik1Var.a.f();
            } else {
                ik1Var.c = true;
            }
        }
        r2 r2Var = this.K;
        r2Var.m = true;
        Iterator<hk1> it = r2Var.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((hk1) aVar.next()).G0();
            }
        }
    }

    public final void K1(int i) {
        super.onCreate(null);
        if (i == 1) {
            finish();
        } else {
            finishAndRemoveTask();
            overridePendingTransition(0, R$anim.no_anim);
        }
    }

    public g3 L1() {
        return null;
    }

    public void M1() {
        Iterator<sz0> it = this.K.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((sz0) aVar.next()).e0();
            }
        }
    }

    public long O1() {
        return this.N;
    }

    @Override // defpackage.jp
    public final Intent P() {
        return getIntent();
    }

    public View P1() {
        return findViewById(R.id.content);
    }

    @Override // defpackage.nj
    public final void Q(y70 y70Var) {
        boolean z = R1() && !this.T;
        this.Z = y70Var;
        if (z) {
            g2();
        }
        if (!this.X) {
            this.f68J.b(d2());
        }
        if (z) {
            return;
        }
        g2();
    }

    public final boolean R1() {
        return oj2.f0(this, this.S);
    }

    public boolean S1(Intent intent) {
        return true;
    }

    public int T1(Intent intent, Bundle bundle) {
        return 0;
    }

    public void V1() {
        TraceEvent.R("onFirstDrawComplete");
        ik1 ik1Var = this.f68J;
        ik1Var.g = true;
        ik1Var.a();
    }

    public void W1() {
        Runnable runnable = this.Z;
        if (runnable == null) {
            return;
        }
        runnable.run();
        this.Z = null;
    }

    public boolean X1(String str) {
        return false;
    }

    @Override // defpackage.jp
    public final void Z() {
        this.K.l = 1;
        sp.a().b(true, this);
    }

    public void a2() {
        new zm0(P1(), new ql0(9, this)).a(false);
    }

    public void b2() {
        this.S = DeviceFormFactor.a(this);
        this.T = org.chromium.base.library_loader.a.l.d();
        Iterator<sz0> it = this.K.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((sz0) aVar.next()).q();
            }
        }
    }

    public boolean c2(Intent intent) {
        return true;
    }

    @Override // defpackage.jp, defpackage.nj
    public final boolean d() {
        return this.Q || isFinishing();
    }

    public void d1() {
        try {
            TraceEvent.q("maybePreconnect", null);
            Intent intent = getIntent();
            if (intent != null && "android.intent.action.VIEW".equals(intent.getAction())) {
                String strO = org.chromium.chrome.browser.b.o(intent);
                if (strO == null) {
                    return;
                }
                if (!org.chromium.chrome.browser.b.q(intent.getExtras())) {
                    zo3.a().c(strO, Profile.d());
                }
            }
        } finally {
            TraceEvent.C("maybePreconnect");
        }
    }

    public boolean d2() {
        return !(zo3.a().e != null);
    }

    @Override // defpackage.nj
    public final void e1() {
        a2();
        M1();
        r2 r2Var = this.K;
        if (r2Var.d()) {
            return;
        }
        Iterator<sz0> it = r2Var.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((sz0) aVar.next()).Y0();
            }
        }
    }

    public boolean e2(Intent intent) {
        return false;
    }

    public void f() {
        r2 r2Var = this.K;
        r2Var.l = 3;
        Iterator<hy1> it = r2Var.c.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((hy1) aVar.next()).f();
            }
        }
    }

    public abstract void g2();

    public void h() {
        r2 r2Var = this.K;
        r2Var.l = 4;
        Iterator<hy1> it = r2Var.c.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((hy1) aVar.next()).h();
            }
        }
    }

    public void j() {
        r2 r2Var = this.K;
        r2Var.l = 2;
        Iterator<kr2> it = r2Var.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((kr2) aVar.next()).j();
            }
        }
    }

    public void l() {
        r2 r2Var = this.K;
        r2Var.l = 5;
        Iterator<kr2> it = r2Var.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((kr2) aVar.next()).l();
            }
        }
    }

    @Override // android.app.Activity
    public boolean moveTaskToBack(boolean z) {
        return super.moveTaskToBack(z);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    @SuppressLint({"MissingSuperCall"})
    public void onActivityResult(int i, int i2, Intent intent) {
        ik1 ik1Var = this.f68J;
        if (ik1Var.h) {
            ik1Var.a.x0(i, i2, intent);
            return;
        }
        if (ik1Var.e == null) {
            ik1Var.e = new ArrayList(1);
        }
        ik1Var.e.add(new ik1.b(i, i2, intent));
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a0) {
            this.a0 = false;
            getWindow().setWindowManager(getWindow().getWindowManager(), getWindow().getAttributes().token, getComponentName().flattenToString(), true);
        }
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Z1(configuration);
        Iterator<bx> it = this.K.i.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((bx) aVar.next()).onConfigurationChanged(configuration);
            }
        }
    }

    @Override // android.app.Activity
    public final void onContextMenuClosed(Menu menu) {
        g3 g3Var = this.O;
        if (g3Var == null) {
            return;
        }
        Iterator<WindowAndroid.d> it = g3Var.z.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((WindowAndroid.d) aVar.next()).a();
            }
        }
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"MissingSuperCall"})
    public final void onCreate(Bundle bundle) {
        boolean z = false;
        this.V = false;
        Log.i("cr_AsyncInitActivity", getClass().getSimpleName().concat("   onCreate start"));
        TraceEvent.q("AsyncInitializationActivity.onCreate()", null);
        Y1();
        Q1();
        Intent intent = getIntent();
        if ((intent.getFlags() & 1048576) != 0) {
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.setFlags(intent.getFlags());
            intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.setComponent(intent.getComponent());
            intent2.setPackage(intent.getPackage());
            intent = intent2;
        }
        setIntent(intent);
        int iT1 = T1(getIntent(), bundle);
        if (iT1 != 0) {
            K1(iT1);
        } else {
            Intent intent3 = getIntent();
            if (!S1(intent3)) {
                K1(2);
            } else if (c2(intent3) && gn0.c(this, intent3, e2(intent3))) {
                K1(1);
            } else {
                ku2 ku2VarT = ku2.t();
                try {
                    super.onCreate(f2(bundle));
                    ku2VarT.close();
                    this.N = SystemClock.elapsedRealtime();
                    this.P = bundle;
                    this.O = L1();
                    Bundle bundle2 = this.P;
                    d dVar = this.M;
                    dVar.getClass();
                    if (bundle2 != null) {
                        Serializable serializable = bundle2.getSerializable("window_callback_errors");
                        if (serializable instanceof HashMap) {
                            dVar.d = (HashMap) serializable;
                        }
                    }
                    this.X = this instanceof SearchActivity;
                    sp.a().c(this);
                    z = true;
                } catch (Throwable th) {
                    try {
                        ku2VarT.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            }
        }
        if (!z) {
            U1();
        }
        TraceEvent.C("AsyncInitializationActivity.onCreate()");
        Log.i("cr_AsyncInitActivity", getClass().getSimpleName().concat("   onCreate end"));
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Log.i("AsyncInitActivity", getClass().getSimpleName().concat("    onDestroy: "));
        this.Q = true;
        r2 r2Var = this.K;
        r2Var.n = true;
        g3 g3Var = this.O;
        if (g3Var != null) {
            g3Var.destroy();
            this.O = null;
        }
        super.onDestroy();
        r2Var.l = 6;
        org.chromium.base.b<v80> bVar = r2Var.e;
        Iterator<v80> it = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                r2Var.a.clear();
                r2Var.c.clear();
                r2Var.d.clear();
                r2Var.b.clear();
                r2Var.f.clear();
                r2Var.g.clear();
                r2Var.h.clear();
                r2Var.i.clear();
                bVar.clear();
                r2Var.j.clear();
                this.V = false;
                return;
            }
            ((v80) aVar.next()).onDestroy();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        Log.i("cr_AsyncInitActivity", "is MultiWindowMode: " + z);
        Iterator<tj1.a> it = this.L.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((tj1.a) aVar.next()).b(z);
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    @SuppressLint({"MissingSuperCall"})
    public void onNewIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        ik1 ik1Var = this.f68J;
        if (ik1Var.h) {
            ik1Var.a.n0(intent);
        } else {
            if (ik1Var.d == null) {
                ik1Var.d = new ArrayList(1);
            }
            ik1Var.d.add(intent);
        }
        setIntent(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        n3.t = true;
        ik1 ik1Var = this.f68J;
        ik1Var.c = false;
        if (ik1Var.h) {
            ik1Var.a.h();
        }
        super.onPause();
        this.V = false;
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        g3 g3Var = this.O;
        if (g3Var == null || !g3Var.h(i, strArr, iArr)) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.V = true;
        this.U = !this.W || this.T;
        this.W = false;
        if (n3.u) {
            n3.t = true;
        } else {
            n3.u = true;
        }
        ik1 ik1Var = this.f68J;
        if (ik1Var.h) {
            ik1Var.a.f();
        } else {
            ik1Var.c = true;
        }
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("window_callback_errors", this.M.d);
        Iterator<vd2> it = this.K.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((vd2) aVar.next()).c0(bundle);
            }
        }
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.V = false;
        ik1 ik1Var = this.f68J;
        if (ik1Var.h) {
            ik1Var.c();
        } else {
            ik1Var.b = true;
        }
        Intent intent = getIntent();
        if (gn0.a(intent, e2(intent)) && c2(intent)) {
            throw new IllegalStateException("The app has not completed the FRE yet " + getClass().getName() + " is trying to start.");
        }
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        ik1 ik1Var = this.f68J;
        ik1Var.b = false;
        if (ik1Var.h) {
            ik1Var.a.l();
        }
        this.V = false;
    }

    @Override // android.app.Activity
    public final void onUserInteraction() {
        this.R = SystemClock.elapsedRealtime();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Iterator<lt3> it = this.K.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((lt3) aVar.next()).onWindowFocusChanged(z);
            }
        }
    }

    @Override // defpackage.jp, defpackage.nj
    public final void q(Exception exc) {
        throw new p52(4, exc);
    }

    @Override // android.app.Activity
    public final void recreate() {
        super.recreate();
        Iterator<w92> it = this.K.j.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((w92) aVar.next()).E();
            }
        }
    }

    @Override // defpackage.nj
    public final void w0() {
        b2();
    }

    public boolean x0(int i, int i2, Intent intent) {
        boolean z;
        d dVar = this.M;
        SparseArray<WindowAndroid.c> sparseArray = dVar.a;
        WindowAndroid.c cVar = sparseArray.get(i);
        sparseArray.delete(i);
        String strRemove = dVar.d.remove(Integer.valueOf(i));
        if (cVar != null) {
            cVar.b(intent, i2);
            z = true;
        } else {
            if (strRemove != null && !dVar.c.a(strRemove)) {
                nx0<Activity> nx0Var = WindowAndroid.A;
                Toast.b(0, xz.a, strRemove).e();
            }
            z = false;
        }
        if (z) {
            return true;
        }
        Iterator<y2> it = this.K.h.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                super.onActivityResult(i, i2, intent);
                return false;
            }
            ((y2) aVar.next()).c1();
        }
    }

    public void G0() {
    }

    public void Q1() {
    }

    public void U1() {
    }

    public void Y1() {
    }

    public void g0() {
    }

    public void Z1(Configuration configuration) {
    }

    public Bundle f2(Bundle bundle) {
        return bundle;
    }

    public void n0(Intent intent) {
    }
}
