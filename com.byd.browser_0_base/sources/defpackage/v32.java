package defpackage;

import org.chromium.chrome.browser.bookmarks.BookmarkModel;

/* JADX INFO: compiled from: PriceDropNotificationManagerImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v32 extends mg {
    public final /* synthetic */ Runnable e;
    public final /* synthetic */ BookmarkModel f;

    public v32(xi xiVar, BookmarkModel bookmarkModel) {
        this.e = xiVar;
        this.f = bookmarkModel;
    }

    @Override // defpackage.mg
    public final void g() {
        this.e.run();
        this.f.r(this);
    }

    @Override // defpackage.mg
    public final void f() {
    }
}
