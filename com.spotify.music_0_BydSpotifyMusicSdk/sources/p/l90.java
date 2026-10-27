package p;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l90 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Object l;

    public /* synthetic */ l90(int i, int i2, Object obj, Serializable serializable) {
        this.c = i2;
        this.l = obj;
        this.e = i;
        this.k = serializable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Object obj = this.k;
        int i2 = this.e;
        Object obj2 = this.l;
        switch (i) {
            case 0:
                ((m90) obj2).d.o(i2, obj);
                break;
            default:
                ((fy1) obj2).o(i2, obj);
                break;
        }
    }
}
