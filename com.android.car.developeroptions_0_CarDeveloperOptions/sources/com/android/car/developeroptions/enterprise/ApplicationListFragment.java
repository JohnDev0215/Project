package com.android.car.developeroptions.enterprise;

import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.ApplicationFeatureProvider;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.enterprise.ApplicationListPreferenceController;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ApplicationListFragment extends DashboardFragment implements ApplicationListPreferenceController.ApplicationListBuilder {
    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "EnterprisePrivacySettings";
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.app_list_disclosure_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ApplicationListPreferenceController(context, this, context.getPackageManager(), this));
        return arrayList;
    }

    private static abstract class AdminGrantedPermission extends ApplicationListFragment {
        private final String[] mPermissions;

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 939;
        }

        public AdminGrantedPermission(String[] strArr) {
            this.mPermissions = strArr;
        }

        @Override // com.android.car.developeroptions.enterprise.ApplicationListPreferenceController.ApplicationListBuilder
        public void buildApplicationList(Context context, ApplicationFeatureProvider.ListOfAppsCallback listOfAppsCallback) {
            FeatureFactory.getFactory(context).getApplicationFeatureProvider(context).listAppsWithAdminGrantedPermissions(this.mPermissions, listOfAppsCallback);
        }
    }

    public static class AdminGrantedPermissionCamera extends AdminGrantedPermission {
        public AdminGrantedPermissionCamera() {
            super(new String[]{"android.permission.CAMERA"});
        }
    }

    public static class AdminGrantedPermissionLocation extends AdminGrantedPermission {
        public AdminGrantedPermissionLocation() {
            super(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
        }
    }

    public static class AdminGrantedPermissionMicrophone extends AdminGrantedPermission {
        public AdminGrantedPermissionMicrophone() {
            super(new String[]{"android.permission.RECORD_AUDIO"});
        }
    }

    public static class EnterpriseInstalledPackages extends ApplicationListFragment {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 938;
        }

        @Override // com.android.car.developeroptions.enterprise.ApplicationListPreferenceController.ApplicationListBuilder
        public void buildApplicationList(Context context, ApplicationFeatureProvider.ListOfAppsCallback listOfAppsCallback) {
            FeatureFactory.getFactory(context).getApplicationFeatureProvider(context).listPolicyInstalledApps(listOfAppsCallback);
        }
    }
}
