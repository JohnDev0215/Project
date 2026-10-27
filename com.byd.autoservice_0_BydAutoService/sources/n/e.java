package n;

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
import n.m;
import n.n;

/* JADX INFO: compiled from: ListMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements m, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f3915b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LayoutInflater f3916c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f3917d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ExpandedMenuView f3918e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3919f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3920g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3921h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public m.a f3922i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public a f3923j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3924k;

    /* JADX INFO: compiled from: ListMenuPresenter.java */
    public class a extends BaseAdapter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3925b = -1;

        public a() {
            a();
        }

        public void a() {
            i iVarX = e.this.f3917d.x();
            if (iVarX != null) {
                ArrayList<i> arrayListB = e.this.f3917d.B();
                int size = arrayListB.size();
                for (int i3 = 0; i3 < size; i3++) {
                    if (arrayListB.get(i3) == iVarX) {
                        this.f3925b = i3;
                        return;
                    }
                }
            }
            this.f3925b = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public i getItem(int i3) {
            ArrayList<i> arrayListB = e.this.f3917d.B();
            int i4 = i3 + e.this.f3919f;
            int i5 = this.f3925b;
            if (i5 >= 0 && i4 >= i5) {
                i4++;
            }
            return arrayListB.get(i4);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = e.this.f3917d.B().size() - e.this.f3919f;
            return this.f3925b < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i3) {
            return i3;
        }

        @Override // android.widget.Adapter
        public View getView(int i3, View view, ViewGroup viewGroup) {
            if (view == null) {
                e eVar = e.this;
                view = eVar.f3916c.inflate(eVar.f3921h, viewGroup, false);
            }
            ((n.a) view).e(getItem(i3), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public e(Context context, int i3) {
        this(i3, 0);
        this.f3915b = context;
        this.f3916c = LayoutInflater.from(context);
    }

    @Override // n.m
    public void a(g gVar, boolean z3) {
        m.a aVar = this.f3922i;
        if (aVar != null) {
            aVar.a(gVar, z3);
        }
    }

    public ListAdapter b() {
        if (this.f3923j == null) {
            this.f3923j = new a();
        }
        return this.f3923j;
    }

    @Override // n.m
    public int c() {
        return this.f3924k;
    }

    @Override // n.m
    public boolean d() {
        return false;
    }

    @Override // n.m
    public Parcelable e() {
        if (this.f3918e == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        o(bundle);
        return bundle;
    }

    @Override // n.m
    public void f(Context context, g gVar) {
        int i3 = this.f3920g;
        if (i3 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i3);
            this.f3915b = contextThemeWrapper;
            this.f3916c = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f3915b != null) {
            this.f3915b = context;
            if (this.f3916c == null) {
                this.f3916c = LayoutInflater.from(context);
            }
        }
        this.f3917d = gVar;
        a aVar = this.f3923j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public n g(ViewGroup viewGroup) {
        if (this.f3918e == null) {
            this.f3918e = (ExpandedMenuView) this.f3916c.inflate(R$layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.f3923j == null) {
                this.f3923j = new a();
            }
            this.f3918e.setAdapter((ListAdapter) this.f3923j);
            this.f3918e.setOnItemClickListener(this);
        }
        return this.f3918e;
    }

    @Override // n.m
    public void h(Parcelable parcelable) {
        l((Bundle) parcelable);
    }

    @Override // n.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // n.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    @Override // n.m
    public void k(m.a aVar) {
        this.f3922i = aVar;
    }

    public void l(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.f3918e.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // n.m
    public boolean m(r rVar) {
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        new h(rVar).d(null);
        m.a aVar = this.f3922i;
        if (aVar == null) {
            return true;
        }
        aVar.b(rVar);
        return true;
    }

    @Override // n.m
    public void n(boolean z3) {
        a aVar = this.f3923j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public void o(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.f3918e;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i3, long j3) {
        this.f3917d.O(this.f3923j.getItem(i3), this, 0);
    }

    public e(int i3, int i4) {
        this.f3921h = i3;
        this.f3920g = i4;
    }
}
