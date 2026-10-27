package com.engine.tbox.item;

import com.engine.tbox.data.TboxStaticInfo;

/* JADX INFO: loaded from: classes.dex */
public class TBoxCache {
    private final TboxStaticInfo mDefaultStaticInfo = new TboxStaticInfo();
    private DataCache<TboxStaticInfo> mTboxInfoCache = new DataCache<>();
    private DataCache<String> mVirtualVinCache = new DataCache<>();

    void updateTBoxStaticInfo(TboxStaticInfo info) {
        this.mTboxInfoCache.cache(info);
    }

    boolean isCachedTboxStaticInfo() {
        return this.mTboxInfoCache.isCached();
    }

    TboxStaticInfo getTBoxStaticInfo() {
        return this.mTboxInfoCache.getValue();
    }

    TboxStaticInfo getDefaultTBoxStaticInfo() {
        return this.mDefaultStaticInfo;
    }

    void updateVirtualVin(String vin) {
        this.mVirtualVinCache.cache(vin);
    }

    String getVirtualVin() {
        return this.mVirtualVinCache.getValue();
    }

    void clearVirtualVin() {
        this.mVirtualVinCache.clear();
    }

    void clear() {
        this.mTboxInfoCache.clear();
        this.mVirtualVinCache.clear();
    }
}
