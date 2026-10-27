package defpackage;

import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.feed.FeedSwipeRefreshLayout;
import org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout;

/* JADX INFO: compiled from: FeedSwipeRefreshLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class vl0 implements SwipeRefreshLayout.e {
    public final /* synthetic */ FeedSwipeRefreshLayout e;

    public vl0(FeedSwipeRefreshLayout feedSwipeRefreshLayout) {
        this.e = feedSwipeRefreshLayout;
    }

    @Override // org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout.e
    public final void c() {
        Iterator<SwipeRefreshLayout.e> it = this.e.L.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((SwipeRefreshLayout.e) aVar.next()).c();
            }
        }
    }
}
