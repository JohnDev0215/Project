package org.chromium.components.bookmarks;

import android.text.TextUtils;
import android.util.Log;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class BookmarkId {
    public static final BookmarkId c = new BookmarkId(0, -3);
    public final long a;
    public final int b;

    public BookmarkId(int i, long j) {
        this.a = j;
        this.b = i;
    }

    public static BookmarkId a(String str) {
        long j = -1;
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return new BookmarkId(0, -1L);
        }
        char cCharAt = str.charAt(0);
        if (cCharAt == 'p' || cCharAt == 'r') {
            if (cCharAt == 'p') {
                i = 1;
            } else if (cCharAt == 'r') {
                i = 2;
            }
            str = str.substring(1);
        }
        try {
            j = Long.parseLong(str);
        } catch (NumberFormatException e) {
            Log.e("BookmarkId", "Error parsing url to extract the bookmark folder id.", e);
        }
        return new BookmarkId(i, j);
    }

    @CalledByNative
    public static BookmarkId createBookmarkId(long j, int i) {
        return new BookmarkId(i, j);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof BookmarkId)) {
            return false;
        }
        BookmarkId bookmarkId = (BookmarkId) obj;
        return bookmarkId.a == this.a && bookmarkId.b == this.b;
    }

    @CalledByNative
    public long getId() {
        return this.a;
    }

    @CalledByNative
    public int getType() {
        return this.b;
    }

    public final int hashCode() {
        return toString().hashCode();
    }

    public final String toString() {
        int i = this.b;
        return (i != 1 ? i != 2 ? "" : String.valueOf('r') : String.valueOf('p')) + this.a;
    }
}
