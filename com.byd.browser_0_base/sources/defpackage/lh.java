package defpackage;

import J.N;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.app.bookmarks.BookmarkActivity;
import org.chromium.chrome.browser.app.bookmarks.BookmarkEditActivity;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.bookmark_history.BookmarkHistoryActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.url.GURL;
import vg.a;

/* JADX INFO: compiled from: BookmarkUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class lh {
    public static final int a = (int) TimeUnit.HOURS.toMillis(1);

    public static BookmarkId a(Activity activity, BookmarkModel bookmarkModel, String str, GURL gurl, BookmarkId bookmarkId, int i) {
        String str2;
        if (bookmarkId == null) {
            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
            bookmarkId = !sharedPreferencesManager.contains("enhanced_bookmark_last_used_parent_folder") ? null : BookmarkId.a(sharedPreferencesManager.g("enhanced_bookmark_last_used_parent_folder", null));
        }
        BookmarkItem bookmarkItemH = bookmarkId != null ? bookmarkModel.h(bookmarkId) : null;
        if (bookmarkId == null || bookmarkItemH == null || bookmarkItemH.g || !bookmarkItemH.d) {
            bookmarkId = bookmarkModel.k();
        }
        if (i == 2 || bookmarkId.getType() == 2) {
            return bookmarkModel.d(str, gurl);
        }
        if (gurl.j().equals("chrome-native://newtab/")) {
            str = activity.getResources().getString(R$string.new_tab_title);
        }
        BookmarkId bookmarkIdA = bookmarkModel.a(bookmarkId, bookmarkModel.i(bookmarkId), str, gurl);
        if (bookmarkIdA == null) {
            Object[] objArr = new Object[4];
            objArr[0] = bookmarkId;
            objArr[1] = bookmarkModel.k();
            objArr[2] = bookmarkModel.k();
            if (bookmarkItemH == null) {
                str2 = "null";
            } else {
                str2 = bookmarkItemH.a() + " " + bookmarkItemH.g + " " + bookmarkItemH.d;
            }
            objArr[3] = str2;
            fa1.d("BookmarkUtils", "Failed to add bookmarks: parentTypeAndId %s, defaultFolderTypeAndId %s, mobileFolderTypeAndId %s, parentEditable Managed isFolder %s,", objArr);
            SharedPreferencesManager.getInstance().o("enhanced_bookmark_last_used_parent_folder", bookmarkModel.k().toString());
        }
        return bookmarkIdA;
    }

    public static Drawable b(Context context) {
        int i = R$drawable.bookmark_folder;
        int iA = kp2.a();
        HashMap map = uh3.a;
        return uh3.f(context, i, bz.b(context, iA));
    }

    public static boolean c(BookmarkItem bookmarkItem) {
        return (bookmarkItem.c.getType() == 2) || bookmarkItem.b();
    }

    public static void d(ChromeTabbedActivity chromeTabbedActivity, BookmarkId bookmarkId, boolean z) {
        String string;
        Object obj = ThreadUtils.a;
        Context context = chromeTabbedActivity == null ? xz.a : chromeTabbedActivity;
        if (bookmarkId == null) {
            string = SharedPreferencesManager.getInstance().g("enhanced_bookmark_last_used_url", "chrome-native://bookmarks/");
        } else {
            Uri.Builder builderBuildUpon = Uri.parse("chrome-native://bookmarks/folder/").buildUpon();
            builderBuildUpon.appendPath(bookmarkId.toString());
            string = builderBuildUpon.build().toString();
        }
        String str = TextUtils.isEmpty(string) ? "chrome-native://bookmarks/" : string;
        if (SharedPreferencesManager.getInstance().contains("enhanced_bookmark_last_used_url")) {
            n3.X0("MobileBookmarkManagerReopenBookmarksInSameSession");
        }
        if (DeviceFormFactor.a(context)) {
            ComponentName componentName = chromeTabbedActivity == null ? null : chromeTabbedActivity.getComponentName();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.putExtra("org.chromium.chrome.browser.incognito_mode", z);
            intent.putExtra("com.android.browser.application_id", context.getApplicationContext().getPackageName());
            intent.setFlags(268435456);
            if (componentName != null) {
                z7.g0(intent, componentName);
            } else {
                intent.setClass(context.getApplicationContext(), ChromeLauncherActivity.class);
            }
            b.A(null, intent, null);
            return;
        }
        Intent intent2 = new Intent(chromeTabbedActivity == null ? xz.a : chromeTabbedActivity, (Class<?>) BookmarkActivity.class);
        intent2.putExtra("org.chromium.chrome.browser.incognito_mode", z);
        intent2.setData(Uri.parse(str));
        if (chromeTabbedActivity != null) {
            intent2.putExtra("org.chromium.chrome.browser.parent_component", chromeTabbedActivity.getComponentName());
            chromeTabbedActivity.startActivity(intent2);
        } else {
            intent2.addFlags(268435456);
            b.A(null, intent2, null);
        }
    }

    public static void e(Activity activity, ji jiVar, final boolean z, final BookmarkId bookmarkId, final boolean z2, final boolean z3) {
        if (bookmarkId == null) {
            Log.e("cr_BookmarkUtils", "Null bookmark found when showing the save flow, aborting.");
            return;
        }
        Profile profileD = Profile.d();
        final vg vgVar = new vg(activity, jiVar, n3.Y(profileD), new pk3(activity, new Handler()), profileD);
        vgVar.i.g(new Runnable() { // from class: sg
            @Override // java.lang.Runnable
            public final void run() {
                vg vgVar2 = vgVar;
                BookmarkModel bookmarkModel = vgVar2.i;
                BookmarkId bookmarkId2 = bookmarkId;
                c32 c32VarL = bookmarkModel.l(bookmarkId2);
                vgVar2.c.getClass();
                vgVar2.f = vgVar2.new a(vgVar2.h);
                xg xgVar = vgVar2.g;
                xgVar.getClass();
                n3.X0("MobileBookmark.SaveFlow.Show");
                xgVar.j = bookmarkId2;
                xgVar.k = c32VarL;
                xgVar.l = z2;
                xgVar.m = z3;
                xgVar.h.p(yg.a, new ie0(4, xgVar));
                xgVar.h.p(yg.d, new by1(6, xgVar));
                if (c32VarL != null) {
                    xgVar.o = e32.a(c32VarL);
                }
                xgVar.n(xgVar.j, xgVar.l);
                c32 c32Var = xgVar.k;
                if (c32Var != null && c32Var.Q()) {
                    xgVar.q(true);
                    xgVar.p(false);
                    xgVar.h.l(yg.j, true);
                    xgVar.h.p(yg.g, xgVar.e.getResources().getString(R$string.enable_price_tracking_menu_item));
                    xgVar.h.p(yg.i, new wg(1, xgVar));
                    if (z) {
                        xgVar.h.l(yg.h, true);
                    }
                    oj2.U(0, 3, "PowerBookmarks.BookmarkSaveFlow.PriceTrackingEnabled");
                }
                boolean zD = vgVar2.e.d(vgVar2.f, true);
                if (!((AccessibilityManager) vgVar2.a.getSystemService("accessibility")).isTouchExplorationEnabled()) {
                    PostTask.b(rh3.c, new wq0(14, vgVar2), 6000L);
                }
                if (oj2.B()) {
                    N.Mkvg2cg$(vgVar2.d, bookmarkId2.getId(), new tg(vgVar2, 0, zD));
                }
            }
        });
    }

    public static void f(Context context, BookmarkId bookmarkId) {
        n3.X0("MobileBookmarkManagerEditBookmark");
        Intent intent = new Intent(context, (Class<?>) BookmarkEditActivity.class);
        intent.putExtra("BookmarkEditActivity.BookmarkId", bookmarkId.toString());
        if ((context instanceof BookmarkActivity) || (context instanceof BookmarkHistoryActivity)) {
            ((Activity) context).startActivityForResult(intent, 14);
        } else {
            context.startActivity(intent);
        }
    }
}
