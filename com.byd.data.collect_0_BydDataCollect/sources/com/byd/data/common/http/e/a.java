package com.byd.data.common.http.e;

import f.a0;
import f.f0;
import f.z;
import java.io.File;
import java.util.List;
import okio.BufferedSink;

/* JADX INFO: compiled from: ParameterRequest.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends f0 {

    /* JADX INFO: renamed from: a */
    private f0 f3417a = f0.d(a0.f5000e, "test_send_parameter");

    /* JADX INFO: renamed from: b */
    public List<File> f3418b;

    /* JADX INFO: renamed from: c */
    public int f3419c;

    /* JADX INFO: renamed from: d */
    public int f3420d;

    public a(List<File> list, int i2, int i3) {
        this.f3418b = list;
        this.f3419c = i2;
        this.f3420d = i3;
    }

    @Override // f.f0
    public long a() {
        return this.f3417a.a();
    }

    @Override // f.f0
    public z b() {
        return this.f3417a.b();
    }

    @Override // f.f0
    public void j(BufferedSink bufferedSink) {
        this.f3417a.j(bufferedSink);
    }
}
