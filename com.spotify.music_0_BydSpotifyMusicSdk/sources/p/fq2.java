package p;

import com.spotify.remoteconfig.fetcherimpl.network.ResolveRequestFailedException;
import java.util.concurrent.atomic.AtomicLong;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public final class fq2 implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ hq2 e;

    public /* synthetic */ fq2(hq2 hq2Var, int i) {
        this.c = i;
        this.e = hq2Var;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) throws ResolveRequestFailedException {
        int i = this.c;
        hq2 hq2Var = this.e;
        switch (i) {
            case 0:
                AtomicLong atomicLong = hq2Var.f;
                hq2Var.c.getClass();
                atomicLong.set(System.currentTimeMillis());
                return;
            default:
                u42 u42Var = (u42) obj;
                v03.j(u42Var, "response");
                hq2Var.getClass();
                Response response = u42Var.a;
                int i2 = response.l;
                if (i2 != 200 && i2 != 404 && i2 != 429) {
                    throw new ResolveRequestFailedException(u4.k(new StringBuilder("Unauth Resolve request failed: "), response.l, '!'), u42Var);
                }
                return;
        }
    }
}
