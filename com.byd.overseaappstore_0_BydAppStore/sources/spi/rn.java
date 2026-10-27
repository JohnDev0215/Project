package spi;

import java.net.InetSocketAddress;
import java.net.Proxy;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class rn {

    @NotNull
    public final t a;

    @NotNull
    public final Proxy b;

    @NotNull
    public final InetSocketAddress c;

    public rn(@NotNull t address, @NotNull Proxy proxy, @NotNull InetSocketAddress socketAddress) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        Intrinsics.checkNotNullParameter(socketAddress, "socketAddress");
        this.a = address;
        this.b = proxy;
        this.c = socketAddress;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof rn) {
            rn rnVar = (rn) obj;
            if (Intrinsics.areEqual(rnVar.a, this.a) && Intrinsics.areEqual(rnVar.b, this.b) && Intrinsics.areEqual(rnVar.c, this.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + ((this.a.hashCode() + 527) * 31)) * 31);
    }

    @NotNull
    public final String toString() {
        StringBuilder sbK = e.k("Route{");
        sbK.append(this.c);
        sbK.append('}');
        return sbK.toString();
    }
}
