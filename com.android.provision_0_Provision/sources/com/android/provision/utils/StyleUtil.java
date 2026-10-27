package com.android.provision.utils;

import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.android.provision.BootGuideApplication;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.skin.entity.AttrFactory;

/* JADX INFO: loaded from: classes.dex */
public class StyleUtil {
    public static final String PREFIX_DENZA = "denza_";
    private static final String TAG = "StyleUtil";
    public static int sUiStyle = Integer.MAX_VALUE;

    public static boolean isDenza() {
        return getStyle() == 1;
    }

    public static int getStyle() {
        if (sUiStyle == Integer.MAX_VALUE) {
            String str = SystemProperties.get("persist.sys.ui_style");
            if (!TextUtils.isEmpty(str)) {
                try {
                    sUiStyle = Integer.parseInt(str);
                } catch (Throwable th) {
                    Log.e(TAG, "sUiStyle = " + str, th);
                    sUiStyle = 0;
                }
            } else {
                sUiStyle = 0;
            }
            BydLogHelper.logW(TAG, "sUiStyle = " + str);
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
                int identifier = BootGuideApplication.getAppContext().getResources().getIdentifier(resName, resType, "com.android.systemui");
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
        return getRes(resName, styleRes, "style");
    }
}
