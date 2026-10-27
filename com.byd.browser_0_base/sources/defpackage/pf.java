package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;

/* JADX INFO: compiled from: BookmarkBridge.java */
/* JADX INFO: loaded from: classes.dex */
public final class pf extends mg {
    public final /* synthetic */ long e;
    public final /* synthetic */ Runnable f;
    public final /* synthetic */ BookmarkBridge g;

    public pf(BookmarkModel bookmarkModel, long j, Runnable runnable) {
        this.g = bookmarkModel;
        this.e = j;
        this.f = runnable;
    }

    @Override // defpackage.mg
    public final void g() {
        this.g.r(this);
        oj2.b0(SystemClock.elapsedRealtime() - this.e, "PartnerBookmark.LoadingTime");
        this.f.run();
    }

    @Override // defpackage.mg
    public final void f() {
    }
}
