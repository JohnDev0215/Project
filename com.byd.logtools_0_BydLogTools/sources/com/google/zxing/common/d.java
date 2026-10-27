package com.google.zxing.common;

import java.util.List;

/* JADX INFO: compiled from: DecoderResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte[] f4202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4203b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f4204c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<byte[]> f4205d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f4206e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Integer f4207f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Integer f4208g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Object f4209h;
    private final int i;
    private final int j;

    public d(byte[] bArr, String str, List<byte[]> list, String str2) {
        this(bArr, str, list, str2, -1, -1);
    }

    public List<byte[]> a() {
        return this.f4205d;
    }

    public String b() {
        return this.f4206e;
    }

    public int c() {
        return this.f4203b;
    }

    public Object d() {
        return this.f4209h;
    }

    public byte[] e() {
        return this.f4202a;
    }

    public int f() {
        return this.i;
    }

    public int g() {
        return this.j;
    }

    public String h() {
        return this.f4204c;
    }

    public boolean i() {
        return this.i >= 0 && this.j >= 0;
    }

    public void j(Integer num) {
        this.f4208g = num;
    }

    public void k(Integer num) {
        this.f4207f = num;
    }

    public void l(int i) {
        this.f4203b = i;
    }

    public void m(Object obj) {
        this.f4209h = obj;
    }

    public d(byte[] bArr, String str, List<byte[]> list, String str2, int i, int i2) {
        this.f4202a = bArr;
        this.f4203b = bArr == null ? 0 : bArr.length * 8;
        this.f4204c = str;
        this.f4205d = list;
        this.f4206e = str2;
        this.i = i2;
        this.j = i;
    }
}
