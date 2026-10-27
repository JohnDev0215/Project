package org.chromium.chrome.browser.bookmarks;

import J.N;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.protobuf.d0;
import defpackage.C0173a;
import defpackage.c32;
import defpackage.i9;
import defpackage.mg;
import defpackage.n3;
import defpackage.pf;
import defpackage.xz;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader;
import org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader.b;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkBridge {
    public long a;
    public boolean b;
    public boolean c;
    public final b<mg> d = new b<>();

    public BookmarkBridge(long j) {
        this.a = j;
        this.b = N.MHTPaGlQ(j, this);
    }

    @CalledByNative
    public static void addToBookmarkIdList(List<BookmarkId> list, long j, int i) {
        list.add(new BookmarkId(i, j));
    }

    @CalledByNative
    public static void addToBookmarkIdListWithDepth(List<BookmarkId> list, long j, int i, List<Integer> list2, int i2) {
        list.add(new BookmarkId(i, j));
        list2.add(Integer.valueOf(i2));
    }

    @CalledByNative
    public static void addToList(List<BookmarkItem> list, BookmarkItem bookmarkItem) {
        list.add(bookmarkItem);
    }

    @CalledByNative
    public static BookmarkItem createBookmarkItem(long j, int i, String str, GURL gurl, boolean z, long j2, int i2, boolean z2, boolean z3, long j3, boolean z4) {
        return new BookmarkItem(new BookmarkId(i, j), str, gurl, z, new BookmarkId(i2, j2), z2, z3, j3, z4);
    }

    @CalledByNative
    public static BookmarkModel createBookmarkModel(long j) {
        return new BookmarkModel(j);
    }

    public final BookmarkId a(BookmarkId bookmarkId, int i, String str, GURL gurl) {
        Object obj = ThreadUtils.a;
        if (this.a == 0) {
            return null;
        }
        n3.X0("BookmarkAdded");
        if (TextUtils.isEmpty(str)) {
            str = gurl.j();
        }
        return (BookmarkId) N.Mg53Jgou(this.a, this, bookmarkId, i, str, gurl);
    }

    public final BookmarkId b(String str, BookmarkId bookmarkId) {
        Object obj = ThreadUtils.a;
        if (this.a == 0 || bookmarkId.getType() != 0 || str == null) {
            return null;
        }
        BookmarkId bookmarkId2 = (BookmarkId) N.MoWzwBNR(this.a, this, bookmarkId, 0, str);
        Iterator<mg> it = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return bookmarkId2;
            }
            ((mg) aVar.next()).e();
        }
    }

    @CalledByNative
    public final void bookmarkAllUserNodesRemoved() {
        Iterator<mg> it = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((mg) aVar.next()).f();
            }
        }
    }

    @CalledByNative
    public final void bookmarkModelChanged() {
        if (this.b) {
            return;
        }
        Iterator<mg> it = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((mg) aVar.next()).f();
            }
        }
    }

    @CalledByNative
    public final void bookmarkModelLoaded() {
        this.c = true;
        Iterator<mg> it = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((mg) aVar.next()).g();
            }
        }
    }

    @CalledByNative
    public final void bookmarkNodeAdded(BookmarkItem bookmarkItem, int i) {
        if (this.b) {
            return;
        }
        Iterator<mg> it = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((mg) aVar.next()).h();
            }
        }
    }

    @CalledByNative
    public final void bookmarkNodeChanged(BookmarkItem bookmarkItem) {
        if (this.b) {
            return;
        }
        Iterator<mg> it = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((mg) aVar.next()).i(bookmarkItem);
            }
        }
    }

    @CalledByNative
    public final void bookmarkNodeChildrenReordered(BookmarkItem bookmarkItem) {
        if (this.b) {
            return;
        }
        Iterator<mg> it = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((mg) aVar.next()).k();
            }
        }
    }

    @CalledByNative
    public final void bookmarkNodeMoved(BookmarkItem bookmarkItem, int i, BookmarkItem bookmarkItem2, int i2) {
        if (this.b) {
            return;
        }
        Iterator<mg> it = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((mg) aVar.next()).l(bookmarkItem, bookmarkItem2, i2);
            }
        }
    }

    @CalledByNative
    public final void bookmarkNodeRemoved(BookmarkItem bookmarkItem, int i, BookmarkItem bookmarkItem2) {
        Iterator<mg> it = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((mg) aVar.next()).m(bookmarkItem, bookmarkItem2, this.b);
            }
        }
    }

    public final void c(mg mgVar) {
        this.d.a(mgVar);
    }

    public final BookmarkId d(String str, GURL gurl) {
        Object obj = ThreadUtils.a;
        long j = this.a;
        if (j == 0) {
            return null;
        }
        return (BookmarkId) N.MPWBoFyN(j, this, str, gurl);
    }

    @CalledByNative
    public final void destroyFromNative() {
        long j = this.a;
        if (j != 0) {
            N.M$aEU5TZ(j, this);
            this.a = 0L;
            this.c = false;
        }
        this.d.clear();
    }

    public final void e(BookmarkId bookmarkId) {
        Object obj = ThreadUtils.a;
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.MJ2llFWZ(j, this, bookmarkId);
    }

    @CalledByNative
    public final void editBookmarksEnabledChanged() {
        Iterator<mg> it = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((mg) aVar.next()).f();
            }
        }
    }

    @CalledByNative
    public final void extensiveBookmarkChangesBeginning() {
        this.b = true;
    }

    @CalledByNative
    public final void extensiveBookmarkChangesEnded() {
        this.b = false;
        bookmarkModelChanged();
    }

    public final boolean f(BookmarkId bookmarkId) {
        Object obj = ThreadUtils.a;
        long j = this.a;
        if (j == 0) {
            return false;
        }
        return N.MhzzenO8(j, this, bookmarkId.getId(), bookmarkId.getType());
    }

    public final void g(Runnable runnable) {
        if (this.c) {
            runnable.run();
            return;
        }
        c(new pf((BookmarkModel) this, SystemClock.elapsedRealtime(), runnable));
        Context context = xz.a;
        if (n3.D) {
            return;
        }
        boolean z = true;
        n3.D = true;
        PartnerBookmarksReader partnerBookmarksReader = new PartnerBookmarksReader(context, PartnerBrowserCustomizations.b());
        if ((context.getApplicationInfo().flags & 1) == 1) {
            if (partnerBookmarksReader.b == 0) {
                return;
            }
            partnerBookmarksReader.new b().c(i9.e);
            return;
        }
        partnerBookmarksReader.h = true;
        if (partnerBookmarksReader.i) {
            N.MZy4XMIu(partnerBookmarksReader.b, partnerBookmarksReader);
        }
        synchronized (partnerBookmarksReader.d) {
            if (partnerBookmarksReader.e != 0 || !partnerBookmarksReader.h || !partnerBookmarksReader.i) {
                z = false;
            }
            if (z) {
                partnerBookmarksReader.a();
            }
        }
    }

    public final BookmarkItem h(BookmarkId bookmarkId) {
        Object obj = ThreadUtils.a;
        if (this.a == 0 || bookmarkId == null) {
            return null;
        }
        if (BookmarkId.c.equals(bookmarkId)) {
            return new BookmarkItem(bookmarkId, null, null, true, n(), false, false, 0L, false);
        }
        BookmarkItem bookmarkItem = (BookmarkItem) N.M4Ir5snM(this.a, this, bookmarkId.getId(), bookmarkId.getType());
        if (bookmarkId.getId() != 3 || bookmarkId.getType() != 0) {
            return bookmarkItem;
        }
        return new BookmarkItem(bookmarkItem.c, xz.a.getString(R$string.bookmarks_title), bookmarkItem.b, bookmarkItem.d, bookmarkItem.e, bookmarkItem.a(), bookmarkItem.g, bookmarkItem.h, bookmarkItem.i);
    }

    public final int i(BookmarkId bookmarkId) {
        Object obj = ThreadUtils.a;
        long j = this.a;
        if (j == 0) {
            return 0;
        }
        return N.MywxQQ$n(j, this, bookmarkId.getId(), bookmarkId.getType());
    }

    public final ArrayList j(BookmarkId bookmarkId) {
        Object obj = ThreadUtils.a;
        if (this.a == 0) {
            return new ArrayList();
        }
        if (BookmarkId.c.equals(bookmarkId)) {
            return s("", 1, -1);
        }
        ArrayList arrayList = new ArrayList();
        N.MjHaBU2n(this.a, this, bookmarkId.getId(), bookmarkId.getType(), arrayList);
        return arrayList;
    }

    public final BookmarkId k() {
        Object obj = ThreadUtils.a;
        long j = this.a;
        if (j == 0) {
            return null;
        }
        return (BookmarkId) N.M7yxRJ0Q(j, this);
    }

    public final c32 l(BookmarkId bookmarkId) {
        byte[] bArrMk0Xl$Jx;
        long j = this.a;
        if (j == 0 || bookmarkId == null || (bArrMk0Xl$Jx = N.Mk0Xl$Jx(j, this, bookmarkId.getId(), bookmarkId.getType())) == null) {
            return null;
        }
        try {
            return c32.S(bArrMk0Xl$Jx);
        } catch (d0 unused) {
            long j2 = this.a;
            if (j2 != 0) {
                N.MzomnHrD(j2, this, bookmarkId.getId(), bookmarkId.getType());
            }
            return null;
        }
    }

    public final BookmarkId m() {
        Object obj = ThreadUtils.a;
        long j = this.a;
        if (j == 0) {
            return null;
        }
        return (BookmarkId) N.MeMSCM3N(j, this);
    }

    public final BookmarkId n() {
        Object obj = ThreadUtils.a;
        long j = this.a;
        if (j == 0) {
            return null;
        }
        return (BookmarkId) N.MTVYsNWF(j, this);
    }

    public final ArrayList o(boolean z) {
        Object obj = ThreadUtils.a;
        if (this.a == 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        N.MOEaKJZM(this.a, this, true, z, arrayList);
        return arrayList;
    }

    public final BookmarkId p(Tab tab) {
        Object obj = ThreadUtils.a;
        if (this.a != 0 && tab != null && !tab.isFrozen()) {
            long j = this.a;
            if (j != 0) {
                return (BookmarkId) N.MUjtS5c8(j, this, tab.c(), true);
            }
        }
        return null;
    }

    public final boolean q() {
        Object obj = ThreadUtils.a;
        long j = this.a;
        if (j == 0) {
            return false;
        }
        return N.M9xtlU8J(j);
    }

    public final void r(mg mgVar) {
        this.d.c(mgVar);
    }

    public final ArrayList s(String str, int i, int i2) {
        int i3;
        Object obj = ThreadUtils.a;
        if (this.a == 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            i3 = -1;
        } else {
            C0173a.e(i);
            i3 = 1;
        }
        N.MUcwMevE(this.a, this, arrayList, str, null, i3, i2);
        return arrayList;
    }

    public final void t(GURL gurl, boolean z) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.Mj0PtWvo(j, this, gurl, z);
    }
}
