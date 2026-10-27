package spi;

import android.annotation.SuppressLint;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class v extends zk {
    public static final boolean e;
    public static final a f = new a(null);
    public final List<up> d;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        e = zk.c.c() && Build.VERSION.SDK_INT >= 29;
    }

    public v() {
        up[] upVarArr = new up[4];
        Objects.requireNonNull(w.a);
        upVarArr[0] = zk.c.c() && Build.VERSION.SDK_INT >= 29 ? new w() : null;
        Objects.requireNonNull(c0.g);
        upVarArr[1] = new c6(c0.f);
        Objects.requireNonNull(i4.b);
        upVarArr[2] = new c6(i4.a);
        Objects.requireNonNull(d2.b);
        upVarArr[3] = new c6(d2.a);
        List listListOfNotNull = CollectionsKt.listOfNotNull((Object[]) upVarArr);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListOfNotNull) {
            if (((up) obj).isSupported()) {
                arrayList.add(obj);
            }
        }
        this.d = arrayList;
    }

    @Override // spi.zk
    @NotNull
    public final l3 b(@NotNull X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        x xVarA = x.d.a(trustManager);
        return xVarA != null ? xVarA : super.b(trustManager);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList, java.util.List<spi.up>] */
    @Override // spi.zk
    public final void d(@NotNull SSLSocket sslSocket, @Nullable String str, @NotNull List<? extends Protocol> protocols) {
        Object next;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        Iterator it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((up) next).a(sslSocket)) {
                    break;
                }
            }
        }
        up upVar = (up) next;
        if (upVar != null) {
            upVar.c(sslSocket, str, protocols);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<spi.up>] */
    @Override // spi.zk
    @Nullable
    public final String f(@NotNull SSLSocket sslSocket) {
        Object next;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Iterator it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((up) next).a(sslSocket)) {
                break;
            }
        }
        up upVar = (up) next;
        if (upVar != null) {
            return upVar.b(sslSocket);
        }
        return null;
    }

    @Override // spi.zk
    @SuppressLint({"NewApi"})
    public final boolean h(@NotNull String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(hostname);
    }
}
