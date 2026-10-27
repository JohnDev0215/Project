package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import org.chromium.chrome.features.tasks.TasksView;

/* JADX INFO: compiled from: BackgroundTabAnimation.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dc extends AnimatorListenerAdapter {
    public final /* synthetic */ ViewGroup e;
    public final /* synthetic */ ImageView f;

    public dc(TasksView tasksView, AppCompatImageView appCompatImageView) {
        this.e = tasksView;
        this.f = appCompatImageView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.e.removeView(this.f);
    }
}
