package org.chromium.components.bookmarks;

import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public final class BookmarkItem {
    public final String a;
    public final GURL b;
    public final BookmarkId c;
    public final boolean d;
    public final BookmarkId e;
    public final boolean f;
    public final boolean g;
    public final long h;
    public final boolean i;

    public BookmarkItem(BookmarkId bookmarkId, String str, GURL gurl, boolean z, BookmarkId bookmarkId2, boolean z2, boolean z3, long j, boolean z4) {
        this.c = bookmarkId;
        this.a = str;
        this.b = gurl;
        this.d = z;
        this.e = bookmarkId2;
        this.f = z2;
        this.g = z3;
        this.h = j;
        this.i = z4;
    }

    public final boolean a() {
        return this.f;
    }

    public final boolean b() {
        return a() && this.c.getType() == 0;
    }
}
