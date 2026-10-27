package com.byd.share;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes2.dex */
public class b {
    public static Bitmap a(String str, int i) {
        return b(str, 400, 400, "UTF-8", "H", "1", i, -1);
    }

    public static Bitmap b(String str, int i, int i2, String str2, String str3, String str4, int i3, int i4) {
        if (!TextUtils.isEmpty(str) && i >= 0 && i2 >= 0) {
            try {
                Hashtable hashtable = new Hashtable();
                if (!TextUtils.isEmpty(str2)) {
                    hashtable.put(EncodeHintType.CHARACTER_SET, str2);
                }
                if (!TextUtils.isEmpty(str3)) {
                    hashtable.put(EncodeHintType.ERROR_CORRECTION, str3);
                }
                if (!TextUtils.isEmpty(str4)) {
                    hashtable.put(EncodeHintType.MARGIN, str4);
                }
                BitMatrix bitMatrixEncode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, hashtable);
                int[] iArr = new int[i * i2];
                for (int i5 = 0; i5 < i2; i5++) {
                    for (int i6 = 0; i6 < i; i6++) {
                        if (bitMatrixEncode.get(i6, i5)) {
                            iArr[(i5 * i) + i6] = i3;
                        } else {
                            iArr[(i5 * i) + i6] = i4;
                        }
                    }
                }
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
                return bitmapCreateBitmap;
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
