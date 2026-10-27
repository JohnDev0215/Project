package com.amazon.mp3.language.preference.locale;

import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppLocales.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0018\b\u0086\u0001\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001e¨\u0006 "}, d2 = {"Lcom/amazon/mp3/language/preference/locale/AppLocales;", "", "entryValue", "Ljava/util/Locale;", "(Ljava/lang/String;ILjava/util/Locale;)V", "getEntryValue", "()Ljava/util/Locale;", "getEntryResourceId", "", "EN_US_LOCALE", "EN_GB_LOCALE", "EN_CA_LOCALE", "EN_AU_LOCALE", "ES_ES_LOCALE", "ES_US_LOCALE", "ES_MX_LOCALE", "DE_DE_LOCALE", "FR_FR_LOCALE", "FR_CA_LOCALE", "IT_IT_LOCALE", "PT_BR_LOCALE", "JA_JP_LOCALE", "EN_IN_LOCALE", "HI_IN_LOCALE", "BN_IN_LOCALE", "KN_IN_LOCALE", "ML_IN_LOCALE", "MR_IN_LOCALE", "PA_IN_LOCALE", "TA_IN_LOCALE", "TE_IN_LOCALE", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum AppLocales {
    EN_US_LOCALE(new Locale("en", "US")),
    EN_GB_LOCALE(new Locale("en", "GB")),
    EN_CA_LOCALE(new Locale("en", "CA")),
    EN_AU_LOCALE(new Locale("en", "AU")),
    ES_ES_LOCALE(new Locale("es", "ES")),
    ES_US_LOCALE(new Locale("es", "US")),
    ES_MX_LOCALE(new Locale("es", "MX")),
    DE_DE_LOCALE(new Locale("de", "DE")),
    FR_FR_LOCALE(new Locale("fr", "FR")),
    FR_CA_LOCALE(new Locale("fr", "CA")),
    IT_IT_LOCALE(new Locale("it", "IT")),
    PT_BR_LOCALE(new Locale("pt", "BR")),
    JA_JP_LOCALE(new Locale("ja", "JP")),
    EN_IN_LOCALE(new Locale("en", "IN")),
    HI_IN_LOCALE(new Locale("hi", "IN")),
    BN_IN_LOCALE(new Locale("bn", "IN")),
    KN_IN_LOCALE(new Locale("kn", "IN")),
    ML_IN_LOCALE(new Locale("ml", "IN")),
    MR_IN_LOCALE(new Locale("mr", "IN")),
    PA_IN_LOCALE(new Locale("pa", "IN")),
    TA_IN_LOCALE(new Locale("ta", "IN")),
    TE_IN_LOCALE(new Locale("te", "IN"));

    private final Locale entryValue;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final HashMap<Locale, AppLocales> localeAndAppLocaleMap = new HashMap<>();

    /* JADX INFO: compiled from: AppLocales.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AppLocales.values().length];
            iArr[AppLocales.EN_US_LOCALE.ordinal()] = 1;
            iArr[AppLocales.ES_US_LOCALE.ordinal()] = 2;
            iArr[AppLocales.EN_GB_LOCALE.ordinal()] = 3;
            iArr[AppLocales.EN_CA_LOCALE.ordinal()] = 4;
            iArr[AppLocales.EN_AU_LOCALE.ordinal()] = 5;
            iArr[AppLocales.ES_ES_LOCALE.ordinal()] = 6;
            iArr[AppLocales.ES_MX_LOCALE.ordinal()] = 7;
            iArr[AppLocales.FR_CA_LOCALE.ordinal()] = 8;
            iArr[AppLocales.DE_DE_LOCALE.ordinal()] = 9;
            iArr[AppLocales.FR_FR_LOCALE.ordinal()] = 10;
            iArr[AppLocales.IT_IT_LOCALE.ordinal()] = 11;
            iArr[AppLocales.PT_BR_LOCALE.ordinal()] = 12;
            iArr[AppLocales.JA_JP_LOCALE.ordinal()] = 13;
            iArr[AppLocales.EN_IN_LOCALE.ordinal()] = 14;
            iArr[AppLocales.HI_IN_LOCALE.ordinal()] = 15;
            iArr[AppLocales.BN_IN_LOCALE.ordinal()] = 16;
            iArr[AppLocales.KN_IN_LOCALE.ordinal()] = 17;
            iArr[AppLocales.PA_IN_LOCALE.ordinal()] = 18;
            iArr[AppLocales.ML_IN_LOCALE.ordinal()] = 19;
            iArr[AppLocales.MR_IN_LOCALE.ordinal()] = 20;
            iArr[AppLocales.TA_IN_LOCALE.ordinal()] = 21;
            iArr[AppLocales.TE_IN_LOCALE.ordinal()] = 22;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final AppLocales fromValue(String str) {
        return INSTANCE.fromValue(str);
    }

    @JvmStatic
    public static final AppLocales getAppLocale(Locale locale) {
        return INSTANCE.getAppLocale(locale);
    }

    AppLocales(Locale locale) {
        this.entryValue = locale;
    }

    public final Locale getEntryValue() {
        return this.entryValue;
    }

    static {
        int i = 0;
        AppLocales[] appLocalesArrValues = values();
        int length = appLocalesArrValues.length;
        while (i < length) {
            AppLocales appLocales = appLocalesArrValues[i];
            i++;
            localeAndAppLocaleMap.put(appLocales.entryValue, appLocales);
        }
    }

    /* JADX INFO: compiled from: AppLocales.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0005H\u0007R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/language/preference/locale/AppLocales$Companion;", "", "()V", "localeAndAppLocaleMap", "Ljava/util/HashMap;", "Ljava/util/Locale;", "Lcom/amazon/mp3/language/preference/locale/AppLocales;", "fromValue", Splash.PARAMS_LOCALE, "", "getAppLocale", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AppLocales getAppLocale(Locale locale) {
            Intrinsics.checkNotNullParameter(locale, "locale");
            return (AppLocales) AppLocales.localeAndAppLocaleMap.get(locale);
        }

        @JvmStatic
        public final AppLocales fromValue(String locale) {
            Intrinsics.checkNotNullParameter(locale, "locale");
            try {
                return AppLocales.valueOf(locale);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    public final int getEntryResourceId() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return R.string.en_US;
            case 2:
                return R.string.es_US;
            case 3:
                return R.string.en_GB;
            case 4:
                return R.string.en_CA;
            case 5:
                return R.string.en_AU;
            case 6:
                return R.string.es_ES;
            case 7:
                return R.string.es_MX;
            case 8:
                return R.string.fr_CA;
            case 9:
                return R.string.de_DE;
            case 10:
                return R.string.fr_FR;
            case 11:
                return R.string.it_IT;
            case 12:
                return R.string.pt_BR;
            case 13:
                return R.string.ja_JP;
            case 14:
                return R.string.en_IN;
            case 15:
                return R.string.hi_IN;
            case 16:
                return R.string.bn_IN;
            case 17:
                return R.string.kn_IN;
            case 18:
                return R.string.pa_IN;
            case 19:
                return R.string.ml_IN;
            case 20:
                return R.string.mr_IN;
            case 21:
                return R.string.ta_IN;
            case 22:
                return R.string.te_IN;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
