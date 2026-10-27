package com.amazon.digitalmusicplayback;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DownloadCoordinator {
    public abstract void cancelDownloads(ArrayList<String> arrayList);

    public abstract void changeToBackgroundDownloadMode(boolean z);

    public abstract void clearDeviceNotAuthorizedException();

    public abstract void download(ArrayList<DownloadEntry> arrayList);

    public abstract ArrayList<DownloadStorageInfo> downloadFolderInfo();

    public abstract boolean downloadOnWifiOnly();

    public abstract DownloadCoordinatorConfig getConfig();

    public abstract ArrayList<StorageInitError> getInitErrors();

    public abstract ArrayList<TrackQualityEntry> getOfflineASINs(boolean z, int i);

    public abstract TrackQualityEntry isAvailableOffline(String str);

    public abstract boolean isOfflineDRMLicenseAvailable();

    public abstract void mergeToPrimary(String str);

    public abstract void pauseDownloads(ArrayList<String> arrayList);

    public abstract long quickplaySize();

    public abstract void removeAllDownloads(ArrayList<String> arrayList);

    public abstract void removeDownload(String str, DownloadReason downloadReason);

    public abstract void resumeDownload(String str);

    public abstract void runPerfTestOnDB(String str, String str2);

    public abstract void setDownloadOnWifiOnly(boolean z);

    public abstract long size();

    public abstract ArrayList<DownloadStorageInfo> storageInfo();

    public abstract void updateConfig(DownloadCoordinatorConfig downloadCoordinatorConfig);

    private static final class CppProxy extends DownloadCoordinator {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);
        private final long nativeRef;

        private native void nativeDestroy(long j);

        private native void native_cancelDownloads(long j, ArrayList<String> arrayList);

        private native void native_changeToBackgroundDownloadMode(long j, boolean z);

        private native void native_clearDeviceNotAuthorizedException(long j);

        private native void native_download(long j, ArrayList<DownloadEntry> arrayList);

        private native ArrayList<DownloadStorageInfo> native_downloadFolderInfo(long j);

        private native boolean native_downloadOnWifiOnly(long j);

        private native DownloadCoordinatorConfig native_getConfig(long j);

        private native ArrayList<StorageInitError> native_getInitErrors(long j);

        private native ArrayList<TrackQualityEntry> native_getOfflineASINs(long j, boolean z, int i);

        private native TrackQualityEntry native_isAvailableOffline(long j, String str);

        private native boolean native_isOfflineDRMLicenseAvailable(long j);

        private native void native_mergeToPrimary(long j, String str);

        private native void native_pauseDownloads(long j, ArrayList<String> arrayList);

        private native long native_quickplaySize(long j);

        private native void native_removeAllDownloads(long j, ArrayList<String> arrayList);

        private native void native_removeDownload(long j, String str, DownloadReason downloadReason);

        private native void native_resumeDownload(long j, String str);

        private native void native_runPerfTestOnDB(long j, String str, String str2);

        private native void native_setDownloadOnWifiOnly(long j, boolean z);

        private native long native_size(long j);

        private native ArrayList<DownloadStorageInfo> native_storageInfo(long j);

        private native void native_updateConfig(long j, DownloadCoordinatorConfig downloadCoordinatorConfig);

        private CppProxy(long j) {
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
        }

        public void _djinni_private_destroy() {
            if (this.destroyed.getAndSet(true)) {
                return;
            }
            nativeDestroy(this.nativeRef);
        }

        protected void finalize() throws Throwable {
            _djinni_private_destroy();
            super.finalize();
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public void download(ArrayList<DownloadEntry> arrayList) {
            native_download(this.nativeRef, arrayList);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public ArrayList<StorageInitError> getInitErrors() {
            return native_getInitErrors(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public void changeToBackgroundDownloadMode(boolean z) {
            native_changeToBackgroundDownloadMode(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public void cancelDownloads(ArrayList<String> arrayList) {
            native_cancelDownloads(this.nativeRef, arrayList);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public void removeDownload(String str, DownloadReason downloadReason) {
            native_removeDownload(this.nativeRef, str, downloadReason);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public void removeAllDownloads(ArrayList<String> arrayList) {
            native_removeAllDownloads(this.nativeRef, arrayList);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public void pauseDownloads(ArrayList<String> arrayList) {
            native_pauseDownloads(this.nativeRef, arrayList);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public void resumeDownload(String str) {
            native_resumeDownload(this.nativeRef, str);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public ArrayList<TrackQualityEntry> getOfflineASINs(boolean z, int i) {
            return native_getOfflineASINs(this.nativeRef, z, i);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public TrackQualityEntry isAvailableOffline(String str) {
            return native_isAvailableOffline(this.nativeRef, str);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public boolean isOfflineDRMLicenseAvailable() {
            return native_isOfflineDRMLicenseAvailable(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public void setDownloadOnWifiOnly(boolean z) {
            native_setDownloadOnWifiOnly(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public boolean downloadOnWifiOnly() {
            return native_downloadOnWifiOnly(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public ArrayList<DownloadStorageInfo> storageInfo() {
            return native_storageInfo(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public ArrayList<DownloadStorageInfo> downloadFolderInfo() {
            return native_downloadFolderInfo(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public void updateConfig(DownloadCoordinatorConfig downloadCoordinatorConfig) {
            native_updateConfig(this.nativeRef, downloadCoordinatorConfig);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public void mergeToPrimary(String str) {
            native_mergeToPrimary(this.nativeRef, str);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public long quickplaySize() {
            return native_quickplaySize(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public long size() {
            return native_size(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public void clearDeviceNotAuthorizedException() {
            native_clearDeviceNotAuthorizedException(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public void runPerfTestOnDB(String str, String str2) {
            native_runPerfTestOnDB(this.nativeRef, str, str2);
        }

        @Override // com.amazon.digitalmusicplayback.DownloadCoordinator
        public DownloadCoordinatorConfig getConfig() {
            return native_getConfig(this.nativeRef);
        }
    }
}
