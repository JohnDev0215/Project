package com.amazon.mp3.environment.url;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.inappmessaging.external.model.Stage;

/* JADX INFO: loaded from: classes3.dex */
public class DynMsgEndpointProvider {
    private static final String DEFAULT_OVERRIDE_ID = Stage.PROD.name();

    public String getCurrentOverrideId() {
        return SettingsUtil.EnvironmentPref.getEnvironment(AmazonApplication.getContext(), R.string.setting_key_dev_dynmsg_endpoint, DEFAULT_OVERRIDE_ID);
    }

    public String[] overrideEntries() {
        Stage[] stageArrValues = Stage.values();
        String[] strArr = new String[stageArrValues.length];
        for (int i = 0; i < stageArrValues.length; i++) {
            strArr[i] = stageArrValues[i].toString();
        }
        return strArr;
    }
}
