package com.byd.filemanager.ui.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import com.byd.filemanager.ThreadManager;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileState;
import com.byd.filemanager.k.m;
import com.byd.filemanager.ui.SearchActivity;
import com.byd.filemanager.ui.c.j;
import com.byd.filemanager.ui.c.k;
import com.byd.filemanager.ui.c.o;
import com.byd.filemanager.ui.view.CapacityView;
import com.byd.filemanager.ui.view.TextImageView;
import com.byd.music.f.h;
import com.byd.music.f.i;
import java.io.File;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

/* JADX INFO: loaded from: classes.dex */
public class HeaderFragment extends BaseFragment implements View.OnClickListener, com.byd.music.g.a.a {
    private o A0;
    private CapacityView B0;
    private io.reactivex.disposables.b C0;
    private com.byd.filemanager.ui.b.d c0;
    private RecyclerView d0;
    private String[] e0;
    private int[] f0;
    private String[] g0;
    private RecyclerView h0;
    private ViewGroup i0;
    private ViewGroup j0;
    private TextView k0;
    private CheckBox l0;
    private ImageView m0;
    private ImageView n0;
    private ImageView o0;
    private ImageView p0;
    private int s0;
    private LinearLayoutManager t0;
    private com.byd.filemanager.ui.b.e u0;
    private View v0;
    private TextImageView w0;
    private TextImageView x0;
    private TextImageView y0;
    private k z0;
    private boolean q0 = true;
    private Handler r0 = new Handler();
    private final com.byd.filemanager.ui.view.a D0 = new com.byd.filemanager.ui.view.a() { // from class: com.byd.filemanager.ui.fragment.HeaderFragment.9
        @Override // com.byd.filemanager.ui.view.a
        public void a(View view) {
            HeaderFragment.this.onClick(view);
        }
    };

    private void Q1() {
        com.byd.filemanager.k.b.c(this.Z, "initPath");
        String strE = com.byd.filemanager.f.h().e();
        if (strE != null) {
            int iK = com.byd.filemanager.f.h().k();
            int iJ = com.byd.filemanager.f.h().j();
            String strL = com.byd.filemanager.e.f().l(iK);
            if (strL != null) {
                if (!strE.startsWith(strL)) {
                    strL = com.byd.filemanager.e.f().k(iK);
                }
                String[] strArrSplit = strE.replace(strL, "").split("/");
                this.c0.H(this.g0[iJ], this.e0[iK]);
                List<String> listA = this.c0.A();
                for (String str : strArrSplit) {
                    if (!TextUtils.isEmpty(str)) {
                        listA.add(str);
                    }
                }
                this.c0.h();
            }
        }
    }

