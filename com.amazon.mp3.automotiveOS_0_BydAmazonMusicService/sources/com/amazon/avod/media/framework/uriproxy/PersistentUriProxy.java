package com.amazon.avod.media.framework.uriproxy;

import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.PriorityTier;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.storage.DiskStorage;
import com.amazon.avod.util.DLog;
import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PersistentUriProxy extends UriProxy {
    private static final Comparator<File> COMPARE_BY_LAST_MODIFIED_TIME = new Comparator<File>() { // from class: com.amazon.avod.media.framework.uriproxy.PersistentUriProxy.1
        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            return Longs.compare(file.lastModified(), file2.lastModified());
        }
    };
    private final long mMaxSizeBytes;

    public PersistentUriProxy(DownloadService downloadService, FileSystem fileSystem, ExecutorService executorService, String str, long j, long j2, PriorityTier priorityTier) {
        this(downloadService, fileSystem, executorService, str, new DiskStorage(), j, j2, priorityTier);
    }

    PersistentUriProxy(DownloadService downloadService, FileSystem fileSystem, ExecutorService executorService, String str, DiskStorage diskStorage, long j, long j2, PriorityTier priorityTier) {
        super(downloadService, fileSystem, executorService, str, diskStorage, j, priorityTier);
        this.mMaxSizeBytes = j2;
    }

    @Override // com.amazon.avod.media.framework.uriproxy.UriProxy
    @Nullable
    public URI lookup(EnhancedURI enhancedURI) {
        if (enhancedURI == null) {
            return null;
        }
        URI uriTranslateToLocalUri = translateToLocalUri(enhancedURI);
        if (!this.mDownloadedItems.containsThenUpdates(uriTranslateToLocalUri)) {
            return null;
        }
        this.mExecutorService.execute(new SetLastModifiedTimeTask(uriTranslateToLocalUri.getPath()));
        return uriTranslateToLocalUri;
    }

    public void sync() {
        this.mExecutorService.execute(new SyncTask());
    }

    @Override // com.amazon.avod.media.framework.uriproxy.UriProxy
    public void cleanup() {
        if (this.mDownloadedItems.getTotalSizeInBytes() > this.mMaxSizeBytes) {
            this.mExecutorService.execute(new TrimTask());
        }
    }

    class SetLastModifiedTimeTask implements Runnable {
        private final String mFilePath;

        public SetLastModifiedTimeTask(String str) {
            this.mFilePath = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            PersistentUriProxy.this.mStorage.touch(this.mFilePath);
        }
    }

    class SyncTask implements Runnable {
        SyncTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PersistentUriProxy persistentUriProxy = PersistentUriProxy.this;
            if (persistentUriProxy.mStorage.contains(persistentUriProxy.mCacheDirectory)) {
                PersistentUriProxy persistentUriProxy2 = PersistentUriProxy.this;
                List<File> childrenOf = persistentUriProxy2.mStorage.getChildrenOf(persistentUriProxy2.mCacheDirectory);
                ArrayList arrayListNewArrayList = Lists.newArrayList();
                boolean z = false;
                synchronized (PersistentUriProxy.this.mSetsLock) {
                    Iterator<File> it = childrenOf.iterator();
                    while (it.hasNext()) {
                        URI uri = it.next().toURI();
                        arrayListNewArrayList.add(uri);
                        if (!PersistentUriProxy.this.mDownloadedItems.contains(uri)) {
                            z = true;
                        }
                    }
                    if (z) {
                        Collections.sort(childrenOf, PersistentUriProxy.COMPARE_BY_LAST_MODIFIED_TIME);
                        PersistentUriProxy.this.mDownloadedItems.clear();
                        for (File file : childrenOf) {
                            PersistentUriProxy.this.mDownloadedItems.add(file.toURI(), file.length());
                        }
                    } else if (PersistentUriProxy.this.mDownloadedItems.size() > arrayListNewArrayList.size()) {
                        PersistentUriProxy.this.mDownloadedItems.trimIfNotIn(arrayListNewArrayList);
                    }
                }
                return;
            }
            synchronized (PersistentUriProxy.this.mSetsLock) {
                PersistentUriProxy.this.mDownloadedItems.clear();
            }
        }
    }

    class TrimTask implements Runnable {
        TrimTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PersistentUriProxy.this.mDownloadedItems.getTotalSizeInBytes() < PersistentUriProxy.this.mMaxSizeBytes) {
                return;
            }
            synchronized (PersistentUriProxy.this.mSetsLock) {
                PersistentUriProxy persistentUriProxy = PersistentUriProxy.this;
                List<URI> listTrimTo = persistentUriProxy.mDownloadedItems.trimTo(persistentUriProxy.mMaxSizeBytes);
                for (int size = listTrimTo.size() - 1; size >= 0; size--) {
                    try {
                        DLog.logf("Persistent ad cache trimmed: %s", listTrimTo.get(size));
                        PersistentUriProxy.this.mStorage.delete(listTrimTo.get(size).getPath());
                    } catch (IOException unused) {
                        DLog.warnf("Failed to delete cached file: %s", listTrimTo.get(size));
                    }
                }
            }
        }
    }
}
