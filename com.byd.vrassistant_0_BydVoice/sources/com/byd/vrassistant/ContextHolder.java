package com.byd.vrassistant;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.LocaleList;
import com.byd.vrassistant.utils.RLog;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class ContextHolder {
    private static final String TAG = "NEU_ContextHolder";
    private static Context appContext;
    public static Application application;
    public static Locale locale;
    private static Context sysContext;
    public static BehaviorSubject<Locale> vrLocaleBsj = BehaviorSubject.create();
    public static BehaviorSubject<Locale> sysLocaleBsj = BehaviorSubject.create();
    public static BehaviorSubject<Locale> toastLocaleBsj = BehaviorSubject.create();
    public static List<String> availableLang = Arrays.asList("en", "da", "de", "es", "fi", "fr", "it", "iw", "ja", "nb", "nl", "pl", "pt", "sv", "th", "tr", "ko", "zh");

    static {
        sysLocaleBsj.flatMap(new Function() { // from class: com.byd.vrassistant.ContextHolder$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return ContextHolder.lambda$static$1((Locale) obj);
            }
        }).subscribe(toastLocaleBsj);
    }

    static /* synthetic */ ObservableSource lambda$static$1(final Locale locale2) throws Throwable {
        if (availableLang.stream().anyMatch(new Predicate() { // from class: com.byd.vrassistant.ContextHolder$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return locale2.getLanguage().contains((String) obj);
            }
        })) {
            return sysLocaleBsj;
        }
        return vrLocaleBsj;
    }

    public static Application getApplication() {
        return application;
    }

    public static Context getSysContext() {
        if (sysContext == null) {
            sysContext = application.getApplicationContext();
        }
        Configuration configuration = sysContext.getResources().getConfiguration();
        configuration.setLocale(sysLocaleBsj.getValue());
        sysContext.getResources().updateConfiguration(configuration, sysContext.getResources().getDisplayMetrics());
        return sysContext;
    }

    public static Context getApplicationContext() {
        if (appContext == null) {
            appContext = application.getApplicationContext();
        }
        return appContext;
    }

    public static BehaviorSubject<Locale> getToastLocaleBsj() {
        return toastLocaleBsj;
    }

    public static Locale getCurrentLocale() {
        if (locale == null) {
            Locale locale2 = getApplicationContext().getResources().getConfiguration().getLocales().get(0);
            locale = locale2;
            updateSysLocale(locale2);
        }
        return locale;
    }

    public static void updateSysLocale(Locale locale2) {
        sysLocaleBsj.onNext(locale2);
    }

    public static void updateLocale(Locale locale2) {
        if (locale2 == null) {
            return;
        }
        vrLocaleBsj.onNext(locale2);
        RLog.i(TAG, "updateLocale: " + locale2);
        locale = locale2;
        LocaleList localeList = new LocaleList(locale2);
        LocaleList.setDefault(localeList);
        Configuration configuration = new Configuration(getApplicationContext().getResources().getConfiguration());
        configuration.setLocales(localeList);
        appContext = getApplicationContext().createConfigurationContext(configuration);
    }
}
