package com.byd.filemanager.bean;

import com.byd.filemanager.e;
import d.a.f;
import d.a.g;

/* JADX INFO: compiled from: SizeInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f3536a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f3537b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3538c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3539d;

    /* JADX INFO: compiled from: SizeInfo.java */
    class a implements g<c> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f3540a;

        a(String str) {
            this.f3540a = str;
        }

        @Override // d.a.g
        public void a(f<c> fVar) throws Exception {
            try {
                long jI = com.byd.filemanager.k.d.i(this.f3540a);
                long jA = com.byd.filemanager.k.d.A(this.f3540a);
                com.byd.filemanager.k.b.b("free:" + jI + ",total:" + jA);
                c cVar = new c(jI, jA);
                int iK = com.byd.filemanager.f.h().k();
                if (e.f().s(iK) && cVar.f3537b <= 0) {
                    com.byd.filemanager.k.b.b("root path exists " + com.byd.filemanager.k.d.h(e.f().l(iK)));
                    com.byd.filemanager.k.b.b("root internalPath exists " + com.byd.filemanager.k.d.h(e.f().k(iK)));
                }
                fVar.onNext(cVar);
                fVar.onComplete();
            } catch (Throwable th) {
                fVar.onError(th);
            }
        }
    }

    public c(long j, long j2) {
        this.f3536a = j;
        this.f3537b = j2;
        d();
    }

    private void d() {
        long j = this.f3537b - this.f3536a;
        if (Long.compare(j, 0L) == 0 || Long.compare(this.f3537b, 0L) == 0) {
            this.f3539d = 0.0f;
        } else {
            this.f3539d = (j * 1.0f) / this.f3537b;
        }
        this.f3538c = com.byd.filemanager.k.d.j(j) + "/" + com.byd.filemanager.k.d.j(this.f3537b);
        StringBuilder sb = new StringBuilder();
        sb.append("sizeText:");
        sb.append(this.f3538c);
        com.byd.filemanager.k.b.b(sb.toString());
    }

    public static d.a.e<c> e(String str) {
        return d.a.e.c(new a(str));
    }

    public float b() {
        return this.f3539d;
    }

    public String c() {
        return this.f3538c;
    }
}
