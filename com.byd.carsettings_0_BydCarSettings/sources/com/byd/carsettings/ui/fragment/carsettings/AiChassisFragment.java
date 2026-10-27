package com.byd.carsettings.ui.fragment.carsettings;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import b.b.a.h.k;
import b.b.a.h.l;
import b.b.g.n.q.m;
import b.b.g.q.d.i;
import b.b.g.q.d.q;
import b.b.g.r.e;
import b.b.g.r.s;
import b.b.o.a.b.d;
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
public class AiChassisFragment extends XFragment<b.b.g.n.h.a> implements SegmentedButtonGroup.f, CompoundButton.OnCheckedChangeListener, View.OnClickListener, b.b.o.b.a.a {
    public ImageView A;
    public int B = 0;
    public b.b.o.a.b.a D;
    public ImageView E;
    public SegmentedButtonGroup F;
    public RelativeLayout G;
    public TextView H;
    public DampingScrollView m;
    public SegmentedButtonGroup n;
    public SegmentedButtonGroup o;
    public SegmentedButtonGroup p;
    public BydSwitch q;
    public RelativeLayout r;
    public RelativeLayout s;
    public RelativeLayout t;
    public RelativeLayout u;
    public ImageView v;
    public RelativeLayout w;
    public Dialog x;
    public i y;
    public Context z;

