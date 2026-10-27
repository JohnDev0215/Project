package com.byd.logtools.d;

import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: CrashFileUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static void a(File file) {
        File[] fileArrListFiles;
        if (file != null) {
            try {
                if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                    for (File file2 : fileArrListFiles) {
                        file2.delete();
                    }
                }
                file.delete();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static synchronized String b(String str) {
        String str2;
        FileInputStream fileInputStream;
        String str3;
        str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        File file = new File(str);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else if (file.exists() && file.isDirectory()) {
                file.delete();
                file.createNewFile();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (file.canRead()) {
            try {
                fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                str3 = new String(bArr);
            } catch (IOException e3) {
                e = e3;
            }
            try {
                fileInputStream.close();
                str2 = str3;
            } catch (IOException e4) {
                e = e4;
                str2 = str3;
                e.printStackTrace();
            }
        }
        return str2;
    }

    public static boolean c(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return !file.mkdirs();
        }
        if (!file.isFile()) {
            return false;
        }
        file.delete();
        return !file.mkdirs();
    }

    public static synchronized void d(String str, String str2) {
        File file = new File(str);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else if (file.exists() && file.isDirectory()) {
                file.delete();
                file.createNewFile();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (file.canWrite()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(str2.getBytes());
                fileOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } else {
            BydLogHelper.logE("No permission to write file.");
        }
    }
}
