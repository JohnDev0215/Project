package com.byd.cleardata.ui.d;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class e extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<String> f2784c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.byd.cleardata.ui.c<Integer> f2785d;

    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b f2786b;

        a(b bVar) {
            this.f2786b = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iJ = this.f2786b.j();
            if (iJ <= 0 || iJ >= e.this.f2784c.size() - 1) {
                return;
            }
            int size = (e.this.f2784c.size() - 1) - iJ;
            if (e.this.f2785d != null) {
                e.this.f2785d.a(Integer.valueOf(size), iJ);
            }
            while (true) {
                e eVar = e.this;
                if (size <= 0) {
                    eVar.h();
                    return;
                } else {
                    eVar.f2784c.remove(e.this.f2784c.size() - 1);
                    size--;
                }
            }
        }
    }

    protected static class b extends RecyclerView.d0 {
        ImageView t;
        TextView u;

        public b(View view) {
            super(view);
            this.t = (ImageView) view.findViewById(R.id.iv_icon);
            this.u = (TextView) view.findViewById(R.id.tv_name);
        }
    }

    public void A(String str) {
        z(str);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public void m(b bVar, int i) {
        bVar.u.setText(this.f2784c.get(i));
        bVar.t.setVisibility(i < this.f2784c.size() + (-1) ? 0 : 8);
        bVar.f1454b.setOnClickListener(new a(bVar));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public b o(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_path, viewGroup, false));
    }

    public boolean D() {
        if (this.f2784c.size() > 2) {
            this.f2784c = this.f2784c.subList(0, 2);
            h();
        }
        return true;
    }

    public boolean E() {
        if (this.f2784c.size() <= 2) {
            return false;
        }
        List<String> list = this.f2784c;
        list.remove(list.size() - 1);
        h();
        return true;
    }

    public void F(String str) {
        com.byd.cleardata.i.c.b("replaceSecond");
        if (this.f2784c.size() > 1) {
            String str2 = this.f2784c.get(0);
            this.f2784c.clear();
            this.f2784c.add(str2);
        }
        this.f2784c.add(str);
        h();
    }

    public void G(String... strArr) {
        this.f2784c.clear();
        this.f2784c.addAll(Arrays.asList(strArr));
        h();
    }

    public void H(com.byd.cleardata.ui.c<Integer> cVar) {
        this.f2785d = cVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f2784c.size();
    }

    public void z(String str) {
        this.f2784c.add(str);
        h();
    }
}
