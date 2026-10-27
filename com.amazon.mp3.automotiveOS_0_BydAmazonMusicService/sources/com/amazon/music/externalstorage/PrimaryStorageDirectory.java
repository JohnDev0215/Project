package com.amazon.music.externalstorage;

import android.content.Context;
import android.os.Environment;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import java.io.File;

/* JADX INFO: loaded from: classes4.dex */
final class PrimaryStorageDirectory implements StorageDirectory {
    private final Context context;
    private final String mediaType;

    public PrimaryStorageDirectory(Context context, String str) {
        this.context = context;
        this.mediaType = str;
    }

    /* JADX INFO: renamed from: com.amazon.music.externalstorage.PrimaryStorageDirectory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$Visibility;

        static {
            int[] iArr = new int[StorageLocationFileManager.Visibility.values().length];
            $SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$Visibility = iArr;
            try {
                iArr[StorageLocationFileManager.Visibility.PRIVATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$Visibility[StorageLocationFileManager.Visibility.PUBLIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.music.externalstorage.StorageDirectory
    public File getDirectory(StorageLocationFileManager.Visibility visibility) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$Visibility[visibility.ordinal()];
        if (i == 1) {
            return this.context.getExternalFilesDir(this.mediaType);
        }
        if (i == 2) {
            return Environment.getExternalStoragePublicDirectory(this.mediaType);
        }
        throw new IllegalArgumentException(String.format("Unknown visibility: %s type passed in.", visibility.name()));
    }
}
