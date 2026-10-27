package com.android.inputmethod.latin;

import android.util.Log;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.ComposedData;
import com.android.inputmethod.latin.settings.SettingsValuesForSuggestion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class DictionaryCollection extends Dictionary {
    private final String TAG;
    protected final CopyOnWriteArrayList<Dictionary> mDictionaries;

    public DictionaryCollection(final String dictType, final Locale locale) {
        super(dictType, locale);
        this.TAG = DictionaryCollection.class.getSimpleName();
        this.mDictionaries = new CopyOnWriteArrayList<>();
    }

    public DictionaryCollection(final String dictType, final Locale locale, final Dictionary... dictionaries) {
        super(dictType, locale);
        this.TAG = DictionaryCollection.class.getSimpleName();
        if (dictionaries == null) {
            this.mDictionaries = new CopyOnWriteArrayList<>();
            return;
        }
        CopyOnWriteArrayList<Dictionary> copyOnWriteArrayList = new CopyOnWriteArrayList<>(dictionaries);
        this.mDictionaries = copyOnWriteArrayList;
        copyOnWriteArrayList.removeAll(Collections.singleton(null));
    }

    public DictionaryCollection(final String dictType, final Locale locale, final Collection<Dictionary> dictionaries) {
        super(dictType, locale);
        this.TAG = DictionaryCollection.class.getSimpleName();
        CopyOnWriteArrayList<Dictionary> copyOnWriteArrayList = new CopyOnWriteArrayList<>(dictionaries);
        this.mDictionaries = copyOnWriteArrayList;
        copyOnWriteArrayList.removeAll(Collections.singleton(null));
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public ArrayList<SuggestedWords.SuggestedWordInfo> getSuggestions(final ComposedData composedData, final NgramContext ngramContext, final long proximityInfoHandle, final SettingsValuesForSuggestion settingsValuesForSuggestion, final int sessionId, final float weightForLocale, final float[] inOutWeightOfLangModelVsSpatialModel) {
        CopyOnWriteArrayList<Dictionary> copyOnWriteArrayList = this.mDictionaries;
        if (copyOnWriteArrayList.isEmpty()) {
            return null;
        }
        ArrayList<SuggestedWords.SuggestedWordInfo> suggestions = copyOnWriteArrayList.get(0).getSuggestions(composedData, ngramContext, proximityInfoHandle, settingsValuesForSuggestion, sessionId, weightForLocale, inOutWeightOfLangModelVsSpatialModel);
        if (suggestions == null) {
            suggestions = new ArrayList<>();
        }
        int size = copyOnWriteArrayList.size();
        for (int i = 1; i < size; i++) {
            ArrayList<SuggestedWords.SuggestedWordInfo> suggestions2 = copyOnWriteArrayList.get(i).getSuggestions(composedData, ngramContext, proximityInfoHandle, settingsValuesForSuggestion, sessionId, weightForLocale, inOutWeightOfLangModelVsSpatialModel);
            if (suggestions2 != null) {
                suggestions.addAll(suggestions2);
            }
        }
        return suggestions;
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public boolean isInDictionary(final String word) {
        for (int size = this.mDictionaries.size() - 1; size >= 0; size--) {
            if (this.mDictionaries.get(size).isInDictionary(word)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public int getFrequency(final String word) {
        int iMax = -1;
        for (int size = this.mDictionaries.size() - 1; size >= 0; size--) {
            iMax = Math.max(this.mDictionaries.get(size).getFrequency(word), iMax);
        }
        return iMax;
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public int getMaxFrequencyOfExactMatches(final String word) {
        int iMax = -1;
        for (int size = this.mDictionaries.size() - 1; size >= 0; size--) {
            iMax = Math.max(this.mDictionaries.get(size).getMaxFrequencyOfExactMatches(word), iMax);
        }
        return iMax;
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public boolean isInitialized() {
        return !this.mDictionaries.isEmpty();
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public void close() {
        Iterator<Dictionary> it = this.mDictionaries.iterator();
        while (it.hasNext()) {
            it.next().close();
        }
    }

    public void addDictionary(final Dictionary newDict) {
        if (newDict == null) {
            return;
        }
        if (this.mDictionaries.contains(newDict)) {
            Log.w(this.TAG, "This collection already contains this dictionary: " + newDict);
        }
        this.mDictionaries.add(newDict);
    }

    public void removeDictionary(final Dictionary dict) {
        if (this.mDictionaries.contains(dict)) {
            this.mDictionaries.remove(dict);
        } else {
            Log.w(this.TAG, "This collection does not contain this dictionary: " + dict);
        }
    }
}
