package android.os.incremental;

import android.content.pm.DataLoaderParams;
import android.content.pm.IDataLoaderStatusListener;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.SparseArray;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/* JADX INFO: loaded from: classes2.dex */
public final class IncrementalManager {
    private static final String ALLOWED_PROPERTY = "incremental.allowed";
    public static final int CREATE_MODE_CREATE = 4;
    public static final int CREATE_MODE_OPEN_EXISTING = 8;
    public static final int CREATE_MODE_PERMANENT_BIND = 2;
    public static final int CREATE_MODE_TEMPORARY_BIND = 1;
    private static final String TAG = "IncrementalManager";
    private final IIncrementalService mService;
    private final SparseArray<IncrementalStorage> mStorages = new SparseArray<>();

    @Retention(RetentionPolicy.SOURCE)
    public @interface CreateMode {
    }

    private static native boolean nativeIsEnabled();

    private static native boolean nativeIsIncrementalPath(String str);

    private static native byte[] nativeUnsafeGetFileSignature(String str);

    public IncrementalManager(IIncrementalService iIncrementalService) {
        this.mService = iIncrementalService;
    }

    public IncrementalStorage getStorage(int i) {
        IncrementalStorage incrementalStorage;
        synchronized (this.mStorages) {
            incrementalStorage = this.mStorages.get(i);
        }
        return incrementalStorage;
    }

    public IncrementalStorage createStorage(String str, DataLoaderParams dataLoaderParams, int i, boolean z, IDataLoaderStatusListener iDataLoaderStatusListener, StorageHealthCheckParams storageHealthCheckParams, IStorageHealthListener iStorageHealthListener) {
        try {
            int iCreateStorage = this.mService.createStorage(str, dataLoaderParams.getData(), i, iDataLoaderStatusListener, storageHealthCheckParams, iStorageHealthListener);
            if (iCreateStorage < 0) {
                return null;
            }
            IncrementalStorage incrementalStorage = new IncrementalStorage(this.mService, iCreateStorage);
            synchronized (this.mStorages) {
                this.mStorages.put(iCreateStorage, incrementalStorage);
            }
            if (z) {
                incrementalStorage.startLoading();
            }
            return incrementalStorage;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public IncrementalStorage openStorage(String str) {
        try {
            int iOpenStorage = this.mService.openStorage(str);
            if (iOpenStorage < 0) {
                return null;
            }
            IncrementalStorage incrementalStorage = new IncrementalStorage(this.mService, iOpenStorage);
            synchronized (this.mStorages) {
                this.mStorages.put(iOpenStorage, incrementalStorage);
            }
            return incrementalStorage;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public IncrementalStorage createStorage(String str, IncrementalStorage incrementalStorage, int i) {
        try {
            int iCreateLinkedStorage = this.mService.createLinkedStorage(str, incrementalStorage.getId(), i);
            if (iCreateLinkedStorage < 0) {
                return null;
            }
            IncrementalStorage incrementalStorage2 = new IncrementalStorage(this.mService, iCreateLinkedStorage);
            synchronized (this.mStorages) {
                this.mStorages.put(iCreateLinkedStorage, incrementalStorage2);
            }
            return incrementalStorage2;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void renameCodePath(File file, File file2) throws Exception {
        File absoluteFile = file.getAbsoluteFile();
        IncrementalStorage incrementalStorageOpenStorage = openStorage(absoluteFile.toString());
        if (incrementalStorageOpenStorage == null) {
            throw new IllegalArgumentException("Not an Incremental path: " + absoluteFile);
        }
        String parent = file2.getAbsoluteFile().getParent();
        IncrementalStorage incrementalStorageCreateStorage = createStorage(parent, incrementalStorageOpenStorage, 6);
        if (incrementalStorageCreateStorage == null) {
            throw new IOException("Failed to create linked storage at dir: " + parent);
        }
        try {
            linkFiles(incrementalStorageOpenStorage, absoluteFile, "", incrementalStorageCreateStorage, file2.getName());
            incrementalStorageOpenStorage.unBind(absoluteFile.toString());
        } catch (Exception e) {
            incrementalStorageCreateStorage.unBind(parent);
            throw e;
        }
    }

    private void linkFiles(final IncrementalStorage incrementalStorage, File file, String str, final IncrementalStorage incrementalStorage2, String str2) throws IOException {
        final Path pathResolve = file.toPath().resolve(str);
        final Path path = Paths.get(str2, new String[0]);
        Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() { // from class: android.os.incremental.IncrementalManager.1
            @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
            public FileVisitResult preVisitDirectory(Path path2, BasicFileAttributes basicFileAttributes) throws IOException {
                incrementalStorage2.makeDirectory(path.resolve(pathResolve.relativize(path2)).toString());
                return FileVisitResult.CONTINUE;
            }

            @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
            public FileVisitResult visitFile(Path path2, BasicFileAttributes basicFileAttributes) throws IOException {
                incrementalStorage.makeLink(path2.toAbsolutePath().toString(), incrementalStorage2, path.resolve(pathResolve.relativize(path2)).toString());
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public void closeStorage(String str) {
        try {
            int iOpenStorage = this.mService.openStorage(str);
            if (iOpenStorage < 0) {
                return;
            }
            this.mService.deleteStorage(iOpenStorage);
            synchronized (this.mStorages) {
                this.mStorages.remove(iOpenStorage);
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static boolean isFeatureEnabled() {
        return nativeIsEnabled();
    }

    public static boolean isAllowed() {
        return isFeatureEnabled() && SystemProperties.getBoolean(ALLOWED_PROPERTY, true);
    }

    public static boolean isIncrementalPath(String str) {
        return nativeIsIncrementalPath(str);
    }

    public static byte[] unsafeGetFileSignature(String str) {
        return nativeUnsafeGetFileSignature(str);
    }
}
