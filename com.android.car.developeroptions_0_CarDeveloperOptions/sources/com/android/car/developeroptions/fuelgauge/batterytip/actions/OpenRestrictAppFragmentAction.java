package com.android.car.developeroptions.fuelgauge.batterytip.actions;

import com.android.car.developeroptions.core.InstrumentedPreferenceFragment;
import com.android.car.developeroptions.fuelgauge.RestrictedAppDetails;
import com.android.car.developeroptions.fuelgauge.batterytip.AppInfo;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryDatabaseManager;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.RestrictAppTip;
import com.android.settingslib.utils.ThreadUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class OpenRestrictAppFragmentAction extends BatteryTipAction {
    BatteryDatabaseManager mBatteryDatabaseManager;
    private final InstrumentedPreferenceFragment mFragment;
    private final RestrictAppTip mRestrictAppTip;

    public OpenRestrictAppFragmentAction(InstrumentedPreferenceFragment instrumentedPreferenceFragment, RestrictAppTip restrictAppTip) {
        super(instrumentedPreferenceFragment.getContext());
        this.mFragment = instrumentedPreferenceFragment;
        this.mRestrictAppTip = restrictAppTip;
        this.mBatteryDatabaseManager = BatteryDatabaseManager.getInstance(this.mContext);
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.actions.BatteryTipAction
    public void handlePositiveAction(int i) {
        this.mMetricsFeatureProvider.action(this.mContext, 1361, i);
        final List<AppInfo> restrictAppList = this.mRestrictAppTip.getRestrictAppList();
        RestrictedAppDetails.startRestrictedAppDetails(this.mFragment, restrictAppList);
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.fuelgauge.batterytip.actions.-$$Lambda$OpenRestrictAppFragmentAction$SIZjjV0Hj65TVPsYQB4qyPuvpAc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$handlePositiveAction$0$OpenRestrictAppFragmentAction(restrictAppList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$handlePositiveAction$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$handlePositiveAction$0$OpenRestrictAppFragmentAction(List list) {
        this.mBatteryDatabaseManager.updateAnomalies(list, 1);
    }
}
