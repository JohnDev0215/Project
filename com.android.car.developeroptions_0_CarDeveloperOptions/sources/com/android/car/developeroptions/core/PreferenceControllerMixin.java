package com.android.car.developeroptions.core;

import android.text.TextUtils;
import android.util.Log;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface PreferenceControllerMixin {
    /* JADX WARN: Multi-variable type inference failed */
    default void updateNonIndexableKeys(List<String> list) {
        if (this instanceof AbstractPreferenceController) {
            AbstractPreferenceController abstractPreferenceController = (AbstractPreferenceController) this;
            if (abstractPreferenceController.isAvailable()) {
                return;
            }
            String preferenceKey = abstractPreferenceController.getPreferenceKey();
            if (TextUtils.isEmpty(preferenceKey)) {
                Log.w("PrefControllerMixin", "Skipping updateNonIndexableKeys due to empty key " + toString());
                return;
            }
            if (list.contains(preferenceKey)) {
                Log.w("PrefControllerMixin", "Skipping updateNonIndexableKeys, key already in list. " + toString());
                return;
            }
            list.add(preferenceKey);
        }
    }
}
