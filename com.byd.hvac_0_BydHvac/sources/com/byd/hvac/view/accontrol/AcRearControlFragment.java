package com.byd.hvac.view.accontrol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.a.a.g.f;
import b.a.a.h.e;
import b.a.a.h.g;
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
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class AcRearControlFragment extends BaseFragment<f> implements b.a.a.d.c, View.OnClickListener, WheelView.d {
    public int A;
    public ArrayList<String> B;
    public ArrayList<String> C;
    public ImageView D;
    public ImageView E;
    public e F;
    public e G;
    public int H;
    public b.a.a.i.i.a I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public ConstraintLayout Q;
    public ConstraintLayout R;
    public int S;
    public ImageView T;
    public ConstraintLayout U;
    public ImageView V;
    public SimpleColorFilter W;
    public SimpleColorFilter X;
    public SimpleColorFilter Y;
    public LottieValueCallback<ColorFilter> Z;
    public LottieValueCallback<ColorFilter> a0;
    public LottieValueCallback<ColorFilter> b0;
    public KeyPath c0;
    public boolean d0;
    public boolean e0;
    public WheelView g;
    public WheelView h;
    public LottieAnimationView i;
    public LottieAnimationView j;
    public TintImageView k;
    public ImageButton l;
    public ImageView m;
    public ImageButton n;
    public ImageView o;
    public ImageView p;
    public ImageButton q;
    public ImageView r;
    public ImageButton s;
    public LottieAnimationView t;
    public WindLevelButton u;
    public LottieAnimationView v;
    public int w;
    public int x;
    public int y;
    public int z;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements WindLevelButton.a {
        public a() {
        }

        @Override // com.byd.hvac.view.WindLevelButton.a
        public void a(int i) {
            BydLogHelper.logD("onChanged progress:" + i);
            if (AcRearControlFragment.this.isResumed() && AcRearControlFragment.this.H0()) {
                return;
            }
            m.b(AcRearControlFragment.this.u);
        }

        @Override // com.byd.hvac.view.WindLevelButton.a
        public void b(int i) {
            BydLogHelper.logD("onTouchDown progress:" + i);
            m.b(AcRearControlFragment.this.u);
        }

        @Override // com.byd.hvac.view.WindLevelButton.a
        public void c(int i) {
            BydLogHelper.logD("onTouchUp progress:" + i);
            if (i != AcRearControlFragment.this.z) {
                ((f) AcRearControlFragment.this.f).C(i);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class b implements AcStartVehicleTipsLayout.b {
        public b() {
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void a() {
            AcRearControlFragment.this.I.a();
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void b() {
            AcRearControlFragment.this.I.a();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class c implements AcInnerDisplayModeTipsLayout.b {
        public c() {
        }

        @Override // com.byd.hvac.view.dialog.AcInnerDisplayModeTipsLayout.b
        public void a() {
            AcRearControlFragment.this.I.a();
        }

        @Override // com.byd.hvac.view.dialog.AcInnerDisplayModeTipsLayout.b
        public void b() {
            AcRearControlFragment.this.I.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: M0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void N0(LottieAnimationView lottieAnimationView, boolean z, LottieComposition lottieComposition) {
        lottieAnimationView.setComposition(lottieComposition);
        T0(lottieAnimationView, z, 1);
        lottieAnimationView.playAnimation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: O0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void P0(LottieAnimationView lottieAnimationView, int i, boolean z, LottieComposition lottieComposition) {
        lottieAnimationView.setComposition(lottieComposition);
        if (i == 0) {
            T0(lottieAnimationView, z, 0);
            return;
        }
        if (i == 1) {
            T0(lottieAnimationView, z, 1);
            lottieAnimationView.setProgress(0.0f);
        } else {
            if (i != 2) {
                return;
            }
            T0(lottieAnimationView, z, 2);
        }
    }

    @Override // b.a.a.d.c
    public void A(int i) {
        BydLogHelper.logD("updateAcWindLevel level = " + i);
        this.z = i;
        if (((f) this.f).y(185) == 1) {
            this.u.setWindLevel(this.z);
        }
        G0(i);
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void A0() {
        K0();
        this.w = ((f) this.f).h();
        this.x = ((f) this.f).g();
        this.y = ((f) this.f).m();
        this.A = ((f) this.f).i(3);
        this.z = ((f) this.f).j();
        this.H = ((f) this.f).k();
        this.P = ((f) this.f).x();
        this.L = ((f) this.f).r();
        this.J = ((f) this.f).n();
        this.M = ((f) this.f).u();
        if (this.J != 2) {
            this.u.setWindLevelMax(4);
            this.u.setWindLevel(0);
            this.u.setMin(1);
            this.j.setVisibility(8);
            this.Q.setVisibility(4);
            this.m.setVisibility(4);
            this.Q.setEnabled(false);
            this.R.setVisibility(4);
            this.r.setVisibility(4);
            this.R.setEnabled(false);
        } else {
            this.u.setWindLevelMax(7);
            this.u.setWindLevel(0);
            this.u.setMin(1);
            if (this.M != 2) {
                this.Q.setVisibility(4);
                this.m.setVisibility(4);
                this.Q.setEnabled(false);
                this.R.setVisibility(4);
                this.r.setVisibility(4);
                this.R.setEnabled(false);
            }
            if (this.L != 2) {
                this.j.setVisibility(8);
            }
        }
        int iT = ((f) this.f).t();
        this.K = iT;
        if (iT != 2) {
            this.i.setVisibility(8);
        }
        int iW = ((f) this.f).w();
        this.S = iW;
        if (iW == 2) {
            this.U.setVisibility(0);
            this.T.setVisibility(8);
        } else {
            this.U.setVisibility(8);
            this.T.setVisibility(0);
        }
        int iV = ((f) this.f).v();
        this.N = iV;
        if (iV != 2) {
            this.u.setEnabled(false);
            this.t.setEnabled(false);
            this.v.setEnabled(false);
        }
        int iS = ((f) this.f).s();
        this.O = iS;
        if (iS != 2) {
            this.k.setVisibility(8);
        }
        f(185, ((f) this.f).y(185));
        f(207, ((f) this.f).y(207));
        N(this.y);
        BydLogHelper.logD("loadData mDriverTemperature = " + this.A);
        BydLogHelper.logD("loadData " + this.C.indexOf(String.valueOf(this.A)));
    }

    @Override // com.byd.hvac.view.datapickview.WheelView.d
    public void G(WheelView wheelView) {
        int id = wheelView.getId();
        if (id == R.id.left_wheel_view) {
            this.d0 = false;
        } else {
            if (id != R.id.right_wheel_view) {
                return;
            }
            this.e0 = false;
        }
    }

    public final void G0(int i) {
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
        e eVar = this.F;
        if (eVar != null) {
            eVar.p(i2);
        }
        e eVar2 = this.G;
        if (eVar2 != null) {
            eVar2.p(i2);
        }
    }

    public final boolean H0() {
        if (((f) this.f).l() != 1) {
            return false;
        }
        if (this.I == null) {
            this.I = new b.a.a.i.i.a();
        }
        if (this.I.b()) {
            this.I.a();
        }
        AcStartVehicleTipsLayout acStartVehicleTipsLayout = (AcStartVehicleTipsLayout) LayoutInflater.from(this.e).inflate(R.layout.dialog_ac_start_vehicle_tips, (ViewGroup) null);
        acStartVehicleTipsLayout.setBtnCallBackListener(new b());
        this.I.c((Activity) this.e, acStartVehicleTipsLayout, (int) this.e.getResources().getDimension(R.dimen.dp_560), (int) this.e.getResources().getDimension(R.dimen.dp_268));
        return true;
    }

    public final boolean I0() {
        if (((f) this.f).o() != 1) {
            return false;
        }
        if (this.I == null) {
            this.I = new b.a.a.i.i.a();
        }
        if (this.I.b()) {
            this.I.a();
        }
        AcInnerDisplayModeTipsLayout acInnerDisplayModeTipsLayout = (AcInnerDisplayModeTipsLayout) LayoutInflater.from(this.e).inflate(R.layout.dialog_ac_inner_display_mode_tips, (ViewGroup) null);
        acInnerDisplayModeTipsLayout.setBtnCallBackListener(new c());
        this.I.c((Activity) this.e, acInnerDisplayModeTipsLayout, (int) this.e.getResources().getDimension(R.dimen.dp_560), (int) this.e.getResources().getDimension(R.dimen.dp_264));
        return true;
    }

    public final int[] J0(int i) {
        TypedArray typedArrayObtainTypedArray = getResources().obtainTypedArray(i);
        int length = typedArrayObtainTypedArray.length();
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = typedArrayObtainTypedArray.getResourceId(i2, -1);
        }
        typedArrayObtainTypedArray.recycle();
        return iArr;
    }

    public final void K0() {
        this.F = new e(getActivity(), this.D, J0(R.array.midRibbonNothingToExit), 50, true);
        this.G = new e(getActivity(), this.E, J0(R.array.bottomRibbonNothingToExit), 50, true);
        this.i.setAnimation(g.a(getActivity()) ? R.raw.icon_ac_off : R.raw.icon_ac_off_e);
        this.j.setAnimation(g.a(getActivity()) ? R.raw.icon_ac_auto : R.raw.icon_ac_auto_e);
        T0(this.i, false, 1);
        T0(this.j, false, 1);
        U0(R.raw.icon_minimum_air_volume_60, this.t, false, 1);
        U0(R.raw.icon_maximum_air_volume_60, this.v, false, 1);
    }

    public final void L0() {
        this.P = ((f) this.f).x();
        if (this.C == null) {
            this.C = new ArrayList<>();
        }
        this.C.clear();
        this.C.add("Hi");
        if (this.P == 0) {
            for (int i = 25; i >= 0; i += -1) {
                this.C.add((i + 65) + "");
            }
        } else {
            for (int i2 = 14; i2 >= 0; i2 += -1) {
                this.C.add((i2 + 18) + "");
            }
        }
        this.C.add("Lo");
        if (this.B == null) {
            this.B = new ArrayList<>();
        }
        this.B.clear();
        this.B.add("Hi");
        if (this.P == 0) {
            for (int i3 = 25; i3 >= 0; i3 += -1) {
                this.B.add((i3 + 65) + "");
            }
        } else {
            for (int i4 = 14; i4 >= 0; i4 += -1) {
                this.B.add((i4 + 18) + "");
            }
        }
        this.B.add("Lo");
    }

    @Override // b.a.a.d.c
    public void N(int i) {
        BydLogHelper.logD("updateRearAcKeyLockState state = " + i);
        this.y = i;
        if (i == 1) {
            this.k.a(1);
        } else if (i == 2) {
            this.k.a(0);
        }
    }

    public final void Q0(int i, final LottieAnimationView lottieAnimationView, final boolean z) {
        LottieCompositionFactory.fromRawRes(this.e, i).addListener(new LottieListener() { // from class: b.a.a.i.g.d
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                this.f275a.N0(lottieAnimationView, z, (LottieComposition) obj);
            }
        });
    }

    public final void R0(boolean z) {
        e eVar = this.G;
        if (eVar != null) {
            eVar.o(z);
        }
    }

    public final void S0(boolean z) {
        e eVar = this.F;
        if (eVar != null) {
            eVar.o(z);
        }
    }

    public final void T0(LottieAnimationView lottieAnimationView, boolean z, int i) {
        this.c0 = new KeyPath("**");
        if (lottieAnimationView != null) {
            if (!z) {
                SimpleColorFilter simpleColorFilter = new SimpleColorFilter(this.e.getColor(R.color.text_tem_white));
                this.Y = simpleColorFilter;
                LottieValueCallback<ColorFilter> lottieValueCallback = new LottieValueCallback<>(simpleColorFilter);
                this.b0 = lottieValueCallback;
                lottieAnimationView.addValueCallback(this.c0, LottieProperty.COLOR_FILTER, lottieValueCallback);
                return;
            }
            if (i == 0) {
                SimpleColorFilter simpleColorFilter2 = new SimpleColorFilter(this.e.getColor(R.color.text_tem_unselect_denza));
                this.Y = simpleColorFilter2;
                LottieValueCallback<ColorFilter> lottieValueCallback2 = new LottieValueCallback<>(simpleColorFilter2);
                this.b0 = lottieValueCallback2;
                lottieAnimationView.addValueCallback(this.c0, LottieProperty.COLOR_FILTER, lottieValueCallback2);
                return;
            }
            if (i == 1) {
                SimpleColorFilter simpleColorFilter3 = new SimpleColorFilter(this.e.getColor(R.color.text_tem_select));
                this.W = simpleColorFilter3;
                LottieValueCallback<ColorFilter> lottieValueCallback3 = new LottieValueCallback<>(simpleColorFilter3);
                this.Z = lottieValueCallback3;
                lottieAnimationView.addValueCallback(this.c0, LottieProperty.COLOR_FILTER, lottieValueCallback3);
                return;
            }
            if (i != 2) {
                return;
            }
            SimpleColorFilter simpleColorFilter4 = new SimpleColorFilter(this.e.getColor(R.color.btn_open_bg_blue));
            this.X = simpleColorFilter4;
            LottieValueCallback<ColorFilter> lottieValueCallback4 = new LottieValueCallback<>(simpleColorFilter4);
            this.a0 = lottieValueCallback4;
            lottieAnimationView.addValueCallback(this.c0, LottieProperty.COLOR_FILTER, lottieValueCallback4);
        }
    }

    public final void U0(int i, final LottieAnimationView lottieAnimationView, final boolean z, final int i2) {
        LottieCompositionFactory.fromRawRes(this.e, i).addListener(new LottieListener() { // from class: b.a.a.i.g.e
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                this.f278a.P0(lottieAnimationView, i2, z, (LottieComposition) obj);
            }
        });
    }

    @Override // com.byd.hvac.view.datapickview.WheelView.d
    public void V(WheelView wheelView, int i, String str) {
        BydLogHelper.logD("onChange index:" + i);
        if (H0()) {
            return;
        }
        m.b(wheelView);
        int i2 = 25;
        if (!TextUtils.isEmpty(str)) {
            if (str.equals("Lo")) {
                i2 = this.P == 0 ? 64 : 17;
                if (wheelView.getId() == R.id.left_wheel_view) {
                    this.m.setVisibility(8);
                } else if (wheelView.getId() == R.id.right_wheel_view) {
                    this.r.setVisibility(8);
                }
            } else if (str.equals("Hi")) {
                i2 = this.P == 0 ? 91 : 33;
                if (wheelView.getId() == R.id.left_wheel_view) {
                    this.m.setVisibility(8);
                } else if (wheelView.getId() == R.id.right_wheel_view) {
                    this.r.setVisibility(8);
                }
            } else {
                i2 = Integer.parseInt(str);
                if (wheelView.getId() == R.id.left_wheel_view) {
                    this.m.setVisibility(0);
                } else if (wheelView.getId() == R.id.right_wheel_view) {
                    this.r.setVisibility(0);
                }
            }
        }
        int id = wheelView.getId();
        if (id == R.id.left_wheel_view || id == R.id.right_wheel_view) {
            ((f) this.f).B(3, i2);
        }
    }

    public final void V0() {
        BydLogHelper.logD("startAnimation " + this.G.m());
        e eVar = this.G;
        if (eVar == null || eVar.m()) {
            return;
        }
        this.G.n(0);
        this.E.setVisibility(0);
    }

    public final void W0() {
        e eVar = this.F;
        if (eVar == null || eVar.m()) {
            return;
        }
        this.F.n(0);
        this.D.setVisibility(0);
    }

    @Override // b.a.a.d.c
    public void c(int i, boolean z) {
        BydLogHelper.logD("updateAcWindMode  mode: " + i);
        this.H = i;
        switch (i) {
            case -1:
            case 0:
            case 5:
                this.o.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.p.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                R0(false);
                S0(false);
                this.D.setImageResource(R.mipmap.img_dark_air_middle_wind_off);
                this.E.setImageResource(R.mipmap.img_dark_air_down_wind_off);
                break;
            case 1:
                this.o.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.p.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                if (1 == this.w) {
                    W0();
                } else {
                    S0(false);
                }
                R0(false);
                this.E.setImageResource(R.mipmap.img_dark_air_down_wind_off);
                break;
            case 2:
            case 6:
                this.o.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.p.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                if (1 != this.w) {
                    R0(false);
                    S0(false);
                } else {
                    V0();
                    W0();
                }
                break;
            case 3:
            case 4:
                this.p.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.o.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                if (1 == this.w) {
                    V0();
                } else {
                    R0(false);
                }
                S0(false);
                this.D.setImageResource(R.mipmap.img_dark_air_middle_wind_off);
                break;
        }
    }

    @Override // b.a.a.d.c
    public void c0(int i) {
        BydLogHelper.logD("updateAcCtrlMode mode = " + i);
        this.x = i;
        int i2 = R.raw.icon_ac_auto;
        if (i == 0) {
            if (this.j.isAnimating()) {
                return;
            }
            if (!g.a(getActivity())) {
                i2 = R.raw.icon_ac_auto_e;
            }
            U0(i2, this.j, true, 1);
            return;
        }
        if (i != 1 || this.j.isAnimating()) {
            return;
        }
        if (!g.a(getActivity())) {
            i2 = R.raw.icon_ac_auto_e;
        }
        U0(i2, this.j, false, 0);
    }

    @Override // b.a.a.d.c
    public void d(int i, int i2) {
        BydLogHelper.logD("updateTemperature area = " + i + " ==  value:" + i2);
        if (this.M != 2) {
            BydLogHelper.logE("updateTemperature do not support RearAcTempertureControl !!");
            return;
        }
        int iX = ((f) this.f).x();
        this.P = iX;
        if (iX == 1 && (i2 > 33 || i2 < 17)) {
            BydLogHelper.logE("updateTemperature 温度单位（摄氏度）与温度值" + i2 + "不匹配 = ");
            return;
        }
        if (iX == 0 && (i2 > 91 || i2 < 64)) {
            BydLogHelper.logE("updateTemperature 温度单位（华氏）与温度值" + i2 + "不匹配 = ");
            return;
        }
        L0();
        String strValueOf = (i2 == 91 || i2 == 33) ? "Hi" : (i2 == 64 || i2 == 17) ? "Lo" : String.valueOf(i2);
        if (i != 3 || !this.C.contains(strValueOf)) {
            BydLogHelper.logE("updateTemperature 温度值不在数据范围内 = ");
            return;
        }
        this.A = i2;
        if (!this.d0) {
            this.g.setItems(this.C);
            int i3 = this.P;
            if (i3 == 0) {
                this.m.setImageResource(R.mipmap.icon_temperature_unit_f);
                if (i2 == 91) {
                    this.g.setSelected(0);
                    this.m.setVisibility(8);
                } else if (i2 == 64) {
                    this.g.setSelected(this.C.size() - 1);
                    this.m.setVisibility(8);
                } else {
                    this.g.setSelected(this.C.indexOf(String.valueOf(i2)));
                    this.m.setVisibility(0);
                }
            } else if (i3 == 1) {
                this.m.setImageResource(R.mipmap.icon_temperature_unit_c);
                if (i2 == 33) {
                    this.g.setSelected(0);
                    this.m.setVisibility(8);
                } else if (i2 == 17) {
                    this.g.setSelected(this.C.size() - 1);
                    this.m.setVisibility(8);
                } else {
                    this.g.setSelected(this.C.indexOf(String.valueOf(i2)));
                    this.m.setVisibility(0);
                }
            }
        }
        if (this.e0) {
            return;
        }
        this.h.setItems(this.B);
        int i4 = this.P;
        if (i4 == 0) {
            this.r.setImageResource(R.mipmap.icon_temperature_unit_f);
            if (i2 == 91) {
                this.h.setSelected(0);
                this.r.setVisibility(8);
                return;
            } else if (i2 == 64) {
                this.h.setSelected(this.C.size() - 1);
                this.r.setVisibility(8);
                return;
            } else {
                this.h.setSelected(this.C.indexOf(String.valueOf(i2)));
                this.r.setVisibility(0);
                return;
            }
        }
        if (i4 == 1) {
            this.r.setImageResource(R.mipmap.icon_temperature_unit_c);
            if (i2 == 33) {
                this.h.setSelected(0);
                this.r.setVisibility(8);
            } else if (i2 == 17) {
                this.h.setSelected(this.C.size() - 1);
                this.r.setVisibility(8);
            } else {
                this.h.setSelected(this.C.indexOf(String.valueOf(i2)));
                this.r.setVisibility(0);
            }
        }
    }

    @Override // b.a.a.d.c
    public void f(int i, int i2) {
        BydLogHelper.logD("onHasMessageChanged id = " + i + " -- state = " + i2);
        if (i != 185) {
            if (i != 207) {
                return;
            }
            l.c(i2 == 1, this.k);
        } else {
            if (i2 != 1) {
                this.u.setWindLevel(0);
                l.c(false, this.g, this.h, this.i, this.j, this.l, this.m, this.n, this.o, this.p, this.D, this.E, this.q, this.r, this.s, this.t, this.u, this.v, this.V);
                T0(this.j, false, 0);
                T0(this.i, false, 0);
                c(-1, false);
                return;
            }
            m(this.P);
            c0(this.x);
            A(this.z);
            c(this.H, false);
            o(this.w, false);
            l.c(true, this.g, this.h, this.i, this.j, this.l, this.m, this.n, this.o, this.p, this.D, this.E, this.q, this.r, this.s, this.t, this.u, this.v, this.V);
        }
    }

    @Override // com.byd.hvac.view.datapickview.WheelView.d
    public void i0(WheelView wheelView, int i, String str) {
        BydLogHelper.logD("onSelect index=" + i + " -- text=" + str);
        I0();
        int id = wheelView.getId();
        if (id == R.id.left_wheel_view || id == R.id.right_wheel_view) {
            b.a.a.h.a.h(1036, 3);
        }
    }

    @Override // b.a.a.d.c
    public void m(int i) {
        BydLogHelper.logD("updateTemperatureUnit unit = " + i);
        this.P = i;
        if (i == 0) {
            this.m.setImageResource(R.mipmap.icon_temperature_unit_f);
            this.r.setImageResource(R.mipmap.icon_temperature_unit_f);
        } else if (i == 1) {
            this.m.setImageResource(R.mipmap.icon_temperature_unit_c);
            this.r.setImageResource(R.mipmap.icon_temperature_unit_c);
        }
        int i2 = ((f) this.f).i(3);
        this.A = i2;
        d(3, i2);
    }

    @Override // b.a.a.d.c
    public void o(int i, boolean z) {
        BydLogHelper.logD("updateAcStartState state = " + i + " -- isRevert:" + z);
        this.w = i;
        if (i == 1) {
            if (!z && !this.i.isAnimating()) {
                U0(g.a(getActivity()) ? R.raw.icon_ac_on : R.raw.icon_ac_on_e, this.i, true, 1);
            }
        } else if (i == 0 && !z && !this.i.isAnimating()) {
            U0(g.a(getActivity()) ? R.raw.icon_ac_off : R.raw.icon_ac_off_e, this.i, false, 1);
        }
        c(((f) this.f).k(), false);
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        if (k.b() || H0()) {
            return;
        }
        switch (view.getId()) {
            case R.id.ib_air_auto /* 2131296502 */:
                I0();
                int i = this.x;
                int i2 = R.raw.icon_ac_auto;
                if (i != 0) {
                    ((f) this.f).z(0);
                    if (!g.a(getActivity())) {
                        i2 = R.raw.icon_ac_auto_e;
                    }
                    Q0(i2, this.j, true);
                } else if (((f) this.f).q() == 1) {
                    ((f) this.f).z(1);
                    if (!g.a(getActivity())) {
                        i2 = R.raw.icon_ac_auto_e;
                    }
                    Q0(i2, this.j, false);
                }
                break;
            case R.id.ib_air_condition_off /* 2131296504 */:
                if (1 != this.w) {
                    ((f) this.f).A(1);
                    Q0(g.a(getActivity()) ? R.raw.icon_ac_on : R.raw.icon_ac_on_e, this.i, true);
                } else {
                    ((f) this.f).A(0);
                    Q0(g.a(getActivity()) ? R.raw.icon_ac_off : R.raw.icon_ac_off_e, this.i, false);
                }
                break;
            case R.id.ib_air_level_big /* 2131296506 */:
                if (this.J != 2) {
                    if (this.z < 4) {
                        ((f) this.f).C(4);
                    }
                } else if (this.z < 7) {
                    ((f) this.f).C(7);
                }
                this.v.playAnimation();
                break;
            case R.id.ib_air_level_small /* 2131296507 */:
                ((f) this.f).C(1);
                this.t.playAnimation();
                break;
            case R.id.ib_air_wind_blow_face /* 2131296511 */:
            case R.id.iv_mid_ribbon_nothing_to_exit /* 2131296572 */:
                int i3 = this.H;
                if (i3 != 0) {
                    switch (i3) {
                        case 2:
                            ((f) this.f).D(3);
                            break;
                        case 3:
                            ((f) this.f).D(2);
                            break;
                        case 4:
                            ((f) this.f).D(6);
                            break;
                        case 5:
                            ((f) this.f).D(7);
                            break;
                        case 6:
                            ((f) this.f).D(4);
                            break;
                        case 7:
                            ((f) this.f).D(5);
                            break;
                    }
                } else {
                    ((f) this.f).D(1);
                    break;
                }
                break;
            case R.id.ib_air_wind_blow_foot /* 2131296512 */:
            case R.id.iv_bottom_ribbon_nothing_to_exit /* 2131296538 */:
                int i4 = this.H;
                if (i4 == 0) {
                    ((f) this.f).D(3);
                    break;
                } else if (i4 == 1) {
                    ((f) this.f).D(2);
                    break;
                } else if (i4 == 2) {
                    ((f) this.f).D(1);
                    break;
                } else if (i4 == 4) {
                    ((f) this.f).D(5);
                    break;
                } else if (i4 == 5) {
                    ((f) this.f).D(4);
                    break;
                } else if (i4 == 6) {
                    ((f) this.f).D(7);
                    break;
                } else if (i4 == 7) {
                    ((f) this.f).D(6);
                    break;
                }
                break;
            case R.id.ib_left_temp_reduce /* 2131296514 */:
            case R.id.ib_right_temp_reduce /* 2131296520 */:
                I0();
                if (this.P != 0) {
                    int i5 = this.A;
                    if (i5 > 17) {
                        ((f) this.f).B(3, i5 - 1);
                    }
                } else {
                    int i6 = this.A;
                    if (i6 > 64) {
                        ((f) this.f).B(3, i6 - 1);
                    }
                }
                break;
            case R.id.ib_left_temp_rise /* 2131296515 */:
            case R.id.ib_right_temp_rise /* 2131296521 */:
                I0();
                if (this.P != 0) {
                    int i7 = this.A;
                    if (i7 < 33) {
                        ((f) this.f).B(3, i7 + 1);
                    }
                } else {
                    int i8 = this.A;
                    if (i8 < 91) {
                        ((f) this.f).B(3, i8 + 1);
                    }
                }
                break;
            case R.id.tv_ac_rear_button_lock /* 2131296899 */:
                if (1 != this.y) {
                    ((f) this.f).E(1);
                } else {
                    ((f) this.f).E(2);
                }
                break;
        }
    }

    @Override // com.byd.hvac.base.BaseFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        b.a.a.i.i.a aVar = this.I;
        if (aVar != null) {
            aVar.a();
        }
        S0(true);
        R0(true);
        WheelView wheelView = this.g;
        if (wheelView != null) {
            wheelView.t();
        }
        WheelView wheelView2 = this.h;
        if (wheelView2 != null) {
            wheelView2.t();
        }
        ArrayList<String> arrayList = this.C;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList<String> arrayList2 = this.B;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        BydLogHelper.logD("onHiddenChanged = " + z);
        if (!this.f985a) {
            BydLogHelper.logD("视图未准备好");
            return;
        }
        if (z) {
            S0(true);
            R0(true);
            return;
        }
        T t = this.f;
        if (t == 0 || ((f) t).y(185) != 1) {
            return;
        }
        c(((f) this.f).k(), false);
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        S0(true);
        R0(true);
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        T t = this.f;
        if (t == 0 || ((f) t).y(185) != 1) {
            return;
        }
        c(((f) this.f).k(), false);
    }

    @Override // com.byd.hvac.view.datapickview.WheelView.d
    public void q0(WheelView wheelView) {
        int id = wheelView.getId();
        if (id == R.id.left_wheel_view) {
            this.d0 = true;
        } else {
            if (id != R.id.right_wheel_view) {
                return;
            }
            this.e0 = true;
        }
    }

    @Override // com.byd.hvac.base.BaseFragment
    public int v0() {
        return ((f) this.f).p() == 1 ? R.layout.fragment_ac_rear_control_right_rudder : R.layout.fragment_ac_rear_control;
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void x0(View view) {
        this.T = (ImageView) view.findViewById(R.id.iv_no_rear_ac_wind_mode);
        this.U = (ConstraintLayout) view.findViewById(R.id.cl_rear_ventilation_state_container);
        this.i = (LottieAnimationView) view.findViewById(R.id.ib_air_condition_off);
        this.j = (LottieAnimationView) view.findViewById(R.id.ib_air_auto);
        this.k = (TintImageView) view.findViewById(R.id.tv_ac_rear_button_lock);
        this.l = (ImageButton) view.findViewById(R.id.ib_left_temp_rise);
        this.m = (ImageView) view.findViewById(R.id.tv_left_temp_unit);
        this.n = (ImageButton) view.findViewById(R.id.ib_left_temp_reduce);
        WheelView wheelView = (WheelView) view.findViewById(R.id.left_wheel_view);
        this.g = wheelView;
        wheelView.setIsLoop(false);
        this.o = (ImageView) view.findViewById(R.id.ib_air_wind_blow_face);
        this.p = (ImageView) view.findViewById(R.id.ib_air_wind_blow_foot);
        this.q = (ImageButton) view.findViewById(R.id.ib_right_temp_rise);
        this.r = (ImageView) view.findViewById(R.id.tv_right_temp_unit);
        this.s = (ImageButton) view.findViewById(R.id.ib_right_temp_reduce);
        WheelView wheelView2 = (WheelView) view.findViewById(R.id.right_wheel_view);
        this.h = wheelView2;
        wheelView2.setIsLoop(false);
        L0();
        this.g.setItems(this.C);
        this.h.setItems(this.B);
        if (this.P == 0) {
            this.g.setSelected(this.C.indexOf(String.valueOf(77)));
            this.h.setSelected(this.B.indexOf(String.valueOf(77)));
        } else {
            this.g.setSelected(this.C.indexOf(String.valueOf(25)));
            this.h.setSelected(this.B.indexOf(String.valueOf(25)));
        }
        this.g.invalidate();
        this.h.invalidate();
        this.t = (LottieAnimationView) view.findViewById(R.id.ib_air_level_small);
        this.u = (WindLevelButton) view.findViewById(R.id.sb_air_wind_level);
        this.v = (LottieAnimationView) view.findViewById(R.id.ib_air_level_big);
        this.Q = (ConstraintLayout) view.findViewById(R.id.cl_left_temp_control);
        this.R = (ConstraintLayout) view.findViewById(R.id.cl_right_temp_control);
        this.V = (ImageView) view.findViewById(R.id.iv_air_people);
        this.D = (ImageView) view.findViewById(R.id.iv_mid_ribbon_nothing_to_exit);
        this.E = (ImageView) view.findViewById(R.id.iv_bottom_ribbon_nothing_to_exit);
        A0();
    }

    @Override // com.byd.hvac.view.datapickview.WheelView.d
    public void y(WheelView wheelView, int i) {
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void z0() {
        super.z0();
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.D.setOnClickListener(this);
        this.E.setOnClickListener(this);
        this.u.setOnWindLevelViewChangeListener(new a());
        this.h.setOnSelectListener(this);
        this.g.setOnSelectListener(this);
    }
}
