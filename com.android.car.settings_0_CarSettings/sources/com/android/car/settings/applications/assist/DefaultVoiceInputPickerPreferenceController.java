package com.android.car.settings.applications.assist;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ComponentName;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.car.settings.applications.assist.VoiceInputInfoProvider;
import com.android.car.settings.applications.defaultapps.DefaultAppsPickerBasePreferenceController;
import com.android.car.settings.common.FragmentController;
import com.android.internal.app.AssistUtils;
import com.android.settingslib.applications.DefaultAppInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class DefaultVoiceInputPickerPreferenceController extends DefaultAppsPickerBasePreferenceController {
    private String mAssistComponentName;
    private final AssistUtils mAssistUtils;
    private final VoiceInputInfoProvider mVoiceInputInfoProvider;

    public DefaultVoiceInputPickerPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        ComponentName cn;
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mAssistComponentName = null;
        this.mAssistUtils = new AssistUtils(context);
        this.mVoiceInputInfoProvider = new VoiceInputInfoProvider(context);
        if (Objects.equals(this.mAssistUtils.getAssistComponentForUser(getCurrentProcessUserId()), VoiceInputUtils.getCurrentService(getContext())) && (cn = this.mAssistUtils.getAssistComponentForUser(getCurrentProcessUserId())) != null) {
            this.mAssistComponentName = cn.flattenToString();
        }
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppsPickerBasePreferenceController
    protected List<DefaultAppInfo> getCandidates() {
        List<DefaultAppInfo> candidates = new ArrayList<>();
        for (VoiceInputInfoProvider.VoiceInteractionInfo info : this.mVoiceInputInfoProvider.getVoiceInteractionInfoList()) {
            boolean enabled = TextUtils.equals(info.getComponentName().flattenToString(), this.mAssistComponentName);
            candidates.add(new DefaultVoiceInputServiceInfo(getContext(), getContext().getPackageManager(), getCurrentProcessUserId(), info, enabled));
        }
        Iterator<VoiceInputInfoProvider.VoiceRecognitionInfo> it = this.mVoiceInputInfoProvider.getVoiceRecognitionInfoList().iterator();
        while (it.hasNext()) {
            candidates.add(new DefaultVoiceInputServiceInfo(getContext(), getContext().getPackageManager(), getCurrentProcessUserId(), it.next(), true));
        }
        return candidates;
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppsPickerBasePreferenceController
    protected String getCurrentDefaultKey() {
        ComponentName cn = VoiceInputUtils.getCurrentService(getContext());
        if (cn == null) {
            return null;
        }
        return cn.flattenToString();
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppsPickerBasePreferenceController
    protected void setCurrentDefault(String key) {
        ComponentName cn = ComponentName.unflattenFromString(key);
        VoiceInputInfoProvider.VoiceInputInfo info = this.mVoiceInputInfoProvider.getInfoForComponent(cn);
        if (info instanceof VoiceInputInfoProvider.VoiceInteractionInfo) {
            VoiceInputInfoProvider.VoiceInteractionInfo interactionInfo = (VoiceInputInfoProvider.VoiceInteractionInfo) info;
            Settings.Secure.putString(getContext().getContentResolver(), "voice_interaction_service", key);
            Settings.Secure.putString(getContext().getContentResolver(), "voice_recognition_service", new ComponentName(interactionInfo.getPackageName(), interactionInfo.getRecognitionService()).flattenToString());
        } else if (info instanceof VoiceInputInfoProvider.VoiceRecognitionInfo) {
            Settings.Secure.putString(getContext().getContentResolver(), "voice_interaction_service", "");
            Settings.Secure.putString(getContext().getContentResolver(), "voice_recognition_service", key);
        }
    }

    @Override // com.android.car.settings.applications.defaultapps.DefaultAppsPickerBasePreferenceController
    protected boolean includeNonePreference() {
        return false;
    }
}
