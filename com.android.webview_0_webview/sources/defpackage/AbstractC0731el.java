package defpackage;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Locale;

/* JADX INFO: renamed from: el, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0731el implements Iterable, Serializable {
    public static final AbstractC0731el t = new C0620cl(AbstractC0119Gl.b);
    public static final InterfaceC0508al u;
    public int s = 0;

    static {
        u = AbstractC0369Uk.a() ? new C0676dl(null) : new C0441Yk(null);
    }

    public static int f(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        }
        if (i2 < i) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
        }
        throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
    }

    public static AbstractC0731el g(byte[] bArr) {
        return h(bArr, 0, bArr.length);
    }

    public static AbstractC0731el h(byte[] bArr, int i, int i2) {
        f(i, i + i2, bArr.length);
        return new C0620cl(u.a(bArr, i, i2));
    }

    public static AbstractC0731el i(String str) {
        return new C0620cl(str.getBytes(AbstractC0119Gl.a));
    }

    public static AbstractC0731el n(byte[] bArr) {
        return new C0620cl(bArr);
    }

    public abstract byte e(int i);

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int iG = this.s;
        if (iG == 0) {
            int size = size();
            C0620cl c0620cl = (C0620cl) this;
            iG = AbstractC0119Gl.g(size, c0620cl.v, c0620cl.o() + 0, size);
            if (iG == 0) {
                iG = 1;
            }
            this.s = iG;
        }
        return iG;
    }

    public abstract void j(byte[] bArr, int i, int i2, int i3);

    public abstract byte k(int i);

    public abstract AbstractC0731el l(int i, int i2);

    public final String m() {
        Charset charset = AbstractC0119Gl.a;
        if (size() == 0) {
            return "";
        }
        C0620cl c0620cl = (C0620cl) this;
        return new String(c0620cl.v, c0620cl.o(), c0620cl.size(), charset);
    }

    public abstract int size();

    public final String toString() {
        String strA;
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(size());
        if (size() <= 50) {
            strA = AbstractC1398qm.a(this);
        } else {
            strA = AbstractC1398qm.a(l(0, 47)) + "...";
        }
        objArr[2] = strA;
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }
}
