package com.android.permissioncontroller.role.ui.auto;

import android.content.Context;
import android.util.AttributeSet;
import com.android.permissioncontroller.role.ui.TwoTargetPreference;

/* JADX INFO: loaded from: classes8.dex */
public class AutoSettingsPreference extends TwoTargetPreference {
    public AutoSettingsPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AutoSettingsPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AutoSettingsPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSettingsPreference(Context context) {
        super(context);
    }

    @Override // com.android.permissioncontroller.role.ui.TwoTargetPreference
    public void setOnSecondTargetClickListener(TwoTargetPreference.OnSecondTargetClickListener listener) {
    }
}
