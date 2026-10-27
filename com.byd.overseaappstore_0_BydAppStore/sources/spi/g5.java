package spi;

import android.content.pm.PackageInstaller;
import com.byd.overseaappstore.broadcast.LanguageChangeBroadcastReceiver;
import com.byd.overseaappstore.broadcast.SoftwareBroadcastReceiver;
import com.byd.overseaappstore.broadcast.SoftwareRemoveBroadcastReceiver;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.data.generator.DownloadUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.generator.ErrorUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.generator.SearchUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.generator.SoftwareStateDataParserGenerator;
import com.byd.overseaappstore.module.ability.data.generator.SoftwareUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.DbDataSoftwareStateInterceptor;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.DeviceDataSoftwareStateInterceptor;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.PoolDataSoftwareStateInterceptor;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataInterceptorFactory;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.module.ability.util.db.OverseaAppStoreDb;
import com.byd.overseaappstore.module.ability.util.notify.alone.SoftwareNeedStartInstallNotify;
import com.byd.overseaappstore.module.ability.util.notify.alone.language.LanguageChangeStateMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.line.ExecutorHciDispatchMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.line.ExecutorHciLineDispatch;
import com.byd.overseaappstore.module.ability.util.notify.alone.sequence.ExecutorInitSyncFinishDispatcher;
import com.byd.overseaappstore.module.ability.util.notify.alone.sequence.ExecutorSequenceDispatcherMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.sequence.ExecutorUpdateFetchFinishDispatcher;
import com.byd.overseaappstore.module.ability.util.notify.alone.task.ExecutorTaskSchedule;
import com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.gear.ExecutorNotPGearState;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.ExecutorIUUState;
import com.byd.overseaappstore.module.ability.util.notify.router.net.ExecutorNetContextState;
import com.byd.overseaappstore.module.ability.util.notify.router.storage.ExecutorStorageState;
import com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat;
import com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess;
import com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCachePreTreat;
import com.byd.overseaappstore.module.ability.util.operate.factory.lineb.ExecutorHciCacheProcess;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;

/* JADX INFO: loaded from: classes.dex */
public final class g5 extends sk {
    public ll<Retrofit> A;
    public ll<bq> B;
    public ll<zp> C;
    public ll<aq> D;
    public ll<SoftwareRepository> E;
    public ll<SoftwareDataInterceptorFactory> F;
    public ll<SoftwareStateDataParserGenerator> G;
    public ll<SoftwareUiStateGenerator> H;
    public ll<ErrorUiStateGenerator> I;
    public ll<x6> J;
    public ll<h7> K;
    public ll<DownloadUiStateGenerator> L;
    public ll<ExecutorSoftwareNewTaskInTrigger> M;
    public ll<g9> N;
    public ll<ExecutorHciDispatchMgr> O;
    public ll<q9> P;
    public ll<s9> Q;
    public ll<b1> R;
    public ll<e1> S;
    public ll<ExecutorSequenceDispatcherMgr> T;
    public ll<ft> U;
    public ll<lo> V;
    public ll<SearchUiStateGenerator> W;
    public ll<hm> X;
    public final h0 a;
    public final g5 b;
    public ll<LanguageChangeStateMgr> c;
    public ll<xu> d;
    public ll<yu> e;
    public ll<z3> f;
    public ll<ExecutorNetContextState> g;
    public ll<ExecutorNotPGearState> h;
    public ll<ExecutorStorageState> i;
    public ll<ExecutorIUUState> j;
    public ll<ExecutorDialogRouterMgr> k;
    public ll<DataGenerator> l;
    public ll<OverseaAppStoreDb> m;
    public ll<tr> n;
    public ll<mq> o;
    public ll<xp> p;
    public ll<yp> q;
    public ll<i9> r;
    public ll<oa> s;
    public ll<PackageInstaller> t;
    public ll<aa> u;
    public ll<ExecutorHciLineDispatch> v;
    public ll<ExecutorTaskSchedule> w;
    public ll<SoftwareNeedStartInstallNotify> x;
    public ll<l9> y;
    public ll<o8> z;

