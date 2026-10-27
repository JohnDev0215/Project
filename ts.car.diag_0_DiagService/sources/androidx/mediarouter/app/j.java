package androidx.mediarouter.app;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ProgressBar;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$color;
import androidx.mediarouter.R$drawable;
import androidx.mediarouter.R$style;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f907a = R$color.mr_dynamic_dialog_icon_light;

    static Context a(Context context) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, l(context));
        int iP = p(contextThemeWrapper, R$attr.mediaRouteTheme);
        return iP != 0 ? new ContextThemeWrapper(contextThemeWrapper, iP) : contextThemeWrapper;
    }

    static Context b(Context context, int i, boolean z) {
        if (i == 0) {
            i = p(context, !z ? androidx.appcompat.R$attr.dialogTheme : androidx.appcompat.R$attr.alertDialogTheme);
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
        return p(contextThemeWrapper, R$attr.mediaRouteTheme) != 0 ? new ContextThemeWrapper(contextThemeWrapper, l(contextThemeWrapper)) : contextThemeWrapper;
    }

    static int c(Context context) {
        int iP = p(context, R$attr.mediaRouteTheme);
        return iP == 0 ? l(context) : iP;
    }

    static int d(Context context) {
        int iO = o(context, 0, androidx.appcompat.R$attr.colorPrimary);
        return androidx.core.graphics.a.b(iO, o(context, 0, R.attr.colorBackground)) < 3.0d ? o(context, 0, androidx.appcompat.R$attr.colorAccent) : iO;
    }

    static Drawable e(Context context) {
        return j(context, R$drawable.mr_cast_checkbox);
    }

    static int f(Context context, int i) {
        return androidx.core.graphics.a.b(-1, o(context, i, androidx.appcompat.R$attr.colorPrimary)) >= 3.0d ? -1 : -570425344;
    }

    static Drawable g(Context context) {
        return i(context, R$attr.mediaRouteDefaultIconDrawable);
    }

    static float h(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.disabledAlpha, typedValue, true)) {
            return typedValue.getFloat();
        }
        return 0.5f;
    }

    private static Drawable i(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        Drawable drawableQ = androidx.core.graphics.drawable.a.q(typedArrayObtainStyledAttributes.getDrawable(0));
        if (r(context)) {
            androidx.core.graphics.drawable.a.m(drawableQ, androidx.core.content.a.b(context, f907a));
        }
        typedArrayObtainStyledAttributes.recycle();
        return drawableQ;
    }

    private static Drawable j(Context context, int i) {
        Drawable drawableQ = androidx.core.graphics.drawable.a.q(androidx.core.content.a.d(context, i));
        if (r(context)) {
            androidx.core.graphics.drawable.a.m(drawableQ, androidx.core.content.a.b(context, f907a));
        }
        return drawableQ;
    }

    static Drawable k(Context context) {
        return j(context, R$drawable.mr_cast_mute_button);
    }

    private static int l(Context context) {
        return r(context) ? f(context, 0) == -570425344 ? R$style.Theme_MediaRouter_Light : R$style.Theme_MediaRouter_Light_DarkControlPanel : f(context, 0) == -570425344 ? R$style.Theme_MediaRouter_LightControlPanel : R$style.Theme_MediaRouter;
    }

    static Drawable m(Context context) {
        return i(context, R$attr.mediaRouteSpeakerIconDrawable);
    }

    static Drawable n(Context context) {
        return i(context, R$attr.mediaRouteSpeakerGroupIconDrawable);
    }

    private static int o(Context context, int i, int i2) {
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

    static int p(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            return typedValue.resourceId;
        }
        return 0;
    }

    static Drawable q(Context context) {
        return i(context, R$attr.mediaRouteTvIconDrawable);
    }

    private static boolean r(Context context) {
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(androidx.appcompat.R$attr.isLightTheme, typedValue, true) && typedValue.data != 0;
    }

    static void s(Context context, Dialog dialog) {
        dialog.getWindow().getDecorView().setBackgroundColor(androidx.core.content.a.b(context, r(context) ? R$color.mr_dynamic_dialog_background_light : R$color.mr_dynamic_dialog_background_dark));
    }

    static void t(Context context, ProgressBar progressBar) {
        if (progressBar.isIndeterminate()) {
            progressBar.getIndeterminateDrawable().setColorFilter(androidx.core.content.a.b(context, r(context) ? R$color.mr_cast_progressbar_progress_and_thumb_light : R$color.mr_cast_progressbar_progress_and_thumb_dark), PorterDuff.Mode.SRC_IN);
        }
    }

    static void u(Context context, View view, View view2, boolean z) {
        int iO = o(context, 0, androidx.appcompat.R$attr.colorPrimary);
        int iO2 = o(context, 0, androidx.appcompat.R$attr.colorPrimaryDark);
        if (z && f(context, 0) == -570425344) {
            iO2 = iO;
            iO = -1;
        }
        view.setBackgroundColor(iO);
        view2.setBackgroundColor(iO2);
        view.setTag(Integer.valueOf(iO));
        view2.setTag(Integer.valueOf(iO2));
    }

    static void v(Context context, MediaRouteVolumeSlider mediaRouteVolumeSlider) {
        int iB;
        int iB2;
        if (r(context)) {
            iB = androidx.core.content.a.b(context, R$color.mr_cast_progressbar_progress_and_thumb_light);
            iB2 = androidx.core.content.a.b(context, R$color.mr_cast_progressbar_background_light);
        } else {
            iB = androidx.core.content.a.b(context, R$color.mr_cast_progressbar_progress_and_thumb_dark);
            iB2 = androidx.core.content.a.b(context, R$color.mr_cast_progressbar_background_dark);
        }
        mediaRouteVolumeSlider.b(iB, iB2);
    }

    static void w(Context context, MediaRouteVolumeSlider mediaRouteVolumeSlider, View view) {
        int iF = f(context, 0);
        if (Color.alpha(iF) != 255) {
            iF = androidx.core.graphics.a.f(iF, ((Integer) view.getTag()).intValue());
        }
        mediaRouteVolumeSlider.a(iF);
    }
}
