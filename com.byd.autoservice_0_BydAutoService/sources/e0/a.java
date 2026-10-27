package e0;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: DrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Method f2785a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f2786b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f2787c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f2788d;

    public static void a(Drawable drawable, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.applyTheme(theme);
        }
    }

    public static boolean b(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 21) {
            return drawable.canApplyTheme();
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void c(Drawable drawable) {
        DrawableContainer.DrawableContainerState drawableContainerState;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 23) {
            drawable.clearColorFilter();
            return;
        }
        if (i3 < 21) {
            drawable.clearColorFilter();
            return;
        }
        drawable.clearColorFilter();
        if (drawable instanceof InsetDrawable) {
            c(((InsetDrawable) drawable).getDrawable());
            return;
        }
        if (drawable instanceof c) {
            c(((c) drawable).a());
            return;
        }
        if (!(drawable instanceof DrawableContainer) || (drawableContainerState = (DrawableContainer.DrawableContainerState) ((DrawableContainer) drawable).getConstantState()) == null) {
            return;
        }
        int childCount = drawableContainerState.getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            Drawable child = drawableContainerState.getChild(i4);
            if (child != null) {
                c(child);
            }
        }
    }

    public static int d(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 19) {
            return drawable.getAlpha();
        }
        return 0;
    }

    public static ColorFilter e(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 21) {
            return drawable.getColorFilter();
        }
        return null;
    }

    public static int f(Drawable drawable) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 23) {
            return drawable.getLayoutDirection();
        }
        if (i3 >= 17) {
            if (!f2788d) {
                try {
                    Method declaredMethod = Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
                    f2787c = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (NoSuchMethodException e3) {
                    Log.i("DrawableCompat", "Failed to retrieve getLayoutDirection() method", e3);
                }
                f2788d = true;
            }
            Method method = f2787c;
            if (method != null) {
                try {
                    return ((Integer) method.invoke(drawable, new Object[0])).intValue();
                } catch (Exception e4) {
                    Log.i("DrawableCompat", "Failed to invoke getLayoutDirection() via reflection", e4);
                    f2787c = null;
                }
            }
        }
        return 0;
    }

    public static void g(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
        } else {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        }
    }

    public static boolean h(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 19) {
            return drawable.isAutoMirrored();
        }
        return false;
    }

    @Deprecated
    public static void i(Drawable drawable) {
        drawable.jumpToCurrentState();
    }

    public static void j(Drawable drawable, boolean z3) {
        if (Build.VERSION.SDK_INT >= 19) {
            drawable.setAutoMirrored(z3);
        }
    }

    public static void k(Drawable drawable, float f3, float f4) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setHotspot(f3, f4);
        }
    }

    public static void l(Drawable drawable, int i3, int i4, int i5, int i6) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setHotspotBounds(i3, i4, i5, i6);
        }
    }

    public static boolean m(Drawable drawable, int i3) {
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 23) {
            return drawable.setLayoutDirection(i3);
        }
        if (i4 >= 17) {
            if (!f2786b) {
                try {
                    Method declaredMethod = Drawable.class.getDeclaredMethod("setLayoutDirection", Integer.TYPE);
                    f2785a = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (NoSuchMethodException e3) {
                    Log.i("DrawableCompat", "Failed to retrieve setLayoutDirection(int) method", e3);
                }
                f2786b = true;
            }
            Method method = f2785a;
            if (method != null) {
                try {
                    method.invoke(drawable, Integer.valueOf(i3));
                    return true;
                } catch (Exception e4) {
                    Log.i("DrawableCompat", "Failed to invoke setLayoutDirection(int) via reflection", e4);
                    f2785a = null;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void n(Drawable drawable, int i3) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setTint(i3);
        } else if (drawable instanceof b) {
            ((b) drawable).setTint(i3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void o(Drawable drawable, ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setTintList(colorStateList);
        } else if (drawable instanceof b) {
            ((b) drawable).setTintList(colorStateList);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void p(Drawable drawable, PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setTintMode(mode);
        } else if (drawable instanceof b) {
            ((b) drawable).setTintMode(mode);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Drawable> T q(Drawable drawable) {
        return drawable instanceof c ? (T) ((c) drawable).a() : drawable;
    }

    public static Drawable r(Drawable drawable) {
        int i3 = Build.VERSION.SDK_INT;
        return i3 >= 23 ? drawable : i3 >= 21 ? !(drawable instanceof b) ? new e(drawable) : drawable : !(drawable instanceof b) ? new d(drawable) : drawable;
    }
}
