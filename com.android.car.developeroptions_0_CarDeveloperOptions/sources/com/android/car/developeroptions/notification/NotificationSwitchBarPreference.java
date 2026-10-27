package com.android.car.developeroptions.notification;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.widget.ToggleSwitch;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class NotificationSwitchBarPreference extends LayoutPreference {
    private boolean mChecked;
    private boolean mEnableSwitch;
    private ToggleSwitch mSwitch;

    public NotificationSwitchBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEnableSwitch = true;
    }

    @Override // com.android.settingslib.widget.LayoutPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        ToggleSwitch toggleSwitch = (ToggleSwitch) preferenceViewHolder.findViewById(R.id.switch_widget);
        this.mSwitch = toggleSwitch;
        if (toggleSwitch != null) {
            toggleSwitch.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.NotificationSwitchBarPreference.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (NotificationSwitchBarPreference.this.mSwitch.isEnabled()) {
                        NotificationSwitchBarPreference.this.mChecked = !r2.mChecked;
                        NotificationSwitchBarPreference notificationSwitchBarPreference = NotificationSwitchBarPreference.this;
                        notificationSwitchBarPreference.setChecked(notificationSwitchBarPreference.mChecked);
                        NotificationSwitchBarPreference notificationSwitchBarPreference2 = NotificationSwitchBarPreference.this;
                        if (notificationSwitchBarPreference2.callChangeListener(Boolean.valueOf(notificationSwitchBarPreference2.mChecked))) {
                            return;
                        }
                        NotificationSwitchBarPreference.this.setChecked(!r1.mChecked);
                    }
                }
            });
            this.mSwitch.setChecked(this.mChecked);
            this.mSwitch.setEnabled(this.mEnableSwitch);
        }
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
        ToggleSwitch toggleSwitch = this.mSwitch;
        if (toggleSwitch != null) {
            toggleSwitch.setChecked(z);
        }
    }
}
