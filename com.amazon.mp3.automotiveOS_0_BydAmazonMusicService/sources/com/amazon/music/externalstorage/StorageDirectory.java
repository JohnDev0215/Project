package com.amazon.music.externalstorage;

import com.amazon.music.externalstorage.StorageLocationFileManager;
import java.io.File;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes4.dex */
interface StorageDirectory {
    File getDirectory(StorageLocationFileManager.Visibility visibility) throws FileNotFoundException;
}
