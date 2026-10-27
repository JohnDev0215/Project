package com.byd.carsettings.ui.fragment.carsettings;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.h.l;
import b.b.g.q.d.h;
import b.b.g.q.d.k;
import b.b.g.q.d.q;
import b.b.g.r.s;
import b.b.g.r.z;
import com.byd.base.view.BydRelativeLayout;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButton;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.view.CountDownTimerButton;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class FreezerFragment extends XFragment<b.b.g.n.h.i> implements CompoundButton.OnCheckedChangeListener, SegmentedButtonGroup.f, SeekBar.OnSeekBarChangeListener, View.OnClickListener, b.b.o.b.a.a {
    public BydRelativeLayout A;
    public BydRelativeLayout B;
    public ImageView D;
    public b.b.g.q.d.h E;
    public BydSwitch F;
    public TextView G;
    public Context H;
    public ImageView I;
    public BydRelativeLayout J;
    public ImageView K;
    public BydSwitch L;
    public BydRelativeLayout M;
    public BydSwitch N;
    public TextView O;
    public TextView P;
    public TextView Q;
    public BydRelativeLayout R;
    public ImageView S;
    public BydSwitch T;
    public ImageView V;
    public b.b.o.a.b.a W;
    public Dialog X;
    public b.b.g.k.a a0;
    public Dialog b0;
    public RelativeLayout.LayoutParams c0;
    public DampingScrollView d0;
    public Dialog e0;
    public b.b.g.q.d.i f0;
    public BydRelativeLayout m;
    public BydSwitch n;
    public BydRelativeLayout o;
    public BydRelativeLayout p;
    public BydRelativeLayout q;
    public SegmentedButtonGroup r;
    public SegmentedButton s;
    public SegmentedButton t;
    public BydRelativeLayout u;
    public TextView v;
    public TextView w;
    public TextView x;
    public SeekBar y;
    public Handler z;
    public int U = 0;
    public int Y = 0;
    public int Z = 0;

    public class a implements q.c {
        public a() {
        }

        @Override // b.b.g.q.d.q.c
        public void a() {
            k.e("freezer_work_exception_dialog");
            if (FreezerFragment.this.b0 != null) {
                FreezerFragment.this.b0.dismiss();
            }
        }

        @Override // b.b.g.q.d.q.c
        public void b() {
            k.e("freezer_work_exception_dialog");
            if (FreezerFragment.this.b0 != null) {
                FreezerFragment.this.b0.dismiss();
            }
        }
    }

    public class b implements View.OnTouchListener {
        public b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (FreezerFragment.this.o == null) {
                return false;
            }
            FreezerFragment.this.o.requestLayout();
            return false;
        }
    }

    public class c extends b.b.g.k.a {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                l.D(0.5f, FreezerFragment.this.m, FreezerFragment.this.o, FreezerFragment.this.p, FreezerFragment.this.q, FreezerFragment.this.A, FreezerFragment.this.B, FreezerFragment.this.M, FreezerFragment.this.R, FreezerFragment.this.J);
                l.n(false, FreezerFragment.this.m, FreezerFragment.this.o, FreezerFragment.this.p, FreezerFragment.this.q, FreezerFragment.this.A, FreezerFragment.this.B, FreezerFragment.this.M, FreezerFragment.this.R, FreezerFragment.this.J);
            }
        }

        public c() {
        }

        @Override // b.b.g.k.a, android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onHasMessageChanged(int i, int i2) {
            super.onHasMessageChanged(i, i2);
            if (i == 219) {
                if (i2 == 0) {
                    b.b.a.f.h.a().c(new a());
                } else if (i2 == 1) {
                    l.D(1.0f, FreezerFragment.this.m, FreezerFragment.this.o, FreezerFragment.this.p, FreezerFragment.this.q, FreezerFragment.this.A, FreezerFragment.this.B, FreezerFragment.this.M, FreezerFragment.this.R, FreezerFragment.this.J);
                    l.n(true, FreezerFragment.this.m, FreezerFragment.this.o, FreezerFragment.this.p, FreezerFragment.this.q, FreezerFragment.this.A, FreezerFragment.this.B, FreezerFragment.this.M, FreezerFragment.this.R, FreezerFragment.this.J);
                }
            }
        }
    }

    public class d extends Handler {
        public d(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (message.what != 250012) {
                return;
            }
            b.b.g.r.e.i(400008);
            if (FreezerFragment.this.y != null) {
                BydLogHelper.logD("setFreezerTemp ", "baseSetMin  : " + FreezerFragment.this.Z);
                ((b.b.g.n.h.i) FreezerFragment.this.mo7getP()).C(((b.b.g.n.h.i) FreezerFragment.this.mo7getP()).l(), FreezerFragment.this.y.getProgress() + FreezerFragment.this.Z);
            }
        }
    }

    public class e implements BydSwitch.a {
        public e() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.base.view.BydSwitch.a
        public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
            FreezerFragment.this.N.playSoundEffect(0);
            if (motionEvent.getAction() != 1) {
                return;
            }
            if (!z) {
                FreezerFragment.this.N.setChecked(false, false);
                ((b.b.g.n.h.i) FreezerFragment.this.mo7getP()).E(2);
                b.b.g.r.e.i(400006);
                FreezerFragment.this.r0(2);
                return;
            }
            int iJ = ((b.b.g.n.h.i) FreezerFragment.this.mo7getP()).j();
            int iR = ((b.b.g.n.h.i) FreezerFragment.this.mo7getP()).r();
            if (iJ == 1 || iR != 0) {
                FreezerFragment.this.y0();
                return;
            }
            FreezerFragment.this.N.setChecked(true, false);
            ((b.b.g.n.h.i) FreezerFragment.this.mo7getP()).E(1);
            b.b.g.r.e.i(400006);
            FreezerFragment.this.r0(1);
        }
    }

    public class f implements q.c {
        public f() {
        }

        @Override // b.b.g.q.d.q.c
        public void a() {
            k.e("freezer_door_not_close_dialog");
            if (FreezerFragment.this.X != null) {
                FreezerFragment.this.X.dismiss();
            }
        }

        @Override // b.b.g.q.d.q.c
        public void b() {
            k.e("freezer_door_not_close_dialog");
            if (FreezerFragment.this.X != null) {
                FreezerFragment.this.X.dismiss();
            }
        }
    }

    public class g implements h.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3093a;

        public g(String str) {
            this.f3093a = str;
        }

        @Override // b.b.g.q.d.h.b
        public void a(int i) {
            b.b.g.r.e.i(400004);
            k.f1464b.put(this.f3093a, -1);
            FreezerFragment.this.v0(i);
        }

        @Override // b.b.g.q.d.h.b
        public void onCancel() {
            k.f1464b.put(this.f3093a, -1);
        }
    }

    public class h implements q.b {
        public h() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            if (FreezerFragment.this.e0 != null) {
                FreezerFragment.this.e0.dismiss();
            }
        }
    }

    public class i implements q.b {
        public i() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            if (FreezerFragment.this.e0 != null) {
                FreezerFragment.this.e0.dismiss();
            }
        }
    }

    public class j implements q.b {
        public j() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            if (FreezerFragment.this.e0 != null) {
                FreezerFragment.this.e0.dismiss();
            }
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.H = context;
        DampingScrollView dampingScrollView = (DampingScrollView) this.f3717c.findViewById(R.id.nsw);
        this.d0 = dampingScrollView;
        dampingScrollView.setOnTouchListener(new b());
    }

    public final void A0() {
        this.e0 = l0("freezer_remember_power_off_dialog").B(m0("freezer_remember_power_off_dialog"), R.string.vehiclesettings_diba_pdm, R.string.vehiclesettings_compression_refrigerator_power_off_remember_description, -1, -1, R.string.vehiclesettings_button_confirm, new i(), -1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void B0(String str, int i2) {
        if (this.E == null) {
            this.E = new b.b.g.q.d.h(getContext(), ((b.b.g.n.h.i) mo7getP()).d(), ((b.b.g.n.h.i) mo7getP()).i(), i2);
        }
        this.E.setOnSpeedSelectedListener(new g(str));
        this.E.F(str, ((b.b.g.n.h.i) mo7getP()).d(), ((b.b.g.n.h.i) mo7getP()).i(), i2);
    }

    public final void C0(int i2) {
        this.r = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.freezer_work_state_seg);
        this.s = (SegmentedButton) this.f3717c.findViewById(R.id.warm);
        SegmentedButton segmentedButton = (SegmentedButton) this.f3717c.findViewById(R.id.save);
        this.t = segmentedButton;
        if (i2 == 1) {
            l.J(8, segmentedButton);
            this.r.t(3);
        } else if (i2 == 2) {
            l.K(true, segmentedButton);
            l.K(true, this.s);
        } else if (i2 == 3) {
            l.J(8, segmentedButton);
            l.J(8, this.s);
            this.r.t(3);
            this.r.t(2);
        }
        l.t(this, this.r);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b0(boolean z) {
        if (z) {
            this.R = (BydRelativeLayout) this.f3717c.findViewById(R.id.freezer_child_lock_container);
            this.S = (ImageView) this.f3717c.findViewById(R.id.freezer_child_lock_iv);
            BydSwitch bydSwitch = (BydSwitch) this.f3717c.findViewById(R.id.freezer_child_lock_sw);
            this.T = bydSwitch;
            l.e(this.R, bydSwitch, ((b.b.g.n.h.i) mo7getP()).h() == 1, this);
            l.F(this, this.S);
        }
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_rtl_freezer_layout;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c0(boolean z) {
        if (z) {
            this.A = (BydRelativeLayout) this.f3717c.findViewById(R.id.freezer_power_off_delay_container);
            this.B = (BydRelativeLayout) this.f3717c.findViewById(R.id.freezer_power_off_delay_set_container);
            this.G = (TextView) this.f3717c.findViewById(R.id.freezer_power_off_delay_time_tv);
            this.D = (ImageView) this.f3717c.findViewById(R.id.freezer_power_off_delay_time_iv);
            this.I = (ImageView) this.f3717c.findViewById(R.id.freezer_power_off_delay_iv);
            this.F = (BydSwitch) this.f3717c.findViewById(R.id.freezer_power_off_delay_sw);
            this.J = (BydRelativeLayout) this.f3717c.findViewById(R.id.freezer_power_remember_container);
            this.K = (ImageView) this.f3717c.findViewById(R.id.freezer_power_remember_icon);
            this.L = (BydSwitch) this.f3717c.findViewById(R.id.freezer_power_remember_sw);
            ImageView imageView = this.K;
            l.F(this, this.D, this.I, imageView, imageView);
            l.e(this.A, this.F, ((b.b.g.n.h.i) mo7getP()).c(), this);
            l.e(this.J, this.L, ((b.b.g.n.h.i) mo7getP()).n() == 1, this);
            l.K(((b.b.g.n.h.i) mo7getP()).l() != 3, this.A, this.B);
            v0(((b.b.g.n.h.i) mo7getP()).d());
            q0(((b.b.g.n.h.i) mo7getP()).c());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void d0(boolean z) {
        if (z) {
            this.M = (BydRelativeLayout) this.f3717c.findViewById(R.id.freezer_sterilization_and_disinfection_container);
            this.N = (BydSwitch) this.f3717c.findViewById(R.id.freezer_sterilization_and_disinfection_sw);
            this.O = (TextView) this.f3717c.findViewById(R.id.freezer_sterilization_and_disinfection_title_tv);
            this.P = (TextView) this.f3717c.findViewById(R.id.freezer_sterilization_and_disinfection_tip_tv);
            this.Q = (TextView) this.f3717c.findViewById(R.id.freezer_sterilization_and_disinfection_center_tv);
            int iO = ((b.b.g.n.h.i) mo7getP()).o();
            l.e(this.M, this.N, iO == 1, this);
            this.N.setOnInterceptTouchListener(new e());
            r0(iO);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void e0(boolean z) {
        if (z) {
            this.m = (BydRelativeLayout) this.f3717c.findViewById(R.id.freezer_door_container);
            BydSwitch bydSwitch = (BydSwitch) this.f3717c.findViewById(R.id.freezer_door_switch);
            this.n = bydSwitch;
            l.e(this.m, bydSwitch, ((b.b.g.n.h.i) mo7getP()).j() == 1, this);
        }
    }

    public final void f0(int i2) {
        TextView textView = this.x;
        String string = textView.getContext().getResources().getString(R.string.vehiclesettings_placehodler);
        Object[] objArr = new Object[2];
        objArr[0] = String.valueOf(i2);
        objArr[1] = getRefP().q() == 1 ? "°C" : "°F";
        l.B(textView, String.format(string, objArr));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void g0(boolean z) {
        if (z) {
            this.p = (BydRelativeLayout) this.f3717c.findViewById(R.id.freezer_work_sate_container);
            this.q = (BydRelativeLayout) this.f3717c.findViewById(R.id.freezer_temperature_turn_container);
            this.u = (BydRelativeLayout) this.f3717c.findViewById(R.id.freezer_temperature_turn_title_area_container);
            this.v = (TextView) this.f3717c.findViewById(R.id.freezer_temperature_left_tv);
            this.w = (TextView) this.f3717c.findViewById(R.id.freezer_temperature_right_tv);
            this.y = (SeekBar) this.f3717c.findViewById(R.id.freezer_temperature_turn_sb);
            this.x = (TextView) this.f3717c.findViewById(R.id.freezer_temperature_tv);
            l.K(true, this.p, this.q, this.u);
            l.s(this, this.y);
            C0(((b.b.g.n.h.i) mo7getP()).g());
            int iL = ((b.b.g.n.h.i) mo7getP()).l();
            t0(iL);
            s0(iL, ((b.b.g.n.h.i) mo7getP()).q());
            i0(((b.b.g.n.h.i) mo7getP()).l());
            f0(getRefP().m(((b.b.g.n.h.i) mo7getP()).l()) + this.Y);
            j0(getRefP().m(((b.b.g.n.h.i) mo7getP()).l()));
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_freezer_layout;
    }

    public final void h0(boolean z) {
        if (z) {
            BydRelativeLayout bydRelativeLayout = (BydRelativeLayout) this.f3717c.findViewById(R.id.freezer_work_and_temperature_state_container);
            this.o = bydRelativeLayout;
            l.K(true, bydRelativeLayout);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i0(int r5) {
        /*
            r4 = this;
            b.b.m.e.g r0 = r4.mo7getP()
            b.b.g.n.h.i r0 = (b.b.g.n.h.i) r0
            int r0 = r0.q()
            r1 = 0
            r2 = 1
            if (r0 != r2) goto L10
            r0 = r2
            goto L11
        L10:
            r0 = r1
        L11:
            r3 = 10
            if (r5 == r2) goto L2b
            r1 = 2
            if (r5 == r1) goto L22
            r2 = 4
            if (r5 == r2) goto L1c
            goto L4a
        L1c:
            if (r0 == 0) goto L20
            r3 = r1
            goto L4a
        L20:
            r3 = r2
            goto L4a
        L22:
            if (r0 == 0) goto L27
            r5 = 15
            goto L29
        L27:
            r5 = 27
        L29:
            r3 = r5
            goto L4a
        L2b:
            b.b.m.e.g r5 = r4.mo7getP()
            b.b.g.n.h.i r5 = (b.b.g.n.h.i) r5
            int r5 = r5.f()
            if (r5 != r2) goto L38
            r1 = r2
        L38:
            if (r0 == 0) goto L42
            if (r1 == 0) goto L3f
            r5 = 12
            goto L29
        L3f:
            r5 = 24
            goto L29
        L42:
            if (r1 == 0) goto L47
            r5 = 22
            goto L29
        L47:
            r5 = 43
            goto L29
        L4a:
            android.widget.SeekBar r5 = r4.y
            if (r5 == 0) goto L51
            r5.setMax(r3)
        L51:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.ui.fragment.carsettings.FreezerFragment.i0(int):void");
    }

    public final void j0(int i2) {
        l.q(this.y, i2);
    }

    public int k0(int i2, int i3) {
        if (this.W == null) {
            this.W = b.b.o.a.b.d.a("freezer");
        }
        return this.W.a(i2, i3);
    }

    public final b.b.g.q.d.i l0(String str) {
        if (this.f0 == null) {
            this.f0 = new b.b.g.q.d.i(this.H);
        }
        this.f0.x(str);
        return this.f0;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_right_freezer_layout;
    }

    public final int m0(String str) {
        int iC;
        iC = b.b.o.b.b.a.c();
        str.hashCode();
        switch (str) {
            case "freezer_door_not_close_dialog":
            case "freezer_work_exception_dialog":
                return iC == 2 ? R.layout.carsetting_right_freezer_countdown_dialog : R.layout.carsetting_freezer_countdown_dialog;
            case "freezer_delay_time_tips_dialog":
            case "freezer_remember_power_off_dialog":
            case "freezer_child_lock_dialog":
                return iC == 2 ? R.layout.carsetting_dialog_withtime : R.layout.carsetting_right_dialog_withtime;
            default:
                return 0;
        }
    }

    public void n0(int i2) {
        this.U = i2;
        b.b.o.a.c.a.b(getClass().getSimpleName(), Integer.valueOf(this.U));
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.h.i newP() {
        return new b.b.g.n.h.i();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        long tempDuration;
        super.onActivityCreated(bundle);
        for (String str : k.b()) {
            if (str.startsWith("freezer_")) {
                tempDuration = CountDownTimerButton.getTempDuration();
                switch (str) {
                    case "freezer_door_not_close_dialog":
                        if (tempDuration > 0) {
                            y0();
                            break;
                        } else {
                            break;
                        }
                        break;
                    case "freezer_delay_time_tips_dialog":
                        x0();
                        break;
                    case "freezer_time_set_dialog":
                        B0(str, k.f1464b.getOrDefault("instrument_speed_reminder_mode", -1).intValue());
                        break;
                    case "freezer_remember_power_off_dialog":
                        A0();
                        break;
                    case "freezer_child_lock_dialog":
                        w0();
                        break;
                    case "freezer_work_exception_dialog":
                        if (tempDuration > 0) {
                            z0();
                            break;
                        } else {
                            break;
                        }
                        break;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.freezer_child_lock_sw /* 2131296986 */:
                if (l.a(this.T)) {
                    ((b.b.g.n.h.i) mo7getP()).z(z ? 1 : 2);
                    b.b.g.r.e.i(400007);
                    break;
                }
                break;
            case R.id.freezer_door_switch /* 2131296989 */:
                if (l.a(this.n)) {
                    if (b.b.a.i.a.b() == 180 && ((b.b.g.n.h.i) mo7getP()).j() == 1) {
                        z.b(this.H, R.string.vehiclesettings_compression_refrigerator_door_state_manually_tips);
                        l.f(this.n, true);
                    } else {
                        ((b.b.g.n.h.i) mo7getP()).A(z ? 1 : 2);
                        b.b.g.r.e.h(400009);
                        b.b.g.r.e.i(400000);
                    }
                    break;
                }
                break;
            case R.id.freezer_power_off_delay_sw /* 2131296994 */:
                if (l.a(this.F)) {
                    ((b.b.g.n.h.i) mo7getP()).y(z ? 1 : 2);
                    q0(z);
                    b.b.g.r.e.i(400003);
                    break;
                }
                break;
            case R.id.freezer_power_remember_sw /* 2131297000 */:
                if (l.a(this.L)) {
                    ((b.b.g.n.h.i) mo7getP()).D(z ? 1 : 2);
                    b.b.g.r.e.i(400005);
                    break;
                }
                break;
            case R.id.freezer_sterilization_and_disinfection_sw /* 2131297004 */:
                if (l.a(this.N)) {
                    ((b.b.g.n.h.i) mo7getP()).E(z ? 1 : 2);
                    b.b.g.r.e.i(400006);
                    r0(z ? 1 : 2);
                    break;
                }
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.freezer_child_lock_iv /* 2131296985 */:
                w0();
                break;
            case R.id.freezer_power_off_delay_iv /* 2131296991 */:
                x0();
                break;
            case R.id.freezer_power_off_delay_time_iv /* 2131296995 */:
                B0("freezer_time_set_dialog", -1);
                break;
            case R.id.freezer_power_remember_icon /* 2131296999 */:
                A0();
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        b.b.g.r.e.a(this.z);
        b.b.g.n.q.b.c().j(this.a0);
        EventBus.getDefault().unregister(this);
        DampingScrollView dampingScrollView = this.d0;
        if (dampingScrollView != null) {
            dampingScrollView.setOnTouchListener(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        int iH = aVar.h();
        if (iH == 30013) {
            int iL = ((b.b.g.n.h.i) mo7getP()).l();
            if (iL != 3) {
                t0(iL);
                s0(iL, ((b.b.g.n.h.i) mo7getP()).q());
                i0(iL);
                j0(getRefP().m(iL));
                l.K(iL != 3, this.A, this.B);
                return;
            }
            return;
        }
        if (iH != 400000) {
            switch (iH) {
                case 400003:
                    BydLogHelper.logD("message : FREEZER_WORK_DELAY_POWER_OFF_CHANGED " + aVar.c());
                    int iC = aVar.c();
                    if (b.b.g.r.e.e(aVar)) {
                        iC = ((b.b.g.n.h.i) mo7getP()).c() ? 1 : 2;
                    }
                    if (iC == 2 || iC == 1) {
                        l.f(this.F, iC == 1);
                        q0(iC == 1);
                    }
                    break;
                case 400004:
                    BydLogHelper.logD("message : FREEZER_WORK_DELAY_POWER_OFF_TIME_CHANGED " + aVar.c());
                    int iC2 = aVar.c();
                    if (b.b.g.r.e.e(aVar)) {
                        iC2 = ((b.b.g.n.h.i) mo7getP()).d();
                    }
                    v0(iC2);
                    break;
                case 400005:
                    BydLogHelper.logD("message : FREEZER_WORK_POWER_OFF_REMEMBER_CHANGED " + aVar.c());
                    int iC3 = aVar.c();
                    if (b.b.g.r.e.e(aVar) && (iC3 = ((b.b.g.n.h.i) mo7getP()).n()) == 0) {
                        iC3 = 2;
                    }
                    if (iC3 == 1 || iC3 == 2) {
                        l.f(this.L, iC3 == 1);
                    }
                    break;
                case 400006:
                    BydLogHelper.logD("message : FREEZER_WORK_DISINF_MESSAGE_CHANGED " + aVar.c());
                    int iC4 = aVar.c();
                    if (b.b.g.r.e.e(aVar) && (iC4 = ((b.b.g.n.h.i) mo7getP()).o()) == 0) {
                        iC4 = 2;
                    }
                    if (iC4 == 1 || iC4 == 2) {
                        l.f(this.N, iC4 == 1);
                        r0(iC4);
                    }
                    break;
                case 400007:
                    BydLogHelper.logD("message : FREEZER_WORK_CHILD_LOCK_CHANGED " + aVar.c());
                    int iC5 = aVar.c();
                    if (b.b.g.r.e.e(aVar) && (iC5 = ((b.b.g.n.h.i) mo7getP()).h()) == 0) {
                        iC5 = 2;
                    }
                    if (iC5 == 1 || iC5 == 2) {
                        l.f(this.T, iC5 == 1);
                    }
                    break;
                case 400008:
                    BydLogHelper.logD("message : FREEZER_WORK_MODE_STATE_CHANGED " + aVar.c());
                    int iL2 = ((b.b.g.n.h.i) mo7getP()).l();
                    if (b.b.g.r.e.e(aVar)) {
                        if (iL2 == 0) {
                            iL2 = 3;
                        }
                        if (iL2 >= 1 && iL2 <= 4) {
                            p0(this.H, k0(37, iL2), this.U == 37);
                        }
                    } else {
                        p0(this.H, k0(37, iL2), true);
                    }
                    if (iL2 >= 1 && iL2 <= 4) {
                        t0(iL2);
                        s0(iL2, ((b.b.g.n.h.i) mo7getP()).q());
                        if (iL2 != 3) {
                            i0(iL2);
                            f0(getRefP().m(((b.b.g.n.h.i) mo7getP()).l()) + this.Y);
                            j0(getRefP().m(iL2));
                        }
                        l.K(iL2 != 3, this.A, this.B);
                        break;
                    }
                    break;
            }
            return;
        }
        BydLogHelper.logD("message : FREEZER_DOOR_TOGGLE_STATE_CHANGED " + aVar.c());
        int iC6 = aVar.c();
        if (!b.b.g.r.e.e(aVar)) {
            if (iC6 == 1 || iC6 == 2) {
                l.f(this.n, iC6 == 1);
                return;
            }
            return;
        }
        int iJ = ((b.b.g.n.h.i) mo7getP()).j();
        if (iJ == 0) {
            iJ = 2;
        }
        int iR = ((b.b.g.n.h.i) mo7getP()).r();
        BydLogHelper.logD("message : FREEZER_GET_WORK_STATE " + iR);
        if (iR == 2 || iR == 1) {
            b.b.g.r.e.i(400009);
        } else if (iJ == 1 || iJ == 2) {
            l.f(this.n, iJ == 1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i2) {
        if (view.getId() == R.id.freezer_work_state_seg) {
            b.b.g.r.e.i(400008);
            if (i2 == 0) {
                i2 = 3;
            } else if (i2 == 3) {
                i2 = 4;
            }
            ((b.b.g.n.h.i) mo7getP()).B(i2);
            s0(i2, ((b.b.g.n.h.i) mo7getP()).q());
            n0(37);
            p0(this.H, k0(37, i2), true);
            if (i2 != 3) {
                i0(i2);
                f0(getRefP().m(i2) + this.Y);
                j0(getRefP().m(i2));
            }
            l.K(i2 != 3, this.A, this.B);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
        if (z) {
            l.r(false, this.d0);
            b.b.g.r.e.n(250012, this.z);
            b.b.g.r.e.i(400008);
            BydLogHelper.logD("setFreezerTemp ", "baseTvMin  : " + this.Y);
            f0(i2 + this.Y);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        b.b.g.r.e.i(400008);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        l.r(true, this.d0);
    }

    public void p0(Context context, int i2, boolean z) {
        if (z) {
            l.j(this.V, i2, context);
        }
    }

    public final void q0(boolean z) {
        b.b.a.h.k.g(!z, this.B, this.G, this.D, this.I);
    }

    public final void r0(int i2) {
        if (i2 == 1) {
            l.K(true, this.P, this.O);
            l.K(false, this.Q);
        } else {
            l.K(false, this.P, this.O);
            l.K(true, this.Q);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void s0(int i2, int i3) {
        BydRelativeLayout bydRelativeLayout = this.q;
        if (bydRelativeLayout != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) bydRelativeLayout.getLayoutParams();
            this.c0 = layoutParams;
            layoutParams.height = -2;
        }
        if (i2 == 1) {
            l.K(true, this.q);
            BydRelativeLayout bydRelativeLayout2 = this.q;
            if (bydRelativeLayout2 != null) {
                bydRelativeLayout2.setLayoutParams(this.c0);
            }
            if (((b.b.g.n.h.i) mo7getP()).f() == 1) {
                if (i3 == 1) {
                    l.B(this.v, String.format(getString(R.string.vehiclesettings_placehodler), "-6", "°C"));
                    l.B(this.w, String.format(getString(R.string.vehiclesettings_placehodler), "6", "°C"));
                    this.Y = -6;
                    this.Z = 13;
                    return;
                }
                l.B(this.v, String.format(getString(R.string.vehiclesettings_placehodler), "21", "°F"));
                l.B(this.w, String.format(getString(R.string.vehiclesettings_placehodler), "43", "°F"));
                this.Y = 21;
                this.Z = 22;
                return;
            }
            if (i3 == 1) {
                l.B(this.v, String.format(getString(R.string.vehiclesettings_placehodler), "-18", "°C"));
                l.B(this.w, String.format(getString(R.string.vehiclesettings_placehodler), "6", "°C"));
                this.Y = -18;
                this.Z = 1;
                return;
            }
            l.B(this.v, String.format(getString(R.string.vehiclesettings_placehodler), "0", "°F"));
            l.B(this.w, String.format(getString(R.string.vehiclesettings_placehodler), "43", "°F"));
            this.Y = 0;
            this.Z = 1;
            return;
        }
        if (i2 == 2) {
            l.K(true, this.q);
            BydRelativeLayout bydRelativeLayout3 = this.q;
            if (bydRelativeLayout3 != null) {
                bydRelativeLayout3.setLayoutParams(this.c0);
            }
            if (i3 == 1) {
                l.B(this.v, String.format(getString(R.string.vehiclesettings_placehodler), "35", "°C"));
                l.B(this.w, String.format(getString(R.string.vehiclesettings_placehodler), "50", "°C"));
                this.Y = 35;
                this.Z = 35;
                return;
            }
            l.B(this.v, String.format(getString(R.string.vehiclesettings_placehodler), "95", "°F"));
            l.B(this.w, String.format(getString(R.string.vehiclesettings_placehodler), "122", "°F"));
            this.Y = 95;
            this.Z = 95;
            return;
        }
        if (i2 != 4) {
            l.J(8, this.q);
            BydRelativeLayout bydRelativeLayout4 = this.q;
            if (bydRelativeLayout4 != null) {
                RelativeLayout.LayoutParams layoutParams2 = this.c0;
                layoutParams2.height = 0;
                bydRelativeLayout4.setLayoutParams(layoutParams2);
                return;
            }
            return;
        }
        l.K(true, this.q);
        BydRelativeLayout bydRelativeLayout5 = this.q;
        if (bydRelativeLayout5 != null) {
            bydRelativeLayout5.setLayoutParams(this.c0);
        }
        if (i3 == 1) {
            l.B(this.v, String.format(getString(R.string.vehiclesettings_placehodler), "8", "°C"));
            l.B(this.w, String.format(getString(R.string.vehiclesettings_placehodler), "10", "°C"));
            this.Y = 8;
            this.Z = 26;
            return;
        }
        l.B(this.v, String.format(getString(R.string.vehiclesettings_placehodler), "46", "°F"));
        l.B(this.w, String.format(getString(R.string.vehiclesettings_placehodler), "50", "°F"));
        this.Y = 46;
        this.Z = 45;
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i2) {
        s.a(this.d0, i2, b.b.m.e.i.a.h(getClass().getName()));
        b.b.m.e.i.a.n(getClass().getName());
    }

    public final void t0(int i2) {
        int i3 = 3;
        if (i2 == 1) {
            i3 = 1;
        } else if (i2 == 2) {
            i3 = 2;
        } else if (i2 == 3 || i2 != 4) {
            i3 = 0;
        }
        l.w(this.r, i3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void u0() {
        this.V = (ImageView) this.f3717c.findViewById(R.id.freezer_car_image);
        p0(this.H, k0(37, ((b.b.g.n.h.i) mo7getP()).l()), true);
    }

    public void v0(int i2) {
        if (i2 == 0) {
            return;
        }
        l.B(this.G, String.format(getString(R.string.vehiclesettings_placehodler), String.valueOf(i2), this.H.getResources().getString(R.string.vehiclesettings_compression_refrigerator_work_delay_power_off_set_unit)));
    }

    public final void w0() {
        this.e0 = l0("freezer_child_lock_dialog").B(m0("freezer_child_lock_dialog"), R.string.vehiclesettings_compression_refrigerator_child_lock, R.string.vehiclesettings_compression_refrigerator_screen_child_lock_description, -1, -1, R.string.vehiclesettings_button_confirm, new j(), -1, null);
    }

    public final void x0() {
        this.e0 = l0("freezer_delay_time_tips_dialog").B(m0("freezer_delay_time_tips_dialog"), R.string.vehiclesettings_compression_refrigerator_work_delay_power_off, R.string.vehiclesettings_compression_refrigerator_work_delay_power_off_description, -1, -1, R.string.vehiclesettings_button_confirm, new h(), -1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        EventBus.getDefault().register(this);
        this.a0 = new c();
        b.b.g.n.q.b.c().h(this.a0);
        this.z = new d(Looper.myLooper());
        e0(((b.b.g.n.h.i) mo7getP()).v());
        h0(((b.b.g.n.h.i) mo7getP()).w());
        g0(((b.b.g.n.h.i) mo7getP()).x());
        c0(((b.b.g.n.h.i) mo7getP()).t());
        d0(((b.b.g.n.h.i) mo7getP()).u());
        b0(((b.b.g.n.h.i) mo7getP()).s());
        u0();
    }

    public final void y0() {
        this.X = l0("freezer_door_not_close_dialog").y(m0("freezer_door_not_close_dialog"), R.string.vehiclesettings_title_dialog_hint, R.string.vehiclesettings_compression_refrigerator_door_unclose_description, -1, -1, -1, null, R.string.vehiclesettings_button_cancel, new f(), null);
    }

    public final void z0() {
        this.b0 = l0("freezer_work_exception_dialog").y(m0("freezer_work_exception_dialog"), R.string.vehiclesettings_title_dialog_hint, R.string.vehiclesettings_compression_refrigerator_system_check, -1, -1, -1, null, R.string.vehiclesettings_button_cancel, new a(), null);
    }
}
