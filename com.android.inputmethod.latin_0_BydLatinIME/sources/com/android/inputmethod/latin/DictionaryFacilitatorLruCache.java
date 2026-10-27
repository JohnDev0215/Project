package com.android.inputmethod.latin;

import android.content.Context;
import android.util.Log;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class DictionaryFacilitatorLruCache {
    private static final int MAX_RETRY_COUNT_FOR_WAITING_FOR_LOADING_DICT = 5;
    private static final String TAG = "DictionaryFacilitatorLruCache";
    private static final int WAIT_FOR_LOADING_MAIN_DICT_IN_MILLISECONDS = 1000;
    private final Context mContext;
    private final String mDictionaryNamePrefix;
    private Locale mLocale;
    private boolean mUseContactsDictionary;
    private final Object mLock = new Object();
    private final DictionaryFacilitator mDictionaryFacilitator = DictionaryFacilitatorProvider.getDictionaryFacilitator(true);

    public DictionaryFacilitatorLruCache(final Context context, final String dictionaryNamePrefix) {
        this.mContext = context;
        this.mDictionaryNamePrefix = dictionaryNamePrefix;
    }

    private static void waitForLoadingMainDictionary(final DictionaryFacilitator dictionaryFacilitator) {
        for (int i = 0; i < 5; i++) {
            try {
                dictionaryFacilitator.waitForLoadingMainDictionaries(1000L, TimeUnit.MILLISECONDS);
                return;
            } catch (InterruptedException e) {
                Log.i(TAG, "Interrupted during waiting for loading main dictionary.", e);
                if (i < 4) {
                    Log.i(TAG, "Retry", e);
                } else {
                    Log.w(TAG, "Give up retrying. Retried 5 times.", e);
                }
            }
        }
    }

    private void resetDictionariesForLocaleLocked() {
        Locale locale = this.mLocale;
        if (locale != null) {
            this.mDictionaryFacilitator.resetDictionaries(this.mContext, locale, this.mUseContactsDictionary, false, false, null, this.mDictionaryNamePrefix, null);
        }
    }

    public void setUseContactsDictionary(final boolean useContactsDictionary) {
        synchronized (this.mLock) {
            if (this.mUseContactsDictionary == useContactsDictionary) {
                return;
            }
            this.mUseContactsDictionary = useContactsDictionary;
            resetDictionariesForLocaleLocked();
            waitForLoadingMainDictionary(this.mDictionaryFacilitator);
        }
    }

    public DictionaryFacilitator get(final Locale locale) {
        DictionaryFacilitator dictionaryFacilitator;
        synchronized (this.mLock) {
            if (!this.mDictionaryFacilitator.isForLocale(locale)) {
                this.mLocale = locale;
                resetDictionariesForLocaleLocked();
            }
            waitForLoadingMainDictionary(this.mDictionaryFacilitator);
            dictionaryFacilitator = this.mDictionaryFacilitator;
        }
        return dictionaryFacilitator;
    }

    public void closeDictionaries() {
        synchronized (this.mLock) {
            this.mDictionaryFacilitator.closeDictionaries();
        }
    }
}
