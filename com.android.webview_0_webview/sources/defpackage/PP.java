package defpackage;

import android.widget.FrameLayout;
import org.chromium.weblayer_private.ContentViewRenderView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PP extends AbstractRunnableC0550bQ {
    public final /* synthetic */ VP u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PP(VP vp, ContentViewRenderView contentViewRenderView) {
        super(vp.q);
        this.u = vp;
    }

    @Override // defpackage.AbstractRunnableC0550bQ
    public void a() {
        VP vp = this.u;
        if (vp.f) {
            return;
        }
        this.u.c.addView(vp.a == 0 ? vp.l : vp.n, 0, new FrameLayout.LayoutParams(-1, -1));
        this.u.c.invalidate();
    }
}
