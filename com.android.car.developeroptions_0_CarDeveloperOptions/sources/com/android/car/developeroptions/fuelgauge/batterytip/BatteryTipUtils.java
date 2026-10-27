package com.android.car.developeroptions.fuelgauge.batterytip;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.core.InstrumentedPreferenceFragment;
import com.android.car.developeroptions.fuelgauge.batterytip.AppInfo;
import com.android.car.developeroptions.fuelgauge.batterytip.actions.BatterySaverAction;
import com.android.car.developeroptions.fuelgauge.batterytip.actions.BatteryTipAction;
import com.android.car.developeroptions.fuelgauge.batterytip.actions.OpenBatterySaverAction;
import com.android.car.developeroptions.fuelgauge.batterytip.actions.OpenRestrictAppFragmentAction;
import com.android.car.developeroptions.fuelgauge.batterytip.actions.RestrictAppAction;
import com.android.car.developeroptions.fuelgauge.batterytip.actions.SmartBatteryAction;
import com.android.car.developeroptions.fuelgauge.batterytip.actions.UnrestrictAppAction;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.AppLabelPredicate;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.AppRestrictionPredicate;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.RestrictAppTip;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.UnrestrictAppTip;
import com.android.internal.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BatteryTipUtils {
    public static List<AppInfo> getRestrictedAppsList(AppOpsManager appOpsManager, UserManager userManager) {
        List<UserHandle> userProfiles = userManager.getUserProfiles();
        List packagesForOps = appOpsManager.getPackagesForOps(new int[]{70});
        ArrayList arrayList = new ArrayList();
        int size = CollectionUtils.size(packagesForOps);
        for (int i = 0; i < size; i++) {
            AppOpsManager.PackageOps packageOps = (AppOpsManager.PackageOps) packagesForOps.get(i);
            List ops = packageOps.getOps();
            int size2 = ops.size();
            for (int i2 = 0; i2 < size2; i2++) {
                AppOpsManager.OpEntry opEntry = (AppOpsManager.OpEntry) ops.get(i2);
                if (opEntry.getOp() == 70 && opEntry.getMode() != 0 && userProfiles.contains(new UserHandle(UserHandle.getUserId(packageOps.getUid())))) {
                    AppInfo.Builder builder = new AppInfo.Builder();
                    builder.setPackageName(packageOps.getPackageName());
                    builder.setUid(packageOps.getUid());
                    arrayList.add(builder.build());
                }
            }
        }
        return arrayList;
    }

    public static BatteryTipAction getActionForBatteryTip(BatteryTip batteryTip, SettingsActivity settingsActivity, InstrumentedPreferenceFragment instrumentedPreferenceFragment) {
        int type = batteryTip.getType();
        if (type == 0) {
            return new SmartBatteryAction(settingsActivity, instrumentedPreferenceFragment);
        }
        if (type == 1) {
            if (batteryTip.getState() == 1) {
                return new OpenRestrictAppFragmentAction(instrumentedPreferenceFragment, (RestrictAppTip) batteryTip);
            }
            return new RestrictAppAction(settingsActivity, (RestrictAppTip) batteryTip);
        }
        if (type == 3 || type == 5) {
            if (batteryTip.getState() == 1) {
                return new OpenBatterySaverAction(settingsActivity);
            }
            return new BatterySaverAction(settingsActivity);
        }
        if (type != 7) {
            return null;
        }
        return new UnrestrictAppAction(settingsActivity, (UnrestrictAppTip) batteryTip);
    }

    public static List<AppInfo> detectAnomalies(Context context, long j) {
        List<AppInfo> listQueryAllAnomalies = BatteryDatabaseManager.getInstance(context).queryAllAnomalies(j, 0);
        listQueryAllAnomalies.removeIf(AppLabelPredicate.getInstance(context).or(AppRestrictionPredicate.getInstance(context)));
        return listQueryAllAnomalies;
    }
}
