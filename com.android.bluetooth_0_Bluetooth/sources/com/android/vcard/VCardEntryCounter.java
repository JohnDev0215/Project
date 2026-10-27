package com.android.vcard;

/* JADX INFO: loaded from: classes.dex */
public class VCardEntryCounter implements VCardInterpreter {
    private int mCount;

    public int getCount() {
        return this.mCount;
    }

    @Override // com.android.vcard.VCardInterpreter
    public void onVCardStarted() {
    }

    @Override // com.android.vcard.VCardInterpreter
    public void onVCardEnded() {
    }

    @Override // com.android.vcard.VCardInterpreter
    public void onEntryStarted() {
    }

    @Override // com.android.vcard.VCardInterpreter
    public void onEntryEnded() {
        this.mCount++;
    }

    @Override // com.android.vcard.VCardInterpreter
    public void onPropertyCreated(VCardProperty property) {
    }
}
