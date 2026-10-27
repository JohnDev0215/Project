package p;

import android.view.View;
import android.view.autofill.AutofillId;
import com.spotify.music.R;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class st2 {
    public static void a(View view, xt2 xt2Var) {
        kd2 kd2Var = (kd2) view.getTag(R.id.tag_unhandled_key_listeners);
        if (kd2Var == null) {
            kd2Var = new kd2();
            view.setTag(R.id.tag_unhandled_key_listeners, kd2Var);
        }
        Objects.requireNonNull(xt2Var);
        View.OnUnhandledKeyEventListener rt2Var = new rt2();
        kd2Var.put(xt2Var, rt2Var);
        view.addOnUnhandledKeyEventListener(rt2Var);
    }

    public static CharSequence b(View view) {
        return view.getAccessibilityPaneTitle();
    }

    public static boolean c(View view) {
        return view.isAccessibilityHeading();
    }

    public static boolean d(View view) {
        return view.isScreenReaderFocusable();
    }

    public static void e(View view, xt2 xt2Var) {
        View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
        kd2 kd2Var = (kd2) view.getTag(R.id.tag_unhandled_key_listeners);
        if (kd2Var == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) kd2Var.get(xt2Var)) == null) {
            return;
        }
        view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
    }

    public static <T> T f(View view, int i) {
        return (T) view.requireViewById(i);
    }

    public static void g(View view, boolean z) {
        view.setAccessibilityHeading(z);
    }

    public static void h(View view, CharSequence charSequence) {
        view.setAccessibilityPaneTitle(charSequence);
    }

    public static void i(View view, AutofillId autofillId) {
        view.setAutofillId(autofillId);
    }

    public static void j(View view, boolean z) {
        view.setScreenReaderFocusable(z);
    }
}
