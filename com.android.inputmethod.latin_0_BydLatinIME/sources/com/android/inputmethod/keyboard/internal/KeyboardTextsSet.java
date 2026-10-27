package com.android.inputmethod.keyboard.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.android.inputmethod.latin.utils.RunInLocale;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyboardTextsSet {
    private static final char BACKSLASH = '\\';
    private static final int MAX_REFERENCE_INDIRECTION = 10;
    private static final String PREFIX_RESOURCE = "!string/";
    public static final String PREFIX_TEXT = "!text/";
    public static final String SWITCH_TO_ALPHA_KEY_LABEL = "keylabel_to_alpha";
    private Locale mResourceLocale;
    private String mResourcePackageName;
    private Resources mResources;
    private String[] mTextsTable;

    public void setLocale(final Locale locale, final Context context) {
        Resources resources = context.getResources();
        setLocale(locale, resources, resources.getResourcePackageName(context.getApplicationInfo().labelRes));
    }

    public void setLocale(final Locale locale, final Resources res, final String resourcePackageName) {
        this.mResources = res;
        this.mResourceLocale = SubtypeLocaleUtils.NO_LANGUAGE.equals(locale.toString()) ? null : locale;
        this.mResourcePackageName = resourcePackageName;
        this.mTextsTable = KeyboardTextsTable.getTextsTable(locale);
    }

    public String getText(final String name) {
        return KeyboardTextsTable.getText(name, this.mTextsTable);
    }

    private static int searchTextNameEnd(final String text, final int start) {
        int length = text.length();
        while (start < length) {
            char cCharAt = text.charAt(start);
            if ((cCharAt < 'a' || cCharAt > 'z') && cCharAt != '_' && (cCharAt < '0' || cCharAt > '9')) {
                return start;
            }
            start++;
        }
        return length;
    }

    public String resolveTextReference(final String rawText) {
        StringBuilder sb;
        if (TextUtils.isEmpty(rawText)) {
            return null;
        }
        int i = 0;
        do {
            i++;
            if (i >= 10) {
                throw new RuntimeException("Too many !text/ or !string/ reference indirection: " + rawText);
            }
            int length = rawText.length();
            if (length < 6) {
                break;
            }
            int iExpandReference = 0;
            sb = null;
            while (iExpandReference < length) {
                char cCharAt = rawText.charAt(iExpandReference);
                if (rawText.startsWith(PREFIX_TEXT, iExpandReference)) {
                    if (sb == null) {
                        sb = new StringBuilder(rawText.substring(0, iExpandReference));
                    }
                    iExpandReference = expandReference(rawText, iExpandReference, PREFIX_TEXT, sb);
                } else if (rawText.startsWith(PREFIX_RESOURCE, iExpandReference)) {
                    if (sb == null) {
                        sb = new StringBuilder(rawText.substring(0, iExpandReference));
                    }
                    iExpandReference = expandReference(rawText, iExpandReference, PREFIX_RESOURCE, sb);
                } else if (cCharAt == '\\') {
                    if (sb != null) {
                        sb.append(rawText.substring(iExpandReference, Math.min(iExpandReference + 2, length)));
                    }
                    iExpandReference++;
                } else if (sb != null) {
                    sb.append(cCharAt);
                }
                iExpandReference++;
            }
            if (sb != null) {
                rawText = sb.toString();
            }
        } while (sb != null);
        if (TextUtils.isEmpty(rawText)) {
            return null;
        }
        return rawText;
    }

    private int expandReference(final String text, final int pos, final String prefix, final StringBuilder sb) {
        int length = pos + prefix.length();
        int iSearchTextNameEnd = searchTextNameEnd(text, length);
        final String strSubstring = text.substring(length, iSearchTextNameEnd);
        if (prefix.equals(PREFIX_TEXT)) {
            sb.append(getText(strSubstring));
        } else {
            final String str = this.mResourcePackageName;
            sb.append(new RunInLocale<String>() { // from class: com.android.inputmethod.keyboard.internal.KeyboardTextsSet.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.android.inputmethod.latin.utils.RunInLocale
                public String job(final Resources res) {
                    return res.getString(res.getIdentifier(strSubstring, "string", str));
                }
            }.runInLocale(this.mResources, this.mResourceLocale));
        }
        return iSearchTextNameEnd - 1;
    }
}
