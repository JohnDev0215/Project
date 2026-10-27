package com.android.car.developeroptions.language;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.inputmethod.UserDictionaryList;
import com.android.car.developeroptions.inputmethod.UserDictionaryListPreferenceController;
import com.android.car.developeroptions.inputmethod.UserDictionarySettings;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class UserDictionaryPreferenceController extends BasePreferenceController {
    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 1;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public UserDictionaryPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        Class cls;
        if (!isAvailable() || preference == null) {
            return;
        }
        TreeSet<String> dictionaryLocales = getDictionaryLocales();
        Bundle extras = preference.getExtras();
        if (dictionaryLocales.size() <= 1) {
            if (!dictionaryLocales.isEmpty()) {
                extras.putString("locale", dictionaryLocales.first());
            }
            cls = UserDictionarySettings.class;
        } else {
            cls = UserDictionaryList.class;
        }
        preference.setFragment(cls.getCanonicalName());
    }

    protected TreeSet<String> getDictionaryLocales() {
        return UserDictionaryListPreferenceController.getUserDictionaryLocalesSet(this.mContext);
    }
}
