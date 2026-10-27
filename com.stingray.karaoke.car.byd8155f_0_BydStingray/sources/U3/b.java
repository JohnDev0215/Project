package U3;

import android.graphics.PointF;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f8997a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f8998b;
    public float c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8999e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f9000f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f9001g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f9002h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f9003i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f9004j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f9005k;
    public PointF l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public PointF f9006m;

    public final int hashCode() {
        int iC = ((AbstractC2641j.c(this.d) + (((int) (M5.b.c(this.f8998b, this.f8997a.hashCode() * 31, 31) + this.c)) * 31)) * 31) + this.f8999e;
        long jFloatToRawIntBits = Float.floatToRawIntBits(this.f9000f);
        return (((iC * 31) + ((int) (jFloatToRawIntBits ^ (jFloatToRawIntBits >>> 32)))) * 31) + this.f9002h;
    }
}
