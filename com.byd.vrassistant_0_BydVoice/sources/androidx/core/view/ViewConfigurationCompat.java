package androidx.core.view;

import android.R;
import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class ViewConfigurationCompat {
    private static final String TAG = "ViewConfigCompat";
    private static Method sGetScaledScrollFactorMethod;

    @Deprecated
    public static int getScaledPagingTouchSlop(ViewConfiguration config) {
        return config.getScaledPagingTouchSlop();
    }

    @Deprecated
    public static boolean hasPermanentMenuKey(ViewConfiguration config) {
        return config.hasPermanentMenuKey();
    }

    public static float getScaledHorizontalScrollFactor(ViewConfiguration config, Context context) {
        return config.getScaledHorizontalScrollFactor();
    }

    public static float getScaledVerticalScrollFactor(ViewConfiguration config, Context context) {
        return config.getScaledVerticalScrollFactor();
    }

    private static float getLegacyScrollFactor(ViewConfiguration config, Context context) {
        if (sGetScaledScrollFactorMethod != null) {
            try {
                return ((Integer) r0.invoke(config, new Object[0])).intValue();
            } catch (Exception unused) {
                Log.i(TAG, "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
            return typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return 0.0f;
    }

    public static int getScaledHoverSlop(ViewConfiguration config) {
        return config.getScaledHoverSlop();
    }

    public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration config, Context context) {
        return config.shouldShowMenuShortcutsWhenKeyboardPresent();
    }

    private ViewConfigurationCompat() {
    }
}
