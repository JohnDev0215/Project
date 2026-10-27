package com.byd.filemanager.i.c;

import android.text.TextUtils;
import com.byd.filemanager.ThreadManager;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileType;
import com.byd.filemanager.task.FileOperateTask;
import com.byd.filemanager.task.FileOperateTasks;
import com.byd.filemanager.task.LoadPathTask;
import com.byd.filemanager.task.Response;
import java.util.List;

/* JADX INFO: compiled from: MainPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends com.byd.filemanager.i.a.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f3573d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final io.reactivex.disposables.a f3574e;
    private com.byd.filemanager.k.p f;
    private FileOperateTask g;

    /* JADX INFO: compiled from: MainPresenter.java */
    class a implements d.a.m.e<io.reactivex.disposables.b> {
        a() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(io.reactivex.disposables.b bVar) throws Exception {
            ((com.byd.filemanager.i.a.c) b.this.f3570a).k(7);
        }
    }

    /* JADX INFO: renamed from: com.byd.filemanager.i.c.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MainPresenter.java */
    class C0084b implements d.a.m.e<Response> {
        C0084b() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Response response) throws Exception {
            if (response.d() == 2) {
                b.this.z(response.c(), response.e());
            } else {
                b.this.v(1, response.d(), response.b());
            }
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class c implements d.a.m.e<io.reactivex.disposables.b> {
        c() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(io.reactivex.disposables.b bVar) throws Exception {
            ((com.byd.filemanager.i.a.c) b.this.f3570a).k(1);
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class d implements d.a.m.e<Response> {
        d() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Response response) throws Exception {
            if (response.d() == 2) {
                b.this.z(response.c(), response.e());
            } else {
                b.this.v(4, response.d(), response.b());
            }
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class e implements d.a.m.e<io.reactivex.disposables.b> {
        e() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(io.reactivex.disposables.b bVar) throws Exception {
            ((com.byd.filemanager.i.a.c) b.this.f3570a).k(4);
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class f implements d.a.m.e<Response> {
        f() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Response response) throws Exception {
            if (response.d() == 2) {
                b.this.z(response.c(), response.e());
            } else {
                b.this.v(5, response.d(), response.b());
            }
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class g implements d.a.m.e<io.reactivex.disposables.b> {
        g() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(io.reactivex.disposables.b bVar) throws Exception {
            ((com.byd.filemanager.i.a.c) b.this.f3570a).k(5);
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class h implements d.a.m.e<Response> {
        h() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Response response) throws Exception {
            if (response.d() == 2) {
                b.this.z(response.c(), response.e());
            } else {
                b.this.v(6, response.d(), response.b());
            }
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class i implements d.a.m.e<io.reactivex.disposables.b> {
        i() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(io.reactivex.disposables.b bVar) throws Exception {
            ((com.byd.filemanager.i.a.c) b.this.f3570a).k(6);
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class j implements d.a.m.e<FileItem> {
        j() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(FileItem fileItem) throws Exception {
            if (fileItem.u()) {
                b.this.y(0, fileItem);
            } else {
                b.this.w(0, false);
            }
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class k implements d.a.m.e<FileItem> {
        k() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(FileItem fileItem) throws Exception {
            if (fileItem.u()) {
                b.this.y(2, fileItem);
            } else {
                b.this.w(2, false);
            }
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class l implements d.a.m.e<Response> {
        l() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Response response) throws Exception {
            b.this.v(3, response.d(), response.b());
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class m implements d.a.m.e<io.reactivex.disposables.b> {
        m() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(io.reactivex.disposables.b bVar) throws Exception {
            ((com.byd.filemanager.i.a.c) b.this.f3570a).k(3);
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class n implements d.a.m.e<Response> {
        n() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Response response) throws Exception {
            b.this.v(3, response.d(), response.b());
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class o implements d.a.m.e<io.reactivex.disposables.b> {
        o() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(io.reactivex.disposables.b bVar) throws Exception {
            ((com.byd.filemanager.i.a.c) b.this.f3570a).k(3);
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class p implements d.a.m.e<Response> {
        p() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Response response) throws Exception {
            b.this.v(16, response.d(), response.b());
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class q implements d.a.m.e<io.reactivex.disposables.b> {
        q() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(io.reactivex.disposables.b bVar) throws Exception {
            ((com.byd.filemanager.i.a.c) b.this.f3570a).k(16);
        }
    }

    /* JADX INFO: compiled from: MainPresenter.java */
    class r implements d.a.i<Boolean> {
        r() {
        }

        @Override // d.a.i
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onNext(Boolean bool) {
            b.this.w(7, bool.booleanValue());
        }

        @Override // d.a.i
        public void onComplete() {
        }

        @Override // d.a.i
        public void onError(Throwable th) {
        }

        @Override // d.a.i
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            b.this.f3574e.c(bVar);
        }
    }

    public b(com.byd.filemanager.i.a.c cVar) {
        super(cVar);
        this.f3573d = "MainPresenter";
        this.f3574e = new io.reactivex.disposables.a();
        this.f = new com.byd.filemanager.k.p();
    }

    private boolean s(List<?> list) {
        return (list == null || list.isEmpty()) ? false : true;
    }

    private boolean t(int i2) {
        int iK = com.byd.filemanager.f.h().k();
        boolean zD = com.byd.filemanager.service.b.c().d(iK);
        com.byd.filemanager.k.b.c("MainPresenter", i2 + " isScanning uiPos=" + iK + ",scanning=" + zD);
        if (!zD || i2 == 1) {
            return false;
        }
        V v = this.f3570a;
        if (v != 0) {
            ((com.byd.filemanager.i.a.c) v).k(3);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(int i2, int i3, List<FileItem> list) {
        V v = this.f3570a;
        if (v != 0) {
            ((com.byd.filemanager.i.a.c) v).n(i2, i3, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(int i2, boolean z) {
        x(i2, z, false);
    }

    private void x(int i2, boolean z, boolean z2) {
        V v = this.f3570a;
        if (v != 0) {
            ((com.byd.filemanager.i.a.c) v).r(i2, z, z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(int i2, FileItem fileItem) {
        V v = this.f3570a;
        if (v != 0) {
            ((com.byd.filemanager.i.a.c) v).l(i2, fileItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(long j2, long j3) {
        V v = this.f3570a;
        if (v != 0) {
            ((com.byd.filemanager.i.a.c) v).g(j2, j3);
        }
    }

    public void A(FileType fileType) {
        if (t(5)) {
            return;
        }
        n();
        FileOperateTask fileOperateTaskD = FileOperateTasks.d(0, fileType);
        this.g = fileOperateTaskD;
        this.f3574e.c(fileOperateTaskD.k(ThreadManager.h().i()).e(new q()).k(d.a.l.b.a.a()).f(d.a.l.b.a.a()).h(new p()));
    }

    public void B(int i2, int i3) {
        com.byd.filemanager.k.b.c("MainPresenter", "queryFiles type = " + i2 + " ,medium = " + i3);
        if (t(i2)) {
            return;
        }
        n();
        if (i2 != 1) {
            FileOperateTask fileOperateTaskF = FileOperateTasks.f(i2, i3);
            this.g = fileOperateTaskF;
            this.f3574e.c(fileOperateTaskF.k(ThreadManager.h().i()).f(d.a.l.b.a.a()).e(new m()).h(new l()));
            return;
        }
        String strK = com.byd.filemanager.e.f().k(i3);
        com.byd.filemanager.k.b.b("queryFiles path:" + strK);
        if (TextUtils.isEmpty(strK)) {
            v(3, 4, null);
        } else {
            C(i2, strK);
        }
    }

    public void C(int i2, String str) {
        com.byd.filemanager.k.b.c("MainPresenter", "queryFiles type = " + i2 + " ,path = " + str);
        if (t(i2)) {
            return;
        }
        n();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LoadPathTask loadPathTaskG = FileOperateTasks.g(str);
        this.g = loadPathTaskG;
        this.f3574e.c(loadPathTaskG.k(ThreadManager.h().i()).f(d.a.l.b.a.a()).e(new o()).h(new n()));
    }

    public void D(FileItem fileItem, String str) {
        if (fileItem == null || TextUtils.isEmpty(str)) {
            return;
        }
        com.byd.filemanager.k.b.b("renameFile name = " + str);
        this.f3574e.c(com.byd.filemanager.j.d.d(fileItem, str).k(d.a.p.a.c()).f(d.a.l.b.a.a()).h(new k()));
    }

    public void E(int i2, List<FileItem> list) {
        if (s(list)) {
            com.byd.filemanager.j.d.e(i2, list).k(d.a.p.a.a()).e(new a()).k(d.a.l.b.a.a()).f(d.a.l.b.a.a()).a(new r());
        } else {
            w(7, true);
        }
    }

    @Override // com.byd.filemanager.i.c.a
    public void b() {
        super.b();
        boolean zD = d();
        com.byd.filemanager.k.b.b("isRestore = " + zD);
        if (zD) {
            return;
        }
        this.f.a();
        this.f3574e.dispose();
    }

    public void k(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.f3574e.c(com.byd.filemanager.j.d.a(str, str2).k(ThreadManager.h().i()).f(d.a.l.b.a.a()).h(new j()));
    }

    public void l() {
        FileOperateTask fileOperateTask = this.g;
        if (fileOperateTask != null) {
            fileOperateTask.l();
            this.g = null;
        }
        this.f.a();
    }

    public void m() {
        FileOperateTask fileOperateTask = this.g;
        if (fileOperateTask != null) {
            fileOperateTask.m();
            this.g = null;
        }
        this.f.a();
    }

    public void n() {
        l();
        this.f3574e.d();
    }

    public void o(List<FileItem> list, String str) {
        com.byd.filemanager.k.b.b("compressFiles");
        n();
        if (TextUtils.isEmpty(str) || !s(list)) {
            w(6, false);
            return;
        }
        FileOperateTask fileOperateTaskA = FileOperateTasks.a(list, str);
        this.g = fileOperateTaskA;
        this.f3574e.c(fileOperateTaskA.k(d.a.p.a.d()).e(new i()).f(d.a.l.b.a.a()).h(new h()));
    }

    public void p(List<FileItem> list, String str) {
        n();
        if (TextUtils.isEmpty(str) || !s(list)) {
            w(4, false);
            return;
        }
        FileOperateTask fileOperateTaskB = FileOperateTasks.b(list, str);
        this.g = fileOperateTaskB;
        this.f3574e.c(fileOperateTaskB.k(d.a.p.a.d()).e(new e()).f(d.a.l.b.a.a()).h(new d()));
    }

    public void q(List<FileItem> list) {
        n();
        if (!s(list)) {
            w(1, false);
            return;
        }
        FileOperateTask fileOperateTaskC = FileOperateTasks.c(list);
        this.g = fileOperateTaskC;
        this.f3574e.c(fileOperateTaskC.k(ThreadManager.h().i()).e(new c()).f(d.a.l.b.a.a()).h(new C0084b()));
    }

    @Override // com.byd.filemanager.i.c.a
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public com.byd.filemanager.i.a.a c() {
        return new com.byd.filemanager.i.b.a();
    }

    public void u(List<FileItem> list, String str) {
        n();
        if (TextUtils.isEmpty(str) || !s(list)) {
            w(5, false);
            return;
        }
        FileOperateTask fileOperateTaskH = FileOperateTasks.h(list, str);
        this.g = fileOperateTaskH;
        this.f3574e.c(fileOperateTaskH.k(d.a.p.a.d()).e(new g()).f(d.a.l.b.a.a()).h(new f()));
    }
}
