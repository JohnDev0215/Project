package com.amazon.mp3.voice.onboarding.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.music.platform.aom.onboarding.provider.WakeWordSettingProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaWakeWordSettingProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\u000bH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/provider/AlexaWakeWordSettingProvider;", "Lcom/amazon/music/platform/aom/onboarding/provider/WakeWordSettingProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "KEY_WAKE_WORD_AUTO_ENABLE", "", "sharedPref", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "canAutoEnableWakeWord", "", "clearWakeWordSetting", "", "isWakeWordUserSettingEnabled", "markCannotAutoEnableWakeWord", "setWakeWordUserSetting", "shouldEnable", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaWakeWordSettingProvider implements WakeWordSettingProvider {
    private final String KEY_WAKE_WORD_AUTO_ENABLE;
    private final Context context;
    private final SharedPreferences sharedPref;

    public AlexaWakeWordSettingProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.KEY_WAKE_WORD_AUTO_ENABLE = "setting_key_wake_word_auto_enable";
        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override // com.amazon.music.platform.aom.onboarding.provider.WakeWordSettingProvider
    public boolean canAutoEnableWakeWord() {
        return this.sharedPref.getBoolean(this.KEY_WAKE_WORD_AUTO_ENABLE, true);
    }

    @Override // com.amazon.music.platform.aom.onboarding.provider.WakeWordSettingProvider
    public void setWakeWordUserSetting(boolean shouldEnable) {
        SettingsUtil.enableWakeWordUserSetting(this.context, shouldEnable);
    }

    @Override // com.amazon.music.platform.aom.onboarding.provider.WakeWordSettingProvider
    public boolean isWakeWordUserSettingEnabled() {
        return SettingsUtil.isWakeWordUserSettingEnabled(this.context);
    }

    @Override // com.amazon.music.platform.aom.onboarding.provider.WakeWordSettingProvider
    public void markCannotAutoEnableWakeWord() {
        SharedPreferences sharedPref = this.sharedPref;
        Intrinsics.checkNotNullExpressionValue(sharedPref, "sharedPref");
        SharedPreferences.Editor editor = sharedPref.edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        editor.putBoolean(this.KEY_WAKE_WORD_AUTO_ENABLE, false);
        editor.apply();
    }

    public final void clearWakeWordSetting() {
        SharedPreferences sharedPref = this.sharedPref;
        Intrinsics.checkNotNullExpressionValue(sharedPref, "sharedPref");
        SharedPreferences.Editor editor = sharedPref.edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        editor.remove(this.KEY_WAKE_WORD_AUTO_ENABLE);
        editor.apply();
    }
}
