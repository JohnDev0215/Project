package com.byd.music.utils.log;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes5.dex */
public class LogCopy {
    int counterAll = 0;
    int count = 0;
    boolean stop = false;

    public interface FileCopyListener {
        void onFail();

        void onNoLog();

        void onSuccesss();

        void updateProcess(int i, int i2, String str, String str2);
    }

    public int getFileCount(File file) {
        this.stop = false;
        if (!file.exists()) {
            return 0;
        }
        for (File file2 : file.listFiles()) {
            if (this.stop) {
                return this.counterAll;
            }
            if (file2.isDirectory()) {
                getFileCount(file2);
            } else {
                this.counterAll++;
            }
        }
        return this.counterAll;
    }

    public void copy(Context context, String str, String str2, FileCopyListener fileCopyListener) {
        if (this.stop) {
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            if (fileCopyListener != null) {
                fileCopyListener.onNoLog();
                return;
            }
            return;
        }
        File[] fileArrListFiles = file.listFiles();
        File file2 = new File(str2);
        if (!file2.exists() && !DocumentsUtils.mkdirs(context, file2)) {
            fileCopyListener.onFail();
            stop();
        }
        for (File file3 : fileArrListFiles) {
            if (this.stop) {
                return;
            }
            if (file3.isDirectory()) {
                copy(context, file3.getPath() + "/", str2 + "/" + file3.getName() + "/", fileCopyListener);
            } else {
                int i = this.count + 1;
                this.count = i;
                fileCopyListener.updateProcess(i, this.counterAll, file3.getPath(), str2 + "/" + file3.getName());
                if (!documentuiCopySdcardFile(context, file3.getPath(), str2 + "/" + file3.getName())) {
                    fileCopyListener.onFail();
                    stop();
                } else if (this.count == this.counterAll) {
                    fileCopyListener.onSuccesss();
                }
            }
        }
    }

    public boolean CopySdcardFile(String str, String str2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i > 0) {
                    if (this.stop) {
                        return false;
                    }
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void stop() {
        this.stop = true;
    }

    public boolean documentuiCopySdcardFile(Context context, String str, String str2) throws Throwable {
        try {
            InputStream inputStream = DocumentsUtils.getInputStream(context, new File(str));
            OutputStream outputStream = DocumentsUtils.getOutputStream(context, new File(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i > 0) {
                    if (this.stop) {
                        return false;
                    }
                    outputStream.write(bArr, 0, i);
                } else {
                    inputStream.close();
                    outputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
