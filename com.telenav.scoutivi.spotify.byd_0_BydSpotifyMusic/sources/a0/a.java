package a0;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: DrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static void a(Drawable drawable) {
        drawable.clearColorFilter();
    }

    public static int b(Drawable drawable) {
        return drawable.getLayoutDirection();
    }

    public static boolean c(Drawable drawable) {
        return drawable.isAutoMirrored();
    }

    public static void d(Drawable drawable, boolean z6) {
        drawable.setAutoMirrored(z6);
    }

    public static void e(Drawable drawable, float f7, float f8) {
        drawable.setHotspot(f7, f8);
    }

    public static void f(Drawable drawable, int i7, int i8, int i9, int i10) {
        drawable.setHotspotBounds(i7, i8, i9, i10);
    }

    public static boolean g(Drawable drawable, int i7) {
        return drawable.setLayoutDirection(i7);
    }

    public static void h(Drawable drawable, int i7) {
        drawable.setTint(i7);
    }

    public static void i(Drawable drawable, ColorStateList colorStateList) {
        drawable.setTintList(colorStateList);
    }

    public static void j(Drawable drawable, PorterDuff.Mode mode) {
        drawable.setTintMode(mode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Drawable> T k(Drawable drawable) {
        return drawable instanceof b ? (T) ((b) drawable).a() : drawable;
    }

    public static Drawable l(Drawable drawable) {
        return drawable;
    }
}
