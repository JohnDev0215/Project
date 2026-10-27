package defpackage;

import defpackage.gg;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.browser_ui.widget.selectable_list.a;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: compiled from: BookmarkDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public interface qf {
    gg.g a();

    LargeIconBridge b();

    void c(BookmarkId bookmarkId);

    void d(BookmarkId bookmarkId);

    void e(BookmarkId bookmarkId);

    void g(BookmarkId bookmarkId);

    BookmarkModel h();

    void i(fh fhVar);

    void j();

    void k(fh fhVar);

    a<BookmarkId> l();

    void m(BookmarkId bookmarkId);

    int n();
}
