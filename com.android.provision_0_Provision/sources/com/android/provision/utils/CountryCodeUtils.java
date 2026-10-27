package com.android.provision.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.LocaleList;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.android.provision.BootGuideApplication;
import com.android.provision.BootGuideManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class CountryCodeUtils {
    public static final int AMERICAS_REGION = 32;
    public static final int ASIA_REGION = 16;
    public static final String BOSCH_CONFIG_AREA = "persist.vendor.byd.cfg.huc.config.area";
    public static final String COUNTRY_PROPERTIES = "sys.byd.countrycode";
    public static final int EUROPE_IL_REGION = 65;
    public static final int EUROPE_REGION = 64;
    public static final int EUROPE_UZ_REGION = 66;
    public static final int MIDDLE_EAST_AND_AFRICA_REGION = 48;
    private static final List<String> PRIVACY_LIST = new ArrayList();
    public static final String TAG = "CountryCodeUtils";

    static {
        try {
            for (String str : BootGuideApplication.getAppContext().getAssets().list("privacy")) {
                if (str.startsWith("privacy_")) {
                    BydLogHelper.logW(TAG, "name = " + str);
                    PRIVACY_LIST.add(str);
                }
            }
            BydLogHelper.logW(TAG, "country size = " + PRIVACY_LIST.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0046 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0049  */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int getAreaByCountryCode() {
        /*
            Method dump skipped, instruction units count: 708
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.provision.utils.CountryCodeUtils.getAreaByCountryCode():int");
    }

    public static String getPrivacyH5() {
        int i = SystemProperties.getInt("sys.byd.countrycode", 47);
        BydLogHelper.logW(TAG, "getPrivacyH5 country code = " + i);
        if (i == 82) {
            Locale curLanguage = getCurLanguage();
            if (Locale.SIMPLIFIED_CHINESE.equals(curLanguage)) {
                BydLogHelper.logW(TAG, "getPrivacyH5 korea_chinese");
                return "file:///android_asset/privacy/privacy_korea_chinese.html";
            }
            if (Locale.KOREAN.equals(curLanguage)) {
                BydLogHelper.logW(TAG, "getPrivacyH5 korea_korean");
                return "file:///android_asset/privacy/privacy_korea_korea.html";
            }
            BydLogHelper.logW(TAG, "getPrivacyH5 korea_other");
            return "file:///android_asset/privacy/privacy_korea_english.html";
        }
        int areaByCountryCode = getAreaByCountryCode();
        String str = BydApi.ASIA_PACIFIC;
        if (areaByCountryCode != 16) {
            if (areaByCountryCode == 32) {
                str = BydApi.SOUTH_AMERICAS;
            } else if (areaByCountryCode != 48) {
                switch (areaByCountryCode) {
                    case 64:
                        str = BydApi.EUROPE;
                        break;
                    case 65:
                        str = BydApi.ISRAEL;
                        break;
                    case 66:
                        str = BydApi.UZBEKISTAN;
                        break;
                }
            } else {
                str = BydApi.MIDDLE_EAST_AFRICA;
            }
        }
        String languageStr = getLanguageStr(areaByCountryCode);
        BydLogHelper.logW(TAG, "area = " + str + " ,language = " + languageStr + " ,isDenza = " + StyleUtil.isDenza());
        if (StyleUtil.isDenza()) {
            return findPrivacyAndSetIndexDenza(str, languageStr);
        }
        return findPrivacyAndSetIndex(str, languageStr);
    }

    public static String findPrivacyAndSetIndex(String area, String language) {
        List<String> list;
        int i = 0;
        int i2 = 0;
        while (true) {
            list = PRIVACY_LIST;
            if (i2 >= list.size()) {
                break;
            }
            String str = list.get(i2);
            if (str.contains(area) && str.contains(language + "_v") && !str.contains(BydApi.FLAG_DENZA)) {
                i = i2;
                break;
            }
            i2++;
        }
        BydLogHelper.logW(TAG, "index = " + i);
        BootGuideManager.getInstance().setCountryIndex(i);
        String str2 = list.get(i);
        BydLogHelper.logW(TAG, "filename = " + str2);
        return BydApi.H5_HEADER + str2;
    }

    public static String findPrivacyAndSetIndexDenza(String area, String language) {
        List<String> list;
        int i = 0;
        int i2 = 0;
        while (true) {
            list = PRIVACY_LIST;
            if (i2 >= list.size()) {
                break;
            }
            String str = list.get(i2);
            if (str.contains(area) && str.contains(language + "_v") && str.contains(BydApi.FLAG_DENZA)) {
                i = i2;
                break;
            }
            i2++;
        }
        BydLogHelper.logW(TAG, "index = " + i);
        BootGuideManager.getInstance().setCountryIndex(i);
        String str2 = list.get(i);
        BydLogHelper.logW(TAG, "filename = " + str2);
        return BydApi.H5_HEADER + str2;
    }

    public static boolean isZh() {
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        BydLogHelper.logW(TAG, "language:" + language + ",country:" + country);
        return !TextUtils.isEmpty(language) && language.endsWith("zh") && !TextUtils.isEmpty(country) && country.endsWith("CN");
    }

    private static void setRegionIndex(int area) {
        int i = 1;
        if (area == 16) {
            i = 0;
        } else if (area == 32) {
            i = 4;
        } else if (area != 48) {
            switch (area) {
                case 65:
                    i = 2;
                    break;
                case 66:
                    i = 5;
                    break;
            }
        } else {
            i = 3;
        }
        BootGuideManager.getInstance().setCountryIndex(i);
    }

    public static String getLanguageStr(int area) {
        Locale curLanguage = getCurLanguage();
        if (curLanguage == null) {
            return "en";
        }
        BydLogHelper.logD("locale = " + curLanguage.getLanguage());
        if (area != 16) {
            if (area != 32) {
                if (area != 48) {
                    switch (area) {
                        case 64:
                            if (!Locale.SIMPLIFIED_CHINESE.equals(curLanguage)) {
                                return Locale.forLanguageTag("es").equals(curLanguage) ? "es" : Locale.forLanguageTag("pt-PT").equals(curLanguage) ? "pt" : Locale.FRENCH.equals(curLanguage) ? "fr" : Locale.GERMAN.equals(curLanguage) ? "de" : Locale.ITALIAN.equals(curLanguage) ? "it" : Locale.forLanguageTag("sv").equals(curLanguage) ? "sv" : Locale.forLanguageTag("nb").equals(curLanguage) ? "nb" : Locale.forLanguageTag("tr").equals(curLanguage) ? "tr" : "en";
                            }
                            break;
                        case 65:
                            if (!Locale.SIMPLIFIED_CHINESE.equals(curLanguage)) {
                                return Locale.forLanguageTag("iw").equals(curLanguage) ? "iw" : "en";
                            }
                            break;
                        case 66:
                            if (!Locale.SIMPLIFIED_CHINESE.equals(curLanguage)) {
                                return Locale.forLanguageTag("ru").equals(curLanguage) ? "ru" : Locale.forLanguageTag("uz-UZ").equals(curLanguage) ? "uz" : "en";
                            }
                            break;
                        default:
                            return "en";
                    }
                } else if (!Locale.SIMPLIFIED_CHINESE.equals(curLanguage)) {
                    return "en";
                }
            } else if (!Locale.SIMPLIFIED_CHINESE.equals(curLanguage)) {
                return Locale.forLanguageTag("es-LA").equals(curLanguage) ? "es_la" : Locale.forLanguageTag("pt-PT").equals(curLanguage) ? "pt_br" : "en";
            }
        } else if (!Locale.SIMPLIFIED_CHINESE.equals(curLanguage)) {
            return (Locale.TRADITIONAL_CHINESE.equals(curLanguage) || Locale.forLanguageTag("zh-HK").equals(curLanguage)) ? "zh_tw" : Locale.forLanguageTag("ms").equals(curLanguage) ? "ms" : Locale.forLanguageTag("ja").equals(curLanguage) ? "ja" : Locale.forLanguageTag("th").equals(curLanguage) ? "th" : Locale.forLanguageTag("in").equals(curLanguage) ? "in" : "en";
        }
        return "zh";
    }

    public static Locale getCurLanguage() {
        LocaleList locales;
        Configuration configuration = BootGuideApplication.getAppContext().getResources().getConfiguration();
        if (configuration == null || (locales = configuration.getLocales()) == null || locales.isEmpty()) {
            return null;
        }
        return locales.get(0);
    }

    public static boolean isRtl(Context context) {
        String language = context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        return "ar".equals(language) || "iw".equals(language) || "fa".equals(language) || "ur".equals(language) || "yi".equals(language) || "dv".equals(language);
    }
}
