package b.b.p.j;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f1616b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LayoutInflater f1617c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f1618d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ExpandedMenuView f1619e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1620f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1621g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1622h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public m.a f1623i;
    public a j;
    public int k;

    /* JADX INFO: compiled from: ListMenuPresenter.java */
    public class a extends BaseAdapter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1624b = -1;

        public a() {
            a();
        }

        public void a() {
            i iVarX = e.this.f1618d.x();
            if (iVarX != null) {
                ArrayList<i> arrayListB = e.this.f1618d.B();
                int size = arrayListB.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (arrayListB.get(i2) == iVarX) {
                        this.f1624b = i2;
                        return;
                    }
                }
            }
            this.f1624b = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public i getItem(int i2) {
            ArrayList<i> arrayListB = e.this.f1618d.B();
            int i3 = i2 + e.this.f1620f;
            int i4 = this.f1624b;
            if (i4 >= 0 && i3 >= i4) {
                i3++;
            }
            return arrayListB.get(i3);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = e.this.f1618d.B().size() - e.this.f1620f;
            return this.f1624b < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                e eVar = e.this;
                view = eVar.f1617c.inflate(eVar.f1622h, viewGroup, false);
            }
            ((n.a) view).e(getItem(i2), 0);
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
        this.f1616b = context;
        this.f1617c = LayoutInflater.from(context);
    }

    @Override // b.b.p.j.m
    public void a(g gVar, boolean z) {
        m.a aVar = this.f1623i;
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

    @Override // b.b.p.j.m
    public int c() {
        return this.k;
    }

    @Override // b.b.p.j.m
    public boolean d() {
        return false;
    }

    @Override // b.b.p.j.m
    public Parcelable e() {
        if (this.f1619e == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        o(bundle);
        return bundle;
    }

    @Override // b.b.p.j.m
    public void f(Context context, g gVar) {
        int i2 = this.f1621g;
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i2);
            this.f1616b = contextThemeWrapper;
            this.f1617c = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f1616b != null) {
            this.f1616b = context;
            if (this.f1617c == null) {
                this.f1617c = LayoutInflater.from(context);
            }
        }
        this.f1618d = gVar;
        a aVar = this.j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public n g(ViewGroup viewGroup) {
        if (this.f1619e == null) {
            this.f1619e = (ExpandedMenuView) this.f1617c.inflate(b.b.g.abc_expanded_menu_layout, viewGroup, false);
            if (this.j == null) {
                this.j = new a();
            }
            this.f1619e.setAdapter((ListAdapter) this.j);
            this.f1619e.setOnItemClickListener(this);
        }
        return this.f1619e;
    }

    @Override // b.b.p.j.m
    public void h(Parcelable parcelable) {
        l((Bundle) parcelable);
    }

    @Override // b.b.p.j.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // b.b.p.j.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    @Override // b.b.p.j.m
    public void k(m.a aVar) {
        this.f1623i = aVar;
    }

    public void l(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.f1619e.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // b.b.p.j.m
    public boolean m(r rVar) {
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        new h(rVar).d(null);
        m.a aVar = this.f1623i;
        if (aVar == null) {
            return true;
        }
        aVar.b(rVar);
        return true;
    }

    @Override // b.b.p.j.m
    public void n(boolean z) {
        a aVar = this.j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public void o(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.f1619e;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
        this.f1618d.O(this.j.getItem(i2), this, 0);
    }

    public e(int i2, int i3) {
        this.f1622h = i2;
        this.f1621g = i3;
    }
}
