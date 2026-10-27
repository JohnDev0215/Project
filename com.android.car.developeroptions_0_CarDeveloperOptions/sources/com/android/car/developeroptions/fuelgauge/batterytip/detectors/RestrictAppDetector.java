package com.android.car.developeroptions.fuelgauge.batterytip.detectors;

import android.content.Context;
import com.android.car.developeroptions.fuelgauge.batterytip.AppInfo;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryDatabaseManager;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPolicy;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipUtils;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.AppLabelPredicate;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.AppRestrictionPredicate;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.RestrictAppTip;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class RestrictAppDetector {
    static final boolean USE_FAKE_DATA = false;
    private AppLabelPredicate mAppLabelPredicate;
    private AppRestrictionPredicate mAppRestrictionPredicate;
    BatteryDatabaseManager mBatteryDatabaseManager;
    private Context mContext;
    private BatteryTipPolicy mPolicy;

    public RestrictAppDetector(Context context, BatteryTipPolicy batteryTipPolicy) {
        this.mContext = context;
        this.mPolicy = batteryTipPolicy;
        this.mBatteryDatabaseManager = BatteryDatabaseManager.getInstance(context);
        this.mAppRestrictionPredicate = AppRestrictionPredicate.getInstance(context);
        this.mAppLabelPredicate = AppLabelPredicate.getInstance(context);
    }

    public BatteryTip detect() {
        if (this.mPolicy.appRestrictionEnabled) {
            long jCurrentTimeMillis = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(this.mPolicy.appRestrictionActiveHour);
            List<AppInfo> listDetectAnomalies = BatteryTipUtils.detectAnomalies(this.mContext, jCurrentTimeMillis);
            if (!listDetectAnomalies.isEmpty()) {
                return new RestrictAppTip(0, listDetectAnomalies);
            }
            List<AppInfo> listQueryAllAnomalies = this.mBatteryDatabaseManager.queryAllAnomalies(jCurrentTimeMillis, 2);
            listQueryAllAnomalies.removeIf(this.mAppLabelPredicate.or(this.mAppRestrictionPredicate.negate()));
            return new RestrictAppTip(listQueryAllAnomalies.isEmpty() ? 2 : 1, listQueryAllAnomalies);
        }
        return new RestrictAppTip(2, new ArrayList());
    }
}
