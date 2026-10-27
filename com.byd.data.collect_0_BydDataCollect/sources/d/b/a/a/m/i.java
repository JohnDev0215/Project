package d.b.a.a.m;

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
    private long f4689a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f4690b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TimeInterpolator f4691c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4692d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4693e;

    public i(long j, long j2) {
        this.f4689a = 0L;
        this.f4690b = 300L;
        this.f4691c = null;
        this.f4692d = 0;
        this.f4693e = 1;
        this.f4689a = j;
        this.f4690b = j2;
    }

    static i b(ValueAnimator valueAnimator) {
        i iVar = new i(valueAnimator.getStartDelay(), valueAnimator.getDuration(), f(valueAnimator));
        iVar.f4692d = valueAnimator.getRepeatCount();
        iVar.f4693e = valueAnimator.getRepeatMode();
        return iVar;
    }

    private static TimeInterpolator f(ValueAnimator valueAnimator) {
        TimeInterpolator interpolator = valueAnimator.getInterpolator();
        return ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) ? a.f4675b : interpolator instanceof AccelerateInterpolator ? a.f4676c : interpolator instanceof DecelerateInterpolator ? a.f4677d : interpolator;
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
        return this.f4689a;
    }

    public long d() {
        return this.f4690b;
    }

    public TimeInterpolator e() {
        TimeInterpolator timeInterpolator = this.f4691c;
        return timeInterpolator != null ? timeInterpolator : a.f4675b;
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
        return this.f4692d;
    }

    public int h() {
        return this.f4693e;
    }

    public int hashCode() {
        return (((((((((int) (c() ^ (c() >>> 32))) * 31) + ((int) (d() ^ (d() >>> 32)))) * 31) + e().getClass().hashCode()) * 31) + g()) * 31) + h();
    }

    public String toString() {
        return '\n' + i.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + c() + " duration: " + d() + " interpolator: " + e().getClass() + " repeatCount: " + g() + " repeatMode: " + h() + "}\n";
    }

    public i(long j, long j2, TimeInterpolator timeInterpolator) {
        this.f4689a = 0L;
        this.f4690b = 300L;
        this.f4691c = null;
        this.f4692d = 0;
        this.f4693e = 1;
        this.f4689a = j;
        this.f4690b = j2;
        this.f4691c = timeInterpolator;
    }
}
