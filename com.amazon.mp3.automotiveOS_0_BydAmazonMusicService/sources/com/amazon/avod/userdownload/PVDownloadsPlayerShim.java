package com.amazon.avod.userdownload;

import com.amazon.avod.acos.StorageHelper;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PVDownloadsPlayerShim {
    private PVDownloadManagerPlayerShim mPVDownloadManager;

    @Nonnull
    public abstract PVDownloadManagerPlayerShim getDownloadManager();

    @Nonnull
    public abstract StorageHelper getStorageHelper();

    private static class Holder {
        private static volatile PVDownloadsPlayerShim sInstance;

        private Holder() {
        }
    }

    protected static void setInstance(PVDownloadsPlayerShim pVDownloadsPlayerShim) {
        PVDownloadsPlayerShim unused = Holder.sInstance = pVDownloadsPlayerShim;
    }

    public static PVDownloadsPlayerShim getInstance() {
        return Holder.sInstance;
    }

    public void setPlayerDownloadManagerProxy(PVDownloadManagerPlayerShim pVDownloadManagerPlayerShim) {
        this.mPVDownloadManager = pVDownloadManagerPlayerShim;
    }

    @Nonnull
    public PVDownloadManagerPlayerShim getPlayerDownloadManagerProxy() {
        PVDownloadManagerPlayerShim pVDownloadManagerPlayerShim = this.mPVDownloadManager;
        return pVDownloadManagerPlayerShim == null ? getDownloadManager() : pVDownloadManagerPlayerShim;
    }
}
