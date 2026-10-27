package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.AppCompatToggleButton;
import defpackage.C0175b;
import defpackage.in3;
import defpackage.om3;
import defpackage.wm2;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatViewInflater {
    public static final Class<?>[] b = {Context.class, AttributeSet.class};
    public static final int[] c = {R.attr.onClick};
    public static final String[] d = {"android.widget.", "android.view.", "android.webkit."};
    public static final wm2<String, Constructor<? extends View>> e = new wm2<>();
    public final Object[] a = new Object[2];

    public static class DeclaredOnClickListener implements View.OnClickListener {
        public final View e;
        public final String f;
        public Method g;
        public Context h;

        public DeclaredOnClickListener(View view, String str) {
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

    public AppCompatAutoCompleteTextView a(Context context, AttributeSet attributeSet) {
        return new AppCompatAutoCompleteTextView(context, attributeSet);
    }

    public AppCompatButton b(Context context, AttributeSet attributeSet) {
        return new AppCompatButton(context, attributeSet);
    }

    public AppCompatCheckBox c(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckBox(context, attributeSet);
    }

    public AppCompatRadioButton d(Context context, AttributeSet attributeSet) {
        return new AppCompatRadioButton(context, attributeSet);
    }

    public AppCompatTextView e(Context context, AttributeSet attributeSet) {
        return new AppCompatTextView(context, attributeSet);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final View f(View view, String str, Context context, AttributeSet attributeSet) {
        byte b2;
        View appCompatRatingBar;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.appcompat.R.styleable.View, 0, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(androidx.appcompat.R.styleable.View_theme, 0);
        if (resourceId != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes.recycle();
        Context contextThemeWrapper = (resourceId == 0 || ((context instanceof ContextThemeWrapper) && ((ContextThemeWrapper) context).a == resourceId)) ? context : new ContextThemeWrapper(context, resourceId);
        str.getClass();
        switch (str.hashCode()) {
            case -1946472170:
                b2 = !str.equals("RatingBar") ? (byte) -1 : (byte) 0;
                break;
            case -1455429095:
                b2 = !str.equals("CheckedTextView") ? (byte) -1 : (byte) 1;
                break;
            case -1346021293:
                b2 = !str.equals("MultiAutoCompleteTextView") ? (byte) -1 : (byte) 2;
                break;
            case -938935918:
                b2 = !str.equals("TextView") ? (byte) -1 : (byte) 3;
                break;
            case -937446323:
                b2 = !str.equals("ImageButton") ? (byte) -1 : (byte) 4;
                break;
            case -658531749:
                b2 = !str.equals("SeekBar") ? (byte) -1 : (byte) 5;
                break;
            case -339785223:
                b2 = !str.equals("Spinner") ? (byte) -1 : (byte) 6;
                break;
            case 776382189:
                b2 = !str.equals("RadioButton") ? (byte) -1 : (byte) 7;
                break;
            case 799298502:
                b2 = !str.equals("ToggleButton") ? (byte) -1 : (byte) 8;
                break;
            case 1125864064:
                b2 = !str.equals("ImageView") ? (byte) -1 : (byte) 9;
                break;
            case 1413872058:
                b2 = !str.equals("AutoCompleteTextView") ? (byte) -1 : (byte) 10;
                break;
            case 1601505219:
                b2 = !str.equals("CheckBox") ? (byte) -1 : (byte) 11;
                break;
            case 1666676343:
                b2 = !str.equals("EditText") ? (byte) -1 : (byte) 12;
                break;
            case 2001146706:
                b2 = !str.equals("Button") ? (byte) -1 : (byte) 13;
                break;
            default:
                b2 = -1;
                break;
        }
        View view2 = null;
        switch (b2) {
            case 0:
                appCompatRatingBar = new AppCompatRatingBar(contextThemeWrapper, attributeSet);
                break;
            case 1:
                appCompatRatingBar = new AppCompatCheckedTextView(contextThemeWrapper, attributeSet);
                break;
            case 2:
                appCompatRatingBar = new AppCompatMultiAutoCompleteTextView(contextThemeWrapper, attributeSet);
                break;
            case 3:
                AppCompatTextView appCompatTextViewE = e(contextThemeWrapper, attributeSet);
                h(appCompatTextViewE, str);
                appCompatRatingBar = appCompatTextViewE;
                break;
            case 4:
                appCompatRatingBar = new AppCompatImageButton(contextThemeWrapper, attributeSet);
                break;
            case 5:
                appCompatRatingBar = new AppCompatSeekBar(contextThemeWrapper, attributeSet);
                break;
            case 6:
                appCompatRatingBar = new AppCompatSpinner(contextThemeWrapper, attributeSet);
                break;
            case 7:
                AppCompatRadioButton appCompatRadioButtonD = d(contextThemeWrapper, attributeSet);
                h(appCompatRadioButtonD, str);
                appCompatRatingBar = appCompatRadioButtonD;
                break;
            case 8:
                appCompatRatingBar = new AppCompatToggleButton(contextThemeWrapper, attributeSet);
                break;
            case 9:
                appCompatRatingBar = new AppCompatImageView(contextThemeWrapper, attributeSet);
                break;
            case 10:
                AppCompatAutoCompleteTextView appCompatAutoCompleteTextViewA = a(contextThemeWrapper, attributeSet);
                h(appCompatAutoCompleteTextViewA, str);
                appCompatRatingBar = appCompatAutoCompleteTextViewA;
                break;
            case 11:
                AppCompatCheckBox appCompatCheckBoxC = c(contextThemeWrapper, attributeSet);
                h(appCompatCheckBoxC, str);
                appCompatRatingBar = appCompatCheckBoxC;
                break;
            case 12:
                appCompatRatingBar = new AppCompatEditText(contextThemeWrapper, attributeSet);
                break;
            case 13:
                AppCompatButton appCompatButtonB = b(contextThemeWrapper, attributeSet);
                h(appCompatButtonB, str);
                appCompatRatingBar = appCompatButtonB;
                break;
            default:
                appCompatRatingBar = null;
                break;
        }
        if (appCompatRatingBar == null && context != contextThemeWrapper) {
            Object[] objArr = this.a;
            if (str.equals("view")) {
                str = attributeSet.getAttributeValue(null, "class");
            }
            try {
                objArr[0] = contextThemeWrapper;
                objArr[1] = attributeSet;
                if (-1 == str.indexOf(46)) {
                    int i = 0;
                    while (true) {
                        String[] strArr = d;
                        if (i < 3) {
                            View viewG = g(contextThemeWrapper, str, strArr[i]);
                            if (viewG != null) {
                                objArr[0] = null;
                                objArr[1] = null;
                                view2 = viewG;
                            } else {
                                i++;
                            }
                        }
                    }
                } else {
                    View viewG2 = g(contextThemeWrapper, str, null);
                    objArr[0] = null;
                    objArr[1] = null;
                    view2 = viewG2;
                }
            } catch (Exception unused) {
            } finally {
                objArr[0] = null;
                objArr[1] = null;
            }
            appCompatRatingBar = view2;
        }
        if (appCompatRatingBar != null) {
            Context context2 = appCompatRatingBar.getContext();
            if (context2 instanceof ContextWrapper) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                if (om3.c.a(appCompatRatingBar)) {
                    TypedArray typedArrayObtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet, c);
                    String string = typedArrayObtainStyledAttributes2.getString(0);
                    if (string != null) {
                        appCompatRatingBar.setOnClickListener(new DeclaredOnClickListener(appCompatRatingBar, string));
                    }
                    typedArrayObtainStyledAttributes2.recycle();
                }
            }
        }
        return appCompatRatingBar;
    }

    public final View g(Context context, String str, String str2) {
        String strConcat;
        wm2<String, Constructor<? extends View>> wm2Var = e;
        Constructor<? extends View> constructor = wm2Var.get(str);
        if (constructor == null) {
            if (str2 != null) {
                try {
                    strConcat = str2.concat(str);
                } catch (Exception unused) {
                    return null;
                }
            } else {
                strConcat = str;
            }
            constructor = Class.forName(strConcat, false, context.getClassLoader()).asSubclass(View.class).getConstructor(b);
            wm2Var.put(str, constructor);
        }
        constructor.setAccessible(true);
        return constructor.newInstance(this.a);
    }

    public final void h(TextView textView, String str) {
        if (textView != null) {
            return;
        }
        throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + str + ">, but returned null");
    }
}
