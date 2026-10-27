package com.byd.cleardata.h;

import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b extends d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final List<com.byd.cleardata.d.d> f2668c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final String f2669d;
    protected long e = 0;
    protected long f = 0;

    class a implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.g f2670a;

        a(c.a.g gVar) {
            this.f2670a = gVar;
        }

        @Override // com.byd.cleardata.h.g
        public void a(long j) {
            b bVar = b.this;
            long j2 = bVar.e + j;
            bVar.e = j2;
            this.f2670a.c(new m(j2, bVar.f));
        }
    }

    public b(List<com.byd.cleardata.d.d> list, String str) {
        this.f2668c = list != null ? new ArrayList(list) : null;
        this.f2669d = str;
    }

    private boolean p(String str, String str2, g gVar) {
        com.byd.cleardata.i.c.b("copy");
        return com.byd.cleardata.i.e.G(str) ? r(str, str2, gVar) : s(str, str2, gVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
    
        com.byd.cleardata.i.c.b(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean q(java.util.List<com.byd.cleardata.d.d> r6, com.byd.cleardata.h.g r7) {
        /*
            r5 = this;
            java.util.List<com.byd.cleardata.d.d> r0 = r5.f2668c
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
        L7:
            r2 = r1
        L8:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L69
            java.lang.Object r2 = r0.next()
            com.byd.cleardata.d.d r2 = (com.byd.cleardata.d.d) r2
            java.lang.String r3 = "copy a file"
            com.byd.cleardata.i.c.b(r3)
            if (r2 == 0) goto L7
            boolean r3 = r2.u()
            if (r3 == 0) goto L7
            java.lang.String r3 = r5.f2669d
            boolean r3 = r2.M(r3)
            if (r3 == 0) goto L2f
            java.lang.String r6 = "file is father of toPath"
        L2b:
            com.byd.cleardata.i.c.b(r6)
            goto L6a
        L2f:
            java.lang.String r3 = r5.f2669d
            java.lang.String r4 = r2.C()
            java.lang.String r3 = com.byd.cleardata.i.e.s(r3, r4)
            if (r3 == 0) goto L66
            java.lang.String r2 = r2.E()
            boolean r2 = r5.p(r2, r3, r7)
            if (r2 == 0) goto L4d
            com.byd.cleardata.d.d r3 = com.byd.cleardata.d.d.z(r3)
            r6.add(r3)
            goto L8
        L4d:
            java.lang.String r7 = "copy error"
            com.byd.cleardata.i.c.b(r7)
            boolean r7 = com.byd.cleardata.i.e.G(r3)
            if (r7 == 0) goto L69
            boolean r7 = com.byd.cleardata.i.e.i(r3)
            if (r7 == 0) goto L69
            com.byd.cleardata.d.d r7 = com.byd.cleardata.d.d.z(r3)
            r6.add(r7)
            goto L69
        L66:
            java.lang.String r6 = "create dest file failed"
            goto L2b
        L69:
            r1 = r2
        L6a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.cleardata.h.b.q(java.util.List, com.byd.cleardata.h.g):boolean");
    }

    private boolean r(String str, String str2, g gVar) {
        com.byd.cleardata.i.c.b("copyDirectory from = " + str + ", to = " + str2);
        if (com.byd.cleardata.i.e.G(str)) {
            File file = new File(str);
            if (new File(str2).mkdir()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        if (o()) {
                            com.byd.cleardata.i.c.b("copyDirectory cancel");
                            return false;
                        }
                        if (!p(file2.getAbsolutePath(), str2 + File.separator + file2.getName(), gVar)) {
                            return false;
                        }
                    }
                }
                return true;
            }
            com.byd.cleardata.i.c.b("copyDirectory mkdir error");
        }
        return false;
    }

    @Override // com.byd.cleardata.h.d
    public void m(c.a.g<? super m> gVar) {
        m mVar;
        List<com.byd.cleardata.d.d> list;
        com.byd.cleardata.i.c.b("copy start");
        ArrayList arrayList = new ArrayList();
        boolean zQ = false;
        if (!TextUtils.isEmpty(this.f2669d) && (list = this.f2668c) != null && !list.isEmpty()) {
            com.byd.cleardata.i.c.e("copy start", this.f2669d, Integer.valueOf(this.f2668c.size()));
            this.f = n(this.f2668c);
            com.byd.cleardata.i.c.b("total = " + this.f);
            if (o()) {
                com.byd.cleardata.i.c.b("copy cancel");
                gVar.c(new m(3, arrayList));
            }
            long j = this.f;
            if (j > 0) {
                gVar.c(new m(this.e, j));
                zQ = q(arrayList, new a(gVar));
                if (zQ) {
                    gVar.c(new m(1, arrayList));
                }
            }
        }
        if (!zQ) {
            if (o()) {
                com.byd.cleardata.i.c.b("copy cancel");
                mVar = new m(3, arrayList);
            } else {
                com.byd.cleardata.i.c.b("copy error or source is empty");
                mVar = new m(4, arrayList);
            }
            gVar.c(mVar);
        }
        com.byd.cleardata.i.c.b("copy end");
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x00d4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:? A[Catch: IOException -> 0x00de, DONT_GENERATE, FINALLY_INSNS, SYNTHETIC, TRY_LEAVE, TryCatch #5 {IOException -> 0x00de, blocks: (B:16:0x0053, B:26:0x0083, B:41:0x00be, B:60:0x00dd, B:59:0x00da, B:17:0x005c, B:25:0x0080, B:40:0x00bb, B:51:0x00cf, B:50:0x00cc, B:18:0x0065, B:21:0x0072, B:23:0x0078, B:29:0x008a, B:31:0x00a4, B:32:0x00a7, B:34:0x00af, B:36:0x00b4, B:46:0x00c6, B:55:0x00d4), top: B:73:0x0053, inners: #0, #3 }] */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean s(java.lang.String r22, java.lang.String r23, com.byd.cleardata.h.g r24) {
        /*
            Method dump skipped, instruction units count: 232
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.cleardata.h.b.s(java.lang.String, java.lang.String, com.byd.cleardata.h.g):boolean");
    }
}
