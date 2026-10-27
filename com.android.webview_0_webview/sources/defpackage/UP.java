package defpackage;

import android.content.Context;
import android.view.TextureView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class UP extends TextureView {
    public final /* synthetic */ VP s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UP(VP vp, Context context) {
        super(context);
        this.s = vp;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        VP vp = this.s;
        VP vp2 = vp.i;
        if (vp2 != null) {
            vp2.d();
            vp.i = null;
        }
    }
}
