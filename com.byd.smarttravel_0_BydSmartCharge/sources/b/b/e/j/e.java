package b.b.e.j;

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
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.ExpandedMenuView;
import b.b.e.j.m;
import b.b.e.j.n;
import java.util.ArrayList;

/* JADX INFO: compiled from: ListMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements m, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f699b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LayoutInflater f700c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f701d;
    public ExpandedMenuView e;
    public int f;
    public int g;
    public int h;
    public m.a i;
    public a j;
    public int k;

    /* JADX INFO: compiled from: ListMenuPresenter.java */
    public class a extends BaseAdapter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f702b = -1;

        public a() {
            a();
        }

        public void a() {
            i iVarX = e.this.f701d.x();
            if (iVarX != null) {
                ArrayList<i> arrayListB = e.this.f701d.B();
                int size = arrayListB.size();
                for (int i = 0; i < size; i++) {
                    if (arrayListB.get(i) == iVarX) {
                        this.f702b = i;
                        return;
                    }
                }
            }
            this.f702b = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public i getItem(int i) {
            ArrayList<i> arrayListB = e.this.f701d.B();
            int i2 = i + e.this.f;
            int i3 = this.f702b;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return arrayListB.get(i2);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = e.this.f701d.B().size() - e.this.f;
            return this.f702b < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                e eVar = e.this;
                view = eVar.f700c.inflate(eVar.h, viewGroup, false);
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
        this.f699b = context;
        this.f700c = LayoutInflater.from(context);
    }

    @Override // b.b.e.j.m
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

    @Override // b.b.e.j.m
    public int c() {
        return this.k;
    }

    @Override // b.b.e.j.m
    public boolean d() {
        return false;
    }

    @Override // b.b.e.j.m
    public Parcelable e() {
        if (this.e == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        o(bundle);
        return bundle;
    }

    @Override // b.b.e.j.m
    public void f(Context context, g gVar) {
        if (this.g != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, this.g);
            this.f699b = contextThemeWrapper;
            this.f700c = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f699b != null) {
            this.f699b = context;
            if (this.f700c == null) {
                this.f700c = LayoutInflater.from(context);
            }
        }
        this.f701d = gVar;
        a aVar = this.j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public n g(ViewGroup viewGroup) {
        if (this.e == null) {
            this.e = (ExpandedMenuView) this.f700c.inflate(R$layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.j == null) {
                this.j = new a();
            }
            this.e.setAdapter((ListAdapter) this.j);
            this.e.setOnItemClickListener(this);
        }
        return this.e;
    }

    @Override // b.b.e.j.m
    public void h(Parcelable parcelable) {
        l((Bundle) parcelable);
    }

    @Override // b.b.e.j.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // b.b.e.j.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    @Override // b.b.e.j.m
    public void k(m.a aVar) {
        this.i = aVar;
    }

    public void l(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.e.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // b.b.e.j.m
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

    @Override // b.b.e.j.m
    public void n(boolean z) {
        a aVar = this.j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public void o(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.e;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f701d.O(this.j.getItem(i), this, 0);
    }

    public e(int i, int i2) {
        this.h = i;
        this.g = i2;
    }
}
