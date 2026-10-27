package com.amazon.mp3.configuration;

import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.voice.util.AlexaLocales;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaLocaleProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005H\u0002J+\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u0001¢\u0006\u0002\b\u0014J\"\u0010\u0015\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u0006H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\u00068FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/configuration/AlexaLocaleProvider;", "", "()V", "ALEXA_LOCALE_MAP", "", "", "Ljava/util/Locale;", "DELIMITER", Splash.PARAMS_LOCALE, "getLocale$annotations", "getLocale", "()Ljava/util/Locale;", "getAlexaLocale", "territoryOrMarketplaceKey", "language", "getLocaleFromTerritoryOrMarketplace", "territory", IntlConfiguration.MARKETPLACE, "Lcom/amazon/music/marketplace/Marketplace;", "defaultLanguage", "getLocaleFromTerritoryOrMarketplace$DigitalMusicAndroid3P_automotiveProdRelease", "setAlexaLocale", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaLocaleProvider {
    private static final Map<String, Locale> ALEXA_LOCALE_MAP;
    private static final String DELIMITER;
    public static final AlexaLocaleProvider INSTANCE;

    private AlexaLocaleProvider() {
    }

    static {
        AlexaLocaleProvider alexaLocaleProvider = new AlexaLocaleProvider();
        INSTANCE = alexaLocaleProvider;
        DELIMITER = Attributes.PREDEFINED_ATTRIBUTE_PREFIX;
        ALEXA_LOCALE_MAP = new HashMap();
        MusicTerritory musicTerritory = MusicTerritory.CANADA;
        String str = musicTerritory.value;
        Intrinsics.checkNotNullExpressionValue(str, "CANADA.value");
        Locale ALEXA_CA_FR_LOCALE = AlexaLocales.ALEXA_CA_FR_LOCALE;
        Locale locale = Locale.US;
        String displayLanguage = ALEXA_CA_FR_LOCALE.getDisplayLanguage(locale);
        Intrinsics.checkNotNullExpressionValue(ALEXA_CA_FR_LOCALE, "ALEXA_CA_FR_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str, displayLanguage, ALEXA_CA_FR_LOCALE);
        MusicTerritory musicTerritory2 = MusicTerritory.US;
        String str2 = musicTerritory2.value;
        Intrinsics.checkNotNullExpressionValue(str2, "US.value");
        Locale ALEXA_ES_US_LOCALE = AlexaLocales.ALEXA_ES_US_LOCALE;
        String displayLanguage2 = ALEXA_ES_US_LOCALE.getDisplayLanguage(locale);
        Intrinsics.checkNotNullExpressionValue(ALEXA_ES_US_LOCALE, "ALEXA_ES_US_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str2, displayLanguage2, ALEXA_ES_US_LOCALE);
        if (AmazonApplication.getCapabilities().isAOMHindiSupportEnabled()) {
            String str3 = MusicTerritory.INDIA.value;
            Intrinsics.checkNotNullExpressionValue(str3, "INDIA.value");
            Locale ALEXA_HI_IN_LOCALE = AlexaLocales.ALEXA_HI_IN_LOCALE;
            String displayLanguage3 = ALEXA_HI_IN_LOCALE.getDisplayLanguage(locale);
            Intrinsics.checkNotNullExpressionValue(ALEXA_HI_IN_LOCALE, "ALEXA_HI_IN_LOCALE");
            alexaLocaleProvider.setAlexaLocale(str3, displayLanguage3, ALEXA_HI_IN_LOCALE);
        }
        String str4 = musicTerritory2.value;
        Intrinsics.checkNotNullExpressionValue(str4, "US.value");
        Locale ALEXA_US_LOCALE = AlexaLocales.ALEXA_US_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_US_LOCALE, "ALEXA_US_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str4, null, ALEXA_US_LOCALE);
        String str5 = musicTerritory.value;
        Intrinsics.checkNotNullExpressionValue(str5, "CANADA.value");
        Locale ALEXA_CA_LOCALE = AlexaLocales.ALEXA_CA_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_CA_LOCALE, "ALEXA_CA_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str5, null, ALEXA_CA_LOCALE);
        String str6 = MusicTerritory.INDIA.value;
        Intrinsics.checkNotNullExpressionValue(str6, "INDIA.value");
        Locale ALEXA_IN_LOCALE = AlexaLocales.ALEXA_IN_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_IN_LOCALE, "ALEXA_IN_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str6, null, ALEXA_IN_LOCALE);
        String str7 = MusicTerritory.UK.value;
        Intrinsics.checkNotNullExpressionValue(str7, "UK.value");
        Locale ALEXA_UK_LOCALE = AlexaLocales.ALEXA_UK_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_UK_LOCALE, "ALEXA_UK_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str7, null, ALEXA_UK_LOCALE);
        String str8 = MusicTerritory.GERMANY.value;
        Intrinsics.checkNotNullExpressionValue(str8, "GERMANY.value");
        Locale ALEXA_DE_LOCALE = AlexaLocales.ALEXA_DE_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_DE_LOCALE, "ALEXA_DE_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str8, null, ALEXA_DE_LOCALE);
        String str9 = MusicTerritory.JAPAN.value;
        Intrinsics.checkNotNullExpressionValue(str9, "JAPAN.value");
        Locale ALEXA_JP_LOCALE = AlexaLocales.ALEXA_JP_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_JP_LOCALE, "ALEXA_JP_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str9, null, ALEXA_JP_LOCALE);
        String str10 = MusicTerritory.NEW_ZEALAND.value;
        Intrinsics.checkNotNullExpressionValue(str10, "NEW_ZEALAND.value");
        Locale ALEXA_AU_LOCALE = AlexaLocales.ALEXA_AU_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_AU_LOCALE, "ALEXA_AU_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str10, null, ALEXA_AU_LOCALE);
        String str11 = MusicTerritory.AUSTRALIA.value;
        Intrinsics.checkNotNullExpressionValue(str11, "AUSTRALIA.value");
        Intrinsics.checkNotNullExpressionValue(ALEXA_AU_LOCALE, "ALEXA_AU_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str11, null, ALEXA_AU_LOCALE);
        String str12 = MusicTerritory.FRANCE.value;
        Intrinsics.checkNotNullExpressionValue(str12, "FRANCE.value");
        Locale ALEXA_FR_LOCALE = AlexaLocales.ALEXA_FR_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_FR_LOCALE, "ALEXA_FR_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str12, null, ALEXA_FR_LOCALE);
        String str13 = MusicTerritory.ITALY.value;
        Intrinsics.checkNotNullExpressionValue(str13, "ITALY.value");
        Locale ALEXA_IT_LOCALE = AlexaLocales.ALEXA_IT_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_IT_LOCALE, "ALEXA_IT_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str13, null, ALEXA_IT_LOCALE);
        String str14 = MusicTerritory.SPAIN.value;
        Intrinsics.checkNotNullExpressionValue(str14, "SPAIN.value");
        Locale ALEXA_ES_LOCALE = AlexaLocales.ALEXA_ES_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_ES_LOCALE, "ALEXA_ES_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str14, null, ALEXA_ES_LOCALE);
        String str15 = MusicTerritory.MEXICO.value;
        Intrinsics.checkNotNullExpressionValue(str15, "MEXICO.value");
        Locale ALEXA_MX_LOCALE = AlexaLocales.ALEXA_MX_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_MX_LOCALE, "ALEXA_MX_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str15, null, ALEXA_MX_LOCALE);
        String str16 = MusicTerritory.BRAZIL.value;
        Intrinsics.checkNotNullExpressionValue(str16, "BRAZIL.value");
        Locale ALEXA_BR_LOCALE = AlexaLocales.ALEXA_BR_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_BR_LOCALE, "ALEXA_BR_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str16, null, ALEXA_BR_LOCALE);
        String str17 = MusicTerritory.IRELAND.value;
        Intrinsics.checkNotNullExpressionValue(str17, "IRELAND.value");
        Intrinsics.checkNotNullExpressionValue(ALEXA_UK_LOCALE, "ALEXA_UK_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str17, null, ALEXA_UK_LOCALE);
        String str18 = MusicTerritory.NETHERLANDS.value;
        Intrinsics.checkNotNullExpressionValue(str18, "NETHERLANDS.value");
        Locale ALEXA_NL_LOCALE = AlexaLocales.ALEXA_NL_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_NL_LOCALE, "ALEXA_NL_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str18, null, ALEXA_NL_LOCALE);
        String str19 = MusicTerritory.COLUMBIA.value;
        Intrinsics.checkNotNullExpressionValue(str19, "COLUMBIA.value");
        Intrinsics.checkNotNullExpressionValue(ALEXA_ES_US_LOCALE, "ALEXA_ES_US_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str19, null, ALEXA_ES_US_LOCALE);
        String str20 = MusicTerritory.COSTA_RICA.value;
        Intrinsics.checkNotNullExpressionValue(str20, "COSTA_RICA.value");
        Intrinsics.checkNotNullExpressionValue(ALEXA_ES_US_LOCALE, "ALEXA_ES_US_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str20, null, ALEXA_ES_US_LOCALE);
        String str21 = MusicTerritory.CHILE.value;
        Intrinsics.checkNotNullExpressionValue(str21, "CHILE.value");
        Intrinsics.checkNotNullExpressionValue(ALEXA_ES_US_LOCALE, "ALEXA_ES_US_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str21, null, ALEXA_ES_US_LOCALE);
        String str22 = MusicTerritory.ARGENTINA.value;
        Intrinsics.checkNotNullExpressionValue(str22, "ARGENTINA.value");
        Intrinsics.checkNotNullExpressionValue(ALEXA_ES_US_LOCALE, "ALEXA_ES_US_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str22, null, ALEXA_ES_US_LOCALE);
        String str23 = MusicTerritory.PERU.value;
        Intrinsics.checkNotNullExpressionValue(str23, "PERU.value");
        Intrinsics.checkNotNullExpressionValue(ALEXA_ES_US_LOCALE, "ALEXA_ES_US_LOCALE");
        alexaLocaleProvider.setAlexaLocale(str23, null, ALEXA_ES_US_LOCALE);
        String id = Marketplace.ROW_NA.getId();
        Intrinsics.checkNotNullExpressionValue(id, "ROW_NA.id");
        Intrinsics.checkNotNullExpressionValue(ALEXA_ES_US_LOCALE, "ALEXA_ES_US_LOCALE");
        alexaLocaleProvider.setAlexaLocale(id, null, ALEXA_ES_US_LOCALE);
    }

    public static final Locale getLocale() {
        return INSTANCE.getLocaleFromTerritoryOrMarketplace$DigitalMusicAndroid3P_automotiveProdRelease(AccountDetailUtil.getMusicTerritoryOfResidence(), AccountDetailUtil.get().getHomeMarketPlace(), Locale.getDefault().getDisplayLanguage(Locale.US));
    }

    private final void setAlexaLocale(String territoryOrMarketplaceKey, String language, Locale locale) {
        if (language != null) {
            territoryOrMarketplaceKey = territoryOrMarketplaceKey + DELIMITER + ((Object) language);
        }
        ALEXA_LOCALE_MAP.put(territoryOrMarketplaceKey, locale);
    }

    private final Locale getAlexaLocale(String territoryOrMarketplaceKey, String language) {
        if (territoryOrMarketplaceKey == null) {
            return null;
        }
        if (language != null) {
            territoryOrMarketplaceKey = territoryOrMarketplaceKey + DELIMITER + ((Object) language);
        }
        return ALEXA_LOCALE_MAP.get(territoryOrMarketplaceKey);
    }

    @VisibleForTesting
    public final Locale getLocaleFromTerritoryOrMarketplace$DigitalMusicAndroid3P_automotiveProdRelease(String territory, Marketplace marketplace, String defaultLanguage) {
        Locale alexaLocale = getAlexaLocale(territory, defaultLanguage);
        if (alexaLocale != null) {
            return alexaLocale;
        }
        Locale alexaLocale2 = getAlexaLocale(territory, null);
        if (alexaLocale2 != null) {
            return alexaLocale2;
        }
        if (marketplace == null) {
            Locale ALEXA_US_LOCALE = AlexaLocales.ALEXA_US_LOCALE;
            Intrinsics.checkNotNullExpressionValue(ALEXA_US_LOCALE, "ALEXA_US_LOCALE");
            return ALEXA_US_LOCALE;
        }
        Locale alexaLocale3 = getAlexaLocale(marketplace.getId(), defaultLanguage);
        if (alexaLocale3 != null) {
            return alexaLocale3;
        }
        Locale alexaLocale4 = getAlexaLocale(marketplace.getId(), null);
        if (alexaLocale4 != null) {
            return alexaLocale4;
        }
        Locale ALEXA_US_LOCALE2 = AlexaLocales.ALEXA_US_LOCALE;
        Intrinsics.checkNotNullExpressionValue(ALEXA_US_LOCALE2, "ALEXA_US_LOCALE");
        return ALEXA_US_LOCALE2;
    }
}
