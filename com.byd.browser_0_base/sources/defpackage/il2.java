package defpackage;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import defpackage.jf3;
import defpackage.zf3;
import java.util.WeakHashMap;
import org.chromium.content.browser.ScreenOrientationProviderImpl;

/* JADX INFO: compiled from: SharedActivityCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class il2 implements sz0 {
    public final h20 e;
    public final jf3 f;
    public final t40 g;
    public final e40 h;
    public final p61<lx0> i;
    public final c40 j;
    public final zf3.b k;
    public boolean l = true;

    public il2(h20 h20Var, il3 il3Var, w20 w20Var, oj ojVar, t40 t40Var, e40 e40Var, q2 q2Var, jf3 jf3Var, p61<lx0> p61Var, c40 c40Var) {
        this.e = h20Var;
        this.f = jf3Var;
        this.g = t40Var;
        this.h = e40Var;
        this.i = p61Var;
        zf3 zf3VarI = ojVar.I();
        this.k = zf3VarI instanceof zf3.b ? (zf3.b) zf3VarI : null;
        this.j = c40Var;
        w20Var.i.a = new ke0(7, il3Var);
        h20Var.i.a(new wv(23, this));
        q2Var.f(this);
    }

    @Override // defpackage.sz0
    public final void Y0() {
        if (this.e.h == null) {
            b(true);
        }
    }

    public final void a(boolean z) {
        zf3.b bVar = this.k;
        if (bVar == null) {
            return;
        }
        p61<lx0> p61Var = this.i;
        if (!z) {
            lx0 lx0Var = p61Var.get();
            if (lx0Var.i) {
                lx0Var.i = false;
                lx0Var.g.removeCallbacks(lx0Var.h);
                lx0Var.b();
                lx0Var.f.r(0);
                return;
            }
            return;
        }
        lx0 lx0Var2 = p61Var.get();
        if (lx0Var2.i) {
            return;
        }
        lx0Var2.i = true;
        lx0Var2.j = bVar.a;
        Window window = lx0Var2.e.getWindow();
        window.getDecorView().setOnSystemUiVisibilityChangeListener(new vw(1, lx0Var2));
        WindowManager.LayoutParams attributes = window.getAttributes();
        int i = bVar.b;
        attributes.layoutInDisplayCutoutMode = i;
        lx0Var2.f.r(Integer.valueOf(i));
        lx0Var2.a(0);
    }

    public final void b(boolean z) {
        int i;
        Activity activity;
        a(z);
        jf3 jf3Var = this.f;
        if (jf3Var.e != z) {
            jf3Var.e = z;
            jf3Var.b();
            jf3Var.c();
            boolean z2 = jf3Var.e;
            jf3.a aVar = jf3Var.i;
            o23 o23Var = jf3Var.a;
            if (z2) {
                o23Var.c(aVar);
            } else {
                o23Var.e(aVar);
            }
        }
        t40 t40Var = this.g;
        if (t40Var.g != z) {
            t40Var.g = z;
            t40Var.a();
        }
        e40 e40Var = this.h;
        if (e40Var.b != z) {
            e40Var.b = z;
            e40Var.a.g();
        }
        c40 c40Var = this.j;
        if (z) {
            i = c40Var.b;
        } else {
            c40Var.getClass();
            i = 0;
        }
        ScreenOrientationProviderImpl screenOrientationProviderImpl = ScreenOrientationProviderImpl.getInstance();
        byte b = (byte) i;
        screenOrientationProviderImpl.getClass();
        g3 g3Var = c40Var.a;
        if (g3Var != null && (activity = (Activity) g3Var.k().get()) != null) {
            WeakHashMap weakHashMap = screenOrientationProviderImpl.f;
            if (b != 0) {
                weakHashMap.put(activity, Byte.valueOf(b));
            } else {
                weakHashMap.remove(activity);
            }
        }
        ScreenOrientationProviderImpl.getInstance().f(g3Var);
    }

    @Override // defpackage.sz0
    public final void q() {
        if (this.e.h == null) {
            a(true);
        }
    }
}
