package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BaseMotionStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ui implements yi {
    public final Context a;
    public final ExtendedFloatingActionButton b;
    public final ArrayList<Animator.AnimatorListener> c = new ArrayList<>();
    public final ti d;
    public ih e;
    public ih f;

    public ui(ExtendedFloatingActionButton extendedFloatingActionButton, ti tiVar) {
        this.b = extendedFloatingActionButton;
        this.a = extendedFloatingActionButton.getContext();
        this.d = tiVar;
    }

    @Override // defpackage.yi
    public ih a() {
        return this.f;
    }

    @Override // defpackage.yi
    public AnimatorSet b() {
        return k(l());
    }

    @Override // defpackage.yi
    public void c() {
        this.d.b();
    }

    @Override // defpackage.yi
    public void d() {
        this.d.b();
    }

    @Override // defpackage.yi
    public final List<Animator.AnimatorListener> e() {
        return this.c;
    }

    @Override // defpackage.yi
    public final void h(ih ihVar) {
        this.f = ihVar;
    }

    public AnimatorSet k(ih ihVar) {
        ArrayList arrayList = new ArrayList();
        if (ihVar.j("opacity")) {
            arrayList.add(ihVar.f("opacity", this.b, View.ALPHA));
        }
        if (ihVar.j("scale")) {
            arrayList.add(ihVar.f("scale", this.b, View.SCALE_Y));
            arrayList.add(ihVar.f("scale", this.b, View.SCALE_X));
        }
        if (ihVar.j("width")) {
            arrayList.add(ihVar.f("width", this.b, ExtendedFloatingActionButton.t));
        }
        if (ihVar.j("height")) {
            arrayList.add(ihVar.f("height", this.b, ExtendedFloatingActionButton.u));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        ch.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final ih l() {
        ih ihVar = this.f;
        if (ihVar != null) {
            return ihVar;
        }
        if (this.e == null) {
            this.e = ih.d(this.a, i());
        }
        return (ih) z8.c(this.e);
    }

    @Override // defpackage.yi
    public void onAnimationStart(Animator animator) {
        this.d.c(animator);
    }
}
