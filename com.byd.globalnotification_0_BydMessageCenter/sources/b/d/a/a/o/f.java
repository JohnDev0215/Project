package b.d.a.a.o;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public class f extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ BottomAppBar f2033a;

    public f(BottomAppBar bottomAppBar) {
        this.f2033a = bottomAppBar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.f2033a.X.onAnimationStart(animator);
        FloatingActionButton floatingActionButtonZ = this.f2033a.z();
        if (floatingActionButtonZ != null) {
            floatingActionButtonZ.setTranslationX(this.f2033a.getFabTranslationX());
        }
    }
}
