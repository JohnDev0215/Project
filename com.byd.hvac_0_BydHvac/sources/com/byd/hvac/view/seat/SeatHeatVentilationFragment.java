package com.byd.hvac.view.seat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.a.a.d.h;
import b.a.a.g.k;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.byd.hvac.R;
import com.byd.hvac.base.BaseFragment;
import com.byd.hvac.view.BydSeekBar;
import com.byd.hvac.view.dialog.AcStartVehicleTipsLayout;
import com.byd.hvac.view.seat.SeatHeatVentilationFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class SeatHeatVentilationFragment extends BaseFragment<k> implements h, View.OnClickListener, BydSeekBar.b {
    public int A;
    public LinearLayout A0;
    public int A1;
    public int B;
    public LinearLayout B0;
    public int B1;
    public ConstraintLayout B2;
    public int C;
    public LinearLayout C0;
    public View C1;
    public ImageView C2;
    public RelativeLayout D;
    public LinearLayout D0;
    public View D1;
    public b.a.a.f.h1.a D2;
    public RelativeLayout E;
    public LinearLayout E0;
    public View E1;
    public b.a.a.f.h1.b E2;
    public RelativeLayout F;
    public ImageView F0;
    public View F1;
    public int F2;
    public RelativeLayout G;
    public ImageView G0;
    public View G1;
    public LottieAnimationView H;
    public ImageView H0;
    public View H1;
    public LottieAnimationView I;
    public ImageView I0;
    public View I1;
    public LottieAnimationView J;
    public ImageView J0;
    public View J1;
    public LottieAnimationView K;
    public ImageView K0;
    public View K1;
    public LottieAnimationView L;
    public ImageView L0;
    public View L1;
    public int L2;
    public LottieAnimationView M;
    public ImageView M0;
    public View M1;
    public int M2;
    public LottieAnimationView N;
    public ImageView N0;
    public View N1;
    public int N2;
    public LottieAnimationView O;
    public ImageView O0;
    public int O1;
    public int O2;
    public LottieAnimationView P;
    public ImageView P0;
    public int P1;
    public int P2;
    public LottieAnimationView Q;
    public ImageView Q0;
    public int Q1;
    public int Q2;
    public LottieAnimationView R;
    public ImageView R0;
    public int R1;
    public int R2;
    public LottieAnimationView S;
    public ImageView S0;
    public int S1;
    public int S2;
    public LottieAnimationView T;
    public ImageView T0;
    public int T1;
    public int T2;
    public LinearLayout U;
    public ImageView U0;
    public int U1;
    public int U2;
    public LinearLayout V;
    public ImageView V0;
    public int V1;
    public int V2;
    public LinearLayout W;
    public ImageView W0;
    public int W1;
    public int W2;
    public LinearLayout X;
    public BydSeekBar X0;
    public int X1;
    public int X2;
    public LinearLayout Y;
    public BydSeekBar Y0;
    public int Y1;
    public int Y2;
    public LinearLayout Z;
    public BydSeekBar Z0;
    public int Z1;
    public int Z2;
    public LinearLayout a0;
    public BydSeekBar a1;
    public int a2;
    public int a3;
    public LinearLayout b0;
    public BydSeekBar b1;
    public int b2;
    public int b3;
    public LinearLayout c0;
    public int c1;
    public int c2;
    public int c3;
    public LinearLayout d0;
    public int d1;
    public ImageView d2;
    public int d3;
    public LinearLayout e0;
    public int e1;
    public ImageView e2;
    public int e3;
    public LinearLayout f0;
    public int f1;
    public ImageView f2;
    public int f3;
    public LinearLayout g0;
    public int g1;
    public ImageView g2;
    public int g3;
    public ImageView h;
    public LinearLayout h0;
    public int h1;
    public ImageView h2;
    public int h3;
    public ImageView i;
    public LinearLayout i0;
    public int i1;
    public ImageView i2;
    public int i3;
    public ImageView j;
    public LinearLayout j0;
    public int j1;
    public ImageView j2;
    public ImageView k;
    public TextSwitcher k0;
    public int k1;
    public ImageView k2;
    public ImageView l;
    public TextSwitcher l0;
    public int l1;
    public ImageView l2;
    public int m;
    public TextSwitcher m0;
    public int m1;
    public ImageView m2;
    public int n;
    public TextSwitcher n0;
    public int n1;
    public ImageView n2;
    public int o;
    public TextSwitcher o0;
    public int o1;
    public ImageView o2;
    public int p;
    public TextSwitcher p0;
    public int p1;
    public ImageView p2;
    public int q;
    public TextSwitcher q0;
    public int q1;
    public List<String> q2;
    public int r;
    public TextSwitcher r0;
    public int r1;
    public List<String> r2;
    public int s;
    public TextSwitcher s0;
    public int s1;
    public int t;
    public TextView t0;
    public int t1;
    public int u;
    public TextView u0;
    public int u1;
    public int v;
    public b v0;
    public int v1;
    public int w;
    public int w0;
    public int w1;
    public int x;
    public int x0;
    public int x1;
    public int y;
    public b.a.a.i.i.a y0;
    public int y1;
    public int z;
    public LinearLayout z0;
    public int z1;
    public final String g = "SeatHeatVentilationFragment";
    public final int s2 = 0;
    public final int t2 = 1;
    public final int u2 = 2;
    public final int v2 = 3;
    public final int w2 = 4;
    public final int x2 = 5;
    public final int y2 = 6;
    public final int z2 = 7;
    public final int A2 = 8;
    public final float G2 = 0.5f;
    public final float H2 = 1.0f;
    public final float I2 = 0.33f;
    public final float J2 = 0.66f;
    public final float K2 = 1.0f;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements AcStartVehicleTipsLayout.b {
        public a() {
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void a() {
            SeatHeatVentilationFragment.this.y0.a();
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void b() {
            SeatHeatVentilationFragment.this.y0.a();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<Context> f1068a;

        public b(Context context) {
            this.f1068a = new WeakReference<>(context);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    SeatHeatVentilationFragment.this.V.setVisibility(4);
                    break;
                case 1:
                    SeatHeatVentilationFragment.this.d0.setVisibility(4);
                    break;
                case 2:
                    SeatHeatVentilationFragment.this.Z.setVisibility(4);
                    break;
                case 3:
                    SeatHeatVentilationFragment.this.h0.setVisibility(4);
                    break;
                case 4:
                    SeatHeatVentilationFragment.this.W.setVisibility(4);
                    break;
                case 5:
                    SeatHeatVentilationFragment.this.e0.setVisibility(4);
                    break;
                case 6:
                    SeatHeatVentilationFragment.this.a0.setVisibility(4);
                    break;
                case 7:
                    SeatHeatVentilationFragment.this.i0.setVisibility(4);
                    break;
                case 8:
                    SeatHeatVentilationFragment.this.A0.setVisibility(4);
                    break;
            }
        }
    }

    public static /* synthetic */ void s1(LottieAnimationView lottieAnimationView, LottieComposition lottieComposition) {
        lottieAnimationView.setComposition(lottieComposition);
        lottieAnimationView.playAnimation();
    }

    public static /* synthetic */ void t1(LottieAnimationView lottieAnimationView, LottieComposition lottieComposition) {
        lottieAnimationView.setComposition(lottieComposition);
        lottieAnimationView.setProgress(0.0f);
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void A0() {
        this.D2 = new b.a.a.f.h1.a();
        int iN = ((k) this.f).n();
        this.F2 = iN;
        if (iN == 143) {
            this.E2 = this.D2.a(143);
        } else {
            this.E2 = this.D2.a(196);
        }
        this.C2.setImageResource(this.E2.f125a);
        this.C = ((k) this.f).Y();
        this.x0 = ((k) this.f).m(2);
        this.g1 = ((k) this.f).i(1);
        this.h1 = ((k) this.f).i(2);
        this.i1 = ((k) this.f).i(3);
        this.j1 = ((k) this.f).i(4);
        this.s1 = ((k) this.f).l(1);
        this.B1 = ((k) this.f).l(2);
        this.t1 = ((k) this.f).l(3);
        this.u1 = ((k) this.f).l(4);
        this.z1 = ((k) this.f).W();
        this.A1 = ((k) this.f).V();
        this.w0 = ((k) this.f).o();
        g1();
        if (h1()) {
            d1();
        } else {
            X0();
        }
    }

    public void A1(int i, int i2, boolean z) {
        BydLogHelper.logW("SeatHeatVentilationFragment", "updateSeatWarmState area = " + i + " --  value = " + i2);
        if (i == 1) {
            O0(i2, z);
            return;
        }
        if (i == 2) {
            S0(i2, z);
        } else if (i == 3) {
            Q0(i2, z);
        } else {
            if (i != 4) {
                return;
            }
            U0(i2, z);
        }
    }

    public final void B1(int i) {
        if (i == 1) {
            this.h.setAlpha(0.2f);
            return;
        }
        if (i == 2) {
            this.h.setAlpha(0.4f);
            return;
        }
        if (i == 3) {
            this.h.setAlpha(0.6f);
        } else if (i == 4) {
            this.h.setAlpha(0.8f);
        } else {
            if (i != 5) {
                return;
            }
            this.h.setAlpha(1.0f);
        }
    }

    @Override // b.a.a.d.h
    public void D(int i, boolean z) {
        int i2;
        BydLogHelper.logW("SeatHeatVentilationFragment", "updateSeatHeatingNotL1PState  value = " + i);
        this.W1 = i;
        if (i == 2) {
            this.j.setVisibility(0);
            this.j.setImageResource(M0(this.E2.f));
            this.j.setAlpha(0.33f);
            i2 = R.raw.icon_seat_heat_3level_1_0;
            if (m1()) {
                this.j.setAlpha(0.5f);
                i2 = R.raw.icon_seat_heat_1_0;
            }
        } else if (i == 3) {
            this.j.setVisibility(0);
            this.j.setImageResource(M0(this.E2.f));
            this.j.setAlpha(0.66f);
            i2 = R.raw.icon_seat_heat_3level_2_1;
            if (m1()) {
                this.j.setAlpha(1.0f);
                i2 = R.raw.icon_seat_heat_2_1;
            }
        } else if (i != 4) {
            this.j.setVisibility(8);
            i2 = R.raw.icon_seat_heat_3level_0_3;
            if (m1()) {
                i2 = R.raw.icon_seat_heat_0_2;
            }
            if (n1()) {
                int i3 = this.R1;
                if (i3 == 2) {
                    this.j.setVisibility(0);
                    this.j.setImageResource(M0(this.E2.e));
                    this.j.setAlpha(0.5f);
                } else if (i3 == 3) {
                    this.j.setVisibility(0);
                    this.j.setImageResource(M0(this.E2.e));
                    this.j.setAlpha(1.0f);
                }
            } else {
                int i4 = this.R1;
                if (i4 == 2) {
                    this.j.setVisibility(0);
                    this.j.setImageResource(M0(this.E2.e));
                    this.j.setAlpha(0.33f);
                } else if (i4 == 3) {
                    this.j.setVisibility(0);
                    this.j.setImageResource(M0(this.E2.e));
                    this.j.setAlpha(0.66f);
                } else if (i4 == 4) {
                    this.j.setVisibility(0);
                    this.j.setImageResource(M0(this.E2.e));
                    this.j.setAlpha(1.0f);
                }
            }
        } else {
            this.j.setVisibility(0);
            this.j.setImageResource(M0(this.E2.f));
            this.j.setAlpha(1.0f);
            i2 = R.raw.icon_seat_heat_3level_3_2;
        }
        if (!z) {
            if (this.c2 != this.W1) {
                if (this.P.isAnimating()) {
                    this.P.cancelAnimation();
                }
                v1(i2, this.P);
            } else if (!this.P.isAnimating()) {
                v1(i2, this.P);
            }
        }
        this.c2 = this.W1;
    }

    @Override // b.a.a.d.h
    public void H(int i, int i2, boolean z) {
        BydLogHelper.logW("SeatHeatVentilationFragment", "updateSeatWarmState1 area = " + i + " --  value = " + i2);
        if (i == 1) {
            this.u = i2;
            A1(1, i2, z);
            this.v = this.u;
            return;
        }
        if (i == 2) {
            this.w = i2;
            A1(2, i2, z);
            this.x = this.w;
        } else if (i == 3) {
            this.y = i2;
            A1(3, i2, z);
            this.z = this.y;
        } else {
            if (i != 4) {
                return;
            }
            this.A = i2;
            A1(4, i2, z);
            this.B = this.A;
        }
    }

    @Override // b.a.a.d.h
    public void I(int i, int i2, boolean z) {
        BydLogHelper.logD("update5LevelSeatVentilatingMode area:" + i + " -- state:" + i2);
        if (i == 1) {
            this.o1 = i2;
            if (i2 != 1) {
                if (i2 == 2) {
                    this.a1.setProgressGradientColor(getResources().getColor(R.color.ventilation_seekbar_progress_start_color), getResources().getColor(R.color.ventilation_seekbar_progress_end_color));
                    this.a1.setVisibility(0);
                    this.W0.setImageResource(R.mipmap.icon_seat_ai_ventilation_5level);
                    this.P0.setImageResource(M0(this.E2.f126b));
                    this.P0.setVisibility(0);
                    y1(1, this.v1);
                    return;
                }
                return;
            }
            if (this.c1 == 1 && this.g1 == 2 && this.s1 == 2) {
                this.a1.setVisibility(8);
                this.P0.setVisibility(8);
            }
            this.W0.setImageResource(R.mipmap.icon_seat_ai_off_5level);
            if (this.s1 == 1) {
                this.P0.setImageResource(M0(this.E2.f126b));
                this.P0.setVisibility(0);
                y1(1, this.v1);
                return;
            }
            return;
        }
        if (i == 2) {
            this.p1 = i2;
            if (i2 != 1) {
                if (i2 == 2) {
                    this.Y0.setProgressGradientColor(getResources().getColor(R.color.ventilation_seekbar_progress_start_color), getResources().getColor(R.color.ventilation_seekbar_progress_end_color));
                    this.Y0.setVisibility(0);
                    this.U0.setImageResource(R.mipmap.icon_seat_ai_ventilation_5level);
                    this.Q0.setImageResource(M0(this.E2.e));
                    this.Q0.setVisibility(0);
                    y1(2, this.w1);
                    return;
                }
                return;
            }
            if (this.d1 == 1 && this.h1 == 2 && this.B1 == 2) {
                this.Y0.setVisibility(8);
                this.Q0.setVisibility(8);
            }
            this.U0.setImageResource(R.mipmap.icon_seat_ai_off_5level);
            if (this.B1 == 1) {
                this.Q0.setImageResource(M0(this.E2.e));
                this.Q0.setVisibility(0);
                y1(2, this.w1);
                return;
            }
            return;
        }
        if (i == 3) {
            this.q1 = i2;
            if (i2 != 1) {
                if (i2 == 2) {
                    this.Z0.setProgressGradientColor(getResources().getColor(R.color.ventilation_seekbar_progress_start_color), getResources().getColor(R.color.ventilation_seekbar_progress_end_color));
                    this.Z0.setVisibility(0);
                    this.V0.setImageResource(R.mipmap.icon_seat_ai_ventilation_5level);
                    this.R0.setImageResource(M0(this.E2.h));
                    this.R0.setVisibility(0);
                    y1(3, this.x1);
                    return;
                }
                return;
            }
            if (this.e1 == 1 && this.i1 == 2 && this.t1 == 2) {
                this.Z0.setVisibility(8);
                this.R0.setVisibility(8);
            }
            this.V0.setImageResource(R.mipmap.icon_seat_ai_off_5level);
            if (this.t1 == 1) {
                this.R0.setImageResource(M0(this.E2.h));
                this.R0.setVisibility(0);
                y1(3, this.x1);
                return;
            }
            return;
        }
        if (i != 4) {
            return;
        }
        this.r1 = i2;
        if (i2 != 1) {
            if (i2 == 2) {
                this.X0.setProgressGradientColor(getResources().getColor(R.color.ventilation_seekbar_progress_start_color), getResources().getColor(R.color.ventilation_seekbar_progress_end_color));
                this.X0.setVisibility(0);
                this.T0.setImageResource(R.mipmap.icon_seat_ai_ventilation_5level);
                this.S0.setImageResource(M0(this.E2.k));
                this.S0.setVisibility(0);
                y1(4, this.y1);
                return;
            }
            return;
        }
        if (this.f1 == 1 && this.j1 == 2 && this.u1 == 2) {
            this.X0.setVisibility(8);
            this.S0.setVisibility(8);
        }
        this.T0.setImageResource(R.mipmap.icon_seat_ai_off_5level);
        if (this.u1 == 1) {
            this.S0.setImageResource(M0(this.E2.k));
            this.S0.setVisibility(0);
            y1(4, this.y1);
        }
    }

    @Override // b.a.a.d.h
    public void J(int i, int i2, boolean z) {
        BydLogHelper.logD("update5LevelSeatVentilatingState area:" + i + " -- state:" + i2);
        if (i == 1) {
            this.s1 = i2;
            if (i2 == 1) {
                this.a1.setProgressGradientColor(getResources().getColor(R.color.ventilation_seekbar_progress_start_color), getResources().getColor(R.color.ventilation_seekbar_progress_end_color));
                this.a1.setVisibility(0);
                this.F0.setImageResource(R.mipmap.icon_seat_ventilation_on_5level);
                this.P0.setImageResource(M0(this.E2.f126b));
                this.P0.setVisibility(0);
                y1(1, this.v1);
                return;
            }
            if (i2 == 2) {
                if (this.c1 == 1 && this.g1 == 2 && this.o1 == 1) {
                    this.a1.setVisibility(8);
                    this.P0.setVisibility(8);
                }
                this.F0.setImageResource(R.mipmap.icon_seat_ventilation_off_5level);
                if (this.o1 == 2) {
                    this.P0.setImageResource(M0(this.E2.f126b));
                    this.P0.setVisibility(0);
                    y1(1, this.v1);
                    return;
                }
                return;
            }
            return;
        }
        if (i == 2) {
            this.B1 = i2;
            if (i2 == 1) {
                this.Y0.setProgressGradientColor(getResources().getColor(R.color.ventilation_seekbar_progress_start_color), getResources().getColor(R.color.ventilation_seekbar_progress_end_color));
                this.Y0.setVisibility(0);
                this.K0.setImageResource(R.mipmap.icon_seat_ventilation_on_5level);
                this.Q0.setImageResource(M0(this.E2.e));
                this.Q0.setVisibility(0);
                y1(2, this.w1);
                return;
            }
            if (i2 == 2) {
                if (this.d1 == 1 && this.h1 == 2 && this.p1 == 1) {
                    this.Y0.setVisibility(8);
                    this.Q0.setVisibility(8);
                }
                this.K0.setImageResource(R.mipmap.icon_seat_ventilation_off_5level);
                if (this.p1 == 2) {
                    this.Q0.setImageResource(M0(this.E2.e));
                    this.Q0.setVisibility(0);
                    y1(2, this.w1);
                    return;
                }
                return;
            }
            return;
        }
        if (i == 3) {
            this.t1 = i2;
            if (i2 == 1) {
                this.Z0.setProgressGradientColor(getResources().getColor(R.color.ventilation_seekbar_progress_start_color), getResources().getColor(R.color.ventilation_seekbar_progress_end_color));
                this.Z0.setVisibility(0);
                this.I0.setImageResource(R.mipmap.icon_seat_ventilation_on_5level);
                this.R0.setImageResource(M0(this.E2.h));
                this.R0.setVisibility(0);
                y1(3, this.x1);
                return;
            }
            if (i2 == 2) {
                if (this.e1 == 1 && this.i1 == 2 && this.q1 == 1) {
                    this.Z0.setVisibility(8);
                    this.R0.setVisibility(8);
                }
                this.I0.setImageResource(R.mipmap.icon_seat_ventilation_off_5level);
                if (this.q1 == 2) {
                    this.R0.setImageResource(M0(this.E2.h));
                    this.R0.setVisibility(0);
                    y1(3, this.x1);
                    return;
                }
                return;
            }
            return;
        }
        if (i != 4) {
            return;
        }
        this.u1 = i2;
        if (i2 == 1) {
            this.X0.setProgressGradientColor(getResources().getColor(R.color.ventilation_seekbar_progress_start_color), getResources().getColor(R.color.ventilation_seekbar_progress_end_color));
            this.X0.setVisibility(0);
            this.N0.setImageResource(R.mipmap.icon_seat_ventilation_on_5level);
            this.S0.setImageResource(M0(this.E2.k));
            this.S0.setVisibility(0);
            y1(4, this.y1);
            return;
        }
        if (i2 == 2) {
            if (this.f1 == 1 && this.j1 == 2 && this.r1 == 1) {
                this.X0.setVisibility(8);
                this.S0.setVisibility(8);
            }
            this.N0.setImageResource(R.mipmap.icon_seat_ventilation_off_5level);
            if (this.r1 == 2) {
                this.S0.setImageResource(M0(this.E2.k));
                this.S0.setVisibility(0);
                y1(4, this.y1);
            }
        }
    }

    @Override // b.a.a.d.h
    public void K(int i, int i2, boolean z) {
        BydLogHelper.logD("update5LevelSeatHeatingState area:" + i + " -- state:" + i2);
        if (i == 1) {
            this.g1 = i2;
            if (i2 == 1) {
                this.a1.setProgressGradientColor(getResources().getColor(R.color.heat_seekbar_progress_start_color), getResources().getColor(R.color.heat_seekbar_progress_end_color));
                this.a1.setVisibility(0);
                this.H0.setImageResource(R.mipmap.icon_seat_heat_on_5level);
                this.P0.setImageResource(M0(this.E2.f127c));
                this.P0.setVisibility(0);
                y1(1, this.n1);
                return;
            }
            if (i2 == 2) {
                if (this.c1 == 1 && this.o1 == 1 && this.s1 == 2) {
                    this.a1.setVisibility(8);
                    this.P0.setVisibility(8);
                }
                this.H0.setImageResource(R.mipmap.icon_seat_heat_off_5level);
                if (this.c1 == 2) {
                    this.P0.setImageResource(M0(this.E2.f127c));
                    this.P0.setVisibility(0);
                    y1(1, this.n1);
                    return;
                }
                return;
            }
            return;
        }
        if (i == 2) {
            this.h1 = i2;
            if (i2 == 1) {
                this.Y0.setProgressGradientColor(getResources().getColor(R.color.heat_seekbar_progress_start_color), getResources().getColor(R.color.heat_seekbar_progress_end_color));
                this.Y0.setVisibility(0);
                this.L0.setImageResource(R.mipmap.icon_seat_heat_on_5level);
                this.Q0.setImageResource(M0(this.E2.f));
                this.Q0.setVisibility(0);
                y1(2, this.m1);
                return;
            }
            if (i2 == 2) {
                if (this.d1 == 1 && this.p1 == 1 && this.B1 == 2) {
                    this.Y0.setVisibility(8);
                    this.Q0.setVisibility(8);
                }
                this.L0.setImageResource(R.mipmap.icon_seat_heat_off_5level);
                if (this.d1 == 2) {
                    this.Q0.setImageResource(M0(this.E2.f127c));
                    this.Q0.setVisibility(0);
                    y1(2, this.m1);
                    return;
                }
                return;
            }
            return;
        }
        if (i == 3) {
            this.i1 = i2;
            if (i2 == 1) {
                this.Z0.setProgressGradientColor(getResources().getColor(R.color.heat_seekbar_progress_start_color), getResources().getColor(R.color.heat_seekbar_progress_end_color));
                this.Z0.setVisibility(0);
                this.J0.setImageResource(R.mipmap.icon_seat_heat_on_5level);
                this.R0.setImageResource(M0(this.E2.i));
                this.R0.setVisibility(0);
                y1(3, this.l1);
                return;
            }
            if (i2 == 2) {
                if (this.e1 == 1 && this.q1 == 1 && this.t1 == 2) {
                    this.Z0.setVisibility(8);
                    this.R0.setVisibility(8);
                }
                this.J0.setImageResource(R.mipmap.icon_seat_heat_off_5level);
                if (this.e1 == 2) {
                    this.R0.setImageResource(M0(this.E2.i));
                    this.R0.setVisibility(0);
                    y1(3, this.l1);
                    return;
                }
                return;
            }
            return;
        }
        if (i != 4) {
            return;
        }
        this.j1 = i2;
        if (i2 == 1) {
            this.X0.setProgressGradientColor(getResources().getColor(R.color.heat_seekbar_progress_start_color), getResources().getColor(R.color.heat_seekbar_progress_end_color));
            this.X0.setVisibility(0);
            this.O0.setImageResource(R.mipmap.icon_seat_heat_on_5level);
            this.S0.setImageResource(M0(this.E2.l));
            this.S0.setVisibility(0);
            y1(4, this.k1);
            return;
        }
        if (i2 == 2) {
            if (this.f1 == 1 && this.r1 == 1 && this.u1 == 2) {
                this.X0.setVisibility(8);
                this.S0.setVisibility(8);
            }
            this.O0.setImageResource(R.mipmap.icon_seat_heat_off_5level);
            if (this.f1 == 2) {
                this.S0.setImageResource(M0(this.E2.l));
                this.S0.setVisibility(0);
                y1(4, this.k1);
            }
        }
    }

    public final boolean L0() {
        if (((k) this.f).G() != 0) {
            return false;
        }
        if (this.y0 == null) {
            this.y0 = new b.a.a.i.i.a();
        }
        if (this.y0.b()) {
            this.y0.a();
        }
        AcStartVehicleTipsLayout acStartVehicleTipsLayout = (AcStartVehicleTipsLayout) LayoutInflater.from(this.e).inflate(R.layout.dialog_ac_start_vehicle_tips, (ViewGroup) null);
        ((TextView) acStartVehicleTipsLayout.findViewById(R.id.tv_content)).setText(getString(R.string.seat_ventilate_heat_acc_off_alert));
        acStartVehicleTipsLayout.setBtnCallBackListener(new a());
        this.y0.c((Activity) this.e, acStartVehicleTipsLayout, (int) this.e.getResources().getDimension(R.dimen.dp_560), (int) this.e.getResources().getDimension(R.dimen.dp_268));
        return true;
    }

    public final int M0(int i) {
        if (((k) this.f).Z() != 1) {
            return i;
        }
        if (i == R.layout.fragment_seat_heat_ventilation) {
            return R.layout.fragment_seat_heat_ventilation_right_rudder;
        }
        b.a.a.f.h1.b bVar = this.E2;
        int i2 = bVar.d;
        if (i == i2) {
            return i2;
        }
        int i3 = bVar.g;
        if (i == i3) {
            return i3;
        }
        int i4 = bVar.j;
        if (i == i4) {
            return bVar.m;
        }
        if (i == bVar.m) {
            return i4;
        }
        int i5 = bVar.f127c;
        if (i == i5) {
            return i5;
        }
        int i6 = bVar.f;
        if (i == i6) {
            return i6;
        }
        int i7 = bVar.i;
        if (i == i7) {
            return bVar.l;
        }
        if (i == bVar.l) {
            return i7;
        }
        int i8 = bVar.f126b;
        if (i == i8) {
            return i8;
        }
        int i9 = bVar.e;
        if (i == i9) {
            return i9;
        }
        int i10 = bVar.h;
        if (i == i10) {
            return bVar.k;
        }
        if (i == bVar.k) {
            return i10;
        }
        return 0;
    }

    @NonNull
    public final TextView N0() {
        TextView textView = new TextView(this.e);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) this.e.getResources().getDimension(R.dimen.dp_96), (int) this.e.getResources().getDimension(R.dimen.dp_60));
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(this.e.getAssets(), "font/HFMoBestSans-R.otf");
        if (typefaceCreateFromAsset != null) {
            textView.setTypeface(typefaceCreateFromAsset);
        }
        textView.setLayoutParams(layoutParams);
        textView.setGravity(17);
        return textView;
    }

    public final void O0(int i, boolean z) {
        int i2;
        this.u = i;
        if (i == 2) {
            this.i.setVisibility(0);
            this.i.setImageResource(M0(this.E2.f127c));
            this.i.setAlpha(0.33f);
            i2 = R.raw.icon_seat_heat_3level_1_0;
            if (i1()) {
                this.i.setAlpha(0.5f);
                i2 = R.raw.icon_seat_heat_1_0;
            }
        } else if (i == 3) {
            this.i.setVisibility(0);
            this.i.setImageResource(M0(this.E2.f127c));
            this.i.setAlpha(0.66f);
            i2 = R.raw.icon_seat_heat_3level_2_1;
            if (i1()) {
                this.i.setAlpha(1.0f);
                i2 = R.raw.icon_seat_heat_2_1;
            }
        } else if (i != 4) {
            this.i.setVisibility(8);
            i2 = R.raw.icon_seat_heat_3level_0_3;
            if (i1()) {
                i2 = R.raw.icon_seat_heat_0_2;
            }
            if (j1()) {
                int i3 = this.m;
                if (i3 == 2) {
                    this.i.setVisibility(0);
                    this.i.setImageResource(M0(this.E2.f126b));
                    this.i.setAlpha(0.5f);
                } else if (i3 == 3) {
                    this.i.setVisibility(0);
                    this.i.setImageResource(M0(this.E2.f126b));
                    this.i.setAlpha(1.0f);
                }
            } else {
                int i4 = this.m;
                if (i4 == 2) {
                    this.i.setVisibility(0);
                    this.i.setImageResource(M0(this.E2.f126b));
                    this.i.setAlpha(0.33f);
                } else if (i4 == 3) {
                    this.i.setVisibility(0);
                    this.i.setImageResource(M0(this.E2.f126b));
                    this.i.setAlpha(0.66f);
                } else if (i4 == 4) {
                    this.i.setVisibility(0);
                    this.i.setImageResource(M0(this.E2.f126b));
                    this.i.setAlpha(1.0f);
                }
            }
        } else {
            this.i.setVisibility(0);
            this.i.setImageResource(M0(this.E2.f127c));
            this.i.setAlpha(1.0f);
            i2 = R.raw.icon_seat_heat_3level_3_2;
        }
        if (!z) {
            if (this.v != this.u) {
                if (this.K.isAnimating()) {
                    this.K.cancelAnimation();
                }
                v1(i2, this.K);
            } else if (!this.K.isAnimating()) {
                v1(i2, this.K);
            }
        }
        this.v = this.u;
    }

    @Override // b.a.a.d.h
    public void P(int i, int i2, boolean z) {
        BydLogHelper.logW("SeatHeatVentilationFragment", "updateSeatVentilationState2 area = " + i + " --  value = " + i2);
        if (i == 3) {
            if (q1(this.q)) {
                BydLogHelper.logD("按优先级策略，左后座椅通风当前后车身控制器的0x486 1.0-1.2 有有效值，不处理402信号");
                return;
            }
            this.S1 = i2;
            z1(i, i2, z);
            this.Y1 = this.S1;
            return;
        }
        if (i != 4) {
            return;
        }
        if (q1(this.s)) {
            BydLogHelper.logD("按优先级策略，右后座椅通风当前后车身控制器的0x486 2.0-2.2 有有效值，不处理42F信号");
            return;
        }
        this.T1 = i2;
        z1(i, i2, z);
        this.Z1 = this.T1;
    }

    public final void P0(int i, boolean z) {
        int i2;
        BydLogHelper.logW("SeatHeatVentilationFragment", "handlerDriverSeatVent2Or3LevelUI value = " + i);
        this.m = i;
        if (i == 2) {
            this.i.setImageResource(M0(this.E2.f126b));
            this.i.setVisibility(0);
            this.i.setAlpha(0.33f);
            i2 = R.raw.icon_seat_ventilation_3level_1_0;
            if (j1()) {
                this.i.setAlpha(0.5f);
                i2 = R.raw.icon_seat_ventilation_1_0;
            }
        } else if (i == 3) {
            this.i.setImageResource(M0(this.E2.f126b));
            this.i.setVisibility(0);
            this.i.setAlpha(0.66f);
            i2 = R.raw.icon_seat_ventilation_3level_2_1;
            if (j1()) {
                this.i.setAlpha(1.0f);
                i2 = R.raw.icon_seat_ventilation_2_1;
            }
        } else if (i != 4) {
            this.i.setVisibility(8);
            i2 = R.raw.icon_seat_ventilation_3level_0_3;
            if (j1()) {
                i2 = R.raw.icon_seat_ventilation_0_2;
            }
            if (i1()) {
                int i3 = this.u;
                if (i3 == 2) {
                    this.i.setImageResource(M0(this.E2.f127c));
                    this.i.setVisibility(0);
                    this.i.setAlpha(0.5f);
                } else if (i3 == 3) {
                    this.i.setImageResource(M0(this.E2.f127c));
                    this.i.setVisibility(0);
                    this.i.setAlpha(1.0f);
                }
            } else {
                int i4 = this.u;
                if (i4 == 2) {
                    this.i.setImageResource(M0(this.E2.f127c));
                    this.i.setVisibility(0);
                    this.i.setAlpha(0.33f);
                } else if (i4 == 3) {
                    this.i.setImageResource(M0(this.E2.f127c));
                    this.i.setVisibility(0);
                    this.i.setAlpha(0.66f);
                } else if (i4 == 4) {
                    this.i.setImageResource(M0(this.E2.f127c));
                    this.i.setVisibility(0);
                    this.i.setAlpha(1.0f);
                }
            }
        } else {
            this.i.setImageResource(M0(this.E2.f126b));
            this.i.setVisibility(0);
            this.i.setAlpha(1.0f);
            i2 = R.raw.icon_seat_ventilation_3level_3_2;
        }
        if (!z) {
            if (this.n != this.m) {
                if (this.J.isAnimating()) {
                    this.J.cancelAnimation();
                }
                v1(i2, this.J);
            } else if (!this.J.isAnimating()) {
                v1(i2, this.J);
            }
        }
        this.n = this.m;
    }

    public final void Q0(int i, boolean z) {
        int i2;
        int i3;
        int i4;
        if (i == 2) {
            this.k.setVisibility(0);
            this.k.setImageResource(M0(this.E2.i));
            this.k.setAlpha(0.33f);
            i2 = R.raw.icon_seat_heat_3level_1_0;
            if (k1()) {
                this.k.setAlpha(0.5f);
                i2 = R.raw.icon_seat_heat_1_0;
            }
        } else if (i == 3) {
            this.k.setVisibility(0);
            this.k.setImageResource(M0(this.E2.i));
            this.k.setAlpha(0.66f);
            i2 = R.raw.icon_seat_heat_3level_2_1;
            if (k1()) {
                this.k.setAlpha(1.0f);
                i2 = R.raw.icon_seat_heat_2_1;
            }
        } else if (i != 4) {
            this.k.setVisibility(8);
            i2 = R.raw.icon_seat_heat_3level_0_3;
            if (k1()) {
                i2 = R.raw.icon_seat_heat_0_2;
            }
            if (l1()) {
                int i5 = this.q;
                if (i5 == 2 || (i4 = this.S1) == 2) {
                    this.k.setVisibility(0);
                    this.k.setImageResource(M0(this.E2.h));
                    this.k.setAlpha(0.5f);
                } else if (i5 == 3 || i4 == 3) {
                    this.k.setVisibility(0);
                    this.k.setImageResource(M0(this.E2.h));
                    this.k.setAlpha(1.0f);
                }
            } else {
                int i6 = this.q;
                if (i6 == 2 || (i3 = this.S1) == 2) {
                    this.k.setVisibility(0);
                    this.k.setImageResource(M0(this.E2.h));
                    this.k.setAlpha(0.33f);
                } else if (i6 == 3 || i3 == 3) {
                    this.k.setVisibility(0);
                    this.k.setImageResource(M0(this.E2.h));
                    this.k.setAlpha(0.66f);
                } else if (i6 == 4 || i3 == 4) {
                    this.k.setVisibility(0);
                    this.k.setImageResource(M0(this.E2.h));
                    this.k.setAlpha(1.0f);
                }
            }
        } else {
            this.k.setVisibility(0);
            this.k.setImageResource(M0(this.E2.i));
            this.k.setAlpha(1.0f);
            i2 = R.raw.icon_seat_heat_3level_3_2;
        }
        if (z) {
            return;
        }
        if (this.z == this.y && this.a2 == this.U1) {
            if (this.M.isAnimating()) {
                return;
            }
            v1(i2, this.M);
        } else {
            if (this.M.isAnimating()) {
                this.M.cancelAnimation();
            }
            v1(i2, this.M);
        }
    }

    public final void R0(int i, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5 = q1(this.q) ? this.q : this.S1;
        BydLogHelper.logW("SeatHeatVentilationFragment", "handlerLrSeatVent2Or3LevelConfig seatVentilationLeftRearCurState = " + i5);
        if (i5 == 2) {
            this.k.setVisibility(0);
            this.k.setImageResource(M0(this.E2.h));
            this.k.setAlpha(0.33f);
            i2 = R.raw.icon_seat_ventilation_3level_1_0;
            if (l1()) {
                this.k.setAlpha(0.5f);
                i2 = R.raw.icon_seat_ventilation_1_0;
            }
        } else if (i5 == 3) {
            this.k.setVisibility(0);
            this.k.setImageResource(M0(this.E2.h));
            this.k.setAlpha(0.66f);
            i2 = R.raw.icon_seat_ventilation_3level_2_1;
            if (l1()) {
                this.k.setAlpha(1.0f);
                i2 = R.raw.icon_seat_ventilation_2_1;
            }
        } else if (i5 != 4) {
            this.k.setVisibility(8);
            i2 = R.raw.icon_seat_ventilation_3level_0_3;
            if (l1()) {
                i2 = R.raw.icon_seat_ventilation_0_2;
            }
            if (k1()) {
                int i6 = this.y;
                if (i6 == 2 || (i4 = this.U1) == 2) {
                    this.k.setImageResource(M0(this.E2.i));
                    this.k.setVisibility(0);
                    this.k.setAlpha(0.5f);
                } else if (i6 == 3 || i4 == 3) {
                    this.k.setImageResource(M0(this.E2.i));
                    this.k.setVisibility(0);
                    this.k.setAlpha(1.0f);
                }
            } else {
                int i7 = this.y;
                if (i7 == 2 || (i3 = this.U1) == 2) {
                    this.k.setImageResource(M0(this.E2.i));
                    this.k.setVisibility(0);
                    this.k.setAlpha(0.33f);
                } else if (i7 == 3 || i3 == 3) {
                    this.k.setImageResource(M0(this.E2.i));
                    this.k.setVisibility(0);
                    this.k.setAlpha(0.66f);
                } else if (i7 == 4 || i3 == 4) {
                    this.k.setImageResource(M0(this.E2.i));
                    this.k.setVisibility(0);
                    this.k.setAlpha(1.0f);
                }
            }
        } else {
            this.k.setVisibility(0);
            this.k.setImageResource(M0(this.E2.h));
            this.k.setAlpha(1.0f);
            i2 = R.raw.icon_seat_ventilation_3level_3_2;
        }
        if (z) {
            return;
        }
        if (this.r == this.q && this.Y1 == this.S1) {
            if (this.L.isAnimating()) {
                return;
            }
            v1(i2, this.L);
        } else {
            if (this.L.isAnimating()) {
                this.L.cancelAnimation();
            }
            v1(i2, this.L);
        }
    }

    public final void S0(int i, boolean z) {
        int i2;
        this.w = i;
        if (i == 2) {
            this.j.setVisibility(0);
            this.j.setImageResource(M0(this.E2.f));
            this.j.setAlpha(0.33f);
            i2 = R.raw.icon_seat_heat_3level_1_0;
            if (m1()) {
                this.j.setAlpha(0.5f);
                i2 = R.raw.icon_seat_heat_1_0;
            }
        } else if (i == 3) {
            this.j.setVisibility(0);
            this.j.setImageResource(M0(this.E2.f));
            this.j.setAlpha(0.66f);
            i2 = R.raw.icon_seat_heat_3level_2_1;
            if (m1()) {
                this.j.setAlpha(1.0f);
                i2 = R.raw.icon_seat_heat_2_1;
            }
        } else if (i != 4) {
            this.j.setVisibility(8);
            i2 = R.raw.icon_seat_heat_3level_0_3;
            if (m1()) {
                i2 = R.raw.icon_seat_heat_0_2;
            }
            if (n1()) {
                int i3 = this.o;
                if (i3 == 2) {
                    this.j.setVisibility(0);
                    this.j.setImageResource(M0(this.E2.e));
                    this.j.setAlpha(0.5f);
                } else if (i3 == 3) {
                    this.j.setVisibility(0);
                    this.j.setImageResource(M0(this.E2.e));
                    this.j.setAlpha(1.0f);
                }
            } else {
                int i4 = this.o;
                if (i4 == 2) {
                    this.j.setVisibility(0);
                    this.j.setImageResource(M0(this.E2.e));
                    this.j.setAlpha(0.33f);
                } else if (i4 == 3) {
                    this.j.setVisibility(0);
                    this.j.setImageResource(M0(this.E2.e));
                    this.j.setAlpha(0.66f);
                } else if (i4 == 4) {
                    this.j.setVisibility(0);
                    this.j.setImageResource(M0(this.E2.e));
                    this.j.setAlpha(1.0f);
                }
            }
        } else {
            this.j.setVisibility(0);
            this.j.setImageResource(M0(this.E2.f));
            this.j.setAlpha(1.0f);
            i2 = R.raw.icon_seat_heat_3level_3_2;
        }
        if (!z) {
            if (this.x != this.w) {
                if (this.P.isAnimating()) {
                    this.P.cancelAnimation();
                }
                v1(i2, this.P);
            } else if (!this.P.isAnimating()) {
                v1(i2, this.P);
            }
        }
        this.x = this.w;
    }

    public final void T0(int i, boolean z) {
        int i2;
        BydLogHelper.logW("SeatHeatVentilationFragment", "handlerPassengerSeatVent2Or3LevelUI value = " + i);
        this.o = i;
        if (i == 2) {
            this.j.setVisibility(0);
            this.j.setImageResource(M0(this.E2.e));
            this.j.setAlpha(0.33f);
            i2 = R.raw.icon_seat_ventilation_3level_1_0;
            if (n1()) {
                this.j.setAlpha(0.5f);
                i2 = R.raw.icon_seat_ventilation_1_0;
            }
        } else if (i == 3) {
            this.j.setVisibility(0);
            this.j.setImageResource(M0(this.E2.e));
            this.j.setAlpha(0.66f);
            i2 = R.raw.icon_seat_ventilation_3level_2_1;
            if (n1()) {
                this.j.setAlpha(1.0f);
                i2 = R.raw.icon_seat_ventilation_2_1;
            }
        } else if (i != 4) {
            this.j.setVisibility(8);
            this.j.setImageResource(M0(this.E2.f));
            i2 = R.raw.icon_seat_ventilation_3level_0_3;
            if (n1()) {
                i2 = R.raw.icon_seat_ventilation_0_2;
            }
            if (m1()) {
                int i3 = this.w;
                if (i3 == 2) {
                    this.j.setVisibility(0);
                    this.j.setAlpha(0.5f);
                } else if (i3 == 3) {
                    this.j.setVisibility(0);
                    this.j.setAlpha(1.0f);
                }
            } else {
                int i4 = this.w;
                if (i4 == 2) {
                    this.j.setVisibility(0);
                    this.j.setAlpha(0.33f);
                } else if (i4 == 3) {
                    this.j.setVisibility(0);
                    this.j.setAlpha(0.66f);
                } else if (i4 == 4) {
                    this.j.setVisibility(0);
                    this.j.setAlpha(1.0f);
                }
            }
        } else {
            this.j.setVisibility(0);
            this.j.setImageResource(M0(this.E2.e));
            this.j.setAlpha(1.0f);
            i2 = R.raw.icon_seat_ventilation_3level_3_2;
        }
        if (!z) {
            if (this.p != this.o) {
                if (this.O.isAnimating()) {
                    this.O.cancelAnimation();
                }
                v1(i2, this.O);
            } else if (!this.O.isAnimating()) {
                v1(i2, this.O);
            }
        }
        this.p = this.o;
    }

    public final void U0(int i, boolean z) {
        int i2;
        int i3;
        int i4;
        BydLogHelper.logW("SeatHeatVentilationFragment", "handlerRrSeatHeating2Or3LevelUI value = " + i + " --  isRevert = " + z);
        if (i == 2) {
            this.l.setVisibility(0);
            this.l.setImageResource(M0(this.E2.l));
            this.l.setAlpha(0.33f);
            i2 = R.raw.icon_seat_heat_3level_1_0;
            if (o1()) {
                this.l.setAlpha(0.5f);
                i2 = R.raw.icon_seat_heat_1_0;
            }
        } else if (i == 3) {
            this.l.setVisibility(0);
            this.l.setImageResource(M0(this.E2.l));
            this.l.setAlpha(0.66f);
            i2 = R.raw.icon_seat_heat_3level_2_1;
            if (o1()) {
                this.l.setAlpha(1.0f);
                i2 = R.raw.icon_seat_heat_2_1;
            }
        } else if (i != 4) {
            this.l.setVisibility(8);
            BydLogHelper.logW("SeatHeatVentilationFragment", "handlerRrSeatHeating2Or3LevelUI mSeatVentilationRightRearState = " + this.s + " --  mSeatVentilationRightRearState2 = " + this.T1);
            i2 = R.raw.icon_seat_heat_3level_0_3;
            if (o1()) {
                i2 = R.raw.icon_seat_heat_0_2;
            }
            if (p1()) {
                int i5 = this.s;
                if (i5 == 2 || (i4 = this.T1) == 2) {
                    this.l.setVisibility(0);
                    this.l.setImageResource(M0(this.E2.k));
                    this.l.setAlpha(0.5f);
                } else if (i5 == 3 || i4 == 3) {
                    this.l.setVisibility(0);
                    this.l.setImageResource(M0(this.E2.k));
                    this.l.setAlpha(1.0f);
                }
            } else {
                int i6 = this.s;
                if (i6 == 2 || (i3 = this.T1) == 2) {
                    this.l.setVisibility(0);
                    this.l.setImageResource(M0(this.E2.k));
                    this.l.setAlpha(0.33f);
                } else if (i6 == 3 || i3 == 3) {
                    this.l.setVisibility(0);
                    this.l.setImageResource(M0(this.E2.k));
                    this.l.setAlpha(0.66f);
                } else if (i6 == 4 || i3 == 4) {
                    this.l.setVisibility(0);
                    this.l.setImageResource(M0(this.E2.k));
                    this.l.setAlpha(1.0f);
                }
            }
        } else {
            this.l.setVisibility(0);
            this.l.setImageResource(M0(this.E2.l));
            this.l.setAlpha(1.0f);
            i2 = R.raw.icon_seat_heat_3level_3_2;
        }
        if (z) {
            return;
        }
        if (this.B == this.A && this.b2 == this.V1) {
            if (this.T.isAnimating()) {
                return;
            }
            v1(i2, this.T);
        } else {
            if (this.T.isAnimating()) {
                this.T.cancelAnimation();
            }
            v1(i2, this.T);
        }
    }

    public final void V0(int i, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5 = q1(this.s) ? this.s : this.T1;
        BydLogHelper.logW("SeatHeatVentilationFragment", "handlerRrSeatVent2Or3LevelConfig seatVentilationRightRearCurState = " + i5);
        if (i5 == 2) {
            this.l.setVisibility(0);
            this.l.setImageResource(M0(this.E2.k));
            this.l.setAlpha(0.33f);
            i2 = R.raw.icon_seat_ventilation_3level_1_0;
            if (p1()) {
                this.l.setAlpha(0.5f);
                i2 = R.raw.icon_seat_ventilation_1_0;
            }
        } else if (i5 == 3) {
            this.l.setVisibility(0);
            this.l.setImageResource(M0(this.E2.k));
            this.l.setAlpha(0.66f);
            i2 = R.raw.icon_seat_ventilation_3level_2_1;
            if (p1()) {
                this.l.setAlpha(1.0f);
                i2 = R.raw.icon_seat_ventilation_2_1;
            }
        } else if (i5 != 4) {
            this.l.setVisibility(8);
            i2 = R.raw.icon_seat_ventilation_3level_0_3;
            if (p1()) {
                i2 = R.raw.icon_seat_ventilation_0_2;
            }
            if (o1()) {
                int i6 = this.A;
                if (i6 == 2 || (i4 = this.V1) == 2) {
                    this.l.setImageResource(M0(this.E2.l));
                    this.l.setVisibility(0);
                    this.l.setAlpha(0.5f);
                } else if (i6 == 3 || i4 == 3) {
                    this.l.setImageResource(M0(this.E2.l));
                    this.l.setVisibility(0);
                    this.l.setAlpha(1.0f);
                }
            } else {
                int i7 = this.A;
                if (i7 == 2 || (i3 = this.V1) == 2) {
                    this.l.setImageResource(M0(this.E2.l));
                    this.l.setVisibility(0);
                    this.l.setAlpha(0.33f);
                } else if (i7 == 3 || i3 == 3) {
                    this.l.setImageResource(M0(this.E2.l));
                    this.l.setVisibility(0);
                    this.l.setAlpha(0.66f);
                } else if (i7 == 4 || i3 == 4) {
                    this.l.setImageResource(M0(this.E2.l));
                    this.l.setVisibility(0);
                    this.l.setAlpha(1.0f);
                }
            }
        } else {
            this.l.setVisibility(0);
            this.l.setImageResource(M0(this.E2.k));
            this.l.setAlpha(1.0f);
            i2 = R.raw.icon_seat_ventilation_3level_3_2;
        }
        if (z) {
            return;
        }
        if (this.t == this.s && this.Z1 == this.T1) {
            if (this.S.isAnimating()) {
                return;
            }
            v1(i2, this.S);
        } else {
            if (this.S.isAnimating()) {
                this.S.cancelAnimation();
            }
            v1(i2, this.S);
        }
    }

    @Override // b.a.a.d.h
    public void W(int i, int i2, boolean z) {
        BydLogHelper.logD("update5LevelSeatHeatingMode area:" + i + " -- state:" + i2);
        if (i == 1) {
            this.c1 = i2;
            if (i2 != 1) {
                if (i2 == 2) {
                    this.a1.setProgressGradientColor(getResources().getColor(R.color.heat_seekbar_progress_start_color), getResources().getColor(R.color.heat_seekbar_progress_end_color));
                    this.a1.setVisibility(0);
                    this.W0.setImageResource(R.mipmap.icon_seat_ai_heat_5level);
                    this.P0.setImageResource(M0(this.E2.f127c));
                    this.P0.setVisibility(0);
                    y1(1, this.n1);
                    return;
                }
                return;
            }
            if (this.g1 == 2 && this.o1 == 1 && this.s1 == 2) {
                this.a1.setVisibility(8);
                this.P0.setVisibility(8);
            }
            this.W0.setImageResource(R.mipmap.icon_seat_ai_off_5level);
            if (this.g1 == 1) {
                this.P0.setImageResource(M0(this.E2.f127c));
                this.P0.setVisibility(0);
                y1(1, this.n1);
                return;
            }
            return;
        }
        if (i == 2) {
            this.d1 = i2;
            if (i2 != 1) {
                if (i2 == 2) {
                    this.Y0.setProgressGradientColor(getResources().getColor(R.color.heat_seekbar_progress_start_color), getResources().getColor(R.color.heat_seekbar_progress_end_color));
                    this.Y0.setVisibility(0);
                    this.U0.setImageResource(R.mipmap.icon_seat_ai_heat_5level);
                    this.Q0.setImageResource(M0(this.E2.f));
                    this.Q0.setVisibility(0);
                    y1(2, this.m1);
                    return;
                }
                return;
            }
            if (this.h1 == 2 && this.p1 == 1 && this.B1 == 2) {
                this.Y0.setVisibility(8);
                this.Q0.setVisibility(8);
            }
            this.U0.setImageResource(R.mipmap.icon_seat_ai_off_5level);
            if (this.h1 == 1) {
                this.Q0.setImageResource(M0(this.E2.f));
                this.Q0.setVisibility(0);
                y1(2, this.m1);
                return;
            }
            return;
        }
        if (i == 3) {
            this.e1 = i2;
            if (i2 != 1) {
                if (i2 == 2) {
                    this.Z0.setProgressGradientColor(getResources().getColor(R.color.heat_seekbar_progress_start_color), getResources().getColor(R.color.heat_seekbar_progress_end_color));
                    this.Z0.setVisibility(0);
                    this.V0.setImageResource(R.mipmap.icon_seat_ai_heat_5level);
                    this.R0.setImageResource(M0(this.E2.i));
                    this.R0.setVisibility(0);
                    y1(3, this.l1);
                    return;
                }
                return;
            }
            if (this.i1 == 2 && this.q1 == 1 && this.t1 == 2) {
                this.Z0.setVisibility(8);
                this.R0.setVisibility(8);
            }
            this.V0.setImageResource(R.mipmap.icon_seat_ai_off_5level);
            if (this.i1 == 1) {
                this.R0.setImageResource(M0(this.E2.i));
                this.R0.setVisibility(0);
                y1(3, this.l1);
                return;
            }
            return;
        }
        if (i != 4) {
            return;
        }
        this.f1 = i2;
        if (i2 != 1) {
            if (i2 == 2) {
                this.X0.setProgressGradientColor(getResources().getColor(R.color.heat_seekbar_progress_start_color), getResources().getColor(R.color.heat_seekbar_progress_end_color));
                this.X0.setVisibility(0);
                this.T0.setImageResource(R.mipmap.icon_seat_ai_heat_5level);
                this.S0.setImageResource(M0(this.E2.l));
                this.S0.setVisibility(0);
                y1(4, this.k1);
                return;
            }
            return;
        }
        if (this.j1 == 2 && this.r1 == 1 && this.u1 == 2) {
            this.X0.setVisibility(8);
            this.S0.setVisibility(8);
        }
        this.T0.setImageResource(R.mipmap.icon_seat_ai_off_5level);
        if (this.j1 == 1) {
            this.S0.setImageResource(M0(this.E2.l));
            this.S0.setVisibility(0);
            y1(4, this.k1);
        }
    }

    public final void W0() {
        int i = this.w0;
        if (i != 2 && i != 3) {
            this.F.setVisibility(8);
            this.t0.setVisibility(8);
            return;
        }
        this.F.setVisibility(0);
        this.t0.setVisibility(0);
        int i2 = this.w0;
        if (i2 == 2) {
            this.t0.setText(R.string.heat_rear_window_and_outside_mirror);
        } else if (i2 == 3) {
            this.t0.setText(R.string.heat_outside_mirror);
        }
        e(2, this.x0, false);
    }

    public final void X0() {
        BydLogHelper.logW("SeatHeatVentilationFragment", "init2LevelUiStyle start ");
        e1();
        Z0();
        Y0();
        a1();
        b1();
        z1(1, this.m, false);
        if (this.Q1 == 1) {
            z1(2, this.o, false);
        } else {
            r(this.R1, false);
        }
        if (q1(this.q)) {
            p0(3, this.q, false);
        } else {
            P(3, this.S1, false);
        }
        if (q1(this.s)) {
            p0(4, this.s, false);
        } else {
            P(4, this.T1, false);
        }
        A1(1, this.u, false);
        if (this.Q1 == 1) {
            A1(2, this.w, false);
        } else {
            D(this.W1, false);
        }
        if (q1(this.y)) {
            H(3, this.y, false);
        } else {
            n0(3, this.U1, false);
        }
        if (q1(this.A)) {
            H(4, this.A, false);
        } else {
            n0(4, this.V1, false);
        }
        BydLogHelper.logW("SeatHeatVentilationFragment", "init2LevelUiStyle END ");
    }

    public final void Y0() {
        boolean z = this.Q1 == 1;
        BydLogHelper.logW("SeatHeatVentilationFragment", "init2Or3LevelCopilotSeatUI isL1PlatformConfig：" + z);
        if (q1(z ? this.o : this.R1)) {
            f1(1, n1() ? 2 : 3);
            w1(true, this.f0, this.f2, this.o2);
            this.o2.setImageResource(M0(this.E2.g));
            if (q1(z ? this.w : this.W1)) {
                f1(5, m1() ? 2 : 3);
                return;
            } else {
                w1(false, this.M1, this.e0, this.P);
                return;
            }
        }
        w1(false, this.d0);
        if (!q1(z ? this.w : this.W1)) {
            w1(false, this.f0, this.f2, this.o2, this.c0);
            return;
        }
        f1(5, m1() ? 2 : 3);
        w1(false, this.M1, this.O);
        w1(true, this.f0, this.f2, this.o2);
        this.o2.setImageResource(M0(this.E2.g));
    }

    public final void Z0() {
        BydLogHelper.logW("SeatHeatVentilationFragment", "init2Or3LevelDriverSeatUI :");
        if (q1(this.m)) {
            f1(0, j1() ? 2 : 3);
            w1(true, this.X, this.d2, this.m2);
            this.m2.setImageResource(M0(this.E2.d));
            if (q1(this.u)) {
                f1(4, i1() ? 2 : 3);
                return;
            } else {
                w1(false, this.K1, this.W, this.K);
                return;
            }
        }
        w1(false, this.V);
        if (!q1(this.u)) {
            w1(false, this.X, this.d2, this.m2, this.U);
            return;
        }
        f1(4, i1() ? 2 : 3);
        w1(false, this.K1, this.J);
        w1(true, this.X, this.d2, this.m2);
        this.m2.setImageResource(M0(this.E2.d));
    }

    public final void a1() {
        BydLogHelper.logW("SeatHeatVentilationFragment", "init2Or3LevelLeftRearSeatUI :");
        if (q1(this.q) || q1(this.S1)) {
            f1(2, k1() ? 2 : 3);
            w1(true, this.b0, this.e2, this.n2);
            this.n2.setImageResource(M0(this.E2.j));
            if (q1(this.y) || q1(this.U1)) {
                f1(6, k1() ? 2 : 3);
                return;
            } else {
                w1(false, this.L1, this.a0, this.M);
                return;
            }
        }
        w1(false, this.Z);
        if (!q1(this.y) && !q1(this.U1)) {
            w1(false, this.Y, this.b0, this.e2, this.n2);
            return;
        }
        f1(6, k1() ? 2 : 3);
        w1(false, this.L1, this.L);
        w1(true, this.b0, this.e2, this.n2);
        this.n2.setImageResource(M0(this.E2.j));
    }

    @Override // b.a.a.d.h
    public void b0(int i, boolean z) {
        BydLogHelper.logD("update5LevelSteeringWheelHeatingLevel value = " + i + "  -- mSteeringWheelWarmState:" + this.C);
        int i2 = this.A1;
        if (i2 == 3 || i2 == 6) {
            if (this.C == 2) {
                this.O1 = i;
                B1(i);
                this.b1.setProgress(i);
                return;
            }
            return;
        }
        if (i2 == 2 || i2 == 5) {
            int i3 = this.C;
            if (i3 == 2) {
                this.P1 = i;
                x1(i, z);
                return;
            } else {
                if (i3 == 1) {
                    v1(R.raw.steering_wheel_heat_3level_0_3, this.I);
                    return;
                }
                return;
            }
        }
        int i4 = this.z1;
        if ((i4 == 1 || i4 == 2) && this.C == 2) {
            this.O1 = i;
            B1(i);
            this.b1.setProgress(i);
        }
    }

    public final void b1() {
        BydLogHelper.logW("SeatHeatVentilationFragment", "init2Or3LevelLeftRearSeatUI :");
        if (q1(this.s) || q1(this.T1)) {
            f1(3, p1() ? 2 : 3);
            w1(true, this.p2, this.j0, this.g2);
            this.p2.setImageResource(M0(this.E2.m));
            if (q1(this.A) || q1(this.V1)) {
                f1(7, o1() ? 2 : 3);
                return;
            } else {
                w1(false, this.N1, this.i0, this.T);
                return;
            }
        }
        w1(false, this.h0);
        if (!q1(this.A) && !q1(this.V1)) {
            w1(false, this.g0, this.j0, this.g2, this.p2);
            return;
        }
        f1(7, o1() ? 2 : 3);
        w1(false, this.N1, this.S);
        w1(true, this.j0, this.g2, this.p2);
        this.p2.setImageResource(M0(this.E2.m));
    }

    public final void c1() {
        int i = this.w0;
        if (i != 2 && i != 3) {
            this.G.setVisibility(8);
            this.u0.setVisibility(8);
            return;
        }
        this.G.setVisibility(0);
        this.u0.setVisibility(0);
        int i2 = this.w0;
        if (i2 == 2) {
            this.u0.setText(R.string.heat_rear_window_and_outside_mirror);
        } else if (i2 == 3) {
            this.u0.setText(R.string.heat_outside_mirror);
        }
        e(2, this.x0, false);
    }

    public final void d1() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        this.c1 = ((k) this.f).h(1);
        this.d1 = ((k) this.f).h(2);
        this.e1 = ((k) this.f).h(3);
        this.f1 = ((k) this.f).h(4);
        this.n1 = ((k) this.f).g(1);
        this.m1 = ((k) this.f).g(2);
        this.l1 = ((k) this.f).g(3);
        this.k1 = ((k) this.f).g(4);
        this.o1 = ((k) this.f).k(1);
        this.p1 = ((k) this.f).k(2);
        this.q1 = ((k) this.f).k(3);
        this.r1 = ((k) this.f).k(4);
        this.v1 = ((k) this.f).j(1);
        this.w1 = ((k) this.f).j(2);
        this.x1 = ((k) this.f).j(3);
        this.y1 = ((k) this.f).j(4);
        w1(false, this.j0, this.g0, this.b0, this.Y, this.X, this.U, this.f0, this.c0);
        int i12 = this.g1;
        if ((i12 == 1 || i12 == 2) && (i = this.s1) != 1 && i != 2) {
            w1(true, this.B0, this.a1, this.H0, this.h2, this.m2);
            this.m2.setImageResource(M0(this.E2.d));
            K(1, this.g1, false);
            s(1, this.n1, false);
        }
        int i13 = this.s1;
        if ((i13 == 1 || i13 == 2) && (i2 = this.g1) != 1 && i2 != 2) {
            w1(true, this.B0, this.a1, this.F0, this.h2, this.m2);
            this.m2.setImageResource(M0(this.E2.d));
            J(1, this.s1, false);
            x(1, this.v1, false);
        }
        int i14 = this.g1;
        if ((i14 == 1 || i14 == 2) && ((i3 = this.s1) == 1 || i3 == 2)) {
            w1(true, this.B0, this.a1, this.H0, this.F0, this.W0, this.C1, this.D1, this.h2, this.m2);
            this.m2.setImageResource(M0(this.E2.d));
            J(1, this.s1, false);
            K(1, this.g1, false);
            x(1, this.v1, false);
            s(1, this.n1, false);
            W(1, this.c1, false);
            I(1, this.o1, false);
        }
        int i15 = this.h1;
        if ((i15 == 1 || i15 == 2) && (i4 = this.B1) != 1 && i4 != 2) {
            w1(true, this.D0, this.Y0, this.L0, this.j2, this.o2);
            this.o2.setImageResource(M0(this.E2.g));
            K(2, this.h1, false);
            s(2, this.m1, false);
        }
        int i16 = this.B1;
        if ((i16 == 1 || i16 == 2) && (i5 = this.h1) != 1 && i5 != 2) {
            w1(true, this.D0, this.Y0, this.K0, this.j2, this.o2);
            this.o2.setImageResource(M0(this.E2.g));
            J(2, this.B1, false);
            x(2, this.w1, false);
        }
        int i17 = this.h1;
        if ((i17 == 1 || i17 == 2) && ((i6 = this.B1) == 1 || i6 == 2)) {
            w1(true, this.D0, this.Y0, this.K0, this.L0, this.U0, this.E1, this.F1, this.j2, this.o2);
            this.o2.setImageResource(M0(this.E2.g));
            J(2, this.B1, false);
            K(2, this.h1, false);
            s(2, this.w1, false);
            x(2, this.w1, false);
            W(2, this.d1, false);
            I(2, this.p1, false);
        }
        int i18 = this.i1;
        if ((i18 == 1 || i18 == 2) && (i7 = this.t1) != 1 && i7 != 2) {
            w1(true, this.C0, this.Z0, this.J0, this.i2, this.n2);
            this.n2.setImageResource(M0(this.E2.j));
            K(3, this.i1, false);
            s(3, this.l1, false);
        }
        int i19 = this.t1;
        if ((i19 == 1 || i19 == 2) && (i8 = this.i1) != 1 && i8 != 2) {
            w1(true, this.C0, this.Z0, this.I0, this.i2, this.n2);
            this.n2.setImageResource(M0(this.E2.j));
            J(3, this.t1, false);
            x(3, this.x1, false);
        }
        int i20 = this.i1;
        if ((i20 == 1 || i20 == 2) && ((i9 = this.t1) == 1 || i9 == 2)) {
            w1(true, this.C0, this.Z0, this.I0, this.J0, this.V0, this.G1, this.H1, this.i2, this.n2);
            this.n2.setImageResource(M0(this.E2.j));
            K(3, this.i1, false);
            J(3, this.t1, false);
            s(3, this.l1, false);
            x(3, this.x1, false);
            W(3, this.e1, false);
            I(3, this.q1, false);
        }
        int i21 = this.j1;
        if ((i21 == 1 || i21 == 2) && (i10 = this.u1) != 1 && i10 != 2) {
            w1(true, this.E0, this.X0, this.O0, this.k2, this.p2);
            this.p2.setImageResource(M0(this.E2.m));
            K(4, this.j1, false);
            s(4, this.k1, false);
        }
        int i22 = this.u1;
        if ((i22 == 1 || i22 == 2) && (i11 = this.j1) != 1 && i11 != 2) {
            w1(true, this.E0, this.X0, this.N0, this.k2, this.p2);
            this.p2.setImageResource(M0(this.E2.m));
            J(4, this.u1, false);
            x(4, this.y1, false);
        }
        int i23 = this.j1;
        if (i23 == 1 || i23 == 2) {
            int i24 = this.u1;
            if (i24 == 1 || i24 == 2) {
                w1(true, this.E0, this.X0, this.N0, this.O0, this.T0, this.I1, this.J1, this.k2, this.p2);
                this.p2.setImageResource(M0(this.E2.m));
                K(4, this.j1, false);
                J(4, this.u1, false);
                x(4, this.y1, false);
                s(4, this.k1, false);
                W(4, this.f1, false);
                I(4, this.r1, false);
            }
        }
    }

    @Override // b.a.a.d.h
    public void e(int i, int i2, boolean z) {
        BydLogHelper.logD("updateAcDefrostState area: " + i + " == state: " + i2);
        int i3 = this.w0;
        if (i3 != 2 && i3 != 3) {
            BydLogHelper.logW("SeatHeatVentilationFragment", "updateAcDefrostState 当前配置不在通风加热页显示 mDefrostRearConfig : " + this.w0);
            return;
        }
        if (i == 2) {
            this.x0 = i2;
            int i4 = this.A1;
            if (i4 == 3 || i4 == 6 || ((k) this.f).W() == 1 || ((k) this.f).W() == 2) {
                if (i2 != 1) {
                    this.M0.setVisibility(8);
                    int i5 = this.w0;
                    if (i5 == 2) {
                        v1(R.raw.icon_rear_window_rearview_mirror_heat_open, this.R);
                        return;
                    } else {
                        if (i5 == 3) {
                            v1(R.raw.icon_dock_mirror_heating_off_seat, this.R);
                            return;
                        }
                        return;
                    }
                }
                this.M0.setVisibility(0);
                this.M0.setImageResource(this.E2.p);
                int i6 = this.w0;
                if (i6 == 2) {
                    v1(R.raw.icon_rear_window_rearview_mirror_heat_close, this.R);
                    return;
                } else {
                    if (i6 == 3) {
                        v1(R.raw.icon_dock_mirror_heating_on_seat, this.R);
                        return;
                    }
                    return;
                }
            }
            if (i2 != 1) {
                this.M0.setVisibility(8);
                if (z || this.N.isAnimating()) {
                    return;
                }
                int i7 = this.w0;
                if (i7 == 2) {
                    v1(R.raw.icon_rear_window_rearview_mirror_heat_open, this.N);
                    return;
                } else {
                    if (i7 == 3) {
                        v1(R.raw.icon_dock_mirror_heating_off_seat, this.N);
                        return;
                    }
                    return;
                }
            }
            this.M0.setVisibility(0);
            this.M0.setImageResource(this.E2.p);
            int i8 = this.w0;
            if (i8 == 2) {
                if (z || this.N.isAnimating()) {
                    return;
                }
                v1(R.raw.icon_rear_window_rearview_mirror_heat_close, this.N);
                return;
            }
            if (i8 != 3 || z || this.N.isAnimating()) {
                return;
            }
            v1(R.raw.icon_dock_mirror_heating_on_seat, this.N);
        }
    }

    public final void e1() {
        this.L2 = ((k) this.f).s();
        this.M2 = ((k) this.f).r();
        this.N2 = ((k) this.f).q();
        this.O2 = ((k) this.f).p();
        this.P2 = ((k) this.f).F();
        this.Q2 = ((k) this.f).D();
        this.R2 = ((k) this.f).E();
        this.S2 = ((k) this.f).C();
        this.T2 = ((k) this.f).A();
        this.U2 = ((k) this.f).B();
        this.V2 = ((k) this.f).x();
        this.W2 = ((k) this.f).z();
        this.X2 = ((k) this.f).y();
        this.Y2 = ((k) this.f).u();
        this.Z2 = ((k) this.f).w();
        this.a3 = ((k) this.f).v();
        this.b3 = ((k) this.f).O();
        this.c3 = ((k) this.f).M();
        this.d3 = ((k) this.f).N();
        this.e3 = ((k) this.f).L();
        this.f3 = ((k) this.f).K();
        this.g3 = ((k) this.f).I();
        this.h3 = ((k) this.f).J();
        this.i3 = ((k) this.f).H();
        this.m = ((k) this.f).S(1);
        this.o = ((k) this.f).S(2);
        this.q = ((k) this.f).S(3);
        this.s = ((k) this.f).S(4);
        this.S1 = ((k) this.f).T(3);
        this.T1 = ((k) this.f).T(4);
        this.R1 = ((k) this.f).R();
        this.Q1 = ((k) this.f).t();
        this.u = ((k) this.f).U(1);
        this.w = ((k) this.f).U(2);
        this.y = ((k) this.f).U(3);
        this.A = ((k) this.f).U(4);
        this.U1 = ((k) this.f).Q(3);
        this.V1 = ((k) this.f).Q(4);
        this.W1 = ((k) this.f).P();
        this.q2 = Arrays.asList("OFF", "1", "2", "3");
    }

    @Override // com.byd.hvac.view.BydSeekBar.b
    public void f0(SeekBar seekBar, int i) {
    }

    public final void f1(int i, int i2) {
        LinearLayout linearLayout;
        TextSwitcher textSwitcher;
        switch (i) {
            case 0:
                linearLayout = this.V;
                textSwitcher = this.k0;
                break;
            case 1:
                linearLayout = this.d0;
                textSwitcher = this.p0;
                break;
            case 2:
                linearLayout = this.Z;
                textSwitcher = this.n0;
                break;
            case 3:
                linearLayout = this.h0;
                textSwitcher = this.r0;
                break;
            case 4:
                linearLayout = this.W;
                textSwitcher = this.m0;
                break;
            case 5:
                linearLayout = this.e0;
                textSwitcher = this.q0;
                break;
            case 6:
                linearLayout = this.a0;
                textSwitcher = this.o0;
                break;
            case 7:
                linearLayout = this.i0;
                textSwitcher = this.s0;
                break;
            default:
                BydLogHelper.logE("Unknown seatID: " + i);
                return;
        }
        if (linearLayout != null && textSwitcher != null) {
            linearLayout.setVisibility(4);
            if (textSwitcher.getChildCount() < i2) {
                textSwitcher.setFactory(new b.a.a.i.m.a(this));
                return;
            }
            return;
        }
        BydLogHelper.logE("Layout or TextView for " + i + " is not initialized");
    }

    public final void g1() {
        switch (this.A1) {
            case 1:
            case 4:
                this.D.setVisibility(0);
                this.l2.setVisibility(0);
                this.l2.setImageResource(this.E2.o);
                h0(this.C, false);
                W0();
                break;
            case 2:
            case 5:
                ArrayList arrayList = new ArrayList();
                this.r2 = arrayList;
                arrayList.add("OFF");
                this.r2.add("1");
                this.r2.add("2");
                this.r2.add("3");
                this.A0.setVisibility(4);
                if (this.l0.getChildCount() < 3) {
                    this.l0.setFactory(new b.a.a.i.m.a(this));
                }
                this.P1 = ((k) this.f).X();
                this.E.setVisibility(0);
                this.I.setVisibility(0);
                this.l2.setImageResource(this.E2.o);
                h0(this.C, false);
                W0();
                break;
            case 3:
            case 6:
                this.O1 = ((k) this.f).X();
                this.D.setVisibility(8);
                this.l2.setVisibility(0);
                this.z0.setVisibility(0);
                this.Q.setVisibility(0);
                this.l2.setImageResource(this.E2.o);
                h0(this.C, false);
                b0(this.O1, false);
                v(this.z1, false);
                c1();
                break;
            default:
                int i = this.z1;
                if (i == 1 || i == 2) {
                    this.O1 = ((k) this.f).X();
                    this.D.setVisibility(8);
                    this.l2.setVisibility(0);
                    this.z0.setVisibility(0);
                    this.Q.setVisibility(0);
                    this.l2.setImageResource(this.E2.o);
                    h0(this.C, false);
                    b0(this.O1, false);
                    v(this.z1, false);
                    c1();
                } else {
                    int i2 = this.C;
                    if (i2 == 1 || i2 == 2) {
                        this.D.setVisibility(0);
                        this.l2.setVisibility(0);
                        this.l2.setImageResource(this.E2.o);
                        h0(this.C, false);
                        W0();
                    } else {
                        this.D.setVisibility(8);
                        this.l2.setVisibility(8);
                        W0();
                    }
                }
                break;
        }
    }

    @Override // b.a.a.d.h
    public void h0(int i, boolean z) {
        BydLogHelper.logD("updateSteeringWheelWarmState state = " + i);
        BydLogHelper.logD("updateSteeringWheelWarmState SteerHeatLevel = " + ((k) this.f).X());
        this.C = i;
        int i2 = this.A1;
        int i3 = R.raw.icon_steering_wheel_heat_open;
        switch (i2) {
            case 1:
            case 4:
                if (i == 2) {
                    this.h.setVisibility(0);
                    this.h.setImageResource(this.E2.n);
                    i3 = R.raw.icon_steering_wheel_heat_close;
                } else if (i == 1) {
                    this.h.setVisibility(8);
                } else {
                    i3 = 0;
                }
                if (!z && !this.H.isAnimating()) {
                    v1(i3, this.H);
                    break;
                }
                break;
            case 2:
            case 5:
                if (i == 2) {
                    this.h.setVisibility(0);
                    this.h.setImageResource(this.E2.n);
                } else if (i == 1) {
                    this.h.setVisibility(8);
                }
                b0(((k) this.f).X(), z);
                break;
            case 3:
            case 6:
                if (i == 2) {
                    this.h.setVisibility(0);
                    this.h.setImageResource(this.E2.n);
                    this.b1.setProgress(((k) this.f).X());
                    B1(((k) this.f).X());
                    this.b1.setVisibility(0);
                    BydLogHelper.logD("updateSteeringWheelWarmState RAW_STEER_WHEEL_HEAT_CLOSE = ");
                    i3 = R.raw.icon_steering_wheel_heat_close;
                } else if (i == 1) {
                    this.h.setVisibility(8);
                    this.b1.setProgress(0);
                    this.b1.setVisibility(8);
                    BydLogHelper.logD("updateSteeringWheelWarmState RAW_STEER_WHEEL_HEAT_OPEN = ");
                } else {
                    i3 = 0;
                }
                BydLogHelper.logD("updateSteeringWheelWarmState state = " + i + " -- rawRes = " + i3);
                if (!z && !this.Q.isAnimating()) {
                    v1(i3, this.Q);
                    break;
                }
                break;
            default:
                int i4 = this.z1;
                if (i4 != 1 && i4 != 2) {
                    if (i == 2) {
                        this.h.setVisibility(0);
                        this.h.setImageResource(this.E2.n);
                        i3 = R.raw.icon_steering_wheel_heat_close;
                    } else if (i == 1) {
                        this.h.setVisibility(8);
                    } else {
                        i3 = 0;
                    }
                    if (!z && !this.H.isAnimating()) {
                        v1(i3, this.H);
                        break;
                    }
                } else {
                    if (i == 2) {
                        this.h.setVisibility(0);
                        this.h.setImageResource(this.E2.n);
                        this.b1.setProgress(((k) this.f).X());
                        B1(((k) this.f).X());
                        this.b1.setVisibility(0);
                        i3 = R.raw.icon_steering_wheel_heat_close;
                    } else if (i == 1) {
                        this.h.setVisibility(8);
                        this.b1.setProgress(0);
                        this.b1.setVisibility(8);
                    } else {
                        i3 = 0;
                    }
                    if (!z && !this.Q.isAnimating()) {
                        v1(i3, this.Q);
                        break;
                    }
                }
                break;
        }
    }

    public final boolean h1() {
        if (this.g1 + this.h1 + this.i1 + this.j1 + this.s1 + this.B1 + this.t1 + this.u1 >= 1) {
            BydLogHelper.logD("is5LevelStyle");
            return true;
        }
        BydLogHelper.logD("not5LevelStyle");
        return false;
    }

    public final boolean i1() {
        return (this.N2 == 3 || this.O2 == 3) ? false : true;
    }

    @Override // b.a.a.d.h
    public void j(int i) {
    }

    public final boolean j1() {
        return (this.L2 == 3 || this.M2 == 3) ? false : true;
    }

    public final boolean k1() {
        return (this.Y2 == 3 || this.Z2 == 3 || this.a3 == 3) ? false : true;
    }

    public final boolean l1() {
        return (this.V2 == 3 || this.W2 == 3 || this.X2 == 3) ? false : true;
    }

    public final boolean m1() {
        return (this.S2 == 3 || this.T2 == 3 || this.U2 == 3) ? false : true;
    }

    @Override // b.a.a.d.h
    public void n0(int i, int i2, boolean z) {
        BydLogHelper.logW("SeatHeatVentilationFragment", "updateSeatWarmState2 area = " + i + " --  value = " + i2);
        if (i == 3) {
            int i3 = this.y;
            if (i3 == 3 || i3 == 2 || i3 == 1 || i3 == 4) {
                BydLogHelper.logD("按优先级策略，左后座椅加热当前后车身控制器的0x486 1.3-1.5 有有效值，不处理402信号");
                return;
            }
            this.U1 = i2;
            A1(i, i2, z);
            this.a2 = this.U1;
            return;
        }
        if (i != 4) {
            return;
        }
        int i4 = this.A;
        if (i4 == 3 || i4 == 2 || i4 == 1 || i4 == 4) {
            BydLogHelper.logD("按优先级策略，右后座椅通风当前后车身控制器的0x486 2.3-2.5 有有效值，不处理42F信号");
            return;
        }
        this.V1 = i2;
        A1(i, i2, z);
        this.b2 = this.V1;
    }

    public final boolean n1() {
        return (this.P2 == 3 || this.Q2 == 3 || this.R2 == 3) ? false : true;
    }

    public final boolean o1() {
        return (this.f3 == 3 || this.g3 == 3 || this.h3 == 3 || this.i3 == 3) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:134:0x0394 A[PHI: r1
  0x0394: PHI (r1v284 int) = (r1v263 int), (r1v288 int) binds: [B:154:0x040f, B:133:0x0392] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0399 A[PHI: r1
  0x0399: PHI (r1v271 int) = (r1v263 int), (r1v288 int) binds: [B:154:0x040f, B:133:0x0392] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x04f6 A[PHI: r1
  0x04f6: PHI (r1v238 int) = (r1v215 int), (r1v242 int) binds: [B:211:0x056f, B:190:0x04f4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x04fb A[PHI: r1
  0x04fb: PHI (r1v225 int) = (r1v215 int), (r1v242 int) binds: [B:211:0x056f, B:190:0x04f4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x050d  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0639 A[PHI: r1
  0x0639: PHI (r1v196 int) = (r1v175 int), (r1v200 int) binds: [B:262:0x06b3, B:241:0x0637] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:243:0x063e A[PHI: r1
  0x063e: PHI (r1v183 int) = (r1v175 int), (r1v200 int) binds: [B:262:0x06b3, B:241:0x0637] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0651  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0657  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x09d6 A[PHI: r1
  0x09d6: PHI (r1v73 int) = (r1v69 int), (r1v96 int) binds: [B:385:0x09d4, B:370:0x0977] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:387:0x09db A[PHI: r1
  0x09db: PHI (r1v86 int) = (r1v69 int), (r1v96 int) binds: [B:385:0x09d4, B:370:0x0977] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:391:0x09ed  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x09f2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x0b18 A[PHI: r1
  0x0b18: PHI (r1v35 int) = (r1v31 int), (r1v56 int) binds: [B:437:0x0b16, B:422:0x0ab9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0b1c A[PHI: r1
  0x0b1c: PHI (r1v48 int) = (r1v31 int), (r1v56 int) binds: [B:437:0x0b16, B:422:0x0ab9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:443:0x0b2e  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x0b33  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0250 A[PHI: r1
  0x0250: PHI (r1v326 int) = (r1v303 int), (r1v330 int) binds: [B:103:0x02ca, B:82:0x024e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0255 A[PHI: r1
  0x0255: PHI (r1v313 int) = (r1v303 int), (r1v330 int) binds: [B:103:0x02ca, B:82:0x024e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x026d  */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onClick(android.view.View r18) {
        /*
            Method dump skipped, instruction units count: 3164
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.hvac.view.seat.SeatHeatVentilationFragment.onClick(android.view.View):void");
    }

    @Override // com.byd.hvac.base.BaseFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        List<String> list = this.r2;
        if (list != null) {
            list.clear();
        }
        ConstraintLayout constraintLayout = this.B2;
        if (constraintLayout != null) {
            constraintLayout.removeAllViews();
        }
        super.onDestroyView();
    }

    @Override // com.byd.hvac.view.BydSeekBar.b
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            switch (seekBar.getId()) {
                case R.id.sb_seat_copilot_ventilation_heat_5level /* 2131296773 */:
                    if (this.d1 == 2) {
                        ((k) this.f).f0(2, i);
                    } else if (this.p1 == 2) {
                        ((k) this.f).f0(2, i);
                    } else if (this.h1 == 1) {
                        ((k) this.f).d0(2, i);
                    } else if (this.B1 == 1) {
                        ((k) this.f).f0(2, i);
                    }
                    break;
                case R.id.sb_seat_driver_ventilation_heat_5level /* 2131296774 */:
                    if (this.c1 == 2) {
                        ((k) this.f).f0(1, i);
                    } else if (this.o1 == 2) {
                        ((k) this.f).f0(1, i);
                    } else if (this.g1 == 1) {
                        ((k) this.f).d0(1, i);
                    } else if (this.s1 == 1) {
                        ((k) this.f).f0(1, i);
                    }
                    break;
                case R.id.sb_seat_left_rear_ventilation_heat_5level /* 2131296775 */:
                    if (this.e1 == 2) {
                        ((k) this.f).f0(3, i);
                    } else if (this.q1 == 2) {
                        ((k) this.f).f0(3, i);
                    } else if (this.i1 == 1) {
                        ((k) this.f).d0(3, i);
                    } else if (this.t1 == 1) {
                        ((k) this.f).f0(3, i);
                    }
                    break;
                case R.id.sb_seat_right_rear_ventilation_heat_5level /* 2131296776 */:
                    if (this.f1 == 2) {
                        ((k) this.f).f0(4, i);
                    } else if (this.r1 == 2) {
                        ((k) this.f).f0(4, i);
                    } else if (this.j1 == 1) {
                        ((k) this.f).d0(4, i);
                    } else if (this.u1 == 1) {
                        ((k) this.f).f0(4, i);
                    }
                    break;
                case R.id.sb_steering_wheel_heat_5level /* 2131296777 */:
                    ((k) this.f).q0(i);
                    break;
            }
        }
    }

    @Override // com.byd.hvac.view.BydSeekBar.b
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (!b.a.a.h.k.b() && L0()) {
        }
    }

    @Override // com.byd.hvac.view.BydSeekBar.b
    public void p(int i) {
    }

    @Override // b.a.a.d.h
    public void p0(int i, int i2, boolean z) {
        BydLogHelper.logW("SeatHeatVentilationFragment", "updateSeatVentilationState1 area = " + i + " --  value = " + i2);
        if (i == 1) {
            this.m = i2;
            z1(1, i2, z);
            this.n = this.m;
            return;
        }
        if (i == 2) {
            this.o = i2;
            z1(2, i2, z);
            this.p = this.o;
        } else if (i == 3) {
            this.q = i2;
            z1(3, i2, z);
            this.r = this.q;
        } else {
            if (i != 4) {
                return;
            }
            this.s = i2;
            z1(4, i2, z);
            this.t = this.s;
        }
    }

    public final boolean p1() {
        return (this.b3 == 3 || this.c3 == 3 || this.d3 == 3 || this.e3 == 3) ? false : true;
    }

    public final boolean q1(int i) {
        BydLogHelper.logW("SeatHeatVentilationFragment", "isValidState state:" + i);
        return i == 3 || i == 2 || i == 1 || i == 4;
    }

    @Override // b.a.a.d.h
    public void r(int i, boolean z) {
        int i2;
        BydLogHelper.logW("SeatHeatVentilationFragment", "updateSeatHeatingNotL1PState  value = " + i);
        this.R1 = i;
        if (i == 2) {
            this.j.setVisibility(0);
            this.j.setImageResource(M0(this.E2.e));
            this.j.setAlpha(0.33f);
            i2 = R.raw.icon_seat_ventilation_3level_1_0;
            if (n1()) {
                this.j.setAlpha(0.5f);
                i2 = R.raw.icon_seat_ventilation_1_0;
            }
        } else if (i == 3) {
            this.j.setVisibility(0);
            this.j.setImageResource(M0(this.E2.e));
            this.j.setAlpha(0.66f);
            i2 = R.raw.icon_seat_ventilation_3level_2_1;
            if (n1()) {
                this.j.setAlpha(1.0f);
                i2 = R.raw.icon_seat_ventilation_2_1;
            }
        } else if (i != 4) {
            this.j.setVisibility(8);
            i2 = R.raw.icon_seat_ventilation_3level_0_3;
            if (n1()) {
                i2 = R.raw.icon_seat_ventilation_0_2;
            }
            if (m1()) {
                int i3 = this.W1;
                if (i3 == 2) {
                    this.j.setImageResource(M0(this.E2.f));
                    this.j.setVisibility(0);
                    this.j.setAlpha(0.5f);
                } else if (i3 == 3) {
                    this.j.setImageResource(M0(this.E2.f));
                    this.j.setVisibility(0);
                    this.j.setAlpha(1.0f);
                }
            } else {
                int i4 = this.W1;
                if (i4 == 2) {
                    this.j.setImageResource(M0(this.E2.f));
                    this.j.setVisibility(0);
                    this.j.setAlpha(0.33f);
                } else if (i4 == 3) {
                    this.j.setImageResource(M0(this.E2.f));
                    this.j.setVisibility(0);
                    this.j.setAlpha(0.66f);
                } else if (i4 == 4) {
                    this.j.setImageResource(M0(this.E2.f));
                    this.j.setVisibility(0);
                    this.j.setAlpha(1.0f);
                }
            }
        } else {
            this.j.setVisibility(0);
            this.j.setImageResource(M0(this.E2.e));
            this.j.setAlpha(1.0f);
            i2 = R.raw.icon_seat_ventilation_3level_3_2;
        }
        if (!z) {
            if (this.X1 != this.R1) {
                if (this.O.isAnimating()) {
                    this.O.cancelAnimation();
                }
                v1(i2, this.O);
            } else if (!this.O.isAnimating()) {
                v1(i2, this.O);
            }
        }
        this.X1 = this.R1;
    }

    @Override // b.a.a.d.h
    public void s(int i, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        int i6;
        BydLogHelper.logD("update5LevelSeatHeatingLevel area:" + i + " -- state:" + i2);
        if (i == 1) {
            this.n1 = i2;
            this.a1.setProgress(i2);
            int i7 = this.g1;
            if (i7 == 1 || (i3 = this.c1) == 2) {
                this.P0.setImageResource(M0(this.E2.f127c));
                this.P0.setVisibility(0);
                y1(1, this.n1);
                return;
            } else {
                if (this.s1 == 2 && i7 == 2 && i3 == 1 && this.o1 == 1) {
                    this.P0.setVisibility(8);
                    return;
                }
                return;
            }
        }
        if (i == 2) {
            this.m1 = i2;
            this.Y0.setProgress(i2);
            int i8 = this.h1;
            if (i8 == 1 || (i4 = this.d1) == 2) {
                this.Q0.setImageResource(M0(this.E2.f));
                this.Q0.setVisibility(0);
                y1(2, this.m1);
                return;
            } else {
                if (this.B1 == 2 && i8 == 2 && i4 == 1 && this.p1 == 1) {
                    this.Q0.setVisibility(8);
                    return;
                }
                return;
            }
        }
        if (i == 3) {
            this.l1 = i2;
            this.Z0.setProgress(i2);
            int i9 = this.i1;
            if (i9 == 1 || (i5 = this.e1) == 2) {
                this.R0.setImageResource(M0(this.E2.i));
                this.R0.setVisibility(0);
                y1(3, this.l1);
                return;
            } else {
                if (this.t1 == 2 && i9 == 2 && i5 == 1 && this.q1 == 1) {
                    this.R0.setVisibility(8);
                    return;
                }
                return;
            }
        }
        if (i != 4) {
            return;
        }
        this.k1 = i2;
        this.X0.setProgress(i2);
        int i10 = this.j1;
        if (i10 == 1 || (i6 = this.f1) == 2) {
            this.S0.setImageResource(M0(this.E2.l));
            this.S0.setVisibility(0);
            y1(4, this.k1);
        } else if (this.u1 == 2 && i10 == 2 && i6 == 1 && this.r1 == 1) {
            this.S0.setVisibility(8);
        }
    }

    @Override // com.byd.hvac.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.f985a) {
            return;
        }
        BydLogHelper.logD("SeatHeatVentilationFragment isVisibleToUser 视图未准备好");
    }

    public final void u1(int i, final LottieAnimationView lottieAnimationView) {
        LottieCompositionFactory.fromRawRes(this.e, i).addListener(new LottieListener() { // from class: b.a.a.i.m.c
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                SeatHeatVentilationFragment.s1(lottieAnimationView, (LottieComposition) obj);
            }
        });
    }

    @Override // b.a.a.d.h
    public void v(int i, boolean z) {
        BydLogHelper.logD("updateSteeringWheelHeatingAutoState state = " + i);
        this.z1 = i;
        if (i == 2) {
            this.G0.setImageResource(R.mipmap.icon_seat_ai_off_5level);
        } else if (i == 1) {
            this.G0.setImageResource(R.mipmap.icon_seat_ai_heat_5level);
        }
    }

    @Override // com.byd.hvac.base.BaseFragment
    public int v0() {
        return M0(R.layout.fragment_seat_heat_ventilation);
    }

    public final void v1(int i, final LottieAnimationView lottieAnimationView) {
        LottieCompositionFactory.fromRawRes(this.e, i).addListener(new LottieListener() { // from class: b.a.a.i.m.b
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                SeatHeatVentilationFragment.t1(lottieAnimationView, (LottieComposition) obj);
            }
        });
    }

    public final void w1(boolean z, View... viewArr) {
        for (View view : viewArr) {
            if (view != null) {
                view.setVisibility(z ? 0 : 8);
            }
        }
    }

    @Override // b.a.a.d.h
    public void x(int i, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        int i6;
        BydLogHelper.logD("update5LevelSeatVentilatingLevel area:" + i + " -- state:" + i2);
        if (i == 1) {
            this.v1 = i2;
            this.a1.setProgress(i2);
            int i7 = this.s1;
            if (i7 == 1 || (i3 = this.o1) == 2) {
                this.P0.setImageResource(M0(this.E2.f126b));
                this.P0.setVisibility(0);
                y1(1, this.v1);
                return;
            } else {
                if (i7 == 2 && this.g1 == 2 && this.c1 == 1 && i3 == 1) {
                    this.P0.setVisibility(8);
                    return;
                }
                return;
            }
        }
        if (i == 2) {
            this.w1 = i2;
            this.Y0.setProgress(i2);
            int i8 = this.B1;
            if (i8 == 1 || (i4 = this.p1) == 2) {
                this.Q0.setImageResource(M0(this.E2.e));
                this.Q0.setVisibility(0);
                y1(2, this.w1);
                return;
            } else {
                if (i8 == 2 && this.h1 == 2 && this.d1 == 1 && i4 == 1) {
                    this.Q0.setVisibility(8);
                    return;
                }
                return;
            }
        }
        if (i == 3) {
            this.x1 = i2;
            this.Z0.setProgress(i2);
            int i9 = this.t1;
            if (i9 == 1 || (i5 = this.q1) == 2) {
                this.R0.setImageResource(M0(this.E2.h));
                this.R0.setVisibility(0);
                y1(3, this.x1);
                return;
            } else {
                if (i9 == 2 && this.i1 == 2 && this.e1 == 1 && i5 == 1) {
                    this.R0.setVisibility(8);
                    return;
                }
                return;
            }
        }
        if (i != 4) {
            return;
        }
        this.y1 = i2;
        this.X0.setProgress(i2);
        int i10 = this.u1;
        if (i10 == 1 || (i6 = this.r1) == 2) {
            this.S0.setImageResource(M0(this.E2.k));
            this.S0.setVisibility(0);
            y1(4, this.y1);
        } else if (i10 == 2 && this.j1 == 2 && this.f1 == 1 && i6 == 1) {
            this.S0.setVisibility(8);
        }
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void x0(View view) {
        this.D = (RelativeLayout) view.findViewById(R.id.rl_steering_wheel_heat);
        this.H = (LottieAnimationView) view.findViewById(R.id.iv_steering_wheel_heat);
        this.l2 = (ImageView) view.findViewById(R.id.iv_study_steering_wheel);
        this.A0 = (LinearLayout) view.findViewById(R.id.ll_steering_wheel_heat_3level_pop_container);
        this.E = (RelativeLayout) view.findViewById(R.id.rl_steering_wheel_heat_3level);
        this.I = (LottieAnimationView) view.findViewById(R.id.iv_steering_wheel_heat_3level);
        this.l0 = (TextSwitcher) view.findViewById(R.id.tv_steering_wheel_heat_3level);
        this.U = (LinearLayout) view.findViewById(R.id.rl_seat_driver_ventilation_heat_level);
        this.k0 = (TextSwitcher) view.findViewById(R.id.tv_seat_driver_ventilation_level);
        this.m0 = (TextSwitcher) view.findViewById(R.id.tv_seat_driver_heat_level);
        this.V = (LinearLayout) view.findViewById(R.id.ll_seat_driver_ventilation_level);
        this.W = (LinearLayout) view.findViewById(R.id.ll_seat_driver_heat_level);
        this.X = (LinearLayout) view.findViewById(R.id.ll_seat_driver_ventilation);
        this.K1 = view.findViewById(R.id.view_seat_driver_ventilation_heat_divider);
        this.J = (LottieAnimationView) view.findViewById(R.id.iv_seat_driver_ventilation);
        this.K = (LottieAnimationView) view.findViewById(R.id.iv_seat_driver_heat);
        this.d2 = (ImageView) view.findViewById(R.id.iv_line_seat_driver_ventilation);
        this.h2 = (ImageView) view.findViewById(R.id.iv_line_seat_driver_ventilation_5level);
        this.m2 = (ImageView) view.findViewById(R.id.iv_study_seat_driver);
        this.Y = (LinearLayout) view.findViewById(R.id.rl_seat_left_rear_ventilation_heat_level);
        this.n0 = (TextSwitcher) view.findViewById(R.id.tv_seat_left_rear_ventilation_level);
        this.o0 = (TextSwitcher) view.findViewById(R.id.tv_seat_left_rear_heat_level);
        this.Z = (LinearLayout) view.findViewById(R.id.ll_seat_left_rear_ventilation_level);
        this.a0 = (LinearLayout) view.findViewById(R.id.ll_seat_left_rear_heat_level);
        this.b0 = (LinearLayout) view.findViewById(R.id.ll_seat_left_rear_ventilation);
        this.L1 = view.findViewById(R.id.view_seat_left_rear_ventilation_heat_divider);
        this.L = (LottieAnimationView) view.findViewById(R.id.iv_seat_left_rear_ventilation);
        this.M = (LottieAnimationView) view.findViewById(R.id.iv_seat_left_rear_heat);
        this.e2 = (ImageView) view.findViewById(R.id.iv_line_seat_left_rear_ventilation);
        this.i2 = (ImageView) view.findViewById(R.id.iv_line_seat_left_rear_ventilation_5level);
        this.n2 = (ImageView) view.findViewById(R.id.iv_study_seat_heat_left_rear);
        this.F = (RelativeLayout) view.findViewById(R.id.rl_electric_defrost);
        this.N = (LottieAnimationView) view.findViewById(R.id.iv_electric_defrost);
        this.t0 = (TextView) view.findViewById(R.id.tv_electric_defrost);
        this.c0 = (LinearLayout) view.findViewById(R.id.rl_seat_copilot_ventilation_heat_level);
        this.p0 = (TextSwitcher) view.findViewById(R.id.tv_seat_copilot_ventilation_level);
        this.q0 = (TextSwitcher) view.findViewById(R.id.tv_seat_copilot_heat_level);
        this.d0 = (LinearLayout) view.findViewById(R.id.ll_seat_copilot_ventilation_level);
        this.e0 = (LinearLayout) view.findViewById(R.id.ll_seat_copilot_heat_level);
        this.f0 = (LinearLayout) view.findViewById(R.id.ll_seat_copilot_ventilation);
        this.M1 = view.findViewById(R.id.view_seat_copilot_ventilation_heat_divider);
        this.O = (LottieAnimationView) view.findViewById(R.id.iv_seat_copilot_ventilation);
        this.P = (LottieAnimationView) view.findViewById(R.id.iv_seat_copilot_heat);
        this.f2 = (ImageView) view.findViewById(R.id.iv_line_seat_copilot_ventilation);
        this.j2 = (ImageView) view.findViewById(R.id.iv_line_seat_copilot_ventilation_5level);
        this.o2 = (ImageView) view.findViewById(R.id.iv_study_seat_heat_copilot);
        this.g0 = (LinearLayout) view.findViewById(R.id.rl_seat_right_rear_ventilation_heat_level);
        this.r0 = (TextSwitcher) view.findViewById(R.id.tv_seat_right_rear_ventilation_level);
        this.s0 = (TextSwitcher) view.findViewById(R.id.tv_seat_right_rear_heat_level);
        this.h0 = (LinearLayout) view.findViewById(R.id.ll_seat_right_rear_ventilation_level);
        this.i0 = (LinearLayout) view.findViewById(R.id.ll_seat_right_rear_heat_level);
        this.j0 = (LinearLayout) view.findViewById(R.id.ll_seat_right_rear_ventilation);
        this.N1 = view.findViewById(R.id.view_seat_right_rear_ventilation_heat_divider);
        this.S = (LottieAnimationView) view.findViewById(R.id.iv_seat_right_rear_ventilation);
        this.T = (LottieAnimationView) view.findViewById(R.id.iv_seat_right_rear_heat);
        this.g2 = (ImageView) view.findViewById(R.id.iv_line_seat_right_rear_ventilation);
        this.k2 = (ImageView) view.findViewById(R.id.iv_line_seat_right_rear_ventilation_5level);
        this.p2 = (ImageView) view.findViewById(R.id.iv_study_seat_heat_right_rear);
        this.h = (ImageView) view.findViewById(R.id.iv_seat_wheel_heat_state);
        this.i = (ImageView) view.findViewById(R.id.iv_seat_driver_heat_state);
        this.j = (ImageView) view.findViewById(R.id.iv_seat_heat_copilot_state);
        this.k = (ImageView) view.findViewById(R.id.iv_seat_heat_left_rear_state);
        this.l = (ImageView) view.findViewById(R.id.iv_seat_heat_right_rear_state);
        this.M0 = (ImageView) view.findViewById(R.id.iv_electric_defrost_state);
        this.z0 = (LinearLayout) view.findViewById(R.id.rl_steering_wheel_heat_5level);
        this.Q = (LottieAnimationView) view.findViewById(R.id.iv_steering_wheel_heat_5level);
        this.G0 = (ImageView) view.findViewById(R.id.iv_steering_wheel_ai_5level);
        this.b1 = (BydSeekBar) view.findViewById(R.id.sb_steering_wheel_heat_5level);
        this.B0 = (LinearLayout) view.findViewById(R.id.rl_seat_driver_ventilation_heat_5level);
        this.W0 = (ImageView) view.findViewById(R.id.iv_seat_driver_ai_5level);
        this.F0 = (ImageView) view.findViewById(R.id.iv_seat_driver_ventilation_5level);
        this.H0 = (ImageView) view.findViewById(R.id.iv_seat_driver_heat_5level);
        this.a1 = (BydSeekBar) view.findViewById(R.id.sb_seat_driver_ventilation_heat_5level);
        this.C0 = (LinearLayout) view.findViewById(R.id.rl_seat_left_rear_ventilation_heat_5level);
        this.V0 = (ImageView) view.findViewById(R.id.iv_seat_left_rear_ai_5level);
        this.I0 = (ImageView) view.findViewById(R.id.iv_seat_left_rear_ventilation_5level);
        this.J0 = (ImageView) view.findViewById(R.id.iv_seat_left_rear_heat_5level);
        this.Z0 = (BydSeekBar) view.findViewById(R.id.sb_seat_left_rear_ventilation_heat_5level);
        this.G = (RelativeLayout) view.findViewById(R.id.rl_electric_defrost_5level);
        this.R = (LottieAnimationView) view.findViewById(R.id.iv_electric_defrost_5level);
        this.u0 = (TextView) view.findViewById(R.id.tv_electric_defrost_5level);
        this.D0 = (LinearLayout) view.findViewById(R.id.rl_seat_copilot_ventilation_heat_5level);
        this.U0 = (ImageView) view.findViewById(R.id.iv_seat_copilot_ai_5level);
        this.K0 = (ImageView) view.findViewById(R.id.iv_seat_copilot_ventilation_5level);
        this.L0 = (ImageView) view.findViewById(R.id.iv_seat_copilot_heat_5level);
        this.Y0 = (BydSeekBar) view.findViewById(R.id.sb_seat_copilot_ventilation_heat_5level);
        this.E0 = (LinearLayout) view.findViewById(R.id.rl_seat_right_rear_ventilation_heat_5level);
        this.T0 = (ImageView) view.findViewById(R.id.iv_seat_right_rear_ai_5level);
        this.N0 = (ImageView) view.findViewById(R.id.iv_seat_right_rear_ventilation_5level);
        this.O0 = (ImageView) view.findViewById(R.id.iv_seat_right_rear_heat_5level);
        this.X0 = (BydSeekBar) view.findViewById(R.id.sb_seat_right_rear_ventilation_heat_5level);
        this.P0 = (ImageView) view.findViewById(R.id.iv_seat_driver_heat_state_5level);
        this.Q0 = (ImageView) view.findViewById(R.id.iv_seat_heat_copilot_state_5level);
        this.R0 = (ImageView) view.findViewById(R.id.iv_seat_heat_left_rear_state_5level);
        this.S0 = (ImageView) view.findViewById(R.id.iv_seat_heat_right_rear_state_5level);
        this.C1 = view.findViewById(R.id.view_driver_divider1);
        this.D1 = view.findViewById(R.id.view_driver_divider2);
        this.E1 = view.findViewById(R.id.view_copilot_divider1);
        this.F1 = view.findViewById(R.id.view_copilot_divider2);
        this.G1 = view.findViewById(R.id.view_left_rear_divider1);
        this.H1 = view.findViewById(R.id.view_left_rear_divider2);
        this.I1 = view.findViewById(R.id.view_right_rear_divider1);
        this.J1 = view.findViewById(R.id.view_right_rear_divider2);
        this.B2 = (ConstraintLayout) view.findViewById(R.id.cl_mid_seat_state_container);
        this.C2 = (ImageView) view.findViewById(R.id.iv_seat_car_bg);
        this.v0 = new b(getContext());
        A0();
    }

    public final void x1(int i, boolean z) {
        int i2;
        if (i == 0) {
            i2 = R.raw.steering_wheel_heat_3level_0_3;
        } else if (i == 1) {
            this.h.setAlpha(0.3f);
            i2 = R.raw.steering_wheel_heat_3level_1_0;
        } else if (i == 2) {
            this.h.setAlpha(0.6f);
            i2 = R.raw.steering_wheel_heat_3level_2_1;
        } else {
            if (i != 3) {
                BydLogHelper.logD("update3LevelSteeringWheelWarmAlpha 三挡方向盘加热 档位错误 level = " + i);
                return;
            }
            this.h.setAlpha(1.0f);
            i2 = R.raw.steering_wheel_heat_3level_3_2;
        }
        if (z || this.I.isAnimating()) {
            return;
        }
        v1(i2, this.I);
    }

    public final void y1(int i, int i2) {
        if (i == 1) {
            if (i2 == 1) {
                this.P0.setAlpha(0.2f);
                return;
            }
            if (i2 == 2) {
                this.P0.setAlpha(0.4f);
                return;
            }
            if (i2 == 3) {
                this.P0.setAlpha(0.6f);
                return;
            } else if (i2 == 4) {
                this.P0.setAlpha(0.8f);
                return;
            } else {
                if (i2 == 5) {
                    this.P0.setAlpha(1.0f);
                    return;
                }
                return;
            }
        }
        if (i == 2) {
            if (i2 == 1) {
                this.Q0.setAlpha(0.2f);
                return;
            }
            if (i2 == 2) {
                this.Q0.setAlpha(0.4f);
                return;
            }
            if (i2 == 3) {
                this.Q0.setAlpha(0.6f);
                return;
            } else if (i2 == 4) {
                this.Q0.setAlpha(0.8f);
                return;
            } else {
                if (i2 == 5) {
                    this.Q0.setAlpha(1.0f);
                    return;
                }
                return;
            }
        }
        if (i == 3) {
            if (i2 == 1) {
                this.R0.setAlpha(0.2f);
                return;
            }
            if (i2 == 2) {
                this.R0.setAlpha(0.4f);
                return;
            }
            if (i2 == 3) {
                this.R0.setAlpha(0.6f);
                return;
            } else if (i2 == 4) {
                this.R0.setAlpha(0.8f);
                return;
            } else {
                if (i2 == 5) {
                    this.R0.setAlpha(1.0f);
                    return;
                }
                return;
            }
        }
        if (i != 4) {
            return;
        }
        if (i2 == 1) {
            this.S0.setAlpha(0.2f);
            return;
        }
        if (i2 == 2) {
            this.S0.setAlpha(0.4f);
            return;
        }
        if (i2 == 3) {
            this.S0.setAlpha(0.6f);
        } else if (i2 == 4) {
            this.S0.setAlpha(0.8f);
        } else if (i2 == 5) {
            this.S0.setAlpha(1.0f);
        }
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void z0() {
        super.z0();
        this.D.setOnClickListener(this);
        this.E.setOnClickListener(this);
        this.J.setOnClickListener(this);
        this.K.setOnClickListener(this);
        this.L.setOnClickListener(this);
        this.M.setOnClickListener(this);
        this.F.setOnClickListener(this);
        this.O.setOnClickListener(this);
        this.P.setOnClickListener(this);
        this.S.setOnClickListener(this);
        this.T.setOnClickListener(this);
        this.G0.setOnClickListener(this);
        this.Q.setOnClickListener(this);
        this.b1.setGradientColorListener(this);
        this.W0.setOnClickListener(this);
        this.F0.setOnClickListener(this);
        this.H0.setOnClickListener(this);
        this.a1.setGradientColorListener(this);
        this.V0.setOnClickListener(this);
        this.I0.setOnClickListener(this);
        this.J0.setOnClickListener(this);
        this.Z0.setGradientColorListener(this);
        this.G.setOnClickListener(this);
        this.U0.setOnClickListener(this);
        this.K0.setOnClickListener(this);
        this.L0.setOnClickListener(this);
        this.Y0.setGradientColorListener(this);
        this.T0.setOnClickListener(this);
        this.N0.setOnClickListener(this);
        this.O0.setOnClickListener(this);
        this.X0.setGradientColorListener(this);
    }

    public void z1(int i, int i2, boolean z) {
        BydLogHelper.logW("SeatHeatVentilationFragment", "updateSeatVentilationState area = " + i + " --  value = " + i2);
        if (i == 1) {
            P0(i2, z);
            return;
        }
        if (i == 2) {
            T0(i2, z);
        } else if (i == 3) {
            R0(i2, z);
        } else {
            if (i != 4) {
                return;
            }
            V0(i2, z);
        }
    }
}
