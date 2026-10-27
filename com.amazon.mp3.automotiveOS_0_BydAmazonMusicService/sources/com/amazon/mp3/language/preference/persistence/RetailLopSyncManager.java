package com.amazon.mp3.language.preference.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.language.preference.locale.AppLocales;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: RetailLopSyncManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\rH\u0002J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u000bH\u0002J\u0018\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0002J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/mp3/language/preference/persistence/RetailLopSyncManager;", "", "context", "Landroid/content/Context;", "sharedPreferences", "Landroid/content/SharedPreferences;", "(Landroid/content/Context;Landroid/content/SharedPreferences;)V", "fetchRetailLanguageOfPreference", "", "customerId", "fetchRetailLopFetchTime", "", "hasUserInteractedWithAMLanguageSelector", "", "isRetailLopFetched", "isRetailLopSyncCompleted", "isRetailLopSyncNotificationShown", "putBoolean", "", "key", "value", "putLong", "putString", "retailLopSyncAccepted", Splash.PARAMS_LOCALE, "Ljava/util/Locale;", "retailLopSyncCompleted", "retailLopSyncNotificationShown", "saveRetailLanguageOfPreference", "languageOfPreference", "saveTimeOfRetailLopFetch", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class RetailLopSyncManager {
    private final Context context;
    private final SharedPreferences sharedPreferences;

    public RetailLopSyncManager(Context context, SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }

    public void saveRetailLanguageOfPreference(String languageOfPreference, String customerId) {
        Intrinsics.checkNotNullParameter(languageOfPreference, "languageOfPreference");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this.context.getString(R.string.retail_lop);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.retail_lop)");
        String str = String.format(string, Arrays.copyOf(new Object[]{customerId}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        putString(str, languageOfPreference);
    }

    public void saveTimeOfRetailLopFetch(String customerId) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this.context.getString(R.string.retail_lop_fetch_time);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.retail_lop_fetch_time)");
        String str = String.format(string, Arrays.copyOf(new Object[]{customerId}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        putLong(str, System.currentTimeMillis());
    }

    public long fetchRetailLopFetchTime(String customerId) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this.context.getString(R.string.retail_lop_fetch_time);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.retail_lop_fetch_time)");
        String str = String.format(string, Arrays.copyOf(new Object[]{customerId}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return this.sharedPreferences.getLong(str, 0L);
    }

    public String fetchRetailLanguageOfPreference(String customerId) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this.context.getString(R.string.retail_lop);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.retail_lop)");
        String str = String.format(string, Arrays.copyOf(new Object[]{customerId}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return this.sharedPreferences.getString(str, "");
    }

    public void retailLopSyncNotificationShown(String customerId) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this.context.getString(R.string.retail_lop_notification_shown);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…l_lop_notification_shown)");
        String str = String.format(string, Arrays.copyOf(new Object[]{customerId}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        putBoolean(str, true);
    }

    public boolean isRetailLopFetched(String customerId) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this.context.getString(R.string.retail_lop);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.retail_lop)");
        String str = String.format(string, Arrays.copyOf(new Object[]{customerId}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return this.sharedPreferences.contains(str);
    }

    public boolean isRetailLopSyncNotificationShown(String customerId) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this.context.getString(R.string.retail_lop_notification_shown);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…l_lop_notification_shown)");
        String str = String.format(string, Arrays.copyOf(new Object[]{customerId}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return this.sharedPreferences.getBoolean(str, false);
    }

    public void retailLopSyncCompleted(String customerId) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this.context.getString(R.string.retail_lop_sync_completed);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…etail_lop_sync_completed)");
        String str = String.format(string, Arrays.copyOf(new Object[]{customerId}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        putBoolean(str, true);
    }

    public boolean isRetailLopSyncCompleted(String customerId) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this.context.getString(R.string.retail_lop_sync_completed);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…etail_lop_sync_completed)");
        String str = String.format(string, Arrays.copyOf(new Object[]{customerId}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return this.sharedPreferences.getBoolean(str, false);
    }

    public void retailLopSyncAccepted(Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        AppLocales appLocale = AppLocales.INSTANCE.getAppLocale(locale);
        String string = this.context.getString(R.string.setting_app_display_language);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ing_app_display_language)");
        putString(string, String.valueOf(appLocale));
    }

    public boolean hasUserInteractedWithAMLanguageSelector() {
        String string = this.context.getString(R.string.setting_app_display_language);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ing_app_display_language)");
        return this.sharedPreferences.contains(string);
    }

    private final void putLong(String key, long value) {
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putLong(key, value);
        editorEdit.apply();
    }

    private final void putString(String key, String value) {
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putString(key, value);
        editorEdit.apply();
    }

    private final void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putBoolean(key, value);
        editorEdit.apply();
    }
}
