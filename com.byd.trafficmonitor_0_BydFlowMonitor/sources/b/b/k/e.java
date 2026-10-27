package b.b.k;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatDelegateImpl;
import androidx.appcompat.widget.Toolbar;
import b.b.p.b;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* JADX INFO: compiled from: AppCompatDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f1430b = -100;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b.e.b<WeakReference<e>> f1431c = new b.e.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f1432d = new Object();

    public static void c(e eVar) {
        synchronized (f1432d) {
            z(eVar);
            f1431c.add(new WeakReference<>(eVar));
        }
    }

    public static e g(Activity activity, d dVar) {
        return new AppCompatDelegateImpl(activity, dVar);
    }

    public static e h(Dialog dialog, d dVar) {
        return new AppCompatDelegateImpl(dialog, dVar);
    }

    public static int j() {
        return f1430b;
    }

    public static void y(e eVar) {
        synchronized (f1432d) {
            z(eVar);
        }
    }

    public static void z(e eVar) {
        synchronized (f1432d) {
            Iterator<WeakReference<e>> it = f1431c.iterator();
            while (it.hasNext()) {
                e eVar2 = it.next().get();
                if (eVar2 == eVar || eVar2 == null) {
                    it.remove();
                }
            }
        }
    }

    public abstract boolean A(int i2);

    public abstract void B(int i2);

    public abstract void C(View view);

    public abstract void D(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void E(int i2);

    public abstract void F(Toolbar toolbar);

    public void G(int i2) {
    }

    public abstract void H(CharSequence charSequence);

    public abstract b.b.p.b I(b.a aVar);

    public abstract void d(View view, ViewGroup.LayoutParams layoutParams);

    @Deprecated
    public void e(Context context) {
    }

    public Context f(Context context) {
        e(context);
        return context;
    }

    public abstract <T extends View> T i(int i2);

    public abstract b k();

    public int l() {
        return -100;
    }

    public abstract MenuInflater m();

    public abstract a n();

    public abstract void o();

    public abstract void p();

    public abstract void q(Configuration configuration);

    public abstract void r(Bundle bundle);

    public abstract void s();

    public abstract void t(Bundle bundle);

    public abstract void u();

    public abstract void v(Bundle bundle);

    public abstract void w();

    public abstract void x();
}
