package com.android.car.settings.applications.assist;

import android.content.ComponentName;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes5.dex */
final class VoiceInputUtils {
    private VoiceInputUtils() {
    }

    static ComponentName getCurrentService(Context context) {
        ComponentName currentVoiceInteraction = getComponentNameOrNull(context, "voice_interaction_service");
        ComponentName currentVoiceRecognizer = getComponentNameOrNull(context, "voice_recognition_service");
        if (currentVoiceInteraction != null) {
            return currentVoiceInteraction;
        }
        if (currentVoiceRecognizer != null) {
            return currentVoiceRecognizer;
        }
        return null;
    }

    private static ComponentName getComponentNameOrNull(Context context, String secureSettingKey) {
        String currentSetting = Settings.Secure.getString(context.getContentResolver(), secureSettingKey);
        if (!TextUtils.isEmpty(currentSetting)) {
            return ComponentName.unflattenFromString(currentSetting);
        }
        return null;
    }
}