    public static final class a<T> implements ll<T> {
        public final g5 a;
        public final int b;

        public a(g5 g5Var, int i) {
            this.a = g5Var;
            this.b = i;
        }

        @Override // spi.ll
        public final T get() {
            switch (this.b) {
                case 0:
                    return (T) new LanguageChangeStateMgr();
                case 1:
                    return (T) new ExecutorDialogRouterMgr(this.a.g.get(), this.a.h.get(), this.a.i.get(), this.a.j.get());
                case 2:
                    return (T) new ExecutorNetContextState(this.a.f.get(), i0.a(this.a.a));
                case 3:
                    return (T) new z3(this.a.e.get(), i0.a(this.a.a));
                case 4:
                    return (T) new xu();
                case 5:
                    return (T) new ExecutorNotPGearState();
                case 6:
                    return (T) new ExecutorStorageState();
                case 7:
                    return (T) new ExecutorIUUState();
                case 8:
                    return (T) new DataGenerator();
                case 9:
                    g5 g5Var = this.a;
                    na naVar = new na(g5Var.s.get(), g5Var.t.get(), g5Var.k.get(), g5Var.l.get(), g5Var.r.get());
                    g5 g5Var2 = this.a;
                    return (T) new aa(naVar, new ia(g5Var2.s.get(), g5Var2.t.get(), g5Var2.l.get(), g5Var2.k.get(), g5Var2.r.get()), this.a.k.get(), this.a.r.get(), this.a.l.get());
                case 10:
                    return (T) new oa(this.a.r.get());
                case 11:
                    return (T) new i9(this.a.q.get(), this.a.l.get());
                case 12:
                    return (T) new xp(this.a.n.get(), this.a.o.get(), i0.a(this.a.a));
                case 13:
                    OverseaAppStoreDb overseaAppStoreDb = this.a.m.get();
                    Objects.requireNonNull(s5.a);
                    Intrinsics.checkNotNullParameter(overseaAppStoreDb, "overseaAppStoreDb");
                    T t = (T) overseaAppStoreDb.d();
                    Objects.requireNonNull(t, "Cannot return null from a non-@Nullable @Provides method");
                    return t;
                case 14:
                    return (T) t5.a(i0.a(this.a.a));
                case 15:
                    OverseaAppStoreDb overseaAppStoreDb2 = this.a.m.get();
                    Objects.requireNonNull(s5.a);
                    Intrinsics.checkNotNullParameter(overseaAppStoreDb2, "overseaAppStoreDb");
                    T t2 = (T) overseaAppStoreDb2.c();
                    Objects.requireNonNull(t2, "Cannot return null from a non-@Nullable @Provides method");
                    return t2;
                case 16:
                    return (T) wk.a(i0.a(this.a.a), this.a.l.get(), this.a.k.get());
                case 17:
                    return (T) new l9(this.a.r.get(), this.a.v.get(), this.a.l.get(), this.a.w.get(), this.a.x.get());
                case 18:
                    return (T) new ExecutorHciLineDispatch();
                case 19:
                    return (T) new ExecutorTaskSchedule();
                case 20:
                    return (T) new SoftwareNeedStartInstallNotify();
                case 21:
                    return (T) new o8(new q8(this.a.k.get()), new r8(this.a.k.get()));
                case 22:
                    return (T) new SoftwareRepository(this.a.B.get(), this.a.r.get(), this.a.D.get(), this.a.l.get());
                case 23:
                    return (T) pj.a(this.a.A.get());
                case 24:
                    return (T) nj.a(i0.a(this.a.a), this.a.f.get());
                case 25:
                    return (T) new zp(i0.a(this.a.a));
                case 26:
                    return (T) new SoftwareUiStateGenerator(i0.a(this.a.a), this.a.E.get(), this.a.s.get(), this.a.l.get(), this.a.G.get());
                case 27:
                    return (T) new SoftwareStateDataParserGenerator(this.a.F.get());
                case 28:
                    DeviceDataSoftwareStateInterceptor deviceDataSoftwareStateInterceptor = new DeviceDataSoftwareStateInterceptor();
                    g5 g5Var3 = this.a;
                    PoolDataSoftwareStateInterceptor poolDataSoftwareStateInterceptor = new PoolDataSoftwareStateInterceptor(i0.a(g5Var3.a), g5Var3.s.get());
                    g5 g5Var4 = this.a;
                    return (T) new SoftwareDataInterceptorFactory(deviceDataSoftwareStateInterceptor, poolDataSoftwareStateInterceptor, new DbDataSoftwareStateInterceptor(g5Var4.s.get(), g5Var4.r.get(), g5Var4.l.get(), i0.a(g5Var4.a)));
                case 29:
                    return (T) new ErrorUiStateGenerator();
                case 30:
                    return (T) new s9(this.a.P.get());
                case 31:
                    g5 g5Var5 = this.a;
                    return (T) new q9(new y9(new ExecutorHciWorkPreTreat(g5Var5.s.get(), g5Var5.j()), new ExecutorHciWorkProcess(g5Var5.s.get(), g5Var5.r.get(), g5Var5.N.get(), g5Var5.l.get(), i0.a(g5Var5.a), g5Var5.O.get(), g5Var5.j()), new u9(g5Var5.r.get(), g5Var5.K.get(), g5Var5.l.get(), g5Var5.s.get(), g5Var5.w.get(), g5Var5.M.get(), i0.a(g5Var5.a), g5Var5.y.get(), g5Var5.k.get(), g5Var5.u.get(), g5Var5.O.get(), g5Var5.x.get()), g5Var5.l.get()), this.a.l.get(), g5.i(this.a));
                case 32:
                    return (T) new h7(this.a.J.get());
                case 33:
                    return (T) mj.a(this.a.A.get());
                case 34:
                    return (T) new DownloadUiStateGenerator(i0.a(this.a.a), this.a.l.get());
                case 35:
                    g5 g5Var6 = this.a;
                    f9 f9Var = new f9(g5Var6.s.get(), g5Var6.M.get(), g5Var6.r.get());
                    g5 g5Var7 = this.a;
                    d9 d9Var = new d9(g5Var7.s.get(), g5Var7.M.get(), g5Var7.r.get());
                    g5 g5Var8 = this.a;
                    e9 e9Var = new e9(g5Var8.s.get(), g5Var8.M.get(), g5Var8.r.get());
                    g5 g5Var9 = this.a;
                    h9 h9Var = new h9(g5Var9.s.get(), g5Var9.r.get());
                    g5 g5Var10 = this.a;
                    return (T) new g9(f9Var, d9Var, e9Var, h9Var, new c9(g5Var10.s.get(), g5Var10.r.get()));
                case 36:
                    return (T) new ExecutorSoftwareNewTaskInTrigger();
                case 37:
                    return (T) new ExecutorHciDispatchMgr(g5.i(this.a), this.a.v.get());
                case 38:
                    return (T) new e1(this.a.R.get());
                case 39:
                    return (T) lj.a(this.a.A.get());
                case 40:
                    return (T) new ExecutorSequenceDispatcherMgr(new ExecutorInitSyncFinishDispatcher(), new ExecutorUpdateFetchFinishDispatcher());
                case 41:
                    return (T) new ft(new gt());
                case 42:
                    return (T) oj.a(this.a.A.get());
                case 43:
                    return (T) new SearchUiStateGenerator(i0.a(this.a.a), this.a.E.get(), this.a.l.get(), this.a.G.get());
                case 44:
                    return (T) new hm(this.a.l.get(), i0.a(this.a.a));
                default:
                    throw new AssertionError(this.b);
            }
        }
    }

