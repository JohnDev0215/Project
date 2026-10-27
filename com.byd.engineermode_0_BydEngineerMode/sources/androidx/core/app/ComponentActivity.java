package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ReportFragment;
import b.d.g;
import b.g.k.e;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends Activity implements androidx.lifecycle.f, e.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private g<Class<?>, ?> f800b = new g<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private androidx.lifecycle.g f801c = new androidx.lifecycle.g(this);

    public Lifecycle a() {
        return this.f801c;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !b.g.k.e.d(decorView, keyEvent)) {
            return b.g.k.e.e(this, decorView, this, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !b.g.k.e.d(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // b.g.k.e.a
    public boolean e(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    @SuppressLint({"RestrictedApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReportFragment.e(this);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        this.f801c.k(Lifecycle.State.CREATED);
        super.onSaveInstanceState(bundle);
    }
}
