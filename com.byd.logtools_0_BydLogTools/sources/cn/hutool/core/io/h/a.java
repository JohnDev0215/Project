package cn.hutool.core.io.h;

import cn.hutool.core.io.g;
import okio.Segment;

/* JADX INFO: compiled from: IoCopier.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<S, T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final int f2928a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final long f2929b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected g f2930c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected boolean f2931d;

    public a(int i, long j, g gVar) {
        this.f2928a = i <= 0 ? Segment.SIZE : i;
        this.f2929b = j <= 0 ? Long.MAX_VALUE : j;
        this.f2930c = gVar;
    }

    protected int a(long j) {
        return (int) Math.min(this.f2928a, j);
    }
}
