package g.a.a;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends q implements w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final char[] f3767c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f3768d;
    public final int s;

    public b(byte[] bArr, int i2) {
        Objects.requireNonNull(bArr, "data cannot be null");
        if (bArr.length == 0 && i2 != 0) {
            throw new IllegalArgumentException("zero length data with non-zero pad bits");
        }
        if (i2 > 7 || i2 < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        }
        this.f3768d = g.a.f.a.f(bArr);
        this.s = i2;
    }

    public static byte[] n(byte[] bArr, int i2) {
        byte[] bArrF = g.a.f.a.f(bArr);
        if (i2 > 0) {
            int length = bArr.length - 1;
            bArrF[length] = (byte) ((255 << i2) & bArrF[length]);
        }
        return bArrF;
    }

    public static b o(int i2, InputStream inputStream) throws IOException {
        if (i2 < 1) {
            throw new IllegalArgumentException("truncated BIT STRING detected");
        }
        int i3 = inputStream.read();
        int i4 = i2 - 1;
        byte[] bArr = new byte[i4];
        if (i4 != 0) {
            if (g.a.f.k.a.c(inputStream, bArr) != i4) {
                throw new EOFException("EOF encountered in middle of BIT STRING");
            }
            if (i3 > 0 && i3 < 8) {
                int i5 = i4 - 1;
                if (bArr[i5] != ((byte) (bArr[i5] & (255 << i3)))) {
                    return new m1(bArr, i3);
                }
            }
        }
        return new o0(bArr, i3);
    }

    @Override // g.a.a.w
    public String d() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new p(byteArrayOutputStream).j(this);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i2 = 0; i2 != byteArray.length; i2++) {
                char[] cArr = f3767c;
                stringBuffer.append(cArr[(byteArray[i2] >>> 4) & 15]);
                stringBuffer.append(cArr[byteArray[i2] & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException e2) {
            throw new ASN1ParsingException("Internal error encoding BitString: " + e2.getMessage(), e2);
        }
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (!(qVar instanceof b)) {
            return false;
        }
        b bVar = (b) qVar;
        return this.s == bVar.s && g.a.f.a.a(p(), bVar.p());
    }

    @Override // g.a.a.l
    public int hashCode() {
        return this.s ^ g.a.f.a.z(p());
    }

    @Override // g.a.a.q
    public q l() {
        return new o0(this.f3768d, this.s);
    }

    @Override // g.a.a.q
    public q m() {
        return new m1(this.f3768d, this.s);
    }

    public byte[] p() {
        return n(this.f3768d, this.s);
    }

    public byte[] q() {
        if (this.s == 0) {
            return g.a.f.a.f(this.f3768d);
        }
        throw new IllegalStateException("attempt to get non-octet aligned data from BIT STRING");
    }

    public int r() {
        return this.s;
    }

    public String toString() {
        return d();
    }
}
