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
public class a extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<com.byd.cleardata.d.a> f2745c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2746d;
    private com.byd.cleardata.ui.c<String> e;

    /* JADX INFO: renamed from: com.byd.cleardata.ui.d.a$a, reason: collision with other inner class name */
    class ViewOnClickListenerC0095a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.byd.cleardata.d.a f2747b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f2748c;

        ViewOnClickListenerC0095a(com.byd.cleardata.d.a aVar, int i) {
            this.f2747b = aVar;
            this.f2748c = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (a.this.e != null) {
                a.this.e.a(this.f2747b.c(), this.f2748c);
            }
        }
    }

    protected static class b extends RecyclerView.d0 {
        TextView t;
        ImageView u;

        public b(View view) {
            super(view);
            this.u = (ImageView) view.findViewById(R.id.iv_icon);
            this.t = (TextView) view.findViewById(R.id.tv_name);
        }
    }

    public a(int i, List<com.byd.cleardata.d.a> list) {
        this.f2745c = list;
        this.f2746d = i;
    }

    public void A(com.byd.cleardata.ui.c<String> cVar) {
        this.e = cVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f2745c.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public void m(b bVar, int i) {
        com.byd.cleardata.d.a aVar = this.f2745c.get(i);
        bVar.u.setImageDrawable(aVar.b());
        bVar.t.setText(aVar.c());
        bVar.f1454b.setOnClickListener(new ViewOnClickListenerC0095a(aVar, i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public b o(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(viewGroup.getContext()).inflate(this.f2746d, viewGroup, false));
    }
}
