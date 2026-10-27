package com.byd.hvac.view.accontrol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import b.a.a.g.e;
import b.a.a.h.k;
import b.a.a.h.l;
import b.a.a.h.m;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.byd.hvac.R;
import com.byd.hvac.base.BaseFragment;
import com.byd.hvac.view.WindLevelButton;
import com.byd.hvac.view.datapickview.WheelView;
import com.byd.hvac.view.dialog.AcInnerDisplayModeTipsLayout;
import com.byd.hvac.view.dialog.AcStartVehicleTipsLayout;
import com.byd.hvac.view.tintimageview.TintImageView;
import com.byd.hvac.view.tintimageview.TintLottieAnimationView;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class AcFrontControlFragment extends BaseFragment<e> implements b.a.a.d.b, View.OnClickListener, WheelView.d {
    public ImageButton A;
    public LottieValueCallback<ColorFilter> A0;
    public TintImageView B;
    public LottieValueCallback<ColorFilter> B0;
    public LottieAnimationView C;
    public KeyPath C0;
    public WindLevelButton D;
    public boolean D0;
    public LottieAnimationView E;
    public boolean E0;
    public int F;
    public int F0;
    public int G;
    public int G0;
    public int H;
    public SparseArray<String> H0;
    public int I;
    public SparseArray<String> I0;
    public int J;
    public SparseArray<String> J0;
    public int K;
    public SparseArray<String> K0;
    public int L;
    public SparseArray<String> L0;
    public int M;
    public SparseArray<String> M0;
    public int N;
    public SparseArray<String> N0;
    public volatile int O;
    public SparseArray<String> O0;
    public volatile int P;
    public SparseArray<String> P0;
    public int Q;
    public int Q0;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public ArrayList<String> X;
    public ArrayList<String> Y;
    public LottieAnimationView Z;
    public ImageView a0;
    public LottieAnimationView b0;
    public b.a.a.h.e c0;
    public ImageView d0;
    public LottieAnimationView e0;
    public ImageView f0;
    public WheelView g;
    public LottieAnimationView g0;
    public WheelView h;
    public b.a.a.h.e h0;
    public TintLottieAnimationView i;
    public b.a.a.h.e i0;
    public TintLottieAnimationView j;
    public b.a.a.i.i.a j0;
    public TintLottieAnimationView k;
    public int k0;
    public TintLottieAnimationView l;
    public int l0;
    public TintLottieAnimationView m;
    public int m0;
    public TintLottieAnimationView n;
    public int n0;
    public TintLottieAnimationView o;
    public int o0;
    public TintLottieAnimationView p;
    public int p0;
    public TintLottieAnimationView q;
    public int q0;
    public ImageButton r;
    public int r0;
    public ImageView s;
    public ImageView s0;
    public ImageButton t;
    public TextView t0;
    public ImageButton u;
    public SimpleColorFilter u0;
    public ImageView v;
    public SimpleColorFilter v0;
    public ImageView w;
    public SimpleColorFilter w0;
    public ImageView x;
    public SimpleColorFilter x0;
    public ImageButton y;
    public LottieValueCallback<ColorFilter> y0;
    public ImageView z;
    public LottieValueCallback<ColorFilter> z0;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements WindLevelButton.a {
        public a() {
        }

        @Override // com.byd.hvac.view.WindLevelButton.a
        public void a(int i) {
            BydLogHelper.logD("onChanged progress:" + i);
            if (AcFrontControlFragment.this.isResumed() && AcFrontControlFragment.this.J0() && AcFrontControlFragment.this.K0()) {
                return;
            }
            m.b(AcFrontControlFragment.this.D);
        }

        @Override // com.byd.hvac.view.WindLevelButton.a
        public void b(int i) {
            BydLogHelper.logD("onTouchDown progress:" + i);
            m.b(AcFrontControlFragment.this.D);
        }

        @Override // com.byd.hvac.view.WindLevelButton.a
        public void c(int i) {
            BydLogHelper.logD("onTouchUp progress:" + i);
            if (i != AcFrontControlFragment.this.W) {
                ((e) AcFrontControlFragment.this.f).Y(i);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class b implements AcStartVehicleTipsLayout.b {
        public b() {
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void a() {
            AcFrontControlFragment.this.j0.a();
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void b() {
            AcFrontControlFragment.this.j0.a();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class c implements AcStartVehicleTipsLayout.b {
        public c() {
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void a() {
            AcFrontControlFragment.this.j0.a();
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void b() {
            AcFrontControlFragment.this.j0.a();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class d implements AcInnerDisplayModeTipsLayout.b {
        public d() {
        }

        @Override // com.byd.hvac.view.dialog.AcInnerDisplayModeTipsLayout.b
        public void a() {
            AcFrontControlFragment.this.j0.a();
        }

        @Override // com.byd.hvac.view.dialog.AcInnerDisplayModeTipsLayout.b
        public void b() {
            AcFrontControlFragment.this.j0.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: T0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void U0(LottieAnimationView lottieAnimationView, boolean z, LottieComposition lottieComposition) {
        lottieAnimationView.setComposition(lottieComposition);
        f1(lottieAnimationView, z, 1);
        lottieAnimationView.playAnimation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: V0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void W0(TintLottieAnimationView tintLottieAnimationView, boolean z, int i, LottieComposition lottieComposition) {
        tintLottieAnimationView.setComposition(lottieComposition);
        f1(tintLottieAnimationView, z, i);
        tintLottieAnimationView.playAnimation();
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void A0() {
        Q0();
        S0();
        R0();
        this.g.setItems(this.X);
        this.h.setItems(this.Y);
        if (this.o0 == 0) {
            if (this.F0 == 2) {
                this.g.setSelected(this.X.indexOf(String.valueOf(77.0d)));
                this.h.setSelected(this.Y.indexOf(String.valueOf(77.0d)));
            } else {
                this.g.setSelected(this.X.indexOf(String.valueOf(77)));
                this.h.setSelected(this.Y.indexOf(String.valueOf(77)));
            }
        } else if (this.F0 == 2) {
            this.g.setSelected(this.X.indexOf(String.valueOf(25.0d)));
            this.h.setSelected(this.Y.indexOf(String.valueOf(25.0d)));
        } else {
            this.g.setSelected(this.X.indexOf(String.valueOf(25)));
            this.h.setSelected(this.Y.indexOf(String.valueOf(25)));
        }
        if (this.F0 == 2) {
            if (k.a(this.e)) {
                this.g.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_46), getResources().getDimensionPixelSize(R.dimen.sp_24));
                this.g.setTextSpaceRatio(3.0f);
                this.h.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_46), getResources().getDimensionPixelSize(R.dimen.sp_24));
                this.h.setTextSpaceRatio(3.0f);
            } else {
                this.g.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_56), getResources().getDimensionPixelSize(R.dimen.sp_30));
                this.h.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_56), getResources().getDimensionPixelSize(R.dimen.sp_30));
            }
        }
        this.q0 = ((e) this.f).E();
        int iM = ((e) this.f).m();
        this.p0 = iM;
        int i = this.q0;
        if (i == 1) {
            j(i);
        } else {
            l(iM);
        }
    }

    @Override // b.a.a.d.b
    public void E(int i) {
        BydLogHelper.logD("updateAcTemperatureControlMode mode = " + i);
        this.Q = i;
        if (i == 3) {
            this.B.setVisibility(8);
            return;
        }
        this.B.setVisibility(0);
        int i2 = this.Q;
        if (i2 == 0 || i2 == 1) {
            this.B.a(i == 1 ? 1 : 0);
        }
    }

    @Override // com.byd.hvac.view.datapickview.WheelView.d
    public void G(WheelView wheelView) {
        int id = wheelView.getId();
        if (id == R.id.left_wheel_view) {
            this.D0 = false;
        } else {
            if (id != R.id.right_wheel_view) {
                return;
            }
            this.E0 = false;
        }
    }

    public final void H0(int i) {
        int i2;
        switch (i) {
            case 2:
                i2 = 44;
                break;
            case 3:
                i2 = 38;
                break;
            case 4:
                i2 = 32;
                break;
            case 5:
                i2 = 26;
                break;
            case 6:
                i2 = 20;
                break;
            case 7:
                i2 = 14;
                break;
            default:
                i2 = 50;
                break;
        }
        float f = (i * 0.12f) + 1.0f;
        b.a.a.h.e eVar = this.c0;
        if (eVar != null) {
            eVar.p(i2);
        }
        b.a.a.h.e eVar2 = this.h0;
        if (eVar2 != null) {
            eVar2.p(i2);
        }
        b.a.a.h.e eVar3 = this.i0;
        if (eVar3 != null) {
            eVar3.p(i2);
        }
        LottieAnimationView lottieAnimationView = this.Z;
        if (lottieAnimationView != null) {
            lottieAnimationView.setSpeed(f);
        }
        LottieAnimationView lottieAnimationView2 = this.b0;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setSpeed(f);
        }
        LottieAnimationView lottieAnimationView3 = this.e0;
        if (lottieAnimationView3 != null) {
            lottieAnimationView3.setSpeed(f);
        }
        LottieAnimationView lottieAnimationView4 = this.g0;
        if (lottieAnimationView4 != null) {
            lottieAnimationView4.setSpeed(f);
        }
    }

    public void I0(int i, TintLottieAnimationView... tintLottieAnimationViewArr) {
        if (tintLottieAnimationViewArr == null || tintLottieAnimationViewArr.length <= 0) {
            return;
        }
        for (TintLottieAnimationView tintLottieAnimationView : tintLottieAnimationViewArr) {
            if (tintLottieAnimationView != null) {
                tintLottieAnimationView.c(i);
            }
        }
    }

    public final boolean J0() {
        if (((e) this.f).E() != 1) {
            return false;
        }
        if (this.j0 == null) {
            this.j0 = new b.a.a.i.i.a();
        }
        if (this.j0.b()) {
            this.j0.a();
        }
        AcStartVehicleTipsLayout acStartVehicleTipsLayout = (AcStartVehicleTipsLayout) LayoutInflater.from(this.e).inflate(R.layout.dialog_ac_start_vehicle_tips, (ViewGroup) null);
        acStartVehicleTipsLayout.setBtnCallBackListener(new b());
        this.j0.c((Activity) this.e, acStartVehicleTipsLayout, (int) this.e.getResources().getDimension(R.dimen.dp_560), (int) this.e.getResources().getDimension(R.dimen.dp_312));
        return true;
    }

    public final boolean K0() {
        if (this.p0 == 1) {
            return false;
        }
        if (this.j0 == null) {
            this.j0 = new b.a.a.i.i.a();
        }
        if (this.j0.b()) {
            this.j0.a();
        }
        AcStartVehicleTipsLayout acStartVehicleTipsLayout = (AcStartVehicleTipsLayout) LayoutInflater.from(this.e).inflate(R.layout.dialog_ac_online_tips, (ViewGroup) null);
        acStartVehicleTipsLayout.setBtnCallBackListener(new c());
        this.j0.c((Activity) this.e, acStartVehicleTipsLayout, (int) this.e.getResources().getDimension(R.dimen.dp_560), (int) this.e.getResources().getDimension(R.dimen.dp_312));
        return true;
    }

    public final boolean L0() {
        if (((e) this.f).F() != 1) {
            return false;
        }
        if (this.j0 == null) {
            this.j0 = new b.a.a.i.i.a();
        }
        if (this.j0.b()) {
            this.j0.a();
        }
        AcInnerDisplayModeTipsLayout acInnerDisplayModeTipsLayout = (AcInnerDisplayModeTipsLayout) LayoutInflater.from(this.e).inflate(R.layout.dialog_ac_inner_display_mode_tips, (ViewGroup) null);
        acInnerDisplayModeTipsLayout.setBtnCallBackListener(new d());
        this.j0.c((Activity) this.e, acInnerDisplayModeTipsLayout, (int) this.e.getResources().getDimension(R.dimen.dp_560), (int) this.e.getResources().getDimension(R.dimen.dp_264));
        return true;
    }

    public final int M0(int i) {
        return i != 0 ? this.Q0 == 143 ? R.mipmap.icon_internal_pa : R.mipmap.icon_internal : this.Q0 == 143 ? R.mipmap.icon_external_pa : R.mipmap.icon_external;
    }

    public final int[] N0(int i) {
        TypedArray typedArrayObtainTypedArray = getResources().obtainTypedArray(i);
        int length = typedArrayObtainTypedArray.length();
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = typedArrayObtainTypedArray.getResourceId(i2, -1);
        }
        typedArrayObtainTypedArray.recycle();
        return iArr;
    }

    public final int O0(int i) {
        if (((e) this.f).G() != 1) {
            return i;
        }
        if (i != R.layout.fragment_ac_front_control) {
            return 0;
        }
        return R.layout.fragment_ac_front_control_right_rudder;
    }

    public SparseArray<String> P0(int i, int i2, int i3) {
        int i4 = 0;
        if (i != 0) {
            if (i2 == 2) {
                if (i3 == 0) {
                    if (this.M0 == null) {
                        this.M0 = new SparseArray<>();
                        while (true) {
                            Integer[] numArr = b.a.a.h.c.g;
                            if (i4 >= numArr.length) {
                                break;
                            }
                            this.M0.append(numArr[i4].intValue(), b.a.a.h.c.h[i4]);
                            i4++;
                        }
                    }
                    return this.M0;
                }
                if (this.N0 == null) {
                    this.N0 = new SparseArray<>();
                    while (true) {
                        Integer[] numArr2 = b.a.a.h.c.e;
                        if (i4 >= numArr2.length) {
                            break;
                        }
                        this.N0.append(numArr2[i4].intValue(), b.a.a.h.c.f[i4]);
                        i4++;
                    }
                }
                return this.N0;
            }
            if (i3 == 0) {
                if (this.O0 == null) {
                    this.O0 = new SparseArray<>();
                    while (true) {
                        Integer[] numArr3 = b.a.a.h.c.f240c;
                        if (i4 >= numArr3.length) {
                            break;
                        }
                        this.O0.append(numArr3[i4].intValue(), b.a.a.h.c.d[i4]);
                        i4++;
                    }
                }
                return this.O0;
            }
            if (this.P0 == null) {
                this.P0 = new SparseArray<>();
                while (true) {
                    Integer[] numArr4 = b.a.a.h.c.f238a;
                    if (i4 >= numArr4.length) {
                        break;
                    }
                    this.P0.append(numArr4[i4].intValue(), b.a.a.h.c.f239b[i4]);
                    i4++;
                }
            }
            return this.P0;
        }
        if (i2 != 2) {
            if (i3 == 0) {
                if (this.K0 == null) {
                    this.K0 = new SparseArray<>();
                    while (true) {
                        Integer[] numArr5 = b.a.a.h.c.k;
                        if (i4 >= numArr5.length) {
                            break;
                        }
                        this.K0.append(numArr5[i4].intValue(), b.a.a.h.c.l[i4]);
                        i4++;
                    }
                }
                return this.K0;
            }
            if (this.L0 == null) {
                this.L0 = new SparseArray<>();
                while (true) {
                    Integer[] numArr6 = b.a.a.h.c.i;
                    if (i4 >= numArr6.length) {
                        break;
                    }
                    this.L0.append(numArr6[i4].intValue(), b.a.a.h.c.j[i4]);
                    i4++;
                }
            }
            return this.L0;
        }
        if (i3 == 0) {
            if (this.I0 == null) {
                this.I0 = new SparseArray<>();
                while (true) {
                    Integer[] numArr7 = b.a.a.h.c.o;
                    if (i4 >= numArr7.length) {
                        break;
                    }
                    this.I0.append(numArr7[i4].intValue(), b.a.a.h.c.p[i4]);
                    i4++;
                }
            }
            return this.I0;
        }
        if (this.J0 == null) {
            this.J0 = new SparseArray<>();
            while (true) {
                Integer[] numArr8 = b.a.a.h.c.m;
                if (i4 >= numArr8.length) {
                    break;
                }
                BydLogHelper.logD("getTempDatas  tempF05NoSupportAcHeatKey [" + i4 + "]=  " + numArr8[i4]);
                StringBuilder sb = new StringBuilder();
                sb.append("getTempDatas  tempF05NoSupportAcHeatValue [");
                sb.append(i4);
                sb.append("]=  ");
                String[] strArr = b.a.a.h.c.n;
                sb.append(strArr[i4]);
                BydLogHelper.logD(sb.toString());
                this.J0.append(numArr8[i4].intValue(), strArr[i4]);
                i4++;
            }
        }
        return this.J0;
    }

    public final void Q0() {
        this.Q0 = ((e) this.f).x();
        int i = ((e) this.f).i();
        this.M = i;
        this.p.setTintButton(ContextCompat.getDrawable(this.e, M0(i)));
        this.p.c(0);
        this.c0 = new b.a.a.h.e(getActivity(), this.a0, N0(R.array.topRibbonNothingToExit), 50, true);
        this.h0 = new b.a.a.h.e(getActivity(), this.d0, N0(R.array.midRibbonNothingToExit), 50, true);
        this.i0 = new b.a.a.h.e(getActivity(), this.f0, N0(R.array.bottomRibbonNothingToExit), 50, true);
        int iA = ((e) this.f).A();
        this.l0 = iA;
        if (iA == 0) {
            this.o.setVisibility(8);
        } else if (iA == 1) {
            this.o.setTintButton(ContextCompat.getDrawable(this.e, R.mipmap.icon_rear_defrosting));
            this.o.setImageResource(R.mipmap.icon_rear_defrosting);
        } else if (iA == 2) {
            this.o.setTintButton(ContextCompat.getDrawable(this.e, R.mipmap.icon_rear_defrosting_exterior_rearview_mirror_heating));
            this.o.setImageResource(R.mipmap.icon_rear_defrosting_exterior_rearview_mirror_heating);
        } else if (iA == 3) {
            this.o.setTintButton(ContextCompat.getDrawable(this.e, R.mipmap.icon_dock_mirror_heating));
            this.o.setImageResource(R.mipmap.icon_dock_mirror_heating);
        }
        int iB = ((e) this.f).B();
        this.r0 = iB;
        if (iB == 2) {
            this.k.setTintButton(ContextCompat.getDrawable(this.e, R.mipmap.icon_ac_cool));
            this.k.setImageResource(R.mipmap.icon_ac_cool);
        } else if (iB == 3) {
            this.k.setTintButton(ContextCompat.getDrawable(this.e, R.mipmap.icon_ac_heat));
            this.k.setImageResource(R.mipmap.icon_ac_heat);
        } else {
            this.k.setTintButton(ContextCompat.getDrawable(this.e, R.mipmap.icon_ac));
            this.k.setImageResource(R.mipmap.icon_ac);
        }
        this.C.setImageResource(R.mipmap.icon_minmum_wind_level);
        this.E.setImageResource(R.mipmap.icon_maxmum_wind_level);
        this.b0.setAnimation(R.raw.particle_up);
        this.e0.setAnimation(R.raw.particle_md);
        this.g0.setAnimation(R.raw.particle_dw);
    }

    public final void R0() {
        this.o0 = ((e) this.f).L();
        int iK = ((e) this.f).K();
        this.F0 = iK;
        this.H0 = P0(this.o0, iK, this.G0);
        if (this.X == null || this.Y == null) {
            this.X = new ArrayList<>();
            this.Y = new ArrayList<>();
        }
        this.X.clear();
        this.Y.clear();
        for (int size = this.H0.size() - 1; size >= 0; size--) {
            this.X.add(this.H0.valueAt(size));
            this.Y.add(this.H0.valueAt(size));
        }
    }

    @Override // b.a.a.d.b
    public void S(int i) {
        BydLogHelper.logD("updateAcVentilationState state = " + i);
        this.N = i;
        if (i == 1) {
            if (this.q.isAnimating()) {
                return;
            }
            this.q.c(1);
        } else {
            if (i != 0 || this.q.isAnimating()) {
                return;
            }
            this.q.c(0);
        }
    }

    public final void S0() {
        this.k0 = ((e) this.f).v();
        this.n0 = ((e) this.f).I();
        this.o0 = ((e) this.f).L();
        if (this.n0 == 2) {
            this.u.setVisibility(0);
        } else {
            this.u.setVisibility(4);
        }
        int iK = ((e) this.f).k();
        this.G0 = iK;
        if (iK == 1) {
            this.m.setVisibility(8);
        } else {
            this.m.setVisibility(0);
        }
    }

    @Override // b.a.a.d.b
    public void U(int i, boolean z) {
        BydLogHelper.logD("updateAcHeatingState state = " + i);
        this.J = i;
        if (i == 1) {
            if (z || this.m.isAnimating()) {
                return;
            }
            this.m.c(1);
            return;
        }
        if (i != 2 || z || this.m.isAnimating()) {
            return;
        }
        this.m.c(0);
    }

    @Override // com.byd.hvac.view.datapickview.WheelView.d
    public void V(WheelView wheelView, int i, String str) {
        BydLogHelper.logD("onChange index:" + i);
        if (J0()) {
            BydLogHelper.logD("onChange powerlevel is acc , return !");
            return;
        }
        m.b(wheelView);
        SparseArray<String> sparseArray = this.H0;
        int iKeyAt = sparseArray != null ? this.H0.keyAt(sparseArray.indexOfValue(str)) : 25;
        BydLogHelper.logD("onChange driverTemp:" + Integer.toHexString(iKeyAt));
        boolean z = str.equals("Lo") || str.equals("Hi");
        int id = wheelView.getId();
        if (id == R.id.left_wheel_view) {
            ((e) this.f).U(1, iKeyAt);
            if (TextUtils.isEmpty(str) || !z) {
                this.s.setVisibility(0);
                return;
            } else {
                this.s.setVisibility(8);
                return;
            }
        }
        if (id != R.id.right_wheel_view) {
            return;
        }
        ((e) this.f).U(2, iKeyAt);
        if (TextUtils.isEmpty(str) || !z) {
            this.z.setVisibility(0);
        } else {
            this.z.setVisibility(8);
        }
    }

    public final void X0() {
        LottieAnimationView lottieAnimationView = this.Z;
        if (lottieAnimationView != null && lottieAnimationView.isAnimating()) {
            this.Z.pauseAnimation();
        }
        LottieAnimationView lottieAnimationView2 = this.b0;
        if (lottieAnimationView2 != null && lottieAnimationView2.isAnimating()) {
            this.b0.pauseAnimation();
        }
        LottieAnimationView lottieAnimationView3 = this.e0;
        if (lottieAnimationView3 != null && lottieAnimationView3.isAnimating()) {
            this.e0.pauseAnimation();
        }
        LottieAnimationView lottieAnimationView4 = this.g0;
        if (lottieAnimationView4 == null || !lottieAnimationView4.isAnimating()) {
            return;
        }
        this.g0.pauseAnimation();
    }

    public final void Y0(int i, final LottieAnimationView lottieAnimationView, final boolean z) {
        LottieCompositionFactory.fromRawRes(this.e, i).addListener(new LottieListener() { // from class: b.a.a.i.g.c
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                this.f272a.U0(lottieAnimationView, z, (LottieComposition) obj);
            }
        });
    }

    public final void Z0(int i, int i2, int i3) {
        LottieAnimationView lottieAnimationView = this.Z;
        if (lottieAnimationView != null && this.R == 2) {
            lottieAnimationView.setVisibility(4);
            this.Z.cancelAnimation();
            this.Z.clearAnimation();
        }
        LottieAnimationView lottieAnimationView2 = this.b0;
        if (lottieAnimationView2 != null && i == 0) {
            lottieAnimationView2.cancelAnimation();
            this.b0.clearAnimation();
            this.b0.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView3 = this.e0;
        if (lottieAnimationView3 != null && i2 == 0) {
            lottieAnimationView3.cancelAnimation();
            this.e0.clearAnimation();
            this.e0.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView4 = this.g0;
        if (lottieAnimationView4 == null || i3 != 0) {
            return;
        }
        lottieAnimationView4.cancelAnimation();
        this.g0.clearAnimation();
        this.g0.setVisibility(8);
    }

    public final void a1(boolean z) {
        b.a.a.h.e eVar = this.i0;
        if (eVar != null) {
            eVar.o(z);
        }
    }

    public final void b1(boolean z) {
        b.a.a.h.e eVar = this.h0;
        if (eVar != null) {
            eVar.o(z);
        }
    }

    @Override // b.a.a.d.b
    public synchronized void c(int i, boolean z) {
        BydLogHelper.logD("updateAcWindMode isRevert: " + z + " == mode: " + i);
        this.S = i;
        switch (i) {
            case -1:
            case 0:
                this.T = 0;
                this.U = 0;
                this.V = 0;
                this.w.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.x.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.v.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                a1(false);
                b1(false);
                c1(false);
                this.a0.setImageResource(R.mipmap.img_dark_air_up_wind_off);
                this.d0.setImageResource(R.mipmap.img_dark_air_middle_wind_off);
                this.f0.setImageResource(R.mipmap.img_dark_air_down_wind_off);
                Z0(this.V, this.T, this.U);
                break;
            case 1:
                this.T = 1;
                this.U = 0;
                this.V = 0;
                this.w.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.x.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.v.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                if (1 == this.F) {
                    j1();
                    h1(this.V, this.T, this.U);
                } else {
                    b1(false);
                }
                a1(false);
                c1(false);
                this.a0.setImageResource(R.mipmap.img_dark_air_up_wind_off);
                this.f0.setImageResource(R.mipmap.img_dark_air_down_wind_off);
                Z0(this.V, this.T, this.U);
                break;
            case 2:
                this.T = 1;
                this.U = 1;
                this.V = 0;
                this.w.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.x.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.v.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                if (1 == this.F) {
                    i1();
                    j1();
                    h1(this.V, this.T, this.U);
                } else {
                    a1(false);
                    b1(false);
                }
                c1(false);
                this.a0.setImageResource(R.mipmap.img_dark_air_up_wind_off);
                Z0(this.V, this.T, this.U);
                break;
            case 3:
                this.U = 1;
                this.T = 0;
                this.V = 0;
                this.x.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.w.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.v.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                if (1 == this.F) {
                    h1(this.V, this.T, this.U);
                    i1();
                } else {
                    a1(false);
                }
                c1(false);
                b1(false);
                this.a0.setImageResource(R.mipmap.img_dark_air_up_wind_off);
                this.d0.setImageResource(R.mipmap.img_dark_air_middle_wind_off);
                Z0(this.V, this.T, this.U);
                break;
            case 4:
                this.U = 1;
                this.T = 0;
                this.V = 1;
                this.w.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.x.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.v.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                if (1 == this.F) {
                    i1();
                    k1();
                    h1(this.V, this.T, this.U);
                } else {
                    a1(false);
                    c1(false);
                }
                b1(false);
                this.d0.setImageResource(R.mipmap.img_dark_air_middle_wind_off);
                Z0(this.V, this.T, this.U);
                break;
            case 5:
                this.U = 0;
                this.T = 0;
                this.V = 1;
                this.w.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.x.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.v.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                if (1 == this.F) {
                    k1();
                    h1(this.V, this.T, this.U);
                } else {
                    c1(false);
                }
                a1(false);
                b1(false);
                this.d0.setImageResource(R.mipmap.img_dark_air_middle_wind_off);
                this.f0.setImageResource(R.mipmap.img_dark_air_down_wind_off);
                Z0(this.V, this.T, this.U);
                break;
            case 6:
                if (this.k0 != 1) {
                    this.U = 1;
                    this.T = 1;
                    this.V = 1;
                    this.w.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                    this.x.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                    this.v.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                    if (1 == this.F) {
                        i1();
                        j1();
                        k1();
                        h1(this.V, this.T, this.U);
                    } else {
                        a1(false);
                        b1(false);
                        c1(false);
                    }
                    Z0(this.V, this.T, this.U);
                }
                break;
            case 7:
                if (this.k0 != 1) {
                    this.U = 0;
                    this.T = 1;
                    this.V = 1;
                    this.w.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                    this.x.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                    this.v.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                    if (1 == this.F) {
                        j1();
                        k1();
                        h1(this.V, this.T, this.U);
                    } else {
                        b1(false);
                        c1(false);
                    }
                    a1(false);
                    this.f0.setImageResource(R.mipmap.img_dark_air_down_wind_off);
                    Z0(this.V, this.T, this.U);
                }
                break;
        }
    }

    public final void c1(boolean z) {
        b.a.a.h.e eVar = this.c0;
        if (eVar != null) {
            eVar.o(z);
        }
    }

    @Override // b.a.a.d.b
    public void d(int i, int i2) {
        BydLogHelper.logD("updateTemperature area = " + i + " -- value = " + i2);
        SparseArray<String> sparseArray = this.H0;
        String str = sparseArray != null ? sparseArray.get(i2) : null;
        BydLogHelper.logD("updateTemperature tempValue = " + str);
        if (i == 1 && str != null) {
            this.O = i2;
            BydLogHelper.logD("updateTemperature 主驾 mTemperatureUnit = " + this.o0);
            if (i2 == this.H0.keyAt(0) || i2 == this.H0.keyAt(this.X.size() - 1)) {
                this.s.setVisibility(8);
            } else {
                this.s.setVisibility(0);
            }
            if (!this.D0) {
                this.g.setSelected(this.X.indexOf(str));
            }
            int i3 = this.o0;
            if (i3 == 0) {
                this.s.setImageResource(R.mipmap.icon_temperature_unit_f);
                return;
            } else {
                if (i3 == 1) {
                    this.s.setImageResource(R.mipmap.icon_temperature_unit_c);
                    return;
                }
                return;
            }
        }
        if (i != 2 || str == null) {
            BydLogHelper.logE("updateTemperature 温度值不在数据范围内 = ");
            return;
        }
        this.P = i2;
        BydLogHelper.logD("updateTemperature  副驾 mTemperatureUnit = " + this.o0);
        if (i2 == this.H0.keyAt(0) || i2 == this.H0.keyAt(this.X.size() - 1)) {
            this.z.setVisibility(8);
        } else {
            this.z.setVisibility(0);
        }
        if (!this.E0) {
            this.h.setSelected(this.Y.indexOf(str));
        }
        int i4 = this.o0;
        if (i4 == 0) {
            this.z.setImageResource(R.mipmap.icon_temperature_unit_f);
        } else if (i4 == 1) {
            this.z.setImageResource(R.mipmap.icon_temperature_unit_c);
        }
    }

    @Override // b.a.a.d.b
    public void d0(int i, boolean z) {
        BydLogHelper.logD("updateMaxAcCompressorState state = " + i);
        this.I = i;
        if (i == 1) {
            if (z || this.l.isAnimating()) {
                return;
            }
            this.l.c(1);
            return;
        }
        if (i != 0 || z || this.l.isAnimating()) {
            return;
        }
        this.l.c(0);
    }

    public final void d1(int i, int i2, int i3) {
        if (this.R == 1) {
            LottieAnimationView lottieAnimationView = this.Z;
            if (lottieAnimationView != null && !lottieAnimationView.isAnimating()) {
                this.Z.playAnimation();
            }
            LottieAnimationView lottieAnimationView2 = this.b0;
            if (lottieAnimationView2 != null && !lottieAnimationView2.isAnimating() && i == 1) {
                this.b0.playAnimation();
            }
            LottieAnimationView lottieAnimationView3 = this.e0;
            if (lottieAnimationView3 != null && !lottieAnimationView3.isAnimating() && i2 == 1) {
                this.e0.playAnimation();
            }
            LottieAnimationView lottieAnimationView4 = this.g0;
            if (lottieAnimationView4 == null || lottieAnimationView4.isAnimating() || i3 != 1) {
                return;
            }
            this.g0.playAnimation();
        }
    }

    @Override // b.a.a.d.b
    public void e(int i, int i2, boolean z) {
        BydLogHelper.logD("updateAcDefrostState area: " + i + " == state: " + i2);
        if (i == 1) {
            this.K = i2;
            if (i2 == 1) {
                if (z || this.n.isAnimating()) {
                    return;
                }
                this.n.b(1);
                return;
            }
            if (i2 != 0 || z || this.n.isAnimating()) {
                return;
            }
            this.n.b(0);
            return;
        }
        if (i == 2) {
            this.L = i2;
            if (i2 == 1) {
                if (z || this.o.isAnimating()) {
                    return;
                }
                this.o.b(1);
                return;
            }
            if (i2 != 0 || z || this.o.isAnimating()) {
                return;
            }
            this.o.b(0);
        }
    }

    public void e1(boolean z) {
        if (z) {
            l.a(false, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.g, this.u, this.v, this.w, this.x, this.a0, this.d0, this.f0, this.y, this.z, this.A, this.h, this.B, this.C, this.E, this.s0, this.D);
            l.b(false, this.g, this.h, this.D);
        } else {
            l.a(true, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.g, this.u, this.v, this.w, this.x, this.a0, this.d0, this.f0, this.y, this.z, this.A, this.h, this.B, this.C, this.E, this.s0, this.D);
            l.b(true, this.g, this.h, this.D);
            l(this.p0);
        }
    }

    @Override // b.a.a.d.b
    public void f(int i, int i2) {
        BydLogHelper.logD("onHasMessageChanged id:" + i + " --- state:" + i2);
        if (i == 166) {
            l.c(i2 == 1, this.u);
        } else {
            if (i != 207) {
                return;
            }
            l.c(i2 == 1, this.q);
        }
    }

    @SuppressLint({"RestrictedApi"})
    public final void f1(LottieAnimationView lottieAnimationView, boolean z, int i) {
        this.C0 = new KeyPath("**");
        if (lottieAnimationView != null) {
            if (!z) {
                SimpleColorFilter simpleColorFilter = new SimpleColorFilter(this.e.getColor(R.color.text_tem_white));
                this.x0 = simpleColorFilter;
                LottieValueCallback<ColorFilter> lottieValueCallback = new LottieValueCallback<>(simpleColorFilter);
                this.B0 = lottieValueCallback;
                lottieAnimationView.addValueCallback(this.C0, LottieProperty.COLOR_FILTER, lottieValueCallback);
                return;
            }
            if (i == 0) {
                SimpleColorFilter simpleColorFilter2 = new SimpleColorFilter(this.e.getColor(R.color.text_tem_unselect_denza));
                this.u0 = simpleColorFilter2;
                LottieValueCallback<ColorFilter> lottieValueCallback2 = new LottieValueCallback<>(simpleColorFilter2);
                this.y0 = lottieValueCallback2;
                lottieAnimationView.addValueCallback(this.C0, LottieProperty.COLOR_FILTER, lottieValueCallback2);
                return;
            }
            if (i == 1) {
                SimpleColorFilter simpleColorFilter3 = new SimpleColorFilter(this.e.getColor(R.color.btn_open_bg_blue));
                this.u0 = simpleColorFilter3;
                LottieValueCallback<ColorFilter> lottieValueCallback3 = new LottieValueCallback<>(simpleColorFilter3);
                this.y0 = lottieValueCallback3;
                lottieAnimationView.addValueCallback(this.C0, LottieProperty.COLOR_FILTER, lottieValueCallback3);
                return;
            }
            if (i == 2) {
                SimpleColorFilter simpleColorFilter4 = new SimpleColorFilter(this.e.getColor(R.color.btn_open_bg_blue));
                this.v0 = simpleColorFilter4;
                LottieValueCallback<ColorFilter> lottieValueCallback4 = new LottieValueCallback<>(simpleColorFilter4);
                this.z0 = lottieValueCallback4;
                lottieAnimationView.addValueCallback(this.C0, LottieProperty.COLOR_FILTER, lottieValueCallback4);
                return;
            }
            if (i != 3) {
                return;
            }
            SimpleColorFilter simpleColorFilter5 = new SimpleColorFilter(this.e.getColor(R.color.defrost_color_select));
            this.w0 = simpleColorFilter5;
            LottieValueCallback<ColorFilter> lottieValueCallback5 = new LottieValueCallback<>(simpleColorFilter5);
            this.A0 = lottieValueCallback5;
            lottieAnimationView.addValueCallback(this.C0, LottieProperty.COLOR_FILTER, lottieValueCallback5);
        }
    }

    @Override // b.a.a.d.b
    public void g(int i, boolean z) {
        BydLogHelper.logD("updateAcCompressorState start state:" + i + " -- isRevert:" + z);
        this.H = i;
        int i2 = this.r0;
        if (i2 == 2) {
            this.k.setTintButton(ContextCompat.getDrawable(this.e, R.mipmap.icon_ac_cool));
        } else if (i2 == 3) {
            this.k.setTintButton(ContextCompat.getDrawable(this.e, R.mipmap.icon_ac_heat));
        } else {
            this.k.setTintButton(ContextCompat.getDrawable(this.e, R.mipmap.icon_ac));
        }
        int i3 = this.H;
        if (i3 == 1) {
            if (z || this.k.isAnimating()) {
                return;
            }
            this.k.c(1);
            return;
        }
        if (i3 != 0 || z || this.k.isAnimating()) {
            return;
        }
        this.k.c(0);
    }

    @Override // b.a.a.d.b
    public void g0(int i, int i2, boolean z) {
        BydLogHelper.logD("updateAcStartState area: " + i + " == state: " + i2 + " == isRevert: " + z);
        if (i == 1) {
            this.F = i2;
            if (i2 == 1) {
                if (!z && !this.i.isAnimating()) {
                    this.i.c(1);
                }
            } else if (i2 == 0) {
                if (!z && !this.i.isAnimating()) {
                    this.i.c(0);
                }
                Z0(0, 0, 0);
            }
            l1(this.S);
            c(this.S, false);
        }
    }

    public final void g1(final TintLottieAnimationView tintLottieAnimationView, int i, final boolean z, final int i2) {
        if (tintLottieAnimationView != null) {
            LottieCompositionFactory.fromRawRes(this.e, i, null).addListener(new LottieListener() { // from class: b.a.a.i.g.b
                @Override // com.airbnb.lottie.LottieListener
                public final void onResult(Object obj) {
                    this.f269a.W0(tintLottieAnimationView, z, i2, (LottieComposition) obj);
                }
            });
        }
    }

    @Override // b.a.a.d.b
    public void h(int i) {
        BydLogHelper.logD("updateHeatPumpCompressorWorkMode mode:" + i);
        this.r0 = i;
        if (i == 2) {
            this.k.setTintButton(ContextCompat.getDrawable(this.e, R.mipmap.icon_ac_cool));
        } else if (i == 3) {
            this.k.setTintButton(ContextCompat.getDrawable(this.e, R.mipmap.icon_ac_heat));
        } else {
            this.k.setTintButton(ContextCompat.getDrawable(this.e, R.mipmap.icon_ac));
        }
        int i2 = this.H;
        if (i2 == 1) {
            this.k.c(1);
        } else if (i2 == 0) {
            this.k.c(0);
        }
    }

    public final void h1(int i, int i2, int i3) {
        if (this.R == 1) {
            LottieAnimationView lottieAnimationView = this.Z;
            if (lottieAnimationView != null && !lottieAnimationView.isAnimating() && this.n0 == 2) {
                this.Z.playAnimation();
                this.Z.setVisibility(0);
            }
            LottieAnimationView lottieAnimationView2 = this.b0;
            if (lottieAnimationView2 != null && !lottieAnimationView2.isAnimating() && i == 1) {
                this.b0.setVisibility(0);
                this.b0.playAnimation();
            }
            LottieAnimationView lottieAnimationView3 = this.e0;
            if (lottieAnimationView3 != null && !lottieAnimationView3.isAnimating() && i2 == 1) {
                this.e0.setVisibility(0);
                this.e0.playAnimation();
            }
            LottieAnimationView lottieAnimationView4 = this.g0;
            if (lottieAnimationView4 == null || lottieAnimationView4.isAnimating() || i3 != 1) {
                return;
            }
            this.g0.setVisibility(0);
            this.g0.playAnimation();
        }
    }

    @Override // com.byd.hvac.view.datapickview.WheelView.d
    public void i0(WheelView wheelView, int i, String str) {
        BydLogHelper.logD("onSelect index:" + i + " == text：" + str);
        L0();
        int id = wheelView.getId();
        if (id == R.id.left_wheel_view) {
            b.a.a.h.a.h(1034, 1);
        } else {
            if (id != R.id.right_wheel_view) {
                return;
            }
            b.a.a.h.a.h(1035, 2);
        }
    }

    public final void i1() {
        b.a.a.h.e eVar;
        if (this.f0 == null || (eVar = this.i0) == null || eVar.m()) {
            return;
        }
        this.i0.n(0);
        this.f0.setVisibility(0);
    }

    @Override // b.a.a.d.b
    public void j(int i) {
        BydLogHelper.logD("updatePowerLevel level:" + i);
        this.q0 = i;
        e1(i == 1);
    }

    public final void j1() {
        b.a.a.h.e eVar;
        if (this.d0 == null || (eVar = this.h0) == null || eVar.m()) {
            return;
        }
        this.h0.n(0);
        this.d0.setVisibility(0);
    }

    @Override // b.a.a.d.b
    public void k(int i, boolean z) {
        BydLogHelper.logD("updateAcCycleMode mode = " + i);
        this.M = i;
        Drawable drawable = ContextCompat.getDrawable(this.e, M0(i));
        if (z || this.p.isAnimating()) {
            return;
        }
        this.p.setTintButton(drawable);
        if (i == 0 || i == 1) {
            this.p.c(1);
        }
    }

    public final void k1() {
        b.a.a.h.e eVar;
        if (this.a0 == null || (eVar = this.c0) == null || eVar.m()) {
            return;
        }
        this.c0.n(0);
        this.a0.setVisibility(0);
    }

    @Override // b.a.a.d.b
    public void l(int i) {
        BydLogHelper.logD("updateAcOnlineState start state:" + i);
        this.p0 = i;
        z(((e) this.f).n());
        if (this.p0 == 1 && this.q0 != 1) {
            m1();
            l.a(true, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.g, this.u, this.v, this.w, this.x, this.a0, this.d0, this.f0, this.y, this.z, this.A, this.h, this.B, this.C, this.D, this.E, this.s0, this.t0);
            l.b(true, this.g, this.h, this.D);
            f(166, ((e) this.f).M(166));
            f(207, ((e) this.f).M(207));
            return;
        }
        I0(0, this.l, this.m, this.k, this.j, this.p, this.n, this.o, this.i, this.q);
        this.D.setWindLevel(0);
        c(-1, false);
        E(0);
        u(2);
        l.a(false, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.g, this.u, this.v, this.w, this.x, this.a0, this.d0, this.f0, this.y, this.z, this.A, this.h, this.B, this.C, this.D, this.E, this.s0, this.t0);
        l.b(false, this.g, this.h, this.D);
    }

    @Override // b.a.a.d.b
    public void l0(int i, int i2) {
        BydLogHelper.logD("updateAcCtrlMode mode = " + i2);
        if (i == 1) {
            this.G = i2;
            if (i2 == 0) {
                if (this.j.isAnimating()) {
                    return;
                }
                this.j.c(1);
            } else {
                if (i2 != 1 || this.j.isAnimating()) {
                    return;
                }
                this.j.c(0);
            }
        }
    }

    public void l1(int i) {
        switch (i) {
            case 1:
                if (1 == this.F) {
                    this.d0.setImageResource(R.mipmap.animat_ribbon_mid_loop_000);
                }
                break;
            case 2:
                if (1 == this.F) {
                    this.d0.setImageResource(R.mipmap.animat_ribbon_mid_loop_000);
                    this.f0.setImageResource(R.mipmap.animat_ribbon_bottom_loop_000);
                }
                break;
            case 3:
                if (1 == this.F) {
                    this.f0.setImageResource(R.mipmap.animat_ribbon_bottom_loop_000);
                }
                break;
            case 4:
                if (1 == this.F) {
                    this.a0.setImageResource(R.mipmap.animat_ribbon_top_loop_000);
                    this.f0.setImageResource(R.mipmap.animat_ribbon_bottom_loop_000);
                }
                break;
            case 5:
                if (1 == this.F) {
                    this.a0.setImageResource(R.mipmap.animat_ribbon_top_loop_000);
                }
                break;
            case 6:
                if (this.k0 != 1 && 1 == this.F) {
                    this.a0.setImageResource(R.mipmap.animat_ribbon_top_loop_000);
                    this.d0.setImageResource(R.mipmap.animat_ribbon_mid_loop_000);
                    this.f0.setImageResource(R.mipmap.animat_ribbon_bottom_loop_000);
                    break;
                }
                break;
            case 7:
                if (this.k0 != 1 && 1 == this.F) {
                    this.a0.setImageResource(R.mipmap.animat_ribbon_top_loop_000);
                    this.d0.setImageResource(R.mipmap.animat_ribbon_mid_loop_000);
                    break;
                }
                break;
        }
    }

    @Override // b.a.a.d.b
    public void m(int i) {
        BydLogHelper.logD("updateTemperatureUnit unit:" + i);
        this.o0 = i;
        if (i == 0) {
            this.s.setImageResource(R.mipmap.icon_temperature_unit_f);
            this.z.setImageResource(R.mipmap.icon_temperature_unit_f);
        } else if (i == 1) {
            this.s.setImageResource(R.mipmap.icon_temperature_unit_c);
            this.z.setImageResource(R.mipmap.icon_temperature_unit_c);
        }
        R0();
        this.O = ((e) this.f).p(1);
        this.P = ((e) this.f).p(2);
        d(1, this.O);
        d(2, this.P);
    }

    public final void m1() {
        this.F = ((e) this.f).o();
        this.G = ((e) this.f).h();
        this.H = ((e) this.f).g();
        this.I = ((e) this.f).C();
        this.J = ((e) this.f).l();
        this.K = ((e) this.f).j(1);
        this.L = ((e) this.f).j(2);
        this.N = ((e) this.f).s();
        this.Q = ((e) this.f).q();
        this.R = ((e) this.f).D();
        this.S = ((e) this.f).u();
        this.W = ((e) this.f).t();
        h(this.r0);
        S(this.N);
        l0(1, this.G);
        g(this.H, false);
        d0(this.I, false);
        U(this.J, false);
        e(1, this.K, false);
        e(2, this.L, false);
        k(this.M, false);
        E(this.Q);
        n(1, this.W);
        u(this.R);
        m(this.o0);
        g0(1, this.F, false);
    }

    @Override // b.a.a.d.b
    public void n(int i, int i2) {
        BydLogHelper.logD("updateAcWindLevel area: " + i + " == level: " + i2);
        if (i == 1) {
            this.W = i2;
            this.D.setWindLevel(i2);
            H0(i2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00c9  */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:217)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:68)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    @Override // android.view.View.OnClickListener
    @android.annotation.SuppressLint({"NonConstantResourceId"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onClick(android.view.View r9) {
        /*
            Method dump skipped, instruction units count: 1478
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.hvac.view.accontrol.AcFrontControlFragment.onClick(android.view.View):void");
    }

    @Override // com.byd.hvac.base.BaseFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        WheelView wheelView = this.h;
        if (wheelView != null) {
            wheelView.t();
        }
        WheelView wheelView2 = this.g;
        if (wheelView2 != null) {
            wheelView2.t();
        }
        b.a.a.i.i.a aVar = this.j0;
        if (aVar != null) {
            aVar.a();
        }
        Z0(0, 0, 0);
        c1(true);
        b1(true);
        a1(true);
        ArrayList<String> arrayList = this.X;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList<String> arrayList2 = this.Y;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!this.f985a) {
            BydLogHelper.logD("视图未准备好");
            return;
        }
        if (z) {
            c1(true);
            b1(true);
            a1(true);
            X0();
            return;
        }
        T t = this.f;
        if (t == 0 || ((e) t).m() != 1) {
            return;
        }
        d1(this.V, this.T, this.U);
        c(this.S, false);
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onPause() {
        c1(true);
        b1(true);
        a1(true);
        X0();
        super.onPause();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.f == 0 || this.p0 != 1) {
            return;
        }
        d1(this.V, this.T, this.U);
        c(this.S, false);
    }

    @Override // com.byd.hvac.view.datapickview.WheelView.d
    public void q0(WheelView wheelView) {
        int id = wheelView.getId();
        if (id == R.id.left_wheel_view) {
            this.D0 = true;
        } else {
            if (id != R.id.right_wheel_view) {
                return;
            }
            this.E0 = true;
        }
    }

    @Override // b.a.a.d.b
    public void u(int i) {
        this.R = i;
        BydLogHelper.logD("updatePM2p5AnionDetectState mNegativeIonState:" + this.R);
        if (i == 1) {
            this.u.setBackgroundResource(R.mipmap.img_dark_air_anion_on);
            h1(this.V, this.T, this.U);
        } else if (i == 2) {
            this.u.setBackgroundResource(R.mipmap.img_dark_air_anion_off);
            Z0(0, 0, 0);
        }
    }

    @Override // com.byd.hvac.base.BaseFragment
    public int v0() {
        return O0(R.layout.fragment_ac_front_control);
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void x0(View view) {
        this.i = (TintLottieAnimationView) view.findViewById(R.id.ib_air_condition_off);
        this.j = (TintLottieAnimationView) view.findViewById(R.id.ib_air_auto);
        this.k = (TintLottieAnimationView) view.findViewById(R.id.ib_air_ac_snowflake);
        this.l = (TintLottieAnimationView) view.findViewById(R.id.ib_air_ac_snowflake_max);
        this.m = (TintLottieAnimationView) view.findViewById(R.id.ib_air_ac_heat);
        this.n = (TintLottieAnimationView) view.findViewById(R.id.ib_air_before_defrost);
        this.o = (TintLottieAnimationView) view.findViewById(R.id.ib_air_after_defrost);
        this.p = (TintLottieAnimationView) view.findViewById(R.id.ib_air_loop_mode);
        this.q = (TintLottieAnimationView) view.findViewById(R.id.ib_air_ventilation);
        this.r = (ImageButton) view.findViewById(R.id.ib_left_temp_rise);
        this.s = (ImageView) view.findViewById(R.id.tv_left_temp_unit);
        this.t = (ImageButton) view.findViewById(R.id.ib_left_temp_reduce);
        WheelView wheelView = (WheelView) view.findViewById(R.id.left_wheel_view);
        this.g = wheelView;
        wheelView.setIsLoop(false);
        this.u = (ImageButton) view.findViewById(R.id.ib_air_anion_state);
        this.v = (ImageView) view.findViewById(R.id.ib_air_defrost_state);
        this.w = (ImageView) view.findViewById(R.id.ib_air_wind_blow_face);
        this.x = (ImageView) view.findViewById(R.id.ib_air_wind_blow_foot);
        this.y = (ImageButton) view.findViewById(R.id.ib_right_temp_rise);
        this.z = (ImageView) view.findViewById(R.id.tv_right_temp_unit);
        this.A = (ImageButton) view.findViewById(R.id.ib_right_temp_reduce);
        WheelView wheelView2 = (WheelView) view.findViewById(R.id.right_wheel_view);
        this.h = wheelView2;
        wheelView2.setIsLoop(false);
        this.B = (TintImageView) view.findViewById(R.id.ib_air_synchronous_state);
        this.C = (LottieAnimationView) view.findViewById(R.id.ib_air_level_small);
        WindLevelButton windLevelButton = (WindLevelButton) view.findViewById(R.id.sb_air_wind_level);
        this.D = windLevelButton;
        windLevelButton.setWindLevelMax(7);
        this.D.setWindLevel(0);
        this.D.setMin(1);
        this.E = (LottieAnimationView) view.findViewById(R.id.ib_air_level_big);
        this.s0 = (ImageView) view.findViewById(R.id.iv_air_people);
        this.t0 = (TextView) view.findViewById(R.id.tv_tips_start_vehicle);
        this.Z = (LottieAnimationView) view.findViewById(R.id.iv_anion_animation);
        this.a0 = (ImageView) view.findViewById(R.id.iv_top_ribbon_nothing_to_exit);
        this.b0 = (LottieAnimationView) view.findViewById(R.id.iv_top_particle_nothing_to_exit);
        this.d0 = (ImageView) view.findViewById(R.id.iv_mid_ribbon_nothing_to_exit);
        this.e0 = (LottieAnimationView) view.findViewById(R.id.iv_mid_particle_nothing_to_exit);
        this.f0 = (ImageView) view.findViewById(R.id.iv_bottom_ribbon_nothing_to_exit);
        this.g0 = (LottieAnimationView) view.findViewById(R.id.iv_bottom_particle_nothing_to_exit);
        A0();
    }

    @Override // com.byd.hvac.view.datapickview.WheelView.d
    public void y(WheelView wheelView, int i) {
    }

    @Override // b.a.a.d.b
    public void z(int i) {
        if (((e) this.f).N()) {
            return;
        }
        this.t0.setVisibility(i == 1 ? 0 : 8);
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void z0() {
        super.z0();
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.B.setOnClickListener(this);
        this.C.setOnClickListener(this);
        this.E.setOnClickListener(this);
        this.a0.setOnClickListener(this);
        this.b0.setOnClickListener(this);
        this.d0.setOnClickListener(this);
        this.e0.setOnClickListener(this);
        this.f0.setOnClickListener(this);
        this.g0.setOnClickListener(this);
        this.D.setOnWindLevelViewChangeListener(new a());
        this.h.setOnSelectListener(this);
        this.g.setOnSelectListener(this);
    }
}
