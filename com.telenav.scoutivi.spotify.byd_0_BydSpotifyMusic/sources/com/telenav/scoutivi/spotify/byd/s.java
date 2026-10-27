package com.telenav.scoutivi.spotify.byd;

import android.app.Activity;
import android.app.Service;
import androidx.fragment.app.Fragment;
import com.vivid.carcontroller.byd.dx.DeviceBroadcastReceiver;
import com.vivid.carcontroller.byd.dx.RestoreStateService;
import com.vivid.spotify.MainActivity;
import com.vivid.spotify.MainViewModel;
import com.vivid.spotify.a0;
import com.vivid.spotify.b0;
import com.vivid.spotify.c0;
import com.vivid.spotify.common.ui.DoubleGridViewModel;
import com.vivid.spotify.details.DetailChildViewModel;
import com.vivid.spotify.details.DetailFragment;
import com.vivid.spotify.details.DetailViewModel;
import com.vivid.spotify.nowplaying.NowPlayingFragment;
import com.vivid.spotify.nowplaying.NowPlayingViewModel;
import com.vivid.spotify.queue.QueueFragment;
import com.vivid.spotify.queue.QueueViewModel;
import com.vivid.spotify.rootui.GridFragment;
import com.vivid.spotify.rootui.GridViewModel;
import com.vivid.spotify.rootui.MainFragment;
import com.vivid.spotify.rootui.MainUIViewModel;
import com.vivid.spotify.rootui.category.CategoryFragment;
import com.vivid.spotify.rootui.category.CategoryViewModel;
import com.vivid.spotify.rootui.v;
import com.vivid.spotify.search.SearchFragment;
import com.vivid.spotify.search.SearchListViewModel;
import com.vivid.spotify.search.SearchViewModel;
import com.vivid.spotify.search.u;
import com.vivid.spotify.service.MediaManagerService;
import com.vivid.spotify.z;
import i3.y;
import java.util.Map;
import java.util.Set;
import m4.a;

/* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
/* JADX INFO: loaded from: classes.dex */
public final class s extends com.telenav.scoutivi.spotify.byd.g {
    private f5.a<g4.g> A;
    private f5.a<RestoreStateService.a> B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a0 f5034a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final n4.a f5035b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final j3.a f5036c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.telenav.scoutivi.spotify.byd.l f5037d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final s f5038e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private f5.a<y> f5039f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private f5.a<i3.s> f5040g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private f5.a<i3.b> f5041h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private f5.a<i3.m> f5042i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private f5.a<com.vivid.carcontroller.byd.dx.g> f5043j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private f5.a<z> f5044k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private f5.a<com.vivid.carcontroller.byd.dx.a> f5045l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private f5.a<com.vivid.carcontroller.byd.dx.b> f5046m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private f5.a<com.vivid.carcontroller.byd.dx.h> f5047n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private f5.a<i3.j> f5048o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private f5.a<com.vivid.carcontroller.byd.dx.l> f5049p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private f5.a<f4.b> f5050q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private f5.a<a4.a> f5051r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private f5.a<f4.m> f5052s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private f5.a<c0> f5053t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private f5.a<com.vivid.spotify.b> f5054u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private f5.a<f4.c> f5055v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private f5.a<g4.f> f5056w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private f5.a<b4.a> f5057x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private f5.a<c4.h> f5058y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private f5.a<c4.g> f5059z;

    /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
    private static final class b implements l4.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final s f5060a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f5061b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Activity f5062c;

