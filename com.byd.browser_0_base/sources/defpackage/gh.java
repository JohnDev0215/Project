package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.components.bookmarks.BookmarkId;

/* JADX INFO: compiled from: BookmarkUiState.java */
/* JADX INFO: loaded from: classes.dex */
public final class gh {
    public final int a;
    public final String b;
    public final BookmarkId c;

    public gh(int i, String str, BookmarkId bookmarkId) {
        this.a = i;
        this.b = str;
        this.c = bookmarkId;
    }

    public static gh a(BookmarkId bookmarkId, BookmarkModel bookmarkModel) {
        BookmarkId bookmarkId2 = BookmarkId.c;
        if (bookmarkId2.equals(bookmarkId)) {
            return new gh(2, "chrome-native://bookmarks/folder//shopping", bookmarkId2);
        }
        Uri.Builder builderBuildUpon = Uri.parse("chrome-native://bookmarks/folder/").buildUpon();
        builderBuildUpon.appendPath(bookmarkId.toString());
        return b(builderBuildUpon.build(), bookmarkModel);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.gh b(android.net.Uri r3, org.chromium.chrome.browser.bookmarks.BookmarkModel r4) {
        /*
            java.lang.String r0 = r3.toString()
            java.lang.String r1 = "chrome-native://bookmarks/"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L15
            org.chromium.components.bookmarks.BookmarkId r3 = r4.n()
            gh r3 = a(r3, r4)
            return r3
        L15:
            java.lang.String r1 = "chrome-native://bookmarks/folder/"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L32
            java.lang.String r3 = r3.getLastPathSegment()
            boolean r1 = r3.isEmpty()
            if (r1 != 0) goto L32
            gh r1 = new gh
            org.chromium.components.bookmarks.BookmarkId r3 = org.chromium.components.bookmarks.BookmarkId.a(r3)
            r2 = 2
            r1.<init>(r2, r0, r3)
            goto L33
        L32:
            r1 = 0
        L33:
            if (r1 == 0) goto L3c
            boolean r3 = r1.c(r4)
            if (r3 == 0) goto L3c
            return r1
        L3c:
            org.chromium.components.bookmarks.BookmarkId r3 = r4.n()
            gh r3 = a(r3, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gh.b(android.net.Uri, org.chromium.chrome.browser.bookmarks.BookmarkModel):gh");
    }

    public final boolean c(BookmarkModel bookmarkModel) {
        int i;
        String str = this.b;
        if (str == null || (i = this.a) == 0) {
            return false;
        }
        if (str.equals("chrome-native://bookmarks/folder//shopping") || i != 2) {
            return true;
        }
        BookmarkId bookmarkId = this.c;
        return bookmarkId != null && bookmarkModel.f(bookmarkId);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof gh)) {
            return false;
        }
        gh ghVar = (gh) obj;
        return this.a == ghVar.a && TextUtils.equals(this.b, ghVar.b);
    }

    public final int hashCode() {
        return (this.b.hashCode() * 31) + this.a;
    }
}
