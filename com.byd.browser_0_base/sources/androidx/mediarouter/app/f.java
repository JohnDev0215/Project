package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$color;
import androidx.mediarouter.R$style;
import defpackage.bz;
import defpackage.gv;

/* JADX INFO: compiled from: MediaRouterThemeHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static final int a = R$color.mr_dynamic_dialog_icon_light;

    public static ContextThemeWrapper a(int i, Context context, boolean z) {
        if (i == 0) {
            i = h(context, !z ? R.attr.dialogTheme : R.attr.alertDialogTheme);
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
        return h(contextThemeWrapper, R$attr.mediaRouteTheme) != 0 ? new ContextThemeWrapper(contextThemeWrapper, f(contextThemeWrapper)) : contextThemeWrapper;
    }

    public static int b(ContextThemeWrapper contextThemeWrapper) {
        int iH = h(contextThemeWrapper, R$attr.mediaRouteTheme);
        return iH == 0 ? f(contextThemeWrapper) : iH;
    }

    public static int c(Context context, int i) {
        return gv.c(-1, g(context, i, R.attr.colorPrimary)) >= 3.0d ? -1 : -570425344;
    }

    public static float d(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.disabledAlpha, typedValue, true)) {
            return typedValue.getFloat();
        }
        return 0.5f;
    }

    public static Drawable e(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        Drawable drawableA = AppCompatResources.a(context, typedArrayObtainStyledAttributes.getResourceId(0, 0));
        if (i(context)) {
            Object obj = bz.a;
            drawableA.setTint(context.getColor(a));
        }
        typedArrayObtainStyledAttributes.recycle();
        return drawableA;
    }

    public static int f(Context context) {
        return i(context) ? c(context, 0) == -570425344 ? R$style.Theme_MediaRouter_Light : R$style.Theme_MediaRouter_Light_DarkControlPanel : c(context, 0) == -570425344 ? R$style.Theme_MediaRouter_LightControlPanel : R$style.Theme_MediaRouter;
    }

    public static int g(Context context, int i, int i2) {
        if (i != 0) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, new int[]{i2});
            int color = typedArrayObtainStyledAttributes.getColor(0, 0);
            typedArrayObtainStyledAttributes.recycle();
            if (color != 0) {
                return color;
            }
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i2, typedValue, true);
        return typedValue.resourceId != 0 ? context.getResources().getColor(typedValue.resourceId) : typedValue.data;
    }

    public static int h(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            return typedValue.resourceId;
        }
        return 0;
    }

    public static boolean i(Context context) {
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(R.attr.isLightTheme, typedValue, true) && typedValue.data != 0;
    }

    public static void j(Context context, Dialog dialog) {
        View decorView = dialog.getWindow().getDecorView();
        int i = i(context) ? R$color.mr_dynamic_dialog_background_light : R$color.mr_dynamic_dialog_background_dark;
        Object obj = bz.a;
        decorView.setBackgroundColor(context.getColor(i));
    }

    public static void k(Context context, ProgressBar progressBar) {
        if (progressBar.isIndeterminate()) {
            int i = i(context) ? R$color.mr_cast_progressbar_progress_and_thumb_light : R$color.mr_cast_progressbar_progress_and_thumb_dark;
            Object obj = bz.a;
            progressBar.getIndeterminateDrawable().setColorFilter(context.getColor(i), PorterDuff.Mode.SRC_IN);
        }
    }

    public static void l(Context context, MediaRouteVolumeSlider mediaRouteVolumeSlider, ViewGroup viewGroup) {
        int iC = c(context, 0);
        if (Color.alpha(iC) != 255) {
            iC = gv.f(iC, ((Integer) viewGroup.getTag()).intValue());
        }
        mediaRouteVolumeSlider.a(iC, iC);
    }
}
