package com.byd.filemanager.ui.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import com.byd.filemanager.f;
import com.byd.filemanager.k.o;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: TabAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends RecyclerView.g<c> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3726c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String[] f3727d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int[] f3728e;
    private com.byd.filemanager.ui.a<Integer> g;
    private int f = 0;
    private final List<b> h = new ArrayList();

    /* JADX INFO: compiled from: TabAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c f3729b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b f3730c;

        a(c cVar, b bVar) {
            this.f3729b = cVar;
            this.f3730c = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iJ = this.f3729b.j();
            if (iJ < 0 || e.this.h == null || iJ >= e.this.h.size()) {
                return;
            }
            if (iJ != e.this.f) {
                if (e.this.f != -1) {
                    ((b) e.this.h.get(e.this.f)).f3735d = false;
                    e eVar = e.this;
                    eVar.i(eVar.f);
                }
                ((b) e.this.h.get(iJ)).f3735d = true;
                e.this.i(iJ);
            }
            e.this.f = iJ;
            if (e.this.g != null) {
                e.this.g.a(Integer.valueOf(this.f3730c.f3732a), this.f3729b.j());
            }
        }
    }

    /* JADX INFO: compiled from: TabAdapter.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3732a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f3733b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f3734c;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f3736e;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f3735d = false;
        boolean f = true;

        public b(String str, int i, int i2) {
            this.f3733b = str;
            this.f3734c = i;
            this.f3732a = i2;
            this.f3736e = com.byd.filemanager.e.f().s(i2);
        }
    }

    /* JADX INFO: compiled from: TabAdapter.java */
    protected static class c extends RecyclerView.c0 {
        TextView t;
        View u;

        public c(View view) {
            super(view);
            this.t = (TextView) view.findViewById(R.id.tv_name);
            this.u = view.findViewById(R.id.view_index);
        }
    }

    public e(Context context) {
        int i = 0;
        this.f3726c = context;
        this.f3727d = context.getResources().getStringArray(R.array.tabs);
        this.f3728e = o.a(this.f3726c, R.array.icon_tab_path);
        while (true) {
            String[] strArr = this.f3727d;
            if (i >= strArr.length) {
                return;
            }
            this.h.add(new b(strArr[i], this.f3728e[i], i));
            i++;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public void l(c cVar, int i) {
        b bVar = this.h.get(i);
        cVar.t.setText(this.f3727d[i]);
        cVar.f1300b.setVisibility(bVar.f ? 0 : 4);
        boolean z = bVar.f3735d;
        cVar.u.setVisibility(z ? 0 : 8);
        if (f.h().v()) {
            cVar.t.setTextAppearance(z ? R.style.TabLayoutTextSelected : R.style.TabLayoutTextUnSelected);
        } else {
            cVar.t.setTextAppearance(z ? R.style.TabLayoutTextSelected_denza : R.style.TabLayoutTextUnSelected_denza);
        }
        cVar.f1300b.setOnClickListener(new a(cVar, bVar));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public c n(ViewGroup viewGroup, int i) {
        return f.h().v() ? new c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_tab, viewGroup, false)) : new c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_tab_denza, viewGroup, false));
    }

    public void C(int i) {
        int i2 = this.f;
        if (i2 != -1) {
            this.h.get(i2).f3735d = false;
        }
        this.f = i;
        this.h.get(i).f3735d = true;
        h();
    }

    public void D(com.byd.filemanager.ui.a<Integer> aVar) {
        this.g = aVar;
    }

    public void E(int i, boolean z) {
        this.h.get(i).f = z;
    }

    public void F() {
        this.f = f.h().k();
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.h.size();
    }
}
