package com.android.car.developeroptions.accessibility;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.SeekBarPreference;

/* JADX INFO: loaded from: classes.dex */
public class BalanceSeekBarPreference extends SeekBarPreference {
    private final Context mContext;
    private BalanceSeekBar mSeekBar;

    public BalanceSeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, R.attr.preferenceStyle, android.R.attr.preferenceStyle));
        this.mContext = context;
        setLayoutResource(R.layout.preference_balance_slider);
    }

    @Override // com.android.car.developeroptions.widget.SeekBarPreference, com.android.settingslib.RestrictedPreference, com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.mSeekBar = (BalanceSeekBar) preferenceViewHolder.findViewById(android.R.id.overlay_display_window_texture);
        init();
    }

    private void init() {
        if (this.mSeekBar == null) {
            return;
        }
        float floatForUser = Settings.System.getFloatForUser(this.mContext.getContentResolver(), "master_balance", 0.0f, -2);
        this.mSeekBar.setMax(200);
        this.mSeekBar.setProgress(((int) (floatForUser * 100.0f)) + 100);
        this.mSeekBar.setEnabled(isEnabled());
    }
}
