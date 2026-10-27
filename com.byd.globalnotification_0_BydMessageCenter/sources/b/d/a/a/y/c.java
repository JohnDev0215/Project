package b.d.a.a.y;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import b.d.a.a.y.e;

/* JADX INFO: loaded from: classes.dex */
public class c extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2138a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f2139b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ e.f f2140c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ e f2141d;

    public c(e eVar, boolean z, e.f fVar) {
        this.f2141d = eVar;
        this.f2139b = z;
        this.f2140c = fVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.f2138a = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        e eVar = this.f2141d;
        eVar.p = 0;
        eVar.k = null;
        if (this.f2138a) {
            return;
        }
        eVar.t.b(this.f2139b ? 8 : 4, this.f2139b);
        e.f fVar = this.f2140c;
        if (fVar != null) {
            b bVar = (b) fVar;
            bVar.f2136a.a(bVar.f2137b);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.f2141d.t.b(0, this.f2139b);
        e eVar = this.f2141d;
        eVar.p = 1;
        eVar.k = animator;
        this.f2138a = false;
    }
}
