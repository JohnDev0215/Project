package com.android.car.developeroptions.applications.assist;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.applications.assist.DefaultVoiceInputPicker;
import com.android.car.developeroptions.applications.assist.VoiceInputHelper;
import com.android.car.developeroptions.applications.defaultapps.DefaultAppPreferenceController;
import com.android.internal.app.AssistUtils;
import com.android.settingslib.applications.DefaultAppInfo;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DefaultVoiceInputPreferenceController extends DefaultAppPreferenceController implements LifecycleObserver, OnResume, OnPause {
    private AssistUtils mAssistUtils;
    private VoiceInputHelper mHelper;
    private Preference mPreference;
    private PreferenceScreen mScreen;
    private SettingObserver mSettingObserver;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "voice_input_settings";
    }

    public DefaultVoiceInputPreferenceController(Context context, Lifecycle lifecycle) throws Throwable {
        super(context);
        this.mSettingObserver = new SettingObserver();
        this.mAssistUtils = new AssistUtils(context);
        VoiceInputHelper voiceInputHelper = new VoiceInputHelper(context);
        this.mHelper = voiceInputHelper;
        voiceInputHelper.buildUi();
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return !DefaultVoiceInputPicker.isCurrentAssistVoiceService(this.mAssistUtils.getAssistComponentForUser(this.mUserId), DefaultVoiceInputPicker.getCurrentService(this.mHelper));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mScreen = preferenceScreen;
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        super.displayPreference(preferenceScreen);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() throws Throwable {
        this.mSettingObserver.register(this.mContext.getContentResolver(), true);
        updatePreference();
    }

    @Override // com.android.car.developeroptions.applications.defaultapps.DefaultAppPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) throws Throwable {
        super.updateState(this.mPreference);
        updatePreference();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        this.mSettingObserver.register(this.mContext.getContentResolver(), false);
    }

    @Override // com.android.car.developeroptions.applications.defaultapps.DefaultAppPreferenceController
    protected DefaultAppInfo getDefaultAppInfo() {
        String defaultAppKey = getDefaultAppKey();
        if (defaultAppKey == null) {
            return null;
        }
        for (VoiceInputHelper.InteractionInfo interactionInfo : this.mHelper.mAvailableInteractionInfos) {
            if (TextUtils.equals(defaultAppKey, interactionInfo.key)) {
                return new DefaultVoiceInputPicker.VoiceInputDefaultAppInfo(this.mContext, this.mPackageManager, this.mUserId, interactionInfo, true);
            }
        }
        for (VoiceInputHelper.RecognizerInfo recognizerInfo : this.mHelper.mAvailableRecognizerInfos) {
            if (TextUtils.equals(defaultAppKey, recognizerInfo.key)) {
                return new DefaultVoiceInputPicker.VoiceInputDefaultAppInfo(this.mContext, this.mPackageManager, this.mUserId, recognizerInfo, true);
            }
        }
        return null;
    }

    @Override // com.android.car.developeroptions.applications.defaultapps.DefaultAppPreferenceController
    protected Intent getSettingIntent(DefaultAppInfo defaultAppInfo) {
        DefaultAppInfo defaultAppInfo2 = getDefaultAppInfo();
        if (defaultAppInfo2 == null || !(defaultAppInfo2 instanceof DefaultVoiceInputPicker.VoiceInputDefaultAppInfo)) {
            return null;
        }
        return ((DefaultVoiceInputPicker.VoiceInputDefaultAppInfo) defaultAppInfo2).getSettingIntent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreference() throws Throwable {
        if (this.mPreference == null) {
            return;
        }
        this.mHelper.buildUi();
        if (isAvailable()) {
            if (this.mScreen.findPreference(getPreferenceKey()) == null) {
                this.mScreen.addPreference(this.mPreference);
                return;
            }
            return;
        }
        this.mScreen.removePreference(this.mPreference);
    }

    private String getDefaultAppKey() {
        ComponentName currentService = DefaultVoiceInputPicker.getCurrentService(this.mHelper);
        if (currentService == null) {
            return null;
        }
        return currentService.flattenToShortString();
    }

    class SettingObserver extends AssistSettingObserver {
        @Override // com.android.car.developeroptions.applications.assist.AssistSettingObserver
        protected List<Uri> getSettingUris() {
            return null;
        }

        SettingObserver() {
        }

        @Override // com.android.car.developeroptions.applications.assist.AssistSettingObserver
        /* JADX INFO: renamed from: onSettingChange */
        public void lambda$onChange$0$AssistSettingObserver() throws Throwable {
            DefaultVoiceInputPreferenceController.this.updatePreference();
        }
    }
}
