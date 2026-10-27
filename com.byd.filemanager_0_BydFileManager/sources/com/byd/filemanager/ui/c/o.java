package com.byd.filemanager.ui.c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import com.byd.filemanager.ThreadManager;

/* JADX INFO: compiled from: SortPopup.java */
/* JADX INFO: loaded from: classes.dex */
public class o extends b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String[] f3780c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.byd.filemanager.ui.a<String> f3781d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3782e;

    /* JADX INFO: compiled from: SortPopup.java */
    public class a extends RecyclerView.g<b> {

        /* JADX INFO: renamed from: com.byd.filemanager.ui.c.o$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: SortPopup.java */
        class ViewOnClickListenerC0094a implements View.OnClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ b f3784b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f3785c;

            /* JADX INFO: renamed from: com.byd.filemanager.ui.c.o$a$a$a, reason: collision with other inner class name */
            /* JADX INFO: compiled from: SortPopup.java */
            class RunnableC0095a implements Runnable {
                RunnableC0095a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    o oVar = o.this;
                    com.byd.filemanager.k.j.c(oVar.f3739a, "sort_index", oVar.f3782e);
                }
            }

            ViewOnClickListenerC0094a(b bVar, String str) {
                this.f3784b = bVar;
                this.f3785c = str;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                o.this.f3782e = this.f3784b.j();
                ThreadManager.h().k(new RunnableC0095a());
                if (o.this.f3781d != null) {
                    o.this.f3781d.a(this.f3785c, this.f3784b.j());
                }
                com.byd.filemanager.f.h().M(o.this.f3782e);
                org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(7));
                o.this.dismiss();
            }
        }

        /* JADX INFO: compiled from: SortPopup.java */
        protected class b extends RecyclerView.c0 {
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
            return o.this.f3780c.length;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public void l(b bVar, int i) {
            bVar.u.setVisibility(o.this.f3782e == i ? 0 : 4);
            String str = o.this.f3780c[i];
            bVar.t.setText(str);
            if (com.byd.filemanager.f.h().v()) {
                bVar.t.setTextColor(o.this.f3782e == i ? o.this.f3739a.getColor(R.color.color_highlight) : o.this.f3739a.getColor(R.color.text_main));
            } else {
                bVar.t.setTextColor(o.this.f3739a.getColor(R.color.text_main_denza));
            }
            bVar.f1300b.setOnClickListener(new ViewOnClickListenerC0094a(bVar, str));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public b n(ViewGroup viewGroup, int i) {
            return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_sort, viewGroup, false));
        }
    }

    public o(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
        this.f3782e = -1;
        if (appCompatActivity != null) {
            this.f3780c = appCompatActivity.getResources().getStringArray(R.array.sort_items);
            this.f3782e = com.byd.filemanager.f.h().p();
        }
    }

    @Override // com.byd.filemanager.ui.c.b
    protected void a() {
    }

    @Override // com.byd.filemanager.ui.c.b
    protected void b(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.f3739a, 1, false));
        recyclerView.setAdapter(new a());
        androidx.recyclerview.widget.d dVar = new androidx.recyclerview.widget.d(this.f3739a, 1);
        dVar.l(androidx.core.content.a.d(this.f3739a, R.drawable.bg_divider));
        recyclerView.h(dVar);
        recyclerView.setOverScrollMode(2);
    }

    @Override // com.byd.filemanager.ui.c.b
    protected int c() {
        return com.byd.filemanager.f.h().v() ? R.layout.popup_sort : R.layout.popup_sort_denza;
    }

    @Override // com.byd.filemanager.ui.c.b
    protected void d() {
        setWidth(this.f3739a.getResources().getDimensionPixelSize(R.dimen.dp_200));
    }
}
