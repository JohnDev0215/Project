package p;

import com.spotify.rcs.resolver.grpc.v0.ResolveRequest;
import io.reactivex.rxjava3.core.Single;
import java.util.concurrent.atomic.AtomicLong;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class hq2 implements f32 {
    public final xa a;
    public final f42 b;
    public final u11 c;
    public final lw1 d;
    public final /* synthetic */ ut0 e;
    public final AtomicLong f;
    public final bx g;
    public final fq2 h;

    public hq2(bc2 bc2Var, ui0 ui0Var, cg2 cg2Var, OkHttpClient okHttpClient) {
        d52 d52Var = new d52();
        d52Var.c("https://spclient.wg.spotify.com/");
        d52Var.b = okHttpClient;
        d52Var.b(b11.c());
        d52Var.a(new a62(false));
        Object objB = d52Var.e().b(f42.class);
        v03.i(objB, "retrofit\n               …olverService::class.java)");
        u11 u11Var = new u11();
        xa xaVar = bc2Var.b;
        v03.j(xaVar, "appMetadata");
        lw1 lw1Var = bc2Var.c;
        v03.j(lw1Var, "policyInputs");
        this.a = xaVar;
        this.b = (f42) objB;
        this.c = u11Var;
        this.d = lw1Var;
        this.e = new ut0(cg2Var);
        this.f = new AtomicLong(0L);
        this.g = new bx(cg2Var, new d51(ui0Var, xaVar, lw1Var, 1));
        this.h = new fq2(this, 1);
    }

    @Override // p.f32
    public final Single a() {
        ResolveRequest resolveRequestA = ex.a(this.a, this.d);
        hk0 hk0VarA = qk2.a();
        resolveRequestA.toString();
        hk0VarA.getClass();
        hk0.A(new Object[0]);
        Single singleDoOnError = this.b.a(resolveRequestA).doOnSubscribe(new fq2(this, 0)).doOnSuccess(this.h).compose(new u11()).doOnSuccess(new gq2(this, 0)).doOnError(yn.m).doOnError(new gq2(this, 1));
        ut0 ut0Var = this.e;
        ut0Var.getClass();
        Single singleSubscribeOn = singleDoOnError.flatMap(new ux1(11, ut0Var)).onErrorReturnItem(dt0.i).subscribeOn(io.reactivex.rxjava3.schedulers.e.c);
        v03.i(singleSubscribeOn, "override fun fetch(fetch…On(Schedulers.io())\n    }");
        return singleSubscribeOn;
    }
}
