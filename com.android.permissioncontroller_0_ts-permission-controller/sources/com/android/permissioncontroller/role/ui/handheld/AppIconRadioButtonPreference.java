package com.android.permissioncontroller.role.ui.handheld;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.role.ui.handheld.AppIconPreference;

/* JADX INFO: loaded from: classes8.dex */
class AppIconRadioButtonPreference extends RadioButtonPreference {
    private AppIconPreference.Mixin mMixin;

    AppIconRadioButtonPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    AppIconRadioButtonPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    AppIconRadioButtonPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    AppIconRadioButtonPreference(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.mMixin = new AppIconPreference.Mixin(getContext());
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.RadioButtonPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        this.mMixin.onBindViewHolder(holder);
    }
}
