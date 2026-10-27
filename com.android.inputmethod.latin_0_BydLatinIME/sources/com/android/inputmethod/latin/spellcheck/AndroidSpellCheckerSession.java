package com.android.inputmethod.latin.spellcheck;

import android.content.res.Resources;
import android.os.Binder;
import android.text.TextUtils;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import com.android.inputmethod.compat.TextInfoCompatUtils;
import com.android.inputmethod.latin.NgramContext;
import com.android.inputmethod.latin.spellcheck.SentenceLevelAdapter;
import com.android.inputmethod.latin.utils.SpannableStringUtils;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidSpellCheckerSession extends AndroidWordLevelSpellCheckerSession {
    private static final boolean DBG = false;
    private static final String TAG = "AndroidSpellCheckerSession";
    private final Resources mResources;
    private SentenceLevelAdapter mSentenceLevelAdapter;

    public AndroidSpellCheckerSession(AndroidSpellCheckerService service) {
        super(service);
        this.mResources = service.getResources();
    }

    private SentenceSuggestionsInfo fixWronglyInvalidatedWordWithSingleQuote(TextInfo ti, SentenceSuggestionsInfo ssi) {
        CharSequence charSequence;
        String str;
        CharSequence[] charSequenceArrSplit;
        CharSequence charSequence2;
        String str2;
        int i;
        CharSequence charSequenceOrString = TextInfoCompatUtils.getCharSequenceOrString(ti);
        String string = charSequenceOrString.toString();
        String str3 = AndroidSpellCheckerService.SINGLE_QUOTE;
        if (!string.contains(AndroidSpellCheckerService.SINGLE_QUOTE)) {
            return null;
        }
        int suggestionsCount = ssi.getSuggestionsCount();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int i2 = 0;
        CharSequence charSequence3 = null;
        int i3 = 0;
        while (i3 < suggestionsCount) {
            SuggestionsInfo suggestionsInfoAt = ssi.getSuggestionsInfoAt(i3);
            if ((suggestionsInfoAt.getSuggestionsAttributes() & 1) == 0) {
                charSequence = charSequenceOrString;
                str = str3;
            } else {
                int offsetAt = ssi.getOffsetAt(i3);
                CharSequence charSequenceSubSequence = charSequenceOrString.subSequence(offsetAt, ssi.getLengthAt(i3) + offsetAt);
                NgramContext.WordInfo[] wordInfoArr = new NgramContext.WordInfo[1];
                wordInfoArr[i2] = new NgramContext.WordInfo(charSequence3);
                new NgramContext(wordInfoArr);
                if (charSequenceSubSequence.toString().contains(str3) && (charSequenceArrSplit = SpannableStringUtils.split(charSequenceSubSequence, str3, true)) != null && charSequenceArrSplit.length > 1) {
                    int length = charSequenceArrSplit.length;
                    int i4 = i2;
                    while (i4 < length) {
                        CharSequence charSequence4 = charSequenceArrSplit[i4];
                        if (TextUtils.isEmpty(charSequence4)) {
                            charSequence2 = charSequenceOrString;
                            str2 = str3;
                        } else {
                            charSequence2 = charSequenceOrString;
                            str2 = str3;
                            if (this.mSuggestionsCache.getSuggestionsFromCache(charSequence4.toString()) != null) {
                                int length2 = charSequence4.length();
                                i = length;
                                SuggestionsInfo suggestionsInfo = new SuggestionsInfo(0, EMPTY_STRING_ARRAY);
                                suggestionsInfo.setCookieAndSequence(suggestionsInfoAt.getCookie(), suggestionsInfoAt.getSequence());
                                arrayList.add(Integer.valueOf(offsetAt));
                                arrayList2.add(Integer.valueOf(length2));
                                arrayList3.add(suggestionsInfo);
                            }
                            i4++;
                            charSequenceOrString = charSequence2;
                            str3 = str2;
                            length = i;
                        }
                        i = length;
                        i4++;
                        charSequenceOrString = charSequence2;
                        str3 = str2;
                        length = i;
                    }
                }
                charSequence = charSequenceOrString;
                str = str3;
                charSequence3 = charSequenceSubSequence;
            }
            i3++;
            charSequenceOrString = charSequence;
            str3 = str;
            i2 = 0;
        }
        int size = arrayList.size();
        if (size <= 0) {
            return null;
        }
        int i5 = size + suggestionsCount;
        int[] iArr = new int[i5];
        int[] iArr2 = new int[i5];
        SuggestionsInfo[] suggestionsInfoArr = new SuggestionsInfo[i5];
        int i6 = 0;
        while (i6 < suggestionsCount) {
            iArr[i6] = ssi.getOffsetAt(i6);
            iArr2[i6] = ssi.getLengthAt(i6);
            suggestionsInfoArr[i6] = ssi.getSuggestionsInfoAt(i6);
            i6++;
        }
        while (i6 < i5) {
            int i7 = i6 - suggestionsCount;
            iArr[i6] = ((Integer) arrayList.get(i7)).intValue();
            iArr2[i6] = ((Integer) arrayList2.get(i7)).intValue();
            suggestionsInfoArr[i6] = (SuggestionsInfo) arrayList3.get(i7);
            i6++;
        }
        return new SentenceSuggestionsInfo(suggestionsInfoArr, iArr, iArr2);
    }

    @Override // android.service.textservice.SpellCheckerService.Session
    public SentenceSuggestionsInfo[] onGetSentenceSuggestionsMultiple(TextInfo[] textInfos, int suggestionsLimit) {
        SentenceSuggestionsInfo[] sentenceSuggestionsInfoArrSplitAndSuggest = splitAndSuggest(textInfos, suggestionsLimit);
        if (sentenceSuggestionsInfoArrSplitAndSuggest != null && sentenceSuggestionsInfoArrSplitAndSuggest.length == textInfos.length) {
            for (int i = 0; i < sentenceSuggestionsInfoArrSplitAndSuggest.length; i++) {
                SentenceSuggestionsInfo sentenceSuggestionsInfoFixWronglyInvalidatedWordWithSingleQuote = fixWronglyInvalidatedWordWithSingleQuote(textInfos[i], sentenceSuggestionsInfoArrSplitAndSuggest[i]);
                if (sentenceSuggestionsInfoFixWronglyInvalidatedWordWithSingleQuote != null) {
                    sentenceSuggestionsInfoArrSplitAndSuggest[i] = sentenceSuggestionsInfoFixWronglyInvalidatedWordWithSingleQuote;
                }
            }
        }
        return sentenceSuggestionsInfoArrSplitAndSuggest;
    }

    private SentenceSuggestionsInfo[] splitAndSuggest(TextInfo[] textInfos, int suggestionsLimit) {
        SentenceLevelAdapter sentenceLevelAdapter;
        if (textInfos == null || textInfos.length == 0) {
            return SentenceLevelAdapter.getEmptySentenceSuggestionsInfo();
        }
        synchronized (this) {
            sentenceLevelAdapter = this.mSentenceLevelAdapter;
            if (sentenceLevelAdapter == null) {
                String locale = getLocale();
                if (!TextUtils.isEmpty(locale)) {
                    sentenceLevelAdapter = new SentenceLevelAdapter(this.mResources, new Locale(locale));
                    this.mSentenceLevelAdapter = sentenceLevelAdapter;
                }
            }
        }
        if (sentenceLevelAdapter == null) {
            return SentenceLevelAdapter.getEmptySentenceSuggestionsInfo();
        }
        int length = textInfos.length;
        SentenceSuggestionsInfo[] sentenceSuggestionsInfoArr = new SentenceSuggestionsInfo[length];
        for (int i = 0; i < length; i++) {
            SentenceLevelAdapter.SentenceTextInfoParams splitWords = sentenceLevelAdapter.getSplitWords(textInfos[i]);
            ArrayList<SentenceLevelAdapter.SentenceWordItem> arrayList = splitWords.mItems;
            int size = arrayList.size();
            TextInfo[] textInfoArr = new TextInfo[size];
            for (int i2 = 0; i2 < size; i2++) {
                textInfoArr[i2] = arrayList.get(i2).mTextInfo;
            }
            sentenceSuggestionsInfoArr[i] = SentenceLevelAdapter.reconstructSuggestions(splitWords, onGetSuggestionsMultiple(textInfoArr, suggestionsLimit, true));
        }
        return sentenceSuggestionsInfoArr;
    }

    @Override // android.service.textservice.SpellCheckerService.Session
    public SuggestionsInfo[] onGetSuggestionsMultiple(TextInfo[] textInfos, int suggestionsLimit, boolean sequentialWords) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            int length = textInfos.length;
            SuggestionsInfo[] suggestionsInfoArr = new SuggestionsInfo[length];
            for (int i = 0; i < length; i++) {
                CharSequence charSequence = null;
                if (sequentialWords && i > 0) {
                    CharSequence charSequenceOrString = TextInfoCompatUtils.getCharSequenceOrString(textInfos[i - 1]);
                    if (!TextUtils.isEmpty(charSequenceOrString)) {
                        charSequence = charSequenceOrString;
                    }
                }
                NgramContext ngramContext = new NgramContext(new NgramContext.WordInfo(charSequence));
                TextInfo textInfo = textInfos[i];
                suggestionsInfoArr[i] = onGetSuggestionsInternal(textInfo, ngramContext, suggestionsLimit);
                suggestionsInfoArr[i].setCookieAndSequence(textInfo.getCookie(), textInfo.getSequence());
            }
            return suggestionsInfoArr;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }
}
