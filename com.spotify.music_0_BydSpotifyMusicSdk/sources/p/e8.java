package p;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class e8 {
    public static final va c = new va(new wa(0));
    public static final int e = -100;
    public static ac1 k = null;
    public static ac1 l = null;
    public static Boolean m = null;
    public static boolean n = false;
    public static final qb o = new qb(0);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Object f119p = new Object();
    public static final Object q = new Object();

    public static boolean a(Context context) {
        if (m == null) {
            try {
                int i = ta.c;
                Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(context, (Class<?>) ta.class), sa.a() | 128).metaData;
                if (bundle != null) {
                    m = Boolean.valueOf(bundle.getBoolean("autoStoreLocales"));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                m = Boolean.FALSE;
            }
        }
        return m.booleanValue();
    }

    public static void d(e8 e8Var) {
        synchronized (f119p) {
            try {
                qb qbVar = o;
                qbVar.getClass();
                jb jbVar = new jb(qbVar);
                while (jbVar.hasNext()) {
                    e8 e8Var2 = (e8) ((WeakReference) jbVar.next()).get();
                    if (e8Var2 == e8Var || e8Var2 == null) {
                        jbVar.remove();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public abstract void b();

    public abstract void c();

    public abstract boolean f(int i);

    public abstract void g(int i);

    public abstract void i(View view);

    public abstract void j(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void k(CharSequence charSequence);
}
