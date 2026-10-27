package com.android.inputmethod.latin.spellcheck;

import android.content.res.Resources;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import com.android.inputmethod.compat.TextInfoCompatUtils;
import com.android.inputmethod.latin.settings.SpacingAndPunctuations;
import com.android.inputmethod.latin.utils.RunInLocale;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class SentenceLevelAdapter {
    private static final SuggestionsInfo EMPTY_SUGGESTIONS_INFO = new SuggestionsInfo(0, null);
    private final WordIterator mWordIterator;

    private static class EmptySentenceSuggestionsInfosInitializationHolder {
        public static final SentenceSuggestionsInfo[] EMPTY_SENTENCE_SUGGESTIONS_INFOS = new SentenceSuggestionsInfo[0];

        private EmptySentenceSuggestionsInfosInitializationHolder() {
        }
    }

    public static SentenceSuggestionsInfo[] getEmptySentenceSuggestionsInfo() {
        return EmptySentenceSuggestionsInfosInitializationHolder.EMPTY_SENTENCE_SUGGESTIONS_INFOS;
    }

    public static class SentenceWordItem {
        public final int mLength;
        public final int mStart;
        public final TextInfo mTextInfo;

        public SentenceWordItem(TextInfo ti, int start, int end) {
            this.mTextInfo = ti;
            this.mStart = start;
            this.mLength = end - start;
        }
    }

    public static class SentenceTextInfoParams {
        final ArrayList<SentenceWordItem> mItems;
        final TextInfo mOriginalTextInfo;
        final int mSize;

        public SentenceTextInfoParams(TextInfo ti, ArrayList<SentenceWordItem> items) {
            this.mOriginalTextInfo = ti;
            this.mItems = items;
            this.mSize = items.size();
        }
    }

    private static class WordIterator {
        private final SpacingAndPunctuations mSpacingAndPunctuations;

        public WordIterator(final Resources res, final Locale locale) {
            this.mSpacingAndPunctuations = new RunInLocale<SpacingAndPunctuations>() { // from class: com.android.inputmethod.latin.spellcheck.SentenceLevelAdapter.WordIterator.1
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.android.inputmethod.latin.utils.RunInLocale
                public SpacingAndPunctuations job(final Resources r) {
                    return new SpacingAndPunctuations(r);
                }
            }.runInLocale(res, locale);
        }

        public int getEndOfWord(final CharSequence sequence, final int fromIndex) {
            int iCharCount;
            int length = sequence.length();
            int iOffsetByCodePoints = fromIndex < 0 ? 0 : Character.offsetByCodePoints(sequence, fromIndex, 1);
            while (iOffsetByCodePoints < length) {
                int iCodePointAt = Character.codePointAt(sequence, iOffsetByCodePoints);
                if (this.mSpacingAndPunctuations.isWordSeparator(iCodePointAt) && (46 != iCodePointAt || ((iCharCount = Character.charCount(46) + iOffsetByCodePoints) < length && this.mSpacingAndPunctuations.isWordSeparator(Character.codePointAt(sequence, iCharCount))))) {
                    return iOffsetByCodePoints;
                }
                iOffsetByCodePoints += Character.charCount(iCodePointAt);
            }
            return iOffsetByCodePoints;
        }

        public int getBeginningOfNextWord(final CharSequence sequence, final int fromIndex) {
            int length = sequence.length();
            if (fromIndex >= length) {
                return -1;
            }
            int iOffsetByCodePoints = fromIndex < 0 ? 0 : Character.offsetByCodePoints(sequence, fromIndex, 1);
            while (iOffsetByCodePoints < length) {
                int iCodePointAt = Character.codePointAt(sequence, iOffsetByCodePoints);
                if (!this.mSpacingAndPunctuations.isWordSeparator(iCodePointAt)) {
                    return iOffsetByCodePoints;
                }
                iOffsetByCodePoints += Character.charCount(iCodePointAt);
            }
            return -1;
        }
    }

    public SentenceLevelAdapter(final Resources res, final Locale locale) {
        this.mWordIterator = new WordIterator(res, locale);
    }

    public SentenceTextInfoParams getSplitWords(TextInfo originalTextInfo) {
        WordIterator wordIterator = this.mWordIterator;
        CharSequence charSequenceOrString = TextInfoCompatUtils.getCharSequenceOrString(originalTextInfo);
        int cookie = originalTextInfo.getCookie();
        int length = charSequenceOrString.length();
        ArrayList arrayList = new ArrayList();
        int beginningOfNextWord = wordIterator.getBeginningOfNextWord(charSequenceOrString, -1);
        int endOfWord = wordIterator.getEndOfWord(charSequenceOrString, beginningOfNextWord);
        while (beginningOfNextWord <= length && endOfWord != -1 && beginningOfNextWord != -1) {
            if (endOfWord >= -1 && endOfWord > beginningOfNextWord) {
                arrayList.add(new SentenceWordItem(TextInfoCompatUtils.newInstance(charSequenceOrString, beginningOfNextWord, endOfWord, cookie, charSequenceOrString.subSequence(beginningOfNextWord, endOfWord).hashCode()), beginningOfNextWord, endOfWord));
            }
            beginningOfNextWord = wordIterator.getBeginningOfNextWord(charSequenceOrString, endOfWord);
            if (beginningOfNextWord == -1) {
                break;
            }
            endOfWord = wordIterator.getEndOfWord(charSequenceOrString, beginningOfNextWord);
        }
        return new SentenceTextInfoParams(originalTextInfo, arrayList);
    }

    public static SentenceSuggestionsInfo reconstructSuggestions(SentenceTextInfoParams originalTextInfoParams, SuggestionsInfo[] results) {
        SuggestionsInfo suggestionsInfo;
        if (results == null || results.length == 0 || originalTextInfoParams == null) {
            return null;
        }
        int cookie = originalTextInfoParams.mOriginalTextInfo.getCookie();
        int sequence = originalTextInfoParams.mOriginalTextInfo.getSequence();
        int i = originalTextInfoParams.mSize;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        SuggestionsInfo[] suggestionsInfoArr = new SuggestionsInfo[i];
        for (int i2 = 0; i2 < i; i2++) {
            SentenceWordItem sentenceWordItem = originalTextInfoParams.mItems.get(i2);
            int i3 = 0;
            while (true) {
                if (i3 >= results.length) {
                    suggestionsInfo = null;
                    break;
                }
                suggestionsInfo = results[i3];
                if (suggestionsInfo != null && suggestionsInfo.getSequence() == sentenceWordItem.mTextInfo.getSequence()) {
                    suggestionsInfo.setCookieAndSequence(cookie, sequence);
                    break;
                }
                i3++;
            }
            iArr[i2] = sentenceWordItem.mStart;
            iArr2[i2] = sentenceWordItem.mLength;
            if (suggestionsInfo == null) {
                suggestionsInfo = EMPTY_SUGGESTIONS_INFO;
            }
            suggestionsInfoArr[i2] = suggestionsInfo;
        }
        return new SentenceSuggestionsInfo(suggestionsInfoArr, iArr, iArr2);
    }
}
