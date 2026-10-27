package b3;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

/* JADX INFO: compiled from: MaterialAttributes.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static TypedValue a(Context context, int i7) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i7, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static boolean b(Context context, int i7, boolean z6) {
        TypedValue typedValueA = a(context, i7);
        return (typedValueA == null || typedValueA.type != 18) ? z6 : typedValueA.data != 0;
    }

    public static int c(Context context, int i7, String str) {
        TypedValue typedValueA = a(context, i7);
        if (typedValueA != null) {
            return typedValueA.data;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i7)));
    }

    public static int d(View view, int i7) {
        return c(view.getContext(), i7, view.getClass().getCanonicalName());
    }
}
