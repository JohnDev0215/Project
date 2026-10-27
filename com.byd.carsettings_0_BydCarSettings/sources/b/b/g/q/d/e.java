package b.b.g.q.d;

import android.app.Dialog;
import android.content.Context;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.Fragment;
import b.b.g.r.u;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;

/* JADX INFO: compiled from: BydMassageDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends b implements View.OnClickListener, SegmentedButtonGroup.f, CompoundButton.OnCheckedChangeListener, b.b.o.b.a.a {
    public ImageButton A;
    public ImageButton B;
    public TextView D;
    public TextView E;
    public TextView F;
    public TextView G;
    public TextView H;
    public volatile int I;
    public volatile int J;
    public volatile int K;
    public volatile int L;
    public volatile int M;
    public volatile int N;
    public b.b.g.n.h.d O;
    public Context P;
    public Dialog Q;
    public SegmentedButtonGroup R;
    public int S;
    public TextView T;
    public TextView U;
    public d V;
    public LottieAnimationView W;
    public LottieAnimationView X;
    public int[][] Y;
    public CheckBox Z;
    public CheckBox a0;
    public RelativeLayout b0;
    public AbsBYDAutoBodyworkListener c0;
    public TextView d0;
    public RelativeLayout e0;
    public RelativeLayout f0;
    public RelativeLayout g0;
    public RelativeLayout h0;
    public ImageView i;
    public int i0;
    public ImageView j;
    public RelativeLayout j0;
    public ArrayList<Fragment> k;
    public RelativeLayout k0;
    public SegmentedButtonGroup l;
    public CheckBox l0;
    public ImageButton m;
    public CheckBox m0;
    public ImageButton n;
    public ConstraintLayout n0;
    public ImageButton o;
    public ConstraintLayout o0;
    public ImageButton p;
    public ImageButton q;
    public TextView r;
    public TextView s;
    public TextView t;
    public TextView u;
    public TextView v;
    public SegmentedButtonGroup w;
    public ImageButton x;
    public ImageButton y;
    public ImageButton z;

    /* JADX INFO: compiled from: BydMassageDialog.java */
    public class a extends AbsBYDAutoBodyworkListener {

        /* JADX INFO: renamed from: b.b.g.q.d.e$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: BydMassageDialog.java */
        public class RunnableC0068a implements Runnable {
            public RunnableC0068a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = e.this;
                eVar.f0(eVar.Z());
            }
        }

        public a() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int i) {
            super.onPowerLevelChanged(i);
            b.b.a.f.h.a().c(new RunnableC0068a());
        }
    }

    public e(Context context, b.b.g.n.h.d dVar) {
        super(context);
        this.k = new ArrayList<>();
        this.I = 0;
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.M = 0;
        this.N = 1;
        this.Y = new int[][]{new int[]{R.attr.massage_mode_wave_weak, R.attr.massage_mode_wave_middle, R.attr.massage_mode_wave_strong}, new int[]{R.attr.massage_mode_pulse_weak, R.attr.massage_mode_pulse_middle, R.attr.massage_mode_pulse_strong}, new int[]{R.attr.massage_mode_stretch_weak, R.attr.massage_mode_stretch_middle, R.attr.massage_mode_stretch_strong}, new int[]{R.attr.massage_mode_waist_weak, R.attr.massage_mode_waist_middle, R.attr.massage_mode_waist_strong}, new int[]{R.attr.massage_mode_shoulder_weak, R.attr.massage_mode_shoulder_middle, R.attr.massage_mode_shoulder_strong}};
        this.i0 = 0;
        this.P = context;
        this.O = dVar;
    }

    public final void A0(int i, int i2) {
        if (i <= 1 || i >= 7) {
            x0(this.W);
        } else {
            if (i2 < 1 || i2 > 3) {
                return;
            }
            h0(this.W, b.b.a.h.b.a(this.P, this.Y[i - 2][i2 - 1]));
        }
    }

    public final void B() {
        b.b.a.h.l.F(null, this.m, this.n, this.o, this.p, this.q, this.x, this.y, this.z, this.A, this.B);
        b.b.a.h.l.t(null, this.w, this.l, this.R);
        b.b.a.h.l.F(null, this.i, this.j);
        y0();
    }

    public void B0(int i, int i2, int i3) {
        BydLogHelper.logD("area = " + i + " --  level = " + i2);
        if (this.l != null && i == Y(this.S)) {
            this.I = i2;
            D0(i, i3, i2);
        } else {
            if (this.w == null || i != M(this.S)) {
                return;
            }
            this.J = i2;
            D0(i, i3, i2);
        }
    }

    public final void C() {
        u.c(this.P, "massage_dialog", 2);
        l();
        k.e("carseat_massage_mode");
        B();
        l0();
        this.Q = null;
    }

    public final void C0(int i, int i2) {
        boolean z = false;
        if (this.l0 != null && i == Y(this.S)) {
            CheckBox checkBox = this.l0;
            if (i2 != 1 && i2 != 0) {
                z = true;
            }
            checkBox.setChecked(z);
            this.l0.setText(i2 == 1 ? "OFF" : "ON");
            if (i2 == 0) {
                this.l0.setText("OFF");
            }
            x0(this.W);
            return;
        }
        if (this.m0 == null || i != M(this.S)) {
            return;
        }
        CheckBox checkBox2 = this.m0;
        if (i2 != 1 && i2 != 0) {
            z = true;
        }
        checkBox2.setChecked(z);
        this.m0.setText(i2 == 1 ? "OFF" : "ON");
        if (i2 == 0) {
            this.m0.setText("OFF");
        }
        x0(this.X);
    }

    public void D() {
        d dVar = this.V;
        if (dVar == null || !dVar.A()) {
            return;
        }
        this.V.l();
    }

    public void D0(int i, int i2, int i3) {
        if (this.i0 == 1 && (i == 3 || i == 4)) {
            C0(i, i2);
        } else {
            E0(i, i2, i3, g0(i));
        }
    }

    public final int E() {
        this.w.o();
        this.a0.setSelected(false);
        this.a0.setText("OFF");
        this.w.setPositionWithOutCallback(-1, false);
        SegmentedButtonGroup segmentedButtonGroup = this.w;
        b.b.a.h.l.D(0.5f, segmentedButtonGroup, this.x, this.y, this.z, this.A, this.B, this.D, this.E, this.F, this.G, this.H, segmentedButtonGroup, this.a0);
        return 0;
    }

    public void E0(int i, int i2, int i3, boolean z) {
        if (z) {
            BydLogHelper.logD("area = " + i + " --  mode = " + i2 + "-- level -- " + i3);
            if (this.m != null && i == Y(this.S)) {
                this.m.setSelected(i2 == 6);
                this.n.setSelected(i2 == 5);
                this.o.setSelected(i2 == 4);
                this.p.setSelected(i2 == 3);
                this.q.setSelected(i2 == 2);
                this.r.setSelected(i2 == 6);
                this.s.setSelected(i2 == 5);
                this.t.setSelected(i2 == 4);
                this.u.setSelected(i2 == 3);
                this.v.setSelected(i2 == 2);
                this.Z.setChecked((i2 == 1 || i2 == 0) ? false : true);
                this.Z.setText(i2 == 1 ? "OFF" : "ON");
                if (i2 == 0) {
                    this.Z.setText("OFF");
                }
                this.K = i2;
                if (i2 == 1) {
                    this.l.setPositionWithOutCallback(-1, false);
                } else {
                    this.l.setPositionWithOutCallback(i3 - 1, false);
                }
                A0(this.K, i3);
                return;
            }
            if (this.x == null || i != M(this.S)) {
                return;
            }
            this.x.setSelected(i2 == 6);
            this.y.setSelected(i2 == 5);
            this.z.setSelected(i2 == 4);
            this.A.setSelected(i2 == 3);
            this.B.setSelected(i2 == 2);
            this.D.setSelected(i2 == 6);
            this.E.setSelected(i2 == 5);
            this.F.setSelected(i2 == 4);
            this.G.setSelected(i2 == 3);
            this.H.setSelected(i2 == 2);
            this.a0.setChecked((i2 == 1 || i2 == 0) ? false : true);
            this.a0.setText(i2 == 1 ? "OFF" : "ON");
            if (i2 == 0) {
                this.a0.setText("OFF");
            }
            this.L = i2;
            if (i2 == 1) {
                this.w.setPositionWithOutCallback(-1, false);
            } else {
                this.w.setPositionWithOutCallback(i3 - 1, false);
            }
            z0(this.L, i3);
        }
    }

    public final int F() {
        this.m0.setChecked(false);
        this.m0.setText("OFF");
        b.b.a.h.l.D(0.5f, this.m0);
        return 0;
    }

    public final int G() {
        b.b.a.h.l.I(1.0f, this.m0);
        return 0;
    }

    public final int H() {
        this.w.s();
        SegmentedButtonGroup segmentedButtonGroup = this.w;
        b.b.a.h.l.I(1.0f, segmentedButtonGroup, this.x, this.y, this.z, this.A, this.B, this.D, this.E, this.F, this.G, this.H, segmentedButtonGroup, this.a0);
        return 0;
    }

    public final int I() {
        this.l.o();
        this.Z.setSelected(false);
        this.Z.setText("OFF");
        this.l.setPositionWithOutCallback(-1, false);
        SegmentedButtonGroup segmentedButtonGroup = this.l;
        b.b.a.h.l.D(0.5f, segmentedButtonGroup, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, segmentedButtonGroup, this.Z);
        return 0;
    }

    public final int J() {
        this.l0.setChecked(false);
        this.l0.setText("OFF");
        b.b.a.h.l.D(0.5f, this.l0);
        return 0;
    }

    public final int K() {
        b.b.a.h.l.I(1.0f, this.m0);
        return 0;
    }

    public final int L() {
        this.l.s();
        SegmentedButtonGroup segmentedButtonGroup = this.l;
        b.b.a.h.l.I(1.0f, segmentedButtonGroup, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, segmentedButtonGroup, this.Z);
        return 0;
    }

    public final int M(int i) {
        if (i == 0) {
            return c0() ? 1 : 2;
        }
        return 4;
    }

    public final int N(int i) {
        return (i == 182 || i == 208) ? R.drawable.uke_img_dark_air_sear_copilot : b.b.a.h.b.a(this.P, R.attr.byd_vehicle_massage_front_left_seat_img);
    }

    public final int O(int i) {
        return (int) ((i == 60 || i == 123 || i == 182 || i == 208) ? this.P.getResources().getDimension(R.dimen.dp_310) : this.P.getResources().getDimension(R.dimen.dp_354));
    }

    public final int P(int i) {
        return (int) ((i == 60 || i == 123 || i == 182 || i == 208) ? this.P.getResources().getDimension(R.dimen.dp_163) : this.P.getResources().getDimension(R.dimen.dp_186));
    }

    public final int Q(int i) {
        int i2 = i != 1 ? i != 2 ? i != 3 ? i != 4 ? 0 : 110016 : 110015 : 110014 : 110013;
        BydLogHelper.logD("levelMsg ： " + i2 + " position : " + i);
        return i2;
    }

    public final int R(int i) {
        return (int) ((i == 60 || i == 123) ? this.P.getResources().getDimension(R.dimen.dp_116) : (i == 182 || i == 208) ? this.P.getResources().getDimension(R.dimen.dp_96) : this.P.getResources().getDimension(R.dimen.dp_93));
    }

    public final int S(int i, int i2) {
        if (i == 60 || i == 123) {
            return (int) this.P.getResources().getDimension(i2 == 0 ? R.dimen.dp_34 : R.dimen.dp_39);
        }
        return (int) ((i == 182 || i == 208) ? this.P.getResources().getDimension(R.dimen.dp_48) : this.P.getResources().getDimension(R.dimen.dp_70));
    }

    public final int T(int i, int i2) {
        if (i == 60 || i == 123) {
            return (int) this.P.getResources().getDimension(i2 == 0 ? R.dimen.dp_52 : R.dimen.dp_47);
        }
        return (int) ((i == 182 || i == 208) ? this.P.getResources().getDimension(R.dimen.dp_38) : this.P.getResources().getDimension(R.dimen.dp_36));
    }

    public final int U(int i) {
        return (int) ((i == 60 || i == 123 || i == 182 || i == 208) ? this.P.getResources().getDimension(R.dimen.dp_60) : this.P.getResources().getDimension(R.dimen.dp_93));
    }

    public final int V(int i) {
        int i2 = i != 1 ? i != 2 ? i != 3 ? i != 4 ? 0 : 110016 : 110015 : 110014 : 110013;
        BydLogHelper.logD("modeMsg ： " + i2 + " position : " + i);
        return i2;
    }

    public final int W(int i) {
        return (i == 182 || i == 208) ? R.drawable.uke_img_dark_air_sear_copilot : b.b.a.h.b.a(this.P, R.attr.byd_vehicle_massage_rear_left_seat_img);
    }

    public final int X(int i) {
        int iB = b.b.a.i.a.b();
        if (i == 0) {
            return N(iB);
        }
        if (i != 1) {
            return b.b.a.h.b.a(this.P, i == 0 ? R.attr.byd_vehicle_massage_front_left_seat_img : R.attr.byd_vehicle_massage_rear_left_seat_img);
        }
        return W(iB);
    }

    public final int Y(int i) {
        if (i == 0) {
            return c0() ? 2 : 1;
        }
        return 3;
    }

    public final int Z() {
        if (this.O.x() && this.O.r()) {
            this.S = 0;
        } else if (this.O.x() && !this.O.r()) {
            this.S = 1;
            this.d0.setText(R.string.vehiclesettings_carseat_massage_rear);
        } else if (!this.O.x() && this.O.r()) {
            this.S = 0;
            this.d0.setText(R.string.vehiclesettings_carseat_massage_front);
        }
        return this.S;
    }

    public final void a0() {
        if (this.O.z() || this.O.w()) {
            this.i0 = 2;
        } else if (this.O.y() || this.O.v()) {
            this.i0 = 1;
        } else {
            this.i0 = 0;
        }
    }

    public boolean b0() {
        return BYDAutoBodyworkDevice.getInstance(b.b.a.a.a.b().a()).getPowerLevel() == 0;
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.dialog_seat_rtl_front_massage;
    }

    public final boolean c0() {
        return b.b.a.i.a.c(this.P);
    }

    public boolean d0() {
        Dialog dialog = this.Q;
        return dialog != null && dialog.isShowing();
    }

    public final boolean e0() {
        return b.b.g.n.q.m.p0().J0() == 2 || b.b.g.n.q.m.p0().I0() == 2 || b.b.g.n.q.m.p0().K0() == 2 || b.b.g.n.q.m.p0().L0() == 2 || b.b.g.n.q.m.p0().M0() == 2;
    }

    public final void f0(int i) {
        this.I = this.O.i(Y(i));
        this.J = this.O.i(M(i));
        this.K = this.O.j(Y(i));
        this.L = this.O.j(M(i));
        this.R.setPositionWithOutCallback(this.S, false);
        o0(i);
        t0(i);
        s0(i);
        i0(i);
    }

    public final boolean g0(int i) {
        if (i == 1) {
            return this.O.s();
        }
        if (i == 2) {
            return this.O.u();
        }
        if (i == 3) {
            return this.O.w();
        }
        if (i != 4) {
            return true;
        }
        return this.O.z();
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.dialog_seat_front_massage;
    }

    public void h0(LottieAnimationView lottieAnimationView, int i) {
        if (lottieAnimationView != null) {
            if (lottieAnimationView.getVisibility() == 8) {
                lottieAnimationView.setVisibility(0);
            }
            if (lottieAnimationView.isAnimating()) {
                lottieAnimationView.pauseAnimation();
            }
            lottieAnimationView.setAnimation(i);
            lottieAnimationView.loop(true);
            lottieAnimationView.playAnimation();
        }
    }

    public final void i0(int i) {
        int iY = Y(i);
        int iM = M(i);
        int i2 = c0() ? iM : iY;
        int i3 = c0() ? iY : iM;
        if (b0()) {
            I();
            E();
            return;
        }
        if (i == 0) {
            boolean zS = this.O.s();
            boolean zU = this.O.u();
            u0(zS, zU);
            if (zS) {
                j0(i2, this.O.i(i2));
            } else {
                E0(i2, 1, -1, true);
            }
            if (zU) {
                j0(i3, this.O.i(i3));
                return;
            } else {
                E0(i3, 1, -1, true);
                return;
            }
        }
        int i4 = this.i0;
        if (i4 == 2) {
            boolean zW = this.O.w();
            boolean z = this.O.z();
            q0(zW, z);
            if (zW) {
                j0(iY, this.O.i(iY));
            } else {
                E0(iY, 1, -1, true);
            }
            if (z) {
                j0(iM, this.O.i(iM));
                return;
            } else {
                E0(iM, 1, -1, true);
                return;
            }
        }
        if (i4 == 1) {
            boolean zV = this.O.v();
            boolean zY = this.O.y();
            p0(zV, zY);
            if (zV) {
                C0(iY, this.O.j(iY));
            } else {
                C0(iY, 1);
            }
            if (zY) {
                C0(iM, this.O.j(iM));
            } else {
                C0(iM, 1);
            }
        }
    }

    public void j0(int i, int i2) {
        B0(i, i2, this.O.j(i));
    }

    public void k0(int i, int i2) {
        D0(i, i2, this.O.i(i));
    }

    public final void l0() {
        x0(this.X);
        x0(this.W);
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.dialog_carsetting_right_seat_front_massage;
    }

    public final void m0(int i) {
        this.D.setSelected(i == 6);
        this.E.setSelected(i == 5);
        this.F.setSelected(i == 4);
        this.G.setSelected(i == 3);
        this.H.setSelected(i == 2);
        this.x.setSelected(i == 6);
        this.y.setSelected(i == 5);
        this.z.setSelected(i == 4);
        this.A.setSelected(i == 3);
        this.B.setSelected(i == 2);
    }

    public void n0() {
        this.c0 = new a();
        BYDAutoBodyworkDevice.getInstance(b.b.a.a.a.b().a()).registerListener(this.c0);
    }

    public final void o0(int i) {
        if (i == 0) {
            b.b.a.h.l.J(0, this.l, this.w, this.n0, this.o0, this.a0, this.Z);
            b.b.a.h.l.J(8, this.j0, this.k0);
        } else {
            b.b.a.h.l.J(this.i0 == 2 ? 0 : 4, this.l, this.w, this.n0, this.o0, this.a0, this.Z);
            b.b.a.h.l.J(this.i0 == 1 ? 0 : 8, this.j0, this.k0);
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getId() == R.id.ib_seat_driver_massage_toggle_left) {
            this.Z.setText(z ? "ON" : "OFF");
            if (z) {
                this.Z.setBackground(ContextCompat.getDrawable(this.P, R.drawable.vehicle_selector_dialog_blue));
                this.Z.setTextColor(ContextCompat.getColor(this.P, R.color.btn_only_selected));
                return;
            } else {
                this.Z.setBackground(ContextCompat.getDrawable(this.P, R.drawable.vehicle_selector_dialog_white));
                this.Z.setTextColor(ContextCompat.getColor(this.P, R.color.btn_only_unselected));
                return;
            }
        }
        if (compoundButton.getId() == R.id.ib_seat_copilot_massage_toggle_right) {
            this.a0.setChecked(z);
            this.a0.setText(z ? "ON" : "OFF");
            if (z) {
                this.a0.setBackground(ContextCompat.getDrawable(this.P, R.drawable.vehicle_selector_dialog_blue));
                this.a0.setTextColor(ContextCompat.getColor(this.P, R.color.btn_only_selected));
                return;
            } else {
                this.a0.setBackground(ContextCompat.getDrawable(this.P, R.drawable.vehicle_selector_dialog_white));
                this.a0.setTextColor(ContextCompat.getColor(this.P, R.color.btn_only_unselected));
                return;
            }
        }
        if (compoundButton.getId() == R.id.ib_seat_driver_massage_low_toggle_left) {
            this.l0.setText(z ? "ON" : "OFF");
            if (z) {
                this.l0.setBackground(ContextCompat.getDrawable(this.P, R.drawable.vehicle_selector_dialog_blue));
                this.l0.setTextColor(ContextCompat.getColor(this.P, R.color.btn_only_selected));
                return;
            } else {
                this.l0.setBackground(ContextCompat.getDrawable(this.P, R.drawable.vehicle_selector_dialog_white));
                this.l0.setTextColor(ContextCompat.getColor(this.P, R.color.btn_only_unselected));
                return;
            }
        }
        if (compoundButton.getId() == R.id.ib_seat_copilot_massage_low_toggle_right) {
            this.m0.setChecked(z);
            this.m0.setText(z ? "ON" : "OFF");
            if (z) {
                this.m0.setBackground(ContextCompat.getDrawable(this.P, R.drawable.vehicle_selector_dialog_blue));
                this.m0.setTextColor(ContextCompat.getColor(this.P, R.color.btn_only_selected));
            } else {
                this.m0.setBackground(ContextCompat.getDrawable(this.P, R.drawable.vehicle_selector_dialog_white));
                this.m0.setTextColor(ContextCompat.getColor(this.P, R.color.btn_only_unselected));
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int iY = Y(this.S);
        int iM = M(this.S);
        int id = view.getId();
        switch (id) {
            case R.id.carseat_massage_close_imge /* 2131296607 */:
                C();
                break;
            case R.id.carseat_massage_tips_imge /* 2131296608 */:
                w0();
                break;
            default:
                switch (id) {
                    case R.id.ib_seat_copilot_massage_low_toggle_right /* 2131297059 */:
                        b.b.g.r.e.k(V(iM), iM, 600);
                        if (!this.m0.isChecked()) {
                            this.O.O(M(this.S), 1);
                        } else {
                            this.O.O(M(this.S), 7);
                        }
                        break;
                    case R.id.ib_seat_copilot_massage_pluse /* 2131297060 */:
                        m0(3);
                        b.b.g.r.e.k(V(iM), iM, 600);
                        this.O.O(iM, 3);
                        break;
                    case R.id.ib_seat_copilot_massage_shoulder /* 2131297061 */:
                        m0(6);
                        b.b.g.r.e.k(V(iM), iM, 600);
                        this.O.O(iM, 6);
                        break;
                    case R.id.ib_seat_copilot_massage_stretch /* 2131297062 */:
                        m0(4);
                        b.b.g.r.e.k(V(iM), iM, 600);
                        this.O.O(iM, 4);
                        break;
                    case R.id.ib_seat_copilot_massage_toggle_right /* 2131297063 */:
                        b.b.g.r.e.k(V(iM), iM, 600);
                        if (!this.a0.isChecked()) {
                            this.O.O(M(this.S), 1);
                        } else {
                            this.O.O(M(this.S), 7);
                        }
                        break;
                    case R.id.ib_seat_copilot_massage_waist /* 2131297064 */:
                        m0(5);
                        b.b.g.r.e.k(V(iM), iM, 600);
                        this.O.O(iM, 5);
                        break;
                    case R.id.ib_seat_copilot_massage_wave /* 2131297065 */:
                        m0(2);
                        b.b.g.r.e.k(V(iM), iM, 600);
                        this.O.O(iM, 2);
                        break;
                    case R.id.ib_seat_driver_massage_low_toggle_left /* 2131297066 */:
                        b.b.g.r.e.k(V(iY), iY, 600);
                        if (!this.l0.isChecked()) {
                            this.O.O(Y(this.S), 1);
                        } else {
                            this.O.O(Y(this.S), 7);
                        }
                        break;
                    case R.id.ib_seat_driver_massage_pluse /* 2131297067 */:
                        r0(3);
                        b.b.g.r.e.k(V(iY), iY, 600);
                        this.O.O(iY, 3);
                        break;
                    case R.id.ib_seat_driver_massage_shoulder /* 2131297068 */:
                        r0(6);
                        b.b.g.r.e.k(V(iY), iY, 600);
                        this.O.O(iY, 6);
                        break;
                    case R.id.ib_seat_driver_massage_stretch /* 2131297069 */:
                        r0(4);
                        b.b.g.r.e.k(V(iY), iY, 600);
                        this.O.O(iY, 4);
                        break;
                    case R.id.ib_seat_driver_massage_toggle_left /* 2131297070 */:
                        b.b.g.r.e.k(V(iY), iY, 600);
                        if (!this.Z.isChecked()) {
                            this.O.O(Y(this.S), 1);
                        } else {
                            this.O.O(Y(this.S), 7);
                        }
                        break;
                    case R.id.ib_seat_driver_massage_waist /* 2131297071 */:
                        r0(5);
                        b.b.g.r.e.k(V(iY), iY, 600);
                        this.O.O(iY, 5);
                        break;
                    case R.id.ib_seat_driver_massage_wave /* 2131297072 */:
                        r0(2);
                        b.b.g.r.e.k(V(iY), iY, 600);
                        this.O.O(iY, 2);
                        break;
                }
                break;
        }
    }

    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i) {
        switch (view.getId()) {
            case R.id.carseat_seat_position /* 2131296617 */:
                this.S = i;
                o0(i);
                i0(i);
                t0(i);
                s0(i);
                this.O.M(this.S);
                break;
            case R.id.sbg_seat_copilot_massage /* 2131298073 */:
                this.J = i + 1;
                int iM = M(this.S);
                BydLogHelper.logD("Copilot onClickedButton pos=" + i + " coPosition : " + iM);
                b.b.g.r.e.k(Q(iM), iM, 600);
                this.O.N(iM, this.J);
                break;
            case R.id.sbg_seat_driver_massage /* 2131298074 */:
                this.I = i + 1;
                int iY = Y(this.S);
                BydLogHelper.logD("Driver onClickedButton pos=" + i + " drPosition : " + iY);
                b.b.g.r.e.k(Q(iY), iY, 600);
                this.O.N(iY, this.I);
                break;
        }
    }

    public final void p0(boolean z, boolean z2) {
        c0();
        if (z2) {
            G();
        } else {
            F();
        }
        if (z) {
            K();
        } else {
            J();
        }
    }

    public final void q0(boolean z, boolean z2) {
        c0();
        if (z2) {
            H();
        } else {
            E();
        }
        if (z) {
            L();
        } else {
            I();
        }
    }

    public final void r0(int i) {
        this.r.setSelected(i == 6);
        this.s.setSelected(i == 5);
        this.t.setSelected(i == 4);
        this.u.setSelected(i == 3);
        this.v.setSelected(i == 2);
        this.m.setSelected(i == 6);
        this.m.setColorFilter(SupportMenu.CATEGORY_MASK);
        this.n.setSelected(i == 5);
        this.o.setSelected(i == 4);
        this.p.setSelected(i == 3);
        this.q.setSelected(i == 2);
    }

    @Override // b.b.g.q.d.b
    public void s(Dialog dialog) {
        a0();
        f0(Z());
    }

    public final void s0(int i) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.h0.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.g0.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.W.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.X.getLayoutParams();
        int iB = b.b.a.i.a.b();
        int iP = P(iB);
        int iO = O(iB);
        layoutParams.width = iP;
        layoutParams.height = iO;
        layoutParams2.width = iP;
        layoutParams2.height = iO;
        this.h0.setLayoutParams(layoutParams);
        this.g0.setLayoutParams(layoutParams2);
        int iU = U(iB);
        int iT = T(iB, i);
        int iS = S(iB, i);
        int iR = R(iB);
        layoutParams3.setMargins(iT, iU, iS, iR);
        layoutParams4.setMargins(iT, iU, iS, iR);
        this.W.setLayoutParams(layoutParams3);
        this.X.setLayoutParams(layoutParams4);
    }

    @Override // b.b.g.q.d.b
    public void t(Dialog dialog) {
        b.b.a.h.l.F(this, this.m, this.n, this.o, this.p, this.q, this.x, this.y, this.z, this.A, this.B);
        b.b.a.h.l.t(this, this.w, this.l, this.R);
        b.b.a.h.l.F(this, this.i, this.j, this.Z, this.a0, this.l0, this.m0);
        b.b.a.h.l.E(this, this.a0, this.Z, this.l0, this.m0);
        n0();
    }

    public final void t0(int i) {
        TextView textView = this.T;
        Context context = this.P;
        int i2 = R.string.vehiclesettings_carseat_massage_passager_seat;
        textView.setText(context.getString(i == 0 ? c0() ? R.string.vehiclesettings_carseat_massage_passager_seat : R.string.vehiclesettings_carseat_massage_main_seat : R.string.vehiclesettings_carseat_massage_left_rear_seat));
        TextView textView2 = this.U;
        Context context2 = this.P;
        if (i != 0) {
            i2 = R.string.vehiclesettings_carseat_massage_right_rear_seat;
        } else if (c0()) {
            i2 = R.string.vehiclesettings_carseat_massage_main_seat;
        }
        textView2.setText(context2.getString(i2));
        b.b.a.h.l.b(this.g0, this.P, X(i));
        b.b.a.h.l.b(this.h0, this.P, X(i));
        b.b.a.h.l.K(i == 0 && e0(), this.b0);
    }

    @Override // b.b.g.q.d.b
    public void u(Dialog dialog) {
        this.Q = dialog;
        this.i = (ImageView) dialog.findViewById(R.id.carseat_massage_tips_imge);
        this.j = (ImageView) dialog.findViewById(R.id.carseat_massage_close_imge);
        BydLogHelper.logD("SeatFrontMassageFragment initView");
        this.l = (SegmentedButtonGroup) dialog.findViewById(R.id.sbg_seat_driver_massage);
        this.e0 = (RelativeLayout) dialog.findViewById(R.id.cl_seat_copilot_massage);
        this.g0 = (RelativeLayout) dialog.findViewById(R.id.cl_seat_copilot_massage_bg_rl);
        this.f0 = (RelativeLayout) dialog.findViewById(R.id.cl_seat_driver_massage);
        this.h0 = (RelativeLayout) dialog.findViewById(R.id.cl_seat_driver_massage_bg_rl);
        this.m = (ImageButton) dialog.findViewById(R.id.ib_seat_driver_massage_shoulder);
        this.n = (ImageButton) dialog.findViewById(R.id.ib_seat_driver_massage_waist);
        this.o = (ImageButton) dialog.findViewById(R.id.ib_seat_driver_massage_stretch);
        this.p = (ImageButton) dialog.findViewById(R.id.ib_seat_driver_massage_pluse);
        this.q = (ImageButton) dialog.findViewById(R.id.ib_seat_driver_massage_wave);
        this.r = (TextView) dialog.findViewById(R.id.tv_seat_driver_massage_shoulder);
        this.s = (TextView) dialog.findViewById(R.id.tv_seat_driver_massage_waist);
        this.t = (TextView) dialog.findViewById(R.id.tv_seat_driver_massage_stretch);
        this.u = (TextView) dialog.findViewById(R.id.tv_seat_driver_massage_pluse);
        this.v = (TextView) dialog.findViewById(R.id.tv_seat_driver_massage_wave);
        this.w = (SegmentedButtonGroup) dialog.findViewById(R.id.sbg_seat_copilot_massage);
        this.x = (ImageButton) dialog.findViewById(R.id.ib_seat_copilot_massage_shoulder);
        this.y = (ImageButton) dialog.findViewById(R.id.ib_seat_copilot_massage_waist);
        this.z = (ImageButton) dialog.findViewById(R.id.ib_seat_copilot_massage_stretch);
        this.A = (ImageButton) dialog.findViewById(R.id.ib_seat_copilot_massage_pluse);
        this.B = (ImageButton) dialog.findViewById(R.id.ib_seat_copilot_massage_wave);
        this.D = (TextView) dialog.findViewById(R.id.tv_seat_copilot_massage_shoulder);
        this.E = (TextView) dialog.findViewById(R.id.tv_seat_copilot_massage_waist);
        this.F = (TextView) dialog.findViewById(R.id.tv_seat_copilot_massage_stretch);
        this.G = (TextView) dialog.findViewById(R.id.tv_seat_copilot_massage_pluse);
        this.H = (TextView) dialog.findViewById(R.id.tv_seat_copilot_massage_wave);
        this.R = (SegmentedButtonGroup) dialog.findViewById(R.id.carseat_seat_position);
        this.T = (TextView) dialog.findViewById(R.id.start_seat_description);
        this.U = (TextView) dialog.findViewById(R.id.end_seat_description);
        this.W = (LottieAnimationView) dialog.findViewById(R.id.massage_lottie_left);
        this.X = (LottieAnimationView) dialog.findViewById(R.id.massage_lottie_right);
        this.Z = (CheckBox) dialog.findViewById(R.id.ib_seat_driver_massage_toggle_left);
        this.a0 = (CheckBox) dialog.findViewById(R.id.ib_seat_copilot_massage_toggle_right);
        this.b0 = (RelativeLayout) dialog.findViewById(R.id.massage_tips_container);
        this.d0 = (TextView) dialog.findViewById(R.id.carset_massage_title);
        this.j0 = (RelativeLayout) dialog.findViewById(R.id.massage_mode_left_low);
        this.n0 = (ConstraintLayout) dialog.findViewById(R.id.massage_mode_left);
        this.k0 = (RelativeLayout) dialog.findViewById(R.id.massage_mode_right_low);
        this.o0 = (ConstraintLayout) dialog.findViewById(R.id.massage_mode_right);
        this.l0 = (CheckBox) dialog.findViewById(R.id.ib_seat_driver_massage_low_toggle_left);
        this.m0 = (CheckBox) dialog.findViewById(R.id.ib_seat_copilot_massage_low_toggle_right);
        b.b.a.h.l.K(this.O.r() && this.O.x(), this.R);
        b.b.a.h.l.K((this.O.r() && this.O.x()) ? false : true, this.d0);
    }

    public final void u0(boolean z, boolean z2) {
        boolean zC0 = c0();
        if (z) {
            if (zC0) {
                H();
            } else {
                L();
            }
        } else if (zC0) {
            E();
        } else {
            I();
        }
        if (z2) {
            if (zC0) {
                L();
                return;
            } else {
                H();
                return;
            }
        }
        if (zC0) {
            I();
        } else {
            E();
        }
    }

    public void v0() {
        u.c(this.P, "massage_dialog", 1);
        y(b.b.o.b.b.a.a(this));
        k.a("carseat_massage_mode");
    }

    @Override // b.b.g.q.d.b
    public void w() {
        super.w();
        B();
        l0();
        u.c(this.P, "massage_dialog", 2);
    }

    public void w0() {
        u.c(this.P, "massage_dialog", 2);
        if (this.V == null) {
            this.V = new d(this.P);
        }
        this.V.B();
    }

    @Override // b.b.g.q.d.b
    public void x() {
        super.x();
        B();
        l0();
        u.c(this.P, "massage_dialog", 2);
        k.e("carseat_massage_mode");
        this.Q = null;
    }

    public final void x0(LottieAnimationView lottieAnimationView) {
        if (lottieAnimationView == null || !lottieAnimationView.isAnimating()) {
            return;
        }
        lottieAnimationView.pauseAnimation();
        lottieAnimationView.cancelAnimation();
        lottieAnimationView.clearAnimation();
        lottieAnimationView.setVisibility(8);
    }

    public void y0() {
        BYDAutoBodyworkDevice.getInstance(b.b.a.a.a.b().a()).unregisterListener(this.c0);
    }

    public final void z0(int i, int i2) {
        if (i <= 1 || i >= 7) {
            x0(this.X);
        } else {
            if (i2 < 1 || i2 > 3) {
                return;
            }
            h0(this.X, b.b.a.h.b.a(this.P, this.Y[i - 2][i2 - 1]));
        }
    }
}
