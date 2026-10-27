package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class B2 {
    public static Method a;
    public static boolean b;

    public static void a(Drawable drawable, int i) {
        drawable.setTint(i);
    }

    public static void b(Drawable drawable, ColorStateList colorStateList) {
        drawable.setTintList(colorStateList);
    }

    public static void c(Drawable drawable, PorterDuff.Mode mode) {
        drawable.setTintMode(mode);
    }

    public static Drawable d(Drawable drawable) {
        return (Build.VERSION.SDK_INT < 23 && !(drawable instanceof C2)) ? new H2(drawable) : drawable;
    }
}
