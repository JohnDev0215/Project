package defpackage;

import android.os.Handler;
import android.util.Size;
import android.widget.OverScroller;

/* JADX INFO: compiled from: PlayerFrameScrollController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c22 {
    public qt1 a;
    public boolean b;
    public float c;
    public final OverScroller d;
    public final e22 f;
    public final Size g;
    public final z12 h;
    public final Runnable i;
    public final Runnable j;
    public Runnable l;
    public final Handler e = new Handler();
    public boolean k = true;

    public c22(OverScroller overScroller, y12 y12Var, ve3 ve3Var, i22 i22Var) {
        this.d = overScroller;
        this.f = y12Var.i;
        this.g = y12Var.b;
        this.h = y12Var;
        this.i = ve3Var;
        this.j = i22Var;
    }

    public static void a(c22 c22Var) {
        OverScroller overScroller = c22Var.d;
        if (overScroller.isFinished()) {
            return;
        }
        boolean zComputeScrollOffset = overScroller.computeScrollOffset();
        int currX = overScroller.getCurrX();
        e22 e22Var = c22Var.f;
        c22Var.b(currX - Math.round(e22Var.c()), overScroller.getCurrY() - Math.round(e22Var.d()));
        if (zComputeScrollOffset) {
            c22Var.e.post(new an2(5, c22Var));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ba A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(float r11, float r12) {
        /*
            Method dump skipped, instruction units count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c22.b(float, float):boolean");
    }
}
