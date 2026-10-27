package defpackage;

import android.view.animation.Animation;
import androidx.mediarouter.app.MediaRouteControllerDialog;

/* JADX INFO: compiled from: MediaRouteControllerDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class nd1 implements Animation.AnimationListener {
    public final /* synthetic */ MediaRouteControllerDialog a;

    public nd1(MediaRouteControllerDialog mediaRouteControllerDialog) {
        this.a = mediaRouteControllerDialog;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        this.a.l(true);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
    }
}
