package q;

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
import java.util.ArrayList;
import q.m;
import q.n;

/* JADX INFO: compiled from: ListMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements m, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f7112d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public LayoutInflater f7113e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public g f7114f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ExpandedMenuView f7115g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f7116h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f7117i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f7118j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public m.a f7119k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public a f7120l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f7121m;

    /* JADX INFO: compiled from: ListMenuPresenter.java */
    public class a extends BaseAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f7122d = -1;

        public a() {
            a();
        }

        public void a() {
            i iVarX = e.this.f7114f.x();
            if (iVarX != null) {
                ArrayList<i> arrayListB = e.this.f7114f.B();
                int size = arrayListB.size();
                for (int i8 = 0; i8 < size; i8++) {
                    if (arrayListB.get(i8) == iVarX) {
                        this.f7122d = i8;
                        return;
                    }
                }
            }
            this.f7122d = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public i getItem(int i8) {
            ArrayList<i> arrayListB = e.this.f7114f.B();
            int i9 = i8 + e.this.f7116h;
            int i10 = this.f7122d;
            if (i10 >= 0 && i9 >= i10) {
                i9++;
            }
            return arrayListB.get(i9);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = e.this.f7114f.B().size() - e.this.f7116h;
            return this.f7122d < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i8) {
            return i8;
        }

        @Override // android.widget.Adapter
        public View getView(int i8, View view, ViewGroup viewGroup) {
            if (view == null) {
                e eVar = e.this;
                view = eVar.f7113e.inflate(eVar.f7118j, viewGroup, false);
            }
            ((n.a) view).e(getItem(i8), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public e(Context context, int i8) {
        this(i8, 0);
        this.f7112d = context;
        this.f7113e = LayoutInflater.from(context);
    }

    @Override // q.m
    public void a(g gVar, boolean z7) {
        m.a aVar = this.f7119k;
        if (aVar != null) {
            aVar.a(gVar, z7);
        }
    }

    @Override // q.m
    public void b(Context context, g gVar) {
        int i8 = this.f7117i;
        if (i8 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i8);
            this.f7112d = contextThemeWrapper;
            this.f7113e = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f7112d != null) {
            this.f7112d = context;
            if (this.f7113e == null) {
                this.f7113e = LayoutInflater.from(context);
            }
        }
        this.f7114f = gVar;
        a aVar = this.f7120l;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public ListAdapter c() {
        if (this.f7120l == null) {
            this.f7120l = new a();
        }
        return this.f7120l;
    }

    @Override // q.m
    public void d(Parcelable parcelable) {
        h((Bundle) parcelable);
    }

    public n e(ViewGroup viewGroup) {
        if (this.f7115g == null) {
            this.f7115g = (ExpandedMenuView) this.f7113e.inflate(R$layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.f7120l == null) {
                this.f7120l = new a();
            }
            this.f7115g.setAdapter((ListAdapter) this.f7120l);
            this.f7115g.setOnItemClickListener(this);
        }
        return this.f7115g;
    }

    @Override // q.m
    public boolean f(r rVar) {
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        new h(rVar).d(null);
        m.a aVar = this.f7119k;
        if (aVar == null) {
            return true;
        }
        aVar.b(rVar);
        return true;
    }

    @Override // q.m
    public void g(boolean z7) {
        a aVar = this.f7120l;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public void h(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.f7115g.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // q.m
    public int i() {
        return this.f7121m;
    }

    @Override // q.m
    public boolean j() {
        return false;
    }

    @Override // q.m
    public Parcelable k() {
        if (this.f7115g == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        o(bundle);
        return bundle;
    }

    @Override // q.m
    public boolean l(g gVar, i iVar) {
        return false;
    }

    @Override // q.m
    public boolean m(g gVar, i iVar) {
        return false;
    }

    @Override // q.m
    public void n(m.a aVar) {
        this.f7119k = aVar;
    }

    public void o(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.f7115g;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i8, long j8) {
        this.f7114f.O(this.f7120l.getItem(i8), this, 0);
    }

    public e(int i8, int i9) {
        this.f7118j = i8;
        this.f7117i = i9;
    }
}
