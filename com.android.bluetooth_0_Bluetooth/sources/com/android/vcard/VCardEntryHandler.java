package com.android.vcard;

/* JADX INFO: loaded from: classes.dex */
public interface VCardEntryHandler {
    void onEnd();

    void onEntryCreated(VCardEntry vCardEntry);

    void onStart();
}
