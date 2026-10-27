package y;

import android.content.Context;
import android.os.Build;
import android.widget.EdgeEffect;

/* JADX INFO: renamed from: y.H, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2687H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f23838a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f23839b;
    public long c = 0;
    public EdgeEffect d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public EdgeEffect f23840e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public EdgeEffect f23841f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public EdgeEffect f23842g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public EdgeEffect f23843h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public EdgeEffect f23844i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public EdgeEffect f23845j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public EdgeEffect f23846k;

    public C2687H(Context context, int i7) {
        this.f23838a = context;
        this.f23839b = i7;
    }

    public static boolean f(EdgeEffect edgeEffect) {
        if (edgeEffect == null) {
            return false;
        }
        return !edgeEffect.isFinished();
    }

    public static boolean g(EdgeEffect edgeEffect) {
        if (edgeEffect == null) {
            return false;
        }
        return !((Build.VERSION.SDK_INT >= 31 ? C2710o.f23975a.b(edgeEffect) : 0.0f) == 0.0f);
    }

    public final EdgeEffect a() {
        int i7 = Build.VERSION.SDK_INT;
        Context context = this.f23838a;
        EdgeEffect edgeEffectA = i7 >= 31 ? C2710o.f23975a.a(context, null) : new Q(context);
        edgeEffectA.setColor(this.f23839b);
        if (!a1.j.a(this.c, 0L)) {
            long j3 = this.c;
            edgeEffectA.setSize((int) (j3 >> 32), (int) (j3 & 4294967295L));
        }
        return edgeEffectA;
    }

    public final EdgeEffect b() {
        EdgeEffect edgeEffect = this.f23840e;
        if (edgeEffect != null) {
            return edgeEffect;
        }
        EdgeEffect edgeEffectA = a();
        this.f23840e = edgeEffectA;
        return edgeEffectA;
    }

    public final EdgeEffect c() {
        EdgeEffect edgeEffect = this.f23841f;
        if (edgeEffect != null) {
            return edgeEffect;
        }
        EdgeEffect edgeEffectA = a();
        this.f23841f = edgeEffectA;
        return edgeEffectA;
    }

    public final EdgeEffect d() {
        EdgeEffect edgeEffect = this.f23842g;
        if (edgeEffect != null) {
            return edgeEffect;
        }
        EdgeEffect edgeEffectA = a();
        this.f23842g = edgeEffectA;
        return edgeEffectA;
    }

    public final EdgeEffect e() {
        EdgeEffect edgeEffect = this.d;
        if (edgeEffect != null) {
            return edgeEffect;
        }
        EdgeEffect edgeEffectA = a();
        this.d = edgeEffectA;
        return edgeEffectA;
    }
}
