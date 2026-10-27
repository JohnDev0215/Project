package com.byd.filemanager.task;

import android.text.TextUtils;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.k.b;
import com.byd.filemanager.k.d;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MoveTask extends CopyTask {
    public MoveTask(List<FileItem> list, String str) {
        super(list, str);
    }

    private boolean v(String str, String str2, IFileOperateListener iFileOperateListener) {
        b.b("moveFile from = " + str + ", to = " + str2);
        if (z(str, str2, iFileOperateListener)) {
            return true;
        }
        return d.G(str) ? x(str, str2, iFileOperateListener) : y(str, str2, iFileOperateListener);
    }

    private boolean w(List<FileItem> list, IFileOperateListener iFileOperateListener) {
        while (true) {
            boolean z = false;
            for (FileItem fileItem : this.f3657e) {
                if (p()) {
                    return false;
                }
                if (fileItem == null || !fileItem.u()) {
                    break;
                }
                if (fileItem.M(this.f)) {
                    b.b("file is father of toPath");
                    return false;
                }
                if (fileItem.D().equals(this.f)) {
                    return true;
                }
                String strS = d.s(this.f, fileItem.C());
                if (strS == null) {
                    b.b("create dest file failed");
                    return false;
                }
                String strE = fileItem.E();
                boolean zV = v(strE, strS, iFileOperateListener);
                if (!zV) {
                    b.b("move error");
                    if (d.h(strS)) {
                        if (d.G(strS)) {
                            list.add(FileItem.z(strS));
                        } else {
                            d.e(strS);
                        }
                    }
                    return zV;
                }
                com.byd.filemanager.service.b.c().m(strE, strS);
                list.add(FileItem.z(strS));
                z = zV;
            }
            return z;
        }
    }

    private boolean x(String str, String str2, IFileOperateListener iFileOperateListener) {
        b.b("moveDirectory from = " + str + ", to = " + str2);
        if (d.G(str)) {
            File file = new File(str);
            if (new File(str2).mkdir()) {
                iFileOperateListener.a(4096L);
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        if (p()) {
                            b.b("moveDirectory cancel");
                            return false;
                        }
                        if (!v(file2.getAbsolutePath(), str2 + File.separator + file2.getName(), iFileOperateListener)) {
                            return false;
                        }
                    }
                    d.e(str);
                    return true;
                }
            } else {
                b.b("moveDirectory mkdir error");
            }
        }
        return false;
    }

    private boolean y(String str, String str2, IFileOperateListener iFileOperateListener) {
        b.b("moveFileAsCopy");
        boolean zU = u(str, str2, iFileOperateListener);
        if (zU) {
            d.e(str);
        }
        return zU;
    }

    private boolean z(String str, String str2, IFileOperateListener iFileOperateListener) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        boolean zRenameTo = new File(str).renameTo(new File(str2));
        b.b("isRenamed = " + zRenameTo);
        if (!zRenameTo) {
            return false;
        }
        iFileOperateListener.a(d.k(str2));
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    @Override // com.byd.filemanager.task.CopyTask, com.byd.filemanager.task.FileOperateTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void n(final d.a.i<? super com.byd.filemanager.task.Response> r9) {
        /*
            r8 = this;
            java.lang.String r0 = "move start"
            com.byd.filemanager.k.b.b(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = r8.f
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            r2 = 3
            java.lang.String r3 = "move cancel"
            if (r1 != 0) goto L5f
            java.util.List<com.byd.filemanager.bean.FileItem> r1 = r8.f3657e
            if (r1 == 0) goto L5f
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L5f
            java.util.List<com.byd.filemanager.bean.FileItem> r1 = r8.f3657e
            long r4 = r8.o(r1)
            r8.h = r4
            boolean r1 = r8.p()
            if (r1 == 0) goto L38
            com.byd.filemanager.k.b.b(r3)
            com.byd.filemanager.task.Response r1 = new com.byd.filemanager.task.Response
            r1.<init>(r2, r0)
            r9.onNext(r1)
        L38:
            long r4 = r8.h
            r6 = 0
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 <= 0) goto L5f
            com.byd.filemanager.task.Response r1 = new com.byd.filemanager.task.Response
            long r6 = r8.g
            r1.<init>(r6, r4)
            r9.onNext(r1)
            com.byd.filemanager.task.MoveTask$1 r1 = new com.byd.filemanager.task.MoveTask$1
            r1.<init>()
            boolean r1 = r8.w(r0, r1)
            if (r1 == 0) goto L60
            com.byd.filemanager.task.Response r4 = new com.byd.filemanager.task.Response
            r5 = 1
            r4.<init>(r5, r0)
            r9.onNext(r4)
            goto L60
        L5f:
            r1 = 0
        L60:
            if (r1 != 0) goto L82
            boolean r1 = r8.p()
            if (r1 == 0) goto L74
            com.byd.filemanager.k.b.b(r3)
            com.byd.filemanager.task.Response r1 = new com.byd.filemanager.task.Response
            r1.<init>(r2, r0)
            r9.onNext(r1)
            goto L82
        L74:
            java.lang.String r1 = "move error or source is empty"
            com.byd.filemanager.k.b.b(r1)
            com.byd.filemanager.task.Response r1 = new com.byd.filemanager.task.Response
            r2 = 4
            r1.<init>(r2, r0)
            r9.onNext(r1)
        L82:
            java.lang.String r9 = "move end"
            com.byd.filemanager.k.b.b(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.filemanager.task.MoveTask.n(d.a.i):void");
    }
}
