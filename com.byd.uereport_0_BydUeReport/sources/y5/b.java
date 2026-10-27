package y5;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import j5.h;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BaseMotionStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class b implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f9323a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ExtendedFloatingActionButton f9324b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<Animator.AnimatorListener> f9325c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f9326d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public h f9327e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public h f9328f;

    public b(ExtendedFloatingActionButton extendedFloatingActionButton, a aVar) {
        this.f9324b = extendedFloatingActionButton;
        this.f9323a = extendedFloatingActionButton.getContext();
        this.f9326d = aVar;
    }

    @Override // y5.f
    public void a() {
        this.f9326d.b();
    }

    @Override // y5.f
    public void b() {
        this.f9326d.b();
    }

    @Override // y5.f
    public final void c(h hVar) {
        this.f9328f = hVar;
    }

    @Override // y5.f
    public h f() {
        return this.f9328f;
    }

    @Override // y5.f
    public AnimatorSet g() {
        return k(l());
    }

    @Override // y5.f
    public final List<Animator.AnimatorListener> h() {
        return this.f9325c;
    }

    public AnimatorSet k(h hVar) {
        ArrayList arrayList = new ArrayList();
        if (hVar.j("opacity")) {
            arrayList.add(hVar.f("opacity", this.f9324b, View.ALPHA));
        }
        if (hVar.j("scale")) {
            arrayList.add(hVar.f("scale", this.f9324b, View.SCALE_Y));
            arrayList.add(hVar.f("scale", this.f9324b, View.SCALE_X));
        }
        if (hVar.j("width")) {
            arrayList.add(hVar.f("width", this.f9324b, ExtendedFloatingActionButton.f3864v));
        }
        if (hVar.j("height")) {
            arrayList.add(hVar.f("height", this.f9324b, ExtendedFloatingActionButton.f3865w));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        j5.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final h l() {
        h hVar = this.f9328f;
        if (hVar != null) {
            return hVar;
        }
        if (this.f9327e == null) {
            this.f9327e = h.d(this.f9323a, d());
        }
        return (h) n0.h.e(this.f9327e);
    }

    @Override // y5.f
    public void onAnimationStart(Animator animator) {
        this.f9326d.c(animator);
    }
}
