package defpackage;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.util.Arrays;

/* JADX INFO: compiled from: PhysicalDisplayAndroid.java */
/* JADX INFO: loaded from: classes3.dex */
public final class z02 extends ya0 {
    public static final boolean r;
    public static Float s;
    public final Context p;
    public final a q;

    static {
        r = Build.VERSION.SDK_INT >= 31;
    }

    public z02(Display display) {
        super(display.getDisplayId());
        if (!r) {
            this.p = null;
            this.q = null;
            return;
        }
        Context context = xz.a;
        ku2 ku2VarQ = ku2.q();
        try {
            Context contextCreateWindowContext = context.createWindowContext(display, 2, null);
            this.p = contextCreateWindowContext;
            ku2VarQ.close();
            a aVar = new a();
            this.q = aVar;
            contextCreateWindowContext.registerComponentCallbacks(aVar);
            f();
        } catch (Throwable th) {
            try {
                ku2VarQ.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @Override // defpackage.ya0
    public final Context c() {
        return this.p;
    }

    public final void e(Point point, float f, float f2, float f3, Display display) {
        Float fValueOf;
        Float f4 = s;
        Float fValueOf2 = Float.valueOf(0.0f);
        if (f4 == null) {
            String strF = iv.e().f("force-device-scale-factor");
            if (strF == null) {
                s = fValueOf2;
            } else {
                try {
                    fValueOf = Float.valueOf(strF);
                    s = fValueOf;
                } catch (NumberFormatException unused) {
                }
                boolean z = fValueOf.floatValue() <= 0.0f;
                if (z) {
                    Log.w("cr_DisplayAndroid", "Ignoring invalid forced DIP scale '" + strF + "'");
                    s = fValueOf2;
                }
            }
        }
        float fFloatValue = s.floatValue() > 0.0f ? s.floatValue() : f;
        boolean zIsWideColorGamut = display.isWideColorGamut();
        Display.Mode mode = display.getMode();
        Display.Mode[] supportedModes = display.getSupportedModes();
        d(point, Float.valueOf(fFloatValue), Float.valueOf(f2), Float.valueOf(f3), 24, 8, Integer.valueOf(display.getRotation()), Boolean.valueOf(zIsWideColorGamut), null, Float.valueOf(display.getRefreshRate()), mode, (supportedModes == null || supportedModes.length <= 0) ? null : Arrays.asList(supportedModes));
    }

    public final void f() {
        Point point = new Point();
        Context context = this.p;
        Rect bounds = ((WindowManager) context.getSystemService(WindowManager.class)).getMaximumWindowMetrics().getBounds();
        point.set(bounds.width(), bounds.height());
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        e(point, displayMetrics.density, displayMetrics.xdpi, displayMetrics.ydpi, context.getDisplay());
    }

    public final void g(Display display) {
        if (r) {
            f();
            return;
        }
        Point point = new Point();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getRealSize(point);
        display.getRealMetrics(displayMetrics);
        e(point, displayMetrics.density, displayMetrics.xdpi, displayMetrics.ydpi, display);
    }

    /* JADX INFO: compiled from: PhysicalDisplayAndroid.java */
    public class a implements ComponentCallbacks {
        public a() {
        }

        @Override // android.content.ComponentCallbacks
        public final void onConfigurationChanged(Configuration configuration) {
            z02.this.f();
        }

        @Override // android.content.ComponentCallbacks
        public final void onLowMemory() {
        }
    }
}
