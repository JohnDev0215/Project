package spi;

import java.net.Proxy;
import java.net.URI;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class un extends Lambda implements Function0<List<? extends Proxy>> {
    public final /* synthetic */ tn a;
    public final /* synthetic */ Proxy b;
    public final /* synthetic */ hf c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public un(tn tnVar, Proxy proxy, hf hfVar) {
        super(0);
        this.a = tnVar;
        this.b = proxy;
        this.c = hfVar;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final List<? extends Proxy> invoke() {
        Proxy proxy = this.b;
        if (proxy != null) {
            return CollectionsKt.listOf(proxy);
        }
        URI uriI = this.c.i();
        if (uriI.getHost() == null) {
            return uu.k(Proxy.NO_PROXY);
        }
        List<Proxy> listSelect = this.a.e.k.select(uriI);
        return listSelect == null || listSelect.isEmpty() ? uu.k(Proxy.NO_PROXY) : uu.x(listSelect);
    }
}
