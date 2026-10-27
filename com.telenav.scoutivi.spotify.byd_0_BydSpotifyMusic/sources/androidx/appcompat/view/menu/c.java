package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.k;
import java.util.ArrayList;

/* JADX INFO: compiled from: ListMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements j, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Context f645e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    LayoutInflater f646g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    e f647h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    ExpandedMenuView f648i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    int f649j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    int f650k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    int f651l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private j.a f652m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    a f653n;

    /* JADX INFO: compiled from: ListMenuPresenter.java */
    private class a extends BaseAdapter {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f654e = -1;

        public a() {
            a();
        }

        void a() {
            g gVarV = c.this.f647h.v();
            if (gVarV != null) {
                ArrayList<g> arrayListZ = c.this.f647h.z();
                int size = arrayListZ.size();
                for (int i7 = 0; i7 < size; i7++) {
                    if (arrayListZ.get(i7) == gVarV) {
                        this.f654e = i7;
                        return;
                    }
                }
            }
            this.f654e = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public g getItem(int i7) {
            ArrayList<g> arrayListZ = c.this.f647h.z();
            int i8 = i7 + c.this.f649j;
            int i9 = this.f654e;
            if (i9 >= 0 && i8 >= i9) {
                i8++;
            }
            return arrayListZ.get(i8);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = c.this.f647h.z().size() - c.this.f649j;
            return this.f654e < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i7) {
            return i7;
        }

        @Override // android.widget.Adapter
        public View getView(int i7, View view, ViewGroup viewGroup) {
            if (view == null) {
                c cVar = c.this;
                view = cVar.f646g.inflate(cVar.f651l, viewGroup, false);
            }
            ((k.a) view).e(getItem(i7), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public c(Context context, int i7) {
        this(i7, 0);
        this.f645e = context;
        this.f646g = LayoutInflater.from(context);
    }

    @Override // androidx.appcompat.view.menu.j
    public void a(e eVar, boolean z6) {
        j.a aVar = this.f652m;
        if (aVar != null) {
            aVar.a(eVar, z6);
        }
    }

    public ListAdapter b() {
        if (this.f653n == null) {
            this.f653n = new a();
        }
        return this.f653n;
    }

    @Override // androidx.appcompat.view.menu.j
    public boolean c() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.j
    public void d(Context context, e eVar) {
        if (this.f650k != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, this.f650k);
            this.f645e = contextThemeWrapper;
            this.f646g = LayoutInflater.from(contextThemeWrapper);
        } else if (this.f645e != null) {
            this.f645e = context;
            if (this.f646g == null) {
                this.f646g = LayoutInflater.from(context);
            }
        }
        this.f647h = eVar;
        a aVar = this.f653n;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.j
    public boolean e(e eVar, g gVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.j
    public boolean f(e eVar, g gVar) {
        return false;
    }

    public k g(ViewGroup viewGroup) {
        if (this.f648i == null) {
            this.f648i = (ExpandedMenuView) this.f646g.inflate(e.g.abc_expanded_menu_layout, viewGroup, false);
            if (this.f653n == null) {
                this.f653n = new a();
            }
            this.f648i.setAdapter((ListAdapter) this.f653n);
            this.f648i.setOnItemClickListener(this);
        }
        return this.f648i;
    }

    @Override // androidx.appcompat.view.menu.j
    public void h(j.a aVar) {
        this.f652m = aVar;
    }

    @Override // androidx.appcompat.view.menu.j
    public boolean i(m mVar) {
        if (!mVar.hasVisibleItems()) {
            return false;
        }
        new f(mVar).d(null);
        j.a aVar = this.f652m;
        if (aVar == null) {
            return true;
        }
        aVar.b(mVar);
        return true;
    }

    @Override // androidx.appcompat.view.menu.j
    public void j(boolean z6) {
        a aVar = this.f653n;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i7, long j7) {
        this.f647h.M(this.f653n.getItem(i7), this, 0);
    }

    public c(int i7, int i8) {
        this.f651l = i7;
        this.f650k = i8;
    }
}
