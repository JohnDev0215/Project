package com.amazon.avod.drm.db;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DrmStoredRightsWithId {
    private final DrmStoredRights mDrmStoredRights;
    private final String mOfflineKeyId;

    public DrmStoredRightsWithId(@Nullable DrmStoredRights drmStoredRights, @Nullable String str) {
        this.mDrmStoredRights = drmStoredRights;
        this.mOfflineKeyId = str;
    }

    @Nullable
    public DrmStoredRights getDrmStoredRights() {
        return this.mDrmStoredRights;
    }

    @Nullable
    public String getOfflineKeyId() {
        return this.mOfflineKeyId;
    }
}
