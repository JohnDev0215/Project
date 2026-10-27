package com.byd.mvp.ui.widget.calendarview.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.viewpager.widget.PagerAdapter;
import b.a.d.f.b;
import com.byd.mvp.ui.widget.calendarview.CalendarViewPager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class CalendarPagerAdapter extends PagerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakReference<CalendarViewPager> f755a;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CalendarPagerAdapter.this.b();
        }
    }

    public CalendarPagerAdapter(CalendarViewPager calendarViewPager) {
        this.f755a = new WeakReference<>(calendarViewPager);
    }

    public final synchronized void b() {
        CalendarViewPager calendarViewPager = this.f755a.get();
        if (calendarViewPager != null && calendarViewPager.f748c != null) {
            int i = calendarViewPager.f;
            ArrayList arrayList = new ArrayList();
            Iterator<Integer> it = calendarViewPager.f748c.keySet().iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                if (Math.abs(i - iIntValue) > 14) {
                    arrayList.add(Integer.valueOf(iIntValue));
                }
            }
            if (arrayList.size() <= 0) {
                return;
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                calendarViewPager.f748c.remove(Integer.valueOf(((Integer) it2.next()).intValue()));
            }
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
        b.a(new a());
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        int i;
        CalendarViewPager calendarViewPager = this.f755a.get();
        if (calendarViewPager == null) {
            return 0;
        }
        int i2 = calendarViewPager.g;
        return (i2 < 0 || (i = calendarViewPager.h) < 0) ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : (i - i2) + 1;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        int i2;
        CalendarViewPager calendarViewPager = this.f755a.get();
        if (calendarViewPager == null) {
            return null;
        }
        int i3 = calendarViewPager.g;
        if (i3 > 0) {
            i += i3;
        }
        if (i3 > 0 && (i2 = calendarViewPager.h) > 0 && (i < i3 || i > i2)) {
            return null;
        }
        b.a.d.e.c.a.b bVarO = calendarViewPager.f748c.get(Integer.valueOf(i));
        if (bVarO == null) {
            bVarO = calendarViewPager.o(viewGroup.getContext(), calendarViewPager.k(i), i);
        }
        int childCount = viewGroup.getChildCount();
        int i4 = 0;
        if (childCount > 0) {
            int i5 = 0;
            while (i4 < childCount) {
                if (bVarO.equals(viewGroup.getChildAt(i4))) {
                    i5 = 1;
                }
                i4++;
            }
            i4 = i5;
        }
        calendarViewPager.q(bVarO);
        if (i4 == 0) {
            viewGroup.addView(bVarO.f348d);
        }
        return bVarO.f348d;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
