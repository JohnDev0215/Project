package org.chromium.base;

import android.content.Context;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public class FileUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "FileUtils";

    public static void recursivelyDeleteFile(File file) {
        File[] fileArrListFiles;
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                recursivelyDeleteFile(file2);
            }
        }
        if (file.delete()) {
            return;
        }
        Log.e(TAG, "Failed to delete: " + file, new Object[0]);
    }

    public static boolean extractAsset(Context context, String str, File file) {
        BufferedOutputStream bufferedOutputStream;
        InputStream inputStream = null;
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int i = inputStreamOpen.read(bArr);
                        if (i != -1) {
                            bufferedOutputStream.write(bArr, 0, i);
                        } else {
                            inputStreamOpen.close();
                            bufferedOutputStream.close();
                            return true;
                        }
                    }
                } catch (IOException unused) {
                    inputStream = inputStreamOpen;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                    return false;
                }
            } catch (IOException unused4) {
                bufferedOutputStream = null;
            }
        } catch (IOException unused5) {
            bufferedOutputStream = null;
        }
    }
}
