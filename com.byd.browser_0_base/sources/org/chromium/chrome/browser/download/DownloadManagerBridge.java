package org.chromium.chrome.browser.download;

import android.app.DownloadManager;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import defpackage.d73;
import defpackage.i9;
import defpackage.o40;
import defpackage.xz;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;

/* JADX INFO: loaded from: classes.dex */
public class DownloadManagerBridge {
    public static final Object a = new Object();

    public static class a {
        public int a;
        public String b;
        public String c;
        public Uri d;
        public long e;
        public long f;
        public int g;
        public String h;
    }

    public static class b extends i9<a> {
        public final long h;
        public final Callback<a> i;

        public b(Callback callback, long j) {
            this.h = j;
            this.i = callback;
        }

        @Override // defpackage.i9
        public final a b() {
            return DownloadManagerBridge.a(this.h);
        }

        @Override // defpackage.i9
        public final void l(a aVar) {
            this.i.onResult(aVar);
        }
    }

    public static a a(long j) {
        a aVar = new a();
        DownloadManager downloadManager = (DownloadManager) xz.a.getSystemService("download");
        try {
            Cursor cursorQuery = downloadManager.query(new DownloadManager.Query().setFilterById(j));
            if (cursorQuery == null) {
                aVar.a = 3;
                return aVar;
            }
            aVar.a = 0;
            if (cursorQuery.moveToNext()) {
                int i = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("status"));
                Log.e("cr_DownloadDelegate", "ggy getDownloadStatus ");
                aVar.a = i != 8 ? i != 16 ? 0 : 2 : 1;
                aVar.b = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("title"));
                aVar.g = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("reason"));
                cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("last_modified_timestamp"));
                aVar.e = cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("bytes_so_far"));
                aVar.f = cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("total_size"));
                String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("local_uri"));
                if (!TextUtils.isEmpty(string)) {
                    aVar.h = Uri.parse(string).getPath();
                }
            } else {
                aVar.a = 3;
            }
            cursorQuery.close();
            try {
                aVar.d = downloadManager.getUriForDownloadedFile(j);
            } catch (SecurityException unused) {
                Log.e("cr_DownloadDelegate", "unable to get content URI from DownloadManager");
            }
            aVar.c = downloadManager.getMimeTypeForDownloadedFile(j);
        } catch (Exception e) {
            aVar.a = 3;
            Log.e("cr_DownloadDelegate", "unable to query android DownloadManager", e);
        }
        return aVar;
    }

    @CalledByNative
    public static void addCompletedDownload(String str, String str2, String str3, String str4, long j, String str5, String str6, String str7, long j2) {
        Log.e("cr_DownloadDelegate", "ggy addCompletedDownload ");
    }

    @CalledByNative
    public static void removeCompletedDownload(String str, boolean z) {
        PostTask.c(d73.g, new o40(str, 1, z));
    }
}
