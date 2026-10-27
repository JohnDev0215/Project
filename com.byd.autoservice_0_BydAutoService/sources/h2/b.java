package h2;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import s1.h;

/* JADX INFO: compiled from: BaseMotionStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3067a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ExtendedFloatingActionButton f3068b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<Animator.AnimatorListener> f3069c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f3070d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public h f3071e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public h f3072f;

    public b(ExtendedFloatingActionButton extendedFloatingActionButton, a aVar) {
        this.f3068b = extendedFloatingActionButton;
        this.f3067a = extendedFloatingActionButton.getContext();
        this.f3070d = aVar;
    }

    @Override // h2.f
    public h a() {
        return this.f3072f;
    }

    @Override // h2.f
    public AnimatorSet b() {
        return k(l());
    }

    @Override // h2.f
    public void c() {
        this.f3070d.b();
    }

    @Override // h2.f
    public void d() {
        this.f3070d.b();
    }

    @Override // h2.f
    public final List<Animator.AnimatorListener> e() {
        return this.f3069c;
    }

    @Override // h2.f
    public final void h(h hVar) {
        this.f3072f = hVar;
    }

    public AnimatorSet k(h hVar) {
        ArrayList arrayList = new ArrayList();
        if (hVar.j("opacity")) {
            arrayList.add(hVar.f("opacity", this.f3068b, View.ALPHA));
        }
        if (hVar.j("scale")) {
            arrayList.add(hVar.f("scale", this.f3068b, View.SCALE_Y));
            arrayList.add(hVar.f("scale", this.f3068b, View.SCALE_X));
        }
        if (hVar.j("width")) {
            arrayList.add(hVar.f("width", this.f3068b, ExtendedFloatingActionButton.f2279t));
        }
        if (hVar.j("height")) {
            arrayList.add(hVar.f("height", this.f3068b, ExtendedFloatingActionButton.f2280u));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        s1.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final h l() {
        h hVar = this.f3072f;
        if (hVar != null) {
            return hVar;
        }
        if (this.f3071e == null) {
            this.f3071e = h.d(this.f3067a, i());
        }
        return (h) k0.h.c(this.f3071e);
    }

    @Override // h2.f
    public void onAnimationStart(Animator animator) {
        this.f3070d.c(animator);
    }
}
