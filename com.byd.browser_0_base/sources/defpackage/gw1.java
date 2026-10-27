package defpackage;

import android.os.ResultReceiver;
import android.view.View;
import defpackage.w01;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gw1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ gw1(Object obj, int i, Object obj2, Object obj3, int i2) {
        this.e = i2;
        this.g = obj;
        this.h = obj2;
        this.f = i;
        this.i = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        int i2 = this.f;
        Object obj = this.i;
        Object obj2 = this.h;
        Object obj3 = this.g;
        switch (i) {
            case 0:
                ((hw1) obj3).a.e((View) obj2, i2, (ResultReceiver) obj);
                break;
            default:
                ((w01) obj3).F((w01.b) obj2, i2, (ka2) obj);
                break;
        }
    }
}
