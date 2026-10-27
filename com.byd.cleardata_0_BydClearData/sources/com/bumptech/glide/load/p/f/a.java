package com.bumptech.glide.load.p.f;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.core.content.c.f;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile boolean f2383a = true;

    public static Drawable a(Context context, int i, Resources.Theme theme) {
        return c(context, context, i, theme);
    }

    public static Drawable b(Context context, Context context2, int i) {
        return c(context, context2, i, null);
    }

    private static Drawable c(Context context, Context context2, int i, Resources.Theme theme) {
        try {
            if (f2383a) {
                return e(context2, i, theme);
            }
        } catch (Resources.NotFoundException unused) {
        } catch (IllegalStateException e) {
            if (context.getPackageName().equals(context2.getPackageName())) {
                throw e;
            }
            return androidx.core.content.a.d(context2, i);
        } catch (NoClassDefFoundError unused2) {
            f2383a = false;
        }
        if (theme == null) {
            theme = context2.getTheme();
        }
        return d(context2, i, theme);
    }

    private static Drawable d(Context context, int i, Resources.Theme theme) {
        return f.a(context.getResources(), i, theme);
    }

    private static Drawable e(Context context, int i, Resources.Theme theme) {
        if (theme != null) {
            context = new a.a.n.d(context, theme);
        }
        return a.a.k.a.a.d(context, i);
    }
}
