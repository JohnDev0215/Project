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
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* JADX INFO: compiled from: AppCompatDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f783c = -100;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b.d.b<WeakReference<d>> f784d = new b.d.b<>();
    public static final Object s = new Object();

    public static void c(d dVar) {
        synchronized (s) {
            y(dVar);
            f784d.add(new WeakReference<>(dVar));
        }
    }

    public static d g(Activity activity, c cVar) {
        return new AppCompatDelegateImpl(activity, cVar);
    }

    public static d h(Dialog dialog, c cVar) {
        return new AppCompatDelegateImpl(dialog, cVar);
    }

    public static int j() {
        return f783c;
    }

    public static void x(d dVar) {
        synchronized (s) {
            y(dVar);
        }
    }

    public static void y(d dVar) {
        synchronized (s) {
            Iterator<WeakReference<d>> it = f784d.iterator();
            while (it.hasNext()) {
                d dVar2 = it.next().get();
                if (dVar2 == dVar || dVar2 == null) {
                    it.remove();
                }
            }
        }
    }

    public abstract void A(int i2);

    public abstract void B(View view);

    public abstract void C(View view, ViewGroup.LayoutParams layoutParams);

    public void D(int i2) {
    }

    public abstract void E(CharSequence charSequence);

    public abstract void d(View view, ViewGroup.LayoutParams layoutParams);

    @Deprecated
    public void e(Context context) {
    }

    public Context f(Context context) {
        e(context);
        return context;
    }

    public abstract <T extends View> T i(int i2);

    public int k() {
        return -100;
    }

    public abstract MenuInflater l();

    public abstract a m();

    public abstract void n();

    public abstract void o();

    public abstract void p(Configuration configuration);

    public abstract void q(Bundle bundle);

    public abstract void r();

    public abstract void s(Bundle bundle);

    public abstract void t();

    public abstract void u(Bundle bundle);

    public abstract void v();

    public abstract void w();

    public abstract boolean z(int i2);
}
