package com.android.car.developeroptions.development;

import android.content.Context;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class DefaultLaunchPreferenceController extends DeveloperOptionsPreferenceController implements PreferenceControllerMixin {
    private final String mPreferenceKey;

    public DefaultLaunchPreferenceController(Context context, String str) {
        super(context);
        this.mPreferenceKey = str;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return this.mPreferenceKey;
    }
}
