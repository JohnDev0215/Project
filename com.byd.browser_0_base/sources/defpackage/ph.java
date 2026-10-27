package defpackage;

import J.N;
import defpackage.mh;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: compiled from: BookmarkWidgetServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class ph implements Runnable {
    public final /* synthetic */ BookmarkId e;
    public final /* synthetic */ mh.d f;

    public ph(mh.d dVar, BookmarkId bookmarkId) {
        this.f = dVar;
        this.e = bookmarkId;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList;
        mh.d dVar = this.f;
        dVar.getClass();
        mh.c cVar = new mh.c();
        dVar.b = cVar;
        BookmarkId bookmarkIdK = this.e;
        if (bookmarkIdK != null) {
            cVar.a = mh.a.a(dVar.c.h(bookmarkIdK));
        }
        if (dVar.b.a == null) {
            bookmarkIdK = dVar.c.k();
            dVar.b.a = mh.a.a(dVar.c.h(bookmarkIdK));
        }
        mh.c cVar2 = dVar.b;
        cVar2.b = mh.a.a(dVar.c.h(cVar2.a.d));
        BookmarkModel bookmarkModel = dVar.c;
        bookmarkModel.getClass();
        Object obj = ThreadUtils.a;
        if (bookmarkModel.a == 0) {
            arrayList = new ArrayList();
        } else {
            ArrayList arrayList2 = new ArrayList();
            N.M4_aKMtg(bookmarkModel.a, bookmarkModel, bookmarkIdK, arrayList2);
            arrayList = arrayList2;
        }
        Collections.sort(arrayList, new qh());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            mh.a aVarA = mh.a.a((BookmarkItem) it.next());
            if (!aVarA.e) {
                dVar.h++;
                dVar.d.b(aVarA.b, dVar.f, new rh(dVar, aVarA));
            }
            dVar.b.c.add(aVarA);
        }
        int i = dVar.h - 1;
        dVar.h = i;
        if (i == 0) {
            ((oh) dVar.a).a.add(dVar.b);
            dVar.d.a();
        }
    }
}
