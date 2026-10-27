package com.android.inputmethod.latin.makedict;

import com.android.inputmethod.latin.makedict.FormatSpec;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class DictionaryHeader {
    public static final String ATTRIBUTE_VALUE_TRUE = "1";
    public static final String CODE_POINT_TABLE_KEY = "codePointTable";
    public static final String DICTIONARY_DATE_KEY = "date";
    public static final String DICTIONARY_DESCRIPTION_KEY = "description";
    public static final String DICTIONARY_ID_KEY = "dictionary";
    public static final String DICTIONARY_LOCALE_KEY = "locale";
    public static final String DICTIONARY_VERSION_KEY = "version";
    public static final String FORGETTING_CURVE_PROBABILITY_VALUES_TABLE_ID_KEY = "FORGETTING_CURVE_PROBABILITY_VALUES_TABLE_ID";
    public static final String HAS_HISTORICAL_INFO_KEY = "HAS_HISTORICAL_INFO";
    public static final String MAX_BIGRAM_COUNT_KEY = "MAX_BIGRAM_ENTRY_COUNT";
    public static final String MAX_TRIGRAM_COUNT_KEY = "MAX_TRIGRAM_ENTRY_COUNT";
    public static final String MAX_UNIGRAM_COUNT_KEY = "MAX_UNIGRAM_ENTRY_COUNT";
    public static final String USES_FORGETTING_CURVE_KEY = "USES_FORGETTING_CURVE";
    public final int mBodyOffset;

    @Nonnull
    public final FormatSpec.DictionaryOptions mDictionaryOptions;

    @Nonnull
    public final FormatSpec.FormatOptions mFormatOptions;

    @Nonnull
    public final String mIdString;

    @Nonnull
    public final String mLocaleString;

    @Nonnull
    public final String mVersionString;

    public DictionaryHeader(final int headerSize, @Nonnull final FormatSpec.DictionaryOptions dictionaryOptions, @Nonnull final FormatSpec.FormatOptions formatOptions) throws UnsupportedFormatException {
        this.mDictionaryOptions = dictionaryOptions;
        this.mFormatOptions = formatOptions;
        this.mBodyOffset = formatOptions.mVersion >= 403 ? 0 : headerSize;
        String str = dictionaryOptions.mAttributes.get("locale");
        if (str == null) {
            throw new UnsupportedFormatException("Cannot create a FileHeader without a locale");
        }
        String str2 = dictionaryOptions.mAttributes.get("version");
        if (str2 == null) {
            throw new UnsupportedFormatException("Cannot create a FileHeader without a version");
        }
        String str3 = dictionaryOptions.mAttributes.get("dictionary");
        if (str3 == null) {
            throw new UnsupportedFormatException("Cannot create a FileHeader without an ID");
        }
        this.mLocaleString = str;
        this.mVersionString = str2;
        this.mIdString = str3;
    }

    @Nullable
    public String getDescription() {
        return this.mDictionaryOptions.mAttributes.get("description");
    }
}
