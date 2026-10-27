package defpackage;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: PartnerBookmarksProviderIterator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tw1 implements Iterator {
    public static final Uri f = new Uri.Builder().scheme("content").authority("com.android.partnerbookmarks").build().buildUpon().appendPath("bookmarks").build();
    public static final String[] g = {"_id", "url", "title", "type", "parent", "favicon", "touchicon"};
    public final Cursor e;

    public tw1(Cursor cursor) {
        this.e = cursor;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        Cursor cursor = this.e;
        if (cursor != null) {
            return (cursor.getCount() <= 0 || cursor.isLast() || cursor.isAfterLast()) ? false : true;
        }
        throw new IllegalStateException();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Cursor cursor = this.e;
        if (cursor == null) {
            throw new IllegalStateException();
        }
        if (!cursor.moveToNext()) {
            throw new NoSuchElementException();
        }
        rw1 rw1Var = new rw1();
        try {
            long j = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
            rw1Var.a = j;
            if (j == 0) {
                Log.i("cr_PartnerBookmarks", "Dropping the bookmark: reserved _id was used");
            } else {
                long j2 = cursor.getLong(cursor.getColumnIndexOrThrow("parent"));
                rw1Var.b = j2;
                if (j2 == 0) {
                    rw1Var.b = 0L;
                }
                rw1Var.c = cursor.getInt(cursor.getColumnIndexOrThrow("type")) == 2;
                rw1Var.d = cursor.getString(cursor.getColumnIndexOrThrow("url"));
                rw1Var.e = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                rw1Var.f = cursor.getBlob(cursor.getColumnIndexOrThrow("favicon"));
                rw1Var.g = cursor.getBlob(cursor.getColumnIndexOrThrow("touchicon"));
                if ((rw1Var.c || rw1Var.d != null) && rw1Var.e != null) {
                    return rw1Var;
                }
                Log.i("cr_PartnerBookmarks", "Dropping the bookmark: no title, or no url on a non-foler");
            }
        } catch (IllegalArgumentException e) {
            Log.i("cr_PartnerBookmarks", "Dropping the bookmark: " + e.getMessage());
        }
        return null;
    }
}
