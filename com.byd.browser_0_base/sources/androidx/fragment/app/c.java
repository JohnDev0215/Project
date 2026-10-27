package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.b;
import androidx.fragment.app.j;
import defpackage.gq2;
import defpackage.i21;

/* JADX INFO: compiled from: DefaultSpecialEffectsController.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c extends AnimatorListenerAdapter {
    public final /* synthetic */ b e;
    public final /* synthetic */ View f;
    public final /* synthetic */ boolean g;
    public final /* synthetic */ j.b h;
    public final /* synthetic */ b.a i;

    public c(b bVar, View view, boolean z, j.b bVar2, b.a aVar) {
        this.e = bVar;
        this.f = view;
        this.g = z;
        this.h = bVar2;
        this.i = aVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        i21.e(animator, "anim");
        ViewGroup viewGroup = this.e.a;
        View view = this.f;
        viewGroup.endViewTransition(view);
        if (this.g) {
            int i = this.h.a;
            i21.d(view, "viewToAnimate");
            gq2.a(i, view);
        }
        this.i.a();
    }
}
