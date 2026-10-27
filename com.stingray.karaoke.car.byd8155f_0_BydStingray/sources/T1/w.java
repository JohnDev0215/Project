package T1;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class w {
    public static final ThreadLocal d = new ThreadLocal();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8524a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final B8.t f8525b;
    public volatile int c = 0;

    public w(B8.t tVar, int i7) {
        this.f8525b = tVar;
        this.f8524a = i7;
    }

    public final int a(int i7) {
        U1.a aVarC = c();
        int iB = aVarC.b(16);
        if (iB == 0) {
            return 0;
        }
        ByteBuffer byteBuffer = (ByteBuffer) aVarC.o;
        int i10 = iB + aVarC.l;
        return byteBuffer.getInt((i7 * 4) + byteBuffer.getInt(i10) + i10 + 4);
    }

    public final int b() {
        U1.a aVarC = c();
        int iB = aVarC.b(16);
        if (iB == 0) {
            return 0;
        }
        int i7 = iB + aVarC.l;
        return ((ByteBuffer) aVarC.o).getInt(((ByteBuffer) aVarC.o).getInt(i7) + i7);
    }

    public final U1.a c() {
        ThreadLocal threadLocal = d;
        U1.a aVar = (U1.a) threadLocal.get();
        if (aVar == null) {
            aVar = new U1.a();
            threadLocal.set(aVar);
        }
        U1.b bVar = (U1.b) this.f8525b.f808n;
        int iB = bVar.b(6);
        if (iB != 0) {
            int i7 = iB + bVar.l;
            int i10 = (this.f8524a * 4) + ((ByteBuffer) bVar.o).getInt(i7) + i7 + 4;
            int i11 = ((ByteBuffer) bVar.o).getInt(i10) + i10;
            ByteBuffer byteBuffer = (ByteBuffer) bVar.o;
            aVar.o = byteBuffer;
            if (byteBuffer != null) {
                aVar.l = i11;
                int i12 = i11 - byteBuffer.getInt(i11);
                aVar.f8960m = i12;
                aVar.f8961n = ((ByteBuffer) aVar.o).getShort(i12);
            } else {
                aVar.l = 0;
                aVar.f8960m = 0;
                aVar.f8961n = 0;
            }
        }
        return aVar;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        U1.a aVarC = c();
        int iB = aVarC.b(4);
        sb.append(Integer.toHexString(iB != 0 ? ((ByteBuffer) aVarC.o).getInt(iB + aVarC.l) : 0));
        sb.append(", codepoints:");
        int iB2 = b();
        for (int i7 = 0; i7 < iB2; i7++) {
            sb.append(Integer.toHexString(a(i7)));
            sb.append(" ");
        }
        return sb.toString();
    }
}
