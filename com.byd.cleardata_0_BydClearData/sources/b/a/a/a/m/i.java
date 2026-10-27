package b.a.a.a.m;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f1746a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f1747b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TimeInterpolator f1748c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1749d;
    private int e;

    public i(long j, long j2) {
        this.f1746a = 0L;
        this.f1747b = 300L;
        this.f1748c = null;
        this.f1749d = 0;
        this.e = 1;
        this.f1746a = j;
        this.f1747b = j2;
    }

    public i(long j, long j2, TimeInterpolator timeInterpolator) {
        this.f1746a = 0L;
        this.f1747b = 300L;
        this.f1748c = null;
        this.f1749d = 0;
        this.e = 1;
        this.f1746a = j;
        this.f1747b = j2;
        this.f1748c = timeInterpolator;
    }

    static i b(ValueAnimator valueAnimator) {
        i iVar = new i(valueAnimator.getStartDelay(), valueAnimator.getDuration(), f(valueAnimator));
        iVar.f1749d = valueAnimator.getRepeatCount();
        iVar.e = valueAnimator.getRepeatMode();
        return iVar;
    }

    private static TimeInterpolator f(ValueAnimator valueAnimator) {
        TimeInterpolator interpolator = valueAnimator.getInterpolator();
        return ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) ? a.f1733b : interpolator instanceof AccelerateInterpolator ? a.f1734c : interpolator instanceof DecelerateInterpolator ? a.f1735d : interpolator;
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
        return this.f1746a;
    }

    public long d() {
        return this.f1747b;
    }

    public TimeInterpolator e() {
        TimeInterpolator timeInterpolator = this.f1748c;
        return timeInterpolator != null ? timeInterpolator : a.f1733b;
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
        return this.f1749d;
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
}
