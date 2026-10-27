package com.byd.carsettings.ui.fragment.carsettings;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.h.k;
import b.b.a.h.l;
import b.b.g.n.h.h;
import b.b.g.q.d.i;
import b.b.g.q.d.q;
import b.b.g.r.e;
import b.b.g.r.s;
import b.b.o.a.b.d;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.digitalkey.activity.DigitalKeyActivity;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;
import java.util.Iterator;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class DoorLocksFragment extends XFragment<h> implements SeekBar.OnSeekBarChangeListener, SegmentedButtonGroup.f, View.OnClickListener, CompoundButton.OnCheckedChangeListener, b.b.o.b.a.a {
    public DampingScrollView A;
    public TextView B;
    public RelativeLayout D;
    public RelativeLayout E;
    public RelativeLayout F;
    public RelativeLayout G;
    public RelativeLayout H;
    public RelativeLayout I;
    public RelativeLayout J;
    public RelativeLayout K;
    public RelativeLayout L;
    public LinearLayout M;
    public RelativeLayout N;
    public RelativeLayout O;
    public RelativeLayout P;
    public RelativeLayout Q;
    public BydSwitch R;
    public BydSwitch S;
    public RelativeLayout T;
    public RelativeLayout U;
    public RelativeLayout V;
    public BydSwitch W;
    public RelativeLayout X;
    public BydSwitch Y;
    public BydSwitch Z;
    public BydSwitch a0;
    public ImageView b0;
    public Handler c0;
    public int d0 = 0;
    public b.b.o.a.b.a e0;
    public LinearLayout f0;
    public LinearLayout g0;
    public RelativeLayout h0;
    public RelativeLayout i0;
    public LinearLayout j0;
    public RelativeLayout k0;
    public BydSwitch l0;
    public TextView m;
    public ImageView m0;
    public SeekBar n;
    public i n0;
    public SegmentedButtonGroup o;
    public Dialog o0;
    public SegmentedButtonGroup p;
    public ImageView q;
    public BydSwitch r;
    public BydSwitch s;
    public BydSwitch t;
    public BydSwitch u;
    public BydSwitch v;
    public BydSwitch w;
    public BydSwitch x;
    public BydSwitch y;
    public Context z;

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (message.what == 250011) {
                if (DoorLocksFragment.this.n != null) {
                    ((h) DoorLocksFragment.this.mo7getP()).Q(DoorLocksFragment.this.n.getProgress());
                }
                e.i(40001);
            }
        }
    }

    public class b implements q.b {
        public b() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            DoorLocksFragment.this.o0.dismiss();
        }
    }

    public static boolean P(Context context, Intent intent) {
        return (context == null || intent == null || context.getPackageManager().queryIntentActivities(intent, 65536).size() <= 0) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.z = context;
        this.A = (DampingScrollView) this.f3717c.findViewById(R.id.nsw);
        T(((h) mo7getP()).f());
        boolean zW = ((h) mo7getP()).w();
        boolean zY = ((h) mo7getP()).y();
        R(zW, zW || zY);
        Q(zY);
        boolean zJ = ((h) mo7getP()).J();
        boolean zI = ((h) mo7getP()).I();
        boolean zH = ((h) mo7getP()).H();
        boolean zF = ((h) mo7getP()).F();
        boolean zG = ((h) mo7getP()).G();
        d0(zJ, zJ || zI || zH || zF || zG);
        b0(zH);
        c0(zI);
        Z(zF);
        a0(zG);
        boolean zB = ((h) mo7getP()).B();
        boolean zC = ((h) mo7getP()).C();
        W(zB, zB || zC);
        f0(zC);
        boolean zN = ((h) mo7getP()).N();
        boolean zK = ((h) mo7getP()).K();
        boolean zL = ((h) mo7getP()).L();
        boolean zA = ((h) mo7getP()).A();
        i0(zN, zN || zK || zL || zA);
        g0(zL);
        e0(zK);
        V(zA);
        U(((h) mo7getP()).z());
        boolean zE = ((h) mo7getP()).E();
        boolean zD = ((h) mo7getP()).D();
        Y(zE, zE || zD);
        X(zD);
        h0(((h) mo7getP()).M());
        S(((h) mo7getP()).x());
        n0();
        EventBus.getDefault().register(this);
    }

    public final void M() {
        l.F(null, this.q);
        l.s(null, this.n);
        l.t(null, this.o, this.p);
        l.y(null, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y);
    }

    public int N(int i, int i2) {
        if (this.e0 == null) {
            this.e0 = d.a("doorLocks");
        }
        return this.e0.a(i, i2);
    }

    public final i O(String str) {
        if (this.n0 == null) {
            this.n0 = new i(this.z);
        }
        this.n0.x(str);
        return this.n0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Q(boolean z) {
        if (z) {
            this.o = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.door_and_lock_sgbg);
            this.G = (RelativeLayout) this.f3717c.findViewById(R.id.door_and_lock_sgbg_container);
            this.o.setPositionWithOutCallback(((h) mo7getP()).g() - 1, false);
            l.K(true, this.G);
            l.t(this, this.o);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void R(boolean z, boolean z2) {
        if (z2) {
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.back_door_out_container);
            this.F = relativeLayout;
            l.K(true, relativeLayout);
        }
        if (z) {
            this.m = (TextView) this.f3717c.findViewById(R.id.door_hight_percent);
            this.n = (SeekBar) this.f3717c.findViewById(R.id.door_and_lock_height_sb);
            RelativeLayout relativeLayout2 = (RelativeLayout) this.f3717c.findViewById(R.id.back_door_container);
            this.E = relativeLayout2;
            l.K(true, relativeLayout2);
            l.z(((h) mo7getP()).e(), this.m, this.z, R.string.vehiclesettings_placehodlers, "%");
            l.q(this.n, ((h) mo7getP()).e());
            l.s(this, this.n);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void S(boolean z) {
        if (z) {
            this.y = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_behind_lock);
            this.Q = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_behind_lock_container);
            l.K(((h) mo7getP()).x(), this.Q);
        }
    }

    public final void T(int i) {
        if (i == 1 || i == 2) {
            this.B = (TextView) this.f3717c.findViewById(R.id.digital_key_tv);
            this.q = (ImageView) this.f3717c.findViewById(R.id.door_and_lock_key_iv);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.nfc_container);
            this.D = relativeLayout;
            l.K(true, relativeLayout);
            l.A(this.B, this.z, R.string.nfc_key_mode_nfc);
            l.F(this, this.q, this.D);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void U(boolean z) {
        if (z) {
            this.k0 = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_elec_slide_door_container);
            this.m0 = (ImageView) this.f3717c.findViewById(R.id.doorlock_elec_slide_door_icon);
            BydSwitch bydSwitch = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_elec_slide_door);
            this.l0 = bydSwitch;
            l.e(this.k0, bydSwitch, ((h) mo7getP()).h(), this);
            m0(b.b.g.n.q.b.c().d());
            l.F(this, this.m0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void V(boolean z) {
        if (z) {
            this.W = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_lock_high_speed);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_lock_high_speed_container);
            this.V = relativeLayout;
            l.e(relativeLayout, this.W, ((h) mo7getP()).i(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void W(boolean z, boolean z2) {
        if (z2) {
            LinearLayout linearLayout = (LinearLayout) this.f3717c.findViewById(R.id.charge_out_container);
            this.j0 = linearLayout;
            l.K(true, linearLayout);
        }
        if (z) {
            this.R = (BydSwitch) this.f3717c.findViewById(R.id.comfortable_driving_chargeintell);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_driving_chargeintell_container);
            this.T = relativeLayout;
            l.e(relativeLayout, this.R, ((h) mo7getP()).d(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void X(boolean z) {
        if (z) {
            this.a0 = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_intelligent_lock);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_intelligent_lock_container);
            this.i0 = relativeLayout;
            l.e(relativeLayout, this.a0, ((h) mo7getP()).j(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Y(boolean z, boolean z2) {
        if (z2) {
            LinearLayout linearLayout = (LinearLayout) this.f3717c.findViewById(R.id.setting_part_lock_container);
            this.g0 = linearLayout;
            l.K(true, linearLayout);
        }
        if (z) {
            this.Z = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_rain_intelligent_unlock);
            this.h0 = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_intelligent_unlock_container);
            l.K(((h) mo7getP()).E(), this.h0);
            l.e(this.h0, this.Z, ((h) mo7getP()).l(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Z(boolean z) {
        if (z) {
            this.t = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_key_tip_lock);
            this.J = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_key_tip_lock_container);
            l.K(((h) mo7getP()).F(), this.J);
            l.e(this.J, this.t, ((h) mo7getP()).o(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a0(boolean z) {
        if (z) {
            this.u = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_key_tip_unlock);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_key_tip_unlock_container);
            this.K = relativeLayout;
            l.e(relativeLayout, this.u, ((h) mo7getP()).q(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b0(boolean z) {
        if (z) {
            this.r = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_key_press_lock);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_key_press_lock_container);
            this.H = relativeLayout;
            l.e(relativeLayout, this.r, ((h) mo7getP()).t(), this);
        }
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_carsetting_rtl_door_and_lock;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c0(boolean z) {
        if (z) {
            this.s = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_key_press_unlock);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_key_press_unlock_container);
            this.I = relativeLayout;
            l.e(relativeLayout, this.s, ((h) mo7getP()).u().booleanValue(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void d0(boolean z, boolean z2) {
        if (z2) {
            LinearLayout linearLayout = (LinearLayout) this.f3717c.findViewById(R.id.setting_part_up);
            this.f0 = linearLayout;
            l.K(true, linearLayout);
        }
        if (z) {
            this.p = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.doorlock_control_segmentedButtonGroup);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.doors_container);
            this.L = relativeLayout;
            l.u(relativeLayout, this.p, ((h) mo7getP()).n(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void e0(boolean z) {
        if (z) {
            this.x = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_lock_updoor);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_lock_updoor_container);
            this.P = relativeLayout;
            l.e(relativeLayout, this.x, ((h) mo7getP()).m(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void f0(boolean z) {
        if (z) {
            this.S = (BydSwitch) this.f3717c.findViewById(R.id.comfortable_driving_outchargeintell);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_driving_outchargeintell_container);
            this.U = relativeLayout;
            l.e(relativeLayout, this.S, ((h) mo7getP()).c(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void g0(boolean z) {
        if (z) {
            this.w = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_rain_lock);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_rain_lock_container);
            this.O = relativeLayout;
            l.e(relativeLayout, this.w, ((h) mo7getP()).s(), this);
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_door_and_lock;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void h0(boolean z) {
        if (z) {
            this.X = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_run_auto_close_door_container);
            BydSwitch bydSwitch = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_run_auto_close_door);
            this.Y = bydSwitch;
            l.e(this.X, bydSwitch, ((h) mo7getP()).v(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void i0(boolean z, boolean z2) {
        if (z2) {
            LinearLayout linearLayout = (LinearLayout) this.f3717c.findViewById(R.id.setting_part_middle);
            this.M = linearLayout;
            l.K(true, linearLayout);
        }
        if (z) {
            this.v = (BydSwitch) this.f3717c.findViewById(R.id.doorlock_drive_unlock);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.doorlock_drive_unlock_container);
            this.N = relativeLayout;
            l.e(relativeLayout, this.v, ((h) mo7getP()).r(), this);
        }
    }

    public void j0(int i) {
        this.d0 = i;
        b.b.o.a.c.a.b(getClass().getSimpleName(), Integer.valueOf(this.d0));
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public h newP() {
        return new h();
    }

    public void l0(Context context, int i, boolean z) {
        if (z) {
            l.j(this.b0, i, context);
        }
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_carsetting_right_door_and_lock;
    }

    public final void m0(int i) {
        k.f(this.l0, i == 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void n0() {
        this.b0 = (ImageView) this.f3717c.findViewById(R.id.door_and_lock_image);
        int iA = b.b.o.a.c.a.a(getClass().getSimpleName());
        if (iA == 0) {
            l0(this.z, N(iA, 0), true);
        }
        switch (iA) {
            case 18:
                l0(this.z, N(18, 0), true);
                break;
            case 19:
                l0(this.z, N(iA, 0), true);
                break;
            case 20:
                l0(this.z, N(iA, ((h) mo7getP()).n() == 0 ? 0 : 1), iA == 20);
                break;
            case 21:
                l0(this.z, N(iA, 0), true);
                break;
        }
    }

    public final void o0() {
        this.o0 = O("door_lock_ele_slide_door_dialog").B(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_door_lock_electric_door_state, R.string.vehiclesettings_door_lock_electric_door_state_tips, -1, -1, R.string.vehiclesettings_button_confirm, new b(), -1, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        Iterator<String> it = b.b.g.q.d.k.b().iterator();
        while (it.hasNext()) {
            if (it.next().startsWith("door_lock_")) {
                o0();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.comfortable_driving_chargeintell /* 2131296734 */:
                if (l.a(this.R)) {
                    j0(0);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).P(z ? 1 : 2);
                    e.i(40011);
                    break;
                }
                break;
            case R.id.comfortable_driving_outchargeintell /* 2131296745 */:
                if (l.a(this.S)) {
                    j0(0);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).O(z ? 1 : 2);
                    e.i(40012);
                    break;
                }
                break;
            case R.id.doorlock_drive_unlock /* 2131296878 */:
                if (l.a(this.v)) {
                    j0(19);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).a0(z ? 1 : 2);
                    e.i(40008);
                    break;
                }
                break;
            case R.id.doorlock_elec_slide_door /* 2131296880 */:
                if (l.a(this.l0)) {
                    j0(0);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).S(z ? 1 : 2);
                    e.i(40017);
                    break;
                }
                break;
            case R.id.doorlock_intelligent_lock /* 2131296884 */:
                if (l.a(this.a0)) {
                    j0(0);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).U(z ? 1 : 2);
                    e.i(40016);
                    break;
                }
                break;
            case R.id.doorlock_key_press_lock /* 2131296887 */:
                if (l.a(this.r)) {
                    j0(21);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).c0(z ? 1 : 2);
                    e.i(40004);
                    break;
                }
                break;
            case R.id.doorlock_key_press_unlock /* 2131296889 */:
                if (l.a(this.s)) {
                    j0(21);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).d0(z ? 1 : 2);
                    e.i(40005);
                    break;
                }
                break;
            case R.id.doorlock_key_tip_lock /* 2131296891 */:
                if (l.a(this.t)) {
                    j0(21);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).Y(z ? 1 : 2);
                    e.i(40006);
                    break;
                }
                break;
            case R.id.doorlock_key_tip_unlock /* 2131296893 */:
                if (l.a(this.u)) {
                    j0(21);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).Z(z ? 1 : 2);
                    e.i(40007);
                    break;
                }
                break;
            case R.id.doorlock_lock_high_speed /* 2131296895 */:
                if (l.a(this.W)) {
                    j0(0);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).T(z ? 1 : 2);
                    e.i(40014);
                    break;
                }
                break;
            case R.id.doorlock_lock_updoor /* 2131296897 */:
                if (l.a(this.x)) {
                    j0(0);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).W(z ? 1 : 2);
                    e.i(40010);
                    break;
                }
                break;
            case R.id.doorlock_rain_intelligent_unlock /* 2131296899 */:
                if (l.a(this.Z)) {
                    j0(0);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).V(z ? 1 : 2);
                    e.i(40015);
                    break;
                }
                break;
            case R.id.doorlock_rain_lock /* 2131296900 */:
                if (l.a(this.w)) {
                    j0(0);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).b0(z ? 1 : 2);
                    e.i(40009);
                    break;
                }
                break;
            case R.id.doorlock_run_auto_close_door /* 2131296902 */:
                if (l.a(this.Y)) {
                    j0(0);
                    l0(this.z, N(this.d0, 0), true);
                    ((h) mo7getP()).e0(z ? 1 : 2);
                    e.i(40013);
                    break;
                }
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.door_and_lock_key_iv) {
            if (id == R.id.doorlock_elec_slide_door_icon) {
                o0();
                return;
            } else if (id != R.id.nfc_container) {
                return;
            }
        }
        this.z.startActivity(new Intent(getActivity(), (Class<?>) DigitalKeyActivity.class));
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        e.a(this.c0);
        M();
        super.onDestroyView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        int iH = aVar.h();
        if (iH == 250015) {
            BydLogHelper.logD("message : BODY_ON_POWER_CHANGED " + aVar.c());
            int iC = aVar.c();
            if (iC == 2 || iC == 0) {
                m0(iC);
            }
            return;
        }
        switch (iH) {
            case 40001:
                BydLogHelper.logD("message : DL_BACK_DOOR_HEIGHT_CHANGE_MESSAGE " + aVar.c());
                int iC2 = aVar.c();
                if (e.e(aVar)) {
                    int iE = ((h) mo7getP()).e();
                    iC2 = iE < 15 ? 15 : iE;
                    if (iC2 > 100) {
                        iC2 = 100;
                    }
                }
                if (iC2 >= 15 && iC2 <= 100) {
                    l.z(iC2, this.m, this.z, R.string.vehiclesettings_placehodlers, "%");
                    l.q(this.n, iC2);
                    break;
                }
                break;
            case 40002:
                BydLogHelper.logD("message : DL_KNOB_AUTO_SHRINK_CHANGE_MESSAGE " + aVar.c());
                int iC3 = aVar.c();
                if (e.e(aVar)) {
                    iC3 = ((h) mo7getP()).g();
                }
                if (iC3 >= 1 && iC3 <= 4) {
                    l.w(this.o, iC3 - 1);
                    break;
                }
                break;
            case 40003:
                BydLogHelper.logD("message : DL_LOCK_OFF_DOOR_CHANGE_MESSAGE " + aVar.c());
                int iC4 = aVar.c();
                if (e.e(aVar)) {
                    int iN = ((h) mo7getP()).n();
                    iC4 = iN == 0 ? 1 : iN == 1 ? 2 : 0;
                }
                if (iC4 == 1 || iC4 == 2) {
                    int i = iC4 - 1;
                    l.w(this.p, i);
                    l0(this.z, N(20, i == 0 ? 0 : 1), this.d0 == 20);
                }
                break;
            case 40004:
                BydLogHelper.logD("message : DL_REMOTE_LOCK_WINDOW_CHANGE_MESSAGE " + aVar.c());
                int iC5 = aVar.c();
                if (e.e(aVar)) {
                    iC5 = ((h) mo7getP()).t() ? 1 : 2;
                }
                if (iC5 == 1 || iC5 == 2) {
                    l.f(this.r, iC5 == 1);
                }
                break;
            case 40005:
                BydLogHelper.logD("message : DL_REMOTE_UNLOCK_WINDOW_CHANGE_MESSAGE " + aVar.c());
                int iC6 = aVar.c();
                if (e.e(aVar)) {
                    iC6 = ((h) mo7getP()).u().booleanValue() ? 1 : 2;
                }
                if (iC6 == 1 || iC6 == 2) {
                    l.f(this.s, iC6 == 1);
                }
                break;
            case 40006:
                BydLogHelper.logD("message : DL_MICRO_LOCK_WINDOW_CHANGE_MESSAGE " + aVar.c());
                int iC7 = aVar.c();
                if (e.e(aVar)) {
                    iC7 = ((h) mo7getP()).o() ? 1 : 2;
                }
                if (iC7 == 1 || iC7 == 2) {
                    l.f(this.t, iC7 == 1);
                }
                break;
            case 40007:
                BydLogHelper.logD("message : DL_MICRO_UNLOCK_WINDOW_CHANGE_MESSAGE " + aVar.c());
                int iC8 = aVar.c();
                if (e.e(aVar)) {
                    iC8 = ((h) mo7getP()).q() ? 1 : 2;
                }
                if (iC8 == 1 || iC8 == 2) {
                    l.f(this.u, iC8 == 1);
                }
                break;
            case 40008:
                BydLogHelper.logD("message : DL_OVER_SPEED_LOCK_CHANGE_MESSAGE " + aVar.c());
                int iC9 = aVar.c();
                if (e.e(aVar)) {
                    iC9 = ((h) mo7getP()).r() ? 1 : 2;
                }
                if (iC9 == 1 || iC9 == 2) {
                    l.f(this.v, iC9 == 1);
                }
                break;
            case 40009:
                BydLogHelper.logD("message : DL_RAIN_LOCK_WINDOW_CHANGE_MESSAGE " + aVar.c());
                int iC10 = aVar.c();
                if (e.e(aVar)) {
                    iC10 = ((h) mo7getP()).s() ? 1 : 2;
                }
                if (iC10 == 1 || iC10 == 2) {
                    l.f(this.w, iC10 == 1);
                }
                break;
            case 40010:
                BydLogHelper.logD("message : DL_LOCK_RISE_WINDOW_CHANGE_MESSAGE " + aVar.c());
                int iC11 = aVar.c();
                if (e.e(aVar)) {
                    iC11 = ((h) mo7getP()).m() ? 1 : 2;
                }
                if (iC11 == 1 || iC11 == 2) {
                    l.f(this.x, iC11 == 1);
                }
                break;
            case 40011:
                BydLogHelper.logD("message : COM_DR_POWER_ON_CHANGE_MESSAGE " + aVar.c());
                int iC12 = aVar.c();
                if (e.e(aVar)) {
                    iC12 = ((h) mo7getP()).d() ? 1 : 2;
                }
                if (iC12 == 1 || iC12 == 2) {
                    l.f(this.R, iC12 == 1);
                }
                break;
            case 40012:
                BydLogHelper.logD("message : COM_DR_POWER_OFF_CHANGE_MESSAGE " + aVar.c());
                int iC13 = aVar.c();
                if (e.e(aVar)) {
                    iC13 = ((h) mo7getP()).c() ? 1 : 2;
                }
                if (iC13 == 1 || iC13 == 2) {
                    l.f(this.S, iC13 == 1);
                }
                break;
            case 40013:
                BydLogHelper.logD("message : DL_LOCK_RUN_CLOSE_DOOR_CHANGE_MESSAGE " + aVar.c());
                int iC14 = aVar.c();
                if (e.e(aVar)) {
                    iC14 = ((h) mo7getP()).v() ? 1 : 2;
                }
                if (iC14 == 1 || iC14 == 2) {
                    l.f(this.Y, iC14 == 1);
                }
                break;
            case 40014:
                BydLogHelper.logD("message : DL_LOCK_HIGH_SPEED_LOCK_CHANGE_MESSAGE " + aVar.c());
                int iC15 = aVar.c();
                if (e.e(aVar)) {
                    iC15 = ((h) mo7getP()).i() ? 1 : 2;
                }
                if (iC15 == 1 || iC15 == 2) {
                    l.f(this.W, iC15 == 1);
                }
                break;
            case 40015:
                BydLogHelper.logD("message : DL_LOCK_INTELLIGENT_UNLOCK_CHANGE_MESSAGE " + aVar.c());
                int iC16 = aVar.c();
                if (e.e(aVar)) {
                    iC16 = ((h) mo7getP()).l() ? 1 : 2;
                }
                if (iC16 == 1 || iC16 == 2) {
                    l.f(this.Z, iC16 == 1);
                }
                break;
            case 40016:
                BydLogHelper.logD("message : DL_LOCK_INTELLIGENT_LOCK_CHANGE_MESSAGE " + aVar.c());
                int iC17 = aVar.c();
                if (e.e(aVar)) {
                    iC17 = ((h) mo7getP()).j() ? 1 : 2;
                }
                if (iC17 == 1 || iC17 == 2) {
                    l.f(this.a0, iC17 == 1);
                }
                break;
            case 40017:
                BydLogHelper.logD("message : DL_LOCK_ELECTRIC_SLIDE_DOOR_CHANGE_MESSAGE " + aVar.c());
                int iC18 = aVar.c();
                if (e.e(aVar)) {
                    iC18 = ((h) mo7getP()).h() ? 1 : 2;
                }
                if (iC18 == 1 || iC18 == 2) {
                    l.f(this.l0, iC18 == 1);
                }
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i) {
        if (view.getId() == R.id.door_and_lock_sgbg) {
            ((h) mo7getP()).R(i);
            e.i(40002);
            j0(19);
            l0(this.z, N(this.d0, 0), true);
            return;
        }
        ((h) mo7getP()).X(i);
        e.i(40003);
        j0(20);
        l0(this.z, N(20, i != 0 ? 1 : 0), true);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            l.r(false, this.A);
            l.z(i, this.m, this.z, R.string.vehiclesettings_placehodlers, "%");
            e.i(40001);
            e.n(250011, this.c0);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        e.i(40001);
        j0(18);
        l0(this.z, N(this.d0, 0), true);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        e.i(40001);
        l.r(true, this.A);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        s.a(this.A, i, b.b.m.e.i.a.h(getClass().getName()));
        b.b.m.e.i.a.n(getClass().getName());
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        this.c0 = new a(Looper.myLooper());
    }
}
