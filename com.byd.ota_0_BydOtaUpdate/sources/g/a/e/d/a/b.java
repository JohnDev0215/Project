package g.a.e.d.a;

import com.byd.ota.master.VehicleVersionInfo;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5702a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5703b;

    public b(byte[] bArr) {
        this.f5702a = 0;
        if (bArr.length != 4) {
            throw new IllegalArgumentException("byte array is not an encoded finite field");
        }
        int iD = f.d(bArr);
        this.f5703b = iD;
        if (!j.c(iD)) {
            throw new IllegalArgumentException("byte array is not an encoded finite field");
        }
        this.f5702a = j.a(this.f5703b);
    }

    public static String i(int i2) {
        if (i2 == 0) {
            return "0";
        }
        String str = ((byte) (i2 & 1)) == 1 ? VehicleVersionInfo.FORCE_UPGRADE : "";
        int i3 = i2 >>> 1;
        int i4 = 1;
        while (i3 != 0) {
            if (((byte) (i3 & 1)) == 1) {
                str = str + "+x^" + i4;
            }
            i3 >>>= 1;
            i4++;
        }
        return str;
    }

    public int a(int i2, int i3) {
        return i2 ^ i3;
    }

    public String b(int i2) {
        StringBuilder sb;
        String str;
        String string = "";
        for (int i3 = 0; i3 < this.f5702a; i3++) {
            if ((((byte) i2) & 1) == 0) {
                sb = new StringBuilder();
                str = "0";
            } else {
                sb = new StringBuilder();
                str = VehicleVersionInfo.FORCE_UPGRADE;
            }
            sb.append(str);
            sb.append(string);
            string = sb.toString();
            i2 >>>= 1;
        }
        return string;
    }

    public int c(int i2, int i3) {
        if (i3 == 0) {
            return 1;
        }
        if (i2 == 0) {
            return 0;
        }
        if (i2 == 1) {
            return 1;
        }
        if (i3 < 0) {
            i2 = f(i2);
            i3 = -i3;
        }
        int iH = 1;
        while (i3 != 0) {
            if ((i3 & 1) == 1) {
                iH = h(iH, i2);
            }
            i2 = h(i2, i2);
            i3 >>>= 1;
        }
        return iH;
    }

    public int d() {
        return this.f5702a;
    }

    public byte[] e() {
        return f.c(this.f5703b);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof b)) {
            b bVar = (b) obj;
            if (this.f5702a == bVar.f5702a && this.f5703b == bVar.f5703b) {
                return true;
            }
        }
        return false;
    }

    public int f(int i2) {
        return c(i2, (1 << this.f5702a) - 2);
    }

    public boolean g(int i2) {
        int i3 = this.f5702a;
        return i3 == 31 ? i2 >= 0 : i2 >= 0 && i2 < (1 << i3);
    }

    public int h(int i2, int i3) {
        return j.d(i2, i3, this.f5703b);
    }

    public int hashCode() {
        return this.f5703b;
    }

    public String toString() {
        return "Finite Field GF(2^" + this.f5702a + ") = GF(2)[X]/<" + i(this.f5703b) + "> ";
    }
}
