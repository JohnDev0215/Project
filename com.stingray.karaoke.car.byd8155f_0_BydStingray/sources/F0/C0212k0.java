package F0;

import android.os.Build;
import android.view.ViewConfiguration;

/* JADX INFO: renamed from: F0.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0212k0 implements W0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewConfiguration f2217a;

    public C0212k0(ViewConfiguration viewConfiguration) {
        this.f2217a = viewConfiguration;
    }

    @Override // F0.W0
    public final float a() {
        return this.f2217a.getScaledMaximumFlingVelocity();
    }

    @Override // F0.W0
    public final long b() {
        return ViewConfiguration.getDoubleTapTimeout();
    }

    @Override // F0.W0
    public final long c() {
        return ViewConfiguration.getLongPressTimeout();
    }

    @Override // F0.W0
    public final float d() {
        return this.f2217a.getScaledTouchSlop();
    }

    @Override // F0.W0
    public final float e() {
        if (Build.VERSION.SDK_INT >= 34) {
            return C0216m0.f2223a.b(this.f2217a);
        }
        return 2.0f;
    }

    @Override // F0.W0
    public final float f() {
        if (Build.VERSION.SDK_INT >= 34) {
            return C0216m0.f2223a.a(this.f2217a);
        }
        return 16.0f;
    }
}
