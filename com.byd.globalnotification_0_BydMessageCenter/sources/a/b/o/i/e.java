package a.b.o.i;

import a.b.k.h;
import a.b.o.i.m;
import a.b.o.i.n;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.view.menu.ExpandedMenuView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class e implements m, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f162a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public LayoutInflater f163b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f164c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ExpandedMenuView f165d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f166e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f167f = 0;
    public int g;
    public m.a h;
    public a i;

    public class a extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f168a = -1;

        public a() {
            a();
        }

        public void a() {
            g gVar = e.this.f164c;
            i iVar = gVar.w;
            if (iVar != null) {
                gVar.i();
                ArrayList<i> arrayList = gVar.j;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    if (arrayList.get(i) == iVar) {
                        this.f168a = i;
                        return;
                    }
                }
            }
            this.f168a = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public i getItem(int i) {
            g gVar = e.this.f164c;
            gVar.i();
            ArrayList<i> arrayList = gVar.j;
            int i2 = i + e.this.f166e;
            int i3 = this.f168a;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return arrayList.get(i2);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            g gVar = e.this.f164c;
            gVar.i();
            int size = gVar.j.size() - e.this.f166e;
            return this.f168a < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                e eVar = e.this;
                view = eVar.f163b.inflate(eVar.g, viewGroup, false);
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
        this.g = i;
        this.f162a = context;
        this.f163b = LayoutInflater.from(context);
    }

    public ListAdapter a() {
        if (this.i == null) {
            this.i = new a();
        }
        return this.i;
    }

    @Override // a.b.o.i.m
    public void b(g gVar, boolean z) {
        m.a aVar = this.h;
        if (aVar != null) {
            aVar.b(gVar, z);
        }
    }

    @Override // a.b.o.i.m
    public boolean c(g gVar, i iVar) {
        return false;
    }

    @Override // a.b.o.i.m
    public void d(m.a aVar) {
        this.h = aVar;
    }

    @Override // a.b.o.i.m
    public boolean f(r rVar) {
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        h hVar = new h(rVar);
        g gVar = hVar.f182a;
        h.a aVar = new h.a(gVar.f176a);
        e eVar = new e(aVar.f16a.f1395a, a.b.g.abc_list_menu_item_layout);
        hVar.f184c = eVar;
        eVar.h = hVar;
        g gVar2 = hVar.f182a;
        gVar2.b(eVar, gVar2.f176a);
        ListAdapter listAdapterA = hVar.f184c.a();
        AlertController.b bVar = aVar.f16a;
        bVar.l = listAdapterA;
        bVar.m = hVar;
        View view = gVar.o;
        if (view != null) {
            bVar.g = view;
        } else {
            bVar.f1398d = gVar.n;
            bVar.f1400f = gVar.m;
        }
        aVar.f16a.k = hVar;
        a.b.k.h hVarA = aVar.a();
        hVar.f183b = hVarA;
        hVarA.setOnDismissListener(hVar);
        WindowManager.LayoutParams attributes = hVar.f183b.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= 131072;
        hVar.f183b.show();
        m.a aVar2 = this.h;
        if (aVar2 == null) {
            return true;
        }
        aVar2.c(rVar);
        return true;
    }

    @Override // a.b.o.i.m
    public boolean g() {
        return false;
    }

    @Override // a.b.o.i.m
    public void h(boolean z) {
        a aVar = this.i;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // a.b.o.i.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void j(android.content.Context r3, a.b.o.i.g r4) {
        /*
            r2 = this;
            int r0 = r2.f167f
            if (r0 == 0) goto L10
            android.view.ContextThemeWrapper r1 = new android.view.ContextThemeWrapper
            r1.<init>(r3, r0)
            r2.f162a = r1
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r1)
            goto L1e
        L10:
            android.content.Context r0 = r2.f162a
            if (r0 == 0) goto L20
            r2.f162a = r3
            android.view.LayoutInflater r0 = r2.f163b
            if (r0 != 0) goto L20
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
        L1e:
            r2.f163b = r3
        L20:
            r2.f164c = r4
            a.b.o.i.e$a r2 = r2.i
            if (r2 == 0) goto L29
            r2.notifyDataSetChanged()
        L29:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.o.i.e.j(android.content.Context, a.b.o.i.g):void");
    }

    @Override // a.b.o.i.m
    public boolean k(g gVar, i iVar) {
        return false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f164c.s(this.i.getItem(i), this, 0);
    }
}
