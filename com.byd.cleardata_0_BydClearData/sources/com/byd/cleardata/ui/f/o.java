package com.byd.cleardata.ui.f;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import com.byd.cleardata.ThreadManager;
import com.byd.cleardata.ui.view.FlowLayoutManager;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class o extends h implements View.OnClickListener {
    private RecyclerView b0;
    private c c0;
    private List<String> d0;
    private com.byd.cleardata.ui.c<String> e0;

    class a implements FlowLayoutManager.a {

        /* JADX INFO: renamed from: com.byd.cleardata.ui.f.o$a$a, reason: collision with other inner class name */
        class RunnableC0106a implements Runnable {
            RunnableC0106a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                o.this.c0.k(0);
            }
        }

        a() {
        }

        @Override // com.byd.cleardata.ui.view.FlowLayoutManager.a
        public void a(int i) {
            com.byd.cleardata.i.g.h(i);
            if (i <= 0 || o.this.d0.size() <= i) {
                return;
            }
            com.byd.cleardata.i.g.f((String) o.this.d0.remove(0));
            ThreadManager.h().i(new RunnableC0106a());
        }
    }

    class b implements com.byd.cleardata.ui.c<String> {
        b() {
        }

        @Override // com.byd.cleardata.ui.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(String str, int i) {
            if (o.this.e0 != null) {
                o.this.e0.a(str, i);
            }
        }
    }

    protected static class c extends RecyclerView.g<b> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private com.byd.cleardata.ui.c<String> f2875c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private List<String> f2876d;

        class a implements View.OnClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String f2877b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ b f2878c;

            a(String str, b bVar) {
                this.f2877b = str;
                this.f2878c = bVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (c.this.f2875c != null) {
                    c.this.f2875c.a(this.f2877b, this.f2878c.j());
                }
            }
        }

        protected static class b extends RecyclerView.d0 {
            TextView t;

            public b(View view) {
                super(view);
                this.t = (TextView) view.findViewById(R.id.tv_name);
            }
        }

        public c(List<String> list) {
            this.f2876d = list;
        }

        private String y(String str) {
            if (str.length() <= 10) {
                return str;
            }
            return str.substring(0, 10) + "...";
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public b o(ViewGroup viewGroup, int i) {
            return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_history, viewGroup, false));
        }

        public void B(com.byd.cleardata.ui.c<String> cVar) {
            this.f2875c = cVar;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            List<String> list = this.f2876d;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
        public void m(b bVar, int i) {
            String str = this.f2876d.get(i);
            bVar.t.setText(y(str));
            bVar.f1454b.setOnClickListener(new a(str, bVar));
        }
    }

    @Override // com.byd.cleardata.ui.f.h
    protected void A1(View view) {
        this.b0 = (RecyclerView) view.findViewById(R.id.recyclerView);
        view.findViewById(R.id.iv_delete).setOnClickListener(this);
        List<String> listC = com.byd.cleardata.i.g.c();
        this.d0 = listC;
        this.c0 = new c(listC);
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager(new a());
        flowLayoutManager.i2(2);
        this.b0.setLayoutManager(flowLayoutManager);
        androidx.recyclerview.widget.d dVar = new androidx.recyclerview.widget.d(j1(), 0);
        dVar.l(androidx.core.content.a.d(j1(), R.drawable.bg_divider_flow));
        this.b0.h(dVar);
        this.b0.setAdapter(this.c0);
        this.c0.B(new b());
    }

    @Override // com.byd.cleardata.ui.f.h
    protected int B1() {
        return R.layout.fragment_search_history;
    }

    public void F1(com.byd.cleardata.ui.c<String> cVar) {
        this.e0 = cVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.iv_delete) {
            com.byd.cleardata.i.g.b();
            this.d0.clear();
            this.c0.h();
        }
    }
}
