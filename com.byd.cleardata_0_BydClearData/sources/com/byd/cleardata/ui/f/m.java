package com.byd.cleardata.ui.f;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import com.byd.cleardata.i.r;
import com.byd.cleardata.ui.SearchActivity;
import com.byd.cleardata.ui.view.CapacityView;
import com.byd.cleardata.ui.view.TextImageView;
import java.io.File;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class m extends com.byd.cleardata.ui.f.h implements View.OnClickListener {
    private CapacityView A0;
    private c.a.j.b B0;
    private com.byd.cleardata.ui.d.e b0;
    private RecyclerView c0;
    private String[] d0;
    private int[] e0;
    private String[] f0;
    private RecyclerView g0;
    private ViewGroup h0;
    private ViewGroup i0;
    private TextView j0;
    private CheckBox k0;
    private ImageView l0;
    private ImageView m0;
    private ImageView n0;
    private ImageView o0;
    private int r0;
    private LinearLayoutManager s0;
    private com.byd.cleardata.ui.d.f t0;
    private View u0;
    private TextImageView v0;
    private TextImageView w0;
    private TextImageView x0;
    private com.byd.cleardata.ui.e.l y0;
    private com.byd.cleardata.ui.e.p z0;
    private boolean p0 = true;
    private Handler q0 = new Handler();
    private final com.byd.cleardata.ui.view.a C0 = new h();

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            m mVar = m.this;
            mVar.z0 = new com.byd.cleardata.ui.e.p(mVar.Z);
            m.this.z0.e(m.this.n0);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            m mVar = m.this;
            mVar.y0 = new com.byd.cleardata.ui.e.l(mVar.Z);
            m.this.y0.e(m.this.x0);
        }
    }

    class c implements com.byd.cleardata.ui.c<Integer> {
        c() {
        }

        @Override // com.byd.cleardata.ui.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Integer num, int i) {
            com.byd.cleardata.i.c.b("onItemClick position = " + i);
            if (com.byd.cleardata.b.h().k() != i) {
                m.this.r0 = i;
                m.this.b0.F(m.this.d0[i]);
                m.this.W1();
                com.byd.cleardata.b.h().D(i);
                m.this.Z1();
                if (m.this.p0) {
                    org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.i(i));
                } else {
                    m.this.p0 = true;
                    org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.i(15));
                }
            }
        }
    }

    class d implements com.byd.cleardata.ui.c<Integer> {
        d() {
        }

        @Override // com.byd.cleardata.ui.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Integer num, int i) {
            for (int i2 = 0; i2 < num.intValue(); i2++) {
                String strE = com.byd.cleardata.b.h().e();
                if (strE != null) {
                    com.byd.cleardata.b.h().w(new File(strE).getParent());
                }
            }
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.h());
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            m mVar = m.this;
            mVar.z0 = new com.byd.cleardata.ui.e.p(mVar.Z);
            m.this.z0.e(m.this.n0);
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            m mVar = m.this;
            mVar.y0 = new com.byd.cleardata.ui.e.l(mVar.Z);
            m.this.y0.e(m.this.x0);
        }
    }

    class g implements c.a.l.d<com.byd.cleardata.d.g> {
        g() {
        }

        @Override // c.a.l.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(com.byd.cleardata.d.g gVar) {
            if (gVar == null) {
                m.this.A0.setVisibility(4);
            } else {
                m.this.A0.setVisibility(0);
                m.this.A0.setScale(gVar);
            }
        }
    }

    class h extends com.byd.cleardata.ui.view.a {
        h() {
        }

        @Override // com.byd.cleardata.ui.view.a
        public void a(View view) {
            m.this.onClick(view);
        }
    }

    private void Q1() {
        com.byd.cleardata.i.c.b("initPath");
        String strE = com.byd.cleardata.b.h().e();
        if (strE != null) {
            int iK = com.byd.cleardata.b.h().k();
            int iJ = com.byd.cleardata.b.h().j();
            String strK = com.byd.cleardata.a.f().k(iK);
            if (strK != null) {
                if (!strE.startsWith(strK)) {
                    strK = com.byd.cleardata.a.f().j(iK);
                }
                String[] strArrSplit = strE.replace(strK, "").split("/");
                this.b0.G(this.f0[iJ], this.d0[iK]);
                for (String str : strArrSplit) {
                    if (!TextUtils.isEmpty(str)) {
                        this.b0.z(str);
                    }
                }
            }
        }
    }

    private void R1() {
        com.byd.cleardata.i.c.b("initPathView");
        com.byd.cleardata.ui.d.e eVar = new com.byd.cleardata.ui.d.e();
        this.b0 = eVar;
        eVar.H(new d());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.Z, 0, false);
        this.s0 = linearLayoutManager;
        this.c0.setLayoutManager(linearLayoutManager);
        this.c0.setAdapter(this.b0);
        this.b0.G(this.f0[com.byd.cleardata.b.h().j()], this.d0[com.byd.cleardata.b.h().k()]);
    }

    private void S1() {
        this.t0 = new com.byd.cleardata.ui.d.f(this.Z);
        this.g0.setLayoutManager(new LinearLayoutManager(this.Z, 0, false));
        this.g0.setAdapter(this.t0);
        this.g0.h(new com.byd.cleardata.i.p(this.Z.getResources().getDimensionPixelSize(R.dimen.dp_16), 0));
        this.t0.E(new c());
        this.t0.D(com.byd.cleardata.b.h().k());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: T1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean U1() {
        S1();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W1() {
        if (this.b0.c() > 0) {
            this.c0.i1(this.b0.c() - 1);
        }
    }

    private void X1(int i, boolean z) {
        this.t0.F(i, z);
    }

    private void Y1(boolean z) {
        com.byd.cleardata.i.c.b("showNormal");
        this.h0.setVisibility(z ? 0 : 4);
        this.i0.setVisibility(z ? 4 : 0);
        if (z) {
            return;
        }
        int iM = com.byd.cleardata.b.h().m();
        this.j0.setText(S(R.string.file_count, Integer.valueOf(iM)));
        boolean z2 = iM > 0;
        this.v0.setEnabled(z2);
        this.w0.setEnabled(z2);
        this.x0.setEnabled(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z1() {
        com.byd.cleardata.i.c.b("updateDiskSize");
        boolean z = com.byd.cleardata.b.h().j() == 2;
        this.A0.setVisibility(z ? 0 : 4);
        if (z) {
            c.a.j.b bVar = this.B0;
            if (bVar != null && !bVar.g()) {
                this.B0.e();
            }
            String strK = com.byd.cleardata.a.f().k(com.byd.cleardata.b.h().k());
            this.A0.setVisibility(strK == null ? 4 : 0);
            if (strK != null) {
                this.B0 = com.byd.cleardata.d.g.d(strK).k(c.a.o.a.a()).f(c.a.i.b.a.a()).h(new g());
            }
        }
    }

    @Override // com.byd.cleardata.ui.f.h
    protected void A1(View view) {
        this.r0 = com.byd.cleardata.b.h().k();
        this.d0 = L().getStringArray(R.array.tabs);
        this.f0 = L().getStringArray(R.array.menus);
        this.e0 = r.a(this.Z, R.array.icon_tab_path);
        this.A0 = (CapacityView) view.findViewById(R.id.scaleView);
        this.c0 = (RecyclerView) view.findViewById(R.id.rv_path);
        this.g0 = (RecyclerView) view.findViewById(R.id.rv_tab);
        this.u0 = view.findViewById(R.id.tv_cancel);
        this.h0 = (ViewGroup) view.findViewById(R.id.ly_normal);
        this.i0 = (ViewGroup) view.findViewById(R.id.ly_operate);
        this.j0 = (TextView) view.findViewById(R.id.tv_count);
        this.k0 = (CheckBox) view.findViewById(R.id.cb_select_all);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_list);
        this.l0 = imageView;
        imageView.setImageResource(com.byd.cleardata.b.h().i() == 0 ? R.drawable.icon_public_list_48 : R.drawable.icon_file_all_48);
        this.m0 = (ImageView) view.findViewById(R.id.iv_folder);
        this.o0 = (ImageView) view.findViewById(R.id.iv_search);
        com.byd.cleardata.b.h().j();
        this.n0 = (ImageView) view.findViewById(R.id.iv_sort);
        com.byd.cleardata.b.h().j();
        this.v0 = (TextImageView) view.findViewById(R.id.tv_copy);
        this.w0 = (TextImageView) view.findViewById(R.id.tv_delete);
        this.x0 = (TextImageView) view.findViewById(R.id.tv_more);
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        view.findViewById(R.id.tv_more).setOnClickListener(this.C0);
        view.findViewById(R.id.tv_delete).setOnClickListener(this.C0);
        view.findViewById(R.id.tv_copy).setOnClickListener(this.C0);
        view.findViewById(R.id.cb_select_all).setOnClickListener(this);
        view.findViewById(R.id.iv_folder).setOnClickListener(this.C0);
        view.findViewById(R.id.iv_list).setOnClickListener(this);
        view.findViewById(R.id.iv_search).setOnClickListener(this);
        view.findViewById(R.id.iv_sort).setOnClickListener(this);
        view.findViewById(R.id.tv_select).setOnClickListener(this);
        R1();
        Looper.getMainLooper().getQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.byd.cleardata.ui.f.c
            @Override // android.os.MessageQueue.IdleHandler
            public final boolean queueIdle() {
                return this.f2844a.U1();
            }
        });
        Y1(com.byd.cleardata.b.h().t());
        this.l0.setVisibility(4);
        this.o0.setVisibility(4);
    }

    @Override // com.byd.cleardata.ui.f.h
    protected int B1() {
        return R.layout.fragment_header;
    }

    @Override // androidx.fragment.app.Fragment
    public void F0() {
        super.F0();
        com.byd.cleardata.i.c.b("onResume");
        com.byd.cleardata.b.h().j();
        com.byd.cleardata.b.h().j();
        Z1();
    }

    @Override // androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        super.G0(bundle);
        com.byd.cleardata.ui.e.l lVar = this.y0;
        bundle.putBoolean("show_more", lVar != null && lVar.isShowing());
        com.byd.cleardata.ui.e.p pVar = this.z0;
        bundle.putBoolean("show_sort", pVar != null && pVar.isShowing());
    }

    public boolean V1() {
        boolean zE = this.b0.E();
        W1();
        if (zE) {
            String strE = com.byd.cleardata.b.h().e();
            if (com.byd.cleardata.b.h().j() != 6) {
                com.byd.cleardata.b.h().w(new File(strE).getParent());
            }
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.h());
        }
        return zE;
    }

    @Override // androidx.fragment.app.Fragment
    public void e0(Bundle bundle) {
        super.e0(bundle);
        if (bundle != null) {
            Q1();
            boolean z = bundle.getBoolean("show_sort", false);
            boolean z2 = bundle.getBoolean("show_more", false);
            if (z) {
                this.q0.postDelayed(new a(), 1000L);
            }
            if (z2) {
                this.q0.postDelayed(new b(), 1000L);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        org.greenrobot.eventbus.c cVarC;
        com.byd.cleardata.e.f fVar;
        com.byd.cleardata.ui.e.a aVarX1;
        androidx.fragment.app.d dVarI1;
        com.byd.cleardata.ui.e.b bVar;
        int id = view.getId();
        if (id == R.id.iv_list) {
            com.byd.cleardata.b.h().F();
            this.l0.setImageResource(com.byd.cleardata.b.h().i() == 0 ? R.drawable.icon_public_list_48 : R.drawable.icon_file_all_48);
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.f(9));
            return;
        }
        if (id == R.id.iv_search) {
            androidx.appcompat.app.c cVar = this.Z;
            cVar.startActivity(new Intent(cVar, (Class<?>) SearchActivity.class));
            return;
        }
        if (id != R.id.iv_sort) {
            if (id == R.id.iv_folder) {
                aVarX1 = com.byd.cleardata.ui.e.g.r2(null);
                dVarI1 = this.Z;
            } else {
                if (id == R.id.tv_cancel) {
                    com.byd.cleardata.b.h().C();
                    return;
                }
                if (id != R.id.tv_more) {
                    if (id == R.id.tv_delete) {
                        if (com.byd.cleardata.b.h().l().isEmpty()) {
                            return;
                        } else {
                            aVarX1 = com.byd.cleardata.ui.e.k.W1(1);
                        }
                    } else {
                        if (id != R.id.tv_copy) {
                            if (id == R.id.cb_select_all) {
                                boolean zIsChecked = this.k0.isChecked();
                                cVarC = org.greenrobot.eventbus.c.c();
                                fVar = new com.byd.cleardata.e.f(12, Boolean.valueOf(zIsChecked));
                            } else {
                                if (id != R.id.tv_select) {
                                    return;
                                }
                                boolean z = !this.k0.isChecked();
                                this.k0.setChecked(z);
                                cVarC = org.greenrobot.eventbus.c.c();
                                fVar = new com.byd.cleardata.e.f(12, Boolean.valueOf(z));
                            }
                            cVarC.k(fVar);
                            return;
                        }
                        if (com.byd.cleardata.b.h().l().isEmpty()) {
                            return;
                        } else {
                            aVarX1 = com.byd.cleardata.ui.e.j.X1(4);
                        }
                    }
                    dVarI1 = i1();
                } else {
                    if (com.byd.cleardata.b.h().l().isEmpty()) {
                        return;
                    }
                    com.byd.cleardata.ui.e.l lVar = new com.byd.cleardata.ui.e.l(this.Z);
                    this.y0 = lVar;
                    bVar = lVar;
                }
            }
            aVarX1.V1(dVarI1.y());
            return;
        }
        com.byd.cleardata.ui.e.p pVar = new com.byd.cleardata.ui.e.p(this.Z);
        this.z0 = pVar;
        bVar = pVar;
        bVar.e(view);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean z = configuration.orientation == 2;
        com.byd.cleardata.i.c.b("isLandscape = " + z);
        ConstraintLayout.b bVar = (ConstraintLayout.b) this.c0.getLayoutParams();
        bVar.setMarginStart(L().getDimensionPixelSize(z ? R.dimen.path_start_land : R.dimen.path_start));
        this.c0.setLayoutParams(bVar);
        ConstraintLayout.b bVar2 = (ConstraintLayout.b) this.g0.getLayoutParams();
        bVar2.setMarginStart(L().getDimensionPixelSize(z ? R.dimen.tab_start_land : R.dimen.tab_start));
        this.g0.setLayoutParams(bVar2);
        ConstraintLayout.b bVar3 = (ConstraintLayout.b) this.u0.getLayoutParams();
        bVar3.setMarginStart(L().getDimensionPixelSize(z ? R.dimen.cancel_start_land : R.dimen.cancel_start));
        this.u0.setLayoutParams(bVar3);
        ConstraintLayout.b bVar4 = (ConstraintLayout.b) this.o0.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) bVar4).topMargin = L().getDimensionPixelSize(z ? R.dimen.search_top_land : R.dimen.search_top);
        this.o0.setLayoutParams(bVar4);
        com.byd.cleardata.ui.e.p pVar = this.z0;
        if (pVar != null && pVar.isShowing()) {
            this.z0.dismiss();
            this.q0.postDelayed(new e(), 1000L);
        }
        com.byd.cleardata.ui.e.l lVar = this.y0;
        if (lVar == null || !lVar.isShowing()) {
            return;
        }
        this.y0.dismiss();
        this.q0.postDelayed(new f(), 1000L);
    }

    @org.greenrobot.eventbus.m(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.cleardata.e.b bVar) {
        com.byd.cleardata.i.c.b("onDiskEvent volume");
        Z1();
        this.t0.G();
        int iB = bVar.b();
        if (iB != com.byd.cleardata.b.h().k() || com.byd.cleardata.a.f().p(iB)) {
            return;
        }
        this.b0.D();
    }

    @org.greenrobot.eventbus.m(threadMode = ThreadMode.MAIN)
    public void onFileChangeEvent(com.byd.cleardata.e.c cVar) {
        com.byd.cleardata.i.c.b("onFileChangeEvent");
        Z1();
    }

    @org.greenrobot.eventbus.m
    public void onFileStateEvent(com.byd.cleardata.e.d dVar) {
        com.byd.cleardata.i.c.b("onFileStateEvent");
        com.byd.cleardata.d.e eVarG = com.byd.cleardata.b.h().g();
        if (eVarG == com.byd.cleardata.d.e.CHOOSING) {
            Y1(false);
        } else {
            Y1(true);
            com.byd.cleardata.ui.e.l lVar = this.y0;
            if (lVar != null && lVar.isShowing()) {
                this.y0.dismiss();
            }
        }
        if (eVarG == com.byd.cleardata.d.e.PASTING) {
            int iK = com.byd.cleardata.b.h().k();
            com.byd.cleardata.b.h().w(com.byd.cleardata.a.f().k(iK));
            this.b0.G(this.f0[com.byd.cleardata.b.h().j()], this.d0[com.byd.cleardata.b.h().k()]);
            this.t0.D(iK);
        }
    }

    @org.greenrobot.eventbus.m
    public void onMenuEvent(com.byd.cleardata.e.e eVar) {
        int iB = eVar.b();
        if (com.byd.cleardata.b.h().s() && com.byd.cleardata.b.h().k() != 0) {
            this.t0.D(0);
            com.byd.cleardata.b.h().D(0);
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.i(15));
        }
        boolean z = iB != 6;
        X1(1, z);
        X1(2, z);
        this.t0.h();
        this.A0.setVisibility(iB == 2 ? 0 : 4);
        if (iB == 2) {
            Z1();
        }
        this.b0.G(this.f0[com.byd.cleardata.b.h().j()], this.d0[com.byd.cleardata.b.h().k()]);
        W1();
        com.byd.cleardata.i.c.b("onMenuEvent");
        if (com.byd.cleardata.b.h().j() == 2 && this.k0.isChecked()) {
            this.k0.setChecked(false);
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.f(12, Boolean.FALSE));
        }
    }

    @org.greenrobot.eventbus.m
    public void onOperateEvent(com.byd.cleardata.e.f fVar) {
        com.byd.cleardata.i.c.b("onOperateEvent");
        int iB = fVar.b();
        if (iB == 10) {
            this.b0.A(((com.byd.cleardata.d.d) fVar.a()).C());
            W1();
        } else if (iB == 11) {
            int iM = com.byd.cleardata.b.h().m();
            this.j0.setText(S(R.string.file_count, Integer.valueOf(iM)));
            this.k0.setChecked(((Boolean) fVar.a()).booleanValue());
            boolean z = iM > 0;
            this.v0.setEnabled(z);
            this.w0.setEnabled(z);
            this.x0.setEnabled(z);
        }
    }

    @org.greenrobot.eventbus.m
    public void onPathEvent(com.byd.cleardata.e.h hVar) {
        if (hVar.b() == 14) {
            this.t0.D(com.byd.cleardata.b.h().k());
            Q1();
        }
    }

    @org.greenrobot.eventbus.m
    public void onVisitEvent(com.byd.cleardata.e.j jVar) {
        com.byd.cleardata.i.c.b("onVisitEvent");
        this.t0.D(com.byd.cleardata.b.h().k());
    }

    @Override // com.byd.cleardata.ui.f.h
    protected boolean z1() {
        return true;
    }
}
