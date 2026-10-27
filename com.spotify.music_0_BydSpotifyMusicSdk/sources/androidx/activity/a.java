package androidx.activity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Trace;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.Lifecycle$Event;
import com.spotify.music.R;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import p.aq1;
import p.cz;
import p.ex0;
import p.fq1;
import p.he1;
import p.hx0;
import p.j32;
import p.kk0;
import p.l13;
import p.l91;
import p.lu2;
import p.mu2;
import p.mz;
import p.n11;
import p.no;
import p.nv;
import p.op1;
import p.oy0;
import p.p91;
import p.pv;
import p.py0;
import p.qp1;
import p.qv;
import p.rv;
import p.s91;
import p.sp1;
import p.sv;
import p.tl1;
import p.tv;
import p.u11;
import p.u4;
import p.ui1;
import p.uv;
import p.v03;
import p.v62;
import p.vv;
import p.w62;
import p.wv;
import p.x4;
import p.x62;
import p.xs1;
import p.yl1;
import p.zb0;
import p.zp1;
import p.zw0;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends wv implements mu2, n11, x62, op1, x4, qp1, fq1, zp1, aq1, ui1 {
    public final mz e;
    public final kk0 k;
    public final androidx.lifecycle.a l;
    public final w62 m;
    public lu2 n;
    public final b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final vv f3p;
    public final oy0 q;
    public final sv r;
    public final CopyOnWriteArrayList s;
    public final CopyOnWriteArrayList t;
    public final CopyOnWriteArrayList u;
    public final CopyOnWriteArrayList v;
    public final CopyOnWriteArrayList w;
    public boolean x;
    public boolean y;

    /* JADX WARN: Type inference failed for: r6v0, types: [p.ov] */
    public a() {
        this.c = new androidx.lifecycle.a(this);
        this.e = new mz();
        int i = 0;
        this.k = new kk0(new nv(i, this));
        androidx.lifecycle.a aVar = new androidx.lifecycle.a(this);
        this.l = aVar;
        w62 w62VarS = zb0.s(this);
        this.m = w62VarS;
        this.o = new b(new rv(i, this));
        final zw0 zw0Var = (zw0) this;
        vv vvVar = new vv(zw0Var);
        this.f3p = vvVar;
        this.q = new oy0(vvVar, new py0() { // from class: p.ov
            @Override // p.py0
            public final Object invoke() {
                zw0Var.reportFullyDrawn();
                return null;
            }
        });
        new AtomicInteger();
        this.r = new sv();
        this.s = new CopyOnWriteArrayList();
        this.t = new CopyOnWriteArrayList();
        this.u = new CopyOnWriteArrayList();
        this.v = new CopyOnWriteArrayList();
        this.w = new CopyOnWriteArrayList();
        this.x = false;
        this.y = false;
        aVar.a(new p91() { // from class: androidx.activity.ComponentActivity$3
            @Override // p.p91
            public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
                if (lifecycle$Event == Lifecycle$Event.ON_STOP) {
                    Window window = zw0Var.getWindow();
                    View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                    if (viewPeekDecorView != null) {
                        viewPeekDecorView.cancelPendingInputEvents();
                    }
                }
            }
        });
        aVar.a(new p91() { // from class: androidx.activity.ComponentActivity$4
            @Override // p.p91
            public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
                if (lifecycle$Event == Lifecycle$Event.ON_DESTROY) {
                    zw0Var.e.b = null;
                    if (!zw0Var.isChangingConfigurations()) {
                        zw0Var.j().a();
                    }
                    vv vvVar2 = zw0Var.f3p;
                    a aVar2 = vvVar2.l;
                    aVar2.getWindow().getDecorView().removeCallbacks(vvVar2);
                    aVar2.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(vvVar2);
                }
            }
        });
        aVar.a(new p91() { // from class: androidx.activity.ComponentActivity$5
            @Override // p.p91
            public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
                a aVar2 = zw0Var;
                if (aVar2.n == null) {
                    uv uvVar = (uv) aVar2.getLastNonConfigurationInstance();
                    if (uvVar != null) {
                        aVar2.n = uvVar.a;
                    }
                    if (aVar2.n == null) {
                        aVar2.n = new lu2();
                    }
                }
                aVar2.l.c(this);
            }
        });
        w62VarS.a();
        he1.p(this);
        w62VarS.b.b("android:support:activity-result", new pv(i, this));
        n(new qv(zw0Var, i));
    }

    @Override // p.x62
    public final v62 a() {
        return this.m.b;
    }

    @Override // p.n11
    public final yl1 g() {
        yl1 yl1Var = new yl1();
        Application application = getApplication();
        LinkedHashMap linkedHashMap = yl1Var.a;
        if (application != null) {
            linkedHashMap.put(u11.u, getApplication());
        }
        linkedHashMap.put(he1.e, this);
        linkedHashMap.put(he1.f, this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            linkedHashMap.put(he1.g, getIntent().getExtras());
        }
        return yl1Var;
    }

    @Override // p.mu2
    public final lu2 j() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.n == null) {
            uv uvVar = (uv) getLastNonConfigurationInstance();
            if (uvVar != null) {
                this.n = uvVar.a;
            }
            if (this.n == null) {
                this.n = new lu2();
            }
        }
        return this.n;
    }

    @Override // p.s91
    public final l91 l() {
        return this.l;
    }

    public final void n(sp1 sp1Var) {
        mz mzVar = this.e;
        mzVar.getClass();
        if (mzVar.b != null) {
            sp1Var.a();
        }
        mzVar.a.add(sp1Var);
    }

    public final void o(hx0 hx0Var) {
        kk0 kk0Var = this.k;
        ((CopyOnWriteArrayList) kk0Var.k).remove(hx0Var);
        u4.z(((Map) kk0Var.l).remove(hx0Var));
        ((Runnable) kk0Var.e).run();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.r.a(i, i2, intent)) {
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.o.b();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Iterator it = this.s.iterator();
        while (it.hasNext()) {
            ((cz) it.next()).accept(configuration);
        }
    }

    @Override // p.wv, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.m.b(bundle);
        mz mzVar = this.e;
        mzVar.getClass();
        mzVar.b = this;
        Iterator it = mzVar.a.iterator();
        while (it.hasNext()) {
            ((sp1) it.next()).a();
        }
        super.onCreate(bundle);
        int i = j32.e;
        u11.F(this);
        if (no.a()) {
            b bVar = this.o;
            OnBackInvokedDispatcher onBackInvokedDispatcherA = tv.a(this);
            bVar.getClass();
            v03.j(onBackInvokedDispatcherA, "invoker");
            bVar.e = onBackInvokedDispatcherA;
            bVar.c();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return true;
        }
        super.onCreatePanelMenu(i, menu);
        getMenuInflater();
        Iterator it = ((CopyOnWriteArrayList) this.k.k).iterator();
        while (it.hasNext()) {
            ((hx0) it.next()).a.j();
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.k.w();
        }
        return false;
    }

    @Override // android.app.Activity
    public final void onMultiWindowModeChanged(boolean z) {
        if (this.x) {
            return;
        }
        Iterator it = this.v.iterator();
        while (it.hasNext()) {
            ((cz) it.next()).accept(new tl1(z));
        }
    }

    @Override // android.app.Activity
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Iterator it = this.u.iterator();
        while (it.hasNext()) {
            ((cz) it.next()).accept(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        Iterator it = ((CopyOnWriteArrayList) this.k.k).iterator();
        while (it.hasNext()) {
            ((hx0) it.next()).a.p();
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z) {
        if (this.y) {
            return;
        }
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            ((cz) it.next()).accept(new xs1(z));
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0) {
            return true;
        }
        super.onPreparePanel(i, view, menu);
        Iterator it = ((CopyOnWriteArrayList) this.k.k).iterator();
        while (it.hasNext()) {
            ((hx0) it.next()).a.s();
        }
        return true;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.r.a(i, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        uv uvVar;
        lu2 lu2Var = this.n;
        if (lu2Var == null && (uvVar = (uv) getLastNonConfigurationInstance()) != null) {
            lu2Var = uvVar.a;
        }
        if (lu2Var == null) {
            return null;
        }
        uv uvVar2 = new uv();
        uvVar2.a = lu2Var;
        return uvVar2;
    }

    @Override // p.wv, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        androidx.lifecycle.a aVar = this.l;
        if (aVar instanceof androidx.lifecycle.a) {
            aVar.h();
        }
        super.onSaveInstanceState(bundle);
        this.m.c(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        super.onTrimMemory(i);
        Iterator it = this.t.iterator();
        while (it.hasNext()) {
            ((cz) it.next()).accept(Integer.valueOf(i));
        }
    }

    public final void p(ex0 ex0Var) {
        this.s.remove(ex0Var);
    }

    public final void q(ex0 ex0Var) {
        this.v.remove(ex0Var);
    }

    public final void r(ex0 ex0Var) {
        this.w.remove(ex0Var);
    }

    @Override // android.app.Activity
    public final void reportFullyDrawn() {
        try {
            if (he1.A()) {
                Trace.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            this.q.a();
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public final void s(ex0 ex0Var) {
        this.t.remove(ex0Var);
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view) {
        l13.l(getWindow().getDecorView(), this);
        View decorView = getWindow().getDecorView();
        v03.j(decorView, "<this>");
        decorView.setTag(R.id.view_tree_view_model_store_owner, this);
        l13.m(getWindow().getDecorView(), this);
        View decorView2 = getWindow().getDecorView();
        v03.j(decorView2, "<this>");
        decorView2.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, this);
        View decorView3 = getWindow().getDecorView();
        v03.j(decorView3, "<this>");
        decorView3.setTag(R.id.report_drawn, this);
        View decorView4 = getWindow().getDecorView();
        vv vvVar = this.f3p;
        if (!vvVar.k) {
            vvVar.k = true;
            decorView4.getViewTreeObserver().addOnDrawListener(vvVar);
        }
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public final void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public final void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Override // android.app.Activity
    public final void startActivityForResult(Intent intent, int i, Bundle bundle) {
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    public final void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    @Override // android.app.Activity
    public final void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        this.x = true;
        try {
            super.onMultiWindowModeChanged(z, configuration);
            this.x = false;
            Iterator it = this.v.iterator();
            while (it.hasNext()) {
                ((cz) it.next()).accept(new tl1(z, 0));
            }
        } catch (Throwable th) {
            this.x = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        this.y = true;
        try {
            super.onPictureInPictureModeChanged(z, configuration);
            this.y = false;
            Iterator it = this.w.iterator();
            while (it.hasNext()) {
                ((cz) it.next()).accept(new xs1(z, 0));
            }
        } catch (Throwable th) {
            this.y = false;
            throw th;
        }
    }
}
