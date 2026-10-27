package com.android.car.settings.applications.assist;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.android.car.settings.applications.assist.VoiceInputInfoProvider;
import com.android.car.settings.applications.defaultapps.DefaultAppsPickerEntryBasePreferenceController;
import com.android.car.settings.common.FragmentController;
import com.android.internal.app.AssistUtils;
import com.android.settingslib.applications.DefaultAppInfo;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class DefaultVoiceInputPickerEntryPreferenceController extends DefaultAppsPickerEntryBasePreferenceController {
    private static final Uri ASSIST_URI = Settings.Secure.getUriFor("assistant");
    private final AssistUtils mAssistUtils;
    private final ContentObserver mSettingObserver;
    private final VoiceInputInfoProvider mVoiceInputInfoProvider;

    public DefaultVoiceInputPickerEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mSettingObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.car.settings.applications.assist.DefaultVoiceInputPickerEntryPreferenceController.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                if (DefaultVoiceInputPickerEntryPreferenceController.ASSIST_URI.equals(uri)) {
                    DefaultVoiceInputPickerEntryPreferenceController.this.refreshUi();
                }
            }
        };
        this.mVoiceInputInfoProvider = new VoiceInputInfoProvider(context);
        this.mAssistUtils = new AssistUtils(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        ComponentName currentVoiceService = VoiceInputUtils.getCurrentService(getContext());
        ComponentName currentAssist = this.mAssistUtils.getAssistComponentForUser(getCurrentProcessUserId());
        return Objects.equals(currentAssist, currentVoiceService) ? 1 : 0;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        getContext().getContentResolver().registerContentObserver(ASSIST_URI, false, this.mSettingObserver);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        getContext().getContentResolver().unregisterContentObserver(this.mSettingObserver);
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppEntryBasePreferenceController
    protected DefaultAppInfo getCurrentDefaultAppInfo() {
        VoiceInputInfoProvider.VoiceInputInfo info = this.mVoiceInputInfoProvider.getInfoForComponent(VoiceInputUtils.getCurrentService(getContext()));
        if (info == null) {
            return null;
        }
        return new DefaultVoiceInputServiceInfo(getContext(), getContext().getPackageManager(), getCurrentProcessUserId(), info, true);
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppsPickerEntryBasePreferenceController
    protected Intent getSettingIntent(DefaultAppInfo info) {
        if (info instanceof DefaultVoiceInputServiceInfo) {
            return ((DefaultVoiceInputServiceInfo) info).getSettingIntent();
        }
        return null;
    }
}
