package l2;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface n {
    static long a(n nVar) {
        byte[] bArr = (byte[]) ((o) nVar).f18491b.get("exo_len");
        if (bArr != null) {
            return ByteBuffer.wrap(bArr).getLong();
        }
        return -1L;
    }
}
