package com.android.car.settings.system;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.PersistableBundle;
import android.os.UserManager;
import android.telephony.CarrierConfigManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SystemUpdatePreferenceController extends PreferenceController<Preference> {
    private static final Logger LOG = new Logger((Class<?>) SystemUpdatePreferenceController.class);
    private boolean mActivityFound;
    private final UserManager mUserManager;

    public SystemUpdatePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mUserManager = UserManager.get(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        if (getContext().getResources().getBoolean(R.bool.config_show_system_update_settings)) {
            return this.mUserManager.isAdminUser() ? 0 : 3;
        }
        return 2;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        Preference preference = getPreference();
        Intent intent = preference.getIntent();
        if (intent != null) {
            PackageManager pm = getContext().getPackageManager();
            List<ResolveInfo> list = pm.queryIntentActivities(intent, 0);
            int listSize = list.size();
            for (int i = 0; i < listSize; i++) {
                ResolveInfo resolveInfo = list.get(i);
                if ((resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                    preference.setIntent(new Intent().setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    preference.setTitle(resolveInfo.loadLabel(pm));
                    this.mActivityFound = true;
                }
            }
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setVisible(this.mActivityFound);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        CarrierConfigManager configManager = (CarrierConfigManager) getContext().getSystemService("carrier_config");
        PersistableBundle b = configManager.getConfig();
        if (b != null && b.getBoolean("ci_action_on_sys_update_bool")) {
            ciActionOnSysUpdate(b);
            return false;
        }
        return false;
    }

    private void ciActionOnSysUpdate(PersistableBundle b) {
        String intentStr = b.getString("ci_action_on_sys_update_intent_string");
        if (!TextUtils.isEmpty(intentStr)) {
            String extra = b.getString("ci_action_on_sys_update_extra_string");
            String extraVal = b.getString("ci_action_on_sys_update_extra_val_string");
            Intent intent = new Intent(intentStr);
            if (!TextUtils.isEmpty(extra)) {
                intent.putExtra(extra, extraVal);
            }
            LOG.d("ciActionOnSysUpdate: broadcasting intent " + intentStr + " with extra " + extra + ", " + extraVal);
            getContext().getApplicationContext().sendBroadcast(intent);
        }
    }
}
