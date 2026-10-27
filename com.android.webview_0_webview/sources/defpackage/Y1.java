package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.io.File;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Y1 {
    public static final Object a = new Object();

    public static ColorStateList a(Context context, int i) {
        return Build.VERSION.SDK_INT >= 23 ? context.getColorStateList(i) : context.getResources().getColorStateList(i);
    }

    public static Drawable b(Context context, int i) {
        return context.getDrawable(i);
    }

    public static File[] c(Context context) {
        return context.getExternalCacheDirs();
    }

    public static File[] d(Context context, String str) {
        return context.getExternalFilesDirs(null);
    }
}
