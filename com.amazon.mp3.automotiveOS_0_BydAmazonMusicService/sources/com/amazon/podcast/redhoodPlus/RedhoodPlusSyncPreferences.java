package com.amazon.podcast.redhoodPlus;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.podcast.Podcast;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class RedhoodPlusSyncPreferences {
    public static final boolean shouldTriggerFullSync(final Context context) {
        if (Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled()) {
            return shouldFullSyncWithAppVersionChange(context) || shouldFullSyncWithUserBenefitsChange(context);
        }
        return false;
    }

    private static final boolean shouldFullSyncWithAppVersionChange(final Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context, "AppVersionPreferences");
        if (!StringUtils.isBlank(sharedPreferences.getString("app_version", null))) {
            return false;
        }
        sharedPreferences.edit().putString("app_version", Podcast.getBuildInfoProvider().getAppVersion());
        return true;
    }

    private static final boolean shouldFullSyncWithUserBenefitsChange(final Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context, "UserBenefitsPreferences");
        Set<String> stringSet = sharedPreferences.getStringSet("user_benefits", Collections.emptySet());
        Set<String> setBenefits = Podcast.getUserInfoProvider().benefits();
        if (CollectionUtils.isEmpty(setBenefits)) {
            return false;
        }
        sharedPreferences.edit().putStringSet("user_benefits", setBenefits);
        HashSet hashSet = new HashSet();
        for (String str : stringSet) {
            if (!setBenefits.contains(str)) {
                hashSet.add(str);
            }
        }
        for (String str2 : setBenefits) {
            if (!stringSet.contains(str2)) {
                hashSet.add(str2);
            }
        }
        return !hashSet.isEmpty();
    }

    private static final SharedPreferences getSharedPreferences(final Context context, final String prefName) {
        return context.getSharedPreferences(prefName, 0);
    }
}
