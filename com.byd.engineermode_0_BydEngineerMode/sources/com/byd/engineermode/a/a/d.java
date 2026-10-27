package com.byd.engineermode.a.a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.R;
import java.util.List;

/* JADX INFO: compiled from: ItemAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<com.byd.engineermode.bean.c> f2255c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.byd.engineermode.a.b.a<com.byd.engineermode.bean.c> f2256d;

    /* JADX INFO: compiled from: ItemAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.byd.engineermode.bean.c f2257b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b f2258c;

        a(com.byd.engineermode.bean.c cVar, b bVar) {
            this.f2257b = cVar;
            this.f2258c = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (d.this.f2256d != null) {
                d.this.f2256d.a(this.f2257b, this.f2258c.j(), -1);
            }
        }
    }

    /* JADX INFO: compiled from: ItemAdapter.java */
    protected static class b extends RecyclerView.c0 {
        private final TextView t;
        private final TextView u;

        public b(View view) {
            super(view);
            this.t = (TextView) view.findViewById(R.id.tv_name);
            this.u = (TextView) view.findViewById(R.id.tv_tip);
        }
    }

    public d(List<com.byd.engineermode.bean.c> list) {
        this.f2255c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f2255c.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public void k(b bVar, int i) {
        com.byd.engineermode.bean.c cVar = this.f2255c.get(i);
        bVar.t.setText(cVar.a());
        bVar.u.setText(cVar.c());
        if (cVar.b()) {
            bVar.f1135b.setOnClickListener(new a(cVar, bVar));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
    public b m(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false));
    }

    public void y(com.byd.engineermode.a.b.a<com.byd.engineermode.bean.c> aVar) {
        this.f2256d = aVar;
    }
}
