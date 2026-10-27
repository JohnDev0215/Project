package com.android.inputmethod.keyboard;

import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import androidx.core.app.NotificationCompat;
import com.android.inputmethod.compat.EditorInfoCompatUtils;
import com.android.inputmethod.dictionarypack.MetadataDbHelper;
import com.android.inputmethod.keyboard.KeyboardLayoutSet;
import com.android.inputmethod.latin.RichInputMethodSubtype;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.utils.InputTypeUtils;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyboardId {
    public static final int ELEMENT_ALPHABET = 0;
    public static final int ELEMENT_ALPHABET_AUTOMATIC_SHIFTED = 2;
    public static final int ELEMENT_ALPHABET_MANUAL_SHIFTED = 1;
    public static final int ELEMENT_ALPHABET_SHIFT_LOCKED = 3;
    public static final int ELEMENT_ALPHABET_SHIFT_LOCK_SHIFTED = 4;
    public static final int ELEMENT_EMOJI_CATEGORY1 = 11;
    public static final int ELEMENT_EMOJI_CATEGORY10 = 20;
    public static final int ELEMENT_EMOJI_CATEGORY11 = 21;
    public static final int ELEMENT_EMOJI_CATEGORY12 = 22;
    public static final int ELEMENT_EMOJI_CATEGORY13 = 23;
    public static final int ELEMENT_EMOJI_CATEGORY14 = 24;
    public static final int ELEMENT_EMOJI_CATEGORY15 = 25;
    public static final int ELEMENT_EMOJI_CATEGORY16 = 26;
    public static final int ELEMENT_EMOJI_CATEGORY2 = 12;
    public static final int ELEMENT_EMOJI_CATEGORY3 = 13;
    public static final int ELEMENT_EMOJI_CATEGORY4 = 14;
    public static final int ELEMENT_EMOJI_CATEGORY5 = 15;
    public static final int ELEMENT_EMOJI_CATEGORY6 = 16;
    public static final int ELEMENT_EMOJI_CATEGORY7 = 17;
    public static final int ELEMENT_EMOJI_CATEGORY8 = 18;
    public static final int ELEMENT_EMOJI_CATEGORY9 = 19;
    public static final int ELEMENT_EMOJI_RECENTS = 10;
    public static final int ELEMENT_NUMBER = 9;
    public static final int ELEMENT_PHONE = 7;
    public static final int ELEMENT_PHONE_SYMBOLS = 8;
    public static final int ELEMENT_SYMBOLS = 5;
    public static final int ELEMENT_SYMBOLS_SHIFTED = 6;
    public static final int MODE_DATE = 6;
    public static final int MODE_DATETIME = 8;
    public static final int MODE_EMAIL = 2;
    public static final int MODE_IM = 3;
    public static final int MODE_NUMBER = 5;
    public static final int MODE_PHONE = 4;
    public static final int MODE_TEXT = 0;
    public static final int MODE_TIME = 7;
    public static final int MODE_URL = 1;
    public final boolean mClobberSettingsKey;
    public final String mCustomActionLabel;
    public final EditorInfo mEditorInfo;
    public final int mElementId;
    public final boolean mHasShortcutKey;
    private final int mHashCode;
    public final int mHeight;
    public final boolean mIsSplitLayout;
    public final boolean mLanguageSwitchKeyEnabled;
    public final int mMode;
    public final RichInputMethodSubtype mSubtype;
    public final int mWidth;

    public static String elementIdToName(final int elementId) {
        switch (elementId) {
            case 0:
                return "alphabet";
            case 1:
                return "alphabetManualShifted";
            case 2:
                return "alphabetAutomaticShifted";
            case 3:
                return "alphabetShiftLocked";
            case 4:
                return "alphabetShiftLockShifted";
            case 5:
                return "symbols";
            case 6:
                return "symbolsShifted";
            case 7:
                return "phone";
            case 8:
                return "phoneSymbols";
            case 9:
                return "number";
            case 10:
                return "emojiRecents";
            case 11:
                return "emojiCategory1";
            case 12:
                return "emojiCategory2";
            case 13:
                return "emojiCategory3";
            case 14:
                return "emojiCategory4";
            case 15:
                return "emojiCategory5";
            case 16:
                return "emojiCategory6";
            case 17:
                return "emojiCategory7";
            case 18:
                return "emojiCategory8";
            case 19:
                return "emojiCategory9";
            case 20:
                return "emojiCategory10";
            case 21:
                return "emojiCategory11";
            case 22:
                return "emojiCategory12";
            case 23:
                return "emojiCategory13";
            case 24:
                return "emojiCategory14";
            case 25:
                return "emojiCategory15";
            case 26:
                return "emojiCategory16";
            default:
                return null;
        }
    }

    private static boolean isAlphabetKeyboard(final int elementId) {
        return elementId < 5;
    }

    public static String modeName(final int mode) {
        switch (mode) {
            case 0:
                return "text";
            case 1:
                return MetadataDbHelper.REMOTE_FILENAME_COLUMN;
            case 2:
                return NotificationCompat.CATEGORY_EMAIL;
            case 3:
                return "im";
            case 4:
                return "phone";
            case 5:
                return "number";
            case 6:
                return "date";
            case 7:
                return "time";
            case 8:
                return "datetime";
            default:
                return null;
        }
    }

    public KeyboardId(final int elementId, final KeyboardLayoutSet.Params params) {
        this.mSubtype = params.mSubtype;
        this.mWidth = params.mKeyboardWidth;
        this.mHeight = params.mKeyboardHeight;
        this.mMode = params.mMode;
        this.mElementId = elementId;
        EditorInfo editorInfo = params.mEditorInfo;
        this.mEditorInfo = editorInfo;
        this.mClobberSettingsKey = params.mNoSettingsKey;
        this.mLanguageSwitchKeyEnabled = params.mLanguageSwitchKeyEnabled;
        this.mCustomActionLabel = editorInfo.actionLabel != null ? editorInfo.actionLabel.toString() : null;
        this.mHasShortcutKey = params.mVoiceInputKeyEnabled;
        this.mIsSplitLayout = params.mIsSplitLayoutEnabled;
        this.mHashCode = computeHashCode(this);
    }

    private static int computeHashCode(final KeyboardId id) {
        return Arrays.hashCode(new Object[]{Integer.valueOf(id.mElementId), Integer.valueOf(id.mMode), Integer.valueOf(id.mWidth), Integer.valueOf(id.mHeight), Boolean.valueOf(id.passwordInput()), Boolean.valueOf(id.mClobberSettingsKey), Boolean.valueOf(id.mHasShortcutKey), Boolean.valueOf(id.mLanguageSwitchKeyEnabled), Boolean.valueOf(id.isMultiLine()), Integer.valueOf(id.imeAction()), id.mCustomActionLabel, Boolean.valueOf(id.navigateNext()), Boolean.valueOf(id.navigatePrevious()), id.mSubtype, Boolean.valueOf(id.mIsSplitLayout)});
    }

    private boolean equals(final KeyboardId other) {
        if (other == this) {
            return true;
        }
        return other.mElementId == this.mElementId && other.mMode == this.mMode && other.mWidth == this.mWidth && other.mHeight == this.mHeight && other.passwordInput() == passwordInput() && other.mClobberSettingsKey == this.mClobberSettingsKey && other.mHasShortcutKey == this.mHasShortcutKey && other.mLanguageSwitchKeyEnabled == this.mLanguageSwitchKeyEnabled && other.isMultiLine() == isMultiLine() && other.imeAction() == imeAction() && TextUtils.equals(other.mCustomActionLabel, this.mCustomActionLabel) && other.navigateNext() == navigateNext() && other.navigatePrevious() == navigatePrevious() && other.mSubtype.equals(this.mSubtype) && other.mIsSplitLayout == this.mIsSplitLayout;
    }

    public boolean isAlphabetKeyboard() {
        return isAlphabetKeyboard(this.mElementId);
    }

    public boolean navigateNext() {
        return (this.mEditorInfo.imeOptions & 134217728) != 0 || imeAction() == 5;
    }

    public boolean navigatePrevious() {
        return (this.mEditorInfo.imeOptions & 67108864) != 0 || imeAction() == 7;
    }

    public boolean passwordInput() {
        int i = this.mEditorInfo.inputType;
        return InputTypeUtils.isPasswordInputType(i) || InputTypeUtils.isVisiblePasswordInputType(i);
    }

    public boolean isMultiLine() {
        return (this.mEditorInfo.inputType & 131072) != 0;
    }

    public int imeAction() {
        return InputTypeUtils.getImeOptionsActionIdFromEditorInfo(this.mEditorInfo);
    }

    public Locale getLocale() {
        return this.mSubtype.getLocale();
    }

    public boolean equals(final Object other) {
        return (other instanceof KeyboardId) && equals((KeyboardId) other);
    }

    public int hashCode() {
        return this.mHashCode;
    }

    public String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[15];
        objArr[0] = elementIdToName(this.mElementId);
        objArr[1] = this.mSubtype.getLocale();
        objArr[2] = this.mSubtype.getExtraValueOf(Constants.Subtype.ExtraValue.KEYBOARD_LAYOUT_SET);
        objArr[3] = Integer.valueOf(this.mWidth);
        objArr[4] = Integer.valueOf(this.mHeight);
        objArr[5] = modeName(this.mMode);
        objArr[6] = actionName(imeAction());
        objArr[7] = navigateNext() ? " navigateNext" : "";
        objArr[8] = navigatePrevious() ? " navigatePrevious" : "";
        objArr[9] = this.mClobberSettingsKey ? " clobberSettingsKey" : "";
        objArr[10] = passwordInput() ? " passwordInput" : "";
        objArr[11] = this.mHasShortcutKey ? " hasShortcutKey" : "";
        objArr[12] = this.mLanguageSwitchKeyEnabled ? " languageSwitchKeyEnabled" : "";
        objArr[13] = isMultiLine() ? " isMultiLine" : "";
        objArr[14] = this.mIsSplitLayout ? " isSplitLayout" : "";
        return String.format(locale, "[%s %s:%s %dx%d %s %s%s%s%s%s%s%s%s%s]", objArr);
    }

    public static boolean equivalentEditorInfoForKeyboard(final EditorInfo a, final EditorInfo b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.inputType == b.inputType && a.imeOptions == b.imeOptions && TextUtils.equals(a.privateImeOptions, b.privateImeOptions);
    }

    public static String actionName(final int actionId) {
        return actionId == 256 ? "actionCustomLabel" : EditorInfoCompatUtils.imeActionName(actionId);
    }
}
