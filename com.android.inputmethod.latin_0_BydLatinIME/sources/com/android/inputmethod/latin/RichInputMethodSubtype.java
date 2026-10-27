package com.android.inputmethod.latin;

import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.compat.BuildCompatUtils;
import com.android.inputmethod.compat.InputMethodSubtypeCompatUtils;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.common.LocaleUtils;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;
import com.byd.ime.ResModifier;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class RichInputMethodSubtype {
    private static final String TAG = "RichInputMethodSubtype";
    private static RichInputMethodSubtype sEmojiSubtype;
    private static RichInputMethodSubtype sNoLanguageSubtype;

    @Nonnull
    private final Locale mLocale;

    @Nonnull
    private final Locale mOriginalLocale;

    @Nonnull
    private final InputMethodSubtype mSubtype;
    private static final HashMap<Locale, Locale> sLocaleMap = initializeLocaleMap();
    private static final String EXTRA_VALUE_OF_DUMMY_NO_LANGUAGE_SUBTYPE = "KeyboardLayoutSet=qwerty,AsciiCapable,EnabledWhenDefaultIsNotAsciiCapable,EmojiCapable";
    private static final int SUBTYPE_ID_OF_DUMMY_NO_LANGUAGE_SUBTYPE = -572473389;

    @Nonnull
    private static final RichInputMethodSubtype DUMMY_NO_LANGUAGE_SUBTYPE = new RichInputMethodSubtype(InputMethodSubtypeCompatUtils.newInputMethodSubtype(R.string.subtype_no_language_qwerty, R.drawable.ic_ime_switcher_dark, SubtypeLocaleUtils.NO_LANGUAGE, Constants.Subtype.KEYBOARD_MODE, EXTRA_VALUE_OF_DUMMY_NO_LANGUAGE_SUBTYPE, false, false, SUBTYPE_ID_OF_DUMMY_NO_LANGUAGE_SUBTYPE));
    private static final String EXTRA_VALUE_OF_DUMMY_EMOJI_SUBTYPE = "KeyboardLayoutSet=emoji,EmojiCapable";
    private static final int SUBTYPE_ID_OF_DUMMY_EMOJI_SUBTYPE = -678744368;

    @Nonnull
    private static final RichInputMethodSubtype DUMMY_EMOJI_SUBTYPE = new RichInputMethodSubtype(InputMethodSubtypeCompatUtils.newInputMethodSubtype(R.string.subtype_emoji, R.drawable.ic_ime_switcher_dark, SubtypeLocaleUtils.NO_LANGUAGE, Constants.Subtype.KEYBOARD_MODE, EXTRA_VALUE_OF_DUMMY_EMOJI_SUBTYPE, false, false, SUBTYPE_ID_OF_DUMMY_EMOJI_SUBTYPE));

    private static final HashMap<Locale, Locale> initializeLocaleMap() {
        HashMap<Locale, Locale> map = new HashMap<>();
        if (BuildCompatUtils.EFFECTIVE_SDK_INT >= 21) {
            map.put(Locale.forLanguageTag("sr-Latn"), new Locale("sr_ZZ"));
        }
        return map;
    }

    public RichInputMethodSubtype(@Nonnull final InputMethodSubtype subtype) {
        this.mSubtype = subtype;
        Locale localeObject = InputMethodSubtypeCompatUtils.getLocaleObject(subtype);
        this.mOriginalLocale = localeObject;
        Locale locale = sLocaleMap.get(localeObject);
        this.mLocale = locale != null ? locale : localeObject;
    }

    public String getExtraValueOf(@Nonnull final String key) {
        return this.mSubtype.getExtraValueOf(key);
    }

    public String getMode() {
        return this.mSubtype.getMode();
    }

    public boolean isNoLanguage() {
        return SubtypeLocaleUtils.NO_LANGUAGE.equals(this.mSubtype.getLocale());
    }

    public String getNameForLogging() {
        return toString();
    }

    @Nonnull
    public String getFullDisplayName() {
        if (isNoLanguage()) {
            return SubtypeLocaleUtils.getKeyboardLayoutSetDisplayName(this.mSubtype);
        }
        return SubtypeLocaleUtils.getSubtypeLocaleDisplayName(this.mSubtype.getLocale());
    }

    @Nonnull
    public String getMiddleDisplayName() {
        if (isNoLanguage()) {
            return SubtypeLocaleUtils.getKeyboardLayoutSetDisplayName(this.mSubtype);
        }
        if (ResModifier.resetLanguageDisplayName(this.mSubtype) != null) {
            return (String) Objects.requireNonNull(ResModifier.resetLanguageDisplayName(this.mSubtype));
        }
        return SubtypeLocaleUtils.getSubtypeLanguageDisplayName(this.mSubtype.getLocale());
    }

    public boolean equals(final Object o) {
        if (!(o instanceof RichInputMethodSubtype)) {
            return false;
        }
        RichInputMethodSubtype richInputMethodSubtype = (RichInputMethodSubtype) o;
        return this.mSubtype.equals(richInputMethodSubtype.mSubtype) && this.mLocale.equals(richInputMethodSubtype.mLocale);
    }

    public int hashCode() {
        return this.mSubtype.hashCode() + this.mLocale.hashCode();
    }

    public String toString() {
        return "Multi-lingual subtype: " + this.mSubtype + ", " + this.mLocale;
    }

    @Nonnull
    public Locale getLocale() {
        return this.mLocale;
    }

    @Nonnull
    public Locale getOriginalLocale() {
        return this.mOriginalLocale;
    }

    public boolean isRtlSubtype() {
        return LocaleUtils.isRtlLanguage(this.mLocale);
    }

    @Nonnull
    public InputMethodSubtype getRawSubtype() {
        return this.mSubtype;
    }

    @Nonnull
    public String getKeyboardLayoutSetName() {
        return SubtypeLocaleUtils.getKeyboardLayoutSetName(this.mSubtype);
    }

    public static RichInputMethodSubtype getRichInputMethodSubtype(@Nullable final InputMethodSubtype subtype) {
        if (subtype == null) {
            return getNoLanguageSubtype();
        }
        return new RichInputMethodSubtype(subtype);
    }

    @Nonnull
    public static RichInputMethodSubtype getNoLanguageSubtype() {
        InputMethodSubtype inputMethodSubtypeFindSubtypeByLocaleAndKeyboardLayoutSet;
        RichInputMethodSubtype richInputMethodSubtype = sNoLanguageSubtype;
        if (richInputMethodSubtype == null && (inputMethodSubtypeFindSubtypeByLocaleAndKeyboardLayoutSet = RichInputMethodManager.getInstance().findSubtypeByLocaleAndKeyboardLayoutSet(SubtypeLocaleUtils.NO_LANGUAGE, SubtypeLocaleUtils.QWERTY)) != null) {
            richInputMethodSubtype = new RichInputMethodSubtype(inputMethodSubtypeFindSubtypeByLocaleAndKeyboardLayoutSet);
        }
        if (richInputMethodSubtype != null) {
            sNoLanguageSubtype = richInputMethodSubtype;
            return richInputMethodSubtype;
        }
        String str = TAG;
        Log.w(str, "Can't find any language with QWERTY subtype");
        StringBuilder sbAppend = new StringBuilder().append("No input method subtype found; returning dummy subtype: ");
        RichInputMethodSubtype richInputMethodSubtype2 = DUMMY_NO_LANGUAGE_SUBTYPE;
        Log.w(str, sbAppend.append(richInputMethodSubtype2).toString());
        return richInputMethodSubtype2;
    }

    @Nonnull
    public static RichInputMethodSubtype getEmojiSubtype() {
        InputMethodSubtype inputMethodSubtypeFindSubtypeByLocaleAndKeyboardLayoutSet;
        RichInputMethodSubtype richInputMethodSubtype = sEmojiSubtype;
        if (richInputMethodSubtype == null && (inputMethodSubtypeFindSubtypeByLocaleAndKeyboardLayoutSet = RichInputMethodManager.getInstance().findSubtypeByLocaleAndKeyboardLayoutSet(SubtypeLocaleUtils.NO_LANGUAGE, SubtypeLocaleUtils.EMOJI)) != null) {
            richInputMethodSubtype = new RichInputMethodSubtype(inputMethodSubtypeFindSubtypeByLocaleAndKeyboardLayoutSet);
        }
        if (richInputMethodSubtype != null) {
            sEmojiSubtype = richInputMethodSubtype;
            return richInputMethodSubtype;
        }
        String str = TAG;
        Log.w(str, "Can't find emoji subtype");
        StringBuilder sbAppend = new StringBuilder().append("No input method subtype found; returning dummy subtype: ");
        RichInputMethodSubtype richInputMethodSubtype2 = DUMMY_EMOJI_SUBTYPE;
        Log.w(str, sbAppend.append(richInputMethodSubtype2).toString());
        return richInputMethodSubtype2;
    }
}
