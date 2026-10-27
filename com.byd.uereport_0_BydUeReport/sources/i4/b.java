package i4;

import android.view.View;
import android.view.ViewGroup;
import com.byd.mvp.ui.widget.calendarview.CalendarViewPager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: CalendarPagerAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends n1.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public WeakReference<CalendarViewPager> f5501c;

    /* JADX INFO: compiled from: CalendarPagerAdapter.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.w();
        }
    }

    public b(CalendarViewPager calendarViewPager) {
        this.f5501c = new WeakReference<>(calendarViewPager);
    }

    @Override // n1.a
    public void b(ViewGroup viewGroup, int i8, Object obj) {
        viewGroup.removeView((View) obj);
        m4.b.b(new a());
    }

    @Override // n1.a
    public int e() {
        int i8;
        CalendarViewPager calendarViewPager = this.f5501c.get();
        if (calendarViewPager == null) {
            return 0;
        }
        int i9 = calendarViewPager.f2965q0;
        if (i9 < 0 || (i8 = calendarViewPager.f2966r0) < 0) {
            return Integer.MAX_VALUE;
        }
        return (i8 - i9) + 1;
    }

    @Override // n1.a
    public Object j(ViewGroup viewGroup, int i8) {
        int i9;
        CalendarViewPager calendarViewPager = this.f5501c.get();
        if (calendarViewPager == null) {
            return null;
        }
        int i10 = calendarViewPager.f2965q0;
        if (i10 > 0) {
            i8 += i10;
        }
        if (i10 > 0 && (i9 = calendarViewPager.f2966r0) > 0 && (i8 < i10 || i8 > i9)) {
            return null;
        }
        h4.b bVarG0 = calendarViewPager.f2961m0.get(Integer.valueOf(i8));
        if (bVarG0 == null) {
            bVarG0 = calendarViewPager.g0(viewGroup.getContext(), calendarViewPager.d0(i8), i8);
        }
        int childCount = viewGroup.getChildCount();
        int i11 = 0;
        if (childCount > 0) {
            int i12 = 0;
            while (i11 < childCount) {
                if (bVarG0.equals(viewGroup.getChildAt(i11))) {
                    i12 = 1;
                }
                i11++;
            }
            i11 = i12;
        }
        calendarViewPager.i0(bVarG0);
        if (i11 == 0) {
            viewGroup.addView(bVarG0.f5344d);
        }
        return bVarG0.f5344d;
    }

    @Override // n1.a
    public boolean k(View view, Object obj) {
        return view == obj;
    }

    public final synchronized void w() {
        CalendarViewPager calendarViewPager = this.f5501c.get();
        if (calendarViewPager != null && calendarViewPager.f2961m0 != null) {
            int i8 = calendarViewPager.f2964p0;
            ArrayList arrayList = new ArrayList();
            Iterator<Integer> it = calendarViewPager.f2961m0.keySet().iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                if (Math.abs(i8 - iIntValue) > 14) {
                    arrayList.add(Integer.valueOf(iIntValue));
                }
            }
            if (arrayList.size() <= 0) {
                return;
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                calendarViewPager.f2961m0.remove(Integer.valueOf(((Integer) it2.next()).intValue()));
            }
        }
    }
}
