package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import defpackage.k81;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.chromium.components.browser_ui.widget.R$color;
import org.chromium.components.browser_ui.widget.R$id;
import org.chromium.components.browser_ui.widget.R$layout;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: BasicListMenu.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zd implements k81, AdapterView.OnItemClickListener {
    public final ListView e;
    public final n81 f;
    public final View g;
    public final LinkedList h;
    public final k81.a i;

    public zd(Context context, ya1 ya1Var, k81.a aVar) {
        n81 n81Var = new n81(ya1Var);
        this.f = n81Var;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.app_menu_layout, (ViewGroup) null);
        this.g = viewInflate;
        ListView listView = (ListView) viewInflate.findViewById(R$id.app_menu_list);
        this.e = listView;
        listView.setAdapter((ListAdapter) n81Var);
        listView.setDivider(null);
        listView.setOnItemClickListener(this);
        this.i = aVar;
        this.h = new LinkedList();
        n81Var.a(1, new o61(R$layout.list_menu_item), new n1(5));
        n81Var.a(0, new o61(R$layout.app_menu_divider), new cg(27));
    }

    public static xa1 c(int i, int i2, int i3) {
        return new xa1(1, f(i, i2, i3, true));
    }

    public static xa1 e(int i, int i2, int i3, boolean z) {
        return new xa1(1, f(i, i2, i3, z));
    }

    public static PropertyModel f(int i, int i2, int i3, boolean z) {
        HashMap mapE = PropertyModel.e(o81.k);
        PropertyModel.p pVar = o81.a;
        int i4 = zj.a;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i;
        mapE.put(pVar, dVar);
        PropertyModel.p pVar2 = o81.f;
        PropertyModel.d dVar2 = new PropertyModel.d();
        dVar2.a = i2;
        mapE.put(pVar2, dVar2);
        PropertyModel.p pVar3 = o81.d;
        PropertyModel.d dVar3 = new PropertyModel.d();
        dVar3.a = i3;
        mapE.put(pVar3, dVar3);
        PropertyModel.n nVar = o81.g;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = z;
        mapE.put(nVar, aVar);
        PropertyModel.p pVar4 = o81.h;
        int i5 = R$color.default_icon_color_secondary_tint_list;
        PropertyModel.d dVar4 = new PropertyModel.d();
        dVar4.a = i5;
        mapE.put(pVar4, dVar4);
        return new PropertyModel(mapE);
    }

    @Override // defpackage.k81
    public final void a(Runnable runnable) {
        this.h.add(runnable);
    }

    @Override // defpackage.k81
    public final int b() {
        return uh3.a(this.f, this.e);
    }

    @Override // defpackage.k81
    public final View d() {
        return this.g;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.i.a(((xa1) this.f.getItem(i)).b);
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }
}
