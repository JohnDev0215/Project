package com.android.systemui.util;

import android.app.UiStyleConfig;
import com.android.systemui.SystemUIApplication;
import com.byd.card.floatcard.SpUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.skin.entity.AttrFactory;

/* JADX INFO: loaded from: classes.dex */
public class StyleUtil {
    public static final String PREFIX_DENZA = "denza_";
    public static int sUiStyle = Integer.MAX_VALUE;

    public static boolean isDenza() {
        return getStyle() == 1;
    }

    public static int getStyle() {
        if (sUiStyle == Integer.MAX_VALUE) {
            int uiStyle = UiStyleConfig.getInstance(SystemUIApplication.getInstance().getApplicationContext()).getUiStyle();
            sUiStyle = uiStyle;
            BydLogHelper.logD("sUiStyle = " + uiStyle);
        }
        return sUiStyle;
    }

    private static int getRes(String resName, int defaultValue, String resType) {
        int style = getStyle();
        if (style != 0) {
            if (style == 1) {
                resName = PREFIX_DENZA + resName;
            }
            if (resName != null) {
                int identifier = SystemUIApplication.getInstance().getResources().getIdentifier(resName, resType, "com.android.systemui");
                if (identifier != 0) {
                    return identifier;
                }
                BydLogHelper.logE("resource id not found " + resName);
            }
        }
        return defaultValue;
    }

    public static int getResMipmap(int defaultValue, String resName) {
        return getRes(resName, defaultValue, "mipmap");
    }

    public static int getResDrawable(int defaultValue, String resName) {
        return getRes(resName, defaultValue, AttrFactory.DRAWABLE);
    }

    public static int getResLayout(int layoutRes, String resName) {
        return getRes(resName, layoutRes, "layout");
    }

    public static int getResColor(int colorRes, String resName) {
        return getRes(resName, colorRes, "color");
    }

    public static int getResStyle(int styleRes, String resName) {
        return getRes(resName, styleRes, SpUtils.STYLE);
    }
}
