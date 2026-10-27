package defpackage;

import android.util.Log;
import java.util.ArrayList;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: compiled from: ReadingListUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m92 {
    public static void a(BookmarkModel bookmarkModel, ArrayList arrayList, ArrayList arrayList2, BookmarkId bookmarkId) {
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList.isEmpty()) {
            BookmarkId bookmarkId2 = (BookmarkId) arrayList.remove(0);
            if (bookmarkId2.getType() == bookmarkId.getType()) {
                arrayList3.add(bookmarkId2);
            } else {
                BookmarkItem bookmarkItemH = bookmarkModel.h(bookmarkId2);
                BookmarkId bookmarkIdA = bookmarkId.getType() == 0 ? bookmarkModel.a(bookmarkId, bookmarkModel.i(bookmarkId), bookmarkItemH.a, bookmarkItemH.b) : bookmarkId.getType() == 2 ? bookmarkModel.d(bookmarkItemH.a, bookmarkItemH.b) : null;
                if (bookmarkIdA == null) {
                    Log.e("cr_ReadingListUtils", "Null bookmark after typeswapping.");
                } else {
                    bookmarkModel.e(bookmarkId2);
                    arrayList2.add(bookmarkIdA);
                }
            }
        }
        arrayList.addAll(arrayList3);
    }
}
