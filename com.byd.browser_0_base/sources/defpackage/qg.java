package defpackage;

import android.content.Context;
import android.view.View;
import defpackage.k81;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.app.bookmarks.BookmarkAddEditFolderActivity;
import org.chromium.chrome.browser.app.bookmarks.BookmarkFolderSelectActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkRow;
import org.chromium.chrome.browser.toolbar.HomeButton;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class qg implements k81.a {
    public final /* synthetic */ int e;
    public final /* synthetic */ View f;

    public /* synthetic */ qg(View view, int i) {
        this.e = i;
        this.f = view;
    }

    @Override // k81.a
    public final void a(PropertyModel propertyModel) {
        int i = this.e;
        View view = this.f;
        switch (i) {
            case 0:
                BookmarkRow bookmarkRow = (BookmarkRow) view;
                int i2 = BookmarkRow.G;
                bookmarkRow.getClass();
                int iH = propertyModel.h(o81.a);
                if (iH == R$string.bookmark_item_select) {
                    bookmarkRow.setChecked(bookmarkRow.B.l().i(bookmarkRow.C));
                    n3.X0("Android.BookmarkPage.SelectFromMenu");
                    if (bookmarkRow.C.getType() == 2) {
                        n3.X0("Android.BookmarkPage.ReadingList.SelectFromMenu");
                    }
                    break;
                } else if (iH == R$string.bookmark_item_edit) {
                    BookmarkItem bookmarkItemH = bookmarkRow.B.h().h(bookmarkRow.C);
                    boolean z = bookmarkItemH.d;
                    BookmarkId bookmarkId = bookmarkItemH.c;
                    if (!z) {
                        lh.f(bookmarkRow.getContext(), bookmarkId);
                    } else {
                        BookmarkAddEditFolderActivity.O1(bookmarkRow.getContext(), bookmarkId);
                    }
                    break;
                } else if (iH == R$string.reading_list_mark_as_read) {
                    bookmarkRow.B.h().t(bookmarkRow.B.h().h(bookmarkRow.C).b, true);
                    n3.X0("Android.BookmarkPage.ReadingList.MarkAsRead");
                    break;
                } else if (iH == R$string.reading_list_mark_as_unread) {
                    bookmarkRow.B.h().t(bookmarkRow.B.h().h(bookmarkRow.C).b, false);
                    n3.X0("Android.BookmarkPage.ReadingList.MarkAsUnread");
                    break;
                } else if (iH == R$string.bookmark_item_move) {
                    Context context = bookmarkRow.getContext();
                    context.startActivity(BookmarkFolderSelectActivity.K1(context, false, bookmarkRow.C));
                    n3.X0("MobileBookmarkManagerMoveToFolder");
                    break;
                } else if (iH != R$string.bookmark_item_delete) {
                    if (iH == R$string.bookmark_show_in_folder) {
                        bookmarkRow.B.c(bookmarkRow.B.h().h(bookmarkRow.C).e);
                        bookmarkRow.B.g(bookmarkRow.C);
                        n3.X0("MobileBookmarkManagerShowInFolder");
                    } else if (iH == R$string.menu_item_move_up) {
                        bookmarkRow.B.e(bookmarkRow.C);
                        n3.X0("MobileBookmarkManagerMoveUp");
                    } else if (iH == R$string.menu_item_move_down) {
                        bookmarkRow.B.m(bookmarkRow.C);
                        n3.X0("MobileBookmarkManagerMoveDown");
                    }
                    break;
                } else {
                    qf qfVar = bookmarkRow.B;
                    if (qfVar != null && qfVar.h() != null) {
                        bookmarkRow.B.h().u(bookmarkRow.C);
                        n3.X0("Android.BookmarkPage.RemoveItem");
                        if (bookmarkRow.C.getType() == 2) {
                            n3.X0("Android.BookmarkPage.ReadingList.RemoveItem");
                        }
                        break;
                    }
                }
                break;
            default:
                HomeButton homeButton = (HomeButton) view;
                homeButton.r.onResult(homeButton.getContext());
                break;
        }
    }
}
