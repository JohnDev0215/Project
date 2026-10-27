package com.vivid.spotify.details;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.q;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.b0;
import androidx.lifecycle.u;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.f;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.d;
import com.vivid.spotify.rootui.w;
import d4.v0;
import f4.o;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DetailFragment extends t {

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private DetailViewModel f5255p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    private d f5256q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    private MediaBrowserCompat.MediaItem f5257r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    i3.m f5258s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    private final o.a f5259t0 = new a();

    class a implements o.a {
        a() {
        }

        @Override // f4.o.a
        public void a() {
            DetailFragment.this.f5255p0.isPlaying.r(TextUtils.equals(com.vivid.spotify.nowplaying.b.l(DetailFragment.this.f5258s0.n()), i3.c.d(DetailFragment.this.f5257r0)) && DetailFragment.this.f5258s0.x());
        }
    }

    class b extends androidx.constraintlayout.motion.widget.s {
        b() {
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.j
        public void c(MotionLayout motionLayout, int i7, int i8, float f7) {
            DetailFragment.this.f5255p0.progress = motionLayout.getProgress();
        }
    }

    class c implements TabLayout.d {
        c() {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(TabLayout.f fVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(TabLayout.f fVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(TabLayout.f fVar) {
            DetailFragment.this.j2();
        }
    }

    private static class d extends FragmentStateAdapter {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final List<w> f5263k;

        class a extends f.b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ List f5264a;

            a(List list) {
                this.f5264a = list;
            }

            @Override // androidx.recyclerview.widget.f.b
            public boolean a(int i7, int i8) {
                return TextUtils.equals(((w) d.this.f5263k.get(i7)).f5512a, ((w) this.f5264a.get(i8)).f5512a) && TextUtils.equals(((w) d.this.f5263k.get(i7)).f5513b, ((w) this.f5264a.get(i8)).f5513b);
            }

            @Override // androidx.recyclerview.widget.f.b
            public boolean b(int i7, int i8) {
                return TextUtils.equals(((w) d.this.f5263k.get(i7)).f5512a, ((w) this.f5264a.get(i8)).f5512a) && TextUtils.equals(((w) d.this.f5263k.get(i7)).f5513b, ((w) this.f5264a.get(i8)).f5513b);
            }

            @Override // androidx.recyclerview.widget.f.b
            public int d() {
                return this.f5264a.size();
            }

            @Override // androidx.recyclerview.widget.f.b
            public int e() {
                return d.this.f5263k.size();
            }
        }

        public d(Fragment fragment, List<w> list) {
            super(fragment);
            ArrayList arrayList = new ArrayList();
            this.f5263k = arrayList;
            if (list != null) {
                arrayList.addAll(list);
            }
        }

        @Override // androidx.viewpager2.adapter.FragmentStateAdapter
        public Fragment D(int i7) {
            return e.g2(this.f5263k.get(i7).f5512a);
        }

        public void W(List<w> list) {
            f.c cVarA = androidx.recyclerview.widget.f.a(new a(list));
            this.f5263k.clear();
            this.f5263k.addAll(list);
            cVarA.e(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            return this.f5263k.size();
        }
    }

    public static void d2(MotionLayout motionLayout, boolean z6) {
        q.b bVarO0;
        if (motionLayout == null || (bVarO0 = motionLayout.o0(com.vivid.spotify.t.transition_detail)) == null) {
            return;
        }
        bVarO0.F(z6);
    }

    private void e2(final List<w> list) {
        d dVar = this.f5256q0;
        if (dVar != null) {
            dVar.W(list);
        }
        if (list.size() == 1 && TextUtils.isEmpty(list.get(0).f5512a)) {
            this.f5255p0.isTabLayoutShow.r(false);
            return;
        }
        this.f5255p0.isTabLayoutShow.r(true);
        d.b bVar = new d.b() { // from class: com.vivid.spotify.details.k
            @Override // com.google.android.material.tabs.d.b
            public final void a(TabLayout.f fVar, int i7) {
                DetailFragment.f2(list, fVar, i7);
            }
        };
        T t6 = this.f5218j0;
        new com.google.android.material.tabs.d(((v0) t6).T, ((v0) t6).f5797a0, false, false, bVar).a();
        int dimensionPixelSize = s().getResources().getDimensionPixelSize(com.vivid.spotify.r.artist_tab_margin_end);
        for (int i7 = 0; i7 < list.size(); i7++) {
            View childAt = ((ViewGroup) ((v0) this.f5218j0).T.getChildAt(0)).getChildAt(i7);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            marginLayoutParams.setMarginEnd(dimensionPixelSize);
            childAt.setLayoutParams(marginLayoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void f2(List list, TabLayout.f fVar, int i7) {
        fVar.r(((w) list.get(i7)).f5512a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g2(List list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.f5255p0.isShimmerShow.r(false);
        e2(list);
    }

    private void h2() {
        ((f4.o) this.f5258s0).Y(this.f5259t0);
    }

    public static void i2(ImageView imageView, boolean z6) {
        if (z6) {
            imageView.setImageDrawable(imageView.getContext().getDrawable(com.vivid.spotify.s.btn_like_unlike_podcast));
        } else {
            imageView.setImageDrawable(imageView.getContext().getDrawable(com.vivid.spotify.s.btn_like_unlike));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j2() {
        View viewFindViewWithTag = ((v0) this.f5218j0).f5797a0.findViewWithTag("tag_list");
        if (viewFindViewWithTag instanceof RecyclerView) {
            ((RecyclerView) viewFindViewWithTag).j1(0);
        }
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void B0() {
        T t6 = this.f5218j0;
        if (t6 != 0) {
            this.f5255p0.progress = ((v0) t6).P.getProgress();
            ((v0) this.f5218j0).f5797a0.setAdapter(null);
        }
        ((f4.o) this.f5258s0).e0(this.f5259t0);
        super.B0();
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f5255p0 = (DetailViewModel) new b0(this).a(DetailViewModel.class);
        v0 v0VarD0 = v0.d0(layoutInflater, viewGroup, false);
        this.f5218j0 = v0VarD0;
        v0VarD0.f0(this.f5255p0);
        if (q() != null) {
            this.f5257r0 = (MediaBrowserCompat.MediaItem) q().getParcelable("media_item");
            if (this.f5255p0.setMediaItem((MediaBrowserCompat.MediaItem) q().getParcelable("parent_media_item"), this.f5257r0)) {
                ((v0) this.f5218j0).M.N1();
                this.f5255p0.isShimmerShow.r(true);
            }
        }
        d dVar = new d(this, this.f5255p0.pageItems.e());
        this.f5256q0 = dVar;
        ((v0) this.f5218j0).f5797a0.setAdapter(dVar);
        ((v0) this.f5218j0).f5797a0.setUserInputEnabled(false);
        this.f5255p0.pageItems.f(X(), new u() { // from class: com.vivid.spotify.details.j
            @Override // androidx.lifecycle.u
            public final void c(Object obj) {
                this.f5296a.g2((List) obj);
            }
        });
        ((v0) this.f5218j0).P.Y(new b());
        ((v0) this.f5218j0).T.d(new c());
        h3.i.b("DetailFragment").b("motionProgress:" + this.f5255p0.progress, new Object[0]);
        ((v0) this.f5218j0).P.setProgress(this.f5255p0.progress);
        ((v0) this.f5218j0).W(X());
        h2();
        return ((v0) this.f5218j0).G();
    }
}
