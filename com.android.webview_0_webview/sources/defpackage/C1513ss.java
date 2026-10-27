package defpackage;

import android.app.Application;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;

/* JADX INFO: renamed from: ss, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1513ss {
    public static Bitmap a(ContentResolver contentResolver, Uri uri) {
        return ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri));
    }

    public static String b() {
        return Application.getProcessName();
    }
}
