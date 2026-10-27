package com.byd.hvac.view.green;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.a.a.d.f;
import b.a.a.g.i;
import b.a.a.h.e;
import b.a.a.h.k;
import b.a.a.h.l;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.byd.hvac.R;
import com.byd.hvac.base.BaseFragment;
import com.byd.hvac.view.dialog.AcStartVehicleTipsLayout;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class GreenControlFragment extends BaseFragment<i> implements f, View.OnClickListener {
    public static final String g = GreenControlFragment.class.getSimpleName();
    public int C;
    public int D;
    public int F;
    public int G;
    public int H;
    public int I;
    public ImageView J;
    public b M;
    public int N;
    public int O;
    public RelativeLayout P;
    public TextView Q;
    public RelativeLayout R;
    public TextView S;
    public b.a.a.i.i.a U;
    public int V;
    public TextView h;
    public TextView i;
    public TextView j;
    public TextView k;
    public TextView l;
    public ConstraintLayout m;
    public TextView n;
    public TextView o;
    public ConstraintLayout p;
    public TextView q;
    public TextView r;
    public int s;
    public int t;
    public int u;
    public int v;
    public ImageView w;
    public LottieAnimationView x;
    public LottieAnimationView y;
    public e z;
    public int A = -1;
    public int B = -1;
    public int E = 2;
    public int K = -1;
    public int L = -1;
    public boolean T = true;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements AcStartVehicleTipsLayout.b {
        public a() {
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void a() {
            GreenControlFragment.this.U.a();
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void b() {
            GreenControlFragment.this.U.a();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<GreenControlFragment> f1066a;

        public b(GreenControlFragment greenControlFragment) {
            this.f1066a = new WeakReference<>(greenControlFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (this.f1066a.get() == null) {
                return;
            }
            int i = message.what;
            if (i == 0) {
                sendEmptyMessageDelayed(1, 1150L);
                this.f1066a.get().T = false;
                this.f1066a.get().R.setVisibility(8);
                this.f1066a.get().S.setVisibility(0);
                this.f1066a.get().S.setTypeface(Typeface.defaultFromStyle(0));
                this.f1066a.get().S.setText("-");
                this.f1066a.get().S.setTextColor(this.f1066a.get().getResources().getColor(R.color.white));
                if (this.f1066a.get().u != 1) {
                    this.f1066a.get().P.setVisibility(8);
                    this.f1066a.get().Q.setVisibility(0);
                    this.f1066a.get().Q.setTypeface(Typeface.defaultFromStyle(0));
                    this.f1066a.get().Q.setText("-");
                    this.f1066a.get().Q.setTextColor(this.f1066a.get().getResources().getColor(R.color.white));
                    return;
                }
                return;
            }
            if (i == 1) {
                sendEmptyMessageDelayed(2, 1150L);
                this.f1066a.get().T = false;
                this.f1066a.get().R.setVisibility(8);
                this.f1066a.get().S.setVisibility(0);
                this.f1066a.get().S.setTypeface(Typeface.defaultFromStyle(0));
                this.f1066a.get().S.setTextColor(this.f1066a.get().getResources().getColor(R.color.white));
                this.f1066a.get().S.setText("- -");
                if (this.f1066a.get().u != 1) {
                    this.f1066a.get().P.setVisibility(8);
                    this.f1066a.get().Q.setVisibility(0);
                    this.f1066a.get().Q.setTypeface(Typeface.defaultFromStyle(0));
                    this.f1066a.get().Q.setText("- -");
                    this.f1066a.get().Q.setTextColor(this.f1066a.get().getResources().getColor(R.color.white));
                    return;
                }
                return;
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                this.f1066a.get().T = true;
                if (this.f1066a.get().E == 1) {
                    this.f1066a.get().C(((i) this.f1066a.get().f).k());
                    return;
                }
                if (this.f1066a.get().E == 2) {
                    int[] iArrJ = ((i) this.f1066a.get().f).j();
                    if (iArrJ.length > 0) {
                        this.f1066a.get().t(1, iArrJ[0]);
                        this.f1066a.get().t(2, iArrJ[1]);
                        return;
                    }
                    return;
                }
                return;
            }
            sendEmptyMessageDelayed(3, 1150L);
            this.f1066a.get().T = false;
            this.f1066a.get().R.setVisibility(8);
            this.f1066a.get().S.setVisibility(0);
            this.f1066a.get().S.setTypeface(Typeface.defaultFromStyle(0));
            this.f1066a.get().S.setTextColor(this.f1066a.get().getResources().getColor(R.color.white));
            this.f1066a.get().S.setText("- - -");
            if (this.f1066a.get().u != 1) {
                this.f1066a.get().P.setVisibility(8);
                this.f1066a.get().Q.setVisibility(0);
                this.f1066a.get().Q.setTypeface(Typeface.defaultFromStyle(0));
                this.f1066a.get().Q.setText("- - -");
                this.f1066a.get().Q.setTextColor(this.f1066a.get().getResources().getColor(R.color.white));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: O0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void P0(LottieComposition lottieComposition) {
        this.x.setComposition(lottieComposition);
        this.x.playAnimation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Q0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void R0(LottieComposition lottieComposition) {
        this.y.setComposition(lottieComposition);
        this.y.playAnimation();
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void A0() {
        this.N = getResources().getDimensionPixelSize(R.dimen.sp_21);
        this.O = getResources().getDimensionPixelSize(R.dimen.sp_25);
        N0();
        int iM = ((i) this.f).m();
        this.I = iM;
        R(iM);
        f(166, ((i) this.f).t(166));
    }

    @Override // b.a.a.d.f
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void C(int i) {
        BydLogHelper.logD(g, "updatePM2p5DualChannelDetectState state = " + i);
        this.H = i;
        if (i == 1) {
            this.i.setTextColor(getResources().getColor(R.color.text_tem_select));
            if (this.T) {
                this.R.setVisibility(0);
                this.S.setVisibility(8);
                if (this.u != 1) {
                    this.P.setVisibility(0);
                    this.Q.setVisibility(8);
                    return;
                }
                return;
            }
            return;
        }
        if (i == 2) {
            this.M.removeCallbacksAndMessages(null);
            this.T = true;
            this.i.setTextColor(getResources().getColor(R.color.text_tem_white));
            this.R.setVisibility(8);
            this.S.setVisibility(0);
            this.S.setTypeface(Typeface.defaultFromStyle(0));
            this.S.setText(getString(R.string.tv_pm25_invalid));
            this.S.setTextColor(getResources().getColor(R.color.white));
            if (this.u != 1) {
                this.P.setVisibility(8);
                this.Q.setVisibility(0);
                this.Q.setTypeface(Typeface.defaultFromStyle(0));
                this.Q.setText(getString(R.string.tv_pm25_invalid));
                this.Q.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }

    public final boolean L0() {
        if (((i) this.f).m() == 1) {
            return false;
        }
        if (this.U == null) {
            this.U = new b.a.a.i.i.a();
        }
        if (this.U.b()) {
            this.U.a();
        }
        AcStartVehicleTipsLayout acStartVehicleTipsLayout = (AcStartVehicleTipsLayout) LayoutInflater.from(this.e).inflate(R.layout.dialog_green_online_tips, (ViewGroup) null);
        acStartVehicleTipsLayout.setBtnCallBackListener(new a());
        this.U.c((Activity) this.e, acStartVehicleTipsLayout, (int) this.e.getResources().getDimension(R.dimen.dp_560), (int) this.e.getResources().getDimension(R.dimen.dp_312));
        return true;
    }

    public final int[] M0(int i) {
        TypedArray typedArrayObtainTypedArray = getResources().obtainTypedArray(i);
        int length = typedArrayObtainTypedArray.length();
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = typedArrayObtainTypedArray.getResourceId(i2, -1);
        }
        typedArrayObtainTypedArray.recycle();
        return iArr;
    }

    public final void N0() {
        int iS = ((i) this.f).s();
        this.E = iS;
        if (iS == 1) {
            this.j.setVisibility(8);
            this.h.setVisibility(8);
            this.i.setVisibility(0);
            C(((i) this.f).k());
        } else if (iS == 2) {
            this.j.setVisibility(0);
            this.h.setVisibility(0);
            this.i.setVisibility(8);
            t(1, this.s);
            t(2, this.t);
        }
        int iR = ((i) this.f).r();
        this.F = iR;
        if (iR != 2) {
            this.l.setVisibility(8);
        }
        this.G = ((i) this.f).g();
    }

    @Override // b.a.a.d.f
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void O(int i) {
        BydLogHelper.logD(g, "updateNegativeIonState  state = " + i);
        this.v = i;
        if (i == 1) {
            U0();
            this.l.setTextColor(getResources().getColor(R.color.text_tem_select));
        } else if (i == 2) {
            S0();
            this.l.setTextColor(getResources().getColor(R.color.text_tem_white));
        }
    }

    @Override // b.a.a.d.f
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void Q(int i) {
        BydLogHelper.logD(g, "updateRapidPurificationPM2p5State  state = " + i);
        this.u = i;
        if (i != 1) {
            if (i == 2 || i == 3) {
                this.k.setTextColor(getResources().getColor(R.color.text_tem_white));
                T0(false);
                W0();
                return;
            }
            return;
        }
        V0();
        this.k.setTextColor(getResources().getColor(R.color.text_tem_select));
        this.m.setBackgroundResource(R.mipmap.img_air_green_checking_denza);
        this.P.setVisibility(8);
        this.Q.setVisibility(0);
        this.Q.setTypeface(Typeface.defaultFromStyle(1));
        this.Q.setText(getString(R.string.pm2_5_checking));
        this.Q.setTextColor(getResources().getColor(R.color.text_air_green_checking));
    }

    @Override // b.a.a.d.f
    public void R(int i) {
        this.I = i;
        if (i == 1) {
            W0();
            l.a(true, this.J, this.h, this.i, this.j, this.m, this.n, this.o, this.p, this.q, this.r);
            return;
        }
        l.a(false, this.J, this.h, this.i, this.j, this.m, this.n, this.o, this.p, this.q, this.r);
        int i2 = this.E;
        if (i2 == 1) {
            C(2);
        } else if (i2 == 2) {
            t(1, 2);
            t(2, 2);
        }
        this.n.setText("--");
        this.o.setText(getString(R.string.in_car) + " --");
        this.q.setText("--");
        this.r.setText(getString(R.string.outside_car) + " --");
    }

    public final void S0() {
        LottieAnimationView lottieAnimationView = this.x;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
            this.x.cancelAnimation();
            this.x.clearAnimation();
        }
    }

    public final void T0(boolean z) {
        ImageView imageView = this.w;
        if (imageView != null) {
            imageView.setVisibility(8);
            this.y.setVisibility(8);
        }
        e eVar = this.z;
        if (eVar == null || this.y == null) {
            return;
        }
        eVar.o(z);
        this.y.cancelAnimation();
        this.y.clearAnimation();
    }

    public final void U0() {
        LottieAnimationView lottieAnimationView = this.x;
        if (lottieAnimationView == null || lottieAnimationView.isAnimating()) {
            return;
        }
        this.x.setVisibility(0);
        Context context = this.e;
        LottieCompositionFactory.fromRawRes(context, k.c(context) ? R.raw.gren_anion_port : R.raw.gren_anion).addListener(new LottieListener() { // from class: b.a.a.i.l.a
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                this.f924a.P0((LottieComposition) obj);
            }
        });
    }

    public final void V0() {
        e eVar = this.z;
        if (eVar != null && !eVar.m()) {
            this.w.setVisibility(0);
            this.z.n(0);
        }
        if (this.y.isAnimating()) {
            return;
        }
        this.y.setVisibility(0);
        Context context = this.e;
        LottieCompositionFactory.fromRawRes(context, k.c(context) ? R.raw.gren_leaf_port : R.raw.gren_leaf).addListener(new LottieListener() { // from class: b.a.a.i.l.b
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                this.f925a.R0((LottieComposition) obj);
            }
        });
    }

    public final void W0() {
        int[] iArrJ = ((i) this.f).j();
        if (iArrJ.length > 0) {
            this.s = iArrJ[0];
            this.t = iArrJ[1];
        }
        this.H = ((i) this.f).k();
        this.v = ((i) this.f).i();
        int[] iArrN = ((i) this.f).n();
        if (iArrN.length > 0) {
            this.A = iArrN[0];
            this.B = iArrN[1];
        }
        int[] iArrL = ((i) this.f).l();
        if (iArrL.length > 0) {
            this.D = iArrL[0];
            this.C = iArrL[1];
        }
        j0(this.A, this.B);
        k0(this.D, this.C);
        int i = this.E;
        if (i == 1) {
            C(this.H);
        } else if (i == 2) {
            t(1, this.s);
            t(2, this.t);
        }
    }

    @Override // b.a.a.d.f
    public void f(int i, int i2) {
        if (i == 166) {
            if (i2 == 1) {
                l.c(true, this.l, this.k);
                O(this.v);
                Q(((i) this.f).o());
            } else {
                l.c(false, this.l, this.k);
                O(2);
                Q(3);
                T0(true);
            }
        }
    }

    @Override // b.a.a.d.f
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void j0(int i, int i2) {
        if (i < 5) {
            i = 5;
        }
        if (i > 999) {
            i = 999;
        }
        if (i2 < 5) {
            i2 = 5;
        }
        int i3 = i2 <= 999 ? i2 : 999;
        this.A = i;
        this.B = i3;
        int[] iArrL = ((i) this.f).l();
        if (iArrL.length > 0) {
            this.D = iArrL[0];
            this.C = iArrL[1];
        }
        this.o.setText(getString(R.string.in_car) + " " + i);
        switch (this.D) {
            case 1:
                this.n.setTextColor(getResources().getColor(R.color.text_air_green_net_good_denza));
                this.o.setTextColor(getResources().getColor(R.color.text_air_green_net_good_denza));
                break;
            case 2:
                this.n.setTextColor(getResources().getColor(R.color.text_air_green_net_fine_denza));
                this.o.setTextColor(getResources().getColor(R.color.text_air_green_net_fine_denza));
                break;
            case 3:
                this.n.setTextColor(getResources().getColor(R.color.text_air_green_net_mild_denza));
                this.o.setTextColor(getResources().getColor(R.color.text_air_green_net_mild_denza));
                break;
            case 4:
                this.n.setTextColor(getResources().getColor(R.color.text_air_green_net_moderate_denza));
                this.o.setTextColor(getResources().getColor(R.color.text_air_green_net_moderate_denza));
                break;
            case 5:
                this.n.setTextColor(getResources().getColor(R.color.text_air_green_net_poor_denza));
                this.o.setTextColor(getResources().getColor(R.color.text_air_green_net_poor_denza));
                break;
            case 6:
                this.n.setTextColor(getResources().getColor(R.color.text_air_green_net_severe_denza));
                this.o.setTextColor(getResources().getColor(R.color.text_air_green_net_severe_denza));
                break;
        }
        this.r.setText(getString(R.string.outside_car) + " " + i3);
        switch (this.C) {
            case 1:
                this.q.setTextColor(getResources().getColor(R.color.text_air_green_net_good_denza));
                this.r.setTextColor(getResources().getColor(R.color.text_air_green_net_good_denza));
                break;
            case 2:
                this.q.setTextColor(getResources().getColor(R.color.text_air_green_net_fine_denza));
                this.r.setTextColor(getResources().getColor(R.color.text_air_green_net_fine_denza));
                break;
            case 3:
                this.q.setTextColor(getResources().getColor(R.color.text_air_green_net_mild_denza));
                this.r.setTextColor(getResources().getColor(R.color.text_air_green_net_mild_denza));
                break;
            case 4:
                this.q.setTextColor(getResources().getColor(R.color.text_air_green_net_moderate_denza));
                this.r.setTextColor(getResources().getColor(R.color.text_air_green_net_moderate_denza));
                break;
            case 5:
                this.q.setTextColor(getResources().getColor(R.color.text_air_green_net_poor_denza));
                this.r.setTextColor(getResources().getColor(R.color.text_air_green_net_poor_denza));
                break;
            case 6:
                this.q.setTextColor(getResources().getColor(R.color.text_air_green_net_severe_denza));
                this.r.setTextColor(getResources().getColor(R.color.text_air_green_net_severe_denza));
                break;
        }
    }

    @Override // b.a.a.d.f
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void k0(int i, int i2) {
        this.D = i;
        this.C = i2;
        switch (i) {
            case 1:
                this.n.setText(getString(R.string.pm25_level_excellent));
                this.n.setTextColor(getResources().getColor(R.color.text_air_green_net_good_denza));
                this.o.setTextColor(getResources().getColor(R.color.text_air_green_net_good_denza));
                if (this.u != 1) {
                    this.m.setBackgroundResource(R.mipmap.img_dark_air_green_net_good_denza);
                }
                break;
            case 2:
                this.n.setText(getString(R.string.pm25_level_good));
                this.n.setTextColor(getResources().getColor(R.color.text_air_green_net_fine_denza));
                this.o.setTextColor(getResources().getColor(R.color.text_air_green_net_fine_denza));
                if (this.u != 1) {
                    this.m.setBackgroundResource(R.mipmap.img_dark_air_green_net_fine_denza);
                }
                break;
            case 3:
                this.n.setText(getString(R.string.pm25_level_low_grade));
                this.n.setTextColor(getResources().getColor(R.color.text_air_green_net_mild_denza));
                this.o.setTextColor(getResources().getColor(R.color.text_air_green_net_mild_denza));
                if (this.u != 1) {
                    this.m.setBackgroundResource(R.mipmap.img_dark_air_green_net_mild_denza);
                }
                break;
            case 4:
                this.n.setText(getString(R.string.pm25_level_middle));
                this.n.setTextColor(getResources().getColor(R.color.text_air_green_net_moderate_denza));
                this.o.setTextColor(getResources().getColor(R.color.text_air_green_net_moderate_denza));
                if (this.u != 1) {
                    this.m.setBackgroundResource(R.mipmap.img_dark_air_green_net_moderate_denza);
                }
                break;
            case 5:
                this.n.setText(getString(R.string.pm25_level_heavy));
                this.n.setTextColor(getResources().getColor(R.color.text_air_green_net_poor_denza));
                this.o.setTextColor(getResources().getColor(R.color.text_air_green_net_poor_denza));
                if (this.u != 1) {
                    this.m.setBackgroundResource(R.mipmap.img_dark_air_green_net_poor_denza);
                }
                break;
            case 6:
                this.n.setText(getString(R.string.pm25_level_serious));
                this.n.setTextColor(getResources().getColor(R.color.text_air_green_net_severe_denza));
                this.o.setTextColor(getResources().getColor(R.color.text_air_green_net_severe_denza));
                if (this.u != 1) {
                    this.m.setBackgroundResource(R.mipmap.img_dark_air_green_net_severe_denza);
                }
                break;
        }
        switch (i2) {
            case 1:
                this.q.setText(getString(R.string.pm25_level_excellent));
                this.q.setTextColor(getResources().getColor(R.color.text_air_green_net_good_denza));
                this.r.setTextColor(getResources().getColor(R.color.text_air_green_net_good_denza));
                this.p.setBackgroundResource(R.mipmap.img_dark_air_green_net_good_denza);
                break;
            case 2:
                this.q.setText(getString(R.string.pm25_level_good));
                this.q.setTextColor(getResources().getColor(R.color.text_air_green_net_fine_denza));
                this.r.setTextColor(getResources().getColor(R.color.text_air_green_net_fine_denza));
                this.p.setBackgroundResource(R.mipmap.img_dark_air_green_net_fine_denza);
                break;
            case 3:
                this.q.setText(getString(R.string.pm25_level_low_grade));
                this.q.setTextColor(getResources().getColor(R.color.text_air_green_net_mild_denza));
                this.r.setTextColor(getResources().getColor(R.color.text_air_green_net_mild_denza));
                this.p.setBackgroundResource(R.mipmap.img_dark_air_green_net_mild_denza);
                break;
            case 4:
                this.q.setText(getString(R.string.pm25_level_middle));
                this.q.setTextColor(getResources().getColor(R.color.text_air_green_net_moderate_denza));
                this.r.setTextColor(getResources().getColor(R.color.text_air_green_net_moderate_denza));
                this.p.setBackgroundResource(R.mipmap.img_dark_air_green_net_moderate_denza);
                break;
            case 5:
                this.q.setText(getString(R.string.pm25_level_heavy));
                this.q.setTextColor(getResources().getColor(R.color.text_air_green_net_poor_denza));
                this.r.setTextColor(getResources().getColor(R.color.text_air_green_net_poor_denza));
                this.p.setBackgroundResource(R.mipmap.img_dark_air_green_net_poor_denza);
                break;
            case 6:
                this.q.setText(getString(R.string.pm25_level_serious));
                this.q.setTextColor(getResources().getColor(R.color.text_air_green_net_severe_denza));
                this.r.setTextColor(getResources().getColor(R.color.text_air_green_net_severe_denza));
                this.p.setBackgroundResource(R.mipmap.img_dark_air_green_net_severe_denza);
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (k.b()) {
        }
        switch (view.getId()) {
            case R.id.ib_anion_state /* 2131296513 */:
                if (this.v == 1) {
                    ((i) this.f).u(2);
                } else {
                    ((i) this.f).u(1);
                }
                break;
            case R.id.ib_pm25_dual_channel_state /* 2131296516 */:
                if (!L0()) {
                    this.M.removeCallbacksAndMessages(null);
                    if (this.H == 1) {
                        this.T = true;
                        ((i) this.f).w(2);
                    } else {
                        this.T = false;
                        this.M.sendEmptyMessage(0);
                        ((i) this.f).w(1);
                    }
                    break;
                }
                break;
            case R.id.ib_pm25_in_car_state /* 2131296517 */:
                if (!L0()) {
                    if (this.s == 1) {
                        ((i) this.f).v(1, 3);
                    } else {
                        ((i) this.f).v(1, 1);
                    }
                    break;
                }
                break;
            case R.id.ib_pm25_out_car_state /* 2131296518 */:
                if (!L0()) {
                    if (this.t == 1) {
                        ((i) this.f).v(2, 3);
                    } else {
                        ((i) this.f).v(2, 2);
                    }
                    break;
                }
                break;
            case R.id.ib_rapid_green_state /* 2131296519 */:
                if (this.u == 1) {
                    ((i) this.f).x(2);
                } else {
                    ((i) this.f).x(1);
                }
                break;
        }
    }

    @Override // com.byd.hvac.base.BaseFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.M.removeCallbacksAndMessages(null);
        TextView textView = this.h;
        if (textView != null) {
            textView.setOnClickListener(null);
        }
        TextView textView2 = this.i;
        if (textView2 != null) {
            textView2.setOnClickListener(null);
        }
        TextView textView3 = this.j;
        if (textView3 != null) {
            textView3.setOnClickListener(null);
        }
        TextView textView4 = this.k;
        if (textView4 != null) {
            textView4.setOnClickListener(null);
        }
        TextView textView5 = this.l;
        if (textView5 != null) {
            textView5.setOnClickListener(null);
        }
        S0();
        T0(true);
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        T t;
        super.onHiddenChanged(z);
        if (!this.f985a) {
            BydLogHelper.logD("视图未准备好");
            return;
        }
        if (z || (t = this.f) == 0 || ((i) t).t(166) != 1) {
            S0();
            T0(true);
        } else {
            O(((i) this.f).i());
            Q(((i) this.f).o());
        }
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onPause() {
        S0();
        T0(true);
        super.onPause();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        T t = this.f;
        if (t == 0 || ((i) t).t(166) != 1) {
            S0();
            T0(true);
        } else {
            O(((i) this.f).i());
            Q(((i) this.f).o());
        }
    }

    @Override // b.a.a.d.f
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void t(int i, int i2) {
        BydLogHelper.logD(g, "updatePM2p5CheckState area = " + i + "  --  state = " + i2);
        if (i != 1) {
            if (i == 2) {
                if (i2 == 2) {
                    this.M.removeCallbacksAndMessages(null);
                    this.T = true;
                    this.t = i2;
                    this.j.setTextColor(getResources().getColor(R.color.text_tem_white));
                    this.S.setVisibility(0);
                    this.R.setVisibility(8);
                    return;
                }
                if (i2 == 1) {
                    this.t = i2;
                    this.j.setTextColor(getResources().getColor(R.color.text_tem_select));
                    this.R.setVisibility(0);
                    this.S.setVisibility(8);
                    return;
                }
                return;
            }
            return;
        }
        if (i2 == 1) {
            this.s = i2;
            this.h.setTextColor(getResources().getColor(R.color.text_tem_select));
            if (this.u != 1) {
                this.Q.setVisibility(8);
                this.P.setVisibility(0);
                return;
            }
            return;
        }
        if (i2 == 2) {
            this.M.removeCallbacksAndMessages(null);
            this.T = true;
            this.s = i2;
            this.h.setTextColor(getResources().getColor(R.color.text_tem_white));
            if (this.u != 1) {
                this.P.setVisibility(8);
                this.Q.setVisibility(0);
                this.Q.setTypeface(Typeface.defaultFromStyle(0));
                this.Q.setText(getString(R.string.tv_pm25_invalid));
                this.Q.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }

    @Override // com.byd.hvac.base.BaseFragment
    public int v0() {
        int iH = ((i) this.f).h();
        this.V = iH;
        return iH == 143 ? ((i) this.f).q() != 1 ? R.layout.fragment_green_control_pa : R.layout.fragment_green_control_right_rudder_pa : ((i) this.f).q() != 1 ? R.layout.fragment_green_control : R.layout.fragment_green_control_right_rudder;
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void x0(View view) {
        this.h = (TextView) view.findViewById(R.id.ib_pm25_in_car_state);
        this.i = (TextView) view.findViewById(R.id.ib_pm25_dual_channel_state);
        this.j = (TextView) view.findViewById(R.id.ib_pm25_out_car_state);
        this.k = (TextView) view.findViewById(R.id.ib_rapid_green_state);
        this.l = (TextView) view.findViewById(R.id.ib_anion_state);
        this.m = (ConstraintLayout) view.findViewById(R.id.cl_in_car_pm25_level);
        this.n = (TextView) view.findViewById(R.id.tv_inside_pm25_level);
        this.o = (TextView) view.findViewById(R.id.tv_inside_pm25_value);
        this.p = (ConstraintLayout) view.findViewById(R.id.cl_out_car_pm25_level);
        this.q = (TextView) view.findViewById(R.id.tv_out_pm25_level);
        this.r = (TextView) view.findViewById(R.id.tv_out_pm25_value);
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(this.e.getAssets(), "font/HFMoBestSans-R.otf");
        if (typefaceCreateFromAsset != null) {
            this.o.setTypeface(typefaceCreateFromAsset);
            this.r.setTypeface(typefaceCreateFromAsset);
        }
        this.J = (ImageView) view.findViewById(R.id.iv_green_car_bg);
        this.P = (RelativeLayout) view.findViewById(R.id.rl_in_pm25_value_container);
        this.Q = (TextView) view.findViewById(R.id.tv_in_pm25_close_state);
        this.R = (RelativeLayout) view.findViewById(R.id.rl_out_pm25_value_container);
        this.S = (TextView) view.findViewById(R.id.tv_out_pm25_close_state);
        this.w = (ImageView) view.findViewById(R.id.iv_green_line_nothing_to_exit);
        this.x = (LottieAnimationView) view.findViewById(R.id.iv_green_anion_nothing_to_exit);
        this.y = (LottieAnimationView) view.findViewById(R.id.iv_green_leaf_nothing_to_exit);
        int iP = ((i) this.f).p();
        if (this.V == 143) {
            this.z = new e(getActivity(), this.w, M0(R.array.greenLineNothingToExitPa), 50, true);
            this.x.setAnimation(k.c(this.e) ? R.raw.gren_anion_pa_port : R.raw.gren_anion_pa);
            this.y.setAnimation(k.c(this.e) ? R.raw.gren_leaf_pa_port : R.raw.gren_leaf_pa);
            this.J.setImageResource(R.mipmap.img_air_green_net_car_bg_pa);
        } else {
            this.z = new e(getActivity(), this.w, M0(R.array.greenLineNothingToExit2), 50, true);
            this.x.setAnimation(k.c(this.e) ? R.raw.gren_anion_port : R.raw.gren_anion);
            this.y.setAnimation(k.c(this.e) ? R.raw.gren_leaf_port : R.raw.gren_leaf);
            if (iP == 2) {
                this.J.setImageResource(R.mipmap.img_dark_air_green_net_car_bg_7);
            } else {
                this.J.setImageResource(R.mipmap.img_dark_air_green_net_car_bg_5);
            }
        }
        this.M = new b(this);
        A0();
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void z0() {
        super.z0();
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
    }
}
