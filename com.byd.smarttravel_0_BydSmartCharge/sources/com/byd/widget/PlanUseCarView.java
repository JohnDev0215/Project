package com.byd.widget;

import android.content.Context;
import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import c.a.d.i;
import c.a.d.k;
import c.a.d.l;
import com.byd.widget.BydSwitch;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class PlanUseCarView extends FrameLayout implements View.OnClickListener, BydSwitch.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient boolean f1986b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient AbsBYDAutoChargingListener f1987c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient AbsBYDAutoStatisticListener f1988d;
    public transient c.a.e.b e;
    public transient TextView f;
    public transient TextView g;
    public transient TextView h;
    public transient TextView i;
    public transient TextView j;
    public transient TextView k;
    public transient TextView l;
    public transient TextView m;
    public transient BydSwitch n;
    public transient ChargeAnimView o;
    public transient ImageView p;
    public transient ImageView q;
    public transient ImageView r;

    public class a extends AbsBYDAutoChargingListener {

        /* JADX INFO: renamed from: com.byd.widget.PlanUseCarView$a$a, reason: collision with other inner class name */
        public class RunnableC0062a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ int f1990b;

            public RunnableC0062a(int i) {
                this.f1990b = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (PlanUseCarView.this.o != null) {
                    PlanUseCarView.this.o.setBatteryScan(this.f1990b);
                }
                PlanUseCarView.this.p();
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                PlanUseCarView.this.p();
            }
        }

        public a() {
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onBatteryManagementDeviceStateChanged(int i) {
            super.onBatteryManagementDeviceStateChanged(i);
            new Handler(Looper.getMainLooper()).post(new RunnableC0062a(i != 1 ? i == 11 ? -1 : 0 : 1));
        }

        @Override // android.hardware.bydauto.charging.AbsBYDAutoChargingListener
        public void onChargingGunStateChanged(int i) {
            super.onChargingGunStateChanged(i);
            new Handler(Looper.getMainLooper()).post(new b());
        }
    }

    public class b extends AbsBYDAutoStatisticListener {

        public class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ double f1994b;

            public a(double d2) {
                this.f1994b = d2;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (PlanUseCarView.this.o != null) {
                    PlanUseCarView.this.o.setBatteryProgress((int) this.f1994b);
                }
            }
        }

        public b() {
        }

        @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onElecPercentageChanged(double d2) {
            super.onElecPercentageChanged(d2);
            new Handler(Looper.getMainLooper()).post(new a(d2));
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Throwable {
            try {
                c.a.d.f.f1598c.clear();
                if (view.getTag() == null) {
                    return;
                }
                String str = (String) view.getTag();
                String[] strArrSplit = str.split("&");
                if (strArrSplit.length != 3) {
                    return;
                }
                String str2 = strArrSplit[0];
                String str3 = strArrSplit[1];
                int iIntValue = Integer.valueOf(strArrSplit[2]).intValue();
                k.b("showTimeSelect : " + str);
                i.i(PlanUseCarView.this.getContext(), "KEY_USECAR_CYCLE", str3);
                PlanUseCarView.this.k(Integer.parseInt(str2.split("\\|")[0]), Integer.parseInt(str2.split("\\|")[1]), iIntValue);
                PlanUseCarView.this.s();
                PlanUseCarView.this.r();
                if (PlanUseCarView.this.e != null) {
                    PlanUseCarView.this.e.b(0, "TYPE_ALARM_START_USECAR");
                }
            } catch (Exception e) {
                Log.e("smart_charge", e.getMessage());
            }
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c.a.d.f.f1598c.clear();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                c.a.d.f.f1598c.clear();
                if (view.getTag() == null) {
                    return;
                }
                String[] strArrSplit = ((String) view.getTag()).split("&");
                if (strArrSplit.length != 2) {
                    return;
                }
                String[] strArrSplit2 = strArrSplit[0].split("\\|");
                int i = Integer.parseInt(strArrSplit2[0]);
                boolean z = true;
                int i2 = Integer.parseInt(strArrSplit2[1]);
                int i3 = Integer.parseInt(strArrSplit2[2]);
                int i4 = Integer.parseInt(strArrSplit2[3]);
                int i5 = (i * 60) + i2;
                i.h(PlanUseCarView.this.getContext(), "KEY_PRICE_START_TIME", i5);
                int i6 = (i3 * 60) + i4;
                i.h(PlanUseCarView.this.getContext(), "KEY_PRICE_END_TIME", i6);
                Context context = PlanUseCarView.this.getContext();
                if (i5 != i6 && !"1".equals(strArrSplit[1])) {
                    z = false;
                }
                i.g(context, "KEY_PRICE_FULL", z);
                PlanUseCarView.this.q();
                if (PlanUseCarView.this.e != null) {
                    PlanUseCarView.this.e.b(0, "TYPE_ALARM_PRICE_USECAR");
                }
            } catch (Exception e) {
                Log.e("smart_charge", e.getMessage());
            }
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c.a.d.f.f1598c.clear();
        }
    }

    public PlanUseCarView(Context context, c.a.e.b bVar) {
        super(context);
        this.f1986b = true;
        t(context);
        this.e = bVar;
    }

    @Override // com.byd.widget.BydSwitch.a
    public void c(View view, MotionEvent motionEvent, boolean z) throws Throwable {
        if (view.isEnabled()) {
            ((BydSwitch) view).b(z, false);
            c.a.c.a.d(view, 0);
            if (view.getId() != R.id.sbg_charge) {
                return;
            }
            o(z, true);
            p();
        }
    }

    public final void i(int i, Calendar calendar, Calendar calendar2, int[] iArr, int[] iArr2, Calendar calendar3) {
        TextView textView = this.k;
        if (textView != null) {
            textView.setText(getResources().getString(R.string.gun_already_plug));
        }
        ImageView imageView = this.q;
        if (imageView != null) {
            imageView.setImageDrawable(b.g.b.a.d(getContext(), R.drawable.icon_chargegun_highlight));
        }
        if (i == 1) {
            long[] jArrB = c.a.d.e.b(calendar3.getTimeInMillis(), calendar2.getTimeInMillis());
            BydLogHelper.logI("smart_charge", "state_charging_width_end:" + Arrays.toString(jArrB));
            TextView textView2 = this.l;
            if (textView2 != null) {
                textView2.setText(String.format(getResources().getString(R.string.state_charging_width_end), Long.valueOf(jArrB[1]), Long.valueOf(jArrB[2])));
                return;
            }
            return;
        }
        if (i == 2) {
            TextView textView3 = this.l;
            if (textView3 != null) {
                textView3.setText(getResources().getString(R.string.plan_out_of_time));
                return;
            }
            return;
        }
        if (i == 9) {
            int hEVChargePlanStartTimeTag = BYDAutoChargingDevice.getInstance(getContext()).getHEVChargePlanStartTimeTag();
            String string = hEVChargePlanStartTimeTag == 2 ? getResources().getString(R.string.tomorrow) : hEVChargePlanStartTimeTag == 1 ? getResources().getString(R.string.today) : "";
            TextView textView4 = this.l;
            if (textView4 != null) {
                textView4.setText(String.format(getResources().getString(R.string.start_charge_in_time), string, c.a.d.e.f(iArr[3]) + ":" + c.a.d.e.f(iArr[4])));
            }
        }
    }

    public final void j(Calendar calendar, Calendar calendar2, int[] iArr, int[] iArr2, Calendar calendar3) {
        TextView textView = this.k;
        if (textView != null) {
            textView.setText(getResources().getString(R.string.charge_gun_disconnect));
        }
        ImageView imageView = this.q;
        if (imageView != null) {
            imageView.setImageDrawable(b.g.b.a.d(getContext(), R.drawable.icon_chargegun_default));
        }
        if (!i.c(getContext(), "KEY_USECAR_SWITCH", false)) {
            TextView textView2 = this.l;
            if (textView2 != null) {
                textView2.setText(getResources().getString(R.string.plan_is_close));
                return;
            }
            return;
        }
        long[] jArrB = c.a.d.e.b(calendar3.getTimeInMillis(), calendar.getTimeInMillis());
        BydLogHelper.logI("smart_charge", "start_charge_if_connected:" + Arrays.toString(jArrB));
        if (jArrB[0] >= 1) {
            TextView textView3 = this.l;
            if (textView3 != null) {
                textView3.setText(String.format(getResources().getString(R.string.state_charging_wait_for_more_mins), jArrB[0] + getResources().getString(R.string.days) + jArrB[1] + getResources().getString(R.string.hours)));
                return;
            }
            return;
        }
        int hEVChargePlanStartTimeTag = BYDAutoChargingDevice.getInstance(getContext()).getHEVChargePlanStartTimeTag();
        String string = hEVChargePlanStartTimeTag == 2 ? getResources().getString(R.string.tomorrow) : hEVChargePlanStartTimeTag == 1 ? getResources().getString(R.string.today) : "";
        TextView textView4 = this.l;
        if (textView4 != null) {
            textView4.setText(String.format(getResources().getString(R.string.start_charge_if_connected), string, c.a.d.e.f(iArr[3]) + ":" + c.a.d.e.f(iArr[4])));
        }
    }

    public final void k(int i, int i2, int i3) throws Throwable {
        Calendar calendar = Calendar.getInstance();
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        if (i3 == 0 && (i4 * 60) + i5 >= (i * 60) + i2) {
            calendar.add(5, 1);
            k.b("startCalendar----明日计划");
        }
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), i, i2, 0);
        calendar.set(14, 0);
        k.b("startCalendar : " + calendar.getTime().toString());
        i.h(getContext(), "KEY_USECAR_START_TIME", calendar.getTimeInMillis());
    }

    public void l() throws Throwable {
        this.n.setChecked(i.c(getContext(), "KEY_USECAR_SWITCH", false));
        o(this.n.isChecked(), true);
    }

    public void m() throws Throwable {
        this.n.setTime(400L);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.p.setOnClickListener(this);
        TextView textView = this.j;
        textView.setPaintFlags(textView.getPaintFlags() | 8);
        boolean z = c.a.d.e.g(i.f(getContext(), "KEY_USECAR_CYCLE")) == 0;
        r();
        q();
        s();
        p();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jD = i.d(getContext(), "KEY_USECAR_START_TIME");
        if (z && jCurrentTimeMillis > jD) {
            i.g(getContext(), "KEY_USECAR_SWITCH", false);
        }
        this.n.b(i.c(getContext(), "KEY_USECAR_SWITCH", false), false);
        this.n.setOnInterceptTouchListener(this);
        o(this.n.isChecked(), false);
        n();
    }

    public final void n() {
        if (this.f1986b) {
            return;
        }
        int batteryManagementDeviceState = BYDAutoChargingDevice.getInstance(getContext()).getBatteryManagementDeviceState();
        int i = batteryManagementDeviceState != 1 ? batteryManagementDeviceState == 11 ? -1 : 0 : 1;
        ChargeAnimView chargeAnimView = this.o;
        if (chargeAnimView != null) {
            chargeAnimView.setBatteryProgress((int) BYDAutoStatisticDevice.getInstance(getContext()).getElecPercentageValue());
            this.o.setBatteryScan(i);
            this.o.setBatteryCharge(getContext());
        }
        BYDAutoChargingDevice bYDAutoChargingDevice = BYDAutoChargingDevice.getInstance(getContext());
        a aVar = new a();
        this.f1987c = aVar;
        bYDAutoChargingDevice.registerListener((AbsBYDAutoChargingListener) aVar);
        BYDAutoStatisticDevice bYDAutoStatisticDevice = BYDAutoStatisticDevice.getInstance(getContext());
        b bVar = new b();
        this.f1988d = bVar;
        bYDAutoStatisticDevice.registerListener((AbsBYDAutoStatisticListener) bVar);
    }

    public final void o(boolean z, boolean z2) throws Throwable {
        c.a.e.b bVar;
        if (z) {
            i.g(getContext(), "KEY_USECAR_SWITCH", true);
            l.b(this.r, this.h, this.i, this.g, this.m, this.j, this.p);
            if (z2) {
                Calendar calendar = Calendar.getInstance();
                int i = calendar.get(11);
                int i2 = calendar.get(12);
                long jD = i.d(getContext(), "KEY_USECAR_START_TIME");
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis(jD);
                int i3 = calendar2.get(11);
                int i4 = calendar2.get(12);
                if (c.a.d.e.g(i.f(getContext(), "KEY_USECAR_CYCLE")) == 0 && (i * 60) + i2 >= (i3 * 60) + i4) {
                    calendar.add(5, 1);
                    k.b("[开关开启]----[时间同步]----明日计划");
                }
                calendar2.set(calendar.get(1), calendar.get(2), calendar.get(5));
                i.h(getContext(), "KEY_USECAR_START_TIME", calendar2.getTimeInMillis());
            }
        } else {
            i.g(getContext(), "KEY_USECAR_SWITCH", false);
            l.a(this.r, this.h, this.i, this.g, this.m, this.j, this.p);
        }
        if (!z2 || (bVar = this.e) == null) {
            return;
        }
        bVar.b(0, "TYPE_ALARM_SWITCH_USECAR");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_price_time /* 2131296454 */:
            case R.id.tv_price_time /* 2131296635 */:
                v(false);
                break;
            case R.id.tv_more /* 2131296631 */:
                c.a.e.b bVar = this.e;
                if (bVar != null) {
                    bVar.b(1, "");
                }
                break;
            case R.id.tv_start_time /* 2131296639 */:
            case R.id.tv_usecar /* 2131296643 */:
            case R.id.tv_week /* 2131296645 */:
                w(false);
                break;
        }
    }

    public final void p() {
        if (this.f1986b) {
            return;
        }
        int chargingGunState = BYDAutoChargingDevice.getInstance(getContext()).getChargingGunState();
        int[] hEVChargePlanStartTime = BYDAutoChargingDevice.getInstance(getContext()).getHEVChargePlanStartTime();
        int[] hEVChargePlaneEndTime = BYDAutoChargingDevice.getInstance(getContext()).getHEVChargePlaneEndTime();
        BydLogHelper.logI("smart_charge", "hevStartTime:" + Arrays.toString(hEVChargePlanStartTime));
        BydLogHelper.logI("smart_charge", "hevEndTime:" + Arrays.toString(hEVChargePlaneEndTime));
        Calendar calendar = Calendar.getInstance();
        calendar.set(hEVChargePlanStartTime[0], hEVChargePlanStartTime[1] + (-1), hEVChargePlanStartTime[2], hEVChargePlanStartTime[3], hEVChargePlanStartTime[4], 0);
        calendar.set(14, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(hEVChargePlaneEndTime[0], hEVChargePlaneEndTime[1] - 1, hEVChargePlaneEndTime[2], hEVChargePlaneEndTime[3], hEVChargePlaneEndTime[4], 0);
        calendar2.set(14, 0);
        Calendar calendar3 = Calendar.getInstance();
        int i = calendar3.get(1);
        int i2 = calendar3.get(2);
        BydLogHelper.logI("smart_charge", "year:" + i + ",month：" + (i2 + 1) + ",day：" + calendar3.get(5) + ",hour：" + calendar3.get(11) + ",minute：" + calendar3.get(12));
        if (chargingGunState == 1 || chargingGunState == 2 || chargingGunState == 3) {
            i(BYDAutoChargingDevice.getInstance(getContext()).getBatteryManagementDeviceState(), calendar, calendar2, hEVChargePlanStartTime, hEVChargePlaneEndTime, calendar3);
        } else {
            j(calendar, calendar2, hEVChargePlanStartTime, hEVChargePlaneEndTime, calendar3);
        }
    }

    public final void q() {
        long jD = i.d(getContext(), "KEY_PRICE_START_TIME");
        long jD2 = i.d(getContext(), "KEY_PRICE_END_TIME");
        if (i.b(getContext(), "KEY_PRICE_FULL")) {
            this.j.setText(getResources().getString(R.string.no_price_discount));
            return;
        }
        String strD = c.a.d.e.d(jD / 60, jD % 60);
        String strD2 = c.a.d.e.d(jD2 / 60, jD2 % 60);
        String string = jD > jD2 ? getResources().getString(R.string.next_day) : "";
        this.j.setText(strD + "-" + strD2 + string);
    }

    public final void r() {
        long jD = i.d(getContext(), "KEY_USECAR_START_TIME");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(jD);
        this.g.setText(c.a.d.e.c(calendar.get(11), calendar.get(12)));
    }

    public final void s() {
        this.i.setText(c.a.d.e.e(getContext(), i.f(getContext(), "KEY_USECAR_CYCLE")));
    }

    public final void t(Context context) {
        boolean zC = c.a.d.d.c(getContext());
        this.f1986b = zC;
        View viewInflate = LayoutInflater.from(context).inflate(c.a.d.c.a(zC ? R.layout.view_plan_usecar : R.layout.view_plan_usecar_hev), (ViewGroup) null, false);
        addView(viewInflate);
        this.f = (TextView) viewInflate.findViewById(R.id.tv_more);
        this.g = (TextView) viewInflate.findViewById(R.id.tv_start_time);
        this.h = (TextView) viewInflate.findViewById(R.id.tv_usecar);
        this.i = (TextView) viewInflate.findViewById(R.id.tv_week);
        this.j = (TextView) viewInflate.findViewById(R.id.tv_price_time);
        this.m = (TextView) viewInflate.findViewById(R.id.tv_price_title);
        this.p = (ImageView) viewInflate.findViewById(R.id.iv_price_time);
        this.n = (BydSwitch) viewInflate.findViewById(R.id.sbg_charge);
        this.r = (ImageView) viewInflate.findViewById(R.id.iv_bg);
        if (this.f1986b) {
            return;
        }
        this.k = (TextView) viewInflate.findViewById(R.id.tv_charge_status);
        this.l = (TextView) viewInflate.findViewById(R.id.tv_remain_time);
        this.o = (ChargeAnimView) viewInflate.findViewById(R.id.charge_anim_view);
        this.q = (ImageView) viewInflate.findViewById(R.id.ic_chargegun_highlight);
    }

    public void u() {
        ChargeAnimView chargeAnimView;
        this.r.setImageDrawable(null);
        if (!this.f1986b && this.f1987c != null) {
            BYDAutoChargingDevice.getInstance(getContext()).unregisterListener(this.f1987c);
        }
        if (!this.f1986b && this.f1988d != null) {
            BYDAutoStatisticDevice.getInstance(getContext()).unregisterListener(this.f1988d);
        }
        if (this.f1986b || (chargeAnimView = this.o) == null) {
            return;
        }
        chargeAnimView.d();
    }

    public void v(boolean z) {
        int iIntValue;
        int iIntValue2;
        int iIntValue3;
        boolean z2;
        HashMap<String, Object> map;
        int i = -1;
        if (!z || (map = c.a.d.f.f1598c) == null || map.get("plan_price_cbFull") == null || c.a.d.f.f1598c.get("plan_price_defaultH") == null || c.a.d.f.f1598c.get("plan_price_defaultMin") == null || c.a.d.f.f1598c.get("plan_price_defaultHE") == null || c.a.d.f.f1598c.get("plan_price_defaultMinE") == null) {
            boolean zB = i.b(getContext(), "KEY_PRICE_FULL");
            long jD = i.d(getContext(), "KEY_PRICE_START_TIME");
            long jD2 = i.d(getContext(), "KEY_PRICE_END_TIME");
            if (jD < 0 || jD2 < 0) {
                iIntValue = -1;
                iIntValue2 = -1;
                iIntValue3 = -1;
                z2 = zB;
            } else {
                int i2 = (int) jD;
                int i3 = (int) jD2;
                z2 = zB;
                iIntValue = i2 % 60;
                i = i2 / 60;
                iIntValue3 = i3 % 60;
                iIntValue2 = i3 / 60;
            }
        } else {
            boolean zBooleanValue = ((Boolean) c.a.d.f.f1598c.get("plan_price_cbFull")).booleanValue();
            int iIntValue4 = ((Integer) c.a.d.f.f1598c.get("plan_price_defaultH")).intValue();
            iIntValue = ((Integer) c.a.d.f.f1598c.get("plan_price_defaultMin")).intValue();
            iIntValue2 = ((Integer) c.a.d.f.f1598c.get("plan_price_defaultHE")).intValue();
            iIntValue3 = ((Integer) c.a.d.f.f1598c.get("plan_price_defaultMinE")).intValue();
            z2 = zBooleanValue;
            i = iIntValue4;
        }
        c.a.e.a aVarI = c.a.d.f.i(getContext(), "plan_price", getResources().getString(R.string.discount_price_zone), i + "", iIntValue + "", iIntValue2 + "", iIntValue3 + "", z2, new e(), new f());
        aVarI.t(false);
        aVarI.w();
    }

    public void w(boolean z) {
        String strF;
        int iIntValue;
        int iIntValue2;
        HashMap<String, Object> map;
        if (!z || (map = c.a.d.f.f1598c) == null || map.get("plan_usecar_time_defaultH") == null || c.a.d.f.f1598c.get("plan_usecar_time_defaultMin") == null || c.a.d.f.f1598c.get("plan_usecar_time_strCycle") == null) {
            long jD = i.d(getContext(), "KEY_USECAR_START_TIME");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(jD);
            int i = calendar.get(11);
            int i2 = calendar.get(12);
            strF = i.f(getContext(), "KEY_USECAR_CYCLE");
            iIntValue = i2;
            iIntValue2 = i;
        } else {
            iIntValue2 = ((Integer) c.a.d.f.f1598c.get("plan_usecar_time_defaultH")).intValue();
            iIntValue = ((Integer) c.a.d.f.f1598c.get("plan_usecar_time_defaultMin")).intValue();
            strF = (String) c.a.d.f.f1598c.get("plan_usecar_time_strCycle");
        }
        c.a.e.a aVarJ = c.a.d.f.j(getContext(), "plan_usecar_time", getResources().getString(R.string.use_car_time), iIntValue2 + "", iIntValue + "", strF, new c(), new d());
        aVarJ.t(false);
        aVarJ.w();
    }

    public PlanUseCarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1986b = true;
        t(context);
    }
}
