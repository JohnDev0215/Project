package k1;

import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: renamed from: k1.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1598c implements Cloneable {
    public final char[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f17620m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f17621n = Long.MAX_VALUE;
    public AbstractC1597b o;

    public AbstractC1598c(char[] cArr) {
        this.l = cArr;
    }

    @Override // 
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public AbstractC1598c clone() {
        try {
            return (AbstractC1598c) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }

    public final String d() {
        String str = new String(this.l);
        if (str.length() < 1) {
            return "";
        }
        long j3 = this.f17621n;
        if (j3 != Long.MAX_VALUE) {
            long j10 = this.f17620m;
            if (j3 >= j10) {
                return str.substring((int) j10, ((int) j3) + 1);
            }
        }
        long j11 = this.f17620m;
        return str.substring((int) j11, ((int) j11) + 1);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractC1598c)) {
            return false;
        }
        AbstractC1598c abstractC1598c = (AbstractC1598c) obj;
        if (this.f17620m == abstractC1598c.f17620m && this.f17621n == abstractC1598c.f17621n && Arrays.equals(this.l, abstractC1598c.l)) {
            return Objects.equals(this.o, abstractC1598c.o);
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = Arrays.hashCode(this.l) * 31;
        long j3 = this.f17620m;
        int i7 = (iHashCode + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        long j10 = this.f17621n;
        int i10 = (i7 + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        AbstractC1597b abstractC1597b = this.o;
        return (i10 + (abstractC1597b != null ? abstractC1597b.hashCode() : 0)) * 31;
    }

    public float l() {
        if (this instanceof e) {
            return ((e) this).l();
        }
        return Float.NaN;
    }

    public int m() {
        if (this instanceof e) {
            return ((e) this).m();
        }
        return 0;
    }

    public final String q() {
        String string = getClass().toString();
        return string.substring(string.lastIndexOf(46) + 1);
    }

    public final void r(long j3) {
        if (this.f17621n != Long.MAX_VALUE) {
            return;
        }
        this.f17621n = j3;
        AbstractC1597b abstractC1597b = this.o;
        if (abstractC1597b != null) {
            abstractC1597b.s(this);
        }
    }

    public String toString() {
        long j3 = this.f17620m;
        long j10 = this.f17621n;
        if (j3 > j10 || j10 == Long.MAX_VALUE) {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass());
            sb.append(" (INVALID, ");
            sb.append(this.f17620m);
            sb.append("-");
            return android.hardware.bydauto.ac.tsimpl.a.f(this.f17621n, ")", sb);
        }
        return q() + " (" + this.f17620m + " : " + this.f17621n + ") <<" + new String(this.l).substring((int) this.f17620m, ((int) this.f17621n) + 1) + ">>";
    }
}
