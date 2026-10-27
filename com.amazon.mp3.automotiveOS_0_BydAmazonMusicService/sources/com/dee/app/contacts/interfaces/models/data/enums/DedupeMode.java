package com.dee.app.contacts.interfaces.models.data.enums;

/* JADX INFO: loaded from: classes5.dex */
public enum DedupeMode {
    REMOVE_A2A("RemoveA2AContactDuplicates"),
    REMOVE_CLOUD_ONLY("RemoveCloudOnlyContactDuplicates");

    private final String dedupeMode;

    DedupeMode(String str) {
        this.dedupeMode = str;
    }

    public String getDedupeMode() {
        return this.dedupeMode;
    }
}
