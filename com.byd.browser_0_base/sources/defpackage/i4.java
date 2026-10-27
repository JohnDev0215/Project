package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.b;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i4 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ b f;

    public /* synthetic */ i4(b bVar, int i) {
        this.e = i;
        this.f = bVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005c  */
    @Override // org.chromium.base.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onResult(java.lang.Object r10) {
        /*
            r9 = this;
            r0 = 0
            int r1 = r9.e
            org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.b r9 = r9.f
            switch(r1) {
                case 0: goto La;
                default: goto L8;
            }
        L8:
            goto L77
        La:
            java.lang.String r10 = (java.lang.String) r10
            org.chromium.ui.modelutil.PropertyModel r1 = r9.b
            org.chromium.ui.modelutil.PropertyModel$l<p81<xa1>> r2 = org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.c.c
            java.lang.Object r1 = r1.j(r2)
            p81 r1 = (defpackage.p81) r1
            r1.clear()
            s10[] r2 = r9.c
            int r3 = r2.length
            r4 = r0
        L1d:
            if (r4 >= r3) goto L76
            r5 = r2[r4]
            java.lang.String r6 = r5.b
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L2d
            boolean r6 = r9.d
            if (r6 != 0) goto L73
        L2d:
            r6 = 1
            if (r10 == 0) goto L5c
            java.util.Locale r7 = java.util.Locale.ENGLISH
            java.lang.String r8 = r5.d
            java.lang.String r8 = r8.toLowerCase(r7)
            java.lang.String r7 = r10.toLowerCase(r7)
            boolean r7 = r8.contains(r7)
            if (r7 != 0) goto L5c
            java.util.Locale r7 = java.util.Locale.getDefault()
            java.lang.String r8 = r5.a
            java.lang.String r7 = r8.toLowerCase(r7)
            java.util.Locale r8 = java.util.Locale.getDefault()
            java.lang.String r8 = r10.toLowerCase(r8)
            boolean r7 = r7.contains(r8)
            if (r7 != 0) goto L5c
            r7 = r6
            goto L5d
        L5c:
            r7 = r0
        L5d:
            if (r7 == 0) goto L60
            goto L73
        L60:
            i4 r7 = new i4
            r7.<init>(r9, r6)
            boolean r6 = r9.d
            org.chromium.ui.modelutil.PropertyModel r5 = org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.c.a.a(r5, r7, r6)
            xa1 r6 = new xa1
            r6.<init>(r0, r5)
            r1.t(r6)
        L73:
            int r4 = r4 + 1
            goto L1d
        L76:
            return
        L77:
            u10 r10 = (defpackage.u10) r10
            org.chromium.ui.modelutil.PropertyModel r1 = r9.b
            org.chromium.ui.modelutil.PropertyModel$n r2 = org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.c.a
            r1.l(r2, r0)
            org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.a$a r9 = r9.a
            org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.AllPasswordsBottomSheetBridge r9 = (org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.AllPasswordsBottomSheetBridge) r9
            long r0 = r9.a
            s10 r9 = r10.a
            java.lang.String r2 = r9.a
            java.lang.String r9 = r9.b
            boolean r10 = r10.b
            J.N.MvNYgfef(r0, r2, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.i4.onResult(java.lang.Object):void");
    }
}
