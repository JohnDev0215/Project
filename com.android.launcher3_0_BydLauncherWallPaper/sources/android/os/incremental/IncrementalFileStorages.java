package android.os.incremental;

import android.content.Context;
import android.content.pm.DataLoaderParams;
import android.content.pm.IDataLoaderStatusListener;
import android.content.pm.InstallationFileParcel;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class IncrementalFileStorages {
    private static final String TAG = "IncrementalFileStorages";
    private IncrementalStorage mDefaultStorage;
    private final IncrementalManager mIncrementalManager;
    private final File mStageDir;

    public static IncrementalFileStorages initialize(Context context, File file, DataLoaderParams dataLoaderParams, IDataLoaderStatusListener iDataLoaderStatusListener, StorageHealthCheckParams storageHealthCheckParams, IStorageHealthListener iStorageHealthListener, List<InstallationFileParcel> list) throws IOException {
        IncrementalManager incrementalManager = (IncrementalManager) context.getSystemService(Context.INCREMENTAL_SERVICE);
        if (incrementalManager == null) {
            throw new IOException("Failed to obtain incrementalManager.");
        }
        IncrementalFileStorages incrementalFileStorages = new IncrementalFileStorages(file, incrementalManager, dataLoaderParams, iDataLoaderStatusListener, storageHealthCheckParams, iStorageHealthListener);
        for (InstallationFileParcel installationFileParcel : list) {
            if (installationFileParcel.location == 0) {
                try {
                    incrementalFileStorages.addApkFile(installationFileParcel);
                } catch (IOException e) {
                    throw new IOException("Failed to add file to IncFS: " + installationFileParcel.name + ", reason: ", e);
                }
            } else {
                throw new IOException("Unknown file location: " + installationFileParcel.location);
            }
        }
        incrementalFileStorages.startLoading();
        return incrementalFileStorages;
    }

    private IncrementalFileStorages(File file, IncrementalManager incrementalManager, DataLoaderParams dataLoaderParams, IDataLoaderStatusListener iDataLoaderStatusListener, StorageHealthCheckParams storageHealthCheckParams, IStorageHealthListener iStorageHealthListener) throws IOException {
        try {
            this.mStageDir = file;
            this.mIncrementalManager = incrementalManager;
            if (dataLoaderParams.getComponentName().getPackageName().equals("local")) {
                String arguments = dataLoaderParams.getArguments();
                if (TextUtils.isEmpty(arguments)) {
                    throw new IOException("Failed to create storage: incrementalPath is empty");
                }
                IncrementalStorage incrementalStorageOpenStorage = incrementalManager.openStorage(arguments);
                this.mDefaultStorage = incrementalStorageOpenStorage;
                if (incrementalStorageOpenStorage == null) {
                    throw new IOException("Couldn't open incremental storage at " + arguments);
                }
                incrementalStorageOpenStorage.bind(file.getAbsolutePath());
                return;
            }
            IncrementalStorage incrementalStorageCreateStorage = incrementalManager.createStorage(file.getAbsolutePath(), dataLoaderParams, 5, false, iDataLoaderStatusListener, storageHealthCheckParams, iStorageHealthListener);
            this.mDefaultStorage = incrementalStorageCreateStorage;
            if (incrementalStorageCreateStorage == null) {
                throw new IOException("Couldn't create incremental storage at " + file);
            }
        } catch (IOException e) {
            cleanUp();
            throw e;
        }
    }

    private void addApkFile(InstallationFileParcel installationFileParcel) throws IOException {
        String str = installationFileParcel.name;
        if (new File(this.mStageDir, str).exists()) {
            return;
        }
        this.mDefaultStorage.makeFile(str, installationFileParcel.size, null, installationFileParcel.metadata, installationFileParcel.signature);
    }

    public void startLoading() throws IOException {
        if (!this.mDefaultStorage.startLoading()) {
            throw new IOException("Failed to start loading data for Incremental installation.");
        }
    }

    public void disableReadLogs() {
        this.mDefaultStorage.disableReadLogs();
    }

    public void cleanUp() {
        IncrementalStorage incrementalStorage = this.mDefaultStorage;
        if (incrementalStorage == null) {
            return;
        }
        try {
            incrementalStorage.unBind(this.mStageDir.getAbsolutePath());
        } catch (IOException unused) {
        }
        this.mDefaultStorage = null;
    }
}
