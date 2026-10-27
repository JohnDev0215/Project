package com.amazon.music.inappmessaging.internal.content;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes4.dex */
public class BehavioralAdsUtil {
    public static final String SHARED_PREF_NAME = "com.amazon.mp3_BehavioralAdsUtil";
    private static final String SHOW_DYNMSG_BEHAVIORAL_ADS = "SHOW_DYNMSG_BEHAVIORAL_ADS";

    public static boolean hasShownBehavioralAdsThroughDynMsg(final Context context) {
        return (context == null || context.getSharedPreferences(SHARED_PREF_NAME, 0).getString(SHOW_DYNMSG_BEHAVIORAL_ADS, null) == null) ? false : true;
    }

    public static void saveBehavioralAdsNotificationState(final Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
        editorEdit.putBoolean(SHOW_DYNMSG_BEHAVIORAL_ADS, false);
        editorEdit.apply();
    }
}
