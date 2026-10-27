package defpackage;

import J.N;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.permissions.AndroidPermissionDelegate;

/* JADX INFO: compiled from: AdaptiveToolbarStatePredictor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p3 {
    public final AndroidPermissionDelegate a;

    /* JADX INFO: compiled from: AdaptiveToolbarStatePredictor.java */
    public static class a {
        public final boolean a;
        public final int b;
        public final int c;
        public final int d;

        public a(int i, int i2, int i3, boolean z) {
            this.a = z;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }
    }

    public p3(AndroidPermissionDelegate androidPermissionDelegate) {
        this.a = androidPermissionDelegate;
    }

    public static boolean a(int i) {
        if (i == 0) {
            return false;
        }
        return i == 2 || i == 3 || i == 4;
    }

    public final void b(final Callback<a> callback) {
        if (!n3.H0()) {
            callback.onResult(new a(0, 0, 0, false));
            return;
        }
        final int iD = SharedPreferencesManager.getInstance().d(5, "Chrome.AdaptiveToolbarCustomization.Settings");
        final int iP0 = n3.p0();
        final boolean z = SharedPreferencesManager.getInstance().readBoolean("Chrome.AdaptiveToolbarCustomization.Enabled", true);
        sk skVar = bq.a;
        final boolean zM6bsIDpc = N.M6bsIDpc("AdaptiveButtonInTopToolbarCustomizationV2", "ignore_segmentation_results", false);
        N.MNlIGBvD(Profile.d(), new wy2(new ad0(new Callback() { // from class: o3
            /* JADX WARN: Removed duplicated region for block: B:54:0x009f A[PHI: r12
  0x009f: PHI (r12v8 int) = (r12v6 int), (r12v7 int) binds: [B:53:0x009d, B:60:0x00ae] A[DONT_GENERATE, DONT_INLINE]] */
            @Override // org.chromium.base.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onResult(java.lang.Object r12) {
                /*
                    r11 = this;
                    android.util.Pair r12 = (android.util.Pair) r12
                    p3 r0 = r11.e
                    r0.getClass()
                    java.lang.Object r1 = r12.first
                    java.lang.Boolean r1 = (java.lang.Boolean) r1
                    boolean r1 = r1.booleanValue()
                    java.lang.Object r12 = r12.second
                    java.lang.Integer r12 = (java.lang.Integer) r12
                    int r12 = r12.intValue()
                    p3$a r2 = new p3$a
                    boolean r3 = defpackage.n3.H0()
                    sk r4 = defpackage.bq.a
                    java.lang.String r4 = "AdaptiveButtonInTopToolbarCustomizationV2"
                    java.lang.String r5 = "disable_ui"
                    r6 = 0
                    boolean r5 = J.N.M6bsIDpc(r4, r5, r6)
                    r7 = 1
                    r5 = r5 ^ r7
                    java.lang.String r8 = "show_ui_only_after_ready"
                    boolean r4 = J.N.M6bsIDpc(r4, r8, r7)
                    if (r3 == 0) goto L3a
                    if (r5 == 0) goto L3a
                    if (r4 == 0) goto L38
                    if (r1 == 0) goto L3a
                L38:
                    r1 = r7
                    goto L3b
                L3a:
                    r1 = r6
                L3b:
                    boolean r3 = r2
                    int r4 = r3
                    int r5 = r4
                    boolean r8 = r5
                    if (r3 != 0) goto L47
                    r3 = r6
                    goto L5b
                L47:
                    boolean r3 = defpackage.p3.a(r4)
                    if (r3 == 0) goto L4f
                    r3 = r4
                    goto L5b
                L4f:
                    if (r8 == 0) goto L52
                    goto L5a
                L52:
                    boolean r3 = defpackage.p3.a(r12)
                    if (r3 == 0) goto L5a
                    r3 = r12
                    goto L5b
                L5a:
                    r3 = r5
                L5b:
                    r9 = 4
                    org.chromium.ui.permissions.AndroidPermissionDelegate r0 = r0.a
                    if (r3 != r9) goto L68
                    if (r0 != 0) goto L63
                    goto L68
                L63:
                    boolean r10 = defpackage.to3.b(r0)
                    goto L69
                L68:
                    r10 = r7
                L69:
                    if (r10 == 0) goto L6c
                    goto L7f
                L6c:
                    int r3 = defpackage.n3.p0()
                    if (r3 != r9) goto L7a
                    if (r0 != 0) goto L75
                    goto L7a
                L75:
                    boolean r10 = defpackage.to3.b(r0)
                    goto L7b
                L7a:
                    r10 = r7
                L7b:
                    if (r10 == 0) goto L7e
                    goto L7f
                L7e:
                    r3 = r6
                L7f:
                    boolean r10 = defpackage.p3.a(r4)
                    if (r10 == 0) goto L86
                    goto L87
                L86:
                    r4 = 5
                L87:
                    if (r8 == 0) goto L8a
                    goto L91
                L8a:
                    boolean r8 = defpackage.p3.a(r12)
                    if (r8 == 0) goto L91
                    goto L92
                L91:
                    r12 = r5
                L92:
                    if (r12 != r9) goto L9c
                    if (r0 != 0) goto L97
                    goto L9c
                L97:
                    boolean r5 = defpackage.to3.b(r0)
                    goto L9d
                L9c:
                    r5 = r7
                L9d:
                    if (r5 == 0) goto La1
                L9f:
                    r6 = r12
                    goto Lb1
                La1:
                    int r12 = defpackage.n3.p0()
                    if (r12 != r9) goto Lae
                    if (r0 != 0) goto Laa
                    goto Lae
                Laa:
                    boolean r7 = defpackage.to3.b(r0)
                Lae:
                    if (r7 == 0) goto Lb1
                    goto L9f
                Lb1:
                    r2.<init>(r3, r4, r6, r1)
                    org.chromium.base.Callback r11 = r6
                    r11.onResult(r2)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.o3.onResult(java.lang.Object):void");
            }
        }, 5), 5));
    }
}
