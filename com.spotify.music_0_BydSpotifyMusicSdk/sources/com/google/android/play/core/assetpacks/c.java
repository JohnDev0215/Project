package com.google.android.play.core.assetpacks;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import p.b23;
import p.tz2;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final tz2 c = new tz2("AssetPackStorage", 0);
    public final Context a;
    public final b23 b;

    static {
        TimeUnit timeUnit = TimeUnit.DAYS;
        timeUnit.toMillis(14L);
        timeUnit.toMillis(28L);
    }

    public c(Context context, b23 b23Var) {
        this.a = context;
        this.b = b23Var;
    }

    public static long b(File file, boolean z) {
        if (!file.exists()) {
            return -1L;
        }
        ArrayList arrayList = new ArrayList();
        tz2 tz2Var = c;
        if (z && file.listFiles().length > 1) {
            tz2Var.f("Multiple pack versions found, using highest version code.", new Object[0]);
        }
        try {
            for (File file2 : file.listFiles()) {
                if (!file2.getName().equals("stale.tmp")) {
                    arrayList.add(Long.valueOf(file2.getName()));
                }
            }
        } catch (NumberFormatException e) {
            tz2Var.d(e, "Corrupt asset pack directories.", new Object[0]);
        }
        if (arrayList.isEmpty()) {
            return -1L;
        }
        Collections.sort(arrayList);
        return ((Long) arrayList.get(arrayList.size() - 1)).longValue();
    }

    public static void f(File file) {
        if (file.listFiles() == null || file.listFiles().length <= 1) {
            return;
        }
        long jB = b(file, false);
        for (File file2 : file.listFiles()) {
            if (!file2.getName().equals(String.valueOf(jB)) && !file2.getName().equals("stale.tmp")) {
                g(file2);
            }
        }
    }

    public static boolean g(File file) {
        File[] fileArrListFiles = file.listFiles();
        boolean zG = true;
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                zG &= g(file2);
            }
        }
        if (file.delete()) {
            return zG;
        }
        return false;
    }

    public final void a(int i, long j, String str) {
        File file = new File(d(), str);
        if (file.exists()) {
            for (File file2 : file.listFiles()) {
                if (!file2.getName().equals(String.valueOf(i)) && !file2.getName().equals("stale.tmp")) {
                    g(file2);
                } else if (file2.getName().equals(String.valueOf(i))) {
                    for (File file3 : file2.listFiles()) {
                        if (!file3.getName().equals(String.valueOf(j))) {
                            g(file3);
                        }
                    }
                }
            }
        }
    }

    public final File c(int i, long j, String str) {
        return new File(new File(new File(new File(d(), "_tmp"), str), String.valueOf(i)), String.valueOf(j));
    }

    public final File d() {
        return new File(this.a.getFilesDir(), "assetpacks");
    }

    public final ArrayList e() {
        ArrayList arrayList = new ArrayList();
        try {
        } catch (IOException e) {
            c.c("Could not process directory while scanning installed packs. %s", e);
        }
        if (d().exists() && d().listFiles() != null) {
            for (File file : d().listFiles()) {
                if (!file.getCanonicalPath().equals(new File(d(), "_tmp").getCanonicalPath())) {
                    arrayList.add(file);
                }
            }
            return arrayList;
        }
        return arrayList;
    }

    public final int h(int i, long j, String str) throws IOException {
        File file = new File(new File(c(i, j, str), "_packs"), "merge.tmp");
        if (!file.exists()) {
            return 0;
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            properties.load(fileInputStream);
            fileInputStream.close();
            if (properties.getProperty("numberOfMerges") == null) {
                throw new zzck("Merge checkpoint file corrupt.");
            }
            try {
                return Integer.parseInt(properties.getProperty("numberOfMerges"));
            } catch (NumberFormatException e) {
                throw new zzck("Merge checkpoint file corrupt.", e);
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final File i(int i, long j, String str) {
        return new File(new File(new File(d(), str), String.valueOf(i)), String.valueOf(j));
    }

    public final File j(String str, int i, long j, String str2) {
        return new File(new File(new File(c(i, j, str), "_slices"), "_unverified"), str2);
    }

    public final File k(String str, int i, long j, String str2) {
        return new File(new File(new File(c(i, j, str), "_slices"), "_verified"), str2);
    }

    public final String l(String str) {
        int length;
        File file = new File(d(), str);
        boolean zExists = file.exists();
        tz2 tz2Var = c;
        if (!zExists) {
            tz2Var.b("Pack not found with pack name: %s", str);
            return null;
        }
        b23 b23Var = this.b;
        File file2 = new File(file, String.valueOf(b23Var.a()));
        if (!file2.exists()) {
            tz2Var.b("Pack not found with pack name: %s app version: %s", str, Integer.valueOf(b23Var.a()));
            return null;
        }
        File[] fileArrListFiles = file2.listFiles();
        if (fileArrListFiles == null || (length = fileArrListFiles.length) == 0) {
            tz2Var.b("No pack version found for pack name: %s app version: %s", str, Integer.valueOf(b23Var.a()));
            return null;
        }
        if (length <= 1) {
            return fileArrListFiles[0].getCanonicalPath();
        }
        tz2Var.c("Multiple pack versions found for pack name: %s app version: %s", str, Integer.valueOf(b23Var.a()));
        return null;
    }

    public final HashMap m() {
        HashMap map = new HashMap();
        Iterator it = e().iterator();
        while (it.hasNext()) {
            String name = ((File) it.next()).getName();
            int iB = (int) b(new File(d(), name), true);
            long jB = b(new File(new File(d(), name), String.valueOf(iB)), true);
            if (i(iB, jB, name).exists()) {
                map.put(name, Long.valueOf(jB));
            }
        }
        return map;
    }
}
