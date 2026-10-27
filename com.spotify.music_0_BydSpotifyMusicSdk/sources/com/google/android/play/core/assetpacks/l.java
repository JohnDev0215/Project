package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import p.tz2;
import p.x13;

/* JADX INFO: loaded from: classes.dex */
public final class l {
    public static final tz2 b = new tz2("MergeSliceTaskHandler", 0);
    public final c a;

    public l(c cVar) {
        this.a = cVar;
    }

    public static void b(File file, File file2) {
        if (!file.isDirectory()) {
            if (file2.exists()) {
                throw new zzck("File clashing with existing file from other slice: ".concat(file2.toString()));
            }
            if (!file.renameTo(file2)) {
                throw new zzck("Unable to move file: ".concat(String.valueOf(file)));
            }
            return;
        }
        file2.mkdirs();
        for (File file3 : file.listFiles()) {
            b(file3, new File(file2, file3.getName()));
        }
        if (!file.delete()) {
            throw new zzck("Unable to delete directory: ".concat(String.valueOf(file)));
        }
    }

    public final void a(x13 x13Var) {
        File fileK = this.a.k(x13Var.b, x13Var.c, x13Var.d, x13Var.e);
        boolean zExists = fileK.exists();
        int i = x13Var.a;
        if (!zExists) {
            throw new zzck(String.format("Cannot find verified files for slice %s.", x13Var.e), i);
        }
        c cVar = this.a;
        cVar.getClass();
        int i2 = x13Var.c;
        long j = x13Var.d;
        String str = x13Var.b;
        File file = new File(cVar.c(i2, j, str), "_packs");
        if (!file.exists()) {
            file.mkdirs();
        }
        b(fileK, file);
        try {
            int iH = cVar.h(i2, j, str) + 1;
            File file2 = new File(new File(cVar.c(i2, j, str), "_packs"), "merge.tmp");
            Properties properties = new Properties();
            properties.put("numberOfMerges", String.valueOf(iH));
            file2.getParentFile().mkdirs();
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (IOException e) {
            b.c("Writing merge checkpoint failed with %s.", e.getMessage());
            throw new zzck("Writing merge checkpoint failed.", e, i);
        }
    }
}
