package defpackage;

import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

/* JADX INFO: renamed from: kN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1049kN {
    public final Uri a;
    public final ContentValues b;

    public C1049kN(Uri uri, String str, String str2) {
        uri.getClass();
        this.a = uri;
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        ContentValues contentValues = new ContentValues();
        this.b = contentValues;
        str.getClass();
        contentValues.put("_display_name", str);
        ContentValues contentValues2 = this.b;
        str2.getClass();
        contentValues2.put("mime_type", str2);
        this.b.put("date_added", Long.valueOf(jCurrentTimeMillis));
        this.b.put("date_modified", Long.valueOf(jCurrentTimeMillis));
        try {
            this.b.put("is_pending", (Integer) 1);
        } catch (Exception e) {
            Log.e("MediaStoreUtils", "Unable to set pending content values.", e);
        }
    }
}
