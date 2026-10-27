package com.byd.carsettings.ui.fragment.carsettings;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import b.b.g.n.q.t;
import b.b.g.n.q.u;
import b.b.g.q.d.q;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.view.CountDownTimerButton;
import com.byd.dipilot.viewmodel.AdasViewModel;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class CockpitPerFragment extends XFragment<b.b.g.n.h.f> implements View.OnClickListener, SegmentedButtonGroup.f, b.b.o.b.a.a {
    public static b.b.g.k.b m;
    public View G;
    public BydSwitch H;
    public int I;
    public SegmentedButtonGroup J;
    public RelativeLayout K;
    public ImageView L;
    public boolean M;
    public boolean N;
    public b.b.o.a.b.a O;
    public LinearLayout P;
    public RelativeLayout Q;
    public ImageView R;
    public BydSwitch S;
    public Dialog T;
    public b.b.g.q.d.i U;
    public ScrollView n;
    public BydSwitch o;
    public TextView p;
    public ImageView q;
    public ImageView r;
    public TextView s;
    public BydSwitch t;
    public ImageView u;
    public TextView v;
    public BydSwitch w;
    public Context y;
    public LinearLayout z;
    public boolean x = false;
    public int A = 0;
    public int B = 0;
    public int D = 0;
    public int E = 0;
    public int F = 0;

    public class a implements q.c {
        public a() {
        }

        @Override // b.b.g.q.d.q.c
        public void a() {
            b.b.g.q.d.k.e("cockpitper_driver_detection_assist_countdown");
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }

        @Override // b.b.g.q.d.q.c
        public void b() {
            b.b.g.q.d.k.e("cockpitper_driver_detection_assist_countdown");
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }
    }

    public class b implements q.b {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.g.q.d.q.b
        public void a() {
            ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).t(0, CockpitPerFragment.this.D);
            CockpitPerFragment.this.t.setChecked(false, false);
            CockpitPerFragment.this.M = false;
            if (!CockpitPerFragment.this.w.isChecked()) {
                CockpitPerFragment.this.o.setChecked(false, false);
                ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).s(0, CockpitPerFragment.this.A);
                b.b.a.h.l.K(false, CockpitPerFragment.this.z);
            }
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }
    }

    public class c implements q.c {
        public c() {
        }

        @Override // b.b.g.q.d.q.c
        public void a() {
            b.b.g.q.d.k.e("cockpitper_driver_fatigue_warning_countdown");
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }

        @Override // b.b.g.q.d.q.c
        public void b() {
            b.b.g.q.d.k.e("cockpitper_driver_fatigue_warning_countdown");
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }
    }

    public class d implements q.b {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.g.q.d.q.b
        public void a() {
            CockpitPerFragment.this.w.setChecked(false, false);
            ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).n(0, CockpitPerFragment.this.E);
            CockpitPerFragment.this.N = false;
            if (!CockpitPerFragment.this.t.isChecked()) {
                CockpitPerFragment.this.o.setChecked(false, false);
                ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).s(0, CockpitPerFragment.this.A);
                b.b.a.h.l.K(false, CockpitPerFragment.this.z);
            }
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }
    }

    public class e implements q.c {
        public e() {
        }

        @Override // b.b.g.q.d.q.c
        public void a() {
            b.b.g.q.d.k.e("cockpitper_driver_distraction_reminder_countdown");
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }

        @Override // b.b.g.q.d.q.c
        public void b() {
            b.b.g.q.d.k.e("cockpitper_driver_distraction_reminder_countdown");
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }
    }

    public class f implements q.d {
        public f() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.g.q.d.q.d
        public void a() {
            CockpitPerFragment.this.J.setPositionWithOutCallback(((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).d() == 3 ? 1 : 0, false);
        }
    }

    public class g implements q.b {
        public g() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.g.q.d.q.b
        public void a() {
            CockpitPerFragment.this.H.setChecked(false, false);
            ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).q(2);
            CockpitPerFragment.this.O0(2);
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }
    }

    public class h implements q.c {
        public h() {
        }

        @Override // b.b.g.q.d.q.c
        public void a() {
            b.b.g.q.d.k.e("cockpitper_cpd_confirm_cancel");
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }

        @Override // b.b.g.q.d.q.c
        public void b() {
            b.b.g.q.d.k.e("cockpitper_cpd_confirm_cancel");
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }
    }

    public class i implements q.b {
        public i() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.g.q.d.q.b
        public void a() {
            CockpitPerFragment.this.H.setChecked(false, false);
            ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).q(2);
            CockpitPerFragment.this.O0(2);
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }
    }

    public class j implements q.c {
        public j() {
        }

        @Override // b.b.g.q.d.q.c
        public void a() {
            b.b.g.q.d.k.e("cockpitper_cpd_close_confirm");
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }

        @Override // b.b.g.q.d.q.c
        public void b() {
            b.b.g.q.d.k.e("cockpitper_cpd_close_confirm");
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }
    }

    public class k implements BydSwitch.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3065a;

        public k(Context context) {
            this.f3065a = context;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.base.view.BydSwitch.a
        public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
            CockpitPerFragment.this.o.playSoundEffect(0);
            if (motionEvent.getAction() == 1 && CockpitPerFragment.this.x) {
                CockpitPerFragment.this.u0(0);
                CockpitPerFragment cockpitPerFragment = CockpitPerFragment.this;
                cockpitPerFragment.x0(this.f3065a, cockpitPerFragment.e0(0, 0), true);
                if (!z) {
                    if (CockpitPerFragment.this.t != null) {
                        CockpitPerFragment cockpitPerFragment2 = CockpitPerFragment.this;
                        cockpitPerFragment2.M = cockpitPerFragment2.t.isChecked();
                    }
                    if (CockpitPerFragment.this.w != null) {
                        CockpitPerFragment cockpitPerFragment3 = CockpitPerFragment.this;
                        cockpitPerFragment3.N = cockpitPerFragment3.w.isChecked();
                    }
                    CockpitPerFragment.this.J0();
                    return;
                }
                ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).s(1, CockpitPerFragment.this.A);
                CockpitPerFragment.this.o.setChecked(true, false);
                if (CockpitPerFragment.this.M || CockpitPerFragment.this.N) {
                    ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).t(CockpitPerFragment.this.M ? 1 : 0, CockpitPerFragment.this.D);
                    CockpitPerFragment.this.t.setChecked(CockpitPerFragment.this.M, false);
                    ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).n(CockpitPerFragment.this.N ? 1 : 0, CockpitPerFragment.this.E);
                    CockpitPerFragment.this.w.setChecked(CockpitPerFragment.this.N, false);
                } else {
                    ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).t(1, CockpitPerFragment.this.D);
                    CockpitPerFragment.this.t.setChecked(true, false);
                    ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).n(1, CockpitPerFragment.this.E);
                    CockpitPerFragment.this.w.setChecked(true, false);
                }
                b.b.a.h.l.K(true, CockpitPerFragment.this.z);
            }
        }
    }

    public class l implements BydSwitch.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3067a;

        public l(Context context) {
            this.f3067a = context;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.base.view.BydSwitch.a
        public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
            CockpitPerFragment.this.t.playSoundEffect(0);
            if (motionEvent.getAction() == 1 && CockpitPerFragment.this.x) {
                CockpitPerFragment.this.u0(0);
                CockpitPerFragment cockpitPerFragment = CockpitPerFragment.this;
                cockpitPerFragment.x0(this.f3067a, cockpitPerFragment.e0(0, 0), true);
                if (!z) {
                    CockpitPerFragment.this.N0();
                } else {
                    ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).t(1, CockpitPerFragment.this.D);
                    CockpitPerFragment.this.t.setChecked(true, false);
                }
            }
        }
    }

    public class m implements BydSwitch.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3069a;

        public m(Context context) {
            this.f3069a = context;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.base.view.BydSwitch.a
        public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
            CockpitPerFragment.this.w.playSoundEffect(0);
            if (motionEvent.getAction() == 1 && CockpitPerFragment.this.x) {
                CockpitPerFragment.this.u0(0);
                CockpitPerFragment cockpitPerFragment = CockpitPerFragment.this;
                cockpitPerFragment.x0(this.f3069a, cockpitPerFragment.e0(0, 0), true);
                if (!z) {
                    CockpitPerFragment.this.L0();
                } else {
                    ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).n(1, CockpitPerFragment.this.E);
                    CockpitPerFragment.this.w.setChecked(true, false);
                }
            }
        }
    }

    public class n implements BydSwitch.a {
        public n() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.base.view.BydSwitch.a
        public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
            CockpitPerFragment.this.S.playSoundEffect(0);
            if (motionEvent.getAction() != 1) {
                return;
            }
            if (z) {
                b.b.g.r.e.i(241008);
                ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).o(1);
                CockpitPerFragment.this.S.setChecked(true, false);
            } else {
                b.b.g.r.e.h(241008);
                CockpitPerFragment.this.S.setChecked(false, false);
                CockpitPerFragment.this.G0();
            }
        }
    }

    public class o implements q.d {
        public o() {
        }

        @Override // b.b.g.q.d.q.d
        public void a() {
            b.b.g.r.e.i(241008);
            CockpitPerFragment.this.S.setChecked(true, false);
        }
    }

    public class p implements q.b {
        public p() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            CockpitPerFragment.this.T.dismiss();
        }
    }

    public class q implements q.b {
        public q() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            CockpitPerFragment.this.T.dismiss();
        }
    }

    public class r implements q.b {
        public r() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }
    }

    public class s implements q.b {
        public s() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.g.q.d.q.b
        public void a() {
            CockpitPerFragment.this.o.setChecked(false, false);
            ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).s(0, CockpitPerFragment.this.A);
            CockpitPerFragment.this.t.setChecked(false, false);
            ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).t(0, CockpitPerFragment.this.D);
            CockpitPerFragment.this.w.setChecked(false, false);
            ((b.b.g.n.h.f) CockpitPerFragment.this.mo7getP()).n(0, CockpitPerFragment.this.E);
            b.b.a.h.l.K(false, CockpitPerFragment.this.z);
            if (CockpitPerFragment.this.T != null) {
                CockpitPerFragment.this.T.dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j0() {
        Dialog dialog = this.T;
        if (dialog != null) {
            dialog.dismiss();
        }
        ((b.b.g.n.h.f) mo7getP()).q(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void l0() {
        Dialog dialog = this.T;
        if (dialog != null) {
            dialog.dismiss();
        }
        this.J.setPositionWithOutCallback(((b.b.g.n.h.f) mo7getP()).d() == 3 ? 1 : 0, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void n0() {
        Dialog dialog = this.T;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void p0() {
        Dialog dialog = this.T;
        if (dialog != null) {
            dialog.dismiss();
            ((b.b.g.n.h.f) mo7getP()).o(2);
            b.b.g.r.e.i(241008);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: q0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void r0() {
        Dialog dialog = this.T;
        if (dialog != null) {
            dialog.dismiss();
            b.b.g.r.e.i(241008);
            this.S.setChecked(true, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: s0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void t0() {
        Dialog dialog = this.T;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.n = (ScrollView) this.f3717c.findViewById(R.id.nsw);
        this.o = (BydSwitch) this.f3717c.findViewById(R.id.cockpit_perception_driver_detection_sys_sw);
        this.p = (TextView) this.f3717c.findViewById(R.id.driver_detection_sys_tv);
        this.q = (ImageView) this.f3717c.findViewById(R.id.driver_detection_sys_img);
        this.r = (ImageView) this.f3717c.findViewById(R.id.driver_tired_tips_img);
        this.s = (TextView) this.f3717c.findViewById(R.id.driver_tired_tips_tv);
        this.t = (BydSwitch) this.f3717c.findViewById(R.id.cockpit_perception_driver_tired_tips_sw);
        this.u = (ImageView) this.f3717c.findViewById(R.id.driver_distraction_monitor_img);
        this.v = (TextView) this.f3717c.findViewById(R.id.driver_distraction_monitor_tv);
        this.w = (BydSwitch) this.f3717c.findViewById(R.id.cockpit_perception_distraction_monitor_sw);
        this.z = (LinearLayout) this.f3717c.findViewById(R.id.cockpit_set_container);
        this.P = (LinearLayout) this.f3717c.findViewById(R.id.dms_out_container);
        this.G = this.f3717c.findViewById(R.id.cl_cpd);
        BydSwitch bydSwitch = (BydSwitch) this.f3717c.findViewById(R.id.cpd_btn_switch);
        this.H = bydSwitch;
        bydSwitch.setOnInterceptTouchListener(new BydSwitch.a() { // from class: b.b.g.q.e.a.h
            @Override // com.byd.base.view.BydSwitch.a
            public final void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
                this.f1508a.A0(view, motionEvent, z);
            }
        });
        SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.sbg_cpd);
        this.J = segmentedButtonGroup;
        segmentedButtonGroup.setOnPositionChangedListener(new SegmentedButtonGroup.f() { // from class: b.b.g.q.e.a.i
            @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
            public final void onPositionChanged(View view, View view2, int i2) {
                this.f1509a.w0(view, view2, i2);
            }
        });
        this.f3717c.findViewById(R.id.iv_cpd_notice).setOnClickListener(new View.OnClickListener() { // from class: b.b.g.q.e.a.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f1502a.F0(view);
            }
        });
        this.K = (RelativeLayout) this.f3717c.findViewById(R.id.dms_container);
        this.L = (ImageView) this.f3717c.findViewById(R.id.cockpit_image);
        this.Q = (RelativeLayout) this.f3717c.findViewById(R.id.cockpit_mobile_monitoring_system);
        this.R = (ImageView) this.f3717c.findViewById(R.id.cockpit_mobile_monitoring_system_icon);
        this.S = (BydSwitch) this.f3717c.findViewById(R.id.cockpit_mobile_monitoring_system_sw);
        this.o.setOnInterceptTouchListener(new k(context));
        this.t.setOnInterceptTouchListener(new l(context));
        this.w.setOnInterceptTouchListener(new m(context));
        b.b.a.h.l.F(this, this.q, this.r, this.u);
        this.y = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void A0(View view, MotionEvent motionEvent, boolean z) {
        this.H.playSoundEffect(0);
        if (motionEvent.getAction() != 1) {
            return;
        }
        u0(35);
        x0(this.y, e0(35, 0), true);
        b.b.g.n.h.f fVar = (b.b.g.n.h.f) mo7getP();
        BydLogHelper.logD(this.f3715a, "setSwitchChange  check =" + z);
        if (z) {
            fVar.q(1);
            this.H.setChecked(true, false);
            O0(1);
        } else if (u.I().p() == 1) {
            C0();
        } else {
            D0();
        }
    }

    public final void B0(String str) {
        long tempDuration;
        tempDuration = CountDownTimerButton.getTempDuration();
        str.hashCode();
        switch (str) {
            case "cockpitper_driver_fatigue_warning_countdown":
                if (tempDuration > 0) {
                    N0();
                    break;
                }
                break;
            case "cockpitper_cpd_close_confirm":
                BydLogHelper.logD("倒计时", "COCKPITPER_CPD_CLOSE_CONFIRM  : " + tempDuration);
                if (tempDuration > 0) {
                    C0();
                    break;
                }
                break;
            case "cockpitper_cpd_ims_icon_description":
                H0();
                break;
            case "cockpitper_driver_distraction_reminder":
                K0();
                break;
            case "cockpitper_driver_cpd_notice":
                F0(null);
                break;
            case "cockpitper_cpd_ims_close_confirm":
                G0();
                break;
            case "cockpitper_driver_detection_assist_countdown":
                if (tempDuration > 0) {
                    J0();
                    break;
                }
                break;
            case "cockpitper_driver_fatigue_warning":
                M0();
                break;
            case "cockpitper_cpd_delay_confirm":
                E0();
                break;
            case "cockpitper_driver_distraction_reminder_countdown":
                if (tempDuration > 0) {
                    L0();
                    break;
                }
                break;
            case "cockpitper_cpd_confirm_cancel":
                BydLogHelper.logD("倒计时", "COCKPITPER_CPD_CLOSE_CONFIRM  : " + tempDuration);
                if (tempDuration > 0) {
                    D0();
                    break;
                }
                break;
            case "cockpitper_driver_detection_assist":
                I0();
                break;
        }
    }

    public final void C0() {
        this.T = f0("cockpitper_cpd_close_confirm").y(g0("cockpitper_cpd_confirm_cancel"), R.string.vehiclesettings_title_dialog_hint, R.string.vehiclesettings_legacy_detection_system_close_confirm_content, -1, -1, R.string.vehiclesettings_button_confirm, new i(), R.string.vehiclesettings_button_cancel, new j(), null);
    }

    public final void D0() {
        this.T = f0("cockpitper_cpd_confirm_cancel").y(g0("cockpitper_cpd_confirm_cancel"), R.string.vehiclesettings_title_dialog_hint, R.string.vehiclesettings_legacy_detection_system_for_children_close_tips, -1, -1, R.string.vehiclesettings_button_confirm, new g(), R.string.vehiclesettings_button_cancel, new h(), null);
    }

    public final void E0() {
        this.T = f0("cockpitper_cpd_delay_confirm").A(g0("cockpitper_cpd_delay_confirm"), R.string.vehiclesettings_legacy_detection_system_for_children, R.string.vehiclesettings_legacy_detection_system_for_children_delay_tips, -1, -1, R.string.vehiclesettings_button_confirm, 0, new q.b() { // from class: b.b.g.q.e.a.f
            @Override // b.b.g.q.d.q.b
            public final void a() {
                this.f1506a.j0();
            }
        }, R.string.vehiclesettings_button_cancel, new q.a() { // from class: b.b.g.q.e.a.c
            @Override // b.b.g.q.d.q.a
            public final void a() {
                this.f1503a.l0();
            }
        }, null, new f());
    }

    public final void F0(View view) {
        this.T = f0("cockpitper_driver_cpd_notice").z(R.layout.carsetting_cockpitper_detection_assist, R.string.vehiclesettings_legacy_detection_system_for_children, R.string.vehiclesettings_legacy_detection_system_for_children_tips_content, R.string.vehiclesettings_legacy_detection_system_for_children_tips_warn, -1, R.string.vehiclesettings_button_confirm, 1, new q.b() { // from class: b.b.g.q.e.a.a
            @Override // b.b.g.q.d.q.b
            public final void a() {
                this.f1501a.n0();
            }
        }, -1, null);
    }

    public final void G0() {
        this.T = f0("cockpitper_cpd_ims_close_confirm").A(g0("cockpitper_cpd_ims_close_confirm"), R.string.vehiclesettings_legacy_detection_system_interior_motion_sensor, R.string.vehiclesettings_legacy_detection_system_interior_motion_sensor_close_confirm, -1, -1, R.string.vehiclesettings_button_confirm, 0, new q.b() { // from class: b.b.g.q.e.a.g
            @Override // b.b.g.q.d.q.b
            public final void a() {
                this.f1507a.p0();
            }
        }, R.string.vehiclesettings_button_cancel, new q.a() { // from class: b.b.g.q.e.a.d
            @Override // b.b.g.q.d.q.a
            public final void a() {
                this.f1504a.r0();
            }
        }, null, new o());
    }

    public final void H0() {
        this.T = f0("cockpitper_cpd_ims_icon_description").z(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_legacy_detection_system_interior_motion_sensor, R.string.vehiclesettings_legacy_detection_system_interior_motion_sensor_tips, -1, -1, R.string.vehiclesettings_button_confirm, 1, new q.b() { // from class: b.b.g.q.e.a.e
            @Override // b.b.g.q.d.q.b
            public final void a() {
                this.f1505a.t0();
            }
        }, -1, null);
    }

    public final void I0() {
        this.T = f0("cockpitper_driver_detection_assist").z(R.layout.carsetting_dialog_warm_auto, R.string.vehiclesettings_cockpitper_driver_monitor_assist, R.string.vehiclesettings_cockpitper__driver_monitor_assist_tips, -1, -1, R.string.vehiclesettings_button_confirm, 1, new p(), -1, null);
    }

    public void J0() {
        this.T = f0("cockpitper_driver_detection_assist_countdown").y(g0("cockpitper_driver_detection_assist_countdown"), R.string.vehiclesettings_cockpitper_driver_monitor_assist, R.string.vehiclesettings_cockpitper_driver_monitor_assist_countdown, -1, -1, R.string.vehiclesettings_button_confirm, new s(), R.string.vehiclesettings_button_cancel, new a(), null);
    }

    public final void K0() {
        this.T = f0("cockpitper_driver_distraction_reminder").B(R.layout.carsetting_dialog_withtime_cabin, R.string.vehiclesettings_cockpitper_driver_distraction_monitor_reminder, R.string.vehiclesettings_cockpitper_driver_distraction_monitor_reminder_tips, -1, -1, R.string.vehiclesettings_button_confirm, new r(), -1, null);
    }

    public final void L0() {
        this.T = f0("cockpitper_driver_distraction_reminder_countdown").y(g0("cockpitper_driver_distraction_reminder_countdown"), R.string.vehiclesettings_cockpitper_driver_distraction_monitor_reminder, R.string.vehiclesettings_cockpitper_driver_distraction_monitor_reminder_countdown, -1, -1, R.string.vehiclesettings_button_confirm, new d(), R.string.vehiclesettings_button_cancel, new e(), null);
    }

    public final void M0() {
        this.T = f0("cockpitper_driver_fatigue_warning").B(R.layout.carsetting_dialog_withtime_cabin, R.string.vehiclesettings_cockpitper_driver_tired_reminder, R.string.vehiclesettings_cockpitper_driver_tired_reminder_tips, -1, -1, R.string.vehiclesettings_button_confirm, new q(), -1, null);
    }

    public final void N0() {
        this.T = f0("cockpitper_driver_fatigue_warning_countdown").y(g0("cockpitper_driver_fatigue_warning_countdown"), R.string.vehiclesettings_cockpitper_driver_tired_reminder, R.string.vehiclesettings_cockpitper_driver_tired_reminder_tips_countdown, -1, -1, R.string.vehiclesettings_button_confirm, new b(), R.string.vehiclesettings_button_cancel, new c(), null);
    }

    public final void O0(int i2) {
        SegmentedButtonGroup segmentedButtonGroup;
        boolean z = i2 != 2 && i2 > 0;
        BydLogHelper.logD(this.f3715a, "updateCpdState  state" + i2);
        this.J.setVisibility(z ? 0 : 8);
        this.H.setChecked(z, false);
        if (i2 <= 0 || (segmentedButtonGroup = this.J) == null || this.H == null) {
            return;
        }
        if (i2 != this.I) {
            this.I = i2;
        }
        if (z) {
            segmentedButtonGroup.setPositionWithOutCallback(i2 == 1 ? 0 : 1, false, false);
        }
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_carsetting_rtl_cockpit_perception_assist;
    }

    public int e0(int i2, int i3) {
        if (this.O == null) {
            this.O = b.b.o.a.b.d.a("cockpit");
        }
        return this.O.a(i2, i3);
    }

    public final b.b.g.q.d.i f0(String str) {
        if (this.U == null) {
            this.U = new b.b.g.q.d.i(this.y);
        }
        this.U.x(str);
        return this.U;
    }

    public final int g0(String str) {
        int iC;
        iC = b.b.o.b.b.a.c();
        str.hashCode();
        switch (str) {
            case "cockpitper_driver_fatigue_warning_countdown":
            case "cockpitper_cpd_close_confirm":
            case "cockpitper_driver_detection_assist_countdown":
            case "cockpitper_driver_distraction_reminder_countdown":
            case "cockpitper_cpd_confirm_cancel":
                return iC == 2 ? R.layout.carsetting_right_cockpitper_countdown_dialog : R.layout.carsetting_cockpitper_countdown_dialog;
            case "cockpitper_cpd_ims_close_confirm":
            case "cockpitper_cpd_delay_confirm":
                return iC == 2 ? R.layout.carsetting_right_dialog_withtime : R.layout.carsetting_dialog_withtime;
            default:
                return 0;
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_cockpit_perception_assist;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_carsetting_right_cockpit_perception_assist;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        for (String str : b.b.g.q.d.k.b()) {
            if (str.startsWith("cockpitper_")) {
                B0(str);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cockpit_mobile_monitoring_system_icon /* 2131296725 */:
                H0();
                break;
            case R.id.driver_detection_sys_img /* 2131296912 */:
                I0();
                break;
            case R.id.driver_distraction_monitor_img /* 2131296914 */:
                K0();
                break;
            case R.id.driver_tired_tips_img /* 2131296916 */:
                M0();
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        t.b().m(m);
        Dialog dialog = this.T;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.T.dismiss();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        int iH = aVar.h();
        if (iH == 210075) {
            BydLogHelper.logD("DMS_TAG", " MessageCode.DiPilotCode.CPD_STATE_CHANGE: " + aVar.c());
            int iC = aVar.c();
            if (b.b.g.r.e.e(aVar)) {
                iC = ((b.b.g.n.h.f) mo7getP()).d();
                if (iC == 0) {
                    O0(2);
                }
                x0(this.y, e0(35, 0), this.F == 35);
            } else {
                x0(this.y, e0(35, 0), true);
            }
            if (iC != 0) {
                O0(iC);
            }
            return;
        }
        if (iH == 241008) {
            BydLogHelper.logD("DMS_TAG", " COCKPIT_MOBILE_MONITORING_SYSTEM_STATE : " + aVar.c());
            int iC2 = aVar.c();
            if (b.b.g.r.e.e(aVar) && (iC2 = ((b.b.g.n.h.f) mo7getP()).c()) == 0) {
                iC2 = 2;
            }
            if (iC2 < 1 || iC2 > 2) {
                return;
            }
            b.b.a.h.l.f(this.S, iC2 == 1);
            return;
        }
        switch (iH) {
            case 240000:
                BydLogHelper.logD("DMS_TAG", " COCKPITPER_DMS_TOGGLE_STATE DMS : " + aVar.c());
                if (!b.b.g.r.e.e(aVar)) {
                    this.o.setChecked(aVar.c() == 1, false);
                    b.b.a.h.l.K(aVar.c() == 1, this.z);
                    x0(this.y, e0(0, 0), true);
                } else {
                    this.o.setChecked(this.A == 1, false);
                    b.b.a.h.l.K(this.A == 1, this.z);
                    x0(this.y, e0(0, 0), this.F == 0);
                }
                break;
            case 240001:
                BydLogHelper.logD("DMS_TAG", " COCKPITPER_DMS_MODE_CHANGED_MESSAGE DMS : " + aVar.c());
                this.B = aVar.c();
                break;
            case 240002:
                BydLogHelper.logD("DMS_TAG", " COCKPITPER_DMS_TIRED_REMIND_MESSAGE  " + aVar.c());
                if (!b.b.g.r.e.e(aVar)) {
                    this.t.setChecked(aVar.c() == 1, false);
                    x0(this.y, e0(0, 0), true);
                } else {
                    this.t.setChecked(this.D == 1, false);
                    x0(this.y, e0(0, 0), this.F == 0);
                }
                break;
            case 240003:
                BydLogHelper.logD("DMS_TAG", " COCKPITPER_DMS_DISTRACTION_REMIND_MESSAGE  : " + aVar.c());
                if (!b.b.g.r.e.e(aVar)) {
                    this.w.setChecked(aVar.c() == 1, false);
                    x0(this.y, e0(0, 0), true);
                } else {
                    this.w.setChecked(this.E == 1, false);
                    x0(this.y, e0(0, 0), this.F == 0);
                }
                break;
            case 240004:
                BydLogHelper.logD("DMS_TAG", " COCKPITPER_DMS_WORK_STATE  : " + aVar.c());
                boolean z = aVar.c() == 0;
                if (b.b.g.r.e.e(aVar)) {
                    z = this.x;
                }
                y0(!z);
                this.x = z;
                if (!z) {
                    this.o.setChecked(false, false);
                    b.b.a.h.l.K(false, this.z);
                } else {
                    ((b.b.g.n.h.f) mo7getP()).f();
                    ((b.b.g.n.h.f) mo7getP()).j();
                    ((b.b.g.n.h.f) mo7getP()).m();
                    ((b.b.g.n.h.f) mo7getP()).i();
                }
                break;
            case 240005:
                BydLogHelper.logD("DMS_TAG", " COCKPITPER_DMS_TOGGLE_STATE_UPDATE  : " + aVar.c());
                this.A = aVar.c();
                break;
            case 240006:
                BydLogHelper.logD("DMS_TAG", " COCKPITPER_DMS_TIRED_REMIND_MESSAGE_UPDATE  : " + aVar.c());
                this.D = aVar.c();
                break;
            case 240007:
                BydLogHelper.logD("DMS_TAG", " COCKPITPER_DMS_DISTRACTION_REMIND_MESSAGE_UPDATE : " + aVar.c());
                this.E = aVar.c();
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i2) {
        ((b.b.g.n.h.f) mo7getP()).r(i2, this.B);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i2) {
        b.b.g.r.s.a(this.n, i2, b.b.m.e.i.a.h(getClass().getName()));
        b.b.m.e.i.a.n(getClass().getName());
    }

    public void u0(int i2) {
        this.F = i2;
        b.b.o.a.c.a.b(getClass().getSimpleName(), Integer.valueOf(this.F));
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: v0, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.h.f newP() {
        return new b.b.g.n.h.f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void w0(View view, View view2, int i2) {
        BydLogHelper.logE(this.f3715a, "onGroupPosChange  pos =" + i2);
        if (i2 > 1) {
            return;
        }
        u0(35);
        x0(this.y, e0(35, 0), true);
        if (i2 != 1) {
            ((b.b.g.n.h.f) mo7getP()).q(i2 != 0 ? 3 : 1);
        } else {
            b.b.g.r.e.h(210075);
            E0();
        }
    }

    public void x0(Context context, int i2, boolean z) {
        if (z) {
            b.b.a.h.l.j(this.L, i2, context);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        if (((b.b.g.n.h.f) mo7getP()).e()) {
            t tVarB = t.b();
            b.b.g.k.b bVar = new b.b.g.k.b();
            m = bVar;
            tVarB.h(bVar);
        }
        AdasViewModel adasViewModel = (AdasViewModel) new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AdasViewModel.class);
        if (adasViewModel.b() == null) {
            adasViewModel.d(new b.b.g.k.i());
        }
        EventBus.getDefault().register(this);
        b.b.g.n.h.f fVar = (b.b.g.n.h.f) mo7getP();
        b.b.a.h.l.K(((b.b.g.n.h.f) mo7getP()).e(), this.K, this.z, this.P);
        if (((b.b.g.n.h.f) mo7getP()).e()) {
            fVar.l();
        }
        if (fVar.g()) {
            this.G.setVisibility(0);
            O0(fVar.d());
        }
        if (((b.b.g.n.h.f) mo7getP()).h()) {
            b.b.a.h.l.K(true, this.Q);
            b.b.a.h.l.f(this.S, ((b.b.g.n.h.f) mo7getP()).c() == 1);
            b.b.a.h.l.F(this, this.R);
            this.S.setOnInterceptTouchListener(new n());
        }
        z0();
    }

    public final void y0(boolean z) {
        b.b.a.h.k.f(this.o, z);
        b.b.a.h.k.f(this.t, z);
        b.b.a.h.k.f(this.w, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void z0() {
        int iA = b.b.o.a.c.a.a(getClass().getSimpleName());
        if (iA == 0) {
            x0(this.y, e0(((b.b.g.n.h.f) mo7getP()).e() ? 0 : 35, 0), true);
        } else {
            if (iA != 35) {
                return;
            }
            x0(this.y, e0(35, 0), true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.j.b.b bVar) {
        Dialog dialog = this.T;
        if (dialog != null) {
            dialog.dismiss();
            this.T = null;
        }
    }
}
