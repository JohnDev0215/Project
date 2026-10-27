package androidx.activity;

import a.n.d0;
import a.n.e0;
import a.n.f;
import a.n.g;
import a.n.i;
import a.n.k;
import a.n.l;
import a.n.u;
import a.n.w;
import a.n.z;
import a.q.c;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends a.h.c.b implements k, e0, f, c, a.a.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d0 f1366d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public z f1367e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l f1364b = new l(this);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a.q.b f1365c = new a.q.b(this);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final OnBackPressedDispatcher f1368f = new OnBackPressedDispatcher(new a());

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
        public d0 f1372a;
    }

    public ComponentActivity() {
        l lVar = this.f1364b;
        if (lVar == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        lVar.a(new i() { // from class: androidx.activity.ComponentActivity.2
            @Override // a.n.i
            public void g(k kVar, g.a aVar) {
                if (aVar == g.a.ON_STOP) {
                    Window window = ComponentActivity.this.getWindow();
                    View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                    if (viewPeekDecorView != null) {
                        viewPeekDecorView.cancelPendingInputEvents();
                    }
                }
            }
        });
        this.f1364b.a(new i() { // from class: androidx.activity.ComponentActivity.3
            @Override // a.n.i
            public void g(k kVar, g.a aVar) {
                if (aVar != g.a.ON_DESTROY || ComponentActivity.this.isChangingConfigurations()) {
                    return;
                }
                ComponentActivity.this.e().a();
            }
        });
    }

    @Override // a.n.k
    public g a() {
        return this.f1364b;
    }

    @Override // a.a.c
    public final OnBackPressedDispatcher c() {
        return this.f1368f;
    }

    @Override // a.q.c
    public final a.q.a d() {
        return this.f1365c.f1276b;
    }

    @Override // a.n.e0
    public d0 e() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.f1366d == null) {
            b bVar = (b) getLastNonConfigurationInstance();
            if (bVar != null) {
                this.f1366d = bVar.f1372a;
            }
            if (this.f1366d == null) {
                this.f1366d = new d0();
            }
        }
        return this.f1366d;
    }

    @Override // a.n.f
    public z j() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.f1367e == null) {
            this.f1367e = new w(getApplication(), this, getIntent() != null ? getIntent().getExtras() : null);
        }
        return this.f1367e;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.f1368f.a();
    }

    @Override // a.h.c.b, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f1365c.a(bundle);
        u.b(this);
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        b bVar;
        d0 d0Var = this.f1366d;
        if (d0Var == null && (bVar = (b) getLastNonConfigurationInstance()) != null) {
            d0Var = bVar.f1372a;
        }
        if (d0Var == null) {
            return null;
        }
        b bVar2 = new b();
        bVar2.f1372a = d0Var;
        return bVar2;
    }

    @Override // a.h.c.b, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        l lVar = this.f1364b;
        if (lVar instanceof l) {
            lVar.f(g.b.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.f1365c.b(bundle);
    }
}
