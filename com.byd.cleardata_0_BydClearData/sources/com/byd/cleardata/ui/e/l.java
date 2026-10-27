package com.byd.cleardata.ui.e;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import com.byd.cleardata.i.r;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class l extends com.byd.cleardata.ui.e.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<c> f2821c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.byd.cleardata.d.d f2822d;
    private b e;

    class a implements com.byd.cleardata.ui.c<Integer> {
        a() {
        }

        @Override // com.byd.cleardata.ui.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Integer num, int i) {
            int i2;
            com.byd.cleardata.ui.e.a aVarB2;
            com.byd.cleardata.i.c.b("position = " + i);
            switch (num.intValue()) {
                case 0:
                    g.r2(l.this.f2822d).V1(l.this.f2799a.y());
                    break;
                case 1:
                    i2 = 5;
                    j.X1(i2).V1(l.this.f2799a.y());
                    l.this.dismiss();
                    break;
                case 2:
                    l lVar = l.this;
                    if (com.byd.cleardata.i.a.c(lVar.f2799a, lVar.f2822d) == null) {
                        aVarB2 = m.b2(l.this.f2822d);
                        aVarB2.V1(l.this.f2799a.y());
                        com.byd.cleardata.b.h().C();
                        l.this.dismiss();
                    } else {
                        l lVar2 = l.this;
                        com.byd.cleardata.i.e.J(lVar2.f2799a, lVar2.f2822d);
                        com.byd.cleardata.b.h().C();
                        l.this.dismiss();
                    }
                    break;
                case 3:
                    i2 = 6;
                    j.X1(i2).V1(l.this.f2799a.y());
                    l.this.dismiss();
                    break;
                case 4:
                    e.d2(l.this.f2822d).V1(l.this.f2799a.y());
                    com.byd.cleardata.b.h().C();
                    break;
                case 5:
                    aVarB2 = m.c2(l.this.f2822d, true);
                    aVarB2.V1(l.this.f2799a.y());
                    com.byd.cleardata.b.h().C();
                    l.this.dismiss();
                    break;
                case 6:
                    aVarB2 = o.Z1(l.this.f2822d);
                    aVarB2.V1(l.this.f2799a.y());
                    com.byd.cleardata.b.h().C();
                    l.this.dismiss();
                    break;
            }
        }
    }

    public class b extends RecyclerView.g<C0101b> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private List<c> f2824c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private com.byd.cleardata.ui.c<Integer> f2825d;

        class a extends com.byd.cleardata.ui.view.a {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ C0101b f2826c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ c f2827d;

            a(C0101b c0101b, c cVar) {
                this.f2826c = c0101b;
                this.f2827d = cVar;
            }

            @Override // com.byd.cleardata.ui.view.a
            public void a(View view) {
                if (b.this.f2825d != null) {
                    b.this.f2825d.a(Integer.valueOf(this.f2827d.f2828a), this.f2826c.j());
                }
            }
        }

        /* JADX INFO: renamed from: com.byd.cleardata.ui.e.l$b$b, reason: collision with other inner class name */
        protected class C0101b extends RecyclerView.d0 {
            ImageView t;
            TextView u;

            public C0101b(View view) {
                super(view);
                this.t = (ImageView) view.findViewById(R.id.iv_icon);
                this.u = (TextView) view.findViewById(R.id.tv_name);
            }
        }

        public b(List<c> list) {
            this.f2824c = list;
        }

        public void A(com.byd.cleardata.ui.c<Integer> cVar) {
            this.f2825d = cVar;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return this.f2824c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void m(C0101b c0101b, int i) {
            c cVar = this.f2824c.get(i);
            c0101b.t.setImageResource(cVar.f2830c);
            c0101b.u.setText(cVar.f2829b);
            c0101b.f1454b.setOnClickListener(new a(c0101b, cVar));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
        public C0101b o(ViewGroup viewGroup, int i) {
            return new C0101b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_more, viewGroup, false));
        }
    }

    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2828a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f2829b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2830c;

        public c(int i, int i2, String str) {
            this.f2828a = i;
            this.f2829b = str;
            this.f2830c = i2;
        }
    }

    public l(androidx.appcompat.app.c cVar) {
        super(cVar);
    }

    @Override // com.byd.cleardata.ui.e.b
    protected void a() {
        List<c> list;
        c cVar;
        this.f2821c = new ArrayList();
        List<com.byd.cleardata.d.d> listL = com.byd.cleardata.b.h().l();
        boolean z = listL.size() == 1;
        if (z) {
            this.f2822d = listL.get(0);
        }
        int[] iArrA = r.a(this.f2799a, R.array.icon_list_more);
        String[] stringArray = this.f2799a.getResources().getStringArray(R.array.more_items);
        for (int i = 0; i < stringArray.length; i++) {
            if (z) {
                if (i != 6 || !this.f2822d.K()) {
                    list = this.f2821c;
                    cVar = new c(i, iArrA[i], stringArray[i]);
                    list.add(cVar);
                }
            } else if (i == 1 || i == 3) {
                list = this.f2821c;
                cVar = new c(i, iArrA[i], stringArray[i]);
                list.add(cVar);
            }
        }
    }

    @Override // com.byd.cleardata.ui.e.b
    protected void b(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.f2799a));
        b bVar = new b(this.f2821c);
        this.e = bVar;
        recyclerView.setAdapter(bVar);
        this.e.h();
        com.byd.cleardata.i.k kVar = new com.byd.cleardata.i.k(this.f2799a, R.dimen.dp_24, R.dimen.dp_10);
        kVar.l(androidx.core.content.a.d(this.f2799a, R.drawable.bg_divider));
        recyclerView.h(kVar);
        recyclerView.setOverScrollMode(2);
        this.e.A(new a());
    }

    @Override // com.byd.cleardata.ui.e.b
    protected int c() {
        return R.layout.popup_more;
    }

    @Override // com.byd.cleardata.ui.e.b
    protected void d() {
        setWidth(this.f2799a.getResources().getDimensionPixelSize(R.dimen.dp_184));
    }
}
