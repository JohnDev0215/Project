package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.stackdivider.DividerUtils;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class NaviStatusReceiver extends BroadcastReceiver {
    public static final String ACTION = "byd.intent.action.NAVIGATION_STATE_CHANGED";
    private static final String NAVIGATION_PACKAGE_NAME = "packageName";
    private static final String NAVIGATION_STATE = "state";
    public static final int NAVI_STATE_START_NAVI = 1;
    public static final int NAVI_STATE_STOP_NAVI = 0;
    private static final String TAG = "NaviStatusReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BydLogHelper.logD(TAG, "NaviStatusReceiver action = " + action);
        if (ACTION.equals(action)) {
            String stringExtra = intent.getStringExtra(NAVIGATION_PACKAGE_NAME);
            int intExtra = intent.getIntExtra(NAVIGATION_STATE, -1);
            BydLogHelper.logD("Navi status ================================ packageName : " + stringExtra + " = state = " + intExtra);
            if (stringExtra != null) {
                stringExtra.hashCode();
                switch (stringExtra) {
                    case "com.byd.automap":
                    case "net.zmap.znav.byd8155":
                    case "com.neusoft.na.navigation":
                    case "com.huawei.maps.auto.app":
                    case "com.tmap.auto.byd":
                        DividerUtils.getInstance().setNaviStatus(intExtra);
                        break;
                }
            }
        }
    }
}
