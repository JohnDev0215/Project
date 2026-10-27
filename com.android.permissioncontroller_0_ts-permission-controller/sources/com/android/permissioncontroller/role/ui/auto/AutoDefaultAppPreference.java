package com.android.permissioncontroller.role.ui.auto;

import android.content.Context;
import android.widget.RadioButton;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.TwoStatePreference;
import com.android.permissioncontroller.R;

/* JADX INFO: loaded from: classes8.dex */
public class AutoDefaultAppPreference extends TwoStatePreference {
    public AutoDefaultAppPreference(Context context) {
        super(context, null, TypedArrayUtils.getAttr(context, R.attr.preferenceStyle, android.R.attr.preferenceStyle));
        init();
    }

    private void init() {
        setLayoutResource(R.layout.car_radio_button_preference);
        setWidgetLayoutResource(R.layout.radio_button_preference_widget);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        RadioButton radioButton = (RadioButton) holder.findViewById(R.id.radio_button);
        radioButton.setChecked(isChecked());
    }
}
