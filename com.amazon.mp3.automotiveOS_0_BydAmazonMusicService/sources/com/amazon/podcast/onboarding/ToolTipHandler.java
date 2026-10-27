package com.amazon.podcast.onboarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.bootstrap.UserInfoProvider;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class ToolTipHandler {
    public static void sendNavEvent(final Context context, final String onboardingFlag) {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent("NAVIGATION_EVENT_INTENT_FILTER");
        intent.putExtra("NAVIGATION_INTENT_FLAG", onboardingFlag);
        localBroadcastManager.sendBroadcast(intent);
    }

    public static Boolean checkIfFlagNotExists(final Context context, final String onboardingFlag) {
        UserInfoProvider userInfoProvider = Podcast.getUserInfoProvider();
        if (userInfoProvider == null) {
            return Boolean.TRUE;
        }
        Set<String> stringSet = context.getSharedPreferences("ONBOARDING_PREF", 0).getStringSet(onboardingFlag, null);
        return Boolean.valueOf(stringSet == null || !stringSet.contains(userInfoProvider.customerId()));
    }

    public static void insertFlag(final Context context, final String onboardingFlag) {
        UserInfoProvider userInfoProvider = Podcast.getUserInfoProvider();
        if (userInfoProvider == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("ONBOARDING_PREF", 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        Set<String> stringSet = sharedPreferences.getStringSet(onboardingFlag, null);
        if (stringSet == null) {
            stringSet = new HashSet<>();
        }
        stringSet.add(userInfoProvider.customerId());
        editorEdit.putStringSet(onboardingFlag, stringSet);
        editorEdit.apply();
    }
}
