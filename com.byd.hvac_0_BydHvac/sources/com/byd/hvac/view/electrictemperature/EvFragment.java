package com.byd.hvac.view.electrictemperature;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import b.a.a.g.g;
import b.a.a.h.e;
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
import com.byd.hvac.view.dialog.AcInnerDisplayModeTipsLayout;
import com.byd.hvac.view.dialog.AcStartVehicleTipsLayout;
import com.byd.hvac.view.verticalseekbar.TemperatureVerticalSeekBar;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class EvFragment extends BaseFragment<g> implements b.a.a.d.d, View.OnClickListener, TemperatureVerticalSeekBar.a {
    public int A;
    public int B;
    public volatile int C;
    public volatile int D;
    public int E;
    public int F;
    public ArrayList<String> G;
    public ArrayList<String> H;
    public ImageView I;
    public ImageView J;
    public ImageView K;
    public e L;
    public e M;
    public e N;
    public b.a.a.i.i.a O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public ImageView T;
    public SimpleColorFilter U;
    public SimpleColorFilter V;
    public SimpleColorFilter W;
    public SimpleColorFilter X;
    public LottieValueCallback<ColorFilter> Y;
    public LottieValueCallback<ColorFilter> Z;
    public LottieValueCallback<ColorFilter> a0;
    public LottieValueCallback<ColorFilter> b0;
    public KeyPath c0;
    public int d0;
    public Handler e0;
    public TemperatureVerticalSeekBar g;
    public TemperatureVerticalSeekBar h;
    public LottieAnimationView i;
    public LottieAnimationView j;
    public LottieAnimationView k;
    public LottieAnimationView l;
    public LottieAnimationView m;
    public ImageButton n;
    public ImageButton o;
    public ImageView p;
    public ImageView q;
    public ImageView r;
    public ImageButton s;
    public ImageButton t;
    public LottieAnimationView u;
    public WindLevelButton v;
    public LottieAnimationView w;
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
            if (EvFragment.this.I0() && EvFragment.this.J0()) {
                return;
            }
            m.b(EvFragment.this.v);
        }

        @Override // com.byd.hvac.view.WindLevelButton.a
        public void b(int i) {
            BydLogHelper.logD("onTouchDown progress:" + i);
            m.b(EvFragment.this.v);
        }

        @Override // com.byd.hvac.view.WindLevelButton.a
        public void c(int i) {
            BydLogHelper.logD("onTouchUp progress:" + i);
            if (i != EvFragment.this.F) {
                ((g) EvFragment.this.f).A(i);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class b implements AcStartVehicleTipsLayout.b {
        public b() {
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void a() {
            EvFragment.this.O.a();
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void b() {
            EvFragment.this.O.a();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class c implements AcStartVehicleTipsLayout.b {
        public c() {
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void a() {
            EvFragment.this.O.a();
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void b() {
            EvFragment.this.O.a();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class d implements AcInnerDisplayModeTipsLayout.b {
        public d() {
        }

        @Override // com.byd.hvac.view.dialog.AcInnerDisplayModeTipsLayout.b
        public void a() {
            EvFragment.this.O.a();
        }

        @Override // com.byd.hvac.view.dialog.AcInnerDisplayModeTipsLayout.b
        public void b() {
            EvFragment.this.O.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: R0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void S0(LottieAnimationView lottieAnimationView, boolean z, int i, LottieComposition lottieComposition) {
        if (lottieAnimationView != null) {
            lottieAnimationView.setComposition(lottieComposition);
            Z0(lottieAnimationView, z, i);
            lottieAnimationView.playAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: T0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void U0(LottieAnimationView lottieAnimationView, int i, boolean z, LottieComposition lottieComposition) {
        lottieAnimationView.setComposition(lottieComposition);
        if (i == 1) {
            Z0(lottieAnimationView, z, 1);
            lottieAnimationView.setProgress(0.0f);
        } else if (i == 2) {
            Z0(lottieAnimationView, z, 2);
        } else {
            if (i != 3) {
                return;
            }
            Z0(lottieAnimationView, z, 3);
        }
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void A0() {
        N0();
        Q0();
        P0();
        int iJ = ((g) this.f).j();
        this.P = iJ;
        l(iJ);
    }

    @Override // com.byd.hvac.view.verticalseekbar.TemperatureVerticalSeekBar.a
    public void F(View view, int i) {
    }

    public final void H0(int i) {
        int i2 = 50;
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
        }
        e eVar = this.L;
        if (eVar != null) {
            eVar.p(i2);
        }
        e eVar2 = this.M;
        if (eVar2 != null) {
            eVar2.p(i2);
        }
        e eVar3 = this.N;
        if (eVar3 != null) {
            eVar3.p(i2);
        }
    }

    public final boolean I0() {
        if (((g) this.f).r() != 1) {
            return false;
        }
        if (this.O == null) {
            this.O = new b.a.a.i.i.a();
        }
        if (this.O.b()) {
            this.O.a();
        }
        AcStartVehicleTipsLayout acStartVehicleTipsLayout = (AcStartVehicleTipsLayout) LayoutInflater.from(this.e).inflate(R.layout.dialog_ac_start_vehicle_tips, (ViewGroup) null);
        acStartVehicleTipsLayout.setBtnCallBackListener(new b());
        this.O.c((Activity) this.e, acStartVehicleTipsLayout, (int) getResources().getDimension(R.dimen.dp_560), (int) getResources().getDimension(R.dimen.dp_268));
        return true;
    }

    public final boolean J0() {
        if (((g) this.f).j() == 1) {
            return false;
        }
        if (this.O == null) {
            this.O = new b.a.a.i.i.a();
        }
        if (this.O.b()) {
            this.O.a();
        }
        AcStartVehicleTipsLayout acStartVehicleTipsLayout = (AcStartVehicleTipsLayout) LayoutInflater.from(this.e).inflate(R.layout.dialog_ac_online_tips, (ViewGroup) null);
        acStartVehicleTipsLayout.setBtnCallBackListener(new c());
        this.O.c((Activity) this.e, acStartVehicleTipsLayout, (int) this.e.getResources().getDimension(R.dimen.dp_560), (int) this.e.getResources().getDimension(R.dimen.dp_312));
        return true;
    }

    public final boolean K0() {
        if (((g) this.f).s() != 1) {
            return false;
        }
        if (this.O == null) {
            this.O = new b.a.a.i.i.a();
        }
        if (this.O.b()) {
            this.O.a();
        }
        AcInnerDisplayModeTipsLayout acInnerDisplayModeTipsLayout = (AcInnerDisplayModeTipsLayout) LayoutInflater.from(this.e).inflate(R.layout.dialog_ac_inner_display_mode_tips, (ViewGroup) null);
        acInnerDisplayModeTipsLayout.setBtnCallBackListener(new d());
        this.O.c((Activity) this.e, acInnerDisplayModeTipsLayout, (int) getResources().getDimension(R.dimen.dp_560), (int) getResources().getDimension(R.dimen.dp_264));
        return true;
    }

    public final int[] L0(int i) {
        TypedArray typedArrayObtainTypedArray = getResources().obtainTypedArray(i);
        int length = typedArrayObtainTypedArray.length();
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = typedArrayObtainTypedArray.getResourceId(i2, -1);
        }
        typedArrayObtainTypedArray.recycle();
        return iArr;
    }

    @Override // com.byd.hvac.view.verticalseekbar.TemperatureVerticalSeekBar.a
    public void M(View view, int i) {
        BydLogHelper.logD("onSlideStopTouch progress:" + i);
        if (I0() || J0()) {
            return;
        }
        K0();
        m.b(view);
        int id = view.getId();
        if (id == R.id.left_wheel_view) {
            ((g) this.f).z(1, Integer.parseInt(this.G.get(i)));
        } else {
            if (id != R.id.right_wheel_view) {
                return;
            }
            ((g) this.f).z(2, Integer.parseInt(this.H.get(i)));
        }
    }

    public final int M0(int i) {
        if (((g) this.f).t() != 1) {
            return i;
        }
        if (i != R.layout.fragment_ev) {
            return 0;
        }
        return R.layout.fragment_ev_right_rudder;
    }

    public final void N0() {
        int iP = ((g) this.f).p();
        this.R = iP;
        if (iP == 0) {
            this.l.setVisibility(8);
        } else {
            a1(b.a.a.h.g.a(getActivity()) ? R.raw.icon_rear_defrosting : R.raw.icon_rear_defrosting_e, this.l, false, 1);
        }
        a1(b.a.a.h.g.a(getActivity()) ? R.raw.icon_ac_off : R.raw.icon_ac_off_e, this.i, false, 1);
        a1(R.raw.icon_ac, this.j, false, 1);
        a1(b.a.a.h.g.a(getActivity()) ? R.raw.icon_front_defrosting : R.raw.icon_front_defrosting_e, this.k, false, 1);
        this.m.setAnimation(R.raw.icon_internal_to_external);
        this.u.setAnimation(R.raw.icon_minimum_air_volume_60);
        this.w.setAnimation(R.raw.icon_maximum_air_volume_60);
    }

    public final void O0() {
        this.L = new e(getActivity(), this.I, L0(R.array.topRibbonNothingToExit), 50, true);
        this.M = new e(getActivity(), this.J, L0(R.array.midRibbonNothingToExit), 50, true);
        this.N = new e(getActivity(), this.K, L0(R.array.bottomRibbonNothingToExit), 50, true);
    }

    public final void P0() {
        if (this.G == null) {
            this.G = new ArrayList<>();
        }
        this.G.clear();
        for (int i = 0; i < 17; i++) {
            this.G.add((i + 17) + "");
        }
        if (this.H == null) {
            this.H = new ArrayList<>();
        }
        this.H.clear();
        this.H = new ArrayList<>();
        for (int i2 = 0; i2 < 17; i2++) {
            this.H.add((i2 + 17) + "");
        }
    }

    public final void Q0() {
        this.Q = ((g) this.f).o();
        int iP = ((g) this.f).p();
        this.R = iP;
        if (iP == 0) {
            this.l.setVisibility(8);
        }
    }

    public final void V0(int i, final LottieAnimationView lottieAnimationView, final boolean z, final int i2) {
        LottieCompositionFactory.fromRawRes(this.e, i).addListener(new LottieListener() { // from class: b.a.a.i.j.b
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                this.f914a.S0(lottieAnimationView, z, i2, (LottieComposition) obj);
            }
        });
    }

    public final void W0(boolean z) {
        e eVar = this.N;
        if (eVar != null) {
            eVar.o(z);
        }
    }

    public final void X0(boolean z) {
        e eVar = this.M;
        if (eVar != null) {
            eVar.o(z);
        }
    }

    public final void Y0(boolean z) {
        e eVar = this.L;
        if (eVar != null) {
            eVar.o(z);
        }
    }

    public final void Z0(LottieAnimationView lottieAnimationView, boolean z, int i) {
        BydLogHelper.logD("setButtonAnimationColor view = " + lottieAnimationView + " --- select = " + z);
        this.c0 = new KeyPath("**");
        if (lottieAnimationView != null) {
            if (!z) {
                SimpleColorFilter simpleColorFilter = new SimpleColorFilter(this.e.getColor(R.color.text_tem_white));
                this.X = simpleColorFilter;
                LottieValueCallback<ColorFilter> lottieValueCallback = new LottieValueCallback<>(simpleColorFilter);
                this.b0 = lottieValueCallback;
                lottieAnimationView.addValueCallback(this.c0, LottieProperty.COLOR_FILTER, lottieValueCallback);
                return;
            }
            if (i == 1) {
                SimpleColorFilter simpleColorFilter2 = new SimpleColorFilter(this.e.getColor(R.color.btn_open_bg_blue));
                this.U = simpleColorFilter2;
                LottieValueCallback<ColorFilter> lottieValueCallback2 = new LottieValueCallback<>(simpleColorFilter2);
                this.Y = lottieValueCallback2;
                lottieAnimationView.addValueCallback(this.c0, LottieProperty.COLOR_FILTER, lottieValueCallback2);
                return;
            }
            if (i == 2) {
                SimpleColorFilter simpleColorFilter3 = new SimpleColorFilter(this.e.getColor(R.color.btn_open_bg_blue));
                this.V = simpleColorFilter3;
                LottieValueCallback<ColorFilter> lottieValueCallback3 = new LottieValueCallback<>(simpleColorFilter3);
                this.Z = lottieValueCallback3;
                lottieAnimationView.addValueCallback(this.c0, LottieProperty.COLOR_FILTER, lottieValueCallback3);
                return;
            }
            if (i != 3) {
                return;
            }
            SimpleColorFilter simpleColorFilter4 = new SimpleColorFilter(this.e.getColor(R.color.defrost_color_select));
            this.W = simpleColorFilter4;
            LottieValueCallback<ColorFilter> lottieValueCallback4 = new LottieValueCallback<>(simpleColorFilter4);
            this.a0 = lottieValueCallback4;
            lottieAnimationView.addValueCallback(this.c0, LottieProperty.COLOR_FILTER, lottieValueCallback4);
        }
    }

    public final void a1(int i, final LottieAnimationView lottieAnimationView, final boolean z, final int i2) {
        LottieCompositionFactory.fromRawRes(this.e, i).addListener(new LottieListener() { // from class: b.a.a.i.j.c
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                this.f917a.U0(lottieAnimationView, i2, z, (LottieComposition) obj);
            }
        });
    }

    public final void b1() {
        e eVar = this.N;
        if (eVar == null || eVar.m()) {
            return;
        }
        this.N.n(0);
        this.K.setVisibility(0);
    }

    @Override // b.a.a.d.d
    public void c(int i, boolean z) {
        BydLogHelper.logD("updateAcWindMode isRevert: " + z + " == mode: " + i);
        this.E = i;
        switch (i) {
            case -1:
            case 0:
                this.q.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.r.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.p.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                W0(false);
                X0(false);
                Y0(false);
                this.I.setImageResource(R.mipmap.img_dark_air_up_wind_off);
                this.J.setImageResource(R.mipmap.img_dark_air_middle_wind_off);
                this.K.setImageResource(R.mipmap.img_dark_air_down_wind_off);
                break;
            case 1:
                this.q.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.r.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.p.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                if (1 == this.x) {
                    c1();
                } else {
                    X0(false);
                }
                W0(false);
                Y0(false);
                this.I.setImageResource(R.mipmap.img_dark_air_up_wind_off);
                this.K.setImageResource(R.mipmap.img_dark_air_down_wind_off);
                break;
            case 2:
                this.q.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.r.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.p.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                if (1 == this.x) {
                    b1();
                    c1();
                } else {
                    W0(false);
                    X0(false);
                }
                Y0(false);
                this.I.setImageResource(R.mipmap.img_dark_air_up_wind_off);
                break;
            case 3:
                this.r.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.q.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.p.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                if (1 == this.x) {
                    b1();
                } else {
                    W0(false);
                }
                Y0(false);
                X0(false);
                this.I.setImageResource(R.mipmap.img_dark_air_up_wind_off);
                this.J.setImageResource(R.mipmap.img_dark_air_middle_wind_off);
                break;
            case 4:
                this.q.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.r.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                this.p.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                if (1 == this.x) {
                    b1();
                    d1();
                } else {
                    W0(false);
                    Y0(false);
                }
                X0(false);
                this.J.setImageResource(R.mipmap.img_dark_air_middle_wind_off);
                break;
            case 5:
                this.q.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.r.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                this.p.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                if (1 == this.x) {
                    d1();
                } else {
                    Y0(false);
                }
                W0(false);
                X0(false);
                this.J.setImageResource(R.mipmap.img_dark_air_middle_wind_off);
                this.K.setImageResource(R.mipmap.img_dark_air_down_wind_off);
                break;
            case 6:
                if (this.Q != 1) {
                    this.q.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                    this.r.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                    this.p.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                    if (1 != this.x) {
                        Y0(false);
                        W0(false);
                        X0(false);
                    } else {
                        b1();
                        c1();
                        d1();
                    }
                }
                break;
            case 7:
                if (this.Q != 1) {
                    this.q.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                    this.r.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.left_bar_btn_text_color_n)));
                    this.p.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_open_bg_blue)));
                    if (1 == this.x) {
                        c1();
                        d1();
                    } else {
                        X0(false);
                        Y0(false);
                    }
                    W0(false);
                    this.K.setImageResource(R.mipmap.img_dark_air_down_wind_off);
                }
                break;
        }
    }

    public final void c1() {
        e eVar = this.M;
        if (eVar == null || eVar.m()) {
            return;
        }
        this.M.n(0);
        this.J.setVisibility(0);
    }

    @Override // b.a.a.d.d
    public void d(int i, int i2) {
        BydLogHelper.logD("updateTemperature area = " + i + " -- value = " + i2);
        if (i2 > 33 || i2 < 17) {
            BydLogHelper.logD("updateTemperature 温度值不匹配 = ");
            return;
        }
        if (i == 1) {
            this.C = i2;
            this.g.setProgress(this.G.indexOf(String.valueOf(i2)));
        } else if (i == 2) {
            this.D = i2;
            this.h.setProgress(this.H.indexOf(String.valueOf(i2)));
        }
    }

    public final void d1() {
        e eVar = this.L;
        if (eVar == null || eVar.m()) {
            return;
        }
        this.L.n(0);
        this.I.setVisibility(0);
    }

    @Override // b.a.a.d.d
    public void e(int i, int i2, boolean z) {
        BydLogHelper.logD("updateAcDefrostState area: " + i + " == state: " + i2);
        if (i == 1) {
            this.z = i2;
            int i3 = R.raw.icon_front_defrosting;
            if (i2 == 1) {
                if (z || this.k.isAnimating()) {
                    return;
                }
                if (!b.a.a.h.g.a(getActivity())) {
                    i3 = R.raw.icon_front_defrosting_e;
                }
                a1(i3, this.k, true, 3);
                return;
            }
            if (i2 != 0 || z || this.k.isAnimating()) {
                return;
            }
            if (!b.a.a.h.g.a(getActivity())) {
                i3 = R.raw.icon_front_defrosting_e;
            }
            a1(i3, this.k, false, 3);
            return;
        }
        if (i == 2) {
            this.A = i2;
            int i4 = R.raw.icon_rear_defrosting;
            if (i2 == 1) {
                if (z || this.l.isAnimating()) {
                    return;
                }
                int i5 = this.R;
                if (i5 == 1) {
                    if (!b.a.a.h.g.a(getActivity())) {
                        i4 = R.raw.icon_rear_defrosting_e;
                    }
                    a1(i4, this.l, true, 3);
                    return;
                } else if (i5 == 2) {
                    a1(R.raw.icon_rear_defrosting_exterior_rearview_mirror_heating, this.l, true, 3);
                    return;
                } else {
                    if (i5 == 3) {
                        a1(R.raw.icon_dock_mirror_heating, this.l, true, 3);
                        return;
                    }
                    return;
                }
            }
            if (i2 != 0 || z || this.l.isAnimating()) {
                return;
            }
            int i6 = this.R;
            if (i6 == 1) {
                if (!b.a.a.h.g.a(getActivity())) {
                    i4 = R.raw.icon_rear_defrosting_e;
                }
                a1(i4, this.l, false, 3);
            } else if (i6 == 2) {
                a1(R.raw.icon_rear_defrosting_exterior_rearview_mirror_heating, this.l, false, 3);
            } else if (i6 == 3) {
                a1(R.raw.icon_dock_mirror_heating, this.l, false, 3);
            }
        }
    }

    public final void e1() {
        this.x = ((g) this.f).k();
        this.y = ((g) this.f).g();
        this.z = ((g) this.f).i(1);
        this.A = ((g) this.f).i(2);
        this.B = ((g) this.f).h();
        this.C = ((g) this.f).l(1);
        this.D = ((g) this.f).l(2);
        this.E = ((g) this.f).n();
        this.F = ((g) this.f).m();
        this.S = ((g) this.f).q();
        this.d0 = ((g) this.f).s();
        h(this.S);
        c(this.E, false);
        g(this.y, false);
        e(1, this.z, false);
        e(2, this.A, false);
        k(this.B, false);
        n(1, this.F);
        d(1, this.C);
        d(2, this.D);
        o(this.x, false);
    }

    @Override // b.a.a.d.d
    public void g(int i, boolean z) {
        BydLogHelper.logD("updateAcCompressorState state = " + i);
        this.y = i;
        if (i == 1) {
            if (z || this.j.isAnimating()) {
                return;
            }
            int i2 = this.S;
            if (i2 == 2) {
                a1(R.raw.icon_ac_refrigeration, this.j, true, 1);
                return;
            } else if (i2 == 3) {
                a1(R.raw.icon_ac_heating, this.j, true, 1);
                return;
            } else {
                a1(R.raw.icon_ac, this.j, true, 1);
                return;
            }
        }
        if (i != 0 || z || this.j.isAnimating()) {
            return;
        }
        int i3 = this.S;
        if (i3 == 2) {
            a1(R.raw.icon_ac_refrigeration, this.j, false, 1);
        } else if (i3 == 3) {
            a1(R.raw.icon_ac_heating, this.j, false, 1);
        } else {
            a1(R.raw.icon_ac, this.j, false, 1);
        }
    }

    @Override // b.a.a.d.d
    public void h(int i) {
        BydLogHelper.logD("updateHeatPumpCompressorWorkMode mode:" + i);
        this.S = i;
        if (i == 2) {
            int i2 = this.y;
            if (i2 == 1) {
                a1(R.raw.icon_ac_refrigeration, this.j, true, 1);
                return;
            } else {
                if (i2 == 0) {
                    a1(R.raw.icon_ac_refrigeration, this.j, false, 1);
                    return;
                }
                return;
            }
        }
        if (i == 3) {
            int i3 = this.y;
            if (i3 == 1) {
                a1(R.raw.icon_ac_heating, this.j, true, 1);
                return;
            } else {
                if (i3 == 0) {
                    a1(R.raw.icon_ac_heating, this.j, false, 1);
                    return;
                }
                return;
            }
        }
        int i4 = this.y;
        if (i4 == 1) {
            a1(R.raw.icon_ac, this.j, true, 1);
        } else if (i4 == 0) {
            a1(R.raw.icon_ac, this.j, false, 1);
        }
    }

    @Override // b.a.a.d.d
    public void k(int i, boolean z) {
        BydLogHelper.logD("updateAcCycleMode mode = " + i);
        this.B = i;
        if (i == 1) {
            if (z || this.m.isAnimating()) {
                return;
            }
            a1(R.raw.icon_internal_to_external, this.m, true, 1);
            return;
        }
        if (i != 0 || z || this.m.isAnimating()) {
            return;
        }
        a1(R.raw.icon_external_to_internal, this.m, true, 1);
    }

    @Override // b.a.a.d.d
    public void l(int i) {
        BydLogHelper.logD("updateAcOnlineState state = " + i);
        this.P = i;
        if (((g) this.f).j() == 1) {
            l.a(true, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.g, this.p, this.q, this.r, this.I, this.J, this.K, this.s, this.t, this.h, this.u, this.v, this.w, this.T);
            l.b(true, this.g, this.h, this.v);
            e1();
            return;
        }
        Z0(this.j, false, 1);
        Z0(this.m, false, 1);
        Z0(this.k, false, 1);
        Z0(this.l, false, 1);
        o(0, false);
        this.v.setWindLevel(0);
        c(-1, false);
        l.a(false, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.g, this.p, this.q, this.r, this.I, this.J, this.K, this.s, this.t, this.h, this.u, this.v, this.w, this.T);
        l.b(false, this.g, this.h, this.v);
    }

    @Override // b.a.a.d.d
    public void n(int i, int i2) {
        BydLogHelper.logD("updateAcWindLevel area: " + i + " == level: " + i2);
        if (i == 1) {
            this.F = i2;
            this.v.setWindLevel(i2);
            H0(i2);
        }
    }

    @Override // b.a.a.d.d
    public void o(int i, boolean z) {
        BydLogHelper.logD("updateAcDefrostState isRevert: " + z + " == state: " + i);
        this.x = i;
        if (i == 1) {
            if (!z && !this.i.isAnimating()) {
                a1(b.a.a.h.g.a(getActivity()) ? R.raw.icon_ac_on : R.raw.icon_ac_on_e, this.i, true, 1);
            }
        } else if (i == 0 && !z && !this.i.isAnimating()) {
            a1(b.a.a.h.g.a(getActivity()) ? R.raw.icon_ac_off : R.raw.icon_ac_off_e, this.i, false, 1);
        }
        c(((g) this.f).n(), false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (k.b() || I0() || J0()) {
            return;
        }
        switch (view.getId()) {
            case R.id.ib_air_ac_snowflake /* 2131296498 */:
                K0();
                if (1 != this.y) {
                    ((g) this.f).v(1);
                    int i = this.S;
                    if (i == 2) {
                        V0(R.raw.icon_ac_refrigeration, this.j, true, 1);
                    } else if (i != 3) {
                        V0(R.raw.icon_ac, this.j, true, 1);
                    } else {
                        V0(R.raw.icon_ac_heating, this.j, true, 1);
                    }
                } else {
                    ((g) this.f).v(0);
                    int i2 = this.S;
                    if (i2 == 2) {
                        V0(R.raw.icon_ac_refrigeration, this.j, false, 1);
                    } else if (i2 != 3) {
                        V0(R.raw.icon_ac, this.j, false, 1);
                    } else {
                        V0(R.raw.icon_ac_heating, this.j, false, 1);
                    }
                }
                break;
            case R.id.ib_air_after_defrost /* 2131296500 */:
                int i3 = this.A;
                int i4 = R.raw.icon_rear_defrosting;
                if (1 != i3) {
                    ((g) this.f).x(2, 1);
                    int i5 = this.R;
                    if (i5 == 1) {
                        if (!b.a.a.h.g.a(getActivity())) {
                            i4 = R.raw.icon_rear_defrosting_e;
                        }
                        V0(i4, this.l, true, 3);
                    } else if (i5 == 2) {
                        V0(R.raw.icon_rear_defrosting_exterior_rearview_mirror_heating, this.l, true, 3);
                    } else if (i5 == 3) {
                        V0(R.raw.icon_dock_mirror_heating, this.l, true, 3);
                    }
                } else {
                    ((g) this.f).x(2, 0);
                    int i6 = this.R;
                    if (i6 == 1) {
                        if (!b.a.a.h.g.a(getActivity())) {
                            i4 = R.raw.icon_rear_defrosting_e;
                        }
                        V0(i4, this.l, false, 3);
                    } else if (i6 == 2) {
                        V0(R.raw.icon_rear_defrosting_exterior_rearview_mirror_heating, this.l, false, 3);
                    } else if (i6 == 3) {
                        V0(R.raw.icon_dock_mirror_heating, this.l, false, 3);
                    }
                }
                break;
            case R.id.ib_air_before_defrost /* 2131296503 */:
                int i7 = this.z;
                int i8 = R.raw.icon_front_defrosting;
                if (1 != i7) {
                    ((g) this.f).x(1, 1);
                    if (!b.a.a.h.g.a(getActivity())) {
                        i8 = R.raw.icon_front_defrosting_e;
                    }
                    V0(i8, this.k, true, 3);
                } else {
                    ((g) this.f).x(1, 0);
                    if (!b.a.a.h.g.a(getActivity())) {
                        i8 = R.raw.icon_front_defrosting_e;
                    }
                    V0(i8, this.k, false, 3);
                }
                break;
            case R.id.ib_air_condition_off /* 2131296504 */:
                if (1 != this.x) {
                    ((g) this.f).y(1);
                    V0(b.a.a.h.g.a(getActivity()) ? R.raw.icon_ac_on : R.raw.icon_ac_on_e, this.i, true, 1);
                } else {
                    ((g) this.f).y(0);
                    V0(b.a.a.h.g.a(getActivity()) ? R.raw.icon_ac_off : R.raw.icon_ac_off_e, this.i, false, 1);
                }
                break;
            case R.id.ib_air_defrost_state /* 2131296505 */:
            case R.id.iv_top_ribbon_nothing_to_exit /* 2131296618 */:
                if (this.Q == 1) {
                    int i9 = this.E;
                    if (i9 == 3) {
                        ((g) this.f).B(4);
                        break;
                    } else if (i9 == 4) {
                        ((g) this.f).B(3);
                        break;
                    } else if (i9 != 5) {
                        if (i9 == 1 || i9 == 2 || i9 == 0) {
                            ((g) this.f).B(5);
                        }
                        break;
                    }
                } else {
                    int i10 = this.E;
                    if (i10 == 1) {
                        ((g) this.f).B(7);
                        break;
                    } else if (i10 == 2) {
                        ((g) this.f).B(6);
                        break;
                    } else if (i10 == 3) {
                        ((g) this.f).B(4);
                        break;
                    } else if (i10 == 4) {
                        ((g) this.f).B(3);
                        break;
                    } else if (i10 != 5) {
                        if (i10 == 6) {
                            ((g) this.f).B(2);
                        } else if (i10 == 7) {
                            ((g) this.f).B(1);
                        } else if (i10 == 0) {
                            ((g) this.f).B(5);
                        }
                        break;
                    }
                }
                break;
            case R.id.ib_air_level_big /* 2131296506 */:
                if (this.F < 7) {
                    ((g) this.f).A(7);
                }
                this.w.playAnimation();
                break;
            case R.id.ib_air_level_small /* 2131296507 */:
                ((g) this.f).A(1);
                this.u.playAnimation();
                break;
            case R.id.ib_air_loop_mode /* 2131296508 */:
                if (this.B != 0) {
                    ((g) this.f).w(0);
                    V0(R.raw.icon_internal_to_external, this.m, true, 1);
                } else {
                    ((g) this.f).w(1);
                    V0(R.raw.icon_external_to_internal, this.m, true, 1);
                }
                break;
            case R.id.ib_air_wind_blow_face /* 2131296511 */:
            case R.id.iv_mid_ribbon_nothing_to_exit /* 2131296572 */:
                if (this.Q != 1) {
                    int i11 = this.E;
                    if (i11 != 1) {
                        if (i11 == 2) {
                            ((g) this.f).B(3);
                        } else if (i11 == 3) {
                            ((g) this.f).B(2);
                        } else if (i11 == 4) {
                            ((g) this.f).B(6);
                        } else if (i11 == 5) {
                            ((g) this.f).B(7);
                        } else if (i11 == 6) {
                            ((g) this.f).B(4);
                        } else if (i11 == 7) {
                            ((g) this.f).B(5);
                        } else if (i11 == 0) {
                            ((g) this.f).B(1);
                        }
                        break;
                    }
                } else {
                    int i12 = this.E;
                    if (i12 != 1) {
                        if (i12 == 2) {
                            ((g) this.f).B(3);
                        } else if (i12 == 3) {
                            ((g) this.f).B(2);
                        } else if (i12 == 5 || i12 == 4 || i12 == 0) {
                            ((g) this.f).B(1);
                        }
                        break;
                    }
                }
                break;
            case R.id.ib_air_wind_blow_foot /* 2131296512 */:
            case R.id.iv_bottom_ribbon_nothing_to_exit /* 2131296538 */:
                if (this.Q == 1) {
                    int i13 = this.E;
                    if (i13 == 1) {
                        ((g) this.f).B(2);
                        break;
                    } else if (i13 == 2) {
                        ((g) this.f).B(1);
                        break;
                    } else if (i13 != 3) {
                        if (i13 == 4) {
                            ((g) this.f).B(5);
                        } else if (i13 == 5) {
                            ((g) this.f).B(4);
                        } else if (i13 == 0) {
                            ((g) this.f).B(3);
                        }
                        break;
                    }
                } else {
                    int i14 = this.E;
                    if (i14 == 1) {
                        ((g) this.f).B(2);
                        break;
                    } else if (i14 == 2) {
                        ((g) this.f).B(1);
                        break;
                    } else if (i14 != 3) {
                        if (i14 == 4) {
                            ((g) this.f).B(5);
                        } else if (i14 == 5) {
                            ((g) this.f).B(4);
                        } else if (i14 == 6) {
                            ((g) this.f).B(7);
                        } else if (i14 == 7) {
                            ((g) this.f).B(6);
                        } else if (i14 == 0) {
                            ((g) this.f).B(3);
                        }
                        break;
                    }
                }
                break;
            case R.id.ib_left_temp_reduce /* 2131296514 */:
                K0();
                if (this.C > 17) {
                    ((g) this.f).z(1, this.C - 1);
                }
                break;
            case R.id.ib_left_temp_rise /* 2131296515 */:
                K0();
                if (this.C < 33) {
                    ((g) this.f).z(1, this.C + 1);
                }
                break;
            case R.id.ib_right_temp_reduce /* 2131296520 */:
                K0();
                if (this.D > 17) {
                    ((g) this.f).z(2, this.D - 1);
                }
                break;
            case R.id.ib_right_temp_rise /* 2131296521 */:
                K0();
                if (this.D < 33) {
                    ((g) this.f).z(2, this.D + 1);
                }
                break;
        }
    }

    @Override // com.byd.hvac.base.BaseFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        b.a.a.i.i.a aVar = this.O;
        if (aVar != null && aVar.b()) {
            this.O.a();
        }
        ArrayList<String> arrayList = this.G;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList<String> arrayList2 = this.H;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        Y0(true);
        X0(true);
        W0(true);
        super.onDestroyView();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onPause() {
        Y0(true);
        X0(true);
        W0(true);
        super.onPause();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.e0.postDelayed(new Runnable() { // from class: b.a.a.i.j.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f913a.A0();
            }
        }, 50L);
    }

    @Override // com.byd.hvac.view.verticalseekbar.TemperatureVerticalSeekBar.a
    public void r0(View view, int i) {
        view.playSoundEffect(0);
    }

    @Override // com.byd.hvac.base.BaseFragment
    public int v0() {
        return M0(R.layout.fragment_ev);
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void x0(View view) {
        this.i = (LottieAnimationView) view.findViewById(R.id.ib_air_condition_off);
        this.j = (LottieAnimationView) view.findViewById(R.id.ib_air_ac_snowflake);
        this.k = (LottieAnimationView) view.findViewById(R.id.ib_air_before_defrost);
        this.l = (LottieAnimationView) view.findViewById(R.id.ib_air_after_defrost);
        this.m = (LottieAnimationView) view.findViewById(R.id.ib_air_loop_mode);
        this.n = (ImageButton) view.findViewById(R.id.ib_left_temp_rise);
        this.g = (TemperatureVerticalSeekBar) view.findViewById(R.id.left_wheel_view);
        this.o = (ImageButton) view.findViewById(R.id.ib_left_temp_reduce);
        this.p = (ImageView) view.findViewById(R.id.ib_air_defrost_state);
        this.q = (ImageView) view.findViewById(R.id.ib_air_wind_blow_face);
        this.r = (ImageView) view.findViewById(R.id.ib_air_wind_blow_foot);
        this.s = (ImageButton) view.findViewById(R.id.ib_right_temp_rise);
        this.h = (TemperatureVerticalSeekBar) view.findViewById(R.id.right_wheel_view);
        this.t = (ImageButton) view.findViewById(R.id.ib_right_temp_reduce);
        this.u = (LottieAnimationView) view.findViewById(R.id.ib_air_level_small);
        WindLevelButton windLevelButton = (WindLevelButton) view.findViewById(R.id.sb_air_wind_level);
        this.v = windLevelButton;
        windLevelButton.setWindLevelMax(7);
        this.v.setWindLevel(0);
        this.v.setMin(1);
        this.w = (LottieAnimationView) view.findViewById(R.id.ib_air_level_big);
        this.I = (ImageView) view.findViewById(R.id.iv_top_ribbon_nothing_to_exit);
        this.J = (ImageView) view.findViewById(R.id.iv_mid_ribbon_nothing_to_exit);
        this.K = (ImageView) view.findViewById(R.id.iv_bottom_ribbon_nothing_to_exit);
        this.T = (ImageView) view.findViewById(R.id.iv_air_people);
        O0();
        this.e0 = new Handler();
        A0();
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void z0() {
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.J.setOnClickListener(this);
        this.K.setOnClickListener(this);
        this.v.setOnWindLevelViewChangeListener(new a());
        this.h.setOnSlideChangeListener(this);
        this.g.setOnSlideChangeListener(this);
    }
}
