package com.android.inputmethod.latin.settings;

import android.content.res.Resources;
import com.android.inputmethod.keyboard.internal.MoreKeySpec;
import com.android.inputmethod.latin.PunctuationSuggestions;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.StringUtils;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class SpacingAndPunctuations {
    private final int mAbbreviationMarker;
    public final boolean mCurrentLanguageHasSpaces;
    private final int mSentenceSeparator;
    public final String mSentenceSeparatorAndSpace;
    private final int[] mSortedSentenceTerminators;
    private final int[] mSortedSymbolsClusteringTogether;
    private final int[] mSortedSymbolsFollowedBySpace;
    private final int[] mSortedSymbolsPrecededBySpace;
    private final int[] mSortedWordConnectors;
    public final int[] mSortedWordSeparators;
    public final PunctuationSuggestions mSuggestPuncList;
    public final boolean mUsesAmericanTypography;
    public final boolean mUsesGermanRules;

    public SpacingAndPunctuations(final Resources res) {
        this.mSortedSymbolsPrecededBySpace = StringUtils.toSortedCodePointArray(res.getString(R.string.symbols_preceded_by_space));
        this.mSortedSymbolsFollowedBySpace = StringUtils.toSortedCodePointArray(res.getString(R.string.symbols_followed_by_space));
        this.mSortedSymbolsClusteringTogether = StringUtils.toSortedCodePointArray(res.getString(R.string.symbols_clustering_together));
        this.mSortedWordConnectors = StringUtils.toSortedCodePointArray(res.getString(R.string.symbols_word_connectors));
        this.mSortedWordSeparators = StringUtils.toSortedCodePointArray(res.getString(R.string.symbols_word_separators));
        this.mSortedSentenceTerminators = StringUtils.toSortedCodePointArray(res.getString(R.string.symbols_sentence_terminators));
        int integer = res.getInteger(R.integer.sentence_separator);
        this.mSentenceSeparator = integer;
        this.mAbbreviationMarker = res.getInteger(R.integer.abbreviation_marker);
        this.mSentenceSeparatorAndSpace = new String(new int[]{integer, 32}, 0, 2);
        this.mCurrentLanguageHasSpaces = res.getBoolean(R.bool.current_language_has_spaces);
        Locale locale = res.getConfiguration().locale;
        this.mUsesAmericanTypography = Locale.ENGLISH.getLanguage().equals(locale.getLanguage());
        this.mUsesGermanRules = Locale.GERMAN.getLanguage().equals(locale.getLanguage());
        this.mSuggestPuncList = PunctuationSuggestions.newPunctuationSuggestions(MoreKeySpec.splitKeySpecs(res.getString(R.string.suggested_punctuations)));
    }

    public SpacingAndPunctuations(final SpacingAndPunctuations model, final int[] overrideSortedWordSeparators) {
        this.mSortedSymbolsPrecededBySpace = model.mSortedSymbolsPrecededBySpace;
        this.mSortedSymbolsFollowedBySpace = model.mSortedSymbolsFollowedBySpace;
        this.mSortedSymbolsClusteringTogether = model.mSortedSymbolsClusteringTogether;
        this.mSortedWordConnectors = model.mSortedWordConnectors;
        this.mSortedWordSeparators = overrideSortedWordSeparators;
        this.mSortedSentenceTerminators = model.mSortedSentenceTerminators;
        this.mSuggestPuncList = model.mSuggestPuncList;
        this.mSentenceSeparator = model.mSentenceSeparator;
        this.mAbbreviationMarker = model.mAbbreviationMarker;
        this.mSentenceSeparatorAndSpace = model.mSentenceSeparatorAndSpace;
        this.mCurrentLanguageHasSpaces = model.mCurrentLanguageHasSpaces;
        this.mUsesAmericanTypography = model.mUsesAmericanTypography;
        this.mUsesGermanRules = model.mUsesGermanRules;
    }

    public boolean isWordSeparator(final int code) {
        return Arrays.binarySearch(this.mSortedWordSeparators, code) >= 0;
    }

    public boolean isWordConnector(final int code) {
        return Arrays.binarySearch(this.mSortedWordConnectors, code) >= 0;
    }

    public boolean isWordCodePoint(final int code) {
        return Character.isLetter(code) || isWordConnector(code);
    }

    public boolean isUsuallyPrecededBySpace(final int code) {
        return Arrays.binarySearch(this.mSortedSymbolsPrecededBySpace, code) >= 0;
    }

    public boolean isUsuallyFollowedBySpace(final int code) {
        return Arrays.binarySearch(this.mSortedSymbolsFollowedBySpace, code) >= 0;
    }

    public boolean isClusteringSymbol(final int code) {
        return Arrays.binarySearch(this.mSortedSymbolsClusteringTogether, code) >= 0;
    }

    public boolean isSentenceTerminator(final int code) {
        return Arrays.binarySearch(this.mSortedSentenceTerminators, code) >= 0;
    }

    public boolean isAbbreviationMarker(final int code) {
        return code == this.mAbbreviationMarker;
    }

    public boolean isSentenceSeparator(final int code) {
        return code == this.mSentenceSeparator;
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();
        sb.append("mSortedSymbolsPrecededBySpace = ");
        sb.append("" + Arrays.toString(this.mSortedSymbolsPrecededBySpace));
        sb.append("\n   mSortedSymbolsFollowedBySpace = ");
        sb.append("" + Arrays.toString(this.mSortedSymbolsFollowedBySpace));
        sb.append("\n   mSortedWordConnectors = ");
        sb.append("" + Arrays.toString(this.mSortedWordConnectors));
        sb.append("\n   mSortedWordSeparators = ");
        sb.append("" + Arrays.toString(this.mSortedWordSeparators));
        sb.append("\n   mSuggestPuncList = ");
        sb.append("" + this.mSuggestPuncList);
        sb.append("\n   mSentenceSeparator = ");
        sb.append("" + this.mSentenceSeparator);
        sb.append("\n   mSentenceSeparatorAndSpace = ");
        sb.append("" + this.mSentenceSeparatorAndSpace);
        sb.append("\n   mCurrentLanguageHasSpaces = ");
        sb.append("" + this.mCurrentLanguageHasSpaces);
        sb.append("\n   mUsesAmericanTypography = ");
        sb.append("" + this.mUsesAmericanTypography);
        sb.append("\n   mUsesGermanRules = ");
        sb.append("" + this.mUsesGermanRules);
        return sb.toString();
    }
}
