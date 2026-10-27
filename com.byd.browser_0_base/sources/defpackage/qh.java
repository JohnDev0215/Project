package defpackage;

import java.util.Comparator;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: compiled from: BookmarkWidgetServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class qh implements Comparator<BookmarkItem> {
    @Override // java.util.Comparator
    public final int compare(BookmarkItem bookmarkItem, BookmarkItem bookmarkItem2) {
        boolean z = bookmarkItem.d;
        if (z == bookmarkItem2.d) {
            return 0;
        }
        return z ? -1 : 1;
    }
}
