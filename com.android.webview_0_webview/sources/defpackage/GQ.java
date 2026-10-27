package defpackage;

import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.weblayer_private.TabImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class GQ extends AbstractC0929iE {
    public final /* synthetic */ TabImpl b;

    public GQ(TabImpl tabImpl) {
        this.b = tabImpl;
    }

    @Override // defpackage.AbstractC0929iE
    public void didStartNavigation(NavigationHandle navigationHandle) {
        if (!navigationHandle.a || navigationHandle.c) {
            return;
        }
        this.b.w();
    }
}
