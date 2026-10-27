package com.byd.carsettings.ui.fragment.carsettings;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.k;
import b.b.a.h.l;
import b.b.g.n.h.e;
import b.b.g.q.d.i;
import b.b.g.q.d.q;
import b.b.g.r.s;
import b.b.g.r.z;
import b.b.m.g.g;
import b.b.o.a.b.d;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.carsettings.LightAdapter;
import com.byd.carsettings.ui.widget.InterceptRelativeLayout;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;
import java.util.ArrayList;
import java.util.Iterator;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class CarLightsFragment extends XFragment<e> implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener, b.b.o.b.a.a, LightAdapter.b {
    public RelativeLayout A;
    public RelativeLayout B;
    public RelativeLayout D;
    public RelativeLayout E;
    public RelativeLayout F;
    public RelativeLayout G;
    public RelativeLayout H;
    public RelativeLayout I;
    public ImageView J;
    public i K;
    public Dialog L;
    public ImageView M;
    public Handler N;
    public b.b.o.a.b.a Q;
    public TextView R;
    public ImageView S;
    public RelativeLayout T;
    public InterceptRelativeLayout U;
    public RadioGroup V;
    public RecyclerView W;
    public LightAdapter Y;
    public BydSwitch m;
    public SeekBar n;
    public TextView o;
    public SeekBar p;
    public TextView q;
    public SeekBar r;
    public TextView s;
    public Context t;
    public BydSwitch u;
    public DampingScrollView v;
    public RadioButton w;
    public RadioButton x;
    public RadioGroup y;
    public BydSwitch z;
    public int O = 0;
    public int P = V(0, 0);
    public ArrayList<b.b.g.c.a.a.b> X = new ArrayList<>();

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 250002:
                    b.b.g.r.e.i(10001);
                    if (CarLightsFragment.this.n != null) {
                        CarLightsFragment carLightsFragment = CarLightsFragment.this;
                        carLightsFragment.v0(carLightsFragment.o, Math.abs((((e) CarLightsFragment.this.mo7getP()).m() == 0 ? CarLightsFragment.this.n.getProgress() : CarLightsFragment.this.n.getProgress() * 2) - 11));
                    }
                    break;
                case 250003:
                    b.b.g.r.e.i(10002);
                    if (CarLightsFragment.this.p != null) {
                        CarLightsFragment carLightsFragment2 = CarLightsFragment.this;
                        carLightsFragment2.q0(carLightsFragment2.q, CarLightsFragment.this.p.getProgress(), 0);
                    }
                    break;
                case 250004:
                    b.b.g.r.e.i(10003);
                    if (CarLightsFragment.this.r != null) {
                        CarLightsFragment carLightsFragment3 = CarLightsFragment.this;
                        carLightsFragment3.q0(carLightsFragment3.s, CarLightsFragment.this.r.getProgress(), 1);
                    }
                    break;
            }
        }
    }

    public class b implements q.b {
        public b() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            CarLightsFragment.this.L.dismiss();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.t = context;
        e0(((e) mo7getP()).B());
        Z(((e) mo7getP()).d());
        b0(((e) mo7getP()).h());
        boolean zT = ((e) mo7getP()).t();
        boolean zU = ((e) mo7getP()).u();
        boolean zR = ((e) mo7getP()).r();
        boolean zX = ((e) mo7getP()).x();
        boolean zL = ((e) mo7getP()).l();
        boolean zO = ((e) mo7getP()).o();
        c0(zL, zL || zO);
        a0(zX);
        h0(zU, zU || zR || zT);
        f0(zT);
        g0(zR);
        d0(zO);
        this.v = (DampingScrollView) this.f3717c.findViewById(R.id.nsw);
        EventBus.getDefault().register(this);
    }

    public final void T(int i) {
        b.b.g.n.q.i.k().u(i);
        b.b.g.r.e.i(10008);
    }

    public final void U() {
        l.p(null, this.y);
        l.s(null, this.n, this.p, this.r);
        l.y(null, this.m, this.u, this.z);
        l.F(null, this.R, this.S, this.T);
    }

    public int V(int i, int i2) {
        if (this.Q == null) {
            this.Q = d.a("carLights");
        }
        return this.Q.a(i, i2);
    }

    public final i W(String str) {
        if (this.K == null) {
            this.K = new i(this.t);
        }
        this.K.x(str);
        return this.K;
    }

    public int X() {
        return 11;
    }

    public final String Y(int i) {
        return (i == 60 || i == 123) ? "com.byd.mycar.ts" : "com.byd.mycar";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Z(boolean z) {
        if (z) {
            this.z = (BydSwitch) this.f3717c.findViewById(R.id.light_adapter_switch);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.afs_container);
            this.B = relativeLayout;
            l.e(relativeLayout, this.z, ((e) mo7getP()).c(), this);
        }
    }

    public final void a0(boolean z) {
        if (z) {
            this.R = (TextView) this.f3717c.findViewById(R.id.amb_light);
            this.S = (ImageView) this.f3717c.findViewById(R.id.amb_light_iv);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.amb_container);
            this.T = relativeLayout;
            l.K(true, relativeLayout);
            l.F(this, this.R, this.S, this.T);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b0(boolean z) {
        if (z) {
            this.m = (BydSwitch) this.f3717c.findViewById(R.id.carlight_switch);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.exlights_daylight_container);
            this.A = relativeLayout;
            l.e(relativeLayout, this.m, ((e) mo7getP()).f(), this);
        }
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_carsetting_rtl_carlights;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c0(boolean z, boolean z2) {
        l.K(z2, (RelativeLayout) this.f3717c.findViewById(R.id.vehicle_lamp_signal_container));
        if (z) {
            this.u = (BydSwitch) this.f3717c.findViewById(R.id.carlight_guest_switch);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.welcome_container);
            this.I = relativeLayout;
            l.e(relativeLayout, this.u, ((e) mo7getP()).j(), this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void d0(boolean z) {
        if (z) {
            RadioGroup radioGroup = (RadioGroup) this.f3717c.findViewById(R.id.carlight_rg);
            this.y = radioGroup;
            this.w = (RadioButton) radioGroup.findViewById(R.id.carlight_unlock_btn);
            this.x = (RadioButton) this.y.findViewById(R.id.carlight_lock_btn);
            this.H = (RelativeLayout) this.f3717c.findViewById(R.id.carlight_rg_container);
            l.p(this, this.y);
            l.K(true, this.H);
            l.F(this, this.w, this.x);
            r0(((e) mo7getP()).n());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void e0(boolean z) {
        if (z) {
            InterceptRelativeLayout interceptRelativeLayout = (InterceptRelativeLayout) this.f3717c.findViewById(R.id.car_light_light_toggle_control_container);
            this.U = interceptRelativeLayout;
            l.K(true, interceptRelativeLayout);
            this.V = (RadioGroup) this.f3717c.findViewById(R.id.car_light_light_toggle_control_rg);
            boolean zA = ((e) mo7getP()).A();
            l.K(zA, this.V);
            if (zA) {
                int iS = ((e) mo7getP()).s();
                RadioGroup radioGroup = this.V;
                radioGroup.check(radioGroup.getChildAt(iS - 1).getId());
                this.V.setOnCheckedChangeListener(this);
            }
            this.W = (RecyclerView) this.f3717c.findViewById(R.id.carlight_light_control_display_rv);
            boolean z2 = ((e) mo7getP()).z();
            boolean zH = ((e) mo7getP()).h();
            l.K(zA || z2 || zH, this.W);
            if (zA || z2 || zH) {
                i0(zA, z2, zH);
                if (this.Y == null) {
                    this.Y = new LightAdapter(this.t, this.X);
                }
                this.Y.setListener(this);
                this.W.setLayoutManager(new GridLayoutManager(this.t, 2));
                this.W.setAdapter(this.Y);
            }
            if (b.b.a.i.a.e()) {
                int iY = b.b.g.n.q.e.j().y();
                k.g(iY == 1, this.U);
                this.U.setIntercept(iY == 1);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void f0(boolean z) {
        if (z) {
            this.n = (SeekBar) this.f3717c.findViewById(R.id.carlight_main_light_sb);
            this.o = (TextView) this.f3717c.findViewById(R.id.carlight_main_level);
            this.D = (RelativeLayout) this.f3717c.findViewById(R.id.headlamp_container);
            this.J = (ImageView) this.f3717c.findViewById(R.id.carlight_main_light_img);
            this.n.setMax(((e) mo7getP()).m() == 0 ? 10 : 5);
            l.F(this, this.J);
            l.K(true, this.D);
            l.s(this, this.n);
            u0(Math.abs(((e) mo7getP()).v() - X()), false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void g0(boolean z) {
        if (z) {
            this.p = (SeekBar) this.f3717c.findViewById(R.id.carlight_home_light_sb);
            this.q = (TextView) this.f3717c.findViewById(R.id.carlight_home_level);
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.back_home_container);
            this.E = relativeLayout;
            l.K(true, relativeLayout);
            l.s(this, this.p);
            o0(((e) mo7getP()).e());
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_carlights;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void h0(boolean z, boolean z2) {
        if (z2) {
            RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.light_container);
            this.G = relativeLayout;
            l.K(true, relativeLayout);
        }
        if (z) {
            this.r = (SeekBar) this.f3717c.findViewById(R.id.carlight_leave_light_sb);
            this.s = (TextView) this.f3717c.findViewById(R.id.carlight_leave_level);
            this.F = (RelativeLayout) this.f3717c.findViewById(R.id.leave_home_container);
            l.s(this, this.r);
            l.K(true, this.F);
            s0(((e) mo7getP()).q());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void i0(boolean z, boolean z2, boolean z3) {
        ArrayList<b.b.g.c.a.a.b> arrayList = this.X;
        if (arrayList != null && arrayList.size() > 0) {
            this.X.clear();
        }
        if (z) {
            this.X.add(new b.b.g.c.a.a.b(R.drawable.selector_light_rb_rear_fog_drawable, "rear_fog_light", R.string.vehiclesettings_light_control_light_state_rear_fog_light, ((e) mo7getP()).w() == 1));
        }
        if (z2) {
            this.X.add(new b.b.g.c.a.a.b(R.drawable.selector_light_rb_front_fog_drawable, "front_fog_light", R.string.vehiclesettings_light_control_light_state_front_fog_light, ((e) mo7getP()).i() == 1));
        }
        if (z3) {
            this.X.add(new b.b.g.c.a.a.b(R.drawable.selector_light_rb_day_time_drawable, "day_time_light", R.string.vehiclesettings_light_control_light_state_rear_day_light, ((e) mo7getP()).g() == 1));
        }
    }

    public void j0(String str) {
        if (!g.d(this.t, Y(b.b.g.n.q.b.c().a()))) {
            Context context = this.t;
            z.c(context, context.getString(R.string.vehiclesettings_amb_light_jump));
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CAR");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse(str));
        startActivity(intent);
        BydLogHelper.logD(this.f3715a, "jump to mycar");
    }

    public void k0(int i) {
        this.O = i;
        b.b.o.a.c.a.b(getClass().getSimpleName(), Integer.valueOf(i));
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
    public e newP() {
        return new e();
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_carsetting_right_carlights;
    }

    public void m0(Context context, int i, boolean z) {
        if (z) {
            l.j(this.M, i, context);
        }
    }

    @Override // com.byd.carsettings.adapter.carsettings.LightAdapter.b
    public void n(int i, boolean z) {
        ArrayList<b.b.g.c.a.a.b> arrayList = this.X;
        if (arrayList == null || arrayList.size() <= 0) {
            BydLogHelper.logD(getClass().getSimpleName(), " lightList is null or size <= 0");
        }
        w0(this.X.get(i).c(), z);
    }

    public final void n0(String str, boolean z) {
        for (b.b.g.c.a.a.b bVar : this.X) {
            if (bVar.c().equals(str)) {
                bVar.e(z);
            }
        }
        this.Y.notifyDataSetChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void o0(int i) {
        if (((e) mo7getP()).r()) {
            l.q(this.p, i);
            l.B(this.q, i == 0 ? this.t.getResources().getString(R.string.vehiclesettings_close) : String.format(this.t.getResources().getString(R.string.vehiclesettings_placehodler), String.valueOf(i * 10), this.t.getResources().getString(R.string.vehiclesettings_leave_home_delay_time_unit)));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        Iterator<String> it = b.b.g.q.d.k.b().iterator();
        while (it.hasNext()) {
            if (it.next().startsWith("light_")) {
                x0();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id == R.id.carlight_guest_switch) {
            if (l.a(this.u)) {
                b.b.g.r.e.i(10004);
                ((e) mo7getP()).G(z);
                k0(2);
                m0(this.t, V(2, z ? 1 : 2), true);
                return;
            }
            return;
        }
        if (id == R.id.carlight_switch) {
            if (l.a(this.m)) {
                b.b.g.r.e.i(10000);
                ((e) mo7getP()).E(z);
                k0(0);
                m0(this.t, V(0, 0), true);
                return;
            }
            return;
        }
        if (id == R.id.light_adapter_switch && l.a(this.z)) {
            b.b.g.r.e.i(10006);
            ((e) mo7getP()).D(z);
            k0(3);
            m0(this.t, V(3, 0), true);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.carlight_main_light_img) {
            x0();
        }
        switch (id) {
            case R.id.amb_container /* 2131296382 */:
            case R.id.amb_light /* 2131296383 */:
            case R.id.amb_light_iv /* 2131296384 */:
                j0("mycar://byd.car.ambientlight");
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        b.b.g.r.e.a(this.N);
        U();
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Dialog dialog = this.L;
        if (dialog != null) {
            dialog.dismiss();
            this.L = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        switch (aVar.h()) {
            case 10000:
                BydLogHelper.logD("DAY_LIGHT_STATE_MESSAGE :" + aVar.c());
                int iC = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC = ((e) mo7getP()).f() ? 1 : 2;
                }
                if (iC == 1 || iC == 2) {
                    l.f(this.m, iC == 1);
                }
                break;
            case 10001:
                BydLogHelper.logD("HEADLAMP_HEIGHT_MESSAGE :" + aVar.c());
                int iC2 = aVar.c();
                if (b.b.g.r.e.e(aVar) && (iC2 = ((e) mo7getP()).v()) == 0) {
                    u0(0, false);
                    m0(this.t, this.P, this.O == 1);
                }
                if (iC2 != 0) {
                    u0(Math.abs(iC2 - X()), false);
                    m0(this.t, this.P, !b.b.g.r.e.e(aVar) || this.O == 1);
                }
                break;
            case 10002:
                BydLogHelper.logD("BACK_LIGHT_DELAY_MESSAGE :" + aVar.c());
                int iC3 = aVar.c();
                if (b.b.g.r.e.e(aVar) && (iC3 = ((e) mo7getP()).e()) == 0) {
                    o0(iC3);
                }
                if (iC3 != 0) {
                    if (iC3 != 7) {
                        o0(Math.abs(iC3));
                    } else {
                        o0(Math.abs(iC3 - 7));
                    }
                }
                break;
            case 10003:
                BydLogHelper.logD("setLeftHomeDelayView :" + aVar.c());
                int iC4 = aVar.c();
                if (b.b.g.r.e.e(aVar) && (iC4 = ((e) mo7getP()).q()) == 0) {
                    s0(iC4);
                }
                if (iC4 != 0) {
                    if (iC4 != 7) {
                        s0(Math.abs(iC4));
                    } else {
                        s0(Math.abs(iC4 - 7));
                    }
                }
                break;
            case 10004:
                int iC5 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    int i = ((e) mo7getP()).j() ? 1 : 2;
                    l.f(this.u, i == 1);
                    m0(this.t, V(2, i), this.O == 2);
                } else if (iC5 == 1 || iC5 == 2) {
                    l.f(this.u, iC5 == 1);
                    m0(this.t, V(2, iC5), true);
                }
                break;
            case 10005:
                int iC6 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    int iN = ((e) mo7getP()).n();
                    if (iN >= 1 && iN <= 3) {
                        r0(iN);
                        m0(this.t, V(4, iN), this.O == 4);
                        break;
                    }
                } else if (iC6 >= 1 && iC6 <= 3) {
                    r0(iC6);
                    m0(this.t, V(4, iC6), true);
                    break;
                }
                break;
            case 10006:
                int iC7 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC7 = ((e) mo7getP()).c() ? 1 : 2;
                }
                if (iC7 == 2 || iC7 == 1) {
                    l.f(this.z, iC7 == 1);
                }
                break;
            case 10008:
                BydLogHelper.logD(getClass().getSimpleName(), "LIGHT_CONTROL_STATE_CHANGE_MESSAGE :" + aVar.c());
                int iC8 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC8 = ((e) mo7getP()).s();
                }
                if (iC8 >= 1 && iC8 <= 4) {
                    this.V.setOnCheckedChangeListener(null);
                    ((RadioButton) this.V.getChildAt(iC8 - 1)).setChecked(true);
                    this.V.setOnCheckedChangeListener(this);
                    break;
                }
                break;
            case 10009:
                BydLogHelper.logD(getClass().getSimpleName(), "LIGHT_CONTROL_STATE_CHANGE_MESSAGE :" + aVar.c());
                int iC9 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC9 = ((e) mo7getP()).w();
                    BydLogHelper.logD("rearFog :" + iC9);
                }
                if (iC9 >= 1 && iC9 <= 2) {
                    n0("rear_fog_light", iC9 == 1);
                    break;
                }
                break;
            case 10010:
                BydLogHelper.logD(getClass().getSimpleName(), "FRONT_FOG_LIGHT_STATE_CHANGE_MESSAGE :" + aVar.c());
                int iC10 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC10 = ((e) mo7getP()).i();
                    BydLogHelper.logD("frontFog :" + iC10);
                }
                if (iC10 >= 1 && iC10 <= 2) {
                    n0("front_fog_light", iC10 == 1);
                    break;
                }
                break;
            case 10011:
                BydLogHelper.logD(getClass().getSimpleName(), "DAY_LIGHT_STATE_CHANGE_MESSAGE :" + aVar.c());
                int iC11 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC11 = ((e) mo7getP()).g();
                    BydLogHelper.logD("dayLight :" + iC11);
                }
                if (iC11 >= 1 && iC11 <= 2) {
                    n0("day_time_light", iC11 == 1);
                    break;
                }
                break;
            case 10012:
                BydLogHelper.logD(getClass().getSimpleName(), "ROLL_BENCH_MODE_STATUS_MESSAGE :" + aVar.c());
                int iC12 = aVar.c();
                BydLogHelper.logD(getClass().getSimpleName(), "VehicleConfigUtils.isUkeType() :" + b.b.a.i.a.e());
                if (b.b.a.i.a.e()) {
                    k.g(iC12 == 1, this.U);
                    this.U.setIntercept(iC12 == 1);
                }
                break;
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            this.v.setScroll(false);
            int id = seekBar.getId();
            if (id == R.id.carlight_home_light_sb) {
                b.b.g.r.e.n(250003, this.N);
                b.b.g.r.e.i(10002);
                k0(3);
                o0(i);
                m0(this.t, V(3, 5), true);
                return;
            }
            if (id == R.id.carlight_leave_light_sb) {
                b.b.g.r.e.n(250004, this.N);
                b.b.g.r.e.i(10003);
                k0(3);
                s0(i);
                m0(this.t, V(3, 5), true);
                return;
            }
            if (id != R.id.carlight_main_light_sb) {
                return;
            }
            b.b.g.r.e.n(250002, this.N);
            b.b.g.r.e.i(10001);
            k0(1);
            t0(Math.abs(i));
            m0(this.t, this.P, true);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        int id = seekBar.getId();
        if (id == R.id.carlight_home_light_sb) {
            b.b.g.r.e.i(10002);
        } else if (id == R.id.carlight_leave_light_sb) {
            b.b.g.r.e.i(10003);
        } else {
            if (id != R.id.carlight_main_light_sb) {
                return;
            }
            b.b.g.r.e.i(10001);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        this.v.setScroll(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void p0() {
        this.M = (ImageView) this.f3717c.findViewById(R.id.carlight_image);
        int iA = b.b.o.a.c.a.a(getClass().getSimpleName());
        if (iA == 0) {
            m0(this.t, V(iA, 0), true);
            return;
        }
        if (iA == 1) {
            u0(Math.abs(((e) mo7getP()).v() - X()), false);
            m0(this.t, this.P, true);
            return;
        }
        if (iA == 2) {
            m0(this.t, V(iA, ((e) mo7getP()).j() ? 1 : 2), true);
        } else if (iA == 3) {
            m0(this.t, V(iA, 5), true);
        } else {
            if (iA != 4) {
                return;
            }
            m0(this.t, V(4, ((e) mo7getP()).n()), true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void q0(TextView textView, int i, int i2) {
        if (i == 0) {
            i = 7;
        }
        if (i2 == 0) {
            ((e) mo7getP()).H(i);
        } else {
            ((e) mo7getP()).J(i);
        }
        this.P = V(0, 0);
    }

    public final void r0(int i) {
        RadioButton radioButton = this.x;
        Context context = this.t;
        l.c(radioButton, i == 3 ? context.getDrawable(R.drawable.vehicle_selector_dialog_blue) : context.getDrawable(R.drawable.vehicle_selector_dialog_white));
        l.c(this.w, i == 2 ? this.t.getDrawable(R.drawable.vehicle_selector_dialog_blue) : this.t.getDrawable(R.drawable.vehicle_selector_dialog_white));
        l.B(this.x, i == 3 ? this.t.getResources().getString(R.string.vehiclesettings_previewing) : this.t.getResources().getString(R.string.vehiclesettings_lock_welcome));
        l.B(this.w, i == 2 ? this.t.getResources().getString(R.string.vehiclesettings_previewing) : this.t.getResources().getString(R.string.vehiclesettings_unlock_welcome));
        l.x(this.x, i == 3);
        l.x(this.w, i == 2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void s0(int i) {
        if (((e) mo7getP()).u()) {
            BydLogHelper.logD("setLeftHomeDelayView :" + i);
            l.q(this.r, i);
            l.B(this.s, i == 0 ? this.t.getResources().getString(R.string.vehiclesettings_close) : String.format(this.t.getResources().getString(R.string.vehiclesettings_placehodler), String.valueOf(i * 10), this.t.getResources().getString(R.string.vehiclesettings_leave_home_delay_time_unit)));
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        s.a(this.v, i, b.b.m.e.i.a.h(getClass().getName()));
        b.b.m.e.i.a.n(getClass().getName());
    }

    public final void t0(int i) {
        u0(i, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void u0(int i, boolean z) {
        String strA;
        int iM = ((e) mo7getP()).m();
        if (!((e) mo7getP()).t() || i < 0) {
            return;
        }
        if (!z) {
            l.q(this.n, (iM == 0 || z) ? i : i / 2);
        }
        if (iM == 1) {
            this.P = V(1, z ? i : i / 2);
            if (!z) {
                i /= 2;
            }
            strA = String.valueOf(Math.abs(i - 5));
        } else if (i % 2 == 0) {
            String strValueOf = String.valueOf((int) (Math.abs(i - 10) * 0.5f));
            if (i == 0) {
                this.P = V(1, 0);
            } else {
                this.P = V(1, i / 2);
            }
            strA = strValueOf;
        } else {
            this.P = V(1, (i + 1) / 2);
            strA = b.b.g.r.i.a(Math.abs(i - 10) * 0.5f);
        }
        TextView textView = this.o;
        l.B(textView, String.format(textView.getContext().getResources().getString(R.string.vehiclesettings_placehodler), strA, this.t.getResources().getString(R.string.vehiclesettings_lamp_height_adjust_unit)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void v0(TextView textView, int i) {
        if (textView != null) {
            ((e) mo7getP()).K(i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void w0(String str, boolean z) {
        str.hashCode();
        switch (str) {
            case "rear_fog_light":
                ((e) mo7getP()).L(z);
                b.b.g.r.e.i(10009);
                break;
            case "day_time_light":
                ((e) mo7getP()).E(z);
                b.b.g.r.e.i(10011);
                break;
            case "front_fog_light":
                ((e) mo7getP()).F(z);
                b.b.g.r.e.i(10010);
                break;
        }
    }

    public final void x0() {
        this.L = W("light_main_level").B(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_lamp_height_adjust, R.string.vehiclesettings_light_main_level_tips, -1, -1, R.string.vehiclesettings_button_confirm, new b(), -1, null);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        p0();
        this.N = new a(Looper.myLooper());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int i2 = 2;
        int i3 = 3;
        switch (i) {
            case R.id.carlight_lock_btn /* 2131296587 */:
                this.x.setChecked(false);
                if (((e) mo7getP()).n() == 3) {
                    ((e) mo7getP()).I(1);
                    r0(1);
                    k0(4);
                    i3 = 1;
                } else {
                    ((e) mo7getP()).I(3);
                    r0(3);
                    k0(4);
                }
                m0(this.t, V(4, i3), true);
                break;
            case R.id.carlight_unlock_btn /* 2131296597 */:
                this.w.setChecked(false);
                if (((e) mo7getP()).n() == 2) {
                    ((e) mo7getP()).I(1);
                    r0(1);
                    k0(4);
                    i2 = 1;
                } else {
                    ((e) mo7getP()).I(2);
                    r0(2);
                    k0(4);
                }
                m0(this.t, V(4, i2), true);
                b.b.g.r.e.i(10005);
                break;
            case R.id.light_auto /* 2131297392 */:
                T(2);
                break;
            case R.id.light_close /* 2131297393 */:
                T(1);
                break;
            case R.id.light_near /* 2131297397 */:
                T(4);
                break;
            case R.id.light_small /* 2131297399 */:
                T(3);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.j.b.b bVar) {
        Dialog dialog = this.L;
        if (dialog != null) {
            dialog.dismiss();
            this.L = null;
        }
    }
}
