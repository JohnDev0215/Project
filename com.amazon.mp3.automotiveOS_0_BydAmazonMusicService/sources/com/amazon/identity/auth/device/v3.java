package com.amazon.identity.auth.device;

import android.net.Uri;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class v3 implements v4<String> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Uri f625a;

    public v3(w3 w3Var, Uri uri) {
        this.f625a = uri;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0021  */
    @Override // com.amazon.identity.auth.device.v4
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(android.content.ContentProviderClient r7) throws java.lang.Exception {
        /*
            r6 = this;
            android.net.Uri r1 = r6.f625a
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r0 = r7
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5)
            if (r7 == 0) goto L21
            boolean r0 = r7.moveToFirst()     // Catch: java.lang.Throwable -> L1c
            if (r0 == 0) goto L21
            r0 = 0
            java.lang.String r0 = r7.getString(r0)     // Catch: java.lang.Throwable -> L1c
            r7.close()
            goto L27
        L1c:
            r0 = move-exception
            r7.close()
            throw r0
        L21:
            r0 = 0
            if (r7 == 0) goto L27
            r7.close()
        L27:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.v3.a(android.content.ContentProviderClient):java.lang.Object");
    }
}
