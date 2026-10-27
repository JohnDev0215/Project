package spi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.List;
import java.util.Objects;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class eg implements u0 {
    public final s6 b;

    public eg() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public eg(@NotNull s6 defaultDns) {
        Intrinsics.checkNotNullParameter(defaultDns, "defaultDns");
        this.b = defaultDns;
    }

    public /* synthetic */ eg(s6 s6Var, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? s6.a : s6Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0085  */
    @Override // spi.u0
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final spi.im a(@org.jetbrains.annotations.Nullable spi.rn r21, @org.jetbrains.annotations.NotNull spi.ln r22) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 417
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.eg.a(spi.rn, spi.ln):spi.im");
    }

    public final InetAddress b(Proxy proxy, hf hfVar, s6 s6Var) throws IOException {
        Proxy.Type type = proxy.type();
        if (type != null && dg.a[type.ordinal()] == 1) {
            return (InetAddress) CollectionsKt.first((List) s6Var.a(hfVar.e));
        }
        SocketAddress socketAddressAddress = proxy.address();
        Objects.requireNonNull(socketAddressAddress, "null cannot be cast to non-null type java.net.InetSocketAddress");
        InetAddress address = ((InetSocketAddress) socketAddressAddress).getAddress();
        Intrinsics.checkNotNullExpressionValue(address, "(address() as InetSocketAddress).address");
        return address;
    }
}
