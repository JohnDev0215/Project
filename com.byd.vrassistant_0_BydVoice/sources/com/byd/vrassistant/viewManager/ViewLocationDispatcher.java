package com.byd.vrassistant.viewManager;

import android.view.ViewGroup;
import android.view.WindowManager;
import com.byd.vrassistant.bean.WakeVrArea;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SizeUtils;
import com.byd.vrassistant.view.ViewManager;
import com.google.android.material.badge.BadgeDrawable;

/* JADX INFO: loaded from: classes2.dex */
public class ViewLocationDispatcher {
    private static final String TAG = "ViewLocationDispatcher";

    public static ViewGroup.LayoutParams getParams(String str) {
        WakeVrArea wakeVrAreaWakeArea = ViewManager.getVoiceAreaHelper().wakeArea(str);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.format = -3;
        layoutParams.type = 2010;
        layoutParams.width = -2;
        layoutParams.height = -2;
        RLog.i(TAG, "getBarParams_isRight==" + BridgeHelper.getVrAreaStatus().isRightDriver() + ",wakeArea=" + wakeVrAreaWakeArea);
        if (wakeVrAreaWakeArea == WakeVrArea.LEFT || (wakeVrAreaWakeArea == WakeVrArea.FRONT && !BridgeHelper.getVrAreaStatus().isRightDriver())) {
            layoutParams.gravity = BadgeDrawable.TOP_START;
        } else {
            layoutParams.gravity = BadgeDrawable.TOP_END;
        }
        layoutParams.x = SizeUtils.dp2px(24.0f);
        layoutParams.y = SizeUtils.dp2px(8.0f);
        RLog.i(TAG, "Sebastian getBarParams: params.x = " + layoutParams.x + "params.y" + layoutParams.y);
        layoutParams.flags = 296;
        return layoutParams;
    }

    public static WindowManager.LayoutParams getPopInfo() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.format = -3;
        layoutParams.type = 2038;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = BadgeDrawable.TOP_START;
        layoutParams.flags = 1280;
        return layoutParams;
    }
}
