package com.android.car.settings.applications.defaultapps;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.provider.Settings;
import android.service.autofill.AutofillServiceInfo;
import android.text.TextUtils;
import android.view.autofill.AutofillManager;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.settingslib.applications.DefaultAppInfo;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DefaultAutofillPickerEntryPreferenceController extends DefaultAppsPickerEntryBasePreferenceController {
    private static final Logger LOG = new Logger((Class<?>) DefaultAutofillPickerEntryPreferenceController.class);
    private final AutofillManager mAutofillManager;

    public DefaultAutofillPickerEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mAutofillManager = (AutofillManager) context.getSystemService(AutofillManager.class);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        AutofillManager autofillManager = this.mAutofillManager;
        if (autofillManager != null && autofillManager.isAutofillSupported()) {
            return 0;
        }
        return 2;
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppEntryBasePreferenceController
    protected DefaultAppInfo getCurrentDefaultAppInfo() {
        String flattenComponent = Settings.Secure.getString(getContext().getContentResolver(), "autofill_service");
        if (!TextUtils.isEmpty(flattenComponent)) {
            DefaultAppInfo appInfo = new DefaultAppInfo(getContext(), getContext().getPackageManager(), getCurrentProcessUserId(), ComponentName.unflattenFromString(flattenComponent));
            return appInfo;
        }
        return null;
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppsPickerEntryBasePreferenceController
    protected Intent getSettingIntent(DefaultAppInfo info) {
        if (info == null) {
            return null;
        }
        Intent intent = new Intent("android.service.autofill.AutofillService");
        List<ResolveInfo> resolveInfos = getContext().getPackageManager().queryIntentServices(intent, 128);
        for (ResolveInfo resolveInfo : resolveInfos) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            String flattenKey = new ComponentName(serviceInfo.packageName, serviceInfo.name).flattenToString();
            if (TextUtils.equals(info.getKey(), flattenKey)) {
                try {
                    String settingsActivity = new AutofillServiceInfo(getContext(), serviceInfo).getSettingsActivity();
                    if (!TextUtils.isEmpty(settingsActivity)) {
                        return new Intent("android.intent.action.MAIN").setComponent(new ComponentName(serviceInfo.packageName, settingsActivity));
                    }
                } catch (SecurityException e) {
                    LOG.w("Error getting info for " + serviceInfo + ": " + e);
                }
            }
        }
        return null;
    }
}
