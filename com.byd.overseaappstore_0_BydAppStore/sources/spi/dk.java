package spi;

import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.support.v4.media.session.PlaybackStateCompat;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.c3;
import spi.u0;
import spi.u8;

/* JADX INFO: loaded from: classes2.dex */
public final class dk implements Cloneable, c3.a {
    public final int A;
    public final int B;
    public final long C;

    @NotNull
    public final sn D;

    @NotNull
    public final p6 a;

    @NotNull
    public final c4 b;

    @NotNull
    public final List<ag> c;

    @NotNull
    public final List<ag> d;

    @NotNull
    public final u8.c e;
    public final boolean f;

    @NotNull
    public final u0 g;
    public final boolean h;
    public final boolean i;

    @NotNull
    public final q4 j;

    @Nullable
    public final w2 k;

    @NotNull
    public final s6 l;

    @Nullable
    public final Proxy m;

    @NotNull
    public final ProxySelector n;

    @NotNull
    public final u0 o;

    @NotNull
    public final SocketFactory p;
    public final SSLSocketFactory q;

    @Nullable
    public final X509TrustManager r;

    @NotNull
    public final List<d4> s;

    @NotNull
    public final List<Protocol> t;

    @NotNull
    public final HostnameVerifier u;

    @NotNull
    public final m3 v;

    @Nullable
    public final l3 w;
    public final int x;
    public final int y;
    public final int z;
    public static final b G = new b(null);

    @NotNull
    public static final List<Protocol> E = uu.k(Protocol.HTTP_2, Protocol.HTTP_1_1);

    @NotNull
    public static final List<d4> F = uu.k(d4.e, d4.f);

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public dk() {
        this(new a());
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public dk(@org.jetbrains.annotations.NotNull spi.dk.a r6) throws java.security.NoSuchAlgorithmException, java.security.KeyStoreException {
        /*
            Method dump skipped, instruction units count: 509
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.dk.<init>(spi.dk$a):void");
    }

    @Override // spi.c3.a
    @NotNull
    public final c3 a(@NotNull im request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return new vl(this, request, false);
    }

    @NotNull
    public final Object clone() {
        return super.clone();
    }

    public static final class a {
        public int A;
        public int B;
        public long C;

        @Nullable
        public sn D;

        @NotNull
        public p6 a;

        @NotNull
        public c4 b;

        @NotNull
        public final List<ag> c;

        @NotNull
        public final List<ag> d;

        @NotNull
        public u8.c e;
        public boolean f;

        @NotNull
        public u0 g;
        public boolean h;
        public boolean i;

        @NotNull
        public q4 j;

        @Nullable
        public w2 k;

        @NotNull
        public s6 l;

        @Nullable
        public Proxy m;

        @Nullable
        public ProxySelector n;

        @NotNull
        public u0 o;

        @NotNull
        public SocketFactory p;

        @Nullable
        public SSLSocketFactory q;

        @Nullable
        public X509TrustManager r;

        @NotNull
        public List<d4> s;

        @NotNull
        public List<? extends Protocol> t;

        @NotNull
        public HostnameVerifier u;

        @NotNull
        public m3 v;

        @Nullable
        public l3 w;
        public int x;
        public int y;
        public int z;

        public a() {
            this.a = new p6();
            this.b = new c4();
            this.c = new ArrayList();
            this.d = new ArrayList();
            u8.a asFactory = u8.a;
            byte[] bArr = uu.a;
            Intrinsics.checkNotNullParameter(asFactory, "$this$asFactory");
            this.e = new ru(asFactory);
            this.f = true;
            u0.a.C0142a c0142a = u0.a;
            this.g = c0142a;
            this.h = true;
            this.i = true;
            this.j = q4.a;
            this.l = s6.a;
            this.o = c0142a;
            SocketFactory socketFactory = SocketFactory.getDefault();
            Intrinsics.checkNotNullExpressionValue(socketFactory, "SocketFactory.getDefault()");
            this.p = socketFactory;
            Objects.requireNonNull(dk.G);
            this.s = dk.F;
            this.t = dk.E;
            this.u = ck.a;
            this.v = m3.c;
            this.y = TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE;
            this.z = TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE;
            this.A = TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE;
            this.C = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public a(@NotNull dk okHttpClient) {
            this();
            Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
            this.a = okHttpClient.a;
            this.b = okHttpClient.b;
            CollectionsKt.addAll(this.c, okHttpClient.c);
            CollectionsKt.addAll(this.d, okHttpClient.d);
            this.e = okHttpClient.e;
            this.f = okHttpClient.f;
            this.g = okHttpClient.g;
            this.h = okHttpClient.h;
            this.i = okHttpClient.i;
            this.j = okHttpClient.j;
            this.k = okHttpClient.k;
            this.l = okHttpClient.l;
            this.m = okHttpClient.m;
            this.n = okHttpClient.n;
            this.o = okHttpClient.o;
            this.p = okHttpClient.p;
            this.q = okHttpClient.q;
            this.r = okHttpClient.r;
            this.s = okHttpClient.s;
            this.t = okHttpClient.t;
            this.u = okHttpClient.u;
            this.v = okHttpClient.v;
            this.w = okHttpClient.w;
            this.x = okHttpClient.x;
            this.y = okHttpClient.y;
            this.z = okHttpClient.z;
            this.A = okHttpClient.A;
            this.B = okHttpClient.B;
            this.C = okHttpClient.C;
            this.D = okHttpClient.D;
        }
    }
}
