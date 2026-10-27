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
import android.widget.ProgressBar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.mediarouter.R;

/* JADX INFO: loaded from: classes2.dex */
final class MediaRouterThemeHelper {
    static final int COLOR_DARK_ON_LIGHT_BACKGROUND = -570425344;
    private static final int COLOR_DARK_ON_LIGHT_BACKGROUND_RES_ID = R.color.mr_dynamic_dialog_icon_light;
    static final int COLOR_WHITE_ON_DARK_BACKGROUND = -1;
    private static final float MIN_CONTRAST = 3.0f;

    private MediaRouterThemeHelper() {
    }

    static Drawable getMuteButtonDrawableIcon(Context context) {
        return getIconByDrawableId(context, R.drawable.mr_cast_mute_button);
    }

    static Drawable getCheckBoxDrawableIcon(Context context) {
        return getIconByDrawableId(context, R.drawable.mr_cast_checkbox);
    }

    static Drawable getDefaultDrawableIcon(Context context) {
        return getIconByAttrId(context, R.attr.mediaRouteDefaultIconDrawable);
    }

    static Drawable getTvDrawableIcon(Context context) {
        return getIconByAttrId(context, R.attr.mediaRouteTvIconDrawable);
    }

    static Drawable getSpeakerDrawableIcon(Context context) {
        return getIconByAttrId(context, R.attr.mediaRouteSpeakerIconDrawable);
    }

    static Drawable getSpeakerGroupDrawableIcon(Context context) {
        return getIconByAttrId(context, R.attr.mediaRouteSpeakerGroupIconDrawable);
    }

    private static Drawable getIconByDrawableId(Context context, int drawableId) {
        Drawable icon = DrawableCompat.wrap(ContextCompat.getDrawable(context, drawableId));
        if (isLightTheme(context)) {
            int tintColor = ContextCompat.getColor(context, COLOR_DARK_ON_LIGHT_BACKGROUND_RES_ID);
            DrawableCompat.setTint(icon, tintColor);
        }
        return icon;
    }

    private static Drawable getIconByAttrId(Context context, int attrId) {
        TypedArray styledAttributes = context.obtainStyledAttributes(new int[]{attrId});
        Drawable icon = DrawableCompat.wrap(styledAttributes.getDrawable(0));
        if (isLightTheme(context)) {
            int tintColor = ContextCompat.getColor(context, COLOR_DARK_ON_LIGHT_BACKGROUND_RES_ID);
            DrawableCompat.setTint(icon, tintColor);
        }
        styledAttributes.recycle();
        return icon;
    }

    static Context createThemedButtonContext(Context context) {
        Context context2 = new ContextThemeWrapper(context, getRouterThemeId(context));
        int style = getThemeResource(context2, R.attr.mediaRouteTheme);
        return style != 0 ? new ContextThemeWrapper(context2, style) : context2;
    }

    static Context createThemedDialogContext(Context context, int theme, boolean alertDialog) {
        int i;
        if (theme == 0) {
            if (!alertDialog) {
                i = androidx.appcompat.R.attr.dialogTheme;
            } else {
                i = androidx.appcompat.R.attr.alertDialogTheme;
            }
            theme = getThemeResource(context, i);
        }
        Context context2 = new ContextThemeWrapper(context, theme);
        return getThemeResource(context2, R.attr.mediaRouteTheme) != 0 ? new ContextThemeWrapper(context2, getRouterThemeId(context2)) : context2;
    }

    static int createThemedDialogStyle(Context context) {
        int theme = getThemeResource(context, R.attr.mediaRouteTheme);
        if (theme == 0) {
            return getRouterThemeId(context);
        }
        return theme;
    }

    static int getThemeResource(Context context, int attr) {
        TypedValue value = new TypedValue();
        if (context.getTheme().resolveAttribute(attr, value, true)) {
            return value.resourceId;
        }
        return 0;
    }

