package defpackage;

import J.N;
import android.graphics.Bitmap;
import defpackage.ax0;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xo1 implements Callback {
    public final /* synthetic */ int e = 1;
    public final /* synthetic */ long f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ xo1(long j, Callback callback, al alVar, ax0.a aVar) {
        this.g = alVar;
        this.h = callback;
        this.i = aVar;
        this.f = j;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        Object obj2 = this.i;
        Object obj3 = this.h;
        Object obj4 = this.g;
        switch (i) {
            case 0:
                dp1 dp1Var = (dp1) obj4;
                Integer num = (Integer) obj;
                dp1Var.getClass();
                oj2.U(num.intValue(), 6, "WebApk.Notification.PermissionRequestResult");
                dp1Var.a((ft1) obj3, this.f, (String) obj2, num.intValue());
                break;
            default:
                al alVar = (al) obj4;
                alVar.getClass();
                ((Callback) obj3).onResult((Bitmap) obj);
                String str = ((ax0.a) obj2).b;
                alVar.a.getClass();
                N.MtnQwbxo(str, this.f);
                break;
        }
    }

    public /* synthetic */ xo1(dp1 dp1Var, ft1 ft1Var, long j, String str) {
        this.g = dp1Var;
        this.h = ft1Var;
        this.f = j;
        this.i = str;
    }
}
