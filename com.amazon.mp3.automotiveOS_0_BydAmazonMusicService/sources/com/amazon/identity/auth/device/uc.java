package com.amazon.identity.auth.device;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class uc implements yb {
    public static final String e = "com.amazon.identity.auth.device.uc";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public tb f615a;
    public fd b;
    public nb c;
    public StringBuilder d;

    public uc(fd fdVar, tb tbVar, boolean z) {
        if (tbVar != null) {
            this.f615a = tbVar;
        } else {
            this.f615a = new tb();
        }
        this.b = fdVar;
        this.d = new StringBuilder();
        if (z) {
            nb nbVar = new nb(this.b);
            this.c = nbVar;
            this.b = nbVar;
        }
    }

    @Override // com.amazon.identity.auth.device.yb
    public void a(md mdVar) {
        this.b.a(mdVar);
    }

    @Override // com.amazon.identity.auth.device.yb
    public void b() {
        this.f615a.a();
    }

    @Override // com.amazon.identity.auth.device.yb
    public void onNetworkError() {
        this.f615a.b();
    }

    @Override // com.amazon.identity.auth.device.yb
    public void a(byte[] bArr, int i) {
        if (this.b.b()) {
            if (this.d.length() < 1024) {
                this.d.append(new String(bArr, 0, this.d.length() + i > 1024 ? 1024 - this.d.length() : i));
            }
            this.b.a(bArr, i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0053  */
    @Override // com.amazon.identity.auth.device.yb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a() {
        /*
            r5 = this;
            com.amazon.identity.auth.device.fd r0 = r5.b
            r0.c()
            com.amazon.identity.auth.device.fd r0 = r5.b
            com.amazon.identity.kcpsdk.common.ParseError r0 = r0.a()
            com.amazon.identity.kcpsdk.common.ParseError r1 = com.amazon.identity.kcpsdk.common.ParseError.ParseErrorNoError
            if (r0 == r1) goto L25
            java.lang.String r0 = com.amazon.identity.auth.device.uc.e
            java.lang.StringBuilder r1 = r5.d
            r1.toString()
            com.amazon.identity.auth.device.ga.a(r0)
            com.amazon.identity.auth.device.fd r0 = r5.b
            com.amazon.identity.kcpsdk.common.ParseError r0 = r0.a()
            com.amazon.identity.auth.device.tb r1 = r5.f615a
            r1.a(r0)
            goto L65
        L25:
            com.amazon.identity.auth.device.nb r0 = r5.c
            if (r0 == 0) goto L35
            com.amazon.identity.auth.device.mb r0 = r0.f464a
            boolean r0 = r0.k
            if (r0 != 0) goto L35
            com.amazon.identity.auth.device.tb r0 = r5.f615a
            r0.a()
            goto L65
        L35:
            com.amazon.identity.auth.device.fd r0 = r5.b
            java.lang.Object r0 = r0.d()
            r1 = 0
            if (r0 != 0) goto L3f
            goto L53
        L3f:
            java.lang.Class r2 = r0.getClass()     // Catch: java.lang.Throwable -> L54
            java.lang.String r3 = "getError"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L54 java.lang.Throwable -> L54 java.lang.Throwable -> L54 java.lang.Throwable -> L54
            java.lang.reflect.Method r2 = r2.getMethod(r3, r4)     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L54 java.lang.Throwable -> L54 java.lang.Throwable -> L54 java.lang.Throwable -> L54
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L54 java.lang.Throwable -> L54 java.lang.Throwable -> L54 java.lang.Throwable -> L54
            java.lang.Object r2 = r2.invoke(r0, r3)     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L54 java.lang.Throwable -> L54 java.lang.Throwable -> L54 java.lang.Throwable -> L54
            if (r2 == 0) goto L54
        L53:
            r1 = 1
        L54:
            if (r1 == 0) goto L60
            java.lang.String r1 = com.amazon.identity.auth.device.uc.e
            java.lang.StringBuilder r2 = r5.d
            r2.toString()
            com.amazon.identity.auth.device.ga.a(r1)
        L60:
            com.amazon.identity.auth.device.tb r1 = r5.f615a
            r1.a(r0)
        L65:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.uc.a():void");
    }
}
