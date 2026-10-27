package defpackage;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.ou2;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;
import org.chromium.components.browser_ui.util.R$string;
import org.chromium.components.browser_ui.widget.MoreProgressButton;
import org.chromium.components.browser_ui.widget.R$layout;

/* JADX INFO: compiled from: DateDividedAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class b60 extends RecyclerView.e<RecyclerView.a0> {
    public int h;
    public final TreeSet i = new TreeSet(new a());

    /* JADX INFO: compiled from: DateDividedAdapter.java */
    public class a implements Comparator<i> {
        @Override // java.util.Comparator
        public final int compare(i iVar, i iVar2) {
            i iVar3 = iVar;
            i iVar4 = iVar2;
            if (iVar3 == iVar4) {
                return 0;
            }
            return iVar3.c() != iVar4.c() ? iVar3.c() < iVar4.c() ? -1 : 1 : b60.E(iVar3.a, iVar4.a);
        }
    }

    /* JADX INFO: compiled from: DateDividedAdapter.java */
    public static class b extends RecyclerView.a0 {
        public b(View view) {
            super(view);
        }
    }

    /* JADX INFO: compiled from: DateDividedAdapter.java */
    public class c extends k {
        public final long c;

        public c(long j) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            this.c = calendar.getTime().getTime();
        }

        @Override // b60.k
        public final long a() {
            Date date = new Date(this.c);
            Calendar calendarA = gl.a();
            calendarA.setTime(date);
            return (((long) calendarA.get(1)) << 16) + ((long) calendarA.get(6));
        }

        @Override // b60.k
        public final long b() {
            return this.c;
        }
    }

    /* JADX INFO: compiled from: DateDividedAdapter.java */
    public static class d extends RecyclerView.a0 {
        public final TextView y;

        public d(View view) {
            super(view);
            if (view instanceof TextView) {
                this.y = (TextView) view;
            }
        }
    }

    /* JADX INFO: compiled from: DateDividedAdapter.java */
    public static class e extends g {
        public e(MoreProgressButton moreProgressButton) {
            super(moreProgressButton);
        }

        @Override // b60.g, b60.k
        public final long b() {
            return Long.MIN_VALUE;
        }
    }

    /* JADX INFO: compiled from: DateDividedAdapter.java */
    public static class f extends i {
        @Override // b60.i
        public final int b(int i) {
            return -2;
        }

        @Override // b60.i
        public final int c() {
            return 4;
        }
    }

    /* JADX INFO: compiled from: DateDividedAdapter.java */
    public static class g extends k {
        public final long c = Long.MIN_VALUE - ((long) (-1));
        public final View d;

        public g(MoreProgressButton moreProgressButton) {
            this.d = moreProgressButton;
        }

        @Override // b60.k
        public final long a() {
            return this.c;
        }

        @Override // b60.k
        public long b() {
            return Long.MAX_VALUE;
        }
    }

    /* JADX INFO: compiled from: DateDividedAdapter.java */
    public static class h extends i {
        @Override // b60.i
        public final int b(int i) {
            return -1;
        }

        @Override // b60.i
        public final int c() {
            return 1;
        }
    }

    /* JADX INFO: compiled from: DateDividedAdapter.java */
    public static class j extends RecyclerView.a0 {
    }

    /* JADX INFO: compiled from: DateDividedAdapter.java */
    public static abstract class k {
        public int a = -1;
        public boolean b;

        public abstract long a();

        public abstract long b();
    }

    static {
        gl.b();
    }

    public static int E(Date date, Date date2) {
        Calendar calendarA = gl.a();
        Calendar calendarA2 = gl.a();
        calendarA.setTime(date);
        calendarA2.setTime(date2);
        if (calendarA.get(1) == calendarA2.get(1) && calendarA.get(6) == calendarA2.get(6)) {
            return 0;
        }
        return calendarA.before(calendarA2) ? 1 : -1;
    }

    public abstract void D(RecyclerView.a0 a0Var, k kVar);

    public d F(RecyclerView recyclerView) {
        return new d(LayoutInflater.from(recyclerView.getContext()).inflate(K(), (ViewGroup) recyclerView, false));
    }

    public abstract b G(RecyclerView recyclerView);

    public abstract kv0 H(RecyclerView recyclerView);

    public final Pair<i, Integer> I(int i2) {
        for (i iVar : this.i) {
            if (i2 < iVar.b.size()) {
                return new Pair<>(iVar, Integer.valueOf(i2));
            }
            i2 -= iVar.b.size();
        }
        return null;
    }

    public final Pair<Date, k> J(int i2) {
        Pair<i, Integer> pairI = I(i2);
        i iVar = (i) pairI.first;
        Date date = iVar.a;
        int iIntValue = ((Integer) pairI.second).intValue();
        boolean z = iVar.c;
        ArrayList arrayList = iVar.b;
        if (!z) {
            iVar.c = true;
            Collections.sort(arrayList, new c60(iVar));
        }
        return new Pair<>(date, (k) arrayList.get(iIntValue));
    }

    public abstract int K();

    public final boolean L() {
        TreeSet treeSet = this.i;
        return !treeSet.isEmpty() && ((i) treeSet.last()).c() == 4;
    }

    public final boolean M() {
        TreeSet treeSet = this.i;
        return !treeSet.isEmpty() && ((i) treeSet.first()).c() == 1;
    }

    public final void N() {
        if (M()) {
            TreeSet treeSet = this.i;
            treeSet.remove(treeSet.first());
            O();
            o();
        }
    }

    public final void O() {
        this.h = 0;
        for (i iVar : this.i) {
            Iterator it = iVar.b.iterator();
            while (it.hasNext()) {
                ((k) it.next()).a = -1;
            }
            int i2 = this.h;
            boolean z = iVar.c;
            ArrayList arrayList = iVar.b;
            if (!z) {
                iVar.c = true;
                Collections.sort(arrayList, new c60(iVar));
            }
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                ((k) arrayList.get(i3)).a = i2;
                arrayList.size();
                i2++;
            }
            this.h = arrayList.size() + this.h;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.h;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final long l(int i2) {
        if (!this.f) {
            return -1L;
        }
        Pair<Date, k> pairJ = J(i2);
        Object obj = pairJ.second;
        if (obj != null) {
            return ((k) obj).a();
        }
        Date date = (Date) pairJ.first;
        Calendar calendarA = gl.a();
        calendarA.setTime(date);
        return (((long) calendarA.get(1)) << 16) + ((long) calendarA.get(6));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int m(int i2) {
        Pair<i, Integer> pairI = I(i2);
        return ((i) pairI.first).b(((Integer) pairI.second).intValue());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void t(RecyclerView.a0 a0Var, int i2) {
        Pair<i, Integer> pairI = I(i2);
        int iB = ((i) pairI.first).b(((Integer) pairI.second).intValue());
        Pair<Date, k> pairJ = J(i2);
        if (iB == -2) {
            View view = ((e) pairJ.second).d;
            View view2 = ((b) a0Var).e;
            ((ViewGroup) view2).removeAllViews();
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            ((ViewGroup) view2).addView(view);
            return;
        }
        if (iB == -1) {
            View view3 = ((g) pairJ.second).d;
            View view4 = ((b) a0Var).e;
            ((ViewGroup) view4).removeAllViews();
            if (view3.getParent() != null) {
                ((ViewGroup) view3.getParent()).removeView(view3);
            }
            ((ViewGroup) view4).addView(view3);
            return;
        }
        if (iB != 0) {
            if (iB == 1) {
                D(a0Var, (k) pairJ.second);
                return;
            } else {
                if (iB != 2) {
                    return;
                }
                return;
            }
        }
        d dVar = (d) a0Var;
        Date date = (Date) pairJ.first;
        Context context = xz.a;
        Calendar calendar = ou2.a.a;
        calendar.setTimeInMillis(System.currentTimeMillis());
        Calendar calendar2 = ou2.a.b;
        calendar2.setTime(date);
        StringBuilder sb = new StringBuilder();
        if (jl.b(calendar, calendar2)) {
            sb.append(context.getString(R$string.today));
        } else {
            calendar.add(5, -1);
            if (jl.b(calendar, calendar2)) {
                sb.append(context.getString(R$string.yesterday));
            } else {
                sb.append(DateUtils.formatDateTime(context, date.getTime(), 98308));
            }
        }
        dVar.y.setText(sb);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i2, RecyclerView recyclerView) {
        if (i2 == -2) {
            return G(recyclerView);
        }
        if (i2 == -1) {
            return new b(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.date_divided_adapter_header_view_holder, (ViewGroup) recyclerView, false));
        }
        if (i2 == 0) {
            return F(recyclerView);
        }
        if (i2 != 1) {
            return null;
        }
        return H(recyclerView);
    }

    /* JADX INFO: compiled from: DateDividedAdapter.java */
    public static class i {
        public final Date a;
        public final ArrayList b;
        public boolean c;

        public i(long j) {
            this.b = new ArrayList();
            this.a = new Date(j);
            this.c = true;
        }

        public final void a(k kVar) {
            ArrayList arrayList = this.b;
            arrayList.add(kVar);
            this.c = arrayList.size() == 1;
        }

        public int b(int i) {
            return !((k) this.b.get(i)).b ? 1 : 0;
        }

        public int c() {
            return 3;
        }

        public i() {
            this.b = new ArrayList();
            this.a = new Date(0L);
        }
    }
}
