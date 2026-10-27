package com.amazon.music.externalstorage;

import android.content.Context;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import java.io.File;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes4.dex */
final class PublicNotSupportedSecondaryStorageDirectory implements StorageDirectory {
    private final Context context;
    private final String mediaType;

    public PublicNotSupportedSecondaryStorageDirectory(Context context, String str) {
        this.context = context;
        this.mediaType = str;
    }

    @Override // com.amazon.music.externalstorage.StorageDirectory
    public File getDirectory(StorageLocationFileManager.Visibility visibility) throws FileNotFoundException {
        File secondaryExternalStorage = getSecondaryExternalStorage(visibility);
        if (!secondaryExternalStorage.exists()) {
            secondaryExternalStorage.mkdirs();
        }
        return secondaryExternalStorage;
    }

    private File getSecondaryExternalStorage(StorageLocationFileManager.Visibility visibility) throws FileNotFoundException {
        File secondaryRootDirectory = getSecondaryRootDirectory();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$Visibility[visibility.ordinal()];
        if (i == 1) {
            return new File(secondaryRootDirectory, "Public");
        }
        if (i == 2) {
            return new File(secondaryRootDirectory, "Private");
        }
        throw new IllegalArgumentException(String.format("Unknown visiblity: %s type passed in.", visibility.name()));
    }

    /* JADX INFO: renamed from: com.amazon.music.externalstorage.PublicNotSupportedSecondaryStorageDirectory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$Visibility;

        static {
            int[] iArr = new int[StorageLocationFileManager.Visibility.values().length];
            $SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$Visibility = iArr;
            try {
                iArr[StorageLocationFileManager.Visibility.PUBLIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$Visibility[StorageLocationFileManager.Visibility.PRIVATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private File getSecondaryRootDirectory() throws FileNotFoundException {
        File file;
        File[] externalFilesDirs = this.context.getExternalFilesDirs(this.mediaType);
        if (externalFilesDirs == null || externalFilesDirs.length < 2 || (file = externalFilesDirs[1]) == null) {
            throw new FileNotFoundException("Secondary storage was not found");
        }
        return file;
    }
}
