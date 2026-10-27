package com.dee.app.contacts.interfaces.models.data.enums;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public enum ContactSourceType {
    WhitelistedContact("whitelistedContact"),
    ManuallyAddedContact("manuallyAddedContact"),
    SyncedContact("syncedContact"),
    VoiceAddedContact("voiceAddedContact"),
    AutoAddedContact("autoAddedContact"),
    BulkImportedContact("bulkImportedContact"),
    SkypeContact("skypeContact"),
    MergedContact("mergedContact"),
    ProfileContact("profileContact"),
    AmazonPhotos("amazonPhotos"),
    AmazonMusic("amazonMusic"),
    UnsetContact("");


    @NonNull
    private final String name;

    ContactSourceType(@NonNull String str) {
        this.name = str;
    }

    @NonNull
    public String getName() {
        return this.name;
    }
}
