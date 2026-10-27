package a.l.d;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public class s extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f1038a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f1039b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Fragment f1040c;

    public s(r rVar, ViewGroup viewGroup, View view, Fragment fragment) {
        this.f1038a = viewGroup;
        this.f1039b = view;
        this.f1040c = fragment;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f1038a.endViewTransition(this.f1039b);
        animator.removeListener(this);
        Fragment fragment = this.f1040c;
        View view = fragment.F;
        if (view == null || !fragment.y) {
            return;
        }
        view.setVisibility(8);
    }
}
