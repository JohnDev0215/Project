package p;

import android.net.Uri;
import com.spotify.base.java.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class ve2 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ xe2 e;
    public final /* synthetic */ Uri k;

    public /* synthetic */ ve2(xe2 xe2Var, Uri uri, int i) {
        this.c = i;
        this.e = xe2Var;
        this.k = uri;
    }

    public final io.reactivex.rxjava3.internal.operators.completable.e a(Throwable th) {
        int i = this.c;
        Uri uri = this.k;
        xe2 xe2Var = this.e;
        switch (i) {
            case 0:
                Logger.c(th, "Exception while calling prepare.", new Object[0]);
                xe2Var.g.remove(uri);
                mk mkVar = (mk) xe2Var.b;
                mkVar.getClass();
                return new io.reactivex.rxjava3.internal.operators.completable.e(1, new q(3, mkVar));
            default:
                Logger.c(th, "Exception while calling search for searchAndPrepare.", new Object[0]);
                xe2Var.g.remove(uri);
                mk mkVar2 = (mk) xe2Var.b;
                mkVar2.getClass();
                return new io.reactivex.rxjava3.internal.operators.completable.e(1, new q(3, mkVar2));
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final /* bridge */ /* synthetic */ Object apply(Object obj) {
        switch (this.c) {
        }
        return a((Throwable) obj);
    }
}
