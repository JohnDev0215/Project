package com.android.car.settings.applications.defaultapps;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.provider.Settings;
import android.text.Html;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.settingslib.applications.DefaultAppInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DefaultAutofillPickerPreferenceController extends DefaultAppsPickerBasePreferenceController {
    public DefaultAutofillPickerPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppsPickerBasePreferenceController
    protected List<DefaultAppInfo> getCandidates() {
        List<DefaultAppInfo> candidates = new ArrayList<>();
        List<ResolveInfo> resolveInfos = getContext().getPackageManager().queryIntentServices(new Intent("android.service.autofill.AutofillService"), 128);
        for (ResolveInfo info : resolveInfos) {
            String permission = info.serviceInfo.permission;
            if ("android.permission.BIND_AUTOFILL_SERVICE".equals(permission)) {
                candidates.add(new DefaultAppInfo(getContext(), getContext().getPackageManager(), getCurrentProcessUserId(), new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name)));
            }
        }
        return candidates;
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppsPickerBasePreferenceController
    protected String getCurrentDefaultKey() {
        ComponentName componentName;
        String setting = Settings.Secure.getString(getContext().getContentResolver(), "autofill_service");
        if (setting != null && (componentName = ComponentName.unflattenFromString(setting)) != null) {
            return componentName.flattenToString();
        }
        return "";
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppsPickerBasePreferenceController
    protected void setCurrentDefault(String key) {
        Settings.Secure.putString(getContext().getContentResolver(), "autofill_service", key);
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppsPickerBasePreferenceController
    protected CharSequence getConfirmationMessage(DefaultAppInfo info) {
        if (info == null) {
            return null;
        }
        CharSequence appName = info.loadLabel();
        String message = getContext().getString(R.string.autofill_confirmation_message, appName);
        return Html.fromHtml(message, 0);
    }
}
