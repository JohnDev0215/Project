package com.byd.carsettings.ui.fragment.newenergy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.g.g.d;
import b.b.g.q.d.l;
import b.b.g.q.d.m;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.db.model.EnergyBean;
import com.byd.carsettings.ui.widget.bydenergychart.BydEnergyChartView;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ConsumptionManageFragment extends XFragment<b.b.g.n.n.a> implements View.OnClickListener, SegmentedButtonGroup.f, b.b.o.b.a.a {
    public static final String m = ConsumptionManageFragment.class.getSimpleName();
    public static boolean n = false;
    public String I;
    public String J;
    public String K;
    public String L;
    public String M;
    public String N;
    public String O;
    public b.b.g.g.d P;
    public i Q;
    public h T;
    public CheckBox o;
    public CheckBox p;
    public CheckBox q;
    public CheckBox r;
    public l s;
    public BydEnergyChartView t;
    public TextView u;
    public TextView v;
    public TextView w;
    public SegmentedButtonGroup x;
    public ScrollView y;
    public final String z = "ConsumptionManageFragment_label_data";
    public final String A = "ConsumptionManageFragment_label_chart";
    public List<EnergyBean> B = new ArrayList();
    public List<EnergyBean> D = new ArrayList();
    public List<Float> E = new ArrayList();
    public List<Float> F = new ArrayList();
    public List<Float> G = new ArrayList();
    public boolean H = false;
    public boolean R = false;
    public boolean S = true;
    public CompoundButton.OnCheckedChangeListener U = new g();

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3204a;

        public a(int i) {
            this.f3204a = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            List<EnergyBean> listY;
            try {
                if (ConsumptionManageFragment.n) {
                    return;
                }
                boolean unused = ConsumptionManageFragment.n = true;
                ConsumptionManageFragment.this.B.clear();
                if (((b.b.g.n.n.a) ConsumptionManageFragment.this.mo7getP()).y() && (listY = ConsumptionManageFragment.this.P.y()) != null) {
                    ConsumptionManageFragment.this.B.addAll(listY);
                }
                List<EnergyBean> listS = ConsumptionManageFragment.this.P.S();
                ConsumptionManageFragment.this.D.clear();
                if (listS != null) {
                    if (((b.b.g.n.n.a) ConsumptionManageFragment.this.mo7getP()).w()) {
                        for (int i = 0; i < listS.size(); i++) {
                            listS.get(i).b(listS.get(i).a() / 10.0d);
                        }
                    }
                    ConsumptionManageFragment.this.D.addAll(listS);
                }
                ConsumptionManageFragment.this.T.removeMessages(this.f3204a);
                ConsumptionManageFragment.this.T.sendEmptyMessage(this.f3204a);
                boolean unused2 = ConsumptionManageFragment.n = false;
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }
    }

    public class b implements RadioGroup.OnCheckedChangeListener {
        public b() {
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.rg_electricity) {
                ConsumptionManageFragment.this.p0(1);
            } else if (i == R.id.rg_fuel) {
                ConsumptionManageFragment.this.p0(2);
            } else {
                ConsumptionManageFragment.this.p0(0);
            }
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((b.b.g.n.n.a) ConsumptionManageFragment.this.mo7getP()).C(1);
            try {
                if (ConsumptionManageFragment.this.P != null) {
                    ConsumptionManageFragment.this.P.q();
                    if (ConsumptionManageFragment.this.T != null) {
                        ConsumptionManageFragment.this.T.removeMessages(4);
                        ConsumptionManageFragment.this.T.sendEmptyMessageDelayed(4, 1000L);
                    }
                }
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.f1481c.clear();
            ((b.b.g.n.n.a) ConsumptionManageFragment.this.mo7getP()).D(ConsumptionManageFragment.this.o.isChecked() ? 1 : 0, ConsumptionManageFragment.this.p.isChecked() ? 1 : 0, ConsumptionManageFragment.this.q.isChecked() ? 1 : 0, ConsumptionManageFragment.this.r.isChecked() ? 1 : 0);
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.f1481c.clear();
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ConsumptionManageFragment.this.o.setOnCheckedChangeListener(null);
            ConsumptionManageFragment.this.p.setOnCheckedChangeListener(null);
            ConsumptionManageFragment.this.q.setOnCheckedChangeListener(null);
            ConsumptionManageFragment.this.r.setOnCheckedChangeListener(null);
        }
    }

    public class g implements CompoundButton.OnCheckedChangeListener {
        public g() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            m.f1481c.put("ConsumptionManageFragment_label_data_1", Boolean.valueOf(ConsumptionManageFragment.this.o.isChecked()));
            m.f1481c.put("ConsumptionManageFragment_label_data_2", Boolean.valueOf(ConsumptionManageFragment.this.p.isChecked()));
            m.f1481c.put("ConsumptionManageFragment_label_data_3", Boolean.valueOf(ConsumptionManageFragment.this.q.isChecked()));
            m.f1481c.put("ConsumptionManageFragment_label_data_4", Boolean.valueOf(ConsumptionManageFragment.this.r.isChecked()));
            ConsumptionManageFragment.this.s.s(ConsumptionManageFragment.this.o.isChecked() || ConsumptionManageFragment.this.p.isChecked() || ConsumptionManageFragment.this.q.isChecked() || ConsumptionManageFragment.this.r.isChecked());
        }
    }

    public static class h extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<ConsumptionManageFragment> f3212a;

        public h(ConsumptionManageFragment consumptionManageFragment) {
            this.f3212a = new WeakReference<>(consumptionManageFragment);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (this.f3212a.get() == null || this.f3212a.get().t == null) {
                return;
            }
            try {
                BydLogHelper.logI(ConsumptionManageFragment.m, "bydEnergyChartView,handler,msg.what = " + message.what);
                int i = message.what;
                if (i != 1 && i != 2) {
                    if (i == 4) {
                        this.f3212a.get().k0(2);
                        return;
                    } else {
                        if (i == 3) {
                            this.f3212a.get().k0(1);
                            return;
                        }
                        return;
                    }
                }
                this.f3212a.get().t.setListXY(this.f3212a.get().G, this.f3212a.get().E, this.f3212a.get().F, this.f3212a.get().D, this.f3212a.get().B);
                this.f3212a.get().f0(((b.b.g.n.n.a) this.f3212a.get().mo7getP()).j());
                if (!((b.b.g.n.n.a) this.f3212a.get().mo7getP()).w()) {
                    this.f3212a.get().g0(((b.b.g.n.n.a) this.f3212a.get().mo7getP()).l());
                }
                if (message.what == 1) {
                    removeMessages(3);
                    sendEmptyMessageDelayed(3, 60000L);
                }
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }
    }

    public class i implements ServiceConnection {
        public i() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                ConsumptionManageFragment.this.R = true;
                ConsumptionManageFragment.this.P = d.a.d0(iBinder);
                if (ConsumptionManageFragment.this.P != null) {
                    ConsumptionManageFragment.this.k0(1);
                }
            } catch (Exception e2) {
                b.b.m.c.a.c(ConsumptionManageFragment.m, e2.getMessage());
                BydLogHelper.logE(e2.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ConsumptionManageFragment.this.R = false;
            ConsumptionManageFragment.this.s0();
        }

        public /* synthetic */ i(ConsumptionManageFragment consumptionManageFragment, a aVar) {
            this();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        ScrollView scrollView = (ScrollView) this.f3717c.findViewById(R.id.scrollView);
        this.y = scrollView;
        scrollView.setOverScrollMode(2);
        this.t = (BydEnergyChartView) this.f3717c.findViewById(R.id.chart_energy);
        this.u = (TextView) this.f3717c.findViewById(R.id.tv_chart_title_left);
        this.v = (TextView) this.f3717c.findViewById(R.id.tv_chart_title_right);
        this.x = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.sbg_mile);
        this.w = (TextView) this.f3717c.findViewById(R.id.tv_table_name);
        this.T = new h(this);
        n = false;
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_consumption_manage;
    }

    public final void e0() {
        try {
            this.Q = new i(this, null);
            Intent intent = new Intent();
            intent.setAction("com.byd.aidl");
            intent.setPackage("com.byd.uereport");
            getContext().bindService(intent, this.Q, 1);
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void f0(float f2) {
        ((b.b.g.n.n.a) mo7getP()).m();
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(getResources().getString(R.string.newenergy_average_elec_unit_kw_km));
        String string = sb.toString();
        if (this.t != null) {
            String string2 = getResources().getString(R.string.newenergy_consumption_line_average_elec_title);
            if (f2 == -1.0f) {
                TextView textView = this.u;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(string2);
                sb2.append(string2.length() > 10 ? BydAdbHelper.COMMAND_LINE_END : " ");
                sb2.append("--");
                sb2.append(string);
                textView.setText(sb2.toString());
                this.t.setElecLine(BydEnergyChartView.f3468a);
                return;
            }
            TextView textView2 = this.u;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(string2);
            sb3.append(string2.length() > 10 ? BydAdbHelper.COMMAND_LINE_END : " ");
            sb3.append(String.format("%.1f", Float.valueOf(f2)));
            sb3.append(string);
            textView2.setText(sb3.toString());
            this.t.setElecLine(f2);
        }
    }

    public void g0(float f2) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(getResources().getString(R.string.newenergy_average_fuel_unit));
        String string = sb.toString();
        if (this.t != null) {
            String string2 = getResources().getString(R.string.newenergy_consumption_line_average_fuel_title);
            if (f2 == -1.0f) {
                TextView textView = this.v;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(string2);
                sb2.append(string2.length() > 10 ? BydAdbHelper.COMMAND_LINE_END : " ");
                sb2.append("--");
                sb2.append(string);
                textView.setText(sb2.toString());
                this.t.setFuelLine(BydEnergyChartView.f3468a);
                return;
            }
            TextView textView2 = this.v;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(string2);
            sb3.append(string2.length() > 10 ? BydAdbHelper.COMMAND_LINE_END : " ");
            sb3.append(String.format("%.1f", Float.valueOf(f2)));
            sb3.append(string);
            textView2.setText(sb3.toString());
            this.t.setFuelLine(f2);
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_consumption_manage;
    }

    public void h0(int i2, int i3) {
        this.t.setDrawType(-1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void i0() {
        Resources resources;
        int i2;
        Resources resources2;
        int i3;
        if (((b.b.g.n.n.a) mo7getP()).o() == 2) {
            resources = getResources();
            i2 = R.string.newenergy_speed_unit_mph;
        } else {
            resources = getResources();
            i2 = R.string.newenergy_speed_unit_km;
        }
        this.O = resources.getString(i2);
        int i4 = ((b.b.g.n.n.a) mo7getP()).i();
        if (i4 == 3 || i4 == 4) {
            this.M = getResources().getString(R.string.consumption_unit_l);
        } else {
            this.M = getResources().getString(R.string.consumption_l);
        }
        if (this.f3717c.findViewById(R.id.ll_list_total_fuel).getVisibility() == 0) {
            ((TextView) this.f3717c.findViewById(R.id.tv_oil_total_value)).setText(((b.b.g.n.n.a) mo7getP()).t() + "");
            ((TextView) this.f3717c.findViewById(R.id.tv_oil_total_unit)).setText(this.M);
        }
        if (this.f3717c.findViewById(R.id.ll_list_charge_elec).getVisibility() == 0) {
            if (((b.b.g.n.n.a) mo7getP()).n() == 2) {
                resources2 = getResources();
                i3 = R.string.newenergy_elec_unit_hp;
            } else {
                resources2 = getResources();
                i3 = R.string.newenergy_elec_unit_kw;
            }
            this.N = resources2.getString(i3);
            ((TextView) this.f3717c.findViewById(R.id.tv_elec_total_value)).setText(((b.b.g.n.n.a) mo7getP()).r() + "");
            ((TextView) this.f3717c.findViewById(R.id.tv_elec_total_unit)).setText(this.N);
        }
        ((TextView) this.f3717c.findViewById(R.id.tv_time_travel_value)).setText(((b.b.g.n.n.a) mo7getP()).h() + "");
        ((TextView) this.f3717c.findViewById(R.id.tv_average_speed_value)).setText(((b.b.g.n.n.a) mo7getP()).e() + "");
        ((TextView) this.f3717c.findViewById(R.id.tv_average_speed_unit)).setText(this.O);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void j0() {
        Resources resources;
        int i2;
        TextView textView;
        boolean z = ((b.b.g.n.n.a) mo7getP()).m() == 2;
        this.J = getResources().getString(z ? R.string.newenergy_mile_unit_miles : R.string.newenergy_mile_unit);
        if (((b.b.g.n.n.a) mo7getP()).n() == 2) {
            resources = getResources();
            i2 = z ? R.string.newenergy_average_elec_unit_hp_mile : R.string.newenergy_average_elec_unit_hp_km;
        } else {
            resources = getResources();
            i2 = z ? R.string.newenergy_average_elec_unit_kw_mile : R.string.newenergy_average_elec_unit_kw_km;
        }
        this.K = resources.getString(i2);
        ((TextView) this.f3717c.findViewById(R.id.tv_mile_total_value)).setText(((b.b.g.n.n.a) mo7getP()).u());
        ((TextView) this.f3717c.findViewById(R.id.tv_mile_total_unit)).setText(this.J);
        if (b.b.g.r.m.b(getContext()) && (textView = (TextView) this.f3717c.findViewById(R.id.tv_mile_total_name)) != null) {
            float fA = b.b.g.r.m.a(((b.b.g.n.n.a) mo7getP()).u(), getResources().getDimensionPixelSize(R.dimen.sp_30));
            float fA2 = b.b.g.r.m.a(this.J, getResources().getDimensionPixelSize(R.dimen.sp_16));
            String str = m;
            StringBuilder sb = new StringBuilder();
            sb.append("lengthValue = ");
            sb.append(fA);
            sb.append(", lengthUnit = ");
            sb.append(fA2);
            sb.append(", maxWidth = ");
            int dimensionPixelSize = (int) (((getResources().getDimensionPixelSize(R.dimen.dp_286) - fA) - fA2) - getResources().getDimensionPixelSize(R.dimen.dp_16));
            sb.append(dimensionPixelSize);
            BydLogHelper.logI(str, sb.toString());
            textView.setMaxWidth(dimensionPixelSize);
        }
        if (this.f3717c.findViewById(R.id.ll_dm).getVisibility() == 0) {
            int i3 = ((b.b.g.n.n.a) mo7getP()).i();
            if (i3 == 3 || i3 == 4) {
                this.L = getResources().getString(R.string.newenergy_average_fuel_unit_mpg);
            } else {
                this.L = getResources().getString(R.string.newenergy_average_fuel_unit);
            }
            ((TextView) this.f3717c.findViewById(R.id.tv_oil_total_average_value)).setText(((b.b.g.n.n.a) mo7getP()).s());
            ((TextView) this.f3717c.findViewById(R.id.tv_oil_total_average_unit)).setText(this.L);
        }
        ((TextView) this.f3717c.findViewById(R.id.tv_mile_endurance_value)).setText(((b.b.g.n.n.a) mo7getP()).g());
        ((TextView) this.f3717c.findViewById(R.id.tv_elec_total_average_value)).setText(((b.b.g.n.n.a) mo7getP()).q() + "");
        ((TextView) this.f3717c.findViewById(R.id.tv_mile_endurance_unit)).setText(((b.b.g.n.n.a) mo7getP()).f(getContext()));
        ((TextView) this.f3717c.findViewById(R.id.tv_elec_total_average_unit)).setText(this.K);
    }

    public final void k0(int i2) {
        b.b.a.f.h.a().j(new a(i2));
    }

    public final void l0(boolean z) {
        this.f3717c.findViewById(R.id.tv_data_clear).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.tv_chart_clear).setOnClickListener(z ? this : null);
        this.x.setOnPositionChangedListener(z ? this : null);
        ((RadioGroup) this.f3717c.findViewById(R.id.rg_mode)).setOnCheckedChangeListener(z ? new b() : null);
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_consumption_manage_byd;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void m0() {
        String string;
        this.f3717c.findViewById(R.id.ll_dm).setVisibility(((b.b.g.n.n.a) mo7getP()).A() ? 0 : 8);
        this.f3717c.findViewById(R.id.tv_endurance_mileage).setVisibility(((b.b.g.n.n.a) mo7getP()).A() ? 0 : 8);
        this.f3717c.findViewById(R.id.rg_mode).setVisibility(((b.b.g.n.n.a) mo7getP()).y() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_total_mileage).setVisibility(((b.b.g.n.n.a) mo7getP()).B() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_list_charge_elec).setVisibility(((b.b.g.n.n.a) mo7getP()).x() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_list_total_fuel).setVisibility(((b.b.g.n.n.a) mo7getP()).z() ? 0 : 8);
        ((TextView) this.f3717c.findViewById(R.id.tv_list_average_title)).setText(getResources().getString(((b.b.g.n.n.a) mo7getP()).y() ? R.string.newenergy_consumption_list_average : R.string.newenergy_consumption_list_average_elec));
        p0(!((b.b.g.n.n.a) mo7getP()).y() ? 1 : 0);
        this.B.clear();
        this.D.clear();
        boolean zD = ((b.b.g.n.n.a) mo7getP()).d();
        this.H = zD;
        this.x.setVisibility(zD ? 8 : 0);
        this.I = getResources().getString(R.string.newenergy_km_unit);
        if (this.H) {
            string = getResources().getString(R.string.newenergy_consumption_line_table);
        } else {
            string = String.format(getResources().getString(R.string.consumption_line_table), "50" + this.I);
        }
        this.w.setText(string);
        this.t.setDrawType(1 ^ (((b.b.g.n.n.a) mo7getP()).y() ? 1 : 0));
        this.x.setPosition(2);
        o0(((b.b.g.n.n.a) mo7getP()).y() ? 10.0f : 15.0f, 5.0f, 10.0f, this.H);
        this.t.setXUnit(this.I);
        this.t.setListXY(this.G, this.E, this.F, this.D, this.B);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.n.a newP() {
        return new b.b.g.n.n.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void o0(float f2, float f3, float f4, boolean z) {
        this.t.setXYInterval(f2, f3, f4);
        this.E.clear();
        this.E.add(Float.valueOf(((b.b.g.n.n.a) mo7getP()).y() ? -20.0f : -30.0f));
        this.E.add(Float.valueOf(0.0f));
        this.E.add(Float.valueOf(f2));
        this.E.add(Float.valueOf(f2 * 2.0f));
        this.E.add(Float.valueOf(f2 * 3.0f));
        this.E.add(Float.valueOf(f2 * 4.0f));
        this.F.clear();
        this.F.add(Float.valueOf(-f3));
        this.F.add(Float.valueOf(0.0f));
        this.F.add(Float.valueOf(f3));
        this.F.add(Float.valueOf(f3 * 2.0f));
        this.F.add(Float.valueOf(f3 * 3.0f));
        this.F.add(Float.valueOf(f3 * 4.0f));
        this.G.clear();
        this.G.add(Float.valueOf(5.0f * f4));
        this.G.add(Float.valueOf(4.0f * f4));
        this.G.add(Float.valueOf(3.0f * f4));
        this.G.add(Float.valueOf(2.0f * f4));
        this.G.add(Float.valueOf(f4));
        this.G.add(Float.valueOf(0.0f));
        if (z) {
            Collections.reverse(this.G);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        try {
            for (String str : m.f1480b) {
                byte b2 = -1;
                int iHashCode = str.hashCode();
                if (iHashCode != -1279742908) {
                    if (iHashCode == 1067120260 && str.equals("ConsumptionManageFragment_label_data")) {
                        b2 = 0;
                    }
                } else if (str.equals("ConsumptionManageFragment_label_chart")) {
                    b2 = 1;
                }
                if (b2 == 0) {
                    r0();
                } else if (b2 == 1) {
                    q0();
                }
            }
        } catch (Exception e2) {
            b.b.m.c.a.c(m, "label----" + e2.getMessage());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (b.b.m.g.c.a(300L)) {
            return;
        }
        int id = view.getId();
        if (id == R.id.tv_chart_clear) {
            q0();
        } else {
            if (id != R.id.tv_data_clear) {
                return;
            }
            r0();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        l0(false);
        s0();
        h hVar = this.T;
        if (hVar != null) {
            hVar.removeCallbacksAndMessages(null);
            this.T = null;
        }
        super.onDestroyView();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        h hVar = this.T;
        if (hVar != null) {
            hVar.removeCallbacksAndMessages(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i2) {
        if (view.getId() != R.id.sbg_mile) {
            return;
        }
        this.x.setPosition(i2, false);
        try {
            if (i2 == 0) {
                o0(((b.b.g.n.n.a) mo7getP()).y() ? 10.0f : 15.0f, 5.0f, 2.0f, false);
                this.t.setListXY(this.G, this.E, this.F, this.D.subList(0, this.D.size() < 51 ? this.D.size() : 51), this.B.subList(0, this.B.size() < 51 ? this.D.size() : 51));
                this.w.setText(String.format(getResources().getString(R.string.consumption_line_table), "10" + this.I));
                return;
            }
            if (i2 == 1) {
                o0(((b.b.g.n.n.a) mo7getP()).y() ? 10.0f : 15.0f, 5.0f, 4.0f, false);
                this.t.setListXY(this.G, this.E, this.F, this.D.subList(0, this.D.size() < 101 ? this.D.size() : 101), this.B.subList(0, this.B.size() < 101 ? this.D.size() : 101));
                this.w.setText(String.format(getResources().getString(R.string.consumption_line_table), "25" + this.I));
                return;
            }
            if (i2 == 2) {
                o0(((b.b.g.n.n.a) mo7getP()).y() ? 10.0f : 15.0f, 5.0f, 10.0f, false);
                this.t.setListXY(this.G, this.E, this.F, this.D, this.B);
                this.w.setText(String.format(getResources().getString(R.string.consumption_line_table), "50" + this.I));
            }
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        n = false;
        if (!this.S && this.R && this.P != null) {
            BydLogHelper.logI(m, "bydEnergyChartView,handler,onResume ");
            h hVar = this.T;
            if (hVar != null) {
                hVar.removeCallbacksAndMessages(null);
                this.T.sendEmptyMessage(3);
            }
        }
        this.S = false;
    }

    public final void p0(int i2) {
        this.t.setDrawType(i2);
        this.u.setVisibility(i2 == 2 ? 8 : 0);
        this.v.setVisibility(i2 != 1 ? 0 : 8);
    }

    public final void q0() {
        m.h(getContext(), "ConsumptionManageFragment_label_chart", getContext().getResources().getString(R.string.newenergy_consumption_line_clear_data), getContext().getResources().getString(R.string.newenergy_consumption_line_clear_data_dialog_message), getContext().getResources().getString(R.string.dialog_btn_ok), getContext().getResources().getString(R.string.dialog_btn_cancel), new c(), null).C();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void r0() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(b.b.a.i.a.f531a ? R.layout.dialog_data_clear_byd : R.layout.dialog_data_clear, (ViewGroup) null);
        this.o = (CheckBox) viewInflate.findViewById(R.id.cb_total_fuel);
        this.p = (CheckBox) viewInflate.findViewById(R.id.cb_external_charging);
        this.q = (CheckBox) viewInflate.findViewById(R.id.cb_average_speed);
        this.r = (CheckBox) viewInflate.findViewById(R.id.cb_travel_time);
        this.p.setVisibility(((b.b.g.n.n.a) mo7getP()).x() ? 0 : 8);
        this.o.setVisibility(((b.b.g.n.n.a) mo7getP()).z() ? 0 : 8);
        l lVarI = m.i(getContext(), "ConsumptionManageFragment_label_data", getContext().getResources().getString(R.string.newenergy_consumption_list_clear_data), getContext().getResources().getString(R.string.dialog_btn_ok), getContext().getResources().getString(R.string.dialog_btn_cancel), new d(), new e(), true, viewInflate);
        this.s = lVarI;
        lVarI.C();
        if (m.f1481c.containsKey("ConsumptionManageFragment_label_data_1")) {
            this.o.setChecked(((Boolean) m.f1481c.get("ConsumptionManageFragment_label_data_1")).booleanValue());
        }
        if (m.f1481c.containsKey("ConsumptionManageFragment_label_data_2")) {
            this.p.setChecked(((Boolean) m.f1481c.get("ConsumptionManageFragment_label_data_2")).booleanValue());
        }
        if (m.f1481c.containsKey("ConsumptionManageFragment_label_data_3")) {
            this.q.setChecked(((Boolean) m.f1481c.get("ConsumptionManageFragment_label_data_3")).booleanValue());
        }
        if (m.f1481c.containsKey("ConsumptionManageFragment_label_data_4")) {
            this.r.setChecked(((Boolean) m.f1481c.get("ConsumptionManageFragment_label_data_4")).booleanValue());
        }
        this.o.setOnCheckedChangeListener(this.U);
        this.p.setOnCheckedChangeListener(this.U);
        this.q.setOnCheckedChangeListener(this.U);
        this.r.setOnCheckedChangeListener(this.U);
        this.s.s(this.o.isChecked() || this.p.isChecked() || this.q.isChecked() || this.r.isChecked());
        this.s.setDismissListener(new f());
    }

    public final void s0() {
        try {
            this.R = false;
            getContext().unbindService(this.Q);
            this.P = null;
            this.Q = null;
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        e0();
        ((b.b.g.n.n.a) mo7getP()).v();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void z() {
        super.z();
        l0(true);
        ((b.b.g.n.n.a) mo7getP()).E();
    }
}
