package com.android.car.developeroptions.development;

import android.content.Context;
import android.text.format.Formatter;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.ProcStatsData;
import com.android.car.developeroptions.applications.ProcessStatsBase;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.android.settingslib.utils.ThreadUtils;

/* JADX INFO: loaded from: classes.dex */
public class MemoryUsagePreferenceController extends DeveloperOptionsPreferenceController implements PreferenceControllerMixin {
    private ProcStatsData mProcStatsData;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "memory";
    }

    public MemoryUsagePreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mProcStatsData = getProcStatsData();
        setDuration();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.development.-$$Lambda$MemoryUsagePreferenceController$_Viu5w97WC1IxaO1BaVSjBmHVk4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateState$1$MemoryUsagePreferenceController();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateState$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateState$1$MemoryUsagePreferenceController() {
        this.mProcStatsData.refreshStats(true);
        ProcStatsData.MemInfo memInfo = this.mProcStatsData.getMemInfo();
        final String shortFileSize = Formatter.formatShortFileSize(this.mContext, (long) memInfo.realUsedRam);
        final String shortFileSize2 = Formatter.formatShortFileSize(this.mContext, (long) memInfo.realTotalRam);
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.development.-$$Lambda$MemoryUsagePreferenceController$qhd0OmycLQWoObzuEDVyTfOjB3U
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateState$0$MemoryUsagePreferenceController(shortFileSize, shortFileSize2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateState$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateState$0$MemoryUsagePreferenceController(String str, String str2) {
        this.mPreference.setSummary(this.mContext.getString(R.string.memory_summary, str, str2));
    }

    void setDuration() {
        this.mProcStatsData.setDuration(ProcessStatsBase.sDurations[0]);
    }

    ProcStatsData getProcStatsData() {
        return new ProcStatsData(this.mContext, false);
    }
}
