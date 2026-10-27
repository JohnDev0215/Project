package com.byd.music.style;

import android.content.Context;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class UiStyleManager {
    private static final UiStyleManager INSTANCE = new UiStyleManager();
    private final UiStyleApi mUiStyleApi;

    private UiStyleManager() {
        int uiStyle = BydUiStyleConfig.getUiStyle();
        LogUtil.e("UiStyleManager", "UiStyleManager init with style = " + uiStyle);
        if (uiStyle == 1) {
            this.mUiStyleApi = new UiStyleDenza();
            return;
        }
        if (uiStyle == 2) {
            this.mUiStyleApi = new UiStyleOcean();
        } else if (uiStyle == 3) {
            this.mUiStyleApi = new UiStyleFancy();
        } else {
            this.mUiStyleApi = new UiStyleDynasty();
        }
    }

    public static UiStyleManager getInstance() {
        return INSTANCE;
    }

    public int getDrawableId(Context context, int i) {
        return this.mUiStyleApi.getDrawableId(context, i);
    }

    public int getColorId(Context context, int i) {
        return this.mUiStyleApi.getColorId(context, i);
    }

    public int getLayout(Context context, int i) {
        return this.mUiStyleApi.getLayout(context, i);
    }
}
