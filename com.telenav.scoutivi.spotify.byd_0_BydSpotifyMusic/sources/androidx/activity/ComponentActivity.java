package androidx.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.IntentSenderRequest;
import androidx.lifecycle.b0;
import androidx.lifecycle.d0;
import androidx.lifecycle.e0;
import androidx.lifecycle.f0;
import androidx.lifecycle.g0;
import androidx.lifecycle.h;
import androidx.lifecycle.i;
import androidx.lifecycle.m;
import androidx.lifecycle.o;
import androidx.lifecycle.p;
import androidx.lifecycle.x;
import androidx.lifecycle.z;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.d;
import d.a;
import java.util.concurrent.atomic.AtomicInteger;
import w.f;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends f implements e0, h, androidx.savedstate.c, androidx.activity.c, androidx.activity.result.c {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private d0 f294k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private b0.b f295l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f297n;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final c.a f291h = new c.a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final p f292i = new p(this);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final androidx.savedstate.b f293j = androidx.savedstate.b.a(this);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final OnBackPressedDispatcher f296m = new OnBackPressedDispatcher(new a());

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final AtomicInteger f298o = new AtomicInteger();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final ActivityResultRegistry f299p = new b();

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ComponentActivity.super.onBackPressed();
            } catch (IllegalStateException e7) {
                if (!TextUtils.equals(e7.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                    throw e7;
                }
            }
        }
    }

    class b extends ActivityResultRegistry {

        class a implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ int f305e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ a.C0061a f306g;

            a(int i7, a.C0061a c0061a) {
                this.f305e = i7;
                this.f306g = c0061a;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.c(this.f305e, this.f306g.a());
            }
        }

        /* JADX INFO: renamed from: androidx.activity.ComponentActivity$b$b, reason: collision with other inner class name */
        class RunnableC0011b implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ int f308e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ IntentSender.SendIntentException f309g;

            RunnableC0011b(int i7, IntentSender.SendIntentException sendIntentException) {
                this.f308e = i7;
                this.f309g = sendIntentException;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.b(this.f308e, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", this.f309g));
            }
        }

        b() {
        }

        @Override // androidx.activity.result.ActivityResultRegistry
        public <I, O> void f(int i7, d.a<I, O> aVar, I i8, w.c cVar) {
            ComponentActivity componentActivity = ComponentActivity.this;
            a.C0061a<O> c0061aB = aVar.b(componentActivity, i8);
            if (c0061aB != null) {
                new Handler(Looper.getMainLooper()).post(new a(i7, c0061aB));
                return;
            }
            Intent intentA = aVar.a(componentActivity, i8);
            Bundle bundleExtra = null;
            if (intentA.getExtras() != null && intentA.getExtras().getClassLoader() == null) {
                intentA.setExtrasClassLoader(componentActivity.getClassLoader());
            }
            if (intentA.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
                bundleExtra = intentA.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                intentA.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
            }
            Bundle bundle = bundleExtra;
            if ("androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(intentA.getAction())) {
                String[] stringArrayExtra = intentA.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                if (stringArrayExtra == null) {
                    stringArrayExtra = new String[0];
                }
                w.a.g(componentActivity, stringArrayExtra, i7);
                return;
            }
            if (!"androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(intentA.getAction())) {
                w.a.h(componentActivity, intentA, i7, bundle);
                return;
            }
            IntentSenderRequest intentSenderRequest = (IntentSenderRequest) intentA.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
            try {
                w.a.i(componentActivity, intentSenderRequest.j(), i7, intentSenderRequest.b(), intentSenderRequest.c(), intentSenderRequest.h(), 0, bundle);
            } catch (IntentSender.SendIntentException e7) {
                new Handler(Looper.getMainLooper()).post(new RunnableC0011b(i7, e7));
            }
        }
    }

    static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Object f311a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        d0 f312b;

        c() {
        }
    }

    public ComponentActivity() {
        if (a() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        a().a(new m() { // from class: androidx.activity.ComponentActivity.3
            @Override // androidx.lifecycle.m
            public void d(o oVar, i.b bVar) {
                if (bVar == i.b.ON_STOP) {
                    Window window = ComponentActivity.this.getWindow();
                    View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                    if (viewPeekDecorView != null) {
                        viewPeekDecorView.cancelPendingInputEvents();
                    }
                }
            }
        });
        a().a(new m() { // from class: androidx.activity.ComponentActivity.4
            @Override // androidx.lifecycle.m
            public void d(o oVar, i.b bVar) {
                if (bVar == i.b.ON_DESTROY) {
                    ComponentActivity.this.f291h.b();
                    if (ComponentActivity.this.isChangingConfigurations()) {
                        return;
                    }
                    ComponentActivity.this.k().a();
                }
            }
        });
        a().a(new m() { // from class: androidx.activity.ComponentActivity.5
            @Override // androidx.lifecycle.m
            public void d(o oVar, i.b bVar) {
                ComponentActivity.this.p();
                ComponentActivity.this.a().c(this);
            }
        });
    }

    private void q() {
        f0.a(getWindow().getDecorView(), this);
        g0.a(getWindow().getDecorView(), this);
        d.a(getWindow().getDecorView(), this);
    }

    @Override // w.f, androidx.lifecycle.o
    public i a() {
        return this.f292i;
    }

    @Override // android.app.Activity
    public void addContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view, @SuppressLint({"UnknownNullness", "MissingNullability"}) ViewGroup.LayoutParams layoutParams) {
        q();
        super.addContentView(view, layoutParams);
    }

    @Override // androidx.activity.c
    public final OnBackPressedDispatcher c() {
        return this.f296m;
    }

    @Override // androidx.savedstate.c
    public final SavedStateRegistry d() {
        return this.f293j.b();
    }

    @Override // androidx.activity.result.c
    public final ActivityResultRegistry g() {
        return this.f299p;
    }

    @Override // androidx.lifecycle.e0
    public d0 k() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        p();
        return this.f294k;
    }

    @Override // androidx.lifecycle.h
    public b0.b m() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.f295l == null) {
            this.f295l = new z(getApplication(), this, getIntent() != null ? getIntent().getExtras() : null);
        }
        return this.f295l;
    }

    public final void o(c.b bVar) {
        this.f291h.a(bVar);
    }

    @Override // android.app.Activity
    @Deprecated
    protected void onActivityResult(int i7, int i8, Intent intent) {
        if (this.f299p.b(i7, i8, intent)) {
            return;
        }
        super.onActivityResult(i7, i8, intent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.f296m.d();
    }

    @Override // w.f, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.f293j.c(bundle);
        this.f291h.c(this);
        super.onCreate(bundle);
        this.f299p.g(bundle);
        x.g(this);
        int i7 = this.f297n;
        if (i7 != 0) {
            setContentView(i7);
        }
    }

    @Override // android.app.Activity
    @Deprecated
    public void onRequestPermissionsResult(int i7, String[] strArr, int[] iArr) {
        if (this.f299p.b(i7, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i7, strArr, iArr);
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        c cVar;
        Object objR = r();
        d0 d0Var = this.f294k;
        if (d0Var == null && (cVar = (c) getLastNonConfigurationInstance()) != null) {
            d0Var = cVar.f312b;
        }
        if (d0Var == null && objR == null) {
            return null;
        }
        c cVar2 = new c();
        cVar2.f311a = objR;
        cVar2.f312b = d0Var;
        return cVar2;
    }

    @Override // w.f, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        i iVarA = a();
        if (iVarA instanceof p) {
            ((p) iVarA).o(i.c.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.f293j.d(bundle);
        this.f299p.h(bundle);
    }

    void p() {
        if (this.f294k == null) {
            c cVar = (c) getLastNonConfigurationInstance();
            if (cVar != null) {
                this.f294k = cVar.f312b;
            }
            if (this.f294k == null) {
                this.f294k = new d0();
            }
        }
    }

    @Deprecated
    public Object r() {
        return null;
    }

    @Override // android.app.Activity
    public void reportFullyDrawn() {
        try {
            if (d1.a.d()) {
                d1.a.a("reportFullyDrawn() for " + getComponentName());
            }
            super.reportFullyDrawn();
        } finally {
            d1.a.b();
        }
    }

    public final <I, O> androidx.activity.result.b<I> s(d.a<I, O> aVar, androidx.activity.result.a<O> aVar2) {
        return t(aVar, this.f299p, aVar2);
    }

    @Override // android.app.Activity
    public void setContentView(int i7) {
        q();
        super.setContentView(i7);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i7) {
        super.startActivityForResult(intent, i7);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i7, Intent intent, int i8, int i9, int i10) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i7, intent, i8, i9, i10);
    }

    public final <I, O> androidx.activity.result.b<I> t(d.a<I, O> aVar, ActivityResultRegistry activityResultRegistry, androidx.activity.result.a<O> aVar2) {
        return activityResultRegistry.i("activity_rq#" + this.f298o.getAndIncrement(), this, aVar, aVar2);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i7, Bundle bundle) {
        super.startActivityForResult(intent, i7, bundle);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i7, Intent intent, int i8, int i9, int i10, Bundle bundle) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i7, intent, i8, i9, i10, bundle);
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view) {
        q();
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view, @SuppressLint({"UnknownNullness", "MissingNullability"}) ViewGroup.LayoutParams layoutParams) {
        q();
        super.setContentView(view, layoutParams);
    }
}
