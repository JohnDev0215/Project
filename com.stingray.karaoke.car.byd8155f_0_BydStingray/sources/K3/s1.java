package k3;

import android.media.VolumeProvider;
import android.os.Build;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class s1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18112a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18113b;
    public final String c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public VolumeProvider f18114e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Handler f18115f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ u1 f18116g;

    public s1(u1 u1Var, int i7, int i10, int i11, String str, Handler handler) {
        this.f18116g = u1Var;
        this.f18115f = handler;
        this.f18112a = i7;
        this.f18113b = i10;
        this.d = i11;
        this.c = str;
    }

    public final VolumeProvider a() {
        if (this.f18114e == null) {
            if (Build.VERSION.SDK_INT >= 30) {
                this.f18114e = new l3.Z(this, this.f18112a, this.f18113b, this.d, this.c);
            } else {
                this.f18114e = new l3.a0(this, this.f18112a, this.f18113b, this.d);
            }
        }
        return this.f18114e;
    }
}
