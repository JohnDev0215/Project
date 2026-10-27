package defpackage;

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
import defpackage.v1;
import defpackage.w1;
import java.util.ArrayList;

/* JADX INFO: compiled from: ListMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class n1 implements v1, AdapterView.OnItemClickListener {
    public Context b;
    public LayoutInflater c;
    public p1 d;
    public ExpandedMenuView e;
    public int f;
    public int g;
    public int h;
    public v1.a i;
    public a j;
    public int k;

    /* JADX INFO: compiled from: ListMenuPresenter.java */
    public class a extends BaseAdapter {
        public int b = -1;

        public a() {
            a();
        }

        public void a() {
            r1 r1VarX = n1.this.d.x();
            if (r1VarX != null) {
                ArrayList<r1> arrayListB = n1.this.d.B();
                int size = arrayListB.size();
                for (int i = 0; i < size; i++) {
                    if (arrayListB.get(i) == r1VarX) {
                        this.b = i;
                        return;
                    }
                }
            }
            this.b = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public r1 getItem(int i) {
            ArrayList<r1> arrayListB = n1.this.d.B();
            int i2 = i + n1.this.f;
            int i3 = this.b;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return arrayListB.get(i2);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = n1.this.d.B().size() - n1.this.f;
            return this.b < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                n1 n1Var = n1.this;
                view = n1Var.c.inflate(n1Var.h, viewGroup, false);
            }
            ((w1.a) view).e(getItem(i), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public n1(Context context, int i) {
        this(i, 0);
        this.b = context;
        this.c = LayoutInflater.from(context);
    }

    @Override // defpackage.v1
    public void a(p1 p1Var, boolean z) {
        v1.a aVar = this.i;
        if (aVar != null) {
            aVar.a(p1Var, z);
        }
    }

    public ListAdapter b() {
        if (this.j == null) {
            this.j = new a();
        }
        return this.j;
    }

    @Override // defpackage.v1
    public int c() {
        return this.k;
    }

    @Override // defpackage.v1
    public boolean d() {
        return false;
    }

    @Override // defpackage.v1
    public Parcelable e() {
        if (this.e == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        o(bundle);
        return bundle;
    }

    @Override // defpackage.v1
    public void f(Context context, p1 p1Var) {
        int i = this.g;
        if (i != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            this.b = contextThemeWrapper;
            this.c = LayoutInflater.from(contextThemeWrapper);
        } else if (this.b != null) {
            this.b = context;
            if (this.c == null) {
                this.c = LayoutInflater.from(context);
            }
        }
        this.d = p1Var;
        a aVar = this.j;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public w1 g(ViewGroup viewGroup) {
        if (this.e == null) {
            this.e = (ExpandedMenuView) this.c.inflate(R$layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.j == null) {
                this.j = new a();
            }
            this.e.setAdapter((ListAdapter) this.j);
            this.e.setOnItemClickListener(this);
        }
        return this.e;
    }

    @Override // defpackage.v1
    public void h(Parcelable parcelable) {
        l((Bundle) parcelable);
    }

    @Override // defpackage.v1
    public boolean i(p1 p1Var, r1 r1Var) {
        return false;
    }

    @Override // defpackage.v1
    public boolean j(p1 p1Var, r1 r1Var) {
        return false;
    }

    @Override // defpackage.v1
    public void k(v1.a aVar) {
        this.i = aVar;
    }

    public void l(Bundle bundle) {
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.e.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // defpackage.v1
    public boolean m(a2 a2Var) {
        if (!a2Var.hasVisibleItems()) {
            return false;
        }
        new q1(a2Var).d(null);
        v1.a aVar = this.i;
        if (aVar == null) {
            return true;
        }
        aVar.b(a2Var);
        return true;
    }

    @Override // defpackage.v1
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
        this.d.O(this.j.getItem(i), this, 0);
    }

    public n1(int i, int i2) {
        this.h = i;
        this.g = i2;
    }
}
