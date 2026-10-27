package com.android.car.settings.applications.assist;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.android.car.settings.applications.assist.VoiceInputInfoProvider;
import com.android.settingslib.applications.DefaultAppInfo;

/* JADX INFO: loaded from: classes5.dex */
public class DefaultVoiceInputServiceInfo extends DefaultAppInfo {
    private VoiceInputInfoProvider.VoiceInputInfo mInfo;

    public DefaultVoiceInputServiceInfo(Context context, PackageManager pm, int userId, VoiceInputInfoProvider.VoiceInputInfo info, boolean enabled) {
        super(context, pm, userId, info.getComponentName(), (String) null, enabled);
        this.mInfo = info;
    }

    @Override // com.android.settingslib.applications.DefaultAppInfo, com.android.settingslib.widget.CandidateInfo
    public CharSequence loadLabel() {
        return this.mInfo.getLabel();
    }

    public Intent getSettingIntent() {
        if (this.mInfo.getSettingsActivityComponentName() == null) {
            return null;
        }
        return new Intent("android.intent.action.MAIN").setComponent(this.mInfo.getSettingsActivityComponentName());
    }
}
