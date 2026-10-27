package com.android.car.developeroptions.fuelgauge;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.settingslib.Utils;
import com.android.settingslib.widget.apppreference.AppPreference;

/* JADX INFO: loaded from: classes.dex */
public class PowerGaugePreference extends AppPreference {
    private CharSequence mContentDescription;
    private BatteryEntry mInfo;
    private CharSequence mProgress;
    private boolean mShowAnomalyIcon;

    public PowerGaugePreference(Context context, Drawable drawable, CharSequence charSequence, BatteryEntry batteryEntry) {
        this(context, null, drawable, charSequence, batteryEntry);
    }

    public PowerGaugePreference(Context context) {
        this(context, null, null, null, null);
    }

    public PowerGaugePreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, null, null, null);
    }

    private PowerGaugePreference(Context context, AttributeSet attributeSet, Drawable drawable, CharSequence charSequence, BatteryEntry batteryEntry) {
        super(context, attributeSet);
        if (drawable != null) {
            setIcon(drawable);
        }
        setWidgetLayoutResource(R.layout.preference_widget_summary);
        this.mInfo = batteryEntry;
        this.mContentDescription = charSequence;
        this.mShowAnomalyIcon = false;
    }

    public void setContentDescription(String str) {
        this.mContentDescription = str;
        notifyChanged();
    }

    public void setPercent(double d) {
        this.mProgress = Utils.formatPercentage(d, true);
        notifyChanged();
    }

    public String getPercent() {
        return this.mProgress.toString();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mProgress = charSequence;
        notifyChanged();
    }

    public void shouldShowAnomalyIcon(boolean z) {
        this.mShowAnomalyIcon = z;
        notifyChanged();
    }

    BatteryEntry getInfo() {
        return this.mInfo;
    }

    @Override // com.android.settingslib.widget.apppreference.AppPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.widget_summary);
        textView.setText(this.mProgress);
        if (this.mShowAnomalyIcon) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_warning_24dp, 0, 0, 0);
        } else {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        }
        if (this.mContentDescription != null) {
            ((TextView) preferenceViewHolder.findViewById(android.R.id.title)).setContentDescription(this.mContentDescription);
        }
    }
}
