package defpackage;

import android.graphics.Bitmap;
import android.util.Base64;
import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: BitmapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class we {
    public static String a(Bitmap bitmap) {
        if (bitmap == null) {
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }
}
