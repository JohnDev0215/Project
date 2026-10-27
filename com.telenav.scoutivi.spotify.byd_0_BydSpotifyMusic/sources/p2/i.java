package p2;

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
    private long f8835a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f8836b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TimeInterpolator f8837c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f8838d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f8839e;

    public i(long j7, long j8) {
        this.f8837c = null;
        this.f8838d = 0;
        this.f8839e = 1;
        this.f8835a = j7;
        this.f8836b = j8;
    }

    static i b(ValueAnimator valueAnimator) {
        i iVar = new i(valueAnimator.getStartDelay(), valueAnimator.getDuration(), f(valueAnimator));
        iVar.f8838d = valueAnimator.getRepeatCount();
        iVar.f8839e = valueAnimator.getRepeatMode();
        return iVar;
    }

    private static TimeInterpolator f(ValueAnimator valueAnimator) {
        TimeInterpolator interpolator = valueAnimator.getInterpolator();
        return ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) ? a.f8821b : interpolator instanceof AccelerateInterpolator ? a.f8822c : interpolator instanceof DecelerateInterpolator ? a.f8823d : interpolator;
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
        return this.f8835a;
    }

    public long d() {
        return this.f8836b;
    }

    public TimeInterpolator e() {
        TimeInterpolator timeInterpolator = this.f8837c;
        return timeInterpolator != null ? timeInterpolator : a.f8821b;
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
        return this.f8838d;
    }

    public int h() {
        return this.f8839e;
    }

    public int hashCode() {
        return (((((((((int) (c() ^ (c() >>> 32))) * 31) + ((int) (d() ^ (d() >>> 32)))) * 31) + e().getClass().hashCode()) * 31) + g()) * 31) + h();
    }

    public String toString() {
        return '\n' + getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + c() + " duration: " + d() + " interpolator: " + e().getClass() + " repeatCount: " + g() + " repeatMode: " + h() + "}\n";
    }

    public i(long j7, long j8, TimeInterpolator timeInterpolator) {
        this.f8838d = 0;
        this.f8839e = 1;
        this.f8835a = j7;
        this.f8836b = j8;
        this.f8837c = timeInterpolator;
    }
}
