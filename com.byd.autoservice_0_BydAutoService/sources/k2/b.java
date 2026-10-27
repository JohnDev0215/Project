package k2;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

/* JADX INFO: compiled from: MaterialAttributes.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static TypedValue a(Context context, int i3) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i3, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static boolean b(Context context, int i3, boolean z3) {
        TypedValue typedValueA = a(context, i3);
        return (typedValueA == null || typedValueA.type != 18) ? z3 : typedValueA.data != 0;
    }

    public static int c(Context context, int i3, String str) {
        TypedValue typedValueA = a(context, i3);
        if (typedValueA != null) {
            return typedValueA.data;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i3)));
    }

    public static int d(View view, int i3) {
        return c(view.getContext(), i3, view.getClass().getCanonicalName());
    }
}
