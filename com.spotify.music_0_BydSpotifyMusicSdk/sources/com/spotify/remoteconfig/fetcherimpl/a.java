package com.spotify.remoteconfig.fetcherimpl;

import com.google.protobuf.i;
import com.spotify.ucs.proto.v0.UcsRequest;
import com.spotify.ucs.proto.v0.c;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.e;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import okhttp3.OkHttpClient;
import p.a62;
import p.b11;
import p.bc2;
import p.bx;
import p.cg2;
import p.co;
import p.d51;
import p.d52;
import p.dt0;
import p.du;
import p.eg2;
import p.ex;
import p.f32;
import p.f42;
import p.hk0;
import p.lw1;
import p.m02;
import p.ou;
import p.pc;
import p.py0;
import p.qk2;
import p.st0;
import p.u11;
import p.ui0;
import p.ut0;
import p.v03;
import p.v81;
import p.ve;
import p.w01;
import p.we;
import p.xa;
import p.xe;
import p.ye;
import p.zb0;

/* JADX INFO: loaded from: classes.dex */
public final class a implements f32 {
    public final xa a;
    public final f42 b;
    public final u11 c;
    public final cg2 d;
    public final lw1 e;
    public final /* synthetic */ ut0 f;
    public final bx g;
    public final AtomicLong h;
    public final boolean i;
    public final v81 j;
    public final ve k;

    public a(ui0 ui0Var, OkHttpClient okHttpClient, bc2 bc2Var, cg2 cg2Var) {
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
        this.d = cg2Var;
        this.e = lw1Var;
        this.f = new ut0(cg2Var);
        this.g = new bx(cg2Var, new d51(ui0Var, xaVar, lw1Var, 0));
        this.h = new AtomicLong(0L);
        this.i = ye.a.contains("com.spotify.auto");
        this.j = kotlin.a.c(new py0() { // from class: com.spotify.remoteconfig.fetcherimpl.AuthFetcherImpl$sdkProperties$2
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                Integer num;
                Boolean bool;
                eg2 eg2Var = (eg2) this.this$0.d;
                m02 m02VarB = eg2Var.c.b();
                m02 m02VarB2 = eg2Var.d.b();
                v03.j(m02VarB, "fetchedConfig");
                v03.j(m02VarB2, "overriddenConfig");
                Map map = m02VarB.b;
                v03.j(map, "<this>");
                Map map2 = m02VarB2.b;
                v03.j(map2, "map");
                LinkedHashMap linkedHashMap = new LinkedHashMap(map);
                linkedHashMap.putAll(map2);
                w01 w01VarA = w01.a(m02VarB.a, ou.n0(linkedHashMap.values()), null, 27);
                m02 m02Var = m02.c;
                m02 m02VarU = zb0.u(w01VarA);
                b bVar = new b(false, 1200);
                Map map3 = m02VarU.b;
                pc pcVar = (pc) map3.get("remote-configuration-client-android:fetch_inject_ps");
                boolean zA = (pcVar == null || (bool = pcVar.c) == null) ? bVar.a() : bool.booleanValue();
                pc pcVar2 = (pc) map3.get("remote-configuration-client-android:reconnect_throttle_seconds");
                return new b(zA, (pcVar2 == null || (num = pcVar2.d) == null) ? bVar.b() : num.intValue());
            }
        });
        this.k = new ve(this, 1);
    }

    @Override // p.f32
    public final Single a() {
        this.c.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        v81 v81Var = this.j;
        timeUnit.toMillis(((b) v81Var.getValue()).b());
        boolean z = this.i;
        int i = 1;
        int i2 = 0;
        boolean z2 = z || ((b) v81Var.getValue()).a();
        xa xaVar = this.a;
        v03.j(xaVar, "appMetadata");
        lw1 lw1Var = this.e;
        v03.j(lw1Var, "policyInputs");
        com.spotify.ucs.proto.v0.b bVarH = UcsRequest.h();
        c cVarH = UcsRequest.CallerInfo.h();
        cVarH.f();
        cVarH.e();
        cVarH.d("ASYNC");
        bVarH.e(cVarH);
        bVarH.f(ex.a(xaVar, lw1Var));
        if (z2) {
            bVarH.d(UcsRequest.AccountAttributesRequest.e());
        }
        i iVarBuild = bVarH.build();
        v03.i(iVarBuild, "requestBuilder.build()");
        UcsRequest ucsRequest = (UcsRequest) iVarBuild;
        qk2.a().getClass();
        hk0.t(new Object[0]);
        hk0 hk0VarA = qk2.a();
        String.valueOf(ucsRequest);
        hk0VarA.getClass();
        hk0.A(new Object[0]);
        eg2 eg2Var = (eg2) this.d;
        eg2Var.getClass();
        Single singleCreate = Single.create(new du(i, eg2Var));
        v03.i(singleCreate, "override fun getETag(): …configuration.etag)\n    }");
        Single singleDoOnError = singleCreate.flatMap(new co(this, 7, ucsRequest)).doOnSubscribe(new ve(this, i2)).doOnSuccess(this.k).compose(new u11()).doOnSuccess(new we(this, i2)).doOnSuccess(new xe(this, jCurrentTimeMillis)).doOnError(new we(this, i));
        b bVar = (b) v81Var.getValue();
        v03.j(bVar, "sdkProperties");
        ut0 ut0Var = this.f;
        ut0Var.getClass();
        Single singleSubscribeOn = singleDoOnError.flatMap(new st0(z, bVar, ut0Var)).onErrorReturnItem(dt0.i).subscribeOn(e.c);
        v03.i(singleSubscribeOn, "override fun fetch(fetch…On(Schedulers.io())\n    }");
        return singleSubscribeOn;
    }
}
