package com.vivid.spotify.details;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.text.BidiFormatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.b0;
import androidx.lifecycle.c0;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.f;
import com.vivid.spotify.common.ui.BaseListItemViewModel;
import com.vivid.spotify.common.ui.MediaCoverItemViewModel;
import com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView;
import com.vivid.spotify.u;
import com.vivid.spotify.v;
import com.vivid.spotify.x;
import d4.e2;
import d4.z3;
import i3.m;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: DetailChildFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends s {

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private DetailChildViewModel f5279m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private e2 f5280n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    private MediaBrowserCompat.MediaItem f5281o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private DetailViewModel f5282p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    private String f5283q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    f4.m f5284r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    i3.m f5285s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    y3.b f5286t0;

    /* JADX INFO: compiled from: DetailChildFragment.java */
    public class a extends com.vivid.spotify.common.base.d<RecyclerView.c0> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<MediaBrowserCompat.MediaItem> f5287c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Application f5288d;

        /* JADX INFO: renamed from: com.vivid.spotify.details.e$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DetailChildFragment.java */
        public class C0058a extends RecyclerView.c0 {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            z3 f5290t;

            public C0058a(z3 z3Var) {
                super(z3Var.G());
                this.f5290t = z3Var;
            }
        }

        /* JADX INFO: compiled from: DetailChildFragment.java */
        class b extends RecyclerView.c0 {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            private final d4.f f5292t;

            b(d4.f fVar) {
                super(fVar.G());
                this.f5292t = fVar;
            }
        }

        public a(Application application) {
            this.f5288d = application;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void F() {
            e eVar = e.this;
            eVar.f5286t0.c(eVar.T(x.added_to_queue));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void G(BaseListItemViewModel baseListItemViewModel, View view) {
            e.this.f5285s0.j(((MediaBrowserCompat.MediaItem) baseListItemViewModel.getContent()).h(), new m.d() { // from class: com.vivid.spotify.details.d
                @Override // i3.m.d
                public final void b() {
                    this.f5278a.F();
                }
            });
        }

        public void H(List<MediaBrowserCompat.MediaItem> list) {
            v3.d dVar = new v3.d(new v3.c(this.f5287c, list));
            f.c cVarA = androidx.recyclerview.widget.f.a(dVar);
            this.f5287c.clear();
            this.f5287c.addAll(list);
            cVarA.e(this);
            int iF = dVar.f();
            if (iF != -1) {
                e.this.f5280n0.G.j1(iF);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            return this.f5287c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int g(int i7) {
            return this.f5287c.get(0).k() ? b.AlbumList.getType() : b.SongList.getType();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void q(RecyclerView.c0 c0Var, int i7) {
            MediaBrowserCompat.MediaItem mediaItem = this.f5287c.get(i7);
            if (!(c0Var instanceof b)) {
                if (c0Var instanceof C0058a) {
                    C0058a c0058a = (C0058a) c0Var;
                    c0058a.f5290t.d0().setMediaItem(mediaItem);
                    c0058a.f5290t.A();
                    return;
                }
                return;
            }
            b bVar = (b) c0Var;
            BaseListItemViewModel baseListItemViewModelD0 = bVar.f5292t.d0();
            baseListItemViewModelD0.setContent(mediaItem);
            int iK = (int) (f4.d.k(mediaItem) * 1000.0d);
            baseListItemViewModelD0.isCompletionPercentageShow.r(iK < 1000 && iK > 0);
            baseListItemViewModelD0.completionPercentage.r(iK);
            baseListItemViewModelD0.title.r(BidiFormatter.getInstance().unicodeWrap(i3.c.g(mediaItem)));
            baseListItemViewModelD0.subTitle.r(BidiFormatter.getInstance().unicodeWrap(i3.c.f(mediaItem)));
            baseListItemViewModelD0.isExplicit.r(i3.c.h(mediaItem));
            baseListItemViewModelD0.is19Plus.r(f4.d.n(mediaItem));
            baseListItemViewModelD0.setPlaceHolderByUri(i3.c.d(mediaItem));
            if (e.this.f5285s0.z()) {
                baseListItemViewModelD0.showRightButton.r(true);
                baseListItemViewModelD0.rightImageDrawable.r(e.this.N().getDrawable(com.vivid.spotify.s.add_to_queue, e.this.s().getTheme()));
            }
            bVar.f5292t.A();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.c0 s(ViewGroup viewGroup, int i7) {
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
            if (i7 == b.AlbumList.getType()) {
                z3 z3VarE0 = z3.e0(layoutInflaterFrom, viewGroup, false);
                Application application = this.f5288d;
                e eVar = e.this;
                MediaCoverItemViewModel mediaCoverItemViewModel = new MediaCoverItemViewModel(application, eVar.f5284r0, eVar.f5285s0, eVar.f5281o0);
                e.this.N1(mediaCoverItemViewModel);
                z3VarE0.g0(mediaCoverItemViewModel);
                return new C0058a(z3VarE0);
            }
            Application application2 = this.f5288d;
            e eVar2 = e.this;
            final BaseListItemViewModel baseListItemViewModel = new BaseListItemViewModel(application2, eVar2.f5284r0, eVar2.f5285s0, eVar2.f5282p0.getMediaItem());
            e.this.N1(baseListItemViewModel);
            baseListItemViewModel.setBackground(e.this.N().getDrawable(com.vivid.spotify.s.list_item_bg, e.this.s().getTheme()));
            d4.f fVarE0 = d4.f.e0(layoutInflaterFrom, viewGroup, false);
            fVarE0.g0(baseListItemViewModel);
            baseListItemViewModel.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.vivid.spotify.details.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f5276e.G(baseListItemViewModel, view);
                }
            });
            return new b(fVarE0);
        }
    }

    /* JADX INFO: compiled from: DetailChildFragment.java */
    private enum b {
        SongList(0),
        AlbumList(1);


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f5295e;

        b(int i7) {
            this.f5295e = i7;
        }

        public int getType() {
            return this.f5295e;
        }
    }

    private void d2(List<MediaBrowserCompat.MediaItem> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        MediaBrowserCompat.MediaItem mediaItem = list.get(0);
        RecyclerView.o layoutManager = this.f5280n0.G.getLayoutManager();
        if (!mediaItem.k()) {
            if (layoutManager == null || (layoutManager instanceof GridLayoutManager)) {
                BaseRecyclerView baseRecyclerView = this.f5280n0.G;
                baseRecyclerView.setPaddingRelative(baseRecyclerView.getPaddingStart(), this.f5280n0.G.getPaddingTop(), s().getResources().getDimensionPixelSize(com.vivid.spotify.r.common_list_padding_end), this.f5280n0.G.getPaddingBottom());
                h2(this.f5280n0.G);
                this.f5280n0.G.h(new com.vivid.spotify.common.ui.c(1, N().getDimensionPixelSize(com.vivid.spotify.r.base_list_item_decoration_size)));
                this.f5280n0.G.setLayoutManager(new LinearLayoutManager(s()));
                return;
            }
            return;
        }
        int integer = N().getInteger(u.list_span_count_second);
        if (layoutManager != null && (layoutManager instanceof GridLayoutManager) && ((GridLayoutManager) layoutManager).X2() == integer) {
            return;
        }
        BaseRecyclerView baseRecyclerView2 = this.f5280n0.G;
        baseRecyclerView2.setPaddingRelative(baseRecyclerView2.getPaddingStart(), this.f5280n0.G.getPaddingTop(), s().getResources().getDimensionPixelSize(com.vivid.spotify.r.media_cover_grid_padding_end), this.f5280n0.G.getPaddingBottom());
        h2(this.f5280n0.G);
        this.f5280n0.G.h(new com.vivid.spotify.common.ui.c(0, s().getResources().getDimensionPixelSize(com.vivid.spotify.r.media_cover_grid_item_padding)));
        this.f5280n0.G.setLayoutManager(new GridLayoutManager(s(), integer));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e2(List list) {
        this.f5279m0.setMediaItem(list);
        d2(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f2(MediaBrowserCompat.MediaItem mediaItem) {
        this.f5281o0 = mediaItem;
    }

    public static e g2(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("media_title", str);
        e eVar = new e();
        eVar.C1(bundle);
        return eVar;
    }

    private static void h2(RecyclerView recyclerView) {
        for (int itemDecorationCount = recyclerView.getItemDecorationCount() - 1; itemDecorationCount >= 0; itemDecorationCount--) {
            recyclerView.Y0(itemDecorationCount);
        }
    }

    public static void i2(RecyclerView recyclerView, List<MediaBrowserCompat.MediaItem> list) {
        a aVar = (a) recyclerView.getAdapter();
        if (aVar == null || list == null) {
            return;
        }
        aVar.H(list);
    }

    @Override // com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void B0() {
        super.B0();
        this.f5282p0.observeParentMediaItem().l(X());
        this.f5282p0.observeListData(this.f5283q0).l(X());
        this.f5280n0 = null;
    }

    @Override // com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f5280n0 = (e2) androidx.databinding.g.i(layoutInflater, v.fragment_play_list, viewGroup, false);
        this.f5279m0 = (DetailChildViewModel) new b0(this).a(DetailChildViewModel.class);
        this.f5283q0 = q().getString("media_title");
        this.f5281o0 = (MediaBrowserCompat.MediaItem) q().getParcelable("parent_media_item");
        DetailViewModel detailViewModel = (DetailViewModel) c0.c(G()).a(DetailViewModel.class);
        this.f5282p0 = detailViewModel;
        LiveData<List<MediaBrowserCompat.MediaItem>> liveDataObserveListData = detailViewModel.observeListData(this.f5283q0);
        a aVar = new a(j().getApplication());
        if (liveDataObserveListData != null && liveDataObserveListData.e() != null) {
            aVar.f5287c.addAll(liveDataObserveListData.e());
        }
        this.f5280n0.G.setAdapter(aVar);
        d2(liveDataObserveListData.e());
        liveDataObserveListData.f(X(), new androidx.lifecycle.u() { // from class: com.vivid.spotify.details.b
            @Override // androidx.lifecycle.u
            public final void c(Object obj) {
                this.f5275a.e2((List) obj);
            }
        });
        this.f5281o0 = this.f5282p0.observeParentMediaItem().e();
        this.f5282p0.observeParentMediaItem().f(X(), new androidx.lifecycle.u() { // from class: com.vivid.spotify.details.a
            @Override // androidx.lifecycle.u
            public final void c(Object obj) {
                this.f5274a.f2((MediaBrowserCompat.MediaItem) obj);
            }
        });
        this.f5280n0.G.setTag("tag_list");
        this.f5280n0.d0(this.f5282p0);
        this.f5280n0.e0(this.f5279m0);
        return this.f5280n0.G();
    }
}
