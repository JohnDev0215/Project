package p;

import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public final class rp extends sp {
    public final /* synthetic */ int c;
    public final Object e;

    public rp(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // p.sp
    public final void a(Throwable th) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                if (th != null) {
                    ((Future) obj).cancel(false);
                }
                break;
            case 1:
                ((oa0) obj).dispose();
                break;
            default:
                ((ry0) obj).invoke(th);
                break;
        }
    }

    @Override // p.ry0
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        lq2 lq2Var = lq2.a;
        switch (this.c) {
            case 0:
                a((Throwable) obj);
                break;
            case 1:
                a((Throwable) obj);
                break;
            default:
                a((Throwable) obj);
                break;
        }
        return lq2Var;
    }

    public final String toString() {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                return "CancelFutureOnCancel[" + ((Future) obj) + ']';
            case 1:
                return "DisposeOnCancel[" + ((oa0) obj) + ']';
            default:
                return "InvokeOnCancel[" + ((ry0) obj).getClass().getSimpleName() + '@' + f60.s(this) + ']';
        }
    }
}
