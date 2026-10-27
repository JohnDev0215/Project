package com.byd.filemanager.j;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.text.TextUtils;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.k.k;
import com.byd.filemanager.k.l;
import d.a.f;
import d.a.g;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: compiled from: Observables.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: compiled from: Observables.java */
    class a implements g<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ FileItem f3599a;

        a(FileItem fileItem) {
            this.f3599a = fileItem;
        }

        @Override // d.a.g
        public void a(f<String> fVar) throws Exception {
            com.byd.filemanager.k.b.b("getFilesCount start");
            fVar.onNext(com.byd.filemanager.k.d.x(this.f3599a.B()));
            fVar.onComplete();
        }
    }

    /* JADX INFO: compiled from: Observables.java */
    class b implements g<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ FileItem f3600a;

        b(FileItem fileItem) {
            this.f3600a = fileItem;
        }

        @Override // d.a.g
        public void a(f<String> fVar) throws Exception {
            try {
                String strV = com.byd.filemanager.k.d.v(this.f3600a.w());
                com.byd.filemanager.k.b.b("location = " + strV);
                if (!TextUtils.isEmpty(strV)) {
                    fVar.onNext(strV);
                }
                fVar.onComplete();
            } catch (Throwable th) {
                th.printStackTrace();
                fVar.onError(th);
            }
        }
    }

    /* JADX INFO: compiled from: Observables.java */
    class c implements g<FileItem> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f3601a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f3602b;

        c(String str, String str2) {
            this.f3601a = str;
            this.f3602b = str2;
        }

        @Override // d.a.g
        public void a(f<FileItem> fVar) throws Exception {
            com.byd.filemanager.k.b.b("create path " + this.f3601a + File.separator + this.f3602b);
            boolean z = false;
            long jCount = 0;
            try {
                try {
                    Stream<Path> list = Files.list(Paths.get(this.f3601a, new String[0]));
                    try {
                        jCount = list.filter(new Predicate() { // from class: com.byd.filemanager.j.a
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                return Files.isDirectory((Path) obj, new LinkOption[0]);
                            }
                        }).count();
                        list.close();
                    } finally {
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                if (jCount < 500) {
                    File fileB = com.byd.filemanager.k.d.b(this.f3601a, this.f3602b);
                    k.b();
                    StringBuilder sb = new StringBuilder();
                    sb.append("create success ");
                    if (fileB != null && fileB.exists()) {
                        z = true;
                    }
                    sb.append(z);
                    com.byd.filemanager.k.b.b(sb.toString());
                    fVar.onNext(new FileItem(fileB));
                } else {
                    com.byd.filemanager.k.b.b("number of folders exceeds 500");
                    fVar.onNext(new FileItem());
                }
            } catch (Throwable th2) {
                com.byd.filemanager.k.b.d("create failed", th2);
                fVar.onNext(new FileItem());
            }
            fVar.onComplete();
        }
    }

    /* JADX INFO: renamed from: com.byd.filemanager.j.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Observables.java */
    class C0085d implements g<FileItem> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ FileItem f3603a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f3604b;

        C0085d(FileItem fileItem, String str) {
            this.f3603a = fileItem;
            this.f3604b = str;
        }

        @Override // d.a.g
        public void a(f<FileItem> fVar) throws Exception {
            String strE = this.f3603a.E();
            File fileM = com.byd.filemanager.k.d.M(this.f3603a.w(), this.f3604b);
            if (fileM == null) {
                com.byd.filemanager.k.b.g("renameFile file is null");
                fVar.onNext(new FileItem(fileM));
                fVar.onComplete();
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jLastModified = fileM.lastModified();
            com.byd.filemanager.k.b.b("currentTimeMillis = " + jCurrentTimeMillis);
            com.byd.filemanager.k.b.b("lastModified1 = " + fileM.lastModified());
            if (jLastModified > jCurrentTimeMillis) {
                fileM.setLastModified(fileM.lastModified() + SystemClock.uptimeMillis());
            } else {
                fileM.setLastModified(jCurrentTimeMillis);
            }
            com.byd.filemanager.k.b.b("lastModified2 = " + fileM.lastModified());
            com.byd.filemanager.service.b.c().m(strE);
            if (com.byd.filemanager.k.d.g(fileM)) {
                this.f3603a.R(fileM);
                this.f3603a.U(fileM.getName());
                this.f3603a.P(false);
                k.b();
            }
            fVar.onNext(new FileItem(fileM));
            fVar.onComplete();
        }
    }

    /* JADX INFO: compiled from: Observables.java */
    class e implements g<Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3605a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f3606b;

        e(int i, List list) {
            this.f3605a = i;
            this.f3606b = list;
        }

        @Override // d.a.g
        public void a(f<Boolean> fVar) throws Exception {
            try {
                l.d(this.f3605a, this.f3606b);
                fVar.onNext(Boolean.TRUE);
                fVar.onComplete();
            } catch (Throwable th) {
                fVar.onError(th);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static d.a.e<FileItem> a(String str, String str2) {
        return d.a.e.c(new c(str, str2));
    }

    @SuppressLint({"NewApi"})
    public static d.a.e<String> b(FileItem fileItem) {
        return d.a.e.c(new a(fileItem));
    }

    public static d.a.e<String> c(FileItem fileItem) {
        return d.a.e.c(new b(fileItem));
    }

    public static d.a.e<FileItem> d(FileItem fileItem, String str) {
        return d.a.e.c(new C0085d(fileItem, str));
    }

    public static d.a.e<Boolean> e(int i, List<FileItem> list) {
        return d.a.e.c(new e(i, list));
    }
}
