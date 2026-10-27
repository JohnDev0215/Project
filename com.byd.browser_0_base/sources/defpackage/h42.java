package defpackage;

import defpackage.a00;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.commerce.core.ShoppingService;

/* JADX INFO: compiled from: PriceTrackingActionProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h42 implements a00.a {
    public final nw2<ShoppingService> a;
    public final nw2<BookmarkModel> b;
    public final nw2<Profile> c;

    public h42(nw2 nw2Var, nw2 nw2Var2, zp1 zp1Var) {
        this.a = nw2Var;
        this.b = nw2Var2;
        this.c = zp1Var;
    }

    @Override // a00.a
    public final void a(Tab tab, gm2 gm2Var) {
        BookmarkModel bookmarkModel = this.b.get();
        bookmarkModel.g(new ci3(this, gm2Var, tab, bookmarkModel, 1));
    }
}
