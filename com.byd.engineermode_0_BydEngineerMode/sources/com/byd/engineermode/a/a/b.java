package com.byd.engineermode.a.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.R;
import com.byd.engineermode.bean.ElectricBean;
import com.byd.engineermode.utils.StringArrayToList;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ElectronAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends RecyclerView.g<C0065b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<com.byd.engineermode.bean.a> f2246c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.byd.engineermode.a.b.a<ElectricBean> f2247d;
    private Context e;
    private f f;
    private List<f> g = new ArrayList();

    /* JADX INFO: compiled from: ElectronAdapter.java */
    class a implements com.byd.engineermode.a.b.a<ElectricBean> {
        a() {
        }

        @Override // com.byd.engineermode.a.b.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(ElectricBean electricBean, int i, int i2) {
            if (b.this.f2247d != null) {
                b.this.f2247d.b(electricBean, i, i2);
            }
        }

        @Override // com.byd.engineermode.a.b.a
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(ElectricBean electricBean, int i, int i2) {
            com.byd.engineermode.utils.a.a("updateElectric  position = " + i + ", parentPosition = " + i2);
            if (b.this.f2247d != null) {
                b.this.f2247d.a(electricBean, i, i2);
            }
        }
    }

    /* JADX INFO: renamed from: com.byd.engineermode.a.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ElectronAdapter.java */
    protected static class C0065b extends RecyclerView.c0 {
        private final TextView t;
        private final RecyclerView u;

        public C0065b(View view) {
            super(view);
            this.t = (TextView) view.findViewById(R.id.tv_title);
            this.u = (RecyclerView) view.findViewById(R.id.recyclerView);
        }
    }

    public b(Context context, List<com.byd.engineermode.bean.a> list) {
        this.e = context;
        this.f2246c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f2246c.size();
    }

    public void w(int i, int i2) {
        this.g.get(i2).i(i);
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
    public void k(C0065b c0065b, int i) {
        com.byd.engineermode.bean.a aVar = this.f2246c.get(i);
        c0065b.t.setText(StringArrayToList.h().c(aVar.a().b()));
        this.f = new f(aVar.b(), i);
        c0065b.u.setLayoutManager(new LinearLayoutManager(this.e));
        c0065b.u.setAdapter(this.f);
        com.byd.engineermode.utils.a.a("updateElectric position = " + i);
        this.f.B(new a());
        this.g.add(this.f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public C0065b m(ViewGroup viewGroup, int i) {
        return new C0065b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_electric, viewGroup, false));
    }

    public void z(com.byd.engineermode.a.b.a<ElectricBean> aVar) {
        this.f2247d = aVar;
    }
}