    private void R1() {
        com.byd.filemanager.ui.b.d dVar = new com.byd.filemanager.ui.b.d();
        this.c0 = dVar;
        dVar.I(new com.byd.filemanager.ui.a<Integer>() { // from class: com.byd.filemanager.ui.fragment.HeaderFragment.4
            @Override // com.byd.filemanager.ui.a
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(Integer num, int i) {
                for (int i2 = 0; i2 < num.intValue(); i2++) {
                    String strE = com.byd.filemanager.f.h().e();
                    if (strE != null) {
                        com.byd.filemanager.f.h().D(new File(strE).getParent());
                    }
                }
                org.greenrobot.eventbus.c.c().k(new h());
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.a0, 0, false);
        this.t0 = linearLayoutManager;
        this.d0.setLayoutManager(linearLayoutManager);
        this.d0.setAdapter(this.c0);
    }

    private void S1() {
        this.u0 = new com.byd.filemanager.ui.b.e(this.a0);
        this.h0.setLayoutManager(new LinearLayoutManager(this.a0, 0, false));
        this.h0.setAdapter(this.u0);
        this.h0.h(new m(this.a0.getResources().getDimensionPixelSize(R.dimen.dp_16), 0));
        this.u0.D(new com.byd.filemanager.ui.a<Integer>() { // from class: com.byd.filemanager.ui.fragment.HeaderFragment.3
            @Override // com.byd.filemanager.ui.a
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(Integer num, int i) {
                if (com.byd.filemanager.f.h().k() != i) {
                    HeaderFragment.this.s0 = i;
                    HeaderFragment.this.c0.F(HeaderFragment.this.e0[i]);
                    HeaderFragment.this.W1();
                    com.byd.filemanager.f.h().L(i);
                    HeaderFragment.this.Z1();
                    if (HeaderFragment.this.q0) {
                        org.greenrobot.eventbus.c.c().k(new i(i));
                    } else {
                        HeaderFragment.this.q0 = true;
                        org.greenrobot.eventbus.c.c().k(new i(15));
                    }
                }
            }
        });
        this.u0.C(com.byd.filemanager.f.h().k());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: T1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean U1() {
        S1();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W1() {
        com.byd.filemanager.ui.b.d dVar = this.c0;
        if (dVar == null || dVar.c() <= 0) {
            return;
        }
        this.d0.j1(this.c0.c() - 1);
    }

    private void X1(int i, boolean z) {
        this.u0.E(i, z);
    }

    private void Y1(boolean z) {
        com.byd.filemanager.k.b.c(this.Z, "showNormal:" + z);
        this.i0.setVisibility(z ? 0 : 4);
        this.j0.setVisibility(z ? 4 : 0);
        if (z) {
            return;
        }
        int iM = com.byd.filemanager.f.h().m();
        this.k0.setText(R(R.string.file_counts, Integer.valueOf(iM)));
        boolean z2 = iM > 0;
        this.w0.setEnabled(z2);
        this.x0.setEnabled(z2);
        this.y0.setEnabled(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z1() {
        boolean z = com.byd.filemanager.f.h().j() == 1;
        int iK = com.byd.filemanager.f.h().k();
        com.byd.filemanager.k.b.c(this.Z, "updateDiskSize  isAllFile=" + z + ",pos=" + iK);
        if (!z) {
            this.B0.setVisibility(4);
            return;
        }
        io.reactivex.disposables.b bVar = this.C0;
        if (bVar != null && !bVar.isDisposed()) {
            this.C0.dispose();
        }
        String strL = com.byd.filemanager.e.f().l(com.byd.filemanager.f.h().k());
        com.byd.filemanager.k.b.b("updateDiskSize -- path:" + strL);
        if (TextUtils.isEmpty(strL)) {
            this.B0.setVisibility(4);
        }
        if (strL != null) {
            this.C0 = com.byd.filemanager.bean.c.e(strL).k(ThreadManager.h().i()).f(d.a.l.b.a.a()).h(new d.a.m.e<com.byd.filemanager.bean.c>() { // from class: com.byd.filemanager.ui.fragment.HeaderFragment.8
                @Override // d.a.m.e
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void accept(com.byd.filemanager.bean.c cVar) throws Exception {
                    if (cVar == null) {
                        HeaderFragment.this.B0.setVisibility(4);
                    } else {
                        HeaderFragment.this.B0.setVisibility(0);
                        HeaderFragment.this.B0.setScale(cVar);
                    }
                }
            });
        }
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected void A1(View view) {
        this.s0 = com.byd.filemanager.f.h().k();
        this.e0 = K().getStringArray(R.array.tabs);
        this.g0 = K().getStringArray(R.array.menus);
        this.f0 = com.byd.filemanager.k.o.a(this.a0, R.array.icon_tab_path);
        this.B0 = (CapacityView) view.findViewById(R.id.scaleView);
        this.d0 = (RecyclerView) view.findViewById(R.id.rv_path);
        this.h0 = (RecyclerView) view.findViewById(R.id.rv_tab);
        this.v0 = view.findViewById(R.id.tv_cancel);
        this.i0 = (ViewGroup) view.findViewById(R.id.ly_normal);
        this.j0 = (ViewGroup) view.findViewById(R.id.ly_operate);
        this.k0 = (TextView) view.findViewById(R.id.tv_count);
        this.l0 = (CheckBox) view.findViewById(R.id.cb_select_all);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_list);
        this.m0 = imageView;
        imageView.setImageResource(com.byd.filemanager.f.h().i() == 0 ? R.drawable.icon_public_list_48 : R.drawable.icon_file_all_48);
        this.n0 = (ImageView) view.findViewById(R.id.iv_folder);
        this.p0 = (ImageView) view.findViewById(R.id.iv_search);
        this.n0.setVisibility(com.byd.filemanager.f.h().j() == 1 ? 0 : 4);
        this.o0 = (ImageView) view.findViewById(R.id.iv_sort);
        this.o0.setVisibility(com.byd.filemanager.f.h().j() == 0 ? 4 : 0);
        this.w0 = (TextImageView) view.findViewById(R.id.tv_copy);
        this.x0 = (TextImageView) view.findViewById(R.id.tv_delete);
        this.y0 = (TextImageView) view.findViewById(R.id.tv_more);
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        view.findViewById(R.id.tv_more).setOnClickListener(this.D0);
        view.findViewById(R.id.tv_delete).setOnClickListener(this.D0);
        view.findViewById(R.id.tv_copy).setOnClickListener(this.D0);
        view.findViewById(R.id.cb_select_all).setOnClickListener(this);
        view.findViewById(R.id.iv_folder).setOnClickListener(this.D0);
        view.findViewById(R.id.iv_list).setOnClickListener(this);
        view.findViewById(R.id.iv_search).setOnClickListener(this);
        view.findViewById(R.id.iv_sort).setOnClickListener(this);
        view.findViewById(R.id.tv_select).setOnClickListener(this);
        R1();
        Looper.getMainLooper().getQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.byd.filemanager.ui.fragment.c
            @Override // android.os.MessageQueue.IdleHandler
            public final boolean queueIdle() {
                return this.f3858a.U1();
            }
        });
        Y1(com.byd.filemanager.f.h().w());
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected int B1() {
        return com.byd.filemanager.f.h().v() ? com.byd.filemanager.f.h().x() ? R.layout.fragment_header_rh : R.layout.fragment_header : com.byd.filemanager.f.h().x() ? R.layout.fragment_header_denza_rh : R.layout.fragment_header_denza;
    }

    @Override // androidx.fragment.app.Fragment
    public void F0() {
        super.F0();
        com.byd.filemanager.k.b.c(this.Z, "onResume");
        this.n0.setVisibility(com.byd.filemanager.f.h().j() == 1 ? 0 : 4);
        this.o0.setVisibility(com.byd.filemanager.f.h().j() == 0 ? 4 : 0);
        Z1();
    }

    @Override // androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        super.G0(bundle);
        k kVar = this.z0;
        bundle.putBoolean("show_more", kVar != null && kVar.isShowing());
        o oVar = this.A0;
        bundle.putBoolean("show_sort", oVar != null && oVar.isShowing());
    }

    public boolean V1() {
        com.byd.filemanager.ui.b.d dVar = this.c0;
        boolean z = dVar != null && dVar.E();
        W1();
        if (z) {
            String strE = com.byd.filemanager.f.h().e();
            if (TextUtils.isEmpty(strE)) {
                return false;
            }
            if (com.byd.filemanager.f.h().j() != 5) {
                com.byd.filemanager.f.h().D(new File(strE).getParent());
            }
            org.greenrobot.eventbus.c.c().k(new h());
        }
        return z;
    }

    @Override // androidx.fragment.app.Fragment
    public void e0(Bundle bundle) {
        super.e0(bundle);
        Q1();
        if (bundle != null) {
            boolean z = bundle.getBoolean("show_sort", false);
            boolean z2 = bundle.getBoolean("show_more", false);
            if (z) {
                this.r0.postDelayed(new Runnable() { // from class: com.byd.filemanager.ui.fragment.HeaderFragment.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HeaderFragment headerFragment = HeaderFragment.this;
                        headerFragment.A0 = new o(headerFragment.a0);
                        HeaderFragment.this.A0.e(HeaderFragment.this.o0);
                    }
                }, 1000L);
            }
            if (z2) {
                this.r0.postDelayed(new Runnable() { // from class: com.byd.filemanager.ui.fragment.HeaderFragment.2
                    @Override // java.lang.Runnable
                    public void run() {
                        HeaderFragment headerFragment = HeaderFragment.this;
                        headerFragment.z0 = new k(headerFragment.a0);
                        HeaderFragment.this.z0.e(HeaderFragment.this.y0);
                    }
                }, 1000L);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_list) {
            com.byd.filemanager.f.h().N();
            this.m0.setImageResource(com.byd.filemanager.f.h().i() == 0 ? R.drawable.icon_public_list_48 : R.drawable.icon_file_all_48);
            org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(9));
            return;
        }
        if (id == R.id.iv_search) {
            AppCompatActivity appCompatActivity = this.a0;
            appCompatActivity.startActivity(new Intent(appCompatActivity, (Class<?>) SearchActivity.class));
            return;
        }
        if (id == R.id.iv_sort) {
            if (com.byd.filemanager.e.f().s(com.byd.filemanager.f.h().k())) {
                o oVar = new o(this.a0);
                this.A0 = oVar;
                oVar.e(view);
                return;
            }
            return;
        }
        if (id == R.id.iv_folder) {
            if (com.byd.filemanager.e.f().s(com.byd.filemanager.f.h().k())) {
                com.byd.filemanager.ui.c.f.A2(null).c2(this.a0.x());
                return;
            }
            return;
        }
        if (id == R.id.tv_cancel) {
            com.byd.filemanager.f.h().K();
            return;
        }
        if (id == R.id.tv_more) {
            if (com.byd.filemanager.f.h().l().isEmpty()) {
                return;
            }
            k kVar = new k(this.a0);
            this.z0 = kVar;
            kVar.e(view);
            return;
        }
        if (id == R.id.tv_delete) {
            if (com.byd.filemanager.f.h().l().isEmpty()) {
                return;
            }
            j.d2(1).c2(i1().x());
        } else if (id == R.id.tv_copy) {
            if (com.byd.filemanager.f.h().l().isEmpty()) {
                return;
            }
            com.byd.filemanager.ui.c.i.e2(4).c2(i1().x());
        } else if (id == R.id.cb_select_all) {
            org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(12, Boolean.valueOf(this.l0.isChecked())));
        } else if (id == R.id.tv_select) {
            boolean z = !this.l0.isChecked();
            this.l0.setChecked(z);
            org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(12, Boolean.valueOf(z)));
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean z = configuration.orientation == 2;
        com.byd.filemanager.k.b.c(this.Z, "isLandscape = " + z);
        ConstraintLayout.b bVar = (ConstraintLayout.b) this.d0.getLayoutParams();
        bVar.setMarginStart(K().getDimensionPixelSize(z ? R.dimen.path_start_land : R.dimen.path_start));
        this.d0.setLayoutParams(bVar);
        ConstraintLayout.b bVar2 = (ConstraintLayout.b) this.h0.getLayoutParams();
        bVar2.setMarginStart(K().getDimensionPixelSize(z ? R.dimen.tab_start_land : R.dimen.tab_start));
        this.h0.setLayoutParams(bVar2);
        ConstraintLayout.b bVar3 = (ConstraintLayout.b) this.v0.getLayoutParams();
        bVar3.setMarginStart(K().getDimensionPixelSize(z ? R.dimen.cancel_start_land : R.dimen.cancel_start));
        this.v0.setLayoutParams(bVar3);
        ConstraintLayout.b bVar4 = (ConstraintLayout.b) this.p0.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) bVar4).topMargin = K().getDimensionPixelSize(z ? R.dimen.search_top_land : R.dimen.search_top);
        this.p0.setLayoutParams(bVar4);
        o oVar = this.A0;
        if (oVar != null && oVar.isShowing()) {
            this.A0.dismiss();
            this.r0.postDelayed(new Runnable() { // from class: com.byd.filemanager.ui.fragment.HeaderFragment.6
                @Override // java.lang.Runnable
                public void run() {
                    HeaderFragment headerFragment = HeaderFragment.this;
                    headerFragment.A0 = new o(headerFragment.a0);
                    HeaderFragment.this.A0.e(HeaderFragment.this.o0);
                }
            }, 1000L);
        }
        k kVar = this.z0;
        if (kVar == null || !kVar.isShowing()) {
            return;
        }
        this.z0.dismiss();
        this.r0.postDelayed(new Runnable() { // from class: com.byd.filemanager.ui.fragment.HeaderFragment.7
            @Override // java.lang.Runnable
            public void run() {
                HeaderFragment headerFragment = HeaderFragment.this;
                headerFragment.z0 = new k(headerFragment.a0);
                HeaderFragment.this.z0.e(HeaderFragment.this.y0);
            }
        }, 1000L);
    }

