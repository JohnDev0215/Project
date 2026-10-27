package com.amazon.mp3.language.preference.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import androidx.annotation.RequiresApi;
import com.amazon.hermes.Hermes;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.language.preference.AppDisplayLanguageSetting;
import com.amazon.mp3.language.preference.locale.AppLocales;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocaleManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\u0010\u001a\u00020\tJ\u000e\u0010\u0011\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\u0011\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007J\u001a\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0007H\u0003J\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/language/preference/util/LocaleManager;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "appLocale", "Ljava/util/Locale;", "clearCache", "", "initializeAppLocale", "context", "Landroid/content/Context;", "isAppLocaleAvailable", "", "isLocaleUpdateRequired", "onUserSignOut", "setLocale", Splash.PARAMS_LOCALE, "setLocaleForApi24", "config", "Landroid/content/res/Configuration;", "target", "updateResources", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LocaleManager {
    public static final LocaleManager INSTANCE = new LocaleManager();
    private static final String TAG = LocaleManager.class.getSimpleName();
    private static Locale appLocale;

    private LocaleManager() {
    }

    public final void setLocale(Context context, Locale locale) {
        Intrinsics.checkNotNullParameter(context, "context");
        Log.debug(TAG, "Setting app locale to : %s", String.valueOf(locale));
        appLocale = locale;
        setLocale(context);
        clearCache();
    }

    public final void setLocale(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (appLocale == null) {
            if (!AccountCredentialUtil.get(context).isSignedIn()) {
                return;
            }
            Log.debug(TAG, "Initializing app locale on user sign in");
            initializeAppLocale(context);
        }
        Log.debug(TAG, "Updating locale to %s", appLocale);
        updateResources(context);
        Context appContext = context.getApplicationContext();
        if (context != appContext) {
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            updateResources(appContext);
        }
    }

    private final void initializeAppLocale(Context context) {
        AppLocales appLocalesFromValue = AppLocales.INSTANCE.fromValue(AppDisplayLanguageSetting.INSTANCE.getDisplayLanguage(context));
        Locale entryValue = appLocalesFromValue == null ? null : appLocalesFromValue.getEntryValue();
        appLocale = entryValue;
        Log.debug(TAG, "AppLocale Initialized to : %s", entryValue);
    }

    private final void updateResources(Context context) {
        if (isLocaleUpdateRequired(context)) {
            Locale.setDefault(appLocale);
            Resources resources = context.getResources();
            Configuration configuration = new Configuration(resources.getConfiguration());
            setLocaleForApi24(configuration, appLocale);
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
    }

    public final boolean isLocaleUpdateRequired(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Locale locale = context.getResources().getConfiguration().getLocales().get(0);
        Intrinsics.checkNotNullExpressionValue(locale, "{\n            context.re…tion.locales[0]\n        }");
        Log.debug(TAG, "activity context locale:%s, appLocale:%s", locale.getDisplayName(), appLocale);
        return !Intrinsics.areEqual(locale, appLocale);
    }

    @RequiresApi(api = 24)
    private final void setLocaleForApi24(Configuration config, Locale target) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(target);
        LocaleList localeList = LocaleList.getDefault();
        Intrinsics.checkNotNullExpressionValue(localeList, "getDefault()");
        int size = localeList.size();
        for (int i = 0; i < size; i++) {
            linkedHashSet.add(localeList.get(i));
        }
        Object[] array = linkedHashSet.toArray(new Locale[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Locale[] localeArr = (Locale[]) array;
        config.setLocales(new LocaleList((Locale[]) Arrays.copyOf(localeArr, localeArr.length)));
    }

    public final void onUserSignOut() {
        Log.debug(TAG, "Clearing appLocale on user sign out");
        appLocale = null;
        clearCache();
    }

    private final void clearCache() {
        Log.debug(TAG, "Clearing locale cache");
        Hermes.get().getRequestQueue().getCache().clear();
    }

    public final boolean isAppLocaleAvailable() {
        return appLocale != null;
    }
}
