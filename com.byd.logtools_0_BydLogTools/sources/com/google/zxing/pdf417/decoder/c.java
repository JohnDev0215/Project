package com.google.zxing.pdf417.decoder;

import com.google.zxing.NotFoundException;

/* JADX INFO: compiled from: BoundingBox.java */
/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.zxing.common.b f4451a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.google.zxing.i f4452b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.google.zxing.i f4453c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.google.zxing.i f4454d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.google.zxing.i f4455e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f4456f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f4457g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f4458h;
    private final int i;

    c(com.google.zxing.common.b bVar, com.google.zxing.i iVar, com.google.zxing.i iVar2, com.google.zxing.i iVar3, com.google.zxing.i iVar4) throws NotFoundException {
        boolean z = iVar == null || iVar2 == null;
        boolean z2 = iVar3 == null || iVar4 == null;
        if (z && z2) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (z) {
            iVar = new com.google.zxing.i(0.0f, iVar3.d());
            iVar2 = new com.google.zxing.i(0.0f, iVar4.d());
        } else if (z2) {
            iVar3 = new com.google.zxing.i(bVar.k() - 1, iVar.d());
            iVar4 = new com.google.zxing.i(bVar.k() - 1, iVar2.d());
        }
        this.f4451a = bVar;
        this.f4452b = iVar;
        this.f4453c = iVar2;
        this.f4454d = iVar3;
        this.f4455e = iVar4;
        this.f4456f = (int) Math.min(iVar.c(), iVar2.c());
        this.f4457g = (int) Math.max(iVar3.c(), iVar4.c());
        this.f4458h = (int) Math.min(iVar.d(), iVar3.d());
        this.i = (int) Math.max(iVar2.d(), iVar4.d());
    }

    static c j(c cVar, c cVar2) throws NotFoundException {
        return cVar == null ? cVar2 : cVar2 == null ? cVar : new c(cVar.f4451a, cVar.f4452b, cVar.f4453c, cVar2.f4454d, cVar2.f4455e);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    com.google.zxing.pdf417.decoder.c a(int r13, int r14, boolean r15) throws com.google.zxing.NotFoundException {
        /*
            r12 = this;
            com.google.zxing.i r0 = r12.f4452b
            com.google.zxing.i r1 = r12.f4453c
            com.google.zxing.i r2 = r12.f4454d
            com.google.zxing.i r3 = r12.f4455e
            if (r13 <= 0) goto L29
            if (r15 == 0) goto Le
            r4 = r0
            goto Lf
        Le:
            r4 = r2
        Lf:
            float r5 = r4.d()
            int r5 = (int) r5
            int r5 = r5 - r13
            if (r5 >= 0) goto L18
            r5 = 0
        L18:
            com.google.zxing.i r13 = new com.google.zxing.i
            float r4 = r4.c()
            float r5 = (float) r5
            r13.<init>(r4, r5)
            if (r15 == 0) goto L26
            r8 = r13
            goto L2a
        L26:
            r10 = r13
            r8 = r0
            goto L2b
        L29:
            r8 = r0
        L2a:
            r10 = r2
        L2b:
            if (r14 <= 0) goto L5b
            if (r15 == 0) goto L32
            com.google.zxing.i r13 = r12.f4453c
            goto L34
        L32:
            com.google.zxing.i r13 = r12.f4455e
        L34:
            float r0 = r13.d()
            int r0 = (int) r0
            int r0 = r0 + r14
            com.google.zxing.common.b r14 = r12.f4451a
            int r14 = r14.h()
            if (r0 < r14) goto L4a
            com.google.zxing.common.b r14 = r12.f4451a
            int r14 = r14.h()
            int r0 = r14 + (-1)
        L4a:
            com.google.zxing.i r14 = new com.google.zxing.i
            float r13 = r13.c()
            float r0 = (float) r0
            r14.<init>(r13, r0)
            if (r15 == 0) goto L58
            r9 = r14
            goto L5c
        L58:
            r11 = r14
            r9 = r1
            goto L5d
        L5b:
            r9 = r1
        L5c:
            r11 = r3
        L5d:
            com.google.zxing.pdf417.decoder.c r13 = new com.google.zxing.pdf417.decoder.c
            com.google.zxing.common.b r7 = r12.f4451a
            r6 = r13
            r6.<init>(r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.c.a(int, int, boolean):com.google.zxing.pdf417.decoder.c");
    }

    com.google.zxing.i b() {
        return this.f4453c;
    }

    com.google.zxing.i c() {
        return this.f4455e;
    }

    int d() {
        return this.f4457g;
    }

    int e() {
        return this.i;
    }

    int f() {
        return this.f4456f;
    }

    int g() {
        return this.f4458h;
    }

    com.google.zxing.i h() {
        return this.f4452b;
    }

    com.google.zxing.i i() {
        return this.f4454d;
    }

    c(c cVar) {
        this.f4451a = cVar.f4451a;
        this.f4452b = cVar.h();
        this.f4453c = cVar.b();
        this.f4454d = cVar.i();
        this.f4455e = cVar.c();
        this.f4456f = cVar.f();
        this.f4457g = cVar.d();
        this.f4458h = cVar.g();
        this.i = cVar.e();
    }
}
