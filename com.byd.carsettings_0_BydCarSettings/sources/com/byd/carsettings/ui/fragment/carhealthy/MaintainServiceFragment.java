package com.byd.carsettings.ui.fragment.carhealthy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.k;
import b.b.g.g.e;
import b.b.g.h.a;
import b.b.g.n.q.o;
import b.b.g.q.d.l;
import b.b.g.q.d.m;
import b.b.g.r.v;
import b.b.g.r.z;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.MainActivity;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.carhealthy.OilListAdapter;
import com.byd.carsettings.adapter.carhealthy.OilRecordListAdapter;
import com.byd.carsettings.adapter.carhealthy.OilResetListAdapter;
import com.byd.carsettings.db.model.MaintainMileBean;
import com.byd.carsettings.db.model.MaintainTimeBean;
import com.byd.carsettings.db.model.MaintainanceRecord;
import com.byd.carsettings.ui.widget.wheelview.WheelView;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MaintainServiceFragment extends XFragment<b.b.g.n.g.b> implements View.OnClickListener, BydSwitch.a, b.b.o.b.a.a {
    public l D;
    public b.b.g.h.a L;
    public j N;
    public TextView n;
    public ImageView o;
    public RecyclerView p;
    public ScrollView q;
    public RecyclerView r;
    public RecyclerView s;
    public TextView t;
    public TextView u;
    public TextView v;
    public BydSwitch w;
    public BydSwitch x;
    public final String m = getClass().getSimpleName();
    public String y = "";
    public String z = "";
    public int[] A = {R.id.ll_oil, R.id.ll_oil, R.id.ll_detect, R.id.ll_detect, R.id.ll_sw, R.id.ll_sw};
    public List<b.b.g.m.c.a> B = new ArrayList();
    public boolean E = false;
    public final String F = "ConsumptionManageFragment_label_oil_reset";
    public final String G = "ConsumptionManageFragment_label_detect";
    public final String H = "ConsumptionManageFragment_label_oil_life";
    public final String I = "ConsumptionManageFragment_label_oil_record";
    public final String J = "ConsumptionManageFragment_label_maintainservice_time";
    public final String K = "ConsumptionManageFragment_label_maintainservice_mile";
    public boolean M = false;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MaintainServiceFragment.this.f3717c != null) {
                MaintainServiceFragment.this.f3717c.findViewById(R.id.ll_oil).setVisibility(MaintainServiceFragment.this.B.size() == 0 ? 8 : 0);
            }
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ WheelView f3010a;

        public b(WheelView wheelView) {
            this.f3010a = wheelView;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String selectedText = this.f3010a.getSelectedText();
            if (selectedText.length() > 1 && selectedText.startsWith("0")) {
                selectedText = selectedText.substring(1);
            }
            MaintainServiceFragment.this.u.setText(selectedText + MaintainServiceFragment.this.y);
            try {
                ((b.b.g.n.g.b) MaintainServiceFragment.this.mo7getP()).q(2, Integer.parseInt(selectedText), MaintainServiceFragment.this.L);
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }
    }

    public class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ WheelView f3012a;

        public c(WheelView wheelView) {
            this.f3012a = wheelView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.f1481c.put("ConsumptionManageFragment_label_maintainservice_mile", this.f3012a.getSelectedText());
        }
    }

    public class d implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ WheelView f3014a;

        public d(WheelView wheelView) {
            this.f3014a = wheelView;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String selectedText = this.f3014a.getSelectedText();
            if (selectedText.length() > 1 && selectedText.startsWith("0")) {
                selectedText = selectedText.substring(1);
            }
            MaintainServiceFragment.this.t.setText(selectedText + MaintainServiceFragment.this.z);
            try {
                ((b.b.g.n.g.b) MaintainServiceFragment.this.mo7getP()).q(1, Integer.parseInt(selectedText), MaintainServiceFragment.this.L);
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }
    }

    public class e implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ WheelView f3016a;

        public e(WheelView wheelView) {
            this.f3016a = wheelView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.f1481c.put("ConsumptionManageFragment_label_maintainservice_time", this.f3016a.getSelectedText());
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaintainServiceFragment.this.E = true;
            ((b.b.g.n.g.b) MaintainServiceFragment.this.mo7getP()).r(0);
        }
    }

    public class g implements Comparator<MaintainanceRecord> {
        public g() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(MaintainanceRecord maintainanceRecord, MaintainanceRecord maintainanceRecord2) {
            if (maintainanceRecord.getDate() > maintainanceRecord2.getDate()) {
                return -1;
            }
            return maintainanceRecord.getDate() < maintainanceRecord2.getDate() ? 1 : 0;
        }
    }

    public class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3020a;

        public h(int i) {
            this.f3020a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MaintainServiceFragment.this.q == null || this.f3020a >= MaintainServiceFragment.this.A.length) {
                return;
            }
            if (this.f3020a == 0) {
                MaintainServiceFragment.this.q.scrollTo(0, 0);
                return;
            }
            b.b.m.c.a.d(MaintainServiceFragment.this.m, "[scrollToPosition]----getY:" + MaintainServiceFragment.this.f3717c.findViewById(MaintainServiceFragment.this.A[this.f3020a]).getY());
            MaintainServiceFragment.this.q.scrollTo(0, Math.round(MaintainServiceFragment.this.f3717c.findViewById(MaintainServiceFragment.this.A[this.f3020a]).getY()));
        }
    }

    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MaintainServiceFragment.this.f3717c != null && MaintainServiceFragment.this.f3717c.findViewById(R.id.ll_oil).getVisibility() == 0) {
                MaintainServiceFragment.this.v0();
            }
            MainActivity.p[3] = 0;
        }
    }

    public class j implements ServiceConnection {
        public j() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                MaintainServiceFragment.this.M = true;
                MaintainServiceFragment.this.L = a.AbstractBinderC0044a.d0(iBinder);
                if (MaintainServiceFragment.this.L != null) {
                    MaintainMileBean maintainMileBeanK = MaintainServiceFragment.this.L.K();
                    if (maintainMileBeanK != null) {
                        v.d().g("KEY_MAINTAIN_MILE_STATE", maintainMileBeanK.c());
                        v.d().h("KEY_MAINTAIN_MILE_1", maintainMileBeanK.a());
                        if (maintainMileBeanK.b() != -1.0d || maintainMileBeanK.c()) {
                            v.d().h("KEY_MAINTAIN_MILE_TARGET", maintainMileBeanK.b());
                        } else {
                            v.d().k("KEY_MAINTAIN_MILE_TARGET");
                        }
                        MaintainServiceFragment maintainServiceFragment = MaintainServiceFragment.this;
                        maintainServiceFragment.f0(((b.b.g.n.g.b) maintainServiceFragment.mo7getP()).e());
                    }
                    MaintainTimeBean maintainTimeBeanI = MaintainServiceFragment.this.L.I();
                    if (maintainTimeBeanI != null) {
                        v.d().g("KEY_MAINTAIN_TIME_STATE", maintainTimeBeanI.c());
                        v.d().i("KEY_MAINTAIN_TIME", maintainTimeBeanI.a());
                        if (maintainTimeBeanI.b() != -1 || maintainTimeBeanI.c()) {
                            v.d().j("KEY_MAINTAIN_TIME_TARGET", maintainTimeBeanI.b());
                        } else {
                            v.d().k("KEY_MAINTAIN_TIME_TARGET");
                        }
                        MaintainServiceFragment maintainServiceFragment2 = MaintainServiceFragment.this;
                        maintainServiceFragment2.e0(1, ((b.b.g.n.g.b) maintainServiceFragment2.mo7getP()).d(1), maintainTimeBeanI.c());
                    }
                }
            } catch (Exception e2) {
                b.b.m.c.a.c(MaintainServiceFragment.this.m, e2.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MaintainServiceFragment.this.M = false;
        }

        public /* synthetic */ j(MaintainServiceFragment maintainServiceFragment, a aVar) {
            this();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        RecyclerView recyclerView = (RecyclerView) this.f3717c.findViewById(R.id.rv_oil);
        this.p = recyclerView;
        recyclerView.setOverScrollMode(2);
        ScrollView scrollView = (ScrollView) this.f3717c.findViewById(R.id.scrollView);
        this.q = scrollView;
        scrollView.setOverScrollMode(2);
        this.n = (TextView) this.f3717c.findViewById(R.id.tv_oil_result);
        this.o = (ImageView) this.f3717c.findViewById(R.id.iv_result);
        this.t = (TextView) this.f3717c.findViewById(R.id.tv_set_time);
        this.u = (TextView) this.f3717c.findViewById(R.id.tv_set_mile);
        this.v = (TextView) this.f3717c.findViewById(R.id.tv_detect);
        this.w = (BydSwitch) this.f3717c.findViewById(R.id.sw_time);
        this.x = (BydSwitch) this.f3717c.findViewById(R.id.sw_mile);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.BydSwitch.a
    public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
        if (view.isEnabled()) {
            ((BydSwitch) view).setChecked(z, false);
            b.b.g.o.f.d(view, 0);
            int id = view.getId();
            if (id == R.id.sw_mile) {
                this.u.setVisibility(z ? 0 : 8);
                this.f3717c.findViewById(R.id.iv_set_mile).setVisibility(z ? 0 : 8);
                ((b.b.g.n.g.b) mo7getP()).q(2, z ? ((b.b.g.n.g.b) mo7getP()).d(2) : BYDAutoInstrumentDevice.MAINTENANCE_MILEAGE_KILOMETER_MAX, this.L);
            } else {
                if (id != R.id.sw_time) {
                    return;
                }
                this.t.setVisibility(z ? 0 : 8);
                this.f3717c.findViewById(R.id.iv_set_time).setVisibility(z ? 0 : 8);
                ((b.b.g.n.g.b) mo7getP()).q(1, z ? ((b.b.g.n.g.b) mo7getP()).d(1) : 991, this.L);
            }
        }
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_maintain_service;
    }

    public final void d0() {
        try {
            this.N = new j(this, null);
            Intent intent = new Intent();
            intent.setAction("com.byd.aidl.dialog");
            intent.setPackage("com.byd.uereport");
            getContext().bindService(intent, this.N, 1);
        } catch (Exception e2) {
            Log.e("----", "bindService----" + e2.getMessage());
        }
    }

    public void e0(int i2, int i3, boolean z) {
        if (i2 == 2) {
            if (34001 != i3) {
                this.u.setText(i3 + this.y);
            }
            this.u.setVisibility(z ? 0 : 8);
            this.f3717c.findViewById(R.id.iv_set_mile).setVisibility(z ? 0 : 8);
            this.x.setChecked(z, false);
            return;
        }
        if (i2 == 1) {
            if (991 != i3) {
                this.t.setText(i3 + this.z);
            }
            this.t.setVisibility(z ? 0 : 8);
            this.f3717c.findViewById(R.id.iv_set_time).setVisibility(z ? 0 : 8);
            this.w.setChecked(z, false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void f0(int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(getResources().getString(i2 == 2 ? R.string.carhealthy_maintainservice_mile : R.string.carhealthy_maintainservice_km));
        this.y = sb.toString();
        e0(2, ((b.b.g.n.g.b) mo7getP()).d(2), v.d().b("KEY_MAINTAIN_MILE_STATE", false));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void g0(int i2) {
        if (((b.b.g.n.g.b) mo7getP()).m()) {
            if (this.E) {
                z.b(getContext(), R.string.vehiclehealth_detect_over);
                this.E = false;
            }
            if (i2 == 0) {
                this.n.setText(getContext().getResources().getString(R.string.vehiclehealth_oil_detection_below));
                this.n.setTextColor(ContextCompat.getColor(getContext(), R.color.carmsg_serious));
                this.o.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_dark_carsetting_fault_48));
                this.o.setColorFilter(ContextCompat.getColor(getContext(), R.color.carmsg_serious));
            } else if (i2 == 10) {
                this.n.setText(getContext().getResources().getString(R.string.vehiclehealth_oil_detection_over));
                this.n.setTextColor(ContextCompat.getColor(getContext(), R.color.carmsg_serious));
                this.o.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_dark_carsetting_fault_48));
                this.o.setColorFilter(ContextCompat.getColor(getContext(), R.color.carmsg_serious));
            } else if (i2 < 1 || i2 > 9) {
                this.n.setText("");
                this.o.setImageDrawable(null);
            } else {
                this.n.setText(getContext().getResources().getString(R.string.vehiclehealth_oil_detection_normal));
                this.n.setTextColor(ContextCompat.getColor(getContext(), R.color.carmsg_dtc_ok));
                this.o.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_dark_carsetting_normal_48));
                this.o.setColorFilter(ContextCompat.getColor(getContext(), R.color.carmsg_dtc_ok));
            }
            q0(i2, 0);
            this.f3717c.findViewById(R.id.ll_detect).setVisibility(((b.b.g.n.g.b) mo7getP()).m() ? 0 : 8);
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_maintain_service;
    }

    public void h0(int i2, int i3) {
        boolean z;
        boolean z2;
        int i4 = 0;
        while (true) {
            try {
                z = true;
                if (i4 >= this.B.size()) {
                    z2 = false;
                    break;
                } else {
                    if (this.B.get(i4).f889d == i2) {
                        this.B.get(i4).f886a = i3;
                        z2 = true;
                        break;
                    }
                    i4++;
                }
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
                return;
            }
        }
        if (!z2) {
            int[] iArr = {R.string.carhealthy_oil_engine, R.string.carhealthy_oil_transmission, R.string.carhealthy_oil_brake, R.string.carhealthy_oil_blade_battery, R.string.carhealthy_oil_motor_elec};
            int i5 = 0;
            while (true) {
                if (i5 >= this.B.size()) {
                    z = false;
                    break;
                } else {
                    if (this.B.get(i5).f889d > i2) {
                        this.B.add(i5, new b.b.g.m.c.a(i3, getResources().getString(iArr[i2]), 0, i2));
                        break;
                    }
                    i5++;
                }
            }
            if (!z) {
                List<b.b.g.m.c.a> list = this.B;
                list.add(list.size() == 0 ? 0 : this.B.size(), new b.b.g.m.c.a(i3, getResources().getString(iArr[i2]), 0, i2));
            }
        }
        p0();
        ((OilListAdapter) this.p.getAdapter()).a(this.B);
    }

    public void i0(int i2) {
        l lVar = this.D;
        if (lVar != null) {
            lVar.s(i2 == 1);
        }
    }

    public void j0(int i2, int i3) {
        if (i3 == 1 || i3 == 2) {
            try {
                z.b(getContext(), i3 == 1 ? R.string.carhealthy_reset_success : R.string.carhealthy_reset_fail);
                p0();
                if (i3 != 1) {
                    for (int i4 = 0; i4 < this.B.size(); i4++) {
                        if (this.B.get(i4).f889d == i2) {
                            this.B.get(i4).f888c = 0;
                        }
                    }
                    RecyclerView recyclerView = this.s;
                    if (recyclerView == null || recyclerView.getAdapter() == null) {
                        return;
                    }
                    ((OilResetListAdapter) this.s.getAdapter()).f(this.B);
                    return;
                }
                b.b.g.h.a aVar = this.L;
                if (aVar != null) {
                    aVar.u(i2);
                }
                for (int i5 = 0; i5 < this.B.size(); i5++) {
                    if (this.B.get(i5).f889d == i2) {
                        this.B.get(i5).f888c = 0;
                        this.B.get(i5).f886a = 100;
                    }
                }
                ((OilListAdapter) this.p.getAdapter()).a(this.B);
                RecyclerView recyclerView2 = this.s;
                if (recyclerView2 != null && recyclerView2.getAdapter() != null) {
                    ((OilResetListAdapter) this.s.getAdapter()).f(this.B);
                }
                MaintainanceRecord maintainanceRecord = new MaintainanceRecord();
                maintainanceRecord.setType(i2);
                maintainanceRecord.setDate(System.currentTimeMillis());
                maintainanceRecord.setHevMileage(o.m().l());
                maintainanceRecord.setTotalMileage(o.m().u());
                e.b.a().o(maintainanceRecord);
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void k0(int i2) {
        e0(2, ((b.b.g.n.g.b) mo7getP()).d(2), v.d().b("KEY_MAINTAIN_MILE_STATE", false));
    }

    public final List<MaintainanceRecord> l0(int i2) {
        List<MaintainanceRecord> listN = e.b.a().n(i2);
        return listN == null ? new ArrayList() : listN;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_maintain_service_byd;
    }

    public final void m0(boolean z) {
        this.v.setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.tv_oil_reset).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.iv_oil_life).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.tv_set_mile).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.tv_set_time).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.iv_set_mile).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.iv_set_time).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.tv_oil_record).setOnClickListener(z ? this : null);
        this.w.setOnInterceptTouchListener(z ? this : null);
        this.x.setOnInterceptTouchListener(z ? this : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void n0() {
        int iF = ((b.b.g.n.g.b) mo7getP()).f();
        this.f3717c.findViewById(R.id.ll_detect).setVisibility(((b.b.g.n.g.b) mo7getP()).m() ? 0 : 8);
        q0(iF, 0);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.g.b newP() {
        return new b.b.g.n.g.b();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        try {
            Iterator<String> it = m.f1480b.iterator();
            while (it.hasNext()) {
                switch (it.next()) {
                    case "ConsumptionManageFragment_label_detect":
                        s0();
                        break;
                    case "ConsumptionManageFragment_label_oil_life":
                        t0();
                        break;
                    case "ConsumptionManageFragment_label_oil_record":
                        u0();
                        break;
                    case "ConsumptionManageFragment_label_oil_reset":
                        v0();
                        break;
                    case "ConsumptionManageFragment_label_maintainservice_time":
                        String str = (String) m.f1481c.get("ConsumptionManageFragment_label_maintainservice_time");
                        m.f1481c.put("ConsumptionManageFragment_label_maintainservice_time", "");
                        w0(str);
                        break;
                    case "ConsumptionManageFragment_label_maintainservice_mile":
                        String str2 = (String) m.f1481c.get("ConsumptionManageFragment_label_maintainservice_mile");
                        m.f1481c.put("ConsumptionManageFragment_label_maintainservice_mile", "");
                        r0(str2);
                        break;
                    case "ConsumptionManageFragment_label_oil_record_reset":
                        RecyclerView recyclerView = this.s;
                        if (recyclerView != null && recyclerView.getAdapter() != null && m.f1481c.containsKey("ConsumptionManageFragment_label_oil_record_reset")) {
                            ((OilResetListAdapter) this.s.getAdapter()).g(((Integer) m.f1481c.get("ConsumptionManageFragment_label_oil_record_reset")).intValue());
                            break;
                        } else {
                            break;
                        }
                        break;
                }
            }
        } catch (Exception e2) {
            b.b.m.c.a.c(this.m, "label----" + e2.getMessage());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (b.b.m.g.c.a(300L)) {
        }
        switch (view.getId()) {
            case R.id.iv_oil_life /* 2131297317 */:
                t0();
                break;
            case R.id.iv_set_mile /* 2131297348 */:
            case R.id.tv_set_mile /* 2131298723 */:
                r0(null);
                break;
            case R.id.iv_set_time /* 2131297349 */:
            case R.id.tv_set_time /* 2131298724 */:
                w0(null);
                break;
            case R.id.tv_detect /* 2131298450 */:
                s0();
                break;
            case R.id.tv_oil_record /* 2131298625 */:
                u0();
                break;
            case R.id.tv_oil_reset /* 2131298626 */:
                v0();
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        x0();
        m0(false);
        if (this.f3717c != null) {
            ((ImageView) this.f3717c.findViewById(R.id.iv_oil_result)).setImageDrawable(null);
        }
        RecyclerView recyclerView = this.r;
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
        }
        RecyclerView recyclerView2 = this.p;
        if (recyclerView2 != null) {
            recyclerView2.setAdapter(null);
        }
        super.onDestroyView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        ((b.b.g.n.g.b) mo7getP()).r(1);
        this.n.setText("");
        this.o.setImageDrawable(null);
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (MainActivity.p[3] == 100) {
            this.f3717c.findViewById(R.id.ll_oil).post(new i());
        }
    }

    public final void p0() {
        int i2 = 0;
        boolean z = b.b.g.n.q.b.c().e(193) == 1;
        while (i2 < this.B.size()) {
            if (!z || this.B.get(i2).f886a == -999) {
                this.B.remove(i2);
                i2--;
            }
            i2++;
        }
        this.f3717c.findViewById(R.id.ll_oil).post(new a());
    }

    public void q0(int i2, int i3) {
        if (i2 == 13) {
            k.e(this.v);
            k.e(this.f3717c.findViewById(R.id.tv_detect_title));
        } else {
            k.h(this.v);
            k.h(this.f3717c.findViewById(R.id.tv_detect_title));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void r0(java.lang.String r14) {
        /*
            Method dump skipped, instruction units count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.ui.fragment.carhealthy.MaintainServiceFragment.r0(java.lang.String):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void s0() {
        l lVarI = m.i(getContext(), "ConsumptionManageFragment_label_detect", getContext().getResources().getString(R.string.vehiclehealth_tips), getContext().getResources().getString(R.string.dialog_btn_ok), getContext().getResources().getString(R.string.dialog_btn_cancel), new f(), null, true, LayoutInflater.from(getContext()).inflate(R.layout.dialog_carhealthy_oil_hint, (ViewGroup) null));
        this.D = lVarI;
        lVarI.C();
        this.D.s(((b.b.g.n.g.b) mo7getP()).h() == 1);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i2) {
        super.t(i2);
        ScrollView scrollView = this.q;
        if (scrollView != null) {
            scrollView.post(new h(i2));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void t0() {
        m.f(getContext(), "ConsumptionManageFragment_label_oil_life", getContext().getResources().getString(R.string.carhealthy_oil_life), getContext().getResources().getString(((b.b.g.n.g.b) mo7getP()).l() ? R.string.carhealthy_oil_life_hint_ev : R.string.carhealthy_oil_life_hint), GravityCompat.START, getResources().getDimensionPixelSize(R.dimen.sp_18), getContext().getResources().getString(R.string.dialog_btn_ok), null, null, null).C();
    }

    public final void u0() {
        ArrayList arrayList = new ArrayList();
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_oil_record, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.rv_record);
        this.r = recyclerView;
        recyclerView.setNestedScrollingEnabled(false);
        this.r.setOverScrollMode(2);
        this.r.setLayoutManager(new LinearLayoutManager(getContext()));
        this.r.setAdapter(new OilRecordListAdapter(getContext()));
        ArrayList<MaintainanceRecord> arrayList2 = new ArrayList(5);
        HashMap map = new HashMap();
        for (int i2 = 0; i2 < 5; i2++) {
            List<MaintainanceRecord> listL0 = l0(i2);
            if (listL0.size() > 0) {
                map.put(Integer.valueOf(i2), listL0);
                MaintainanceRecord maintainanceRecord = new MaintainanceRecord();
                maintainanceRecord.setId(-1L);
                maintainanceRecord.setDate(listL0.get(0).getDate());
                maintainanceRecord.setType(i2);
                arrayList2.add(maintainanceRecord);
            }
        }
        if (arrayList2.size() > 0) {
            arrayList2.sort(new g());
            for (MaintainanceRecord maintainanceRecord2 : arrayList2) {
                arrayList.add(maintainanceRecord2);
                arrayList.addAll((Collection) map.get(Integer.valueOf(maintainanceRecord2.getType())));
            }
        }
        ((OilRecordListAdapter) this.r.getAdapter()).a(arrayList);
        this.r.setVisibility(arrayList.size() == 0 ? 8 : 0);
        viewInflate.findViewById(R.id.tv_nodata).setVisibility(arrayList.size() == 0 ? 0 : 8);
        viewInflate.findViewById(R.id.iv_nodata).setVisibility(arrayList.size() == 0 ? 0 : 8);
        l lVarI = m.i(getContext(), "ConsumptionManageFragment_label_oil_record", getContext().getResources().getString(R.string.carhealthy_maintain_record), getContext().getResources().getString(R.string.dialog_btn_ok), null, null, null, true, viewInflate);
        lVarI.y(false);
        lVarI.C();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void v0() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_oil_reset, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.rv_oil_reset);
        this.s = recyclerView;
        recyclerView.setOverScrollMode(2);
        this.s.setNestedScrollingEnabled(false);
        this.s.setLayoutManager(new LinearLayoutManager(getContext()));
        this.s.setAdapter(new OilResetListAdapter(getContext(), (b.b.g.n.g.b) mo7getP()));
        for (int i2 = 0; i2 < this.B.size(); i2++) {
            this.B.get(i2).f888c = 0;
        }
        ((OilResetListAdapter) this.s.getAdapter()).f(this.B);
        m.j(getContext(), "ConsumptionManageFragment_label_oil_reset", getContext().getResources().getString(R.string.carhealthy_oil_life_reset), getContext().getResources().getString(R.string.dialog_btn_ok), null, null, null, true, true, viewInflate).C();
    }

    public final void w0(String str) {
        int i2;
        StringBuilder sb;
        int i3;
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_time_set_maintainservice, (ViewGroup) null);
        WheelView wheelView = (WheelView) viewInflate.findViewById(R.id.wheelView_data);
        ((TextView) viewInflate.findViewById(R.id.tv_unit)).setText(this.z);
        if (TextUtils.isEmpty(str)) {
            str = this.t.getText().toString().replace(this.z, "");
        }
        if ("".equals(str) || "0".equals(str)) {
            i2 = 0;
        } else {
            try {
                i3 = Integer.parseInt(str);
            } catch (Exception e2) {
                e = e2;
                i3 = 0;
            }
            try {
                i2 = ((i3 + 15) / 15) * 15;
                int i4 = i2 - 15;
                if (i2 - i3 > i3 - i4) {
                    i2 = i4;
                }
            } catch (Exception e3) {
                e = e3;
                BydLogHelper.logE(e.getMessage());
                i2 = i3;
            }
        }
        ArrayList arrayList = new ArrayList();
        int size = 0;
        for (int i5 = 0; i5 <= 990; i5 += 15) {
            if (i5 > 9 || i5 <= 0) {
                sb = new StringBuilder();
                sb.append(i5);
                sb.append("");
            } else {
                sb = new StringBuilder();
                sb.append("0");
                sb.append(i5);
            }
            arrayList.add(sb.toString());
            if (i2 == i5) {
                size = arrayList.size() - 1;
            }
        }
        wheelView.setData(arrayList);
        wheelView.setDefault(size);
        l lVarI = m.i(getContext(), "ConsumptionManageFragment_label_maintainservice_time", getResources().getString(R.string.carhealthy_maintain_time), getResources().getString(R.string.dialog_btn_ok), getResources().getString(R.string.dialog_btn_cancel), new d(wheelView), null, true, viewInflate);
        lVarI.setDismissListener(new e(wheelView));
        lVarI.y(false);
        lVarI.C();
    }

    public final void x0() {
        try {
            this.M = false;
            getContext().unbindService(this.N);
            this.N = null;
            this.L = null;
        } catch (Exception e2) {
            Log.e("----", "unbindService----" + e2.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        d0();
        this.B.clear();
        this.B.add(new b.b.g.m.c.a(-999, getResources().getString(R.string.carhealthy_oil_engine), 0, 0));
        this.B.add(new b.b.g.m.c.a(-999, getResources().getString(R.string.carhealthy_oil_transmission), 0, 1));
        this.B.add(new b.b.g.m.c.a(-999, getResources().getString(R.string.carhealthy_oil_brake), 0, 2));
        this.B.add(new b.b.g.m.c.a(-999, getResources().getString(R.string.carhealthy_oil_blade_battery), 0, 3));
        this.B.add(new b.b.g.m.c.a(-999, getResources().getString(R.string.carhealthy_oil_motor_elec), 0, 4));
        this.p.setLayoutManager(new LinearLayoutManager(getContext()));
        this.p.setAdapter(new OilListAdapter(getContext(), this.B));
        this.p.setNestedScrollingEnabled(false);
        this.z = " " + getResources().getString(R.string.carhealthy_maintainservice_days);
        ((ImageView) this.f3717c.findViewById(R.id.iv_oil_result)).setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_dark_carhealthy));
        ((b.b.g.n.g.b) mo7getP()).j();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void z() {
        super.z();
        m0(true);
        ((b.b.g.n.g.b) mo7getP()).s();
        for (int i2 = 0; i2 < this.B.size(); i2++) {
            this.B.get(i2).f886a = ((b.b.g.n.g.b) mo7getP()).g(this.B.get(i2).f889d);
        }
        p0();
        ((OilListAdapter) this.p.getAdapter()).a(this.B);
    }
}
