package com.google.zxing;

import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: compiled from: Result.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f4286a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final byte[] f4287b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4288c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private i[] f4289d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final BarcodeFormat f4290e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Map<ResultMetadataType, Object> f4291f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final long f4292g;

    public h(String str, byte[] bArr, i[] iVarArr, BarcodeFormat barcodeFormat) {
        this(str, bArr, iVarArr, barcodeFormat, System.currentTimeMillis());
    }

    public void a(i[] iVarArr) {
        i[] iVarArr2 = this.f4289d;
        if (iVarArr2 == null) {
            this.f4289d = iVarArr;
            return;
        }
        if (iVarArr == null || iVarArr.length <= 0) {
            return;
        }
        i[] iVarArr3 = new i[iVarArr2.length + iVarArr.length];
        System.arraycopy(iVarArr2, 0, iVarArr3, 0, iVarArr2.length);
        System.arraycopy(iVarArr, 0, iVarArr3, iVarArr2.length, iVarArr.length);
        this.f4289d = iVarArr3;
    }

    public BarcodeFormat b() {
        return this.f4290e;
    }

    public byte[] c() {
        return this.f4287b;
    }

    public Map<ResultMetadataType, Object> d() {
        return this.f4291f;
    }

    public i[] e() {
        return this.f4289d;
    }

    public String f() {
        return this.f4286a;
    }

    public void g(Map<ResultMetadataType, Object> map) {
        if (map != null) {
            Map<ResultMetadataType, Object> map2 = this.f4291f;
            if (map2 == null) {
                this.f4291f = map;
            } else {
                map2.putAll(map);
            }
        }
    }

    public void h(ResultMetadataType resultMetadataType, Object obj) {
        if (this.f4291f == null) {
            this.f4291f = new EnumMap(ResultMetadataType.class);
        }
        this.f4291f.put(resultMetadataType, obj);
    }

    public String toString() {
        return this.f4286a;
    }

    public h(String str, byte[] bArr, i[] iVarArr, BarcodeFormat barcodeFormat, long j) {
        this(str, bArr, bArr == null ? 0 : bArr.length * 8, iVarArr, barcodeFormat, j);
    }

    public h(String str, byte[] bArr, int i, i[] iVarArr, BarcodeFormat barcodeFormat, long j) {
        this.f4286a = str;
        this.f4287b = bArr;
        this.f4288c = i;
        this.f4289d = iVarArr;
        this.f4290e = barcodeFormat;
        this.f4291f = null;
        this.f4292g = j;
    }
}