    @l(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.music.f.b bVar) {
        int iB;
        com.byd.filemanager.k.b.c(this.Z, "onDiskEvent volume");
        Z1();
        com.byd.filemanager.ui.b.e eVar = this.u0;
        if (eVar != null) {
            eVar.F();
        }
        if (this.c0 == null || bVar == null || (iB = bVar.b()) != com.byd.filemanager.f.h().k() || com.byd.filemanager.e.f().s(iB)) {
            return;
        }
        this.c0.D();
    }

    @l(threadMode = ThreadMode.MAIN)
    public void onFileChangeEvent(com.byd.music.f.c cVar) {
        if (cVar == null || cVar.c() != 3) {
            Z1();
        } else {
            this.r0.postDelayed(new Runnable() { // from class: com.byd.filemanager.ui.fragment.HeaderFragment.5
                @Override // java.lang.Runnable
                public void run() {
                    HeaderFragment.this.Z1();
                }
            }, 200L);
        }
    }

    @l
    public void onFileStateEvent(com.byd.music.f.d dVar) {
        FileState fileStateG = com.byd.filemanager.f.h().g();
        com.byd.filemanager.k.b.c(this.Z, "onFileStateEvent =" + dVar);
        if (fileStateG == FileState.CHOOSING) {
            Y1(false);
        } else {
            Y1(true);
            k kVar = this.z0;
            if (kVar != null && kVar.isShowing()) {
                this.z0.dismiss();
            }
        }
        if (fileStateG == FileState.PASTING) {
            int iK = com.byd.filemanager.f.h().k();
            Z1();
            this.n0.setVisibility(com.byd.filemanager.f.h().j() == 1 ? 0 : 4);
            this.o0.setVisibility(com.byd.filemanager.f.h().j() == 0 ? 4 : 0);
            com.byd.filemanager.f.h().D(com.byd.filemanager.e.f().k(iK));
            this.c0.G(this.g0[com.byd.filemanager.f.h().j()], this.e0[com.byd.filemanager.f.h().k()]);
            this.u0.C(iK);
        }
    }

