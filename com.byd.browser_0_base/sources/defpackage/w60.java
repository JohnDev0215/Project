package defpackage;

import java.util.Comparator;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class w60 implements Comparator {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ w60(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0073 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0075 A[RETURN, SYNTHETIC] */
    @Override // java.util.Comparator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int compare(java.lang.Object r7, java.lang.Object r8) {
        /*
            r6 = this;
            int r0 = r6.e
            java.lang.Object r6 = r6.f
            switch(r0) {
                case 0: goto L8;
                default: goto L7;
            }
        L7:
            goto L51
        L8:
            x60 r6 = (defpackage.x60) r6
            f81 r7 = (defpackage.f81) r7
            f81 r8 = (defpackage.f81) r8
            r6.getClass()
            r0 = r7
            f81$f r0 = (f81.f) r0
            org.chromium.components.offline_items_collection.OfflineItem r0 = r0.e
            r1 = r8
            f81$f r1 = (f81.f) r1
            org.chromium.components.offline_items_collection.OfflineItem r1 = r1.e
            long r2 = r6.d(r8)
            long r6 = r6.d(r7)
            int r6 = java.lang.Long.compare(r2, r6)
            if (r6 == 0) goto L2a
            goto L50
        L2a:
            long r6 = r1.q
            long r2 = r0.q
            int r6 = java.lang.Long.compare(r6, r2)
            if (r6 == 0) goto L35
            goto L50
        L35:
            ny r6 = r0.e
            java.lang.String r6 = r6.a
            ny r7 = r1.e
            java.lang.String r7 = r7.a
            int r6 = r6.compareTo(r7)
            if (r6 == 0) goto L44
            goto L50
        L44:
            ny r6 = r0.e
            java.lang.String r6 = r6.b
            ny r7 = r1.e
            java.lang.String r7 = r7.b
            int r6 = r6.compareTo(r7)
        L50:
            return r6
        L51:
            org.chromium.chrome.browser.password_check.f r6 = (org.chromium.chrome.browser.password_check.f) r6
            org.chromium.chrome.browser.password_check.CompromisedCredential r7 = (org.chromium.chrome.browser.password_check.CompromisedCredential) r7
            org.chromium.chrome.browser.password_check.CompromisedCredential r8 = (org.chromium.chrome.browser.password_check.CompromisedCredential) r8
            r6.getClass()
            boolean r0 = r7.p
            boolean r1 = r8.p
            if (r0 == r1) goto L63
            if (r0 == 0) goto L75
            goto L73
        L63:
            java.util.HashSet<org.chromium.chrome.browser.password_check.CompromisedCredential> r0 = r6.f
            boolean r0 = r0.contains(r7)
            java.util.HashSet<org.chromium.chrome.browser.password_check.CompromisedCredential> r6 = r6.f
            boolean r6 = r6.contains(r8)
            if (r0 == r6) goto L77
            if (r0 == 0) goto L75
        L73:
            r6 = -1
            goto La9
        L75:
            r6 = 1
            goto La9
        L77:
            long r1 = r7.m
            long r3 = r8.m
            if (r0 == 0) goto L89
            if (r6 == 0) goto L89
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L89
            int r6 = java.lang.Long.compare(r1, r3)
            int r6 = -r6
            goto La9
        L89:
            if (r0 != 0) goto L96
            if (r6 != 0) goto L96
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 == 0) goto L96
            int r6 = java.lang.Long.compare(r1, r3)
            goto La9
        L96:
            java.lang.String r6 = r7.h
            java.lang.String r0 = r8.h
            int r6 = r6.compareTo(r0)
            java.lang.String r7 = r7.i
            java.lang.String r8 = r8.i
            int r7 = r7.compareTo(r8)
            if (r6 != 0) goto La9
            r6 = r7
        La9:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.w60.compare(java.lang.Object, java.lang.Object):int");
    }
}
