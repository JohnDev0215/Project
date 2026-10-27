package com.android.car.developeroptions.utils;

import android.text.TextUtils;
import android.util.ArrayMap;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class PreferenceGroupChildrenCache {
    private ArrayMap<String, Preference> mPreferenceCache;

    public void cacheRemoveAllPrefs(PreferenceGroup preferenceGroup) {
        this.mPreferenceCache = new ArrayMap<>();
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceGroup.getPreference(i);
            if (!TextUtils.isEmpty(preference.getKey())) {
                this.mPreferenceCache.put(preference.getKey(), preference);
            }
        }
    }

    public void removeCachedPrefs(PreferenceGroup preferenceGroup) {
        Iterator<Preference> it = this.mPreferenceCache.values().iterator();
        while (it.hasNext()) {
            preferenceGroup.removePreference(it.next());
        }
        this.mPreferenceCache = null;
    }

    public Preference getCachedPreference(String str) {
        ArrayMap<String, Preference> arrayMap = this.mPreferenceCache;
        if (arrayMap != null) {
            return arrayMap.remove(str);
        }
        return null;
    }
}
