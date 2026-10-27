package b.b.p.j;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.ExpandedMenuView;
import b.b.p.j.m;
import b.b.p.j.n;
import java.util.ArrayList;

/* JADX INFO: compiled from: ListMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements m, AdapterView.OnItemClickListener {
    public int E3;
    public int F3;
    public m.a G3;
    public a H3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f931c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public LayoutInflater f932d;
    public g s;
    public ExpandedMenuView x;
    public int y;

    /* JADX INFO: compiled from: ListMenuPresenter.java */
    public class a extends BaseAdapter {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f933c = -1;

        public a() {
            a();
        }

        public void a() {
            i iVarV = e.this.s.v();
            if (iVarV != null) {
                ArrayList<i> arrayListZ = e.this.s.z();
                int size = arrayListZ.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (arrayListZ.get(i2) == iVarV) {
                        this.f933c = i2;
                        return;
                    }
                }
            }
            this.f933c = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public i getItem(int i2) {
            ArrayList<i> arrayListZ = e.this.s.z();
            int i3 = i2 + e.this.y;
            int i4 = this.f933c;
            if (i4 >= 0 && i3 >= i4) {
                i3++;
            }
            return arrayListZ.get(i3);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = e.this.s.z().size() - e.this.y;
            return this.f933c < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                e eVar = e.this;
                view = eVar.f932d.inflate(eVar.F3, viewGroup, false);
            }
            ((n.a) view).d(getItem(i2), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public e(Context context, int i2) {
        this(i2, 0);
        this.f931c = context;
        this.f932d = LayoutInflater.from(context);
    }

    @Override // b.b.p.j.m
    public void a(g gVar, boolean z) {
        m.a aVar = this.G3;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    public ListAdapter b() {
        if (this.H3 == null) {
            this.H3 = new a();
        }
        return this.H3;
    }

    @Override // b.b.p.j.m
    public boolean c() {
        return false;
    }

    @Override // b.b.p.j.m
    public void d(Context context, g gVar) {
        int i2 = this.E3;
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i2);
            this.f931c = contextThemeWrapper;
            this.f932d = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f931c != null) {
            this.f931c = context;
            if (this.f932d == null) {
                this.f932d = LayoutInflater.from(context);
            }
        }
        this.s = gVar;
        a aVar = this.H3;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // b.b.p.j.m
    public boolean e(g gVar, i iVar) {
        return false;
    }

    public n f(ViewGroup viewGroup) {
        if (this.x == null) {
            this.x = (ExpandedMenuView) this.f932d.inflate(b.b.g.f757i, viewGroup, false);
            if (this.H3 == null) {
                this.H3 = new a();
            }
            this.x.setAdapter((ListAdapter) this.H3);
            this.x.setOnItemClickListener(this);
        }
        return this.x;
    }

    @Override // b.b.p.j.m
    public boolean g(g gVar, i iVar) {
        return false;
    }

    @Override // b.b.p.j.m
    public void h(m.a aVar) {
        this.G3 = aVar;
    }

    @Override // b.b.p.j.m
    public boolean i(r rVar) {
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        new h(rVar).d(null);
        m.a aVar = this.G3;
        if (aVar == null) {
            return true;
        }
        aVar.b(rVar);
        return true;
    }

    @Override // b.b.p.j.m
    public void j(boolean z) {
        a aVar = this.H3;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
        this.s.M(this.H3.getItem(i2), this, 0);
    }

    public e(int i2, int i3) {
        this.F3 = i2;
        this.E3 = i3;
    }
}
