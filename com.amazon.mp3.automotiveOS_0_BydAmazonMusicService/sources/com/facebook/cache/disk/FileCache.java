package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.common.disk.DiskTrimmable;
import java.io.IOException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface FileCache extends DiskTrimmable {
    void clearAll();

    @Nullable
    BinaryResource getResource(CacheKey key);

    boolean hasKey(CacheKey key);

    boolean hasKeySync(CacheKey key);

    @Nullable
    BinaryResource insert(CacheKey key, WriterCallback writer) throws IOException;

    boolean probe(CacheKey key);

    void remove(CacheKey key);
}
