package com.byd.filemanager.task;

import android.annotation.SuppressLint;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.k.b;
import d.a.e;
import d.a.i;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class FileOperateTask extends e<Response> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected i<? super Response> f3664b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected String f3663a = getClass().getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected AtomicBoolean f3665c = new AtomicBoolean(false);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected AtomicBoolean f3666d = new AtomicBoolean(false);

    @Override // d.a.e
    protected void j(i<? super Response> iVar) {
        this.f3664b = iVar;
        try {
            n(iVar);
        } catch (Throwable th) {
            iVar.onError(th);
        }
        iVar.onComplete();
    }

    public void l() {
        this.f3665c.set(true);
    }

    public void m() {
        this.f3666d.set(true);
        this.f3665c.set(true);
    }

    public abstract void n(i<? super Response> iVar);

    @SuppressLint({"NewApi"})
    protected long o(List<FileItem> list) {
        if (list == null) {
            return 0L;
        }
        long j = 0;
        for (FileItem fileItem : list) {
            if (p()) {
                return 0L;
            }
            long jB = fileItem.B();
            if (jB < 0) {
                return 0L;
            }
            if (jB == 0) {
                jB = 1;
            }
            j += jB;
        }
        return j;
    }

    public boolean p() {
        if (this.f3665c.get()) {
            b.c(this.f3663a, "cancelTask");
        }
        return this.f3665c.get();
    }

    public boolean q() {
        if (this.f3666d.get()) {
            b.c(this.f3663a, "cancelTask");
        }
        return this.f3666d.get();
    }
}