        @Override // l4.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public b b(Activity activity) {
            this.f5062c = (Activity) p4.d.b(activity);
            return this;
        }

        @Override // l4.a
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public com.telenav.scoutivi.spotify.byd.c a() {
            p4.d.a(this.f5062c, Activity.class);
            return new c(this.f5061b, this.f5062c);
        }

        private b(s sVar, e eVar) {
            this.f5060a = sVar;
            this.f5061b = eVar;
        }
    }

    /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
    private static final class c extends com.telenav.scoutivi.spotify.byd.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final s f5063a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f5064b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c f5065c;

        private com.vivid.spotify.common.base.a f(com.vivid.spotify.common.base.a aVar) {
            com.vivid.spotify.common.base.c.a(aVar, (a4.a) this.f5063a.f5051r.get());
            return aVar;
        }

        private MainActivity g(MainActivity mainActivity) {
            com.vivid.spotify.common.base.c.a(mainActivity, (a4.a) this.f5063a.f5051r.get());
            com.vivid.spotify.i.b(mainActivity, (f4.m) this.f5063a.f5052s.get());
            com.vivid.spotify.i.c(mainActivity, (i3.m) this.f5063a.f5042i.get());
            com.vivid.spotify.i.f(mainActivity, this.f5063a.s0());
            com.vivid.spotify.i.d(mainActivity, (c0) this.f5063a.f5053t.get());
            com.vivid.spotify.i.e(mainActivity, (z) this.f5063a.f5044k.get());
            com.vivid.spotify.i.a(mainActivity, (com.vivid.spotify.b) this.f5063a.f5054u.get());
            return mainActivity;
        }

        @Override // m4.a.InterfaceC0127a
        public a.c a() {
            return m4.b.a(n4.b.a(this.f5063a.f5035b), e(), new l(this.f5064b));
        }

        @Override // com.vivid.spotify.h
        public void b(MainActivity mainActivity) {
            g(mainActivity);
        }

        @Override // com.vivid.spotify.common.base.b
        public void c(com.vivid.spotify.common.base.a aVar) {
            f(aVar);
        }

        @Override // dagger.hilt.android.internal.managers.g.a
        public l4.c d() {
            return new g(this.f5064b, this.f5065c);
        }

        public Set<String> e() {
            return p4.e.c(12).a(com.vivid.spotify.rootui.category.g.a()).a(com.vivid.spotify.details.i.a()).a(com.vivid.spotify.details.r.a()).a(com.vivid.spotify.common.ui.b.a()).a(com.vivid.spotify.rootui.i.a()).a(com.vivid.spotify.rootui.category.q.a()).a(v.a()).a(com.vivid.spotify.k.a()).a(com.vivid.spotify.nowplaying.i.a()).a(com.vivid.spotify.queue.h.a()).a(com.vivid.spotify.search.s.a()).a(u.a()).b();
        }

        private c(s sVar, e eVar, Activity activity) {
            this.f5065c = this;
            this.f5063a = sVar;
            this.f5064b = eVar;
        }
    }

    /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
    private static final class d implements l4.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final s f5066a;

        @Override // l4.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public com.telenav.scoutivi.spotify.byd.d a() {
            return new e();
        }

        private d(s sVar) {
            this.f5066a = sVar;
        }
    }

    /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
    private static final class e extends com.telenav.scoutivi.spotify.byd.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final s f5067a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f5068b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private f5.a f5069c;

        /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
        private static final class a<T> implements f5.a<T> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final s f5070a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final e f5071b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private final int f5072c;

            a(s sVar, e eVar, int i7) {
                this.f5070a = sVar;
                this.f5071b = eVar;
                this.f5072c = i7;
            }

            @Override // f5.a
            public T get() {
                if (this.f5072c == 0) {
                    return (T) dagger.hilt.android.internal.managers.c.a();
                }
                throw new AssertionError(this.f5072c);
            }
        }

        private void c() {
            this.f5069c = p4.b.a(new a(this.f5067a, this.f5068b, 0));
        }

        @Override // dagger.hilt.android.internal.managers.a.InterfaceC0063a
        public l4.a a() {
            return new b(this.f5068b);
        }

        @Override // dagger.hilt.android.internal.managers.b.d
        public i4.a b() {
            return (i4.a) this.f5069c.get();
        }

        private e(s sVar) {
            this.f5068b = this;
            this.f5067a = sVar;
            c();
        }
    }

    /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private n4.a f5073a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private com.telenav.scoutivi.spotify.byd.l f5074b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private j3.a f5075c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private a0 f5076d;

        public f a(n4.a aVar) {
            this.f5073a = (n4.a) p4.d.b(aVar);
            return this;
        }

        public com.telenav.scoutivi.spotify.byd.g b() {
            p4.d.a(this.f5073a, n4.a.class);
            if (this.f5074b == null) {
                this.f5074b = new com.telenav.scoutivi.spotify.byd.l();
            }
            if (this.f5075c == null) {
                this.f5075c = new j3.a();
            }
            if (this.f5076d == null) {
                this.f5076d = new a0();
            }
            return new s(this.f5073a, this.f5074b, this.f5075c, this.f5076d);
        }

        private f() {
        }
    }

    /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
    private static final class g implements l4.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final s f5077a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f5078b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c f5079c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Fragment f5080d;

        @Override // l4.c
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public com.telenav.scoutivi.spotify.byd.e a() {
            p4.d.a(this.f5080d, Fragment.class);
            return new h(this.f5078b, this.f5079c, this.f5080d);
        }

        @Override // l4.c
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public g b(Fragment fragment) {
            this.f5080d = (Fragment) p4.d.b(fragment);
            return this;
        }

        private g(s sVar, e eVar, c cVar) {
            this.f5077a = sVar;
            this.f5078b = eVar;
            this.f5079c = cVar;
        }
    }

    /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
    private static final class h extends com.telenav.scoutivi.spotify.byd.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final s f5081a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f5082b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c f5083c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final h f5084d;

        private com.vivid.spotify.details.e l(com.vivid.spotify.details.e eVar) {
            com.vivid.spotify.details.g.a(eVar, (f4.m) this.f5081a.f5052s.get());
            com.vivid.spotify.details.g.b(eVar, (i3.m) this.f5081a.f5042i.get());
            com.vivid.spotify.details.g.c(eVar, this.f5081a.s0());
            return eVar;
        }

        private DetailFragment m(DetailFragment detailFragment) {
            com.vivid.spotify.details.m.a(detailFragment, (i3.m) this.f5081a.f5042i.get());
            return detailFragment;
        }

        private GridFragment n(GridFragment gridFragment) {
            com.vivid.spotify.rootui.d.a(gridFragment, (f4.m) this.f5081a.f5052s.get());
            com.vivid.spotify.rootui.d.b(gridFragment, (i3.m) this.f5081a.f5042i.get());
            return gridFragment;
        }

        private com.vivid.spotify.rootui.category.i o(com.vivid.spotify.rootui.category.i iVar) {
            com.vivid.spotify.rootui.category.k.a(iVar, (f4.m) this.f5081a.f5052s.get());
            com.vivid.spotify.rootui.category.k.b(iVar, (i3.m) this.f5081a.f5042i.get());
            return iVar;
        }

        private MainFragment p(MainFragment mainFragment) {
            com.vivid.spotify.rootui.r.a(mainFragment, (f4.m) this.f5081a.f5052s.get());
            return mainFragment;
        }

        private NowPlayingFragment q(NowPlayingFragment nowPlayingFragment) {
            com.vivid.spotify.nowplaying.f.a(nowPlayingFragment, (com.vivid.spotify.b) this.f5081a.f5054u.get());
            com.vivid.spotify.nowplaying.f.c(nowPlayingFragment, (i3.m) this.f5081a.f5042i.get());
            com.vivid.spotify.nowplaying.f.d(nowPlayingFragment, (f4.m) this.f5081a.f5052s.get());
            com.vivid.spotify.nowplaying.f.b(nowPlayingFragment, (g4.f) this.f5081a.f5056w.get());
            return nowPlayingFragment;
        }

        private QueueFragment r(QueueFragment queueFragment) {
            com.vivid.spotify.queue.f.b(queueFragment, (f4.m) this.f5081a.f5052s.get());
            com.vivid.spotify.queue.f.c(queueFragment, (i3.m) this.f5081a.f5042i.get());
            com.vivid.spotify.queue.f.d(queueFragment, this.f5081a.s0());
            com.vivid.spotify.queue.f.a(queueFragment, (com.vivid.spotify.b) this.f5081a.f5054u.get());
            return queueFragment;
        }

        private com.vivid.spotify.search.j s(com.vivid.spotify.search.j jVar) {
            com.vivid.spotify.search.q.a(jVar, (f4.m) this.f5081a.f5052s.get());
            com.vivid.spotify.search.q.b(jVar, (i3.m) this.f5081a.f5042i.get());
            com.vivid.spotify.search.q.c(jVar, this.f5081a.s0());
            return jVar;
        }

        @Override // m4.a.b
        public a.c a() {
            return this.f5083c.a();
        }

        @Override // com.vivid.spotify.search.i
        public void b(SearchFragment searchFragment) {
        }

        @Override // com.vivid.spotify.rootui.category.j
        public void c(com.vivid.spotify.rootui.category.i iVar) {
            o(iVar);
        }

        @Override // com.vivid.spotify.rootui.category.e
        public void d(CategoryFragment categoryFragment) {
        }

        @Override // com.vivid.spotify.details.l
        public void e(DetailFragment detailFragment) {
            m(detailFragment);
        }

        @Override // com.vivid.spotify.rootui.c
        public void f(GridFragment gridFragment) {
            n(gridFragment);
        }

        @Override // com.vivid.spotify.rootui.q
        public void g(MainFragment mainFragment) {
            p(mainFragment);
        }

        @Override // com.vivid.spotify.search.p
        public void h(com.vivid.spotify.search.j jVar) {
            s(jVar);
        }

        @Override // com.vivid.spotify.details.f
        public void i(com.vivid.spotify.details.e eVar) {
            l(eVar);
        }

        @Override // com.vivid.spotify.queue.e
        public void j(QueueFragment queueFragment) {
            r(queueFragment);
        }

        @Override // com.vivid.spotify.nowplaying.e
        public void k(NowPlayingFragment nowPlayingFragment) {
            q(nowPlayingFragment);
        }

        private h(s sVar, e eVar, c cVar, Fragment fragment) {
            this.f5084d = this;
            this.f5081a = sVar;
            this.f5082b = eVar;
            this.f5083c = cVar;
        }
    }

    /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
    private static final class i implements l4.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final s f5085a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Service f5086b;

        @Override // l4.d
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public com.telenav.scoutivi.spotify.byd.f a() {
            p4.d.a(this.f5086b, Service.class);
            return new j(this.f5086b);
        }

        @Override // l4.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public i b(Service service) {
            this.f5086b = (Service) p4.d.b(service);
            return this;
        }

        private i(s sVar) {
            this.f5085a = sVar;
        }
    }

    /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
    private static final class j extends com.telenav.scoutivi.spotify.byd.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final s f5087a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final j f5088b;

        private MediaManagerService c(MediaManagerService mediaManagerService) {
            com.vivid.spotify.service.c.c(mediaManagerService, (i3.j) this.f5087a.f5048o.get());
            com.vivid.spotify.service.c.d(mediaManagerService, (i3.m) this.f5087a.f5042i.get());
            com.vivid.spotify.service.c.b(mediaManagerService, (f4.b) this.f5087a.f5050q.get());
            com.vivid.spotify.service.c.e(mediaManagerService, (c0) this.f5087a.f5053t.get());
            com.vivid.spotify.service.c.a(mediaManagerService, (com.vivid.spotify.b) this.f5087a.f5054u.get());
            return mediaManagerService;
        }

        private RestoreStateService d(RestoreStateService restoreStateService) {
            com.vivid.carcontroller.byd.dx.t.b(restoreStateService, (com.vivid.carcontroller.byd.dx.l) this.f5087a.f5049p.get());
            com.vivid.carcontroller.byd.dx.t.a(restoreStateService, (RestoreStateService.a) this.f5087a.B.get());
            return restoreStateService;
        }

        @Override // com.vivid.carcontroller.byd.dx.s
        public void a(RestoreStateService restoreStateService) {
            d(restoreStateService);
        }

        @Override // com.vivid.spotify.service.b
        public void b(MediaManagerService mediaManagerService) {
            c(mediaManagerService);
        }

        private j(s sVar, Service service) {
            this.f5088b = this;
            this.f5087a = sVar;
        }
    }

    /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
    private static final class k<T> implements f5.a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final s f5089a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5090b;

        k(s sVar, int i7) {
            this.f5089a = sVar;
            this.f5090b = i7;
        }

        @Override // f5.a
        public T get() {
            switch (this.f5090b) {
                case 0:
                    return (T) this.f5089a.k0();
                case 1:
                    return (T) this.f5089a.l0();
                case 2:
                    return (T) new y();
                case 3:
                    return (T) this.f5089a.i0();
                case 4:
                    return (T) this.f5089a.T();
                case 5:
                    return (T) this.f5089a.o0();
                case 6:
                    return (T) this.f5089a.R();
                case 7:
                    return (T) this.f5089a.S();
                case 8:
                    return (T) this.f5089a.U();
                case 9:
                    return (T) this.f5089a.V();
                case 10:
                    return (T) this.f5089a.j0();
                case 11:
                    return (T) this.f5089a.Z();
                case 12:
                    return (T) this.f5089a.a0();
                case 13:
                    return (T) this.f5089a.r0();
                case 14:
                    return (T) this.f5089a.t0();
                case 15:
                    return (T) this.f5089a.X();
                case 16:
                    return (T) this.f5089a.Y();
                case 17:
                    return (T) this.f5089a.h0();
                case 18:
                    return (T) this.f5089a.p0();
                case 19:
                    return (T) this.f5089a.q0();
                case 20:
                    return (T) this.f5089a.n0();
                case 21:
                    return (T) this.f5089a.m0();
                case 22:
                    return (T) this.f5089a.g0();
                default:
                    throw new AssertionError(this.f5090b);
            }
        }
    }

    /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
    private static final class l implements l4.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final s f5091a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f5092b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private androidx.lifecycle.y f5093c;

        @Override // l4.e
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public com.telenav.scoutivi.spotify.byd.h a() {
            p4.d.a(this.f5093c, androidx.lifecycle.y.class);
            return new m(this.f5092b, this.f5093c);
        }

        @Override // l4.e
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public l b(androidx.lifecycle.y yVar) {
            this.f5093c = (androidx.lifecycle.y) p4.d.b(yVar);
            return this;
        }

        private l(s sVar, e eVar) {
            this.f5091a = sVar;
            this.f5092b = eVar;
        }
    }

    /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
    private static final class m extends com.telenav.scoutivi.spotify.byd.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final s f5094a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f5095b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final m f5096c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private f5.a<CategoryViewModel> f5097d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private f5.a<DetailChildViewModel> f5098e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private f5.a<DetailViewModel> f5099f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private f5.a<DoubleGridViewModel> f5100g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private f5.a<GridViewModel> f5101h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private f5.a<com.vivid.spotify.rootui.category.GridViewModel> f5102i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private f5.a<MainUIViewModel> f5103j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private f5.a<MainViewModel> f5104k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private f5.a<NowPlayingViewModel> f5105l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private f5.a<QueueViewModel> f5106m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private f5.a<SearchListViewModel> f5107n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private f5.a<SearchViewModel> f5108o;

        /* JADX INFO: compiled from: DaggerBYDApplication_HiltComponents_SingletonC.java */
        private static final class a<T> implements f5.a<T> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final s f5109a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final e f5110b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private final m f5111c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private final int f5112d;

            a(s sVar, e eVar, m mVar, int i7) {
                this.f5109a = sVar;
                this.f5110b = eVar;
                this.f5111c = mVar;
                this.f5112d = i7;
            }

            @Override // f5.a
            public T get() {
                switch (this.f5112d) {
                    case 0:
                        return (T) this.f5111c.n();
                    case 1:
                        return (T) this.f5111c.o();
                    case 2:
                        return (T) this.f5111c.p();
                    case 3:
                        return (T) this.f5111c.q();
                    case 4:
                        return (T) this.f5111c.r();
                    case 5:
                        return (T) this.f5111c.s();
                    case 6:
                        return (T) this.f5111c.u();
                    case 7:
                        return (T) this.f5111c.v();
                    case 8:
                        return (T) this.f5111c.w();
                    case 9:
                        return (T) this.f5111c.x();
                    case 10:
                        return (T) this.f5111c.y();
                    case 11:
                        return (T) this.f5111c.z();
                    default:
                        throw new AssertionError(this.f5112d);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CategoryViewModel n() {
            return new CategoryViewModel(n4.b.a(this.f5094a.f5035b), (f4.m) this.f5094a.f5052s.get());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DetailChildViewModel o() {
            return new DetailChildViewModel(n4.b.a(this.f5094a.f5035b));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DetailViewModel p() {
            return new DetailViewModel(n4.b.a(this.f5094a.f5035b), (f4.m) this.f5094a.f5052s.get(), (i3.m) this.f5094a.f5042i.get(), this.f5094a.s0(), (f4.c) this.f5094a.f5055v.get());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DoubleGridViewModel q() {
            return new DoubleGridViewModel(n4.b.a(this.f5094a.f5035b), (f4.m) this.f5094a.f5052s.get(), (i3.m) this.f5094a.f5042i.get());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public GridViewModel r() {
            return new GridViewModel(n4.b.a(this.f5094a.f5035b), (f4.m) this.f5094a.f5052s.get(), (i3.m) this.f5094a.f5042i.get());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public com.vivid.spotify.rootui.category.GridViewModel s() {
            return new com.vivid.spotify.rootui.category.GridViewModel(n4.b.a(this.f5094a.f5035b), (f4.m) this.f5094a.f5052s.get(), (i3.m) this.f5094a.f5042i.get(), (f4.c) this.f5094a.f5055v.get());
        }

        private void t(androidx.lifecycle.y yVar) {
            this.f5097d = new a(this.f5094a, this.f5095b, this.f5096c, 0);
            this.f5098e = new a(this.f5094a, this.f5095b, this.f5096c, 1);
            this.f5099f = new a(this.f5094a, this.f5095b, this.f5096c, 2);
            this.f5100g = new a(this.f5094a, this.f5095b, this.f5096c, 3);
            this.f5101h = new a(this.f5094a, this.f5095b, this.f5096c, 4);
            this.f5102i = new a(this.f5094a, this.f5095b, this.f5096c, 5);
            this.f5103j = new a(this.f5094a, this.f5095b, this.f5096c, 6);
            this.f5104k = new a(this.f5094a, this.f5095b, this.f5096c, 7);
            this.f5105l = new a(this.f5094a, this.f5095b, this.f5096c, 8);
            this.f5106m = new a(this.f5094a, this.f5095b, this.f5096c, 9);
            this.f5107n = new a(this.f5094a, this.f5095b, this.f5096c, 10);
            this.f5108o = new a(this.f5094a, this.f5095b, this.f5096c, 11);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MainUIViewModel u() {
            return new MainUIViewModel(n4.b.a(this.f5094a.f5035b), (i3.b) this.f5094a.f5041h.get(), (f4.m) this.f5094a.f5052s.get(), (c4.g) this.f5094a.f5059z.get(), (z) this.f5094a.f5044k.get(), this.f5094a.s0());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MainViewModel v() {
            return new MainViewModel(n4.b.a(this.f5094a.f5035b), (i3.m) this.f5094a.f5042i.get());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public NowPlayingViewModel w() {
            return new NowPlayingViewModel(n4.b.a(this.f5094a.f5035b), (i3.m) this.f5094a.f5042i.get(), (g4.f) this.f5094a.f5056w.get(), (g4.g) this.f5094a.A.get());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public QueueViewModel x() {
            return new QueueViewModel(n4.b.a(this.f5094a.f5035b), (i3.m) this.f5094a.f5042i.get());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public SearchListViewModel y() {
            return new SearchListViewModel(n4.b.a(this.f5094a.f5035b));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public SearchViewModel z() {
            return new SearchViewModel(n4.b.a(this.f5094a.f5035b), (f4.m) this.f5094a.f5052s.get(), (c4.g) this.f5094a.f5059z.get());
        }

        @Override // m4.c.b
        public Map<String, f5.a<androidx.lifecycle.a0>> a() {
            return p4.c.b(12).c("com.vivid.spotify.rootui.category.CategoryViewModel", this.f5097d).c("com.vivid.spotify.details.DetailChildViewModel", this.f5098e).c("com.vivid.spotify.details.DetailViewModel", this.f5099f).c("com.vivid.spotify.common.ui.DoubleGridViewModel", this.f5100g).c("com.vivid.spotify.rootui.GridViewModel", this.f5101h).c("com.vivid.spotify.rootui.category.GridViewModel", this.f5102i).c("com.vivid.spotify.rootui.MainUIViewModel", this.f5103j).c("com.vivid.spotify.MainViewModel", this.f5104k).c("com.vivid.spotify.nowplaying.NowPlayingViewModel", this.f5105l).c("com.vivid.spotify.queue.QueueViewModel", this.f5106m).c("com.vivid.spotify.search.SearchListViewModel", this.f5107n).c("com.vivid.spotify.search.SearchViewModel", this.f5108o).a();
        }

        private m(s sVar, e eVar, androidx.lifecycle.y yVar) {
            this.f5096c = this;
            this.f5094a = sVar;
            this.f5095b = eVar;
            t(yVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.vivid.carcontroller.byd.dx.a R() {
        return new com.vivid.carcontroller.byd.dx.a(n4.b.a(this.f5035b), this.f5042i.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.vivid.carcontroller.byd.dx.b S() {
        return new com.vivid.carcontroller.byd.dx.b(n4.b.a(this.f5035b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.vivid.carcontroller.byd.dx.g T() {
        return new com.vivid.carcontroller.byd.dx.g(n4.b.a(this.f5035b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.vivid.carcontroller.byd.dx.h U() {
        return new com.vivid.carcontroller.byd.dx.h(n4.b.a(this.f5035b), this.f5042i.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.vivid.carcontroller.byd.dx.l V() {
        return d0(com.vivid.carcontroller.byd.dx.m.a(n4.c.a(this.f5035b)));
    }

    public static f W() {
        return new f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.vivid.spotify.b X() {
        return new com.vivid.spotify.b(n4.b.a(this.f5035b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public g4.f Y() {
        return new g4.f(n4.b.a(this.f5035b), this.f5042i.get(), s0(), this.f5055v.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public f4.b Z() {
        return new f4.b(this.f5042i.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public a4.a a0() {
        return new a4.a(n4.b.a(this.f5035b));
    }

    private void b0(n4.a aVar, com.telenav.scoutivi.spotify.byd.l lVar, j3.a aVar2, a0 a0Var) {
        this.f5039f = p4.b.a(new k(this.f5038e, 2));
        this.f5040g = p4.b.a(new k(this.f5038e, 1));
        this.f5041h = p4.b.a(new k(this.f5038e, 3));
        this.f5042i = p4.b.a(new k(this.f5038e, 0));
        this.f5043j = p4.b.a(new k(this.f5038e, 4));
        this.f5044k = p4.b.a(new k(this.f5038e, 5));
        this.f5045l = p4.b.a(new k(this.f5038e, 6));
        this.f5046m = p4.b.a(new k(this.f5038e, 7));
        this.f5047n = p4.b.a(new k(this.f5038e, 8));
        this.f5048o = p4.b.a(new k(this.f5038e, 10));
        this.f5049p = p4.b.a(new k(this.f5038e, 9));
        this.f5050q = p4.b.a(new k(this.f5038e, 11));
        this.f5051r = p4.b.a(new k(this.f5038e, 12));
        this.f5052s = p4.b.a(new k(this.f5038e, 13));
        this.f5053t = p4.b.a(new k(this.f5038e, 14));
        this.f5054u = p4.b.a(new k(this.f5038e, 15));
        this.f5055v = p4.b.a(new k(this.f5038e, 17));
        this.f5056w = p4.b.a(new k(this.f5038e, 16));
        this.f5057x = p4.b.a(new k(this.f5038e, 20));
        this.f5058y = p4.b.a(new k(this.f5038e, 19));
        this.f5059z = p4.b.a(new k(this.f5038e, 18));
        this.A = p4.b.a(new k(this.f5038e, 21));
        this.B = p4.b.a(new k(this.f5038e, 22));
    }

    private BYDApplication c0(BYDApplication bYDApplication) {
        com.vivid.spotify.common.base.f.a(bYDApplication, this.f5042i.get());
        com.telenav.scoutivi.spotify.byd.i.d(bYDApplication, this.f5043j.get());
        com.telenav.scoutivi.spotify.byd.i.f(bYDApplication, this.f5044k.get());
        com.telenav.scoutivi.spotify.byd.i.a(bYDApplication, this.f5045l.get());
        com.telenav.scoutivi.spotify.byd.i.c(bYDApplication, this.f5046m.get());
        com.telenav.scoutivi.spotify.byd.i.e(bYDApplication, this.f5047n.get());
        com.telenav.scoutivi.spotify.byd.i.g(bYDApplication, this.f5049p.get());
        com.telenav.scoutivi.spotify.byd.i.b(bYDApplication, this.f5050q.get());
        return bYDApplication;
    }

    private com.vivid.carcontroller.byd.dx.l d0(com.vivid.carcontroller.byd.dx.l lVar) {
        com.vivid.carcontroller.byd.dx.n.a(lVar, this.f5048o.get());
        com.vivid.carcontroller.byd.dx.n.b(lVar, this.f5042i.get());
        return lVar;
    }

    private DeviceBroadcastReceiver e0(DeviceBroadcastReceiver deviceBroadcastReceiver) {
        com.vivid.carcontroller.byd.dx.p.c(deviceBroadcastReceiver, this.f5042i.get());
        com.vivid.carcontroller.byd.dx.p.d(deviceBroadcastReceiver, this.f5049p.get());
        com.vivid.carcontroller.byd.dx.p.a(deviceBroadcastReceiver, this.f5045l.get());
        com.vivid.carcontroller.byd.dx.p.b(deviceBroadcastReceiver, f0());
        return deviceBroadcastReceiver;
    }

    private DeviceBroadcastReceiver.a f0() {
        return com.telenav.scoutivi.spotify.byd.m.a(this.f5037d, n4.b.a(this.f5035b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RestoreStateService.a g0() {
        return n.a(this.f5037d, n4.b.a(this.f5035b), this.f5042i.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public f4.c h0() {
        return new f4.c(n4.b.a(this.f5035b), this.f5042i.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public i3.b i0() {
        return o.a(this.f5037d, n4.b.a(this.f5035b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public i3.j j0() {
        return new i3.j(this.f5040g.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public i3.m k0() {
        return b0.a(this.f5034a, n4.b.a(this.f5035b), this.f5040g.get(), this.f5041h.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public i3.s l0() {
        return j3.b.a(this.f5036c, n4.b.a(this.f5035b), this.f5039f.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public g4.g m0() {
        return new g4.g(n4.b.a(this.f5035b), this.f5042i.get(), s0(), this.f5055v.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public b4.a n0() {
        return new b4.a(n4.b.a(this.f5035b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public z o0() {
        return new z(n4.b.a(this.f5035b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c4.g p0() {
        return new c4.g(this.f5058y.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c4.h q0() {
        return new c4.h(this.f5057x.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public f4.m r0() {
        return new f4.m(this.f5040g.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public y3.b s0() {
        return p.a(this.f5037d, n4.b.a(this.f5035b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c0 t0() {
        return new c0(n4.b.a(this.f5035b));
    }

    @Override // com.telenav.scoutivi.spotify.byd.b
    public void a(BYDApplication bYDApplication) {
        c0(bYDApplication);
    }

    @Override // com.vivid.carcontroller.byd.dx.o
    public void b(DeviceBroadcastReceiver deviceBroadcastReceiver) {
        e0(deviceBroadcastReceiver);
    }

    @Override // dagger.hilt.android.internal.managers.h.a
    public l4.d c() {
        return new i();
    }

    @Override // dagger.hilt.android.internal.managers.b.InterfaceC0064b
    public l4.b d() {
        return new d();
    }

    private s(n4.a aVar, com.telenav.scoutivi.spotify.byd.l lVar, j3.a aVar2, a0 a0Var) {
        this.f5038e = this;
        this.f5034a = a0Var;
        this.f5035b = aVar;
        this.f5036c = aVar2;
        this.f5037d = lVar;
        b0(aVar, lVar, aVar2, a0Var);
    }
}
