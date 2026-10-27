package com.android.inputmethod.latin;

import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.ComposedData;
import com.android.inputmethod.latin.settings.SettingsValuesForSuggestion;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes2.dex */
public final class ReadOnlyBinaryDictionary extends Dictionary {
    private final BinaryDictionary mBinaryDictionary;
    private final ReentrantReadWriteLock mLock;

    public ReadOnlyBinaryDictionary(final String filename, final long offset, final long length, final boolean useFullEditDistance, final Locale locale, final String dictType) {
        super(dictType, locale);
        this.mLock = new ReentrantReadWriteLock();
        this.mBinaryDictionary = new BinaryDictionary(filename, offset, length, useFullEditDistance, locale, dictType, false);
    }

    public boolean isValidDictionary() {
        return this.mBinaryDictionary.isValidDictionary();
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public ArrayList<SuggestedWords.SuggestedWordInfo> getSuggestions(final ComposedData composedData, final NgramContext ngramContext, final long proximityInfoHandle, final SettingsValuesForSuggestion settingsValuesForSuggestion, final int sessionId, final float weightForLocale, final float[] inOutWeightOfLangModelVsSpatialModel) {
        if (!this.mLock.readLock().tryLock()) {
            return null;
        }
        try {
            return this.mBinaryDictionary.getSuggestions(composedData, ngramContext, proximityInfoHandle, settingsValuesForSuggestion, sessionId, weightForLocale, inOutWeightOfLangModelVsSpatialModel);
        } finally {
            this.mLock.readLock().unlock();
        }
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public boolean isInDictionary(final String word) {
        if (!this.mLock.readLock().tryLock()) {
            return false;
        }
        try {
            return this.mBinaryDictionary.isInDictionary(word);
        } finally {
            this.mLock.readLock().unlock();
        }
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public boolean shouldAutoCommit(final SuggestedWords.SuggestedWordInfo candidate) {
        if (!this.mLock.readLock().tryLock()) {
            return false;
        }
        try {
            return this.mBinaryDictionary.shouldAutoCommit(candidate);
        } finally {
            this.mLock.readLock().unlock();
        }
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public int getFrequency(final String word) {
        if (!this.mLock.readLock().tryLock()) {
            return -1;
        }
        try {
            return this.mBinaryDictionary.getFrequency(word);
        } finally {
            this.mLock.readLock().unlock();
        }
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public int getMaxFrequencyOfExactMatches(final String word) {
        if (!this.mLock.readLock().tryLock()) {
            return -1;
        }
        try {
            return this.mBinaryDictionary.getMaxFrequencyOfExactMatches(word);
        } finally {
            this.mLock.readLock().unlock();
        }
    }

    @Override // com.android.inputmethod.latin.Dictionary
    public void close() {
        this.mLock.writeLock().lock();
        try {
            this.mBinaryDictionary.close();
        } finally {
            this.mLock.writeLock().unlock();
        }
    }
}
