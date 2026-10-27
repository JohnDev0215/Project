package com.android.internal.app;

import android.content.Context;
import android.telephony.TelephonyManager;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class LocaleStore {
    private static final HashMap<String, LocaleInfo> sLocaleCache = new HashMap<>();
    private static boolean sFullyInitialized = false;

    public static class LocaleInfo implements Serializable {
        private static final int SUGGESTION_TYPE_CFG = 2;
        private static final int SUGGESTION_TYPE_NONE = 0;
        private static final int SUGGESTION_TYPE_SIM = 1;
        private String mFullCountryNameNative;
        private String mFullNameNative;
        private final String mId;
        private boolean mIsChecked;
        private boolean mIsPseudo;
        private boolean mIsTranslated;
        private String mLangScriptKey;
        private final Locale mLocale;
        private final Locale mParent;
        private int mSuggestionFlags;

        static /* synthetic */ int access$076(LocaleInfo localeInfo, int i) {
            int i2 = i | localeInfo.mSuggestionFlags;
            localeInfo.mSuggestionFlags = i2;
            return i2;
        }

        private LocaleInfo(Locale locale) {
            this.mLocale = locale;
            this.mId = locale.toLanguageTag();
            this.mParent = getParent(locale);
            this.mIsChecked = false;
            this.mSuggestionFlags = 0;
            this.mIsTranslated = false;
            this.mIsPseudo = false;
        }

        private LocaleInfo(String str) {
            this(Locale.forLanguageTag(str));
        }

        private static Locale getParent(Locale locale) {
            if (locale.getCountry().isEmpty()) {
                return null;
            }
            return new Locale.Builder().setLocale(locale).setRegion("").setExtension('u', "").build();
        }

        public String toString() {
            return this.mId;
        }

        public Locale getLocale() {
            return this.mLocale;
        }

        public Locale getParent() {
            return this.mParent;
        }

        public String getId() {
            return this.mId;
        }

        public boolean isTranslated() {
            return this.mIsTranslated;
        }

        public void setTranslated(boolean z) {
            this.mIsTranslated = z;
        }

        boolean isSuggested() {
            return this.mIsTranslated && this.mSuggestionFlags != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isSuggestionOfType(int i) {
            return this.mIsTranslated && (this.mSuggestionFlags & i) == i;
        }

        public String getFullNameNative() {
            if (this.mFullNameNative == null) {
                Locale locale = this.mLocale;
                this.mFullNameNative = LocaleHelper.getDisplayName(locale, locale, true);
            }
            return this.mFullNameNative;
        }

        String getFullCountryNameNative() {
            if (this.mFullCountryNameNative == null) {
                Locale locale = this.mLocale;
                this.mFullCountryNameNative = LocaleHelper.getDisplayCountry(locale, locale);
            }
            return this.mFullCountryNameNative;
        }

        String getFullCountryNameInUiLanguage() {
            return LocaleHelper.getDisplayCountry(this.mLocale);
        }

        public String getFullNameInUiLanguage() {
            return LocaleHelper.getDisplayName(this.mLocale, true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getLangScriptKey() {
            String languageTag;
            if (this.mLangScriptKey == null) {
                Locale parent = getParent(LocaleHelper.addLikelySubtags(new Locale.Builder().setLocale(this.mLocale).setExtension('u', "").build()));
                if (parent == null) {
                    languageTag = this.mLocale.toLanguageTag();
                } else {
                    languageTag = parent.toLanguageTag();
                }
                this.mLangScriptKey = languageTag;
            }
            return this.mLangScriptKey;
        }

        String getLabel(boolean z) {
            if (z) {
                return getFullCountryNameNative();
            }
            return getFullNameNative();
        }

        String getContentDescription(boolean z) {
            if (z) {
                return getFullCountryNameInUiLanguage();
            }
            return getFullNameInUiLanguage();
        }

        public boolean getChecked() {
            return this.mIsChecked;
        }

        public void setChecked(boolean z) {
            this.mIsChecked = z;
        }
    }

    private static Set<String> getSimCountries(Context context) {
        HashSet hashSet = new HashSet();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        if (telephonyManager != null) {
            String upperCase = telephonyManager.getSimCountryIso().toUpperCase(Locale.US);
            if (!upperCase.isEmpty()) {
                hashSet.add(upperCase);
            }
            String upperCase2 = telephonyManager.getNetworkCountryIso().toUpperCase(Locale.US);
            if (!upperCase2.isEmpty()) {
                hashSet.add(upperCase2);
            }
        }
        return hashSet;
    }

    public static void updateSimCountries(Context context) {
        Set<String> simCountries = getSimCountries(context);
        for (LocaleInfo localeInfo : sLocaleCache.values()) {
            if (simCountries.contains(localeInfo.getLocale().getCountry())) {
                LocaleInfo.access$076(localeInfo, 1);
            }
        }
    }

    private static void addSuggestedLocalesForRegion(Locale locale) {
        if (locale == null) {
            return;
        }
        String country = locale.getCountry();
        if (country.isEmpty()) {
            return;
        }
        for (LocaleInfo localeInfo : sLocaleCache.values()) {
            if (country.equals(localeInfo.getLocale().getCountry())) {
                LocaleInfo.access$076(localeInfo, 1);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x007b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void fillCache(android.content.Context r11) {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.app.LocaleStore.fillCache(android.content.Context):void");
    }

    private static int getLevel(Set<String> set, LocaleInfo localeInfo, boolean z) {
        if (set.contains(localeInfo.getId())) {
            return 0;
        }
        if (localeInfo.mIsPseudo) {
            return 2;
        }
        return ((!z || localeInfo.isTranslated()) && localeInfo.getParent() != null) ? 2 : 0;
    }

    public static Set<LocaleInfo> getLevelLocales(Context context, Set<String> set, LocaleInfo localeInfo, boolean z) {
        fillCache(context);
        String id = localeInfo == null ? null : localeInfo.getId();
        HashSet hashSet = new HashSet();
        for (LocaleInfo localeInfo2 : sLocaleCache.values()) {
            if (getLevel(set, localeInfo2, z) == 2) {
                if (localeInfo == null) {
                    if (localeInfo2.isSuggestionOfType(1)) {
                        hashSet.add(localeInfo2);
                    } else {
                        hashSet.add(getLocaleInfo(localeInfo2.getParent()));
                    }
                } else if (id.equals(localeInfo2.getParent().toLanguageTag())) {
                    hashSet.add(localeInfo2);
                }
            }
        }
        return hashSet;
    }

    public static LocaleInfo getLocaleInfo(Locale locale) {
        String languageTag = locale.toLanguageTag();
        if (!sLocaleCache.containsKey(languageTag)) {
            LocaleInfo localeInfo = new LocaleInfo(locale);
            sLocaleCache.put(languageTag, localeInfo);
            return localeInfo;
        }
        return sLocaleCache.get(languageTag);
    }
}
