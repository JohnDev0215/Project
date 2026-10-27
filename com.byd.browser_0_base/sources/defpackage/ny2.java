package defpackage;

import android.app.Activity;
import android.util.Log;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.bookmarks.BookmarkId;

/* JADX INFO: compiled from: TabBookmarker.java */
/* JADX INFO: loaded from: classes.dex */
public final class ny2 {
    public final Activity a;
    public final zp1 b;
    public final nw2<ji> c;
    public final nw2<wp2> d;

    public ny2(Activity activity, aq1 aq1Var, bs bsVar, bp bpVar, boolean z) {
        this.a = activity;
        this.b = aq1Var;
        this.c = bsVar;
        this.d = bpVar;
    }

    public final void a(final Tab tab, final boolean z) {
        final String simpleName = ny2.class.getSimpleName();
        if (tab == null) {
            Log.e(simpleName, "  addOrEditBookmark: tabToBookmark == null  ");
            return;
        }
        if (tab.isFrozen()) {
            Log.e(simpleName, "   tabToBookmark.isFrozen() " + tab.isFrozen());
            return;
        }
        final BookmarkModel bookmarkModel = (BookmarkModel) this.b.get();
        if (bookmarkModel == null) {
            Log.e(simpleName, " addOrEditBookmark:bookmarkModel == null ");
        } else {
            if (bookmarkModel.q()) {
                bookmarkModel.g(new Runnable() { // from class: my2
                    public final /* synthetic */ int i = 0;

                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Removed duplicated region for block: B:26:0x0083  */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final void run() {
                        /*
                            Method dump skipped, instruction units count: 289
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: defpackage.my2.run():void");
                    }
                });
                return;
            }
            StringBuilder sb = new StringBuilder(" !bookmarkModel.isEditBookmarksEnabled() ");
            sb.append(!bookmarkModel.q());
            Log.e(simpleName, sb.toString());
        }
    }

    public final void b(Tab tab) {
        BookmarkId bookmarkIdP = ((BookmarkModel) this.b.get()).p(tab);
        if (bookmarkIdP == null) {
            a(tab, true);
        } else {
            lh.e(this.a, this.c.get(), true, bookmarkIdP, false, false);
        }
    }
}
