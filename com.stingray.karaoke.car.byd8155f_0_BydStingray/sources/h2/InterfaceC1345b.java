package h2;

import android.net.Uri;
import e2.I;

/* JADX INFO: renamed from: h2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC1345b {
    default h5.y j(I i7) {
        byte[] bArr = i7.f13967k;
        if (bArr != null) {
            return p(bArr);
        }
        Uri uri = i7.f13968m;
        if (uri != null) {
            return z(uri);
        }
        return null;
    }

    h5.y p(byte[] bArr);

    h5.y z(Uri uri);
}
