package com.amazon.mp3.language.preference.locale;

import com.amazon.mp3.AmazonApplication;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'US' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: AppLocaleProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0018\b\u0086\u0001\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bJ\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001e¨\u0006 "}, d2 = {"Lcom/amazon/mp3/language/preference/locale/AppLocaleProvider;", "", "primaryLocale", "Lcom/amazon/mp3/language/preference/locale/AppLocales;", "(Ljava/lang/String;ILcom/amazon/mp3/language/preference/locale/AppLocales;)V", "getPrimaryLocale", "()Lcom/amazon/mp3/language/preference/locale/AppLocales;", "getINGatedLocales", "", "getLocales", "US", "PR", "VI", "GU", "MP", "AS", "GB", "JP", "DE", "MX", "IT", "BR", "ES", "CA", "FR", "AU", "NZ", "CO", "CL", "AR", "IN", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AppLocaleProvider {
    private static final /* synthetic */ AppLocaleProvider[] $VALUES;
    public static final AppLocaleProvider AR;
    public static final AppLocaleProvider AS;
    public static final AppLocaleProvider AU;
    public static final AppLocaleProvider BR;
    public static final AppLocaleProvider CA;
    public static final AppLocaleProvider CL;
    public static final AppLocaleProvider CO;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final AppLocaleProvider DE;
    public static final AppLocaleProvider ES;
    public static final AppLocaleProvider FR;
    public static final AppLocaleProvider GB;
    public static final AppLocaleProvider GU;
    public static final AppLocaleProvider IN;
    public static final AppLocaleProvider IT;
    public static final AppLocaleProvider JP;
    public static final AppLocaleProvider MP;
    public static final AppLocaleProvider MX;
    public static final AppLocaleProvider NZ;
    public static final AppLocaleProvider PR;
    public static final AppLocaleProvider US;
    public static final AppLocaleProvider VI;
    private final AppLocales primaryLocale;

    /* JADX INFO: compiled from: AppLocaleProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AppLocaleProvider.values().length];
            iArr[AppLocaleProvider.IN.ordinal()] = 1;
            iArr[AppLocaleProvider.US.ordinal()] = 2;
            iArr[AppLocaleProvider.PR.ordinal()] = 3;
            iArr[AppLocaleProvider.VI.ordinal()] = 4;
            iArr[AppLocaleProvider.GU.ordinal()] = 5;
            iArr[AppLocaleProvider.MP.ordinal()] = 6;
            iArr[AppLocaleProvider.AS.ordinal()] = 7;
            iArr[AppLocaleProvider.GB.ordinal()] = 8;
            iArr[AppLocaleProvider.DE.ordinal()] = 9;
            iArr[AppLocaleProvider.JP.ordinal()] = 10;
            iArr[AppLocaleProvider.MX.ordinal()] = 11;
            iArr[AppLocaleProvider.IT.ordinal()] = 12;
            iArr[AppLocaleProvider.BR.ordinal()] = 13;
            iArr[AppLocaleProvider.ES.ordinal()] = 14;
            iArr[AppLocaleProvider.CA.ordinal()] = 15;
            iArr[AppLocaleProvider.FR.ordinal()] = 16;
            iArr[AppLocaleProvider.AU.ordinal()] = 17;
            iArr[AppLocaleProvider.NZ.ordinal()] = 18;
            iArr[AppLocaleProvider.CO.ordinal()] = 19;
            iArr[AppLocaleProvider.CL.ordinal()] = 20;
            iArr[AppLocaleProvider.AR.ordinal()] = 21;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ AppLocaleProvider[] $values() {
        return new AppLocaleProvider[]{US, PR, VI, GU, MP, AS, GB, JP, DE, MX, IT, BR, ES, CA, FR, AU, NZ, CO, CL, AR, IN};
    }

    @JvmStatic
    public static final AppLocaleProvider fromValue(String str) {
        return INSTANCE.fromValue(str);
    }

    public static AppLocaleProvider valueOf(String str) {
        return (AppLocaleProvider) Enum.valueOf(AppLocaleProvider.class, str);
    }

    public static AppLocaleProvider[] values() {
        return (AppLocaleProvider[]) $VALUES.clone();
    }

    private AppLocaleProvider(String str, int i, AppLocales appLocales) {
        this.primaryLocale = appLocales;
    }

    public final AppLocales getPrimaryLocale() {
        return this.primaryLocale;
    }

    static {
        AppLocales appLocales = AppLocales.EN_US_LOCALE;
        US = new AppLocaleProvider("US", 0, appLocales);
        PR = new AppLocaleProvider("PR", 1, appLocales);
        VI = new AppLocaleProvider("VI", 2, appLocales);
        GU = new AppLocaleProvider("GU", 3, appLocales);
        MP = new AppLocaleProvider("MP", 4, appLocales);
        AS = new AppLocaleProvider("AS", 5, appLocales);
        GB = new AppLocaleProvider("GB", 6, AppLocales.EN_GB_LOCALE);
        JP = new AppLocaleProvider("JP", 7, AppLocales.JA_JP_LOCALE);
        DE = new AppLocaleProvider("DE", 8, AppLocales.DE_DE_LOCALE);
        AppLocales appLocales2 = AppLocales.ES_MX_LOCALE;
        MX = new AppLocaleProvider("MX", 9, appLocales2);
        IT = new AppLocaleProvider("IT", 10, AppLocales.IT_IT_LOCALE);
        BR = new AppLocaleProvider("BR", 11, AppLocales.PT_BR_LOCALE);
        ES = new AppLocaleProvider("ES", 12, AppLocales.ES_ES_LOCALE);
        CA = new AppLocaleProvider("CA", 13, AppLocales.EN_CA_LOCALE);
        FR = new AppLocaleProvider("FR", 14, AppLocales.FR_FR_LOCALE);
        AppLocales appLocales3 = AppLocales.EN_AU_LOCALE;
        AU = new AppLocaleProvider("AU", 15, appLocales3);
        NZ = new AppLocaleProvider("NZ", 16, appLocales3);
        CO = new AppLocaleProvider("CO", 17, appLocales2);
        CL = new AppLocaleProvider("CL", 18, appLocales2);
        AR = new AppLocaleProvider("AR", 19, appLocales2);
        IN = new AppLocaleProvider("IN", 20, AppLocales.EN_IN_LOCALE);
        $VALUES = $values();
        INSTANCE = new Companion(null);
    }

    public final List<AppLocales> getLocales() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.EN_IN_LOCALE, AppLocales.HI_IN_LOCALE, AppLocales.BN_IN_LOCALE, AppLocales.KN_IN_LOCALE, AppLocales.ML_IN_LOCALE, AppLocales.MR_IN_LOCALE, AppLocales.PA_IN_LOCALE, AppLocales.TA_IN_LOCALE, AppLocales.TE_IN_LOCALE});
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.EN_US_LOCALE, AppLocales.ES_US_LOCALE, AppLocales.DE_DE_LOCALE, AppLocales.FR_FR_LOCALE, AppLocales.IT_IT_LOCALE, AppLocales.JA_JP_LOCALE, AppLocales.PT_BR_LOCALE});
            case 8:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.EN_GB_LOCALE, AppLocales.FR_FR_LOCALE, AppLocales.ES_ES_LOCALE, AppLocales.DE_DE_LOCALE, AppLocales.IT_IT_LOCALE});
            case 9:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.DE_DE_LOCALE, AppLocales.EN_GB_LOCALE, AppLocales.ES_ES_LOCALE, AppLocales.FR_FR_LOCALE, AppLocales.IT_IT_LOCALE});
            case 10:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.JA_JP_LOCALE, AppLocales.EN_US_LOCALE});
            case 11:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.ES_MX_LOCALE, AppLocales.EN_US_LOCALE});
            case 12:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.IT_IT_LOCALE, AppLocales.EN_GB_LOCALE, AppLocales.ES_ES_LOCALE, AppLocales.DE_DE_LOCALE, AppLocales.FR_FR_LOCALE});
            case 13:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.PT_BR_LOCALE, AppLocales.EN_US_LOCALE, AppLocales.ES_MX_LOCALE});
            case 14:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.ES_ES_LOCALE, AppLocales.EN_GB_LOCALE, AppLocales.FR_FR_LOCALE, AppLocales.DE_DE_LOCALE, AppLocales.IT_IT_LOCALE});
            case 15:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.EN_CA_LOCALE, AppLocales.FR_CA_LOCALE});
            case 16:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.FR_FR_LOCALE, AppLocales.EN_GB_LOCALE, AppLocales.PT_BR_LOCALE, AppLocales.ES_ES_LOCALE, AppLocales.DE_DE_LOCALE, AppLocales.IT_IT_LOCALE});
            case 17:
            case 18:
                return CollectionsKt__CollectionsJVMKt.listOf(AppLocales.EN_AU_LOCALE);
            case 19:
            case 20:
            case 21:
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.ES_MX_LOCALE, AppLocales.EN_US_LOCALE});
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final List<AppLocales> getINGatedLocales() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                if (AmazonApplication.getCapabilities().isINLanguageExpansionSupported()) {
                    return CollectionsKt__CollectionsKt.emptyList();
                }
                return CollectionsKt__CollectionsKt.listOf((Object[]) new AppLocales[]{AppLocales.BN_IN_LOCALE, AppLocales.KN_IN_LOCALE, AppLocales.ML_IN_LOCALE, AppLocales.MR_IN_LOCALE, AppLocales.PA_IN_LOCALE, AppLocales.TA_IN_LOCALE, AppLocales.TE_IN_LOCALE});
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return CollectionsKt__CollectionsKt.emptyList();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: compiled from: AppLocaleProvider.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/language/preference/locale/AppLocaleProvider$Companion;", "", "()V", "fromValue", "Lcom/amazon/mp3/language/preference/locale/AppLocaleProvider;", "territory", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AppLocaleProvider fromValue(String territory) {
            Intrinsics.checkNotNullParameter(territory, "territory");
            try {
                return AppLocaleProvider.valueOf(territory);
            } catch (Exception unused) {
                return null;
            }
        }
    }
}
