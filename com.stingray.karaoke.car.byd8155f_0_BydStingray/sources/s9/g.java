package s9;

import A7.m;
import java.util.ArrayList;
import r9.z;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z f22391a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f22392b;
    public final String c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f22393e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f22394f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f22395g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f22396h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f22397i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f22398j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Long f22399k;
    public final Long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Long f22400m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Integer f22401n;
    public final Integer o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Integer f22402p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ArrayList f22403q;

    public g(z zVar, boolean z6, String str, long j3, long j10, long j11, int i7, long j12, int i10, int i11, Long l, Long l10, Long l11, Integer num, Integer num2, Integer num3) {
        m.f(zVar, "canonicalPath");
        m.f(str, "comment");
        this.f22391a = zVar;
        this.f22392b = z6;
        this.c = str;
        this.d = j3;
        this.f22393e = j10;
        this.f22394f = j11;
        this.f22395g = i7;
        this.f22396h = j12;
        this.f22397i = i10;
        this.f22398j = i11;
        this.f22399k = l;
        this.l = l10;
        this.f22400m = l11;
        this.f22401n = num;
        this.o = num2;
        this.f22402p = num3;
        this.f22403q = new ArrayList();
    }

    public /* synthetic */ g(z zVar, boolean z6, String str, long j3, long j10, long j11, int i7, long j12, int i10, int i11, Long l, Long l10, Long l11, int i12) {
        this(zVar, z6, (i12 & 4) != 0 ? "" : str, (i12 & 8) != 0 ? -1L : j3, (i12 & 16) != 0 ? -1L : j10, (i12 & 32) != 0 ? -1L : j11, (i12 & 64) != 0 ? -1 : i7, (i12 & 128) != 0 ? -1L : j12, (i12 & 256) != 0 ? -1 : i10, (i12 & 512) != 0 ? -1 : i11, (i12 & 1024) != 0 ? null : l, (i12 & 2048) != 0 ? null : l10, (i12 & 4096) != 0 ? null : l11, null, null, null);
    }
}
