package androidx.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Trace;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.ComponentActivity;
import androidx.activity.result.IntentSenderRequest;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import androidx.lifecycle.g;
import androidx.lifecycle.k;
import androidx.savedstate.a;
import defpackage.an3;
import defpackage.az;
import defpackage.b71;
import defpackage.bn3;
import defpackage.cs1;
import defpackage.dp0;
import defpackage.ds1;
import defpackage.ep0;
import defpackage.es1;
import defpackage.fe2;
import defpackage.fs1;
import defpackage.g10;
import defpackage.ge2;
import defpackage.gf1;
import defpackage.hf1;
import defpackage.i21;
import defpackage.jp0;
import defpackage.k12;
import defpackage.kf1;
import defpackage.kp0;
import defpackage.ls1;
import defpackage.n2;
import defpackage.n3;
import defpackage.oj2;
import defpackage.sj1;
import defpackage.t2;
import defpackage.vi3;
import defpackage.wv;
import defpackage.wx;
import defpackage.x2;
import defpackage.xj1;
import defpackage.xq0;
import defpackage.yj;
import defpackage.yq0;
import defpackage.z7;
import defpackage.zr1;
import defpackage.zv;
import defpackage.zy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends androidx.core.app.ComponentActivity implements zy, bn3, androidx.lifecycle.c, ge2, zr1, x2, cs1, ls1, es1, fs1, gf1 {
    public static final /* synthetic */ int v = 0;
    public final hf1 g;
    public final fe2 i;
    public an3 j;
    public final OnBackPressedDispatcher k;
    public final e l;
    public final xq0 m;
    public final b n;
    public final CopyOnWriteArrayList<wx<Configuration>> o;
    public final CopyOnWriteArrayList<wx<Integer>> p;
    public final CopyOnWriteArrayList<wx<Intent>> q;
    public final CopyOnWriteArrayList<wx<sj1>> r;
    public final CopyOnWriteArrayList<wx<k12>> s;
    public boolean t;
    public boolean u;
    public final az f = new az();
    public final g h = new g(this);

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                ComponentActivity.super.onBackPressed();
            } catch (IllegalStateException e) {
                if (!TextUtils.equals(e.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                    throw e;
                }
            }
        }
    }

    public class b extends androidx.activity.result.a {
        public b() {
        }

        @Override // androidx.activity.result.a
        public final void b(int i, t2 t2Var, Object obj) {
            Bundle bundle;
            ComponentActivity componentActivity = ComponentActivity.this;
            t2.a aVarB = t2Var.b(componentActivity, obj);
            if (aVarB != null) {
                new Handler(Looper.getMainLooper()).post(new androidx.activity.a(this, i, aVarB));
                return;
            }
            Intent intentA = t2Var.a(componentActivity, obj);
            if (intentA.getExtras() != null && intentA.getExtras().getClassLoader() == null) {
                intentA.setExtrasClassLoader(componentActivity.getClassLoader());
            }
            if (intentA.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
                Bundle bundleExtra = intentA.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                intentA.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                bundle = bundleExtra;
            } else {
                bundle = null;
            }
            if ("androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(intentA.getAction())) {
                String[] stringArrayExtra = intentA.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                if (stringArrayExtra == null) {
                    stringArrayExtra = new String[0];
                }
                n2.e(i, componentActivity, stringArrayExtra);
                return;
            }
            if (!"androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(intentA.getAction())) {
                int i2 = n2.b;
                componentActivity.startActivityForResult(intentA, i, bundle);
                return;
            }
            IntentSenderRequest intentSenderRequest = (IntentSenderRequest) intentA.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
            try {
                IntentSender intentSender = intentSenderRequest.e;
                Intent intent = intentSenderRequest.f;
                int i3 = intentSenderRequest.g;
                int i4 = intentSenderRequest.h;
                int i5 = n2.b;
                componentActivity.startIntentSenderForResult(intentSender, i, intent, i3, i4, 0, bundle);
            } catch (IntentSender.SendIntentException e) {
                new Handler(Looper.getMainLooper()).post(new androidx.activity.b(this, i, e));
            }
        }
    }

    public static class c {
        public static OnBackInvokedDispatcher a(Activity activity) {
            return activity.getOnBackInvokedDispatcher();
        }
    }

    public static final class d {
        public an3 a;
    }

    public class e implements Executor, ViewTreeObserver.OnDrawListener, Runnable {
        public Runnable f;
        public final long e = SystemClock.uptimeMillis() + 10000;
        public boolean g = false;

        public e() {
        }

        public final void a(View view) {
            if (this.g) {
                return;
            }
            this.g = true;
            view.getViewTreeObserver().addOnDrawListener(this);
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            this.f = runnable;
            View decorView = ComponentActivity.this.getWindow().getDecorView();
            if (!this.g) {
                decorView.postOnAnimation(new wv(1, this));
            } else if (Looper.myLooper() == Looper.getMainLooper()) {
                decorView.invalidate();
            } else {
                decorView.postInvalidate();
            }
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public final void onDraw() {
            boolean z;
            Runnable runnable = this.f;
            if (runnable == null) {
                if (SystemClock.uptimeMillis() > this.e) {
                    this.g = false;
                    ComponentActivity.this.getWindow().getDecorView().post(this);
                    return;
                }
                return;
            }
            runnable.run();
            this.f = null;
            xq0 xq0Var = ComponentActivity.this.m;
            synchronized (xq0Var.b) {
                z = xq0Var.c;
            }
            if (z) {
                this.g = false;
                ComponentActivity.this.getWindow().getDecorView().post(this);
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            ComponentActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [xv] */
    public ComponentActivity() {
        int i = 0;
        this.g = new hf1(new wv(i, this));
        fe2 fe2Var = new fe2(this);
        this.i = fe2Var;
        this.k = new OnBackPressedDispatcher(new a());
        e eVar = new e();
        this.l = eVar;
        this.m = new xq0(eVar, new yq0() { // from class: xv
            @Override // defpackage.yq0
            public final Object a() {
                int i2 = ComponentActivity.v;
                this.e.reportFullyDrawn();
                return null;
            }
        });
        new AtomicInteger();
        this.n = new b();
        this.o = new CopyOnWriteArrayList<>();
        this.p = new CopyOnWriteArrayList<>();
        this.q = new CopyOnWriteArrayList<>();
        this.r = new CopyOnWriteArrayList<>();
        this.s = new CopyOnWriteArrayList<>();
        this.t = false;
        this.u = false;
        if (j1() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        j1().a(new f() { // from class: androidx.activity.ComponentActivity.3
            @Override // androidx.lifecycle.f
            public final void a(b71 b71Var, d.a aVar) {
                if (aVar == d.a.ON_STOP) {
                    Window window = ComponentActivity.this.getWindow();
                    View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                    if (viewPeekDecorView != null) {
                        viewPeekDecorView.cancelPendingInputEvents();
                    }
                }
            }
        });
        j1().a(new f() { // from class: androidx.activity.ComponentActivity.4
            @Override // androidx.lifecycle.f
            public final void a(b71 b71Var, d.a aVar) {
                if (aVar == d.a.ON_DESTROY) {
                    ComponentActivity.this.f.b = null;
                    if (ComponentActivity.this.isChangingConfigurations()) {
                        return;
                    }
                    ComponentActivity.this.h0().a();
                }
            }
        });
        j1().a(new f() { // from class: androidx.activity.ComponentActivity.5
            @Override // androidx.lifecycle.f
            public final void a(b71 b71Var, d.a aVar) {
                ComponentActivity componentActivity = ComponentActivity.this;
                if (componentActivity.j == null) {
                    d dVar = (d) componentActivity.getLastNonConfigurationInstance();
                    if (dVar != null) {
                        componentActivity.j = dVar.a;
                    }
                    if (componentActivity.j == null) {
                        componentActivity.j = new an3();
                    }
                }
                componentActivity.j1().b(this);
            }
        });
        fe2Var.a();
        k.a(this);
        fe2Var.b.b("android:support:activity-result", new a.b() { // from class: yv
            @Override // androidx.savedstate.a.b
            public final Bundle a() {
                int i2 = ComponentActivity.v;
                ComponentActivity componentActivity = this.a;
                componentActivity.getClass();
                Bundle bundle = new Bundle();
                ComponentActivity.b bVar = componentActivity.n;
                bVar.getClass();
                HashMap map = bVar.b;
                bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(map.values()));
                bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(map.keySet()));
                bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(bVar.d));
                bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) bVar.g.clone());
                return bundle;
            }
        });
        addOnContextAvailableListener(new zv(this, i));
    }

    private void v1() {
        z7.f0(getWindow().getDecorView(), this);
        View decorView = getWindow().getDecorView();
        i21.e(decorView, "<this>");
        decorView.setTag(androidx.lifecycle.viewmodel.R$id.view_tree_view_model_store_owner, this);
        n3.g1(getWindow().getDecorView(), this);
        View decorView2 = getWindow().getDecorView();
        i21.e(decorView2, "<this>");
        decorView2.setTag(R$id.view_tree_on_back_pressed_dispatcher_owner, this);
        View decorView3 = getWindow().getDecorView();
        i21.e(decorView3, "<this>");
        decorView3.setTag(R$id.report_drawn, this);
    }

    @Override // defpackage.gf1
    public final void A0(FragmentManager.c cVar) {
        hf1 hf1Var = this.g;
        hf1Var.b.add(cVar);
        hf1Var.a.run();
    }

    @Override // defpackage.ls1
    public final void D(dp0 dp0Var) {
        this.p.add(dp0Var);
    }

    @Override // defpackage.ls1
    public final void F0(dp0 dp0Var) {
        this.p.remove(dp0Var);
    }

    @Override // defpackage.cs1
    public final void H0(wx<Configuration> wxVar) {
        this.o.add(wxVar);
    }

    @Override // androidx.lifecycle.c
    public final g10 S() {
        xj1 xj1Var = new xj1();
        Application application = getApplication();
        LinkedHashMap linkedHashMap = xj1Var.a;
        if (application != null) {
            linkedHashMap.put(oj2.q, getApplication());
        }
        linkedHashMap.put(k.a, this);
        linkedHashMap.put(k.b, this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            linkedHashMap.put(k.c, getIntent().getExtras());
        }
        return xj1Var;
    }

    @Override // defpackage.gf1
    public final void U0(FragmentManager.c cVar) {
        hf1 hf1Var = this.g;
        hf1Var.b.remove(cVar);
        if (((hf1.a) hf1Var.c.remove(cVar)) != null) {
            throw null;
        }
        hf1Var.a.run();
    }

    @Override // defpackage.x2
    public final androidx.activity.result.a X() {
        return this.n;
    }

    @Override // defpackage.es1
    public final void Y(ep0 ep0Var) {
        this.r.add(ep0Var);
    }

    @Override // android.app.Activity
    public void addContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view, @SuppressLint({"UnknownNullness", "MissingNullability"}) ViewGroup.LayoutParams layoutParams) {
        v1();
        this.l.a(getWindow().getDecorView());
        super.addContentView(view, layoutParams);
    }

    @Override // defpackage.zy
    public final void addOnContextAvailableListener(ds1 ds1Var) {
        this.f.addOnContextAvailableListener(ds1Var);
    }

    @Override // defpackage.bn3
    public final an3 h0() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.j == null) {
            d dVar = (d) getLastNonConfigurationInstance();
            if (dVar != null) {
                this.j = dVar.a;
            }
            if (this.j == null) {
                this.j = new an3();
            }
        }
        return this.j;
    }

    @Override // androidx.core.app.ComponentActivity, defpackage.b71
    public g j1() {
        return this.h;
    }

    @Override // defpackage.fs1
    public final void l0(kp0 kp0Var) {
        this.s.remove(kp0Var);
    }

    @Override // defpackage.es1
    public final void o1(ep0 ep0Var) {
        this.r.remove(ep0Var);
    }

    @Override // android.app.Activity
    @Deprecated
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.n.a(i, i2, intent)) {
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    @Deprecated
    public void onBackPressed() {
        this.k.c();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Iterator<wx<Configuration>> it = this.o.iterator();
        while (it.hasNext()) {
            it.next().accept(configuration);
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.i.b(bundle);
        az azVar = this.f;
        azVar.getClass();
        azVar.b = this;
        Iterator it = azVar.a.iterator();
        while (it.hasNext()) {
            ((ds1) it.next()).a();
        }
        super.onCreate(bundle);
        int i = ReportFragment.f;
        ReportFragment.b.b(this);
        if (yj.a()) {
            OnBackPressedDispatcher onBackPressedDispatcher = this.k;
            OnBackInvokedDispatcher onBackInvokedDispatcherA = c.a(this);
            onBackPressedDispatcher.getClass();
            i21.e(onBackInvokedDispatcherA, "invoker");
            onBackPressedDispatcher.e = onBackInvokedDispatcherA;
            onBackPressedDispatcher.d();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return true;
        }
        super.onCreatePanelMenu(i, menu);
        MenuInflater menuInflater = getMenuInflater();
        Iterator<kf1> it = this.g.b.iterator();
        while (it.hasNext()) {
            it.next().c(menu, menuInflater);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i != 0) {
            return false;
        }
        Iterator<kf1> it = this.g.b.iterator();
        while (it.hasNext()) {
            if (it.next().a(menuItem)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        if (this.t) {
            return;
        }
        Iterator<wx<sj1>> it = this.r.iterator();
        while (it.hasNext()) {
            it.next().accept(new sj1(z));
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(@SuppressLint({"UnknownNullness", "MissingNullability"}) Intent intent) {
        super.onNewIntent(intent);
        Iterator<wx<Intent>> it = this.q.iterator();
        while (it.hasNext()) {
            it.next().accept(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        Iterator<kf1> it = this.g.b.iterator();
        while (it.hasNext()) {
            it.next().b(menu);
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z) {
        if (this.u) {
            return;
        }
        Iterator<wx<k12>> it = this.s.iterator();
        while (it.hasNext()) {
            it.next().accept(new k12(z));
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0) {
            return true;
        }
        super.onPreparePanel(i, view, menu);
        Iterator<kf1> it = this.g.b.iterator();
        while (it.hasNext()) {
            it.next().d(menu);
        }
        return true;
    }

    @Override // android.app.Activity
    @Deprecated
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.n.a(i, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        d dVar;
        an3 an3Var = this.j;
        if (an3Var == null && (dVar = (d) getLastNonConfigurationInstance()) != null) {
            an3Var = dVar.a;
        }
        if (an3Var == null) {
            return null;
        }
        d dVar2 = new d();
        dVar2.a = an3Var;
        return dVar2;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        g gVarJ1 = j1();
        if (gVarJ1 instanceof g) {
            gVarJ1.g();
        }
        super.onSaveInstanceState(bundle);
        this.i.c(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        Iterator<wx<Integer>> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().accept(Integer.valueOf(i));
        }
    }

    @Override // defpackage.zy
    public final void removeOnContextAvailableListener(ds1 ds1Var) {
        this.f.removeOnContextAvailableListener(ds1Var);
    }

    @Override // android.app.Activity
    public final void reportFullyDrawn() {
        try {
            if (Trace.isEnabled()) {
                Trace.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            xq0 xq0Var = this.m;
            synchronized (xq0Var.b) {
                xq0Var.c = true;
                Iterator it = xq0Var.d.iterator();
                while (it.hasNext()) {
                    ((yq0) it.next()).a();
                }
                xq0Var.d.clear();
                vi3 vi3Var = vi3.a;
            }
        } finally {
            Trace.endSection();
        }
    }

    @Override // defpackage.ge2
    public final androidx.savedstate.a s0() {
        return this.i.b;
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        v1();
        this.l.a(getWindow().getDecorView());
        super.setContentView(i);
    }

    @Override // android.app.Activity
    @Deprecated
    public final void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    @Deprecated
    public final void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Override // defpackage.fs1
    public final void t(kp0 kp0Var) {
        this.s.add(kp0Var);
    }

    @Override // defpackage.cs1
    public final void u(jp0 jp0Var) {
        this.o.remove(jp0Var);
    }

    @Override // defpackage.zr1
    public final OnBackPressedDispatcher v() {
        return this.k;
    }

    @Override // android.app.Activity
    @Deprecated
    public final void startActivityForResult(Intent intent, int i, Bundle bundle) {
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    @Deprecated
    public final void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        this.t = true;
        try {
            super.onMultiWindowModeChanged(z, configuration);
            this.t = false;
            Iterator<wx<sj1>> it = this.r.iterator();
            while (it.hasNext()) {
                it.next().accept(new sj1(z, 0));
            }
        } catch (Throwable th) {
            this.t = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        this.u = true;
        try {
            super.onPictureInPictureModeChanged(z, configuration);
            this.u = false;
            Iterator<wx<k12>> it = this.s.iterator();
            while (it.hasNext()) {
                it.next().accept(new k12(z, 0));
            }
        } catch (Throwable th) {
            this.u = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view) {
        v1();
        this.l.a(getWindow().getDecorView());
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view, @SuppressLint({"UnknownNullness", "MissingNullability"}) ViewGroup.LayoutParams layoutParams) {
        v1();
        this.l.a(getWindow().getDecorView());
        super.setContentView(view, layoutParams);
    }
}
