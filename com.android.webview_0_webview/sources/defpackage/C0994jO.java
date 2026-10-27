package defpackage;

import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: renamed from: jO, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0994jO extends AbstractC0827gO {
    public static Float o;
    public final boolean n;

    public C0994jO(Display display) {
        super(display.getDisplayId());
        this.n = display.getName().equals("Desktop");
    }

    public void f(Display display) {
        Display.Mode mode;
        List listAsList;
        Float fValueOf;
        int i = Build.VERSION.SDK_INT;
        Point point = new Point();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getRealSize(point);
        display.getRealMetrics(displayMetrics);
        Float f = o;
        Float fValueOf2 = Float.valueOf(0.0f);
        int i2 = 0;
        if (f == null) {
            String strF = Es.e().f("force-device-scale-factor");
            if (strF == null) {
                o = fValueOf2;
            } else {
                try {
                    fValueOf = Float.valueOf(strF);
                    o = fValueOf;
                } catch (NumberFormatException unused) {
                }
                boolean z = fValueOf.floatValue() <= 0.0f;
                if (z) {
                    Ts.f("DisplayAndroid", Z7.d("Ignoring invalid forced DIP scale '", strF, "'"), new Object[0]);
                    o = fValueOf2;
                }
            }
        }
        if (o.floatValue() > 0.0f) {
            displayMetrics.density = o.floatValue();
        }
        boolean zD = i >= 26 ? C1295ot.d(display) : false;
        if (i >= 23) {
            mode = display.getMode();
            listAsList = Arrays.asList(display.getSupportedModes());
        } else {
            mode = null;
            listAsList = null;
        }
        Float fValueOf3 = Float.valueOf(displayMetrics.density);
        int i3 = 24;
        if (1 != 1) {
            PixelFormat pixelFormat = new PixelFormat();
            PixelFormat.getPixelFormatInfo(1, pixelFormat);
            if (!PixelFormat.formatHasAlpha(1)) {
                i3 = pixelFormat.bitsPerPixel;
            } else if (1 != 1) {
                if (1 == 43) {
                    i3 = 30;
                } else if (1 == 6) {
                    i3 = 15;
                } else if (1 == 7) {
                    i3 = 12;
                }
            }
        }
        Integer numValueOf = Integer.valueOf(i3);
        switch (1) {
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                i2 = 5;
                break;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
            default:
                i2 = 8;
                break;
            case 7:
                i2 = 4;
                break;
            case 8:
            case 9:
            case 10:
                break;
            case 11:
                i2 = 2;
                break;
        }
        super.e(point, fValueOf3, numValueOf, Integer.valueOf(i2), Integer.valueOf(display.getRotation()), Boolean.valueOf(zD), null, Float.valueOf(display.getRefreshRate()), mode, listAsList);
    }
}
