package com.google.android.material.datepicker;

import a.g.k.t;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.g;

/* JADX INFO: loaded from: classes.dex */
class k extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.google.android.material.datepicker.a f3074c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final d<?> f3075d;
    private final g.l e;
    private final int f;

    class a implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialCalendarGridView f3076b;

        a(MaterialCalendarGridView materialCalendarGridView) {
            this.f3076b = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.f3076b.getAdapter().j(i)) {
                k.this.e.a(this.f3076b.getAdapter().getItem(i).longValue());
            }
        }
    }

    public static class b extends RecyclerView.d0 {
        final TextView t;
        final MaterialCalendarGridView u;

        b(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(b.a.a.a.f.i);
            this.t = textView;
            t.i0(textView, true);
            this.u = (MaterialCalendarGridView) linearLayout.findViewById(b.a.a.a.f.e);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    k(Context context, d<?> dVar, com.google.android.material.datepicker.a aVar, g.l lVar) {
        i iVarZ = aVar.z();
        i iVarW = aVar.w();
        i iVarY = aVar.y();
        if (iVarZ.compareTo(iVarY) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (iVarY.compareTo(iVarW) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.f = (j.f3071b * g.M1(context)) + (h.z1(context) ? g.M1(context) : 0);
        this.f3074c = aVar;
        this.f3075d = dVar;
        this.e = lVar;
        v(true);
    }

    int A(i iVar) {
        return this.f3074c.z().C(iVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public void m(b bVar, int i) {
        i iVarB = this.f3074c.z().B(i);
        bVar.t.setText(iVarB.z());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.u.findViewById(b.a.a.a.f.e);
        if (materialCalendarGridView.getAdapter() == null || !iVarB.equals(materialCalendarGridView.getAdapter().f3072c)) {
            j jVar = new j(iVarB, this.f3075d, this.f3074c);
            materialCalendarGridView.setNumColumns(iVarB.f);
            materialCalendarGridView.setAdapter((ListAdapter) jVar);
        } else {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public b o(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(b.a.a.a.h.i, viewGroup, false);
        if (!h.z1(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.p(-1, this.f));
        return new b(linearLayout, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3074c.x();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long d(int i) {
        return this.f3074c.z().B(i).A();
    }

    i y(int i) {
        return this.f3074c.z().B(i);
    }

    CharSequence z(int i) {
        return y(i).z();
    }
}
