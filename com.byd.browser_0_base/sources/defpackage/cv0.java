package defpackage;

import org.chromium.chrome.browser.history.a;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: compiled from: HistoryContentManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class cv0 extends mg {
    public final /* synthetic */ dv0 e;

    public cv0(dv0 dv0Var) {
        this.e = dv0Var;
    }

    @Override // defpackage.mg
    public final void h() {
        dv0 dv0Var = this.e;
        ev0 ev0Var = dv0Var.g;
        if (ev0Var != null) {
            ev0Var.j = null;
            ev0Var.c();
            a aVar = dv0Var.o;
            aVar.e.d(0, aVar.h, "refresh_collect ");
            dv0Var.g = null;
        }
        dv0Var.e.clear();
        dv0Var.a();
    }

    @Override // defpackage.mg
    public final void m(BookmarkItem bookmarkItem, BookmarkItem bookmarkItem2, boolean z) {
        dv0 dv0Var = this.e;
        ev0 ev0Var = dv0Var.g;
        if (ev0Var != null) {
            ev0Var.j = null;
            ev0Var.c();
            dv0Var.g = null;
            a aVar = dv0Var.o;
            aVar.e.d(0, aVar.h, "refresh_collect ");
        }
        dv0Var.e.clear();
        dv0Var.a();
    }

    @Override // defpackage.mg
    public final void f() {
    }
}
