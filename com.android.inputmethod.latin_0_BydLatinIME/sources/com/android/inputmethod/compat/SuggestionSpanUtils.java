package com.android.inputmethod.compat;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.SuggestionSpan;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.LocaleUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class SuggestionSpanUtils {
    private static final Field FIELD_FLAG_AUTO_CORRECTION;
    private static final Integer OBJ_FLAG_AUTO_CORRECTION;

    static {
        Field field = CompatUtils.getField(SuggestionSpan.class, "FLAG_AUTO_CORRECTION");
        FIELD_FLAG_AUTO_CORRECTION = field;
        OBJ_FLAG_AUTO_CORRECTION = (Integer) CompatUtils.getFieldValue(null, null, field);
    }

    private SuggestionSpanUtils() {
    }

    public static CharSequence getTextWithAutoCorrectionIndicatorUnderline(final Context context, final String text, @Nonnull final Locale locale) {
        Integer num;
        if (TextUtils.isEmpty(text) || (num = OBJ_FLAG_AUTO_CORRECTION) == null) {
            return text;
        }
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new SuggestionSpan(context, locale, new String[0], num.intValue(), null), 0, text.length(), 289);
        return spannableString;
    }

    public static CharSequence getTextWithSuggestionSpan(final Context context, final String pickedWord, final SuggestedWords suggestedWords, final Locale locale) {
        if (TextUtils.isEmpty(pickedWord) || suggestedWords.isEmpty() || suggestedWords.isPrediction() || suggestedWords.isPunctuationSuggestions()) {
            return pickedWord;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < suggestedWords.size() && arrayList.size() < 5; i++) {
            if (!suggestedWords.getInfo(i).isKindOf(8)) {
                String word = suggestedWords.getWord(i);
                if (!TextUtils.equals(pickedWord, word)) {
                    arrayList.add(word.toString());
                }
            }
        }
        SuggestionSpan suggestionSpan = new SuggestionSpan(context, locale, (String[]) arrayList.toArray(new String[arrayList.size()]), 0, null);
        SpannableString spannableString = new SpannableString(pickedWord);
        spannableString.setSpan(suggestionSpan, 0, pickedWord.length(), 33);
        return spannableString;
    }

    @Nullable
    public static Locale findFirstLocaleFromSuggestionSpans(final SuggestionSpan[] suggestionSpans) {
        for (SuggestionSpan suggestionSpan : suggestionSpans) {
            String locale = suggestionSpan.getLocale();
            if (!TextUtils.isEmpty(locale)) {
                return LocaleUtils.constructLocaleFromString(locale);
            }
        }
        return null;
    }
}
