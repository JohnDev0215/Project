package f2;

import com.alibaba.fastjson.JSONException;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.MathContext;

/* JADX INFO: compiled from: JSONReaderScanner.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends c {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final ThreadLocal<char[]> f4895w = new ThreadLocal<>();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Reader f4896x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public char[] f4897y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f4898z;

    @Override // f2.c, f2.b
    public final BigDecimal Z() {
        int i8 = this.f4887o;
        if (i8 == -1) {
            i8 = 0;
        }
        char cT0 = t0((this.f4886n + i8) - 1);
        int i9 = this.f4886n;
        if (cT0 == 'L' || cT0 == 'S' || cT0 == 'B' || cT0 == 'F' || cT0 == 'D') {
            i9--;
        }
        if (i9 <= 65535) {
            return new BigDecimal(this.f4897y, i8, i9, MathContext.UNLIMITED);
        }
        throw new JSONException("decimal overflow");
    }

    @Override // f2.c, f2.b, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        char[] cArr = this.f4897y;
        if (cArr.length <= 65536) {
            f4895w.set(cArr);
        }
        this.f4897y = null;
        l2.e.a(this.f4896x);
    }

    @Override // f2.b
    public byte[] e0() {
        if (this.f4879g != 26) {
            return l2.e.e(this.f4897y, this.f4887o + 1, this.f4886n);
        }
        throw new JSONException("TODO");
    }

    @Override // f2.c
    public final String e1(int i8, int i9) {
        if (i9 >= 0) {
            return new String(this.f4897y, i8, i9);
        }
        throw new StringIndexOutOfBoundsException(i9);
    }

    @Override // f2.c
    public final char[] f1(int i8, int i9) {
        if (i9 < 0) {
            throw new StringIndexOutOfBoundsException(i9);
        }
        if (i8 == 0) {
            return this.f4897y;
        }
        char[] cArr = new char[i9];
        System.arraycopy(this.f4897y, i8, cArr, 0, i9);
        return cArr;
    }

    @Override // f2.c, f2.b
    public final String l0() {
        int i8 = this.f4887o;
        if (i8 == -1) {
            i8 = 0;
        }
        char cT0 = t0((this.f4886n + i8) - 1);
        int i9 = this.f4886n;
        if (cT0 == 'L' || cT0 == 'S' || cT0 == 'B' || cT0 == 'F' || cT0 == 'D') {
            i9--;
        }
        return new String(this.f4897y, i8, i9);
    }

    @Override // f2.c, f2.b
    public final String n0() {
        if (this.f4888p) {
            return new String(this.f4885m, 0, this.f4886n);
        }
        int i8 = this.f4887o + 1;
        if (i8 < 0) {
            throw new IllegalStateException();
        }
        if (i8 <= this.f4897y.length - this.f4886n) {
            return new String(this.f4897y, i8, this.f4886n);
        }
        throw new IllegalStateException();
    }

    @Override // f2.c, f2.b
    public final char next() {
        int i8 = this.f4883k + 1;
        this.f4883k = i8;
        int i9 = this.f4898z;
        if (i8 >= i9) {
            if (i9 == -1) {
                return (char) 26;
            }
            int i10 = this.f4886n;
            if (i10 > 0) {
                int i11 = i9 - i10;
                if (this.f4882j == '\"' && i11 > 0) {
                    i11--;
                }
                char[] cArr = this.f4897y;
                System.arraycopy(cArr, i11, cArr, 0, i10);
            }
            this.f4887o = -1;
            int i12 = this.f4886n;
            this.f4883k = i12;
            try {
                char[] cArr2 = this.f4897y;
                int length = cArr2.length - i12;
                if (length == 0) {
                    char[] cArr3 = new char[cArr2.length * 2];
                    System.arraycopy(cArr2, 0, cArr3, 0, cArr2.length);
                    this.f4897y = cArr3;
                    length = cArr3.length - i12;
                }
                int i13 = this.f4896x.read(this.f4897y, this.f4883k, length);
                this.f4898z = i13;
                if (i13 == 0) {
                    throw new JSONException("illegal stat, textLength is zero");
                }
                if (i13 == -1) {
                    this.f4882j = (char) 26;
                    return (char) 26;
                }
                this.f4898z = i13 + this.f4883k;
                i8 = i12;
            } catch (IOException e8) {
                throw new JSONException(e8.getMessage(), e8);
            }
        }
        char c8 = this.f4897y[i8];
        this.f4882j = c8;
        return c8;
    }

    @Override // f2.c, f2.b
    public final boolean o() {
        int i8 = 0;
        while (true) {
            char c8 = this.f4897y[i8];
            if (c8 == 26) {
                this.f4879g = 20;
                return true;
            }
            if (!c.z0(c8)) {
                return false;
            }
            i8++;
        }
    }

    @Override // f2.c
    public final String q0(int i8, int i9, int i10, i iVar) {
        return iVar.c(this.f4897y, i8, i9, i10);
    }

    @Override // f2.c
    public final void r0(int i8, char[] cArr, int i9, int i10) {
        System.arraycopy(this.f4897y, i8, cArr, i9, i10);
    }

    @Override // f2.c
    public final boolean s0(char[] cArr) {
        for (int i8 = 0; i8 < cArr.length; i8++) {
            if (t0(this.f4883k + i8) != cArr[i8]) {
                return false;
            }
        }
        return true;
    }

    @Override // f2.c
    public final char t0(int i8) {
        int i9 = this.f4898z;
        if (i8 >= i9) {
            if (i9 == -1) {
                if (i8 < this.f4886n) {
                    return this.f4897y[i8];
                }
                return (char) 26;
            }
            int i10 = this.f4883k;
            if (i10 == 0) {
                char[] cArr = this.f4897y;
                int length = (cArr.length * 3) / 2;
                char[] cArr2 = new char[length];
                System.arraycopy(cArr, i10, cArr2, 0, i9);
                int i11 = this.f4898z;
                try {
                    this.f4898z += this.f4896x.read(cArr2, i11, length - i11);
                    this.f4897y = cArr2;
                } catch (IOException e8) {
                    throw new JSONException(e8.getMessage(), e8);
                }
            } else {
                int i12 = i9 - i10;
                if (i12 > 0) {
                    char[] cArr3 = this.f4897y;
                    System.arraycopy(cArr3, i10, cArr3, 0, i12);
                }
                try {
                    Reader reader = this.f4896x;
                    char[] cArr4 = this.f4897y;
                    int i13 = reader.read(cArr4, i12, cArr4.length - i12);
                    this.f4898z = i13;
                    if (i13 == 0) {
                        throw new JSONException("illegal state, textLength is zero");
                    }
                    if (i13 == -1) {
                        return (char) 26;
                    }
                    this.f4898z = i13 + i12;
                    int i14 = this.f4883k;
                    i8 -= i14;
                    this.f4887o -= i14;
                    this.f4883k = 0;
                } catch (IOException e9) {
                    throw new JSONException(e9.getMessage(), e9);
                }
            }
        }
        return this.f4897y[i8];
    }

    @Override // f2.c
    public final void u0(int i8, int i9, char[] cArr) {
        System.arraycopy(this.f4897y, i8, cArr, 0, i9);
    }

    @Override // f2.c
    public final int x0(char c8, int i8) {
        int i9 = i8 - this.f4883k;
        while (true) {
            char cT0 = t0(this.f4883k + i9);
            if (c8 == cT0) {
                return i9 + this.f4883k;
            }
            if (cT0 == 26) {
                return -1;
            }
            i9++;
        }
    }

    @Override // f2.c
    public boolean y0() {
        if (this.f4898z == -1) {
            return true;
        }
        int i8 = this.f4883k;
        char[] cArr = this.f4897y;
        if (i8 != cArr.length) {
            return this.f4882j == 26 && i8 + 1 >= cArr.length;
        }
        return true;
    }
}
