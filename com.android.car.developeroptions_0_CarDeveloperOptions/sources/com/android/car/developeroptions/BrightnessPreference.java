package com.android.car.developeroptions;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.util.AttributeSet;
import androidx.preference.Preference;

/* JADX INFO: loaded from: classes.dex */
public class BrightnessPreference extends Preference {
    public BrightnessPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.preference.Preference
    protected void onClick() {
        getContext().startActivityAsUser(new Intent("com.android.intent.action.SHOW_BRIGHTNESS_DIALOG"), UserHandle.CURRENT_OR_SELF);
    }
}
