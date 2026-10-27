package O6;

import B2.O;
import I5.k;
import com.stingray.karaoke.car.MainApplication;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Q6.b {
    public volatile k l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f5166m = new Object();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final O f5167n;

    public f(O o) {
        this.f5167n = o;
    }

    @Override // Q6.b
    public final Object c() {
        if (this.l == null) {
            synchronized (this.f5166m) {
                try {
                    if (this.l == null) {
                        this.l = new k(new P6.a((MainApplication) this.f5167n.f527m, 0, false));
                    }
                } finally {
                }
            }
        }
        return this.l;
    }
}
