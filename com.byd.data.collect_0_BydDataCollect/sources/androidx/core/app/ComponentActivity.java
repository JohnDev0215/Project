package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.d;
import androidx.lifecycle.h;
import androidx.lifecycle.o;
import b.g.k.e;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends Activity implements androidx.lifecycle.g, e.a {
    private b.d.g<Class<?>, ?> mExtraDataMap = new b.d.g<>();
    private h mLifecycleRegistry = new h(this);

    public androidx.lifecycle.d a() {
        return this.mLifecycleRegistry;
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
    public boolean g(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    @SuppressLint({"RestrictedApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        o.f(this);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        this.mLifecycleRegistry.k(d.b.CREATED);
        super.onSaveInstanceState(bundle);
    }
}
