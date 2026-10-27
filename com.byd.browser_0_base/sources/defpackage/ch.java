package defpackage;

import defpackage.eg;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.bookmarks.BookmarkSelectableListLayout;
import org.chromium.chrome.browser.bookmarks.BookmarkToolbar;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: BookmarkToolbarCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class ch {
    public final BookmarkToolbar a;

    public ch(BookmarkSelectableListLayout bookmarkSelectableListLayout, eg.a aVar, SelectableListToolbar.b bVar, xf xfVar, boolean z, rs1 rs1Var, BookmarkModel bookmarkModel, ng ngVar, je0 je0Var) {
        BookmarkToolbar bookmarkToolbar = (BookmarkToolbar) bookmarkSelectableListLayout.j(R$layout.bookmark_toolbar, aVar, R$id.normal_menu_group, R$id.selection_mode_menu_group, null, z);
        this.a = bookmarkToolbar;
        bookmarkToolbar.A(bVar, R$string.bookmark_search_hint, je0Var);
        PropertyModel propertyModel = new PropertyModel(PropertyModel.e(eh.k));
        propertyModel.p(eh.a, bookmarkModel);
        propertyModel.p(eh.b, ngVar);
        propertyModel.p(eh.c, aVar);
        propertyModel.p(eh.d, 1);
        propertyModel.l(eh.f, z);
        propertyModel.l(eh.g, false);
        new dh(propertyModel, xfVar, rs1Var);
        j.b(propertyModel, bookmarkToolbar, new bg(2));
    }
}
