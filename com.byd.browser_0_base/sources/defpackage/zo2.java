package defpackage;

import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: SkinCompatViewInflater.java */
/* JADX INFO: loaded from: classes3.dex */
public final class zo2 {
    public static final Class<?>[] b = {Context.class, AttributeSet.class};
    public static final int[] c = {R.attr.onClick};
    public static final String[] d = {"android.widget.", "android.view.", "android.webkit."};
    public static final n8 e = new n8();
    public final Object[] a = new Object[2];

    /* JADX INFO: compiled from: SkinCompatViewInflater.java */
    public static class a implements View.OnClickListener {
        public final View e;
        public final String f;
        public Method g;
        public Context h;

        public a(View view, String str) {
            this.e = view;
            this.f = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            String str;
            Method method;
            if (this.g == null) {
                View view2 = this.e;
                Context context = view2.getContext();
                while (true) {
                    String str2 = this.f;
                    if (context == null) {
                        int id = view2.getId();
                        if (id == -1) {
                            str = "";
                        } else {
                            str = " with id '" + view2.getContext().getResources().getResourceEntryName(id) + "'";
                        }
                        StringBuilder sbN = C0175b.n("Could not find method ", str2, "(View) in a parent or ancestor Context for android:onClick attribute defined on view ");
                        sbN.append(view2.getClass());
                        sbN.append(str);
                        throw new IllegalStateException(sbN.toString());
                    }
                    try {
                        if (!context.isRestricted() && (method = context.getClass().getMethod(str2, View.class)) != null) {
                            this.g = method;
                            this.h = context;
                        }
                    } catch (NoSuchMethodException unused) {
                    }
                    context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
                }
            }
            try {
                this.g.invoke(this.h, view);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
            } catch (InvocationTargetException e2) {
                throw new IllegalStateException("Could not execute method for android:onClick", e2);
            }
        }
    }

    public final View a(Context context, String str, String str2) {
        n8 n8Var = e;
        Constructor constructor = (Constructor) n8Var.get(str);
        if (constructor == null) {
            try {
                constructor = context.getClassLoader().loadClass(str2 != null ? str2.concat(str) : str).asSubclass(View.class).getConstructor(b);
                n8Var.put(str, constructor);
            } catch (Exception unused) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.a);
    }
}
