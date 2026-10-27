package com.byd.widget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import c.a.d.c;
import c.a.d.e;
import c.a.d.f;
import c.a.d.i;
import c.a.d.k;
import c.a.d.l;
import com.byd.widget.BydSwitch;
import com.bydux.api.R;
import java.util.Calendar;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class PlanChargeView extends FrameLayout implements View.OnClickListener, BydSwitch.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient TextView f1981b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient TextView f1982c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient TextView f1983d;
    public transient TextView e;
    public transient TextView f;
    public transient TextView g;
    public transient View h;
    public transient ImageView i;
    public transient BydSwitch j;
    public transient c.a.e.a k;
    public transient c.a.e.b l;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Throwable {
            try {
                PlanChargeView.this.k.e();
                f.f1598c.clear();
                if (view.getTag() == null) {
                    return;
                }
                String str = (String) view.getTag();
                String[] strArrSplit = str.split("&");
                if (strArrSplit.length != 4) {
                    return;
                }
                String[] strArrSplit2 = strArrSplit[0].split("\\|");
                i.i(PlanChargeView.this.getContext(), "KEY_CHARGE_CYCLE", strArrSplit[1]);
                int iIntValue = Integer.valueOf(strArrSplit[2]).intValue();
                i.g(PlanChargeView.this.getContext(), "KEY_CHARGE_FULL", "1".equals(strArrSplit[3]));
                k.b("showTimeSelect : " + str);
                if (strArrSplit2.length != 4) {
                    return;
                }
                PlanChargeView.this.g(Integer.parseInt(strArrSplit2[0]), Integer.parseInt(strArrSplit2[1]), Integer.parseInt(strArrSplit2[2]), Integer.parseInt(strArrSplit2[3]), iIntValue);
                PlanChargeView.this.k();
                PlanChargeView.this.j();
                if (PlanChargeView.this.l != null) {
                    PlanChargeView.this.l.b(0, "TYPE_ALARM_DIALOG");
                }
            } catch (Exception e) {
                Log.e("smart_charge", e.getMessage());
            }
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PlanChargeView.this.k.e();
            f.f1598c.clear();
        }
    }

    public PlanChargeView(Context context, c.a.e.b bVar) {
        super(context);
        l(context);
        this.l = bVar;
    }

    @Override // com.byd.widget.BydSwitch.a
    public void c(View view, MotionEvent motionEvent, boolean z) throws Throwable {
        if (view.isEnabled()) {
            ((BydSwitch) view).b(z, false);
            c.a.c.a.d(view, 0);
            if (view.getId() != R.id.sbg_charge) {
                return;
            }
            i(z, true);
        }
    }

    public final void g(int i, int i2, int i3, int i4, int i5) throws Throwable {
        boolean z;
        Calendar calendar = Calendar.getInstance();
        int i6 = calendar.get(11);
        int i7 = calendar.get(12);
        if (i5 != 0 || (i6 * 60) + i7 < (i * 60) + i2) {
            z = false;
        } else {
            calendar.add(5, 1);
            k.b("startCalendar----明日计划");
            z = true;
        }
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), i, i2, 0);
        calendar.set(14, 0);
        k.b("startCalendar : " + calendar.getTime().toString());
        i.h(getContext(), "KEY_CHARGE_START_TIME", calendar.getTimeInMillis());
        Calendar calendar2 = Calendar.getInstance();
        if ((i * 60) + i2 > (i3 * 60) + i4) {
            calendar2.add(5, 1);
            i.g(getContext(), "KEY_CHARGE_NEXTDAY", true);
        } else {
            i.g(getContext(), "KEY_CHARGE_NEXTDAY", false);
        }
        if (z) {
            calendar2.add(5, 1);
        }
        calendar2.set(calendar2.get(1), calendar2.get(2), calendar2.get(5), i3, i4, 0);
        calendar2.set(14, 0);
        k.b("endCalendar : " + calendar2.getTime().toString());
        i.h(getContext(), "KEY_CHARGE_END_TIME", calendar2.getTimeInMillis());
    }

    public void h() throws Throwable {
        this.j.setTime(400L);
        this.f1981b.setOnClickListener(this);
        this.i.setOnClickListener(this);
        boolean z = e.g(i.f(getContext(), "KEY_CHARGE_CYCLE")) == 0;
        k();
        j();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jD = i.d(getContext(), "KEY_CHARGE_START_TIME");
        long jD2 = i.d(getContext(), "KEY_CHARGE_END_TIME");
        boolean zB = i.b(getContext(), "KEY_CHARGE_FULL");
        if (z) {
            if (!zB && jCurrentTimeMillis > jD2) {
                i.g(getContext(), "KEY_CHARGE_SWITCH", false);
            } else if (zB && jCurrentTimeMillis > jD) {
                i.g(getContext(), "KEY_CHARGE_SWITCH", false);
            }
        }
        this.j.b(i.c(getContext(), "KEY_CHARGE_SWITCH", false), false);
        this.j.setOnInterceptTouchListener(this);
        i(this.j.isChecked(), false);
    }

    public final void i(boolean z, boolean z2) throws Throwable {
        c.a.e.b bVar;
        if (z) {
            i.g(getContext(), "KEY_CHARGE_SWITCH", true);
            l.b(this.i, this.g, this.f1982c, this.h, this.f1983d, this.e, this.f);
            if (z2) {
                Calendar calendar = Calendar.getInstance();
                int i = calendar.get(11);
                int i2 = calendar.get(12);
                long jD = i.d(getContext(), "KEY_CHARGE_START_TIME");
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis(jD);
                int i3 = calendar2.get(11);
                int i4 = calendar2.get(12);
                if (e.g(i.f(getContext(), "KEY_CHARGE_CYCLE")) == 0 && (i * 60) + i2 >= (i3 * 60) + i4) {
                    calendar.add(5, 1);
                    k.b("[开关开启]----[时间同步]----明日计划");
                }
                calendar2.set(calendar.get(1), calendar.get(2), calendar.get(5));
                i.h(getContext(), "KEY_CHARGE_START_TIME", calendar2.getTimeInMillis());
                if (!i.b(getContext(), "KEY_CHARGE_FULL")) {
                    long jD2 = i.d(getContext(), "KEY_CHARGE_END_TIME");
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTimeInMillis(jD2);
                    if ((i3 * 60) + i4 > (calendar3.get(11) * 60) + calendar3.get(12)) {
                        calendar.add(5, 1);
                    }
                    calendar3.set(calendar.get(1), calendar.get(2), calendar.get(5));
                    i.h(getContext(), "KEY_CHARGE_END_TIME", calendar3.getTimeInMillis());
                }
            }
        } else {
            l.a(this.i, this.g, this.f1982c, this.h, this.f1983d, this.e, this.f);
            i.g(getContext(), "KEY_CHARGE_SWITCH", false);
        }
        if (!z2 || (bVar = this.l) == null) {
            return;
        }
        bVar.b(0, "TYPE_ALARM_SWITCH");
    }

    public final void j() {
        long jD = i.d(getContext(), "KEY_CHARGE_START_TIME");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(jD);
        int i = calendar.get(11);
        int i2 = calendar.get(12);
        this.f1982c.setText(e.c(i, i2));
        long jD2 = i.d(getContext(), "KEY_CHARGE_END_TIME");
        boolean zB = i.b(getContext(), "KEY_CHARGE_FULL");
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(jD2);
        int i3 = calendar2.get(11);
        int i4 = calendar2.get(12);
        boolean z = (i * 60) + i2 > (i3 * 60) + i4;
        if (zB) {
            this.f1983d.setText(getResources().getString(R.string.charge_to_full_sort));
        } else {
            this.f1983d.setText(e.c(i3, i4));
        }
        if (zB || !z) {
            this.e.setText("");
        } else {
            this.e.setText(getResources().getString(R.string.next_day));
        }
    }

    public final void k() {
        this.f.setText(e.e(getContext(), i.f(getContext(), "KEY_CHARGE_CYCLE")));
    }

    public final void l(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(c.a(R.layout.view_plan_charge), (ViewGroup) null, false);
        addView(viewInflate);
        this.f1981b = (TextView) viewInflate.findViewById(R.id.tv_more);
        this.i = (ImageView) viewInflate.findViewById(R.id.iv_bg);
        this.j = (BydSwitch) viewInflate.findViewById(R.id.sbg_charge);
        this.f1982c = (TextView) viewInflate.findViewById(R.id.tv_start_time);
        this.f1983d = (TextView) viewInflate.findViewById(R.id.tv_end_time);
        this.e = (TextView) viewInflate.findViewById(R.id.tv_end_time_tag);
        this.f = (TextView) viewInflate.findViewById(R.id.tv_week);
        this.g = (TextView) viewInflate.findViewById(R.id.tv_title);
        this.h = viewInflate.findViewById(R.id.tv_time_interval);
    }

    public void m() {
        this.i.setImageDrawable(null);
        this.k = null;
    }

    public void n(boolean z) {
        int iIntValue;
        boolean zC;
        String strF;
        int iIntValue2;
        int iIntValue3;
        int iIntValue4;
        HashMap<String, Object> map;
        c.a.e.a aVar = this.k;
        if (aVar != null && aVar.l()) {
            this.k.e();
        }
        if (!z || (map = f.f1598c) == null || map.get("plan_charge_time_defaultH") == null || f.f1598c.get("plan_charge_time_defaultMin") == null || f.f1598c.get("plan_charge_time_defaultHE") == null || f.f1598c.get("plan_charge_time_defaultMinE") == null || f.f1598c.get("plan_charge_time_cbFull") == null || f.f1598c.get("plan_charge_time_strCycle") == null) {
            long jD = i.d(getContext(), "KEY_CHARGE_START_TIME");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(jD);
            int i = calendar.get(11);
            int i2 = calendar.get(12);
            long jD2 = i.d(getContext(), "KEY_CHARGE_END_TIME");
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(jD2);
            int i3 = calendar2.get(11);
            iIntValue = calendar2.get(12);
            zC = i.c(getContext(), "KEY_CHARGE_FULL", true);
            strF = i.f(getContext(), "KEY_CHARGE_CYCLE");
            iIntValue2 = i3;
            iIntValue3 = i;
            iIntValue4 = i2;
        } else {
            iIntValue3 = ((Integer) f.f1598c.get("plan_charge_time_defaultH")).intValue();
            iIntValue4 = ((Integer) f.f1598c.get("plan_charge_time_defaultMin")).intValue();
            iIntValue2 = ((Integer) f.f1598c.get("plan_charge_time_defaultHE")).intValue();
            iIntValue = ((Integer) f.f1598c.get("plan_charge_time_defaultMinE")).intValue();
            zC = ((Boolean) f.f1598c.get("plan_charge_time_cbFull")).booleanValue();
            strF = (String) f.f1598c.get("plan_charge_time_strCycle");
        }
        c.a.e.a aVarH = f.h(getContext(), "plan_charge_time", getResources().getString(R.string.time_with_end), iIntValue3 + "", iIntValue4 + "", iIntValue2 + "", iIntValue + "", zC, strF, new a(), new b());
        this.k = aVarH;
        aVarH.t(false);
        this.k.w();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        c.a.e.b bVar;
        if (c.a.d.b.b(500L)) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_bg) {
            n(false);
        } else if (id == R.id.tv_more && (bVar = this.l) != null) {
            bVar.b(1, "");
        }
    }
}
