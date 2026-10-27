package com.ecarx.xui.adaptapi.device.impl;

import com.ecarx.xui.adaptapi.device.storage.IStorage;
import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class StorageImpl implements IStorage {
    private static final String TAG = "StorageImpl";
    long allSize;

    @Override // com.ecarx.xui.adaptapi.device.storage.IStorage
    public void releaseStorageWatcher(IStorage.IStorageWatcher iStorageWatcher) {
    }

    @Override // com.ecarx.xui.adaptapi.device.storage.IStorage
    public void startStorageWatcher(int[] iArr, IStorage.IStorageWatcher iStorageWatcher) {
    }

    public StorageImpl() {
        LogTool.d(TAG, "StorageImpl create");
    }

    @Override // com.ecarx.xui.adaptapi.device.storage.IStorage
    public long getSize(int i) {
        this.allSize = 33554432L;
        return 33554432L;
    }
}
