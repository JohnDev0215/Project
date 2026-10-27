package io.sentry.cache;

import io.sentry.C1454c;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f16488a = Charset.forName("UTF-8");

    public static void a(F1 f12, String str, String str2) {
        File file;
        String cacheDirPath = f12.getCacheDirPath();
        if (cacheDirPath == null) {
            file = null;
        } else {
            File file2 = new File(cacheDirPath, str);
            file2.mkdirs();
            file = file2;
        }
        if (file == null) {
            f12.getLogger().k(EnumC1495p1.INFO, "Cache dir is not set, cannot delete from scope cache", new Object[0]);
            return;
        }
        File file3 = new File(file, str2);
        if (file3.exists()) {
            f12.getLogger().k(EnumC1495p1.DEBUG, "Deleting %s from scope cache", str2);
            if (file3.delete()) {
                return;
            }
            f12.getLogger().k(EnumC1495p1.ERROR, "Failed to delete: %s", file3.getAbsolutePath());
        }
    }

    public static Object b(F1 f12, String str, String str2, Class cls, C1454c c1454c) {
        File file;
        String cacheDirPath = f12.getCacheDirPath();
        if (cacheDirPath == null) {
            file = null;
        } else {
            file = new File(cacheDirPath, str);
            file.mkdirs();
        }
        if (file == null) {
            f12.getLogger().k(EnumC1495p1.INFO, "Cache dir is not set, cannot read from scope cache", new Object[0]);
            return null;
        }
        File file2 = new File(file, str2);
        if (file2.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2), f16488a));
                try {
                    if (c1454c == null) {
                        Object objA = f12.getSerializer().a(bufferedReader, cls);
                        bufferedReader.close();
                        return objA;
                    }
                    Object objD = f12.getSerializer().d(bufferedReader, cls, c1454c);
                    bufferedReader.close();
                    return objD;
                } finally {
                }
            } catch (Throwable th) {
                f12.getLogger().p(EnumC1495p1.ERROR, th, "Error reading entity from scope cache: %s", str2);
            }
        } else {
            f12.getLogger().k(EnumC1495p1.DEBUG, "No entry stored for %s", str2);
        }
        return null;
    }

    public static void c(F1 f12, Object obj, String str, String str2) {
        File file;
        String cacheDirPath = f12.getCacheDirPath();
        if (cacheDirPath == null) {
            file = null;
        } else {
            File file2 = new File(cacheDirPath, str);
            file2.mkdirs();
            file = file2;
        }
        if (file == null) {
            f12.getLogger().k(EnumC1495p1.INFO, "Cache dir is not set, cannot store in scope cache", new Object[0]);
            return;
        }
        File file3 = new File(file, str2);
        if (file3.exists()) {
            f12.getLogger().k(EnumC1495p1.DEBUG, "Overwriting %s in scope cache", str2);
            if (!file3.delete()) {
                f12.getLogger().k(EnumC1495p1.ERROR, "Failed to delete: %s", file3.getAbsolutePath());
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, f16488a));
                try {
                    f12.getSerializer().f(obj, bufferedWriter);
                    bufferedWriter.close();
                    fileOutputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            f12.getLogger().p(EnumC1495p1.ERROR, th, "Error persisting entity: %s", str2);
        }
    }
}
