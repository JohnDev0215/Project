package b.d.a.a.y;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import b.d.a.a.y.e;

/* JADX INFO: loaded from: classes.dex */
public class d extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ boolean f2142a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e.f f2143b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ e f2144c;

    public d(e eVar, boolean z, e.f fVar) {
        this.f2144c = eVar;
        this.f2142a = z;
        this.f2143b = fVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        e eVar = this.f2144c;
        eVar.p = 0;
        eVar.k = null;
        e.f fVar = this.f2143b;
        if (fVar != null) {
            b bVar = (b) fVar;
            bVar.f2136a.b(bVar.f2137b);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.f2144c.t.b(0, this.f2142a);
        e eVar = this.f2144c;
        eVar.p = 2;
        eVar.k = animator;
    }
}
