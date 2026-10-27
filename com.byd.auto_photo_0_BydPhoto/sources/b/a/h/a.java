package b.a.h;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import androidx.annotation.RequiresApi;
import com.byd.util.w;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final BitmapFactory.Options f474a = new BitmapFactory.Options();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f475b = Runtime.getRuntime().maxMemory() / 8;

    @RequiresApi(api = 17)
    public static Bitmap a(Context context, Bitmap bitmap, float f, boolean z) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript renderScriptCreate = RenderScript.create(context);
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmap);
        Allocation allocationCreateFromBitmap2 = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateBitmap);
        scriptIntrinsicBlurCreate.setRadius(f);
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap2);
        allocationCreateFromBitmap2.copyTo(bitmapCreateBitmap);
        if (z) {
            bitmap.recycle();
        }
        renderScriptCreate.destroy();
        return bitmapCreateBitmap;
    }

    private static boolean b(BitmapFactory.Options options) {
        int i;
        int i2 = options.outHeight;
        if (i2 == 0 || (i = options.outWidth) == 0) {
            return false;
        }
        return (i > i2 ? i / i2 : i2 / i) > 5;
    }

    private static boolean c(BitmapFactory.Options options, int i, int i2, int i3, boolean z) {
        if (z && b(options)) {
            return false;
        }
        if (i3 > 1) {
            options.inSampleSize = i3;
            return true;
        }
        int i4 = options.outWidth;
        int i5 = options.outHeight;
        if (i == 0) {
            i = i4;
        }
        if (i2 == 0) {
            i2 = i5;
        }
        int iCeil = (int) Math.ceil(i4 / i);
        int iCeil2 = (int) Math.ceil(i5 / i2);
        if (iCeil > 1 || iCeil2 > 1) {
            if (iCeil > iCeil2) {
                options.inSampleSize = iCeil;
            } else {
                options.inSampleSize = iCeil2;
            }
        }
        return true;
    }

    private static BitmapRegionDecoder d(String str) {
        try {
            return BitmapRegionDecoder.newInstance(str, false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BitmapRegionDecoder e(byte[] bArr) {
        try {
            return BitmapRegionDecoder.newInstance(bArr, 0, bArr.length, false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BitmapFactory.Options f(Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (config == null) {
            config = Bitmap.Config.RGB_565;
        }
        options.inPreferredConfig = config;
        options.inJustDecodeBounds = true;
        return options;
    }

    public static Bitmap g(InputStream inputStream, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (i > 1) {
            options.inSampleSize = i;
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    public static Bitmap h(String str, int i, int i2, int i3, Bitmap.Config config, boolean z) {
        Bitmap bitmapL;
        BitmapFactory.Options optionsF = f(config);
        try {
            BitmapFactory.decodeFile(str, optionsF);
            if (!z || c(optionsF, i, i2, i3, true)) {
                bitmapL = null;
            } else {
                w.c("==doLongPicDecode==" + str);
                bitmapL = l(str, i, i2, optionsF);
            }
            if (bitmapL != null) {
                return bitmapL;
            }
            c(optionsF, i, i2, i3, false);
            optionsF.inJustDecodeBounds = false;
            try {
                return BitmapFactory.decodeFile(str, optionsF);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } catch (OutOfMemoryError e2) {
                e2.printStackTrace();
                optionsF.inSampleSize *= 2;
                return BitmapFactory.decodeFile(str, optionsF);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static Bitmap i(byte[] bArr, int i, int i2, int i3, Bitmap.Config config, boolean z) {
        BitmapFactory.Options optionsF = f(config);
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, optionsF);
        Bitmap bitmapM = (!z || c(optionsF, i, i2, i3, true)) ? null : m(bArr, i, i2, optionsF);
        if (bitmapM != null) {
            return bitmapM;
        }
        c(optionsF, i, i2, i3, false);
        optionsF.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, optionsF);
    }

    public static Bitmap j(String str, int i, int i2, int i3, Bitmap.Config config, boolean z) {
        return h(str, i, i2, i3, config, z);
    }

    public static Bitmap k(String str, int i, int i2, Bitmap.Config config, boolean z) {
        return j(str, i, i2, 1, config, z);
    }

    private static Bitmap l(String str, int i, int i2, BitmapFactory.Options options) {
        BitmapRegionDecoder bitmapRegionDecoderD = d(str);
        if (bitmapRegionDecoderD == null) {
            return null;
        }
        return n(bitmapRegionDecoderD, i, i2, options);
    }

    private static Bitmap m(byte[] bArr, int i, int i2, BitmapFactory.Options options) {
        BitmapRegionDecoder bitmapRegionDecoderE = e(bArr);
        if (bitmapRegionDecoderE == null) {
            return null;
        }
        return n(bitmapRegionDecoderE, i, i2, options);
    }

    private static Bitmap n(BitmapRegionDecoder bitmapRegionDecoder, int i, int i2, BitmapFactory.Options options) {
        Bitmap bitmapDecodeRegion;
        int iMin = Math.min(options.outWidth, i);
        int iMin2 = Math.min(options.outHeight, i2);
        int iMax = Math.max(0, (options.outWidth - iMin) / 2);
        int iMax2 = Math.max(0, (options.outHeight - iMin2) / 2);
        Rect rect = new Rect(iMax, iMax2, Math.min(iMin + iMax, options.outWidth), Math.min(iMin2 + iMax2, options.outHeight));
        options.inJustDecodeBounds = false;
        try {
            try {
                bitmapDecodeRegion = bitmapRegionDecoder.decodeRegion(rect, options);
                if (!bitmapRegionDecoder.isRecycled()) {
                    bitmapRegionDecoder.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (!bitmapRegionDecoder.isRecycled()) {
                    bitmapRegionDecoder.recycle();
                }
                bitmapDecodeRegion = null;
            }
            return bitmapDecodeRegion;
        } catch (Throwable th) {
            if (!bitmapRegionDecoder.isRecycled()) {
                bitmapRegionDecoder.recycle();
            }
            throw th;
        }
    }

    public static int o(InputStream inputStream) {
        BitmapFactory.Options options = f474a;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        return q(options);
    }

    public static int p(String str) {
        BitmapFactory.Options options = f474a;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return q(options);
    }

    public static int q(BitmapFactory.Options options) {
        int i = options.outWidth;
        int i2 = options.outHeight;
        long j = i * i2 * 4;
        w.c(options.outWidth + " " + options.outHeight + " total : " + j + " mem : " + f475b);
        int i3 = 1;
        do {
            if (j <= f475b && i <= 4096 && i2 <= 4096) {
                break;
            }
            i3++;
            i = options.outWidth / i3;
            i2 = options.outHeight / i3;
            j = i * i2 * 4;
            if (i == 1) {
                break;
            }
        } while (i2 != 1);
        w.c(i + " " + i2 + " sampleSize : " + i3);
        return i3;
    }

    public static boolean r(Bitmap bitmap, String str, Bitmap.Config config) {
        Bitmap.CompressFormat compressFormat;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                if (config != null) {
                    try {
                        if (config == Bitmap.Config.RGB_565) {
                            if (str.endsWith("png") && !str.endsWith("PNG")) {
                                compressFormat = Bitmap.CompressFormat.JPEG;
                            }
                        }
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.getFD().sync();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.getFD().sync();
                            } catch (Exception e4) {
                                e4.printStackTrace();
                            }
                            try {
                                fileOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } else {
                    compressFormat = str.endsWith("png") ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.PNG;
                }
                bitmap.compress(compressFormat, 100, fileOutputStream2);
                fileOutputStream2.flush();
                try {
                    fileOutputStream2.getFD().sync();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
                try {
                    fileOutputStream2.close();
                    return true;
                } catch (IOException e7) {
                    e7.printStackTrace();
                    return true;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e8) {
            e = e8;
        }
    }
}
