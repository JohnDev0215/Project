package defpackage;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import defpackage.k81;
import java.util.List;
import org.chromium.chrome.browser.app.bookmarks.BookmarkAddEditFolderActivity;
import org.chromium.chrome.browser.app.bookmarks.BookmarkFolderSelectActivity;
import org.chromium.chrome.browser.feed.sections.SectionHeaderView;
import org.chromium.chrome.browser.history_clusters.b;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class uf implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ uf(Object obj, int i, Object obj2, Object obj3) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        Object obj = this.h;
        Object obj2 = this.g;
        Object obj3 = this.f;
        switch (i) {
            case 0:
                BookmarkFolderSelectActivity bookmarkFolderSelectActivity = (BookmarkFolderSelectActivity) obj3;
                Button button = (Button) obj2;
                Button button2 = (Button) obj;
                int i2 = BookmarkFolderSelectActivity.Q;
                if (view == button) {
                    bookmarkFolderSelectActivity.finish();
                    return;
                }
                if (view != button2) {
                    bookmarkFolderSelectActivity.getClass();
                    return;
                }
                BookmarkFolderSelectActivity.c cVar = bookmarkFolderSelectActivity.M.g.get(bookmarkFolderSelectActivity.O);
                if (bookmarkFolderSelectActivity.f58J) {
                    BookmarkId bookmarkId = cVar.e == 1 ? cVar.a : null;
                    Intent intent = new Intent();
                    intent.putExtra("BookmarkFolderSelectActivity.selectedFolder", bookmarkId.toString());
                    bookmarkFolderSelectActivity.setResult(-1, intent);
                    bookmarkFolderSelectActivity.finish();
                    return;
                }
                int i3 = cVar.e;
                if (i3 == 0) {
                    BookmarkAddEditFolderActivity.N1(bookmarkFolderSelectActivity, bookmarkFolderSelectActivity.K);
                    return;
                } else {
                    if (i3 == 1) {
                        bookmarkFolderSelectActivity.M1(cVar.a);
                        return;
                    }
                    return;
                }
            case 1:
                SectionHeaderView sectionHeaderView = (SectionHeaderView) obj3;
                ya1 ya1Var = (ya1) obj2;
                k81.a aVar = (k81.a) obj;
                int i4 = SectionHeaderView.l;
                sectionHeaderView.getClass();
                a8.b0(4);
                if (ya1Var == null || aVar == null) {
                    return;
                }
                sectionHeaderView.h.setDelegate(new bh2(new zd(sectionHeaderView.h.getContext(), ya1Var, aVar)));
                ListMenuButton listMenuButton = sectionHeaderView.h;
                listMenuButton.n = true;
                listMenuButton.f();
                return;
            default:
                ((b) obj3).c((xa1) obj2, (List) obj);
                throw null;
        }
    }
}
