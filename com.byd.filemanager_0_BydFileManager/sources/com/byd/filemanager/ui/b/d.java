package com.byd.filemanager.ui.b;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: PathAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<String> f3722c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.byd.filemanager.ui.a<Integer> f3723d;

    /* JADX INFO: compiled from: PathAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b f3724b;

        a(b bVar) {
            this.f3724b = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iJ = this.f3724b.j();
            if (iJ <= 0 || iJ >= d.this.f3722c.size() - 1) {
                return;
            }
            int size = (d.this.f3722c.size() - 1) - iJ;
            if (d.this.f3723d != null) {
                d.this.f3723d.a(Integer.valueOf(size), iJ);
            }
            while (size > 0) {
                d.this.f3722c.remove(d.this.f3722c.size() - 1);
                size--;
            }
            d.this.h();
        }
    }

    /* JADX INFO: compiled from: PathAdapter.java */
    protected static class b extends RecyclerView.c0 {
        ImageView t;
        TextView u;

        public b(View view) {
            super(view);
            this.t = (ImageView) view.findViewById(R.id.iv_icon);
            this.u = (TextView) view.findViewById(R.id.tv_name);
        }
    }

    public List<String> A() {
        return this.f3722c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public void l(b bVar, int i) {
        bVar.u.setText(this.f3722c.get(i));
        bVar.t.setVisibility(i < this.f3722c.size() + (-1) ? 0 : 8);
        bVar.f1300b.setOnClickListener(new a(bVar));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public b n(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_path, viewGroup, false));
    }

    public boolean D() {
        if (this.f3722c.size() > 2) {
            this.f3722c = this.f3722c.subList(0, 2);
            h();
        }
        return true;
    }

    public boolean E() {
        if (this.f3722c.size() <= 2) {
            return false;
        }
        List<String> list = this.f3722c;
        list.remove(list.size() - 1);
        h();
        return true;
    }

    public void F(String str) {
        if (this.f3722c.size() > 1) {
            String str2 = this.f3722c.get(0);
            this.f3722c.clear();
            this.f3722c.add(str2);
        }
        this.f3722c.add(str);
        h();
    }

    public void G(String... strArr) {
        H(strArr);
        h();
    }

    public void H(String... strArr) {
        com.byd.filemanager.k.b.m("PathAdapter resetUI is begin");
        if (!this.f3722c.isEmpty()) {
            this.f3722c.clear();
        }
        this.f3722c.addAll(Arrays.asList(strArr));
    }

    public void I(com.byd.filemanager.ui.a<Integer> aVar) {
        this.f3723d = aVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3722c.size();
    }

    public void y(String str) {
        this.f3722c.add(str);
        h();
    }

    public void z(String str) {
        y(str);
    }
}
