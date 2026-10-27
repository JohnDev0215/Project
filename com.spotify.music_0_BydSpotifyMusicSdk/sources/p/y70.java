package p;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.SpecialEffectsController$Operation$State;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class y70 extends AnimatorListenerAdapter {
    public final /* synthetic */ androidx.fragment.app.a a;
    public final /* synthetic */ View b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ bf2 d;
    public final /* synthetic */ w70 e;

    public y70(androidx.fragment.app.a aVar, View view, boolean z, bf2 bf2Var, w70 w70Var) {
        this.a = aVar;
        this.b = view;
        this.c = z;
        this.d = bf2Var;
        this.e = w70Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        v03.j(animator, "anim");
        ViewGroup viewGroup = this.a.a;
        View view = this.b;
        viewGroup.endViewTransition(view);
        boolean z = this.c;
        bf2 bf2Var = this.d;
        if (z) {
            SpecialEffectsController$Operation$State specialEffectsController$Operation$State = bf2Var.a;
            v03.i(view, "viewToAnimate");
            specialEffectsController$Operation$State.a(view);
        }
        this.e.b();
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(bf2Var);
        }
    }
}
