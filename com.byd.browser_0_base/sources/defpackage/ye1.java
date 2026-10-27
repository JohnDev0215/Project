package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/* JADX INFO: compiled from: MenuButton.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ye1 extends AnimatorListenerAdapter {
    public final /* synthetic */ View e;
    public final /* synthetic */ View f;

    public ye1(ImageButton imageButton, ImageView imageView) {
        this.e = imageButton;
        this.f = imageView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        View view = this.f;
        view.setAlpha(1.0f);
        view.setTranslationY(0.0f);
        this.e.setAlpha(1.0f);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.e.setAlpha(1.0f);
    }
}
