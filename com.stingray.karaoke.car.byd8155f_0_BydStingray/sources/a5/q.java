package a5;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;
import r.C2218F;

/* JADX INFO: loaded from: classes.dex */
public final class q extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f11172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TextView f11173b;
    public final /* synthetic */ int c;
    public final /* synthetic */ TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ r f11174e;

    public q(r rVar, int i7, TextView textView, int i10, TextView textView2) {
        this.f11174e = rVar;
        this.f11172a = i7;
        this.f11173b = textView;
        this.c = i10;
        this.d = textView2;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        C2218F c2218f;
        int i7 = this.f11172a;
        r rVar = this.f11174e;
        rVar.f11180h = i7;
        rVar.f11178f = null;
        TextView textView = this.f11173b;
        if (textView != null) {
            textView.setVisibility(4);
            if (this.c == 1 && (c2218f = rVar.l) != null) {
                c2218f.setText((CharSequence) null);
            }
        }
        TextView textView2 = this.d;
        if (textView2 != null) {
            textView2.setTranslationY(0.0f);
            textView2.setAlpha(1.0f);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        TextView textView = this.d;
        if (textView != null) {
            textView.setVisibility(0);
        }
    }
}
