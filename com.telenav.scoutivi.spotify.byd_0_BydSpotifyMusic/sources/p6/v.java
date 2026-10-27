package p6;

/* JADX INFO: compiled from: Segment.kt */
/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final a f8901h = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f8902a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f8903b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f8904c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f8905d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8906e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public v f8907f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public v f8908g;

    /* JADX INFO: compiled from: Segment.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    public v() {
        this.f8902a = new byte[8192];
        this.f8906e = true;
        this.f8905d = false;
    }

    public final void a() {
        v vVar = this.f8908g;
        int i7 = 0;
        if (!(vVar != this)) {
            throw new IllegalStateException("cannot compact".toString());
        }
        kotlin.jvm.internal.l.c(vVar);
        if (vVar.f8906e) {
            int i8 = this.f8904c - this.f8903b;
            v vVar2 = this.f8908g;
            kotlin.jvm.internal.l.c(vVar2);
            int i9 = 8192 - vVar2.f8904c;
            v vVar3 = this.f8908g;
            kotlin.jvm.internal.l.c(vVar3);
            if (!vVar3.f8905d) {
                v vVar4 = this.f8908g;
                kotlin.jvm.internal.l.c(vVar4);
                i7 = vVar4.f8903b;
            }
            if (i8 > i9 + i7) {
                return;
            }
            v vVar5 = this.f8908g;
            kotlin.jvm.internal.l.c(vVar5);
            f(vVar5, i8);
            b();
            w.b(this);
        }
    }

    public final v b() {
        v vVar = this.f8907f;
        if (vVar == this) {
            vVar = null;
        }
        v vVar2 = this.f8908g;
        kotlin.jvm.internal.l.c(vVar2);
        vVar2.f8907f = this.f8907f;
        v vVar3 = this.f8907f;
        kotlin.jvm.internal.l.c(vVar3);
        vVar3.f8908g = this.f8908g;
        this.f8907f = null;
        this.f8908g = null;
        return vVar;
    }

    public final v c(v segment) {
        kotlin.jvm.internal.l.e(segment, "segment");
        segment.f8908g = this;
        segment.f8907f = this.f8907f;
        v vVar = this.f8907f;
        kotlin.jvm.internal.l.c(vVar);
        vVar.f8908g = segment;
        this.f8907f = segment;
        return segment;
    }

    public final v d() {
        this.f8905d = true;
        return new v(this.f8902a, this.f8903b, this.f8904c, true, false);
    }

    public final v e(int i7) {
        v vVarC;
        if (!(i7 > 0 && i7 <= this.f8904c - this.f8903b)) {
            throw new IllegalArgumentException("byteCount out of range".toString());
        }
        if (i7 >= 1024) {
            vVarC = d();
        } else {
            vVarC = w.c();
            byte[] bArr = this.f8902a;
            byte[] bArr2 = vVarC.f8902a;
            int i8 = this.f8903b;
            h5.k.e(bArr, bArr2, 0, i8, i8 + i7, 2, null);
        }
        vVarC.f8904c = vVarC.f8903b + i7;
        this.f8903b += i7;
        v vVar = this.f8908g;
        kotlin.jvm.internal.l.c(vVar);
        vVar.c(vVarC);
        return vVarC;
    }

    public final void f(v sink, int i7) {
        kotlin.jvm.internal.l.e(sink, "sink");
        if (!sink.f8906e) {
            throw new IllegalStateException("only owner can write".toString());
        }
        int i8 = sink.f8904c;
        if (i8 + i7 > 8192) {
            if (sink.f8905d) {
                throw new IllegalArgumentException();
            }
            int i9 = sink.f8903b;
            if ((i8 + i7) - i9 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = sink.f8902a;
            h5.k.e(bArr, bArr, 0, i9, i8, 2, null);
            sink.f8904c -= sink.f8903b;
            sink.f8903b = 0;
        }
        byte[] bArr2 = this.f8902a;
        byte[] bArr3 = sink.f8902a;
        int i10 = sink.f8904c;
        int i11 = this.f8903b;
        h5.k.c(bArr2, bArr3, i10, i11, i11 + i7);
        sink.f8904c += i7;
        this.f8903b += i7;
    }

    public v(byte[] data, int i7, int i8, boolean z6, boolean z7) {
        kotlin.jvm.internal.l.e(data, "data");
        this.f8902a = data;
        this.f8903b = i7;
        this.f8904c = i8;
        this.f8905d = z6;
        this.f8906e = z7;
    }
}
