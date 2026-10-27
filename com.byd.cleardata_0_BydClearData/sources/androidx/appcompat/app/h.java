package androidx.appcompat.app;

import a.g.k.t;
import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.a0;
import androidx.appcompat.widget.n;
import androidx.appcompat.widget.o;
import androidx.appcompat.widget.r;
import androidx.appcompat.widget.s;
import androidx.appcompat.widget.s0;
import androidx.appcompat.widget.v;
import androidx.appcompat.widget.y;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Class<?>[] f745a = {Context.class, AttributeSet.class};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f746b = {R.attr.onClick};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String[] f747c = {"android.widget.", "android.view.", "android.webkit."};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final a.d.g<String, Constructor<? extends View>> f748d = new a.d.g<>();
    private final Object[] e = new Object[2];

    private static class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final View f749b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f750c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Method f751d;
        private Context e;

        public a(View view, String str) {
            this.f749b = view;
            this.f750c = str;
        }

        private void a(Context context) {
            String str;
            Method method;
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.f750c, View.class)) != null) {
                        this.f751d = method;
                        this.e = context;
                        return;
                    }
                } catch (NoSuchMethodException unused) {
                }
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
            int id = this.f749b.getId();
            if (id == -1) {
                str = "";
            } else {
                str = " with id '" + this.f749b.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.f750c + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.f749b.getClass() + str);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f751d == null) {
                a(this.f749b.getContext());
            }
            try {
                this.f751d.invoke(this.e, view);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
            } catch (InvocationTargetException e2) {
                throw new IllegalStateException("Could not execute method for android:onClick", e2);
            }
        }
    }

    private void a(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if (context instanceof ContextWrapper) {
            if (Build.VERSION.SDK_INT < 15 || t.I(view)) {
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f746b);
                String string = typedArrayObtainStyledAttributes.getString(0);
                if (string != null) {
                    view.setOnClickListener(new a(view, string));
                }
                typedArrayObtainStyledAttributes.recycle();
            }
        }
    }

    private View r(Context context, String str, String str2) {
        String str3;
        a.d.g<String, Constructor<? extends View>> gVar = f748d;
        Constructor<? extends View> constructor = gVar.get(str);
        if (constructor == null) {
            if (str2 != null) {
                try {
                    str3 = str2 + str;
                } catch (Exception unused) {
                    return null;
                }
            } else {
                str3 = str;
            }
            constructor = Class.forName(str3, false, context.getClassLoader()).asSubclass(View.class).getConstructor(f745a);
            gVar.put(str, constructor);
        }
        constructor.setAccessible(true);
        return constructor.newInstance(this.e);
    }

    private View s(Context context, String str, AttributeSet attributeSet) {
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            Object[] objArr = this.e;
            objArr[0] = context;
            objArr[1] = attributeSet;
            if (-1 != str.indexOf(46)) {
                return r(context, str, null);
            }
            int i = 0;
            while (true) {
                String[] strArr = f747c;
                if (i >= strArr.length) {
                    return null;
                }
                View viewR = r(context, str, strArr[i]);
                if (viewR != null) {
                    return viewR;
                }
                i++;
            }
        } catch (Exception unused) {
            return null;
        } finally {
            Object[] objArr2 = this.e;
            objArr2[0] = null;
            objArr2[1] = null;
        }
    }

    private static Context t(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.a.j.A3, 0, 0);
        int resourceId = z ? typedArrayObtainStyledAttributes.getResourceId(a.a.j.B3, 0) : 0;
        if (z2 && resourceId == 0 && (resourceId = typedArrayObtainStyledAttributes.getResourceId(a.a.j.C3, 0)) != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes.recycle();
        return resourceId != 0 ? ((context instanceof a.a.n.d) && ((a.a.n.d) context).getThemeResId() == resourceId) ? context : new a.a.n.d(context, resourceId) : context;
    }

    private void u(View view, String str) {
        if (view != null) {
            return;
        }
        throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + str + ">, but returned null");
    }

    protected androidx.appcompat.widget.d b(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.d(context, attributeSet);
    }

    protected androidx.appcompat.widget.f c(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.f(context, attributeSet);
    }

    protected AppCompatCheckBox d(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckBox(context, attributeSet);
    }

    protected androidx.appcompat.widget.g e(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.g(context, attributeSet);
    }

    protected androidx.appcompat.widget.j f(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.j(context, attributeSet);
    }

    protected androidx.appcompat.widget.l g(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.l(context, attributeSet);
    }

    protected n h(Context context, AttributeSet attributeSet) {
        return new n(context, attributeSet);
    }

    protected o i(Context context, AttributeSet attributeSet) {
        return new o(context, attributeSet);
    }

    protected r j(Context context, AttributeSet attributeSet) {
        return new r(context, attributeSet);
    }

    protected s k(Context context, AttributeSet attributeSet) {
        return new s(context, attributeSet);
    }

    protected androidx.appcompat.widget.t l(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.t(context, attributeSet);
    }

    protected v m(Context context, AttributeSet attributeSet) {
        return new v(context, attributeSet);
    }

    protected y n(Context context, AttributeSet attributeSet) {
        return new y(context, attributeSet);
    }

    protected a0 o(Context context, AttributeSet attributeSet) {
        return new a0(context, attributeSet);
    }

    protected View p(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    final View q(View view, String str, Context context, AttributeSet attributeSet, boolean z, boolean z2, boolean z3, boolean z4) {
        Context context2;
        View viewK;
        context2 = (!z || view == null) ? context : view.getContext();
        if (z2 || z3) {
            context2 = t(context2, attributeSet, z2, z3);
        }
        if (z4) {
            context2 = s0.b(context2);
        }
        str.hashCode();
        switch (str) {
            case "RatingBar":
                viewK = k(context2, attributeSet);
                u(viewK, str);
                break;
            case "CheckedTextView":
                viewK = e(context2, attributeSet);
                u(viewK, str);
                break;
            case "MultiAutoCompleteTextView":
                viewK = i(context2, attributeSet);
                u(viewK, str);
                break;
            case "TextView":
                viewK = n(context2, attributeSet);
                u(viewK, str);
                break;
            case "ImageButton":
                viewK = g(context2, attributeSet);
                u(viewK, str);
                break;
            case "SeekBar":
                viewK = l(context2, attributeSet);
                u(viewK, str);
                break;
            case "Spinner":
                viewK = m(context2, attributeSet);
                u(viewK, str);
                break;
            case "RadioButton":
                viewK = j(context2, attributeSet);
                u(viewK, str);
                break;
            case "ToggleButton":
                viewK = o(context2, attributeSet);
                u(viewK, str);
                break;
            case "ImageView":
                viewK = h(context2, attributeSet);
                u(viewK, str);
                break;
            case "AutoCompleteTextView":
                viewK = b(context2, attributeSet);
                u(viewK, str);
                break;
            case "CheckBox":
                viewK = d(context2, attributeSet);
                u(viewK, str);
                break;
            case "EditText":
                viewK = f(context2, attributeSet);
                u(viewK, str);
                break;
            case "Button":
                viewK = c(context2, attributeSet);
                u(viewK, str);
                break;
            default:
                viewK = p(context2, str, attributeSet);
                break;
        }
        if (viewK == null && context != context2) {
            viewK = s(context2, str, attributeSet);
        }
        if (viewK != null) {
            a(viewK, attributeSet);
        }
        return viewK;
    }
}
