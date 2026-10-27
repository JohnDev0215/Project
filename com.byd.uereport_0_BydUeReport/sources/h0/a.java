package h0;

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
    public static Method f5104a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f5105b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f5106c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5107d;

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
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 23) {
            drawable.clearColorFilter();
            return;
        }
        if (i8 < 21) {
            drawable.clearColorFilter();
            return;
        }
        drawable.clearColorFilter();
        if (drawable instanceof InsetDrawable) {
            c(((InsetDrawable) drawable).getDrawable());
            return;
        }
        if (drawable instanceof c) {
            c(((c) drawable).b());
            return;
        }
        if (!(drawable instanceof DrawableContainer) || (drawableContainerState = (DrawableContainer.DrawableContainerState) ((DrawableContainer) drawable).getConstantState()) == null) {
            return;
        }
        int childCount = drawableContainerState.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            Drawable child = drawableContainerState.getChild(i9);
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
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 23) {
            return drawable.getLayoutDirection();
        }
        if (i8 >= 17) {
            if (!f5107d) {
                try {
                    Method declaredMethod = Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
                    f5106c = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (NoSuchMethodException e8) {
                    Log.i("DrawableCompat", "Failed to retrieve getLayoutDirection() method", e8);
                }
                f5107d = true;
            }
            Method method = f5106c;
            if (method != null) {
                try {
                    return ((Integer) method.invoke(drawable, new Object[0])).intValue();
                } catch (Exception e9) {
                    Log.i("DrawableCompat", "Failed to invoke getLayoutDirection() via reflection", e9);
                    f5106c = null;
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

    public static void j(Drawable drawable, boolean z7) {
        if (Build.VERSION.SDK_INT >= 19) {
            drawable.setAutoMirrored(z7);
        }
    }

    public static void k(Drawable drawable, float f8, float f9) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setHotspot(f8, f9);
        }
    }

    public static void l(Drawable drawable, int i8, int i9, int i10, int i11) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setHotspotBounds(i8, i9, i10, i11);
        }
    }

    public static boolean m(Drawable drawable, int i8) {
        int i9 = Build.VERSION.SDK_INT;
        if (i9 >= 23) {
            return drawable.setLayoutDirection(i8);
        }
        if (i9 >= 17) {
            if (!f5105b) {
                try {
                    Method declaredMethod = Drawable.class.getDeclaredMethod("setLayoutDirection", Integer.TYPE);
                    f5104a = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (NoSuchMethodException e8) {
                    Log.i("DrawableCompat", "Failed to retrieve setLayoutDirection(int) method", e8);
                }
                f5105b = true;
            }
            Method method = f5104a;
            if (method != null) {
                try {
                    method.invoke(drawable, Integer.valueOf(i8));
                    return true;
                } catch (Exception e9) {
                    Log.i("DrawableCompat", "Failed to invoke setLayoutDirection(int) via reflection", e9);
                    f5104a = null;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void n(Drawable drawable, int i8) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setTint(i8);
        } else if (drawable instanceof b) {
            ((b) drawable).setTint(i8);
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
        return drawable instanceof c ? (T) ((c) drawable).b() : drawable;
    }

    public static Drawable r(Drawable drawable) {
        int i8 = Build.VERSION.SDK_INT;
        return i8 >= 23 ? drawable : i8 >= 21 ? !(drawable instanceof b) ? new e(drawable) : drawable : !(drawable instanceof b) ? new d(drawable) : drawable;
    }
}
