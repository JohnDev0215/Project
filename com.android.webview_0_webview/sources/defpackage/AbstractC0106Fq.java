package defpackage;

import android.content.ComponentName;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: Fq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0106Fq {
    public static Map a(String str) {
        HashMap map = new HashMap();
        Cursor cursorQuery = Is.a.getContentResolver().query(new Uri.Builder().scheme("content").authority(str + ".DeveloperModeContentProvider").path("/flag-overrides").build(), null, null, null, null);
        try {
            int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("flagName");
            int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("flagState");
            while (cursorQuery.moveToNext()) {
                map.put(cursorQuery.getString(columnIndexOrThrow), Boolean.valueOf(cursorQuery.getInt(columnIndexOrThrow2) != 0));
            }
            cursorQuery.close();
            return map;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    AbstractC0279Pk.a.a(th, th2);
                }
            }
            throw th;
        }
    }

    public static boolean b(String str) {
        return Is.a.getPackageManager().getComponentEnabledSetting(new ComponentName(str, "org.chromium.android_webview.devui.DeveloperModeState")) == 1;
    }
}
