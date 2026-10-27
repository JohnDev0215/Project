package a.l.d;

import a.l.d.g0;
import a.l.d.r;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public final class h extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f979a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f980b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Fragment f981c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ g0.a f982d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a.h.g.a f983e;

    public h(ViewGroup viewGroup, View view, Fragment fragment, g0.a aVar, a.h.g.a aVar2) {
        this.f979a = viewGroup;
        this.f980b = view;
        this.f981c = fragment;
        this.f982d = aVar;
        this.f983e = aVar2;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f979a.endViewTransition(this.f980b);
        Fragment.b bVar = this.f981c.I;
        Animator animator2 = bVar == null ? null : bVar.f1562b;
        this.f981c.I(null);
        if (animator2 == null || this.f979a.indexOfChild(this.f980b) >= 0) {
            return;
        }
        ((r.b) this.f982d).a(this.f981c, this.f983e);
    }
}
