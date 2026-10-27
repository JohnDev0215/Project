package defpackage;

import J.N;
import android.view.Surface;
import org.chromium.content_public.browser.WebContents;
import org.chromium.weblayer_private.ContentViewRenderView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class XP implements WP {
    public VP a;
    public final /* synthetic */ ContentViewRenderView b;

    public XP(ContentViewRenderView contentViewRenderView, NP np) {
        this.b = contentViewRenderView;
    }

    @Override // defpackage.WP
    public void a() {
        VP vp = this.b.u;
        if (vp != null && vp != this.a) {
            vp.e(true);
            VP vp2 = this.a;
            VP vp3 = this.b.u;
            vp2.i = vp3;
            vp3.j = vp2;
        }
        this.b.u = this.a;
        N.MUdnoeZS(this.b.v);
    }

    @Override // defpackage.WP
    public void b(Surface surface, boolean z, int i, int i2, int i3) {
        N.M4g6Q4ab(this.b.v, z, i, i2, i3, surface);
        ContentViewRenderView contentViewRenderView = this.b;
        WebContents webContents = contentViewRenderView.x;
        if (webContents != null) {
            N.MQtCkWmJ(contentViewRenderView.v, webContents, i2, i3);
        }
    }

    @Override // defpackage.WP
    public void c(boolean z) {
        N.MfxYKOP7(this.b.v, z);
    }
}
