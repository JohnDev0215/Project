package g.a.b.l0.h;

import g.a.b.n;
import g.a.b.t;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Hashtable f5123a;

    static {
        Hashtable hashtable = new Hashtable();
        f5123a = hashtable;
        hashtable.put("SHA-1", g.a.f.d.b(128));
        hashtable.put("SHA-224", g.a.f.d.b(192));
        hashtable.put("SHA-256", g.a.f.d.b(256));
        hashtable.put("SHA-384", g.a.f.d.b(256));
        hashtable.put("SHA-512", g.a.f.d.b(256));
        hashtable.put("SHA-512/224", g.a.f.d.b(192));
        hashtable.put("SHA-512/256", g.a.f.d.b(256));
    }

    public static int a(n nVar) {
        return ((Integer) f5123a.get(nVar.getAlgorithmName())).intValue();
    }

    public static int b(t tVar) {
        String algorithmName = tVar.getAlgorithmName();
        return ((Integer) f5123a.get(algorithmName.substring(0, algorithmName.indexOf("/")))).intValue();
    }

    public static byte[] c(n nVar, byte[] bArr, int i2) {
        int i3 = (i2 + 7) / 8;
        byte[] bArr2 = new byte[i3];
        int digestSize = i3 / nVar.getDigestSize();
        int digestSize2 = nVar.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        int i4 = 1;
        int i5 = 0;
        for (int i6 = 0; i6 <= digestSize; i6++) {
            nVar.update((byte) i4);
            nVar.update((byte) (i2 >> 24));
            nVar.update((byte) (i2 >> 16));
            nVar.update((byte) (i2 >> 8));
            nVar.update((byte) i2);
            nVar.update(bArr, 0, bArr.length);
            nVar.doFinal(bArr3, 0);
            int i7 = i6 * digestSize2;
            int i8 = i3 - i7;
            if (i8 > digestSize2) {
                i8 = digestSize2;
            }
            System.arraycopy(bArr3, 0, bArr2, i7, i8);
            i4++;
        }
        int i9 = i2 % 8;
        if (i9 != 0) {
            int i10 = 8 - i9;
            int i11 = 0;
            while (i5 != i3) {
                int i12 = bArr2[i5] & 255;
                bArr2[i5] = (byte) ((i11 << (8 - i10)) | (i12 >>> i10));
                i5++;
                i11 = i12;
            }
        }
        return bArr2;
    }
}
