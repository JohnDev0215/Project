package com.byd.filemanager.task;

import android.text.TextUtils;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.k.b;
import com.byd.filemanager.k.d;
import d.a.i;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CopyTask extends FileOperateTask {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected final List<FileItem> f3657e;
    protected final String f;
    protected long g = 0;
    protected long h = 0;

    public CopyTask(List<FileItem> list, String str) {
        this.f3657e = list != null ? new ArrayList(list) : null;
        this.f = str;
    }

    private boolean r(String str, String str2, IFileOperateListener iFileOperateListener) {
        b.c(this.f3663a, "copy from =" + str + ",to =" + str2);
        return d.G(str) ? t(str, str2, iFileOperateListener) : u(str, str2, iFileOperateListener);
    }

    private boolean s(List<FileItem> list, IFileOperateListener iFileOperateListener) {
        boolean zR;
        Iterator<FileItem> it = this.f3657e.iterator();
        loop0: while (true) {
            zR = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                FileItem next = it.next();
                b.b("copy a file");
                if (next == null || !next.u()) {
                    break;
                }
                if (next.M(this.f)) {
                    b.c(this.f3663a, "file is father of toPath=" + this.f + ",sourcePath=" + next.E());
                    return false;
                }
                String strS = d.s(this.f, next.C());
                if (strS == null) {
                    b.c(this.f3663a, "create dest file failed");
                    return false;
                }
                zR = r(next.E(), strS, iFileOperateListener);
                if (zR) {
                    list.add(FileItem.z(strS));
                } else {
                    b.c(this.f3663a, "copy error");
                    if (d.G(strS) && d.h(strS)) {
                        list.add(FileItem.z(strS));
                    }
                }
            }
        }
        return zR;
    }

    private boolean t(String str, String str2, IFileOperateListener iFileOperateListener) {
        b.c(this.f3663a, "copyDirectory from = " + str + ", to =" + str2);
        if (d.G(str)) {
            File file = new File(str);
            if (new File(str2).mkdir()) {
                iFileOperateListener.a(4096L);
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        if (p()) {
                            b.c(this.f3663a, "copyDirectory cancel");
                            return false;
                        }
                        if (!r(file2.getAbsolutePath(), str2 + File.separator + file2.getName(), iFileOperateListener)) {
                            return false;
                        }
                    }
                }
                return true;
            }
            b.c(this.f3663a, "copyDirectory mkdir error");
        }
        return false;
    }

    @Override // com.byd.filemanager.task.FileOperateTask
    public void n(final i<? super Response> iVar) {
        List<FileItem> list;
        b.c(this.f3663a, "copy start");
        ArrayList arrayList = new ArrayList();
        boolean zS = false;
        if (!TextUtils.isEmpty(this.f) && (list = this.f3657e) != null && !list.isEmpty()) {
            b.e(this.f3663a, "copy start", this.f, Integer.valueOf(this.f3657e.size()));
            this.h = o(this.f3657e);
            b.c(this.f3663a, "total = " + this.h);
            if (p()) {
                b.c(this.f3663a, "copy cancel");
                iVar.onNext(new Response(3, arrayList));
            }
            long j = this.h;
            if (j > 0) {
                iVar.onNext(new Response(this.g, j));
                zS = s(arrayList, new IFileOperateListener() { // from class: com.byd.filemanager.task.CopyTask.1
                    @Override // com.byd.filemanager.task.IFileOperateListener
                    public void a(long j2) {
                        CopyTask copyTask = CopyTask.this;
                        long j3 = copyTask.g + j2;
                        copyTask.g = j3;
                        iVar.onNext(new Response(j3, copyTask.h));
                    }
                });
                if (zS) {
                    iVar.onNext(new Response(1, arrayList));
                }
            }
        }
        if (!zS) {
            if (p()) {
                b.c(this.f3663a, "copy cancel");
                iVar.onNext(new Response(3, arrayList));
            } else {
                b.c(this.f3663a, "copy error or source is empty");
                iVar.onNext(new Response(4, arrayList));
            }
        }
        b.b("copy end");
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x00e6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:? A[Catch: IOException -> 0x00f0, DONT_GENERATE, FINALLY_INSNS, SYNTHETIC, TRY_LEAVE, TryCatch #4 {IOException -> 0x00f0, blocks: (B:16:0x0063, B:26:0x0095, B:40:0x00d0, B:59:0x00ef, B:58:0x00ec, B:17:0x006c, B:25:0x0092, B:39:0x00cd, B:50:0x00e1, B:49:0x00de, B:18:0x0075, B:21:0x0082, B:23:0x0088, B:29:0x009c, B:31:0x00b7, B:32:0x00ba, B:34:0x00c2, B:36:0x00c7, B:45:0x00d8, B:54:0x00e6), top: B:70:0x0063, inners: #0, #3 }] */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean u(java.lang.String r25, java.lang.String r26, com.byd.filemanager.task.IFileOperateListener r27) {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.filemanager.task.CopyTask.u(java.lang.String, java.lang.String, com.byd.filemanager.task.IFileOperateListener):boolean");
    }
}
