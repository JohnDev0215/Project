package com.android.car.settings.applications.specialaccess;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import com.android.car.settings.applications.specialaccess.AppStateAppOpsBridge;
import com.android.car.settings.common.FragmentController;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.applications.ApplicationsState;

/* JADX INFO: loaded from: classes5.dex */
public class WifiControlPreferenceController extends AppOpsPreferenceController {
    private static final ApplicationsState.AppFilter FILTER_CHANGE_WIFI_STATE = new ApplicationsState.AppFilter() { // from class: com.android.car.settings.applications.specialaccess.WifiControlPreferenceController.1
        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationsState.AppEntry info) {
            return !ArrayUtils.contains(((AppStateAppOpsBridge.PermissionState) info.extraInfo).getRequestedPermissions(), "android.permission.NETWORK_SETTINGS");
        }
    };

    public WifiControlPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        init(71, "android.permission.CHANGE_WIFI_STATE", 1);
    }

    @Override // com.android.car.settings.applications.specialaccess.AppOpsPreferenceController
    protected ApplicationsState.AppFilter getAppFilter() {
        ApplicationsState.AppFilter filter = super.getAppFilter();
        return new ApplicationsState.CompoundFilter(filter, FILTER_CHANGE_WIFI_STATE);
    }
}
