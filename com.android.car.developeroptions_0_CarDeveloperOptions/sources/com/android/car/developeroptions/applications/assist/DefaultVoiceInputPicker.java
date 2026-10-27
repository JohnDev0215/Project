package com.android.car.developeroptions.applications.assist;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.assist.VoiceInputHelper;
import com.android.car.developeroptions.applications.defaultapps.DefaultAppPickerFragment;
import com.android.internal.app.AssistUtils;
import com.android.settingslib.applications.DefaultAppInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DefaultVoiceInputPicker extends DefaultAppPickerFragment {
    private String mAssistRestrict;
    private AssistUtils mAssistUtils;
    private VoiceInputHelper mHelper;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 844;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.default_voice_settings;
    }

    @Override // com.android.car.developeroptions.applications.defaultapps.DefaultAppPickerFragment, com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) throws Throwable {
        super.onAttach(context);
        this.mAssistUtils = new AssistUtils(context);
        VoiceInputHelper voiceInputHelper = new VoiceInputHelper(context);
        this.mHelper = voiceInputHelper;
        voiceInputHelper.buildUi();
        ComponentName currentAssist = getCurrentAssist();
        if (isCurrentAssistVoiceService(currentAssist, getCurrentService(this.mHelper))) {
            this.mAssistRestrict = currentAssist.flattenToShortString();
        }
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected List<VoiceInputDefaultAppInfo> getCandidates() {
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        boolean z = true;
        for (VoiceInputHelper.InteractionInfo interactionInfo : this.mHelper.mAvailableInteractionInfos) {
            boolean zEquals = TextUtils.equals(interactionInfo.key, this.mAssistRestrict);
            arrayList.add(new VoiceInputDefaultAppInfo(context, this.mPm, this.mUserId, interactionInfo, zEquals));
            z |= zEquals;
        }
        boolean z2 = !z;
        Iterator<VoiceInputHelper.RecognizerInfo> it = this.mHelper.mAvailableRecognizerInfos.iterator();
        while (it.hasNext()) {
            arrayList.add(new VoiceInputDefaultAppInfo(context, this.mPm, this.mUserId, it.next(), !z2));
        }
        return arrayList;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected String getDefaultKey() {
        ComponentName currentService = getCurrentService(this.mHelper);
        if (currentService == null) {
            return null;
        }
        return currentService.flattenToShortString();
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected boolean setDefaultKey(String str) {
        for (VoiceInputHelper.InteractionInfo interactionInfo : this.mHelper.mAvailableInteractionInfos) {
            if (TextUtils.equals(str, interactionInfo.key)) {
                Settings.Secure.putString(getContext().getContentResolver(), "voice_interaction_service", str);
                Settings.Secure.putString(getContext().getContentResolver(), "voice_recognition_service", new ComponentName(interactionInfo.service.packageName, interactionInfo.serviceInfo.getRecognitionService()).flattenToShortString());
                return true;
            }
        }
        Iterator<VoiceInputHelper.RecognizerInfo> it = this.mHelper.mAvailableRecognizerInfos.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (TextUtils.equals(str, it.next().key)) {
                Settings.Secure.putString(getContext().getContentResolver(), "voice_interaction_service", "");
                Settings.Secure.putString(getContext().getContentResolver(), "voice_recognition_service", str);
                break;
            }
        }
        return true;
    }

    public static ComponentName getCurrentService(VoiceInputHelper voiceInputHelper) {
        ComponentName componentName = voiceInputHelper.mCurrentVoiceInteraction;
        if (componentName != null) {
            return componentName;
        }
        ComponentName componentName2 = voiceInputHelper.mCurrentRecognizer;
        if (componentName2 != null) {
            return componentName2;
        }
        return null;
    }

    private ComponentName getCurrentAssist() {
        return this.mAssistUtils.getAssistComponentForUser(this.mUserId);
    }

    public static boolean isCurrentAssistVoiceService(ComponentName componentName, ComponentName componentName2) {
        return (componentName == null && componentName2 == null) || (componentName != null && componentName.equals(componentName2));
    }

    public static class VoiceInputDefaultAppInfo extends DefaultAppInfo {
        public VoiceInputHelper.BaseInfo mInfo;

        public VoiceInputDefaultAppInfo(Context context, PackageManager packageManager, int i, VoiceInputHelper.BaseInfo baseInfo, boolean z) {
            super(context, packageManager, i, baseInfo.componentName, (String) null, z);
            this.mInfo = baseInfo;
        }

        @Override // com.android.settingslib.applications.DefaultAppInfo, com.android.settingslib.widget.CandidateInfo
        public String getKey() {
            return this.mInfo.key;
        }

        @Override // com.android.settingslib.applications.DefaultAppInfo, com.android.settingslib.widget.CandidateInfo
        public CharSequence loadLabel() {
            VoiceInputHelper.BaseInfo baseInfo = this.mInfo;
            if (baseInfo instanceof VoiceInputHelper.InteractionInfo) {
                return baseInfo.appLabel;
            }
            return baseInfo.label;
        }

        public Intent getSettingIntent() {
            if (this.mInfo.settings == null) {
                return null;
            }
            return new Intent("android.intent.action.MAIN").setComponent(this.mInfo.settings);
        }
    }
}
