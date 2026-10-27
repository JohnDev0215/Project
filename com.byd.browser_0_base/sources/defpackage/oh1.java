package defpackage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;

/* JADX INFO: compiled from: MetadataListReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class oh1 {

    /* JADX INFO: compiled from: MetadataListReader.java */
    public static class a {
        public final ByteBuffer a;

        public a(ByteBuffer byteBuffer) {
            this.a = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        public final long a() {
            return ((long) this.a.getInt()) & 4294967295L;
        }

        public final void b(int i) {
            ByteBuffer byteBuffer = this.a;
            byteBuffer.position(byteBuffer.position() + i);
        }
    }

    public static nh1 a(MappedByteBuffer mappedByteBuffer) throws IOException {
        ByteBuffer byteBuffer;
        long jA;
        ByteBuffer byteBufferDuplicate = mappedByteBuffer.duplicate();
        a aVar = new a(byteBufferDuplicate);
        aVar.b(4);
        int i = byteBufferDuplicate.getShort() & 65535;
        if (i > 100) {
            throw new IOException("Cannot read metadata.");
        }
        aVar.b(6);
        int i2 = 0;
        while (true) {
            byteBuffer = aVar.a;
            if (i2 >= i) {
                jA = -1;
                break;
            }
            int i3 = byteBuffer.getInt();
            aVar.b(4);
            jA = aVar.a();
            aVar.b(4);
            if (1835365473 == i3) {
                break;
            }
            i2++;
        }
        if (jA != -1) {
            aVar.b((int) (jA - ((long) byteBufferDuplicate.position())));
            aVar.b(12);
            long jA2 = aVar.a();
            for (int i4 = 0; i4 < jA2; i4++) {
                int i5 = byteBuffer.getInt();
                long jA3 = aVar.a();
                aVar.a();
                if (1164798569 == i5 || 1701669481 == i5) {
                    byteBufferDuplicate.position((int) (jA3 + jA));
                    nh1 nh1Var = new nh1();
                    byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
                    nh1Var.b(byteBufferDuplicate.position() + byteBufferDuplicate.getInt(byteBufferDuplicate.position()), byteBufferDuplicate);
                    return nh1Var;
                }
            }
        }
        throw new IOException("Cannot read metadata.");
    }
}
