package androidx.appcompat.widget;

import android.R;
import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class DrawableUtils {
    public static final int[] a = {R.attr.state_checked};
    public static final int[] b = new int[0];
    public static final Rect c = new Rect();

    public static class Api18Impl {
        static {
            try {
                Class<?> cls = Class.forName("android.graphics.Insets");
                Drawable.class.getMethod("getOpticalInsets", new Class[0]);
                cls.getField("left");
                cls.getField("top");
                cls.getField("right");
                cls.getField("bottom");
            } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException unused) {
            }
        }

        private Api18Impl() {
        }
    }

    public static class Api29Impl {
        private Api29Impl() {
        }
    }

    private DrawableUtils() {
    }

    public static void a(Drawable drawable) {
        String name = drawable.getClass().getName();
        if (Build.VERSION.SDK_INT >= 31 || !"android.graphics.drawable.ColorStateListDrawable".equals(name)) {
            return;
        }
        int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(a);
        } else {
            drawable.setState(b);
        }
        drawable.setState(state);
    }

    public static Rect b(Drawable drawable) {
        Insets opticalInsets = drawable.getOpticalInsets();
        return new Rect(opticalInsets.left, opticalInsets.top, opticalInsets.right, opticalInsets.bottom);
    }

    public static PorterDuff.Mode c(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
