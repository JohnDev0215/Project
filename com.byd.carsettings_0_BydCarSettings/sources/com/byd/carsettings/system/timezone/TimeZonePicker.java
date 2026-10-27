package com.byd.carsettings.system.timezone;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import b.b.g.o.f;
import com.byd.carsettings.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class TimeZonePicker extends ListView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f2876a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2877b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Handler f2878c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2879d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2880e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2881f;
    public int g;
    public c h;
    public int i;
    public int j;
    public List<String> k;
    public int l;
    public int m;
    public int n;
    public final int o;
    public boolean p;

    public class a implements AbsListView.OnScrollListener {
        public a() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            TimeZonePicker.this.l();
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            View childAt;
            if (i != 0 || (childAt = TimeZonePicker.this.getChildAt(0)) == null) {
                return;
            }
            float y = childAt.getY();
            if (y != 0.0f) {
                if (Math.abs(y) < TimeZonePicker.this.f2879d / 2) {
                    TimeZonePicker timeZonePicker = TimeZonePicker.this;
                    timeZonePicker.smoothScrollBy(timeZonePicker.g(y), 50);
                } else {
                    TimeZonePicker timeZonePicker2 = TimeZonePicker.this;
                    timeZonePicker2.smoothScrollBy(timeZonePicker2.g(timeZonePicker2.f2879d + y), 50);
                }
            }
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2883a;

        public b(int i) {
            this.f2883a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            TimeZonePicker timeZonePicker = TimeZonePicker.this;
            TimeZonePicker.super.setSelection(timeZonePicker.h(this.f2883a));
        }
    }

    public interface c {
        void onItemSelected(int i, String str);
    }

    public class d extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<String> f2885a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List<String> f2886b = new ArrayList();

        public d() {
        }

        public void a(List<String> list) {
            this.f2885a.clear();
            this.f2885a.addAll(list);
        }

        public void b(List<String> list) {
            this.f2886b.clear();
            this.f2886b.addAll(list);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return (this.f2885a.size() + TimeZonePicker.this.n) - 1;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return "";
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(TimeZonePicker.this.getContext()).inflate(TimeZonePicker.this.g, (ViewGroup) null);
            }
            TextView textView = (TextView) view.findViewById(TimeZonePicker.this.f2880e);
            TextView textView2 = (TextView) view.findViewById(TimeZonePicker.this.f2881f);
            if (i < TimeZonePicker.this.n / 2 || i >= this.f2885a.size() + (TimeZonePicker.this.n / 2)) {
                textView.setText("");
                textView2.setText("");
                view.setVisibility(4);
            } else {
                textView.setText(this.f2885a.get((i - (TimeZonePicker.this.n / 2)) % this.f2885a.size()));
                textView2.setText(this.f2886b.get((i - (TimeZonePicker.this.n / 2)) % this.f2886b.size()));
                view.setVisibility(0);
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return false;
        }
    }

    public TimeZonePicker(Context context) {
        super(context);
        this.l = -1;
        this.m = -7829368;
        this.n = 3;
        this.o = 15;
        this.p = true;
    }

    public int g(float f2) {
        return (int) f2;
    }

    public String getSelectLabel() {
        int selection = getSelection();
        if (selection < 0) {
            selection = 0;
        }
        try {
            return this.k.get(selection);
        } catch (Exception unused) {
            return "";
        }
    }

    public int getSelection() {
        if (this.f2877b == 0) {
            this.f2877b = this.n / 2;
        }
        return (this.f2877b - (this.n / 2)) % this.k.size();
    }

    public int h(int i) {
        List<String> list = this.k;
        if (list == null || list.size() == 0) {
            return 0;
        }
        return i;
    }

    public void i() {
        this.f2878c = new Handler();
        this.g = R.layout.item_time_zone_picker;
        this.f2880e = R.id.tv_zone_name;
        this.f2881f = R.id.tv_zone_gmt;
        this.f2876a = new d();
        setVerticalScrollBarEnabled(false);
        setScrollingCacheEnabled(false);
        setCacheColorHint(0);
        setOverScrollMode(2);
        setDividerHeight(0);
        setAdapter((ListAdapter) this.f2876a);
        setFriction(ViewConfiguration.getScrollFriction() * 6.0f);
        setOnScrollListener(new a());
    }

    public void j() {
        this.f2879d = k();
        getLayoutParams().height = this.f2879d * this.n;
    }

    public final int k() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(this.g, (ViewGroup) null);
        viewInflate.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        viewInflate.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        return viewInflate.getMeasuredHeight();
    }

    public void l() {
        int i = this.n / 2;
        int firstVisiblePosition = getFirstVisiblePosition();
        if (getChildAt(0) != null) {
            int i2 = Math.abs(getChildAt(0).getY()) <= ((float) (this.f2879d / 2)) ? firstVisiblePosition + i : firstVisiblePosition + i + 1;
            if (i2 != this.f2877b) {
                if (!this.p) {
                    f.e(this);
                }
                this.p = false;
                this.f2877b = i2;
                c cVar = this.h;
                if (cVar != null) {
                    cVar.onItemSelected(getSelection(), getSelectLabel());
                }
                m(firstVisiblePosition, i2, i);
            }
        }
    }

    public final void m(int i, int i2, int i3) {
        for (int i4 = (i2 - i3) - 1; i4 < i2 + i3 + 1; i4++) {
            View childAt = getChildAt(i4 - i);
            if (childAt != null) {
                TextView textView = (TextView) childAt.findViewById(this.f2880e);
                TextView textView2 = (TextView) childAt.findViewById(this.f2881f);
                if (i2 == i4) {
                    textView.setTextColor(this.l);
                    textView2.setTextColor(this.l);
                    childAt.setAlpha(1.0f);
                } else {
                    textView.setTextColor(this.m);
                    textView2.setTextColor(this.m);
                }
            }
        }
    }

    public void setLabelSelectedColor(int i) {
        this.l = i;
        m(getFirstVisiblePosition(), this.f2877b, this.n / 2);
    }

    public void setLabelSelectedTextSize(int i) {
        this.i = i;
        m(getFirstVisiblePosition(), this.f2877b, this.n / 2);
    }

    public void setLabelUnSelectedColor(int i) {
        this.m = i;
        m(getFirstVisiblePosition(), this.f2877b, this.n / 2);
    }

    public void setLabelUnSelectedTextSize(int i) {
        this.j = i;
        m(getFirstVisiblePosition(), this.f2877b, this.n / 2);
    }

    public void setLabels(List<String> list) {
        this.k = list;
        this.f2876a.a(list);
        this.f2876a.notifyDataSetChanged();
        j();
    }

    public void setLabelsSecond(List<String> list) {
        this.f2876a.b(list);
        this.f2876a.notifyDataSetChanged();
        j();
    }

    public void setNumberPickerSize(int i) {
        this.n = i;
        j();
    }

    public void setOnItemSelectedListener(c cVar) {
        this.h = cVar;
    }

    @Override // android.widget.ListView, android.widget.AdapterView
    public void setSelection(int i) {
        this.f2878c.post(new b(i));
    }

    public TimeZonePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = -1;
        this.m = -7829368;
        this.n = 3;
        this.o = 15;
        this.p = true;
        i();
    }

    public TimeZonePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = -1;
        this.m = -7829368;
        this.n = 3;
        this.o = 15;
        this.p = true;
    }
}
