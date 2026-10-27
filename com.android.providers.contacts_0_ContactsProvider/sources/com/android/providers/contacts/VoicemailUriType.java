package com.android.providers.contacts;

import com.android.providers.contacts.util.UriType;

/* JADX INFO: loaded from: classes.dex */
enum VoicemailUriType implements UriType {
    NO_MATCH(null),
    VOICEMAILS("voicemail"),
    VOICEMAILS_ID("voicemail/#"),
    STATUS("status"),
    STATUS_ID("status/#");

    private final String path;

    VoicemailUriType(String str) {
        this.path = str;
    }

    @Override // com.android.providers.contacts.util.UriType
    public String path() {
        return this.path;
    }
}
