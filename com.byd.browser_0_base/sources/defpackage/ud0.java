package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import defpackage.l5;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: DrawableWithAnimatedVisibilityChange.java */
/* JADX INFO: loaded from: classes.dex */
public final class ud0 extends AnimatorListenerAdapter {
    public final /* synthetic */ vd0 e;

    public ud0(vd0 vd0Var) {
        this.e = vd0Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        vd0 vd0Var = this.e;
        super/*android.graphics.drawable.Drawable*/.setVisible(false, false);
        ArrayList arrayList = vd0Var.j;
        if (arrayList == null || vd0Var.k) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((l5.a) it.next()).b(vd0Var);
        }
    }
}
