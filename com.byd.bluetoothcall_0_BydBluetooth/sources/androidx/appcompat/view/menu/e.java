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
    Context f340b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LayoutInflater f341c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    g f342d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ExpandedMenuView f343e;
    int f;
    int g;
    int h;
    private m.a i;
    a j;
    private int k;

    /* JADX INFO: compiled from: ListMenuPresenter.java */
    private class a extends BaseAdapter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f344b = -1;

        public a() {
            a();
        }

        void a() {
            i iVarX = e.this.f342d.x();
            if (iVarX != null) {
                ArrayList<i> arrayListB = e.this.f342d.B();
                int size = arrayListB.size();
                for (int i = 0; i < size; i++) {
                    if (arrayListB.get(i) == iVarX) {
                        this.f344b = i;
                        return;
                    }
                }
            }
            this.f344b = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public i getItem(int i) {
            ArrayList<i> arrayListB = e.this.f342d.B();
            int i2 = i + e.this.f;
            int i3 = this.f344b;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return arrayListB.get(i2);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = e.this.f342d.B().size() - e.this.f;
            return this.f344b < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                e eVar = e.this;
                view = eVar.f341c.inflate(eVar.h, viewGroup, false);
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

    public e(Context context, int i) {
        this(i, 0);
        this.f340b = context;
        this.f341c = LayoutInflater.from(context);
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
    public int c() {
        return this.k;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable e() {
        if (this.f343e == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        o(bundle);
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(Context context, g gVar) {
        int i = this.g;
        if (i != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            this.f340b = contextThemeWrapper;
            this.f341c = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f340b != null) {
            this.f340b = context;
            if (this.f341c == null) {
                this.f341c = LayoutInflater.from(context);
            }
        }
        this.f342d = gVar;
        a aVar = this.j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public n g(ViewGroup viewGroup) {
        if (this.f343e == null) {
            this.f343e = (ExpandedMenuView) this.f341c.inflate(b.a.g.i, viewGroup, false);
            if (this.j == null) {
                this.j = new a();
            }
            this.f343e.setAdapter((ListAdapter) this.j);
            this.f343e.setOnItemClickListener(this);
        }
        return this.f343e;
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
        this.i = aVar;
    }

    public void l(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.f343e.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean m(r rVar) {
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
    public void n(boolean z) {
        a aVar = this.j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public void o(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.f343e;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f342d.O(this.j.getItem(i), this, 0);
    }

    public e(int i, int i2) {
        this.h = i;
        this.g = i2;
    }
}
