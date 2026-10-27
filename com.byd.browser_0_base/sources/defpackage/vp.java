package defpackage;

import android.content.UriMatcher;
import android.database.MatrixCursor;
import android.net.Uri;
import org.chromium.chrome.browser.base.SplitCompatContentProvider;

/* JADX INFO: compiled from: ChromeBrowserProviderImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class vp extends SplitCompatContentProvider.a {
    public static final String[] d = {"_id", "url", "visits", "date", "bookmark", "title", "favicon", "created"};
    public final Object b = new Object();
    public UriMatcher c;

    @Override // org.chromium.chrome.browser.base.SplitCompatContentProvider.a
    public final String b(Uri uri) {
        synchronized (this.b) {
            if (this.c == null) {
                this.c = new UriMatcher(-1);
                String str = this.a.getContext().getPackageName() + ".ChromeBrowserProvider";
                this.c.addURI(str, "bookmarks", 0);
                this.c.addURI(str, "bookmarks/#", 1);
                String str2 = this.a.getContext().getPackageName() + ".browser";
                this.c.addURI(str2, "bookmarks", 2);
                this.c.addURI(str2, "bookmarks/#", 3);
                this.c.addURI(str2, "searches", 4);
                this.c.addURI(str2, "searches/#", 5);
                this.c.addURI(str2, "history", 6);
                this.c.addURI(str2, "history/#", 7);
                this.c.addURI(str2, "combined", 2);
                this.c.addURI(str2, "combined/#", 3);
                this.c.addURI("com.google.android.apps.chrome.browser-contract", "history", 6);
                this.c.addURI("com.google.android.apps.chrome.browser-contract", "history/#", 7);
                this.c.addURI("com.google.android.apps.chrome.browser-contract", "combined", 2);
                this.c.addURI("com.google.android.apps.chrome.browser-contract", "combined/#", 3);
                this.c.addURI("com.google.android.apps.chrome.browser-contract", "searches", 4);
                this.c.addURI("com.google.android.apps.chrome.browser-contract", "searches/#", 5);
                this.c.addURI("com.google.android.apps.chrome.browser-contract", "bookmarks", 8);
                this.c.addURI("com.google.android.apps.chrome.browser-contract", "bookmarks/#", 9);
                this.c.addURI("com.android.browser", "history", 6);
                this.c.addURI("com.android.browser", "history/#", 7);
                this.c.addURI("com.android.browser", "combined", 2);
                this.c.addURI("com.android.browser", "combined/#", 3);
                this.c.addURI("com.android.browser", "searches", 4);
                this.c.addURI("com.android.browser", "searches/#", 5);
                this.c.addURI("com.android.browser", "bookmarks", 8);
                this.c.addURI("com.android.browser", "bookmarks/#", 9);
                this.c.addURI("browser", "bookmarks", 2);
                this.c.addURI("browser", "bookmarks/#", 3);
                this.c.addURI("browser", "searches", 4);
                this.c.addURI("browser", "searches/#", 5);
                this.c.addURI(str2, "bookmarks/search_suggest_query", 10);
                this.c.addURI(str2, "search_suggest_query", 11);
            }
        }
        switch (this.c.match(uri)) {
            case 0:
            case 2:
                return "vnd.android.cursor.dir/bookmark";
            case 1:
            case 3:
                return "vnd.android.cursor.item/bookmark";
            case 4:
                return "vnd.android.cursor.dir/searches";
            case 5:
                return "vnd.android.cursor.item/searches";
            case 6:
                return "vnd.android.cursor.dir/browser-history";
            case 7:
                return "vnd.android.cursor.item/browser-history";
            default:
                throw new IllegalArgumentException("ChromeBrowserProvider: getType - unknown URL " + uri);
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatContentProvider.a
    public final MatrixCursor d() {
        return new MatrixCursor(d, 0);
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatContentProvider.a
    public final void a() {
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatContentProvider.a
    public final void c() {
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatContentProvider.a
    public final void e() {
    }
}
