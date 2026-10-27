package com.android.inputmethod.latin;

import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.ComposedData;
import com.android.inputmethod.latin.settings.SettingsValuesForSuggestion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Dictionary {
    public static final int NOT_A_PROBABILITY = -1;
    public static final float NOT_A_WEIGHT_OF_LANG_MODEL_VS_SPATIAL_MODEL = -1.0f;
    public static final String TYPE_MAIN = "main";
    public final String mDictType;
    public final Locale mLocale;
    public static final String TYPE_USER_TYPED = "user_typed";
    public static final PhonyDictionary DICTIONARY_USER_TYPED = new PhonyDictionary(TYPE_USER_TYPED);
    public static final String TYPE_USER_SHORTCUT = "user_shortcut";
    public static final PhonyDictionary DICTIONARY_USER_SHORTCUT = new PhonyDictionary(TYPE_USER_SHORTCUT);
    public static final String TYPE_APPLICATION_DEFINED = "application_defined";
    public static final PhonyDictionary DICTIONARY_APPLICATION_DEFINED = new PhonyDictionary(TYPE_APPLICATION_DEFINED);
    public static final String TYPE_HARDCODED = "hardcoded";
    public static final PhonyDictionary DICTIONARY_HARDCODED = new PhonyDictionary(TYPE_HARDCODED);
    public static final String TYPE_RESUMED = "resumed";
    public static final PhonyDictionary DICTIONARY_RESUMED = new PhonyDictionary(TYPE_RESUMED);
    public static final String TYPE_USER = "user";
    public static final String TYPE_CONTACTS = "contacts";
    public static final String TYPE_USER_HISTORY = "history";
    private static final HashSet<String> sUserSpecificDictionaryTypes = new HashSet<>(Arrays.asList(TYPE_USER_TYPED, TYPE_USER, TYPE_CONTACTS, TYPE_USER_HISTORY));

    public void close() {
    }

    public int getFrequency(final String word) {
        return -1;
    }

    public int getMaxFrequencyOfExactMatches(final String word) {
        return -1;
    }

    public abstract ArrayList<SuggestedWords.SuggestedWordInfo> getSuggestions(final ComposedData composedData, final NgramContext ngramContext, final long proximityInfoHandle, final SettingsValuesForSuggestion settingsValuesForSuggestion, final int sessionId, final float weightForLocale, final float[] inOutWeightOfLangModelVsSpatialModel);

    public abstract boolean isInDictionary(final String word);

    public boolean isInitialized() {
        return true;
    }

    public boolean shouldAutoCommit(final SuggestedWords.SuggestedWordInfo candidate) {
        return false;
    }

    public Dictionary(final String dictType, final Locale locale) {
        this.mDictType = dictType;
        this.mLocale = locale;
    }

    public boolean isValidWord(final String word) {
        return isInDictionary(word);
    }

    protected boolean same(final char[] word, final int length, final String typedWord) {
        if (typedWord.length() != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (word[i] != typedWord.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isUserSpecific() {
        return sUserSpecificDictionaryTypes.contains(this.mDictType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class PhonyDictionary extends Dictionary {
        @Override // com.android.inputmethod.latin.Dictionary
        public ArrayList<SuggestedWords.SuggestedWordInfo> getSuggestions(final ComposedData composedData, final NgramContext ngramContext, final long proximityInfoHandle, final SettingsValuesForSuggestion settingsValuesForSuggestion, final int sessionId, final float weightForLocale, final float[] inOutWeightOfLangModelVsSpatialModel) {
            return null;
        }

        @Override // com.android.inputmethod.latin.Dictionary
        public boolean isInDictionary(String word) {
            return false;
        }

        PhonyDictionary(final String type) {
            super(type, null);
        }
    }
}
