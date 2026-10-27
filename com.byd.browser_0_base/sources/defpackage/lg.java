package defpackage;

import android.annotation.SuppressLint;
import android.view.View;
import defpackage.wm3;
import org.chromium.chrome.browser.bookmarks.BookmarkRow;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: BookmarkManagerViewBinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class lg {
    @SuppressLint({"ClickableViewAccessibility"})
    public static void a(PropertyModel propertyModel, View view, v62 v62Var) {
        PropertyModel.r<BookmarkId> rVar = ig.c;
        if (v62Var == rVar) {
            ((BookmarkRow) view).v((BookmarkId) propertyModel.j(rVar), propertyModel.h(ig.d), propertyModel.k(ig.e));
            return;
        }
        if (v62Var == ig.f) {
            ((BookmarkRow) view).setDragHandleOnTouchListener(new jg(0, propertyModel));
            return;
        }
        PropertyModel.n nVar = ig.h;
        if (v62Var == nVar) {
            if (!propertyModel.k(nVar)) {
                wm3.a(view);
                return;
            }
            wm3.a aVar = new wm3.a(2);
            aVar.c = 1;
            wm3.b(view, aVar);
            ((Runnable) propertyModel.j(ig.i)).run();
        }
    }
}
