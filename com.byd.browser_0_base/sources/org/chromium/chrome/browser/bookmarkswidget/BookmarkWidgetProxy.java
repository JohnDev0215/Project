package org.chromium.chrome.browser.bookmarkswidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import defpackage.mh;
import defpackage.q11;
import defpackage.xz;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkWidgetProxy extends Activity {
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if ((xz.a.getPackageName() + ".CHANGE_FOLDER").equals(intent.getAction())) {
            int iL = q11.l(intent, "appWidgetId", -1);
            String strR = q11.r(intent, "folderId");
            if (iL >= 0 && strR != null) {
                mh.c(iL).edit().putString("bookmarkswidget.current_folder", strR).apply();
                AppWidgetManager.getInstance(xz.a).notifyAppWidgetViewDataChanged(iL, R$id.bookmarks_list);
            }
        } else {
            Intent intent2 = new Intent(intent);
            intent2.setClass(this, ChromeLauncherActivity.class);
            intent2.putExtra("org.chromium.chrome.browser.webapp_source", 3);
            intent2.putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
            try {
                intent2.addFlags(268435456);
                startActivity(intent2);
            } catch (Exception e) {
                Log.w("BookmarkWidgetProxy", "Failed to start intent activity", e);
            }
        }
        finish();
    }
}
