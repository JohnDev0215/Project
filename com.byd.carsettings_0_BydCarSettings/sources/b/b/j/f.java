package b.b.j;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: NfcMsg.java */
/* JADX INFO: loaded from: classes2.dex */
public class f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1946a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1947b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1948c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1949d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1950e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f1951f;
    public long g;
    public double h;
    public int[] i;
    public float[] j;
    public long[] k;
    public double[] l;
    public List m;
    public Object n;

    public f(int i, int i2) {
        this.f1946a = i;
        this.f1947b = i2;
    }

    public int a() {
        return this.f1946a;
    }

    public void b(Object obj) {
        this.n = obj;
    }

    public String toString() {
        return "CarsettingMsg{msgId=" + this.f1946a + ", intArg=" + this.f1947b + ", intArg2=" + this.f1948c + ", intArg3=" + this.f1949d + ", intArg4=" + this.f1950e + ", floatArg=" + this.f1951f + ", longArg=" + this.g + ", doubleArg=" + this.h + ", intArgs=" + Arrays.toString(this.i) + ", floatArgs=" + Arrays.toString(this.j) + ", longArgs=" + Arrays.toString(this.k) + ", doubleArgs=" + Arrays.toString(this.l) + ", listArgs=" + this.m + ", object=" + this.n + '}';
    }
}
