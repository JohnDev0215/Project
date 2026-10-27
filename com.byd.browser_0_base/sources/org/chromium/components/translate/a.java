package org.chromium.components.translate;

import J.N;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import defpackage.k81;
import defpackage.uh3;
import java.util.Iterator;
import java.util.LinkedList;
import org.chromium.components.translate.TranslateMessage;

/* JADX INFO: compiled from: TranslateMessageSecondaryMenu.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a implements k81, AdapterView.OnItemClickListener {
    public final InterfaceC0144a e;
    public final b f;
    public final View g;
    public final ListView h;
    public final LinkedList i;

    /* JADX INFO: renamed from: org.chromium.components.translate.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TranslateMessageSecondaryMenu.java */
    @FunctionalInterface
    public interface InterfaceC0144a {
    }

    public a(Context context, TranslateMessage translateMessage, DataSetObserver dataSetObserver, TranslateMessage.MenuItem[] menuItemArr) {
        this.e = translateMessage;
        b bVar = new b(context, menuItemArr);
        this.f = bVar;
        bVar.registerDataSetObserver(dataSetObserver);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.app_menu_layout, (ViewGroup) null);
        this.g = viewInflate;
        ListView listView = (ListView) viewInflate.findViewById(R$id.app_menu_list);
        this.h = listView;
        listView.setAdapter((ListAdapter) bVar);
        listView.setDivider(null);
        listView.setOnItemClickListener(this);
        this.i = new LinkedList();
    }

    @Override // defpackage.k81
    public final void a(Runnable runnable) {
        this.i.add(runnable);
    }

    @Override // defpackage.k81
    public final int b() {
        return uh3.a(this.f, this.h);
    }

    @Override // defpackage.k81
    public final View d() {
        return this.g;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        b bVar = this.f;
        TranslateMessage.MenuItem menuItem = bVar.f[i];
        long j2 = ((TranslateMessage) this.e).d;
        TranslateMessage.MenuItem[] menuItemArr = j2 == 0 ? null : (TranslateMessage.MenuItem[]) N.MENF59pO(j2, menuItem.d, menuItem.e, menuItem.c);
        if (menuItemArr != null) {
            bVar.f = menuItemArr;
            bVar.notifyDataSetChanged();
        } else {
            Iterator it = this.i.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
    }
}
