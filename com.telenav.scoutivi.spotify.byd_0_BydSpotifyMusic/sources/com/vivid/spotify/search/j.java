package com.vivid.spotify.search;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.text.BidiFormatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.b0;
import androidx.lifecycle.i;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vivid.spotify.common.ui.BaseListItemViewModel;
import com.vivid.spotify.common.ui.MediaCoverItemViewModel;
import com.vivid.spotify.search.j;
import com.vivid.spotify.v;
import com.vivid.spotify.x;
import d4.y2;
import d4.z3;
import i3.m;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: SearchListFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends com.vivid.spotify.search.b {

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private final String f5553m0 = "SearchListFragment";

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private SearchListViewModel f5554n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    private y2 f5555o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    f4.m f5556p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    i3.m f5557q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    y3.b f5558r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    SearchViewModel f5559s0;

    /* JADX INFO: compiled from: SearchListFragment.java */
    class a extends RecyclerView.t {
        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i7, int i8) {
            j.this.a2();
        }
    }

    /* JADX INFO: compiled from: SearchListFragment.java */
    class b extends GridLayoutManager.c {
        b() {
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int f(int i7) {
            return 1;
        }
    }

    /* JADX INFO: compiled from: SearchListFragment.java */
    public class c extends com.vivid.spotify.common.base.d<RecyclerView.c0> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Application f5562d;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<MediaBrowserCompat.MediaItem> f5561c = new ArrayList();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f5563e = d.SongList.getType();

        /* JADX INFO: compiled from: SearchListFragment.java */
        public class a extends RecyclerView.c0 {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            z3 f5565t;

            public a(z3 z3Var) {
                super(z3Var.G());
                this.f5565t = z3Var;
            }
        }

        /* JADX INFO: compiled from: SearchListFragment.java */
        class b extends RecyclerView.c0 {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            private final d4.f f5567t;

            b(d4.f fVar) {
                super(fVar.G());
                this.f5567t = fVar;
            }
        }

        public c(Application application) {
            this.f5562d = application;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void H(MediaCoverItemViewModel mediaCoverItemViewModel, androidx.lifecycle.o oVar, i.b bVar) {
            if (bVar == i.b.ON_START) {
                mediaCoverItemViewModel.onResume();
            } else if (bVar == i.b.ON_STOP) {
                mediaCoverItemViewModel.onPause();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void I(BaseListItemViewModel baseListItemViewModel, androidx.lifecycle.o oVar, i.b bVar) {
            if (bVar == i.b.ON_START) {
                baseListItemViewModel.onResume();
            } else if (bVar == i.b.ON_STOP) {
                baseListItemViewModel.onPause();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void J(BaseListItemViewModel baseListItemViewModel, View view) {
            j.this.f5556p0.r((MediaBrowserCompat.MediaItem) baseListItemViewModel.getContent());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void K() {
            j jVar = j.this;
            jVar.f5558r0.c(jVar.T(x.added_to_queue));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void L(BaseListItemViewModel baseListItemViewModel, View view) {
            j.this.f5557q0.j(((MediaBrowserCompat.MediaItem) baseListItemViewModel.getContent()).h(), new m.d() { // from class: com.vivid.spotify.search.o
                @Override // i3.m.d
                public final void b() {
                    this.f5577a.K();
                }
            });
        }

        public void M(List<MediaBrowserCompat.MediaItem> list) {
            this.f5561c.clear();
            this.f5561c.addAll(list);
            j();
        }

        public void N(int i7) {
            this.f5563e = i7;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            return this.f5561c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public long f(int i7) {
            return (this.f5561c.get(i7).j() + this.f5563e).hashCode();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int g(int i7) {
            return this.f5563e;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void q(RecyclerView.c0 c0Var, int i7) {
            MediaBrowserCompat.MediaItem mediaItem = this.f5561c.get(i7);
            if (!(c0Var instanceof b)) {
                if (c0Var instanceof a) {
                    ((a) c0Var).f5565t.d0().setMediaItem(mediaItem);
                    return;
                }
                return;
            }
            BaseListItemViewModel baseListItemViewModelD0 = ((b) c0Var).f5567t.d0();
            baseListItemViewModelD0.setContent(mediaItem);
            int iK = (int) (f4.d.k(mediaItem) * 1000.0d);
            baseListItemViewModelD0.isCompletionPercentageShow.r(iK < 1000 && iK > 0);
            baseListItemViewModelD0.completionPercentage.r(iK);
            baseListItemViewModelD0.title.r(BidiFormatter.getInstance().unicodeWrap(i3.c.g(mediaItem)));
            baseListItemViewModelD0.subTitle.r(BidiFormatter.getInstance().unicodeWrap(i3.c.f(mediaItem)));
            baseListItemViewModelD0.isExplicit.r(i3.c.h(mediaItem));
            baseListItemViewModelD0.is19Plus.r(f4.d.n(mediaItem));
            baseListItemViewModelD0.setPlaceHolderByUri(i3.c.d(mediaItem));
            if (j.this.f5557q0.z()) {
                baseListItemViewModelD0.showRightButton.r(true);
                baseListItemViewModelD0.rightImageDrawable.r(j.this.N().getDrawable(com.vivid.spotify.s.add_to_queue, j.this.s().getTheme()));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.c0 s(ViewGroup viewGroup, int i7) {
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
            if (i7 == d.AlbumList.getType()) {
                Application application = this.f5562d;
                j jVar = j.this;
                final MediaCoverItemViewModel mediaCoverItemViewModel = new MediaCoverItemViewModel(application, jVar.f5556p0, jVar.f5557q0, null);
                j.this.a().a(new androidx.lifecycle.m() { // from class: com.vivid.spotify.search.n
                    @Override // androidx.lifecycle.m
                    public final void d(androidx.lifecycle.o oVar, i.b bVar) {
                        j.c.H(mediaCoverItemViewModel, oVar, bVar);
                    }
                });
                z3 z3VarE0 = z3.e0(layoutInflaterFrom, viewGroup, false);
                z3VarE0.g0(mediaCoverItemViewModel);
                return new a(z3VarE0);
            }
            Application application2 = this.f5562d;
            j jVar2 = j.this;
            final BaseListItemViewModel baseListItemViewModel = new BaseListItemViewModel(application2, jVar2.f5556p0, jVar2.f5557q0);
            j.this.a().a(new androidx.lifecycle.m() { // from class: com.vivid.spotify.search.m
                @Override // androidx.lifecycle.m
                public final void d(androidx.lifecycle.o oVar, i.b bVar) {
                    j.c.I(baseListItemViewModel, oVar, bVar);
                }
            });
            baseListItemViewModel.setBackground(j.this.N().getDrawable(com.vivid.spotify.s.list_item_bg, j.this.s().getTheme()));
            d4.f fVarE0 = d4.f.e0(layoutInflaterFrom, viewGroup, false);
            fVarE0.g0(baseListItemViewModel);
            baseListItemViewModel.setOnClickListener(new View.OnClickListener() { // from class: com.vivid.spotify.search.k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f5571e.J(baseListItemViewModel, view);
                }
            });
            baseListItemViewModel.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.vivid.spotify.search.l
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f5573e.L(baseListItemViewModel, view);
                }
            });
            return new b(fVarE0);
        }
    }

    /* JADX INFO: compiled from: SearchListFragment.java */
    private enum d {
        SongList(0),
        AlbumList(1);


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f5570e;

        d(int i7) {
            this.f5570e = i7;
        }

        public int getType() {
            return this.f5570e;
        }
    }

    private static boolean X1(MediaBrowserCompat.MediaItem mediaItem) {
        return i3.c.c(mediaItem).startsWith("spotify:episode") || i3.c.c(mediaItem).startsWith("spotify:track");
    }

    public static j Y1() {
        return new j();
    }

    private static void Z1(RecyclerView recyclerView) {
        for (int itemDecorationCount = recyclerView.getItemDecorationCount() - 1; itemDecorationCount >= 0; itemDecorationCount--) {
            recyclerView.Y0(itemDecorationCount);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a2() {
        this.f5559s0.topAlpha.m(Float.valueOf(this.f5555o0.G.computeVerticalScrollOffset() / N().getDimension(com.vivid.spotify.r.fragment_search_top_bg_height)));
    }

    public static void b2(RecyclerView recyclerView, List<MediaBrowserCompat.MediaItem> list) {
        boolean z6;
        if (list.size() != 0) {
            c cVar = (c) recyclerView.getAdapter();
            MediaBrowserCompat.MediaItem mediaItem = list.get(0);
            loop0: while (true) {
                for (MediaBrowserCompat.MediaItem mediaItem2 : list) {
                    z6 = z6 && X1(mediaItem2);
                }
            }
            RecyclerView.o layoutManager = recyclerView.getLayoutManager();
            if (mediaItem.k() || !z6) {
                int integer = recyclerView.getContext().getResources().getInteger(com.vivid.spotify.u.list_span_count);
                if (layoutManager == null || !(layoutManager instanceof GridLayoutManager)) {
                    recyclerView.setPaddingRelative(recyclerView.getPaddingLeft(), recyclerView.getPaddingTop(), recyclerView.getContext().getResources().getDimensionPixelSize(com.vivid.spotify.r.media_cover_grid_padding_end), recyclerView.getPaddingBottom());
                    new GridLayoutManager(recyclerView.getContext(), integer).g3(new b());
                    cVar.N(d.AlbumList.getType());
                    recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), integer));
                    Z1(recyclerView);
                    recyclerView.h(new com.vivid.spotify.common.ui.c(0, recyclerView.getContext().getResources().getDimensionPixelSize(com.vivid.spotify.r.media_cover_grid_item_padding)));
                } else {
                    ((GridLayoutManager) layoutManager).f3(integer);
                }
            } else if (layoutManager == null || (layoutManager instanceof GridLayoutManager)) {
                recyclerView.setPaddingRelative(recyclerView.getPaddingLeft(), recyclerView.getPaddingTop(), recyclerView.getResources().getDimensionPixelSize(com.vivid.spotify.r.common_list_padding_end), recyclerView.getPaddingBottom());
                Z1(recyclerView);
                cVar.N(d.SongList.getType());
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            }
            if (cVar != null) {
                cVar.M(list);
            }
        }
    }

    @Override // com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void B0() {
        super.B0();
    }

    @Override // com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f5559s0 = (SearchViewModel) new b0(G()).a(SearchViewModel.class);
        this.f5555o0 = (y2) androidx.databinding.g.i(layoutInflater, v.fragment_search_play_list, viewGroup, false);
        this.f5554n0 = (SearchListViewModel) new b0(this).a(SearchListViewModel.class);
        this.f5555o0.G.setAdapter(new c(j().getApplication()));
        this.f5555o0.G.setTag("tag_list");
        this.f5555o0.G.l(new a());
        a2();
        this.f5555o0.e0(this.f5554n0);
        ((androidx.recyclerview.widget.q) this.f5555o0.G.getItemAnimator()).Q(false);
        return this.f5555o0.G();
    }
}
