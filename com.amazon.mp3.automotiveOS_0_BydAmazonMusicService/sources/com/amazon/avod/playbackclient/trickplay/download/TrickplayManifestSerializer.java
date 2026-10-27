package com.amazon.avod.playbackclient.trickplay.download;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.io.Closer;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public final class TrickplayManifestSerializer {
    private final StorageHelper mStorageHelper;

    public TrickplayManifestSerializer() {
        this(StorageHelper.getInstance());
    }

    TrickplayManifestSerializer(@Nonnull StorageHelper storageHelper) {
        this.mStorageHelper = (StorageHelper) Preconditions.checkNotNull(storageHelper, "fileSharer");
    }

    public boolean persistManifest(VodTrickplayManifest vodTrickplayManifest, File file) throws Throwable {
        Closer closerCreate = Closer.create();
        try {
            try {
                File file2 = new File(file, "manifest.ser");
                if (!file2.exists()) {
                    Files.createParentDirs(file2);
                    if (!file2.createNewFile()) {
                        return false;
                    }
                }
                ((ObjectOutputStream) closerCreate.register(new ObjectOutputStream((OutputStream) closerCreate.register(new FileOutputStream(file2))))).writeObject(vodTrickplayManifest);
                return true;
            } catch (Throwable th) {
                try {
                    closerCreate.rethrow(th);
                    closerCreate.close();
                    return false;
                } finally {
                    closerCreate.close();
                }
            }
        } catch (IOException e) {
            DLog.exceptionf(e, "Encountered IO error trying to persist trickplay manifest.", new Object[0]);
            return false;
        }
    }

    public VodTrickplayManifest fetchResultFromDisk(File file) throws Throwable {
        File file2 = new File(file, "manifest.ser");
        if (!file2.exists()) {
            return VodTrickplayManifest.EMPTY_MANIFEST;
        }
        Closer closerCreate = Closer.create();
        try {
            try {
                try {
                    VodTrickplayManifest vodTrickplayManifest = (VodTrickplayManifest) ((ObjectInputStream) closerCreate.register(new ObjectInputStream((FileInputStream) closerCreate.register(new FileInputStream(file2))))).readObject();
                    try {
                        closerCreate.close();
                    } catch (IOException e) {
                        DLog.exceptionf(e, "Failed to close resources.", new Object[0]);
                    }
                    return vodTrickplayManifest;
                } catch (FileNotFoundException e2) {
                    DLog.exceptionf(e2, "Unexpected IO error. Trickplay manifest file no longer exists.", new Object[0]);
                    try {
                        closerCreate.close();
                    } catch (IOException e3) {
                        DLog.exceptionf(e3, "Failed to close resources.", new Object[0]);
                    }
                    return VodTrickplayManifest.EMPTY_MANIFEST;
                } catch (ClassNotFoundException e4) {
                    DLog.exceptionf(e4, "Encountered deserialization error trying to read existing trickplay manifest.", new Object[0]);
                    try {
                        closerCreate.close();
                    } catch (IOException e5) {
                        DLog.exceptionf(e5, "Failed to close resources.", new Object[0]);
                    }
                    return VodTrickplayManifest.EMPTY_MANIFEST;
                }
            } catch (StreamCorruptedException e6) {
                DLog.exceptionf(e6, "File containing trickplay manifest is not a serialized object.", new Object[0]);
                try {
                    closerCreate.close();
                } catch (IOException e7) {
                    DLog.exceptionf(e7, "Failed to close resources.", new Object[0]);
                }
                return VodTrickplayManifest.EMPTY_MANIFEST;
            } catch (IOException e8) {
                DLog.exceptionf(e8, "Encountered IO error trying to read existing trickplay manifest.", new Object[0]);
                try {
                    closerCreate.close();
                } catch (IOException e9) {
                    DLog.exceptionf(e9, "Failed to close resources.", new Object[0]);
                }
                return VodTrickplayManifest.EMPTY_MANIFEST;
            }
        } catch (Throwable th) {
            try {
                closerCreate.close();
            } catch (IOException e10) {
                DLog.exceptionf(e10, "Failed to close resources.", new Object[0]);
            }
            throw th;
        }
    }
}
