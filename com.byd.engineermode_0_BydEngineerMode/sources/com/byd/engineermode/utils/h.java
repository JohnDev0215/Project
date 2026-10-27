package com.byd.engineermode.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* JADX INFO: compiled from: FileUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f2515a = true;

    public static void a(File file) {
        if (file != null) {
            try {
                if (!file.isDirectory()) {
                    if (i.e()) {
                        a.i("delet file");
                    }
                    file.delete();
                    return;
                }
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        boolean zDelete = file2.delete();
                        if (i.e()) {
                            a.i(zDelete + " : delet, fileson = " + file2.getName());
                        }
                    }
                }
                if (i.e()) {
                    a.i("delet dectory = " + file.getName());
                }
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean b(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        return true;
    }

    public static void c(File file, File file2) throws Exception {
        FileOutputStream fileOutputStream;
        FileChannel channel;
        FileChannel fileChannel;
        FileInputStream fileInputStream;
        if (!file2.exists()) {
            file2.createNewFile();
        }
        FileInputStream fileInputStream2 = null;
        channel = null;
        FileChannel channel2 = null;
        fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    channel = fileInputStream.getChannel();
                } catch (Exception e) {
                    e = e;
                    channel = null;
                    fileInputStream2 = fileInputStream;
                    fileChannel = channel;
                    try {
                        e.printStackTrace();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        try {
                            fileInputStream2.close();
                            channel.close();
                            fileOutputStream.close();
                            fileChannel.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    channel = null;
                    fileInputStream2 = fileInputStream;
                    fileChannel = channel;
                    fileInputStream2.close();
                    channel.close();
                    fileOutputStream.close();
                    fileChannel.close();
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
                channel = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                channel = null;
            }
        } catch (Exception e4) {
            e = e4;
            fileOutputStream = null;
            channel = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            channel = null;
        }
        try {
            channel2 = fileOutputStream.getChannel();
            channel.transferTo(0L, channel.size(), channel2);
            fileOutputStream.getFD().sync();
            try {
                fileInputStream.close();
                channel.close();
                fileOutputStream.close();
                channel2.close();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
        } catch (Exception e6) {
            e = e6;
            fileChannel = channel2;
            fileInputStream2 = fileInputStream;
            e.printStackTrace();
            throw e;
        } catch (Throwable th5) {
            th = th5;
            fileChannel = channel2;
            fileInputStream2 = fileInputStream;
            fileInputStream2.close();
            channel.close();
            fileOutputStream.close();
            fileChannel.close();
            throw th;
        }
    }

    public static float d(File file) throws Throwable {
        if (!file.exists()) {
            return 0.0f;
        }
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    float fAvailable = fileInputStream2.available();
                    try {
                        fileInputStream2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return fAvailable;
                } catch (Exception e2) {
                    e = e2;
                    fileInputStream = fileInputStream2;
                    e.printStackTrace();
                    if (fileInputStream == null) {
                        return 0.0f;
                    }
                    try {
                        fileInputStream.close();
                        return 0.0f;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        return 0.0f;
                    }
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean e(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (!file.isFile()) {
                return true;
            }
            file.delete();
            boolean zMkdirs = file.mkdirs();
            if (i.e()) {
                a.a("deleteOldDir success, logRootDirPath b =  " + zMkdirs);
            }
            return zMkdirs;
        }
        boolean zMkdirs2 = false;
        try {
            zMkdirs2 = file.mkdirs();
            if (i.e()) {
                a.a("deleteOldDir success, logRootDirPath b =  " + zMkdirs2);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return zMkdirs2;
    }
}