    public class a implements q.b {
        public a() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AiChassisFragment.this.x.dismiss();
        }
    }

    public class b implements q.b {
        public b() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            AiChassisFragment.this.x.dismiss();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.m = (DampingScrollView) this.f3717c.findViewById(R.id.nsw);
        this.z = context;
        EventBus.getDefault().register(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void L(boolean z) {
        if (z) {
            this.F = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.intelligent_chassis_speeddown_segmentedbtn);
            this.v = (ImageView) this.f3717c.findViewById(R.id.intelligent_chassis_speeddown_icon);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.intelligent_chassis_speeddown_container);
            this.w = relativeLayout;
            l.v(relativeLayout, this.F, ((b.b.g.n.h.a) mo7getP()).d() - 1, this, this.v, this, true);
            l.F(this, this.v);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void M(boolean z) {
        if (z) {
            this.q = (BydSwitch) this.f3717c.findViewById(R.id.comfortable_stop_car_sw);
            this.u = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_stop_car_sw_container);
            ImageView imageView = (ImageView) this.f3717c.findViewById(R.id.intelligent_comfortable_stop_icon);
            this.E = imageView;
            l.F(this, imageView);
            l.e(this.u, this.q, ((b.b.g.n.h.a) mo7getP()).e(), this);
            e0();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void N(boolean z, boolean z2) {
        if (z || z2) {
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_selfup_out_container);
            this.G = relativeLayout;
            l.K(true, relativeLayout);
        }
        if (z) {
            this.n = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.comfortable_selfup_segmentedbtn);
            this.H = (TextView) this.f3717c.findViewById(R.id.comfortable_selfup_tv);
            int i = R.string.vehiclesettings_dynamic_suspension_c_mode;
            int iG = ((b.b.g.n.h.a) mo7getP()).g();
            if (iG == 2) {
                i = R.string.vehiclesettings_dynamic_suspension_a_mode;
            }
            if (iG == 1) {
                i = R.string.vehiclesettings_dynamic_suspension_p_mode;
            }
            l.B(this.H, this.z.getString(i));
            RelativeLayout relativeLayout2 = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_selfup_container);
            this.r = relativeLayout2;
            l.u(relativeLayout2, this.n, ((b.b.g.n.h.a) mo7getP()).h(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void O(boolean z) {
        if (z) {
            this.p = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.comfortable_stop_segmentedButtonGroup);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_stop_container);
            this.t = relativeLayout;
            l.u(relativeLayout, this.p, ((b.b.g.n.h.a) mo7getP()).c(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void P(boolean z) {
        if (z) {
            this.o = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.comfortable_turn_help);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.comfortable_turn_help_container);
            this.s = relativeLayout;
            l.u(relativeLayout, this.o, ((b.b.g.n.h.a) mo7getP()).i(), this);
            l.C(((b.b.g.n.h.a) mo7getP()).n(), this.o);
        }
    }

    public final void Q() {
        l.t(null, this.n, this.o, this.p);
        l.y(null, this.q);
    }

    public int R(int i, int i2) {
        if (this.D == null) {
            this.D = d.a("aiChassis");
        }
        return this.D.a(i, i2);
    }

    public final i S(String str) {
        if (this.y == null) {
            this.y = new i(this.z);
        }
        this.y.x(str);
        return this.y;
    }

    public void T(int i) {
        this.B = i;
        b.b.o.a.c.a.b(getClass().getSimpleName(), Integer.valueOf(this.B));
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.h.a newP() {
        return new b.b.g.n.h.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void V(b.b.a.c.a aVar) {
        int iC = aVar.c();
        if (!e.e(aVar)) {
            if (iC <= 1 && iC >= 0) {
                l.w(this.p, iC);
            }
            W(this.z, R(7, iC != 0 ? 1 : 0), true);
            return;
        }
        int iC2 = ((b.b.g.n.h.a) mo7getP()).c();
        if (iC2 <= 1 && iC2 >= 0) {
            l.w(this.p, iC2);
        }
        W(this.z, R(7, iC2 == 0 ? 0 : 1), this.B == 7);
    }

    public void W(Context context, int i, boolean z) {
        if (z) {
            l.j(this.A, i, context);
        }
    }

    public final void X(b.b.a.c.a aVar) {
        int iC = aVar.c();
        if (e.e(aVar) && (iC = m.p0().w()) == 0) {
            l.f(this.q, false);
        }
        if (iC != 0 && iC <= 3) {
            l.f(this.q, iC != 3);
        } else if (iC == 0) {
            l.f(this.q, false);
        }
        e0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Y(b.b.a.c.a aVar) {
        int iC = aVar.c();
        if (e.e(aVar)) {
            int iH = ((b.b.g.n.h.a) mo7getP()).h();
            l.w(this.n, iH);
            W(this.z, R(5, iH == 0 ? 0 : 1), this.B == 5);
        } else if (iC == 1 || iC == 2) {
            l.w(this.n, iC - 1);
            W(this.z, R(5, iC != 1 ? 1 : 0), true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Z(b.b.a.c.a aVar) {
        int iC = aVar.c();
        if (e.e(aVar)) {
            int i = ((b.b.g.n.h.a) mo7getP()).i();
            l.w(this.o, i);
            W(this.z, R(6, i == 1 ? 1 : 0), this.B == 6);
        } else {
            if (iC == 0 || iC >= 5) {
                return;
            }
            l.w(this.o, iC >= 3 ? 1 : 0);
            W(this.z, R(6, iC >= 3 ? 1 : 0), true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a0(b.b.a.c.a aVar) {
        int iC = aVar.c();
        if (e.e(aVar)) {
            iC = !((b.b.g.n.h.a) mo7getP()).n() ? 1 : 0;
        }
        if (iC == 0) {
            k.h(this.o);
        } else if (iC == 1) {
            k.e(this.o);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b0(b.b.a.c.a aVar) {
        int iC = aVar.c();
        if (!e.e(aVar)) {
            if (iC < 1 || iC > 3) {
                return;
            }
            l.w(this.F, iC - 1);
            W(this.z, R(34, iC), true);
            return;
        }
        int iD = ((b.b.g.n.h.a) mo7getP()).d();
        if (iD == 0) {
            iD = 1;
        }
        if (iD < 1 || iD > 3) {
            return;
        }
        l.w(this.F, iD - 1);
        W(this.z, R(34, iD), this.B == 34);
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_carsetting_rtl_intelligent_chassis_layout;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void c0() {
        this.A = (ImageView) this.f3717c.findViewById(R.id.intelligent_chassis_image);
        int iA = b.b.o.a.c.a.a(getClass().getSimpleName());
        if (iA == 0) {
            W(this.z, R(iA, 0), true);
            return;
        }
        if (iA == 34) {
            W(this.z, R(iA, ((b.b.g.n.h.a) mo7getP()).d()), true);
            return;
        }
        if (iA == 5) {
            W(this.z, R(iA, ((b.b.g.n.h.a) mo7getP()).h() != 0 ? 1 : 0), true);
        } else if (iA == 6) {
            W(this.z, R(iA, ((b.b.g.n.h.a) mo7getP()).i() != 0 ? 1 : 0), true);
        } else {
            if (iA != 7) {
                return;
            }
            W(this.z, R(iA, ((b.b.g.n.h.a) mo7getP()).c() != 0 ? 1 : 0), true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void d0(int i) {
        e.i(90005);
        ((b.b.g.n.h.a) mo7getP()).s(i);
        T(34);
        W(this.z, R(34, i), true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void e0() {
        BydSwitch bydSwitch = this.q;
        if (bydSwitch != null) {
            k.f(bydSwitch, ((b.b.g.n.h.a) mo7getP()).f() == 3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void f0(boolean z) {
        e.i(90003);
        ((b.b.g.n.h.a) mo7getP()).t(z ? 1 : 2);
        T(0);
        W(this.z, R(0, 0), true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void g0(int i) {
        e.i(90000);
        ((b.b.g.n.h.a) mo7getP()).u(i);
        T(5);
        W(this.z, R(5, i == 0 ? 0 : 1), true);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_intelligent_chassis_layout;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void h0(int i) {
        e.i(90002);
        ((b.b.g.n.h.a) mo7getP()).r(i == 0 ? 2 : 1);
        T(7);
        W(this.z, R(7, i == 0 ? 0 : 1), true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void i0(int i) {
        e.i(90001);
        ((b.b.g.n.h.a) mo7getP()).v(i == 0 ? 1 : 3);
        T(6);
        W(this.z, R(6, i == 0 ? 0 : 1), true);
    }

    public final void j0() {
        this.x = S("intelligent_chassis_com_stop").B(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_comfortable_parking, R.string.vehiclesettings_comfortable_parking_icon_description, -1, -1, R.string.vehiclesettings_button_confirm, new a(), -1, null);
    }

    public final void k0() {
        this.x = S("intelligent_chassis_speed_down").z(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_comfortable_control, R.string.vehiclesettings_comfortable_contro_description, -1, -1, R.string.vehiclesettings_button_confirm, 1, new b(), -1, null);
    }

    public final void l0(String str) {
        if ("intelligent_chassis_com_stop".equals(str)) {
            j0();
        } else if ("intelligent_chassis_speed_down".equals(str)) {
            k0();
        }
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_carsetting_right_intelligent_chassis_layout;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        for (String str : b.b.g.q.d.k.b()) {
            if (str.startsWith("intelligent_")) {
                l0(str);
            }
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getId() == R.id.comfortable_stop_car_sw && l.a(this.q)) {
            f0(z);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.intelligent_chassis_speeddown_icon) {
            k0();
        } else if (view.getId() == R.id.intelligent_comfortable_stop_icon) {
            j0();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        Q();
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Dialog dialog = this.x;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.x.dismiss();
        this.x = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        switch (aVar.h()) {
            case 90000:
                BydLogHelper.logD("message : AI_CHASSIS_DYNAMIC_SUSPENSION_CHANGE_MESSAGE " + aVar.c());
                Y(aVar);
                break;
            case 90001:
                BydLogHelper.logD("message : AI_CHASSIS_STEER_ASSIS_CHANGE_MESSAGE " + aVar.c());
                Z(aVar);
                break;
            case 90002:
                BydLogHelper.logD("message : AI_CHASSIS_BRAKE_PEDAL_CHANGE_MESSAGE " + aVar.c());
                V(aVar);
                break;
            case 90003:
                BydLogHelper.logD("message : AI_CHASSIS_COMFORTABT_STOP_CHANGE_MESSAGE " + aVar.c());
                X(aVar);
                break;
            case 90005:
                BydLogHelper.logD("message : AI_CHASSIS_SPEED_DOWN_CHANGE_MESSAGE " + aVar.c());
                b0(aVar);
                break;
            case 90007:
                BydLogHelper.logD("message : AI_CHASSIS_STEER_ASSIS_CAN_ADJUSTED_MESSAGE " + aVar.c());
                a0(aVar);
                break;
        }
    }

    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i) {
        switch (view.getId()) {
            case R.id.comfortable_selfup_segmentedbtn /* 2131296766 */:
                g0(i);
                break;
            case R.id.comfortable_stop_segmentedButtonGroup /* 2131296771 */:
                h0(i);
                break;
            case R.id.comfortable_turn_help /* 2131296773 */:
                i0(i);
                break;
            case R.id.intelligent_chassis_speeddown_segmentedbtn /* 2131297105 */:
                d0(i + 1);
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        s.a(this.m, i, b.b.m.e.i.a.h(getClass().getName()));
        b.b.m.e.i.a.n(getClass().getName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        boolean zJ = ((b.b.g.n.h.a) mo7getP()).j();
        N(((b.b.g.n.h.a) mo7getP()).m(), zJ);
        P(((b.b.g.n.h.a) mo7getP()).q());
        O(((b.b.g.n.h.a) mo7getP()).o());
        M(((b.b.g.n.h.a) mo7getP()).l());
        L(zJ);
        c0();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.j.b.b bVar) {
        Dialog dialog = this.x;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.x.dismiss();
        this.x = null;
    }
}
