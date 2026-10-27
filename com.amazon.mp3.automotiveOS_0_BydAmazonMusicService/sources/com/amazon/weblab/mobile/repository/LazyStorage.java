package com.amazon.weblab.mobile.repository;

import com.amazon.weblab.mobile.model.SessionInfo;
import java.io.IOException;
import org.json.JSONException;

/* JADX INFO: loaded from: classes5.dex */
public class LazyStorage extends FileStorage {
    private LazyParser mParser;

    @Override // com.amazon.weblab.mobile.repository.FileStorage, com.amazon.weblab.mobile.repository.IStorage
    public /* bridge */ /* synthetic */ boolean hasBackup() {
        return super.hasBackup();
    }

    @Override // com.amazon.weblab.mobile.repository.FileStorage, com.amazon.weblab.mobile.repository.IStorage
    public /* bridge */ /* synthetic */ void writeBackup(StorageEntity storageEntity) throws Throwable {
        super.writeBackup(storageEntity);
    }

    LazyStorage(String str, String str2) {
        super(str, str2);
    }

    @Override // com.amazon.weblab.mobile.repository.FileStorage, com.amazon.weblab.mobile.repository.IStorage
    public StorageEntity readBackup() throws Throwable {
        if (!hasBackup()) {
            throw new IOException();
        }
        try {
            LazyParser lazyParser = new LazyParser(this.mWeblabClientIdentifier);
            this.mParser = lazyParser;
            lazyParser.parse(this.mFile);
            return new StorageEntity(this.mParser.getAppVersion(), new SessionInfo(this.mParser.getSessionId(), this.mParser.getMarketplaceId()), this.mParser.getTreatmentAssignments());
        } catch (JSONException e) {
            throw new RuntimeException("Stored data is in an unexpected format or it is corrupted", e);
        }
    }
}
