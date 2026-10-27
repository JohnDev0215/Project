package b.b.g.m;

import b.b.g.r.r;

/* JADX INFO: compiled from: HideItemInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f880a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f881b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f882c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f883d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f884e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f885f;

    public b(int i) {
        this.f880a = i;
        this.f881b = -1;
        this.f882c = -1;
        this.f883d = -1;
        this.f884e = r.d(i);
    }

    public static b a(int i, int i2) {
        return new b(1, i, i2);
    }

    public static b b(int i, int i2) {
        return new b(2, i, i2);
    }

    public static b c(int i, int i2) {
        return new b(4, i, i2);
    }

    public static b d(int i, int i2, int i3) {
        return new b(4, i, i2, i3);
    }

    public static b e(int i) {
        return new b(4, i);
    }

    public String toString() {
        return "HideItemInfo{moduleIndex=" + this.f880a + ", pageIndex=" + this.f881b + ", itemIndex=" + this.f882c + ", subitemIndex=" + this.f883d + ", moduleCls=" + this.f884e + ", pageCls=" + this.f885f + '}';
    }

    public b(int i, int i2) {
        this.f880a = i;
        this.f881b = i2;
        this.f882c = -1;
        this.f883d = -1;
        this.f884e = r.d(i);
        this.f885f = r.e(i, i2);
    }

    public b(int i, int i2, int i3) {
        this.f880a = i;
        this.f881b = i2;
        this.f882c = i3;
        this.f883d = -1;
        this.f884e = r.d(i);
        this.f885f = r.e(i, i2);
    }

    public b(int i, int i2, int i3, int i4) {
        this.f880a = i;
        this.f881b = i2;
        this.f882c = i3;
        this.f883d = i4;
        this.f884e = r.d(i);
        this.f885f = r.e(i, i2);
    }
}
