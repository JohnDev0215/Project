package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.view.e;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ReportFragment;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class ComponentActivity extends Activity implements androidx.lifecycle.f, e.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private androidx.lifecycle.g f409c;

    public ComponentActivity() {
        new b.a.g();
        this.f409c = new androidx.lifecycle.g(this);
    }

    @NonNull
    public Lifecycle a() {
        return this.f409c;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !androidx.core.view.e.d(decorView, keyEvent)) {
            return androidx.core.view.e.e(this, decorView, this, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !androidx.core.view.e.d(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // androidx.core.view.e.a
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public boolean e(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    @SuppressLint({"RestrictedApi"})
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ReportFragment.f(this);
    }

    @Override // android.app.Activity
    @CallSuper
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        this.f409c.i(Lifecycle.State.CREATED);
        super.onSaveInstanceState(bundle);
    }
}
