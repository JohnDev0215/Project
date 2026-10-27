package b.d.a.a.l;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f1981a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1982b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TimeInterpolator f1983c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1984d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1985e;

    public h(long j, long j2) {
        this.f1981a = 0L;
        this.f1982b = 300L;
        this.f1983c = null;
        this.f1984d = 0;
        this.f1985e = 1;
        this.f1981a = j;
        this.f1982b = j2;
    }

    public h(long j, long j2, TimeInterpolator timeInterpolator) {
        this.f1981a = 0L;
        this.f1982b = 300L;
        this.f1983c = null;
        this.f1984d = 0;
        this.f1985e = 1;
        this.f1981a = j;
        this.f1982b = j2;
        this.f1983c = timeInterpolator;
    }

    public void a(Animator animator) {
        animator.setStartDelay(this.f1981a);
        animator.setDuration(this.f1982b);
        animator.setInterpolator(b());
        if (animator instanceof ValueAnimator) {
            ValueAnimator valueAnimator = (ValueAnimator) animator;
            valueAnimator.setRepeatCount(this.f1984d);
            valueAnimator.setRepeatMode(this.f1985e);
        }
    }

    public TimeInterpolator b() {
        TimeInterpolator timeInterpolator = this.f1983c;
        return timeInterpolator != null ? timeInterpolator : a.f1967b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        if (this.f1981a == hVar.f1981a && this.f1982b == hVar.f1982b && this.f1984d == hVar.f1984d && this.f1985e == hVar.f1985e) {
            return b().getClass().equals(hVar.b().getClass());
        }
        return false;
    }

    public int hashCode() {
        long j = this.f1981a;
        long j2 = this.f1982b;
        return ((((b().getClass().hashCode() + (((((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31)) * 31) + this.f1984d) * 31) + this.f1985e;
    }

    public String toString() {
        return '\n' + h.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + this.f1981a + " duration: " + this.f1982b + " interpolator: " + b().getClass() + " repeatCount: " + this.f1984d + " repeatMode: " + this.f1985e + "}\n";
    }
}
