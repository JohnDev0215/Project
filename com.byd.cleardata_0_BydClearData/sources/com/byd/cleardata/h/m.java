package com.byd.cleardata.h;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f2688a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f2689b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f2690c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<com.byd.cleardata.d.d> f2691d;
    private com.byd.cleardata.d.d e;

    public m(int i, com.byd.cleardata.d.d dVar) {
        this.f2688a = i;
        this.e = dVar;
    }

    public m(int i, List<com.byd.cleardata.d.d> list) {
        this.f2688a = i;
        this.f2691d = list;
    }

    public m(long j, long j2) {
        this.f2688a = 2;
        this.f2689b = j;
        this.f2690c = j2;
    }

    public com.byd.cleardata.d.d a() {
        return this.e;
    }

    public List<com.byd.cleardata.d.d> b() {
        return this.f2691d;
    }

    public long c() {
        return this.f2689b;
    }

    public int d() {
        return this.f2688a;
    }

    public long e() {
        return this.f2690c;
    }
}
