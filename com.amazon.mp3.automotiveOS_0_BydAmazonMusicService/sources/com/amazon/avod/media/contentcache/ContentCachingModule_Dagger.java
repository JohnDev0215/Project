package com.amazon.avod.media.contentcache;

import com.amazon.avod.media.contentcache.internal.PrioritizingVideoCacheManager;

/* JADX INFO: loaded from: classes2.dex */
public class ContentCachingModule_Dagger {
    VideoCacheManager provideCacheManager(PrioritizingVideoCacheManager prioritizingVideoCacheManager) {
        return prioritizingVideoCacheManager;
    }
}
