package com.byd.cleardata.h;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class a extends d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<com.byd.cleardata.d.d> f2664c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f2665d;
    private long e = 0;
    private long f = 0;

    /* JADX INFO: renamed from: com.byd.cleardata.h.a$a, reason: collision with other inner class name */
    class C0094a implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.g f2666a;

        C0094a(c.a.g gVar) {
            this.f2666a = gVar;
        }

        @Override // com.byd.cleardata.h.g
        public void a(long j) {
            a.q(a.this, j);
            this.f2666a.c(new m(a.this.e, a.this.f));
        }
    }

    public a(List<com.byd.cleardata.d.d> list, String str) {
        this.f2664c = list;
        this.f2665d = str;
    }

    static /* synthetic */ long q(a aVar, long j) {
        long j2 = aVar.e + j;
        aVar.e = j2;
        return j2;
    }

    private boolean t(File file, ZipOutputStream zipOutputStream, String str, g gVar) {
        return file.isDirectory() ? u(file, zipOutputStream, str, gVar) : v(file, zipOutputStream, str, gVar);
    }

    private boolean u(File file, ZipOutputStream zipOutputStream, String str, g gVar) {
        File[] fileArrListFiles;
        if (file == null || !file.exists() || !file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
            return false;
        }
        for (File file2 : fileArrListFiles) {
            if (o()) {
                com.byd.cleardata.i.c.b("compressDirectory cancel");
                return false;
            }
            if (!t(file2, zipOutputStream, str + file.getName() + File.separator, gVar)) {
                return false;
            }
        }
        return true;
    }

    private boolean v(File file, ZipOutputStream zipOutputStream, String str, g gVar) {
        BufferedInputStream bufferedInputStream;
        byte[] bArr;
        com.byd.cleardata.i.c.b("compressFile from " + file);
        if (com.byd.cleardata.i.e.h(file)) {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(str + file.getName()));
                    bArr = new byte[8192];
                } finally {
                }
            } catch (Throwable th) {
                com.byd.cleardata.i.c.d("compressFile error", th);
            }
            while (true) {
                int i = bufferedInputStream.read(bArr, 0, 8192);
                if (i == -1) {
                    bufferedInputStream.close();
                    return true;
                }
                if (o()) {
                    com.byd.cleardata.i.c.b("compressFile cancel");
                    bufferedInputStream.close();
                    return false;
                }
                zipOutputStream.write(bArr, 0, i);
                if (!file.getAbsolutePath().startsWith("/storage/emulated/0")) {
                    zipOutputStream.flush();
                }
                gVar.a(i);
                com.byd.cleardata.i.c.d("compressFile error", th);
            }
        }
        return false;
    }

    private File w() {
        String name = this.f2664c.get(0).w().getName();
        int iLastIndexOf = name.lastIndexOf(".");
        StringBuilder sb = new StringBuilder();
        if (iLastIndexOf == -1) {
            iLastIndexOf = name.length();
        }
        sb.append(name.substring(0, iLastIndexOf));
        sb.append(".zip");
        String strT = com.byd.cleardata.i.e.t(this.f2665d, sb.toString());
        if (strT == null) {
            return null;
        }
        File file = new File(this.f2665d + File.separator + strT);
        try {
            if (file.createNewFile()) {
                return file;
            }
            return null;
        } catch (IOException e) {
            com.byd.cleardata.i.c.d("getZipFile error", e);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0077  */
    @Override // com.byd.cleardata.h.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m(c.a.g<? super com.byd.cleardata.h.m> r11) {
        /*
            r10 = this;
            java.lang.String r0 = "compress start"
            com.byd.cleardata.i.c.b(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = r10.f2665d
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            r2 = 3
            java.lang.String r3 = "compress cancel"
            java.lang.String r4 = "source is empty"
            r5 = 0
            if (r1 != 0) goto L77
            java.util.List<com.byd.cleardata.d.d> r1 = r10.f2664c
            if (r1 == 0) goto L77
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L77
            java.util.List<com.byd.cleardata.d.d> r1 = r10.f2664c
            long r6 = r10.n(r1)
            r10.f = r6
            boolean r1 = r10.o()
            if (r1 == 0) goto L3b
            com.byd.cleardata.i.c.b(r3)
            com.byd.cleardata.h.m r1 = new com.byd.cleardata.h.m
            r1.<init>(r2, r0)
            r11.c(r1)
        L3b:
            long r6 = r10.f
            r8 = 0
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 <= 0) goto L77
            com.byd.cleardata.h.m r1 = new com.byd.cleardata.h.m
            long r8 = r10.e
            r1.<init>(r8, r6)
            r11.c(r1)
            java.io.File r1 = r10.w()
            if (r1 == 0) goto L7b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "zipFile = "
            r4.append(r5)
            java.lang.String r5 = r1.getAbsolutePath()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.byd.cleardata.i.c.b(r4)
            java.util.List<com.byd.cleardata.d.d> r4 = r10.f2664c
            com.byd.cleardata.h.a$a r5 = new com.byd.cleardata.h.a$a
            r5.<init>(r11)
            boolean r5 = r10.s(r1, r4, r5)
            goto L7b
        L77:
            com.byd.cleardata.i.c.b(r4)
            r1 = 0
        L7b:
            if (r5 == 0) goto L8f
            com.byd.cleardata.d.d r2 = new com.byd.cleardata.d.d
            r2.<init>(r1)
            r0.add(r2)
            com.byd.cleardata.h.m r1 = new com.byd.cleardata.h.m
            r2 = 1
            r1.<init>(r2, r0)
        L8b:
            r11.c(r1)
            goto Lad
        L8f:
            com.byd.cleardata.i.e.e(r1)
            boolean r1 = r10.o()
            if (r1 == 0) goto La1
            com.byd.cleardata.i.c.b(r3)
            com.byd.cleardata.h.m r1 = new com.byd.cleardata.h.m
            r1.<init>(r2, r0)
            goto L8b
        La1:
            java.lang.String r1 = "compress error"
            com.byd.cleardata.i.c.b(r1)
            com.byd.cleardata.h.m r1 = new com.byd.cleardata.h.m
            r2 = 4
            r1.<init>(r2, r0)
            goto L8b
        Lad:
            java.lang.String r11 = "compress end"
            com.byd.cleardata.i.c.b(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.cleardata.h.a.m(c.a.g):void");
    }

    public boolean s(File file, List<com.byd.cleardata.d.d> list, g gVar) {
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new CheckedOutputStream(new FileOutputStream(file), new CRC32()));
            try {
                for (com.byd.cleardata.d.d dVar : list) {
                    if (o()) {
                        zipOutputStream.close();
                        return false;
                    }
                    if (dVar == null || !dVar.u()) {
                        zipOutputStream.close();
                        return false;
                    }
                    if (!t(dVar.w(), zipOutputStream, "", gVar)) {
                        zipOutputStream.close();
                        return false;
                    }
                }
                zipOutputStream.close();
                return true;
            } finally {
            }
        } catch (Throwable th) {
            com.byd.cleardata.i.c.d("compress error", th);
            return false;
        }
    }
}
