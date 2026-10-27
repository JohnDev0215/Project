package com.byd.filemanager.task;

import android.annotation.SuppressLint;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.k.b;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DeleteTask extends FileOperateTask {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected long f3660e = 0;
    protected long f = 0;
    private List<FileItem> g;
    private IFileOperateListener h;

    public DeleteTask(List<FileItem> list) {
        this.g = list != null ? new ArrayList(list) : null;
    }

    private boolean r(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                return s(file);
            }
            if (t(file)) {
                this.h.a(1L);
                return true;
            }
        }
        return false;
    }

    private boolean s(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            boolean zDelete = file.delete();
            this.h.a(1L);
            b.b("listFiles is null, delete dir result:" + zDelete);
            return zDelete;
        }
        for (File file2 : fileArrListFiles) {
            if (p()) {
                b.b("deleteDirectory cancel");
                return false;
            }
            if (!r(file2)) {
                return false;
            }
        }
        if (!t(file)) {
            return false;
        }
        this.h.a(1L);
        return true;
    }

    private boolean t(File file) {
        if (file == null) {
            b.b("file is null");
            return false;
        }
        if (file.exists()) {
            return file.delete();
        }
        b.b("file exists:false");
        return false;
    }

    @SuppressLint({"NewApi"})
    private long u() {
        long jX = 0;
        for (FileItem fileItem : this.g) {
            if (p()) {
                return 0L;
            }
            jX += fileItem.x();
        }
        return jX;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x009a  */
    @Override // com.byd.filemanager.task.FileOperateTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void n(final d.a.i<? super com.byd.filemanager.task.Response> r10) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.filemanager.task.DeleteTask.n(d.a.i):void");
    }
}
