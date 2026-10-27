package p;

import com.spotify.connectivity.product_state.esperanto.proto.ProductStateClient;
import com.spotify.connectivity.product_state.esperanto.proto.ProductStateEsperantoKt;
import com.spotify.connectivity.productstateesperanto.ProductStateMethods;
import com.spotify.connectivity.productstateesperanto.ProductStateMethodsImpl;
import com.spotify.cosmos.cosmonaut.Cosmonaut;
import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.esperanto.esperantocosmos.CosmosTransport;
import com.spotify.eventsender.corebridge.EventSenderCoreBridgeImpl;
import com.spotify.playerlimited.playerimpl.services.SessionEndpoint;
import java.util.Set;
import kotlin.collections.EmptyList;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class ct1 implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;

    public /* synthetic */ ct1(xy1 xy1Var, int i) {
        this.a = i;
        this.b = xy1Var;
    }

    public static tt a(ut utVar) {
        v03.j(utVar, "clientInfo");
        return new tt();
    }

    public static io.reactivex.rxjava3.subjects.b b(f61 f61Var) {
        v03.j(f61Var, "isConnectedObservable");
        io.reactivex.rxjava3.subjects.b bVar = f61Var.a;
        v03.i(bVar, "isConnectedSubject");
        return bVar;
    }

    public static s72 c(tt ttVar) {
        v03.j(ttVar, "clientIdEventContextProvider");
        int i = s72.f;
        EmptyList emptyList = EmptyList.c;
        return new s72(v03.B(ttVar), new u11(), "https://spclient.wg.spotify.com/", s72.f, u11.f193p);
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.b;
        switch (i) {
            case 0:
                return a((ut) xy1Var.get());
            case 1:
                xi0 xi0Var = (xi0) xy1Var.get();
                v03.j(xi0Var, "eventSender");
                jw0 jw0Var = ((com.spotify.eventsender.eventsender.b) xi0Var).b;
                v03.j(jw0Var, "eventPublisher");
                return new xj1(jw0Var);
            case 2:
                xi0 xi0Var2 = (xi0) xy1Var.get();
                v03.j(xi0Var2, "eventSender");
                jw0 jw0Var2 = ((com.spotify.eventsender.eventsender.b) xi0Var2).b;
                vr2.b(jw0Var2);
                return jw0Var2;
            case 3:
                return b((f61) xy1Var.get());
            case 4:
                return c((tt) xy1Var.get());
            case 5:
                return new u00((t00) xy1Var.get());
            case 6:
                return new vp1((u6) xy1Var.get());
            case 7:
                return new rs1((OkHttpClient) xy1Var.get());
            case 8:
                return new jf0((x00) xy1Var.get());
            case 9:
                return new fh0((zw1) xy1Var.get());
            case 10:
                jx jxVar = (jx) xy1Var.get();
                v03.j(jxVar, "configProvider");
                return new com.spotify.automotive.remoteconfig.a(false, false, jxVar);
            case 11:
                return new up2((ur2) xy1Var.get());
            case 12:
                vi0 vi0Var = (vi0) xy1Var.get();
                v03.j(vi0Var, "eventPublisher");
                return new EventSenderCoreBridgeImpl(vi0Var);
            case 13:
                return new jq1((e52) xy1Var.get());
            case 14:
                return el1.a((Set) xy1Var.get());
            case 15:
                com.squareup.moshi.o oVar = (com.squareup.moshi.o) xy1Var.get();
                v03.j(oVar, "moshi");
                return new fl1(oVar);
            case 16:
                return new wz0((wi0) xy1Var.get());
            case 17:
                vz vzVar = (vz) xy1Var.get();
                v03.j(vzVar, "playerClient");
                return new zh0(vzVar);
            case 18:
                return new yc1((u6) xy1Var.get());
            case 19:
                Cosmonaut cosmonaut = (Cosmonaut) xy1Var.get();
                v03.j(cosmonaut, "cosmonaut");
                SessionEndpoint sessionEndpoint = (SessionEndpoint) cosmonaut.createCosmosService(SessionEndpoint.class);
                vr2.b(sessionEndpoint);
                return sessionEndpoint;
            case 20:
                RxRouter rxRouter = (RxRouter) xy1Var.get();
                v03.j(rxRouter, "rxRouter");
                return new p72(new CosmosTransport(new wz(rxRouter, 1)));
            case 21:
                RxRouter rxRouter2 = (RxRouter) xy1Var.get();
                v03.j(rxRouter2, "rxRouter");
                return new qx(new CosmosTransport(new wz(rxRouter2, 2)));
            case 22:
                RxRouter rxRouter3 = (RxRouter) xy1Var.get();
                v03.j(rxRouter3, "rxRouter");
                return new ca1(new CosmosTransport(new wz(rxRouter3, 3)));
            case 23:
                RxRouter rxRouter4 = (RxRouter) xy1Var.get();
                v03.j(rxRouter4, "rxRouter");
                return new dp1(new CosmosTransport(new wz(rxRouter4, 4)));
            case 24:
                return new hh0((qx) xy1Var.get());
            case 25:
                v03.j((dp1) xy1Var.get(), "client");
                return new ih0();
            case 26:
                v03.j((ca1) xy1Var.get(), "limitedOffliningClient");
                return new da1();
            case 27:
                return new xx1((ProductStateMethods) xy1Var.get());
            case 28:
                RxRouter rxRouter5 = (RxRouter) xy1Var.get();
                v03.j(rxRouter5, "rxRouter");
                ProductStateClient productStateClientCreateProductStateClient = ProductStateEsperantoKt.createProductStateClient(new CosmosTransport(new wz(rxRouter5, 5)));
                vr2.b(productStateClientCreateProductStateClient);
                return productStateClientCreateProductStateClient;
            default:
                ProductStateClient productStateClient = (ProductStateClient) xy1Var.get();
                v03.j(productStateClient, "productStateClient");
                return new ProductStateMethodsImpl(productStateClient);
        }
    }
}
