package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.C0057c;
import androidx.appcompat.widget.C0059e;
import androidx.appcompat.widget.C0060f;
import androidx.appcompat.widget.C0061g;
import androidx.appcompat.widget.C0064j;
import androidx.appcompat.widget.C0066l;
import androidx.appcompat.widget.C0068n;
import androidx.appcompat.widget.C0069o;
import androidx.appcompat.widget.C0072s;
import androidx.appcompat.widget.C0073t;
import androidx.appcompat.widget.C0077x;
import androidx.appcompat.widget.C0079z;
import androidx.appcompat.widget.Q;
import androidx.appcompat.widget.r;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Class<?>[] f142b = {Context.class, AttributeSet.class};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f143c = {R.attr.onClick};
    private static final String[] d = {"android.widget.", "android.view.", "android.webkit."};
    private static final b.a.g<String, Constructor<? extends View>> e = new b.a.g<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f144a = new Object[2];

    /* JADX INFO: compiled from: caradapter-bug */
    private static class a implements View.OnClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final View f145c;
        private final String d;
        private Method e;
        private Context f;

        public a(@NonNull View view, @NonNull String str) {
            this.f145c = view;
            this.d = str;
        }

        private void a(@Nullable Context context) {
            String str;
            Method method;
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.d, View.class)) != null) {
                        this.e = method;
                        this.f = context;
                        return;
                    }
                } catch (NoSuchMethodException unused) {
                }
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
            int id = this.f145c.getId();
            if (id == -1) {
                str = "";
            } else {
                str = " with id '" + this.f145c.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.d + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.f145c.getClass() + str);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@NonNull View view) {
            if (this.e == null) {
                a(this.f145c.getContext());
            }
            try {
                this.e.invoke(this.f, view);
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
            if (Build.VERSION.SDK_INT < 15 || ViewCompat.x(view)) {
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f143c);
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
        Constructor<? extends View> constructor = e.get(str);
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
            constructor = Class.forName(str3, false, context.getClassLoader()).asSubclass(View.class).getConstructor(f142b);
            e.put(str, constructor);
        }
        constructor.setAccessible(true);
        return constructor.newInstance(this.f144a);
    }

    private View s(Context context, String str, AttributeSet attributeSet) {
        String[] strArr = d;
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            this.f144a[0] = context;
            this.f144a[1] = attributeSet;
            if (-1 != str.indexOf(46)) {
                return r(context, str, null);
            }
            for (String str2 : strArr) {
                View viewR = r(context, str, str2);
                if (viewR != null) {
                    return viewR;
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        } finally {
            Object[] objArr = this.f144a;
            objArr[0] = null;
            objArr[1] = null;
        }
    }

    private static Context t(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.View, 0, 0);
        int resourceId = z ? typedArrayObtainStyledAttributes.getResourceId(R$styleable.View_android_theme, 0) : 0;
        if (z2 && resourceId == 0 && (resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.View_theme, 0)) != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes.recycle();
        return resourceId != 0 ? ((context instanceof androidx.appcompat.c.d) && ((androidx.appcompat.c.d) context).getThemeResId() == resourceId) ? context : new androidx.appcompat.c.d(context, resourceId) : context;
    }

    private void u(View view, String str) {
        if (view != null) {
            return;
        }
        throw new IllegalStateException(d.class.getName() + " asked to inflate view for <" + str + ">, but returned null");
    }

    @NonNull
    protected C0057c b(Context context, AttributeSet attributeSet) {
        return new C0057c(context, attributeSet);
    }

    @NonNull
    protected C0059e c(Context context, AttributeSet attributeSet) {
        return new C0059e(context, attributeSet);
    }

    @NonNull
    protected C0060f d(Context context, AttributeSet attributeSet) {
        return new C0060f(context, attributeSet);
    }

    @NonNull
    protected C0061g e(Context context, AttributeSet attributeSet) {
        return new C0061g(context, attributeSet);
    }

    @NonNull
    protected C0064j f(Context context, AttributeSet attributeSet) {
        return new C0064j(context, attributeSet);
    }

    @NonNull
    protected C0066l g(Context context, AttributeSet attributeSet) {
        return new C0066l(context, attributeSet);
    }

    @NonNull
    protected C0068n h(Context context, AttributeSet attributeSet) {
        return new C0068n(context, attributeSet);
    }

    @NonNull
    protected C0069o i(Context context, AttributeSet attributeSet) {
        return new C0069o(context, attributeSet);
    }

    @NonNull
    protected r j(Context context, AttributeSet attributeSet) {
        return new r(context, attributeSet);
    }

    @NonNull
    protected C0072s k(Context context, AttributeSet attributeSet) {
        return new C0072s(context, attributeSet);
    }

    @NonNull
    protected C0073t l(Context context, AttributeSet attributeSet) {
        return new C0073t(context, attributeSet);
    }

    @NonNull
    protected AppCompatSpinner m(Context context, AttributeSet attributeSet) {
        return new AppCompatSpinner(context, attributeSet);
    }

    @NonNull
    protected C0077x n(Context context, AttributeSet attributeSet) {
        return new C0077x(context, attributeSet);
    }

    @NonNull
    protected C0079z o(Context context, AttributeSet attributeSet) {
        return new C0079z(context, attributeSet);
    }

    @Nullable
    protected View p(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    final View q(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet, boolean z, boolean z2, boolean z3, boolean z4) {
        Context context2;
        View viewN;
        context2 = (!z || view == null) ? context : view.getContext();
        if (z2 || z3) {
            context2 = t(context2, attributeSet, z2, z3);
        }
        if (z4) {
            context2 = Q.b(context2);
        }
        switch (str) {
            case "TextView":
                viewN = n(context2, attributeSet);
                u(viewN, str);
                break;
            case "ImageView":
                viewN = h(context2, attributeSet);
                u(viewN, str);
                break;
            case "Button":
                viewN = c(context2, attributeSet);
                u(viewN, str);
                break;
            case "EditText":
                viewN = f(context2, attributeSet);
                u(viewN, str);
                break;
            case "Spinner":
                viewN = m(context2, attributeSet);
                u(viewN, str);
                break;
            case "ImageButton":
                viewN = g(context2, attributeSet);
                u(viewN, str);
                break;
            case "CheckBox":
                viewN = d(context2, attributeSet);
                u(viewN, str);
                break;
            case "RadioButton":
                viewN = j(context2, attributeSet);
                u(viewN, str);
                break;
            case "CheckedTextView":
                viewN = e(context2, attributeSet);
                u(viewN, str);
                break;
            case "AutoCompleteTextView":
                viewN = b(context2, attributeSet);
                u(viewN, str);
                break;
            case "MultiAutoCompleteTextView":
                viewN = i(context2, attributeSet);
                u(viewN, str);
                break;
            case "RatingBar":
                viewN = k(context2, attributeSet);
                u(viewN, str);
                break;
            case "SeekBar":
                viewN = l(context2, attributeSet);
                u(viewN, str);
                break;
            case "ToggleButton":
                viewN = o(context2, attributeSet);
                u(viewN, str);
                break;
            default:
                viewN = p(context2, str, attributeSet);
                break;
        }
        if (viewN == null && context != context2) {
            viewN = s(context2, str, attributeSet);
        }
        if (viewN != null) {
            a(viewN, attributeSet);
        }
        return viewN;
    }
}
