package o4;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import java.util.Hashtable;
import r6.b;

/* JADX INFO: compiled from: QrCodeUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static Bitmap a(String str, int i8, int i9) {
        return b(str, i8, i9, "UTF-8", "H", "2", -16777216, -1);
    }

    public static Bitmap b(String str, int i8, int i9, String str2, String str3, String str4, int i10, int i11) {
        if (!TextUtils.isEmpty(str) && i8 >= 0 && i9 >= 0) {
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
                b bVarA = new t6.a().a(str, BarcodeFormat.QR_CODE, i8, i9, hashtable);
                int[] iArr = new int[i8 * i9];
                for (int i12 = 0; i12 < i9; i12++) {
                    for (int i13 = 0; i13 < i8; i13++) {
                        if (bVarA.c(i13, i12)) {
                            iArr[(i12 * i8) + i13] = i10;
                        } else {
                            iArr[(i12 * i8) + i13] = i11;
                        }
                    }
                }
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i8, i9, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap.setPixels(iArr, 0, i8, 0, 0, i8, i9);
                return bitmapCreateBitmap;
            } catch (WriterException e8) {
                e8.printStackTrace();
            }
        }
        return null;
    }
}
