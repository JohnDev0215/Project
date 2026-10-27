package com.android.permissioncontroller.role.ui.specialappaccess.handheld;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreference;
import com.android.permissioncontroller.role.ui.handheld.AppIconPreference;

/* JADX INFO: loaded from: classes8.dex */
class AppIconSwitchPreference extends SwitchPreference {
    private AppIconPreference.Mixin mMixin;

    AppIconSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    AppIconSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    AppIconSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    AppIconSwitchPreference(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.mMixin = new AppIconPreference.Mixin(getContext());
    }

    @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        this.mMixin.onBindViewHolder(holder);
    }
}
