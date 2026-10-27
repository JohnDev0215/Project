package com.android.car.developeroptions.widget;

import android.content.Context;
import android.os.UserManager;
import android.util.AttributeSet;
import androidx.preference.PreferenceCategory;
import com.android.car.developeroptions.SelfAvailablePreference;
import com.android.car.developeroptions.Utils;

/* JADX INFO: loaded from: classes.dex */
public class WorkOnlyCategory extends PreferenceCategory implements SelfAvailablePreference {
    public WorkOnlyCategory(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.android.car.developeroptions.SelfAvailablePreference
    public boolean isAvailable(Context context) {
        return Utils.getManagedProfile(UserManager.get(context)) != null;
    }
}
