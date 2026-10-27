package com.byd.mvp.ui.widget.calendarview.adapter;

import android.content.res.Resources;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.a.d.e.a.a.b;
import com.byd.mvp.R$color;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.ui.adapter.BydAdapter;
import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CalendarDayAdapter extends BydAdapter<CalendarDate> {
    public static final String i = CalendarDayAdapter.class.getSimpleName();
    public CalendarDate j;
    public CalendarDate k;
    public CalendarDate l;
    public CalendarDate m;
    public String n;
    public int o;
    public int p;

    public class a extends BydAdapter<CalendarDate>.BaseViewHolder {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f753c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public TextView f754d;
        public TextView e;
        public RelativeLayout f;

        public a(View view) {
            super(view);
            this.f753c = (TextView) view.findViewById(R$id.tv_text_item_date_day);
            this.f754d = (TextView) view.findViewById(R$id.tv_text_hint_item_date_day);
            this.e = (TextView) view.findViewById(R$id.tv_today_hint_item_date_day);
            this.f = (RelativeLayout) view.findViewById(R$id.rl_item_date_day);
        }
    }

    public CalendarDayAdapter(List<CalendarDate> list, CalendarDate calendarDate) {
        super(list);
        this.o = -1;
        this.p = -1;
        this.j = calendarDate;
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter, b.a.d.e.a.a.a
    public void a(int i2, int i3) {
        b bVar = this.f685c;
        if (bVar != null) {
            bVar.a(i2, i3);
        }
        notifyItemRemoved(i2);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<CalendarDate>.BaseViewHolder e(View view) {
        return new a(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int g() {
        return R$layout.item_calendar;
    }

    public void i(CalendarDate calendarDate, String str) {
        this.k = calendarDate;
        this.n = str;
        notifyDataSetChanged();
    }

    public void j(CalendarDate calendarDate) {
        this.m = calendarDate;
    }

    public void k(CalendarDate calendarDate) {
        this.l = calendarDate;
    }

    public void l(int i2) {
        this.p = i2;
    }

    public void m(CalendarDate calendarDate) {
        int i2;
        if (calendarDate != null) {
            i2 = 0;
            while (i2 < this.f684b.size()) {
                if (((CalendarDate) this.f684b.get(i2)).month == calendarDate.month && ((CalendarDate) this.f684b.get(i2)).day == calendarDate.day) {
                    break;
                } else {
                    i2++;
                }
            }
            i2 = -1;
        } else {
            i2 = -1;
        }
        if (i2 == this.o) {
            return;
        }
        this.o = i2;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        CalendarDate calendarDate;
        a aVar = (a) viewHolder;
        aVar.f687a = i2;
        CalendarDate calendarDate2 = (CalendarDate) this.f684b.get(i2);
        if (this.j.month != calendarDate2.month) {
            aVar.f.setVisibility(8);
            return;
        }
        aVar.f.setVisibility(0);
        aVar.f753c.setText(calendarDate2.day + "");
        int[] iArr = new int[3];
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        Resources resources = aVar.f753c.getContext().getResources();
        int color = resources.getColor(R$color.text_color_black);
        if (calendarDate2.moreThan(i3, i4, i5)) {
            color = resources.getColor(R$color.text_color_gray_hint);
        }
        CalendarDate calendarDate3 = this.l;
        if (calendarDate3 != null && calendarDate2.lessThan(calendarDate3)) {
            color = resources.getColor(R$color.text_color_gray_hint);
        }
        CalendarDate calendarDate4 = this.m;
        if (calendarDate4 != null && calendarDate2.moreThan(calendarDate4)) {
            color = resources.getColor(R$color.text_color_gray_hint);
        }
        aVar.f753c.setTextColor(color);
        if (calendarDate2.equals(i3, i4, i5)) {
            aVar.e.setVisibility(0);
        } else {
            aVar.e.setVisibility(8);
        }
        CalendarDate calendarDate5 = this.k;
        if (calendarDate5 == null || !calendarDate5.equals(calendarDate2)) {
            aVar.f754d.setVisibility(8);
        } else {
            aVar.f754d.setVisibility(0);
            TextView textView = aVar.f754d;
            String str = this.n;
            textView.setText(str != null ? str : "");
        }
        int i6 = this.p;
        if (i6 > 0) {
            aVar.f.setBackgroundResource(i6);
        }
        if (this.o == i2) {
            aVar.f.setSelected(true);
            aVar.f753c.setTextColor(resources.getColor(R$color.text_color_white));
        } else {
            aVar.f.setSelected(false);
        }
        CalendarDate calendarDate6 = this.l;
        if ((calendarDate6 == null || !calendarDate2.lessThan(calendarDate6)) && ((calendarDate = this.m) == null || !calendarDate2.moreThan(calendarDate))) {
            return;
        }
        aVar.f.setSelected(false);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter, android.view.View.OnClickListener
    public void onClick(View view) {
        int iA = ((BydAdapter.BaseViewHolder) view.getTag()).a();
        CalendarDate calendarDate = (CalendarDate) this.f684b.get(iA);
        if (calendarDate == null) {
            return;
        }
        CalendarDate calendarDate2 = this.l;
        if (calendarDate2 == null || !calendarDate.lessThan(calendarDate2)) {
            CalendarDate calendarDate3 = this.m;
            if ((calendarDate3 == null || !calendarDate.moreThan(calendarDate3)) && calendarDate.month == this.j.month) {
                super.onClick(view);
                this.o = iA;
                notifyDataSetChanged();
            }
        }
    }
}
