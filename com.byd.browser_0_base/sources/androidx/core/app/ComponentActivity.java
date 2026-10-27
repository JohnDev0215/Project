package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.g;
import defpackage.b71;
import defpackage.e41;
import defpackage.in3;
import defpackage.om3;
import defpackage.wm2;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ComponentActivity extends Activity implements b71, e41 {
    public final g e;

    public ComponentActivity() {
        new wm2();
        this.e = new g(this);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (getWindow().getDecorView() != null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
        }
        return r1(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        if (getWindow().getDecorView() != null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
        }
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    public g j1() {
        return this.e;
    }

    @Override // android.app.Activity
    @SuppressLint({"RestrictedApi"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = ReportFragment.f;
        ReportFragment.b.b(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        g gVar = this.e;
        gVar.getClass();
        gVar.d("markState");
        gVar.g();
        super.onSaveInstanceState(bundle);
    }

    public final boolean r1(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
