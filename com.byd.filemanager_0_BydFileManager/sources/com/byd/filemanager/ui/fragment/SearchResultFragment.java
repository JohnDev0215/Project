package com.byd.filemanager.ui.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileType;
import com.byd.filemanager.k.l;
import com.byd.filemanager.k.n;
import com.byd.filemanager.ui.MainActivity;
import com.byd.filemanager.ui.SearchActivity;
import com.byd.filemanager.ui.b.b;
import com.byd.filemanager.ui.d.i;
import com.byd.filemanager.ui.view.EmptyRecyclerView;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class SearchResultFragment extends BaseFragment {
    private String c0;
    private EmptyRecyclerView d0;
    private TabLayout e0;
    private com.byd.filemanager.ui.b.b f0;
    private String[] i0;
    private Map<Integer, List<FileItem>> k0;
    private TextView l0;
    private ImageView m0;
    private List<FileItem> g0 = new ArrayList();
    private List<FileItem> h0 = new ArrayList();
    private int j0 = 0;
    private Handler n0 = new Handler(Looper.getMainLooper()) { // from class: com.byd.filemanager.ui.fragment.SearchResultFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                SearchResultFragment.this.f0.h();
            }
        }
    };

    private void J1() {
        for (int i = 0; i < this.i0.length; i++) {
            TabLayout tabLayout = this.e0;
            tabLayout.d(tabLayout.x().n(O1(i)));
        }
        TabLayout.g gVarW = this.e0.w(this.j0);
        if (gVarW != null) {
            gVarW.k();
        }
        this.e0.c(new TabLayout.d() { // from class: com.byd.filemanager.ui.fragment.SearchResultFragment.5
            @Override // com.google.android.material.tabs.TabLayout.c
            public void a(TabLayout.g gVar) {
                if (com.byd.filemanager.f.h().v()) {
                    return;
                }
                ((TextView) gVar.d().findViewById(R.id.tv_name)).setTextAppearance(R.style.TabLayoutTextUnSelected);
            }

            @Override // com.google.android.material.tabs.TabLayout.c
            public void b(TabLayout.g gVar) {
                if (!com.byd.filemanager.f.h().v()) {
                    ((TextView) gVar.d().findViewById(R.id.tv_name)).setTextAppearance(R.style.TabLayoutTextSelected);
                }
                final int iF = gVar.f();
                SearchResultFragment.this.j0 = iF;
                com.byd.filemanager.k.b.b("position = " + iF);
                SearchResultFragment.this.g0.clear();
                if (iF == 0) {
                    if (!TextUtils.isEmpty(SearchResultFragment.this.c0)) {
                        SearchResultFragment.this.h0.sort(new l.a(SearchResultFragment.this.c0));
                    }
                    SearchResultFragment.this.g0.addAll(SearchResultFragment.this.h0);
                } else {
                    List list = (List) SearchResultFragment.this.h0.stream().filter(new Predicate<FileItem>() { // from class: com.byd.filemanager.ui.fragment.SearchResultFragment.5.1
                        @Override // java.util.function.Predicate
                        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                        public boolean test(FileItem fileItem) {
                            return fileItem.F() == iF - 1;
                        }
                    }).collect(Collectors.toList());
                    if (!TextUtils.isEmpty(SearchResultFragment.this.c0)) {
                        list.sort(new l.a(SearchResultFragment.this.c0));
                    }
                    SearchResultFragment.this.g0.addAll(list);
                }
                SearchResultFragment.this.f0.h();
            }

            @Override // com.google.android.material.tabs.TabLayout.c
            public void c(TabLayout.g gVar) {
            }
        });
        T1(2, com.byd.filemanager.e.f().p());
        T1(3, com.byd.filemanager.e.f().q());
    }

    static /* synthetic */ boolean K1(int i, FileItem fileItem) {
        return fileItem.F() == i;
    }

    static /* synthetic */ boolean L1(int i, FileItem fileItem) {
        return fileItem.F() == i;
    }

    static /* synthetic */ boolean M1(int i, FileItem fileItem) {
        return fileItem.F() == i;
    }

    static /* synthetic */ boolean N1(int i, FileItem fileItem) {
        return fileItem.F() == i;
    }

    private View O1(int i) {
        View viewInflate;
        TextView textView;
        if (com.byd.filemanager.f.h().v()) {
            viewInflate = LayoutInflater.from(p()).inflate(R.layout.tab_text, (ViewGroup) this.e0, false);
            textView = (TextView) viewInflate.findViewById(R.id.tv_name);
        } else {
            viewInflate = LayoutInflater.from(p()).inflate(R.layout.tab_text_denza, (ViewGroup) this.e0, false);
            textView = (TextView) viewInflate.findViewById(R.id.tv_name);
            if (i == 0) {
                textView.setTextAppearance(R.style.TabLayoutTextSelected);
            } else {
                textView.setTextAppearance(R.style.TabLayoutTextUnSelected);
            }
        }
        textView.setText(this.i0[i]);
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S1(FileItem fileItem) {
        Intent intent = new Intent(p(), (Class<?>) MainActivity.class);
        intent.addFlags(69206016);
        intent.putExtra("extra_file", fileItem.w());
        w1(intent);
    }

    private void T1(int i, boolean z) {
        TabLayout.TabView tabView = this.e0.w(i).i;
        tabView.setClickable(z);
        TextView textView = (TextView) tabView.findViewById(R.id.tv_name);
        if (!z) {
            textView.setTextColor(K().getColor(R.color.text_tip));
        }
        textView.setEnabled(z);
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected void A1(View view) {
        this.d0 = (EmptyRecyclerView) view.findViewById(R.id.recyclerView);
        this.e0 = (TabLayout) view.findViewById(R.id.tabLayout);
        J1();
        this.d0.setLayoutManager(new LinearLayoutManager(p()));
        androidx.recyclerview.widget.d dVar = new androidx.recyclerview.widget.d(p(), 1);
        dVar.l(androidx.core.content.a.d(p(), R.drawable.bg_divider));
        this.d0.h(dVar);
        com.byd.filemanager.ui.b.b bVar = new com.byd.filemanager.ui.b.b(p(), this.g0);
        this.f0 = bVar;
        bVar.K(false);
        this.f0.O(1);
        this.d0.setEmptyView(view.findViewById(R.id.ly_empty));
        this.d0.setAdapter(this.f0);
        this.d0.getRecycledViewPool().k(1, com.byd.filemanager.k.c.d(j1()) ? 6 : 11);
        i.a(this.d0, 0);
        this.f0.L(new com.byd.filemanager.ui.a<FileItem>() { // from class: com.byd.filemanager.ui.fragment.SearchResultFragment.2
            @Override // com.byd.filemanager.ui.a
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(FileItem fileItem, int i) {
                com.byd.filemanager.k.b.b("onItemClick fileItem = " + fileItem);
                if (!fileItem.N()) {
                    SearchResultFragment.this.S1(fileItem);
                    return;
                }
                if (fileItem.G() == FileType.APK) {
                    if (com.byd.filemanager.k.d.E(fileItem)) {
                        com.byd.filemanager.k.a.e(SearchResultFragment.this.j1(), fileItem);
                        return;
                    } else {
                        n.a(R.string.apk_install);
                        return;
                    }
                }
                if (com.byd.filemanager.k.a.b(SearchResultFragment.this.p(), fileItem) != null) {
                    com.byd.filemanager.k.d.J(SearchResultFragment.this.p(), fileItem);
                } else {
                    com.byd.filemanager.ui.c.l.i2(fileItem).c2(SearchResultFragment.this.w());
                }
            }
        });
        this.f0.M(new b.g() { // from class: com.byd.filemanager.ui.fragment.SearchResultFragment.3
            @Override // com.byd.filemanager.ui.b.b.g
            public boolean a(View view2, FileItem fileItem) {
                return false;
            }
        });
        this.k0 = new HashMap();
        this.m0 = (ImageView) view.findViewById(R.id.iv_icon_empty);
        if (com.byd.filemanager.f.h().v()) {
            this.m0.setImageResource(R.drawable.img_empty);
        } else {
            this.m0.setImageResource(R.drawable.img_empty_denza);
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_desc_empty);
        this.l0 = textView;
        textView.setText(R.string.empty_file_no_found);
        if (!TextUtils.isEmpty(this.c0)) {
            com.byd.filemanager.k.b.b("mLastKey:" + this.c0);
            this.f0.J(this.c0);
            this.g0.sort(new l.a(this.c0));
        }
        this.d0.l(new RecyclerView.t() { // from class: com.byd.filemanager.ui.fragment.SearchResultFragment.4
            @Override // androidx.recyclerview.widget.RecyclerView.t
            public void a(RecyclerView recyclerView, int i) {
                super.a(recyclerView, i);
                if (i == 1) {
                    ((SearchActivity) SearchResultFragment.this.p()).b0();
                }
            }
        });
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected int B1() {
        return com.byd.filemanager.f.h().v() ? R.layout.fragment_search_result : R.layout.fragment_search_result_denza;
    }

    public int I1() {
        return this.j0;
    }

    public void P1(FileItem fileItem) {
        List<FileItem> list;
        if (fileItem == null || (list = this.h0) == null) {
            return;
        }
        list.add(fileItem);
        this.g0.add(fileItem);
        this.n0.removeMessages(1);
        this.n0.sendEmptyMessageDelayed(1, 500L);
    }

    public void Q1(String str) {
        this.c0 = str;
        this.n0.removeMessages(1);
        com.byd.filemanager.ui.b.b bVar = this.f0;
        if (bVar == null) {
            com.byd.filemanager.k.b.g("mFileAdapter is null");
            return;
        }
        bVar.J(this.c0);
        this.g0.sort(new l.a(str));
        this.f0.h();
    }

    public void R1() {
        List<FileItem> list = this.h0;
        if (list != null && list.size() > 0) {
            this.h0.clear();
        }
        List<FileItem> list2 = this.g0;
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        this.g0.clear();
        com.byd.filemanager.ui.b.b bVar = this.f0;
        if (bVar != null) {
            bVar.h();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        Bundle bundleV = v();
        if (bundleV != null) {
            this.j0 = bundleV.getInt("key_search_tab_position", 0);
        }
        this.i0 = K().getStringArray(R.array.tabs_all);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean z = configuration.orientation == 2;
        this.d0.getRecycledViewPool().k(1, z ? 6 : 11);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.m0.getLayoutParams();
        layoutParams.topMargin = K().getDimensionPixelSize(z ? R.dimen.dp_48 : R.dimen.dp_333);
        this.m0.setLayoutParams(layoutParams);
    }

    @org.greenrobot.eventbus.l(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.music.f.b bVar) {
        final int iB = bVar.b();
        com.byd.filemanager.k.b.b("onDiskEvent root = " + iB);
        T1(2, com.byd.filemanager.e.f().p());
        T1(3, com.byd.filemanager.e.f().q());
        if (com.byd.filemanager.e.f().s(iB)) {
            return;
        }
        if (iB == 1) {
            this.h0.removeIf(new Predicate() { // from class: com.byd.filemanager.ui.fragment.f
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return SearchResultFragment.K1(iB, (FileItem) obj);
                }
            });
            int i = this.j0;
            if (i == 2) {
                this.g0.clear();
            } else if (i == 0) {
                this.g0.removeIf(new Predicate() { // from class: com.byd.filemanager.ui.fragment.e
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return SearchResultFragment.L1(iB, (FileItem) obj);
                    }
                });
            }
            this.f0.h();
        }
        if (iB == 2) {
            this.h0.removeIf(new Predicate() { // from class: com.byd.filemanager.ui.fragment.g
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return SearchResultFragment.M1(iB, (FileItem) obj);
                }
            });
            int i2 = this.j0;
            if (i2 == 3) {
                this.g0.clear();
            } else if (i2 == 0) {
                this.g0.removeIf(new Predicate() { // from class: com.byd.filemanager.ui.fragment.d
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return SearchResultFragment.N1(iB, (FileItem) obj);
                    }
                });
            }
            this.f0.h();
        }
    }

    @org.greenrobot.eventbus.l(threadMode = ThreadMode.MAIN)
    public void onPlayEvent(com.byd.music.a aVar) {
        n.a(R.string.error_open);
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void r0() {
        this.n0.removeMessages(1);
        super.r0();
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected boolean z1() {
        return true;
    }
}
