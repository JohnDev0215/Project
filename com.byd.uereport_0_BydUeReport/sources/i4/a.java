package i4;

import android.content.res.Resources;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c4.a;
import com.byd.mvp.R$color;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import java.util.List;

/* JADX INFO: compiled from: CalendarDayAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends c4.a<CalendarDate> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f5488d = a.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CalendarDate f5489e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CalendarDate f5490f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public CalendarDate f5491g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CalendarDate f5492h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f5493i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f5494j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f5495k;

    /* JADX INFO: renamed from: i4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CalendarDayAdapter.java */
    public class C0062a extends c4.a<CalendarDate>.C0013a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f5496a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f5497b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f5498c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RelativeLayout f5499d;

        public C0062a(View view) {
            super(view);
            this.f5496a = (TextView) view.findViewById(R$id.tv_text_item_date_day);
            this.f5497b = (TextView) view.findViewById(R$id.tv_text_hint_item_date_day);
            this.f5498c = (TextView) view.findViewById(R$id.tv_today_hint_item_date_day);
            this.f5499d = (RelativeLayout) view.findViewById(R$id.rl_item_date_day);
        }
    }

    public a(List<CalendarDate> list, CalendarDate calendarDate) {
        super(list);
        this.f5494j = -1;
        this.f5495k = -1;
        this.f5489e = calendarDate;
    }

    public void a(CalendarDate calendarDate, String str) {
        this.f5490f = calendarDate;
        this.f5493i = str;
        notifyDataSetChanged();
    }

    public void b(CalendarDate calendarDate) {
        this.f5492h = calendarDate;
    }

    public void c(CalendarDate calendarDate) {
        this.f5491g = calendarDate;
    }

    public void d(int i8) {
        this.f5495k = i8;
    }

    @Override // c4.a
    public void drag(int i8, int i9) {
        d4.a aVar = this.mDragItemCallback;
        if (aVar != null) {
            aVar.a(i8, i9);
        }
        notifyItemRemoved(i8);
    }

    public void e(CalendarDate calendarDate) {
        int i8;
        if (calendarDate != null) {
            i8 = 0;
            while (i8 < this.data.size()) {
                if (((CalendarDate) this.data.get(i8)).month == calendarDate.month && ((CalendarDate) this.data.get(i8)).day == calendarDate.day) {
                    break;
                } else {
                    i8++;
                }
            }
            i8 = -1;
        } else {
            i8 = -1;
        }
        if (i8 == this.f5494j) {
            return;
        }
        this.f5494j = i8;
        notifyDataSetChanged();
    }

    @Override // c4.a
    public c4.a<CalendarDate>.C0013a initViewHolder(View view) {
        return new C0062a(view);
    }

    @Override // c4.a
    public int loadLayoutId() {
        return R$layout.item_calendar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(RecyclerView.c0 c0Var, int i8) {
        CalendarDate calendarDate;
        C0062a c0062a = (C0062a) c0Var;
        c0062a.id = i8;
        CalendarDate calendarDate2 = (CalendarDate) this.data.get(i8);
        if (this.f5489e.month != calendarDate2.month) {
            c0062a.f5499d.setVisibility(8);
            return;
        }
        c0062a.f5499d.setVisibility(0);
        c0062a.f5496a.setText(calendarDate2.day + "");
        int[] iArr = new int[3];
        int i9 = iArr[0];
        int i10 = iArr[1];
        int i11 = iArr[2];
        Resources resources = c0062a.f5496a.getContext().getResources();
        int color = resources.getColor(R$color.text_color_black);
        if (calendarDate2.moreThan(i9, i10, i11)) {
            color = resources.getColor(R$color.text_color_gray_hint);
        }
        CalendarDate calendarDate3 = this.f5491g;
        if (calendarDate3 != null && calendarDate2.lessThan(calendarDate3)) {
            color = resources.getColor(R$color.text_color_gray_hint);
        }
        CalendarDate calendarDate4 = this.f5492h;
        if (calendarDate4 != null && calendarDate2.moreThan(calendarDate4)) {
            color = resources.getColor(R$color.text_color_gray_hint);
        }
        c0062a.f5496a.setTextColor(color);
        if (calendarDate2.equals(i9, i10, i11)) {
            c0062a.f5498c.setVisibility(0);
        } else {
            c0062a.f5498c.setVisibility(8);
        }
        CalendarDate calendarDate5 = this.f5490f;
        if (calendarDate5 == null || !calendarDate5.equals(calendarDate2)) {
            c0062a.f5497b.setVisibility(8);
        } else {
            c0062a.f5497b.setVisibility(0);
            TextView textView = c0062a.f5497b;
            String str = this.f5493i;
            textView.setText(str != null ? str : "");
        }
        int i12 = this.f5495k;
        if (i12 > 0) {
            c0062a.f5499d.setBackgroundResource(i12);
        }
        if (this.f5494j == i8) {
            c0062a.f5499d.setSelected(true);
            c0062a.f5496a.setTextColor(resources.getColor(R$color.text_color_white));
        } else {
            c0062a.f5499d.setSelected(false);
        }
        CalendarDate calendarDate6 = this.f5491g;
        if ((calendarDate6 == null || !calendarDate2.lessThan(calendarDate6)) && ((calendarDate = this.f5492h) == null || !calendarDate2.moreThan(calendarDate))) {
            return;
        }
        c0062a.f5499d.setSelected(false);
    }

    @Override // c4.a, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = ((a.C0013a) view.getTag()).getId();
        CalendarDate calendarDate = (CalendarDate) this.data.get(id);
        if (calendarDate == null) {
            return;
        }
        CalendarDate calendarDate2 = this.f5491g;
        if (calendarDate2 == null || !calendarDate.lessThan(calendarDate2)) {
            CalendarDate calendarDate3 = this.f5492h;
            if ((calendarDate3 == null || !calendarDate.moreThan(calendarDate3)) && calendarDate.month == this.f5489e.month) {
                super.onClick(view);
                this.f5494j = id;
                notifyDataSetChanged();
            }
        }
    }

    @Override // c4.a
    public void removeItemByPosition(int i8) {
        d4.a aVar = this.mDragItemCallback;
        if (aVar != null) {
            aVar.d(i8);
        }
        notifyItemRemoved(i8);
    }
}
