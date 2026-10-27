package androidx.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.core.app.d;
import androidx.lifecycle.d;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import androidx.lifecycle.h;
import androidx.lifecycle.o;
import androidx.lifecycle.r;
import androidx.lifecycle.s;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends d implements g, s, androidx.savedstate.b, c {
    private r f;
    private int h;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final h f656d = new h(this);
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
        Object f660a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        r f661b;

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
                public void d(g gVar, d.a aVar) {
                    if (aVar == d.a.ON_STOP) {
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
            public void d(g gVar, d.a aVar) {
                if (aVar != d.a.ON_DESTROY || ComponentActivity.this.isChangingConfigurations()) {
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

    @Override // androidx.lifecycle.g
    public androidx.lifecycle.d a() {
        return this.f656d;
    }

    @Override // androidx.activity.c
    public final OnBackPressedDispatcher c() {
        return this.g;
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry d() {
        return this.e.b();
    }

    @Override // androidx.lifecycle.s
    public r o() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.f == null) {
            b bVar = (b) getLastNonConfigurationInstance();
            if (bVar != null) {
                this.f = bVar.f661b;
            }
            if (this.f == null) {
                this.f = new r();
            }
        }
        return this.f;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.g.c();
    }

    @Override // androidx.core.app.d, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e.c(bundle);
        o.e(this);
        int i = this.h;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        b bVar;
        Object objU = u();
        r rVar = this.f;
        if (rVar == null && (bVar = (b) getLastNonConfigurationInstance()) != null) {
            rVar = bVar.f661b;
        }
        if (rVar == null && objU == null) {
            return null;
        }
        b bVar2 = new b();
        bVar2.f660a = objU;
        bVar2.f661b = rVar;
        return bVar2;
    }

    @Override // androidx.core.app.d, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        androidx.lifecycle.d dVarA = a();
        if (dVarA instanceof h) {
            ((h) dVarA).p(d.b.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.e.d(bundle);
    }

    @Deprecated
    public Object u() {
        return null;
    }
}
