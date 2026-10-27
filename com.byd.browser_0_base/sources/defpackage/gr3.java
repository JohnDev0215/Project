package defpackage;

import J.N;
import android.view.GestureDetector;
import android.view.MotionEvent;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$FollowResults;
import org.chromium.chrome.browser.feed.webfeed.f;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.widget.LoadingView;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebFeedRecommendationFollowAcceleratorController.java */
/* JADX INFO: loaded from: classes.dex */
public final class gr3 extends GestureDetector.SimpleOnGestureListener {
    public boolean a;
    public final /* synthetic */ byte[] b;
    public final /* synthetic */ f c;

    public gr3(f fVar, byte[] bArr) {
        this.c = fVar;
        this.b = bArr;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        if (!this.a) {
            this.a = true;
            final f fVar = this.c;
            bu buVar = fVar.b.g;
            if (buVar != null) {
                au auVar = new au(buVar, R$string.web_feed_follow_loading_description);
                LoadingView loadingView = buVar.r;
                loadingView.a(auVar);
                loadingView.e();
            }
            final Tab tab = fVar.c.get();
            N.MxULk9PS(0, 62);
            final GURL url = tab.getUrl();
            final byte[] bArr = this.b;
            N.MtL148iF(bArr, true, 6, new Callback() { // from class: fr3
                @Override // org.chromium.base.Callback
                public final void onResult(Object obj) {
                    Tab tab2 = tab;
                    byte[] bArr2 = bArr;
                    GURL gurl = url;
                    f fVar2 = fVar;
                    fVar2.getClass();
                    hr3 hr3Var = new hr3((WebFeedBridge$FollowResults) obj, fVar2, tab2, gurl, bArr2);
                    bu buVar2 = fVar2.b.g;
                    if (buVar2 != null) {
                        LoadingView loadingView2 = buVar2.r;
                        loadingView2.a(hr3Var);
                        loadingView2.c();
                    }
                }
            });
        }
        return true;
    }
}
