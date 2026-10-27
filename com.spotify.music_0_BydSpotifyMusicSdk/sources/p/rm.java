package p;

import android.animation.Animator;
import android.util.Log;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped$Reason;
import com.spotify.automotive.auth.model.LoginResponse;
import com.spotify.automotive.connectivity.ConnectionStateController$ConnectionState;
import com.spotify.automotive.playback.ErrorState;
import com.spotify.base.java.logging.Logger;
import com.spotify.bootstrap.v1.proto.Bootstrap$BootstrapRequest;
import com.spotify.bootstrap.v1.proto.Bootstrap$TrialsFacadeRequestV1;
import com.spotify.connectivity.connectiontype.ConnectionType;
import com.spotify.connectivity.productstateesperanto.RxProductStateImpl;
import com.spotify.cosmos.cosmonaut.TypedResponse;
import com.spotify.rcs.resolver.grpc.v0.proto.Resolve$Context;
import com.spotify.rcs.resolver.grpc.v0.proto.Resolve$Fetch;
import com.spotify.rcs.resolver.grpc.v0.proto.Resolve$ResolveRequest;
import com.spotify.ucs.v0.proto.Ucs$UcsRequest;
import com.spotify.webapi.service.models.UserPrivate;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class rm implements io.reactivex.rxjava3.functions.o, bq, vh2, i80, io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;

    public /* synthetic */ rm(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public void accept(Object obj) {
        int i = this.c;
        Object obj2 = this.k;
        Object obj3 = this.e;
        switch (i) {
            case 0:
                com.spotify.automotive.auth.b bVar = (com.spotify.automotive.auth.b) obj3;
                bVar.getClass();
                Logger.b("Failed to log in automatically. There are probably no stored credentials.", new Object[0]);
                ((c7) ((wk2) obj2)).a("result", "error");
                bVar.b((Throwable) obj);
                break;
            case 1:
                z91 z91Var = (z91) obj3;
                Boolean bool = (Boolean) obj;
                if (((if2) obj2).equals(z91Var.b)) {
                    z91Var.d.onNext(bool);
                }
                break;
            default:
                rx rxVar = (rx) obj3;
                f61 f61Var = (f61) obj2;
                rxVar.getClass();
                boolean zIsOffline = ((ConnectionType) obj).isOffline();
                io.reactivex.rxjava3.subjects.b bVar2 = rxVar.a;
                if (!zIsOffline) {
                    ConnectionStateController$ConnectionState connectionStateController$ConnectionState = ConnectionStateController$ConnectionState.k;
                    rxVar.c = connectionStateController$ConnectionState;
                    bVar2.onNext(connectionStateController$ConnectionState);
                    f61Var.a.onNext(Boolean.TRUE);
                } else {
                    rxVar.b.b.onNext(ErrorState.NETWORK_ERROR);
                    ConnectionStateController$ConnectionState connectionStateController$ConnectionState2 = ConnectionStateController$ConnectionState.e;
                    rxVar.c = connectionStateController$ConnectionState2;
                    bVar2.onNext(connectionStateController$ConnectionState2);
                    f61Var.a.onNext(Boolean.FALSE);
                }
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        switch (this.c) {
            case 0:
                sm smVar = (sm) this.e;
                io.reactivex.rxjava3.functions.o oVar = (io.reactivex.rxjava3.functions.o) this.k;
                TypedResponse typedResponse = (TypedResponse) obj;
                smVar.getClass();
                if (typedResponse.getBody() == null || !Objects.equals(((LoginResponse) typedResponse.getBody()).a, "bootstrapRequired")) {
                    return Single.just(typedResponse);
                }
                pm pmVar = new pm(((LoginResponse) typedResponse.getBody()).f);
                OkHttpClient.Builder builderE = smVar.b.e();
                builderE.c.add(pmVar);
                OkHttpClient okHttpClient = new OkHttpClient(builderE);
                fn fnVar = (fn) smVar.a;
                fnVar.getClass();
                sk2 sk2Var = fnVar.f;
                v03.j(sk2Var, "timeKeeper");
                c7 c7VarA = ((b7) sk2Var).a("bootstrap");
                c7VarA.h = "bootstrap-service-android";
                ln1.f = c7VarA;
                c7VarA.g("bootstrap-feature");
                c7 c7Var = ln1.f;
                if (c7Var != null) {
                    c7Var.g("prepare-bootstrap");
                }
                d52 d52Var = new d52();
                e52 e52Var = fnVar.b;
                d52Var.d(e52Var.c);
                d52Var.b = okHttpClient;
                d52Var.b(b11.c());
                List list = e52Var.e;
                v03.i(list, "retrofit.callAdapterFactories()");
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : list) {
                    if (obj2 instanceof a62) {
                        arrayList.add(obj2);
                    }
                }
                a62 a62Var = (a62) ou.d0(arrayList);
                if (a62Var == null) {
                    a62Var = new a62(true);
                }
                d52Var.a(a62Var);
                Object objB = d52Var.e().b(ym.class);
                v03.i(objB, "bootstrapRetrofitBuilder…trapEndpoint::class.java)");
                ym ymVar = (ym) objB;
                c7 c7Var2 = ln1.f;
                if (c7Var2 != null) {
                    c7Var2.g("create-bootstrap-request");
                }
                om omVarG = Bootstrap$BootstrapRequest.g();
                omVarG.d(Bootstrap$TrialsFacadeRequestV1.f());
                nt ntVar = fnVar.a;
                boolean zContains = fn.h.contains(ntVar.a);
                lw1 lw1Var = ntVar.d;
                Set setKeySet = lw1Var.b().keySet();
                boolean z = setKeySet instanceof Collection;
                String str = ntVar.b;
                if (z && setKeySet.isEmpty()) {
                    Logger.g("This integration was setup without RemoteConfig ClientVersion as an Identifier. Please fix!", new Object[0]);
                    lw1Var.a(new ft(str));
                } else {
                    Iterator it = setKeySet.iterator();
                    while (it.hasNext()) {
                        if (v03.b((String) it.next(), "/remote-config/rc-client-version")) {
                        }
                    }
                    Logger.g("This integration was setup without RemoteConfig ClientVersion as an Identifier. Please fix!", new Object[0]);
                    lw1Var.a(new ft(str));
                }
                TreeMap treeMapB = lw1Var.b();
                ArrayList arrayList2 = new ArrayList(treeMapB.size());
                for (Map.Entry entry : treeMapB.entrySet()) {
                    com.spotify.rcs.resolver.grpc.v0.proto.h hVarG = Resolve$Context.ContextEntry.g();
                    hVarG.d((String) entry.getKey());
                    hVarG.e((String) entry.getValue());
                    arrayList2.add((Resolve$Context.ContextEntry) hVarG.build());
                }
                com.spotify.ucs.v0.proto.b bVarH = Ucs$UcsRequest.h();
                com.spotify.ucs.v0.proto.c cVarH = Ucs$UcsRequest.CallerInfo.h();
                cVarH.f(ntVar.a);
                cVarH.e(str);
                cVarH.d();
                bVarH.e(cVarH);
                x32 x32VarH = Resolve$ResolveRequest.h();
                x32VarH.f(ntVar.c);
                com.spotify.rcs.resolver.grpc.v0.proto.i iVarF = Resolve$Fetch.f();
                iVarF.d();
                x32VarH.e(iVarF);
                com.spotify.rcs.resolver.grpc.v0.proto.g gVarF = Resolve$Context.f();
                gVarF.d(arrayList2);
                x32VarH.d(gVarF);
                bVarH.f(x32VarH);
                if (zContains) {
                    bVarH.d(Ucs$UcsRequest.AccountAttributesRequest.e());
                }
                omVarG.e(bVarH);
                com.google.protobuf.i iVarBuild = omVarG.build();
                c7 c7Var3 = ln1.f;
                if (c7Var3 != null) {
                    c7Var3.c("create-bootstrap-request");
                }
                v03.i(iVarBuild, "newBuilder()\n           …apRequest()\n            }");
                Single<R> map = ymVar.a((Bootstrap$BootstrapRequest) iVarBuild).doOnSubscribe(new dn(fnVar)).doOnEvent(new en(fnVar)).map(vm.c);
                v03.i(map, "private fun prepareBoots…ootstrapDataParser)\n    }");
                Single singleDoOnEvent = map.map(new cn(fnVar)).onErrorReturnItem(new tm()).doOnEvent(new bn(fnVar));
                c7 c7Var4 = ln1.f;
                if (c7Var4 != null) {
                    c7Var4.c("prepare-bootstrap");
                }
                v03.i(singleDoOnEvent, "bootstrapDataSingle");
                return singleDoOnEvent.flatMap(new dd1(3, oVar));
            case 1:
                oj ojVar = (oj) this.e;
                Completable completable = (Completable) this.k;
                ojVar.getClass();
                if (((Boolean) obj).booleanValue()) {
                    return completable;
                }
                ojVar.h.b.onNext(ErrorState.PLAYBACK_TEMPORARY_ERROR);
                return io.reactivex.rxjava3.internal.operators.completable.d.c;
            case 2:
                ku kuVar = (ku) this.e;
                return kuVar.a.b.v((String) this.k, ((UserPrivate) obj).id);
            default:
                return RxProductStateImpl.lambda$productStateKeyOr$2((String) this.e, (String) this.k, (Map) obj);
        }
    }

    @Override // p.vh2
    public Object b() {
        int i = this.c;
        Object obj = this.k;
        Object obj2 = this.e;
        switch (i) {
            case 1:
                Iterable iterable = (Iterable) obj;
                j62 j62Var = (j62) ((jr2) obj2).c;
                j62Var.getClass();
                if (iterable.iterator().hasNext()) {
                    j62Var.b().compileStatement("DELETE FROM events WHERE _id in " + j62.X(iterable)).execute();
                }
                break;
            default:
                jr2 jr2Var = (jr2) obj2;
                jr2Var.getClass();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    long jIntValue = ((Integer) entry.getValue()).intValue();
                    LogEventDropped$Reason logEventDropped$Reason = LogEventDropped$Reason.INVALID_PAYLOD;
                    String str = (String) entry.getKey();
                    j62 j62Var2 = (j62) jr2Var.i;
                    j62Var2.getClass();
                    j62Var2.i(new ir2(jIntValue, str, logEventDropped$Reason));
                }
                break;
        }
        return null;
    }

    @Override // p.bq
    public void c() {
        Animator animator = (Animator) this.e;
        bf2 bf2Var = (bf2) this.k;
        v03.j(bf2Var, "$operation");
        animator.end();
        if (Log.isLoggable("FragmentManager", 2)) {
            bf2Var.toString();
        }
    }

    @Override // p.i80
    public void e(yy1 yy1Var) {
        i80 i80Var = (i80) this.e;
        i80 i80Var2 = (i80) this.k;
        i80Var.e(yy1Var);
        i80Var2.e(yy1Var);
    }
}
