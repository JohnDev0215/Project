package r;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* JADX INFO: renamed from: r.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC2240b implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ActionBarOverlayLayout f21471m;

    public /* synthetic */ RunnableC2240b(ActionBarOverlayLayout actionBarOverlayLayout, int i7) {
        this.l = i7;
        this.f21471m = actionBarOverlayLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = this.f21471m;
                actionBarOverlayLayout.e();
                actionBarOverlayLayout.f11313E = actionBarOverlayLayout.f11320n.animate().translationY(0.0f).setListener(actionBarOverlayLayout.f11314F);
                break;
            default:
                ActionBarOverlayLayout actionBarOverlayLayout2 = this.f21471m;
                actionBarOverlayLayout2.e();
                actionBarOverlayLayout2.f11313E = actionBarOverlayLayout2.f11320n.animate().translationY(-actionBarOverlayLayout2.f11320n.getHeight()).setListener(actionBarOverlayLayout2.f11314F);
                break;
        }
    }
}
