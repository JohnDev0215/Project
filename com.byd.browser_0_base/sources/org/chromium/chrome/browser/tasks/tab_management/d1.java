package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import defpackage.e33;
import defpackage.k33;
import defpackage.k81;
import defpackage.o61;
import defpackage.py0;
import defpackage.xa1;
import defpackage.ya1;
import defpackage.zf;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.tasks.tab_management.TabSelectionEditorActionViewLayout;
import org.chromium.components.browser_ui.widget.selectable_list.a;

/* JADX INFO: compiled from: TabSelectionEditorMenu.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d1 implements k81, AdapterView.OnItemClickListener, a.InterfaceC0128a<Integer>, TabSelectionEditorActionViewLayout.a {
    public final Context e;
    public final LinkedHashMap f = new LinkedHashMap();
    public final View g;
    public final ListView h;
    public final TabSelectionEditorActionViewLayout i;
    public final ya1 j;
    public final a k;

    /* JADX INFO: compiled from: TabSelectionEditorMenu.java */
    public class a extends org.chromium.ui.modelutil.f {
        public a(ya1 ya1Var) {
            super(ya1Var);
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public final boolean isEnabled(int i) {
            return d1.this.j.get(i).b.k(e33.k);
        }
    }

    public d1(Context context, TabSelectionEditorActionViewLayout tabSelectionEditorActionViewLayout) {
        this.e = context;
        this.i = tabSelectionEditorActionViewLayout;
        tabSelectionEditorActionViewLayout.setActionViewLayoutDelegate(this);
        ya1 ya1Var = new ya1();
        this.j = ya1Var;
        a aVar = new a(ya1Var);
        this.k = aVar;
        aVar.a(0, new o61(R$layout.list_menu_item), new zf(26));
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.app_menu_layout, (ViewGroup) null);
        this.g = viewInflate;
        ListView listView = (ListView) viewInflate.findViewById(R$id.app_menu_list);
        this.h = listView;
        listView.setAdapter((ListAdapter) aVar);
        listView.setDivider(null);
        listView.setOnItemClickListener(this);
        tabSelectionEditorActionViewLayout.setListMenuButtonDelegate(new py0(2, this));
    }

    @Override // defpackage.k81
    public final int b() {
        return this.e.getResources().getDimensionPixelSize(R$dimen.menu_width);
    }

    @Override // defpackage.k81
    public final View d() {
        return this.g;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        Iterator it = this.f.values().iterator();
        while (it.hasNext()) {
            ((k33) it.next()).k.onResult(arrayList);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        boolean z;
        k33 k33Var = (k33) this.f.get(Integer.valueOf(((xa1) this.k.getItem(i)).b.h(e33.a)));
        if (k33Var.f) {
            k33Var.j.run();
            z = true;
        } else {
            z = false;
        }
        if (z && k33Var.g) {
            this.i.g.b();
        }
    }

    @Override // defpackage.k81
    public final void a(Runnable runnable) {
    }
}
