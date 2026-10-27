package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/* JADX INFO: compiled from: MenuButton.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ze1 extends AnimatorListenerAdapter {
    public final /* synthetic */ View e;
    public final /* synthetic */ View f;

    public ze1(ImageView imageView, ImageButton imageButton) {
        this.e = imageView;
        this.f = imageButton;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f.setAlpha(1.0f);
        this.e.setVisibility(8);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.e.setVisibility(8);
    }
}
