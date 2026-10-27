package spi;

import android.app.ActivityManager;
import android.content.Context;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.appcompat.widget.ActivityChooserModel;

/* JADX INFO: loaded from: classes.dex */
public final class oi {
    public final int a;
    public final int b;
    public final Context c;
    public final int d;

    public static final class a {
        public final Context a;
        public ActivityManager b;
        public b c;
        public float e;
        public float d = 2.0f;
        public float f = 0.4f;
        public float g = 0.33f;
        public int h = 4194304;

        public a(Context context) {
            this.e = 1;
            this.a = context;
            this.b = (ActivityManager) context.getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
            this.c = new b(context.getResources().getDisplayMetrics());
            if (this.b.isLowRamDevice()) {
                this.e = 0.0f;
            }
        }
    }

    public static final class b {
        public final DisplayMetrics a;

        public b(DisplayMetrics displayMetrics) {
            this.a = displayMetrics;
        }
    }

    public oi(a aVar) {
        this.c = aVar.a;
        int i = aVar.b.isLowRamDevice() ? aVar.h / 2 : aVar.h;
        this.d = i;
        int iRound = Math.round(r1.getMemoryClass() * 1024 * 1024 * (aVar.b.isLowRamDevice() ? aVar.g : aVar.f));
        DisplayMetrics displayMetrics = aVar.c.a;
        float f = displayMetrics.widthPixels * displayMetrics.heightPixels * 4;
        int iRound2 = Math.round(aVar.e * f);
        int iRound3 = Math.round(f * aVar.d);
        int i2 = iRound - i;
        int i3 = iRound3 + iRound2;
        if (i3 <= i2) {
            this.b = iRound3;
            this.a = iRound2;
        } else {
            float f2 = i2;
            float f3 = aVar.e;
            float f4 = aVar.d;
            float f5 = f2 / (f3 + f4);
            this.b = Math.round(f4 * f5);
            this.a = Math.round(f5 * aVar.e);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            StringBuilder sbK = e.k("Calculation complete, Calculated memory cache size: ");
            sbK.append(a(this.b));
            sbK.append(", pool size: ");
            sbK.append(a(this.a));
            sbK.append(", byte array size: ");
            sbK.append(a(i));
            sbK.append(", memory class limited? ");
            sbK.append(i3 > iRound);
            sbK.append(", max size: ");
            sbK.append(a(iRound));
            sbK.append(", memoryClass: ");
            sbK.append(aVar.b.getMemoryClass());
            sbK.append(", isLowMemoryDevice: ");
            sbK.append(aVar.b.isLowRamDevice());
            Log.d("MemorySizeCalculator", sbK.toString());
        }
    }

    public final String a(int i) {
        return Formatter.formatFileSize(this.c, i);
    }
}
