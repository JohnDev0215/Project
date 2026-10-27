package androidx.recyclerview.widget;

/* JADX INFO: compiled from: BatchingListUpdateCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final k f3019a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f3020b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f3021c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f3022d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Object f3023e = null;

    public c(k kVar) {
        this.f3019a = kVar;
    }

    @Override // androidx.recyclerview.widget.k
    public void a(int i7, int i8) {
        int i9;
        if (this.f3020b == 2 && (i9 = this.f3021c) >= i7 && i9 <= i7 + i8) {
            this.f3022d += i8;
            this.f3021c = i7;
        } else {
            e();
            this.f3021c = i7;
            this.f3022d = i8;
            this.f3020b = 2;
        }
    }

    @Override // androidx.recyclerview.widget.k
    public void b(int i7, int i8) {
        int i9;
        if (this.f3020b == 1 && i7 >= (i9 = this.f3021c)) {
            int i10 = this.f3022d;
            if (i7 <= i9 + i10) {
                this.f3022d = i10 + i8;
                this.f3021c = Math.min(i7, i9);
                return;
            }
        }
        e();
        this.f3021c = i7;
        this.f3022d = i8;
        this.f3020b = 1;
    }

    @Override // androidx.recyclerview.widget.k
    public void c(int i7, int i8) {
        e();
        this.f3019a.c(i7, i8);
    }

    @Override // androidx.recyclerview.widget.k
    public void d(int i7, int i8, Object obj) {
        int i9;
        if (this.f3020b == 3) {
            int i10 = this.f3021c;
            int i11 = this.f3022d;
            if (i7 <= i10 + i11 && (i9 = i7 + i8) >= i10 && this.f3023e == obj) {
                this.f3021c = Math.min(i7, i10);
                this.f3022d = Math.max(i11 + i10, i9) - this.f3021c;
                return;
            }
        }
        e();
        this.f3021c = i7;
        this.f3022d = i8;
        this.f3023e = obj;
        this.f3020b = 3;
    }

    public void e() {
        int i7 = this.f3020b;
        if (i7 == 0) {
            return;
        }
        if (i7 == 1) {
            this.f3019a.b(this.f3021c, this.f3022d);
        } else if (i7 == 2) {
            this.f3019a.a(this.f3021c, this.f3022d);
        } else if (i7 == 3) {
            this.f3019a.d(this.f3021c, this.f3022d, this.f3023e);
        }
        this.f3023e = null;
        this.f3020b = 0;
    }
}