    private g5(h0 h0Var) {
        this.b = this;
        this.a = h0Var;
        this.c = d.o(this, 0);
        a aVar = new a(this, 4);
        this.d = aVar;
        this.e = u6.a(aVar);
        this.f = d.o(this, 3);
        this.g = d.o(this, 2);
        this.h = d.o(this, 5);
        this.i = d.o(this, 6);
        this.j = d.o(this, 7);
        this.k = d.o(this, 1);
        this.l = d.o(this, 8);
        this.m = d.o(this, 14);
        this.n = d.o(this, 13);
        this.o = d.o(this, 15);
        a aVar2 = new a(this, 12);
        this.p = aVar2;
        this.q = u6.a(aVar2);
        this.r = d.o(this, 11);
        this.s = d.o(this, 10);
        this.t = d.o(this, 16);
        this.u = d.o(this, 9);
        this.v = d.o(this, 18);
        this.w = d.o(this, 19);
        this.x = d.o(this, 20);
        this.y = d.o(this, 17);
        this.z = d.o(this, 21);
        this.A = d.o(this, 24);
        this.B = d.o(this, 23);
        a aVar3 = new a(this, 25);
        this.C = aVar3;
        this.D = u6.a(aVar3);
        this.E = d.o(this, 22);
        this.F = d.o(this, 28);
        this.G = d.o(this, 27);
        this.H = d.o(this, 26);
        this.I = d.o(this, 29);
        this.J = d.o(this, 33);
        this.K = d.o(this, 32);
        this.L = d.o(this, 34);
        this.M = d.o(this, 36);
        this.N = d.o(this, 35);
        this.O = d.o(this, 37);
        this.P = d.o(this, 31);
        this.Q = d.o(this, 30);
        this.R = d.o(this, 39);
        this.S = d.o(this, 38);
        this.T = d.o(this, 40);
        this.U = d.o(this, 41);
        this.V = d.o(this, 42);
        this.W = d.o(this, 43);
        this.X = d.o(this, 44);
    }

