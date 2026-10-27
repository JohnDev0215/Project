package com.byd.vrsettings.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import com.byd.vrsettings.R;
import com.chad.library.BuildConfig;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class LanguageUtil {
    public static String topLanguageConvert(Context context, String str) {
        if (str == null) {
            return "Unknow";
        }
        if (str.contains("eng")) {
            return context.getResources().getString(R.string.text_language_english_america);
        }
        if (str.contains("nor")) {
            return context.getResources().getString(R.string.text_language_norwegian);
        }
        if (str.contains("spa") && str.contains("XLA")) {
            return context.getResources().getString(R.string.text_language_spanish_xla);
        }
        if (str.contains("spa") && str.contains("ESP")) {
            return context.getResources().getString(R.string.text_language_spanish_esp);
        }
        if (str.contains("tha")) {
            return context.getResources().getString(R.string.text_language_thai);
        }
        if (str.contains("jap") || str.contains("jan") || str.contains("jpn")) {
            return context.getResources().getString(R.string.text_language_japanese);
        }
        if (str.contains("fra")) {
            return context.getResources().getString(R.string.text_language_french);
        }
        if (str.contains("deu")) {
            return context.getResources().getString(R.string.text_language_german);
        }
        if (str.contains("por") && str.contains("BRA")) {
            return context.getResources().getString(R.string.text_language_portuguese_brazil);
        }
        if (str.contains("por") && str.contains("PRT")) {
            return context.getResources().getString(R.string.text_language_portuguese_prt);
        }
        if (str.contains("ita")) {
            return context.getResources().getString(R.string.text_language_italian);
        }
        if (str.contains("cmn")) {
            return context.getResources().getString(R.string.text_language_chinese);
        }
        if (str.contains("yue")) {
            return context.getResources().getString(R.string.text_language_cantonese);
        }
        if (str.contains("dan")) {
            return context.getResources().getString(R.string.text_language_dan);
        }
        if (str.contains("fin")) {
            return context.getResources().getString(R.string.text_language_fin);
        }
        if (str.contains("heb")) {
            return context.getResources().getString(R.string.text_language_heb);
        }
        if (str.contains("kor")) {
            return context.getResources().getString(R.string.text_language_kor);
        }
        if (str.contains("nld")) {
            return context.getResources().getString(R.string.text_language_nld);
        }
        if (str.contains("pol")) {
            return context.getResources().getString(R.string.text_language_pol);
        }
        if (str.contains("swe")) {
            return context.getResources().getString(R.string.text_language_swe);
        }
        if (str.contains("tur")) {
            return context.getResources().getString(R.string.text_language_tur);
        }
        return str.contains("hun") ? context.getResources().getString(R.string.text_language_hun) : "Unknow";
    }

    public static String sencondLanguageConvert(Context context, String str) {
        return str == null ? "Unknow" : str.contains("USA") ? "English(America)" : str.contains("NOR") ? "Norsk" : (str.contains("spa") && str.contains("XLA")) ? "Español(México)" : (str.contains("spa") && str.contains("ESP")) ? "Español" : str.contains("tha") ? "ไทย" : (str.contains("jap") || str.contains("jan") || str.contains("jpn")) ? "日本語" : str.contains("fra") ? "Français" : str.contains("deu") ? "Deutsch" : (str.contains("por") && str.contains("BRA")) ? "Português(Brasil)" : (str.contains("por") && str.contains("PRT")) ? "Português" : str.contains("ita") ? "Italiano" : str.contains("cmn") ? "中文（普通话）" : str.contains("yue") ? "中文（粤语）" : str.contains("dan") ? "Dansk" : str.contains("fin") ? "Suomi" : str.contains("heb") ? "עברית" : str.contains("kor") ? "한국어" : str.contains("nld") ? "Nederlands" : str.contains("pol") ? "Polski" : str.contains("swe") ? "Svenska" : str.contains("tur") ? "Türkçe" : str.contains("hun") ? "Magyar" : "Unknow";
    }

    public static String convertAll(Context context, String str) {
        return topLanguageConvert(context, str);
    }

    public static String convertLocalName(String str) {
        return (TextUtils.isEmpty(str) || !str.contains("data_")) ? str : str.replace("data_", BuildConfig.FLAVOR);
    }

    public static String getLanguage(Context context, String str) {
        Locale locale = new Locale("en");
        if (TextUtils.isEmpty(str) || str.contains("eng")) {
            locale = new Locale("en");
        } else if (str.contains("por") && str.contains("BRA")) {
            locale = new Locale("pt", "br");
        } else if (str.contains("por") && str.contains("PRT")) {
            locale = new Locale("pt", "pt");
        } else if (str.contains("spa") && str.contains("XLA")) {
            locale = new Locale("es", "mx");
        } else if (str.contains("spa") && str.contains("ESP")) {
            locale = new Locale("es", "es");
        } else if (str.contains("nor")) {
            locale = new Locale("nb");
        } else if (str.contains("tha")) {
            locale = new Locale("th");
        } else if (str.contains("jap") || str.contains("jan") || str.contains("jpn")) {
            locale = new Locale("ja");
        } else if (str.contains("fra")) {
            locale = new Locale("fr");
        } else if (str.contains("deu")) {
            locale = new Locale("de");
        } else if (str.contains("ita")) {
            locale = new Locale("it");
        } else if (str.contains("cmn")) {
            locale = new Locale("zh", "cm");
        } else if (str.contains("yue")) {
            locale = new Locale("zh", "hk");
        } else if (str.contains("kor")) {
            locale = new Locale("ko");
        } else if (str.contains("tur")) {
            locale = new Locale("tr");
        } else if (str.contains("dan")) {
            locale = new Locale("da");
        } else if (str.contains("fin")) {
            locale = new Locale("fi");
        } else if (str.contains("heb")) {
            locale = new Locale("iw");
        } else if (str.contains("nld")) {
            locale = new Locale("nl");
        } else if (str.contains("pol")) {
            locale = new Locale("pl");
        } else if (str.contains("swe")) {
            locale = new Locale("sv");
        } else if (str.contains("hun")) {
            locale = new Locale("hu");
        }
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration).getResources().getString(R.string.text_update_info);
    }

    public static String sencondLanguageConvertBackDoor(Context context, String str) {
        return str == null ? "Unknow" : (str.contains("eng") && str.contains("USA")) ? "English(America)" : (str.contains("eng") && str.contains("SGP")) ? "English(Singapore)" : (str.contains("eng") && str.contains("GBR")) ? "English(United Kingdom)" : (str.contains("eng") && str.contains("AUS")) ? "English(Australia)" : (str.contains("eng") && str.contains("XWW")) ? "English(Norway)" : str.contains("NOR") ? "Norsk" : (str.contains("spa") && str.contains("ESP")) ? "Español" : (str.contains("spa") && str.contains("XLA")) ? "Español(México)" : str.contains("tha") ? "ไทย" : (str.contains("jap") || str.contains("jan") || str.contains("jpn")) ? "日本語" : str.contains("fra") ? "Français" : str.contains("deu") ? "Deutsch" : (str.contains("por") && str.contains("BRA")) ? "Português(Brasil)" : (str.contains("por") && str.contains("PRT")) ? "Português" : str.contains("ita") ? "Italiano" : str.contains("cmn") ? "中文（普通话）" : str.contains("yue") ? "中文（粤语）" : str.contains("dan") ? "Dansk" : str.contains("fin") ? "Suomi" : str.contains("heb") ? "עברית" : str.contains("kor") ? "한국어" : str.contains("nld") ? "Nederlands" : str.contains("pol") ? "Polski" : str.contains("swe") ? "Svenska" : str.contains("tur") ? "Türkçe" : str.contains("hun") ? "Magyar" : "Unknow";
    }

    public static String topLanguageConvertBackDoor(Context context, String str) {
        return str == null ? "Unknow" : (str.contains("eng") && str.contains("USA")) ? "English(America)" : (str.contains("eng") && str.contains("SGP")) ? "English(Singapore)" : (str.contains("eng") && str.contains("GBR")) ? "English(United Kingdom)" : (str.contains("eng") && str.contains("AUS")) ? "English(Australia)" : (str.contains("eng") && str.contains("XWW")) ? "English(Norway)" : str.contains("nor") ? "Norway" : (str.contains("spa") && str.contains("XLA")) ? "Spanish(Mexico)" : (str.contains("spa") && str.contains("ESP")) ? "Spanish" : str.contains("tha") ? "Thai" : (str.contains("jap") || str.contains("jan") || str.contains("jpn")) ? "Japanese" : str.contains("fra") ? "French" : str.contains("deu") ? "Germany" : (str.contains("por") && str.contains("BRA")) ? "Portuguese(Brazil)" : (str.contains("por") && str.contains("PRT")) ? "Portuguese" : str.contains("ita") ? "Italian" : str.contains("cmn") ? "Chinese" : str.contains("yue") ? "Cantonese" : str.contains("dan") ? "Danish" : str.contains("fin") ? "Finnish" : str.contains("heb") ? "Hebrew" : str.contains("kor") ? "Korean" : str.contains("nld") ? "Dutch" : str.contains("pol") ? "Polish" : str.contains("swe") ? "Swedish" : str.contains("tur") ? "Turkish" : str.contains("hun") ? "Magyar" : "Unknow";
    }

    public static String getSystemDefaultLanguage(String str) {
        if (TextUtils.isEmpty(str)) {
            return BuildConfig.FLAVOR;
        }
        str.hashCode();
        switch (str) {
        }
        return BuildConfig.FLAVOR;
    }

    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static Locale getVRLanguage(String str) {
        Locale locale = new Locale("en");
        if (TextUtils.isEmpty(str)) {
            return new Locale("en");
        }
        if (str.contains("eng")) {
            return new Locale("en");
        }
        if (str.contains("por") && str.contains("PRT")) {
            return new Locale("pt", "pt");
        }
        if (str.contains("por") && str.contains("BRA")) {
            return new Locale("pt", "br");
        }
        if (str.contains("spa") && str.contains("ESP")) {
            return new Locale("es", "es");
        }
        if (str.contains("spa") && str.contains("XLA")) {
            return new Locale("es", "mx");
        }
        if (str.contains("nor")) {
            return new Locale("nb");
        }
        if (str.contains("tha")) {
            return new Locale("th");
        }
        if (str.contains("jap") || str.contains("jan") || str.contains("jpn")) {
            return new Locale("ja");
        }
        if (str.contains("fra")) {
            return new Locale("fr");
        }
        if (str.contains("deu")) {
            return new Locale("de");
        }
        if (str.contains("ita")) {
            return new Locale("it");
        }
        if (str.contains("cmn")) {
            return new Locale("zh", "cn");
        }
        if (str.contains("yue")) {
            return new Locale("zh", "hk");
        }
        if (str.contains("kor")) {
            return new Locale("ko");
        }
        if (str.contains("tur")) {
            return new Locale("tr");
        }
        if (str.contains("swe")) {
            return new Locale("sv");
        }
        if (str.contains("nld")) {
            return new Locale("nl");
        }
        if (str.contains("dan")) {
            return new Locale("da");
        }
        return str.contains("heb") ? new Locale("iw") : locale;
    }
}
