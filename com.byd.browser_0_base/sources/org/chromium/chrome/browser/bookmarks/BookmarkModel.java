package org.chromium.chrome.browser.bookmarks;

import J.N;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: loaded from: classes.dex */
public final class BookmarkModel extends BookmarkBridge {
    public final b<a> e;

    public interface a {
        void a(String[] strArr);
    }

    public BookmarkModel(long j) {
        super(j);
        this.e = new b<>();
    }

    public static final BookmarkModel v(Profile profile) {
        Object obj = ThreadUtils.a;
        return (BookmarkModel) N.MygU7Vrn(profile);
    }

    public final void u(BookmarkId... bookmarkIdArr) {
        ArrayList arrayList = new ArrayList();
        Object obj = ThreadUtils.a;
        long j = this.a;
        if (j != 0) {
            N.MBJyw2pU(j, this);
        }
        for (BookmarkId bookmarkId : bookmarkIdArr) {
            BookmarkItem bookmarkItemH = h(bookmarkId);
            if (bookmarkItemH != null) {
                arrayList.add(bookmarkItemH.a);
                e(bookmarkId);
            }
        }
        Object obj2 = ThreadUtils.a;
        long j2 = this.a;
        if (j2 != 0) {
            N.MIekL1sa(j2, this);
        }
        Iterator<a> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a((String[]) arrayList.toArray(new String[arrayList.size()]));
            }
        }
    }

    public final void w(ArrayList arrayList, BookmarkId bookmarkId) {
        int i = i(bookmarkId);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            BookmarkId bookmarkId2 = (BookmarkId) arrayList.get(i2);
            int i3 = i + i2;
            Object obj = ThreadUtils.a;
            long j = this.a;
            if (j != 0) {
                N.MfKsAC2S(j, this, bookmarkId2, bookmarkId, i3);
            }
        }
    }
}
