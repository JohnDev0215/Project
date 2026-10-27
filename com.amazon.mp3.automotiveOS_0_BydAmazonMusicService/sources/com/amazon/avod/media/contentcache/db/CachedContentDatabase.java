package com.amazon.avod.media.contentcache.db;

import com.amazon.avod.db.ADatabaseInstance;

/* JADX INFO: loaded from: classes2.dex */
public class CachedContentDatabase extends ADatabaseInstance {
    public CachedContentDatabase() {
        super("dbcachedcontent", 22, ADatabaseInstance.Scope.GLOBAL);
        addTable(new CachedContentTable());
    }
}
