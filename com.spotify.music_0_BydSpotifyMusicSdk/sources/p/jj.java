package p;

import com.google.common.base.Optional;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class jj implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ oj e;
    public final /* synthetic */ Optional k;

    public /* synthetic */ jj(oj ojVar, Optional optional, int i) {
        this.c = i;
        this.e = ojVar;
        this.k = optional;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Optional optional = this.k;
        oj ojVar = this.e;
        switch (i) {
            case 0:
                ojVar.getClass();
                ojVar.f.p(((if2) optional.c()).toString());
                break;
            default:
                ojVar.getClass();
                ojVar.f.d(((if2) optional.c()).toString());
                break;
        }
    }
}
