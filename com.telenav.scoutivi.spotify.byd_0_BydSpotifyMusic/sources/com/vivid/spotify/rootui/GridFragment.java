package com.vivid.spotify.rootui;

import android.app.Application;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.b0;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.f;
import com.vivid.spotify.common.ui.MediaCoverItemViewModel;
import com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;
import d4.e4;
import d4.j4;
import d4.k1;
import d4.z3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class GridFragment extends j {

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private final String f5386p0 = "GridFragment";

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    GridViewModel f5387q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    f4.m f5388r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    i3.m f5389s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    private MainUIViewModel f5390t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    private androidx.lifecycle.t<List<MediaBrowserCompat.MediaItem>> f5391u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    private androidx.lifecycle.u<List<x>> f5392v0;

    class a extends RecyclerView.t {
        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i7, int i8) {
            ((k1) GridFragment.this.f5218j0).H.setTranslationY(-((k1) r1).I.computeVerticalScrollOffset());
        }
    }

    class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            ((k1) GridFragment.this.f5218j0).G().removeOnAttachStateChangeListener(this);
            GridFragment.this.f2();
            GridFragment.this.i2();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    class c extends RecyclerView.t {
        c() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i7, int i8) {
            GridFragment.this.k2();
        }
    }

    class d extends GridLayoutManager.c {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ f f5396e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ int f5397f;

        d(f fVar, int i7) {
            this.f5396e = fVar;
            this.f5397f = i7;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int f(int i7) {
            if (this.f5396e.g(i7) == 11) {
                return 1;
            }
            return this.f5397f;
        }
    }

    private static class e extends v3.b {
        public e(List list, List list2) {
            super(list, list2);
        }

        @Override // v3.b, androidx.recyclerview.widget.f.b
        public boolean a(int i7, int i8) {
            if (!f(this.f9676a, i7) && !f(this.f9677b, i8)) {
                Object obj = this.f9676a.get(i7);
                Object obj2 = this.f9677b.get(i8);
                if (obj != null && obj2 != null) {
                    return ((obj instanceof MediaBrowserCompat.MediaItem) && (obj2 instanceof MediaBrowserCompat.MediaItem)) ? i3.c.i((MediaBrowserCompat.MediaItem) obj, (MediaBrowserCompat.MediaItem) obj2) : obj.equals(obj2);
                }
            }
            return false;
        }

        @Override // v3.b, androidx.recyclerview.widget.f.b
        public boolean b(int i7, int i8) {
            if (!f(this.f9676a, i7) && !f(this.f9677b, i8)) {
                Object obj = this.f9676a.get(i7);
                Object obj2 = this.f9677b.get(i8);
                if (obj != null && obj2 != null) {
                    return ((obj instanceof MediaBrowserCompat.MediaItem) && (obj2 instanceof MediaBrowserCompat.MediaItem)) ? i3.c.i((MediaBrowserCompat.MediaItem) obj, (MediaBrowserCompat.MediaItem) obj2) : obj.equals(obj2);
                }
            }
            return false;
        }
    }

    public class f extends com.vivid.spotify.common.base.d<RecyclerView.c0> implements BaseRecyclerView.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List f5399c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private BaseRecyclerView f5400d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Application f5401e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f5402f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private CopyOnWriteArrayList<BaseRecyclerView.a> f5403g;

        class a extends RecyclerView.c0 implements BaseRecyclerView.a {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            private final z3 f5405t;

            a(z3 z3Var) {
                super(z3Var.G());
                this.f5405t = z3Var;
            }

            @Override // com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView.a
            public void onConfigurationChanged(Configuration configuration) {
                try {
                    this.f5405t.G().getLayoutParams().width = f.this.f5400d.getItemWidth();
                } catch (Exception e7) {
                    h3.i.b("GridFragment").e(e7, "setWidth error", new Object[0]);
                }
            }
        }

        class b extends RecyclerView.c0 {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            public final j4 f5407t;

            public b(j4 j4Var) {
                super(j4Var.G());
                this.f5407t = j4Var;
            }
        }

        public f(Application application, List<MediaBrowserCompat.MediaItem> list) {
            ArrayList arrayList = new ArrayList();
            this.f5399c = arrayList;
            this.f5402f = false;
            this.f5403g = new CopyOnWriteArrayList<>();
            this.f5401e = application;
            arrayList.addAll(list);
        }

        @Override // com.vivid.spotify.common.base.d
        public int B() {
            return GridFragment.this.N().getDimensionPixelSize(com.vivid.spotify.r.media_title_height);
        }

        public void D(List<MediaBrowserCompat.MediaItem> list) {
            BaseRecyclerView baseRecyclerView;
            v3.d dVar = new v3.d(new e(this.f5399c, list));
            f.c cVarA = androidx.recyclerview.widget.f.a(dVar);
            this.f5399c.clear();
            this.f5399c.addAll(list);
            cVarA.e(this);
            if (dVar.f() == -1 || (baseRecyclerView = this.f5400d) == null) {
                return;
            }
            baseRecyclerView.r1(0);
        }

        public void E(boolean z6) {
            this.f5402f = z6;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            return this.f5399c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int g(int i7) {
            return this.f5399c.get(i7).getClass().isAssignableFrom(y.class) ? 12 : 11;
        }

        @Override // com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView.a
        public void onConfigurationChanged(Configuration configuration) {
            Iterator<BaseRecyclerView.a> it = this.f5403g.iterator();
            while (it.hasNext()) {
                it.next().onConfigurationChanged(configuration);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void p(RecyclerView recyclerView) {
            super.p(recyclerView);
            BaseRecyclerView baseRecyclerView = (BaseRecyclerView) recyclerView;
            this.f5400d = baseRecyclerView;
            baseRecyclerView.z1(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void q(RecyclerView.c0 c0Var, int i7) {
            if (g(i7) != 11) {
                ((b) c0Var).f5407t.d0().f5231a.r(((y) this.f5399c.get(i7)).f5517a);
                return;
            }
            MediaCoverItemViewModel mediaCoverItemViewModelD0 = ((a) c0Var).f5405t.d0();
            MediaBrowserCompat.MediaItem mediaItem = (MediaBrowserCompat.MediaItem) this.f5399c.get(i7);
            mediaCoverItemViewModelD0.setMediaItem(mediaItem);
            if (i3.c.a(mediaItem) != null) {
                mediaCoverItemViewModelD0.icon.r(Uri.parse(i3.c.a(mediaItem)));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.c0 s(ViewGroup viewGroup, int i7) {
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
            if (i7 != 11) {
                j4 j4VarE0 = j4.e0(layoutInflaterFrom, viewGroup, false);
                j4VarE0.g0(new com.vivid.spotify.common.ui.d());
                return new b(j4VarE0);
            }
            Application application = this.f5401e;
            GridFragment gridFragment = GridFragment.this;
            MediaCoverItemViewModel mediaCoverItemViewModel = new MediaCoverItemViewModel(application, gridFragment.f5388r0, gridFragment.f5389s0, gridFragment.f5387q0.getMediaItem());
            GridFragment.this.N1(mediaCoverItemViewModel);
            z3 z3VarE0 = z3.e0(layoutInflaterFrom, viewGroup, false);
            z3VarE0.g0(mediaCoverItemViewModel);
            a aVar = new a(z3VarE0);
            if (this.f5402f && this.f5400d != null) {
                aVar.f5405t.G().getLayoutParams().width = this.f5400d.getItemWidth();
                this.f5403g.addIfAbsent(aVar);
            }
            return aVar;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void t(RecyclerView recyclerView) {
            super.t(recyclerView);
            this.f5400d.E1(this);
            this.f5400d = null;
        }
    }

    public class g extends com.vivid.spotify.common.base.d<b> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Application f5409c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final List<w> f5410d;

        class a extends f.b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ List f5412a;

            a(List list) {
                this.f5412a = list;
            }

            @Override // androidx.recyclerview.widget.f.b
            public boolean a(int i7, int i8) {
                return ((w) g.this.f5410d.get(i7)).equals(this.f5412a.get(i8));
            }

            @Override // androidx.recyclerview.widget.f.b
            public boolean b(int i7, int i8) {
                return i7 == i8;
            }

            @Override // androidx.recyclerview.widget.f.b
            public int d() {
                return this.f5412a.size();
            }

            @Override // androidx.recyclerview.widget.f.b
            public int e() {
                return g.this.f5410d.size();
            }
        }

        class b extends RecyclerView.c0 {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            private final e4 f5414t;

            b(e4 e4Var) {
                super(e4Var.G());
                this.f5414t = e4Var;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void O(List<MediaBrowserCompat.MediaItem> list) {
                BaseRecyclerView baseRecyclerView = this.f5414t.H;
                if (baseRecyclerView.getAdapter() == null) {
                    g gVar = g.this;
                    f fVar = GridFragment.this.new f(gVar.f5409c, list);
                    fVar.E(true);
                    baseRecyclerView.setAdapter(fVar);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GridFragment.this.s());
                    linearLayoutManager.C2(0);
                    baseRecyclerView.setLayoutManager(linearLayoutManager);
                    baseRecyclerView.h(new com.vivid.spotify.common.ui.c(0, GridFragment.this.N().getDimensionPixelSize(com.vivid.spotify.r.media_cover_item_margin_right)));
                    ((androidx.recyclerview.widget.q) baseRecyclerView.getItemAnimator()).Q(false);
                }
                com.vivid.spotify.common.ui.d dVarD0 = this.f5414t.d0();
                dVarD0.f5232b.clear();
                dVarD0.f5232b.addAll(list);
            }
        }

        public g(Application application, List<w> list) {
            ArrayList arrayList = new ArrayList();
            this.f5410d = arrayList;
            this.f5409c = application;
            arrayList.addAll(list);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
        public void q(b bVar, int i7) {
            com.vivid.spotify.common.ui.d dVarD0 = bVar.f5414t.d0();
            dVarD0.f5231a.r(this.f5410d.get(i7).f5512a);
            dVarD0.f5233c.r(i7 == 0);
            bVar.O(this.f5410d.get(i7).f5514c);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
        public b s(ViewGroup viewGroup, int i7) {
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
            com.vivid.spotify.common.ui.d dVar = new com.vivid.spotify.common.ui.d();
            e4 e4VarE0 = e4.e0(layoutInflaterFrom, viewGroup, false);
            e4VarE0.g0(dVar);
            return new b(e4VarE0);
        }

        public void G(List<w> list) {
            f.c cVarA = androidx.recyclerview.widget.f.a(new a(list));
            this.f5410d.clear();
            this.f5410d.addAll(list);
            cVarA.e(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            return this.f5410d.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int g(int i7) {
            return (i7 * 10) + 1;
        }
    }

    private GridLayoutManager e2(f fVar) {
        int integer = N().getInteger(com.vivid.spotify.u.list_span_count);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(s(), integer);
        gridLayoutManager.g3(new d(fVar, integer));
        return gridLayoutManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f2() {
        if (this.f5387q0.mediaItemsNotifyLiveData == null) {
            try {
                androidx.navigation.e eVarF = androidx.navigation.q.a(((k1) this.f5218j0).G()).f();
                this.f5387q0.mediaItemsNotifyLiveData = eVarF.g().b("extra_key_media_list");
            } catch (Exception e7) {
                h3.i.b("GridFragment").e(e7, "initMediaDataLiveData", new Object[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g2(List list) {
        this.f5387q0.onRootMenuChanged(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h2(Application application, List list) {
        n2(application, ((k1) this.f5218j0).I, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i2() {
        try {
            androidx.navigation.e eVarL = androidx.navigation.q.a(((k1) this.f5218j0).G()).l();
            if (eVarL != null) {
                if (TextUtils.equals("rootGrid", eVarL.e().l())) {
                    androidx.lifecycle.t<List<MediaBrowserCompat.MediaItem>> tVarC = eVarL.g().c("extra_key_media_list", new ArrayList());
                    this.f5391u0 = tVarC;
                    tVarC.f(X(), this.f5387q0.parentDataChangeObserver);
                } else {
                    this.f5390t0 = (MainUIViewModel) new b0(j()).a(MainUIViewModel.class);
                    this.f5392v0 = new androidx.lifecycle.u() { // from class: com.vivid.spotify.rootui.a
                        @Override // androidx.lifecycle.u
                        public final void c(Object obj) {
                            this.f5439a.g2((List) obj);
                        }
                    };
                    this.f5390t0.rootMenus.f(X(), this.f5392v0);
                }
            }
        } catch (Exception e7) {
            h3.i.b("GridFragment").e(e7, "listenParentMediaDataChange", new Object[0]);
        }
    }

    private void j2() {
        for (int itemDecorationCount = ((k1) this.f5218j0).I.getItemDecorationCount() - 1; itemDecorationCount >= 0; itemDecorationCount--) {
            ((k1) this.f5218j0).I.Y0(itemDecorationCount);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k2() {
        float measuredHeight = ((k1) this.f5218j0).J.getMeasuredHeight();
        ((k1) this.f5218j0).J.setAlpha(measuredHeight != 0.0f ? ((k1) this.f5218j0).I.computeVerticalScrollOffset() / measuredHeight : 0.0f);
    }

    public static void l2(RecyclerView recyclerView, List<MediaBrowserCompat.MediaItem> list) {
        f fVar = (f) recyclerView.getAdapter();
        if (fVar != null) {
            fVar.D(list);
        }
    }

    private void m2() {
        ((k1) this.f5218j0).I.setGridChildCount(N().getInteger(com.vivid.spotify.u.list_span_count));
        ((k1) this.f5218j0).I.setDemoLayoutManager(ShimmerRecyclerView.b.GRID);
        ((k1) this.f5218j0).I.setDemoLayoutReference(com.vivid.spotify.v.media_cover_item_viewholder);
        T t6 = this.f5218j0;
        ((k1) t6).I.setPaddingRelative(((k1) t6).I.getPaddingLeft(), ((k1) this.f5218j0).I.getPaddingTop(), N().getDimensionPixelSize(com.vivid.spotify.r.media_cover_grid_padding_end), N().getDimensionPixelSize(com.vivid.spotify.r.now_playing_bar_height) - N().getDimensionPixelSize(com.vivid.spotify.r.now_playing_bar_padding));
        j2();
        ((k1) this.f5218j0).I.h(new com.vivid.spotify.common.ui.c(0, N().getDimensionPixelSize(com.vivid.spotify.r.media_cover_grid_item_padding)));
    }

    private void n2(Application application, ShimmerRecyclerView shimmerRecyclerView, List<w> list) {
        f fVar;
        if (list != null) {
            shimmerRecyclerView.J1();
            RecyclerView.g adapter = shimmerRecyclerView.getAdapter();
            RecyclerView.o layoutManager = shimmerRecyclerView.getLayoutManager();
            if (list.size() != 1) {
                shimmerRecyclerView.setItemBlankAreaSize(N().getDimensionPixelSize(com.vivid.spotify.r.fragment_main_grid_media_cover_item_margin_bottom));
                if (adapter == null || !adapter.getClass().isAssignableFrom(g.class)) {
                    shimmerRecyclerView.setAdapter(new g(application, list));
                    shimmerRecyclerView.h(new com.vivid.spotify.common.ui.c(1, s().getResources().getDimensionPixelSize(com.vivid.spotify.r.fragment_main_grid_media_cover_item_margin_top)));
                } else {
                    ((g) adapter).G(list);
                }
                if (layoutManager == null || !layoutManager.getClass().isAssignableFrom(LinearLayoutManager.class)) {
                    shimmerRecyclerView.setLayoutManager(new LinearLayoutManager(s()));
                    return;
                }
                return;
            }
            Collection<? extends MediaBrowserCompat.MediaItem> collection = list.get(0).f5514c;
            List<MediaBrowserCompat.MediaItem> arrayList = new ArrayList<>();
            if (!this.f5387q0.showBack.q()) {
                arrayList.add(new y(list.get(0).f5512a));
            }
            arrayList.addAll(collection);
            if (adapter == null || !adapter.getClass().isAssignableFrom(f.class)) {
                fVar = new f(application, arrayList);
                shimmerRecyclerView.setAdapter(fVar);
            } else {
                fVar = (f) adapter;
                fVar.D(arrayList);
            }
            if (layoutManager == null || !layoutManager.getClass().isAssignableFrom(GridLayoutManager.class)) {
                shimmerRecyclerView.setPaddingRelative(shimmerRecyclerView.getPaddingLeft(), shimmerRecyclerView.getPaddingTop(), N().getDimensionPixelSize(com.vivid.spotify.r.media_cover_grid_padding_end), N().getDimensionPixelSize(com.vivid.spotify.r.now_playing_bar_height) - N().getDimensionPixelSize(com.vivid.spotify.r.now_playing_bar_padding));
                j2();
                shimmerRecyclerView.h(new com.vivid.spotify.common.ui.c(0, N().getDimensionPixelSize(com.vivid.spotify.r.media_cover_grid_item_padding)));
                shimmerRecyclerView.setLayoutManager(e2(fVar));
            }
        }
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void B0() {
        androidx.lifecycle.u<List<x>> uVar;
        super.B0();
        androidx.lifecycle.t<List<MediaBrowserCompat.MediaItem>> tVar = this.f5391u0;
        if (tVar != null) {
            tVar.l(X());
        }
        MainUIViewModel mainUIViewModel = this.f5390t0;
        if (mainUIViewModel == null || (uVar = this.f5392v0) == null) {
            return;
        }
        mainUIViewModel.rootMenus.k(uVar);
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        boolean z6;
        this.f5218j0 = k1.d0(layoutInflater, viewGroup, false);
        this.f5387q0 = (GridViewModel) new b0(this).a(GridViewModel.class);
        final Application application = j().getApplication();
        h3.i.b("GridFragment").g("onCreateView!!");
        if (q() != null) {
            z6 = q().getBoolean("show_back", false);
            this.f5387q0.showBack.r(z6);
            if (z6) {
                m2();
                ((k1) this.f5218j0).I.l(new a());
                ((k1) this.f5218j0).H.setTranslationY(-((k1) r1).I.computeVerticalScrollOffset());
            } else {
                ((k1) this.f5218j0).I.setDemoLayoutReference(com.vivid.spotify.v.media_list_parent_item_viewholder);
            }
            if (this.f5387q0.setMediaItem((MediaBrowserCompat.MediaItem) q().getParcelable("media_item")) || this.f5387q0.mediaItems.e() == null || this.f5387q0.mediaItems.e().isEmpty()) {
                h3.i.b("GridFragment").b("set argument", new Object[0]);
                ((k1) this.f5218j0).I.N1();
            }
        } else {
            z6 = false;
        }
        n2(application, ((k1) this.f5218j0).I, this.f5387q0.mediaItems.e());
        this.f5387q0.mediaItems.f(X(), new androidx.lifecycle.u() { // from class: com.vivid.spotify.rootui.b
            @Override // androidx.lifecycle.u
            public final void c(Object obj) {
                this.f5440a.h2(application, (List) obj);
            }
        });
        ((k1) this.f5218j0).f0(this.f5387q0);
        ((k1) this.f5218j0).I.setTag("tag_list");
        ((k1) this.f5218j0).W(X());
        ((k1) this.f5218j0).G().addOnAttachStateChangeListener(new b());
        if (z6) {
            ((k1) this.f5218j0).J.setVisibility(0);
            ((k1) this.f5218j0).I.l(new c());
            k2();
        } else {
            ((k1) this.f5218j0).J.setVisibility(8);
        }
        a().a(this.f5387q0);
        return ((k1) this.f5218j0).G();
    }
}
