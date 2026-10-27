package com.android.inputmethod.keyboard.internal;

import android.text.TextUtils;
import android.util.SparseIntArray;
import com.android.inputmethod.compat.CharacterCompat;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.internal.KeySpecParser;
import com.android.inputmethod.latin.common.CollectionUtils;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.common.StringUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class MoreKeySpec {
    private static final char BACKSLASH = '\\';
    private static final char COMMA = ',';
    public final int mCode;
    public final int mIconId;

    @Nullable
    public final String mLabel;

    @Nullable
    public final String mOutputText;
    private static final String ADDITIONAL_MORE_KEY_MARKER = StringUtils.newSingleCodePointString(37);

    @Nonnull
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    public MoreKeySpec(@Nonnull final String moreKeySpec, boolean needsToUpperCase, @Nonnull final Locale locale) {
        if (moreKeySpec.isEmpty()) {
            throw new KeySpecParser.KeySpecParserError("Empty more key spec");
        }
        String label = KeySpecParser.getLabel(moreKeySpec);
        label = needsToUpperCase ? StringUtils.toTitleCaseOfKeyLabel(label, locale) : label;
        this.mLabel = label;
        int code = KeySpecParser.getCode(moreKeySpec);
        code = needsToUpperCase ? StringUtils.toTitleCaseOfKeyCode(code, locale) : code;
        if (code == -15) {
            this.mCode = -4;
            this.mOutputText = label;
        } else {
            this.mCode = code;
            String outputText = KeySpecParser.getOutputText(moreKeySpec);
            this.mOutputText = needsToUpperCase ? StringUtils.toTitleCaseOfKeyLabel(outputText, locale) : outputText;
        }
        this.mIconId = KeySpecParser.getIconId(moreKeySpec);
    }

    @Nonnull
    public Key buildKey(final int x, final int y, final int labelFlags, @Nonnull final KeyboardParams params) {
        return new Key(this.mLabel, this.mIconId, this.mCode, this.mOutputText, null, labelFlags, 1, x, y, params.mDefaultKeyWidth, params.mDefaultRowHeight, params.mHorizontalGap, params.mVerticalGap);
    }

    public int hashCode() {
        int i = ((this.mCode + 31) * 31) + this.mIconId;
        String str = this.mLabel;
        int iHashCode = (i * 31) + (str == null ? 0 : str.hashCode());
        String str2 = this.mOutputText;
        return (iHashCode * 31) + (str2 != null ? str2.hashCode() : 0);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MoreKeySpec)) {
            return false;
        }
        MoreKeySpec moreKeySpec = (MoreKeySpec) o;
        return this.mCode == moreKeySpec.mCode && this.mIconId == moreKeySpec.mIconId && TextUtils.equals(this.mLabel, moreKeySpec.mLabel) && TextUtils.equals(this.mOutputText, moreKeySpec.mOutputText);
    }

    public String toString() {
        String str = this.mIconId == 0 ? this.mLabel : KeyboardIconsSet.PREFIX_ICON + KeyboardIconsSet.getIconName(this.mIconId);
        int i = this.mCode;
        String strPrintableCode = i == -4 ? this.mOutputText : Constants.printableCode(i);
        return (StringUtils.codePointCount(str) == 1 && str.codePointAt(0) == this.mCode) ? strPrintableCode : str + "|" + strPrintableCode;
    }

    public static class LettersOnBaseLayout {
        private final SparseIntArray mCodes = new SparseIntArray();
        private final HashSet<String> mTexts = new HashSet<>();

        public void addLetter(@Nonnull final Key key) {
            int code = key.getCode();
            if (CharacterCompat.isAlphabetic(code)) {
                this.mCodes.put(code, 0);
            } else if (code == -4) {
                this.mTexts.add(key.getOutputText());
            }
        }

        public boolean contains(@Nonnull final MoreKeySpec moreKey) {
            int i = moreKey.mCode;
            if (!CharacterCompat.isAlphabetic(i) || this.mCodes.indexOfKey(i) < 0) {
                return i == -4 && this.mTexts.contains(moreKey.mOutputText);
            }
            return true;
        }
    }

    @Nullable
    public static MoreKeySpec[] removeRedundantMoreKeys(@Nullable final MoreKeySpec[] moreKeys, @Nonnull final LettersOnBaseLayout lettersOnBaseLayout) {
        if (moreKeys == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (MoreKeySpec moreKeySpec : moreKeys) {
            if (!lettersOnBaseLayout.contains(moreKeySpec)) {
                arrayList.add(moreKeySpec);
            }
        }
        int size = arrayList.size();
        if (size == moreKeys.length) {
            return moreKeys;
        }
        if (size == 0) {
            return null;
        }
        return (MoreKeySpec[]) arrayList.toArray(new MoreKeySpec[size]);
    }

    @Nullable
    public static String[] splitKeySpecs(@Nullable final String text) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        int length = text.length();
        if (length == 1) {
            if (text.charAt(0) == ',') {
                return null;
            }
            return new String[]{text};
        }
        ArrayList arrayList = null;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char cCharAt = text.charAt(i);
            if (cCharAt == ',') {
                if (i - i2 > 0) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(text.substring(i2, i));
                }
                i2 = i + 1;
            } else if (cCharAt == '\\') {
                i++;
            }
            i++;
        }
        String strSubstring = length - i2 > 0 ? text.substring(i2) : null;
        if (arrayList == null) {
            if (strSubstring != null) {
                return new String[]{strSubstring};
            }
            return null;
        }
        if (strSubstring != null) {
            arrayList.add(strSubstring);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Nonnull
    private static String[] filterOutEmptyString(@Nullable final String[] array) {
        if (array == null) {
            return EMPTY_STRING_ARRAY;
        }
        ArrayList arrayListArrayAsList = null;
        for (int i = 0; i < array.length; i++) {
            String str = array[i];
            if (TextUtils.isEmpty(str)) {
                if (arrayListArrayAsList == null) {
                    arrayListArrayAsList = CollectionUtils.arrayAsList(array, 0, i);
                }
            } else if (arrayListArrayAsList != null) {
                arrayListArrayAsList.add(str);
            }
        }
        return arrayListArrayAsList == null ? array : (String[]) arrayListArrayAsList.toArray(new String[arrayListArrayAsList.size()]);
    }

    public static String[] insertAdditionalMoreKeys(@Nullable final String[] moreKeySpecs, @Nullable final String[] additionalMoreKeySpecs) {
        String[] strArrFilterOutEmptyString = filterOutEmptyString(moreKeySpecs);
        String[] strArrFilterOutEmptyString2 = filterOutEmptyString(additionalMoreKeySpecs);
        int length = strArrFilterOutEmptyString.length;
        int length2 = strArrFilterOutEmptyString2.length;
        ArrayList arrayListArrayAsList = null;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            String str = strArrFilterOutEmptyString[i2];
            if (str.equals(ADDITIONAL_MORE_KEY_MARKER)) {
                if (i < length2) {
                    String str2 = strArrFilterOutEmptyString2[i];
                    if (arrayListArrayAsList != null) {
                        arrayListArrayAsList.add(str2);
                    } else {
                        strArrFilterOutEmptyString[i2] = str2;
                    }
                    i++;
                } else if (arrayListArrayAsList == null) {
                    arrayListArrayAsList = CollectionUtils.arrayAsList(strArrFilterOutEmptyString, 0, i2);
                }
            } else if (arrayListArrayAsList != null) {
                arrayListArrayAsList.add(str);
            }
        }
        if (length2 > 0 && i == 0) {
            arrayListArrayAsList = CollectionUtils.arrayAsList(strArrFilterOutEmptyString2, i, length2);
            for (String str3 : strArrFilterOutEmptyString) {
                arrayListArrayAsList.add(str3);
            }
        } else if (i < length2) {
            arrayListArrayAsList = CollectionUtils.arrayAsList(strArrFilterOutEmptyString, 0, length);
            for (int i3 = i; i3 < length2; i3++) {
                arrayListArrayAsList.add(strArrFilterOutEmptyString2[i]);
            }
        }
        if (arrayListArrayAsList == null && length > 0) {
            return strArrFilterOutEmptyString;
        }
        if (arrayListArrayAsList == null || arrayListArrayAsList.size() <= 0) {
            return null;
        }
        return (String[]) arrayListArrayAsList.toArray(new String[arrayListArrayAsList.size()]);
    }

    public static int getIntValue(@Nullable final String[] moreKeys, final String key, final int defaultValue) {
        if (moreKeys == null) {
            return defaultValue;
        }
        int length = key.length();
        boolean z = false;
        for (int i = 0; i < moreKeys.length; i++) {
            String str = moreKeys[i];
            if (str != null && str.startsWith(key)) {
                moreKeys[i] = null;
                if (z) {
                    continue;
                } else {
                    try {
                        defaultValue = Integer.parseInt(str.substring(length));
                        z = true;
                    } catch (NumberFormatException unused) {
                        throw new RuntimeException("integer should follow after " + key + ": " + str);
                    }
                }
            }
        }
        return defaultValue;
    }

    public static boolean getBooleanValue(@Nullable final String[] moreKeys, final String key) {
        if (moreKeys == null) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < moreKeys.length; i++) {
            String str = moreKeys[i];
            if (str != null && str.equals(key)) {
                moreKeys[i] = null;
                z = true;
            }
        }
        return z;
    }
}
