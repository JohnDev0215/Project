package com.neusoft.na.ehp.utils;

import android.content.res.AssetManager;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class AssetInstaller {
    private static final String TAG = "NeuEhpAsset";
    private AssetManager assetManager;
    private File mTargetDir;

    public AssetInstaller(AssetManager assetManager, File file) {
        this.assetManager = assetManager;
        this.mTargetDir = file;
    }

    public boolean install() {
        removeAssets();
        try {
            for (String str : this.assetManager.list(ExtensionRequestData.EMPTY_VALUE)) {
                if (str.indexOf("images") < 0 && str.indexOf("webkit") < 0) {
                    copy(str);
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private void copy(String str) throws IOException {
        String[] list = this.assetManager.list(str);
        if (list.length > 0) {
            for (String str2 : list) {
                copy(str + File.separator + str2);
            }
            return;
        }
        copyAssetFile(str, new File(this.mTargetDir, str));
    }

    private boolean copyAssetFile(String str, File file) {
        Log.i(TAG, "extract asset file " + str + " to " + file.getAbsolutePath());
        try {
            createDir(file.getParentFile());
            InputStream inputStreamOpen = this.assetManager.open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i > -1) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileOutputStream.flush();
                    inputStreamOpen.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception unused) {
            Log.i(TAG, "extract asset file fail");
            return false;
        }
    }

    private void removeAssets() {
        delDir(this.mTargetDir);
    }

    public void delDir(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                delDir(file2);
            }
        }
        file.delete();
    }

    private void createDir(File file) {
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }
}
