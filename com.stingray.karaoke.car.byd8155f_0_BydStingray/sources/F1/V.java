package F1;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class V {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final V f2320b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final U f2321a;

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            f2320b = T.f2317m;
        } else {
            f2320b = U.f2318b;
        }
    }

    public V(WindowInsets windowInsets) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.f2321a = new T(this, windowInsets);
        } else {
            this.f2321a = new S(this, windowInsets);
        }
    }

    public static V c(WindowInsets windowInsets, View view) {
        windowInsets.getClass();
        V v10 = new V(windowInsets);
        if (view != null && view.isAttachedToWindow()) {
            int[] iArr = H.f2303a;
            V vA = B.a(view);
            U u4 = v10.f2321a;
            u4.m(vA);
            u4.d(view.getRootView());
        }
        return v10;
    }

    public final int a() {
        return this.f2321a.h().f24114b;
    }

    public final WindowInsets b() {
        U u4 = this.f2321a;
        if (u4 instanceof O) {
            return ((O) u4).c;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof V)) {
            return false;
        }
        return Objects.equals(this.f2321a, ((V) obj).f2321a);
    }

    public final int hashCode() {
        U u4 = this.f2321a;
        if (u4 == null) {
            return 0;
        }
        return u4.hashCode();
    }

    public V() {
        this.f2321a = new U(this);
    }
}
