package com.amazon.avod.media.framework.uriproxy;

import com.amazon.avod.media.downloadservice.BasicDownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadListener;
import com.amazon.avod.media.downloadservice.DownloadListenerSet;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadRequestPriority;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.downloadservice.PriorityTier;
import com.amazon.avod.media.downloadservice.SaveCallback;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.error.GenericMediaException;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.storage.DiskStorage;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class UriProxy {
    protected final String mCacheDirectory;
    protected final DownloadService mDownloadService;
    protected final long mDownloadTimeoutInNanos;
    protected final UriCacheMap mDownloadedItems;
    protected final ExecutorService mExecutorService;
    private final FileSystem mFileSystem;
    protected final HashMap<EnhancedURI, DownloadRequest> mPendingItems;
    protected final PriorityTier mPriorityTier;
    protected final Object mSetsLock;
    protected final DiskStorage mStorage;

    public UriProxy(DownloadService downloadService, FileSystem fileSystem, ExecutorService executorService, String str, long j, PriorityTier priorityTier) {
        this(downloadService, fileSystem, executorService, str, new DiskStorage(), j, priorityTier);
    }

    UriProxy(@Nonnull DownloadService downloadService, @Nonnull FileSystem fileSystem, @Nonnull ExecutorService executorService, @Nonnull String str, @Nonnull DiskStorage diskStorage, long j, @Nonnull PriorityTier priorityTier) {
        Preconditions.checkNotNull(str);
        this.mDownloadService = (DownloadService) Preconditions.checkNotNull(downloadService);
        FileSystem fileSystem2 = (FileSystem) Preconditions.checkNotNull(fileSystem);
        this.mFileSystem = fileSystem2;
        this.mExecutorService = (ExecutorService) Preconditions.checkNotNull(executorService);
        this.mStorage = (DiskStorage) Preconditions.checkNotNull(diskStorage);
        this.mDownloadTimeoutInNanos = j;
        this.mPriorityTier = (PriorityTier) Preconditions.checkNotNull(priorityTier);
        this.mSetsLock = new Object();
        this.mDownloadedItems = new UriCacheMap();
        this.mPendingItems = Maps.newHashMap();
        this.mCacheDirectory = fileSystem2.getFilesDir().getAbsolutePath() + File.separator + str;
    }

    public void load(EnhancedURI enhancedURI, long j, int i, boolean z, PriorityTier priorityTier, @Nullable DownloadListener downloadListener) {
        Preconditions.checkNotNull(enhancedURI);
        synchronized (this.mSetsLock) {
            URI uriTranslateToLocalUri = translateToLocalUri(enhancedURI);
            if (!this.mPendingItems.containsKey(enhancedURI) && !this.mDownloadedItems.contains(uriTranslateToLocalUri)) {
                DownloadRequestPriority downloadRequestPriority = new DownloadRequestPriority(priorityTier, i, j);
                DownloadListenerSet downloadListenerSet = new DownloadListenerSet();
                downloadListenerSet.addListener(new UriProxyDownloadListener(enhancedURI));
                if (downloadListener != null) {
                    downloadListenerSet.addListener(downloadListener);
                }
                BasicDownloadRequest basicDownloadRequest = new BasicDownloadRequest(enhancedURI.getURI().toString(), new UriProxySaveCallback(uriTranslateToLocalUri.getPath()), downloadListenerSet, this.mDownloadTimeoutInNanos, downloadRequestPriority);
                this.mPendingItems.put(enhancedURI, basicDownloadRequest);
                this.mDownloadService.submitDownloadRequest(basicDownloadRequest, false);
                DLog.logf("Submitted download request for URL: %s, prime preroll: %s, priority: %s", basicDownloadRequest.getUrl(), Boolean.valueOf(z), priorityTier);
            }
        }
    }

    public URI lookup(EnhancedURI enhancedURI) {
        synchronized (this.mSetsLock) {
            URI uriTranslateToLocalUri = translateToLocalUri(enhancedURI);
            if (this.mDownloadedItems.contains(uriTranslateToLocalUri)) {
                return uriTranslateToLocalUri;
            }
            return null;
        }
    }

    public boolean contains(EnhancedURI enhancedURI) {
        synchronized (this.mSetsLock) {
            if (enhancedURI == null) {
                return false;
            }
            return this.mDownloadedItems.contains(translateToLocalUri(enhancedURI));
        }
    }

    public void cancel(EnhancedURI enhancedURI) {
        DownloadRequest downloadRequest;
        synchronized (this.mSetsLock) {
            downloadRequest = this.mPendingItems.get(enhancedURI);
        }
        if (downloadRequest != null) {
            this.mDownloadService.cancelDownloadRequest(downloadRequest);
        }
    }

    public void cleanup() {
        synchronized (this.mSetsLock) {
            this.mDownloadedItems.clear();
        }
        this.mExecutorService.execute(new ClearTask());
    }

    URI translateToLocalUri(@Nonnull EnhancedURI enhancedURI) {
        int i;
        Preconditions.checkNotNull(enhancedURI);
        String path = enhancedURI.getURI().getPath();
        int iLastIndexOf = path.lastIndexOf(".");
        return new File(this.mCacheDirectory + File.separator + "cached" + enhancedURI.getIdentifier() + ((iLastIndexOf < 0 || path.length() < (i = iLastIndexOf + 4)) ? "" : path.substring(iLastIndexOf, i))).toURI();
    }

    class ClearTask implements Runnable {
        ClearTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (UriProxy.this.mSetsLock) {
                UnmodifiableIterator it = ImmutableList.copyOf((Collection) UriProxy.this.mPendingItems.values()).iterator();
                while (it.hasNext()) {
                    UriProxy.this.mDownloadService.cancelDownloadRequest((DownloadRequest) it.next());
                }
            }
            try {
                UriProxy uriProxy = UriProxy.this;
                uriProxy.mStorage.delete(uriProxy.mCacheDirectory);
            } catch (IOException unused) {
                DLog.warnf("Failed to clean session cache folder: %s", UriProxy.this.mCacheDirectory);
            }
        }
    }

    class UriProxySaveCallback implements SaveCallback {
        private final String mSavePath;

        public UriProxySaveCallback(String str) {
            this.mSavePath = str;
        }

        @Override // com.amazon.avod.media.downloadservice.SaveCallback
        public void save(InputStream inputStream, int i) throws Throwable {
            try {
                UriProxy.this.mStorage.putInputStream(this.mSavePath, inputStream, i);
            } catch (IOException e) {
                throw GenericMediaException.wrapIfNeeded(e, StandardErrorCode.DISK_IO_ERROR);
            }
        }
    }

    class UriProxyDownloadListener implements DownloadListener {
        private final EnhancedURI mRemoteUri;

        public UriProxyDownloadListener(EnhancedURI enhancedURI) {
            this.mRemoteUri = enhancedURI;
        }

        @Override // com.amazon.avod.media.downloadservice.DownloadListener
        public void onSuccess(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics) {
            synchronized (UriProxy.this.mSetsLock) {
                UriProxy.this.mPendingItems.remove(this.mRemoteUri);
                UriProxy uriProxy = UriProxy.this;
                uriProxy.mDownloadedItems.add(uriProxy.translateToLocalUri(this.mRemoteUri), downloadStatistics.getBytesProcessed());
            }
            DLog.logf("Completed download from: %s", downloadRequest.getUrl());
        }

        @Override // com.amazon.avod.media.downloadservice.DownloadListener
        public void onFailure(DownloadRequest downloadRequest, Exception exc, DownloadStatistics downloadStatistics) {
            removePendingUri();
            DLog.logf("Failed to download from: %s", downloadRequest.getUrl());
        }

        @Override // com.amazon.avod.media.downloadservice.DownloadListener
        public void onCancel(DownloadRequest downloadRequest) {
            removePendingUri();
            DLog.logf("Canceled download from: %s", downloadRequest.getUrl());
        }

        @Override // com.amazon.avod.media.downloadservice.DownloadListener
        public void onDownloadProgress(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics, long j) {
            DLog.devf("Reporting download progress from: %s", downloadRequest.getUrl());
        }

        private void removePendingUri() {
            synchronized (UriProxy.this.mSetsLock) {
                UriProxy.this.mPendingItems.remove(this.mRemoteUri);
            }
        }
    }
}
