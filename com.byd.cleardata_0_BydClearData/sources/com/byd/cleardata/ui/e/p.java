package com.byd.cleardata.ui.e;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import com.byd.cleardata.ThreadManager;

/* JADX INFO: loaded from: classes.dex */
public class p extends b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String[] f2835c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.byd.cleardata.ui.c<String> f2836d;
    private int e;

    public class a extends RecyclerView.g<b> {

        /* JADX INFO: renamed from: com.byd.cleardata.ui.e.p$a$a, reason: collision with other inner class name */
        class ViewOnClickListenerC0102a implements View.OnClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ b f2838b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f2839c;

            /* JADX INFO: renamed from: com.byd.cleardata.ui.e.p$a$a$a, reason: collision with other inner class name */
            class RunnableC0103a implements Runnable {
                RunnableC0103a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    p pVar = p.this;
                    com.byd.cleardata.i.l.c(pVar.f2799a, "sort_index", pVar.e);
                }
            }

            ViewOnClickListenerC0102a(b bVar, String str) {
                this.f2838b = bVar;
                this.f2839c = str;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                p.this.e = this.f2838b.j();
                ThreadManager.h().j(new RunnableC0103a());
                if (p.this.f2836d != null) {
                    p.this.f2836d.a(this.f2839c, this.f2838b.j());
                }
                com.byd.cleardata.b.h().E(p.this.e);
                org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.f(7));
                p.this.dismiss();
            }
        }

        protected class b extends RecyclerView.d0 {
            TextView t;
            ImageView u;

            public b(View view) {
                super(view);
                this.t = (TextView) view.findViewById(R.id.tv_item);
                this.u = (ImageView) view.findViewById(R.id.iv_selected);
            }
        }

        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return p.this.f2835c.length;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void m(b bVar, int i) {
            androidx.appcompat.app.c cVar;
            int i2;
            bVar.u.setVisibility(p.this.e == i ? 0 : 4);
            String str = p.this.f2835c[i];
            bVar.t.setText(str);
            TextView textView = bVar.t;
            if (p.this.e == i) {
                cVar = p.this.f2799a;
                i2 = R.color.color_highlight;
            } else {
                cVar = p.this.f2799a;
                i2 = R.color.text_main;
            }
            textView.setTextColor(cVar.getColor(i2));
            bVar.f1454b.setOnClickListener(new ViewOnClickListenerC0102a(bVar, str));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public b o(ViewGroup viewGroup, int i) {
            return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_sort, viewGroup, false));
        }
    }

    public p(androidx.appcompat.app.c cVar) {
        super(cVar);
        this.e = -1;
        this.f2835c = cVar.getResources().getStringArray(R.array.sort_items);
        this.e = com.byd.cleardata.b.h().p();
    }

    @Override // com.byd.cleardata.ui.e.b
    protected void a() {
    }

    @Override // com.byd.cleardata.ui.e.b
    protected void b(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.f2799a, 1, false));
        recyclerView.setAdapter(new a());
        androidx.recyclerview.widget.d dVar = new androidx.recyclerview.widget.d(this.f2799a, 1);
        dVar.l(androidx.core.content.a.d(this.f2799a, R.drawable.bg_divider));
        recyclerView.h(dVar);
        recyclerView.setOverScrollMode(2);
    }

    @Override // com.byd.cleardata.ui.e.b
    protected int c() {
        return R.layout.popup_sort;
    }

    @Override // com.byd.cleardata.ui.e.b
    protected void d() {
        setWidth(this.f2799a.getResources().getDimensionPixelSize(R.dimen.dp_200));
    }
}
