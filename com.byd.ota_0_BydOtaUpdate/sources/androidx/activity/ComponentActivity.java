package androidx.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ReportFragment;
import androidx.savedstate.SavedStateRegistry;
import b.a.c;
import b.l.d;
import b.l.f;
import b.l.g;
import b.l.p;
import b.l.q;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends androidx.core.app.ComponentActivity implements f, q, b.o.b, c {
    public int F3;
    public p y;
    public final g s = new g(this);
    public final b.o.a x = b.o.a.a(this);
    public final OnBackPressedDispatcher E3 = new OnBackPressedDispatcher(new a());

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ComponentActivity.super.onBackPressed();
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f64a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public p f65b;
    }

    public ComponentActivity() {
        if (a() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 19) {
            a().a(new d() { // from class: androidx.activity.ComponentActivity.2
                @Override // b.l.d
                public void d(f fVar, Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_STOP) {
                        Window window = ComponentActivity.this.getWindow();
                        View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                        if (viewPeekDecorView != null) {
                            viewPeekDecorView.cancelPendingInputEvents();
                        }
                    }
                }
            });
        }
        a().a(new d() { // from class: androidx.activity.ComponentActivity.3
            @Override // b.l.d
            public void d(f fVar, Lifecycle.Event event) {
                if (event != Lifecycle.Event.ON_DESTROY || ComponentActivity.this.isChangingConfigurations()) {
                    return;
                }
                ComponentActivity.this.h().a();
            }
        });
        if (19 > i2 || i2 > 23) {
            return;
        }
        a().a(new ImmLeaksCleaner(this));
    }

    @Override // androidx.core.app.ComponentActivity, b.l.f
    public Lifecycle a() {
        return this.s;
    }

    @Override // b.a.c
    public final OnBackPressedDispatcher b() {
        return this.E3;
    }

    @Override // b.o.b
    public final SavedStateRegistry c() {
        return this.x.b();
    }

    @Override // b.l.q
    public p h() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.y == null) {
            b bVar = (b) getLastNonConfigurationInstance();
            if (bVar != null) {
                this.y = bVar.f65b;
            }
            if (this.y == null) {
                this.y = new p();
            }
        }
        return this.y;
    }

    @Deprecated
    public Object l() {
        return null;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.E3.c();
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.x.c(bundle);
        ReportFragment.e(this);
        int i2 = this.F3;
        if (i2 != 0) {
            setContentView(i2);
        }
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        b bVar;
        Object objL = l();
        p pVar = this.y;
        if (pVar == null && (bVar = (b) getLastNonConfigurationInstance()) != null) {
            pVar = bVar.f65b;
        }
        if (pVar == null && objL == null) {
            return null;
        }
        b bVar2 = new b();
        bVar2.f64a = objL;
        bVar2.f65b = pVar;
        return bVar2;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        Lifecycle lifecycleA = a();
        if (lifecycleA instanceof g) {
            ((g) lifecycleA).p(Lifecycle.State.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.x.d(bundle);
    }
}
