package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: BookmarkOpener.java */
/* JADX INFO: loaded from: classes.dex */
public final class ng {
    public final BookmarkModel a;
    public final Context b;
    public final ComponentName c;

    public ng(BookmarkModel bookmarkModel, Context context, ComponentName componentName) {
        this.a = bookmarkModel;
        this.b = context;
        this.c = componentName;
    }

    public static void c(BookmarkItem bookmarkItem, String str) {
        int type = bookmarkItem.c.getType();
        oj2.T(str.concat(type != 0 ? type != 1 ? type != 2 ? "" : "ReadingList" : "Partner" : "Normal"), System.currentTimeMillis() - bookmarkItem.h, 1L, 2592000000L, 50);
    }

    public final Intent a(BookmarkItem bookmarkItem, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(bookmarkItem.b.j()));
        Context context = this.b;
        intent.putExtra("com.android.browser.application_id", context.getApplicationContext().getPackageName());
        intent.setFlags(268435456);
        intent.putExtra("com.google.chrome.transition_type", 2);
        BookmarkId bookmarkId = bookmarkItem.c;
        intent.putExtra("com.google.chrome.transition_bookmark_id", bookmarkId.toString());
        intent.putExtra("org.chromium.chrome.browser.incognito_mode", z);
        ComponentName componentName = this.c;
        if (componentName != null) {
            z7.g0(intent, componentName);
        } else {
            intent.setClass(context.getApplicationContext(), ChromeLauncherActivity.class);
        }
        if (bookmarkId.getType() == 2) {
            intent.putExtra("org.chromium.chrome.browser.tab_launch_type", 18);
            intent.putExtra("create_new_tab", true);
            intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", z);
        }
        q11.a(intent);
        return intent;
    }

    public final void b(ArrayList arrayList, boolean z) {
        BookmarkModel bookmarkModel;
        BookmarkItem bookmarkItemH;
        if (arrayList.size() == 0) {
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList<BookmarkItem> arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        BookmarkItem bookmarkItem = null;
        while (it.hasNext()) {
            BookmarkId bookmarkId = (BookmarkId) it.next();
            if (bookmarkId != null && (bookmarkItemH = (bookmarkModel = this.a).h(bookmarkId)) != null) {
                int type = bookmarkItemH.c.getType();
                GURL gurl = bookmarkItemH.b;
                if (type == 2) {
                    bookmarkModel.t(gurl, true);
                }
                if (bookmarkItem == null) {
                    bookmarkItem = bookmarkItemH;
                } else {
                    arrayList2.add(gurl.j());
                }
                arrayList3.add(bookmarkItemH);
            }
        }
        n3.X0("MobileBookmarkManagerMultipleEntriesOpened");
        for (BookmarkItem bookmarkItem2 : arrayList3) {
            oj2.U(bookmarkItem2.c.getType(), 3, "Bookmarks.MultipleOpened.OpenBookmarkType");
            c(bookmarkItem2, "Bookmarks.MultipleOpened.OpenBookmarkTimeInterval2.");
        }
        Intent intentA = a(bookmarkItem, z);
        intentA.putExtra("create_new_tab", true);
        intentA.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", z);
        intentA.putExtra("org.chromium.chrome.browser.additional_urls", arrayList2);
        b.A(null, intentA, null);
    }
}
