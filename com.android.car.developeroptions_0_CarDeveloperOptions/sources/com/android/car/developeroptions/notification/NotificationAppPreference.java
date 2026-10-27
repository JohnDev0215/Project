package com.android.car.developeroptions.notification;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.MasterSwitchPreference;
import com.android.settingslib.RestrictedLockUtils;

/* JADX INFO: loaded from: classes.dex */
public class NotificationAppPreference extends MasterSwitchPreference {
    private boolean mChecked;
    private boolean mEnableSwitch;
    private Switch mSwitch;

    @Override // com.android.car.developeroptions.widget.MasterSwitchPreference, com.android.settingslib.TwoTargetPreference
    protected int getSecondTargetResId() {
        return R.layout.preference_widget_master_switch;
    }

    public NotificationAppPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEnableSwitch = true;
    }

    public NotificationAppPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mEnableSwitch = true;
    }

    public NotificationAppPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mEnableSwitch = true;
    }

    @Override // com.android.car.developeroptions.widget.MasterSwitchPreference, com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewFindViewById = preferenceViewHolder.findViewById(android.R.id.widget_frame);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.NotificationAppPreference.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (NotificationAppPreference.this.mSwitch == null || NotificationAppPreference.this.mSwitch.isEnabled()) {
                        NotificationAppPreference.this.setChecked(!r2.mChecked);
                        NotificationAppPreference notificationAppPreference = NotificationAppPreference.this;
                        if (!notificationAppPreference.callChangeListener(Boolean.valueOf(notificationAppPreference.mChecked))) {
                            NotificationAppPreference.this.setChecked(!r1.mChecked);
                        } else {
                            NotificationAppPreference notificationAppPreference2 = NotificationAppPreference.this;
                            notificationAppPreference2.persistBoolean(notificationAppPreference2.mChecked);
                        }
                    }
                }
            });
        }
        Switch r3 = (Switch) preferenceViewHolder.findViewById(R.id.switchWidget);
        this.mSwitch = r3;
        if (r3 != null) {
            r3.setContentDescription(getTitle());
            this.mSwitch.setChecked(this.mChecked);
            this.mSwitch.setEnabled(this.mEnableSwitch);
        }
    }

    @Override // com.android.car.developeroptions.widget.MasterSwitchPreference
    public boolean isChecked() {
        return this.mSwitch != null && this.mChecked;
    }

    @Override // com.android.car.developeroptions.widget.MasterSwitchPreference
    public void setChecked(boolean z) {
        this.mChecked = z;
        Switch r0 = this.mSwitch;
        if (r0 != null) {
            r0.setChecked(z);
        }
    }

    @Override // com.android.car.developeroptions.widget.MasterSwitchPreference
    public void setSwitchEnabled(boolean z) {
        this.mEnableSwitch = z;
        Switch r0 = this.mSwitch;
        if (r0 != null) {
            r0.setEnabled(z);
        }
    }

    @Override // com.android.car.developeroptions.widget.MasterSwitchPreference
    public void setDisabledByAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        setSwitchEnabled(enforcedAdmin == null);
    }
}
