package com.byd.profile.utils;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceUtil {
    private static final String ACTION_VOICE = "com.byd.uereport.ACTION_VOICE";
    private static final String EXTRA_CONTENT = "content";
    private static final String EXTRA_VOICE_VALUE = "voice_value";
    private static final String TARGET_PACKAGE = "com.byd.uereport";

    public static void remainder(Context context, int resIndex) {
        com.bydux.api.common.tools.log.BydLogHelper.logD("remainder resIndex = " + resIndex);
        Intent intent = new Intent(ACTION_VOICE);
        intent.putExtra(EXTRA_VOICE_VALUE, resIndex);
        intent.setPackage(TARGET_PACKAGE);
        context.sendBroadcast(intent);
    }
}
