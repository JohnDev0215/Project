package defpackage;

import J.N;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedPageInformation;
import org.chromium.chrome.browser.feed.webfeed.WebFeedMainMenuItem;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.widget.chips.ChipView;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class cr3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ WebFeedMainMenuItem f;

    public /* synthetic */ cr3(WebFeedMainMenuItem webFeedMainMenuItem, int i) {
        this.e = i;
        this.f = webFeedMainMenuItem;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        WebFeedMainMenuItem webFeedMainMenuItem = this.f;
        switch (i) {
            case 0:
                ChipView chipView = webFeedMainMenuItem.m;
                webFeedMainMenuItem.k = chipView;
                webFeedMainMenuItem.c(chipView, webFeedMainMenuItem.e.getText(R$string.menu_follow), R$drawable.ic_add, new vt(10, webFeedMainMenuItem));
                break;
            default:
                Tab tab = webFeedMainMenuItem.g;
                GURL gurl = webFeedMainMenuItem.f;
                N.MSWj76M1(new WebFeedBridge$WebFeedPageInformation(tab, gurl), 2, new dr3(webFeedMainMenuItem, 0));
                break;
        }
    }
}
