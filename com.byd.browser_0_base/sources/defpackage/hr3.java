package defpackage;

import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$FollowResults;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedMetadata;
import org.chromium.chrome.browser.feed.webfeed.f;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.widget.LoadingView;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebFeedRecommendationFollowAcceleratorController.java */
/* JADX INFO: loaded from: classes.dex */
public final class hr3 implements LoadingView.c {
    public final /* synthetic */ WebFeedBridge$FollowResults e;
    public final /* synthetic */ Tab f;
    public final /* synthetic */ byte[] g;
    public final /* synthetic */ GURL h;
    public final /* synthetic */ f i;

    public hr3(WebFeedBridge$FollowResults webFeedBridge$FollowResults, f fVar, Tab tab, GURL gurl, byte[] bArr) {
        this.i = fVar;
        this.e = webFeedBridge$FollowResults;
        this.f = tab;
        this.g = bArr;
        this.h = gurl;
    }

    @Override // org.chromium.ui.widget.LoadingView.c
    public final void M() {
        f fVar = this.i;
        fVar.b.b();
        if (this.e.a == 1) {
            fVar.b.d();
        }
        WebFeedBridge$FollowResults webFeedBridge$FollowResults = this.e;
        WebFeedBridge$WebFeedMetadata webFeedBridge$WebFeedMetadata = webFeedBridge$FollowResults.b;
        if (webFeedBridge$WebFeedMetadata != null) {
            byte[] bArr = webFeedBridge$WebFeedMetadata.a;
        }
        fVar.d.a(this.f, webFeedBridge$FollowResults, this.g, this.h, webFeedBridge$WebFeedMetadata.b, 6);
    }

    @Override // org.chromium.ui.widget.LoadingView.c
    public final void V0() {
    }
}
