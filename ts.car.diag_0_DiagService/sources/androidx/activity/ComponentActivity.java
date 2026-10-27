package androidx.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.activity.result.ActivityResultRegistry;
import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import androidx.lifecycle.g;
import androidx.lifecycle.p;
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import androidx.lifecycle.s;
import androidx.savedstate.SavedStateRegistry;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends androidx.core.app.ComponentActivity implements f, q, androidx.savedstate.b, androidx.activity.c, androidx.activity.result.c {
    private final g d;
    private final androidx.savedstate.a e;
    private p f;
    private final OnBackPressedDispatcher g;

    @LayoutRes
    private int h;
    private ActivityResultRegistry i;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ComponentActivity.super.onBackPressed();
            } catch (IllegalStateException e) {
                if (!TextUtils.equals(e.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                    throw e;
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends ActivityResultRegistry {
        b(ComponentActivity componentActivity) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        p f74a;

        c() {
        }
    }

    public ComponentActivity() {
        int i = Build.VERSION.SDK_INT;
        this.d = new g(this);
        this.e = androidx.savedstate.a.a(this);
        this.g = new OnBackPressedDispatcher(new a());
        new AtomicInteger();
        this.i = new b(this);
        if (a() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        if (i >= 19) {
            a().a(new d() { // from class: androidx.activity.ComponentActivity.3
                @Override // androidx.lifecycle.d
                public void f(@NonNull f fVar, @NonNull Lifecycle.Event event) {
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
        a().a(new d() { // from class: androidx.activity.ComponentActivity.4
            @Override // androidx.lifecycle.d
            public void f(@NonNull f fVar, @NonNull Lifecycle.Event event) {
                if (event != Lifecycle.Event.ON_DESTROY || ComponentActivity.this.isChangingConfigurations()) {
                    return;
                }
                ComponentActivity.this.h().a();
            }
        });
        if (19 > i || i > 23) {
            return;
        }
        a().a(new ImmLeaksCleaner(this));
    }

    private void l() {
        r.a(getWindow().getDecorView(), this);
        s.a(getWindow().getDecorView(), this);
        androidx.savedstate.c.a(getWindow().getDecorView(), this);
    }

    @Override // androidx.core.app.ComponentActivity, androidx.lifecycle.f
    @NonNull
    public Lifecycle a() {
        return this.d;
    }

    @Override // android.app.Activity
    public void addContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view, @SuppressLint({"UnknownNullness", "MissingNullability"}) ViewGroup.LayoutParams layoutParams) {
        l();
        super.addContentView(view, layoutParams);
    }

    @Override // androidx.activity.c
    @NonNull
    public final OnBackPressedDispatcher b() {
        return this.g;
    }

    @Override // androidx.savedstate.b
    @NonNull
    public final SavedStateRegistry c() {
        return this.e.b();
    }

    @Override // androidx.activity.result.c
    @NonNull
    public final ActivityResultRegistry d() {
        return this.i;
    }

    @Override // androidx.lifecycle.q
    @NonNull
    public p h() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.f == null) {
            c cVar = (c) getLastNonConfigurationInstance();
            if (cVar != null) {
                this.f = cVar.f74a;
            }
            if (this.f == null) {
                this.f = new p();
            }
        }
        return this.f;
    }

    @Nullable
    @Deprecated
    public Object m() {
        return null;
    }

    @Override // android.app.Activity
    @CallSuper
    @Deprecated
    protected void onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (this.i.b(i, i2, intent)) {
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    @MainThread
    public void onBackPressed() {
        this.g.c();
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.e.c(bundle);
        this.i.d(bundle);
        ReportFragment.f(this);
        int i = this.h;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Override // android.app.Activity
    @CallSuper
    @Deprecated
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (this.i.b(i, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr)) || Build.VERSION.SDK_INT < 23) {
            return;
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    @Nullable
    public final Object onRetainNonConfigurationInstance() {
        c cVar;
        Object objM = m();
        p pVar = this.f;
        if (pVar == null && (cVar = (c) getLastNonConfigurationInstance()) != null) {
            pVar = cVar.f74a;
        }
        if (pVar == null && objM == null) {
            return null;
        }
        c cVar2 = new c();
        cVar2.f74a = pVar;
        return cVar2;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    @CallSuper
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        Lifecycle lifecycleA = a();
        if (lifecycleA instanceof g) {
            ((g) lifecycleA).n(Lifecycle.State.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.e.d(bundle);
        this.i.e(bundle);
    }

    @Override // android.app.Activity
    public void setContentView(@LayoutRes int i) {
        l();
        super.setContentView(i);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i, @Nullable Bundle bundle) {
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, @Nullable Bundle bundle) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view) {
        l();
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view, @SuppressLint({"UnknownNullness", "MissingNullability"}) ViewGroup.LayoutParams layoutParams) {
        l();
        super.setContentView(view, layoutParams);
    }
}
