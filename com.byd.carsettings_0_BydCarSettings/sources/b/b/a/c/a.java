package b.b.a.c;

import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Arrays;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: CarsettingMsg.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f476a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f477b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f478c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f479d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f480e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f481f;
    public long g;
    public double h;
    public int[] i;
    public float[] j;
    public long[] k;
    public double[] l;
    public List m;
    public String n;
    public Object o;

    public a(int i) {
        this.f476a = i;
    }

    public static void j(a aVar) {
        BydLogHelper.logD("msgid：" + aVar.h());
        EventBus.getDefault().post(aVar);
    }

    public double a() {
        return this.h;
    }

    public float b() {
        return this.f481f;
    }

    public int c() {
        return this.f477b;
    }

    public int d() {
        return this.f478c;
    }

    public int e() {
        return this.f479d;
    }

    public int f() {
        return this.f480e;
    }

    public String g() {
        return this.n;
    }

    public int h() {
        return this.f476a;
    }

    public Object i() {
        return this.o;
    }

    public void k(Object obj) {
        this.o = obj;
    }

    public String toString() {
        return "CarsettingMsg{msgId=" + this.f476a + ", intArg=" + this.f477b + ", intArg2=" + this.f478c + ", intArg3=" + this.f479d + ", intArg4=" + this.f480e + ", floatArg=" + this.f481f + ", longArg=" + this.g + ", doubleArg=" + this.h + ", intArgs=" + Arrays.toString(this.i) + ", floatArgs=" + Arrays.toString(this.j) + ", longArgs=" + Arrays.toString(this.k) + ", doubleArgs=" + Arrays.toString(this.l) + ", listArgs=" + this.m + ", object=" + this.o + '}';
    }

    public a(int i, int i2) {
        this.f476a = i;
        this.f477b = i2;
    }

    public a(int i, int i2, int i3) {
        this.f476a = i;
        this.f477b = i2;
        this.f478c = i3;
    }

    public a(int i, Object obj) {
        this.f476a = i;
        this.o = obj;
    }

    public a(int i, float f2) {
        this.f476a = i;
        this.f481f = f2;
    }

    public a(int i, long j) {
        this.f476a = i;
        this.g = j;
    }

    public a(int i, double d2) {
        this.f476a = i;
        this.h = d2;
    }

    public a(int i, int i2, int i3, int i4, int i5) {
        this.f476a = i;
        this.f477b = i2;
        this.f478c = i3;
        this.f479d = i4;
        this.f480e = i5;
    }

    public a(int i, String str) {
        this.f476a = i;
        this.n = str;
    }
}
