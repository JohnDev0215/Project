package defpackage;

import android.view.SurfaceView;
import android.widget.FrameLayout;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SP extends AbstractRunnableC0550bQ {
    public final /* synthetic */ VP u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SP(VP vp) {
        super(vp.q);
        this.u = vp;
    }

    @Override // defpackage.AbstractRunnableC0550bQ
    public void a() {
        VP vp = this.u;
        int i = vp.a;
        if (i != 0) {
            if (i == 1) {
                vp.c.removeView(vp.n);
                VP vp2 = this.u;
                VP vp3 = vp2.j;
                if (vp3 != null) {
                    vp3.f();
                    vp2.j = null;
                    return;
                }
                return;
            }
            return;
        }
        FrameLayout frameLayout = vp.c;
        SurfaceView surfaceView = vp.l;
        int childCount = frameLayout.getChildCount();
        int iIndexOfChild = frameLayout.indexOfChild(surfaceView);
        if (iIndexOfChild > 0) {
            for (int i2 = 0; i2 < iIndexOfChild; i2++) {
                frameLayout.bringChildToFront(frameLayout.getChildAt(0));
            }
            for (int i3 = 0; i3 < (childCount - iIndexOfChild) - 1; i3++) {
                frameLayout.bringChildToFront(frameLayout.getChildAt(1));
            }
            frameLayout.invalidate();
        }
        this.u.c.postOnAnimation(new RP(this, new QP(this)));
    }
}
