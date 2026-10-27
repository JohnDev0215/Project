package okhttp3.internal.connection;

import java.net.Proxy;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.EmptyList;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.internal.Util;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class RouteSelector {
    public static final Companion i = new Companion(0);
    public final Address a;
    public final RouteDatabase b;
    public final Call c;
    public final EventListener d;
    public final List e;
    public int f;
    public List g;
    public final ArrayList h;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public static final class Selection {
        public final List a;
        public int b;

        public Selection(ArrayList arrayList) {
            this.a = arrayList;
        }

        public final boolean a() {
            return this.b < this.a.size();
        }
    }

    public RouteSelector(Address address, RouteDatabase routeDatabase, RealCall realCall, EventListener eventListener) {
        List listL;
        v03.j(address, "address");
        v03.j(routeDatabase, "routeDatabase");
        v03.j(realCall, "call");
        v03.j(eventListener, "eventListener");
        this.a = address;
        this.b = routeDatabase;
        this.c = realCall;
        this.d = eventListener;
        EmptyList emptyList = EmptyList.c;
        this.e = emptyList;
        this.g = emptyList;
        this.h = new ArrayList();
        HttpUrl httpUrl = address.i;
        v03.j(httpUrl, "url");
        Proxy proxy = address.g;
        if (proxy != null) {
            listL = v03.B(proxy);
        } else {
            URI uriH = httpUrl.h();
            if (uriH.getHost() == null) {
                listL = Util.l(Proxy.NO_PROXY);
            } else {
                List<Proxy> listSelect = address.h.select(uriH);
                listL = (listSelect == null || listSelect.isEmpty()) ? Util.l(Proxy.NO_PROXY) : Util.y(listSelect);
            }
        }
        this.e = listL;
        this.f = 0;
    }

    public final boolean a() {
        return (this.f < this.e.size()) || (this.h.isEmpty() ^ true);
    }
}
