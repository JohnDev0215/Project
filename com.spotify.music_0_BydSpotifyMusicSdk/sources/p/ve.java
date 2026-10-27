package p;

import com.spotify.remoteconfig.fetcherimpl.network.UcsRequestFailedException;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class ve implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ com.spotify.remoteconfig.fetcherimpl.a e;

    public /* synthetic */ ve(com.spotify.remoteconfig.fetcherimpl.a aVar, int i) {
        this.c = i;
        this.e = aVar;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) throws UcsRequestFailedException {
        int i = this.c;
        com.spotify.remoteconfig.fetcherimpl.a aVar = this.e;
        switch (i) {
            case 0:
                AtomicLong atomicLong = aVar.h;
                aVar.c.getClass();
                atomicLong.set(System.currentTimeMillis());
                return;
            default:
                u42 u42Var = (u42) obj;
                v03.j(u42Var, "response");
                aVar.getClass();
                int i2 = u42Var.a.l;
                if (i2 != 200 && i2 != 304 && i2 != 404 && i2 != 429) {
                    throw new UcsRequestFailedException(u42Var);
                }
                return;
        }
    }
}
