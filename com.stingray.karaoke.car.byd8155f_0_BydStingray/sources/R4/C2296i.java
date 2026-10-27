package r4;

import F.l;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import e4.j;
import java.io.InputStream;
import java.util.ArrayList;

/* JADX INFO: renamed from: r4.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2296i implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f21843a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2288a f21844b;
    public final l c;

    public C2296i(ArrayList arrayList, C2288a c2288a, l lVar) {
        this.f21843a = arrayList;
        this.f21844b = c2288a;
        this.c = lVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    @Override // e4.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final g4.x a(java.lang.Object r6, int r7, int r8, e4.h r9) {
        /*
            r5 = this;
            java.io.InputStream r6 = (java.io.InputStream) r6
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r1 = 16384(0x4000, float:2.2959E-41)
            r0.<init>(r1)
            r2 = 0
            byte[] r1 = new byte[r1]     // Catch: java.io.IOException -> L18
        Lc:
            int r3 = r6.read(r1)     // Catch: java.io.IOException -> L18
            r4 = -1
            if (r3 == r4) goto L1a
            r4 = 0
            r0.write(r1, r4, r3)     // Catch: java.io.IOException -> L18
            goto Lc
        L18:
            r6 = move-exception
            goto L22
        L1a:
            r0.flush()     // Catch: java.io.IOException -> L18
            byte[] r6 = r0.toByteArray()
            goto L31
        L22:
            java.lang.String r0 = "StreamGifDecoder"
            r1 = 5
            boolean r1 = android.util.Log.isLoggable(r0, r1)
            if (r1 == 0) goto L30
            java.lang.String r1 = "Error reading data from stream"
            h6.n.u(r0, r1, r6)
        L30:
            r6 = r2
        L31:
            if (r6 != 0) goto L34
            goto L3e
        L34:
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.wrap(r6)
            r4.a r5 = r5.f21844b
            g4.x r2 = r5.a(r6, r7, r8, r9)
        L3e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: r4.C2296i.a(java.lang.Object, int, int, e4.h):g4.x");
    }

    @Override // e4.j
    public final boolean b(Object obj, e4.h hVar) {
        return !((Boolean) hVar.c(AbstractC2295h.f21842b)).booleanValue() && l9.l.R(this.f21843a, (InputStream) obj, this.c) == ImageHeaderParser$ImageType.GIF;
    }
}
