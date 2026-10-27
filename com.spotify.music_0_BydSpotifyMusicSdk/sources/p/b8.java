package p;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b8 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Context e;

    public /* synthetic */ b8(Context context, int i) {
        this.c = i;
        this.e = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009d  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() throws java.lang.Throwable {
        /*
            r11 = this;
            int r0 = r11.c
            android.content.Context r1 = r11.e
            switch(r0) {
                case 0: goto L2f;
                case 1: goto L14;
                default: goto L7;
            }
        L7:
            p.db r0 = new p.db
            r2 = 5
            r0.<init>(r2)
            p.zb0 r2 = p.ux2.c
            r3 = 0
            p.ux2.B(r1, r0, r2, r3)
            return
        L14:
            java.util.concurrent.ThreadPoolExecutor r0 = new java.util.concurrent.ThreadPoolExecutor
            r5 = 0
            r6 = 1
            r7 = 0
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.MILLISECONDS
            java.util.concurrent.LinkedBlockingQueue r10 = new java.util.concurrent.LinkedBlockingQueue
            r10.<init>()
            r4 = r0
            r4.<init>(r5, r6, r7, r9, r10)
            p.b8 r2 = new p.b8
            r3 = 2
            r2.<init>(r1, r3)
            r0.execute(r2)
            return
        L2f:
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 33
            r3 = 1
            if (r0 < r2) goto Lb5
            android.content.ComponentName r0 = new android.content.ComponentName
            java.lang.String r2 = "androidx.appcompat.app.AppLocalesMetadataHolderService"
            r0.<init>(r1, r2)
            android.content.pm.PackageManager r2 = r1.getPackageManager()
            int r2 = r2.getComponentEnabledSetting(r0)
            if (r2 == r3) goto Lb5
            boolean r2 = p.no.a()
            java.lang.String r4 = "locale"
            if (r2 == 0) goto L8a
            p.qb r2 = p.e8.o
            r2.getClass()
            p.jb r5 = new p.jb
            r5.<init>(r2)
        L59:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L78
            java.lang.Object r2 = r5.next()
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2
            java.lang.Object r2 = r2.get()
            p.e8 r2 = (p.e8) r2
            if (r2 == 0) goto L59
            p.s8 r2 = (p.s8) r2
            android.content.Context r2 = r2.s
            if (r2 == 0) goto L59
            java.lang.Object r2 = r2.getSystemService(r4)
            goto L79
        L78:
            r2 = 0
        L79:
            if (r2 == 0) goto L8f
            android.os.LocaleList r2 = p.d8.a(r2)
            p.ac1 r5 = new p.ac1
            p.cc1 r6 = new p.cc1
            r6.<init>(r2)
            r5.<init>(r6)
            goto L91
        L8a:
            p.ac1 r5 = p.e8.k
            if (r5 == 0) goto L8f
            goto L91
        L8f:
            p.ac1 r5 = p.ac1.b
        L91:
            p.bc1 r2 = r5.a
            p.cc1 r2 = (p.cc1) r2
            android.os.LocaleList r2 = r2.a
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto Lae
            java.lang.String r2 = p.p02.p(r1)
            java.lang.Object r4 = r1.getSystemService(r4)
            if (r4 == 0) goto Lae
            android.os.LocaleList r2 = p.c8.a(r2)
            p.d8.b(r4, r2)
        Lae:
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            r1.setComponentEnabledSetting(r0, r3, r3)
        Lb5:
            p.e8.n = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.b8.run():void");
    }
}
