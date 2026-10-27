package com.byd.carsettings.ui.fragment.carsettings;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import b.b.a.h.k;
import b.b.a.h.l;
import b.b.g.n.h.g;
import b.b.g.n.q.m;
import b.b.g.q.d.i;
import b.b.g.q.d.j;
import b.b.g.q.d.q;
import b.b.g.q.d.r;
import b.b.g.q.d.s;
import b.b.g.r.u;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;
import net.sqlcipher.database.SQLiteDatabase;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class ComfortDrFragment extends XFragment<g> implements CompoundButton.OnCheckedChangeListener, SegmentedButtonGroup.f, b.b.o.b.a.a, View.OnClickListener, r {
    public RelativeLayout A;
    public RelativeLayout B;
    public ImageView D;
    public LinearLayout E;
    public LinearLayout F;
    public LinearLayout G;
    public BydSwitch H;
    public RelativeLayout I;
    public int J = 0;
    public b.b.o.a.b.a K;
    public Dialog L;
    public Dialog M;
    public i N;
    public BydSwitch O;
    public s P;
    public TextView Q;
    public BydSwitch R;
    public Context m;
    public DampingScrollView n;
    public BydSwitch o;
    public BydSwitch p;
    public BydSwitch q;
    public BydSwitch r;
    public BydSwitch s;
    public BydSwitch t;
    public SegmentedButtonGroup u;
    public RelativeLayout v;
    public RelativeLayout w;
    public RelativeLayout x;
    public RelativeLayout y;
    public RelativeLayout z;

    public class a implements BydSwitch.a {
        public a() {
        }

        @Override // com.byd.base.view.BydSwitch.a
        public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
            ComfortDrFragment.this.O.playSoundEffect(0);
            if (motionEvent.getAction() != 1) {
                return;
            }
            if (!z) {
                m.p0().u2(1);
                b.b.g.r.e.i(50008);
                l.f(ComfortDrFragment.this.O, false);
                ComfortDrFragment.this.h0(38);
                ComfortDrFragment comfortDrFragment = ComfortDrFragment.this;
                comfortDrFragment.k0(comfortDrFragment.m, ComfortDrFragment.this.V(38, 0), true, true);
                return;
            }
            if (u.b(ComfortDrFragment.this.m, "wade_protect_risk_dialog_show", 2) == 2) {
                ComfortDrFragment.this.s0();
                return;
            }
            m.p0().u2(7);
            b.b.g.r.e.i(50008);
            l.f(ComfortDrFragment.this.O, true);
            ComfortDrFragment.this.h0(38);
            ComfortDrFragment comfortDrFragment2 = ComfortDrFragment.this;
            comfortDrFragment2.k0(comfortDrFragment2.m, ComfortDrFragment.this.V(38, 0), true, true);
        }
    }

    public class b implements q.b {
        public b() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            ComfortDrFragment.this.M.dismiss();
        }
    }

    public class c implements q.b {
        public c() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            ComfortDrFragment.this.L.dismiss();
        }
    }

    public class d implements BydSwitch.a {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.base.view.BydSwitch.a
        public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
            ComfortDrFragment.this.R.playSoundEffect(0);
            if (motionEvent.getAction() != 1) {
                return;
            }
            if (!z) {
                l.f(ComfortDrFragment.this.R, false);
                ((g) ComfortDrFragment.this.mo7getP()).I(2);
                b.b.g.r.e.i(50010);
            } else if (((g) ComfortDrFragment.this.mo7getP()).x()) {
                ComfortDrFragment.this.q0(1);
            } else {
                Toast.makeText(ComfortDrFragment.this.getContext(), ComfortDrFragment.this.m.getString(R.string.vehiclesettings_comfortable_sand_abs_mode_error), 1).show();
            }
        }
    }

    public class e implements q.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3081a;

        public e(int i) {
            this.f3081a = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.g.q.d.q.b
        public void a() {
            if (this.f3081a == 1) {
                l.f(ComfortDrFragment.this.R, true);
                ((g) ComfortDrFragment.this.mo7getP()).I(1);
                b.b.g.r.e.i(50010);
            }
            ComfortDrFragment.this.L.dismiss();
        }
    }

    public class f implements q.a {
        public f() {
        }

        @Override // b.b.g.q.d.q.a
        public void a() {
            l.f(ComfortDrFragment.this.R, false);
            ComfortDrFragment.this.L.dismiss();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.m = context;
        boolean zV = ((g) mo7getP()).v();
        d0(zV);
        boolean zS = ((g) mo7getP()).s();
        Y(zS, zS || zV);
        boolean zW = ((g) mo7getP()).w();
        e0(zW);
        boolean z = ((g) mo7getP()).z();
        f0(z, z || zW);
        boolean zY = ((g) mo7getP()).y();
        Z(zY);
        boolean zA = ((g) mo7getP()).A();
        g0(zA);
        boolean zU = ((g) mo7getP()).u();
        c0(zU, zU || zY || zA);
        boolean zT = ((g) mo7getP()).t();
        a0(zT);
        int iB = ((g) mo7getP()).B();
        b0(iB, zT || iB == 1 || iB == 2);
        this.n = (DampingScrollView) this.f3717c.findViewById(R.id.nsw);
        EventBus.getDefault().register(this);
    }

    public void R(int i) {
        if (i == 1) {
            m0();
        } else if (i == 2) {
            n0();
        }
    }

    public final boolean S() {
        if (m.p0().F1() == 1) {
            R(1);
            return false;
        }
        if (!T()) {
            return true;
        }
        R(2);
        return false;
    }

    public final boolean T() {
        int iD1 = m.p0().D1();
        return iD1 == 1 || iD1 == 2 || m.p0().E1() == 1 || m.p0().G1() == 1;
    }

    public final void U() {
        l.y(null, this.o, this.p, this.q, this.r, this.s, this.t);
    }

    public int V(int i, int i2) {
        if (this.K == null) {
            this.K = b.b.o.a.b.d.a("comDrive");
        }
        return this.K.a(i, i2);
    }

    public final i W(String str) {
        if (this.N == null) {
            this.N = new i(this.m);
        }
        this.N.x(str);
        return this.N;
    }

    public final int X(int i) {
        if (i == 1) {
            return R.string.vehiclesettings_comfortable_sand_abs_mode_system_errror;
        }
        if (i == 2) {
            return R.string.vehiclesettings_comfortable_sand_abs_mode_not_p_gear;
        }
        if (i == 3) {
            return R.string.vehiclesettings_comfortable_sand_abs_mode_error;
        }
        if (i != 4) {
            return 0;
        }
        return R.string.vehiclesettings_comfortable_sand_abs_mode_open_drive;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Y(boolean z, boolean z2) {
        if (z2) {
            LinearLayout linearLayout = (LinearLayout) this.f3717c.findViewById(R.id.comfortable_driving_safetybelt_rain_container);
            this.E = linearLayout;
            l.K(true, linearLayout);
        }
        if (z) {
            this.p = (BydSwitch) this.f3717c.findViewById(R.id.comfortable_driving_windshieldwiper);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_driving_windshieldwiper_contianer);
            this.w = relativeLayout;
            l.e(relativeLayout, this.p, ((g) mo7getP()).d(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Z(boolean z) {
        if (z) {
            this.q = (BydSwitch) this.f3717c.findViewById(R.id.comfortable_driving_seat);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_driving_seat_container);
            this.x = relativeLayout;
            l.e(relativeLayout, this.q, ((g) mo7getP()).e(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a0(boolean z) {
        if (z) {
            this.s = (BydSwitch) this.f3717c.findViewById(R.id.comfortable_driving_mirrorfold);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_driving_mirrorfold_container);
            this.z = relativeLayout;
            l.e(relativeLayout, this.s, ((g) mo7getP()).c(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b0(int i, boolean z) {
        if (z) {
            LinearLayout linearLayout = (LinearLayout) this.f3717c.findViewById(R.id.comfortable_driving_mirrorset_mirrorfold);
            this.G = linearLayout;
            l.K(true, linearLayout);
        }
        if (i == 1) {
            this.t = (BydSwitch) this.f3717c.findViewById(R.id.comfortable_driving_mirrorturn);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_driving_mirrorturn_container);
            this.A = relativeLayout;
            l.e(relativeLayout, this.t, ((g) mo7getP()).o() == 1, this);
        }
        if (i == 2) {
            this.u = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.comfortable_driving_mirrorset);
            RelativeLayout relativeLayout2 = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_driving_mirrorset_container);
            this.B = relativeLayout2;
            l.u(relativeLayout2, this.u, ((g) mo7getP()).n(), this);
        }
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_carsetting_rtl_comfortable_driving;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c0(boolean z, boolean z2) {
        if (z2) {
            LinearLayout linearLayout = (LinearLayout) this.f3717c.findViewById(R.id.comfortable_driving_seat_rain_container);
            this.F = linearLayout;
            l.K(true, linearLayout);
        }
        if (z) {
            this.H = (BydSwitch) this.f3717c.findViewById(R.id.comfortable_passenger_seat_sw);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_passenger_seat_container);
            this.I = relativeLayout;
            l.e(relativeLayout, this.H, ((g) mo7getP()).j() == 1, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void d0(boolean z) {
        if (z) {
            this.o = (BydSwitch) this.f3717c.findViewById(R.id.comfortable_driving_safetybelt);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_driving_safetybelt_container);
            this.v = relativeLayout;
            l.e(relativeLayout, this.o, ((g) mo7getP()).h(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void e0(boolean z) {
        if (z) {
            this.R = (BydSwitch) this.f3717c.findViewById(R.id.comfortable_sand_abs_mode_sw);
            ImageView imageView = (ImageView) this.f3717c.findViewById(R.id.comfortable_sand_abs_mode_icon);
            l.e((RelativeLayout) this.f3717c.findViewById(R.id.comfortable_sand_abs_mode_container), this.R, ((g) mo7getP()).q() == 2, this);
            k.d(((g) mo7getP()).x(), this.R);
            l.F(this, imageView);
            this.R.setOnInterceptTouchListener(new d());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void f0(boolean z, boolean z2) {
        if (z2) {
            l.K(true, (LinearLayout) this.f3717c.findViewById(R.id.comfortable_wading_protect_outside_container));
        }
        if (z) {
            this.O = (BydSwitch) this.f3717c.findViewById(R.id.comfortable_wading_protect_sw);
            ImageView imageView = (ImageView) this.f3717c.findViewById(R.id.comfortable_wading_protect_icon);
            l.e((RelativeLayout) this.f3717c.findViewById(R.id.comfortable_wading_protect_container), this.O, ((g) mo7getP()).g() == 7, this);
            l.F(this, imageView);
            this.O.setOnInterceptTouchListener(new a());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void g0(boolean z) {
        if (z) {
            this.r = (BydSwitch) this.f3717c.findViewById(R.id.comfortable_driving_steeringwheel);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_driving_steeringwheel_container);
            this.y = relativeLayout;
            l.e(relativeLayout, this.r, ((g) mo7getP()).r(), this);
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_comfortable_driving;
    }

    public void h0(int i) {
        this.J = i;
        b.b.o.a.c.a.b(getClass().getSimpleName(), Integer.valueOf(this.J));
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
    public g newP() {
        return new g();
    }

    public void j0(Context context, int i, boolean z) {
        k0(context, i, z, false);
    }

    public void k0(Context context, int i, boolean z, boolean z2) {
        if (z) {
            l.j(this.D, i, context);
        }
        if (z2) {
            l.B(this.Q, context.getString(R.string.vehiclesettings_comfortable_wading_protection_max_height) + context.getString(R.string.vehiclesettings_comfortable_wading_protection_premit_max_value));
        }
        l.J(z2 ? 0 : 8, this.Q);
    }

    public void l0() {
        this.D = (ImageView) this.f3717c.findViewById(R.id.comfortable_driving_image);
        this.Q = (TextView) this.f3717c.findViewById(R.id.comfortable_driving_tv);
        int iA = b.b.o.a.c.a.a(getClass().getSimpleName());
        k0(this.m, V(iA, 0), true, iA == 38);
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_carsetting_right_comfortable_driving;
    }

    public final void m0() {
        s sVar = this.P;
        if (sVar != null) {
            sVar.l();
            this.P.x();
        }
        this.M = W("comfortable_wading_protect_car_state_exception_dialog").B(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_comfortable_wading_protection_enter_fail, R.string.vehiclesettings_comfortable_wading_protection_vehicle_state, -1, -1, R.string.vehiclesettings_button_confirm, new b(), -1, null);
    }

    public final void n0() {
        Dialog dialog = this.M;
        if (dialog != null) {
            dialog.dismiss();
            b.b.g.q.d.k.e("comfortable_wading_protect_car_state_exception_dialog");
        }
        if (this.P == null) {
            this.P = new s(this.m);
        }
        this.P.z();
    }

    public final void o0() {
        Toast.makeText(this.m, X(4), 1).show();
        l.f(this.R, true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        for (String str : b.b.g.q.d.k.b()) {
            if (str.startsWith("comfortable_")) {
                switch (str) {
                    case "comfortable_wading_protect_read_dialog":
                        s0();
                        break;
                    case "comfortable_wading_protect_car_state_exception_item_dialog":
                        n0();
                        break;
                    case "comfortable_wading_sand_abs_icon_dialog":
                        q0(2);
                        break;
                    case "comfortable_wading_protect_dialog":
                        r0();
                        break;
                    case "comfortable_wading_sand_abs_dialog":
                        q0(1);
                        break;
                    case "comfortable_wading_protect_car_state_exception_dialog":
                        m0();
                        break;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.comfortable_driving_mirrorfold /* 2131296738 */:
                if (l.a(this.s)) {
                    ((g) mo7getP()).C(z ? 1 : 2);
                    b.b.g.r.e.i(50004);
                    h0(11);
                    j0(this.m, V(11, 0), true);
                    break;
                }
                break;
            case R.id.comfortable_driving_mirrorturn /* 2131296743 */:
                if (l.a(this.t)) {
                    ((g) mo7getP()).H(z ? 1 : 2);
                    b.b.g.r.e.i(50005);
                    h0(12);
                    j0(this.m, V(12, 0), true);
                    break;
                }
                break;
            case R.id.comfortable_driving_safetybelt /* 2131296747 */:
                if (l.a(this.o)) {
                    ((g) mo7getP()).F(z ? 1 : 2);
                    b.b.g.r.e.i(SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH);
                    h0(23);
                    j0(this.m, V(23, 0), true);
                    break;
                }
                break;
            case R.id.comfortable_driving_seat /* 2131296750 */:
                if (l.a(this.q)) {
                    ((g) mo7getP()).E(z ? 1 : 2);
                    b.b.g.r.e.i(50002);
                    h0(9);
                    j0(this.m, V(9, 0), true);
                    break;
                }
                break;
            case R.id.comfortable_driving_steeringwheel /* 2131296753 */:
                if (l.a(this.r)) {
                    ((g) mo7getP()).J(z ? 1 : 2);
                    b.b.g.r.e.i(50003);
                    h0(10);
                    j0(this.m, V(10, 0), true);
                    break;
                }
                break;
            case R.id.comfortable_driving_windshieldwiper /* 2131296756 */:
                if (l.a(this.p)) {
                    ((g) mo7getP()).D(z ? 1 : 2);
                    b.b.g.r.e.i(50001);
                    h0(8);
                    j0(this.m, V(8, 0), true);
                    break;
                }
                break;
            case R.id.comfortable_passenger_seat_sw /* 2131296759 */:
                if (l.a(this.H)) {
                    ((g) mo7getP()).G(z ? 1 : 2);
                    b.b.g.r.e.i(50007);
                    h0(22);
                    j0(this.m, V(22, 0), true);
                    break;
                }
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.comfortable_wading_protect_icon) {
            r0();
        } else if (view.getId() == R.id.comfortable_sand_abs_mode_icon) {
            q0(2);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        U();
        super.onDestroyView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        int iJ;
        switch (aVar.h()) {
            case SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH /* 50000 */:
                BydLogHelper.logD("message : COM_DR_SEATBELT_CHANGE_MESSAGE " + aVar.c());
                int iC = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC = !((g) mo7getP()).h() ? 1 : 0;
                }
                l.f(this.o, iC == 0);
                break;
            case 50001:
                BydLogHelper.logD("message : COM_DR_RAIN_WIPER_CHANGE_MESSAGE " + aVar.c());
                int iC2 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC2 = ((g) mo7getP()).d() ? 1 : 2;
                }
                if (iC2 == 1 || iC2 == 2) {
                    l.f(this.p, iC2 == 1);
                }
                break;
            case 50002:
                BydLogHelper.logD("message : COM_DR_SEAT_RETURN_CHANGE_MESSAGE " + aVar.c());
                int iF = ((g) mo7getP()).f();
                if (iF == 1 || iF == 2) {
                    l.g(this.q, iF == 1, false);
                }
                break;
            case 50003:
                BydLogHelper.logD("message : COM_DR_STEER_POSITION_CHANGE_MESSAGE " + aVar.c());
                int iC3 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC3 = ((g) mo7getP()).r() ? 1 : 2;
                }
                if (iC3 == 1 || iC3 == 2) {
                    l.g(this.r, iC3 == 1, false);
                }
                break;
            case 50004:
                BydLogHelper.logD("message : COM_DR_MIRROR_FOLLOWUP_CHANGE_MESSAGE " + aVar.c());
                int iC4 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC4 = ((g) mo7getP()).c() ? 1 : 2;
                }
                if (iC4 == 1 || iC4 == 2) {
                    l.g(this.s, iC4 == 1, false);
                }
                break;
            case 50005:
                BydLogHelper.logD("message : COM_DR_MIRROR_FLIP_CHANGE_MESSAGE " + aVar.c());
                int iO = ((g) mo7getP()).o();
                if (b.b.g.r.e.e(aVar)) {
                    iO = ((g) mo7getP()).o() == 1 ? 1 : 2;
                }
                if ((iO == 1 || iO == 2) && ((g) mo7getP()).B() == 1) {
                    l.g(this.t, iO == 1, false);
                }
                break;
            case 50006:
                BydLogHelper.logD("message : COM_DR_MIRROR_FLIP_SET_CHANGE_MESSAGE " + aVar.c());
                int iC5 = aVar.c();
                if (b.b.g.r.e.e(aVar) && (iC5 = ((g) mo7getP()).n()) == 0) {
                    l.w(this.u, 0);
                } else if (!((g) mo7getP()).m()) {
                    l.w(this.u, 0);
                } else if (iC5 == 1 || iC5 == 2) {
                    l.w(this.u, iC5);
                } else {
                    l.w(this.u, 0);
                }
                break;
            case 50007:
                BydLogHelper.logD("message : COM_DR_REAR_SEAT_WELCOME_CHANGE_MESSAGE " + aVar.c());
                int iC6 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iJ = ((g) mo7getP()).j();
                    if (iJ == 0) {
                        iJ = 2;
                    }
                } else if (iC6 != 0) {
                    iJ = ((g) mo7getP()).j();
                }
                if (iJ == 1 || iJ == 2) {
                    l.f(this.H, iJ == 1);
                }
                break;
            case 50008:
                BydLogHelper.logD("message : COM_DR_WADE_PROTECT_CHANGE_MESSAGE " + aVar.c());
                int iC7 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC7 = ((g) mo7getP()).g();
                }
                l.f(this.O, iC7 == 7);
                break;
            case 50009:
                BydLogHelper.logD("message : COM_DR_WADE_PROTECT_FAILED_CHANGE_MESSAGE " + aVar.c());
                if (!S()) {
                    l.f(this.O, false);
                }
                break;
            case 50010:
                BydLogHelper.logD("message : COM_DR_SAND_MODE_ABS_CHANGE_MESSAGE " + aVar.c());
                int iC8 = aVar.c();
                if (b.b.g.r.e.e(aVar) && (iC8 = ((g) mo7getP()).q()) == 0) {
                    iC8 = 1;
                }
                if (iC8 == 2 || iC8 == 1) {
                    l.f(this.R, iC8 == 2);
                }
                break;
            case 50011:
                BydLogHelper.logD("message : COM_DR_SAND_MODE_ABS_DRIVE_CHANGE_MESSAGE " + aVar.c());
                if (!((g) mo7getP()).x()) {
                    l.f(this.R, false);
                }
                k.d(((g) mo7getP()).x(), this.R);
                break;
            case 50012:
                BydLogHelper.logD("message : COM_DR_SAND_MODE_ABS_DRIVE_CHANGE_MESSAGE " + aVar.c());
                p0(aVar.c());
                break;
            case 50013:
                BydLogHelper.logD("message : COM_DR_SAND_MODE_ENTER_ABS_CHANGE_MESSAGE " + aVar.c());
                o0();
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i) {
        if (i == 0) {
            ((g) mo7getP()).H(2);
        } else if (i == 1) {
            ((g) mo7getP()).H(i);
        } else {
            ((g) mo7getP()).H(3);
        }
        b.b.g.r.e.i(50006);
        h0(12);
        j0(this.m, V(12, 0), true);
    }

    public final void p0(int i) {
        if (i < 1 || i > 3) {
            return;
        }
        Toast.makeText(this.m, X(i), 1).show();
        l.f(this.R, false);
    }

    @Override // b.b.g.q.d.r
    public void q(boolean z) {
        BydSwitch bydSwitch = this.O;
        if (bydSwitch != null) {
            l.f(bydSwitch, z);
            k0(this.m, V(38, 0), true, true);
        }
    }

    public final void q0(int i) {
        this.L = W(i == 1 ? "comfortable_wading_sand_abs_dialog" : "comfortable_wading_sand_abs_icon_dialog").B(b.b.o.b.b.a.c() == 2 ? R.layout.carsetting_right_dialog_withtime : R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_comfortable_sand_abs_mode, R.string.vehiclesettings_comfortable_sand_abs_mode_tips, -1, -1, i == 1 ? R.string.vehiclesettings_comfortable_sand_abs_mode_open_bttton : R.string.vehiclesettings_button_confirm, new e(i), i == 1 ? R.string.vehiclesettings_button_cancel : -1, i == 1 ? new f() : null);
    }

    public final void r0() {
        this.L = W("comfortable_wading_protect_dialog").B(R.layout.wade_protect_dialog_details, R.string.vehiclesettings_comfortable_wading_protection, R.string.vehiclesettings_comfortable_wading_protection_up_tips, R.string.vehiclesettings_comfortable_wading_protection_down_tips, -1, R.string.vehiclesettings_button_confirm, new c(), -1, null);
    }

    public final void s0() {
        j jVar = new j(this.m);
        jVar.B();
        jVar.A(this);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        b.b.g.r.s.a(this.n, i, b.b.m.e.i.a.h(getClass().getName()));
        b.b.m.e.i.a.n(getClass().getName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        l0();
        l.t(((g) mo7getP()).B() == 2 ? this : null, this.u);
    }
}
