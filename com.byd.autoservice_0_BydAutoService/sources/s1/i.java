package s1;

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
    public long f4916a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f4917b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TimeInterpolator f4918c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4919d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4920e;

    public i(long j3, long j4) {
        this.f4916a = 0L;
        this.f4917b = 300L;
        this.f4918c = null;
        this.f4919d = 0;
        this.f4920e = 1;
        this.f4916a = j3;
        this.f4917b = j4;
    }

    public static i b(ValueAnimator valueAnimator) {
        i iVar = new i(valueAnimator.getStartDelay(), valueAnimator.getDuration(), f(valueAnimator));
        iVar.f4919d = valueAnimator.getRepeatCount();
        iVar.f4920e = valueAnimator.getRepeatMode();
        return iVar;
    }

    public static TimeInterpolator f(ValueAnimator valueAnimator) {
        TimeInterpolator interpolator = valueAnimator.getInterpolator();
        return ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) ? a.f4902b : interpolator instanceof AccelerateInterpolator ? a.f4903c : interpolator instanceof DecelerateInterpolator ? a.f4904d : interpolator;
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
        return this.f4916a;
    }

    public long d() {
        return this.f4917b;
    }

    public TimeInterpolator e() {
        TimeInterpolator timeInterpolator = this.f4918c;
        return timeInterpolator != null ? timeInterpolator : a.f4902b;
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
        return this.f4919d;
    }

    public int h() {
        return this.f4920e;
    }

    public int hashCode() {
        return (((((((((int) (c() ^ (c() >>> 32))) * 31) + ((int) (d() ^ (d() >>> 32)))) * 31) + e().getClass().hashCode()) * 31) + g()) * 31) + h();
    }

    public String toString() {
        return '\n' + i.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + c() + " duration: " + d() + " interpolator: " + e().getClass() + " repeatCount: " + g() + " repeatMode: " + h() + "}\n";
    }

    public i(long j3, long j4, TimeInterpolator timeInterpolator) {
        this.f4916a = 0L;
        this.f4917b = 300L;
        this.f4918c = null;
        this.f4919d = 0;
        this.f4920e = 1;
        this.f4916a = j3;
        this.f4917b = j4;
        this.f4918c = timeInterpolator;
    }
}
