package a.b.k;

import a.b.p.z;
import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class<?>[] f54b = {Context.class, AttributeSet.class};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f55c = {R.attr.onClick};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String[] f56d = {"android.widget.", "android.view.", "android.webkit."};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a.e.h<String, Constructor<? extends View>> f57e = new a.e.h<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f58a = new Object[2];

    public static class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f59a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f60b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Method f61c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Context f62d;

        public a(View view, String str) {
            this.f59a = view;
            this.f60b = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String string;
            Method method;
            if (this.f61c == null) {
                Context context = this.f59a.getContext();
                while (context != null) {
                    try {
                        if (!context.isRestricted() && (method = context.getClass().getMethod(this.f60b, View.class)) != null) {
                            this.f61c = method;
                            this.f62d = context;
                        }
                    } catch (NoSuchMethodException unused) {
                    }
                    context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
                }
                int id = this.f59a.getId();
                if (id == -1) {
                    string = "";
                } else {
                    StringBuilder sbD = b.a.a.a.a.d(" with id '");
                    sbD.append(this.f59a.getContext().getResources().getResourceEntryName(id));
                    sbD.append("'");
                    string = sbD.toString();
                }
                StringBuilder sbD2 = b.a.a.a.a.d("Could not find method ");
                sbD2.append(this.f60b);
                sbD2.append("(View) in a parent or ancestor Context for android:onClick attribute defined on view ");
                sbD2.append(this.f59a.getClass());
                sbD2.append(string);
                throw new IllegalStateException(sbD2.toString());
            }
            try {
                this.f61c.invoke(this.f62d, view);
            } catch (IllegalAccessException e2) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e2);
            } catch (InvocationTargetException e3) {
                throw new IllegalStateException("Could not execute method for android:onClick", e3);
            }
        }
    }

    public a.b.p.d a(Context context, AttributeSet attributeSet) {
        return new a.b.p.d(context, attributeSet);
    }

    public a.b.p.f b(Context context, AttributeSet attributeSet) {
        return new a.b.p.f(context, attributeSet);
    }

    public a.b.p.g c(Context context, AttributeSet attributeSet) {
        return new a.b.p.g(context, attributeSet);
    }

    public a.b.p.r d(Context context, AttributeSet attributeSet) {
        return new a.b.p.r(context, attributeSet, a.b.a.radioButtonStyle);
    }

    public z e(Context context, AttributeSet attributeSet) {
        return new z(context, attributeSet);
    }

    public View f() {
        return null;
    }

    public final View g(Context context, String str, String str2) {
        String str3;
        Constructor<? extends View> orDefault = f57e.getOrDefault(str, null);
        if (orDefault == null) {
            if (str2 != null) {
                try {
                    str3 = str2 + str;
                } catch (Exception unused) {
                    return null;
                }
            } else {
                str3 = str;
            }
            orDefault = Class.forName(str3, false, context.getClassLoader()).asSubclass(View.class).getConstructor(f54b);
            f57e.put(str, orDefault);
        }
        orDefault.setAccessible(true);
        return orDefault.newInstance(this.f58a);
    }

    public final void h(View view, String str) {
        if (view != null) {
            return;
        }
        throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + str + ">, but returned null");
    }
}
