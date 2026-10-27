package defpackage;

import android.content.Context;
import org.chromium.content_public.browser.WebContents;
import org.chromium.weblayer_private.TabImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class YQ extends Qw {
    public final HP i;
    public final Context j;

    public YQ(HP hp, Context context) {
        super(context);
        this.i = hp;
        this.j = context;
    }

    @Override // defpackage.Qw
    public void g(boolean z) {
        TabImpl tabImpl = this.i.g;
        WebContents webContents = tabImpl.c;
        if (!z) {
            tabImpl.setBrowserControlsVisibilityConstraint(3, 3);
            f(webContents, false);
            return;
        }
        if (webContents.J()) {
            webContents.t();
        }
        if (webContents.M().a()) {
            tabImpl.setBrowserControlsVisibilityConstraint(3, 1);
        }
        f(webContents, true);
    }
}
