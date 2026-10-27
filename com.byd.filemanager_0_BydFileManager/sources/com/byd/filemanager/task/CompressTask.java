package com.byd.filemanager.task;

import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.k.b;
import com.byd.filemanager.k.d;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class CompressTask extends FileOperateTask {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<FileItem> f3654e;
    private final String f;
    private long g = 0;
    private long h = 0;
    private List<String> i;
    private List<String> j;

    public CompressTask(List<FileItem> list, String str) {
        this.f3654e = list;
        this.f = str;
    }

    private File A() {
        String name = this.f3654e.get(0).w().getName();
        int iLastIndexOf = name.lastIndexOf(".");
        StringBuilder sb = new StringBuilder();
        if (iLastIndexOf == -1) {
            iLastIndexOf = name.length();
        }
        sb.append(name.substring(0, iLastIndexOf));
        sb.append(".zip");
        String strT = d.t(this.f, sb.toString());
        if (strT == null) {
            return null;
        }
        File file = new File(this.f + File.separator + strT);
        try {
            if (file.createNewFile()) {
                return file;
            }
            return null;
        } catch (IOException e2) {
            b.d("getZipFile error", e2);
            return null;
        }
    }

    static /* synthetic */ long s(CompressTask compressTask, long j) {
        long j2 = compressTask.g + j;
        compressTask.g = j2;
        return j2;
    }

    private void u() {
        this.i = new ArrayList();
        this.j = new ArrayList();
        for (int i = 0; i < this.f3654e.size(); i++) {
            FileItem fileItem = this.f3654e.get(i);
            if (fileItem != null) {
                this.j.add(fileItem.C());
            }
        }
    }

    private boolean w(File file, ZipOutputStream zipOutputStream, String str, IFileOperateListener iFileOperateListener) {
        return file.isDirectory() ? x(file, zipOutputStream, str, iFileOperateListener) : y(file, zipOutputStream, str, iFileOperateListener);
    }

    private boolean x(File file, ZipOutputStream zipOutputStream, String str, IFileOperateListener iFileOperateListener) {
        File[] fileArrListFiles;
        if (file == null || !file.exists() || !file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
            return false;
        }
        for (File file2 : fileArrListFiles) {
            if (p()) {
                b.b("compressDirectory cancel");
                return false;
            }
            if (!w(file2, zipOutputStream, str + file.getName() + File.separator, iFileOperateListener)) {
                return false;
            }
        }
        return true;
    }

    private boolean y(File file, ZipOutputStream zipOutputStream, String str, IFileOperateListener iFileOperateListener) {
        b.b("compressFile from " + file);
        if (d.g(file)) {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    String str2 = str + file.getName();
                    String strZ = z(str2);
                    b.b("name:" + str2 + " nameNew:" + strZ);
                    zipOutputStream.putNextEntry(new ZipEntry(strZ));
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int i = bufferedInputStream.read(bArr, 0, 8192);
                        if (i == -1) {
                            zipOutputStream.closeEntry();
                            bufferedInputStream.close();
                            return true;
                        }
                        if (p()) {
                            b.b("compressFile cancel");
                            bufferedInputStream.close();
                            return false;
                        }
                        zipOutputStream.write(bArr, 0, i);
                        if (!file.getAbsolutePath().startsWith("/storage/emulated/0")) {
                            zipOutputStream.flush();
                        }
                        List<String> list = this.i;
                        if (list != null) {
                            list.add(strZ);
                        }
                        iFileOperateListener.a(i);
                    }
                } finally {
                }
            } catch (Throwable th) {
                b.d("compressFile error", th);
            }
        }
        return false;
    }

    private String z(String str) {
        List<String> list = this.i;
        if (list == null || list.size() <= 0 || !this.i.contains(str)) {
            return str;
        }
        String[] strArrM = d.m(str);
        String str2 = strArrM[0];
        String str3 = strArrM[1];
        String str4 = str2 + str3;
        List<String> list2 = this.j;
        if (list2 != null && list2.size() > 0) {
            for (int i = 1; i < Integer.MAX_VALUE; i++) {
                str4 = str2 + '(' + i + ')' + str3;
                if (!this.j.contains(str4) && !this.i.contains(str4)) {
                    return str4;
                }
            }
        }
        return str4;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0096  */
    @Override // com.byd.filemanager.task.FileOperateTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void n(final d.a.i<? super com.byd.filemanager.task.Response> r11) {
        /*
            r10 = this;
            java.lang.String r0 = "compress start"
            com.byd.filemanager.k.b.b(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = r10.f
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            r2 = 3
            java.lang.String r3 = "compress cancel"
            java.lang.String r4 = "source is empty"
            r5 = 0
            if (r1 != 0) goto L7e
            java.util.List<com.byd.filemanager.bean.FileItem> r1 = r10.f3654e
            if (r1 == 0) goto L7e
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L7e
            java.util.List<com.byd.filemanager.bean.FileItem> r1 = r10.f3654e
            long r6 = r10.o(r1)
            r10.h = r6
            boolean r1 = r10.p()
            if (r1 == 0) goto L3b
            com.byd.filemanager.k.b.b(r3)
            com.byd.filemanager.task.Response r1 = new com.byd.filemanager.task.Response
            r1.<init>(r2, r0)
            r11.onNext(r1)
        L3b:
            long r6 = r10.h
            r8 = 0
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 <= 0) goto L7a
            com.byd.filemanager.task.Response r1 = new com.byd.filemanager.task.Response
            long r8 = r10.g
            r1.<init>(r8, r6)
            r11.onNext(r1)
            java.io.File r1 = r10.A()
            if (r1 == 0) goto L82
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "zipFile = "
            r4.append(r5)
            java.lang.String r5 = r1.getAbsolutePath()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.byd.filemanager.k.b.b(r4)
            r10.u()
            java.util.List<com.byd.filemanager.bean.FileItem> r4 = r10.f3654e
            com.byd.filemanager.task.CompressTask$1 r5 = new com.byd.filemanager.task.CompressTask$1
            r5.<init>()
            boolean r5 = r10.v(r1, r4, r5)
            goto L82
        L7a:
            com.byd.filemanager.k.b.b(r4)
            goto L81
        L7e:
            com.byd.filemanager.k.b.b(r4)
        L81:
            r1 = 0
        L82:
            if (r5 == 0) goto L96
            com.byd.filemanager.bean.FileItem r2 = new com.byd.filemanager.bean.FileItem
            r2.<init>(r1)
            r0.add(r2)
            com.byd.filemanager.task.Response r1 = new com.byd.filemanager.task.Response
            r2 = 1
            r1.<init>(r2, r0)
            r11.onNext(r1)
            goto Lbf
        L96:
            com.byd.filemanager.k.d.d(r1)
            boolean r1 = r10.p()
            if (r1 == 0) goto Lb1
            boolean r1 = r10.q()
            if (r1 != 0) goto Lb1
            com.byd.filemanager.k.b.b(r3)
            com.byd.filemanager.task.Response r1 = new com.byd.filemanager.task.Response
            r1.<init>(r2, r0)
            r11.onNext(r1)
            goto Lbf
        Lb1:
            java.lang.String r1 = "compress error"
            com.byd.filemanager.k.b.b(r1)
            com.byd.filemanager.task.Response r1 = new com.byd.filemanager.task.Response
            r2 = 4
            r1.<init>(r2, r0)
            r11.onNext(r1)
        Lbf:
            java.lang.String r11 = "compress end"
            com.byd.filemanager.k.b.b(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.filemanager.task.CompressTask.n(d.a.i):void");
    }

    public boolean v(File file, List<FileItem> list, IFileOperateListener iFileOperateListener) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
                try {
                    for (FileItem fileItem : list) {
                        if (p()) {
                            zipOutputStream.close();
                            fileOutputStream.close();
                            return false;
                        }
                        if (fileItem == null || !fileItem.u()) {
                            zipOutputStream.close();
                            fileOutputStream.close();
                            List<String> list2 = this.i;
                            if (list2 != null) {
                                list2.clear();
                            }
                            List<String> list3 = this.j;
                            if (list3 != null) {
                                list3.clear();
                            }
                            b.g("执行了finallly");
                            return false;
                        }
                        if (!w(fileItem.w(), zipOutputStream, "", iFileOperateListener)) {
                            zipOutputStream.close();
                            fileOutputStream.close();
                            List<String> list4 = this.i;
                            if (list4 != null) {
                                list4.clear();
                            }
                            List<String> list5 = this.j;
                            if (list5 != null) {
                                list5.clear();
                            }
                            b.g("执行了finallly");
                            return false;
                        }
                    }
                    zipOutputStream.close();
                    fileOutputStream.close();
                    List<String> list6 = this.i;
                    if (list6 != null) {
                        list6.clear();
                    }
                    List<String> list7 = this.j;
                    if (list7 != null) {
                        list7.clear();
                    }
                    b.g("执行了finallly");
                    return true;
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                b.d("compress error", th);
                List<String> list8 = this.i;
                if (list8 != null) {
                    list8.clear();
                }
                List<String> list9 = this.j;
                if (list9 != null) {
                    list9.clear();
                }
                b.g("执行了finallly");
                return false;
            } finally {
                List<String> list10 = this.i;
                if (list10 != null) {
                    list10.clear();
                }
                List<String> list11 = this.j;
                if (list11 != null) {
                    list11.clear();
                }
                b.g("执行了finallly");
            }
        }
    }
}
