package com.android.car.developeroptions.fuelgauge;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.fuelgauge.BatteryInfo;
import com.android.car.developeroptions.widget.UsageView;
import com.android.internal.os.BatteryStatsHelper;

/* JADX INFO: loaded from: classes.dex */
public class BatteryHistoryPreference extends Preference {
    boolean hideSummary;
    BatteryInfo mBatteryInfo;
    private CharSequence mSummary;
    private TextView mSummaryView;

    public BatteryHistoryPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R.layout.battery_usage_graph);
        setSelectable(false);
    }

    public void setStats(BatteryStatsHelper batteryStatsHelper) {
        BatteryInfo.getBatteryInfo(getContext(), new BatteryInfo.Callback() { // from class: com.android.car.developeroptions.fuelgauge.-$$Lambda$BatteryHistoryPreference$PpD9df9dwf953GJ1ku6YTsaUSzg
            @Override // com.android.car.developeroptions.fuelgauge.BatteryInfo.Callback
            public final void onBatteryInfoLoaded(BatteryInfo batteryInfo) {
                this.f$0.lambda$setStats$0$BatteryHistoryPreference(batteryInfo);
            }
        }, batteryStatsHelper, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$setStats$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$setStats$0$BatteryHistoryPreference(BatteryInfo batteryInfo) {
        this.mBatteryInfo = batteryInfo;
        notifyChanged();
    }

    public void setBottomSummary(CharSequence charSequence) {
        this.mSummary = charSequence;
        TextView textView = this.mSummaryView;
        if (textView != null) {
            textView.setVisibility(0);
            this.mSummaryView.setText(this.mSummary);
        }
        this.hideSummary = false;
    }

    public void hideBottomSummary() {
        TextView textView = this.mSummaryView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        this.hideSummary = true;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.mBatteryInfo == null) {
            return;
        }
        ((TextView) preferenceViewHolder.findViewById(R.id.charge)).setText(this.mBatteryInfo.batteryPercentString);
        TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.bottom_summary);
        this.mSummaryView = textView;
        CharSequence charSequence = this.mSummary;
        if (charSequence != null) {
            textView.setText(charSequence);
        }
        if (this.hideSummary) {
            this.mSummaryView.setVisibility(8);
        }
        UsageView usageView = (UsageView) preferenceViewHolder.findViewById(R.id.battery_usage);
        usageView.findViewById(R.id.label_group).setAlpha(0.7f);
        this.mBatteryInfo.bindHistory(usageView, new BatteryInfo.BatteryDataParser[0]);
        BatteryUtils.logRuntime("BatteryHistoryPreference", "onBindViewHolder", jCurrentTimeMillis);
    }
}
