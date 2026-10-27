package r;

import android.R;
import android.annotation.SuppressLint;
import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: DrawableUtils.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f7616a = {R.attr.state_checked};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f7617b = new int[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Rect f7618c = new Rect();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Class<?> f7619d;

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                f7619d = Class.forName("android.graphics.Insets");
            } catch (ClassNotFoundException unused) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean a(Drawable drawable) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 < 15 && (drawable instanceof InsetDrawable)) {
            return false;
        }
        if (i8 < 15 && (drawable instanceof GradientDrawable)) {
            return false;
        }
        if (i8 < 17 && (drawable instanceof LayerDrawable)) {
            return false;
        }
        if (!(drawable instanceof DrawableContainer)) {
            if (drawable instanceof h0.c) {
                return a(((h0.c) drawable).b());
            }
            if (drawable instanceof n.c) {
                return a(((n.c) drawable).a());
            }
            if (drawable instanceof ScaleDrawable) {
                return a(((ScaleDrawable) drawable).getDrawable());
            }
            return true;
        }
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (!(constantState instanceof DrawableContainer.DrawableContainerState)) {
            return true;
        }
        for (Drawable drawable2 : ((DrawableContainer.DrawableContainerState) constantState).getChildren()) {
            if (!a(drawable2)) {
                return false;
            }
        }
        return true;
    }

    public static void b(Drawable drawable) {
        if (Build.VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
            c(drawable);
        }
    }

    public static void c(Drawable drawable) {
        int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(f7616a);
        } else {
            drawable.setState(f7617b);
        }
        drawable.setState(state);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static Rect d(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 29) {
            Insets opticalInsets = drawable.getOpticalInsets();
            Rect rect = new Rect();
            rect.left = opticalInsets.left;
            rect.right = opticalInsets.right;
            rect.top = opticalInsets.top;
            rect.bottom = opticalInsets.bottom;
            return rect;
        }
        if (f7619d != null) {
            try {
                Drawable drawableQ = h0.a.q(drawable);
                Object objInvoke = drawableQ.getClass().getMethod("getOpticalInsets", new Class[0]).invoke(drawableQ, new Object[0]);
                if (objInvoke != null) {
                    Rect rect2 = new Rect();
                    for (Field field : f7619d.getFields()) {
                        String name = field.getName();
                        byte b8 = -1;
                        switch (name.hashCode()) {
                            case -1383228885:
                                if (name.equals("bottom")) {
                                    b8 = 3;
                                }
                                break;
                            case 115029:
                                if (name.equals("top")) {
                                    b8 = 1;
                                }
                                break;
                            case 3317767:
                                if (name.equals("left")) {
                                    b8 = 0;
                                }
                                break;
                            case 108511772:
                                if (name.equals("right")) {
                                    b8 = 2;
                                }
                                break;
                        }
                        if (b8 == 0) {
                            rect2.left = field.getInt(objInvoke);
                        } else if (b8 == 1) {
                            rect2.top = field.getInt(objInvoke);
                        } else if (b8 == 2) {
                            rect2.right = field.getInt(objInvoke);
                        } else if (b8 == 3) {
                            rect2.bottom = field.getInt(objInvoke);
                        }
                    }
                    return rect2;
                }
            } catch (Exception unused) {
                Log.e("DrawableUtils", "Couldn't obtain the optical insets. Ignoring.");
            }
        }
        return f7618c;
    }

    public static PorterDuff.Mode e(int i8, PorterDuff.Mode mode) {
        if (i8 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i8 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i8 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i8) {
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
