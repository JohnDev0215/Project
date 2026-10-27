package com.byd.hvac.view.fragrance;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.a.a.d.e;
import b.a.a.g.h;
import b.a.a.h.k;
import b.a.a.h.l;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.byd.hvac.R;
import com.byd.hvac.base.BaseFragment;
import com.byd.hvac.view.dialog.AcStartVehicleTipsLayout;
import com.byd.hvac.view.segmentedbutton.SegmentedButtonGroup;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class FragranceControlFragment extends BaseFragment<h> implements e, View.OnClickListener {
    public ImageView A;
    public ImageView B;
    public ImageView C;
    public ImageView D;
    public ImageView E;
    public int F;
    public TextView G;
    public b.a.a.i.i.a H;
    public ImageView I;
    public ImageView J;
    public ImageView K;
    public HashMap<Integer, b.a.a.c.a> L;
    public SegmentedButtonGroup g;
    public ConstraintLayout h;
    public ConstraintLayout i;
    public ConstraintLayout j;
    public TextView k;
    public TextView l;
    public TextView m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public LottieAnimationView s;
    public LottieAnimationView t;
    public LottieAnimationView u;
    public ArrayList<Integer> v = new ArrayList<>();
    public int w;
    public int x;
    public int y;
    public ImageView z;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements AcStartVehicleTipsLayout.b {
        public a() {
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void a() {
            FragranceControlFragment.this.H.a();
        }

        @Override // com.byd.hvac.view.dialog.AcStartVehicleTipsLayout.b
        public void b() {
            FragranceControlFragment.this.H.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: H0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void I0(View view, View view2, int i) {
        if (D0()) {
            return;
        }
        if (i == 0) {
            ((h) this.f).m(4);
        } else {
            ((h) this.f).m(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: J0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void K0(LottieComposition lottieComposition) {
        this.s.playAnimation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: L0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void M0(LottieComposition lottieComposition) {
        this.t.playAnimation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: N0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void O0(LottieComposition lottieComposition) {
        this.u.playAnimation();
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void A0() {
        F0();
        G0();
        int iJ = ((h) this.f).j();
        this.F = iJ;
        L(iJ);
    }

    public final void C0(int i) {
        this.w = ((h) this.f).h(1);
        this.x = ((h) this.f).h(2);
        this.y = ((h) this.f).h(3);
        this.C.setSelected(false);
        this.k.setTypeface(Typeface.defaultFromStyle(0));
        this.D.setSelected(false);
        this.l.setTypeface(Typeface.defaultFromStyle(0));
        this.E.setSelected(false);
        this.m.setTypeface(Typeface.defaultFromStyle(0));
        if (i == 1) {
            if (this.w != 1) {
                this.C.setSelected(false);
                this.k.setTypeface(Typeface.defaultFromStyle(0));
                return;
            } else {
                this.C.setSelected(true);
                Z(i, ((h) this.f).i(i));
                this.k.setTypeface(Typeface.defaultFromStyle(1));
                return;
            }
        }
        if (i == 2) {
            if (this.x != 1) {
                this.D.setSelected(false);
                this.l.setTypeface(Typeface.defaultFromStyle(0));
                return;
            } else {
                this.D.setSelected(true);
                Z(i, ((h) this.f).i(i));
                this.l.setTypeface(Typeface.defaultFromStyle(1));
                return;
            }
        }
        if (i == 3) {
            if (this.y != 1) {
                this.E.setSelected(false);
                this.m.setTypeface(Typeface.defaultFromStyle(0));
            } else {
                this.E.setSelected(true);
                Z(i, ((h) this.f).i(i));
                this.m.setTypeface(Typeface.defaultFromStyle(1));
            }
        }
    }

    public final boolean D0() {
        int iJ = ((h) this.f).j();
        this.F = iJ;
        if (iJ == 1) {
            return false;
        }
        this.H = new b.a.a.i.i.a();
        AcStartVehicleTipsLayout acStartVehicleTipsLayout = (AcStartVehicleTipsLayout) LayoutInflater.from(this.e).inflate(R.layout.dialog_ac_fragrance_online_tips, (ViewGroup) null);
        acStartVehicleTipsLayout.setBtnCallBackListener(new a());
        this.H.c((Activity) this.e, acStartVehicleTipsLayout, (int) this.e.getResources().getDimension(R.dimen.dp_560), (int) this.e.getResources().getDimension(R.dimen.dp_312));
        return true;
    }

    public final void E0() {
        LottieAnimationView lottieAnimationView = this.s;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(8);
            this.t.setVisibility(8);
            this.u.setVisibility(8);
        }
    }

    public final void F0() {
        HashMap<Integer, b.a.a.c.a> map = new HashMap<>();
        this.L = map;
        map.put(54, new b.a.a.c.a(R.string.fragrance_cha_ka, R.mipmap.img_light_air_fragrance_chaka));
        this.L.put(127, new b.a.a.c.a(R.string.fragrance_chang_an, R.mipmap.img_light_air_fragrance_changan));
        this.L.put(41, new b.a.a.c.a(R.string.fragrance_tian_shan, R.mipmap.img_light_air_fragrance_tianshan));
        this.L.put(122, new b.a.a.c.a(R.string.fragrance_mountain_rain, R.mipmap.img_fragrance_mountain_rain));
        this.L.put(15, new b.a.a.c.a(R.string.fragrance_summer_wind_sea_salt, R.mipmap.img_fragrance_summer_wind_sea_salt));
        this.L.put(117, new b.a.a.c.a(R.string.fragrance_beautiful, R.mipmap.img_fragrance_beautiful));
        this.L.put(8, new b.a.a.c.a(R.string.fragrance_name_jingyuechenxiang, R.mipmap.img_light_air_fragrance_jingyuechenxiang));
        this.L.put(9, new b.a.a.c.a(R.string.fragrance_name_yangguangganju, R.mipmap.img_light_air_fragrance_yangguangganju));
        this.L.put(10, new b.a.a.c.a(R.string.fragrance_name_chenluqinglian, R.mipmap.img_light_air_fragrance_chenluqinglian));
        this.L.put(7, new b.a.a.c.a(R.string.fragrance_name_wanning, R.mipmap.img_light_air_fragrance_wanning));
        this.L.put(30, new b.a.a.c.a(R.string.fragrance_name_dongting, R.mipmap.img_light_air_fragrance_dongting));
        this.L.put(102, new b.a.a.c.a(R.string.fragrance_name_wuyi, R.mipmap.img_light_air_fragrance_wuyi));
        this.L.put(145, new b.a.a.c.a(R.string.fragrance_name_yunfu, R.mipmap.img_light_air_fragrance_yunfu));
    }

    public final void G0() {
        this.v.add(Integer.valueOf(R.raw.fragrance_dan));
        this.v.add(Integer.valueOf(R.raw.fragrance_zhong));
        this.v.add(Integer.valueOf(R.raw.fragrance_nong));
        Z(1, ((h) this.f).i(1));
        Z(2, ((h) this.f).i(2));
        Z(3, ((h) this.f).i(3));
    }

    @Override // b.a.a.d.e
    public void L(int i) {
        this.F = i;
        if (i == 1) {
            l.a(true, this.g, this.h, this.i, this.j);
            Q0();
            return;
        }
        this.g.setPosition(0, false);
        C0(0);
        E0();
        P0();
        l.a(false, this.g, this.h, this.i, this.j);
    }

    public final void P0() {
        LottieAnimationView lottieAnimationView = this.s;
        if (lottieAnimationView != null) {
            lottieAnimationView.cancelAnimation();
            this.s.clearAnimation();
            this.s.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView2 = this.t;
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.cancelAnimation();
            this.t.clearAnimation();
            this.t.setVisibility(8);
        }
        LottieAnimationView lottieAnimationView3 = this.u;
        if (lottieAnimationView3 != null) {
            lottieAnimationView3.cancelAnimation();
            this.u.clearAnimation();
            this.u.setVisibility(8);
        }
    }

    public final void Q0() {
        int iL = ((h) this.f).l();
        this.n = iL;
        this.o = ((h) this.f).g(iL);
        T(this.n);
        X(this.o);
        this.w = ((h) this.f).h(1);
        this.x = ((h) this.f).h(2);
        this.y = ((h) this.f).h(3);
        a0(1, this.w);
        a0(2, this.x);
        a0(3, this.y);
        this.p = ((h) this.f).k(1);
        this.q = ((h) this.f).k(2);
        this.r = ((h) this.f).k(3);
        m0(1, this.p);
        m0(2, this.q);
        m0(3, this.r);
        Z(1, ((h) this.f).i(1));
        Z(2, ((h) this.f).i(2));
        Z(3, ((h) this.f).i(3));
    }

    public final void R0() {
        ArrayList<Integer> arrayList;
        ArrayList<Integer> arrayList2;
        int i = this.o;
        if (i < 1 || i > 3) {
            if (i >= 4) {
                E0();
                P0();
                return;
            }
            return;
        }
        E0();
        int i2 = this.n;
        if (i2 == 1 && this.w == 1) {
            this.s.setVisibility(0);
            LottieCompositionFactory.fromRawRes(this.e, R.raw.fragrance_nong).addListener(new LottieListener() { // from class: b.a.a.i.k.a
                @Override // com.airbnb.lottie.LottieListener
                public final void onResult(Object obj) {
                    this.f920a.K0((LottieComposition) obj);
                }
            });
            return;
        }
        if (i2 == 2 && this.x == 1 && (arrayList2 = this.v) != null && !arrayList2.isEmpty()) {
            this.t.setVisibility(0);
            LottieCompositionFactory.fromRawRes(this.e, this.v.get(this.o - 1).intValue()).addListener(new LottieListener() { // from class: b.a.a.i.k.d
                @Override // com.airbnb.lottie.LottieListener
                public final void onResult(Object obj) {
                    this.f923a.M0((LottieComposition) obj);
                }
            });
        } else if (this.n != 3 || this.y != 1 || (arrayList = this.v) == null || arrayList.isEmpty()) {
            E0();
            P0();
        } else {
            this.u.setVisibility(0);
            LottieCompositionFactory.fromRawRes(this.e, this.v.get(this.o - 1).intValue()).addListener(new LottieListener() { // from class: b.a.a.i.k.b
                @Override // com.airbnb.lottie.LottieListener
                public final void onResult(Object obj) {
                    this.f921a.O0((LottieComposition) obj);
                }
            });
        }
    }

    @Override // b.a.a.d.e
    public void T(int i) {
        BydLogHelper.logD("updateFragranceType type = " + i);
        this.n = i;
        C0(i);
        R0();
    }

    @Override // b.a.a.d.e
    public void X(int i) {
        BydLogHelper.logD("updateFragranceConcentration value:" + i);
        this.o = i;
        if (i == 4) {
            this.g.setPosition(0, false);
        } else if (i == 1 || i == 2 || i == 3) {
            this.g.setPosition(i, false);
        }
        R0();
    }

    @Override // b.a.a.d.e
    public void Z(int i, int i2) {
        b.a.a.c.a aVar = this.L.get(Integer.valueOf(i2));
        BydLogHelper.logD("updateFragranceName type = " + i + " --- fragranceName = " + i2 + " --- fragranceBean = " + aVar);
        if (i == 1) {
            if (aVar == null) {
                this.k.setText(this.e.getString(R.string.hvac_unknown_fragrance));
                this.z.setImageResource(R.mipmap.img_light_air_fragrance_unknow_bg);
                return;
            }
            this.k.setText(aVar.f64b);
            int i3 = aVar.f63a;
            if (i3 > 0) {
                this.z.setImageResource(i3);
                return;
            } else {
                this.z.setImageResource(R.mipmap.img_light_air_fragrance_unknow_bg);
                return;
            }
        }
        if (i == 2) {
            if (aVar == null) {
                this.l.setText(this.e.getString(R.string.hvac_unknown_fragrance));
                this.A.setImageResource(R.mipmap.img_light_air_fragrance_unknow_bg);
                return;
            }
            this.l.setText(aVar.f64b);
            int i4 = aVar.f63a;
            if (i4 > 0) {
                this.A.setImageResource(i4);
                return;
            } else {
                this.A.setImageResource(R.mipmap.img_light_air_fragrance_unknow_bg);
                return;
            }
        }
        if (i != 3) {
            return;
        }
        if (aVar == null) {
            this.m.setText(this.e.getString(R.string.hvac_unknown_fragrance));
            this.B.setImageResource(R.mipmap.img_light_air_fragrance_unknow_bg);
            return;
        }
        this.m.setText(aVar.f64b);
        int i5 = aVar.f63a;
        if (i5 > 0) {
            this.B.setImageResource(i5);
        } else {
            this.B.setImageResource(R.mipmap.img_light_air_fragrance_unknow_bg);
        }
    }

    @Override // b.a.a.d.e
    public void a0(int i, int i2) {
        BydLogHelper.logD("mFragranceInstallState type = " + i + " -- state = " + i2);
        this.G.setText("");
        if (i == 1) {
            this.w = i2;
            m0(1, ((h) this.f).k(1));
            if (i2 == 2) {
                this.z.setImageResource(R.mipmap.img_light_air_fragrance_kongzhuangtai_bg);
                this.k.setText("");
                this.C.setSelected(false);
                this.h.setEnabled(false);
                if (((h) this.f).l() == 1) {
                    E0();
                    P0();
                }
            } else if (i2 == 1 && this.F == 1) {
                if (((h) this.f).k(i) > 0) {
                    this.h.setEnabled(true);
                }
                Z(i, ((h) this.f).i(i));
                if (((h) this.f).l() == i) {
                    T(i);
                }
            }
        } else if (i == 2) {
            this.x = i2;
            m0(2, ((h) this.f).k(2));
            if (i2 == 2) {
                this.A.setImageResource(R.mipmap.img_light_air_fragrance_kongzhuangtai_bg);
                this.l.setText("");
                this.D.setSelected(false);
                this.i.setEnabled(false);
                if (((h) this.f).l() == 2) {
                    E0();
                    P0();
                }
            } else if (i2 == 1 && this.F == 1) {
                if (((h) this.f).k(i) > 0) {
                    this.i.setEnabled(true);
                }
                Z(i, ((h) this.f).i(i));
                if (((h) this.f).l() == i) {
                    T(i);
                }
            }
        } else if (i == 3) {
            this.y = i2;
            m0(3, ((h) this.f).k(3));
            if (i2 == 2) {
                this.B.setImageResource(R.mipmap.img_light_air_fragrance_kongzhuangtai_bg);
                this.m.setText("");
                this.E.setSelected(false);
                this.j.setEnabled(false);
                if (((h) this.f).l() == 3) {
                    E0();
                    P0();
                }
            } else if (i2 == 1 && this.F == 1) {
                if (((h) this.f).k(i) > 0) {
                    this.j.setEnabled(true);
                }
                Z(i, ((h) this.f).i(i));
                if (((h) this.f).l() == i) {
                    T(i);
                }
            }
        }
        if (this.w == 2 && this.x == 2 && this.y == 2) {
            this.G.setText(this.e.getString(R.string.no_byd_fragrance_tip));
        }
    }

    @Override // b.a.a.d.e
    public void m0(int i, int i2) {
        BydLogHelper.logD("updateFragranceResidue type = " + i + " -- value:" + i2);
        if (i == 1) {
            if (this.w != 1) {
                this.I.setVisibility(8);
                return;
            }
            if (i2 == 0) {
                this.I.setVisibility(8);
                this.C.setSelected(false);
                this.k.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.sp_15));
                this.k.setTypeface(Typeface.defaultFromStyle(0));
                this.s.setVisibility(8);
                this.s.cancelAnimation();
                l.c(false, this.h);
                return;
            }
            if (i2 <= 0 || i2 > 100) {
                return;
            }
            l.c(true, this.h);
            if (((h) this.f).l() == 1) {
                T(1);
            }
            if (i2 >= 75) {
                this.I.setVisibility(0);
                this.I.setImageResource(R.mipmap.fragrance_residue_100_76);
                return;
            } else if (i2 >= 50) {
                this.I.setVisibility(0);
                this.I.setImageResource(R.mipmap.fragrance_residue_75_51);
                return;
            } else if (i2 >= 25) {
                this.I.setVisibility(0);
                this.I.setImageResource(R.mipmap.fragrance_residue_50_26);
                return;
            } else {
                this.I.setVisibility(0);
                this.I.setImageResource(R.mipmap.fragrance_residue_25_0);
                return;
            }
        }
        if (i == 2) {
            if (this.x != 1) {
                this.J.setVisibility(8);
                return;
            }
            if (i2 == 0) {
                this.J.setVisibility(8);
                this.D.setSelected(false);
                this.l.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.sp_15));
                this.l.setTypeface(Typeface.defaultFromStyle(0));
                this.t.setVisibility(8);
                this.t.cancelAnimation();
                l.c(false, this.i);
                return;
            }
            if (i2 <= 0 || i2 > 100) {
                return;
            }
            l.c(true, this.i);
            if (((h) this.f).l() == 2) {
                T(2);
            }
            if (i2 >= 75) {
                this.J.setVisibility(0);
                this.J.setImageResource(R.mipmap.fragrance_residue_100_76);
                return;
            } else if (i2 >= 50) {
                this.J.setVisibility(0);
                this.J.setImageResource(R.mipmap.fragrance_residue_75_51);
                return;
            } else if (i2 >= 25) {
                this.J.setVisibility(0);
                this.J.setImageResource(R.mipmap.fragrance_residue_50_26);
                return;
            } else {
                this.J.setVisibility(0);
                this.J.setImageResource(R.mipmap.fragrance_residue_25_0);
                return;
            }
        }
        if (i == 3) {
            if (this.y != 1) {
                this.K.setVisibility(8);
                return;
            }
            if (i2 == 0) {
                this.K.setVisibility(8);
                this.E.setSelected(false);
                this.m.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.sp_15));
                this.m.setTypeface(Typeface.defaultFromStyle(0));
                this.u.setVisibility(8);
                this.u.cancelAnimation();
                l.c(false, this.j);
                return;
            }
            if (i2 <= 0 || i2 > 100) {
                return;
            }
            l.c(true, this.j);
            if (((h) this.f).l() == 3) {
                T(3);
            }
            if (i2 >= 75) {
                this.K.setVisibility(0);
                this.K.setImageResource(R.mipmap.fragrance_residue_100_76);
            } else if (i2 >= 50) {
                this.K.setVisibility(0);
                this.K.setImageResource(R.mipmap.fragrance_residue_75_51);
            } else if (i2 >= 25) {
                this.K.setVisibility(0);
                this.K.setImageResource(R.mipmap.fragrance_residue_50_26);
            } else {
                this.K.setVisibility(0);
                this.K.setImageResource(R.mipmap.fragrance_residue_25_0);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (k.b() || D0()) {
            return;
        }
        switch (view.getId()) {
            case R.id.cl_fragrance_beautiful /* 2131296374 */:
                ((h) this.f).n(3);
                break;
            case R.id.cl_fragrance_mountain_rain /* 2131296375 */:
                ((h) this.f).n(1);
                break;
            case R.id.cl_fragrance_summer_wind_sea_salt /* 2131296376 */:
                ((h) this.f).n(2);
                break;
        }
    }

    @Override // com.byd.hvac.base.BaseFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.g.setOnClickedButtonListener(null);
        this.h.setOnClickListener(null);
        this.i.setOnClickListener(null);
        this.j.setOnClickListener(null);
        P0();
        ArrayList<Integer> arrayList = this.v;
        if (arrayList != null) {
            arrayList.clear();
        }
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
        if (z || (t = this.f) == 0 || ((h) t).j() != 1) {
            P0();
        } else {
            T(((h) this.f).l());
            X(((h) this.f).g(this.n));
        }
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        P0();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        T t = this.f;
        if (t == 0 || ((h) t).j() != 1) {
            P0();
        } else {
            T(((h) this.f).l());
            X(((h) this.f).g(this.n));
        }
    }

    @Override // com.byd.hvac.base.BaseFragment
    public int v0() {
        return R.layout.fragment_fragrance_control;
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void x0(View view) {
        this.g = (SegmentedButtonGroup) view.findViewById(R.id.sbg_fragrance_switch);
        this.G = (TextView) view.findViewById(R.id.tv_tips);
        this.h = (ConstraintLayout) view.findViewById(R.id.cl_fragrance_mountain_rain);
        this.k = (TextView) view.findViewById(R.id.tv_fragrance_mountain_rain);
        this.s = (LottieAnimationView) view.findViewById(R.id.iv_fragrance_mountain_rain_animation);
        this.z = (ImageView) view.findViewById(R.id.iv_fragrance_mountain_rain);
        this.C = (ImageView) view.findViewById(R.id.iv_fragrance_mountain_rain_bg);
        this.I = (ImageView) view.findViewById(R.id.iv_fragrance_mountain_rain_residue);
        this.i = (ConstraintLayout) view.findViewById(R.id.cl_fragrance_summer_wind_sea_salt);
        this.l = (TextView) view.findViewById(R.id.tv_fragrance_summer_wind_sea_salt);
        this.t = (LottieAnimationView) view.findViewById(R.id.iv_fragrance_summer_wind_sea_salt_animation);
        this.A = (ImageView) view.findViewById(R.id.iv_fragrance_summer_wind_sea_salt);
        this.D = (ImageView) view.findViewById(R.id.iv_fragrance_summer_wind_sea_salt_bg);
        this.J = (ImageView) view.findViewById(R.id.iv_fragrance_summer_wind_sea_salt_residue);
        this.j = (ConstraintLayout) view.findViewById(R.id.cl_fragrance_beautiful);
        this.m = (TextView) view.findViewById(R.id.tv_fragrance_beautiful);
        this.u = (LottieAnimationView) view.findViewById(R.id.iv_fragrance_beautiful_animation);
        this.B = (ImageView) view.findViewById(R.id.iv_fragrance_beautiful);
        this.E = (ImageView) view.findViewById(R.id.iv_fragrance_beautiful_bg);
        this.K = (ImageView) view.findViewById(R.id.iv_fragrance_beautiful_residue);
        A0();
    }

    @Override // com.byd.hvac.base.BaseFragment
    public void z0() {
        super.z0();
        this.g.setOnPositionChangedListener(new SegmentedButtonGroup.f() { // from class: b.a.a.i.k.c
            @Override // com.byd.hvac.view.segmentedbutton.SegmentedButtonGroup.f
            public final void a(View view, View view2, int i) {
                this.f922a.I0(view, view2, i);
            }
        });
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
    }
}
