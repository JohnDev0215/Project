package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VI {
    public final LJ a;
    public ByteBuffer b;
    public final List c = new ArrayList();
    public int d;

    public VI(LJ lj, int i, UI ui) {
        this.a = lj;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i <= 0 ? 1024 : i);
        this.b = byteBufferAllocateDirect;
        byteBufferAllocateDirect.order(ByteOrder.LITTLE_ENDIAN);
        this.d = 0;
    }
}
