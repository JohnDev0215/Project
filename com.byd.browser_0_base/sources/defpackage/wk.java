package defpackage;

import J.N;
import android.graphics.Bitmap;
import defpackage.ax0;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class wk implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Callback f;
    public final /* synthetic */ ax0.a g;
    public final /* synthetic */ long h;
    public final /* synthetic */ ax0 i;

    public /* synthetic */ wk(ax0 ax0Var, Callback callback, ax0.a aVar, long j, int i) {
        this.e = i;
        this.i = ax0Var;
        this.f = callback;
        this.g = aVar;
        this.h = j;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        long j = this.h;
        ax0.a aVar = this.g;
        Callback callback = this.f;
        ax0 ax0Var = this.i;
        switch (i) {
            case 0:
                al alVar = (al) ax0Var;
                alVar.getClass();
                callback.onResult((od) obj);
                String str = aVar.b;
                alVar.a.getClass();
                N.MtnQwbxo(str, j);
                break;
            default:
                wl1 wl1Var = (wl1) ax0Var;
                wl1Var.getClass();
                callback.onResult((Bitmap) obj);
                String str2 = aVar.b;
                wl1Var.a.getClass();
                N.MtnQwbxo(str2, j);
                break;
        }
    }
}
