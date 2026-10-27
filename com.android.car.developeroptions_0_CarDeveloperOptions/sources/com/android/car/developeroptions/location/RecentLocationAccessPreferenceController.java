package com.android.car.developeroptions.location;

import android.content.Context;
import android.content.Intent;
import android.provider.DeviceConfig;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.location.RecentLocationAccesses;
import com.android.settingslib.widget.AppEntitiesHeaderController;
import com.android.settingslib.widget.AppEntityInfo;
import com.android.settingslib.widget.LayoutPreference;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class RecentLocationAccessPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private AppEntitiesHeaderController mController;
    private final RecentLocationAccesses mRecentLocationAccesses;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "apps_dashboard";
    }

    public RecentLocationAccessPreferenceController(Context context) {
        this(context, new RecentLocationAccesses(context));
    }

    RecentLocationAccessPreferenceController(Context context, RecentLocationAccesses recentLocationAccesses) {
        super(context);
        this.mRecentLocationAccesses = recentLocationAccesses;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return Boolean.parseBoolean(DeviceConfig.getProperty("privacy", "permissions_hub_enabled"));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        AppEntitiesHeaderController appEntitiesHeaderControllerNewInstance = AppEntitiesHeaderController.newInstance(this.mContext, ((LayoutPreference) preferenceScreen.findPreference("apps_dashboard")).findViewById(R.id.app_entities_header));
        appEntitiesHeaderControllerNewInstance.setHeaderTitleRes(R.string.location_category_recent_location_access);
        appEntitiesHeaderControllerNewInstance.setHeaderDetailsRes(R.string.location_recent_location_access_view_details);
        appEntitiesHeaderControllerNewInstance.setHeaderEmptyRes(R.string.location_no_recent_accesses);
        appEntitiesHeaderControllerNewInstance.setHeaderDetailsClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.location.-$$Lambda$RecentLocationAccessPreferenceController$CQdJHM21S6_0mayv4edrOfibq6U
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$displayPreference$0$RecentLocationAccessPreferenceController(view);
            }
        });
        this.mController = appEntitiesHeaderControllerNewInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$0$RecentLocationAccessPreferenceController(View view) {
        Intent intent = new Intent("android.intent.action.REVIEW_PERMISSION_USAGE");
        intent.putExtra("android.intent.extra.PERMISSION_NAME", "android.permission.ACCESS_FINE_LOCATION");
        intent.putExtra("android.intent.extra.DURATION_MILLIS", TimeUnit.DAYS.toMillis(1L));
        this.mContext.startActivity(intent);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        updateRecentApps();
    }

    private void updateRecentApps() {
        List<RecentLocationAccesses.Access> appListSorted = this.mRecentLocationAccesses.getAppListSorted();
        if (appListSorted.size() > 0) {
            int i = 0;
            while (i < Math.min(appListSorted.size(), 3)) {
                final RecentLocationAccesses.Access access = appListSorted.get(i);
                AppEntityInfo.Builder builder = new AppEntityInfo.Builder();
                builder.setIcon(access.icon);
                builder.setTitle(access.label);
                builder.setSummary(access.contentDescription);
                builder.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.location.-$$Lambda$RecentLocationAccessPreferenceController$qy35qucOQU9izSE_xJ3S9vVAY2E
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$updateRecentApps$1$RecentLocationAccessPreferenceController(access, view);
                    }
                });
                this.mController.setAppEntity(i, builder.build());
                i++;
            }
            while (i < 3) {
                this.mController.removeAppEntity(i);
                i++;
            }
        }
        this.mController.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateRecentApps$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateRecentApps$1$RecentLocationAccessPreferenceController(RecentLocationAccesses.Access access, View view) {
        Intent intent = new Intent("android.intent.action.MANAGE_APP_PERMISSION");
        intent.putExtra("android.intent.extra.PERMISSION_NAME", "android.permission.ACCESS_FINE_LOCATION");
        intent.putExtra("android.intent.extra.PACKAGE_NAME", access.packageName);
        intent.putExtra("android.intent.extra.USER", access.userHandle);
        this.mContext.startActivity(intent);
    }
}
