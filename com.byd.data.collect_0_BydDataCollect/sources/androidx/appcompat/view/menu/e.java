package androidx.appcompat.view.menu;

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
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.n;
import java.util.ArrayList;

/* JADX INFO: compiled from: ListMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements m, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Context f290b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LayoutInflater f291c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    g f292d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ExpandedMenuView f293e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f294f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f295g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f296h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private m.a f297i;
    a j;
    private int k;

    /* JADX INFO: compiled from: ListMenuPresenter.java */
    private class a extends BaseAdapter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f298b = -1;

        public a() {
            a();
        }

        void a() {
            i iVarX = e.this.f292d.x();
            if (iVarX != null) {
                ArrayList<i> arrayListB = e.this.f292d.B();
                int size = arrayListB.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (arrayListB.get(i2) == iVarX) {
                        this.f298b = i2;
                        return;
                    }
                }
            }
            this.f298b = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public i getItem(int i2) {
            ArrayList<i> arrayListB = e.this.f292d.B();
            int i3 = i2 + e.this.f294f;
            int i4 = this.f298b;
            if (i4 >= 0 && i3 >= i4) {
                i3++;
            }
            return arrayListB.get(i3);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = e.this.f292d.B().size() - e.this.f294f;
            return this.f298b < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                e eVar = e.this;
                view = eVar.f291c.inflate(eVar.f296h, viewGroup, false);
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
        this.f290b = context;
        this.f291c = LayoutInflater.from(context);
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
        m.a aVar = this.f297i;
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
    public int c() {
        return this.k;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable e() {
        if (this.f293e == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        o(bundle);
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(Context context, g gVar) {
        int i2 = this.f295g;
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i2);
            this.f290b = contextThemeWrapper;
            this.f291c = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f290b != null) {
            this.f290b = context;
            if (this.f291c == null) {
                this.f291c = LayoutInflater.from(context);
            }
        }
        this.f292d = gVar;
        a aVar = this.j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public n g(ViewGroup viewGroup) {
        if (this.f293e == null) {
            this.f293e = (ExpandedMenuView) this.f291c.inflate(b.a.g.abc_expanded_menu_layout, viewGroup, false);
            if (this.j == null) {
                this.j = new a();
            }
            this.f293e.setAdapter((ListAdapter) this.j);
            this.f293e.setOnItemClickListener(this);
        }
        return this.f293e;
    }

    @Override // androidx.appcompat.view.menu.m
    public void h(Parcelable parcelable) {
        l((Bundle) parcelable);
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void k(m.a aVar) {
        this.f297i = aVar;
    }

    public void l(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.f293e.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean m(r rVar) {
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        new h(rVar).d(null);
        m.a aVar = this.f297i;
        if (aVar == null) {
            return true;
        }
        aVar.b(rVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.m
    public void n(boolean z) {
        a aVar = this.j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public void o(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.f293e;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
        this.f292d.O(this.j.getItem(i2), this, 0);
    }

    public e(int i2, int i3) {
        this.f296h = i2;
        this.f295g = i3;
    }
}
