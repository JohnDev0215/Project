package com.ecarx.xui.adaptapi.policy;

/* JADX INFO: loaded from: classes2.dex */
public class WindowManagerPolicy implements IWindowManagerPolicy {
    public static final String TAG = "WindowManagerPolicy";

    public static final class WindowCode {
        public static final int CODE_APP_POP_FULLSCREEN = 6;
        public static final int CODE_APP_TOAST = 9;
        public static final int CODE_CAMERA_RADAR = 66;
        public static final int CODE_HARDWARE_TOAST = 60;
        public static final int CODE_LOCK_SCREEN_VIEW = 25;
        public static final int CODE_NEWCOMER_GUIDE = 57;
        public static final int CODE_NOTIFY_CENTER = 18;
        public static final int CODE_ONCALL = 54;
        public static final int CODE_OTA_POPUP = 24;
        public static final int CODE_PARTIAL = 58;
        public static final int CODE_PHONE_POPUP = 26;
        public static final int CODE_SCREENSAVER = 21;
        public static final int CODE_SOS = 63;
        public static final int CODE_STARTUP_WARNING = 68;
        public static final int CODE_SWIPE_FROM_BOTTOM_VIEW = 48;
        public static final int CODE_SWIPE_FROM_LEFT_VIEW = 17;
        public static final int CODE_SWIPE_FROM_RIGHT_VIEW = 42;
        public static final int CODE_SWIPE_FROM_TOP_VIEW = 45;
        public static final int CODE_THEME_LOADING = 67;
        public static final int CODE_UNLOCK_SCREEN_VIEW = 51;
        public static final int CODE_XIAOKA = 23;
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x000c A[PHI: r2
  0x000c: PHI (r2v4 int) = (r2v1 int), (r2v1 int), (r2v2 int), (r2v2 int), (r2v3 int), (r2v3 int), (r2v3 int) binds: [B:6:0x000a, B:9:0x0011, B:21:0x002e, B:33:0x0046, B:42:0x005b, B:45:0x0060, B:60:0x007f] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.ecarx.xui.adaptapi.policy.IWindowManagerPolicy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getWindowTypeByCode(int r4) {
        /*
            r3 = this;
            r0 = 24
            r1 = 2016(0x7e0, float:2.825E-42)
            if (r4 != r0) goto L8
            goto L8a
        L8:
            r0 = 6
            r2 = 2
            if (r4 != r0) goto Lf
        Lc:
            r1 = r2
            goto L8a
        Lf:
            r0 = 9
            if (r4 != r0) goto L14
            goto Lc
        L14:
            r0 = 25
            if (r4 != r0) goto L1a
            goto L89
        L1a:
            r0 = 17
            if (r4 != r0) goto L22
            r1 = 2201(0x899, float:3.084E-42)
            goto L8a
        L22:
            r0 = 42
            if (r4 != r0) goto L2a
            r1 = 2202(0x89a, float:3.086E-42)
            goto L8a
        L2a:
            r0 = 45
            r2 = 2203(0x89b, float:3.087E-42)
            if (r4 != r0) goto L31
            goto Lc
        L31:
            r0 = 48
            if (r4 != r0) goto L38
            r1 = 2204(0x89c, float:3.088E-42)
            goto L8a
        L38:
            r0 = 51
            if (r4 != r0) goto L3d
            goto L89
        L3d:
            r0 = 23
            if (r4 != r0) goto L44
            r1 = 2008(0x7d8, float:2.814E-42)
            goto L8a
        L44:
            r0 = 18
            if (r4 != r0) goto L49
            goto Lc
        L49:
            r0 = 26
            if (r4 != r0) goto L50
            r1 = 2002(0x7d2, float:2.805E-42)
            goto L8a
        L50:
            r0 = 54
            if (r4 != r0) goto L57
            r1 = 2007(0x7d7, float:2.812E-42)
            goto L8a
        L57:
            r0 = 57
            r2 = 2021(0x7e5, float:2.832E-42)
            if (r4 != r0) goto L5e
            goto Lc
        L5e:
            r0 = 60
            if (r4 != r0) goto L63
            goto Lc
        L63:
            r0 = 63
            if (r4 != r0) goto L6a
            r1 = 2033(0x7f1, float:2.849E-42)
            goto L8a
        L6a:
            r0 = 21
            if (r4 != r0) goto L6f
            goto L8a
        L6f:
            r0 = 58
            if (r4 != r0) goto L76
            r1 = 2006(0x7d6, float:2.811E-42)
            goto L8a
        L76:
            r0 = 67
            if (r4 != r0) goto L7d
            r1 = 2026(0x7ea, float:2.839E-42)
            goto L8a
        L7d:
            r0 = 68
            if (r4 != r0) goto L82
            goto Lc
        L82:
            r0 = 66
            if (r4 != r0) goto L89
            r1 = 2015(0x7df, float:2.824E-42)
            goto L8a
        L89:
            r1 = 0
        L8a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "getWindowTypeByCode windowCode = "
            r0.<init>(r2)
            java.lang.StringBuilder r4 = r0.append(r4)
            java.lang.String r0 = ", result = "
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.String r4 = r4.toString()
            java.lang.String r0 = "WindowManagerPolicy"
            android.util.Log.d(r0, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecarx.xui.adaptapi.policy.WindowManagerPolicy.getWindowTypeByCode(int):int");
    }
}
