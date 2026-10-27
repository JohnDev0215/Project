package androidx.mediarouter.app;

import android.view.animation.Animation;
import androidx.mediarouter.app.OverlayListView;

/* JADX INFO: compiled from: MediaRouteControllerDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Animation.AnimationListener {
    public final /* synthetic */ MediaRouteControllerDialog a;

    public c(MediaRouteControllerDialog mediaRouteControllerDialog) {
        this.a = mediaRouteControllerDialog;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
        MediaRouteControllerDialog mediaRouteControllerDialog = this.a;
        OverlayListView overlayListView = mediaRouteControllerDialog.I;
        for (OverlayListView.a aVar : overlayListView.e) {
            if (!aVar.k) {
                aVar.j = overlayListView.getDrawingTime();
                aVar.k = true;
            }
        }
        mediaRouteControllerDialog.I.postDelayed(mediaRouteControllerDialog.s0, mediaRouteControllerDialog.l0);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
    }
}
