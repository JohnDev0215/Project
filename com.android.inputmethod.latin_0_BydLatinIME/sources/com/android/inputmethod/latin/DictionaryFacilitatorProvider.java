package com.android.inputmethod.latin;

/* JADX INFO: loaded from: classes2.dex */
public class DictionaryFacilitatorProvider {
    public static DictionaryFacilitator getDictionaryFacilitator(boolean isNeededForSpellChecking) {
        return new DictionaryFacilitatorImpl();
    }
}
