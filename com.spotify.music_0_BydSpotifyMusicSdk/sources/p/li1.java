package p;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
public final class li1 {
    public final double a;
    public final boolean b;
    public final boolean c;

    public li1(Context context) {
        Bitmap.Config[] configArr = i.a;
        double d = 0.2d;
        try {
            Object obj = sz.a;
            Object objB = pz.b(context, ActivityManager.class);
            v03.h(objB);
            if (((ActivityManager) objB).isLowRamDevice()) {
                d = 0.15d;
            }
        } catch (Exception unused) {
        }
        this.a = d;
        this.b = true;
        this.c = true;
    }
}
