package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: ShareImageFileUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lk2 extends ac<Void> {
    public final /* synthetic */ Context h;
    public final /* synthetic */ Uri i;
    public final /* synthetic */ Callback j;

    public lk2(Activity activity, Uri uri, Callback callback) {
        this.h = activity;
        this.i = uri;
        this.j = callback;
    }

    @Override // defpackage.i9
    public final Object b() {
        Bitmap bitmapDecodeBitmap;
        try {
            bitmapDecodeBitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.h.getContentResolver(), this.i));
            try {
                if (bitmapDecodeBitmap.getConfig() == Bitmap.Config.HARDWARE) {
                    bitmapDecodeBitmap = bitmapDecodeBitmap.copy(Bitmap.Config.ARGB_8888, false);
                }
            } catch (IOException unused) {
            }
        } catch (IOException unused2) {
            bitmapDecodeBitmap = null;
        }
        new Handler(Looper.getMainLooper()).post(new kk2(this, bitmapDecodeBitmap));
        return null;
    }
}
