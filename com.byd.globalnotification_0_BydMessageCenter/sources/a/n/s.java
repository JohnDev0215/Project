package a.n;

import a.n.g;
import a.n.u;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public class s implements k {
    public static final s i = new s();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Handler f1107e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1103a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1104b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1105c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1106d = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l f1108f = new l(this);
    public Runnable g = new a();
    public u.a h = new b();

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            s sVar = s.this;
            if (sVar.f1104b == 0) {
                sVar.f1105c = true;
                sVar.f1108f.d(g.a.ON_PAUSE);
            }
            s sVar2 = s.this;
            if (sVar2.f1103a == 0 && sVar2.f1105c) {
                sVar2.f1108f.d(g.a.ON_STOP);
                sVar2.f1106d = true;
            }
        }
    }

    public class b implements u.a {
        public b() {
        }
    }

    @Override // a.n.k
    public g a() {
        return this.f1108f;
    }

    public void b() {
        int i2 = this.f1104b + 1;
        this.f1104b = i2;
        if (i2 == 1) {
            if (!this.f1105c) {
                this.f1107e.removeCallbacks(this.g);
            } else {
                this.f1108f.d(g.a.ON_RESUME);
                this.f1105c = false;
            }
        }
    }

    public void e() {
        int i2 = this.f1103a + 1;
        this.f1103a = i2;
        if (i2 == 1 && this.f1106d) {
            this.f1108f.d(g.a.ON_START);
            this.f1106d = false;
        }
    }
}
