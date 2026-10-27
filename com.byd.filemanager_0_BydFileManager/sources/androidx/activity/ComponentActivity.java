package androidx.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import androidx.lifecycle.h;
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends androidx.core.app.ComponentActivity implements g, r, androidx.savedstate.b, c {
    private q f;
    private int h;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final h f61d = new h(this);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final androidx.savedstate.a f62e = androidx.savedstate.a.a(this);
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
        Object f66a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        q f67b;

        b() {
        }
    }

    public ComponentActivity() {
        if (a() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            a().a(new e() { // from class: androidx.activity.ComponentActivity.2
                @Override // androidx.lifecycle.e
                public void d(g gVar, Lifecycle.Event event) {
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
        a().a(new e() { // from class: androidx.activity.ComponentActivity.3
            @Override // androidx.lifecycle.e
            public void d(g gVar, Lifecycle.Event event) {
                if (event != Lifecycle.Event.ON_DESTROY || ComponentActivity.this.isChangingConfigurations()) {
                    return;
                }
                ComponentActivity.this.o().a();
            }
        });
        if (19 > i || i > 23) {
            return;
        }
        a().a(new ImmLeaksCleaner(this));
    }

    @Override // androidx.core.app.ComponentActivity, androidx.lifecycle.g
    public Lifecycle a() {
        return this.f61d;
    }

    @Override // androidx.activity.c
    public final OnBackPressedDispatcher c() {
        return this.g;
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry d() {
        return this.f62e.b();
    }

    @Override // androidx.lifecycle.r
    public q o() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.f == null) {
            b bVar = (b) getLastNonConfigurationInstance();
            if (bVar != null) {
                this.f = bVar.f67b;
            }
            if (this.f == null) {
                this.f = new q();
            }
        }
        return this.f;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.g.c();
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f62e.c(bundle);
        ReportFragment.e(this);
        int i = this.h;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        b bVar;
        Object objT = t();
        q qVar = this.f;
        if (qVar == null && (bVar = (b) getLastNonConfigurationInstance()) != null) {
            qVar = bVar.f67b;
        }
        if (qVar == null && objT == null) {
            return null;
        }
        b bVar2 = new b();
        bVar2.f66a = objT;
        bVar2.f67b = qVar;
        return bVar2;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        Lifecycle lifecycleA = a();
        if (lifecycleA instanceof h) {
            ((h) lifecycleA).p(Lifecycle.State.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.f62e.d(bundle);
    }

    @Deprecated
    public Object t() {
        return null;
    }
}
