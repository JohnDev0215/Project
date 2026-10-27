package b.d.a.a.v;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import b.d.a.a.v.f;
import com.google.android.material.datepicker.MaterialCalendarGridView;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class r extends RecyclerView.e<a> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f2118c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b.d.a.a.v.a f2119d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d<?> f2120e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f.InterfaceC0044f f2121f;
    public final int g;

    public static class a extends RecyclerView.b0 {
        public final TextView t;
        public final MaterialCalendarGridView u;

        public a(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(b.d.a.a.f.month_title);
            this.t = textView;
            a.h.k.q.V(textView, true);
            this.u = (MaterialCalendarGridView) linearLayout.findViewById(b.d.a.a.f.month_grid);
            if (z) {
                return;
            }
            this.t.setVisibility(8);
        }
    }

    public r(Context context, d<?> dVar, b.d.a.a.v.a aVar, f.InterfaceC0044f interfaceC0044f) {
        o oVar = aVar.f2065a;
        o oVar2 = aVar.f2066b;
        o oVar3 = aVar.f2068d;
        if (oVar.compareTo(oVar3) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (oVar3.compareTo(oVar2) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        int iP = f.P(context) * p.f2110f;
        int dimensionPixelSize = n.P(context) ? context.getResources().getDimensionPixelSize(b.d.a.a.d.mtrl_calendar_day_height) : 0;
        this.f2118c = context;
        this.g = iP + dimensionPixelSize;
        this.f2119d = aVar;
        this.f2120e = dVar;
        this.f2121f = interfaceC0044f;
        if (this.f1636a.a()) {
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }
        this.f1637b = true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public int a() {
        return this.f2119d.f2070f;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public long b(int i) {
        return this.f2119d.f2065a.i(i).f2104a.getTimeInMillis();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public void f(RecyclerView.b0 b0Var, int i) {
        a aVar = (a) b0Var;
        o oVarI = this.f2119d.f2065a.i(i);
        aVar.t.setText(oVarI.h(aVar.f1629a.getContext()));
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) aVar.u.findViewById(b.d.a.a.f.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !oVarI.equals(materialCalendarGridView.getAdapter().f2111a)) {
            p pVar = new p(oVarI, this.f2120e, this.f2119d);
            materialCalendarGridView.setNumColumns(oVarI.f2107d);
            materialCalendarGridView.setAdapter((ListAdapter) pVar);
        } else {
            materialCalendarGridView.invalidate();
            p adapter = materialCalendarGridView.getAdapter();
            Iterator<Long> it = adapter.f2113c.iterator();
            while (it.hasNext()) {
                adapter.f(materialCalendarGridView, it.next().longValue());
            }
            d<?> dVar = adapter.f2112b;
            if (dVar != null) {
                Iterator<Long> it2 = dVar.f().iterator();
                while (it2.hasNext()) {
                    adapter.f(materialCalendarGridView, it2.next().longValue());
                }
                adapter.f2113c = adapter.f2112b.f();
            }
        }
        materialCalendarGridView.setOnItemClickListener(new q(this, materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public RecyclerView.b0 h(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(b.d.a.a.h.mtrl_calendar_month_labeled, viewGroup, false);
        if (!n.P(viewGroup.getContext())) {
            return new a(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.n(-1, this.g));
        return new a(linearLayout, true);
    }

    public o j(int i) {
        return this.f2119d.f2065a.i(i);
    }

    public int k(o oVar) {
        return this.f2119d.f2065a.j(oVar);
    }
}
