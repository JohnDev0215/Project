package com.byd.mvp.ui.widget.calendarview.adapter;

import android.content.res.Resources;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.c.a.b;
import com.byd.mvp.R$color;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.ui.adapter.BydAdapter;
import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CalendarDayAdapter extends BydAdapter<CalendarDate> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3841a = CalendarDayAdapter.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CalendarDate f3842b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public CalendarDate f3843c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CalendarDate f3844d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CalendarDate f3845e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f3846f;
    public int g;
    public int h;

    public class a extends BydAdapter<CalendarDate>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f3847a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f3848b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f3849c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RelativeLayout f3850d;

        public a(View view) {
            super(view);
            this.f3847a = (TextView) view.findViewById(R$id.tv_text_item_date_day);
            this.f3848b = (TextView) view.findViewById(R$id.tv_text_hint_item_date_day);
            this.f3849c = (TextView) view.findViewById(R$id.tv_today_hint_item_date_day);
            this.f3850d = (RelativeLayout) view.findViewById(R$id.rl_item_date_day);
        }
    }

    public CalendarDayAdapter(List<CalendarDate> list, CalendarDate calendarDate) {
        super(list);
        this.g = -1;
        this.h = -1;
        this.f3842b = calendarDate;
    }

    public void a(CalendarDate calendarDate, String str) {
        this.f3843c = calendarDate;
        this.f3846f = str;
        notifyDataSetChanged();
    }

    public void b(CalendarDate calendarDate) {
        this.f3845e = calendarDate;
    }

    public void d(CalendarDate calendarDate) {
        this.f3844d = calendarDate;
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter, b.b.m.f.c.a.a
    public void drag(int i, int i2) {
        b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.a(i, i2);
        }
        notifyItemRemoved(i);
    }

    public void e(int i) {
        this.h = i;
    }

    public void f(CalendarDate calendarDate) {
        int i;
        if (calendarDate != null) {
            i = 0;
            while (i < this.data.size()) {
                if (((CalendarDate) this.data.get(i)).month == calendarDate.month && ((CalendarDate) this.data.get(i)).day == calendarDate.day) {
                    break;
                } else {
                    i++;
                }
            }
            i = -1;
        } else {
            i = -1;
        }
        if (i == this.g) {
            return;
        }
        this.g = i;
        notifyDataSetChanged();
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<CalendarDate>.BaseViewHolder initViewHolder(View view) {
        return new a(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R$layout.item_calendar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CalendarDate calendarDate;
        a aVar = (a) viewHolder;
        aVar.id = i;
        CalendarDate calendarDate2 = (CalendarDate) this.data.get(i);
        if (this.f3842b.month != calendarDate2.month) {
            aVar.f3850d.setVisibility(8);
            return;
        }
        aVar.f3850d.setVisibility(0);
        aVar.f3847a.setText(calendarDate2.day + "");
        int[] iArr = new int[3];
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        Resources resources = aVar.f3847a.getContext().getResources();
        int color = resources.getColor(R$color.text_color_black);
        if (calendarDate2.moreThan(i2, i3, i4)) {
            color = resources.getColor(R$color.text_color_gray_hint);
        }
        CalendarDate calendarDate3 = this.f3844d;
        if (calendarDate3 != null && calendarDate2.lessThan(calendarDate3)) {
            color = resources.getColor(R$color.text_color_gray_hint);
        }
        CalendarDate calendarDate4 = this.f3845e;
        if (calendarDate4 != null && calendarDate2.moreThan(calendarDate4)) {
            color = resources.getColor(R$color.text_color_gray_hint);
        }
        aVar.f3847a.setTextColor(color);
        if (calendarDate2.equals(i2, i3, i4)) {
            aVar.f3849c.setVisibility(0);
        } else {
            aVar.f3849c.setVisibility(8);
        }
        CalendarDate calendarDate5 = this.f3843c;
        if (calendarDate5 == null || !calendarDate5.equals(calendarDate2)) {
            aVar.f3848b.setVisibility(8);
        } else {
            aVar.f3848b.setVisibility(0);
            TextView textView = aVar.f3848b;
            String str = this.f3846f;
            textView.setText(str != null ? str : "");
        }
        int i5 = this.h;
        if (i5 > 0) {
            aVar.f3850d.setBackgroundResource(i5);
        }
        if (this.g == i) {
            aVar.f3850d.setSelected(true);
            aVar.f3847a.setTextColor(resources.getColor(R$color.text_color_white));
        } else {
            aVar.f3850d.setSelected(false);
        }
        CalendarDate calendarDate6 = this.f3844d;
        if ((calendarDate6 == null || !calendarDate2.lessThan(calendarDate6)) && ((calendarDate = this.f3845e) == null || !calendarDate2.moreThan(calendarDate))) {
            return;
        }
        aVar.f3850d.setSelected(false);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = ((BydAdapter.BaseViewHolder) view.getTag()).getId();
        CalendarDate calendarDate = (CalendarDate) this.data.get(id);
        if (calendarDate == null) {
            return;
        }
        CalendarDate calendarDate2 = this.f3844d;
        if (calendarDate2 == null || !calendarDate.lessThan(calendarDate2)) {
            CalendarDate calendarDate3 = this.f3845e;
            if ((calendarDate3 == null || !calendarDate.moreThan(calendarDate3)) && calendarDate.month == this.f3842b.month) {
                super.onClick(view);
                this.g = id;
                notifyDataSetChanged();
            }
        }
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public void removeItemByPosition(int i) {
        b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.d(i);
        }
        notifyItemRemoved(i);
    }
}
