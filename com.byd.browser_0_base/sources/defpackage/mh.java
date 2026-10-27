package defpackage;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import defpackage.fy2;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.base.SplitCompatRemoteViewsService;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: BookmarkWidgetServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class mh extends SplitCompatRemoteViewsService.a {

    /* JADX INFO: compiled from: BookmarkWidgetServiceImpl.java */
    public static class a {
        public String a;
        public GURL b;
        public BookmarkId c;
        public BookmarkId d;
        public boolean e;
        public Bitmap f;

        public static a a(BookmarkItem bookmarkItem) {
            if (bookmarkItem == null) {
                return null;
            }
            a aVar = new a();
            aVar.a = bookmarkItem.a;
            aVar.b = bookmarkItem.b;
            aVar.c = bookmarkItem.c;
            aVar.d = bookmarkItem.e;
            aVar.e = bookmarkItem.d;
            return aVar;
        }
    }

    /* JADX INFO: compiled from: BookmarkWidgetServiceImpl.java */
    public static class c {
        public a a;
        public a b;
        public final ArrayList c = new ArrayList();
    }

    /* JADX INFO: compiled from: BookmarkWidgetServiceImpl.java */
    public static class d {
        public e a;
        public c b;
        public BookmarkModel c;
        public LargeIconBridge d;
        public uc2 e;
        public int f;
        public int g;
        public int h;
    }

    /* JADX INFO: compiled from: BookmarkWidgetServiceImpl.java */
    public interface e {
    }

    public static void b(int i) {
        SharedPreferences sharedPreferencesC = c(i);
        if (sharedPreferencesC != null) {
            sharedPreferencesC.edit().clear().apply();
        }
    }

    public static SharedPreferences c(int i) {
        return xz.a.getSharedPreferences(String.format(Locale.US, "widgetState-%d", Integer.valueOf(i)), 0);
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatRemoteViewsService.a
    public final b a(Intent intent) {
        int iL = q11.l(intent, "appWidgetId", -1);
        if (iL >= 0) {
            return new b(this.a, iL);
        }
        Log.w("cr_BookmarkWidget", "Missing EXTRA_APPWIDGET_ID!");
        return null;
    }

    /* JADX INFO: compiled from: BookmarkWidgetServiceImpl.java */
    public static class b implements RemoteViewsService.RemoteViewsFactory, fy2.a {
        public final Context e;
        public final int f;
        public final SharedPreferences g;
        public int h;
        public c i;

        public b(SplitCompatRemoteViewsService splitCompatRemoteViewsService, int i) {
            this.e = splitCompatRemoteViewsService;
            this.f = i;
            this.g = mh.c(i);
            this.h = splitCompatRemoteViewsService.getColor(R$color.default_icon_color_baseline);
            fy2.a().a.a(this);
        }

        @Override // fy2.a
        public final void a() {
            this.h = this.e.getColor(R$color.default_icon_color_baseline);
            AppWidgetManager.getInstance(xz.a).notifyAppWidgetViewDataChanged(this.f, R$id.bookmarks_list);
        }

        public final a b(int i) {
            c cVar = this.i;
            if (cVar == null) {
                return null;
            }
            if (cVar.b != null) {
                if (i == 0) {
                    return cVar.a;
                }
                i--;
            }
            if (cVar.c.size() <= i) {
                return null;
            }
            return (a) this.i.c.get(i);
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public final int getCount() {
            if (this.i == null || !this.g.getString("bookmarkswidget.current_folder", "").equals(this.i.a.c.toString())) {
                PostTask.d(rh3.a, new wq0(15, this));
            }
            c cVar = this.i;
            if (cVar == null) {
                return 0;
            }
            return cVar.c.size() + (this.i.b != null ? 1 : 0);
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public final long getItemId(int i) {
            a aVarB = b(i);
            if (aVarB == null) {
                return -2L;
            }
            return aVarB.c.getId();
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public final RemoteViews getLoadingView() {
            return new RemoteViews(this.e.getPackageName(), R$layout.bookmark_widget_item);
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public final RemoteViews getViewAt(int i) {
            Intent data;
            if (this.i == null) {
                Log.w("cr_BookmarkWidget", "No current folder data available.");
                return null;
            }
            a aVarB = b(i);
            if (aVarB == null) {
                fa1.k("BookmarkWidget", "Couldn't get bookmark for position %d", Integer.valueOf(i));
                return null;
            }
            String str = aVarB.a;
            String strJ = aVarB.b.j();
            c cVar = this.i;
            BookmarkId bookmarkId = aVarB == cVar.a ? cVar.b.c : aVarB.c;
            RemoteViews remoteViews = new RemoteViews(this.e.getPackageName(), R$layout.bookmark_widget_item);
            int i2 = R$id.title;
            if (TextUtils.isEmpty(str)) {
                str = strJ;
            }
            remoteViews.setTextViewText(i2, str);
            if (aVarB == this.i.a) {
                int i3 = R$id.favicon;
                remoteViews.setInt(i3, "setColorFilter", this.h);
                remoteViews.setImageViewResource(i3, R$drawable.ic_arrow_back_white_24dp);
            } else if (aVarB.e) {
                int i4 = R$id.favicon;
                remoteViews.setInt(i4, "setColorFilter", this.h);
                remoteViews.setImageViewResource(i4, R$drawable.ic_folder_blue_24dp);
            } else {
                int i5 = R$id.favicon;
                remoteViews.setInt(i5, "setColorFilter", 0);
                remoteViews.setImageViewBitmap(i5, aVarB.f);
            }
            if (aVarB.e) {
                data = new Intent(xz.a.getPackageName() + ".CHANGE_FOLDER").putExtra("appWidgetId", this.f).putExtra("folderId", bookmarkId.toString());
            } else {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.putExtra("com.google.chrome.transition_bookmark_id", bookmarkId.toString());
                data = !TextUtils.isEmpty(strJ) ? intent.addCategory("android.intent.category.BROWSABLE").setData(Uri.parse(strJ)) : intent.addCategory("android.intent.category.LAUNCHER");
            }
            remoteViews.setOnClickFillInIntent(R$id.list_item, data);
            return remoteViews;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public final int getViewTypeCount() {
            return 2;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public final boolean hasStableIds() {
            return false;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public final void onCreate() {
            sp.a().d(false);
            if (this.g.getString("bookmarkswidget.current_folder", null) == null) {
                n3.X0("BookmarkNavigatorWidgetAdded");
            }
            BookmarkModel.v(Profile.d()).c(new a());
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public final void onDataSetChanged() {
            SharedPreferences sharedPreferences = this.g;
            c cVar = null;
            BookmarkId bookmarkIdA = BookmarkId.a(sharedPreferences.getString("bookmarkswidget.current_folder", null));
            LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(1);
            PostTask.d(rh3.a, new nh(this, new d(), bookmarkIdA, linkedBlockingQueue, 0));
            try {
                cVar = (c) linkedBlockingQueue.take();
            } catch (InterruptedException unused) {
            }
            this.i = cVar;
            sharedPreferences.edit().putString("bookmarkswidget.current_folder", this.i.a.c.toString()).apply();
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public final void onDestroy() {
            PostTask.d(rh3.a, new androidx.appcompat.widget.a(15, this));
            mh.b(this.f);
        }

        /* JADX INFO: compiled from: BookmarkWidgetServiceImpl.java */
        public class a extends mg {
            public a() {
            }

            @Override // defpackage.mg
            public final void f() {
                AppWidgetManager.getInstance(xz.a).notifyAppWidgetViewDataChanged(b.this.f, R$id.bookmarks_list);
            }

            @Override // defpackage.mg
            public final void g() {
            }
        }
    }
}
