package androidx.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import androidx.lifecycle.g;
import androidx.lifecycle.p;
import androidx.lifecycle.q;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends androidx.core.app.ComponentActivity implements f, q, androidx.savedstate.b, c {
    private p f;
    private int h;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final g f588d = new g(this);
    private final androidx.savedstate.a e = androidx.savedstate.a.a(this);
    private final OnBackPressedDispatcher g = new OnBackPressedDispatcher(new a());

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ComponentActivity.super.onBackPressed();
        }
    }

    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Object f592a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        p f593b;

        b() {
        }
    }

    public ComponentActivity() {
        if (a() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            a().a(new d() { // from class: androidx.activity.ComponentActivity.2
                @Override // androidx.lifecycle.d
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
            @Override // androidx.lifecycle.d
            public void d(f fVar, Lifecycle.Event event) {
                if (event != Lifecycle.Event.ON_DESTROY || ComponentActivity.this.isChangingConfigurations()) {
                    return;
                }
                ComponentActivity.this.i().a();
            }
        });
        if (19 > i || i > 23) {
            return;
        }
        a().a(new ImmLeaksCleaner(this));
    }

    @Override // androidx.core.app.ComponentActivity, androidx.lifecycle.f
    public Lifecycle a() {
        return this.f588d;
    }

    @Override // androidx.activity.c
    public final OnBackPressedDispatcher c() {
        return this.g;
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry d() {
        return this.e.b();
    }

    @Override // androidx.lifecycle.q
    public p i() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.f == null) {
            b bVar = (b) getLastNonConfigurationInstance();
            if (bVar != null) {
                this.f = bVar.f593b;
            }
            if (this.f == null) {
                this.f = new p();
            }
        }
        return this.f;
    }

    @Deprecated
    public Object l() {
        return null;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.g.c();
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e.c(bundle);
        ReportFragment.e(this);
        int i = this.h;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        b bVar;
        Object objL = l();
        p pVar = this.f;
        if (pVar == null && (bVar = (b) getLastNonConfigurationInstance()) != null) {
            pVar = bVar.f593b;
        }
        if (pVar == null && objL == null) {
            return null;
        }
        b bVar2 = new b();
        bVar2.f592a = objL;
        bVar2.f593b = pVar;
        return bVar2;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        Lifecycle lifecycleA = a();
        if (lifecycleA instanceof g) {
            ((g) lifecycleA).p(Lifecycle.State.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.e.d(bundle);
    }
}
