package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: DrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class sd0 {
    public static void a(int i, Drawable drawable) {
        drawable.setTint(i);
    }

    public static void b(Drawable drawable, ColorStateList colorStateList) {
        drawable.setTintList(colorStateList);
    }

    public static void c(Drawable drawable, PorterDuff.Mode mode) {
        drawable.setTintMode(mode);
    }
}
