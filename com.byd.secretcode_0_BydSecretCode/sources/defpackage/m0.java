package defpackage;

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
import defpackage.b1;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* JADX INFO: compiled from: AppCompatDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class m0 {
    public static int b = -100;
    public static final b4<WeakReference<m0>> c = new b4<>();
    public static final Object d = new Object();

    public static void c(m0 m0Var) {
        synchronized (d) {
            z(m0Var);
            c.add(new WeakReference<>(m0Var));
        }
    }

    public static m0 g(Activity activity, l0 l0Var) {
        return new AppCompatDelegateImpl(activity, l0Var);
    }

    public static m0 h(Dialog dialog, l0 l0Var) {
        return new AppCompatDelegateImpl(dialog, l0Var);
    }

    public static int j() {
        return b;
    }

    public static void y(m0 m0Var) {
        synchronized (d) {
            z(m0Var);
        }
    }

    public static void z(m0 m0Var) {
        synchronized (d) {
            Iterator<WeakReference<m0>> it = c.iterator();
            while (it.hasNext()) {
                m0 m0Var2 = it.next().get();
                if (m0Var2 == m0Var || m0Var2 == null) {
                    it.remove();
                }
            }
        }
    }

    public abstract boolean A(int i);

    public abstract void B(int i);

    public abstract void C(View view);

    public abstract void D(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void E(Toolbar toolbar);

    public void F(int i) {
    }

    public abstract void G(CharSequence charSequence);

    public abstract b1 H(b1.a aVar);

    public abstract void d(View view, ViewGroup.LayoutParams layoutParams);

    @Deprecated
    public void e(Context context) {
    }

    public Context f(Context context) {
        e(context);
        return context;
    }

    public abstract <T extends View> T i(int i);

    public abstract j0 k();

    public int l() {
        return -100;
    }

    public abstract MenuInflater m();

    public abstract i0 n();

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
