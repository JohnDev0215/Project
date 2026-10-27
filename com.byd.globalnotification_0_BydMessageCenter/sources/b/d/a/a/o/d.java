package b.d.a.a.o;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.appcompat.widget.ActionMenuView;
import com.google.android.material.bottomappbar.BottomAppBar;

/* JADX INFO: loaded from: classes.dex */
public class d extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2024a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ActionMenuView f2025b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f2026c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ boolean f2027d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ BottomAppBar f2028e;

    public d(BottomAppBar bottomAppBar, ActionMenuView actionMenuView, int i, boolean z) {
        this.f2028e = bottomAppBar;
        this.f2025b = actionMenuView;
        this.f2026c = i;
        this.f2027d = z;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.f2024a = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (this.f2024a) {
            return;
        }
        boolean z = this.f2028e.T != 0;
        BottomAppBar bottomAppBar = this.f2028e;
        int i = bottomAppBar.T;
        if (i != 0) {
            bottomAppBar.T = 0;
            bottomAppBar.getMenu().clear();
            bottomAppBar.n(i);
        }
        BottomAppBar bottomAppBar2 = this.f2028e;
        ActionMenuView actionMenuView = this.f2025b;
        int i2 = this.f2026c;
        boolean z2 = this.f2027d;
        if (bottomAppBar2 == null) {
            throw null;
        }
        e eVar = new e(bottomAppBar2, actionMenuView, i2, z2);
        if (z) {
            actionMenuView.post(eVar);
        } else {
            eVar.run();
        }
    }
}
