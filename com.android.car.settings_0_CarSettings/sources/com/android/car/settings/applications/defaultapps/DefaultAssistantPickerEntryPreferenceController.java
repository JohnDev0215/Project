package com.android.car.settings.applications.defaultapps;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.service.voice.VoiceInteractionServiceInfo;
import com.android.car.settings.common.FragmentController;
import com.android.car.ui.preference.CarUiTwoActionIconPreference;
import com.android.internal.app.AssistUtils;
import com.android.settingslib.applications.DefaultAppInfo;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DefaultAssistantPickerEntryPreferenceController extends DefaultAppsPickerEntryBasePreferenceController {
    static final Intent ASSISTANT_SERVICE = new Intent("android.service.voice.VoiceInteractionService");
    private final AssistUtils mAssistUtils;

    public DefaultAssistantPickerEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mAssistUtils = new AssistUtils(context);
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppEntryBasePreferenceController
    protected DefaultAppInfo getCurrentDefaultAppInfo() {
        ComponentName cn = this.mAssistUtils.getAssistComponentForUser(getCurrentProcessUserId());
        if (cn == null) {
            return null;
        }
        return new DefaultAppInfo(getContext(), getContext().getPackageManager(), getCurrentProcessUserId(), cn);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceClicked(CarUiTwoActionIconPreference preference) {
        String packageName = getContext().getPackageManager().getPermissionControllerPackageName();
        if (packageName != null) {
            Intent intent = new Intent("android.intent.action.MANAGE_DEFAULT_APP").setPackage(packageName).putExtra("android.intent.extra.ROLE_NAME", "android.app.role.ASSISTANT");
            getContext().startActivity(intent);
            return true;
        }
        return true;
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppsPickerEntryBasePreferenceController
    protected Intent getSettingIntent(DefaultAppInfo info) {
        String activity;
        ComponentName cn = this.mAssistUtils.getAssistComponentForUser(getCurrentProcessUserId());
        if (cn == null) {
            return null;
        }
        Intent probe = ASSISTANT_SERVICE.setPackage(cn.getPackageName());
        PackageManager pm = getContext().getPackageManager();
        List<ResolveInfo> services = pm.queryIntentServices(probe, 128);
        if (services == null || services.isEmpty() || (activity = getAssistSettingsActivity(pm, services.get(0))) == null) {
            return null;
        }
        return new Intent("android.intent.action.MAIN").setComponent(new ComponentName(cn.getPackageName(), activity));
    }

    private String getAssistSettingsActivity(PackageManager pm, ResolveInfo resolveInfo) {
        VoiceInteractionServiceInfo voiceInfo = new VoiceInteractionServiceInfo(pm, resolveInfo.serviceInfo);
        if (!voiceInfo.getSupportsAssist()) {
            return null;
        }
        return voiceInfo.getSettingsActivity();
    }
}
