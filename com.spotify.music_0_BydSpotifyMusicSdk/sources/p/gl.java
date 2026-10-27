package p;

import java.io.Serializable;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public final class gl {
    public final /* synthetic */ int a = 1;
    public final int b;
    public final int c;
    public final Serializable[] d;

    /* JADX WARN: Type inference failed for: r0v3, types: [byte[][], java.io.Serializable[]] */
    public gl(int i, int i2) {
        this.d = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i2, i);
        this.b = i;
        this.c = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final byte a(int i, int i2) {
        return ((byte[][]) this.d)[i2][i];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b(int i, int i2, int i3) {
        ((byte[][]) this.d)[i2][i] = (byte) i3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c(int i, int i2, boolean z) {
        ((byte[][]) this.d)[i2][i] = z ? (byte) 1 : (byte) 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        switch (this.a) {
            case 1:
                StringBuilder sb = new StringBuilder((this.b * 2 * this.c) + 2);
                for (int i = 0; i < this.c; i++) {
                    byte[] bArr = ((byte[][]) this.d)[i];
                    for (int i2 = 0; i2 < this.b; i2++) {
                        byte b = bArr[i2];
                        if (b == 0) {
                            sb.append(" 0");
                        } else if (b != 1) {
                            sb.append("  ");
                        } else {
                            sb.append(" 1");
                        }
                    }
                    sb.append('\n');
                }
                return sb.toString();
            default:
                return super.toString();
        }
    }
}
