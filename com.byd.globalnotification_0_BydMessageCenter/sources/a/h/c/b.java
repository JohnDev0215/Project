package a.h.c;

import a.h.k.e;
import a.h.k.q;
import a.n.g;
import a.n.k;
import a.n.l;
import a.n.u;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class b extends Activity implements k, e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public l f702a = new l(this);

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView != null) {
            q.d(decorView, keyEvent);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView != null) {
            q.d(decorView, keyEvent);
        }
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // a.h.k.e
    public boolean h(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    @SuppressLint({"RestrictedApi"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        u.b(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        this.f702a.f(g.b.CREATED);
        super.onSaveInstanceState(bundle);
    }
}
