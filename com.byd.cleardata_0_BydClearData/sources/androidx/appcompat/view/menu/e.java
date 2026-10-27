package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.n;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class e implements m, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Context f803b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LayoutInflater f804c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    g f805d;
    ExpandedMenuView e;
    int f;
    int g;
    int h;
    private m.a i;
    a j;

    private class a extends BaseAdapter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f806b = -1;

        public a() {
            a();
        }

        void a() {
            i iVarV = e.this.f805d.v();
            if (iVarV != null) {
                ArrayList<i> arrayListZ = e.this.f805d.z();
                int size = arrayListZ.size();
                for (int i = 0; i < size; i++) {
                    if (arrayListZ.get(i) == iVarV) {
                        this.f806b = i;
                        return;
                    }
                }
            }
            this.f806b = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public i getItem(int i) {
            ArrayList<i> arrayListZ = e.this.f805d.z();
            int i2 = i + e.this.f;
            int i3 = this.f806b;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return arrayListZ.get(i2);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = e.this.f805d.z().size() - e.this.f;
            return this.f806b < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                e eVar = e.this;
                view = eVar.f804c.inflate(eVar.h, viewGroup, false);
            }
            ((n.a) view).e(getItem(i), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public e(int i, int i2) {
        this.h = i;
        this.g = i2;
    }

    public e(Context context, int i) {
        this(i, 0);
        this.f803b = context;
        this.f804c = LayoutInflater.from(context);
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
        m.a aVar = this.i;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    public ListAdapter b() {
        if (this.j == null) {
            this.j = new a();
        }
        return this.j;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean c() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // androidx.appcompat.view.menu.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(android.content.Context r3, androidx.appcompat.view.menu.g r4) {
        /*
            r2 = this;
            int r0 = r2.g
            if (r0 == 0) goto L12
            android.view.ContextThemeWrapper r1 = new android.view.ContextThemeWrapper
            r1.<init>(r3, r0)
            r2.f803b = r1
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r1)
        Lf:
            r2.f804c = r3
            goto L21
        L12:
            android.content.Context r0 = r2.f803b
            if (r0 == 0) goto L21
            r2.f803b = r3
            android.view.LayoutInflater r0 = r2.f804c
            if (r0 != 0) goto L21
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
            goto Lf
        L21:
            r2.f805d = r4
            androidx.appcompat.view.menu.e$a r3 = r2.j
            if (r3 == 0) goto L2a
            r3.notifyDataSetChanged()
        L2a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.e.d(android.content.Context, androidx.appcompat.view.menu.g):void");
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean e(g gVar, i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean f(g gVar, i iVar) {
        return false;
    }

    public n g(ViewGroup viewGroup) {
        if (this.e == null) {
            this.e = (ExpandedMenuView) this.f804c.inflate(a.a.g.g, viewGroup, false);
            if (this.j == null) {
                this.j = new a();
            }
            this.e.setAdapter((ListAdapter) this.j);
            this.e.setOnItemClickListener(this);
        }
        return this.e;
    }

    @Override // androidx.appcompat.view.menu.m
    public void h(m.a aVar) {
        this.i = aVar;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean i(r rVar) {
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        new h(rVar).d(null);
        m.a aVar = this.i;
        if (aVar == null) {
            return true;
        }
        aVar.b(rVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.m
    public void j(boolean z) {
        a aVar = this.j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f805d.M(this.j.getItem(i), this, 0);
    }
}
