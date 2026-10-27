package defpackage;

import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: BookmarkToolbarMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class dh implements fh, DragReorderableListAdapter.a {
    public final PropertyModel e;
    public final xf f;
    public qf g;

    public dh(PropertyModel propertyModel, xf xfVar, rs1 rs1Var) {
        this.e = propertyModel;
        this.f = xfVar;
        xfVar.p.a(this);
        rs1Var.h(new so(11, this));
    }

    @Override // defpackage.fh
    public final void a(int i) {
        this.e.p(eh.d, Integer.valueOf(i));
    }

    @Override // org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter.a
    public final void b(boolean z) {
        this.e.l(eh.g, z);
    }

    @Override // defpackage.fh
    public final void d(BookmarkId bookmarkId) {
        this.e.p(eh.h, bookmarkId);
    }

    @Override // defpackage.fh
    public final void onDestroy() {
        this.f.p.c(this);
        qf qfVar = this.g;
        if (qfVar != null) {
            qfVar.i(this);
        }
    }
}
