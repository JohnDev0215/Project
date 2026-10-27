package com.vivid.spotify.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.b0;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.d;
import com.vivid.labels.LabelsView;
import com.vivid.spotify.nowplaying.NowPlayingViewModel;
import com.vivid.spotify.rootui.w;
import d4.t2;
import d4.y2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SearchFragment extends com.vivid.spotify.search.a {

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    private SearchViewModel f5519q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    private d f5520r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    private NowPlayingViewModel f5521s0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private final String f5518p0 = "SearchFragment";

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    private final TabLayout.d f5522t0 = new a();

    class a implements TabLayout.d {
        a() {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(TabLayout.f fVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(TabLayout.f fVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(TabLayout.f fVar) {
            SearchFragment searchFragment = SearchFragment.this;
            ((t2) searchFragment.f5218j0).S.R(searchFragment.h2(fVar.g(), 0.0f));
            SearchFragment.this.r2();
        }
    }

    class b implements TextWatcher {
        b() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable)) {
                SearchFragment.this.f5519q0.resetData();
            } else {
                SearchFragment.this.f5519q0.search();
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        }
    }

    class c implements d.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f5525a;

        c(List list) {
            this.f5525a = list;
        }

        @Override // com.google.android.material.tabs.d.b
        public void a(TabLayout.f fVar, int i7) {
            fVar.r(((w) this.f5525a.get(i7)).f5512a);
        }
    }

    private class d extends FragmentStateAdapter {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final List<w> f5527k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private final HashMap<Integer, Long> f5528l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private int f5529m;

        class a implements View.OnLayoutChangeListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ androidx.viewpager2.adapter.a f5531a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f5532b;

            a(androidx.viewpager2.adapter.a aVar, int i7) {
                this.f5531a = aVar;
                this.f5532b = i7;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
                if (((ViewGroup) this.f5531a.f2857a).getChildCount() > 0) {
                    this.f5531a.f2857a.removeOnLayoutChangeListener(this);
                    ((y2) androidx.databinding.g.f(((ViewGroup) this.f5531a.f2857a).getChildAt(0))).d0().setMediaItem(((w) d.this.f5527k.get(this.f5532b)).f5514c);
                }
            }
        }

        public d(FragmentManager fragmentManager, androidx.lifecycle.i iVar, List<w> list) {
            super(fragmentManager, iVar);
            ArrayList arrayList = new ArrayList();
            this.f5527k = arrayList;
            this.f5528l = new HashMap<>();
            this.f5529m = 0;
            if (list != null) {
                arrayList.addAll(list);
            }
        }

        private void W() {
            this.f5529m++;
        }

        private boolean X(List<w> list, List<w> list2) {
            if (list.size() != list2.size()) {
                return false;
            }
            for (int i7 = 0; i7 < list.size(); i7++) {
                if (!list.get(i7).equals(list2.get(i7))) {
                    return false;
                }
            }
            return true;
        }

        @Override // androidx.viewpager2.adapter.FragmentStateAdapter
        public Fragment D(int i7) {
            return j.Y1();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: Y, reason: merged with bridge method [inline-methods] */
        public void r(androidx.viewpager2.adapter.a aVar, int i7, List<Object> list) {
            super.r(aVar, i7, list);
            y2 y2Var = (y2) androidx.databinding.g.f(((ViewGroup) aVar.f2857a).getChildAt(0));
            if (y2Var != null) {
                y2Var.d0().setMediaItem(this.f5527k.get(i7).f5514c);
            } else {
                aVar.f2857a.addOnLayoutChangeListener(new a(aVar, i7));
            }
        }

        public void Z(List<w> list) {
            if (!X(this.f5527k, list)) {
                this.f5527k.clear();
                this.f5527k.addAll(list);
                j();
                ((t2) SearchFragment.this.f5218j0).V.j(0, false);
            }
            if (list.isEmpty()) {
                W();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            return this.f5527k.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int g(int i7) {
            return (this.f5527k.get(i7).f5512a + i7 + this.f5529m).hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int h2(int i7, float f7) {
        View childAt = ((t2) this.f5218j0).S.getChildAt(0);
        if (childAt == null) {
            return -1;
        }
        ViewGroup viewGroup = (ViewGroup) childAt;
        View childAt2 = viewGroup.getChildAt(i7);
        int i8 = i7 + 1;
        View childAt3 = i8 < viewGroup.getChildCount() ? viewGroup.getChildAt(i8) : null;
        int width = childAt2 != null ? childAt2.getWidth() : 0;
        int width2 = childAt3 != null ? childAt3.getWidth() : 0;
        int left = (childAt2.getLeft() + (width / 2)) - (((t2) this.f5218j0).S.getWidth() / 2);
        int i9 = (int) ((width + width2) * 0.5f * f7);
        return i0.u.y(((t2) this.f5218j0).S) == 0 ? left + i9 : left - i9;
    }

    private void i2() {
        if (this.f5218j0 == 0 || j() == null || j().getCurrentFocus() == null) {
            return;
        }
        ((InputMethodManager) s().getSystemService("input_method")).hideSoftInputFromWindow(j().getCurrentFocus().getWindowToken(), 0);
        ((t2) this.f5218j0).G.clearFocus();
    }

    private void j2() {
        ((t2) this.f5218j0).G.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.vivid.spotify.search.c
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i7, KeyEvent keyEvent) {
                return this.f5547a.k2(textView, i7, keyEvent);
            }
        });
        ((t2) this.f5218j0).G.addTextChangedListener(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean k2(TextView textView, int i7, KeyEvent keyEvent) {
        String str;
        h3.l lVarB = h3.i.b("SearchFragment");
        StringBuilder sb = new StringBuilder();
        sb.append("a :  ");
        sb.append(i7);
        sb.append("   ;  b :    ");
        if (keyEvent == null) {
            str = "null";
        } else {
            str = keyEvent.getKeyCode() + "";
        }
        sb.append(str);
        lVarB.b(sb.toString(), new Object[0]);
        if (i7 != 3 && keyEvent != null && keyEvent.getKeyCode() != 66) {
            return false;
        }
        i2();
        if (!this.f5519q0.searchIfNeeded()) {
            ((t2) this.f5218j0).V.j(0, false);
        }
        this.f5519q0.saveSearchKeyword();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void l2(View view, float f7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void m2(List list) {
        if (list == null) {
            list = new ArrayList();
        }
        p2(list);
        if (list.isEmpty()) {
            this.f5519q0.topAlpha.m(Float.valueOf(0.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n2(String str, int i7) {
        ((t2) this.f5218j0).G.setText(str);
        this.f5519q0.input.r(str);
        ((t2) this.f5218j0).G.setSelection(str.length());
        this.f5519q0.saveSearchKeyword();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o2(Float f7) {
        ((t2) this.f5218j0).U.setAlpha(f7.floatValue());
    }

    private void p2(List<w> list) {
        d dVar = this.f5520r0;
        if (dVar != null) {
            dVar.Z(list);
        }
        c cVar = new c(list);
        T t6 = this.f5218j0;
        new com.google.android.material.tabs.d(((t2) t6).S, ((t2) t6).V, false, false, cVar).a();
        int dimensionPixelSize = s().getResources().getDimensionPixelSize(com.vivid.spotify.r.artist_tab_margin_end);
        for (int i7 = 0; i7 < list.size(); i7++) {
            View childAt = ((ViewGroup) ((t2) this.f5218j0).S.getChildAt(0)).getChildAt(i7);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            marginLayoutParams.setMarginEnd(dimensionPixelSize);
            childAt.setLayoutParams(marginLayoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q2() {
        InputMethodManager inputMethodManager = (InputMethodManager) s().getSystemService("input_method");
        if (inputMethodManager != null) {
            T t6 = this.f5218j0;
            if (t6 == 0) {
                h3.i.b("SearchFragment").b("mBinding is null", new Object[0]);
            } else {
                ((t2) t6).G.requestFocus();
                inputMethodManager.showSoftInput(((t2) this.f5218j0).G, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r2() {
        View viewFindViewWithTag = ((t2) this.f5218j0).V.findViewWithTag("tag_list");
        if (viewFindViewWithTag instanceof RecyclerView) {
            ((RecyclerView) viewFindViewWithTag).j1(0);
        }
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void B0() {
        ((t2) this.f5218j0).S.E(this.f5522t0);
        ((t2) this.f5218j0).V.setAdapter(null);
        this.f5520r0 = null;
        super.B0();
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void K0() {
        super.K0();
        i2();
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void P0() {
        super.P0();
        if (this.f5218j0 == 0 || this.f5521s0.observeShowPage().e().booleanValue() || !this.f5519q0.isEmpty()) {
            return;
        }
        ((t2) this.f5218j0).G().post(new Runnable() { // from class: com.vivid.spotify.search.h
            @Override // java.lang.Runnable
            public final void run() {
                this.f5552e.q2();
            }
        });
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f5519q0 = (SearchViewModel) new b0(this).a(SearchViewModel.class);
        this.f5521s0 = (NowPlayingViewModel) new b0(t1()).a(NowPlayingViewModel.class);
        t2 t2VarE0 = t2.e0(layoutInflater, viewGroup, false);
        this.f5218j0 = t2VarE0;
        t2VarE0.g0(this.f5519q0);
        j2();
        ((t2) this.f5218j0).V.setPageTransformer(new ViewPager2.k() { // from class: com.vivid.spotify.search.f
            @Override // androidx.viewpager2.widget.ViewPager2.k
            public final void a(View view, float f7) {
                SearchFragment.l2(view, f7);
            }
        });
        this.f5519q0.pageItems.f(X(), new androidx.lifecycle.u() { // from class: com.vivid.spotify.search.e
            @Override // androidx.lifecycle.u
            public final void c(Object obj) {
                this.f5549a.m2((List) obj);
            }
        });
        ((t2) this.f5218j0).O.setOnLabelClickListener(new LabelsView.a() { // from class: com.vivid.spotify.search.g
            @Override // com.vivid.labels.LabelsView.a
            public final void a(String str, int i7) {
                this.f5551a.n2(str, i7);
            }
        });
        d dVar = new d(r(), X().a(), this.f5519q0.pageItems.e());
        this.f5520r0 = dVar;
        ((t2) this.f5218j0).V.setAdapter(dVar);
        ((t2) this.f5218j0).V.setUserInputEnabled(false);
        ((t2) this.f5218j0).R.G.N1();
        ((t2) this.f5218j0).W(X());
        ((t2) this.f5218j0).S.d(this.f5522t0);
        this.f5519q0.topAlpha.f(X(), new androidx.lifecycle.u() { // from class: com.vivid.spotify.search.d
            @Override // androidx.lifecycle.u
            public final void c(Object obj) {
                this.f5548a.o2((Float) obj);
            }
        });
        return ((t2) this.f5218j0).G();
    }
}
