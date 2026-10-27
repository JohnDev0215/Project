package com.byd.carsettings.ui.fragment.carsettings;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import b.b.a.h.l;
import b.b.a.j.b.b;
import b.b.g.n.h.d;
import b.b.g.q.d.e;
import b.b.g.q.d.k;
import b.b.m.g.c;
import b.b.o.b.a.a;
import com.byd.carsettings.R;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class CarSetSeatFragment extends XFragment<d> implements View.OnClickListener, a {
    public static String m;
    public Context n;
    public DampingScrollView o;
    public ImageView p;
    public ImageView q;
    public ImageView r;
    public e s;
    public RelativeLayout t;
    public RelativeLayout u;
    public RelativeLayout v;
    public b.b.g.q.d.a w;
    public ImageView x;
    public b.b.o.a.b.a y;
    public TextView z;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.n = context;
        this.o = (DampingScrollView) this.f3717c.findViewById(R.id.nsw);
        N(((d) mo7getP()).B());
        O(((d) mo7getP()).D());
        M(((d) mo7getP()).t());
        EventBus.getDefault().register(this);
    }

    public final void K() {
        l.F(null, this.p);
    }

    public int L(int i, int i2) {
        if (this.y == null) {
            this.y = b.b.o.a.b.d.a("seat");
        }
        return this.y.a(i, i2);
    }

    public final void M(boolean z) {
        if (z) {
            this.r = (ImageView) this.f3717c.findViewById(R.id.carseat_front_massage_iv);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.carseat_front_massage_container);
            this.v = relativeLayout;
            l.K(true, relativeLayout);
            l.F(this, this.r, this.v);
        }
    }

    public final void N(boolean z) {
        if (z) {
            this.p = (ImageView) this.f3717c.findViewById(R.id.carseat_main_driving_iv);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.carseat_main_driving_container);
            this.t = relativeLayout;
            l.K(true, relativeLayout);
            l.F(this, this.p, this.t);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void O(boolean z) {
        if (z) {
            this.q = (ImageView) this.f3717c.findViewById(R.id.carseat_wind_warm_iv);
            this.z = (TextView) this.f3717c.findViewById(R.id.carseat_wind_warm_tv);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.carseat_wind_warm_container);
            this.u = relativeLayout;
            l.K(true, relativeLayout);
            l.F(this, this.q, this.u);
            if (((d) mo7getP()).A() && !((d) mo7getP()).C()) {
                this.z.setText(R.string.seat_heat);
            } else {
                if (!((d) mo7getP()).C() || ((d) mo7getP()).A()) {
                    return;
                }
                this.z.setText(R.string.seat_ventilation);
            }
        }
    }

    public final void P(int i) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.byd.hvac", "com.byd.hvac.view.MainActivity"));
        intent.setFlags(337641472);
        if (i == 0) {
            intent.putExtra("module", "Hvac.SeatHeatVentilation");
        }
        if (i == 1) {
            intent.putExtra("module", "Hvac.SeatFrontMassage");
        }
        if (i == 2) {
            intent.putExtra("module", "Hvac.SeatRearMassage");
        }
        if (DoorLocksFragment.P(getContext(), intent)) {
            startActivity(intent);
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
    public d newP() {
        return new d();
    }

    public void R() {
        ImageView imageView = (ImageView) this.f3717c.findViewById(R.id.carseat_image);
        this.x = imageView;
        l.j(imageView, L(0, 0), this.n);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void S(String str) {
        if (this.w == null) {
            this.w = new b.b.g.q.d.a(this.n);
        }
        this.w.d0(str);
        this.w.n0((d) mo7getP());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void T() {
        if (this.s == null) {
            this.s = new e(getContext(), (d) mo7getP());
        }
        this.s.v0();
    }

    public final void U(String str) {
        str.hashCode();
        switch (str) {
            case "carseat_driver_mode":
                S(str);
                break;
            case "carseat_massage_mode":
                T();
                break;
            case "carseat_massage_tips_mode":
                e eVar = this.s;
                if (eVar != null && eVar.d0()) {
                    this.s.w0();
                    break;
                }
                break;
        }
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_carsetting_rtl_carseat_layout;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_carseat_layout;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return getContext().getResources().getConfiguration().orientation == 2 ? R.layout.pager_carsetting_right_carseat_layout : R.layout.pager_carseat_layout;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        for (int i = 0; i < k.c(); i++) {
            String str = k.b().get(i);
            if (str.startsWith("carseat_")) {
                U(str);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (c.a(500L)) {
        }
        switch (view.getId()) {
            case R.id.carseat_front_massage_container /* 2131296601 */:
            case R.id.carseat_front_massage_iv /* 2131296602 */:
                T();
                break;
            case R.id.carseat_main_driving_container /* 2131296605 */:
            case R.id.carseat_main_driving_iv /* 2131296606 */:
                S("carseat_driver_mode");
                break;
            case R.id.carseat_wind_warm_container /* 2131296650 */:
            case R.id.carseat_wind_warm_iv /* 2131296651 */:
                P(0);
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        K();
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        e eVar = this.s;
        if (eVar == null || !eVar.d0()) {
            return;
        }
        this.s.D();
        this.s.l();
        this.s = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @RequiresApi(api = 29)
    public void onGetMessage(b.b.a.c.a aVar) {
        int iH = aVar.h();
        if (iH == 10007) {
            BydLogHelper.logD("on CARSETTINGS_TABLAYOUT_UNSELECTED_CHANGE  : " + aVar.c());
            e eVar = this.s;
            if (eVar != null && eVar.d0() && aVar.c() == 8) {
                BydLogHelper.logD("on CARSETTINGS_TABLAYOUT_UNSELECTED_CHANGE  massageDialog : " + aVar.c());
                this.s.D();
                this.s.l();
                this.s = null;
                return;
            }
            return;
        }
        switch (iH) {
            case 110000:
                BydLogHelper.logD("message : CAR_SEAT_TURN_SEAT_HEIGHT_CHANGE_MESSAGE " + aVar.c());
                break;
            case 110001:
                BydLogHelper.logD("message : CAR_SEAT_TURN_SEAT_HORIZONTAL_CHANGE_MESSAGE " + aVar.c());
                break;
            case 110002:
                BydLogHelper.logD("message : CAR_SEAT_TURN_BACKREST_CHANGE_MESSAGE " + aVar.c());
                break;
            case 110003:
                BydLogHelper.logD("message : CAR_SEAT_TURN_CUSHION_CHANGE_MESSAGE " + aVar.c());
                break;
            case 110004:
                BydLogHelper.logD("message : CAR_SEAT_TURN_CALIPER_SPLINT_CHANGE_MESSAGE " + aVar.c());
                break;
            case 110005:
                BydLogHelper.logD("message : CAR_SEAT_TURN_HEADREST_HEIGHT_CHANGE_MESSAGE " + aVar.c());
                break;
            case 110006:
                BydLogHelper.logD("message : CAR_SEAT_TURN_HEADREST_HORIZONTAL_CHANGE_MESSAGE " + aVar.c());
                break;
            case 110007:
                BydLogHelper.logD("message : CAR_SEAT_TURN_WHEEL_CHANGE_MESSAGE " + aVar.c());
                break;
            case 110008:
                BydLogHelper.logD("message : CAR_SEAT_TURN_WMIRROR_CHANGE_MESSAGE " + aVar.c());
                break;
            case 110009:
                BydLogHelper.logD("message : CAR_SEAT_REMEBER_PARAMS_CHANGE_MESSAGE " + aVar.c());
                break;
            case 110010:
                BydLogHelper.logD("message : CAR_SEAT_RECOVER_PARAMS_CHANGE_MESSAGE " + aVar.c());
                int iC = aVar.c();
                int iD = aVar.d();
                int iE = aVar.e();
                int iF = aVar.f();
                b.b.a.h.a.b(this, "seat recover or save :  position " + iC + " location " + iD + " sate " + iE + " code " + iF);
                if (iE == 2 && iF == 1) {
                    ((d) mo7getP()).E();
                }
                if (iE == 3) {
                    int i = iC != 1 ? 6 : 7;
                    if (iF < 1 || iF > i || iC != 1 || iF == 1 || iF != 2) {
                    }
                }
                break;
            case 110011:
            case 110013:
            case 110014:
            case 110015:
            case 110016:
                BydLogHelper.logD("message : CAR_SEAT_MASSAGE_MODE_CHANGE_MESSAGE " + aVar.c());
                e eVar2 = this.s;
                if (eVar2 != null && eVar2.d0()) {
                    int iD2 = aVar.d();
                    int iC2 = aVar.c();
                    if (!b.b.g.r.e.e(aVar)) {
                        if (iD2 >= 1 && iD2 <= 7) {
                            this.s.k0(iC2, iD2);
                            break;
                        }
                    } else {
                        int iC3 = aVar.c();
                        this.s.k0(iC3, ((d) mo7getP()).j(iC3));
                        break;
                    }
                }
                break;
            case 110012:
                BydLogHelper.logD("message : CAR_SEAT_MASSAGE_LEVEL_CHANGE_MESSAGE " + aVar.c());
                e eVar3 = this.s;
                if (eVar3 != null && eVar3.d0()) {
                    int iD3 = aVar.d();
                    int iC4 = aVar.c();
                    if (!b.b.g.r.e.e(aVar)) {
                        if (iD3 >= 1 && iD3 <= 3) {
                            this.s.j0(iC4, iD3);
                            break;
                        }
                    } else {
                        int iC5 = aVar.c();
                        this.s.j0(iC5, ((d) mo7getP()).i(iC5));
                        break;
                    }
                }
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(m)) {
            return;
        }
        T();
        m = null;
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        R();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b bVar) {
        if (bVar.a() != 2) {
            e eVar = this.s;
            if (eVar != null && eVar.d0()) {
                this.s.D();
                this.s.l();
                this.s = null;
            }
            b.b.g.q.d.a aVar = this.w;
            if (aVar != null) {
                aVar.l();
                this.w = null;
            }
        }
    }
}
