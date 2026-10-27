package com.amplifyframework.predictions.models;

import androidx.core.os.EnvironmentCompat;

/* JADX INFO: loaded from: classes5.dex */
public enum LanguageType {
    AFRIKAANS("af"),
    ALBANIAN("sq"),
    AMHARIC("am"),
    ARABIC("ar"),
    ARMENIAN("hy"),
    ASSAMESE("as"),
    AZERBAIJANI("az"),
    BASHKIR("ba"),
    BASQUE("eu"),
    BELARUSIAN("be"),
    BENGALI("bn"),
    BOSNIAN("bs"),
    BULGARIAN("bg"),
    BURMESE("my"),
    CATALAN("ca"),
    CEBUANO("ceb"),
    CENTRAL_KHMER("km"),
    CHINESE_SIMPLIFIED("zh"),
    CHINESE_TRADITIONAL("zh-TW"),
    CHUVASH("cv"),
    CROATIAN("hr"),
    CZECH("cs"),
    DANISH("da"),
    DARI("fa-AF"),
    DUTCH("nl"),
    ENGLISH("en"),
    ESPERANTO("eo"),
    ESTONIAN("et"),
    FINNISH("fi"),
    FRENCH("fr"),
    FRENCH_CANADIAN("fr-CA"),
    GALICIAN("gl"),
    GEORGIAN("ka"),
    GERMAN("de"),
    GREEK("el"),
    GUJARATI("gu"),
    HAITIAN("ht"),
    HAUSA("ha"),
    HEBREW("he"),
    HINDI("hi"),
    HUNGARIAN("hu"),
    ICELANDIC("is"),
    ILOKO("ilo"),
    INDONESIAN("id"),
    IRISH("ga"),
    ITALIAN("it"),
    JAPANESE("ja"),
    JAVANESE("jv"),
    KANNADA("kn"),
    KAZAKH("kk"),
    KIRGHIZ("ky"),
    KOREAN("ko"),
    KURDISH("ku"),
    LATIN("la"),
    LATVIAN("lv"),
    LITHUANIAN("lt"),
    LUXEMBOURGISH("lb"),
    MACEDONIAN("mk"),
    MALAGASY("mg"),
    MALAY("ms"),
    MALAYALAM("ml"),
    MARATHI("mr"),
    MONGOLIAN("mn"),
    NEPALI("ne"),
    NEWARI("new"),
    NORWEGIAN("no"),
    ORIYA("or"),
    PASHTO("ps"),
    PERSIAN("fa"),
    POLISH("pl"),
    PORTUGUESE("pt"),
    PUNJABI("pa"),
    PUSHTO("ps"),
    QUECHUA("qu"),
    ROMANIAN("ro"),
    RUSSIAN("ru"),
    SANSKRIT("sa"),
    SCOTTISH_GAELIC("gd"),
    SERBIAN("sr"),
    SINDHI("sd"),
    SINHALA("si"),
    SLOVAK("sk"),
    SLOVENIAN("sl"),
    SOMALI("so"),
    SPANISH("es"),
    SUNDANESE("su"),
    SWAHILI("sw"),
    SWEDISH("sv"),
    TAGALOG("tl"),
    TAJIK("tg"),
    TAMIL("ta"),
    TATAR("tt"),
    TELUGU("te"),
    THAI("th"),
    TURKISH("tr"),
    TURKMEN("tk"),
    UIGHUR("ug"),
    UKRAINIAN("uk"),
    URDU("ur"),
    UZBEK("uz"),
    VIETNAMESE("vi"),
    WELSH("cy"),
    YIDDISH("yi"),
    YORUBA("yo"),
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);

    private final String languageCode;

    LanguageType(String str) {
        this.languageCode = str;
    }

    public static LanguageType from(String str) {
        int i;
        str.hashCode();
        switch (str) {
            case "bn":
                return BENGALI;
            case "en":
                return ENGLISH;
            case "es":
                return SPANISH;
            case "hi":
                return HINDI;
            case "ja":
                return JAPANESE;
            case "mr":
                return MARATHI;
            case "pa":
                return PUNJABI;
            case "pt":
                return PORTUGUESE;
            case "ru":
                return RUSSIAN;
            case "zh":
                return CHINESE_SIMPLIFIED;
            case "zh-TW":
                return CHINESE_TRADITIONAL;
            default:
                for (LanguageType languageType : values()) {
                    if (languageType.getLanguageCode().equals(str)) {
                        return languageType;
                    }
                }
                return UNKNOWN;
        }
    }

    public String getLanguageCode() {
        return this.languageCode;
    }
}
