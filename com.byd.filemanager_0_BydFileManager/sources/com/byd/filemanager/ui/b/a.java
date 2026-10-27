package com.byd.filemanager.ui.b;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import java.util.List;

/* JADX INFO: compiled from: AppInfoAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<com.byd.filemanager.bean.a> f3687c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3688d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.byd.filemanager.ui.a<String> f3689e;

    /* JADX INFO: renamed from: com.byd.filemanager.ui.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppInfoAdapter.java */
    class ViewOnClickListenerC0087a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.byd.filemanager.bean.a f3690b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f3691c;

        ViewOnClickListenerC0087a(com.byd.filemanager.bean.a aVar, int i) {
            this.f3690b = aVar;
            this.f3691c = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (a.this.f3689e != null) {
                a.this.f3689e.a(this.f3690b.c(), this.f3691c);
            }
        }
    }

    /* JADX INFO: compiled from: AppInfoAdapter.java */
    protected static class b extends RecyclerView.c0 {
        TextView t;
        ImageView u;

        public b(View view) {
            super(view);
            this.u = (ImageView) view.findViewById(R.id.iv_icon);
            this.t = (TextView) view.findViewById(R.id.tv_name);
        }
    }

    public a(int i, List<com.byd.filemanager.bean.a> list) {
        this.f3687c = list;
        this.f3688d = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3687c.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
    public void l(b bVar, int i) {
        com.byd.filemanager.bean.a aVar = this.f3687c.get(i);
        bVar.u.setImageDrawable(aVar.b());
        bVar.t.setText(aVar.c());
        bVar.f1300b.setOnClickListener(new ViewOnClickListenerC0087a(aVar, i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public b n(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(viewGroup.getContext()).inflate(this.f3688d, viewGroup, false));
    }

    public void z(com.byd.filemanager.ui.a<String> aVar) {
        this.f3689e = aVar;
    }
}
