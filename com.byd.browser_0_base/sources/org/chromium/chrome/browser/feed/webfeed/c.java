package org.chromium.chrome.browser.feed.webfeed;

import J.N;
import android.view.GestureDetector;
import android.view.MotionEvent;
import defpackage.au;
import defpackage.bu;
import defpackage.yq3;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.feed.webfeed.e;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.widget.LoadingView;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebFeedFollowIntroController.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends GestureDetector.SimpleOnGestureListener {
    public boolean a;
    public final /* synthetic */ e.b b;
    public final /* synthetic */ e c;

    public c(e eVar, e.b bVar) {
        this.c = eVar;
        this.b = bVar;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        if (!this.a) {
            this.a = true;
            e.b bVar = this.b;
            e eVar = this.c;
            if (!eVar.d.a("webfeed_follow_intro_debug.enable")) {
                eVar.f.notifyEvent("web_feed_follow_intro_clicked");
            }
            bu buVar = eVar.h.g;
            if (buVar != null) {
                au auVar = new au(buVar, R$string.web_feed_follow_loading_description);
                LoadingView loadingView = buVar.r;
                loadingView.a(auVar);
                loadingView.e();
            }
            Tab tab = eVar.i.get();
            N.MxULk9PS(0, 33);
            GURL url = tab.getUrl();
            N.MztsaYnr(new WebFeedBridge$WebFeedPageInformation(tab, url), 2, new yq3(eVar, tab, url, bVar, 0));
        }
        return true;
    }
}
