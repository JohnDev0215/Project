package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.WriterCallback;
import java.io.IOException;
import java.util.Collection;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface DiskStorage {

    public interface Entry {
        String getId();

        long getSize();

        long getTimestamp();
    }

    public interface Inserter {
        boolean cleanUp();

        BinaryResource commit(Object debugInfo) throws IOException;

        void writeData(WriterCallback callback, Object debugInfo) throws IOException;
    }

    void clearAll() throws IOException;

    boolean contains(String resourceId, Object debugInfo) throws IOException;

    Collection<Entry> getEntries() throws IOException;

    @Nullable
    BinaryResource getResource(String resourceId, Object debugInfo) throws IOException;

    Inserter insert(String resourceId, Object debugInfo) throws IOException;

    boolean isExternal();

    void purgeUnexpectedResources();

    long remove(Entry entry) throws IOException;

    long remove(String resourceId) throws IOException;

    boolean touch(String resourceId, Object debugInfo) throws IOException;
}
