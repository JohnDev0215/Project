package com.android.inputmethod.latin.utils;

import com.android.inputmethod.latin.common.StringUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class RecapitalizeStatus {
    public static final int CAPS_MODE_ALL_LOWER = 1;
    public static final int CAPS_MODE_ALL_UPPER = 3;
    public static final int CAPS_MODE_FIRST_WORD_UPPER = 2;
    public static final int CAPS_MODE_LAST = 3;
    public static final int CAPS_MODE_ORIGINAL_MIXED_CASE = 0;
    public static final int NOT_A_RECAPITALIZE_MODE = -1;
    private int mCursorEndAfter;
    private int mCursorStartAfter;
    private int mCursorStartBefore;
    private boolean mIsEnabled = true;
    private boolean mIsStarted;
    private Locale mLocale;
    private int mRotationStyleCurrentIndex;
    private boolean mSkipOriginalMixedCaseMode;
    private int[] mSortedSeparators;
    private String mStringAfter;
    private String mStringBefore;
    private static final int[] ROTATION_STYLE = {0, 1, 2, 3};
    private static final int[] EMPTY_STORTED_SEPARATORS = new int[0];

    private static final int getStringMode(final String string, final int[] sortedSeparators) {
        if (StringUtils.isIdenticalAfterUpcase(string)) {
            return 3;
        }
        if (StringUtils.isIdenticalAfterDowncase(string)) {
            return 1;
        }
        return StringUtils.isIdenticalAfterCapitalizeEachWord(string, sortedSeparators) ? 2 : 0;
    }

    public static String modeToString(final int recapitalizeMode) {
        return recapitalizeMode != -1 ? recapitalizeMode != 0 ? recapitalizeMode != 1 ? recapitalizeMode != 2 ? recapitalizeMode != 3 ? "unknown<" + recapitalizeMode + ">" : "allUpper" : "firstWordUpper" : "allLower" : "mixedCase" : "undefined";
    }

    public RecapitalizeStatus() {
        start(-1, -1, "", Locale.getDefault(), EMPTY_STORTED_SEPARATORS);
        stop();
    }

    public void start(final int cursorStart, final int cursorEnd, final String string, final Locale locale, final int[] sortedSeparators) {
        if (this.mIsEnabled) {
            this.mCursorStartBefore = cursorStart;
            this.mStringBefore = string;
            this.mCursorStartAfter = cursorStart;
            this.mCursorEndAfter = cursorEnd;
            this.mStringAfter = string;
            int stringMode = getStringMode(string, sortedSeparators);
            this.mLocale = locale;
            this.mSortedSeparators = sortedSeparators;
            if (stringMode == 0) {
                this.mRotationStyleCurrentIndex = 0;
                this.mSkipOriginalMixedCaseMode = false;
            } else {
                int length = ROTATION_STYLE.length - 1;
                while (length > 0 && ROTATION_STYLE[length] != stringMode) {
                    length--;
                }
                this.mRotationStyleCurrentIndex = length;
                this.mSkipOriginalMixedCaseMode = true;
            }
            this.mIsStarted = true;
        }
    }

    public void stop() {
        this.mIsStarted = false;
    }

    public boolean isStarted() {
        return this.mIsStarted;
    }

    public void enable() {
        this.mIsEnabled = true;
    }

    public void disable() {
        this.mIsEnabled = false;
    }

    public boolean mIsEnabled() {
        return this.mIsEnabled;
    }

    public boolean isSetAt(final int cursorStart, final int cursorEnd) {
        return cursorStart == this.mCursorStartAfter && cursorEnd == this.mCursorEndAfter;
    }

    public void rotate() {
        int[] iArr;
        String str = this.mStringAfter;
        int i = 0;
        do {
            int i2 = this.mRotationStyleCurrentIndex + 1;
            iArr = ROTATION_STYLE;
            int length = i2 % iArr.length;
            this.mRotationStyleCurrentIndex = length;
            if (iArr[length] == 0 && this.mSkipOriginalMixedCaseMode) {
                this.mRotationStyleCurrentIndex = (length + 1) % iArr.length;
            }
            i++;
            int i3 = iArr[this.mRotationStyleCurrentIndex];
            if (i3 == 0) {
                this.mStringAfter = this.mStringBefore;
            } else if (i3 == 1) {
                this.mStringAfter = this.mStringBefore.toLowerCase(this.mLocale);
            } else if (i3 == 2) {
                this.mStringAfter = StringUtils.capitalizeEachWord(this.mStringBefore, this.mSortedSeparators, this.mLocale);
            } else if (i3 == 3) {
                this.mStringAfter = this.mStringBefore.toUpperCase(this.mLocale);
            } else {
                this.mStringAfter = this.mStringBefore;
            }
            if (!this.mStringAfter.equals(str)) {
                break;
            }
        } while (i < iArr.length + 1);
        this.mCursorEndAfter = this.mCursorStartAfter + this.mStringAfter.length();
    }

    public void trim() {
        int length = this.mStringBefore.length();
        int iOffsetByCodePoints = 0;
        while (iOffsetByCodePoints < length && Character.isWhitespace(this.mStringBefore.codePointAt(iOffsetByCodePoints))) {
            iOffsetByCodePoints = this.mStringBefore.offsetByCodePoints(iOffsetByCodePoints, 1);
        }
        int iOffsetByCodePoints2 = length;
        while (iOffsetByCodePoints2 > 0 && Character.isWhitespace(this.mStringBefore.codePointBefore(iOffsetByCodePoints2))) {
            iOffsetByCodePoints2 = this.mStringBefore.offsetByCodePoints(iOffsetByCodePoints2, -1);
        }
        if (!(iOffsetByCodePoints == 0 && length == iOffsetByCodePoints2) && iOffsetByCodePoints < iOffsetByCodePoints2) {
            int i = this.mCursorStartBefore;
            this.mCursorEndAfter = i + iOffsetByCodePoints2;
            int i2 = i + iOffsetByCodePoints;
            this.mCursorStartAfter = i2;
            this.mCursorStartBefore = i2;
            String strSubstring = this.mStringBefore.substring(iOffsetByCodePoints, iOffsetByCodePoints2);
            this.mStringBefore = strSubstring;
            this.mStringAfter = strSubstring;
        }
    }

    public String getRecapitalizedString() {
        return this.mStringAfter;
    }

    public int getNewCursorStart() {
        return this.mCursorStartAfter;
    }

    public int getNewCursorEnd() {
        return this.mCursorEndAfter;
    }

    public int getCurrentMode() {
        return ROTATION_STYLE[this.mRotationStyleCurrentIndex];
    }
}
