package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
public abstract class G {
    public final K l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f11919m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f11920n = -1;
    public final /* synthetic */ J o;

    public G(J j3, K k10) {
        this.o = j3;
        this.l = k10;
    }

    public final void a(boolean z6) {
        if (z6 == this.f11919m) {
            return;
        }
        this.f11919m = z6;
        int i7 = z6 ? 1 : -1;
        J j3 = this.o;
        int i10 = j3.c;
        j3.c = i7 + i10;
        if (!j3.d) {
            j3.d = true;
            while (true) {
                try {
                    int i11 = j3.c;
                    if (i10 == i11) {
                        break;
                    }
                    boolean z10 = i10 == 0 && i11 > 0;
                    boolean z11 = i10 > 0 && i11 == 0;
                    if (z10) {
                        j3.e();
                    } else if (z11) {
                        j3.f();
                    }
                    i10 = i11;
                } catch (Throwable th) {
                    j3.d = false;
                    throw th;
                }
            }
            j3.d = false;
        }
        if (this.f11919m) {
            j3.c(this);
        }
    }

    public void c() {
    }

    public boolean d(A a10) {
        return false;
    }

    public abstract boolean e();
}
