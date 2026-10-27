package p;

import com.spotify.automotive.base.session.worker.LogoutWorker;
import com.spotify.connectivity.connectiontypeflags.ConnectionTypeFlagsModule_Companion_ProvideNetCapabilitiesValidatedDisabledValueFactory;
import com.spotify.connectivity.connectiontypeflags.ConnectionTypeFlagsModule_Companion_ProvideShouldUseSingleThreadFactory;
import com.spotify.connectivity.connectiontypeflags.ConnectionTypePropertiesReader_Factory;
import com.spotify.connectivity.platformconnectiontype.ConnectionApisServiceScopeModule_ProvideConnectionApisFactory;
import com.spotify.connectivity.platformconnectiontype.ConnectionApisServiceScopeModule_ProvideFlightModeEnabledMonitorFactory;
import com.spotify.connectivity.platformconnectiontype.ConnectionTypeModule_ProvideConnectivityListenerFactory;
import com.spotify.connectivity.platformconnectiontype.ConnectionTypeModule_ProvideConnectivityUtilFactory;
import com.spotify.connectivity.platformconnectiontype.ConnectionTypeModule_ProvideInternetMonitorFactory;
import com.spotify.connectivity.platformconnectiontype.ConnectionTypeModule_ProvidePlatformConnectionTypePropertiesFactory;
import com.spotify.connectivity.platformconnectiontype.ConnectionTypeModule_ProvideSpotifyConnectivityManagerFactory;

/* JADX INFO: loaded from: classes.dex */
public final class o50 implements a7 {
    public final wy1 a;
    public final wy1 b;
    public final me2 c;
    public final me2 d;
    public final ConnectionTypePropertiesReader_Factory e;
    public final ConnectionTypeFlagsModule_Companion_ProvideNetCapabilitiesValidatedDisabledValueFactory f;
    public final ConnectionTypeModule_ProvidePlatformConnectionTypePropertiesFactory g;
    public final ConnectionTypeModule_ProvideConnectivityUtilFactory h;
    public final ConnectionTypeModule_ProvideConnectivityListenerFactory i;
    public final wy1 j;
    public final ConnectionTypeModule_ProvideInternetMonitorFactory k;
    public final wy1 l;
    public final wy1 m;
    public final wy1 n;
    public final dt1 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ct1 f163p;
    public final wy1 q;
    public final jj0 r;
    public final ct1 s;
    public final s6 t;
    public final wy1 u;
    public final s6 v;
    public final nd w;
    public final me2 x;
    public final wy1 y;

    public o50(h50 h50Var) {
        wy1 wy1VarB = ua0.b(f60.a);
        this.a = wy1VarB;
        int i = 6;
        int i2 = 5;
        wy1 wy1VarB2 = ua0.b(new ed(new nd(h50Var.l, i), wy1VarB, h50Var.o, i2));
        this.b = wy1VarB2;
        this.c = new me2(this.a, wy1VarB2, i2);
        int i3 = 15;
        this.d = new me2(h50Var.v, h50Var.w, i3);
        ConnectionTypePropertiesReader_Factory connectionTypePropertiesReader_FactoryCreate = ConnectionTypePropertiesReader_Factory.create(h50Var.x);
        this.e = connectionTypePropertiesReader_FactoryCreate;
        this.f = ConnectionTypeFlagsModule_Companion_ProvideNetCapabilitiesValidatedDisabledValueFactory.create(connectionTypePropertiesReader_FactoryCreate);
        ConnectionTypeModule_ProvidePlatformConnectionTypePropertiesFactory connectionTypeModule_ProvidePlatformConnectionTypePropertiesFactoryCreate = ConnectionTypeModule_ProvidePlatformConnectionTypePropertiesFactory.create(this.f, ConnectionTypeFlagsModule_Companion_ProvideShouldUseSingleThreadFactory.create(this.e));
        this.g = connectionTypeModule_ProvidePlatformConnectionTypePropertiesFactoryCreate;
        ConnectionTypeModule_ProvideConnectivityUtilFactory connectionTypeModule_ProvideConnectivityUtilFactoryCreate = ConnectionTypeModule_ProvideConnectivityUtilFactory.create(connectionTypeModule_ProvidePlatformConnectionTypePropertiesFactoryCreate);
        this.h = connectionTypeModule_ProvideConnectivityUtilFactoryCreate;
        this.i = ConnectionTypeModule_ProvideConnectivityListenerFactory.create(h50Var.l, connectionTypeModule_ProvideConnectivityUtilFactoryCreate, this.g);
        wy1 wy1VarB3 = ua0.b(ConnectionApisServiceScopeModule_ProvideFlightModeEnabledMonitorFactory.create(h50Var.l));
        this.j = wy1VarB3;
        this.k = ConnectionTypeModule_ProvideInternetMonitorFactory.create(this.i, wy1VarB3, h50Var.y);
        this.l = ua0.b(ConnectionApisServiceScopeModule_ProvideConnectionApisFactory.create(this.i, this.j, h50Var.y, this.k, ConnectionTypeModule_ProvideSpotifyConnectivityManagerFactory.create(h50Var.l, this.h, f60.e)));
        int i4 = 8;
        int i5 = 4;
        wy1 wy1VarB4 = ua0.b(new qt(h50Var.m, this.d, new m0(ua0.b(new ed(this.l, ua0.b(new ct1(h50Var.R, i4)), h50Var.S, i)), i2), new m0(h50Var.l, i5), 4));
        this.m = wy1VarB4;
        int i6 = 16;
        this.n = ua0.b(new me2(wy1VarB4, h50Var.U, i6));
        this.o = new dt1(new m0(this.m, 27));
        this.f163p = new ct1(new ct1(h50Var.n, 0), i5);
        wy1 wy1VarB5 = ua0.b(q30.b);
        this.q = wy1VarB5;
        int i7 = 7;
        m0 m0Var = new m0(nd2.a(new nd(wy1VarB5, i7)), 26);
        p50 p50Var = new p50(new ct1(h50Var.S, 3));
        b51 b51Var = q50.a;
        jj0 jj0Var = new jj0(h50Var.l, this.o, this.f163p, m0Var, p50Var, b51Var, b51Var);
        this.r = jj0Var;
        int i8 = 2;
        ct1 ct1Var = new ct1(jj0Var, i8);
        this.s = ct1Var;
        int i9 = 1;
        this.t = new s6(ct1Var, i9);
        wy1 wy1VarB6 = ua0.b(new me2(this.a, this.b, 13));
        this.u = ua0.b(new qt(this.t, wy1VarB6, new ed(h50Var.l, wy1VarB6, b51Var, i6), zu.g, 3));
        this.v = new s6(this.s, i8);
        nd ndVar = new nd(new nd(new ct1(new ct1(this.r, i9), i6), 9), i4);
        this.w = ndVar;
        this.x = new me2(h50Var.m, new qu1(this.c, this.n, this.u, h50Var.D, this.v, ndVar, 4), i5);
        wy1 wy1VarB7 = ua0.b(new ct1(this.m, i7));
        this.y = ua0.b(new id1(h50Var.l, h50Var.n, h50Var.T, this.x, this.q, ua0.b(new me2(ua0.b(new ed(he1.h, this.m, wy1VarB7, i3)), h50Var.W, i4)), this.w, this.l, 0));
    }

    @Override // p.a7
    public final void a(Object obj) {
        ((LogoutWorker) obj).o = (ad1) this.y.get();
    }
}
