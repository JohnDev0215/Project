package b.b.g.q.d;

import android.app.Dialog;
import android.content.Context;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.base.view.BydTouchImageView;
import com.byd.base.view.scrollRelativeLayout.BydScrollRelativeLayout;
import com.byd.base.view.segmentedbutton.SegmentedButton;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: BydCarSeatNewDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends b implements View.OnClickListener, BydTouchImageView.c, SegmentedButtonGroup.f, View.OnLongClickListener, b.b.o.b.a.a, b.b.a.j.e.b {
    public RelativeLayout A;
    public TextView A0;
    public RelativeLayout B;
    public RotateAnimation B0;
    public boolean C0;
    public ImageView D;
    public boolean D0;
    public ImageView E;
    public boolean E0;
    public Context F;
    public boolean F0;
    public BydTouchImageView G;
    public int G0;
    public BydTouchImageView H;
    public int H0;
    public BydTouchImageView I;
    public int I0;
    public BydTouchImageView J;
    public int J0;
    public BydTouchImageView K;
    public int K0;
    public BydTouchImageView L;
    public ImageView L0;
    public BydTouchImageView M;
    public int M0;
    public BydTouchImageView N;
    public TextView N0;
    public BydTouchImageView O;
    public RelativeLayout O0;
    public BydTouchImageView P;
    public ImageView P0;
    public BydTouchImageView Q;
    public BydScrollRelativeLayout Q0;
    public BydTouchImageView R;
    public ImageView R0;
    public BydTouchImageView S;
    public BydScrollRelativeLayout S0;
    public BydTouchImageView T;
    public BydScrollRelativeLayout T0;
    public BydTouchImageView U;
    public ImageView U0;
    public BydTouchImageView V;
    public BydScrollRelativeLayout V0;
    public BydTouchImageView W;
    public ImageView W0;
    public BydTouchImageView X;
    public BydScrollRelativeLayout X0;
    public n Y;
    public ImageView Y0;
    public n Z;
    public BydScrollRelativeLayout Z0;
    public n a0;
    public ImageView a1;
    public n b0;
    public int b1;
    public n c0;
    public n d0;
    public String e0;
    public b.b.g.n.h.d f0;
    public int g0;
    public int h0;
    public Dialog i;
    public int i0;
    public ImageView j;
    public int j0;
    public ImageView k;
    public SegmentedButtonGroup k0;
    public ImageView l;
    public SegmentedButton l0;
    public ImageView m;
    public SegmentedButton m0;
    public ImageView n;
    public SegmentedButton n0;
    public ImageView o;
    public SegmentedButton o0;
    public LottieAnimationView p;
    public int[] p0;
    public LottieAnimationView q;
    public RelativeLayout q0;
    public LottieAnimationView r;
    public RelativeLayout r0;
    public LottieAnimationView s;
    public RelativeLayout s0;
    public LottieAnimationView t;
    public TextView t0;
    public LottieAnimationView u;
    public ImageView u0;
    public int v;
    public TextView v0;
    public RelativeLayout w;
    public ImageView w0;
    public RelativeLayout x;
    public TextView x0;
    public RelativeLayout y;
    public ImageView y0;
    public RelativeLayout z;
    public TextView z0;

    /* JADX INFO: renamed from: b.b.g.q.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydCarSeatNewDialog.java */
    public class AnimationAnimationListenerC0066a implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ImageView f1434a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ TextView f1435b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1436c;

        public AnimationAnimationListenerC0066a(ImageView imageView, TextView textView, int i) {
            this.f1434a = imageView;
            this.f1435b = textView;
            this.f1436c = i;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            ImageView imageView = this.f1434a;
            if (imageView != null) {
                imageView.setVisibility(4);
            }
            TextView textView = this.f1435b;
            if (textView != null) {
                textView.setVisibility(0);
            }
            a.this.e0(this.f1436c, false);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            ImageView imageView = this.f1434a;
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            TextView textView = this.f1435b;
            if (textView != null) {
                textView.setVisibility(4);
            }
        }
    }

    public a(Context context) {
        super(context);
        this.v = -1;
        this.p0 = new int[]{R.string.vehiclesettings_seat_turn_main, R.string.vehiclesettings_seat_turn_passagenger, R.string.vehiclesettings_seat_turn_behind_left, R.string.vehiclesettings_seat_turn_behind_right};
        this.C0 = false;
        this.D0 = false;
        this.E0 = false;
        this.F0 = false;
        this.G0 = -1;
        this.H0 = -1;
        this.I0 = -1;
        this.J0 = -1;
        this.K0 = -1;
        this.M0 = 0;
        this.F = context;
    }

    public final void A() {
        int i = this.G0;
        if (i == 1) {
            t0(this.u0, this.t0);
        } else if (i == 2) {
            t0(this.w0, this.v0);
        } else if (i == 3) {
            t0(this.y0, this.x0);
        }
        this.G0 = -1;
        e0(B(), false);
    }

    public int B() {
        return C(this.k0.getPosition());
    }

    public int C(int i) {
        if (i == 0) {
            return ((Integer) this.l0.getTag()).intValue();
        }
        if (i == 1) {
            return ((Integer) this.m0.getTag()).intValue();
        }
        if (i == 2) {
            return ((Integer) this.n0.getTag()).intValue();
        }
        if (i != 3) {
            return 0;
        }
        return ((Integer) this.o0.getTag()).intValue();
    }

    public void D() {
        int i;
        int iG = this.f0.g();
        int i2 = 0;
        int i3 = 1;
        while (true) {
            if (i3 > 4) {
                break;
            }
            if (this.f0.l(i3)) {
                if (iG == i3) {
                    this.M0 = i2;
                    this.b1 = iG;
                }
                i2++;
                F(i3, i2);
            }
            i3++;
        }
        b.b.a.h.l.K(i2 > 1, this.k0);
        b.b.a.h.l.K(i2 <= 1, this.N0);
        if (i2 > 1) {
            b.b.a.h.l.J(i2 > 2 ? 0 : 8, this.n0);
            b.b.a.h.l.J(i2 > 3 ? 0 : 8, this.o0);
            for (i = 4; i > i2; i--) {
                this.k0.t(i - 1);
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.k0.getLayoutParams();
            layoutParams.width = ((int) this.F.getResources().getDimension(R.dimen.dp_120)) * i2;
            this.k0.setLayoutParams(layoutParams);
            b.b.a.h.l.t(this, this.k0);
        }
    }

    public final boolean E(int i) {
        if (i == 1) {
            return this.C0;
        }
        if (i == 2) {
            return this.D0;
        }
        if (i == 3) {
            return this.E0;
        }
        if (i != 4) {
            return false;
        }
        return this.F0;
    }

    public void F(int i, int i2) {
        if (i2 == 1) {
            this.l0.setTag(Integer.valueOf(i));
            this.l0.setText(this.F.getResources().getString(this.p0[i - 1]));
        } else if (i2 == 2) {
            this.m0.setTag(Integer.valueOf(i));
            this.m0.setText(this.F.getResources().getString(this.p0[i - 1]));
        } else if (i2 == 3) {
            this.n0.setTag(Integer.valueOf(i));
            this.n0.setText(this.F.getResources().getString(this.p0[i - 1]));
        } else if (i2 == 4) {
            this.o0.setTag(Integer.valueOf(i));
            this.o0.setText(this.F.getResources().getString(this.p0[i - 1]));
        }
        this.N0.setText(this.F.getResources().getString(this.p0[i - 1]));
    }

    public final void G(int i, int i2) {
        if (i == 1) {
            Settings.System.putInt(this.F.getContentResolver(), "main_recover_position", i2);
            return;
        }
        if (i == 2) {
            Settings.System.putInt(this.F.getContentResolver(), "passenger_recover_position", i2);
        } else if (i == 3) {
            Settings.System.putInt(this.F.getContentResolver(), "left_rear_recover_position", i2);
        } else {
            if (i != 4) {
                return;
            }
            Settings.System.putInt(this.F.getContentResolver(), "right_rear_recover_position", i2);
        }
    }

    public final void H(int i) {
        if (i == 0) {
            U();
        } else if (i == 2) {
            O();
        } else if (i == 4) {
            K();
        } else if (i == 6) {
            S();
        } else if (i == 8) {
            M();
        } else if (i == 10) {
            Q();
        }
        this.v = i;
    }

    public final void I(int i) {
        int i2 = this.v;
        c0(i2);
        if (i == 0) {
            if (i2 != 0) {
                H(0);
                return;
            }
            return;
        }
        if (i == 1) {
            if (i2 != 2) {
                H(2);
                return;
            }
            return;
        }
        if (i == 2) {
            if (i2 != 4) {
                H(4);
            }
        } else if (i == 3) {
            if (i2 != 6) {
                H(6);
            }
        } else if (i == 4) {
            if (i2 != 8) {
                H(8);
            }
        } else if (i == 5 && i2 != 10) {
            H(10);
        }
    }

    public final void J() {
        this.r.setVisibility(0);
        this.r.setAnimation(b.b.a.h.b.a(this.F, R.attr.main_seat_backrest_close));
        this.a0.d(false, false);
        this.r.playAnimation();
        h0(this.b1);
    }

    public final void K() {
        this.r.setVisibility(0);
        this.l.setVisibility(4);
        this.r.setAnimation(b.b.a.h.b.a(this.F, R.attr.main_seat_backrest_open));
        this.a0.d(false, true);
        this.r.playAnimation();
        ImageView imageView = this.E;
        Context context = this.F;
        b.b.a.h.l.b(imageView, context, b.b.a.h.b.a(context, this.b1 > 2 ? R.attr.img_main_back_seat_backrest : R.attr.img_main_seat_backrest));
    }

    public final void L() {
        this.t.setVisibility(0);
        this.t.setAnimation(b.b.a.h.b.a(this.F, R.attr.main_seat_cushion_close));
        this.c0.d(false, false);
        this.t.playAnimation();
        h0(this.b1);
    }

    public final void M() {
        this.t.setVisibility(0);
        this.n.setVisibility(4);
        this.t.setAnimation(b.b.a.h.b.a(this.F, R.attr.main_seat_cushion_open));
        this.c0.d(false, true);
        this.t.playAnimation();
        ImageView imageView = this.E;
        Context context = this.F;
        b.b.a.h.l.b(imageView, context, b.b.a.h.b.a(context, this.b1 > 2 ? R.attr.img_main_back_seat_cushion : R.attr.img_main_seat_cushion));
    }

    public final void N() {
        this.q.setVisibility(0);
        this.q.setAnimation(this.h0);
        this.Z.d(false, false);
        this.q.playAnimation();
        h0(this.b1);
    }

    public final void O() {
        this.q.setVisibility(0);
        this.k.setVisibility(4);
        this.q.setAnimation(this.g0);
        this.Z.d(false, true);
        this.q.playAnimation();
        ImageView imageView = this.E;
        Context context = this.F;
        b.b.a.h.l.b(imageView, context, b.b.a.h.b.a(context, this.b1 > 2 ? R.attr.img_main_back_seat_headrest : R.attr.img_main_seat_headrest));
    }

    public final void P() {
        this.u.setVisibility(0);
        this.u.setAnimation(b.b.a.h.b.a(this.F, R.attr.main_seat_leg_close));
        this.d0.d(false, false);
        this.u.playAnimation();
        h0(this.b1);
    }

    public final void Q() {
        this.u.setVisibility(0);
        this.o.setVisibility(4);
        this.u.setAnimation(b.b.a.h.b.a(this.F, R.attr.main_seat_leg_open));
        this.d0.d(false, true);
        this.u.playAnimation();
        ImageView imageView = this.E;
        Context context = this.F;
        b.b.a.h.l.b(imageView, context, b.b.a.h.b.a(context, this.b1 > 2 ? R.attr.img_main_back_seat_leg : R.attr.img_main_seat_leg));
    }

    public final void R() {
        this.s.setVisibility(0);
        this.s.setAnimation(this.j0);
        this.b0.d(false, false);
        this.s.playAnimation();
        h0(this.b1);
    }

    public final void S() {
        this.s.setVisibility(0);
        this.m.setVisibility(4);
        this.s.setAnimation(this.i0);
        this.b0.d(false, true);
        this.s.playAnimation();
        ImageView imageView = this.E;
        Context context = this.F;
        b.b.a.h.l.b(imageView, context, b.b.a.h.b.a(context, this.b1 > 2 ? R.attr.img_main_back_seat_all : R.attr.img_main_seat_all));
    }

    public final void T() {
        this.p.setVisibility(0);
        this.p.setAnimation(b.b.a.h.b.a(this.F, R.attr.seat_arrow_wheel_close));
        ImageView imageView = this.D;
        Context context = this.F;
        b.b.a.h.l.b(imageView, context, b.b.a.h.b.a(context, R.attr.img_wheel_normal));
        this.Y.d(false, false);
        this.p.playAnimation();
    }

    public final void U() {
        this.p.setVisibility(0);
        this.j.setVisibility(4);
        ImageView imageView = this.D;
        Context context = this.F;
        b.b.a.h.l.b(imageView, context, b.b.a.h.b.a(context, R.attr.img_wheel_background));
        this.Y.d(false, true);
        this.p.setAnimation(b.b.a.h.b.a(this.F, R.attr.seat_arrow_wheel_open));
        this.p.playAnimation();
    }

    public final void V() {
        n nVar = new n();
        this.a0 = nVar;
        this.r.addAnimatorListener(nVar);
    }

    public final void W() {
        n nVar = new n();
        this.c0 = nVar;
        this.t.addAnimatorListener(nVar);
    }

    public final void X() {
        n nVar = new n();
        this.Z = nVar;
        this.q.addAnimatorListener(nVar);
    }

    public final void Y() {
        n nVar = new n();
        this.d0 = nVar;
        this.u.addAnimatorListener(nVar);
        this.s.addAnimatorListener(this.b0);
    }

    public final void Z() {
        a0();
        X();
        V();
        b0();
        W();
        Y();
    }

    @Override // com.byd.base.view.BydTouchImageView.c
    public void a(int i) {
        switch (i) {
            case R.id.back_turn_back /* 2131296420 */:
                this.f0.T(B(), false);
                break;
            case R.id.back_turn_front /* 2131296421 */:
                this.f0.U(B(), false);
                break;
            case R.id.cushion_turn_down /* 2131296796 */:
                this.f0.X(B(), false);
                break;
            case R.id.cushion_turn_up /* 2131296797 */:
                this.f0.Y(B(), false);
                break;
            case R.id.headrest_turn_down /* 2131297028 */:
                this.f0.H(B(), false);
                break;
            case R.id.headrest_turn_left /* 2131297029 */:
                this.f0.K(B(), false);
                break;
            case R.id.headrest_turn_right /* 2131297030 */:
                this.f0.J(B(), false);
                break;
            case R.id.headrest_turn_up /* 2131297031 */:
                this.f0.I(B(), false);
                break;
            case R.id.leg_turn_back /* 2131297389 */:
                this.f0.V(B(), false);
                break;
            case R.id.leg_turn_front /* 2131297390 */:
                this.f0.W(B(), false);
                break;
            case R.id.seat_turn_back /* 2131298104 */:
                this.f0.R(B(), false);
                break;
            case R.id.seat_turn_down /* 2131298105 */:
                this.f0.P(B(), false);
                break;
            case R.id.seat_turn_front /* 2131298106 */:
                this.f0.S(B(), false);
                break;
            case R.id.seat_turn_up /* 2131298107 */:
                this.f0.Q(B(), false);
                break;
            case R.id.wheel_turn_back_iv /* 2131298932 */:
                this.f0.b0(false);
                break;
            case R.id.wheel_turn_down_iv /* 2131298933 */:
                this.f0.a0(false);
                break;
            case R.id.wheel_turn_front_iv /* 2131298934 */:
                this.f0.Z(false);
                break;
            case R.id.wheel_turn_up_iv /* 2131298935 */:
                this.f0.c0(false);
                break;
        }
    }

    public final void a0() {
        n nVar = new n();
        this.Y = nVar;
        this.p.addAnimatorListener(nVar);
    }

    @Override // b.b.a.j.e.b
    public void b(int i) {
        b.b.a.h.a.a(this, " moveRight : " + i);
        switch (i) {
            case R.id.carseat_turn_back_lav_ll /* 2131296623 */:
                this.f0.T(B(), true);
                BydTouchImageView bydTouchImageView = this.P;
                Context context = this.F;
                b.b.a.h.l.b(bydTouchImageView, context, b.b.a.h.b.a(context, R.attr.ic_arrow_backrest_unfold2_hover));
                break;
            case R.id.carseat_turn_head_lav_ll /* 2131296633 */:
                this.f0.J(B(), true);
                BydTouchImageView bydTouchImageView2 = this.N;
                Context context2 = this.F;
                b.b.a.h.l.b(bydTouchImageView2, context2, b.b.a.h.b.a(context2, R.attr.ic_arrow_four_unfold4_hover));
                break;
            case R.id.carseat_turn_leg_lav_ll /* 2131296639 */:
                this.f0.V(B(), true);
                BydTouchImageView bydTouchImageView3 = this.X;
                Context context3 = this.F;
                b.b.a.h.l.b(bydTouchImageView3, context3, b.b.a.h.b.a(context3, R.attr.ic_arrow_leg_unfold2_hover));
                break;
            case R.id.carseat_turn_normal_lav_ll /* 2131296644 */:
                this.f0.R(B(), true);
                BydTouchImageView bydTouchImageView4 = this.R;
                Context context4 = this.F;
                b.b.a.h.l.b(bydTouchImageView4, context4, b.b.a.h.b.a(context4, R.attr.ic_arrow_four_unfold4_hover));
                break;
            case R.id.carseat_turn_sw_lav_ll /* 2131296648 */:
                this.f0.b0(true);
                BydTouchImageView bydTouchImageView5 = this.J;
                Context context5 = this.F;
                b.b.a.h.l.b(bydTouchImageView5, context5, b.b.a.h.b.a(context5, R.attr.ic_arrow_wheel_spread4_hover));
                break;
        }
    }

    public final void b0() {
        n nVar = new n();
        this.b0 = nVar;
        this.s.addAnimatorListener(nVar);
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.dialog_carseat_rtl_new;
    }

    public final void c0(int i) {
        if (i == 0) {
            T();
        } else if (i == 2) {
            N();
        } else if (i == 4) {
            J();
        } else if (i == 6) {
            R();
        } else if (i == 8) {
            L();
        } else if (i == 10) {
            P();
        }
        this.v = -1;
    }

    @Override // b.b.a.j.e.b
    public void d(int i) {
        b.b.a.h.a.a(this, " moveValid : " + i);
        switch (i) {
            case R.id.carseat_turn_back_lav_ll /* 2131296623 */:
                this.f0.U(B(), false);
                b.b.a.h.l.c(this.O, null);
                b.b.a.h.l.c(this.P, null);
                break;
            case R.id.carseat_turn_cushion_lav_ll /* 2131296628 */:
                b.b.a.h.l.c(this.U, null);
                b.b.a.h.l.c(this.V, null);
                this.f0.X(B(), false);
                break;
            case R.id.carseat_turn_head_lav_ll /* 2131296633 */:
                this.f0.H(B(), false);
                this.f0.K(B(), false);
                b.b.a.h.l.c(this.K, null);
                b.b.a.h.l.c(this.L, null);
                b.b.a.h.l.c(this.M, null);
                b.b.a.h.l.c(this.N, null);
                break;
            case R.id.carseat_turn_leg_lav_ll /* 2131296639 */:
                b.b.a.h.l.c(this.W, null);
                b.b.a.h.l.c(this.X, null);
                this.f0.V(B(), false);
                break;
            case R.id.carseat_turn_normal_lav_ll /* 2131296644 */:
                this.f0.P(B(), false);
                this.f0.S(B(), false);
                b.b.a.h.l.c(this.Q, null);
                b.b.a.h.l.c(this.R, null);
                b.b.a.h.l.c(this.S, null);
                b.b.a.h.l.c(this.T, null);
                break;
            case R.id.carseat_turn_sw_lav_ll /* 2131296648 */:
                this.f0.a0(false);
                this.f0.b0(false);
                b.b.a.h.l.c(this.G, null);
                b.b.a.h.l.c(this.H, null);
                b.b.a.h.l.c(this.I, null);
                b.b.a.h.l.c(this.J, null);
                break;
        }
    }

    public void d0(String str) {
        this.e0 = str;
    }

    @Override // com.byd.base.view.BydTouchImageView.c
    public void e(int i) {
        switch (i) {
            case R.id.back_turn_back /* 2131296420 */:
                BydTouchImageView bydTouchImageView = this.P;
                Context context = this.F;
                b.b.a.h.l.b(bydTouchImageView, context, b.b.a.h.b.a(context, R.attr.ic_arrow_backrest_unfold2_hover));
                this.f0.T(B(), true);
                break;
            case R.id.back_turn_front /* 2131296421 */:
                BydTouchImageView bydTouchImageView2 = this.O;
                Context context2 = this.F;
                b.b.a.h.l.b(bydTouchImageView2, context2, b.b.a.h.b.a(context2, R.attr.ic_arrow_backrest_unfold1_hover));
                this.f0.U(B(), true);
                break;
            case R.id.cushion_turn_down /* 2131296796 */:
                BydTouchImageView bydTouchImageView3 = this.V;
                Context context3 = this.F;
                b.b.a.h.l.b(bydTouchImageView3, context3, b.b.a.h.b.a(context3, R.attr.ic_arrow_cushion_unfold2_hover));
                this.f0.X(B(), true);
                break;
            case R.id.cushion_turn_up /* 2131296797 */:
                BydTouchImageView bydTouchImageView4 = this.U;
                Context context4 = this.F;
                b.b.a.h.l.b(bydTouchImageView4, context4, b.b.a.h.b.a(context4, R.attr.ic_arrow_cushion_unfold1_hover));
                this.f0.Y(B(), true);
                break;
            case R.id.headrest_turn_down /* 2131297028 */:
                BydTouchImageView bydTouchImageView5 = this.L;
                Context context5 = this.F;
                b.b.a.h.l.b(bydTouchImageView5, context5, b.b.a.h.b.a(context5, R.attr.ic_arrow_four_unfold2_hover));
                this.f0.H(B(), true);
                break;
            case R.id.headrest_turn_left /* 2131297029 */:
                BydTouchImageView bydTouchImageView6 = this.M;
                Context context6 = this.F;
                b.b.a.h.l.b(bydTouchImageView6, context6, b.b.a.h.b.a(context6, R.attr.ic_arrow_four_unfold3_hover));
                this.f0.K(B(), true);
                break;
            case R.id.headrest_turn_right /* 2131297030 */:
                BydTouchImageView bydTouchImageView7 = this.N;
                Context context7 = this.F;
                b.b.a.h.l.b(bydTouchImageView7, context7, b.b.a.h.b.a(context7, R.attr.ic_arrow_four_unfold4_hover));
                this.f0.J(B(), true);
                break;
            case R.id.headrest_turn_up /* 2131297031 */:
                BydTouchImageView bydTouchImageView8 = this.K;
                Context context8 = this.F;
                b.b.a.h.l.b(bydTouchImageView8, context8, b.b.a.h.b.a(context8, R.attr.ic_arrow_four_unfold1_hover));
                this.f0.I(B(), true);
                break;
            case R.id.leg_turn_back /* 2131297389 */:
                BydTouchImageView bydTouchImageView9 = this.X;
                Context context9 = this.F;
                b.b.a.h.l.b(bydTouchImageView9, context9, b.b.a.h.b.a(context9, R.attr.ic_arrow_leg_unfold2_hover));
                this.f0.V(B(), true);
                break;
            case R.id.leg_turn_front /* 2131297390 */:
                BydTouchImageView bydTouchImageView10 = this.W;
                Context context10 = this.F;
                b.b.a.h.l.b(bydTouchImageView10, context10, b.b.a.h.b.a(context10, R.attr.ic_arrow_leg_unfold1_hover));
                this.f0.W(B(), true);
                break;
            case R.id.seat_turn_back /* 2131298104 */:
                BydTouchImageView bydTouchImageView11 = this.R;
                Context context11 = this.F;
                b.b.a.h.l.b(bydTouchImageView11, context11, b.b.a.h.b.a(context11, R.attr.ic_arrow_four_unfold4_hover));
                this.f0.R(B(), true);
                break;
            case R.id.seat_turn_down /* 2131298105 */:
                BydTouchImageView bydTouchImageView12 = this.T;
                Context context12 = this.F;
                b.b.a.h.l.b(bydTouchImageView12, context12, b.b.a.h.b.a(context12, R.attr.ic_arrow_four_unfold2_hover));
                this.f0.P(B(), true);
                break;
            case R.id.seat_turn_front /* 2131298106 */:
                BydTouchImageView bydTouchImageView13 = this.Q;
                Context context13 = this.F;
                b.b.a.h.l.b(bydTouchImageView13, context13, b.b.a.h.b.a(context13, R.attr.ic_arrow_four_unfold3_hover));
                this.f0.S(B(), true);
                break;
            case R.id.seat_turn_up /* 2131298107 */:
                BydTouchImageView bydTouchImageView14 = this.S;
                Context context14 = this.F;
                b.b.a.h.l.b(bydTouchImageView14, context14, b.b.a.h.b.a(context14, R.attr.ic_arrow_four_unfold1_hover));
                this.f0.Q(B(), true);
                break;
            case R.id.wheel_turn_back_iv /* 2131298932 */:
                BydTouchImageView bydTouchImageView15 = this.J;
                Context context15 = this.F;
                b.b.a.h.l.b(bydTouchImageView15, context15, b.b.a.h.b.a(context15, R.attr.ic_arrow_wheel_spread4_hover));
                this.f0.b0(true);
                break;
            case R.id.wheel_turn_down_iv /* 2131298933 */:
                BydTouchImageView bydTouchImageView16 = this.H;
                Context context16 = this.F;
                b.b.a.h.l.b(bydTouchImageView16, context16, b.b.a.h.b.a(context16, R.attr.ic_arrow_wheel_spread3_hover));
                this.f0.a0(true);
                break;
            case R.id.wheel_turn_front_iv /* 2131298934 */:
                BydTouchImageView bydTouchImageView17 = this.I;
                Context context17 = this.F;
                b.b.a.h.l.b(bydTouchImageView17, context17, b.b.a.h.b.a(context17, R.attr.ic_arrow_wheel_spread2_hover));
                this.f0.Z(true);
                break;
            case R.id.wheel_turn_up_iv /* 2131298935 */:
                BydTouchImageView bydTouchImageView18 = this.G;
                Context context18 = this.F;
                b.b.a.h.l.b(bydTouchImageView18, context18, b.b.a.h.b.a(context18, R.attr.ic_arrow_wheel_spread1_hover));
                this.f0.c0(true);
                break;
        }
    }

    public final void e0(int i, boolean z) {
        if (i == 1) {
            this.C0 = z;
            return;
        }
        if (i == 2) {
            this.D0 = z;
        } else if (i == 3) {
            this.E0 = z;
        } else {
            if (i != 4) {
                return;
            }
            this.F0 = z;
        }
    }

    @Override // com.byd.base.view.BydTouchImageView.c
    public void f(int i) {
        switch (i) {
            case R.id.back_turn_back /* 2131296420 */:
                BydTouchImageView bydTouchImageView = this.P;
                Context context = this.F;
                b.b.a.h.l.b(bydTouchImageView, context, b.b.a.h.b.a(context, R.attr.ic_arrow_backrest_unfold2_normal));
                break;
            case R.id.back_turn_front /* 2131296421 */:
                BydTouchImageView bydTouchImageView2 = this.O;
                Context context2 = this.F;
                b.b.a.h.l.b(bydTouchImageView2, context2, b.b.a.h.b.a(context2, R.attr.ic_arrow_backrest_unfold1_normal));
                break;
            case R.id.cushion_turn_down /* 2131296796 */:
                BydTouchImageView bydTouchImageView3 = this.V;
                Context context3 = this.F;
                b.b.a.h.l.b(bydTouchImageView3, context3, b.b.a.h.b.a(context3, R.attr.ic_arrow_cushion_unfold2_normal));
                break;
            case R.id.cushion_turn_up /* 2131296797 */:
                BydTouchImageView bydTouchImageView4 = this.U;
                Context context4 = this.F;
                b.b.a.h.l.b(bydTouchImageView4, context4, b.b.a.h.b.a(context4, R.attr.ic_arrow_cushion_unfold1_normal));
                break;
            case R.id.headrest_turn_down /* 2131297028 */:
                BydTouchImageView bydTouchImageView5 = this.L;
                Context context5 = this.F;
                b.b.a.h.l.b(bydTouchImageView5, context5, b.b.a.h.b.a(context5, R.attr.ic_arrow_four_unfold2_normal));
                break;
            case R.id.headrest_turn_left /* 2131297029 */:
                BydTouchImageView bydTouchImageView6 = this.M;
                Context context6 = this.F;
                b.b.a.h.l.b(bydTouchImageView6, context6, b.b.a.h.b.a(context6, R.attr.ic_arrow_four_unfold3_normal));
                break;
            case R.id.headrest_turn_right /* 2131297030 */:
                BydTouchImageView bydTouchImageView7 = this.N;
                Context context7 = this.F;
                b.b.a.h.l.b(bydTouchImageView7, context7, b.b.a.h.b.a(context7, R.attr.ic_arrow_four_unfold4_normal));
                break;
            case R.id.headrest_turn_up /* 2131297031 */:
                BydTouchImageView bydTouchImageView8 = this.K;
                Context context8 = this.F;
                b.b.a.h.l.b(bydTouchImageView8, context8, b.b.a.h.b.a(context8, R.attr.ic_arrow_four_unfold1_normal));
                break;
            case R.id.leg_turn_back /* 2131297389 */:
                BydTouchImageView bydTouchImageView9 = this.X;
                Context context9 = this.F;
                b.b.a.h.l.b(bydTouchImageView9, context9, b.b.a.h.b.a(context9, R.attr.ic_arrow_leg_unfold2_normal));
                break;
            case R.id.leg_turn_front /* 2131297390 */:
                BydTouchImageView bydTouchImageView10 = this.W;
                Context context10 = this.F;
                b.b.a.h.l.b(bydTouchImageView10, context10, b.b.a.h.b.a(context10, R.attr.ic_arrow_leg_unfold1_normal));
                break;
            case R.id.seat_turn_back /* 2131298104 */:
                BydTouchImageView bydTouchImageView11 = this.R;
                Context context11 = this.F;
                b.b.a.h.l.b(bydTouchImageView11, context11, b.b.a.h.b.a(context11, R.attr.ic_arrow_four_unfold4_normal));
                break;
            case R.id.seat_turn_down /* 2131298105 */:
                BydTouchImageView bydTouchImageView12 = this.T;
                Context context12 = this.F;
                b.b.a.h.l.b(bydTouchImageView12, context12, b.b.a.h.b.a(context12, R.attr.ic_arrow_four_unfold2_normal));
                break;
            case R.id.seat_turn_front /* 2131298106 */:
                BydTouchImageView bydTouchImageView13 = this.Q;
                Context context13 = this.F;
                b.b.a.h.l.b(bydTouchImageView13, context13, b.b.a.h.b.a(context13, R.attr.ic_arrow_four_unfold3_normal));
                break;
            case R.id.seat_turn_up /* 2131298107 */:
                BydTouchImageView bydTouchImageView14 = this.S;
                Context context14 = this.F;
                b.b.a.h.l.b(bydTouchImageView14, context14, b.b.a.h.b.a(context14, R.attr.ic_arrow_four_unfold1_normal));
                break;
            case R.id.wheel_turn_back_iv /* 2131298932 */:
                BydTouchImageView bydTouchImageView15 = this.J;
                Context context15 = this.F;
                b.b.a.h.l.b(bydTouchImageView15, context15, b.b.a.h.b.a(context15, R.attr.ic_arrow_wheel_spread4_normal));
                break;
            case R.id.wheel_turn_down_iv /* 2131298933 */:
                BydTouchImageView bydTouchImageView16 = this.H;
                Context context16 = this.F;
                b.b.a.h.l.b(bydTouchImageView16, context16, b.b.a.h.b.a(context16, R.attr.ic_arrow_wheel_spread3_normal));
                break;
            case R.id.wheel_turn_front_iv /* 2131298934 */:
                BydTouchImageView bydTouchImageView17 = this.I;
                Context context17 = this.F;
                b.b.a.h.l.b(bydTouchImageView17, context17, b.b.a.h.b.a(context17, R.attr.ic_arrow_wheel_spread2_normal));
                break;
            case R.id.wheel_turn_up_iv /* 2131298935 */:
                BydTouchImageView bydTouchImageView18 = this.G;
                Context context18 = this.F;
                b.b.a.h.l.b(bydTouchImageView18, context18, b.b.a.h.b.a(context18, R.attr.ic_arrow_wheel_spread1_normal));
                break;
        }
    }

    public final void f0(int i) {
        k0(-1);
    }

    @Override // b.b.a.j.e.b
    public void g(int i) {
        b.b.a.h.a.a(this, " moveUp : " + i);
        switch (i) {
            case R.id.carseat_turn_back_lav_ll /* 2131296623 */:
                this.f0.U(B(), true);
                BydTouchImageView bydTouchImageView = this.O;
                Context context = this.F;
                b.b.a.h.l.b(bydTouchImageView, context, b.b.a.h.b.a(context, R.attr.ic_arrow_backrest_unfold1_hover));
                break;
            case R.id.carseat_turn_cushion_lav_ll /* 2131296628 */:
                this.f0.Y(B(), true);
                BydTouchImageView bydTouchImageView2 = this.U;
                Context context2 = this.F;
                b.b.a.h.l.b(bydTouchImageView2, context2, b.b.a.h.b.a(context2, R.attr.ic_arrow_cushion_unfold1_hover));
                break;
            case R.id.carseat_turn_head_lav_ll /* 2131296633 */:
                this.f0.I(B(), true);
                BydTouchImageView bydTouchImageView3 = this.K;
                Context context3 = this.F;
                b.b.a.h.l.b(bydTouchImageView3, context3, b.b.a.h.b.a(context3, R.attr.ic_arrow_four_unfold1_hover));
                break;
            case R.id.carseat_turn_leg_lav_ll /* 2131296639 */:
                this.f0.W(B(), true);
                BydTouchImageView bydTouchImageView4 = this.W;
                Context context4 = this.F;
                b.b.a.h.l.b(bydTouchImageView4, context4, b.b.a.h.b.a(context4, R.attr.ic_arrow_leg_unfold1_hover));
                break;
            case R.id.carseat_turn_normal_lav_ll /* 2131296644 */:
                this.f0.Q(B(), true);
                BydTouchImageView bydTouchImageView5 = this.S;
                Context context5 = this.F;
                b.b.a.h.l.b(bydTouchImageView5, context5, b.b.a.h.b.a(context5, R.attr.ic_arrow_four_unfold1_hover));
                break;
            case R.id.carseat_turn_sw_lav_ll /* 2131296648 */:
                this.f0.c0(true);
                BydTouchImageView bydTouchImageView6 = this.G;
                Context context6 = this.F;
                b.b.a.h.l.b(bydTouchImageView6, context6, b.b.a.h.b.a(context6, R.attr.ic_arrow_wheel_spread1_hover));
                break;
        }
    }

    public void g0(int i) {
        boolean zO = this.f0.o(i);
        b.b.a.h.l.K(zO, this.l, this.y);
        BydTouchImageView[] bydTouchImageViewArr = new BydTouchImageView[2];
        bydTouchImageViewArr[0] = zO ? this.P : null;
        bydTouchImageViewArr[1] = zO ? this.O : null;
        b.b.a.h.l.o(this, bydTouchImageViewArr);
        b.b.a.h.l.K(false, this.O, this.P, this.U0, this.T0);
        if (zO) {
            this.a0.c(this.r);
            this.T0.setScrollerListener(this);
            this.a0.e(this.O, this.P);
            this.a0.b(this.U0, this.T0);
        } else {
            this.a0.a();
        }
        b.b.a.h.l.J(8, this.r);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.dialog_carseat_new;
    }

    public final void h0(int i) {
        ImageView imageView = this.E;
        Context context = this.F;
        b.b.a.h.l.c(imageView, context.getDrawable(b.b.a.h.b.a(context, i > 2 ? R.attr.img_main_back_seat_normal_light : R.attr.img_main_seat_normal_light)));
    }

    @Override // com.byd.base.view.BydTouchImageView.c
    public void i(int i) {
    }

    public void i0(int i) {
        boolean zD = this.f0.d(i);
        b.b.a.h.l.K(zD, this.n, this.A);
        BydTouchImageView[] bydTouchImageViewArr = new BydTouchImageView[2];
        bydTouchImageViewArr[0] = zD ? this.U : null;
        bydTouchImageViewArr[1] = zD ? this.V : null;
        b.b.a.h.l.o(this, bydTouchImageViewArr);
        b.b.a.h.l.K(false, this.U, this.V, this.X0, this.Y0);
        if (zD) {
            this.c0.c(this.t);
            this.X0.setScrollerListener(this);
            this.c0.e(this.U, this.V);
            this.c0.b(this.X0, this.Y0);
        } else {
            this.c0.a();
        }
        b.b.a.h.l.J(8, this.t);
    }

    @Override // b.b.a.j.e.b
    public void j(int i) {
        b.b.a.h.a.a(this, " moveDown : " + i);
        switch (i) {
            case R.id.carseat_turn_back_lav_ll /* 2131296623 */:
                this.f0.T(B(), true);
                BydTouchImageView bydTouchImageView = this.P;
                Context context = this.F;
                b.b.a.h.l.b(bydTouchImageView, context, b.b.a.h.b.a(context, R.attr.ic_arrow_backrest_unfold2_hover));
                break;
            case R.id.carseat_turn_cushion_lav_ll /* 2131296628 */:
                this.f0.X(B(), true);
                BydTouchImageView bydTouchImageView2 = this.V;
                Context context2 = this.F;
                b.b.a.h.l.b(bydTouchImageView2, context2, b.b.a.h.b.a(context2, R.attr.ic_arrow_cushion_unfold2_hover));
                break;
            case R.id.carseat_turn_head_lav_ll /* 2131296633 */:
                this.f0.H(B(), true);
                BydTouchImageView bydTouchImageView3 = this.L;
                Context context3 = this.F;
                b.b.a.h.l.b(bydTouchImageView3, context3, b.b.a.h.b.a(context3, R.attr.ic_arrow_four_unfold2_hover));
                break;
            case R.id.carseat_turn_leg_lav_ll /* 2131296639 */:
                this.f0.V(B(), true);
                BydTouchImageView bydTouchImageView4 = this.X;
                Context context4 = this.F;
                b.b.a.h.l.b(bydTouchImageView4, context4, b.b.a.h.b.a(context4, R.attr.ic_arrow_leg_unfold2_hover));
                break;
            case R.id.carseat_turn_normal_lav_ll /* 2131296644 */:
                this.f0.P(B(), true);
                BydTouchImageView bydTouchImageView5 = this.T;
                Context context5 = this.F;
                b.b.a.h.l.b(bydTouchImageView5, context5, b.b.a.h.b.a(context5, R.attr.ic_arrow_four_unfold2_hover));
                break;
            case R.id.carseat_turn_sw_lav_ll /* 2131296648 */:
                this.f0.a0(true);
                BydTouchImageView bydTouchImageView6 = this.H;
                Context context6 = this.F;
                b.b.a.h.l.b(bydTouchImageView6, context6, b.b.a.h.b.a(context6, R.attr.ic_arrow_wheel_spread3_hover));
                break;
        }
    }

    public void j0(int i) {
        boolean zF = this.f0.f(i);
        boolean zE = this.f0.e(i);
        b.b.a.h.l.K(false, this.K, this.L, this.M, this.N, this.R0, this.S0);
        if (zF && zE) {
            ImageView imageView = this.k;
            Context context = this.F;
            b.b.a.h.l.b(imageView, context, b.b.a.h.b.a(context, R.attr.ic_arrow_four_normal));
            this.g0 = b.b.a.h.b.a(this.F, R.attr.seat_headrest_arrow_four_open);
            this.h0 = b.b.a.h.b.a(this.F, R.attr.seat_headrest_arrow_four_close);
            this.Z.c(this.q);
            this.S0.setScrollerListener(this);
            this.S0.setScrollDirection(3);
            this.Z.e(this.K, this.L, this.M, this.N);
            this.Z.b(this.R0, this.S0);
        } else if (!zE && zF) {
            ImageView imageView2 = this.k;
            Context context2 = this.F;
            b.b.a.h.l.b(imageView2, context2, b.b.a.h.b.a(context2, R.attr.ic_arrow_level_normal));
            this.g0 = b.b.a.h.b.a(this.F, R.attr.seat_headrest_arrow_level_open);
            this.h0 = b.b.a.h.b.a(this.F, R.attr.seat_headrest_arrow_level_close);
            this.Z.c(this.q);
            this.S0.setScrollerListener(this);
            this.S0.setScrollDirection(2);
            this.Z.e(this.M, this.N);
            this.Z.b(this.R0, this.S0);
        } else if (!zE || zF) {
            this.k.setBackground(null);
            this.Z.a();
        } else {
            ImageView imageView3 = this.k;
            Context context3 = this.F;
            b.b.a.h.l.b(imageView3, context3, b.b.a.h.b.a(context3, R.attr.ic_arrow_vertical_normal));
            this.g0 = b.b.a.h.b.a(this.F, R.attr.seat_headrest_arrow_vertical_open);
            this.h0 = b.b.a.h.b.a(this.F, R.attr.seat_headrest_arrow_vertical_close);
            this.Z.c(this.q);
            this.S0.setScrollerListener(this);
            this.S0.setScrollDirection(1);
            this.Z.e(this.K, this.L);
            this.Z.b(this.R0, this.S0);
        }
        b.b.a.h.l.K(zF || zE, this.k, this.x);
        b.b.a.h.l.J(8, this.q);
        BydTouchImageView[] bydTouchImageViewArr = new BydTouchImageView[4];
        bydTouchImageViewArr[0] = zE ? this.K : null;
        bydTouchImageViewArr[1] = zE ? this.L : null;
        bydTouchImageViewArr[2] = zF ? this.M : null;
        bydTouchImageViewArr[3] = zF ? this.N : null;
        b.b.a.h.l.o(this, bydTouchImageViewArr);
    }

    @Override // b.b.a.j.e.b
    public void k(int i) {
        b.b.a.h.a.a(this, " moveLeft : " + i);
        switch (i) {
            case R.id.carseat_turn_back_lav_ll /* 2131296623 */:
                this.f0.U(B(), true);
                BydTouchImageView bydTouchImageView = this.O;
                Context context = this.F;
                b.b.a.h.l.b(bydTouchImageView, context, b.b.a.h.b.a(context, R.attr.ic_arrow_backrest_unfold1_hover));
                break;
            case R.id.carseat_turn_head_lav_ll /* 2131296633 */:
                this.f0.K(B(), true);
                BydTouchImageView bydTouchImageView2 = this.M;
                Context context2 = this.F;
                b.b.a.h.l.b(bydTouchImageView2, context2, b.b.a.h.b.a(context2, R.attr.ic_arrow_four_unfold3_hover));
                break;
            case R.id.carseat_turn_leg_lav_ll /* 2131296639 */:
                this.f0.W(B(), true);
                BydTouchImageView bydTouchImageView3 = this.W;
                Context context3 = this.F;
                b.b.a.h.l.b(bydTouchImageView3, context3, b.b.a.h.b.a(context3, R.attr.ic_arrow_leg_unfold1_hover));
                break;
            case R.id.carseat_turn_normal_lav_ll /* 2131296644 */:
                this.f0.S(B(), true);
                BydTouchImageView bydTouchImageView4 = this.Q;
                Context context4 = this.F;
                b.b.a.h.l.b(bydTouchImageView4, context4, b.b.a.h.b.a(context4, R.attr.ic_arrow_four_unfold3_hover));
                break;
            case R.id.carseat_turn_sw_lav_ll /* 2131296648 */:
                this.f0.Z(true);
                BydTouchImageView bydTouchImageView5 = this.I;
                Context context5 = this.F;
                b.b.a.h.l.b(bydTouchImageView5, context5, b.b.a.h.b.a(context5, R.attr.ic_arrow_wheel_spread2_hover));
                break;
        }
    }

    public final void k0(int i) {
        if (i == 1) {
            s0(this.u0, this.t0, B());
            return;
        }
        if (i == 2) {
            s0(this.w0, this.v0, B());
        } else {
            if (i == 3) {
                s0(this.y0, this.x0, B());
                return;
            }
            t0(this.u0, this.t0);
            t0(this.w0, this.v0);
            t0(this.y0, this.x0);
        }
    }

    public void l0(int i) {
        boolean zC = this.f0.c(i);
        b.b.a.h.l.K(zC, this.o, this.B);
        BydTouchImageView[] bydTouchImageViewArr = new BydTouchImageView[2];
        bydTouchImageViewArr[0] = zC ? this.W : null;
        bydTouchImageViewArr[1] = zC ? this.X : null;
        b.b.a.h.l.o(this, bydTouchImageViewArr);
        b.b.a.h.l.K(false, this.W, this.X, this.Z0, this.a1);
        if (zC) {
            this.d0.c(this.u);
            this.Z0.setScrollerListener(this);
            this.d0.e(this.W, this.X);
            this.d0.b(this.Z0, this.a1);
        } else {
            this.d0.a();
        }
        b.b.a.h.l.J(8, this.u);
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.dialog_carsetting_right_carseat_new;
    }

    public final void m0(int i) {
        boolean zH = this.f0.h(i);
        b.b.a.h.l.K(zH, this.q0, this.t0);
        b.b.a.h.l.K(zH, this.r0, this.v0);
        b.b.a.h.l.J(4, this.u0, this.w0, this.y0);
        b.b.a.h.l.K(zH, this.z0, this.A0);
        b.b.a.h.l.G(this, this.q0, this.r0);
        b.b.a.h.l.F(this, this.q0, this.r0);
        f0(i);
    }

    @Override // com.byd.base.view.BydTouchImageView.c
    public void move(int i) {
    }

    public void n0(b.b.g.n.h.d dVar) {
        this.f0 = dVar;
        this.i = y(b.b.o.b.b.a.a(this));
        k.a(this.e0);
    }

    public void o0(int i) {
        boolean zM = this.f0.m(i);
        boolean zN = this.f0.n(i);
        b.b.a.h.l.K(false, this.Q, this.R, this.S, this.T, this.V0, this.W0);
        if (zM && zN) {
            ImageView imageView = this.m;
            Context context = this.F;
            b.b.a.h.l.b(imageView, context, b.b.a.h.b.a(context, R.attr.ic_arrow_four_normal));
            this.i0 = b.b.a.h.b.a(this.F, R.attr.seat_headrest_arrow_four_open);
            this.j0 = b.b.a.h.b.a(this.F, R.attr.seat_headrest_arrow_four_close);
            this.b0.c(this.s);
            this.V0.setScrollerListener(this);
            this.V0.setScrollDirection(3);
            this.b0.e(this.Q, this.R, this.S, this.T);
            this.b0.b(this.V0, this.W0);
        } else if (!zN && zM) {
            ImageView imageView2 = this.m;
            Context context2 = this.F;
            b.b.a.h.l.b(imageView2, context2, b.b.a.h.b.a(context2, R.attr.ic_arrow_level_normal));
            this.i0 = b.b.a.h.b.a(this.F, R.attr.seat_headrest_arrow_level_open);
            this.j0 = b.b.a.h.b.a(this.F, R.attr.seat_headrest_arrow_level_close);
            this.b0.c(this.s);
            this.V0.setScrollerListener(this);
            this.V0.setScrollDirection(2);
            this.b0.e(this.Q, this.R);
            this.b0.b(this.V0, this.W0);
        } else if (!zN || zM) {
            this.m.setBackground(null);
            this.b0.a();
        } else {
            ImageView imageView3 = this.m;
            Context context3 = this.F;
            b.b.a.h.l.b(imageView3, context3, b.b.a.h.b.a(context3, R.attr.ic_arrow_vertical_normal));
            this.i0 = b.b.a.h.b.a(this.F, R.attr.seat_headrest_arrow_vertical_open);
            this.j0 = b.b.a.h.b.a(this.F, R.attr.seat_headrest_arrow_vertical_close);
            this.b0.c(this.s);
            this.V0.setScrollerListener(this);
            this.V0.setScrollDirection(1);
            this.b0.e(this.S, this.T);
            this.b0.b(this.V0, this.W0);
        }
        b.b.a.h.l.K(zM || zN, this.m, this.z);
        BydTouchImageView[] bydTouchImageViewArr = new BydTouchImageView[4];
        bydTouchImageViewArr[0] = zN ? this.S : null;
        bydTouchImageViewArr[1] = zN ? this.T : null;
        bydTouchImageViewArr[2] = zM ? this.Q : null;
        bydTouchImageViewArr[3] = zM ? this.R : null;
        b.b.a.h.l.o(this, bydTouchImageViewArr);
        b.b.a.h.l.J(8, this.s);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.carseat_remember_down /* 2131296611 */:
                if (!E(B())) {
                    r0(B(), 3);
                    e0(B(), true);
                    this.f0.F(3, B());
                    G(B(), 3);
                    break;
                }
                break;
            case R.id.carseat_remember_middle /* 2131296613 */:
                if (!E(B())) {
                    r0(B(), 2);
                    e0(B(), true);
                    this.f0.F(2, B());
                    G(B(), 2);
                    break;
                }
                break;
            case R.id.carseat_remember_up /* 2131296615 */:
                if (!E(B())) {
                    r0(B(), 1);
                    e0(B(), true);
                    this.f0.F(1, B());
                    G(B(), 1);
                    break;
                }
                break;
            case R.id.carseat_trun_sw_iv /* 2131296619 */:
            case R.id.carseat_trun_sw_rl /* 2131296620 */:
            case R.id.carseat_turn_sw_turn_target /* 2131296649 */:
                I(0);
                break;
            case R.id.carseat_turn_back_iv /* 2131296621 */:
            case R.id.carseat_turn_back_rl /* 2131296624 */:
            case R.id.carseat_turn_back_turn_target /* 2131296625 */:
                I(2);
                break;
            case R.id.carseat_turn_cushion_iv /* 2131296626 */:
            case R.id.carseat_turn_cushion_rl /* 2131296629 */:
            case R.id.carseat_turn_cushion_turn_target /* 2131296630 */:
                I(4);
                break;
            case R.id.carseat_turn_head_iv /* 2131296631 */:
            case R.id.carseat_turn_head_rl /* 2131296634 */:
            case R.id.carseat_turn_head_turn_target /* 2131296636 */:
                I(1);
                break;
            case R.id.carseat_turn_leg_iv /* 2131296637 */:
            case R.id.carseat_turn_leg_rl /* 2131296640 */:
            case R.id.carseat_turn_leg_turn_target /* 2131296641 */:
                I(5);
                break;
            case R.id.carseat_turn_normal_iv /* 2131296642 */:
            case R.id.carseat_turn_normal_rl /* 2131296645 */:
            case R.id.carseat_turn_normal_turn_target /* 2131296646 */:
                I(3);
                break;
            case R.id.dialog_carseat_close /* 2131296820 */:
                c0(this.v);
                if (this.i != null) {
                    this.f0 = null;
                    k.e(this.e0);
                    u0();
                    A();
                    this.i.dismiss();
                    this.i = null;
                }
                break;
            default:
                c0(this.v);
                break;
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        int id = view.getId();
        if (id == R.id.carseat_remember_down) {
            this.f0.G(3, B());
        } else if (id == R.id.carseat_remember_middle) {
            this.f0.G(2, B());
        } else if (id == R.id.carseat_remember_up) {
            this.f0.G(1, B());
        }
        return true;
    }

    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i) {
        c0(this.v);
        p0(C(i));
        this.f0.L(C(i));
        this.b1 = C(i);
    }

    public void p0(int i) {
        h0(i);
        q0(i);
        j0(i);
        g0(i);
        o0(i);
        i0(i);
        l0(i);
        m0(i);
    }

    public void q0(int i) {
        boolean z = i == 1 && this.f0.q();
        b.b.a.h.l.K(z, this.j, this.w, this.D);
        b.b.a.h.l.K((i == 1) != z, this.p);
        BydTouchImageView[] bydTouchImageViewArr = new BydTouchImageView[4];
        bydTouchImageViewArr[0] = z ? this.J : null;
        bydTouchImageViewArr[1] = z ? this.H : null;
        bydTouchImageViewArr[2] = z ? this.I : null;
        bydTouchImageViewArr[3] = z ? this.G : null;
        b.b.a.h.l.o(this, bydTouchImageViewArr);
        if (z) {
            this.Q0.setScrollerListener(this);
            this.Y.c(this.p);
            this.Y.e(this.G, this.H, this.I, this.J);
            this.Y.b(this.P0, this.Q0);
        } else {
            this.Y.a();
            b.b.a.h.l.K(false, this.G, this.H, this.I, this.J, this.P0, this.Q0);
        }
        BydScrollRelativeLayout bydScrollRelativeLayout = this.Q0;
        if (bydScrollRelativeLayout != null) {
            bydScrollRelativeLayout.setScrollDirection(4);
        }
    }

    public final void r0(int i, int i2) {
        if (i2 == 1) {
            s0(this.u0, this.t0, i);
        } else if (i2 == 2) {
            s0(this.w0, this.v0, i);
        } else if (i2 == 3) {
            s0(this.y0, this.x0, i);
        }
    }

    @Override // b.b.g.q.d.b
    public void s(Dialog dialog) {
    }

    public final void s0(ImageView imageView, TextView textView, int i) {
        if (i != B()) {
            return;
        }
        imageView.setVisibility(0);
        textView.setVisibility(4);
        if (this.B0 == null) {
            this.B0 = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        }
        if (this.B0.hasStarted()) {
            this.B0.cancel();
        }
        this.B0.setAnimationListener(new AnimationAnimationListenerC0066a(imageView, textView, i));
        this.B0.setDuration(2000L);
        this.B0.setInterpolator(new LinearInterpolator());
        this.B0.setRepeatCount(0);
        imageView.startAnimation(this.B0);
        e0(i, true);
    }

    @Override // b.b.g.q.d.b
    public void t(Dialog dialog) {
    }

    public final void t0(ImageView imageView, TextView textView) {
        imageView.setVisibility(4);
        textView.setVisibility(0);
        RotateAnimation rotateAnimation = this.B0;
        if (rotateAnimation == null || !rotateAnimation.hasStarted()) {
            return;
        }
        this.B0.cancel();
    }

    @Override // b.b.g.q.d.b
    public void u(Dialog dialog) {
        this.j = (ImageView) dialog.findViewById(R.id.carseat_trun_sw_iv);
        this.w = (RelativeLayout) dialog.findViewById(R.id.carseat_trun_sw_rl);
        this.D = (ImageView) dialog.findViewById(R.id.carset_main_wheel_turn);
        this.k = (ImageView) dialog.findViewById(R.id.carseat_turn_head_iv);
        this.x = (RelativeLayout) dialog.findViewById(R.id.carseat_turn_head_rl);
        this.l = (ImageView) dialog.findViewById(R.id.carseat_turn_back_iv);
        this.y = (RelativeLayout) dialog.findViewById(R.id.carseat_turn_back_rl);
        this.m = (ImageView) dialog.findViewById(R.id.carseat_turn_normal_iv);
        this.z = (RelativeLayout) dialog.findViewById(R.id.carseat_turn_normal_rl);
        this.n = (ImageView) dialog.findViewById(R.id.carseat_turn_cushion_iv);
        this.A = (RelativeLayout) dialog.findViewById(R.id.carseat_turn_cushion_rl);
        this.o = (ImageView) dialog.findViewById(R.id.carseat_turn_leg_iv);
        RelativeLayout relativeLayout = (RelativeLayout) dialog.findViewById(R.id.carseat_turn_leg_rl);
        this.B = relativeLayout;
        b.b.a.h.l.F(this, this.j, this.k, this.l, this.m, this.n, this.o, this.w, this.x, this.y, this.z, this.A, relativeLayout);
        this.p = (LottieAnimationView) dialog.findViewById(R.id.carseat_turn_sw_lav);
        this.q = (LottieAnimationView) dialog.findViewById(R.id.carseat_turn_head_lav);
        this.r = (LottieAnimationView) dialog.findViewById(R.id.carseat_turn_back_lav);
        this.s = (LottieAnimationView) dialog.findViewById(R.id.carseat_turn_normal_lav);
        this.t = (LottieAnimationView) dialog.findViewById(R.id.carseat_turn_cushion_lav);
        this.u = (LottieAnimationView) dialog.findViewById(R.id.carseat_turn_leg_lav);
        this.E = (ImageView) dialog.findViewById(R.id.carseat_background_iv);
        this.O0 = (RelativeLayout) dialog.findViewById(R.id.click_area);
        this.G = (BydTouchImageView) dialog.findViewById(R.id.wheel_turn_up_iv);
        this.H = (BydTouchImageView) dialog.findViewById(R.id.wheel_turn_down_iv);
        this.I = (BydTouchImageView) dialog.findViewById(R.id.wheel_turn_front_iv);
        this.J = (BydTouchImageView) dialog.findViewById(R.id.wheel_turn_back_iv);
        this.K = (BydTouchImageView) dialog.findViewById(R.id.headrest_turn_up);
        this.L = (BydTouchImageView) dialog.findViewById(R.id.headrest_turn_down);
        this.M = (BydTouchImageView) dialog.findViewById(R.id.headrest_turn_left);
        this.N = (BydTouchImageView) dialog.findViewById(R.id.headrest_turn_right);
        this.O = (BydTouchImageView) dialog.findViewById(R.id.back_turn_front);
        this.P = (BydTouchImageView) dialog.findViewById(R.id.back_turn_back);
        this.Q = (BydTouchImageView) dialog.findViewById(R.id.seat_turn_front);
        this.R = (BydTouchImageView) dialog.findViewById(R.id.seat_turn_back);
        this.S = (BydTouchImageView) dialog.findViewById(R.id.seat_turn_up);
        this.T = (BydTouchImageView) dialog.findViewById(R.id.seat_turn_down);
        this.U = (BydTouchImageView) dialog.findViewById(R.id.cushion_turn_up);
        this.V = (BydTouchImageView) dialog.findViewById(R.id.cushion_turn_down);
        this.W = (BydTouchImageView) dialog.findViewById(R.id.leg_turn_front);
        this.X = (BydTouchImageView) dialog.findViewById(R.id.leg_turn_back);
        this.P0 = (ImageView) dialog.findViewById(R.id.carseat_turn_sw_turn_target);
        BydScrollRelativeLayout bydScrollRelativeLayout = (BydScrollRelativeLayout) dialog.findViewById(R.id.carseat_turn_sw_lav_ll);
        this.Q0 = bydScrollRelativeLayout;
        b.b.a.h.l.F(this, bydScrollRelativeLayout);
        this.S0 = (BydScrollRelativeLayout) dialog.findViewById(R.id.carseat_turn_head_lav_ll);
        this.R0 = (ImageView) dialog.findViewById(R.id.carseat_turn_head_turn_target);
        b.b.a.h.l.F(this, this.S0);
        this.T0 = (BydScrollRelativeLayout) dialog.findViewById(R.id.carseat_turn_back_lav_ll);
        this.U0 = (ImageView) dialog.findViewById(R.id.carseat_turn_back_turn_target);
        b.b.a.h.l.F(this, this.T0);
        this.V0 = (BydScrollRelativeLayout) dialog.findViewById(R.id.carseat_turn_normal_lav_ll);
        this.W0 = (ImageView) dialog.findViewById(R.id.carseat_turn_normal_turn_target);
        b.b.a.h.l.F(this, this.V0);
        this.X0 = (BydScrollRelativeLayout) dialog.findViewById(R.id.carseat_turn_cushion_lav_ll);
        this.Y0 = (ImageView) dialog.findViewById(R.id.carseat_turn_cushion_turn_target);
        b.b.a.h.l.F(this, this.X0);
        this.Z0 = (BydScrollRelativeLayout) dialog.findViewById(R.id.carseat_turn_leg_lav_ll);
        this.a1 = (ImageView) dialog.findViewById(R.id.carseat_turn_leg_turn_target);
        b.b.a.h.l.F(this, this.Z0);
        this.k0 = (SegmentedButtonGroup) dialog.findViewById(R.id.carseat_setting_sbg);
        this.l0 = (SegmentedButton) dialog.findViewById(R.id.main_seat);
        this.m0 = (SegmentedButton) dialog.findViewById(R.id.main_deputy);
        this.n0 = (SegmentedButton) dialog.findViewById(R.id.main_left_rear);
        this.o0 = (SegmentedButton) dialog.findViewById(R.id.main_right_rear);
        this.q0 = (RelativeLayout) dialog.findViewById(R.id.carseat_remember_up);
        this.t0 = (TextView) dialog.findViewById(R.id.carseat_remember_up_tv);
        this.u0 = (ImageView) dialog.findViewById(R.id.up_loading);
        this.r0 = (RelativeLayout) dialog.findViewById(R.id.carseat_remember_middle);
        this.v0 = (TextView) dialog.findViewById(R.id.carseat_remember_middle_tv);
        this.w0 = (ImageView) dialog.findViewById(R.id.middle_loading);
        this.s0 = (RelativeLayout) dialog.findViewById(R.id.carseat_remember_down);
        this.x0 = (TextView) dialog.findViewById(R.id.carseat_remember_down_tv);
        this.y0 = (ImageView) dialog.findViewById(R.id.down_loading);
        this.z0 = (TextView) dialog.findViewById(R.id.carseat_bottom_title_tv);
        this.A0 = (TextView) dialog.findViewById(R.id.carseat_bottom_tv);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.dialog_carseat_close);
        this.L0 = imageView;
        b.b.a.h.l.F(this, imageView, this.O0);
        this.O0.setSoundEffectsEnabled(false);
        this.M0 = 0;
        this.N0 = (TextView) dialog.findViewById(R.id.carset_turn_title);
        D();
        Z();
        this.k0.setPositionWithOutCallback(this.M0, false);
        p0(C(this.M0));
    }

    public final void u0() {
        this.u.removeAnimatorListener(this.d0);
        this.t.removeAnimatorListener(this.c0);
        this.s.removeAnimatorListener(this.b0);
        this.q.removeAnimatorListener(this.Z);
        this.r.removeAnimatorListener(this.a0);
        this.p.removeAnimatorListener(this.Y);
    }

    @Override // b.b.g.q.d.b
    public void w() {
        super.w();
        u0();
    }

    @Override // b.b.g.q.d.b
    public void x() {
        super.x();
        this.v = -1;
        k.e(this.e0);
    }
}
