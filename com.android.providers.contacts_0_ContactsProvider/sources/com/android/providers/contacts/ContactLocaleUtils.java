package com.android.providers.contacts;

import android.icu.text.AlphabeticIndex;
import android.icu.text.Transliterator;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import com.android.providers.contacts.HanziToPinyin;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ContactLocaleUtils {
    private static ContactLocaleUtils sSingleton;
    private final LocaleSet mLocales;
    private final ContactLocaleUtilsBase mUtils;
    public static final Locale LOCALE_ARABIC = new Locale("ar");
    public static final Locale LOCALE_GREEK = new Locale("el");
    public static final Locale LOCALE_HEBREW = new Locale("he");
    public static final Locale LOCALE_SERBIAN = new Locale("sr");
    public static final Locale LOCALE_UKRAINIAN = new Locale("uk");
    public static final Locale LOCALE_THAI = new Locale("th");
    private static final Locale[] sDefaultLabelLocales = {Locale.ENGLISH, Locale.JAPANESE, Locale.KOREAN, LOCALE_THAI, LOCALE_ARABIC, LOCALE_HEBREW, LOCALE_GREEK, LOCALE_UKRAINIAN, LOCALE_SERBIAN};

    static void dumpIndex(AlphabeticIndex.ImmutableIndex immutableIndex) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        int i = 0;
        while (i < immutableIndex.getBucketCount()) {
            sb.append(str);
            sb.append(immutableIndex.getBucket(i).getLabel());
            i++;
            str = ",";
        }
        Log.d("ContactLocale", "Labels=[" + ((Object) sb) + "]");
    }

    private static class ContactLocaleUtilsBase {
        protected final AlphabeticIndex.ImmutableIndex mAlphabeticIndex;
        private final int mAlphabeticIndexBucketCount;
        private final int mNumberBucketIndex;
        private final boolean mUsePinyinTransliterator;

        public Iterator<String> getNameLookupKeys(String str, int i) {
            return null;
        }

        public ContactLocaleUtilsBase(LocaleSet localeSet) {
            this.mUsePinyinTransliterator = localeSet.shouldPreferSimplifiedChinese();
            List<Locale> localesForBuckets = getLocalesForBuckets(localeSet);
            AlphabeticIndex maxLabelCount = new AlphabeticIndex(localesForBuckets.get(0)).setMaxLabelCount(300);
            for (int i = 1; i < localesForBuckets.size(); i++) {
                maxLabelCount.addLabels(localesForBuckets.get(i));
            }
            AlphabeticIndex.ImmutableIndex immutableIndexBuildImmutableIndex = maxLabelCount.buildImmutableIndex();
            this.mAlphabeticIndex = immutableIndexBuildImmutableIndex;
            int bucketCount = immutableIndexBuildImmutableIndex.getBucketCount();
            this.mAlphabeticIndexBucketCount = bucketCount;
            this.mNumberBucketIndex = bucketCount - 1;
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0067  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        static java.util.List<java.util.Locale> getLocalesForBuckets(com.android.providers.contacts.LocaleSet r6) {
            /*
                android.os.LocaleList r6 = r6.getAllLocales()
                java.util.ArrayList r0 = new java.util.ArrayList
                int r1 = r6.size()
                java.util.Locale[] r2 = com.android.providers.contacts.ContactLocaleUtils.access$000()
                int r2 = r2.length
                int r1 = r1 + r2
                r0.<init>(r1)
                r1 = 0
                r2 = r1
            L15:
                int r3 = r6.size()
                if (r2 >= r3) goto L25
                java.util.Locale r3 = r6.get(r2)
                r0.add(r3)
                int r2 = r2 + 1
                goto L15
            L25:
                r6 = r1
            L26:
                java.util.Locale[] r2 = com.android.providers.contacts.ContactLocaleUtils.access$000()
                int r2 = r2.length
                if (r6 >= r2) goto L39
                java.util.Locale[] r2 = com.android.providers.contacts.ContactLocaleUtils.access$000()
                r2 = r2[r6]
                r0.add(r2)
                int r6 = r6 + 1
                goto L26
            L39:
                java.util.ArrayList r6 = new java.util.ArrayList
                int r2 = r0.size()
                r6.<init>(r2)
                r2 = 1
                r3 = r1
            L44:
                int r4 = r0.size()
                if (r3 >= r4) goto L6e
                java.lang.Object r4 = r0.get(r3)
                java.util.Locale r4 = (java.util.Locale) r4
                boolean r5 = r6.contains(r4)
                if (r5 == 0) goto L57
                goto L6b
            L57:
                boolean r5 = com.android.providers.contacts.LocaleSet.isLanguageChinese(r4)
                if (r5 == 0) goto L61
                if (r2 != 0) goto L60
                goto L6b
            L60:
                r2 = r1
            L61:
                boolean r5 = com.android.providers.contacts.LocaleSet.isLanguageJapanese(r4)
                if (r5 == 0) goto L68
                r2 = r1
            L68:
                r6.add(r4)
            L6b:
                int r3 = r3 + 1
                goto L44
            L6e:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.ContactLocaleUtils.ContactLocaleUtilsBase.getLocalesForBuckets(com.android.providers.contacts.LocaleSet):java.util.List");
        }

        public int getNumberBucketIndex() {
            return this.mNumberBucketIndex;
        }

        public int getBucketIndex(String str) {
            int length = str.length();
            boolean z = false;
            int iCharCount = 0;
            while (true) {
                if (iCharCount >= length) {
                    break;
                }
                int iCodePointAt = Character.codePointAt(str, iCharCount);
                if (!Character.isDigit(iCodePointAt)) {
                    if (!Character.isSpaceChar(iCodePointAt) && iCodePointAt != 43 && iCodePointAt != 40 && iCodePointAt != 41 && iCodePointAt != 46 && iCodePointAt != 45 && iCodePointAt != 35) {
                        break;
                    }
                    iCharCount += Character.charCount(iCodePointAt);
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                return this.mNumberBucketIndex;
            }
            if (this.mUsePinyinTransliterator) {
                str = HanziToPinyin.getInstance().transliterate(str);
            }
            int bucketIndex = this.mAlphabeticIndex.getBucketIndex(str);
            if (bucketIndex < 0) {
                return -1;
            }
            return bucketIndex >= this.mNumberBucketIndex ? bucketIndex + 1 : bucketIndex;
        }

        public int getBucketCount() {
            return this.mAlphabeticIndexBucketCount + 1;
        }

        public String getBucketLabel(int i) {
            if (i < 0 || i >= getBucketCount()) {
                return "";
            }
            int i2 = this.mNumberBucketIndex;
            if (i == i2) {
                return "#";
            }
            if (i > i2) {
                i--;
            }
            return this.mAlphabeticIndex.getBucket(i).getLabel();
        }

        public ArrayList<String> getLabels() {
            int bucketCount = getBucketCount();
            ArrayList<String> arrayList = new ArrayList<>(bucketCount);
            for (int i = 0; i < bucketCount; i++) {
                arrayList.add(getBucketLabel(i));
            }
            return arrayList;
        }
    }

    private static class JapaneseContactUtils extends ContactLocaleUtilsBase {
        private static final Set<Character.UnicodeBlock> CJ_BLOCKS;
        private static boolean mInitializedTransliterator;
        private static Transliterator mJapaneseTransliterator;
        private final int mMiscBucketIndex;

        public JapaneseContactUtils(LocaleSet localeSet) {
            super(localeSet);
            this.mMiscBucketIndex = super.getBucketIndex("日");
        }

        static {
            ArraySet arraySet = new ArraySet();
            arraySet.add(Character.UnicodeBlock.HIRAGANA);
            arraySet.add(Character.UnicodeBlock.KATAKANA);
            arraySet.add(Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS);
            arraySet.add(Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS);
            arraySet.add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
            arraySet.add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
            arraySet.add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B);
            arraySet.add(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION);
            arraySet.add(Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT);
            arraySet.add(Character.UnicodeBlock.CJK_COMPATIBILITY);
            arraySet.add(Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS);
            arraySet.add(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS);
            arraySet.add(Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT);
            CJ_BLOCKS = Collections.unmodifiableSet(arraySet);
        }

        private static boolean isChineseOrJapanese(int i) {
            return CJ_BLOCKS.contains(Character.UnicodeBlock.of(i));
        }

        @Override // com.android.providers.contacts.ContactLocaleUtils.ContactLocaleUtilsBase
        public int getBucketIndex(String str) {
            int bucketIndex = super.getBucketIndex(str);
            return ((bucketIndex != this.mMiscBucketIndex || isChineseOrJapanese(Character.codePointAt(str, 0))) && bucketIndex <= this.mMiscBucketIndex) ? bucketIndex : bucketIndex + 1;
        }

        @Override // com.android.providers.contacts.ContactLocaleUtils.ContactLocaleUtilsBase
        public int getBucketCount() {
            return super.getBucketCount() + 1;
        }

        @Override // com.android.providers.contacts.ContactLocaleUtils.ContactLocaleUtilsBase
        public String getBucketLabel(int i) {
            int i2 = this.mMiscBucketIndex;
            if (i == i2) {
                return "他";
            }
            if (i > i2) {
                i--;
            }
            return super.getBucketLabel(i);
        }

        @Override // com.android.providers.contacts.ContactLocaleUtils.ContactLocaleUtilsBase
        public Iterator<String> getNameLookupKeys(String str, int i) {
            if (i == 4) {
                return getRomajiNameLookupKeys(str);
            }
            return null;
        }

        private static Transliterator getJapaneseTransliterator() {
            Transliterator transliterator;
            synchronized (JapaneseContactUtils.class) {
                if (!mInitializedTransliterator) {
                    mInitializedTransliterator = true;
                    Transliterator transliterator2 = null;
                    try {
                        transliterator2 = Transliterator.getInstance("Hiragana-Latin; Katakana-Latin; Latin-Ascii");
                    } catch (IllegalArgumentException unused) {
                        Log.w("ContactLocale", "Hiragana/Katakana-Latin transliterator data is missing");
                    }
                    mJapaneseTransliterator = transliterator2;
                    transliterator = mJapaneseTransliterator;
                } else {
                    transliterator = mJapaneseTransliterator;
                }
            }
            return transliterator;
        }

        public static Iterator<String> getRomajiNameLookupKeys(String str) {
            Transliterator japaneseTransliterator = getJapaneseTransliterator();
            if (japaneseTransliterator == null) {
                return null;
            }
            String strTransliterate = japaneseTransliterator.transliterate(str);
            if (TextUtils.isEmpty(strTransliterate) || TextUtils.equals(str, strTransliterate)) {
                return null;
            }
            ArraySet arraySet = new ArraySet();
            arraySet.add(strTransliterate);
            return arraySet.iterator();
        }

        @Override // com.android.providers.contacts.ContactLocaleUtils.ContactLocaleUtilsBase
        public int getNumberBucketIndex() {
            int numberBucketIndex = super.getNumberBucketIndex();
            return numberBucketIndex > this.mMiscBucketIndex ? numberBucketIndex + 1 : numberBucketIndex;
        }
    }

    private static class SimplifiedChineseContactUtils extends ContactLocaleUtilsBase {
        public SimplifiedChineseContactUtils(LocaleSet localeSet) {
            super(localeSet);
        }

        @Override // com.android.providers.contacts.ContactLocaleUtils.ContactLocaleUtilsBase
        public Iterator<String> getNameLookupKeys(String str, int i) {
            if (i == 4 || i == 5) {
                return null;
            }
            return getPinyinNameLookupKeys(str);
        }

        public static Iterator<String> getPinyinNameLookupKeys(String str) {
            ArraySet arraySet = new ArraySet();
            ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().getTokens(str);
            int size = tokens.size();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            for (int i = size - 1; i >= 0; i--) {
                HanziToPinyin.Token token = tokens.get(i);
                int i2 = token.type;
                if (3 != i2) {
                    if (2 == i2) {
                        sb.insert(0, token.target);
                        sb2.insert(0, token.target.charAt(0));
                    } else if (1 == i2) {
                        if (sb.length() > 0) {
                            sb.insert(0, ' ');
                        }
                        if (sb3.length() > 0) {
                            sb3.insert(0, ' ');
                        }
                        sb.insert(0, token.source);
                        sb2.insert(0, token.source.charAt(0));
                    }
                    sb3.insert(0, token.source);
                    arraySet.add(sb3.toString());
                    arraySet.add(sb.toString());
                    arraySet.add(sb2.toString());
                }
            }
            return arraySet.iterator();
        }
    }

    private ContactLocaleUtils(LocaleSet localeSet) {
        if (localeSet == null) {
            this.mLocales = LocaleSet.newDefault();
        } else {
            this.mLocales = localeSet;
        }
        if (this.mLocales.shouldPreferJapanese()) {
            this.mUtils = new JapaneseContactUtils(this.mLocales);
        } else if (this.mLocales.shouldPreferSimplifiedChinese()) {
            this.mUtils = new SimplifiedChineseContactUtils(this.mLocales);
        } else {
            this.mUtils = new ContactLocaleUtilsBase(this.mLocales);
        }
        Log.i("ContactLocale", "AddressBook Labels [" + this.mLocales.toString() + "]: " + getLabels().toString());
    }

    public boolean isLocale(LocaleSet localeSet) {
        return this.mLocales.equals(localeSet);
    }

    public static synchronized ContactLocaleUtils getInstance() {
        if (sSingleton == null) {
            sSingleton = new ContactLocaleUtils(LocaleSet.newDefault());
        }
        return sSingleton;
    }

    public static ContactLocaleUtils newInstanceForTest(Locale... localeArr) {
        return new ContactLocaleUtils(LocaleSet.newForTest(localeArr));
    }

    public static synchronized void setLocaleForTest(Locale... localeArr) {
        setLocales(LocaleSet.newForTest(localeArr));
    }

    public static synchronized void setLocales(LocaleSet localeSet) {
        if (sSingleton == null || !sSingleton.isLocale(localeSet)) {
            sSingleton = new ContactLocaleUtils(localeSet);
        }
    }

    public int getBucketIndex(String str) {
        return this.mUtils.getBucketIndex(str);
    }

    public int getNumberBucketIndex() {
        return this.mUtils.getNumberBucketIndex();
    }

    public String getBucketLabel(int i) {
        return this.mUtils.getBucketLabel(i);
    }

    public ArrayList<String> getLabels() {
        return this.mUtils.getLabels();
    }

    public Iterator<String> getNameLookupKeys(String str, int i) {
        if (!this.mLocales.isPrimaryLocaleCJK()) {
            if (this.mLocales.shouldPreferSimplifiedChinese()) {
                if (i == 3 || i == 2) {
                    return SimplifiedChineseContactUtils.getPinyinNameLookupKeys(str);
                }
            } else if (i == 4) {
                return JapaneseContactUtils.getRomajiNameLookupKeys(str);
            }
        }
        return this.mUtils.getNameLookupKeys(str, i);
    }
}
