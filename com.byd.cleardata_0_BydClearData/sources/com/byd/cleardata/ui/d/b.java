package com.byd.cleardata.ui.d;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b extends RecyclerView.g<d> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<com.byd.cleardata.d.b> f2750c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.byd.cleardata.ui.c<com.byd.cleardata.d.b> f2751d;
    private c e;

    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.byd.cleardata.d.b f2752b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f2753c;

        a(com.byd.cleardata.d.b bVar, d dVar) {
            this.f2752b = bVar;
            this.f2753c = dVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.this.f2751d != null) {
                b.this.f2751d.a(this.f2752b, this.f2753c.j());
            }
        }
    }

    /* JADX INFO: renamed from: com.byd.cleardata.ui.d.b$b, reason: collision with other inner class name */
    class ViewOnLongClickListenerC0096b implements View.OnLongClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.byd.cleardata.d.b f2755b;

        ViewOnLongClickListenerC0096b(com.byd.cleardata.d.b bVar) {
            this.f2755b = bVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (b.this.e == null) {
                return true;
            }
            b.this.e.a(view, this.f2755b);
            return true;
        }
    }

    public interface c {
        boolean a(View view, com.byd.cleardata.d.b bVar);
    }

    public static final class d extends RecyclerView.d0 {
        public ImageView t;
        public TextView u;
        public TextView v;

        public d(View view) {
            super(view);
            this.t = (ImageView) view.findViewById(R.id.iv_app_icon_item_app_list);
            this.u = (TextView) view.findViewById(R.id.tv_app_name_item_app_list);
            this.v = (TextView) view.findViewById(R.id.tv_app_size_item_app_list);
        }
    }

    public b(List<com.byd.cleardata.d.b> list) {
        this.f2750c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public d o(ViewGroup viewGroup, int i) {
        return new d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_app_list, viewGroup, false));
    }

    public void B(com.byd.cleardata.ui.c<com.byd.cleardata.d.b> cVar) {
        this.f2751d = cVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f2750c.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public void m(d dVar, int i) {
        com.byd.cleardata.d.b bVar = this.f2750c.get(i);
        if (bVar.x() != null) {
            dVar.t.setImageDrawable(bVar.x());
        }
        dVar.u.setText(bVar.v());
        dVar.v.setText(com.byd.cleardata.i.a.g(bVar.w()));
        dVar.f1454b.setOnClickListener(new a(bVar, dVar));
        dVar.f1454b.setOnLongClickListener(new ViewOnLongClickListenerC0096b(bVar));
    }
}