    static float getDisabledAlpha(Context context) {
        TypedValue value = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.disabledAlpha, value, true)) {
            return value.getFloat();
        }
        return 0.5f;
    }

    static int getControllerColor(Context context, int style) {
        int primaryColor = getThemeColor(context, style, androidx.appcompat.R.attr.colorPrimary);
        if (ColorUtils.calculateContrast(-1, primaryColor) >= 3.0d) {
            return -1;
        }
        return COLOR_DARK_ON_LIGHT_BACKGROUND;
    }

    static int getButtonTextColor(Context context) {
        int primaryColor = getThemeColor(context, 0, androidx.appcompat.R.attr.colorPrimary);
        int backgroundColor = getThemeColor(context, 0, android.R.attr.colorBackground);
        if (ColorUtils.calculateContrast(primaryColor, backgroundColor) < 3.0d) {
            return getThemeColor(context, 0, androidx.appcompat.R.attr.colorAccent);
        }
        return primaryColor;
    }

    static TypedArray getStyledAttributes(Context context) {
        TypedArray styledAttributes = context.obtainStyledAttributes(new int[]{R.attr.mediaRouteDefaultIconDrawable, R.attr.mediaRouteTvIconDrawable, R.attr.mediaRouteSpeakerIconDrawable, R.attr.mediaRouteSpeakerGroupIconDrawable});
        return styledAttributes;
    }

    static void setDialogBackgroundColor(Context context, Dialog dialog) {
        int i;
        View dialogView = dialog.getWindow().getDecorView();
        if (isLightTheme(context)) {
            i = R.color.mr_dynamic_dialog_background_light;
        } else {
            i = R.color.mr_dynamic_dialog_background_dark;
        }
        int backgroundColor = ContextCompat.getColor(context, i);
        dialogView.setBackgroundColor(backgroundColor);
    }

    static void setMediaControlsBackgroundColor(Context context, View mainControls, View groupControls, boolean hasGroup) {
        int primaryColor = getThemeColor(context, 0, androidx.appcompat.R.attr.colorPrimary);
        int primaryDarkColor = getThemeColor(context, 0, androidx.appcompat.R.attr.colorPrimaryDark);
        if (hasGroup && getControllerColor(context, 0) == COLOR_DARK_ON_LIGHT_BACKGROUND) {
            primaryDarkColor = primaryColor;
            primaryColor = -1;
        }
        mainControls.setBackgroundColor(primaryColor);
        groupControls.setBackgroundColor(primaryDarkColor);
        mainControls.setTag(Integer.valueOf(primaryColor));
        groupControls.setTag(Integer.valueOf(primaryDarkColor));
    }

    static void setVolumeSliderColor(Context context, MediaRouteVolumeSlider volumeSlider, View backgroundView) {
        int controllerColor = getControllerColor(context, 0);
        if (Color.alpha(controllerColor) != 255) {
            int backgroundColor = ((Integer) backgroundView.getTag()).intValue();
            controllerColor = ColorUtils.compositeColors(controllerColor, backgroundColor);
        }
        volumeSlider.setColor(controllerColor);
    }

    static void setVolumeSliderColor(Context context, MediaRouteVolumeSlider volumeSlider) {
        int progressAndThumbColor;
        int backgroundColor;
        if (isLightTheme(context)) {
            progressAndThumbColor = ContextCompat.getColor(context, R.color.mr_cast_progressbar_progress_and_thumb_light);
            backgroundColor = ContextCompat.getColor(context, R.color.mr_cast_progressbar_background_light);
        } else {
            int progressAndThumbColor2 = R.color.mr_cast_progressbar_progress_and_thumb_dark;
            progressAndThumbColor = ContextCompat.getColor(context, progressAndThumbColor2);
            backgroundColor = ContextCompat.getColor(context, R.color.mr_cast_progressbar_background_dark);
        }
        volumeSlider.setColor(progressAndThumbColor, backgroundColor);
    }

    static void setIndeterminateProgressBarColor(Context context, ProgressBar progressBar) {
        int i;
        if (!progressBar.isIndeterminate()) {
            return;
        }
        if (isLightTheme(context)) {
            i = R.color.mr_cast_progressbar_progress_and_thumb_light;
        } else {
            i = R.color.mr_cast_progressbar_progress_and_thumb_dark;
        }
        int progressColor = ContextCompat.getColor(context, i);
        progressBar.getIndeterminateDrawable().setColorFilter(progressColor, PorterDuff.Mode.SRC_IN);
    }

    private static boolean isLightTheme(Context context) {
        TypedValue value = new TypedValue();
        return context.getTheme().resolveAttribute(androidx.appcompat.R.attr.isLightTheme, value, true) && value.data != 0;
    }

    private static int getThemeColor(Context context, int style, int attr) {
        if (style != 0) {
            int[] attrs = {attr};
            TypedArray ta = context.obtainStyledAttributes(style, attrs);
            int color = ta.getColor(0, 0);
            ta.recycle();
            if (color != 0) {
                return color;
            }
        }
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attr, value, true);
        if (value.resourceId != 0) {
            return context.getResources().getColor(value.resourceId);
        }
        return value.data;
    }

    private static int getRouterThemeId(Context context) {
        if (isLightTheme(context)) {
            if (getControllerColor(context, 0) == COLOR_DARK_ON_LIGHT_BACKGROUND) {
                int themeId = R.style.Theme_MediaRouter_Light;
                return themeId;
            }
            int themeId2 = R.style.Theme_MediaRouter_Light_DarkControlPanel;
            return themeId2;
        }
        int themeId3 = getControllerColor(context, 0);
        if (themeId3 == COLOR_DARK_ON_LIGHT_BACKGROUND) {
            int themeId4 = R.style.Theme_MediaRouter_LightControlPanel;
            return themeId4;
        }
        int themeId5 = R.style.Theme_MediaRouter;
        return themeId5;
    }
}
