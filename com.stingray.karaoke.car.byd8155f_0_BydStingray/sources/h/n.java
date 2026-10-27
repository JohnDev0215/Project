package h;

import A7.E;
import F0.C0233v0;
import F1.C0250i;
import F1.H;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Trace;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.lifecycle.A;
import androidx.lifecycle.C;
import androidx.lifecycle.EnumC0980s;
import androidx.lifecycle.EnumC0981t;
import androidx.lifecycle.InterfaceC0977o;
import androidx.lifecycle.InterfaceC0986y;
import androidx.lifecycle.U;
import androidx.lifecycle.W;
import androidx.lifecycle.a0;
import androidx.lifecycle.g0;
import androidx.lifecycle.h0;
import androidx.lifecycle.k0;
import androidx.lifecycle.l0;
import b2.AbstractC0992b;
import b2.C0993c;
import com.stingray.drivemodedetector.R;
import j.InterfaceC1532a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import r.C2257p;
import v1.C2535b;
import v3.AbstractC2547a;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public abstract class n extends Activity implements l0, InterfaceC0977o, t3.e, y, A {

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final /* synthetic */ int f15112E = 0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f15113A;
    public boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final m7.n f15114C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final m7.n f15115D;
    public final C l = new C(this);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final A4.i f15116m = new A4.i();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0250i f15117n = new C0250i(new RunnableC1332d(this, 0));
    public final t4.e o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public k0 f15118p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ViewTreeObserverOnDrawListenerC1338j f15119q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final m7.n f15120r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final AtomicInteger f15121s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final l f15122t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final CopyOnWriteArrayList f15123u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final CopyOnWriteArrayList f15124v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final CopyOnWriteArrayList f15125w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final CopyOnWriteArrayList f15126x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final CopyOnWriteArrayList f15127y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final CopyOnWriteArrayList f15128z;

    public n() {
        t4.e eVar = new t4.e(this);
        this.o = eVar;
        this.f15119q = new ViewTreeObserverOnDrawListenerC1338j(this);
        this.f15120r = h6.r.o(new m(this, 2));
        this.f15121s = new AtomicInteger();
        this.f15122t = new l(this);
        this.f15123u = new CopyOnWriteArrayList();
        this.f15124v = new CopyOnWriteArrayList();
        this.f15125w = new CopyOnWriteArrayList();
        this.f15126x = new CopyOnWriteArrayList();
        this.f15127y = new CopyOnWriteArrayList();
        this.f15128z = new CopyOnWriteArrayList();
        C c = this.l;
        if (c == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        final int i7 = 0;
        c.M0(new InterfaceC0986y(this) { // from class: h.e

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ n f15095m;

            {
                this.f15095m = this;
            }

            @Override // androidx.lifecycle.InterfaceC0986y
            public final void b(A a10, EnumC0980s enumC0980s) {
                Window window;
                View viewPeekDecorView;
                switch (i7) {
                    case 0:
                        n nVar = this.f15095m;
                        if (enumC0980s == EnumC0980s.ON_STOP && (window = nVar.getWindow()) != null && (viewPeekDecorView = window.peekDecorView()) != null) {
                            viewPeekDecorView.cancelPendingInputEvents();
                            break;
                        }
                        break;
                    default:
                        n nVar2 = this.f15095m;
                        if (enumC0980s == EnumC0980s.ON_DESTROY) {
                            nVar2.f15116m.f421m = null;
                            if (!nVar2.isChangingConfigurations()) {
                                nVar2.f().a();
                            }
                            ViewTreeObserverOnDrawListenerC1338j viewTreeObserverOnDrawListenerC1338j = nVar2.f15119q;
                            n nVar3 = viewTreeObserverOnDrawListenerC1338j.o;
                            nVar3.getWindow().getDecorView().removeCallbacks(viewTreeObserverOnDrawListenerC1338j);
                            nVar3.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(viewTreeObserverOnDrawListenerC1338j);
                        }
                        break;
                }
            }
        });
        final int i10 = 1;
        this.l.M0(new InterfaceC0986y(this) { // from class: h.e

            /* JADX INFO: renamed from: m, reason: collision with root package name */
            public final /* synthetic */ n f15095m;

            {
                this.f15095m = this;
            }

            @Override // androidx.lifecycle.InterfaceC0986y
            public final void b(A a10, EnumC0980s enumC0980s) {
                Window window;
                View viewPeekDecorView;
                switch (i10) {
                    case 0:
                        n nVar = this.f15095m;
                        if (enumC0980s == EnumC0980s.ON_STOP && (window = nVar.getWindow()) != null && (viewPeekDecorView = window.peekDecorView()) != null) {
                            viewPeekDecorView.cancelPendingInputEvents();
                            break;
                        }
                        break;
                    default:
                        n nVar2 = this.f15095m;
                        if (enumC0980s == EnumC0980s.ON_DESTROY) {
                            nVar2.f15116m.f421m = null;
                            if (!nVar2.isChangingConfigurations()) {
                                nVar2.f().a();
                            }
                            ViewTreeObserverOnDrawListenerC1338j viewTreeObserverOnDrawListenerC1338j = nVar2.f15119q;
                            n nVar3 = viewTreeObserverOnDrawListenerC1338j.o;
                            nVar3.getWindow().getDecorView().removeCallbacks(viewTreeObserverOnDrawListenerC1338j);
                            nVar3.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(viewTreeObserverOnDrawListenerC1338j);
                        }
                        break;
                }
            }
        });
        this.l.M0(new t3.b(this, 3));
        eVar.k();
        a0.e(this);
        ((C2257p) eVar.o).d("android:support:activity-result", new C0233v0(this, 2));
        i(new InterfaceC1532a() { // from class: h.f
            @Override // j.InterfaceC1532a
            public final void a(n nVar) {
                n nVar2 = this.f15096a;
                A7.m.f(nVar, "it");
                Bundle bundleB = ((C2257p) nVar2.o.o).b("android:support:activity-result");
                if (bundleB != null) {
                    l lVar = nVar2.f15122t;
                    lVar.getClass();
                    ArrayList<Integer> integerArrayList = bundleB.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
                    ArrayList<String> stringArrayList = bundleB.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
                    if (stringArrayList == null || integerArrayList == null) {
                        return;
                    }
                    ArrayList<String> stringArrayList2 = bundleB.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
                    if (stringArrayList2 != null) {
                        lVar.d.addAll(stringArrayList2);
                    }
                    Bundle bundle = bundleB.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
                    Bundle bundle2 = lVar.f15108g;
                    if (bundle != null) {
                        bundle2.putAll(bundle);
                    }
                    int size = stringArrayList.size();
                    for (int i11 = 0; i11 < size; i11++) {
                        String str = stringArrayList.get(i11);
                        LinkedHashMap linkedHashMap = lVar.f15105b;
                        boolean zContainsKey = linkedHashMap.containsKey(str);
                        LinkedHashMap linkedHashMap2 = lVar.f15104a;
                        if (zContainsKey) {
                            Integer num = (Integer) linkedHashMap.remove(str);
                            if (!bundle2.containsKey(str)) {
                                E.c(linkedHashMap2).remove(num);
                            }
                        }
                        Integer num2 = integerArrayList.get(i11);
                        A7.m.e(num2, "rcs[i]");
                        int iIntValue = num2.intValue();
                        String str2 = stringArrayList.get(i11);
                        A7.m.e(str2, "keys[i]");
                        String str3 = str2;
                        linkedHashMap2.put(Integer.valueOf(iIntValue), str3);
                        linkedHashMap.put(str3, Integer.valueOf(iIntValue));
                    }
                }
            }
        });
        this.f15114C = h6.r.o(new m(this, 0));
        this.f15115D = h6.r.o(new m(this, 3));
    }

    @Override // h.y
    public final x a() {
        return (x) this.f15115D.getValue();
    }

    @Override // android.app.Activity
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        j();
        View decorView = getWindow().getDecorView();
        A7.m.e(decorView, "window.decorView");
        this.f15119q.a(decorView);
        super.addContentView(view, layoutParams);
    }

    @Override // t3.e
    public final C2257p b() {
        return (C2257p) this.o.o;
    }

    public h0 d() {
        return (h0) this.f15114C.getValue();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        A7.m.f(keyEvent, "event");
        A7.m.e(getWindow().getDecorView(), "window.decorView");
        int[] iArr = H.f2303a;
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        A7.m.f(keyEvent, "event");
        A7.m.e(getWindow().getDecorView(), "window.decorView");
        int[] iArr = H.f2303a;
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // androidx.lifecycle.InterfaceC0977o
    public final AbstractC0992b e() {
        C0993c c0993c = new C0993c();
        Application application = getApplication();
        LinkedHashMap linkedHashMap = c0993c.f12203a;
        if (application != null) {
            C8.d dVar = g0.d;
            Application application2 = getApplication();
            A7.m.e(application2, "application");
            linkedHashMap.put(dVar, application2);
        }
        linkedHashMap.put(a0.f11964a, this);
        linkedHashMap.put(a0.f11965b, this);
        Intent intent = getIntent();
        Bundle extras = intent != null ? intent.getExtras() : null;
        if (extras != null) {
            linkedHashMap.put(a0.c, extras);
        }
        return c0993c;
    }

    @Override // androidx.lifecycle.l0
    public final k0 f() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.f15118p == null) {
            C1337i c1337i = (C1337i) getLastNonConfigurationInstance();
            if (c1337i != null) {
                this.f15118p = c1337i.f15099a;
            }
            if (this.f15118p == null) {
                this.f15118p = new k0();
            }
        }
        k0 k0Var = this.f15118p;
        A7.m.c(k0Var);
        return k0Var;
    }

    @Override // androidx.lifecycle.A
    public final A9.b g() {
        return this.l;
    }

    public final void i(InterfaceC1532a interfaceC1532a) {
        A4.i iVar = this.f15116m;
        iVar.getClass();
        n nVar = (n) iVar.f421m;
        if (nVar != null) {
            interfaceC1532a.a(nVar);
        }
        ((CopyOnWriteArraySet) iVar.l).add(interfaceC1532a);
    }

    public final void j() {
        View decorView = getWindow().getDecorView();
        A7.m.e(decorView, "window.decorView");
        a0.j(decorView, this);
        View decorView2 = getWindow().getDecorView();
        A7.m.e(decorView2, "window.decorView");
        a0.k(decorView2, this);
        View decorView3 = getWindow().getDecorView();
        A7.m.e(decorView3, "window.decorView");
        h6.q.z(decorView3, this);
        View decorView4 = getWindow().getDecorView();
        A7.m.e(decorView4, "window.decorView");
        decorView4.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, this);
        View decorView5 = getWindow().getDecorView();
        A7.m.e(decorView5, "window.decorView");
        decorView5.setTag(R.id.report_drawn, this);
    }

    public final void k(Bundle bundle) {
        super.onCreate(bundle);
        int i7 = W.l;
        U.b(this);
    }

    public final void l(Bundle bundle) {
        A7.m.f(bundle, "outState");
        this.l.e1(EnumC0981t.f12006n);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    public final void onActivityResult(int i7, int i10, Intent intent) {
        if (this.f15122t.a(i7, i10, intent)) {
            return;
        }
        super.onActivityResult(i7, i10, intent);
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        a().c();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        A7.m.f(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        Iterator it = this.f15123u.iterator();
        while (it.hasNext()) {
            ((E1.a) it.next()).accept(configuration);
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        this.o.l(bundle);
        A4.i iVar = this.f15116m;
        iVar.getClass();
        iVar.f421m = this;
        Iterator it = ((CopyOnWriteArraySet) iVar.l).iterator();
        while (it.hasNext()) {
            ((InterfaceC1532a) it.next()).a(this);
        }
        k(bundle);
        int i7 = W.l;
        U.b(this);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i7, Menu menu) {
        A7.m.f(menu, "menu");
        if (i7 != 0) {
            return true;
        }
        super.onCreatePanelMenu(i7, menu);
        getMenuInflater();
        Iterator it = this.f15117n.f2338a.iterator();
        if (!it.hasNext()) {
            return true;
        }
        ((X1.i) it.next()).getClass();
        throw null;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i7, MenuItem menuItem) {
        A7.m.f(menuItem, "item");
        if (super.onMenuItemSelected(i7, menuItem)) {
            return true;
        }
        if (i7 == 0) {
            Iterator it = this.f15117n.f2338a.iterator();
            if (it.hasNext()) {
                ((X1.i) it.next()).getClass();
                throw null;
            }
        }
        return false;
    }

    @Override // android.app.Activity
    public final void onMultiWindowModeChanged(boolean z6) {
        if (this.f15113A) {
            return;
        }
        Iterator it = this.f15126x.iterator();
        while (it.hasNext()) {
            ((E1.a) it.next()).accept(new C2535b(z6));
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        A7.m.f(intent, "intent");
        super.onNewIntent(intent);
        Iterator it = this.f15125w.iterator();
        while (it.hasNext()) {
            ((E1.a) it.next()).accept(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onPanelClosed(int i7, Menu menu) {
        A7.m.f(menu, "menu");
        Iterator it = this.f15117n.f2338a.iterator();
        if (it.hasNext()) {
            ((X1.i) it.next()).getClass();
            throw null;
        }
        super.onPanelClosed(i7, menu);
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z6) {
        if (this.B) {
            return;
        }
        Iterator it = this.f15127y.iterator();
        while (it.hasNext()) {
            ((E1.a) it.next()).accept(new v1.k(z6));
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onPreparePanel(int i7, View view, Menu menu) {
        A7.m.f(menu, "menu");
        if (i7 != 0) {
            return true;
        }
        super.onPreparePanel(i7, view, menu);
        Iterator it = this.f15117n.f2338a.iterator();
        if (!it.hasNext()) {
            return true;
        }
        ((X1.i) it.next()).getClass();
        throw null;
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i7, String[] strArr, int[] iArr) {
        A7.m.f(strArr, "permissions");
        A7.m.f(iArr, "grantResults");
        if (this.f15122t.a(i7, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i7, strArr, iArr);
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        C1337i c1337i;
        k0 k0Var = this.f15118p;
        if (k0Var == null && (c1337i = (C1337i) getLastNonConfigurationInstance()) != null) {
            k0Var = c1337i.f15099a;
        }
        if (k0Var == null) {
            return null;
        }
        C1337i c1337i2 = new C1337i();
        c1337i2.f15099a = k0Var;
        return c1337i2;
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        A7.m.f(bundle, "outState");
        C c = this.l;
        if (c != null) {
            c.e1(EnumC0981t.f12006n);
        }
        l(bundle);
        this.o.m(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public final void onTrimMemory(int i7) {
        super.onTrimMemory(i7);
        Iterator it = this.f15124v.iterator();
        while (it.hasNext()) {
            ((E1.a) it.next()).accept(Integer.valueOf(i7));
        }
    }

    @Override // android.app.Activity
    public final void onUserLeaveHint() {
        super.onUserLeaveHint();
        Iterator it = this.f15128z.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }

    @Override // android.app.Activity
    public final void reportFullyDrawn() {
        try {
            if (AbstractC2547a.a()) {
                h6.e.f("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            o oVar = (o) this.f15120r.getValue();
            synchronized (oVar.f15130b) {
                try {
                    oVar.c = true;
                    Iterator it = oVar.d.iterator();
                    while (it.hasNext()) {
                        ((InterfaceC2772a) it.next()).a();
                    }
                    oVar.d.clear();
                } finally {
                }
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // android.app.Activity
    public final void setContentView(int i7) {
        j();
        View decorView = getWindow().getDecorView();
        A7.m.e(decorView, "window.decorView");
        this.f15119q.a(decorView);
        super.setContentView(i7);
    }

    @Override // android.app.Activity
    public final void startActivityForResult(Intent intent, int i7) {
        A7.m.f(intent, "intent");
        super.startActivityForResult(intent, i7);
    }

    @Override // android.app.Activity
    public final void startIntentSenderForResult(IntentSender intentSender, int i7, Intent intent, int i10, int i11, int i12) throws IntentSender.SendIntentException {
        A7.m.f(intentSender, "intent");
        super.startIntentSenderForResult(intentSender, i7, intent, i10, i11, i12);
    }

    @Override // android.app.Activity
    public final void startActivityForResult(Intent intent, int i7, Bundle bundle) {
        A7.m.f(intent, "intent");
        super.startActivityForResult(intent, i7, bundle);
    }

    @Override // android.app.Activity
    public final void startIntentSenderForResult(IntentSender intentSender, int i7, Intent intent, int i10, int i11, int i12, Bundle bundle) throws IntentSender.SendIntentException {
        A7.m.f(intentSender, "intent");
        super.startIntentSenderForResult(intentSender, i7, intent, i10, i11, i12, bundle);
    }

    @Override // android.app.Activity
    public final void onMultiWindowModeChanged(boolean z6, Configuration configuration) {
        A7.m.f(configuration, "newConfig");
        this.f15113A = true;
        try {
            super.onMultiWindowModeChanged(z6, configuration);
            this.f15113A = false;
            Iterator it = this.f15126x.iterator();
            while (it.hasNext()) {
                ((E1.a) it.next()).accept(new C2535b(z6));
            }
        } catch (Throwable th) {
            this.f15113A = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z6, Configuration configuration) {
        A7.m.f(configuration, "newConfig");
        this.B = true;
        try {
            super.onPictureInPictureModeChanged(z6, configuration);
            this.B = false;
            Iterator it = this.f15127y.iterator();
            while (it.hasNext()) {
                ((E1.a) it.next()).accept(new v1.k(z6));
            }
        } catch (Throwable th) {
            this.B = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        j();
        View decorView = getWindow().getDecorView();
        A7.m.e(decorView, "window.decorView");
        this.f15119q.a(decorView);
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        j();
        View decorView = getWindow().getDecorView();
        A7.m.e(decorView, "window.decorView");
        this.f15119q.a(decorView);
        super.setContentView(view, layoutParams);
    }
}