    public /* synthetic */ g5(h0 h0Var, e eVar) {
        this(h0Var);
    }

    public static o9 i(g5 g5Var) {
        return new o9(g5Var.r.get(), g5Var.l.get(), g5Var.s.get(), new ExecutorHciCachePreTreat(g5Var.j()), new ExecutorHciCacheProcess(g5Var.N.get(), g5Var.j()), new n9(g5Var.r.get(), g5Var.K.get(), g5Var.l.get(), g5Var.s.get(), g5Var.w.get(), g5Var.M.get(), g5Var.y.get(), i0.a(g5Var.a)));
    }

    @Override // spi.yo.a
    public final xo a() {
        return new e5(this.b, null);
    }

    @Override // spi.rq
    public final void b(SoftwareRemoveBroadcastReceiver softwareRemoveBroadcastReceiver) {
        softwareRemoveBroadcastReceiver.c = this.k.get();
        softwareRemoveBroadcastReceiver.d = this.l.get();
        softwareRemoveBroadcastReceiver.e = this.u.get();
    }

    @Override // spi.jg
    public final void c(LanguageChangeBroadcastReceiver languageChangeBroadcastReceiver) {
        languageChangeBroadcastReceiver.c = this.c.get();
    }

    @Override // spi.pc.a
    public final Set<Boolean> d() {
        return Collections.emptySet();
    }

    @Override // spi.wp
    public final void e(SoftwareBroadcastReceiver softwareBroadcastReceiver) {
        softwareBroadcastReceiver.c = this.k.get();
        softwareBroadcastReceiver.d = this.l.get();
        softwareBroadcastReceiver.e = this.u.get();
    }

    @Override // spi.rj
    public final void f() {
    }

    @Override // spi.q.a
    public final o g() {
        return new z4(this.b, null);
    }

    @Override // spi.nk
    public final void h() {
    }

    public final p9 j() {
        return new p9(this.s.get(), this.k.get(), this.l.get(), i0.a(this.a), this.K.get(), this.L.get());
    }
}
