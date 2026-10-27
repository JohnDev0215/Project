package com.byd.cleardata.ui.d;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.cleardata.R;
import com.byd.cleardata.i.r;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class f extends RecyclerView.g<c> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f2788c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String[] f2789d;
    private final int[] e;
    private com.byd.cleardata.ui.c<Integer> g;
    private int f = 0;
    private final List<b> h = new ArrayList();

    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c f2790b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b f2791c;

        a(c cVar, b bVar) {
            this.f2790b = cVar;
            this.f2791c = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iJ = this.f2790b.j();
            if (iJ != f.this.f) {
                if (f.this.f != -1) {
                    ((b) f.this.h.get(f.this.f)).f2796d = false;
                    f fVar = f.this;
                    fVar.i(fVar.f);
                }
                ((b) f.this.h.get(iJ)).f2796d = true;
                f.this.i(iJ);
            }
            f.this.f = iJ;
            if (f.this.g != null) {
                f.this.g.a(Integer.valueOf(this.f2791c.f2793a), this.f2790b.j());
            }
        }
    }

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2793a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f2794b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2795c;
        boolean e;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2796d = false;
        boolean f = true;

        public b(String str, int i, int i2) {
            this.f2794b = str;
            this.f2795c = i;
            this.f2793a = i2;
            this.e = com.byd.cleardata.a.f().p(i2);
        }
    }

    protected static class c extends RecyclerView.d0 {
        ImageView t;
        TextView u;
        View v;

        public c(View view) {
            super(view);
            this.t = (ImageView) view.findViewById(R.id.iv_icon);
            this.u = (TextView) view.findViewById(R.id.tv_name);
            this.v = view.findViewById(R.id.view_index);
        }
    }

    public f(Context context) {
        int i = 0;
        this.f2788c = context;
        this.f2789d = context.getResources().getStringArray(R.array.tabs);
        this.e = r.a(this.f2788c, R.array.icon_tab_path);
        while (true) {
            String[] strArr = this.f2789d;
            if (i >= strArr.length) {
                return;
            }
            this.h.add(new b(strArr[i], this.e[i], i));
            i++;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public void m(c cVar, int i) {
        Resources resources;
        b bVar = this.h.get(i);
        cVar.t.setImageResource(this.e[i]);
        cVar.u.setText(this.f2789d[i]);
        cVar.f1454b.setVisibility(bVar.f ? 0 : 4);
        boolean z = bVar.f2796d;
        cVar.v.setVisibility(z ? 0 : 8);
        if (bVar.e) {
            TextView textView = cVar.u;
            int i2 = R.color.color_highlight;
            Resources resources2 = this.f2788c.getResources();
            textView.setTextColor(z ? resources2.getColor(R.color.color_highlight) : resources2.getColor(R.color.text_menu));
            ImageView imageView = cVar.t;
            if (z) {
                resources = this.f2788c.getResources();
            } else {
                resources = this.f2788c.getResources();
                i2 = R.color.transparent;
            }
            imageView.setColorFilter(resources.getColor(i2));
        } else {
            cVar.u.setTextColor(this.f2788c.getResources().getColor(R.color.text_tip));
            cVar.t.setColorFilter(this.f2788c.getResources().getColor(R.color.disable));
        }
        cVar.f1454b.setEnabled(bVar.e);
        cVar.f1454b.setOnClickListener(new a(cVar, bVar));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public c o(ViewGroup viewGroup, int i) {
        return new c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_tab, viewGroup, false));
    }

    public void D(int i) {
        int i2 = this.f;
        if (i2 != -1) {
            this.h.get(i2).f2796d = false;
        }
        this.f = i;
        this.h.get(i).f2796d = true;
        h();
    }

    public void E(com.byd.cleardata.ui.c<Integer> cVar) {
        this.g = cVar;
    }

    public void F(int i, boolean z) {
        this.h.get(i).f = z;
    }

    public void G() {
        this.f = com.byd.cleardata.b.h().k();
        for (b bVar : this.h) {
            bVar.e = com.byd.cleardata.a.f().p(bVar.f2793a);
        }
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.h.size();
    }
}
