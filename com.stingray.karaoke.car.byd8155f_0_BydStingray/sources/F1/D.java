package F1;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class D {
    public static CharSequence a(View view) {
        return view.getAccessibilityPaneTitle();
    }

    public static boolean b(View view) {
        return view.isAccessibilityHeading();
    }

    public static boolean c(View view) {
        return view.isScreenReaderFocusable();
    }

    public static void d(View view, CharSequence charSequence) {
        view.setAccessibilityPaneTitle(charSequence);
    }
}
