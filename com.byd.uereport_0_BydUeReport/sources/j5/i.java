package j5;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/* JADX INFO: compiled from: MotionTiming.java */
/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f5593a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f5594b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TimeInterpolator f5595c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5596d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5597e;

    public i(long j8, long j9) {
        this.f5593a = 0L;
        this.f5594b = 300L;
        this.f5595c = null;
        this.f5596d = 0;
        this.f5597e = 1;
        this.f5593a = j8;
        this.f5594b = j9;
    }

    public static i b(ValueAnimator valueAnimator) {
        i iVar = new i(valueAnimator.getStartDelay(), valueAnimator.getDuration(), f(valueAnimator));
        iVar.f5596d = valueAnimator.getRepeatCount();
        iVar.f5597e = valueAnimator.getRepeatMode();
        return iVar;
    }

    public static TimeInterpolator f(ValueAnimator valueAnimator) {
        TimeInterpolator interpolator = valueAnimator.getInterpolator();
        return ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) ? a.f5579b : interpolator instanceof AccelerateInterpolator ? a.f5580c : interpolator instanceof DecelerateInterpolator ? a.f5581d : interpolator;
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
        return this.f5593a;
    }

    public long d() {
        return this.f5594b;
    }

    public TimeInterpolator e() {
        TimeInterpolator timeInterpolator = this.f5595c;
        return timeInterpolator != null ? timeInterpolator : a.f5579b;
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
        return this.f5596d;
    }

    public int h() {
        return this.f5597e;
    }

    public int hashCode() {
        return (((((((((int) (c() ^ (c() >>> 32))) * 31) + ((int) (d() ^ (d() >>> 32)))) * 31) + e().getClass().hashCode()) * 31) + g()) * 31) + h();
    }

    public String toString() {
        return '\n' + i.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + c() + " duration: " + d() + " interpolator: " + e().getClass() + " repeatCount: " + g() + " repeatMode: " + h() + "}\n";
    }

    public i(long j8, long j9, TimeInterpolator timeInterpolator) {
        this.f5593a = 0L;
        this.f5594b = 300L;
        this.f5595c = null;
        this.f5596d = 0;
        this.f5597e = 1;
        this.f5593a = j8;
        this.f5594b = j9;
        this.f5595c = timeInterpolator;
    }
}
