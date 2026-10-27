package com.byd.filemanager.i.c;

import com.byd.filemanager.i.a.d;
import com.byd.filemanager.i.a.e;
import com.byd.filemanager.i.a.f;
import com.byd.filemanager.task.FileOperateTask;
import com.byd.filemanager.task.FileOperateTasks;
import com.byd.filemanager.task.Response;

/* JADX INFO: compiled from: SearchPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private io.reactivex.disposables.b f3593d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private FileOperateTask f3594e;

    /* JADX INFO: compiled from: SearchPresenter.java */
    class a implements d.a.m.e<Response> {
        a() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Response response) throws Exception {
            if (response.d() == 2) {
                V v = c.this.f3570a;
                if (v != 0) {
                    ((f) v).f(response.a());
                    return;
                }
                return;
            }
            V v2 = c.this.f3570a;
            if (v2 != 0) {
                ((f) v2).e();
            }
        }
    }

    /* JADX INFO: compiled from: SearchPresenter.java */
    class b implements d.a.m.e<io.reactivex.disposables.b> {
        b() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(io.reactivex.disposables.b bVar) throws Exception {
        }
    }

    public c(f fVar) {
        super(fVar);
    }

    @Override // com.byd.filemanager.i.c.a
    public void b() {
        super.b();
        io.reactivex.disposables.b bVar = this.f3593d;
        if (bVar != null) {
            bVar.dispose();
        }
        FileOperateTask fileOperateTask = this.f3594e;
        if (fileOperateTask != null) {
            fileOperateTask.l();
        }
    }

    public void f() {
        FileOperateTask fileOperateTask = this.f3594e;
        if (fileOperateTask != null) {
            fileOperateTask.l();
        }
    }

    @Override // com.byd.filemanager.i.c.a
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public d c() {
        return new com.byd.filemanager.i.b.c(this);
    }

    public void h(String str) {
        com.byd.filemanager.k.b.b("searchFiles key = " + str);
        f();
        FileOperateTask fileOperateTaskE = FileOperateTasks.e(str);
        this.f3594e = fileOperateTaskE;
        this.f3593d = fileOperateTaskE.k(d.a.p.a.a()).e(new b()).f(d.a.l.b.a.a()).h(new a());
    }
}
