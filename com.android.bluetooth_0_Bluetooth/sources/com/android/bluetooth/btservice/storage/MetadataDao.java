package com.android.bluetooth.btservice.storage;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
interface MetadataDao {
    void delete(String str);

    void deleteAll();

    void insert(Metadata... metadataArr);

    List<Metadata> load();
}
