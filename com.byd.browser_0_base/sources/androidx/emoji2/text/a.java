package androidx.emoji2.text;

/* JADX INFO: compiled from: DefaultEmojiCompatConfig.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    /* JADX WARN: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x003a A[EDGE_INSN: B:33:0x003a->B:16:0x003a BREAK  A[LOOP:0: B:3:0x0019->B:36:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[LOOP:0: B:3:0x0019->B:36:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.emoji2.text.g a(android.content.Context r8) {
        /*
            android.content.pm.PackageManager r0 = r8.getPackageManager()
            java.lang.String r1 = "Package manager required to locate emoji font provider"
            defpackage.n3.v(r0, r1)
            android.content.Intent r1 = new android.content.Intent
            java.lang.String r2 = "androidx.content.action.LOAD_EMOJI_FONT"
            r1.<init>(r2)
            r2 = 0
            java.util.List r1 = r0.queryIntentContentProviders(r1, r2)
            java.util.Iterator r1 = r1.iterator()
        L19:
            boolean r3 = r1.hasNext()
            r4 = 0
            if (r3 == 0) goto L39
            java.lang.Object r3 = r1.next()
            android.content.pm.ResolveInfo r3 = (android.content.pm.ResolveInfo) r3
            android.content.pm.ProviderInfo r3 = r3.providerInfo
            if (r3 == 0) goto L35
            android.content.pm.ApplicationInfo r5 = r3.applicationInfo
            if (r5 == 0) goto L35
            int r5 = r5.flags
            r6 = 1
            r5 = r5 & r6
            if (r5 != r6) goto L35
            goto L36
        L35:
            r6 = r2
        L36:
            if (r6 == 0) goto L19
            goto L3a
        L39:
            r3 = r4
        L3a:
            if (r3 != 0) goto L3d
            goto L6f
        L3d:
            java.lang.String r1 = r3.authority     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
            java.lang.String r3 = r3.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
            r5 = 64
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r3, r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
            android.content.pm.Signature[] r0 = r0.signatures     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
            r5.<init>()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
            int r6 = r0.length     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
        L4f:
            if (r2 >= r6) goto L5d
            r7 = r0[r2]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
            byte[] r7 = r7.toByteArray()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
            r5.add(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
            int r2 = r2 + 1
            goto L4f
        L5d:
            java.util.List r0 = java.util.Collections.singletonList(r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
            po0 r2 = new po0     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
            java.lang.String r5 = "emojicompat-emoji-font"
            r2.<init>(r1, r3, r5, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
            goto L70
        L69:
            r0 = move-exception
            java.lang.String r1 = "emoji2.text.DefaultEmojiConfig"
            android.util.Log.wtf(r1, r0)
        L6f:
            r2 = r4
        L70:
            if (r2 != 0) goto L73
            goto L78
        L73:
            androidx.emoji2.text.g r4 = new androidx.emoji2.text.g
            r4.<init>(r8, r2)
        L78:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.a.a(android.content.Context):androidx.emoji2.text.g");
    }
}
