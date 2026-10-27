package com.byd.cleardata.h;

import android.text.TextUtils;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class k extends b {

    class a implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.g f2681a;

        a(c.a.g gVar) {
            this.f2681a = gVar;
        }

        @Override // com.byd.cleardata.h.g
        public void a(long j) {
            k kVar = k.this;
            long j2 = kVar.e + j;
            kVar.e = j2;
            this.f2681a.c(new m(j2, kVar.f));
        }
    }

    public k(List<com.byd.cleardata.d.d> list, String str) {
        super(list, str);
    }

    private boolean t(String str, String str2, g gVar) {
        com.byd.cleardata.i.c.b("moveFile from = " + str + ", to = " + str2);
        if (x(str, str2, gVar)) {
            return true;
        }
        return com.byd.cleardata.i.e.G(str) ? v(str, str2, gVar) : w(str, str2, gVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002d, code lost:
    
        com.byd.cleardata.i.c.b(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:?, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean u(java.util.List<com.byd.cleardata.d.d> r6, com.byd.cleardata.h.g r7) {
        /*
            r5 = this;
            java.util.List<com.byd.cleardata.d.d> r0 = r5.f2668c
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
        L7:
            r2 = r1
        L8:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L7d
            java.lang.Object r2 = r0.next()
            com.byd.cleardata.d.d r2 = (com.byd.cleardata.d.d) r2
            boolean r3 = r5.o()
            if (r3 == 0) goto L1b
            return r1
        L1b:
            if (r2 == 0) goto L7
            boolean r3 = r2.u()
            if (r3 == 0) goto L7
            java.lang.String r3 = r5.f2669d
            boolean r3 = r2.M(r3)
            if (r3 == 0) goto L31
            java.lang.String r6 = "file is father of toPath"
        L2d:
            com.byd.cleardata.i.c.b(r6)
            goto L7e
        L31:
            java.lang.String r3 = r2.D()
            java.lang.String r4 = r5.f2669d
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L3f
            r6 = 1
            return r6
        L3f:
            java.lang.String r3 = r5.f2669d
            java.lang.String r4 = r2.C()
            java.lang.String r3 = com.byd.cleardata.i.e.s(r3, r4)
            if (r3 == 0) goto L7a
            java.lang.String r2 = r2.E()
            boolean r2 = r5.t(r2, r3, r7)
            if (r2 == 0) goto L5d
            com.byd.cleardata.d.d r3 = com.byd.cleardata.d.d.z(r3)
            r6.add(r3)
            goto L8
        L5d:
            java.lang.String r7 = "move error"
            com.byd.cleardata.i.c.b(r7)
            boolean r7 = com.byd.cleardata.i.e.i(r3)
            if (r7 == 0) goto L7d
            boolean r7 = com.byd.cleardata.i.e.G(r3)
            if (r7 == 0) goto L76
            com.byd.cleardata.d.d r7 = com.byd.cleardata.d.d.z(r3)
            r6.add(r7)
            goto L7d
        L76:
            com.byd.cleardata.i.e.f(r3)
            goto L7d
        L7a:
            java.lang.String r6 = "create dest file failed"
            goto L2d
        L7d:
            r1 = r2
        L7e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.cleardata.h.k.u(java.util.List, com.byd.cleardata.h.g):boolean");
    }

    private boolean v(String str, String str2, g gVar) {
        com.byd.cleardata.i.c.b("moveDirectory from = " + str + ", to = " + str2);
        if (com.byd.cleardata.i.e.G(str)) {
            File file = new File(str);
            if (new File(str2).mkdir()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        if (o()) {
                            com.byd.cleardata.i.c.b("moveDirectory cancel");
                            return false;
                        }
                        if (!t(file2.getAbsolutePath(), str2 + File.separator + file2.getName(), gVar)) {
                            return false;
                        }
                    }
                    com.byd.cleardata.i.e.f(str);
                    return true;
                }
            } else {
                com.byd.cleardata.i.c.b("moveDirectory mkdir error");
            }
        }
        return false;
    }

    private boolean w(String str, String str2, g gVar) {
        com.byd.cleardata.i.c.b("moveFileAsCopy");
        boolean zS = s(str, str2, gVar);
        if (zS) {
            com.byd.cleardata.i.e.f(str);
        }
        return zS;
    }

    private boolean x(String str, String str2, g gVar) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        boolean zRenameTo = new File(str).renameTo(new File(str2));
        com.byd.cleardata.i.c.b("isRenamed = " + zRenameTo);
        if (!zRenameTo) {
            return false;
        }
        gVar.a(com.byd.cleardata.i.e.l(str2));
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    @Override // com.byd.cleardata.h.b, com.byd.cleardata.h.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m(c.a.g<? super com.byd.cleardata.h.m> r9) {
        /*
            r8 = this;
            java.lang.String r0 = "move start"
            com.byd.cleardata.i.c.b(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = r8.f2669d
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            r2 = 3
            java.lang.String r3 = "move cancel"
            if (r1 != 0) goto L5f
            java.util.List<com.byd.cleardata.d.d> r1 = r8.f2668c
            if (r1 == 0) goto L5f
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L5f
            java.util.List<com.byd.cleardata.d.d> r1 = r8.f2668c
            long r4 = r8.n(r1)
            r8.f = r4
            boolean r1 = r8.o()
            if (r1 == 0) goto L38
            com.byd.cleardata.i.c.b(r3)
            com.byd.cleardata.h.m r1 = new com.byd.cleardata.h.m
            r1.<init>(r2, r0)
            r9.c(r1)
        L38:
            long r4 = r8.f
            r6 = 0
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 <= 0) goto L5f
            com.byd.cleardata.h.m r1 = new com.byd.cleardata.h.m
            long r6 = r8.e
            r1.<init>(r6, r4)
            r9.c(r1)
            com.byd.cleardata.h.k$a r1 = new com.byd.cleardata.h.k$a
            r1.<init>(r9)
            boolean r1 = r8.u(r0, r1)
            if (r1 == 0) goto L60
            com.byd.cleardata.h.m r4 = new com.byd.cleardata.h.m
            r5 = 1
            r4.<init>(r5, r0)
            r9.c(r4)
            goto L60
        L5f:
            r1 = 0
        L60:
            if (r1 != 0) goto L7f
            boolean r1 = r8.o()
            if (r1 == 0) goto L71
            com.byd.cleardata.i.c.b(r3)
            com.byd.cleardata.h.m r1 = new com.byd.cleardata.h.m
            r1.<init>(r2, r0)
            goto L7c
        L71:
            java.lang.String r1 = "move error or source is empty"
            com.byd.cleardata.i.c.b(r1)
            com.byd.cleardata.h.m r1 = new com.byd.cleardata.h.m
            r2 = 4
            r1.<init>(r2, r0)
        L7c:
            r9.c(r1)
        L7f:
            java.lang.String r9 = "move end"
            com.byd.cleardata.i.c.b(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.cleardata.h.k.m(c.a.g):void");
    }
}
