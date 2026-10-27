package com.android.car.developeroptions.wifi.qrcode;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/* JADX INFO: loaded from: classes.dex */
public final class QrCodeGenerator {
    public static Bitmap encodeQrCode(String str, int i) throws WriterException, IllegalArgumentException {
        BitMatrix bitMatrixEncode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, i, i);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.RGB_565);
        for (int i2 = 0; i2 < i; i2++) {
            for (int i3 = 0; i3 < i; i3++) {
                bitmapCreateBitmap.setPixel(i2, i3, bitMatrixEncode.get(i2, i3) ? -16777216 : -1);
            }
        }
        return bitmapCreateBitmap;
    }
}
