package defpackage;

import android.view.View;
import org.chromium.chrome.browser.feed.webfeed.WebFeedMainMenuItem;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class br3 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ WebFeedMainMenuItem f;

    public /* synthetic */ br3(WebFeedMainMenuItem webFeedMainMenuItem, int i) {
        this.e = i;
        this.f = webFeedMainMenuItem;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        WebFeedMainMenuItem webFeedMainMenuItem = this.f;
        switch (i) {
            case 0:
                int i2 = WebFeedMainMenuItem.s;
                webFeedMainMenuItem.b();
                break;
            default:
                int i3 = WebFeedMainMenuItem.s;
                webFeedMainMenuItem.b();
                break;
        }
    }
}
