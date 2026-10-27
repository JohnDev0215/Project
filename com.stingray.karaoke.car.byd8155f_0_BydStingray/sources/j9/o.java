package j9;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class o {
    public static int a(int i7, int i10, int i11) throws IOException {
        if ((i10 & 8) != 0) {
            i7--;
        }
        if (i11 <= i7) {
            return i7 - i11;
        }
        throw new IOException(A7.k.i("PROTOCOL_ERROR padding ", " > remaining length ", i11, i7));
    }
}
