package androidx.fragment.app;

import android.view.View;
import android.view.animation.Animation;
import androidx.fragment.app.b;
import androidx.fragment.app.j;
import defpackage.b80;
import defpackage.i21;

/* JADX INFO: compiled from: DefaultSpecialEffectsController.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d implements Animation.AnimationListener {
    public final /* synthetic */ b a;
    public final /* synthetic */ View b;
    public final /* synthetic */ b.a c;

    public d(View view, b.a aVar, b bVar, j.b bVar2) {
        this.a = bVar;
        this.b = view;
        this.c = aVar;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        i21.e(animation, "animation");
        b bVar = this.a;
        bVar.a.post(new b80(bVar, 0, this.b, this.c));
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
        i21.e(animation, "animation");
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
        i21.e(animation, "animation");
    }
}
