package com.amazon.avod.drm.db;

import com.amazon.avod.db.ADatabaseInstance;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class DrmContentDatabase extends ADatabaseInstance {
    public DrmContentDatabase() {
        super("drm", 3, ADatabaseInstance.Scope.GLOBAL);
        addTable(new DrmContentTable());
        addTable(new DrmOwnerTable());
    }
}
