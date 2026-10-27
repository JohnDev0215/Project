package com.amazon.mls.config.internal.core.network;

import com.amazon.mls.config.internal.core.exceptions.ClientSideException;
import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import com.amazon.mls.config.internal.core.util.SafeStreamCloser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class FileUploader {
    private final InputStreamUploader inputStreamUploader;

    public FileUploader(String str) {
        this.inputStreamUploader = new InputStreamUploader(str);
        LogcatProxy.log("Created Sushi FileUploader with source group '" + str + "'");
    }

    public boolean uploadFile(File file, SushiEndpoint sushiEndpoint) throws Throwable {
        LogcatProxy.log("Uploading sushi file '" + file.getAbsolutePath() + "'...");
        if (!file.exists()) {
            LogcatProxy.log("Sushi file '" + file.getAbsolutePath() + "' does not exist.");
            throw new ClientSideException(String.format(Locale.US, "The file doesn't exist! Path: %s", file.getAbsolutePath()));
        }
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    boolean zUpload = this.inputStreamUploader.upload(fileInputStream2, file.length(), sushiEndpoint);
                    if (zUpload) {
                        LogcatProxy.log("Upload of Sushi file '" + file.getAbsolutePath() + "' successful.");
                    } else {
                        LogcatProxy.log("Upload of Sushi file '" + file.getAbsolutePath() + "' failed.");
                    }
                    SafeStreamCloser.close(fileInputStream2);
                    return zUpload;
                } catch (FileNotFoundException e) {
                    e = e;
                    fileInputStream = fileInputStream2;
                    LogcatProxy.log("Sushi file '" + file.getAbsolutePath() + "' not found.", e);
                    throw new ClientSideException(String.format(Locale.US, "The file doesn't exist! Path: %s", file.getAbsolutePath()));
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    SafeStreamCloser.close(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
        }
    }
}
