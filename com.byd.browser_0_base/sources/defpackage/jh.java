package defpackage;

import android.app.Activity;
import defpackage.wp2;
import org.chromium.components.bookmarks.BookmarkId;

/* JADX INFO: compiled from: BookmarkUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class jh implements wp2.b {
    public final /* synthetic */ Activity e;
    public final /* synthetic */ BookmarkId f;

    public jh(Activity activity, BookmarkId bookmarkId) {
        this.e = activity;
        this.f = bookmarkId;
    }

    @Override // wp2.b
    public final void d(Object obj) {
        n3.X0("EnhancedBookmarks.EditAfterCreateButtonNotClicked");
    }

    @Override // wp2.b
    public final void j(Object obj) {
        n3.X0("EnhancedBookmarks.EditAfterCreateButtonClicked");
        lh.f(this.e, this.f);
    }
}
