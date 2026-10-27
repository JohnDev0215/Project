package com.ts.appservice.settings.impl.container;

import android.content.Context;
import android.os.Bundle;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.container.IContainerSettingsService;
import com.ts.qnxmanager.QnxManager;

/* JADX INFO: loaded from: classes.dex */
public class IContainerServiceBinder extends IContainerSettingsService.Stub {
    private static final String ADAS_DEBUG_MODE = "adasDebugMode";
    private static final int ADAS_DEBUG_OFF_INDEX = 48;
    private static final int ADAS_DEBUG_ON_INDEX = 47;
    private static final String ADAS_SELF_LEARN_DISPLAY = "adasSelfLearn";
    private static final int ADAS_SELF_LEARN_DISPLAY_RESULT_INDEX = 45;
    private static final int ADAS_SELF_LEARN_NO_DISPLAY_RESULT_INDEX = 46;
    private static final int CLUSTER_DEBUG_SUBTYPE_TYPE = 16;
    private static final int CLUSTER_SETTING_MSG_TYPE = 9;
    private static final int OSD_CLUSTER_DISPLAY_INDEX = 1;
    private static final String OSD_CLUSTER_MODE = "osdClusterMode";
    private static final int OSD_CLUSTER_NO_DISPLAY_INDEX = 0;
    private static final String TAG = IContainerServiceBinder.class.getSimpleName();
    private Context mContext;
    private QnxManager mQnxManager;

    public IContainerServiceBinder(Context context) {
        this.mContext = context;
        initQnxManager();
    }

    private void initQnxManager() {
        this.mQnxManager = QnxManager.getInstance();
    }

    public void sendInfo(int index) {
        LogUtil.debug(TAG, "sendInfo");
        Bundle bundle = new Bundle();
        if (index == 0) {
            bundle.putInt(OSD_CLUSTER_MODE, 0);
            this.mQnxManager.sendMessage(9, 16, (String) null, bundle);
        }
        if (index == 1) {
            bundle.putInt(OSD_CLUSTER_MODE, 1);
            this.mQnxManager.sendMessage(9, 16, (String) null, bundle);
            return;
        }
        switch (index) {
            case 45:
                bundle.putInt(ADAS_SELF_LEARN_DISPLAY, 1);
                this.mQnxManager.sendMessage(9, 16, (String) null, bundle);
                break;
            case 46:
                bundle.putInt(ADAS_SELF_LEARN_DISPLAY, 0);
                this.mQnxManager.sendMessage(9, 16, (String) null, bundle);
                break;
            case 47:
                bundle.putInt(ADAS_DEBUG_MODE, 1);
                this.mQnxManager.sendMessage(9, 16, (String) null, bundle);
                break;
            case ADAS_DEBUG_OFF_INDEX /* 48 */:
                bundle.putInt(ADAS_DEBUG_MODE, 0);
                this.mQnxManager.sendMessage(9, 16, (String) null, bundle);
                break;
        }
    }
}
