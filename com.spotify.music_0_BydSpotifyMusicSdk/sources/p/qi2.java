package p;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public abstract class qi2 {
    public int a;
    public ByteBuffer b;
    public int c;
    public int d;

    public qi2() {
        if (u11.c == null) {
            u11.c = new u11();
        }
    }

    public final int a(int i) {
        if (i < this.d) {
            return this.b.getShort(this.c + i);
        }
        return 0;
    }
}
