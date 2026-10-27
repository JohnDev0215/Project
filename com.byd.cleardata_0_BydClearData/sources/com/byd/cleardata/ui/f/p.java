package com.byd.cleardata.ui.f;

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
import com.byd.cleardata.R;
import com.byd.cleardata.i.o;
import com.byd.cleardata.i.q;
import com.byd.cleardata.ui.MainActivity;
import com.byd.cleardata.ui.d.c;
import com.byd.cleardata.ui.view.EmptyRecyclerView;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class p extends h {
    private String b0;
    private EmptyRecyclerView c0;
    private TabLayout d0;
    private com.byd.cleardata.ui.d.c e0;
    private List<com.byd.cleardata.d.d> f0;
    private List<com.byd.cleardata.d.d> g0;
    private String[] h0;
    private Map<Integer, List<com.byd.cleardata.d.d>> j0;
    private TextView k0;
    private ImageView l0;
    private int i0 = 0;
    private Handler m0 = new a(Looper.getMainLooper());

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                p.this.e0.h();
            }
        }
    }

    class b implements com.byd.cleardata.ui.c<com.byd.cleardata.d.d> {
        b() {
        }

        @Override // com.byd.cleardata.ui.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(com.byd.cleardata.d.d dVar, int i) {
            com.byd.cleardata.i.c.b("onItemClick fileItem = " + dVar);
            if (!dVar.N()) {
                p.this.R1(dVar);
                return;
            }
            if (dVar.G() == com.byd.cleardata.d.f.APK) {
                q.a(R.string.apk_install);
            } else if (com.byd.cleardata.i.a.c(p.this.p(), dVar) != null) {
                com.byd.cleardata.i.e.J(p.this.p(), dVar);
            } else {
                com.byd.cleardata.ui.e.m.b2(dVar).V1(p.this.x());
            }
        }
    }

    class c implements c.g {
        c() {
        }

        @Override // com.byd.cleardata.ui.d.c.g
        public boolean a(View view, com.byd.cleardata.d.d dVar) {
            return false;
        }
    }

    class d implements TabLayout.d {

        class a implements Predicate<com.byd.cleardata.d.d> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f2884a;

            a(int i) {
                this.f2884a = i;
            }

            @Override // java.util.function.Predicate
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public boolean test(com.byd.cleardata.d.d dVar) {
                return dVar.F() == this.f2884a - 1;
            }
        }

        d() {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(TabLayout.g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(TabLayout.g gVar) {
            int iF = gVar.f();
            p.this.i0 = iF;
            com.byd.cleardata.i.c.b("position = " + iF);
            p.this.f0.clear();
            if (iF == 0) {
                if (!TextUtils.isEmpty(p.this.b0)) {
                    p.this.g0.sort(new o.a(p.this.b0));
                }
                p.this.f0.addAll(p.this.g0);
            } else {
                List list = (List) p.this.g0.stream().filter(new a(iF)).collect(Collectors.toList());
                if (!TextUtils.isEmpty(p.this.b0)) {
                    list.sort(new o.a(p.this.b0));
                }
                p.this.f0.addAll(list);
            }
            p.this.e0.h();
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(TabLayout.g gVar) {
        }
    }

    private void I1() {
        for (int i = 0; i < this.h0.length; i++) {
            TabLayout tabLayout = this.d0;
            tabLayout.d(tabLayout.x().n(N1(i)));
        }
        this.d0.c(new d());
        S1(2, com.byd.cleardata.a.f().m());
        S1(3, com.byd.cleardata.a.f().n());
    }

    static /* synthetic */ boolean J1(int i, com.byd.cleardata.d.d dVar) {
        return dVar.F() == i;
    }

    static /* synthetic */ boolean K1(int i, com.byd.cleardata.d.d dVar) {
        return dVar.F() == i;
    }

    static /* synthetic */ boolean L1(int i, com.byd.cleardata.d.d dVar) {
        return dVar.F() == i;
    }

    static /* synthetic */ boolean M1(int i, com.byd.cleardata.d.d dVar) {
        return dVar.F() == i;
    }

    private View N1(int i) {
        View viewInflate = LayoutInflater.from(p()).inflate(R.layout.tab_text, (ViewGroup) this.d0, false);
        ((TextView) viewInflate.findViewById(R.id.tv_name)).setText(this.h0[i]);
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R1(com.byd.cleardata.d.d dVar) {
        Intent intent = new Intent(p(), (Class<?>) MainActivity.class);
        intent.putExtra("extra_file", dVar.w());
        w1(intent);
    }

    private void S1(int i, boolean z) {
        TabLayout.i iVar = this.d0.w(i).i;
        iVar.setClickable(z);
        TextView textView = (TextView) iVar.findViewById(R.id.tv_name);
        if (!z) {
            textView.setTextColor(L().getColor(R.color.text_tip));
        }
        textView.setEnabled(z);
    }

    @Override // com.byd.cleardata.ui.f.h
    protected void A1(View view) {
        this.c0 = (EmptyRecyclerView) view.findViewById(R.id.recyclerView);
        this.d0 = (TabLayout) view.findViewById(R.id.tabLayout);
        I1();
        this.c0.setLayoutManager(new LinearLayoutManager(p()));
        androidx.recyclerview.widget.d dVar = new androidx.recyclerview.widget.d(p(), 1);
        dVar.l(androidx.core.content.a.d(p(), R.drawable.bg_divider));
        this.c0.h(dVar);
        this.f0 = new ArrayList();
        this.g0 = new ArrayList();
        com.byd.cleardata.ui.d.c cVar = new com.byd.cleardata.ui.d.c(p(), this.f0);
        this.e0 = cVar;
        cVar.L(false);
        this.e0.P(1);
        this.c0.A1(view.findViewById(R.id.ly_empty), null);
        this.c0.setAdapter(this.e0);
        this.c0.getRecycledViewPool().k(1, com.byd.cleardata.i.d.d(j1()) ? 6 : 11);
        com.byd.cleardata.ui.g.i.a(this.c0, 0);
        this.e0.M(new b());
        this.e0.N(new c());
        this.j0 = new HashMap();
        this.l0 = (ImageView) view.findViewById(R.id.iv_icon_empty);
        TextView textView = (TextView) view.findViewById(R.id.tv_desc_empty);
        this.k0 = textView;
        textView.setText(R.string.empty_file_no_found);
    }

    @Override // com.byd.cleardata.ui.f.h
    protected int B1() {
        return R.layout.fragment_search_result;
    }

    public void O1(com.byd.cleardata.d.d dVar) {
        if (dVar != null) {
            this.g0.add(dVar);
            this.f0.add(dVar);
            this.m0.removeMessages(1);
            this.m0.sendEmptyMessageDelayed(1, 500L);
        }
    }

    public void P1(String str) {
        this.b0 = str;
        this.m0.removeMessages(1);
        this.e0.K(this.b0);
        this.f0.sort(new o.a(str));
        this.e0.h();
    }

    public void Q1() {
        List<com.byd.cleardata.d.d> list = this.g0;
        if (list != null) {
            list.clear();
            this.f0.clear();
            this.e0.h();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        this.h0 = L().getStringArray(R.array.tabs_all);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean z = configuration.orientation == 2;
        this.c0.getRecycledViewPool().k(1, z ? 6 : 11);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.l0.getLayoutParams();
        layoutParams.topMargin = L().getDimensionPixelSize(z ? R.dimen.dp_48 : R.dimen.dp_333);
        this.l0.setLayoutParams(layoutParams);
    }

    @org.greenrobot.eventbus.m(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.cleardata.e.b bVar) {
        final int iB = bVar.b();
        com.byd.cleardata.i.c.b("onDiskEvent root = " + iB);
        S1(2, com.byd.cleardata.a.f().m());
        S1(3, com.byd.cleardata.a.f().n());
        if (com.byd.cleardata.a.f().p(iB)) {
            return;
        }
        if (iB == 1) {
            this.g0.removeIf(new Predicate() { // from class: com.byd.cleardata.ui.f.g
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return p.J1(iB, (com.byd.cleardata.d.d) obj);
                }
            });
            int i = this.i0;
            if (i == 2) {
                this.f0.clear();
            } else if (i == 0) {
                this.f0.removeIf(new Predicate() { // from class: com.byd.cleardata.ui.f.f
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return p.K1(iB, (com.byd.cleardata.d.d) obj);
                    }
                });
            }
            this.e0.h();
        }
        if (iB == 2) {
            this.g0.removeIf(new Predicate() { // from class: com.byd.cleardata.ui.f.e
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return p.L1(iB, (com.byd.cleardata.d.d) obj);
                }
            });
            int i2 = this.i0;
            if (i2 == 3) {
                this.f0.clear();
            } else if (i2 == 0) {
                this.f0.removeIf(new Predicate() { // from class: com.byd.cleardata.ui.f.d
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return p.M1(iB, (com.byd.cleardata.d.d) obj);
                    }
                });
            }
            this.e0.h();
        }
    }

    @Override // com.byd.cleardata.ui.f.h, androidx.fragment.app.Fragment
    public void r0() {
        this.m0.removeMessages(1);
        super.r0();
    }

    @Override // com.byd.cleardata.ui.f.h
    protected boolean z1() {
        return true;
    }
}
