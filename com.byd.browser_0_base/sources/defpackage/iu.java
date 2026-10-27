package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import org.chromium.chrome.browser.tasks.tab_management.ClosableTabGridView;

/* JADX INFO: compiled from: ClosableTabGridView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class iu extends AnimatorListenerAdapter {
    public final /* synthetic */ boolean e;
    public final /* synthetic */ View f;
    public final /* synthetic */ ClosableTabGridView g;

    public iu(ClosableTabGridView closableTabGridView, boolean z, View view) {
        this.g = closableTabGridView;
        this.e = z;
        this.f = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (!this.e) {
            this.f.setVisibility(8);
        }
        this.g.g = false;
    }
}
