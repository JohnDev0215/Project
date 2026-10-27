package defpackage;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import defpackage.rt3;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ImmersiveModeController.java */
/* JADX INFO: loaded from: classes.dex */
public final class lx0 implements lt3, v80 {
    public final Activity e;
    public final o2 f;
    public final Handler g;
    public final l10 h;
    public boolean i;
    public boolean j;

    public lx0(q2 q2Var, Activity activity, WindowAndroid windowAndroid) {
        o2 o2Var = new o2();
        this.f = o2Var;
        this.g = new Handler();
        this.h = new l10(4, this);
        this.e = activity;
        q2Var.f(this);
        o2Var.s(windowAndroid.r);
    }

    public final void a(int i) {
        if (this.i) {
            Handler handler = this.g;
            l10 l10Var = this.h;
            handler.removeCallbacks(l10Var);
            handler.postDelayed(l10Var, i);
        }
    }

    public final void b() {
        int i = Build.VERSION.SDK_INT;
        Activity activity = this.e;
        if (i == 30) {
            View decorView = activity.getWindow().getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            int i2 = this.j ? 5895 : 3847;
            int i3 = this.i ? i2 | systemUiVisibility : (~i2) & systemUiVisibility;
            if (systemUiVisibility != i3) {
                decorView.setSystemUiVisibility(i3);
            }
        } else {
            Window window = activity.getWindow();
            rt3 rt3Var = new rt3(window, window.getDecorView());
            boolean z = this.j;
            rt3.e eVar = rt3Var.a;
            if (z) {
                eVar.e(2);
            } else {
                eVar.e(1);
            }
            if (this.i) {
                eVar.a(7);
            } else {
                eVar.f(7);
            }
        }
        ht3.a(activity.getWindow(), !this.i);
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        this.g.removeCallbacks(this.h);
        this.f.destroy();
    }

    @Override // defpackage.lt3
    public final void onWindowFocusChanged(boolean z) {
        if (z && this.i) {
            a(300);
        }
    }
}
