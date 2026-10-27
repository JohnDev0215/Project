package defpackage;

import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: compiled from: BookmarkListEntry.java */
/* JADX INFO: loaded from: classes.dex */
public final class yf {
    public final int a;
    public final BookmarkItem b;
    public final a c;

    /* JADX INFO: compiled from: BookmarkListEntry.java */
    public static final class a {
        public final CharSequence a;
        public final int b;

        public a(int i, String str) {
            this.a = str;
            this.b = i;
        }
    }

    public yf(int i, BookmarkItem bookmarkItem, a aVar) {
        this.a = i;
        this.b = bookmarkItem;
        this.c = aVar;
    }

    public static yf a(BookmarkItem bookmarkItem, c32 c32Var) {
        int i = bookmarkItem.d ? 3 : 4;
        if (c32Var != null && c32Var.Q()) {
            i = 7;
        }
        return new yf(i, bookmarkItem, null);
    }
}
