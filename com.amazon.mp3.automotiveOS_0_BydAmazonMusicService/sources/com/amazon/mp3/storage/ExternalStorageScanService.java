package com.amazon.mp3.storage;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.download.DownloadCoordinatorConfigUpdater;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.generator.FileNameGenerator;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.externalstorage.ExternalStorageNotSupportedException;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class ExternalStorageScanService extends IntentService {
    private static final String TAG = ExternalStorageScanService.class.getSimpleName();
    private static ExecutorService sExecutorService;
    private static boolean sIsMediaMountedRunnableRunning;
    private static boolean sIsMediaRemovedRunnableRunning;

    public ExternalStorageScanService() {
        super(TAG);
    }

    public static boolean isRunning() {
        return sIsMediaMountedRunnableRunning || sIsMediaRemovedRunnableRunning;
    }

    private synchronized void startMediaMountedRunnable(final boolean isFromAppLaunch) {
        if (!sIsMediaMountedRunnableRunning) {
            sIsMediaMountedRunnableRunning = true;
            awaitExecutorTermination();
            ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
            sExecutorService = executorServiceNewSingleThreadExecutor;
            executorServiceNewSingleThreadExecutor.submit(new Runnable() { // from class: com.amazon.mp3.storage.ExternalStorageScanService$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$startMediaMountedRunnable$0(isFromAppLaunch);
                }
            });
            sExecutorService.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startMediaMountedRunnable$0(boolean z) {
        String str = TAG;
        Log.warning(str, "Starting mediaMountedRunnable");
        deletePreWidevineDRMContent();
        if (!z) {
            notifyDownloadCoordinator();
        }
        BaseMetricsRecorder.getMetricsRecorder(getApplicationContext()).getExternalStorageMetrics().recordSDCardInserted();
        sIsMediaMountedRunnableRunning = false;
        getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI_LOCAL, null);
        Log.warning(str, "Finished mediaMountedRunnable");
    }

    private synchronized void startMediaRemovedRunnable(final boolean isFromAppLaunch) {
        if (!sIsMediaRemovedRunnableRunning) {
            sIsMediaRemovedRunnableRunning = true;
            awaitExecutorTermination();
            ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
            sExecutorService = executorServiceNewSingleThreadExecutor;
            executorServiceNewSingleThreadExecutor.submit(new Runnable() { // from class: com.amazon.mp3.storage.ExternalStorageScanService$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$startMediaRemovedRunnable$1(isFromAppLaunch);
                }
            });
            sExecutorService.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startMediaRemovedRunnable$1(boolean z) {
        String str = TAG;
        Log.warning(str, "Starting mediaRemovedRunnable");
        if (!z) {
            notifyDownloadCoordinator();
        }
        BaseMetricsRecorder.getMetricsRecorder(getApplicationContext()).getExternalStorageMetrics().recordSDCardRemoved();
        sIsMediaRemovedRunnableRunning = false;
        Log.warning(str, "Finished mediaRemovedRunnable");
    }

    private synchronized void awaitExecutorTermination() {
        ExecutorService executorService = sExecutorService;
        if (executorService != null) {
            executorService.shutdownNow();
            try {
                sExecutorService.awaitTermination(5L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Log.error(TAG, "InterruptedException while waiting for executor service termination!", e.toString());
            }
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        sIsMediaMountedRunnableRunning = false;
        sIsMediaRemovedRunnableRunning = false;
        super.onDestroy();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                int i = extras.getInt("externalstorage_drm_service_extra_media_state");
                String str = TAG;
                Log.warning(str, "Intent in response to storage state: " + i + ".");
                if (i == 0) {
                    startMediaMountedRunnable(false);
                } else if (i == 1) {
                    startMediaRemovedRunnable(false);
                } else if (i == 2) {
                    startMediaMountedRunnable(true);
                } else if (i == 3) {
                    startMediaRemovedRunnable(true);
                } else {
                    Log.warning(str, "Service started with incorrect media storage state.");
                }
            } else {
                Log.warning(TAG, "Service started with null extras in intent.");
            }
        }
        stopSelf();
    }

    private List<String> getPrivatelyDownloadedFilesToScan() {
        StorageLocationFileManager storageLocationFileManagerCreate = StorageLocationFileManager.create(this, Environment.DIRECTORY_MUSIC);
        ArrayList arrayList = new ArrayList();
        StorageLocation secondaryStorageLocation = ExternalStorageListener.getSecondaryStorageLocation();
        try {
            File storageDirectory = storageLocationFileManagerCreate.getStorageDirectory(secondaryStorageLocation, StorageLocationFileManager.Visibility.PRIVATE);
            List<String> validMusicChildFiles = FileUtil.getValidMusicChildFiles(storageDirectory);
            arrayList.addAll(validMusicChildFiles);
            String str = TAG;
            Log.info(str, "Found %d files from private directory: %s", Integer.valueOf(validMusicChildFiles.size()), storageDirectory);
            if (storageLocationFileManagerCreate.getStorageConfiguration() == StorageLocationFileManager.StorageConfiguration.EXTERNAL_PUBLIC_NOT_SUPPORTED) {
                File storageDirectory2 = storageLocationFileManagerCreate.getStorageDirectory(secondaryStorageLocation, StorageLocationFileManager.Visibility.PUBLIC);
                List<String> validMusicChildFiles2 = FileUtil.getValidMusicChildFiles(storageDirectory2);
                arrayList.addAll(validMusicChildFiles2);
                Log.info(str, "Found %d files from public directory: %s", Integer.valueOf(validMusicChildFiles2.size()), storageDirectory2);
            }
        } catch (ExternalStorageNotSupportedException unused) {
            Log.warning(TAG, "Secondary Storage not supported on this device. Scan service should not be running.");
        } catch (FileNotFoundException unused2) {
            Log.warning(TAG, "Secondary Storage wasn't found to scan for DRM files.");
        }
        return arrayList;
    }

    private void deletePreWidevineDRMContent() {
        List<String> privatelyDownloadedFilesToScan = getPrivatelyDownloadedFilesToScan();
        if (privatelyDownloadedFilesToScan.isEmpty()) {
            Log.debug(TAG, "Folder is empty - No Pre-widevine tracks to delete");
        }
        for (String str : privatelyDownloadedFilesToScan) {
            if (FileNameGenerator.isPreWidevineDRMFileName(str)) {
                File file = new File(str);
                Log.debug(TAG, "Deleting Pre-Widevine file %s %s", str, Boolean.valueOf(FileUtil.deleteFile(file)));
                File parentFile = file.getParentFile().getParentFile();
                if (str.contains("PrimePlaylist")) {
                    parentFile = parentFile.getParentFile();
                }
                FileUtil.deleteEmptyDirectories(parentFile, true);
            }
        }
    }

    private void notifyDownloadCoordinator() {
        if (MusicDownloader.getInstance(this).isDownloading()) {
            return;
        }
        Handler backgroundHandler = AmazonApplication.getBackgroundHandler();
        final DownloadCoordinatorConfigUpdater downloadCoordinatorConfigUpdater = DownloadCoordinatorConfigUpdater.INSTANCE;
        Objects.requireNonNull(downloadCoordinatorConfigUpdater);
        backgroundHandler.postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.storage.ExternalStorageScanService$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                downloadCoordinatorConfigUpdater.changeSDCardAvailability();
            }
        });
    }
}
