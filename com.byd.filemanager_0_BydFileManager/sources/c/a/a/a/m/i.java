package c.a.a.a.m;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/* JADX INFO: compiled from: MotionTiming.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f2320a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f2321b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TimeInterpolator f2322c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2323d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2324e;

    public i(long j, long j2) {
        this.f2320a = 0L;
        this.f2321b = 300L;
        this.f2322c = null;
        this.f2323d = 0;
        this.f2324e = 1;
        this.f2320a = j;
        this.f2321b = j2;
    }

    static i b(ValueAnimator valueAnimator) {
        i iVar = new i(valueAnimator.getStartDelay(), valueAnimator.getDuration(), f(valueAnimator));
        iVar.f2323d = valueAnimator.getRepeatCount();
        iVar.f2324e = valueAnimator.getRepeatMode();
        return iVar;
    }

    private static TimeInterpolator f(ValueAnimator valueAnimator) {
        TimeInterpolator interpolator = valueAnimator.getInterpolator();
        return ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) ? a.f2306b : interpolator instanceof AccelerateInterpolator ? a.f2307c : interpolator instanceof DecelerateInterpolator ? a.f2308d : interpolator;
    }

    public void a(Animator animator) {
        animator.setStartDelay(c());
        animator.setDuration(d());
        animator.setInterpolator(e());
        if (animator instanceof ValueAnimator) {
            ValueAnimator valueAnimator = (ValueAnimator) animator;
            valueAnimator.setRepeatCount(g());
            valueAnimator.setRepeatMode(h());
        }
    }

    public long c() {
        return this.f2320a;
    }

    public long d() {
        return this.f2321b;
    }

    public TimeInterpolator e() {
        TimeInterpolator timeInterpolator = this.f2322c;
        return timeInterpolator != null ? timeInterpolator : a.f2306b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        if (c() == iVar.c() && d() == iVar.d() && g() == iVar.g() && h() == iVar.h()) {
            return e().getClass().equals(iVar.e().getClass());
        }
        return false;
    }

    public int g() {
        return this.f2323d;
    }

    public int h() {
        return this.f2324e;
    }

    public int hashCode() {
        return (((((((((int) (c() ^ (c() >>> 32))) * 31) + ((int) (d() ^ (d() >>> 32)))) * 31) + e().getClass().hashCode()) * 31) + g()) * 31) + h();
    }

    public String toString() {
        return '\n' + i.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + c() + " duration: " + d() + " interpolator: " + e().getClass() + " repeatCount: " + g() + " repeatMode: " + h() + "}\n";
    }

    public i(long j, long j2, TimeInterpolator timeInterpolator) {
        this.f2320a = 0L;
        this.f2321b = 300L;
        this.f2322c = null;
        this.f2323d = 0;
        this.f2324e = 1;
        this.f2320a = j;
        this.f2321b = j2;
        this.f2322c = timeInterpolator;
    }
}
