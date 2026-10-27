package z1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: DrawableDecoderCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile boolean f10043a = true;

    public static Drawable a(Context context, int i7, Resources.Theme theme) {
        return c(context, context, i7, theme);
    }

    public static Drawable b(Context context, Context context2, int i7) {
        return c(context, context2, i7, null);
    }

    private static Drawable c(Context context, Context context2, int i7, Resources.Theme theme) {
        try {
            if (f10043a) {
                return e(context2, i7, theme);
            }
        } catch (Resources.NotFoundException unused) {
        } catch (IllegalStateException e7) {
            if (context.getPackageName().equals(context2.getPackageName())) {
                throw e7;
            }
            return x.a.c(context2, i7);
        } catch (NoClassDefFoundError unused2) {
            f10043a = false;
        }
        if (theme == null) {
            theme = context2.getTheme();
        }
        return d(context2, i7, theme);
    }

    private static Drawable d(Context context, int i7, Resources.Theme theme) {
        return y.b.b(context.getResources(), i7, theme);
    }

    private static Drawable e(Context context, int i7, Resources.Theme theme) {
        if (theme != null) {
            context = new i.d(context, theme);
        }
        return f.a.b(context, i7);
    }
}
