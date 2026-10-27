package defpackage;

import android.os.Handler;
import com.android.webview.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Yy implements Iterable {
    public static final boolean y = false;
    public static final Wy z = new Wy(null);
    public final Handler s;
    public final int t;
    public final List u;
    public final Runnable v;
    public boolean w;
    public boolean x;

    public Yy() {
        this.s = new Handler();
        this.u = new ArrayList();
        this.v = new Runnable(this) { // from class: Sy
            public final Yy s;

            {
                this.s = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.e();
            }
        };
        this.t = -1;
    }

    public final void e() {
        this.x = false;
        int size = this.u.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            Vy vy = (Vy) this.u.get(size);
            if (!vy.a()) {
                C1406qu c1406qu = vy.a;
                if (c1406qu.e()) {
                    ((ServiceConnectionC1186mu) c1406qu.o).a();
                }
            }
        }
    }

    public final int f(C1406qu c1406qu) {
        for (int i = 0; i < this.u.size(); i++) {
            if (((Vy) this.u.get(i)).a == c1406qu) {
                return i;
            }
        }
        return -1;
    }

    public final void g(int i) {
        Vy vy = (Vy) this.u.remove(i);
        int i2 = 0;
        while (i2 < this.u.size() && z.compare((Vy) this.u.get(i2), vy) < 0) {
            i2++;
        }
        this.u.add(i2, vy);
        if (this.w) {
            if (!vy.a()) {
                C1406qu c1406qu = vy.a;
                if (c1406qu.r != 0) {
                    c1406qu.m(0, 0);
                    return;
                }
                return;
            }
            boolean z2 = i2 == 0;
            boolean z3 = i2 == this.u.size() - 1;
            int i3 = z2 ? 0 : ((Vy) this.u.get(i2 - 1)).a.s;
            int i4 = z3 ? Integer.MAX_VALUE : ((Vy) this.u.get(i2 + 1)).a.s;
            int i5 = vy.a.s;
            if (i5 <= i3 || i5 >= i4) {
                int i6 = i4 - i3;
                if (i6 > R.anim.abc_fade_in) {
                    vy.a.m(1, i4 - 32768);
                } else if (i6 > 2) {
                    vy.a.m(1, (i6 / 2) + i3);
                } else {
                    h();
                }
                if (this.x) {
                    return;
                }
                this.s.postDelayed(this.v, 1000L);
                this.x = true;
            }
        }
    }

    public final void h() {
        int i = 2147450879;
        for (int size = this.u.size() - 1; size >= 0; size--) {
            Vy vy = (Vy) this.u.get(size);
            if (!vy.a()) {
                return;
            }
            vy.a.m(1, i);
            i -= 32768;
        }
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new Xy(this);
    }

    public Yy(int i) {
        this.s = new Handler();
        this.u = new ArrayList();
        this.v = new Runnable(this) { // from class: Ty
            public final Yy s;

            {
                this.s = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.e();
            }
        };
        this.t = i;
    }
}
