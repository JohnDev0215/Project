package com.amazon.weblab.mobile.repository;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
interface IStorage {
    boolean hasBackup();

    StorageEntity readBackup() throws IOException, RuntimeException;

    void writeBackup(StorageEntity storageEntity) throws IOException, RuntimeException;
}
