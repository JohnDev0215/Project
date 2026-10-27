package com.vivid.spotify.rootui.category;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.b0;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.f;
import com.vivid.spotify.common.ui.DoubleGridViewModel;
import com.vivid.spotify.common.ui.MediaCoverItemViewModel;
import com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;
import com.vivid.spotify.u;
import d4.l0;
import d4.y4;
import d4.z3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: GridFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends s {

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    private GridViewModel f5460q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    private b f5461r0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    private MediaBrowserCompat.MediaItem f5463t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    f4.m f5464u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    i3.m f5465v0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private final String f5459p0 = "CategoryGridFragment";

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    private int f5462s0 = 0;

    /* JADX INFO: compiled from: GridFragment.java */
    class a extends GridLayoutManager.c {
        a() {
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int f(int i7) {
            return 1;
        }
    }

    /* JADX INFO: compiled from: GridFragment.java */
    private class b extends com.vivid.spotify.common.base.d<RecyclerView.c0> implements BaseRecyclerView.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<MediaBrowserCompat.MediaItem> f5467c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private BaseRecyclerView f5468d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private CopyOnWriteArrayList<BaseRecyclerView.a> f5469e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final Application f5470f;

        /* JADX INFO: compiled from: GridFragment.java */
        private class a extends RecyclerView.c0 implements BaseRecyclerView.a {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            private final y4 f5472t;

            /* JADX INFO: renamed from: u, reason: collision with root package name */
            private final DoubleGridViewModel f5473u;

            public a(y4 y4Var) {
                super(y4Var.G());
                this.f5472t = y4Var;
                DoubleGridViewModel doubleGridViewModel = (DoubleGridViewModel) new b0(i.this).a(DoubleGridViewModel.class);
                this.f5473u = doubleGridViewModel;
                i.this.N1(doubleGridViewModel);
            }

            public int M(int i7) {
                return i7 + i.this.N().getDimensionPixelSize(com.vivid.spotify.r.media_cover_item_play_icon_bg_size);
            }

            public void N(MediaBrowserCompat.MediaItem mediaItem) {
                this.f5473u.setMediaItem(i.this.f5463t0, mediaItem);
                this.f5472t.f0(this.f5473u);
                this.f5472t.W(i.this.X());
            }

            @Override // com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView.a
            public void onConfigurationChanged(Configuration configuration) {
                if (this.f5472t == null || b.this.f5468d == null) {
                    return;
                }
                this.f5472t.G().getLayoutParams().height = M(b.this.f5468d.getItemWidth());
            }
        }

        public b(Application application, List<MediaBrowserCompat.MediaItem> list) {
            ArrayList arrayList = new ArrayList();
            this.f5467c = arrayList;
            this.f5469e = new CopyOnWriteArrayList<>();
            this.f5470f = application;
            arrayList.addAll(list);
        }

        public void D(List<MediaBrowserCompat.MediaItem> list) {
            v3.d dVar = new v3.d(new v3.c(this.f5467c, list));
            f.c cVarA = androidx.recyclerview.widget.f.a(dVar);
            this.f5467c.clear();
            this.f5467c.addAll(list);
            cVarA.e(this);
            int iF = dVar.f();
            if (iF != -1) {
                ((l0) i.this.f5218j0).G.j1(iF);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            return this.f5467c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int g(int i7) {
            return 1;
        }

        @Override // com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView.a
        public void onConfigurationChanged(Configuration configuration) {
            Iterator<BaseRecyclerView.a> it = this.f5469e.iterator();
            while (it.hasNext()) {
                it.next().onConfigurationChanged(configuration);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void p(RecyclerView recyclerView) {
            super.p(recyclerView);
            BaseRecyclerView baseRecyclerView = (BaseRecyclerView) recyclerView;
            this.f5468d = baseRecyclerView;
            baseRecyclerView.z1(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void q(RecyclerView.c0 c0Var, int i7) {
            MediaBrowserCompat.MediaItem mediaItem = this.f5467c.get(i7);
            if (c0Var instanceof d) {
                ((d) c0Var).N(mediaItem);
            } else if (c0Var instanceof a) {
                ((a) c0Var).N(mediaItem);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.c0 s(ViewGroup viewGroup, int i7) {
            if (i7 == 1) {
                return i.this.new d(this.f5470f, z3.e0(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
            }
            y4 y4VarD0 = y4.d0(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            a aVar = new a(y4VarD0);
            BaseRecyclerView baseRecyclerView = this.f5468d;
            if (baseRecyclerView != null) {
                int itemWidth = baseRecyclerView.getItemWidth();
                y4VarD0.G().getLayoutParams().height = aVar.M(itemWidth);
                this.f5469e.addIfAbsent(aVar);
            }
            return aVar;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void t(RecyclerView recyclerView) {
            super.t(recyclerView);
            this.f5468d.E1(this);
            this.f5468d = null;
        }
    }

    /* JADX INFO: compiled from: GridFragment.java */
    private class c extends RecyclerView.n {
        public c() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.z zVar) {
            if (recyclerView.getAdapter() != i.this.f5461r0) {
                return;
            }
            rect.bottom = i.this.f5462s0;
        }
    }

    /* JADX INFO: compiled from: GridFragment.java */
    private class d extends RecyclerView.c0 {

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        private final z3 f5476t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        private final MediaCoverItemViewModel f5477u;

        public d(Application application, z3 z3Var) {
            super(z3Var.G());
            this.f5476t = z3Var;
            MediaCoverItemViewModel mediaCoverItemViewModel = new MediaCoverItemViewModel(application, i.this.f5464u0, i.this.f5465v0, i.this.f5463t0);
            this.f5477u = mediaCoverItemViewModel;
            i.this.N1(mediaCoverItemViewModel);
        }

        void M(MediaCoverItemViewModel mediaCoverItemViewModel) {
            this.f5476t.g0(mediaCoverItemViewModel);
        }

        public void N(MediaBrowserCompat.MediaItem mediaItem) {
            this.f5477u.setMediaItem(mediaItem);
            this.f5477u.title.r(i3.c.g(mediaItem));
            this.f5477u.subTitle.r(i3.c.f(mediaItem));
            this.f5477u.icon.r(Uri.parse(i3.c.a(mediaItem)));
            M(this.f5477u);
        }
    }

    private RecyclerView.o e2() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(s(), N().getInteger(u.list_span_count));
        gridLayoutManager.g3(new a());
        return gridLayoutManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f2(Application application, List list) {
        h2(application, ((l0) this.f5218j0).G, list);
    }

    public static i g2(MediaBrowserCompat.MediaItem mediaItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("media_item", mediaItem);
        i iVar = new i();
        iVar.C1(bundle);
        return iVar;
    }

    private void h2(Application application, ShimmerRecyclerView shimmerRecyclerView, List<MediaBrowserCompat.MediaItem> list) {
        if (list.size() != 0) {
            RecyclerView.g adapter = shimmerRecyclerView.getAdapter();
            if (adapter != null && (adapter instanceof b)) {
                ((b) adapter).D(list);
                return;
            }
            b bVar = new b(application, list);
            this.f5461r0 = bVar;
            shimmerRecyclerView.setAdapter(bVar);
        }
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void B0() {
        super.B0();
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void P0() {
        super.P0();
        GridViewModel gridViewModel = this.f5460q0;
        if (gridViewModel != null) {
            gridViewModel.refresh();
        }
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f5460q0 = (GridViewModel) new b0(this).a(GridViewModel.class);
        this.f5218j0 = l0.d0(layoutInflater, viewGroup, false);
        final Application application = j().getApplication();
        h2(application, ((l0) this.f5218j0).G, this.f5460q0.observerList().e() == null ? new ArrayList<>() : this.f5460q0.observerList().e());
        Bundle bundleQ = q();
        if (bundleQ != null) {
            MediaBrowserCompat.MediaItem mediaItem = (MediaBrowserCompat.MediaItem) bundleQ.getParcelable("media_item");
            this.f5463t0 = mediaItem;
            if (this.f5460q0.setMediaItem(mediaItem)) {
                this.f5460q0.refresh();
            }
        }
        this.f5460q0.observerList().f(X(), new androidx.lifecycle.u() { // from class: com.vivid.spotify.rootui.category.h
            @Override // androidx.lifecycle.u
            public final void c(Object obj) {
                this.f5457a.f2(application, (List) obj);
            }
        });
        this.f5462s0 = N().getDimensionPixelSize(com.vivid.spotify.r.rv_item_category_grid_item_vertical_margin);
        if (((l0) this.f5218j0).G.getItemDecorationCount() == 0) {
            ((l0) this.f5218j0).G.h(new com.vivid.spotify.common.ui.c(0, N().getDimensionPixelSize(com.vivid.spotify.r.media_cover_grid_item_padding)));
        }
        ((l0) this.f5218j0).f0(new c());
        ((l0) this.f5218j0).G.setLayoutManager(e2());
        ((l0) this.f5218j0).G.setTag("tag_list");
        ((l0) this.f5218j0).W(X());
        ((l0) this.f5218j0).g0(this.f5460q0);
        a().a(this.f5460q0);
        return ((l0) this.f5218j0).G();
    }
}
