package defpackage;

import android.app.Activity;
import android.content.Context;
import defpackage.wp2;
import org.chromium.chrome.browser.app.bookmarks.BookmarkAddEditFolderActivity;
import org.chromium.components.bookmarks.BookmarkId;

/* JADX INFO: compiled from: BookmarkUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class kh implements wp2.b {
    public final /* synthetic */ Context e;
    public final /* synthetic */ BookmarkId f;

    public kh(Activity activity, BookmarkId bookmarkId) {
        this.e = activity;
        this.f = bookmarkId;
    }

    @Override // wp2.b
    public final void d(Object obj) {
        n3.X0("TabMultiSelectV2.BookmarkTabsSnackbarEditNotClicked");
    }

    @Override // wp2.b
    public final void j(Object obj) {
        n3.X0("TabMultiSelectV2.BookmarkTabsSnackbarEditClicked");
        BookmarkAddEditFolderActivity.O1(this.e, this.f);
    }
}
