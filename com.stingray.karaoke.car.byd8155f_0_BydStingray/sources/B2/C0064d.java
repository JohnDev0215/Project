package B2;

import android.app.ActivityManager;
import android.content.Context;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

/* JADX INFO: renamed from: B2.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0064d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f555a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f556b;
    public int c;
    public int d;

    public /* synthetic */ C0064d(int i7) {
        this.f555a = i7;
    }

    public String toString() {
        switch (this.f555a) {
            case 4:
                int i7 = this.f556b;
                if (i7 <= 0) {
                    return "";
                }
                return "BreakStartTime: " + this.c + ", BreakDuration: " + i7 + ", ";
            default:
                return super.toString();
        }
    }

    public /* synthetic */ C0064d(int i7, int i10, int i11, int i12) {
        this.f555a = i12;
        this.f556b = i7;
        this.c = i10;
        this.d = i11;
    }

    public C0064d(x6.e eVar) {
        this.f555a = 4;
        A7.m.f(eVar, "timingConfig");
        this.d = eVar.f23812e;
    }

    public C0064d(R3.c cVar) {
        this.f555a = 3;
        Context context = (Context) cVar.f7385m;
        ActivityManager activityManager = (ActivityManager) cVar.f7386n;
        int i7 = activityManager.isLowRamDevice() ? 2097152 : 4194304;
        this.d = i7;
        int iRound = Math.round(activityManager.getMemoryClass() * 1048576 * (activityManager.isLowRamDevice() ? 0.33f : 0.4f));
        DisplayMetrics displayMetrics = (DisplayMetrics) ((e7.f) cVar.o).f14480m;
        float f10 = displayMetrics.widthPixels * displayMetrics.heightPixels * 4;
        float f11 = cVar.l;
        int iRound2 = Math.round(f10 * f11);
        int iRound3 = Math.round(f10 * 2.0f);
        int i10 = iRound - i7;
        int i11 = iRound3 + iRound2;
        if (i11 <= i10) {
            this.c = iRound3;
            this.f556b = iRound2;
        } else {
            float f12 = i10 / (f11 + 2.0f);
            this.c = Math.round(2.0f * f12);
            this.f556b = Math.round(f12 * f11);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            StringBuilder sb = new StringBuilder("Calculation complete, Calculated memory cache size: ");
            sb.append(Formatter.formatFileSize(context, this.c));
            sb.append(", pool size: ");
            sb.append(Formatter.formatFileSize(context, this.f556b));
            sb.append(", byte array size: ");
            sb.append(Formatter.formatFileSize(context, i7));
            sb.append(", memory class limited? ");
            sb.append(i11 > iRound);
            sb.append(", max size: ");
            sb.append(Formatter.formatFileSize(context, iRound));
            sb.append(", memoryClass: ");
            sb.append(activityManager.getMemoryClass());
            sb.append(", isLowMemoryDevice: ");
            sb.append(activityManager.isLowRamDevice());
            Log.d("MemorySizeCalculator", sb.toString());
        }
    }
}
