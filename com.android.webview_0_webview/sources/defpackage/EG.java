package defpackage;

import android.app.Activity;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class EG implements Callback {
    public final /* synthetic */ HG a;

    public EG(HG hg) {
        this.a = hg;
    }

    @Override // org.chromium.base.Callback
    public void a(Object obj) {
        HG hg = this.a;
        hg.P();
        hg.x = (Activity) obj;
        hg.R();
    }
}
