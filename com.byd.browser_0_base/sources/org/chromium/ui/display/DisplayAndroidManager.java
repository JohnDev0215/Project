package org.chromium.ui.display;

import J.N;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManager;
import defpackage.xz;
import defpackage.ya0;
import defpackage.z02;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class DisplayAndroidManager {
    public static DisplayAndroidManager e;
    public long a;
    public int b;
    public final SparseArray<ya0> c = new SparseArray<>();
    public final a d = new a();

    public static Display b(Context context) {
        Display display;
        if (Build.VERSION.SDK_INT < 30) {
            return ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        }
        try {
            display = context.getDisplay();
        } catch (UnsupportedOperationException unused) {
            display = null;
        }
        return display != null ? display : ((DisplayManager) xz.a.getSystemService("display")).getDisplay(0);
    }

    public static DisplayAndroidManager c() {
        Object obj = ThreadUtils.a;
        if (e == null) {
            DisplayAndroidManager displayAndroidManager = new DisplayAndroidManager();
            e = displayAndroidManager;
            Display display = ((DisplayManager) xz.a.getSystemService("display")).getDisplay(0);
            if (display == null) {
                display = ((WindowManager) xz.a.getSystemService("window")).getDefaultDisplay();
            }
            displayAndroidManager.b = display.getDisplayId();
            displayAndroidManager.a(display);
            a aVar = displayAndroidManager.d;
            aVar.getClass();
            ((DisplayManager) xz.a.getSystemService("display")).registerDisplayListener(aVar, null);
        }
        return e;
    }

    @CalledByNative
    public static void onNativeSideCreated(long j) {
        DisplayAndroidManager displayAndroidManagerC = c();
        displayAndroidManagerC.a = j;
        N.MdOwtyr6(j, displayAndroidManagerC, displayAndroidManagerC.b);
        int i = 0;
        while (true) {
            SparseArray<ya0> sparseArray = displayAndroidManagerC.c;
            if (i >= sparseArray.size()) {
                return;
            }
            displayAndroidManagerC.d(sparseArray.valueAt(i));
            i++;
        }
    }

    public final z02 a(Display display) {
        int displayId = display.getDisplayId();
        z02 z02Var = new z02(display);
        this.c.put(displayId, z02Var);
        z02Var.g(display);
        return z02Var;
    }

    public final void d(ya0 ya0Var) {
        int i;
        int i2;
        long j = this.a;
        if (j == 0) {
            return;
        }
        int i3 = ya0Var.b;
        Point point = ya0Var.c;
        int i4 = point.x;
        int i5 = point.y;
        float f = ya0Var.d;
        int i6 = ya0Var.i;
        if (i6 == 0) {
            i = 0;
        } else {
            if (i6 == 1) {
                i2 = 90;
            } else if (i6 != 2) {
                if (i6 == 3) {
                    i2 = 270;
                }
                i = 0;
            } else {
                i2 = 180;
            }
            i = i2;
        }
        N.M2$ANfTC(j, this, i3, i4, i5, f, i, ya0Var.g, ya0Var.h, ya0Var.m && ya0Var.n);
    }

    public class a implements DisplayManager.DisplayListener {
        public a() {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayChanged(int i) {
            z02 z02Var = (z02) DisplayAndroidManager.this.c.get(i);
            Display display = ((DisplayManager) xz.a.getSystemService("display")).getDisplay(i);
            if (z02Var == null || display == null) {
                return;
            }
            z02Var.g(display);
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayRemoved(int i) {
            DisplayAndroidManager displayAndroidManager = DisplayAndroidManager.this;
            if (i == displayAndroidManager.b) {
                return;
            }
            SparseArray<ya0> sparseArray = displayAndroidManager.c;
            z02 z02Var = (z02) sparseArray.get(i);
            if (z02Var == null) {
                return;
            }
            if (z02.r) {
                z02Var.p.unregisterComponentCallbacks(z02Var.q);
            }
            long j = displayAndroidManager.a;
            if (j != 0) {
                N.MyzQIqd_(j, displayAndroidManager, i);
            }
            sparseArray.remove(i);
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayAdded(int i) {
        }
    }
}
