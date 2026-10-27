package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ReportFragment;
import b.d.g;
import b.f.m.e;
import b.l.f;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends Activity implements f, e.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g<Class<?>, ?> f419c = new g<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.l.g f420d = new b.l.g(this);

    public Lifecycle a() {
        return this.f420d;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !e.d(decorView, keyEvent)) {
            return e.e(this, decorView, this, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !e.d(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // b.f.m.e.a
    public boolean e(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    @SuppressLint({"RestrictedApi"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReportFragment.e(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        this.f420d.k(Lifecycle.State.CREATED);
        super.onSaveInstanceState(bundle);
    }
}
