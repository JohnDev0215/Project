package com.byd.carsettings.ui.fragment.carsettings;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.Key;
import b.b.a.h.k;
import b.b.a.h.l;
import b.b.g.q.d.i;
import b.b.g.q.d.q;
import b.b.g.r.s;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButton;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class CarDiBarFragment extends XFragment<b.b.g.n.h.c> implements View.OnClickListener, SegmentedButtonGroup.f, CompoundButton.OnCheckedChangeListener, b.b.o.b.a.a {
    public static float m = 0.5f;
    public static float n = 1.0f;
    public static int o = 0;
    public static int p = 360;
    public static int q = 2000;
    public DampingScrollView A;
    public BydSwitch B;
    public i D;
    public b.b.g.q.d.c E;
    public RelativeLayout F;
    public RelativeLayout G;
    public SegmentedButton H;
    public SegmentedButton I;
    public RelativeLayout J;
    public RelativeLayout K;
    public ObjectAnimator L;
    public TextView M;
    public TextView N;
    public ObjectAnimator O;
    public Dialog P;
    public Dialog Q;
    public Dialog R;
    public Dialog S;
    public ImageView T;
    public b.b.o.a.b.a U;
    public RelativeLayout V;
    public TextView W;
    public Context r;
    public RelativeLayout s;
    public ImageView t;
    public RelativeLayout u;
    public ImageView v;
    public RelativeLayout w;
    public ImageView x;
    public SegmentedButtonGroup y;
    public SegmentedButtonGroup z;

    public class a implements SegmentedButtonGroup.e {
        public a() {
        }

        @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.e
        public void s(View view, int i) {
            if (i >= 1) {
                CarDiBarFragment.this.m0();
            }
        }
    }

    public class b implements q.b {
        public b() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            CarDiBarFragment.this.S.dismiss();
            CarDiBarFragment.this.S = null;
        }
    }

    public class c implements q.b {
        public c() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            if (CarDiBarFragment.this.P != null) {
                CarDiBarFragment.this.P.dismiss();
                CarDiBarFragment.this.P = null;
            }
        }
    }

    public class d implements q.e {
        public d() {
        }

        @Override // b.b.g.q.d.q.e
        public void a() {
            if (CarDiBarFragment.this.P != null) {
                CarDiBarFragment.this.P.dismiss();
                CarDiBarFragment.this.P = null;
            }
        }
    }

    public class e implements q.b {
        public e() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            CarDiBarFragment.this.Q.dismiss();
            CarDiBarFragment.this.Q = null;
        }
    }

    public class f implements q.b {
        public f() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            CarDiBarFragment.this.R.dismiss();
            CarDiBarFragment.this.R = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.r = context;
        this.A = (DampingScrollView) this.f3717c.findViewById(R.id.nsw);
        Y(((b.b.g.n.h.c) mo7getP()).o());
        int iF = ((b.b.g.n.h.c) mo7getP()).f();
        boolean zM = ((b.b.g.n.h.c) mo7getP()).m();
        X(iF, ((b.b.g.n.h.c) mo7getP()).n() || zM);
        W(zM);
        V(((b.b.g.n.h.c) mo7getP()).l());
        EventBus.getDefault().register(this);
    }

    public final void T() {
        l.F(null, this.s, this.u, this.w);
        l.t(null, this.y);
        l.y(null, this.B);
    }

    public int U(int i, int i2) {
        if (this.U == null) {
            this.U = b.b.o.a.b.d.a("diBar");
        }
        return this.U.a(i, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void V(boolean z) {
        if (z) {
            this.w = (RelativeLayout) this.f3717c.findViewById(R.id.dibar_disinf_rl);
            this.x = (ImageView) this.f3717c.findViewById(R.id.dibar_disinf_iv);
            this.K = (RelativeLayout) this.f3717c.findViewById(R.id.dibar_disinf_container);
            this.W = (TextView) this.f3717c.findViewById(R.id.dibar_disinf_tv);
            l.K(true, this.K);
            if (((b.b.g.n.h.c) mo7getP()).g() == 5) {
                this.w.setAlpha(m);
            } else {
                l.F(this, this.w);
                d0(((b.b.g.n.h.c) mo7getP()).c() == 1);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void W(boolean z) {
        if (z) {
            this.J = (RelativeLayout) this.f3717c.findViewById(R.id.dibar_remember_poweroff_container);
            BydSwitch bydSwitch = (BydSwitch) this.f3717c.findViewById(R.id.dibar_remember_poweroff_sw);
            this.B = bydSwitch;
            l.e(this.J, bydSwitch, ((b.b.g.n.h.c) mo7getP()).h(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void X(int i, boolean z) {
        if (z) {
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.dibar_refrigerator_container);
            this.V = relativeLayout;
            l.K(true, relativeLayout);
        }
        if (i == 2 || i == 1) {
            this.G = (RelativeLayout) this.f3717c.findViewById(R.id.dibar_refrigerator);
            this.y = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.dibar_refrigerator_work_state_mode);
            this.z = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.dibar_refrigerator_work_state_mode_cold);
            this.H = (SegmentedButton) this.f3717c.findViewById(R.id.warm);
            this.I = (SegmentedButton) this.f3717c.findViewById(R.id.cold);
            g0(i);
            a0(((b.b.g.n.h.c) mo7getP()).e(), i);
            if (((b.b.g.n.h.c) mo7getP()).g() == 3) {
                f0();
            }
        }
    }

    public final void Y(boolean z) {
        if (z) {
            this.s = (RelativeLayout) this.f3717c.findViewById(R.id.dibar_tray_out_rl);
            this.u = (RelativeLayout) this.f3717c.findViewById(R.id.dibar_tray_back_rl);
            this.M = (TextView) this.f3717c.findViewById(R.id.dibar_tray_out_tv);
            this.N = (TextView) this.f3717c.findViewById(R.id.dibar_tray_back_tv);
            this.t = (ImageView) this.f3717c.findViewById(R.id.dibar_tray_out_iv);
            this.v = (ImageView) this.f3717c.findViewById(R.id.dibar_tray_back_iv);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.dibar_tray_container);
            this.F = relativeLayout;
            l.K(true, relativeLayout);
            l.F(this, this.s, this.u, this.w);
            u0(true);
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.h.c newP() {
        return new b.b.g.n.h.c();
    }

    public final void a0(int i, int i2) {
        if (i2 == 1) {
            this.y.setPositionWithOutCallback(i == 0 ? 0 : 3 - i, false);
        } else {
            if (i2 != 2) {
                return;
            }
            this.z.setPositionWithOutCallback(i, false);
        }
    }

    public final void b0() {
        this.r = null;
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_carsetting_rtl_dibar_layout;
    }

    public void c0() {
        ImageView imageView = (ImageView) this.f3717c.findViewById(R.id.dibar_image);
        this.T = imageView;
        l.j(imageView, U(0, 0), this.r);
    }

    public final void d0(boolean z) {
        if (z) {
            l.b(this.w, this.r, R.drawable.vehicle_selector_dialog_blue);
            this.W.setSelected(true);
            q0(this.x);
        } else {
            l.b(this.w, this.r, R.drawable.vehicle_selector_dialog_white);
            this.W.setSelected(false);
            s0(this.x);
        }
    }

    public final void e0() {
        s0(this.x);
        k.b(this.w);
    }

    public final void f0() {
        k.b(this.y);
        this.y.setOnMyClickListener(new a());
    }

    public final void g0(int i) {
        l.K(i == 2 || i == 1, this.G);
        if (i == 1) {
            l.K(true, this.y);
            l.K(false, this.z);
            l.t(this, this.y);
        } else if (i != 2) {
            l.K(false, this.y);
            l.K(false, this.z);
        } else {
            l.K(false, this.y);
            l.K(true, this.z);
            l.t(this, this.z);
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_dibar_layout;
    }

    public final void h0(int i) {
        if (i == 1) {
            l.b(this.s, this.r, R.drawable.vehicle_selector_dialog_blue);
            this.M.setSelected(true);
            t0(this.v, this.N);
            r0(this.t, this.M);
            l.b(this.u, this.r, R.drawable.vehicle_selector_dialog_white);
            this.N.setSelected(false);
            return;
        }
        if (i == 2) {
            l.b(this.u, this.r, R.drawable.vehicle_selector_dialog_blue);
            this.N.setSelected(true);
            t0(this.t, this.M);
            r0(this.v, this.N);
            l.b(this.s, this.r, R.drawable.vehicle_selector_dialog_white);
            this.M.setSelected(false);
            return;
        }
        if (i == 3) {
            t0(this.v, this.N);
            l.b(this.u, this.r, R.drawable.vehicle_selector_dialog_white);
            this.N.setSelected(false);
            t0(this.t, this.M);
            l.b(this.s, this.r, R.drawable.vehicle_selector_dialog_white);
            this.M.setSelected(false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void i0(int i) {
        if (i == 2 && ((b.b.g.n.h.c) mo7getP()).o()) {
            u0(false);
        }
    }

    public final void j0(String str) {
        str.hashCode();
        switch (str) {
            case "dibar_tray_unback_mode":
                p0(true);
                break;
            case "dibar_refrigerator_malfunction_mode":
                k0();
                break;
            case "dibar_refrigerator_open_mode":
                l0();
                break;
            case "dibar_refrigerator_tc_exception":
                m0();
                break;
            case "dibar_refrigerator_sensor_exception":
                n0();
                break;
        }
    }

    public final void k0() {
        Dialog dialog = this.S;
        if (dialog != null) {
            dialog.dismiss();
        } else {
            this.S = o0("dibar_refrigerator_open_mode", R.layout.carsetting_dialog_dibar_disinf, R.string.vehiclesettings_diba_disinfectings, R.string.vehiclesettings_diba_refrigerator_disinfection_error_hint, -1, -1, R.string.vehiclesettings_button_confirm, new b(), -1, null, null, null);
        }
    }

    public final void l0() {
        Dialog dialog = this.P;
        if (dialog != null) {
            dialog.dismiss();
        } else {
            this.P = o0("dibar_refrigerator_open_mode", R.layout.carsetting_dialog_dibar_disinf, R.string.vehiclesettings_diba_disinfectings, R.string.vehiclesettings_diba_refrigerator_door_no_close_hint, -1, -1, R.string.vehiclesettings_button_confirm, new c(), -1, null, new d(), null);
        }
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_carsetting_right_dibar_layout;
    }

    public final void m0() {
        Dialog dialog = this.Q;
        if (dialog != null) {
            dialog.dismiss();
        } else {
            this.Q = o0("dibar_refrigerator_tc_exception", R.layout.carsetting_dialog_dibar_disinf, -1, R.string.vehiclesettings_diba_refrigerator_hint, -1, -1, R.string.vehiclesettings_button_confirm, new e(), -1, null, null, null);
        }
    }

    public final void n0() {
        Dialog dialog = this.R;
        if (dialog != null) {
            dialog.dismiss();
        } else {
            this.R = o0("dibar_refrigerator_sensor_exception", R.layout.carsetting_dialog_dibar_disinf, -1, R.string.vehiclesettings_diba_sensor_hint, -1, -1, R.string.vehiclesettings_button_confirm, new f(), -1, null, null, null);
        }
    }

    public final Dialog o0(String str, int i, int i2, int i3, int i4, int i5, int i6, q.b bVar, int i7, q.a aVar, q.e eVar, q.d dVar) {
        i iVar = new i(this.r);
        this.D = iVar;
        iVar.x(str);
        return this.D.A(i, i2, i3, i4, i5, i6, 0, bVar, i7, aVar, eVar, dVar);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        for (String str : b.b.g.q.d.k.b()) {
            if (str.startsWith("dibar_")) {
                j0(str);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (l.a(this.B)) {
            ((b.b.g.n.h.c) mo7getP()).s(z ? 1 : 2);
            b.b.g.r.e.i(120003);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.dibar_disinf_rl) {
            if (((b.b.g.n.h.c) mo7getP()).d() == 1) {
                l0();
                return;
            }
            if (((b.b.g.n.h.c) mo7getP()).g() == 5) {
                d0(false);
                k0();
                return;
            } else if (((b.b.g.n.h.c) mo7getP()).c() == 1) {
                ((b.b.g.n.h.c) mo7getP()).q(2);
                b.b.g.r.e.i(120004);
                d0(false);
                return;
            } else {
                ((b.b.g.n.h.c) mo7getP()).q(1);
                b.b.g.r.e.i(120004);
                d0(true);
                return;
            }
        }
        if (id == R.id.dibar_tray_back_rl) {
            if (((b.b.g.n.h.c) mo7getP()).i() == 2) {
                p0(false);
                return;
            }
            ((b.b.g.n.h.c) mo7getP()).t(2);
            l.b(this.u, this.r, R.drawable.vehicle_selector_dialog_blue);
            this.N.setSelected(true);
            l.b(this.s, this.r, R.drawable.vehicle_selector_dialog_white);
            this.M.setSelected(false);
            b.b.g.r.e.i(120000);
            return;
        }
        if (id != R.id.dibar_tray_out_rl) {
            return;
        }
        if (((b.b.g.n.h.c) mo7getP()).i() == 2) {
            p0(false);
            return;
        }
        ((b.b.g.n.h.c) mo7getP()).t(1);
        l.b(this.s, this.r, R.drawable.vehicle_selector_dialog_blue);
        this.M.setSelected(true);
        l.b(this.u, this.r, R.drawable.vehicle_selector_dialog_white);
        this.N.setSelected(false);
        b.b.g.r.e.i(120000);
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        T();
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Dialog dialog = this.R;
        if (dialog != null) {
            dialog.dismiss();
            this.R = null;
        }
        Dialog dialog2 = this.Q;
        if (dialog2 != null) {
            dialog2.dismiss();
            this.Q = null;
        }
        Dialog dialog3 = this.P;
        if (dialog3 != null) {
            dialog3.dismiss();
            this.P = null;
        }
        Dialog dialog4 = this.S;
        if (dialog4 != null) {
            dialog4.dismiss();
            this.S = null;
        }
        b.b.g.q.d.c cVar = this.E;
        if (cVar != null) {
            cVar.l();
            this.E = null;
        }
        b0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        switch (aVar.h()) {
            case 120000:
                BydLogHelper.logD("message : DIBAR_ACTION_RUNNING_CHANGE_MESSAGE " + aVar.c());
                int iC = aVar.c();
                if (b.b.g.r.e.e(aVar) && (iC = ((b.b.g.n.h.c) mo7getP()).j()) == 0) {
                    h0(3);
                } else if (iC == 1 || iC == 2 || iC == 3) {
                    h0(iC);
                }
                break;
            case 120001:
                BydLogHelper.logD("message : DIBAR_ACTION_DONE_CHANGE_MESSAGE " + aVar.c());
                int iC2 = aVar.c();
                if (iC2 == 1 || iC2 == 2 || iC2 == 3) {
                    h0(3);
                }
                break;
            case 120002:
                BydLogHelper.logD("message : DIBAR_ACTION_WORK_MODE_CHANGE_MESSAGE " + aVar.c());
                int iC3 = aVar.c();
                if (!b.b.g.r.e.e(aVar)) {
                    a0(iC3, ((b.b.g.n.h.c) mo7getP()).f());
                } else {
                    a0(((b.b.g.n.h.c) mo7getP()).e(), ((b.b.g.n.h.c) mo7getP()).f());
                }
                break;
            case 120003:
                BydLogHelper.logD("message : DIBAR_ACTION_POWER_OFF_CHANGE_MESSAGE " + aVar.c());
                int iC4 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC4 = ((b.b.g.n.h.c) mo7getP()).h() ? 1 : 2;
                }
                if (iC4 == 1 || iC4 == 2) {
                    this.B.setChecked(iC4 == 1, false);
                }
                break;
            case 120004:
                BydLogHelper.logD("message : DIBAR_ACTION_DISINF_STATE_CHANGE_MESSAGE " + aVar.c());
                int iC5 = aVar.c();
                if (b.b.g.r.e.e(aVar) && (iC5 = ((b.b.g.n.h.c) mo7getP()).c()) == 0) {
                    k.c(this.w);
                    d0(false);
                }
                if (iC5 == 1 || iC5 == 2) {
                    k.c(this.w);
                    d0(iC5 == 1);
                }
                break;
            case 120005:
                BydLogHelper.logD("message : DIBAR_REFRIGERATOR_STATE_CHANGE_MESSAGE " + aVar.c());
                k.c(this.w);
                int iC6 = aVar.c();
                if (iC6 == 3) {
                    f0();
                    break;
                } else if (iC6 == 5) {
                    e0();
                    break;
                }
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i) {
        if (view.getId() == R.id.dibar_refrigerator_work_state_mode) {
            if (i == 2) {
                ((b.b.g.n.h.c) mo7getP()).r(1);
            } else if (i == 1) {
                if (((b.b.g.n.h.c) mo7getP()).g() == 4) {
                    n0();
                    this.y.setPositionWithOutCallback(((b.b.g.n.h.c) mo7getP()).e(), false);
                    return;
                }
                ((b.b.g.n.h.c) mo7getP()).r(2);
            } else if (i == 0) {
                ((b.b.g.n.h.c) mo7getP()).r(3);
            }
        } else if (view.getId() == R.id.dibar_refrigerator_work_state_mode_cold) {
            ((b.b.g.n.h.c) mo7getP()).r(i != 0 ? 1 : 3);
        }
        b.b.g.r.e.i(120002);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        i0(((b.b.g.n.h.c) mo7getP()).i());
    }

    public final void p0(boolean z) {
        if (this.E == null) {
            this.E = new b.b.g.q.d.c(this.r, "dibar_tray_unback_mode");
        }
        if (z) {
            this.E.z(R.layout.dialog_dibar_tray);
        } else {
            if (b.b.g.q.d.k.b().contains("dibar_tray_unback_mode")) {
                return;
            }
            this.E.z(R.layout.dialog_dibar_tray);
        }
    }

    public final void q0(ImageView imageView) {
        l.J(0, imageView);
        if (this.O == null) {
            this.O = ObjectAnimator.ofFloat(imageView, Key.ROTATION, o, p);
        }
        this.O.setTarget(imageView);
        this.O.setDuration(2000L);
        this.O.setRepeatCount(-1);
        this.O.start();
    }

    public final void r0(ImageView imageView, TextView textView) {
        ObjectAnimator objectAnimator = this.L;
        if (objectAnimator != null && objectAnimator.isRunning()) {
            this.L.pause();
        }
        l.J(0, imageView);
        if (this.L == null) {
            this.L = ObjectAnimator.ofFloat(imageView, Key.ROTATION, o, p);
        }
        this.L.setTarget(imageView);
        this.L.setDuration(q);
        this.L.setRepeatCount(-1);
        this.L.start();
    }

    public final void s0(ImageView imageView) {
        l.J(8, imageView);
        ObjectAnimator objectAnimator = this.O;
        if (objectAnimator == null || !objectAnimator.isRunning()) {
            return;
        }
        this.O.cancel();
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        s.a(this.A, i, b.b.m.e.i.a.h(getClass().getName()));
        b.b.m.e.i.a.n(getClass().getName());
    }

    public final void t0(ImageView imageView, TextView textView) {
        ObjectAnimator objectAnimator = this.L;
        if (objectAnimator != null && objectAnimator.isRunning()) {
            this.L.cancel();
        }
        l.J(8, imageView);
    }

    public final void u0(boolean z) {
        this.u.setAlpha(z ? n : m);
        this.s.setAlpha(z ? n : m);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        c0();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.j.b.b bVar) {
        Dialog dialog = this.R;
        if (dialog != null) {
            dialog.dismiss();
            this.R = null;
        }
        Dialog dialog2 = this.Q;
        if (dialog2 != null) {
            dialog2.dismiss();
            this.Q = null;
        }
        Dialog dialog3 = this.P;
        if (dialog3 != null) {
            dialog3.dismiss();
            this.P = null;
        }
        Dialog dialog4 = this.S;
        if (dialog4 != null) {
            dialog4.dismiss();
            this.S = null;
        }
        b.b.g.q.d.c cVar = this.E;
        if (cVar != null) {
            cVar.l();
            this.E = null;
        }
    }
}
