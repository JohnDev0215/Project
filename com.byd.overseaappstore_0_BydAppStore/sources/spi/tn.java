package spi;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class tn {
    public static final a i = new a(null);
    public List<? extends Proxy> a;
    public int b;
    public List<? extends InetSocketAddress> c;
    public final List<rn> d;
    public final t e;
    public final sn f;
    public final c3 g;
    public final u8 h;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final class b {
        public int a;

        @NotNull
        public final List<rn> b;

        public b(@NotNull List<rn> routes) {
            Intrinsics.checkNotNullParameter(routes, "routes");
            this.b = routes;
        }

        public final boolean a() {
            return this.a < this.b.size();
        }

        @NotNull
        public final rn b() {
            if (!a()) {
                throw new NoSuchElementException();
            }
            List<rn> list = this.b;
            int i = this.a;
            this.a = i + 1;
            return list.get(i);
        }
    }

    public tn(@NotNull t address, @NotNull sn routeDatabase, @NotNull c3 call, @NotNull u8 eventListener) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(routeDatabase, "routeDatabase");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        this.e = address;
        this.f = routeDatabase;
        this.g = call;
        this.h = eventListener;
        this.a = CollectionsKt.emptyList();
        this.c = CollectionsKt.emptyList();
        this.d = new ArrayList();
        hf url = address.a;
        un unVar = new un(this, address.j, url);
        Objects.requireNonNull(eventListener);
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(url, "url");
        List<? extends Proxy> proxies = unVar.invoke();
        this.a = proxies;
        this.b = 0;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(proxies, "proxies");
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<spi.rn>] */
    public final boolean a() {
        return b() || (this.d.isEmpty() ^ true);
    }

    public final boolean b() {
        return this.b < this.a.size();
    }
}
