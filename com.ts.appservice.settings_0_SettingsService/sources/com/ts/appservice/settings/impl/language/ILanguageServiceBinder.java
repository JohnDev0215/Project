package com.ts.appservice.settings.impl.language;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.LocaleList;
import android.os.Message;
import android.os.RemoteException;
import com.android.common.speech.LoggingEvents;
import com.android.internal.app.LocalePicker;
import com.ts.appservice.settings.impl.caradapter.CarAdapterServiceManager;
import com.ts.appservice.settings.impl.qnx.SettingsQnxService;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.setting.SettingAdapterManager;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.language.ILanguageSettingsCallback;
import com.ts.lib.settings.language.ILanguageSettingsService;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class ILanguageServiceBinder extends ILanguageSettingsService.Stub {
    private static final int ARM_ENGLISH_AUSTRALIA_LANGUAGE_VALUE = 33;
    private static final int ARM_SCREEN_ARABIC_LANGUAGE_VALUE = 14;
    private static final int ARM_SCREEN_BULAGRIAN_LANGUAGE_VALUE = 40;
    private static final int ARM_SCREEN_CHINESE_LANGUAGE_VALUE = 1;
    private static final int ARM_SCREEN_COMPLEX_CHINESE_LANGUAGE_VALUE = 2;
    private static final int ARM_SCREEN_CROATIAN_LANGUAGE_VALUE = 35;
    private static final int ARM_SCREEN_CZECH_LANGUAGE_VALUE = 27;
    private static final int ARM_SCREEN_DSNISH_LANGUAGE_VALUE = 20;
    private static final int ARM_SCREEN_DUTCH_LANGUAGE_VALUE = 15;
    private static final int ARM_SCREEN_ENGLISH_LANGUAGE_VALUE = 3;
    private static final int ARM_SCREEN_FINNISH_LANGUAGE_VALUE = 19;
    private static final int ARM_SCREEN_FRENCH_CANADA_LANGUAGE_VALUE = 32;
    private static final int ARM_SCREEN_FRENCH_LANGUAGE_VALUE = 10;
    private static final int ARM_SCREEN_GCATALAN_LANGUAGE_VALUE = 34;
    private static final int ARM_SCREEN_GERMAN_LANGUAGE_VALUE = 11;
    private static final int ARM_SCREEN_GREEK_LANGUAGE_VALUE = 37;
    private static final int ARM_SCREEN_HINDI_LANGUAGE_VALUE = 13;
    private static final int ARM_SCREEN_HUNGARIAN_LANGUAGE_VALUE = 25;
    private static final int ARM_SCREEN_INDONESIAN_LANGUAGE_VALUE = 30;
    private static final int ARM_SCREEN_ITALIAN_LANGUAGE_VALUE = 12;
    private static final int ARM_SCREEN_JAPANESE_LANGUAGE_VALUE = 8;
    private static final int ARM_SCREEN_KAZAKH_LANGUAGE_VALUE = 39;
    private static final int ARM_SCREEN_KOREAN_LANGUAGE_VALUE = 9;
    private static final int ARM_SCREEN_MALAY_LANGUAGE_VALUE = 31;
    private static final int ARM_SCREEN_NORWEGIAN_LANGUAGE_VALUE = 18;
    private static final int ARM_SCREEN_POLISH_LANGUAGE_VALUE = 28;
    private static final int ARM_SCREEN_PORTUGUESE_BRAZIL_LANGUAGE_VALUE = 7;
    private static final int ARM_SCREEN_PORTUGUESE_LANGUAGE_VALUE = 6;
    private static final int ARM_SCREEN_ROMANIAN_LANGUAGE_VALUE = 36;
    private static final int ARM_SCREEN_SLOVAK_LANGUAGE_VALUE = 26;
    private static final int ARM_SCREEN_SPANISH_LANGUAGE_VALUE = 5;
    private static final int ARM_SCREEN_SPANISH_SPAIN_LANGUAGE_VALUE = 4;
    private static final int ARM_SCREEN_SWEDISH_LANGUAGE_VALUE = 17;
    private static final int ARM_SCREEN_THAI_LANGUAGE_VALUE = 16;
    private static final int ARM_SCREEN_TURKISH_LANGUAGE_VALUE = 24;
    private static final int ARM_SCREEN_UKRAINIAN_LANGUAGE_VALUE = 38;
    private static final int ARM_SCREEN_VIETNAMESES_LANGUAGE_VALUE = 29;
    private static final String ENGLISH = "en_US";
    private static final String ENGLISH_AUSTRALIA = "en_AU";
    private static final String FRENCH_CANADA = "fr_CA";
    private static final String JAPANESE_ALL = "ja";
    private static final String KOREAN_ALL = "ko";
    private static final String MALAY = "ms_MY";
    private static final int MESSAGE_SERVICE_CAR_ADAPTER = 6;
    private static final String PORTUGUESE_BRAZIL = "pt_BR";
    private static final String PORTUGUESE_PORTUGAL = "pt_PT";
    private static final int QNX_ARABIC_LANGUAGE_VALUE = 17;
    private static final int QNX_BULAGRIAN_LANGUAGE_VALUE = 32;
    private static final int QNX_CHINESE_LANGUAGE_VALUE = 0;
    private static final int QNX_COMPLEX_CHINESE_LANGUAGE_VALUE = 22;
    private static final int QNX_CROATIAN_LANGUAGE_VALUE = 31;
    private static final int QNX_CZECH_LANGUAGE_VALUE = 21;
    private static final int QNX_DSNISH_LANGUAGE_VALUE = 12;
    private static final int QNX_DUTCH_LANGUAGE_VALUE = 11;
    private static final int QNX_ENGLISH_AUSTRALIA_LANGUAGE_VALUE = 34;
    private static final int QNX_ENGLISH_LANGUAGE_VALUE = 1;
    private static final int QNX_FINNISH_LANGUAGE_VALUE = 14;
    private static final int QNX_FRENCH_CANADA_LANGUAGE_VALUE = 39;
    private static final int QNX_FRENCH_LANGUAGE_VALUE = 8;
    private static final int QNX_GCATALAN_LANGUAGE_VALUE = 36;
    private static final int QNX_GERMAN_LANGUAGE_VALUE = 2;
    private static final int QNX_GREEK_LANGUAGE_VALUE = 16;
    private static final int QNX_HEBREW_LANGUAGE_VALUE = 4;
    private static final int QNX_HINDI_LANGUAGE_VALUE = 27;
    private static final int QNX_HUNGARIAN_LANGUAGE_VALUE = 18;
    private static final int QNX_INDONESIAN_LANGUAGE_VALUE = 25;
    private static final int QNX_ITALIAN_LANGUAGE_VALUE = 13;
    private static final int QNX_JAPANESE_LANGUAGE_VALUE = 20;
    private static final int QNX_KAZAKH_LANGUAGE_VALUE = 38;
    private static final int QNX_KOREAN_LANGUAGE_VALUE = 35;
    private static final int QNX_MALAY_LANGUAGE_VALUE = 24;
    private static final int QNX_NORWEGIAN_LANGUAGE_VALUE = 3;
    private static final int QNX_POLISH_LANGUAGE_VALUE = 28;
    private static final int QNX_PORTUGUESE_BRAZIL_LANGUAGE_VALUE = 6;
    private static final int QNX_PORTUGUESE_LANGUAGE_VALUE = 9;
    private static final int QNX_ROMANIAN_LANGUAGE_VALUE = 33;
    private static final int QNX_RUSSIAN_LANGUAGE_VALUE = 30;
    private static final int QNX_SLOVAK_LANGUAGE_VALUE = 19;
    private static final int QNX_SPANISH_LANGUAGE_VALUE = 5;
    private static final int QNX_SPANISH_SPAIN_LANGUAGE_VALUE = 7;
    private static final int QNX_SWEDISH_LANGUAGE_VALUE = 10;
    private static final int QNX_THAI_LANGUAGE_VALUE = 23;
    private static final int QNX_TURKISH_LANGUAGE_VALUE = 15;
    private static final int QNX_UKRAINIAN_LANGUAGE_VALUE = 37;
    private static final int QNX_UZBEK_LANGUAGE_VALUE = 26;
    private static final int QNX_VIETNAMESES_LANGUAGE_VALUE = 29;
    private static final int RETRY_INTERVAL = 500;
    public static final int SETTING_COMMAND_BUSY = -2147482647;
    public static final int SETTING_COMMAND_FAILED = -2147482648;
    public static final int SETTING_COMMAND_INVALID = -2147482645;
    public static final int SETTING_COMMAND_SUCCESS = 0;
    public static final int SETTING_COMMAND_TIMEOUT = -2147482646;
    private static final int SET_LANGUAGE_ARABIC = 25;
    private static final int SET_LANGUAGE_ARABIC_LSRAEL = 5;
    private static final int SET_LANGUAGE_BULAGRIAN = 39;
    private static final int SET_LANGUAGE_CATALAN = 43;
    private static final int SET_LANGUAGE_COMPLEX_CHINESE = 2;
    private static final int SET_LANGUAGE_CROATIAN = 38;
    private static final int SET_LANGUAGE_CZECH = 29;
    private static final int SET_LANGUAGE_DSNISH = 20;
    private static final int SET_LANGUAGE_DUTCH = 19;
    private static final int SET_LANGUAGE_ENGLISH = 3;
    private static final int SET_LANGUAGE_ENGLISH_AUSTRALIA = 41;
    private static final int SET_LANGUAGE_FINNISH = 22;
    private static final int SET_LANGUAGE_FRENCH = 17;
    private static final int SET_LANGUAGE_FRENCH_CANADA = 46;
    private static final int SET_LANGUAGE_FRENCH_FRANCE = 6;
    private static final int SET_LANGUAGE_GERMANY = 12;
    private static final int SET_LANGUAGE_GREEK = 24;
    private static final int SET_LANGUAGE_HEBREW = 14;
    private static final int SET_LANGUAGE_HINDI = 34;
    private static final int SET_LANGUAGE_HUNGARIAN = 26;
    private static final int SET_LANGUAGE_INDONESIAN = 32;
    private static final int SET_LANGUAGE_ITALIAN = 21;
    private static final int SET_LANGUAGE_JAPANESE = 28;
    private static final int SET_LANGUAGE_JAPANESE_JAPAN = 9;
    private static final int SET_LANGUAGE_KAZAKH = 45;
    private static final int SET_LANGUAGE_KOREAN = 42;
    private static final int SET_LANGUAGE_KOREAN_SOUTH_KOREA = 7;
    private static final int SET_LANGUAGE_MALAY = 31;
    private static final int SET_LANGUAGE_NORWEGIAN = 13;
    private static final int SET_LANGUAGE_POLISH = 35;
    private static final int SET_LANGUAGE_PORTUGUESE = 8;
    private static final int SET_LANGUAGE_PORTUGUESE_BRAZIL = 15;
    private static final int SET_LANGUAGE_ROMANIAN = 40;
    private static final int SET_LANGUAGE_RUSSIAN = 4;
    private static final int SET_LANGUAGE_SIMPLE_CHINESE = 1;
    private static final int SET_LANGUAGE_SLOVAK = 27;
    private static final int SET_LANGUAGE_SPANISH = 47;
    private static final int SET_LANGUAGE_SPANISH_LATIN_AMERICA = 11;
    private static final int SET_LANGUAGE_SPANISH_SPAIN = 16;
    private static final int SET_LANGUAGE_SWEDISH = 18;
    private static final int SET_LANGUAGE_THAI = 30;
    private static final int SET_LANGUAGE_THAI_THAILAND = 10;
    private static final int SET_LANGUAGE_TURKISH = 23;
    private static final int SET_LANGUAGE_UKRAINIAN = 44;
    private static final int SET_LANGUAGE_UZBEK = 33;
    private static final int SET_LANGUAGE_VIETNAMESES = 36;
    private static final String SIMPLIFIED_CHINESE = "zh_CN";
    private static final String SPANISH_LATIN_AMERICA = "es_LA";
    private static final String SPANISH_SPAIN = "es_ES";
    private static final String TRADITIONAL_CHINESE = "zh_HK";
    private static final String UZBEK = "uz_UZ";
    private boolean mCarAdapterBinderState;
    private CarAdapterManager mCarAdapterManager;
    private CarAdapterServiceManager mCarAdapterServiceManager;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private LanguageCallback mLanguageCallback;
    private SettingAdapterManager mSettingAdapterManager;
    private SettingsQnxService mSettingsQnxService;
    private String mStrLanguage;
    private static final String TAG = ILanguageServiceBinder.class.getSimpleName();
    public static final Locale LOCALE_TRADITIONAL_CHINESE_HK = new Locale("zh", "HK", LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String RUSSIAN = "ru";
    public static final Locale LOCALE_RUSSIA = new Locale(RUSSIAN, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    public static final Locale LOCALE_PORTUGUESE = new Locale("pt", "PT", LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String SPANISH_ALL = "es";
    public static final Locale LOCALE_SPANISH = new Locale(SPANISH_ALL, "LA", LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String GERMAN_ALL = "de";
    public static final Locale LOCALE_GERMANY = new Locale(GERMAN_ALL, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String NORWEGIAN = "nb";
    public static final Locale LOCALE_NORWEGIAN = new Locale(NORWEGIAN, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String HEBREW = "iw";
    public static final Locale LOCALE_HEBREW = new Locale(HEBREW, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    public static final Locale LOCALE_PORTUGUESE_BRAZIL = new Locale("pt", "BR", LoggingEvents.EXTRA_CALLING_APP_NAME);
    public static final Locale LOCALE_SPANISH_SPAIN = new Locale(SPANISH_ALL, "ES", LoggingEvents.EXTRA_CALLING_APP_NAME);
    public static final Locale LOCALE_SPANISH_ALL = new Locale(SPANISH_ALL, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String SWEDISH = "sv";
    public static final Locale LOCALE_SWEDISH = new Locale(SWEDISH, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String DUTCH = "nl";
    public static final Locale LOCALE_DUTCH = new Locale(DUTCH, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String DSNISH = "da";
    public static final Locale LOCALE_DSNISH = new Locale(DSNISH, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String ITALIAN = "it";
    public static final Locale LOCALE_ITALIAN = new Locale(ITALIAN, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String FINNISH = "fi";
    public static final Locale LOCALE_FINNISH = new Locale(FINNISH, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String TURKISH = "tr";
    public static final Locale LOCALE_TURKISH = new Locale(TURKISH, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String GREEK = "el";
    public static final Locale LOCALE_GREEK = new Locale(GREEK, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String ARABIC = "ar";
    public static final Locale LOCALE_ARABIC = new Locale(ARABIC, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String HUNGARIAN = "hu";
    public static final Locale LOCALE_HUNGARIAN = new Locale(HUNGARIAN, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String SLOVAK = "sk";
    public static final Locale LOCALE_SLOVAK = new Locale(SLOVAK, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String CZECH = "cs";
    public static final Locale LOCALE_CZECH = new Locale(CZECH, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String THAI_ALL = "th";
    public static final Locale LOCALE_THAI = new Locale(THAI_ALL, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    public static final Locale LOCALE_MALAY = new Locale("ms", "MY", LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String INDONESIAN = "in";
    public static final Locale LOCALE_INDONESIAN = new Locale(INDONESIAN, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    public static final Locale LOCALE_UZBEK = new Locale("uz", "UZ", LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String HINDI = "hi";
    public static final Locale LOCALE_HINDI = new Locale(HINDI, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String POLISH = "pl";
    public static final Locale LOCALE_POLISH = new Locale(POLISH, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String VIETNAMESES = "vi";
    public static final Locale LOCALE_VIETNAMESES = new Locale(VIETNAMESES, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String CROATIAN = "hr";
    public static final Locale LOCALE_CROATIAN = new Locale(CROATIAN, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String BULAGRIAN = "bg";
    public static final Locale LOCALE_BULAGRIAN = new Locale(BULAGRIAN, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String ROMANIAN = "ro";
    public static final Locale LOCALE_ROMANIAN = new Locale(ROMANIAN, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    public static final Locale LOCALE_ENGLISH_AUSTRALIA = new Locale("en", "AU", LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String CATALAN = "ca";
    public static final Locale LOCALE_CATALAN = new Locale(CATALAN, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String UKRAINIAN = "uk";
    public static final Locale LOCALE_UKRAINIAN = new Locale(UKRAINIAN, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String KAZAKH = "kk";
    public static final Locale LOCALE_KAZAKH = new Locale(KAZAKH, LoggingEvents.EXTRA_CALLING_APP_NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
    private static final String FRENCH = "fr";
    public static final Locale LOCALE_FRENCH_CANADA = new Locale(FRENCH, "CA", LoggingEvents.EXTRA_CALLING_APP_NAME);
    public static final HashMap<Integer, Locale> MAP_SET = new HashMap<>();
    public static final HashMap<String, Integer> MAP_GET = new HashMap<>();
    public static final HashMap<Integer, Integer> MAP_QNX_LANGUAGE = new HashMap<>();
    public static final HashMap<Integer, Integer> MAP_ARM_SCREEN_LANGUAGE = new HashMap<>();

    static {
        MAP_GET.put(SIMPLIFIED_CHINESE, 1);
        MAP_GET.put(TRADITIONAL_CHINESE, 2);
        MAP_GET.put(ENGLISH, 3);
        MAP_GET.put(RUSSIAN, 4);
        MAP_GET.put(PORTUGUESE_PORTUGAL, 8);
        MAP_GET.put(SPANISH_LATIN_AMERICA, 11);
        MAP_GET.put(GERMAN_ALL, 12);
        MAP_GET.put(NORWEGIAN, 13);
        MAP_GET.put(HEBREW, 14);
        MAP_GET.put(PORTUGUESE_BRAZIL, 15);
        MAP_GET.put(SPANISH_SPAIN, 16);
        MAP_GET.put(FRENCH, 17);
        MAP_GET.put(SWEDISH, 18);
        MAP_GET.put(DUTCH, 19);
        MAP_GET.put(DSNISH, 20);
        MAP_GET.put(ITALIAN, 21);
        MAP_GET.put(FINNISH, 22);
        MAP_GET.put(TURKISH, 23);
        MAP_GET.put(GREEK, 24);
        MAP_GET.put(ARABIC, 25);
        MAP_GET.put(HUNGARIAN, 26);
        MAP_GET.put(SLOVAK, 27);
        MAP_GET.put(JAPANESE_ALL, 28);
        MAP_GET.put(CZECH, 29);
        MAP_GET.put(THAI_ALL, 30);
        MAP_GET.put(MALAY, 31);
        MAP_GET.put(INDONESIAN, 32);
        MAP_GET.put(UZBEK, 33);
        MAP_GET.put(HINDI, 34);
        MAP_GET.put(POLISH, 35);
        MAP_GET.put(VIETNAMESES, 36);
        MAP_GET.put(CROATIAN, 38);
        MAP_GET.put(BULAGRIAN, 39);
        MAP_GET.put(ROMANIAN, 40);
        MAP_GET.put(ENGLISH_AUSTRALIA, 41);
        MAP_GET.put(KOREAN_ALL, 42);
        MAP_GET.put(CATALAN, 43);
        MAP_GET.put(UKRAINIAN, 44);
        MAP_GET.put(KAZAKH, 45);
        MAP_GET.put(FRENCH_CANADA, 46);
        MAP_GET.put(SPANISH_ALL, 16);
        MAP_SET.put(1, Locale.SIMPLIFIED_CHINESE);
        MAP_SET.put(2, LOCALE_TRADITIONAL_CHINESE_HK);
        MAP_SET.put(3, Locale.US);
        MAP_SET.put(4, LOCALE_RUSSIA);
        MAP_SET.put(8, LOCALE_PORTUGUESE);
        MAP_SET.put(11, LOCALE_SPANISH);
        MAP_SET.put(12, LOCALE_GERMANY);
        MAP_SET.put(13, LOCALE_NORWEGIAN);
        MAP_SET.put(14, LOCALE_HEBREW);
        MAP_SET.put(15, LOCALE_PORTUGUESE_BRAZIL);
        MAP_SET.put(16, LOCALE_SPANISH_ALL);
        MAP_SET.put(17, Locale.FRENCH);
        MAP_SET.put(18, LOCALE_SWEDISH);
        MAP_SET.put(19, LOCALE_DUTCH);
        MAP_SET.put(20, LOCALE_DSNISH);
        MAP_SET.put(21, LOCALE_ITALIAN);
        MAP_SET.put(22, LOCALE_FINNISH);
        MAP_SET.put(23, LOCALE_TURKISH);
        MAP_SET.put(24, LOCALE_GREEK);
        MAP_SET.put(25, LOCALE_ARABIC);
        MAP_SET.put(26, LOCALE_HUNGARIAN);
        MAP_SET.put(27, LOCALE_SLOVAK);
        MAP_SET.put(28, Locale.JAPANESE);
        MAP_SET.put(29, LOCALE_CZECH);
        MAP_SET.put(30, LOCALE_THAI);
        MAP_SET.put(31, LOCALE_MALAY);
        MAP_SET.put(32, LOCALE_INDONESIAN);
        MAP_SET.put(33, LOCALE_UZBEK);
        MAP_SET.put(34, LOCALE_HINDI);
        MAP_SET.put(35, LOCALE_POLISH);
        MAP_SET.put(36, LOCALE_VIETNAMESES);
        MAP_SET.put(38, LOCALE_CROATIAN);
        MAP_SET.put(39, LOCALE_BULAGRIAN);
        MAP_SET.put(40, LOCALE_ROMANIAN);
        MAP_SET.put(41, LOCALE_ENGLISH_AUSTRALIA);
        MAP_SET.put(42, Locale.KOREAN);
        MAP_SET.put(43, LOCALE_CATALAN);
        MAP_SET.put(44, LOCALE_UKRAINIAN);
        MAP_SET.put(45, LOCALE_KAZAKH);
        MAP_SET.put(46, LOCALE_FRENCH_CANADA);
        MAP_SET.put(47, LOCALE_SPANISH_ALL);
        MAP_QNX_LANGUAGE.put(1, 0);
        MAP_QNX_LANGUAGE.put(3, 1);
        MAP_QNX_LANGUAGE.put(12, 2);
        MAP_QNX_LANGUAGE.put(13, 3);
        MAP_QNX_LANGUAGE.put(14, 4);
        MAP_QNX_LANGUAGE.put(11, 5);
        MAP_QNX_LANGUAGE.put(15, 6);
        MAP_QNX_LANGUAGE.put(16, 7);
        MAP_QNX_LANGUAGE.put(17, 8);
        MAP_QNX_LANGUAGE.put(8, 9);
        MAP_QNX_LANGUAGE.put(18, 10);
        MAP_QNX_LANGUAGE.put(19, 11);
        MAP_QNX_LANGUAGE.put(20, 12);
        MAP_QNX_LANGUAGE.put(21, 13);
        MAP_QNX_LANGUAGE.put(22, 14);
        MAP_QNX_LANGUAGE.put(23, 15);
        MAP_QNX_LANGUAGE.put(24, 16);
        MAP_QNX_LANGUAGE.put(25, 17);
        MAP_QNX_LANGUAGE.put(26, 18);
        MAP_QNX_LANGUAGE.put(27, 19);
        MAP_QNX_LANGUAGE.put(28, 20);
        MAP_QNX_LANGUAGE.put(29, 21);
        MAP_QNX_LANGUAGE.put(2, 22);
        MAP_QNX_LANGUAGE.put(30, 23);
        MAP_QNX_LANGUAGE.put(31, 24);
        MAP_QNX_LANGUAGE.put(32, 25);
        MAP_QNX_LANGUAGE.put(33, 26);
        MAP_QNX_LANGUAGE.put(34, 27);
        MAP_QNX_LANGUAGE.put(35, 28);
        MAP_QNX_LANGUAGE.put(36, 29);
        MAP_QNX_LANGUAGE.put(4, 30);
        MAP_QNX_LANGUAGE.put(38, 31);
        MAP_QNX_LANGUAGE.put(39, 32);
        MAP_QNX_LANGUAGE.put(40, 33);
        MAP_QNX_LANGUAGE.put(41, 34);
        MAP_QNX_LANGUAGE.put(42, 35);
        MAP_QNX_LANGUAGE.put(43, 36);
        MAP_QNX_LANGUAGE.put(44, 37);
        MAP_QNX_LANGUAGE.put(45, 38);
        MAP_QNX_LANGUAGE.put(46, 39);
        MAP_QNX_LANGUAGE.put(47, 7);
        MAP_ARM_SCREEN_LANGUAGE.put(1, 1);
        MAP_ARM_SCREEN_LANGUAGE.put(2, 2);
        MAP_ARM_SCREEN_LANGUAGE.put(3, 3);
        MAP_ARM_SCREEN_LANGUAGE.put(16, 4);
        MAP_ARM_SCREEN_LANGUAGE.put(11, 5);
        MAP_ARM_SCREEN_LANGUAGE.put(8, 6);
        MAP_ARM_SCREEN_LANGUAGE.put(15, 7);
        MAP_ARM_SCREEN_LANGUAGE.put(28, 8);
        MAP_ARM_SCREEN_LANGUAGE.put(42, 9);
        MAP_ARM_SCREEN_LANGUAGE.put(17, 10);
        MAP_ARM_SCREEN_LANGUAGE.put(12, 11);
        MAP_ARM_SCREEN_LANGUAGE.put(21, 12);
        MAP_ARM_SCREEN_LANGUAGE.put(34, 13);
        MAP_ARM_SCREEN_LANGUAGE.put(25, 14);
        MAP_ARM_SCREEN_LANGUAGE.put(19, 15);
        MAP_ARM_SCREEN_LANGUAGE.put(30, 16);
        MAP_ARM_SCREEN_LANGUAGE.put(18, 17);
        MAP_ARM_SCREEN_LANGUAGE.put(13, 18);
        MAP_ARM_SCREEN_LANGUAGE.put(22, 19);
        MAP_ARM_SCREEN_LANGUAGE.put(20, 20);
        MAP_ARM_SCREEN_LANGUAGE.put(23, 24);
        MAP_ARM_SCREEN_LANGUAGE.put(26, 25);
        MAP_ARM_SCREEN_LANGUAGE.put(27, 26);
        MAP_ARM_SCREEN_LANGUAGE.put(29, 27);
        MAP_ARM_SCREEN_LANGUAGE.put(35, 28);
        MAP_ARM_SCREEN_LANGUAGE.put(36, 29);
        MAP_ARM_SCREEN_LANGUAGE.put(32, 30);
        MAP_ARM_SCREEN_LANGUAGE.put(31, 31);
        MAP_ARM_SCREEN_LANGUAGE.put(46, 32);
        MAP_ARM_SCREEN_LANGUAGE.put(41, 33);
        MAP_ARM_SCREEN_LANGUAGE.put(43, 34);
        MAP_ARM_SCREEN_LANGUAGE.put(38, 35);
        MAP_ARM_SCREEN_LANGUAGE.put(40, 36);
        MAP_ARM_SCREEN_LANGUAGE.put(24, 37);
        MAP_ARM_SCREEN_LANGUAGE.put(44, 38);
        MAP_ARM_SCREEN_LANGUAGE.put(45, 39);
        MAP_ARM_SCREEN_LANGUAGE.put(39, 40);
    }

    public ILanguageServiceBinder(Context context) {
        this.mContext = context;
        this.mLanguageCallback = new LanguageCallback(this.mContext);
        this.mSettingsQnxService = SettingsQnxService.getInstance(this.mContext);
        HandlerThread handlerThread = new HandlerThread("LanguageService");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.ts.appservice.settings.impl.language.ILanguageServiceBinder.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                ILanguageServiceBinder.this.dequeueMessage(msg);
            }
        };
        this.mHandler = handler;
        handler.sendEmptyMessage(6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dequeueMessage(Message msg) {
        if (msg.what == 6) {
            if (this.mHandler.hasMessages(6)) {
                this.mHandler.removeMessages(6);
            }
            CarAdapterServiceManager carAdapterServiceManager = CarAdapterServiceManager.getInstance(this.mContext);
            this.mCarAdapterServiceManager = carAdapterServiceManager;
            this.mCarAdapterBinderState = carAdapterServiceManager.getCarAdapterBinderState();
            LogUtil.debug(TAG, "mCarAdapterBinderState is " + this.mCarAdapterBinderState);
            if (this.mCarAdapterBinderState) {
                CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(this.mContext);
                this.mCarAdapterManager = carAdapterManager;
                this.mSettingAdapterManager = (SettingAdapterManager) carAdapterManager.getCarAdapterManager("setting");
                LogUtil.info(TAG, "setCurrentLanguageSync current language in get SettingAdapterManager");
                setCurrentLanguageSync();
                return;
            }
            this.mHandler.sendEmptyMessageDelayed(6, 500L);
            return;
        }
        LogUtil.debug(TAG, "unrecognized handler what:" + msg.what);
    }

    public int setLanguage(int value) {
        Locale locale = MAP_SET.get(Integer.valueOf(value));
        if (locale != null && !LoggingEvents.EXTRA_CALLING_APP_NAME.equals(locale.toString())) {
            LogUtil.warning(TAG, "setLanguage success");
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            LocalePicker.updateLocales(localeList);
            return 0;
        }
        LogUtil.warning(TAG, "setLanguage invalid");
        return SETTING_COMMAND_INVALID;
    }

    public int getLanguage() {
        LogUtil.debug(TAG, "getLanguage");
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return 1;
        }
        String strLanguage = locale.toString();
        LogUtil.info(TAG, "getLanguage strLanguage: " + strLanguage);
        if (LoggingEvents.EXTRA_CALLING_APP_NAME.equals(strLanguage)) {
            LogUtil.error(TAG, "getLanguage: language err");
            return 1;
        }
        if (MAP_GET.get(strLanguage) == null) {
            return 1;
        }
        int language = MAP_GET.get(strLanguage).intValue();
        LogUtil.info(TAG, "getLanguage language value is " + language);
        return language;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        LogUtil.debug(TAG, "onConfigurationChanged");
        String strLanguage = newConfig.locale.toString();
        if (strLanguage.equals(this.mStrLanguage)) {
            LogUtil.error(TAG, "onConfigurationChanged: language is same to last language");
            return;
        }
        int language = 1;
        LogUtil.warning(TAG, "onConfigurationChanged language is " + strLanguage);
        if (LoggingEvents.EXTRA_CALLING_APP_NAME.equals(strLanguage) && MAP_GET.get(strLanguage) == null) {
            LogUtil.error(TAG, "onConfigurationChanged: language is null");
            return;
        }
        if (MAP_GET.get(strLanguage) != null) {
            language = MAP_GET.get(strLanguage).intValue();
            setLanguageToQnx(language);
            setLanguageToArmScreen(language);
        }
        this.mStrLanguage = strLanguage;
        this.mLanguageCallback.onLanguageChanged(language);
    }

    private void setLanguageToQnx(int language) {
        LogUtil.debug(TAG, "setLanguageToQnx language : " + language);
        if (MAP_QNX_LANGUAGE.get(Integer.valueOf(language)) != null) {
            this.mSettingsQnxService.setLanguage(MAP_QNX_LANGUAGE.get(Integer.valueOf(language)).intValue());
        }
    }

    private void setCurrentLanguageSync() {
        int language = getLanguage();
        setLanguageToArmScreen(language);
    }

    private void setLanguageToArmScreen(int language) {
        LogUtil.info(TAG, "setLanguageToArmScreen language : " + language);
        if (MAP_ARM_SCREEN_LANGUAGE.get(Integer.valueOf(language)) != null) {
            SettingAdapterManager settingAdapterManager = this.mSettingAdapterManager;
            if (settingAdapterManager != null) {
                settingAdapterManager.setInternationalLanguage(MAP_ARM_SCREEN_LANGUAGE.get(Integer.valueOf(language)).intValue());
                LogUtil.info(TAG, "setInternationalLanguage language end : " + MAP_ARM_SCREEN_LANGUAGE.get(Integer.valueOf(language)));
                return;
            }
            LogUtil.info(TAG, "setLanguageToArmScreen language SettingAdapterManager is null ");
        }
    }

    public void subscribeLanguageCallback(ILanguageSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mLanguageCallback.register(callback);
        }
    }

    public void unsubscribeLanguageCallback(ILanguageSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mLanguageCallback.unregister(callback);
        }
    }
}
