package defpackage;

/* JADX INFO: compiled from: DirectWritingSettingsHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ia0 {
    public static Boolean a;

    /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.content.Context r5) {
        /*
            java.lang.String r0 = "InputMethod.VirtualKeyboard.Handwriting.DWServiceCallbackFailed"
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            r3 = 0
            if (r1 >= r2) goto La
            return r3
        La:
            java.lang.Boolean r1 = defpackage.ia0.a
            r2 = 1
            if (r1 != 0) goto L2b
            java.lang.Class<ga0> r1 = defpackage.ga0.class
            int r4 = defpackage.ga0.m     // Catch: java.lang.Throwable -> L24
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch: java.lang.Throwable -> L24
            java.lang.reflect.Constructor r1 = r1.getConstructor(r4)     // Catch: java.lang.Throwable -> L24
            r1.isAccessible()     // Catch: java.lang.Throwable -> L24
            java.lang.Boolean r1 = java.lang.Boolean.TRUE     // Catch: java.lang.Throwable -> L24
            defpackage.ia0.a = r1     // Catch: java.lang.Throwable -> L24
            defpackage.oj2.O(r0, r3)     // Catch: java.lang.Throwable -> L24
            goto L2b
        L24:
            defpackage.oj2.O(r0, r2)
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            defpackage.ia0.a = r0
        L2b:
            java.lang.Boolean r0 = defpackage.ia0.a
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L34
            return r3
        L34:
            if (r5 == 0) goto L47
            android.content.ContentResolver r0 = r5.getContentResolver()     // Catch: java.lang.SecurityException -> L47
            java.lang.String r1 = "default_input_method"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r1)     // Catch: java.lang.SecurityException -> L47
            java.lang.String r1 = "com.samsung.android.honeyboard/.service.HoneyBoardService"
            boolean r0 = r1.equals(r0)     // Catch: java.lang.SecurityException -> L47
            goto L48
        L47:
            r0 = r3
        L48:
            if (r0 == 0) goto L5e
            if (r5 == 0) goto L5a
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch: java.lang.SecurityException -> L5a
            java.lang.String r0 = "direct_writing"
            int r5 = android.provider.Settings.System.getInt(r5, r0, r3)     // Catch: java.lang.SecurityException -> L5a
            if (r5 != r2) goto L5a
            r5 = r2
            goto L5b
        L5a:
            r5 = r3
        L5b:
            if (r5 == 0) goto L5e
            r3 = r2
        L5e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ia0.a(android.content.Context):boolean");
    }
}
