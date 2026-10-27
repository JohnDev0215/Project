package androidx.core.app;

import a.g.k.e;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.d;
import androidx.lifecycle.h;
import androidx.lifecycle.o;

/* JADX INFO: loaded from: classes.dex */
public class d extends Activity implements androidx.lifecycle.g, e.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a.d.g<Class<?>, ?> f1188b = new a.d.g<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private h f1189c = new h(this);

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !a.g.k.e.d(decorView, keyEvent)) {
            return a.g.k.e.e(this, decorView, this, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !a.g.k.e.d(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // a.g.k.e.a
    public boolean i(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    @SuppressLint({"RestrictedApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        o.e(this);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        this.f1189c.k(d.b.CREATED);
        super.onSaveInstanceState(bundle);
    }
}
