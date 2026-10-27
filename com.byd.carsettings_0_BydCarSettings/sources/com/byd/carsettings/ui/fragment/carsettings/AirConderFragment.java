package com.byd.carsettings.ui.fragment.carsettings;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.g.q.d.q;
import b.b.g.r.s;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class AirConderFragment extends XFragment<b.b.g.n.h.b> implements SegmentedButtonGroup.f, CompoundButton.OnCheckedChangeListener, View.OnClickListener, b.b.o.b.a.a {
    public SegmentedButtonGroup A;
    public TextView B;
    public TextView D;
    public BydSwitch E;
    public ImageView F;
    public ImageView G;
    public ImageView H;
    public ImageView I;
    public RelativeLayout J;
    public RelativeLayout K;
    public RelativeLayout L;
    public RelativeLayout M;
    public RelativeLayout N;
    public LinearLayout O;
    public LinearLayout P;
    public RelativeLayout Q;
    public RelativeLayout R;
    public RelativeLayout S;
    public RelativeLayout T;
    public LinearLayout U;
    public RelativeLayout V;
    public RelativeLayout W;
    public RelativeLayout X;
    public RelativeLayout Y;
    public ImageView Z;
    public ImageView a0;
    public ImageView b0;
    public ImageView c0;
    public ImageView d0;
    public ImageView e0;
    public ImageView f0;
    public Handler g0;
    public ImageView h0;
    public b.b.o.a.b.a i0;
    public int j0;
    public Dialog k0;
    public ImageView m;
    public Context n;
    public SegmentedButtonGroup o;
    public SegmentedButtonGroup p;
    public BydSwitch q;
    public BydSwitch r;
    public BydSwitch s;
    public BydSwitch t;
    public BydSwitch u;
    public BydSwitch v;
    public BydSwitch w;
    public b.b.g.q.d.i x;
    public DampingScrollView y;
    public SegmentedButtonGroup z;

    public class a implements q.b {
        public a() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AirConderFragment.this.k0.dismiss();
        }
    }

    public class b implements q.b {
        public b() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AirConderFragment.this.k0.dismiss();
        }
    }

    public class c implements q.b {
        public c() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AirConderFragment.this.k0.dismiss();
        }
    }

    public class d implements q.b {
        public d() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AirConderFragment.this.k0.dismiss();
        }
    }

    public class e extends Handler {
        public e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            AirConderFragment.this.l0((b.b.a.c.a) message.obj);
        }
    }

    public class f implements q.b {
        public f() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AirConderFragment.this.k0.dismiss();
        }
    }

    public class g implements q.b {
        public g() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AirConderFragment.this.k0.dismiss();
        }
    }

    public class h implements q.b {
        public h() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AirConderFragment.this.k0.dismiss();
        }
    }

    public class i implements q.b {
        public i() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AirConderFragment.this.k0.dismiss();
        }
    }

    public class j implements q.b {
        public j() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AirConderFragment.this.k0.dismiss();
        }
    }

    public class k implements q.b {
        public k() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AirConderFragment.this.k0.dismiss();
        }
    }

    public class l implements q.b {
        public l() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AirConderFragment.this.k0.dismiss();
        }
    }

    public class m implements q.b {
        public m() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AirConderFragment.this.k0.dismiss();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.m = (ImageView) this.f3717c.findViewById(R.id.auto_icon);
        this.b0 = (ImageView) this.f3717c.findViewById(R.id.hot_icon);
        this.c0 = (ImageView) this.f3717c.findViewById(R.id.aircondition_test_time_icon);
        this.d0 = (ImageView) this.f3717c.findViewById(R.id.aircondition_test_door_icon);
        this.e0 = (ImageView) this.f3717c.findViewById(R.id.aircondition_hole_cycle_icon);
        this.f0 = (ImageView) this.f3717c.findViewById(R.id.aircondition_blue_talk_icon);
        this.F = (ImageView) this.f3717c.findViewById(R.id.runtime_icon);
        this.G = (ImageView) this.f3717c.findViewById(R.id.behind_board_icon);
        this.H = (ImageView) this.f3717c.findViewById(R.id.auto_clean_icon);
        this.I = (ImageView) this.f3717c.findViewById(R.id.ac_intelligent_partition_icon);
        this.o = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.aircondition_auto_segmentedButtonGroup);
        this.p = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.aircondition_hot_segmentedButtonGroup);
        this.z = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.aircondition_runtime_segmentedButtonGroup);
        this.A = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.aircondition_board_lock_segmentedButtonGroup);
        this.q = (BydSwitch) this.f3717c.findViewById(R.id.aircondition_test_auto);
        this.r = (BydSwitch) this.f3717c.findViewById(R.id.aircondition_test_withtime);
        this.s = (BydSwitch) this.f3717c.findViewById(R.id.aircondition_test_withdoor_open);
        this.t = (BydSwitch) this.f3717c.findViewById(R.id.aircondition_auto_clean);
        this.u = (BydSwitch) this.f3717c.findViewById(R.id.aircondition_park_cycle);
        this.v = (BydSwitch) this.f3717c.findViewById(R.id.aircondition_hole_cycle);
        this.w = (BydSwitch) this.f3717c.findViewById(R.id.aircondition_blue_talk);
        this.E = (BydSwitch) this.f3717c.findViewById(R.id.ac_intelligent_partition_switch);
        this.B = (TextView) this.f3717c.findViewById(R.id.runtime_tv);
        this.D = (TextView) this.f3717c.findViewById(R.id.behind_board_tv);
        this.y = (DampingScrollView) this.f3717c.findViewById(R.id.nsw);
        this.Z = (ImageView) this.f3717c.findViewById(R.id.aircondition_test_auto_icon);
        this.a0 = (ImageView) this.f3717c.findViewById(R.id.aircondition_park_cycle_icon);
        this.J = (RelativeLayout) this.f3717c.findViewById(R.id.aircondition_auto_rl);
        this.K = (RelativeLayout) this.f3717c.findViewById(R.id.aircondition_warm_rl);
        this.L = (RelativeLayout) this.f3717c.findViewById(R.id.airconditon_mode_container);
        this.h0 = (ImageView) this.f3717c.findViewById(R.id.air_background);
        this.M = (RelativeLayout) this.f3717c.findViewById(R.id.runtime_container);
        this.N = (RelativeLayout) this.f3717c.findViewById(R.id.behind_board_contianer);
        this.O = (LinearLayout) this.f3717c.findViewById(R.id.airconditon_time_container);
        this.P = (LinearLayout) this.f3717c.findViewById(R.id.aircondition_setting_up_container);
        this.Q = (RelativeLayout) this.f3717c.findViewById(R.id.aircondition_test_auto_container);
        this.R = (RelativeLayout) this.f3717c.findViewById(R.id.aircondition_test_withtime_container);
        this.S = (RelativeLayout) this.f3717c.findViewById(R.id.aircondition_test_withdoor_open_container);
        this.T = (RelativeLayout) this.f3717c.findViewById(R.id.aircondition_auto_clean_container);
        this.U = (LinearLayout) this.f3717c.findViewById(R.id.aircondition_setting_down_container);
        this.V = (RelativeLayout) this.f3717c.findViewById(R.id.aircondition_park_cycle_container);
        this.W = (RelativeLayout) this.f3717c.findViewById(R.id.aircondition_hole_cycle_container);
        this.X = (RelativeLayout) this.f3717c.findViewById(R.id.aircondition_blue_talk_contianer);
        this.Y = (RelativeLayout) this.f3717c.findViewById(R.id.ac_intelligent_partition_switch_container);
        this.n = context;
        this.g0 = new e(Looper.myLooper());
        EventBus.getDefault().register(this);
    }

    public final void M() {
        b.b.a.h.l.y(null, this.q, this.s, this.r, this.t, this.u, this.v, this.w, this.E);
        b.b.a.h.l.F(null, this.m);
        b.b.a.h.l.t(null, this.o, this.p, this.z, this.A);
    }

    public int N(int i2, int i3) {
        if (this.i0 == null) {
            this.i0 = b.b.o.a.b.d.a("AirConditioner");
        }
        return this.i0.a(i2, i3);
    }

    public final b.b.g.q.d.i O(String str) {
        if (this.x == null) {
            this.x = new b.b.g.q.d.i(this.n);
        }
        this.x.x(str);
        return this.x;
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.h.b newP() {
        return new b.b.g.n.h.b();
    }

    public void Q(Context context, int i2, boolean z) {
        if (z) {
            b.b.a.h.l.j(this.h0, i2, context);
        }
    }

    public final void R() {
        this.o = null;
        this.p = null;
        this.z = null;
        this.A = null;
        this.q = null;
        this.s = null;
        this.r = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.E = null;
        this.m = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void S() {
        boolean zQ = ((b.b.g.n.h.b) mo7getP()).q();
        boolean zR = ((b.b.g.n.h.b) mo7getP()).r();
        b.b.a.h.l.v(this.J, this.o, ((b.b.g.n.h.b) mo7getP()).c(), this, this.m, this, zQ);
        b.b.a.h.l.v(this.K, this.p, ((b.b.g.n.h.b) mo7getP()).g(), this, this.b0, this, zR);
        b.b.a.h.l.K(zQ || zR, this.L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void T() {
        int iA = b.b.o.a.c.a.a(getClass().getSimpleName());
        this.j0 = iA;
        Q(this.n, N(iA, ((b.b.g.n.h.b) mo7getP()).c() == 0 ? 0 : 1), true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void U() {
        boolean zX = ((b.b.g.n.h.b) mo7getP()).x();
        boolean zW = ((b.b.g.n.h.b) mo7getP()).w();
        b.b.a.h.l.K(zX || zW, this.O);
        b.b.a.h.l.v(this.M, this.z, ((b.b.g.n.h.b) mo7getP()).o(), this, this.F, this, zX);
        b.b.a.h.l.v(this.N, this.A, ((b.b.g.n.h.b) mo7getP()).n() - 2, this, this.G, this, zW);
        this.B.setText(this.n.getResources().getString(R.string.vehiclesettings_remotely_turn_on_air_conditioner_running_time));
        this.D.setText(String.format(this.n.getResources().getString(R.string.vehiclesettings_placehodler), this.n.getResources().getString(R.string.vehiclesettings_rear_ac_panel_auto_lock_time), this.n.getResources().getString(R.string.vehiclesettings_rear_ac_panel_auto_lock_time_unit)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void V() {
        b.b.a.h.l.d(this.Y, this.E, ((b.b.g.n.h.b) mo7getP()).i(), this, this.I, this, ((b.b.g.n.h.b) mo7getP()).v());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void W() {
        boolean zT = ((b.b.g.n.h.b) mo7getP()).t();
        boolean zU = ((b.b.g.n.h.b) mo7getP()).u();
        b.b.a.h.l.K(zT || zU, this.U);
        b.b.a.h.l.d(this.X, this.w, ((b.b.g.n.h.b) mo7getP()).d(), this, this.f0, this, zT);
        b.b.a.h.l.d(this.V, this.u, ((b.b.g.n.h.b) mo7getP()).e(), this, this.a0, this, zT);
        b.b.a.h.l.d(this.W, this.v, ((b.b.g.n.h.b) mo7getP()).u(), this, this.e0, this, zU);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void X() {
        boolean zY = ((b.b.g.n.h.b) mo7getP()).y();
        boolean zS = ((b.b.g.n.h.b) mo7getP()).s();
        b.b.a.h.l.K(zY || zS, this.P);
        b.b.a.h.l.d(this.R, this.r, ((b.b.g.n.h.b) mo7getP()).m(), this, this.c0, this, zY);
        b.b.a.h.l.d(this.S, this.s, ((b.b.g.n.h.b) mo7getP()).l(), this, this.d0, this, zY);
        b.b.a.h.l.d(this.Q, this.q, ((b.b.g.n.h.b) mo7getP()).j(), this, this.Z, this, zY);
        b.b.a.h.l.d(this.T, this.t, ((b.b.g.n.h.b) mo7getP()).h().booleanValue(), this, this.H, this, zS);
    }

    public final void Y() {
        boolean zG = b.b.g.n.q.b.c().g();
        int i2 = R.layout.carsetting_dialog_withtime;
        int i3 = -1;
        int i4 = R.string.vehiclesettings_ac_automatic_mode_ev;
        if (!zG) {
            i3 = R.string.vehiclesettings_ac_automatic_mode_hev_second;
            i4 = R.string.vehiclesettings_ac_automatic_mode_hev_content;
            i2 = R.layout.aircondition_dialog_details;
        }
        b.b.g.q.d.i iVarO = O("ac_auto_mode");
        this.k0 = iVarO.z(i2, R.string.vehiclesettings_air_conditioner_automatic_mode, i4, i3, -1, R.string.vehiclesettings_button_confirm, 1, new k(), -1, null);
    }

    public final void Z(String str) {
        str.hashCode();
        switch (str) {
            case "ac_auto_mode":
                Y();
                break;
            case "ac_door_check":
                a0();
                break;
            case "ac_ble_mode":
                e0();
                break;
            case "ac_warm_mode":
                c0();
                break;
            case "ac_intel_part_check":
                g0();
                break;
            case "ac_panel_mode":
                h0();
                break;
            case "ac_auto_clean_mode":
                d0();
                break;
            case "ac_30min_check":
                b0();
                break;
            case "ac_runtime_mode":
                k0();
                break;
            case "ac_pm25_power_test":
                j0();
                break;
            case "ac_auto_park_cycle":
                i0();
                break;
            case "ac_hole_cycle_mode":
                f0();
                break;
        }
    }

    public final void a0() {
        this.k0 = O("ac_door_check").B(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_automatic_air_detection_of_switch_door, R.string.vehiclesettings_ac_air_auto_detection_door, -1, -1, R.string.vehiclesettings_button_confirm, new h(), -1, null);
    }

    public final void b0() {
        this.k0 = O("ac_30min_check").B(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_30_minutes_air_detection, R.string.vehiclesettings_ac_air_auto_detection_30_min, -1, -1, R.string.vehiclesettings_button_confirm, new i(), -1, null);
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_carsetting_rtl_aircondition;
    }

    public final void c0() {
        this.k0 = O("ac_warm_mode").z(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_air_conditioning_heating_mode, R.string.vehiclesettings_ac_heating_mode, -1, -1, R.string.vehiclesettings_button_confirm, 1, new j(), -1, null);
    }

    public final void d0() {
        this.k0 = O("ac_auto_clean_mode").z(R.layout.carsetting_dialog_warm_auto, R.string.vehiclesettings_automatic_purification, R.string.vehiclesettings_ac_purification_description, -1, -1, R.string.vehiclesettings_button_confirm, 1, new a(), -1, null);
    }

    public final void e0() {
        this.k0 = O("ac_ble_mode").z(R.layout.carsetting_dialog_warm_auto, R.string.vehiclesettings_bluetooth_call_automatically_reduces_wind_speed, R.string.vehiclesettings_ac_air_ble_description, -1, -1, R.string.vehiclesettings_button_confirm, 1, new f(), -1, null);
    }

    public final void f0() {
        this.k0 = O("ac_hole_cycle_mode").z(R.layout.carsetting_dialog_warm_auto, R.string.vehiclesettings_automatic_internal_circulation_of_tunnel, R.string.vehiclesettings_ac_air_hole_cycle_description, -1, -1, R.string.vehiclesettings_button_confirm, 1, new g(), -1, null);
    }

    public final void g0() {
        this.k0 = O("ac_intel_part_check").B(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_intelligent_partition, R.string.vehiclesettings_intelligent_partition_details, -1, -1, R.string.vehiclesettings_button_confirm, new d(), -1, null);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_aircondition;
    }

    public final void h0() {
        this.k0 = O("ac_panel_mode").B(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_rear_ac_panel_auto_lock_time, R.string.vehiclesettings_ac_pannel_description, -1, -1, R.string.vehiclesettings_button_confirm, new m(), -1, null);
    }

    public final void i0() {
        this.k0 = O("ac_auto_park_cycle").z(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_parking_automatic_inner_circulation, R.string.vehiclesettings_parking_automatic_description, -1, -1, R.string.vehiclesettings_button_confirm, 1, new c(), -1, null);
    }

    public final void j0() {
        this.k0 = O("ac_pm25_power_test").B(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_automatic_air_detection_on_power_up, R.string.vehiclesettings_automatic_air_detection_description, -1, -1, R.string.vehiclesettings_button_confirm, new b(), -1, null);
    }

    public final void k0() {
        this.k0 = O("ac_runtime_mode").z(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_remotely_turn_on_air_conditioner_running_time, R.string.vehiclesettings_ac_runtime_description, -1, -1, R.string.vehiclesettings_button_confirm, 1, new l(), -1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v36 */
    /* JADX WARN: Type inference failed for: r0v74 */
    /* JADX WARN: Type inference failed for: r0v75 */
    public final void l0(b.b.a.c.a aVar) {
        switch (aVar.h()) {
            case 30001:
                BydLogHelper.logD("message : AC_AUTO_MODE_CHANGE_MESSAGE " + aVar.c());
                int iC = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC = ((b.b.g.n.h.b) mo7getP()).c() == 0 ? 1 : 2;
                }
                if (iC == 1 || iC == 2) {
                    this.o.setPositionWithOutCallback(iC - 1, false);
                    Q(this.n, N(24, iC != 1 ? 1 : 0), true);
                }
                break;
            case 30002:
                BydLogHelper.logD("message : AC_WARM_MODE_CHANGE_MESSAGE " + aVar.c());
                int iC2 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC2 = ((b.b.g.n.h.b) mo7getP()).g() == 0 ? 1 : 2;
                }
                if (iC2 == 1 || iC2 == 2) {
                    this.p.setPositionWithOutCallback(iC2 - 1, false);
                }
                break;
            case 30003:
                BydLogHelper.logD("message : AC_REMOTE_START_TIME_CHANGE_MESSAGE " + aVar.c());
                int iC3 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    this.z.setPositionWithOutCallback(((b.b.g.n.h.b) mo7getP()).o(), false);
                    break;
                } else if (iC3 >= 1 && iC3 <= 5) {
                    this.z.setPositionWithOutCallback(iC3 - 1, false);
                    break;
                }
                break;
            case 30004:
                BydLogHelper.logD("message : AC_REAR_PANEL_TIME_CHANGE_MESSAGE " + aVar.c());
                int iC4 = aVar.c();
                if (b.b.g.r.e.e(aVar) && (iC4 = ((b.b.g.n.h.b) mo7getP()).n()) == 0) {
                    this.A.setPositionWithOutCallback(iC4 - 1, false);
                    break;
                } else if (iC4 >= 1 && iC4 <= 4) {
                    this.A.setPositionWithOutCallback(iC4 - 2, false);
                    break;
                }
                break;
            case 30005:
                BydLogHelper.logD("message : AC_PM25_POWER_CHANGE_MESSAGE " + aVar.c());
                int iC5 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC5 = ((b.b.g.n.h.b) mo7getP()).j() ? 1 : 2;
                }
                if (iC5 == 1 || iC5 == 2) {
                    this.q.setChecked(iC5 == 1, false);
                }
                break;
            case 30006:
                BydLogHelper.logD("message : AC_PM25_TIME_CHECK_CHANGE_MESSAGE " + aVar.c());
                ?? C = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    C = ((b.b.g.n.h.b) mo7getP()).m();
                }
                if (C <= 1) {
                    this.r.setChecked(C == 1, false);
                }
                break;
            case 30007:
                BydLogHelper.logD("message : AC_PM25_WITH_DOOR_CHANGE_MESSAGE " + aVar.c());
                int iC6 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC6 = ((b.b.g.n.h.b) mo7getP()).l() ? 1 : 2;
                }
                if (iC6 == 1 || iC6 == 2) {
                    this.s.setChecked(iC6 == 1, false);
                }
                break;
            case 30008:
                BydLogHelper.logD("message : AC_AUTO_CLEAN_CHANGE_MESSAGE " + aVar.c());
                int iC7 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC7 = ((b.b.g.n.h.b) mo7getP()).h().booleanValue() ? 1 : 2;
                }
                if (iC7 == 1 || iC7 == 2) {
                    this.t.setChecked(iC7 == 1, false);
                }
                break;
            case 30009:
                BydLogHelper.logD("message : AC_PAUSE_CYCLE_CHANGE_MESSAGE " + aVar.c());
                int iC8 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC8 = ((b.b.g.n.h.b) mo7getP()).e() ? 1 : 2;
                }
                if (iC8 == 2 || iC8 == 1) {
                    this.u.setChecked(iC8 == 1, false);
                }
                break;
            case 30010:
                BydLogHelper.logD("message : AC_TUNNEL_CYCLE_CHANGE_MESSAGE " + aVar.c());
                int iC9 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC9 = ((b.b.g.n.h.b) mo7getP()).f() ? 1 : 2;
                }
                if (iC9 == 2 || iC9 == 1) {
                    this.v.setChecked(iC9 == 1, false);
                }
                break;
            case 30011:
                BydLogHelper.logD("message : AC_BT_WIND_CHANGE_MESSAGE " + aVar.c());
                int iC10 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC10 = ((b.b.g.n.h.b) mo7getP()).d() ? 1 : 2;
                }
                if (iC10 == 2 || iC10 == 1) {
                    this.w.setChecked(iC10 == 1, false);
                }
                break;
            case 30012:
                BydLogHelper.logD("message : AC_INTELLIGENT_AREA_CHANGE_MESSAGE " + aVar.c());
                int iC11 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC11 = ((b.b.g.n.h.b) mo7getP()).i() ? 1 : 2;
                }
                if (iC11 == 1 || iC11 == 2) {
                    this.E.setChecked(iC11 == 1, false);
                }
                break;
        }
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_carsetting_right_aircondition;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        for (String str : b.b.g.q.d.k.b()) {
            if (str.startsWith("ac_")) {
                Z(str);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.ac_intelligent_partition_switch /* 2131296273 */:
                if (b.b.a.h.l.a(this.E)) {
                    ((b.b.g.n.h.b) mo7getP()).F(z ? 1 : 2);
                    b.b.g.r.e.i(30012);
                    break;
                }
                break;
            case R.id.aircondition_auto_clean /* 2131296340 */:
                if (b.b.a.h.l.a(this.t)) {
                    ((b.b.g.n.h.b) mo7getP()).E(z ? 1 : 2);
                    b.b.g.r.e.i(30008);
                    break;
                }
                break;
            case R.id.aircondition_blue_talk /* 2131296345 */:
                if (b.b.a.h.l.a(this.w)) {
                    ((b.b.g.n.h.b) mo7getP()).A(z ? 1 : 2);
                    b.b.g.r.e.i(30011);
                    break;
                }
                break;
            case R.id.aircondition_hole_cycle /* 2131296350 */:
                if (b.b.a.h.l.a(this.v)) {
                    ((b.b.g.n.h.b) mo7getP()).C(z ? 1 : 2);
                    b.b.g.r.e.i(30010);
                    break;
                }
                break;
            case R.id.aircondition_park_cycle /* 2131296355 */:
                if (b.b.a.h.l.a(this.u)) {
                    ((b.b.g.n.h.b) mo7getP()).B(z ? 1 : 2);
                    b.b.g.r.e.i(30009);
                    break;
                }
                break;
            case R.id.aircondition_test_auto /* 2131296363 */:
                if (b.b.a.h.l.a(this.q)) {
                    ((b.b.g.n.h.b) mo7getP()).G(z ? 1 : 2);
                    b.b.g.r.e.i(30005);
                    break;
                }
                break;
            case R.id.aircondition_test_withdoor_open /* 2131296370 */:
                if (b.b.a.h.l.a(this.s)) {
                    ((b.b.g.n.h.b) mo7getP()).H(z ? 1 : 2);
                    b.b.g.r.e.i(30007);
                    break;
                }
                break;
            case R.id.aircondition_test_withtime /* 2131296372 */:
                if (b.b.a.h.l.a(this.r)) {
                    ((b.b.g.n.h.b) mo7getP()).I(z ? 1 : 0);
                    b.b.g.r.e.i(30006);
                    break;
                }
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_intelligent_partition_icon /* 2131296272 */:
                g0();
                break;
            case R.id.aircondition_blue_talk_icon /* 2131296347 */:
                e0();
                break;
            case R.id.aircondition_hole_cycle_icon /* 2131296352 */:
                f0();
                break;
            case R.id.aircondition_park_cycle_icon /* 2131296357 */:
                i0();
                break;
            case R.id.aircondition_test_auto_icon /* 2131296365 */:
                j0();
                break;
            case R.id.aircondition_test_door_icon /* 2131296367 */:
                a0();
                break;
            case R.id.aircondition_test_time_icon /* 2131296369 */:
                b0();
                break;
            case R.id.auto_clean_icon /* 2131296409 */:
                d0();
                break;
            case R.id.auto_icon /* 2131296410 */:
                Y();
                break;
            case R.id.behind_board_icon /* 2131296434 */:
                h0();
                break;
            case R.id.hot_icon /* 2131297044 */:
                c0();
                break;
            case R.id.runtime_icon /* 2131298007 */:
                k0();
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        M();
        R();
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Dialog dialog = this.k0;
        if (dialog != null && dialog.isShowing()) {
            this.k0.dismiss();
            this.k0 = null;
        }
        Handler handler = this.g0;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.g0 = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        Handler handler = this.g0;
        if (handler != null) {
            if (handler.hasMessages(aVar.h())) {
                this.g0.removeMessages(aVar.h());
            }
            Message messageObtain = Message.obtain();
            messageObtain.obj = aVar;
            messageObtain.what = aVar.h();
            this.g0.sendMessageDelayed(messageObtain, b.b.g.r.e.e(aVar) ? 0L : 500L);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i2) {
        switch (view.getId()) {
            case R.id.aircondition_auto_segmentedButtonGroup /* 2131296344 */:
                BydLogHelper.logD("setACAutoAir position ：" + i2);
                ((b.b.g.n.h.b) mo7getP()).z(i2);
                Q(this.n, N(24, i2 == 0 ? 0 : 1), true);
                b.b.g.r.e.i(30001);
                break;
            case R.id.aircondition_board_lock_segmentedButtonGroup /* 2131296349 */:
                ((b.b.g.n.h.b) mo7getP()).J(i2 + 1);
                b.b.g.r.e.i(30004);
                break;
            case R.id.aircondition_hot_segmentedButtonGroup /* 2131296354 */:
                BydLogHelper.logD("setAcWarmMode position ：" + i2);
                ((b.b.g.n.h.b) mo7getP()).D(i2);
                b.b.g.r.e.i(30002);
                break;
            case R.id.aircondition_runtime_segmentedButtonGroup /* 2131296360 */:
                ((b.b.g.n.h.b) mo7getP()).K(i2);
                b.b.g.r.e.i(30003);
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i2) {
        s.a(this.y, i2, b.b.m.e.i.a.h(getClass().getName()));
        b.b.m.e.i.a.n(getClass().getName());
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        S();
        U();
        X();
        W();
        V();
        T();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.j.b.b bVar) {
        Dialog dialog;
        if (bVar.a() == 1 || (dialog = this.k0) == null || !dialog.isShowing()) {
            return;
        }
        this.k0.dismiss();
        this.k0 = null;
    }
}