    @l
    public void onMenuEvent(com.byd.music.f.e eVar) {
        int iB = eVar.b();
        if (com.byd.filemanager.f.h().u() && com.byd.filemanager.f.h().k() != 0) {
            this.u0.C(0);
            com.byd.filemanager.f.h().L(0);
            org.greenrobot.eventbus.c.c().k(new i(15));
        }
        boolean z = iB != 5;
        X1(1, z);
        X1(2, z);
        this.u0.h();
        this.n0.setVisibility(iB == 1 ? 0 : 4);
        this.o0.setVisibility(iB != 0 ? 0 : 4);
        this.B0.setVisibility(iB == 1 ? 0 : 4);
        if (iB == 1) {
            Z1();
        }
        this.c0.G(this.g0[com.byd.filemanager.f.h().j()], this.e0[com.byd.filemanager.f.h().k()]);
        W1();
    }

    @l
    public void onOperateEvent(com.byd.music.f.f fVar) {
        int iB = fVar.b();
        com.byd.filemanager.k.b.c(this.Z, "onOperateEvent  operate:" + iB);
        if (iB == 10) {
            this.c0.z(((FileItem) fVar.a()).C());
            W1();
        } else if (iB == 11) {
            int iM = com.byd.filemanager.f.h().m();
            this.k0.setText(R(R.string.file_counts, Integer.valueOf(iM)));
            this.l0.setChecked(((Boolean) fVar.a()).booleanValue());
            boolean z = iM > 0;
            this.w0.setEnabled(z);
            this.x0.setEnabled(z);
            this.y0.setEnabled(z);
        }
    }

    @l
    public void onPathEvent(h hVar) {
        if (hVar.b() == 14) {
            this.u0.C(com.byd.filemanager.f.h().k());
            Q1();
        }
    }

    @l
    public void onVisitEvent(com.byd.music.f.j jVar) {
        com.byd.filemanager.k.b.c(this.Z, "onVisitEvent");
        this.u0.C(com.byd.filemanager.f.h().k());
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected boolean z1() {
        return true;
    }
}
