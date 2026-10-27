package org.chromium.url;

import defpackage.C0989jJ;
import defpackage.C1877zP;
import defpackage.WI;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Origin {
    public final C1877zP a;

    public Origin(ByteBuffer byteBuffer) {
        this.a = C1877zP.d(byteBuffer);
    }

    public static ByteBuffer serialize(Origin origin) {
        C1877zP c1877zP = origin.a;
        WI wi = new WI(null, c1877zP.a);
        c1877zP.a(wi);
        C0989jJ c0989jJV = wi.v();
        if (c0989jJV.b.isEmpty()) {
            return c0989jJV.a;
        }
        throw new UnsupportedOperationException("Handles are discarded.");
    }
}
