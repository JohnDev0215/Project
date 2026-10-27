package com.byd.auto_emanual.util;

import android.app.UiStyleConfig;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.R;

/* JADX INFO: loaded from: classes.dex */
public class UIStyleUtil {
    public static final int STYLE_DENZA = 1;
    public static final int STYLE_DYNASTY = 0;
    public static final int STYLE_OCEAN = 2;
    public static final int STYLE_UNDEFINED = -1;

    public static boolean isDynasty() {
        return UiStyleConfig.getInstance(MApp.context).isDynasty();
    }

    public static boolean isDenza() {
        return UiStyleConfig.getInstance(MApp.context).isDenza();
    }

    public static boolean isOcean() {
        return UiStyleConfig.getInstance(MApp.context).isOcean();
    }

    public static int getStyle() {
        return UiStyleConfig.getInstance(MApp.context).getUiStyle();
    }

    public static int getTheme() {
        if (!Utils.is8155F()) {
            return R.style.Theme_UserManual_6125;
        }
        if (isDenza()) {
            return R.style.Theme_UserManual_Denza;
        }
        if (isDynasty()) {
        }
        return R.style.Theme_UserManual_Dynasty;
    }
}
