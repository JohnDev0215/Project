package com.android.inputmethod.latin;

import com.android.inputmethod.keyboard.internal.KeySpecParser;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class PunctuationSuggestions extends SuggestedWords {
    @Override // com.android.inputmethod.latin.SuggestedWords
    public boolean isPunctuationSuggestions() {
        return true;
    }

    private PunctuationSuggestions(final ArrayList<SuggestedWords.SuggestedWordInfo> punctuationsList) {
        super(punctuationsList, null, null, false, false, false, 0, -1);
    }

    public static PunctuationSuggestions newPunctuationSuggestions(@Nullable final String[] punctuationSpecs) {
        if (punctuationSpecs == null || punctuationSpecs.length == 0) {
            return new PunctuationSuggestions(new ArrayList(0));
        }
        ArrayList arrayList = new ArrayList(punctuationSpecs.length);
        for (String str : punctuationSpecs) {
            arrayList.add(newHardCodedWordInfo(str));
        }
        return new PunctuationSuggestions(arrayList);
    }

    @Override // com.android.inputmethod.latin.SuggestedWords
    public String getWord(final int index) {
        String word = super.getWord(index);
        int code = KeySpecParser.getCode(word);
        if (code == -4) {
            return KeySpecParser.getOutputText(word);
        }
        return StringUtils.newSingleCodePointString(code);
    }

    @Override // com.android.inputmethod.latin.SuggestedWords
    public String getLabel(final int index) {
        return KeySpecParser.getLabel(super.getWord(index));
    }

    @Override // com.android.inputmethod.latin.SuggestedWords
    public SuggestedWords.SuggestedWordInfo getInfo(final int index) {
        return newHardCodedWordInfo(getWord(index));
    }

    @Override // com.android.inputmethod.latin.SuggestedWords
    public String toString() {
        return "PunctuationSuggestions:  words=" + Arrays.toString(this.mSuggestedWordInfoList.toArray());
    }

    private static SuggestedWords.SuggestedWordInfo newHardCodedWordInfo(final String keySpec) {
        return new SuggestedWords.SuggestedWordInfo(keySpec, "", Integer.MAX_VALUE, 5, Dictionary.DICTIONARY_HARDCODED, -1, -1);
    }
}
