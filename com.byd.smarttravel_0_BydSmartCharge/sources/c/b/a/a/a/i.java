package c.b.a.a.a;

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
    public long f1662a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1663b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TimeInterpolator f1664c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1665d;
    public int e;

    public i(long j, long j2) {
        this.f1662a = 0L;
        this.f1663b = 300L;
        this.f1664c = null;
        this.f1665d = 0;
        this.e = 1;
        this.f1662a = j;
        this.f1663b = j2;
    }

    public static i b(ValueAnimator valueAnimator) {
        i iVar = new i(valueAnimator.getStartDelay(), valueAnimator.getDuration(), f(valueAnimator));
        iVar.f1665d = valueAnimator.getRepeatCount();
        iVar.e = valueAnimator.getRepeatMode();
        return iVar;
    }

    public static TimeInterpolator f(ValueAnimator valueAnimator) {
        TimeInterpolator interpolator = valueAnimator.getInterpolator();
        return ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) ? a.f1649b : interpolator instanceof AccelerateInterpolator ? a.f1650c : interpolator instanceof DecelerateInterpolator ? a.f1651d : interpolator;
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
        return this.f1662a;
    }

    public long d() {
        return this.f1663b;
    }

    public TimeInterpolator e() {
        TimeInterpolator timeInterpolator = this.f1664c;
        return timeInterpolator != null ? timeInterpolator : a.f1649b;
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
        return this.f1665d;
    }

    public int h() {
        return this.e;
    }

    public int hashCode() {
        return (((((((((int) (c() ^ (c() >>> 32))) * 31) + ((int) (d() ^ (d() >>> 32)))) * 31) + e().getClass().hashCode()) * 31) + g()) * 31) + h();
    }

    public String toString() {
        return '\n' + i.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + c() + " duration: " + d() + " interpolator: " + e().getClass() + " repeatCount: " + g() + " repeatMode: " + h() + "}\n";
    }

    public i(long j, long j2, TimeInterpolator timeInterpolator) {
        this.f1662a = 0L;
        this.f1663b = 300L;
        this.f1664c = null;
        this.f1665d = 0;
        this.e = 1;
        this.f1662a = j;
        this.f1663b = j2;
        this.f1664c = timeInterpolator;
    }
}
