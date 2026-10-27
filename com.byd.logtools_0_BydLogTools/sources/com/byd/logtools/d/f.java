package com.byd.logtools.d;

import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* JADX INFO: compiled from: FileUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: compiled from: FileUtil.java */
    class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return !file.isDirectory();
        }
    }

    public static File[] a(File file) {
        return file.listFiles(new a());
    }

    public static void b(File file) {
        if (file != null) {
            try {
                if (!file.isDirectory()) {
                    file.delete();
                    return;
                }
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        file2.delete();
                    }
                }
                BydLogHelper.logD("delet dectory = " + file.getName());
                file.delete();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void c(String str, String str2) throws Exception {
        FileOutputStream fileOutputStream;
        FileChannel channel;
        FileChannel fileChannel;
        File file = new File(str2);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileInputStream fileInputStream = null;
        channel = null;
        FileChannel channel2 = null;
        fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(new File(str));
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = null;
                channel = null;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
                channel = null;
            }
            try {
                channel = fileInputStream2.getChannel();
                try {
                    channel2 = fileOutputStream.getChannel();
                    channel.transferTo(0L, channel.size(), channel2);
                    fileOutputStream.getFD().sync();
                    BydLogHelper.logE("sync");
                    try {
                        fileInputStream2.close();
                        channel.close();
                        fileOutputStream.close();
                        channel2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                } catch (Exception e4) {
                    e = e4;
                    fileChannel = channel2;
                    fileInputStream = fileInputStream2;
                    try {
                        e.printStackTrace();
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            fileInputStream.close();
                            channel.close();
                            fileOutputStream.close();
                            fileChannel.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileChannel = channel2;
                    fileInputStream = fileInputStream2;
                    fileInputStream.close();
                    channel.close();
                    fileOutputStream.close();
                    fileChannel.close();
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                channel = null;
                fileInputStream = fileInputStream2;
                fileChannel = channel;
                e.printStackTrace();
                throw e;
            } catch (Throwable th4) {
                th = th4;
                channel = null;
                fileInputStream = fileInputStream2;
                fileChannel = channel;
                fileInputStream.close();
                channel.close();
                fileOutputStream.close();
                fileChannel.close();
                throw th;
            }
        } catch (Exception e7) {
            e = e7;
            fileOutputStream = null;
            channel = null;
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
            channel = null;
        }
    }

    public static boolean d(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (!file.isFile()) {
                return true;
            }
            file.delete();
            boolean zMkdirs = file.mkdirs();
            BydLogHelper.logE("deleteOldDir success, logRootDirPath b =  " + zMkdirs);
            return zMkdirs;
        }
        boolean zMkdirs2 = false;
        try {
            zMkdirs2 = file.mkdirs();
            BydLogHelper.logD("deleteOldDir success, logRootDirPath b =  " + zMkdirs2);
            return zMkdirs2;
        } catch (SecurityException e2) {
            e2.printStackTrace();
            return zMkdirs2;
        }
    }
}
