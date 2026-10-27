package defpackage;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: Table.java */
/* JADX INFO: loaded from: classes.dex */
public class l63 {
    public int a;
    public ByteBuffer b;
    public int c;
    public int d;

    public l63() {
        if (d70.a == null) {
            d70.a = new d70(0);
        }
    }

    public final int a(int i) {
        if (i < this.d) {
            return this.b.getShort(this.c + i);
        }
        return 0;
    }

    public final void b(int i, ByteBuffer byteBuffer) {
        this.b = byteBuffer;
        if (byteBuffer == null) {
            this.a = 0;
            this.c = 0;
            this.d = 0;
        } else {
            this.a = i;
            int i2 = i - byteBuffer.getInt(i);
            this.c = i2;
            this.d = this.b.getShort(i2);
        }
    }
}
