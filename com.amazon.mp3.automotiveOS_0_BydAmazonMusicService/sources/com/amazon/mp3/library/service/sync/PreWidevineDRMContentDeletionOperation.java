package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.os.Environment;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.music.externalstorage.ExternalStorageNotSupportedException;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.facebook.react.bridge.BaseJavaModule;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: PreWidevineDRMContentDeletionOperation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0006\u0010\u000f\u001a\u00020\u0010R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/library/service/sync/PreWidevineDRMContentDeletionOperation;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "deleteDRMFilesInStorage", "", "storageLocationFileManager", "Lcom/amazon/music/externalstorage/StorageLocationFileManager;", "storageLocation", "Lcom/amazon/music/externalstorage/StorageLocation;", "deletePreWidevineDRMTracks", BaseJavaModule.METHOD_TYPE_SYNC, "", "DRMFileFilter", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PreWidevineDRMContentDeletionOperation {
    private final String TAG = PreWidevineDRMContentDeletionOperation.class.getSimpleName();
    private final Context context;

    public PreWidevineDRMContentDeletionOperation(Context context) {
        this.context = context;
    }

    public final int sync() {
        Context context = this.context;
        if (context == null) {
            return 5;
        }
        Log.info(this.TAG, "Begin deleting PreWidevine content.");
        deletePreWidevineDRMTracks(context);
        return 0;
    }

    private final void deletePreWidevineDRMTracks(Context context) {
        StorageLocationFileManager storageLocationFileManager = StorageLocationFileManager.create(context, Environment.DIRECTORY_MUSIC);
        Intrinsics.checkNotNullExpressionValue(storageLocationFileManager, "storageLocationFileManager");
        deleteDRMFilesInStorage(storageLocationFileManager, StorageLocation.DEVICE);
        deleteDRMFilesInStorage(storageLocationFileManager, StorageLocation.SDCARD);
    }

    private final void deleteDRMFilesInStorage(StorageLocationFileManager storageLocationFileManager, StorageLocation storageLocation) {
        File storageDirectory;
        File[] fileArrListFiles;
        try {
            if (PlatformUtil.isVersionOrLower(22)) {
                storageDirectory = storageLocationFileManager.getPreferredStorageDirectory(StorageLocationFileManager.Visibility.PRIVATE);
            } else {
                storageDirectory = storageLocationFileManager.getStorageDirectory(storageLocation, StorageLocationFileManager.Visibility.PRIVATE);
            }
            if (storageDirectory == null || (fileArrListFiles = storageDirectory.listFiles()) == null) {
                return;
            }
            int i = 0;
            int length = fileArrListFiles.length;
            while (i < length) {
                File file = fileArrListFiles[i];
                i++;
                FileUtil.deleteFilesInDir(file, true, new DRMFileFilter(this));
            }
        } catch (ExternalStorageNotSupportedException e) {
            Log.error(this.TAG, "Error while deleting DRM files", e);
        } catch (FileNotFoundException e2) {
            Log.error(this.TAG, "Error while deleting DRM files", e2);
        }
    }

    /* JADX INFO: compiled from: PreWidevineDRMContentDeletionOperation.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/library/service/sync/PreWidevineDRMContentDeletionOperation$DRMFileFilter;", "Ljava/io/FileFilter;", "(Lcom/amazon/mp3/library/service/sync/PreWidevineDRMContentDeletionOperation;)V", "accept", "", "file", "Ljava/io/File;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class DRMFileFilter implements FileFilter {
        final /* synthetic */ PreWidevineDRMContentDeletionOperation this$0;

        public DRMFileFilter(PreWidevineDRMContentDeletionOperation this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            if (!file.isDirectory()) {
                String path = file.getPath();
                Intrinsics.checkNotNullExpressionValue(path, "file.path");
                if (!StringsKt__StringsJVMKt.endsWith$default(path, ".m4a", false, 2, null)) {
                    return false;
                }
            }
            return true;
        }
    }
}
