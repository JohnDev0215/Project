package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.view.menu.m;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class d implements l, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Context f219c;
    LayoutInflater d;
    f e;
    ExpandedMenuView f;
    int g;
    int h;
    int i;
    private l.a j;
    a k;

    /* JADX INFO: compiled from: caradapter-bug */
    private class a extends BaseAdapter {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f220c = -1;

        public a() {
            a();
        }

        void a() {
            h hVarV = d.this.e.v();
            if (hVarV != null) {
                ArrayList<h> arrayListZ = d.this.e.z();
                int size = arrayListZ.size();
                for (int i = 0; i < size; i++) {
                    if (arrayListZ.get(i) == hVarV) {
                        this.f220c = i;
                        return;
                    }
                }
            }
            this.f220c = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public h getItem(int i) {
            ArrayList<h> arrayListZ = d.this.e.z();
            int i2 = i + d.this.g;
            int i3 = this.f220c;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return arrayListZ.get(i2);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = d.this.e.z().size() - d.this.g;
            return this.f220c < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                d dVar = d.this;
                view = dVar.d.inflate(dVar.i, viewGroup, false);
            }
            ((m.a) view).j(getItem(i), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public d(Context context, int i) {
        this(i, 0);
        this.f219c = context;
        this.d = LayoutInflater.from(context);
    }

    @Override // androidx.appcompat.view.menu.l
    public void a(f fVar, boolean z) {
        l.a aVar = this.j;
        if (aVar != null) {
            aVar.a(fVar, z);
        }
    }

    public ListAdapter b() {
        if (this.k == null) {
            this.k = new a();
        }
        return this.k;
    }

    @Override // androidx.appcompat.view.menu.l
    public boolean c() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.l
    public void d(Context context, f fVar) {
        if (this.h != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, this.h);
            this.f219c = contextThemeWrapper;
            this.d = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f219c != null) {
            this.f219c = context;
            if (this.d == null) {
                this.d = LayoutInflater.from(context);
            }
        }
        this.e = fVar;
        a aVar = this.k;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.l
    public boolean e(f fVar, h hVar) {
        return false;
    }

    public m f(ViewGroup viewGroup) {
        if (this.f == null) {
            this.f = (ExpandedMenuView) this.d.inflate(R$layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.k == null) {
                this.k = new a();
            }
            this.f.setAdapter((ListAdapter) this.k);
            this.f.setOnItemClickListener(this);
        }
        return this.f;
    }

    @Override // androidx.appcompat.view.menu.l
    public boolean g(f fVar, h hVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.l
    public void h(l.a aVar) {
        this.j = aVar;
    }

    @Override // androidx.appcompat.view.menu.l
    public boolean i(q qVar) {
        if (!qVar.hasVisibleItems()) {
            return false;
        }
        new g(qVar).d(null);
        l.a aVar = this.j;
        if (aVar == null) {
            return true;
        }
        aVar.b(qVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.l
    public void j(boolean z) {
        a aVar = this.k;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.e.M(this.k.getItem(i), this, 0);
    }

    public d(int i, int i2) {
        this.i = i;
        this.h = i2;
    }
}
