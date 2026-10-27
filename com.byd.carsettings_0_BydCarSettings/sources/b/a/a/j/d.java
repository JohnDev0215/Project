package b.a.a.j;

import com.alibaba.fastjson.JSONException;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.MathContext;

/* JADX INFO: compiled from: JSONReaderScanner.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends c {
    public static final ThreadLocal<char[]> t = new ThreadLocal<>();
    public Reader u;
    public char[] v;
    public int w;

    @Override // b.a.a.j.c, b.a.a.j.b
    public final BigDecimal J() {
        int i = this.l;
        if (i == -1) {
            i = 0;
        }
        char cA0 = a0((this.k + i) - 1);
        int i2 = this.k;
        if (cA0 == 'L' || cA0 == 'S' || cA0 == 'B' || cA0 == 'F' || cA0 == 'D') {
            i2--;
        }
        if (i2 <= 65535) {
            return new BigDecimal(this.v, i, i2, MathContext.UNLIMITED);
        }
        throw new JSONException("decimal overflow");
    }

    @Override // b.a.a.j.c
    public final String L0(int i, int i2) {
        if (i2 >= 0) {
            return new String(this.v, i, i2);
        }
        throw new StringIndexOutOfBoundsException(i2);
    }

    @Override // b.a.a.j.c
    public final char[] M0(int i, int i2) {
        if (i2 < 0) {
            throw new StringIndexOutOfBoundsException(i2);
        }
        if (i == 0) {
            return this.v;
        }
        char[] cArr = new char[i2];
        System.arraycopy(this.v, i, cArr, 0, i2);
        return cArr;
    }

    @Override // b.a.a.j.b
    public byte[] O() {
        if (this.f242d != 26) {
            return b.a.a.n.e.e(this.v, this.l + 1, this.k);
        }
        throw new JSONException("TODO");
    }

    @Override // b.a.a.j.c, b.a.a.j.b
    public final String T() {
        int i = this.l;
        if (i == -1) {
            i = 0;
        }
        char cA0 = a0((this.k + i) - 1);
        int i2 = this.k;
        if (cA0 == 'L' || cA0 == 'S' || cA0 == 'B' || cA0 == 'F' || cA0 == 'D') {
            i2--;
        }
        return new String(this.v, i, i2);
    }

    @Override // b.a.a.j.c, b.a.a.j.b
    public final String V() {
        if (this.m) {
            return new String(this.j, 0, this.k);
        }
        int i = this.l + 1;
        if (i < 0) {
            throw new IllegalStateException();
        }
        if (i <= this.v.length - this.k) {
            return new String(this.v, i, this.k);
        }
        throw new IllegalStateException();
    }

    @Override // b.a.a.j.c
    public final String X(int i, int i2, int i3, i iVar) {
        return iVar.c(this.v, i, i2, i3);
    }

    @Override // b.a.a.j.c
    public final void Y(int i, char[] cArr, int i2, int i3) {
        System.arraycopy(this.v, i, cArr, i2, i3);
    }

    @Override // b.a.a.j.c
    public final boolean Z(char[] cArr) {
        for (int i = 0; i < cArr.length; i++) {
            if (a0(this.h + i) != cArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // b.a.a.j.c
    public final char a0(int i) {
        int i2 = this.w;
        if (i >= i2) {
            if (i2 == -1) {
                if (i < this.k) {
                    return this.v[i];
                }
                return (char) 26;
            }
            int i3 = this.h;
            if (i3 == 0) {
                char[] cArr = this.v;
                int length = (cArr.length * 3) / 2;
                char[] cArr2 = new char[length];
                System.arraycopy(cArr, i3, cArr2, 0, i2);
                int i4 = this.w;
                try {
                    this.w += this.u.read(cArr2, i4, length - i4);
                    this.v = cArr2;
                } catch (IOException e2) {
                    throw new JSONException(e2.getMessage(), e2);
                }
            } else {
                int i5 = i2 - i3;
                if (i5 > 0) {
                    char[] cArr3 = this.v;
                    System.arraycopy(cArr3, i3, cArr3, 0, i5);
                }
                try {
                    Reader reader = this.u;
                    char[] cArr4 = this.v;
                    int i6 = reader.read(cArr4, i5, cArr4.length - i5);
                    this.w = i6;
                    if (i6 == 0) {
                        throw new JSONException("illegal state, textLength is zero");
                    }
                    if (i6 == -1) {
                        return (char) 26;
                    }
                    this.w = i6 + i5;
                    int i7 = this.h;
                    i -= i7;
                    this.l -= i7;
                    this.h = 0;
                } catch (IOException e3) {
                    throw new JSONException(e3.getMessage(), e3);
                }
            }
        }
        return this.v[i];
    }

    @Override // b.a.a.j.c
    public final void b0(int i, int i2, char[] cArr) {
        System.arraycopy(this.v, i, cArr, 0, i2);
    }

    @Override // b.a.a.j.c, b.a.a.j.b, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        char[] cArr = this.v;
        if (cArr.length <= 65536) {
            t.set(cArr);
        }
        this.v = null;
        b.a.a.n.e.a(this.u);
    }

    @Override // b.a.a.j.c
    public final int e0(char c2, int i) {
        int i2 = i - this.h;
        while (true) {
            char cA0 = a0(this.h + i2);
            if (c2 == cA0) {
                return i2 + this.h;
            }
            if (cA0 == 26) {
                return -1;
            }
            i2++;
        }
    }

    @Override // b.a.a.j.c
    public boolean f0() {
        if (this.w == -1) {
            return true;
        }
        int i = this.h;
        char[] cArr = this.v;
        if (i != cArr.length) {
            return this.g == 26 && i + 1 >= cArr.length;
        }
        return true;
    }

    @Override // b.a.a.j.c, b.a.a.j.b
    public final boolean i() {
        int i = 0;
        while (true) {
            char c2 = this.v[i];
            if (c2 == 26) {
                this.f242d = 20;
                return true;
            }
            if (!c.g0(c2)) {
                return false;
            }
            i++;
        }
    }

    @Override // b.a.a.j.c, b.a.a.j.b
    public final char next() {
        int i = this.h + 1;
        this.h = i;
        int i2 = this.w;
        if (i >= i2) {
            if (i2 == -1) {
                return (char) 26;
            }
            int i3 = this.k;
            if (i3 > 0) {
                int i4 = i2 - i3;
                if (this.g == '\"' && i4 > 0) {
                    i4--;
                }
                char[] cArr = this.v;
                System.arraycopy(cArr, i4, cArr, 0, i3);
            }
            this.l = -1;
            int i5 = this.k;
            this.h = i5;
            try {
                char[] cArr2 = this.v;
                int length = cArr2.length - i5;
                if (length == 0) {
                    char[] cArr3 = new char[cArr2.length * 2];
                    System.arraycopy(cArr2, 0, cArr3, 0, cArr2.length);
                    this.v = cArr3;
                    length = cArr3.length - i5;
                }
                int i6 = this.u.read(this.v, this.h, length);
                this.w = i6;
                if (i6 == 0) {
                    throw new JSONException("illegal stat, textLength is zero");
                }
                if (i6 == -1) {
                    this.g = (char) 26;
                    return (char) 26;
                }
                this.w = i6 + this.h;
                i = i5;
            } catch (IOException e2) {
                throw new JSONException(e2.getMessage(), e2);
            }
        }
        char c2 = this.v[i];
        this.g = c2;
        return c2;
    }
}
