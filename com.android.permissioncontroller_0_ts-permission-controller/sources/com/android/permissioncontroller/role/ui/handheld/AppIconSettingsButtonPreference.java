package com.android.permissioncontroller.role.ui.handheld;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.role.ui.TwoTargetPreference;
import com.android.permissioncontroller.role.ui.handheld.AppIconPreference;

/* JADX INFO: loaded from: classes8.dex */
public class AppIconSettingsButtonPreference extends SettingsButtonPreference {
    private AppIconPreference.Mixin mMixin;

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsButtonPreference, com.android.permissioncontroller.role.ui.TwoTargetPreference
    public /* bridge */ /* synthetic */ void setOnSecondTargetClickListener(TwoTargetPreference.OnSecondTargetClickListener onSecondTargetClickListener) {
        super.setOnSecondTargetClickListener(onSecondTargetClickListener);
    }

    AppIconSettingsButtonPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    AppIconSettingsButtonPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    AppIconSettingsButtonPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AppIconSettingsButtonPreference(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.mMixin = new AppIconPreference.Mixin(getContext());
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsButtonPreference, com.android.permissioncontroller.role.ui.handheld.HandHeldTwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        this.mMixin.onBindViewHolder(holder);
    }
}
