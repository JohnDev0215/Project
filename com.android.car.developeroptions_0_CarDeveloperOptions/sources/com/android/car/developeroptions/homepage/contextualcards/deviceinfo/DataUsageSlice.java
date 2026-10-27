package com.android.car.developeroptions.homepage.contextualcards.deviceinfo;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.text.style.TextAppearanceSpan;
import com.android.car.developeroptions.slices.CustomSliceable;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.net.DataUsageController;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class DataUsageSlice implements CustomSliceable {
    private static final long MILLIS_IN_A_DAY = TimeUnit.DAYS.toMillis(1);
    private final Context mContext;

    @Override // com.android.car.developeroptions.slices.CustomSliceable
    public void onNotifyChange(Intent intent) {
    }

    public DataUsageSlice(Context context) {
        this.mContext = context;
    }

    @VisibleForTesting
    CharSequence getDataUsageText(DataUsageController.DataUsageInfo dataUsageInfo) {
        Formatter.BytesResult bytes = Formatter.formatBytes(this.mContext.getResources(), dataUsageInfo.usageLevel, 10);
        SpannableString spannableString = new SpannableString(bytes.value);
        spannableString.setSpan(new TextAppearanceSpan(this.mContext, R.style.TextAppearance.Large), 0, spannableString.length(), 33);
        return TextUtils.expandTemplate(this.mContext.getText(com.android.car.developeroptions.R.string.data_used_formatted), spannableString, bytes.units);
    }

    @VisibleForTesting
    CharSequence getCycleTime(DataUsageController.DataUsageInfo dataUsageInfo) {
        long jCurrentTimeMillis = dataUsageInfo.cycleEnd - System.currentTimeMillis();
        if (jCurrentTimeMillis <= 0) {
            return this.mContext.getString(com.android.car.developeroptions.R.string.billing_cycle_none_left);
        }
        int i = (int) (jCurrentTimeMillis / MILLIS_IN_A_DAY);
        if (i < 1) {
            return this.mContext.getString(com.android.car.developeroptions.R.string.billing_cycle_less_than_one_day_left);
        }
        return this.mContext.getResources().getQuantityString(com.android.car.developeroptions.R.plurals.billing_cycle_days_left, i, Integer.valueOf(i));
    }
}
