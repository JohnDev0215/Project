package com.amazon.mp3.language.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.WideDialogActivity;
import com.amazon.mp3.activity.WideDialogActivityPurpose;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.language.preference.locale.AppLocaleProvider;
import com.amazon.mp3.language.preference.locale.AppLocales;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppDisplayLanguageSetting.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\nH\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0007\u001a\u00020\u0004J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/language/preference/AppDisplayLanguageSetting;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getDeviceLocaleIfSupported", Splash.PARAMS_MUSIC_TERRITORY, "getDisplayLanguage", "context", "Landroid/content/Context;", "getDisplayLanguageFromPreference", "handleManageLanguageClick", "", "isAppLocaleSupportedByMusicTerritory", "appLocale", "Lcom/amazon/mp3/language/preference/locale/AppLocales;", "setDisplayLanguagePreference", "", "manageLanguagePreference", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AppDisplayLanguageSetting {
    public static final AppDisplayLanguageSetting INSTANCE = new AppDisplayLanguageSetting();
    private static final String TAG = AppDisplayLanguageSetting.class.getSimpleName();

    private AppDisplayLanguageSetting() {
    }

    public final boolean handleManageLanguageClick(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        WideDialogActivity.Companion.showActivity$default(WideDialogActivity.INSTANCE, context, WideDialogActivityPurpose.SHOW_APP_DISPLAY_LANGUAGE_PREFERENCES, R.style.Theme_AmazonMP3_Dialog, null, 8, null);
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.GO_MANAGE_LANGUAGE).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withPageType(PageType.SETTINGS).build());
        return true;
    }

    public final String getDisplayLanguage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String musicTerritory = AccountDetailUtil.getMusicTerritoryOfResidence();
        String displayLanguageFromPreference = getDisplayLanguageFromPreference(context);
        if (displayLanguageFromPreference != null) {
            AppLocales appLocalesFromValue = AppLocales.INSTANCE.fromValue(displayLanguageFromPreference);
            Intrinsics.checkNotNullExpressionValue(musicTerritory, "musicTerritory");
            if (isAppLocaleSupportedByMusicTerritory(appLocalesFromValue, musicTerritory)) {
                Log.debug(TAG, "using user preferred locale as bootstrap language : %s", displayLanguageFromPreference);
                return displayLanguageFromPreference;
            }
        }
        Intrinsics.checkNotNullExpressionValue(musicTerritory, "musicTerritory");
        String deviceLocaleIfSupported = getDeviceLocaleIfSupported(musicTerritory);
        if (deviceLocaleIfSupported != null) {
            Log.debug(TAG, "using device locale as bootstrap language : %s", deviceLocaleIfSupported);
            return deviceLocaleIfSupported;
        }
        AppLocaleProvider appLocaleProviderFromValue = AppLocaleProvider.INSTANCE.fromValue(musicTerritory);
        AppLocales primaryLocale = appLocaleProviderFromValue == null ? null : appLocaleProviderFromValue.getPrimaryLocale();
        if (primaryLocale == null) {
            primaryLocale = AppLocales.EN_US_LOCALE;
        }
        String string = primaryLocale.toString();
        Log.debug(TAG, "using primary locale as bootstrap language : %s, music territory : %s", string, musicTerritory);
        return string;
    }

    private final String getDeviceLocaleIfSupported(String musicTerritory) {
        Locale deviceLocale = AmazonApplication.getDeviceLocale();
        if (deviceLocale == null) {
            return null;
        }
        AppLocales.Companion companion = AppLocales.INSTANCE;
        if (companion.getAppLocale(deviceLocale) == null) {
            return null;
        }
        AppLocales appLocale = companion.getAppLocale(deviceLocale);
        if (isAppLocaleSupportedByMusicTerritory(appLocale, musicTerritory)) {
            return String.valueOf(appLocale);
        }
        return null;
    }

    public final boolean isAppLocaleSupportedByMusicTerritory(AppLocales appLocale, String musicTerritory) {
        Intrinsics.checkNotNullParameter(musicTerritory, "musicTerritory");
        if (appLocale == null) {
            Log.error(TAG, Intrinsics.stringPlus("isAppLocaleSupportedByMusicTerritory: appLocale is null, musicTerritory=", musicTerritory));
            return false;
        }
        AppLocaleProvider appLocaleProviderFromValue = AppLocaleProvider.INSTANCE.fromValue(musicTerritory);
        if (appLocaleProviderFromValue == null) {
            Log.error(TAG, Intrinsics.stringPlus("isAppLocaleSupportedByMusicTerritory: appLocaleProvider is null, musicTerritory=", musicTerritory));
            return false;
        }
        List<AppLocales> locales = appLocaleProviderFromValue.getLocales();
        if (!appLocaleProviderFromValue.getINGatedLocales().contains(appLocale) && locales.contains(appLocale)) {
            return true;
        }
        Log.debug(TAG, "App Locale : %s not supported by music territory : %s", appLocale, musicTerritory);
        return false;
    }

    private final String getDisplayLanguageFromPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.setting_app_display_language), null);
    }

    public final void setDisplayLanguagePreference(Context context, String manageLanguagePreference) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        String string = context.getString(R.string.setting_app_display_language);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ing_app_display_language)");
        editorEdit.putString(string, manageLanguagePreference);
        editorEdit.apply();
    }
}
