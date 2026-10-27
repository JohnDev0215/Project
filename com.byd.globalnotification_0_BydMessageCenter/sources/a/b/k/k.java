package a.b.k;

import a.e.g;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a.e.c<WeakReference<k>> f18a = new a.e.c<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f19b = new Object();

    public static k d(Activity activity, j jVar) {
        return new l(activity, null, jVar, activity);
    }

    public static k e(Dialog dialog, j jVar) {
        return new l(dialog.getContext(), dialog.getWindow(), jVar, dialog);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void k(k kVar) {
        synchronized (f19b) {
            Iterator<WeakReference<k>> it = f18a.iterator();
            while (true) {
                g.a aVar = (g.a) it;
                if (aVar.hasNext()) {
                    k kVar2 = (k) ((WeakReference) aVar.next()).get();
                    if (kVar2 == kVar || kVar2 == null) {
                        aVar.remove();
                    }
                }
            }
        }
    }

    public abstract void c(View view, ViewGroup.LayoutParams layoutParams);

    public abstract View f(View view, String str, Context context, AttributeSet attributeSet);

    public abstract void g();

    public abstract void h();

    public abstract void i(Bundle bundle);

    public abstract void j();

    public abstract boolean l(int i);

    public abstract void m(int i);

    public abstract void n(View view);

    public abstract void o(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void p(CharSequence charSequence);
}
