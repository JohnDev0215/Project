package org.chromium.content.browser.picker;

import android.view.View;
import android.widget.AdapterView;
import defpackage.y60;
import org.chromium.content.browser.input.DateTimeChooserAndroid;

/* JADX INFO: compiled from: InputDialogContainer.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b implements AdapterView.OnItemClickListener {
    public final /* synthetic */ y60 e;
    public final /* synthetic */ int f;
    public final /* synthetic */ double g;
    public final /* synthetic */ double h;
    public final /* synthetic */ double i;
    public final /* synthetic */ double j;
    public final /* synthetic */ d k;

    public b(d dVar, y60 y60Var, int i, double d, double d2, double d3, double d4) {
        this.k = dVar;
        this.e = y60Var;
        this.f = i;
        this.g = d;
        this.h = d2;
        this.i = d3;
        this.j = d4;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        y60 y60Var = this.e;
        int count = y60Var.getCount() - 1;
        d dVar = this.k;
        if (i == count) {
            dVar.a();
            this.k.c(this.f, this.g, this.h, this.i, this.j);
        } else {
            ((DateTimeChooserAndroid.a) dVar.d).a(y60Var.getItem(i).a);
            dVar.a();
            dVar.b = true;
        }
    }
}
