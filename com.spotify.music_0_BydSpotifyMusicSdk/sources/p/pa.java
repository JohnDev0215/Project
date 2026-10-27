package p;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import java.lang.reflect.Constructor;

/* JADX INFO: loaded from: classes.dex */
public class pa {
    public static final Class[] b = {Context.class, AttributeSet.class};
    public static final int[] c = {R.attr.onClick};
    public static final int[] d = {R.attr.accessibilityHeading};
    public static final int[] e = {R.attr.accessibilityPaneTitle};
    public static final int[] f = {R.attr.screenReaderFocusable};
    public static final String[] g = {"android.widget.", "android.view.", "android.webkit."};
    public static final kd2 h = new kd2();
    public final Object[] a = new Object[2];

    public u7 a(Context context, AttributeSet attributeSet) {
        return new u7(context, attributeSet);
    }

    public AppCompatButton b(Context context, AttributeSet attributeSet) {
        return new AppCompatButton(context, attributeSet);
    }

    public x7 c(Context context, AttributeSet attributeSet) {
        return new x7(context, attributeSet, com.spotify.music.R.attr.checkboxStyle);
    }

    public f9 d(Context context, AttributeSet attributeSet) {
        return new f9(context, attributeSet);
    }

    public fa e(Context context, AttributeSet attributeSet) {
        return new fa(context, attributeSet);
    }

    public final View f(Context context, String str, String str2) {
        String strConcat;
        kd2 kd2Var = h;
        Constructor constructor = (Constructor) kd2Var.get(str);
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
            kd2Var.put(str, constructor);
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.a);
    }

    public final void g(TextView textView, String str) {
        if (textView != null) {
            return;
        }
        throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + str + ">, but returned null");
    }
}
