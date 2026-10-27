package i0;

import android.content.Context;
import android.view.ViewConfiguration;

/* JADX INFO: compiled from: ViewConfigurationCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class v {
    public static float a(ViewConfiguration viewConfiguration, Context context) {
        return viewConfiguration.getScaledHorizontalScrollFactor();
    }

    public static float b(ViewConfiguration viewConfiguration, Context context) {
        return viewConfiguration.getScaledVerticalScrollFactor();
    }

    public static boolean c(ViewConfiguration viewConfiguration, Context context) {
        return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
    }
}
