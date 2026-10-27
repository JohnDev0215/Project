package com.android.car.developeroptions.development.featureflags;

import android.content.Context;
import android.util.FeatureFlagUtils;
import androidx.preference.SwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class FeatureFlagPreference extends SwitchPreference {
    private final boolean mIsPersistent;
    private final String mKey;

    public FeatureFlagPreference(Context context, String str) {
        boolean zIsEnabled;
        super(context);
        this.mKey = str;
        setKey(str);
        setTitle(str);
        boolean zIsPersistent = FeatureFlagPersistent.isPersistent(str);
        this.mIsPersistent = zIsPersistent;
        if (zIsPersistent) {
            zIsEnabled = FeatureFlagPersistent.isEnabled(context, str);
        } else {
            zIsEnabled = FeatureFlagUtils.isEnabled(context, str);
        }
        setCheckedInternal(zIsEnabled);
    }

    @Override // androidx.preference.TwoStatePreference
    public void setChecked(boolean z) {
        setCheckedInternal(z);
        if (this.mIsPersistent) {
            FeatureFlagPersistent.setEnabled(getContext(), this.mKey, z);
        } else {
            FeatureFlagUtils.setEnabled(getContext(), this.mKey, z);
        }
    }

    private void setCheckedInternal(boolean z) {
        super.setChecked(z);
        setSummary(Boolean.toString(z));
    }
}
