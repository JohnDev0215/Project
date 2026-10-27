package p;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* JADX INFO: loaded from: classes.dex */
public final class v3 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ ActionBarOverlayLayout e;

    public /* synthetic */ v3(ActionBarOverlayLayout actionBarOverlayLayout, int i) {
        this.c = i;
        this.e = actionBarOverlayLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        ActionBarOverlayLayout actionBarOverlayLayout = this.e;
        switch (i) {
            case 0:
                actionBarOverlayLayout.h();
                actionBarOverlayLayout.E = actionBarOverlayLayout.l.animate().translationY(0.0f).setListener(actionBarOverlayLayout.F);
                break;
            default:
                actionBarOverlayLayout.h();
                actionBarOverlayLayout.E = actionBarOverlayLayout.l.animate().translationY(-actionBarOverlayLayout.l.getHeight()).setListener(actionBarOverlayLayout.F);
                break;
        }
    }
}
