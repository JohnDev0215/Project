package com.android.car.developeroptions.deviceinfo;

import android.content.Context;
import android.graphics.Color;
import android.util.MathUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class StorageSummaryPreference extends Preference {
    private int mPercent;

    public StorageSummaryPreference(Context context) {
        super(context);
        this.mPercent = -1;
        setLayoutResource(R.layout.storage_summary);
        setEnabled(false);
    }

    public void setPercent(long j, long j2) {
        this.mPercent = MathUtils.constrain((int) ((100 * j) / j2), j > 0 ? 1 : 0, 100);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        ProgressBar progressBar = (ProgressBar) preferenceViewHolder.findViewById(android.R.id.progress);
        if (this.mPercent != -1) {
            progressBar.setVisibility(0);
            progressBar.setProgress(this.mPercent);
            progressBar.setScaleY(7.0f);
        } else {
            progressBar.setVisibility(8);
        }
        ((TextView) preferenceViewHolder.findViewById(android.R.id.summary)).setTextColor(Color.parseColor("#8a000000"));
        super.onBindViewHolder(preferenceViewHolder);
    }
}
