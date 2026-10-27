package p;

import com.google.common.base.Optional;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.playback.ErrorState;
import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.connectivity.sessionstate.SessionState;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.rcs.resolver.grpc.v0.Configuration;
import com.spotify.rcs.resolver.grpc.v0.ResolveResponse;
import com.spotify.webapi.service.models.Entity;
import com.spotify.webapi.service.models.Pager;
import com.spotify.webapi.service.models.Playlist;
import com.spotify.webapi.service.models.PlaylistTrack;
import com.spotify.webapi.service.models.Search;
import com.spotify.webapi.service.models.views.View;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ux1 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ ux1(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    public final Object a(Object[] objArr) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 13:
                if (objArr.length == 2) {
                    return ((io.reactivex.rxjava3.functions.c) obj).k(objArr[0], objArr[1]);
                }
                throw new IllegalArgumentException("Array of size 2 expected but got " + objArr.length);
            case 14:
                if (objArr.length == 3) {
                    return ((io.reactivex.rxjava3.functions.h) obj).apply(objArr[0], objArr[1], objArr[2]);
                }
                throw new IllegalArgumentException("Array of size 3 expected but got " + objArr.length);
            case 15:
                if (objArr.length != 4) {
                    throw new IllegalArgumentException("Array of size 4 expected but got " + objArr.length);
                }
                Object obj2 = objArr[0];
                Object obj3 = objArr[1];
                Object obj4 = objArr[2];
                Object obj5 = objArr[3];
                p0 p0Var = (p0) ((af2) ((io.reactivex.rxjava3.functions.i) obj)).e;
                return new r0(p0Var.e, (ju1) obj2, p0Var.c, p0Var.d, ((Boolean) obj3).booleanValue(), ((Boolean) obj4).booleanValue(), ((Integer) obj5).intValue());
            case 16:
                if (objArr.length != 5) {
                    throw new IllegalArgumentException("Array of size 5 expected but got " + objArr.length);
                }
                u4.z(obj);
                Object obj6 = objArr[0];
                Object obj7 = objArr[1];
                Object obj8 = objArr[2];
                Object obj9 = objArr[3];
                Object obj10 = objArr[4];
                throw null;
            case 17:
                if (objArr.length != 6) {
                    throw new IllegalArgumentException("Array of size 6 expected but got " + objArr.length);
                }
                u4.z(obj);
                Object obj11 = objArr[0];
                Object obj12 = objArr[1];
                Object obj13 = objArr[2];
                Object obj14 = objArr[3];
                Object obj15 = objArr[4];
                Object obj16 = objArr[5];
                throw null;
            case 18:
                if (objArr.length != 7) {
                    throw new IllegalArgumentException("Array of size 7 expected but got " + objArr.length);
                }
                u4.z(obj);
                Object obj17 = objArr[0];
                Object obj18 = objArr[1];
                Object obj19 = objArr[2];
                Object obj20 = objArr[3];
                Object obj21 = objArr[4];
                Object obj22 = objArr[5];
                Object obj23 = objArr[6];
                throw null;
            case 19:
                if (objArr.length != 8) {
                    throw new IllegalArgumentException("Array of size 8 expected but got " + objArr.length);
                }
                u4.z(obj);
                Object obj24 = objArr[0];
                Object obj25 = objArr[1];
                Object obj26 = objArr[2];
                Object obj27 = objArr[3];
                Object obj28 = objArr[4];
                Object obj29 = objArr[5];
                Object obj30 = objArr[6];
                Object obj31 = objArr[7];
                throw null;
            default:
                if (objArr.length != 9) {
                    throw new IllegalArgumentException("Array of size 9 expected but got " + objArr.length);
                }
                u4.z(obj);
                Object obj32 = objArr[0];
                Object obj33 = objArr[1];
                Object obj34 = objArr[2];
                Object obj35 = objArr[3];
                Object obj36 = objArr[4];
                Object obj37 = objArr[5];
                Object obj38 = objArr[6];
                Object obj39 = objArr[7];
                Object obj40 = objArr[8];
                throw null;
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        List<Entity> list;
        int i = this.c;
        int i2 = 1;
        int i3 = 0;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                Map map = (Map) obj;
                v03.j(map, "p0");
                ((u11) obj2).getClass();
                return new tx1(v03.b("1", map.get("shows-collection")), v03.b("2", map.get("client-deprecated")), v03.b("1", map.get("on-demand")), v03.b(SessionState.PRODUCT_TYPE_PREMIUM, map.get(RxProductState.Keys.KEY_TYPE)), v03.b("1", map.get("autoplay")), v03.b("1", map.get("pick-and-shuffle")));
            case 1:
                ErrorState errorState = (ErrorState) obj;
                v03.j(errorState, "errorState");
                return new di1((ei1) obj2, errorState);
            case 2:
                LoginState loginState = (LoginState) obj;
                v03.j(loginState, "state");
                gh2 gh2Var = ((zn) obj2).c;
                gh2Var.getClass();
                return loginState == LoginState.e ? new com.spotify.automotive.browse.a((vx1) gh2Var.e, (xb1) gh2Var.k, (Scheduler) gh2Var.m) : new uc1((kk0) gh2Var.l);
            case 3:
                tx1 tx1Var = (tx1) obj;
                v03.i(tx1Var, "it");
                return (SingleSource) ((ry0) obj2).invoke(tx1Var);
            case 4:
                View view = (View) obj;
                v03.j(view, Search.Type.VIEW);
                Pager<Entity> pager = view.content;
                if (pager == null || (list = pager.items) == null) {
                    return EmptyList.c;
                }
                ArrayList arrayList = new ArrayList();
                ao aoVar = (ao) obj2;
                for (Object obj3 : list) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        v03.P();
                        throw null;
                    }
                    aoVar.e((Entity) obj3, arrayList, aoVar.d, i3);
                    i3 = i4;
                }
                return arrayList;
            case 5:
                Pager<PlaylistTrack> pager2 = (Pager) obj;
                v03.j(pager2, "playlistTracks");
                ((pu1) obj2).getClass();
                Playlist playlist = new Playlist();
                playlist.tracks = pager2;
                return playlist;
            case 6:
                a01 a01Var = (a01) obj;
                v03.j(a01Var, "<name for destructuring parameter 0>");
                return new io.reactivex.rxjava3.internal.operators.completable.e(i2, new com.spotify.automotive.playback.b((com.spotify.automotive.playback.c) obj2, a01Var.d));
            case 7:
                return com.spotify.eventsender.disk.a.a((com.spotify.eventsender.disk.a) obj2);
            case 8:
                try {
                    Object objC = ((Optional) obj).c();
                    v03.i(objC, "eventPublisherApiProvider.get()");
                    com.spotify.eventsender.eventsender.observability.a.a((com.spotify.eventsender.eventsender.observability.a) obj2, (tz0) objC);
                    ((u11) ((com.spotify.eventsender.eventsender.observability.a) obj2).f).getClass();
                    break;
                } catch (Throwable th) {
                    ((u11) ((com.spotify.eventsender.eventsender.observability.a) obj2).f).getClass();
                    v03.j("EventSender heartbeat event sending error. " + th, "message");
                }
                return lq2.a;
            case 9:
                h02 h02Var = (h02) obj2;
                i02 i02Var = h02Var.a;
                LinkedHashMap linkedHashMap = h02Var.c;
                k02 k02Var = (k02) i02Var;
                k02Var.getClass();
                v03.j(linkedHashMap, "report");
                io.reactivex.rxjava3.internal.operators.completable.e eVar = new io.reactivex.rxjava3.internal.operators.completable.e(i2, new ic2(k02Var, 2, linkedHashMap));
                Scheduler scheduler = io.reactivex.rxjava3.schedulers.e.c;
                Objects.requireNonNull(scheduler, "scheduler is null");
                return new io.reactivex.rxjava3.internal.operators.completable.j(eVar, scheduler, i2).e(Boolean.TRUE).onErrorReturnItem(Boolean.FALSE).toObservable();
            case 10:
                RxRouter rxRouter = (RxRouter) obj;
                v03.j(rxRouter, "router");
                return rxRouter.resolve((Request) obj2);
            case 11:
                u42 u42Var = (u42) obj;
                ResolveResponse resolveResponse = (ResolveResponse) u42Var.b;
                boolean zI = u42Var.a.i();
                dt0 dt0Var = dt0.i;
                if (!zI || resolveResponse == null || !resolveResponse.h() || resolveResponse.f() == null) {
                    return Single.just(dt0Var);
                }
                hk0 hk0VarA = qk2.a();
                resolveResponse.toString();
                hk0VarA.getClass();
                hk0.t(new Object[0]);
                Configuration configurationF = resolveResponse.f();
                v03.i(configurationF, "body.configuration");
                io.reactivex.rxjava3.internal.operators.completable.b bVarA = ((ut0) obj2).a(configurationF, "");
                if (resolveResponse.h() && resolveResponse.f().isInitialized()) {
                    dt0Var = dt0.j;
                }
                return bVarA.e(dt0Var);
            case 12:
                Flowable flowable = (Flowable) obj;
                AtomicInteger atomicInteger = new AtomicInteger(0);
                u11 u11Var = (u11) obj2;
                f52 f52Var = new f52(atomicInteger, u11Var);
                flowable.getClass();
                return new io.reactivex.rxjava3.internal.operators.flowable.o(flowable, f52Var, i2).f(new co(atomicInteger, 8, u11Var));
            case 13:
                return a((Object[]) obj);
            case 14:
                return a((Object[]) obj);
            case 15:
                return a((Object[]) obj);
            case 16:
                return a((Object[]) obj);
            case 17:
                return a((Object[]) obj);
            case 18:
                return a((Object[]) obj);
            case 19:
                return a((Object[]) obj);
            case 20:
                return a((Object[]) obj);
            case 21:
                return ((Class) obj2).cast(obj);
            default:
                List list2 = (List) obj;
                Collections.sort(list2, (Comparator) obj2);
                return list2;
        }
    }
}
