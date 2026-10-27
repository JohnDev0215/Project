package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import androidx.mediarouter.app.MediaRouteControllerDialog;
import java.util.HashSet;

/* JADX INFO: compiled from: MediaRouteControllerDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class md1 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ MediaRouteControllerDialog e;

    public md1(MediaRouteControllerDialog mediaRouteControllerDialog) {
        this.e = mediaRouteControllerDialog;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        MediaRouteControllerDialog mediaRouteControllerDialog = this.e;
        mediaRouteControllerDialog.I.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        HashSet hashSet = mediaRouteControllerDialog.L;
        if (hashSet == null || hashSet.size() == 0) {
            mediaRouteControllerDialog.l(true);
            return;
        }
        nd1 nd1Var = new nd1(mediaRouteControllerDialog);
        int firstVisiblePosition = mediaRouteControllerDialog.I.getFirstVisiblePosition();
        boolean z = false;
        for (int i = 0; i < mediaRouteControllerDialog.I.getChildCount(); i++) {
            View childAt = mediaRouteControllerDialog.I.getChildAt(i);
            if (mediaRouteControllerDialog.L.contains(mediaRouteControllerDialog.f14J.getItem(firstVisiblePosition + i))) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(mediaRouteControllerDialog.m0);
                alphaAnimation.setFillEnabled(true);
                alphaAnimation.setFillAfter(true);
                if (!z) {
                    alphaAnimation.setAnimationListener(nd1Var);
                    z = true;
                }
                childAt.clearAnimation();
                childAt.startAnimation(alphaAnimation);
            }
        }
    }
}
