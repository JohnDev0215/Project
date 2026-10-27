package com.byd.carsettings.ui.fragment.newenergy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.h.a;
import b.b.g.r.v;
import b.b.g.r.z;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButton;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.newenergy.ServiceProviderListAdapter;
import com.byd.carsettings.ui.activity.MovePowerStationActivity;
import com.byd.carsettings.ui.widget.viewanim.ElectricityAnimView;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ElectricityManageFragment extends XFragment<b.b.g.n.n.b> implements SegmentedButtonGroup.f, SegmentedButtonGroup.e, View.OnClickListener, BydSwitch.a, b.b.o.b.a.a {
    public static boolean m = false;
    public boolean B;
    public b.b.g.h.a P;
    public p R;
    public CountDownTimer S;
    public SegmentedButtonGroup o;
    public SegmentedButtonGroup p;
    public SegmentedButtonGroup q;
    public SegmentedButtonGroup r;
    public SegmentedButtonGroup s;
    public BydSwitch t;
    public BydSwitch u;
    public BydSwitch v;
    public ElectricityAnimView w;
    public ImageView x;
    public ScrollView z;
    public final String n = getClass().getSimpleName();
    public int[] y = {R.id.ll_smart_charging, R.id.ll_soc_limit, R.id.ll_charging_port, R.id.ll_charging_port, R.id.ll_charging_port, R.id.ll_charging_port, R.id.ll_external_discharge, R.id.ll_move_station, R.id.ll_ac_limit};
    public Handler A = null;
    public List<b.b.g.m.e.a> D = new ArrayList();
    public boolean E = false;
    public final String F = "ElectricityManageFragment_label_port";
    public final String G = "ElectricityManageFragment_label_nosense";
    public final String H = "ElectricityManageFragment_label_service";
    public final String I = "ElectricityManageFragment_label_temp";
    public final String J = "ElectricityManageFragment_label_preheat_open";
    public final String K = "ElectricityManageFragment_label_preheat_hint";
    public final String L = "ElectricityManageFragment_label_preheat_unnecessary";
    public final String M = "ElectricityManageFragment_label_aclimit_hint";
    public final String N = "ElectricityManageFragment_label_notlilimit_hint";
    public final String O = "ElectricityManageFragment_label_notlilimit_invalid";
    public boolean Q = false;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView f3214a;

        public a(RecyclerView recyclerView) {
            this.f3214a = recyclerView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((ServiceProviderListAdapter) this.f3214a.getAdapter()).b();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((b.b.g.n.n.b) ElectricityManageFragment.this.mo7getP()).I(1);
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ElectricityManageFragment electricityManageFragment = ElectricityManageFragment.this;
            electricityManageFragment.i0(((b.b.g.n.n.b) electricityManageFragment.mo7getP()).j());
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.g.r.e.c(140000)) {
                return;
            }
            ElectricityManageFragment electricityManageFragment = ElectricityManageFragment.this;
            electricityManageFragment.i0(((b.b.g.n.n.b) electricityManageFragment.mo7getP()).j());
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3219a;

        public e(int i) {
            this.f3219a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ElectricityManageFragment.this.f3717c == null || ElectricityManageFragment.this.z == null || this.f3219a >= ElectricityManageFragment.this.y.length) {
                return;
            }
            if (this.f3219a == 0) {
                ElectricityManageFragment.this.z.scrollTo(0, 0);
                return;
            }
            b.b.m.c.a.d(ElectricityManageFragment.this.n, "[scrollToPosition]----getY:" + ElectricityManageFragment.this.f3717c.findViewById(ElectricityManageFragment.this.y[this.f3219a]).getY());
            ElectricityManageFragment.this.z.scrollTo(0, Math.round(ElectricityManageFragment.this.f3717c.findViewById(ElectricityManageFragment.this.y[this.f3219a]).getY()));
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    public class i implements View.OnClickListener {
        public i() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((b.b.g.n.n.b) ElectricityManageFragment.this.mo7getP()).F(1);
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ElectricityManageFragment electricityManageFragment = ElectricityManageFragment.this;
            electricityManageFragment.f0(((b.b.g.n.n.b) electricityManageFragment.mo7getP()).g(), false);
        }
    }

    public class k implements View.OnClickListener {
        public k() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.g.r.e.c(140017)) {
                return;
            }
            ElectricityManageFragment electricityManageFragment = ElectricityManageFragment.this;
            electricityManageFragment.f0(((b.b.g.n.n.b) electricityManageFragment.mo7getP()).g(), false);
        }
    }

    public class l implements View.OnClickListener {
        public l() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ElectricityManageFragment.this.v.setChecked(false, false);
            if (ElectricityManageFragment.this.S != null) {
                ElectricityManageFragment.this.S.cancel();
            }
        }
    }

    public class m implements View.OnClickListener {
        public m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ElectricityManageFragment.this.v.setChecked(false, false);
            if (ElectricityManageFragment.this.S != null) {
                ElectricityManageFragment.this.S.cancel();
            }
        }
    }

    public class n extends CountDownTimer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.g.q.d.l f3229a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public n(long j, long j2, b.b.g.q.d.l lVar) {
            super(j, j2);
            this.f3229a = lVar;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            try {
                ElectricityManageFragment.this.v.setChecked(false, false);
                this.f3229a.f();
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    }

    public static class o extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<ElectricityManageFragment> f3231a;

        public o(ElectricityManageFragment electricityManageFragment) {
            this.f3231a = new WeakReference<>(electricityManageFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (this.f3231a.get() == null || this.f3231a.get().w == null) {
                return;
            }
            try {
                int i = message.what;
                if (i == 0) {
                    this.f3231a.get().w.setLineTipsViewGone();
                } else if (i == 1) {
                    this.f3231a.get().w.setLineTipsViewVisible();
                } else if (i == 2) {
                    int i2 = 0;
                    while (true) {
                        if (i2 < this.f3231a.get().D.size()) {
                            if (!((b.b.g.m.e.a) this.f3231a.get().D.get(i2)).f933b) {
                                i2++;
                            } else if (this.f3231a.get().f3717c != null && this.f3231a.get().f3717c.findViewById(R.id.tv_service_provider) != null) {
                                ((TextView) this.f3231a.get().f3717c.findViewById(R.id.tv_service_provider)).setText(((b.b.g.m.e.a) this.f3231a.get().D.get(i2)).f932a);
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }
    }

    public class p implements ServiceConnection {
        public p() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                ElectricityManageFragment.this.Q = true;
                ElectricityManageFragment.this.P = a.AbstractBinderC0044a.d0(iBinder);
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ElectricityManageFragment.this.Q = false;
            ElectricityManageFragment.this.D0();
        }

        public /* synthetic */ p(ElectricityManageFragment electricityManageFragment, f fVar) {
            this();
        }
    }

    public static boolean n0(Context context, Intent intent) {
        List<ResolveInfo> listQueryIntentActivities;
        return (context == null || intent == null || (listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536)) == null || listQueryIntentActivities.size() <= 0) ? false : true;
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.A = new o(this);
        ScrollView scrollView = (ScrollView) this.f3717c.findViewById(R.id.scrollView);
        this.z = scrollView;
        scrollView.setOverScrollMode(2);
        this.o = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.sbg_soc_limit);
        this.p = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.sbg_notli_limit);
        this.s = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.sbg_ac_limit);
        this.q = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.sbg_antitheft_charging_port);
        this.r = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.sbg_antitheft_charging_port2);
        this.w = (ElectricityAnimView) this.f3717c.findViewById(R.id.electricityAnimView);
        this.t = (BydSwitch) this.f3717c.findViewById(R.id.switch_antitheft_charging_port);
        this.u = (BydSwitch) this.f3717c.findViewById(R.id.switch_no_sense_charging);
        this.v = (BydSwitch) this.f3717c.findViewById(R.id.switch_charge_preheat);
        this.x = (ImageView) this.f3717c.findViewById(R.id.iv_lock_result);
    }

    public final void A0() {
        b.b.g.q.d.l lVarH = b.b.g.q.d.m.h(getContext(), "ElectricityManageFragment_label_preheat_unnecessary", getContext().getResources().getString(R.string.electricity_charge_preheat), getContext().getResources().getString(R.string.electricity_charge_preheat_unnecessary), getContext().getResources().getString(R.string.dialog_btn_ok), null, new l(), new m());
        lVarH.C();
        this.S = new n(5100L, 1000L, lVarH).start();
    }

    public final void B0() {
        b.b.g.q.d.m.f(getContext(), "ElectricityManageFragment_label_preheat_hint", getContext().getResources().getString(R.string.electricity_charge_preheat), getContext().getResources().getString(R.string.electricity_charge_preheat_hint), GravityCompat.START, getResources().getDimensionPixelSize(R.dimen.sp_20), getContext().getResources().getString(R.string.dialog_btn_ok), null, new g(), null).C();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void C0() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_service_provider, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.rv_service_provider);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ServiceProviderListAdapter(getContext(), (b.b.g.n.n.b) mo7getP()));
        recyclerView.setVisibility(this.D.size() == 0 ? 8 : 0);
        viewInflate.findViewById(R.id.tv_nodata).setVisibility(this.D.size() != 0 ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
        int size = this.D.size() * getResources().getDimensionPixelSize(R.dimen.dp_48);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_368);
        if (size > dimensionPixelSize) {
            layoutParams.height = dimensionPixelSize;
            recyclerView.setLayoutParams(layoutParams);
        }
        ((ServiceProviderListAdapter) recyclerView.getAdapter()).d(this.D);
        b.b.g.q.d.m.i(getContext(), "ElectricityManageFragment_label_service", getContext().getResources().getString(R.string.electricity_service_provider), getContext().getResources().getString(R.string.dialog_btn_ok), getContext().getResources().getString(R.string.dialog_btn_cancel), new a(recyclerView), null, true, viewInflate).C();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void D0() {
        if (((b.b.g.n.n.b) mo7getP()).C()) {
            try {
                this.Q = false;
                if (getContext() != null) {
                    getContext().unbindService(this.R);
                }
                this.P = null;
                this.R = null;
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.BydSwitch.a
    public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
        if (view.isEnabled()) {
            ((BydSwitch) view).setChecked(z, false);
            b.b.g.o.f.d(view, 0);
            switch (view.getId()) {
                case R.id.switch_antitheft_charging_port /* 2131298221 */:
                    int i2 = z ? 1 : 2;
                    b0(true, i2);
                    ((b.b.g.n.n.b) mo7getP()).J(i2, z ? 2 : 1);
                    break;
                case R.id.switch_charge_preheat /* 2131298227 */:
                    b0(false, 0);
                    if (!z) {
                        ((b.b.g.n.n.b) mo7getP()).F(2);
                    } else if (((b.b.g.n.n.b) mo7getP()).f() != 0) {
                        A0();
                    } else {
                        z0();
                    }
                    break;
                case R.id.switch_intelligent_temp /* 2131298249 */:
                    b0(false, 0);
                    if (!z) {
                        ((b.b.g.n.n.b) mo7getP()).I(2);
                    } else {
                        u0();
                    }
                    break;
                case R.id.switch_no_sense_charging /* 2131298265 */:
                    b0(false, 0);
                    if (v.d().e("KEY_NOSENSE_CHARGING", -1) == -1 && z) {
                        C0();
                    }
                    this.f3717c.findViewById(R.id.ll_service_provider).setVisibility(z ? 0 : 8);
                    v.d().i("KEY_NOSENSE_CHARGING", z ? 1 : 0);
                    break;
                case R.id.switch_presale /* 2131298267 */:
                    b0(false, 0);
                    ((b.b.g.n.n.b) mo7getP()).K(z ? 1 : 2);
                    break;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a0() {
        if (((b.b.g.n.n.b) mo7getP()).C()) {
            try {
                this.R = new p(this, null);
                Intent intent = new Intent();
                intent.setAction("com.byd.aidl.dialog");
                intent.setPackage("com.byd.uereport");
                if (getContext() != null) {
                    getContext().bindService(intent, this.R, 1);
                }
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }
    }

    public final void b0(boolean z, int i2) {
        m = z;
        this.x.setVisibility(z ? 0 : 8);
        this.w.setVisibility(z ? 8 : 0);
        if (z) {
            int iA = b.b.g.n.q.b.c().a();
            if (i2 == 1 || i2 == 3 || i2 == 2) {
                this.x.setImageDrawable(ContextCompat.getDrawable(getContext(), b.b.g.r.n.d(iA, i2)));
            }
        }
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_electricity_manage;
    }

    public final void c0() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.byd.smarttravel", "com.byd.smarttravel.MainActivity"));
        intent.setFlags(337641472);
        if (n0(getContext(), intent)) {
            startActivity(intent);
        }
    }

    public void d0(int i2, boolean z) {
        if (i2 == 1) {
            this.s.setPosition(0, z);
            return;
        }
        if (i2 == 2) {
            this.s.setPosition(1, z);
            return;
        }
        if (i2 == 3) {
            this.s.setPosition(2, z);
        } else if (i2 == 4) {
            this.s.setPosition(3, z);
        } else if (i2 == 5) {
            this.s.setPosition(4, z);
        }
    }

    public void e0(int i2) {
        int i3 = 0;
        b0(false, 0);
        if (i2 == 1) {
            i3 = 1;
        } else if (i2 == 11) {
            i3 = -1;
        }
        this.w.setBatteryScan(i3);
    }

    public void f0(int i2, boolean z) {
        if (i2 == 1) {
            this.v.setChecked(true, false);
            return;
        }
        if (i2 == 2) {
            this.v.setChecked(false, false);
            if (z) {
                z.d(null, getResources().getString(R.string.electricity_charge_preheat_completed), R.drawable.icon_light_public_green_cherk_center_48);
                return;
            }
            return;
        }
        if (i2 == 0 || i2 == 3) {
            this.v.setChecked(false, false);
        }
    }

    public void g0(int i2, boolean z) {
        int i3;
        if (i2 == 5) {
            this.o.setPosition(0, z);
            i3 = 60;
        } else if (i2 == 4) {
            this.o.setPosition(1, z);
            i3 = 70;
        } else if (i2 == 3) {
            this.o.setPosition(2, z);
            i3 = 80;
        } else if (i2 == 2) {
            this.o.setPosition(3, z);
            i3 = 90;
        } else if (i2 == 1) {
            this.o.setPosition(4, z);
            i3 = 100;
        } else {
            i3 = 0;
        }
        if (!z || i2 < 1 || i2 > 5) {
            return;
        }
        this.w.setLineTipsView(i3);
        this.A.sendEmptyMessage(1);
        this.A.removeMessages(0);
        this.A.sendEmptyMessageDelayed(0, 2000L);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_electricity_manage;
    }

    public void h0(int i2, boolean z) {
        if (i2 == Integer.MAX_VALUE) {
            b.b.a.h.k.b(this.p);
        } else {
            if (i2 < 0 || i2 > 4) {
                return;
            }
            b.b.a.h.k.c(this.p);
            this.p.setPosition(i2, z);
        }
    }

    public void i0(int i2) {
        if (i2 == 1) {
            ((BydSwitch) this.f3717c.findViewById(R.id.switch_intelligent_temp)).setChecked(true, false);
        } else if (i2 == 2) {
            ((BydSwitch) this.f3717c.findViewById(R.id.switch_intelligent_temp)).setChecked(false, false);
        }
    }

    public void j0(int i2, boolean z, boolean z2) {
        if (i2 == 1) {
            this.q.setPosition(0, z);
            this.r.setPosition(0, z);
            this.t.setChecked(true, false);
        } else if (i2 == 2) {
            this.q.setPosition(1, z);
            this.r.setPosition(1, z);
            this.t.setChecked(false, false);
        } else if (i2 == 3) {
            this.r.setPosition(2, z);
        }
    }

    public void k0(int i2) {
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            b.b.a.h.k.e(this.v);
        } else {
            b.b.a.h.k.h(this.v);
        }
    }

    public void l0(int i2) {
        ElectricityAnimView electricityAnimView = this.w;
        if (electricityAnimView != null) {
            electricityAnimView.setBatteryProgress(i2);
        }
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_electricity_manage_byd;
    }

    public void m0(int i2) {
        if (i2 == 0) {
            ((BydSwitch) this.f3717c.findViewById(R.id.switch_presale)).setChecked(true, false);
        } else if (i2 == 1) {
            ((BydSwitch) this.f3717c.findViewById(R.id.switch_presale)).setChecked(false, false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void o0() {
        int iE = ((b.b.g.n.n.b) mo7getP()).e();
        int i2 = iE != 1 ? iE == 11 ? -1 : 0 : 1;
        this.w.setBatteryCharge(i2);
        this.w.setBatteryScan(i2);
        this.w.setBatteryProgress(((b.b.g.n.n.b) mo7getP()).n());
        if (this.f3720f) {
            return;
        }
        this.w.setAlphaAnimation();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        for (String str : b.b.g.q.d.m.f1480b) {
            str.hashCode();
            switch (str) {
                case "ElectricityManageFragment_label_service":
                    C0();
                    break;
                case "ElectricityManageFragment_label_nosense":
                    v0();
                    break;
                case "ElectricityManageFragment_label_notlilimit_hint":
                    w0();
                    break;
                case "ElectricityManageFragment_label_notlilimit_invalid":
                    x0();
                    break;
                case "ElectricityManageFragment_label_aclimit_hint":
                    t0();
                    break;
                case "ElectricityManageFragment_label_preheat_unnecessary":
                    A0();
                    break;
                case "ElectricityManageFragment_label_preheat_hint":
                    B0();
                    break;
                case "ElectricityManageFragment_label_preheat_open":
                    z0();
                    break;
                case "ElectricityManageFragment_label_port":
                    y0();
                    break;
                case "ElectricityManageFragment_label_temp":
                    u0();
                    break;
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b.b.g.h.a aVar;
        if (b.b.m.g.c.a(300L)) {
        }
        switch (view.getId()) {
            case R.id.iv_ac_limit /* 2131297168 */:
                t0();
                break;
            case R.id.iv_antitheft_charging_port /* 2131297170 */:
                y0();
                break;
            case R.id.iv_charge_preheat /* 2131297215 */:
                B0();
                break;
            case R.id.iv_no_sense_charging /* 2131297312 */:
                v0();
                break;
            case R.id.iv_notli_limit /* 2131297314 */:
                w0();
                break;
            case R.id.ll_external_discharge /* 2131297484 */:
                if (this.Q && (aVar = this.P) != null) {
                    try {
                        aVar.U(9, 0);
                    } catch (Exception e2) {
                        BydLogHelper.logE(e2.getMessage());
                        return;
                    }
                    break;
                }
                break;
            case R.id.ll_move_station /* 2131297514 */:
                startActivity(new Intent(getContext(), (Class<?>) MovePowerStationActivity.class));
                break;
            case R.id.ll_smart_charging /* 2131297563 */:
                c0();
                break;
            case R.id.tv_service_provider /* 2131298721 */:
                C0();
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        D0();
        p0(false);
        Handler handler = this.A;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        ElectricityAnimView electricityAnimView = this.w;
        if (electricityAnimView != null) {
            electricityAnimView.d();
        }
        this.B = false;
        super.onDestroyView();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        b.b.m.c.a.d(this.n, "[Memory]----[Anim]----[ElectricityManagePager]----onPause");
        Handler handler = this.A;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        ElectricityAnimView electricityAnimView = this.w;
        if (electricityAnimView != null) {
            this.E = false;
            electricityAnimView.d();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i2) {
        int id = view.getId();
        if (id == R.id.sbg_soc_limit) {
            b0(false, 0);
            int[] iArr = {5, 4, 3, 2, 1};
            int revertPosition = this.o.getRevertPosition();
            ((b.b.g.n.n.b) mo7getP()).G(iArr[i2], revertPosition != 0 ? revertPosition == 1 ? 4 : revertPosition == 2 ? 3 : revertPosition == 3 ? 2 : revertPosition == 4 ? 1 : revertPosition : 5);
        }
        switch (id) {
            case R.id.sbg_ac_limit /* 2131298060 */:
                b0(false, 0);
                if (i2 == 0) {
                    i = 1;
                } else if (i2 == 1) {
                    i = 2;
                } else if (i2 == 2) {
                    i = 3;
                } else if (i2 == 3) {
                    i = 4;
                } else if (i2 != 4) {
                    i = 0;
                }
                ((b.b.g.n.n.b) mo7getP()).E(i);
                break;
            case R.id.sbg_antitheft_charging_port /* 2131298061 */:
            case R.id.sbg_antitheft_charging_port2 /* 2131298062 */:
                if (i2 == 0) {
                    b0(true, 1);
                    ((b.b.g.n.n.b) mo7getP()).J(1, 0);
                } else if (i2 == 1) {
                    b0(true, 2);
                    ((b.b.g.n.n.b) mo7getP()).J(2, 0);
                } else if (i2 == 2) {
                    b0(true, 3);
                    ((b.b.g.n.n.b) mo7getP()).J(3, 0);
                }
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        String str = this.n;
        String[] strArr = new String[1];
        StringBuilder sb = new StringBuilder();
        sb.append("[Memory]----[Anim]----[ElectricityManagePager]----resumeme----mElectricityAnimView != null:");
        sb.append(this.w != null);
        strArr[0] = sb.toString();
        b.b.m.c.a.d(str, strArr);
        ElectricityAnimView electricityAnimView = this.w;
        if (electricityAnimView == null || this.E) {
            return;
        }
        electricityAnimView.d();
        o0();
    }

    public final void p0(boolean z) {
        if (this.f3717c == null) {
            return;
        }
        this.o.setOnPositionChangedListener(z ? this : null);
        this.s.setOnPositionChangedListener(z ? this : null);
        this.p.setOnMyClickListener(z ? this : null);
        this.q.setOnPositionChangedListener(z ? this : null);
        this.r.setOnPositionChangedListener(z ? this : null);
        this.f3717c.findViewById(R.id.ll_smart_charging).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.ll_external_discharge).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.ll_move_station).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.iv_antitheft_charging_port).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.iv_no_sense_charging).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.iv_charge_preheat).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.tv_service_provider).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.iv_ac_limit).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.iv_notli_limit).setOnClickListener(z ? this : null);
        this.t.setOnInterceptTouchListener(z ? this : null);
        ((BydSwitch) this.f3717c.findViewById(R.id.switch_intelligent_temp)).setOnInterceptTouchListener(z ? this : null);
        ((BydSwitch) this.f3717c.findViewById(R.id.switch_presale)).setOnInterceptTouchListener(z ? this : null);
        this.u.setOnInterceptTouchListener(z ? this : null);
        this.v.setOnInterceptTouchListener(z ? this : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void q0() {
        o0();
        this.E = true;
        ((TextView) this.f3717c.findViewById(R.id.tv_smart_charging)).setText(getContext().getResources().getString(R.string.newenergy_smart_charging));
        this.f3717c.findViewById(R.id.ll_smart_charging).setVisibility(((b.b.g.n.n.b) mo7getP()).A() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_soc_limit).setVisibility(((b.b.g.n.n.b) mo7getP()).u() ? 0 : 8);
        if (((b.b.g.n.n.b) mo7getP()).u()) {
            b.b.a.h.k.h(this.o);
        } else {
            b.b.a.h.k.e(this.o);
        }
        this.f3717c.findViewById(R.id.ll_ac_limit).setVisibility(((b.b.g.n.n.b) mo7getP()).s() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_antitheft_charging_port).setVisibility(((b.b.g.n.n.b) mo7getP()).w() ? 8 : 0);
        this.f3717c.findViewById(R.id.ll_charging_port_title).setVisibility(((b.b.g.n.n.b) mo7getP()).w() ? 0 : 8);
        this.f3717c.findViewById(R.id.iv_antitheft_charging_port).setVisibility(((b.b.g.n.n.b) mo7getP()).w() ? 0 : 8);
        this.q.setVisibility((!((b.b.g.n.n.b) mo7getP()).w() || ((b.b.g.n.n.b) mo7getP()).t()) ? 8 : 0);
        this.r.setVisibility((((b.b.g.n.n.b) mo7getP()).w() && ((b.b.g.n.n.b) mo7getP()).t()) ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_intelligent_temp).setVisibility(((b.b.g.n.n.b) mo7getP()).B() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_charge_preheat).setVisibility(((b.b.g.n.n.b) mo7getP()).y() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_pre_sale_mode).setVisibility(((b.b.g.n.n.b) mo7getP()).z() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_notli_limit).setVisibility(((b.b.g.n.n.b) mo7getP()).x() ? 0 : 8);
        if (((b.b.g.n.n.b) mo7getP()).x()) {
            int[] iArrO = ((b.b.g.n.n.b) mo7getP()).o();
            ((SegmentedButton) this.f3717c.findViewById(R.id.sb_notli_1)).setText(iArrO[0] + "%");
            ((SegmentedButton) this.f3717c.findViewById(R.id.sb_notli_2)).setText(iArrO[1] + "%");
            ((SegmentedButton) this.f3717c.findViewById(R.id.sb_notli_3)).setText(iArrO[2] + "%");
            ((SegmentedButton) this.f3717c.findViewById(R.id.sb_notli_4)).setText(iArrO[3] + "%");
            ((SegmentedButton) this.f3717c.findViewById(R.id.sb_notli_5)).setText(iArrO[4] + "%");
        }
        this.f3717c.findViewById(R.id.ll_external_discharge).setVisibility(((b.b.g.n.n.b) mo7getP()).C() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_move_station).setVisibility(((b.b.g.n.n.b) mo7getP()).D() ? 0 : 8);
    }

    public boolean r0() {
        return isVisible();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.e
    public void s(View view, int i2) {
        if (view.getId() != R.id.sbg_notli_limit) {
            return;
        }
        if (b.b.a.h.k.a(this.p)) {
            x0();
            return;
        }
        this.p.setPosition(i2);
        b0(false, 0);
        ((b.b.g.n.n.b) mo7getP()).H(i2);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: s0, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.n.b newP() {
        return new b.b.g.n.n.b();
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i2) {
        super.t(i2);
        ScrollView scrollView = this.z;
        if (scrollView != null) {
            scrollView.post(new e(i2));
        }
    }

    public final void t0() {
        b.b.g.q.d.m.f(getContext(), "ElectricityManageFragment_label_aclimit_hint", getContext().getResources().getString(R.string.electricity_aclimit), getContext().getResources().getString(R.string.electricity_aclimit_desc), GravityCompat.START, getResources().getDimensionPixelSize(R.dimen.sp_20), getContext().getResources().getString(R.string.dialog_btn_ok), null, new h(), null).C();
    }

    public final void u0() {
        b.b.g.r.e.h(140000);
        b.b.g.q.d.l lVarG = b.b.g.q.d.m.g(getContext(), "ElectricityManageFragment_label_temp", getContext().getResources().getString(R.string.newenergy_charge_temp_ctl_state), getContext().getResources().getString(R.string.newenergy_charge_temp_ctl_dialog_message), GravityCompat.START, getContext().getResources().getString(R.string.newenergy_memory_mode_open), getContext().getResources().getString(R.string.dialog_btn_cancel), new b(), new c());
        lVarG.setDismissListener(new d());
        lVarG.C();
    }

    public final void v0() {
        b.b.g.q.d.m.h(getContext(), "ElectricityManageFragment_label_nosense", getContext().getResources().getString(R.string.electricity_no_sense_charging), getContext().getResources().getString(R.string.electricity_no_sense_hint), getContext().getResources().getString(R.string.dialog_btn_ok), null, new f(), null).C();
    }

    public final void w0() {
        b.b.g.q.d.m.h(getContext(), "ElectricityManageFragment_label_notlilimit_hint", getContext().getResources().getString(R.string.newenergy_charging_limit), getContext().getResources().getString(R.string.electricity_charge_limit_hint), getContext().getResources().getString(R.string.dialog_btn_ok), null, null, null).C();
    }

    public final void x0() {
        b.b.g.q.d.m.h(getContext(), "ElectricityManageFragment_label_notlilimit_hint", getContext().getResources().getString(R.string.newenergy_charging_limit), getContext().getResources().getString(R.string.electricity_charge_limit_best_status), getContext().getResources().getString(R.string.dialog_btn_ok), null, null, null).C();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        ((b.b.g.n.n.b) mo7getP()).r();
        a0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void y0() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_charging_port_hint, (ViewGroup) null);
        if (((b.b.g.n.n.b) mo7getP()).t()) {
            ((TextView) viewInflate.findViewById(R.id.tv_port_title)).setText(getResources().getString(R.string.electricity_three_mode));
            ((TextView) viewInflate.findViewById(R.id.tv_port_content)).setText(getResources().getString(R.string.electricity_three_mode_hint));
        }
        b.b.g.q.d.m.i(getContext(), "ElectricityManageFragment_label_port", getContext().getResources().getString(R.string.newenergy_charging_port_electric_lock_anti_theft), getContext().getResources().getString(R.string.dialog_btn_ok), null, null, null, true, viewInflate).C();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void z() {
        super.z();
        b.b.m.c.a.d(this.n, "[Memory]----[Anim]----[ElectricityManagePager]----initLazyData");
        p0(true);
        ((b.b.g.n.n.b) mo7getP()).L();
        b0(m, ((b.b.g.n.n.b) mo7getP()).l());
    }

    public final void z0() {
        b.b.g.r.e.h(140017);
        b.b.g.q.d.l lVarG = b.b.g.q.d.m.g(getContext(), "ElectricityManageFragment_label_preheat_open", getContext().getResources().getString(R.string.electricity_charge_preheat), getContext().getResources().getString(R.string.electricity_charge_preheat_content), GravityCompat.START, getContext().getResources().getString(R.string.dialog_btn_ok), getContext().getResources().getString(R.string.dialog_btn_cancel), new i(), new j());
        lVarG.setDismissListener(new k());
        lVarG.C();
    }
}
