package com.android.car.developeroptions.datausage;

import android.R;
import android.content.Context;
import android.net.NetworkPolicy;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.widget.UsageView;
import com.android.settingslib.Utils;
import com.android.settingslib.net.NetworkCycleChartData;
import com.android.settingslib.net.NetworkCycleData;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChartDataUsagePreference extends Preference {
    private long mEnd;
    private final int mLimitColor;
    private NetworkCycleChartData mNetworkCycleChartData;
    private NetworkPolicy mPolicy;
    private long mStart;
    private final int mWarningColor;

    public ChartDataUsagePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setSelectable(false);
        this.mLimitColor = Utils.getColorAttrDefaultColor(context, R.attr.colorError);
        this.mWarningColor = Utils.getColorAttrDefaultColor(context, R.attr.textColorSecondary);
        setLayoutResource(com.android.car.developeroptions.R.layout.data_usage_graph);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        UsageView usageView = (UsageView) preferenceViewHolder.findViewById(com.android.car.developeroptions.R.id.data_usage);
        if (this.mNetworkCycleChartData == null) {
            return;
        }
        int top = getTop();
        usageView.clearPaths();
        usageView.configureGraph(toInt(this.mEnd - this.mStart), top);
        calcPoints(usageView, this.mNetworkCycleChartData.getUsageBuckets());
        Context context = getContext();
        long j = this.mStart;
        Context context2 = getContext();
        long j2 = this.mEnd;
        usageView.setBottomLabels(new CharSequence[]{com.android.car.developeroptions.Utils.formatDateRange(context, j, j), com.android.car.developeroptions.Utils.formatDateRange(context2, j2, j2)});
        bindNetworkPolicy(usageView, this.mPolicy, top);
    }

    public int getTop() {
        long totalUsage = this.mNetworkCycleChartData.getTotalUsage();
        NetworkPolicy networkPolicy = this.mPolicy;
        return (int) (Math.max(totalUsage, networkPolicy != null ? Math.max(networkPolicy.limitBytes, networkPolicy.warningBytes) : 0L) / 524288);
    }

    void calcPoints(UsageView usageView, List<NetworkCycleData> list) {
        if (list == null) {
            return;
        }
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(0, 0);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long totalUsage = 0;
        for (NetworkCycleData networkCycleData : list) {
            long startTime = networkCycleData.getStartTime();
            if (startTime > jCurrentTimeMillis) {
                break;
            }
            long endTime = networkCycleData.getEndTime();
            totalUsage += networkCycleData.getTotalUsage();
            if (sparseIntArray.size() == 1) {
                sparseIntArray.put(toInt(startTime - this.mStart) - 1, -1);
            }
            int i = (int) (totalUsage / 524288);
            sparseIntArray.put(toInt((startTime - this.mStart) + 1), i);
            sparseIntArray.put(toInt(endTime - this.mStart), i);
        }
        if (sparseIntArray.size() > 1) {
            usageView.addPath(sparseIntArray);
        }
    }

    private int toInt(long j) {
        return (int) (j / 60000);
    }

    private void bindNetworkPolicy(UsageView usageView, NetworkPolicy networkPolicy, int i) {
        int i2;
        CharSequence[] charSequenceArr = new CharSequence[3];
        if (networkPolicy == null) {
            return;
        }
        long j = networkPolicy.limitBytes;
        int i3 = 0;
        if (j != -1) {
            i2 = this.mLimitColor;
            charSequenceArr[2] = getLabel(j, com.android.car.developeroptions.R.string.data_usage_sweep_limit, i2);
        } else {
            i2 = 0;
        }
        long j2 = networkPolicy.warningBytes;
        if (j2 != -1) {
            usageView.setDividerLoc((int) (j2 / 524288));
            float f = (networkPolicy.warningBytes / 524288) / i;
            usageView.setSideLabelWeights(1.0f - f, f);
            i3 = this.mWarningColor;
            charSequenceArr[1] = getLabel(networkPolicy.warningBytes, com.android.car.developeroptions.R.string.data_usage_sweep_warning, i3);
        }
        usageView.setSideLabels(charSequenceArr);
        usageView.setDividerColors(i3, i2);
    }

    private CharSequence getLabel(long j, int i, int i2) {
        Formatter.BytesResult bytes = Formatter.formatBytes(getContext().getResources(), j, 9);
        return new SpannableStringBuilder().append(TextUtils.expandTemplate(getContext().getText(i), bytes.value, bytes.units), new ForegroundColorSpan(i2), 0);
    }

    public void setNetworkPolicy(NetworkPolicy networkPolicy) {
        this.mPolicy = networkPolicy;
        notifyChanged();
    }

    public long getInspectStart() {
        return this.mStart;
    }

    public long getInspectEnd() {
        return this.mEnd;
    }

    public void setNetworkCycleData(NetworkCycleChartData networkCycleChartData) {
        this.mNetworkCycleChartData = networkCycleChartData;
        this.mStart = networkCycleChartData.getStartTime();
        this.mEnd = networkCycleChartData.getEndTime();
        notifyChanged();
    }

    public void setColors(int i, int i2) {
        notifyChanged();
    }
}
