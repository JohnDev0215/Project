package com.android.inputmethod.compat;

import android.view.textservice.TextInfo;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class TextInfoCompatUtils {
    private static final Method TEXT_INFO_GET_CHAR_SEQUENCE = CompatUtils.getMethod(TextInfo.class, "getCharSequence", new Class[0]);
    private static final Constructor<?> TEXT_INFO_CONSTRUCTOR_FOR_CHAR_SEQUENCE = CompatUtils.getConstructor(TextInfo.class, CharSequence.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);

    public static boolean isCharSequenceSupported() {
        return (TEXT_INFO_GET_CHAR_SEQUENCE == null || TEXT_INFO_CONSTRUCTOR_FOR_CHAR_SEQUENCE == null) ? false : true;
    }

    public static TextInfo newInstance(CharSequence charSequence, int start, int end, int cookie, int sequenceNumber) {
        Constructor<?> constructor = TEXT_INFO_CONSTRUCTOR_FOR_CHAR_SEQUENCE;
        if (constructor != null) {
            return (TextInfo) CompatUtils.newInstance(constructor, charSequence, Integer.valueOf(start), Integer.valueOf(end), Integer.valueOf(cookie), Integer.valueOf(sequenceNumber));
        }
        return new TextInfo(charSequence.subSequence(start, end).toString(), cookie, sequenceNumber);
    }

    public static CharSequence getCharSequenceOrString(final TextInfo textInfo) {
        return (CharSequence) CompatUtils.invoke(textInfo, textInfo == null ? null : textInfo.getText(), TEXT_INFO_GET_CHAR_SEQUENCE, new Object[0]);
    }
}
