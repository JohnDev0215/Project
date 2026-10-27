package com.android.inputmethod.latin.utils;

import com.byd.ime.subtype.model.SubtypeConfigData;
import java.util.Locale;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes2.dex */
public class ScriptUtils {
    public static final int SCRIPT_ARABIC = 0;
    public static final int SCRIPT_ARMENIAN = 1;
    public static final int SCRIPT_BENGALI = 2;
    public static final int SCRIPT_CYRILLIC = 3;
    public static final int SCRIPT_DEVANAGARI = 4;
    public static final int SCRIPT_GEORGIAN = 5;
    public static final int SCRIPT_GREEK = 6;
    public static final int SCRIPT_HEBREW = 7;
    public static final int SCRIPT_KANNADA = 8;
    public static final int SCRIPT_KHMER = 9;
    public static final int SCRIPT_LAO = 10;
    public static final int SCRIPT_LATIN = 11;
    public static final int SCRIPT_MALAYALAM = 12;
    public static final int SCRIPT_MYANMAR = 13;
    public static final int SCRIPT_SINHALA = 14;
    public static final int SCRIPT_TAMIL = 15;
    public static final int SCRIPT_TELUGU = 16;
    public static final int SCRIPT_THAI = 17;
    public static final int SCRIPT_UNKNOWN = -1;
    private static final TreeMap<String, Integer> mLanguageCodeToScriptCode;

    static {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        mLanguageCodeToScriptCode = treeMap;
        treeMap.put("", 11);
        treeMap.put(SubtypeConfigData.SUBTYPE_LOCAL_LANGUAGE_Arabic, 0);
        treeMap.put("hy", 1);
        treeMap.put("bn", 2);
        treeMap.put("bg", 3);
        treeMap.put("sr", 3);
        treeMap.put("ru", 3);
        treeMap.put("ka", 5);
        treeMap.put("el", 6);
        treeMap.put(SubtypeConfigData.SUBTYPE_LOCAL_LANGUAGE_Hebrew, 7);
        treeMap.put("km", 9);
        treeMap.put("lo", 10);
        treeMap.put("ml", 12);
        treeMap.put("my", 13);
        treeMap.put("si", 14);
        treeMap.put("ta", 15);
        treeMap.put("te", 16);
        treeMap.put("th", 17);
    }

    public static boolean isLetterPartOfScript(final int codePoint, final int scriptId) {
        switch (scriptId) {
            case -1:
                return true;
            case 0:
                return (codePoint >= 1536 && codePoint <= 1791) || (codePoint >= 1872 && codePoint <= 1983) || ((codePoint >= 2208 && codePoint <= 2303) || ((codePoint >= 64336 && codePoint <= 65023) || (codePoint >= 65136 && codePoint <= 65279)));
            case 1:
                return (codePoint >= 1328 && codePoint <= 1423) || (codePoint >= 64275 && codePoint <= 64279);
            case 2:
                return codePoint >= 2432 && codePoint <= 2559;
            case 3:
                return codePoint >= 1024 && codePoint <= 1327 && Character.isLetter(codePoint);
            case 4:
                return codePoint >= 2304 && codePoint <= 2431;
            case 5:
                return (codePoint >= 4256 && codePoint <= 4351) || (codePoint >= 11520 && codePoint <= 11567);
            case 6:
                return (codePoint >= 880 && codePoint <= 1023) || (codePoint >= 7936 && codePoint <= 8191) || codePoint == 242;
            case 7:
                return (codePoint >= 1424 && codePoint <= 1535) || (codePoint >= 64285 && codePoint <= 64335);
            case 8:
                return codePoint >= 3200 && codePoint <= 3327;
            case 9:
                return (codePoint >= 6016 && codePoint <= 6143) || (codePoint >= 6624 && codePoint <= 6655);
            case 10:
                return codePoint >= 3712 && codePoint <= 3839;
            case 11:
                return codePoint <= 687 && Character.isLetter(codePoint);
            case 12:
                return codePoint >= 3328 && codePoint <= 3455;
            case 13:
                return (codePoint >= 4096 && codePoint <= 4255) || (codePoint >= 43616 && codePoint <= 43647) || (codePoint >= 43488 && codePoint <= 43519);
            case 14:
                return codePoint >= 3456 && codePoint <= 3583;
            case 15:
                return codePoint >= 2944 && codePoint <= 3071;
            case 16:
                return codePoint >= 3072 && codePoint <= 3199;
            case 17:
                return codePoint >= 3584 && codePoint <= 3711;
            default:
                throw new RuntimeException("Impossible value of script: " + scriptId);
        }
    }

    public static int getScriptFromSpellCheckerLocale(final Locale locale) {
        String language = locale.getLanguage();
        TreeMap<String, Integer> treeMap = mLanguageCodeToScriptCode;
        Integer num = treeMap.get(language);
        if (num == null) {
            num = treeMap.get("");
        }
        return num.intValue();
    }
}
