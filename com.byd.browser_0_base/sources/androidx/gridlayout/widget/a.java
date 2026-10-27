package androidx.gridlayout.widget;

import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: GridLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends GridLayout.h {
    public final /* synthetic */ GridLayout.h a;
    public final /* synthetic */ GridLayout.h b;

    public a(GridLayout.h hVar, GridLayout.h hVar2) {
        this.a = hVar;
        this.b = hVar2;
    }

    @Override // androidx.gridlayout.widget.GridLayout.h
    public final int a(View view, int i, int i2) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        return (!(om3.e.c(view) == 1) ? this.a : this.b).a(view, i, i2);
    }

    @Override // androidx.gridlayout.widget.GridLayout.h
    public final String c() {
        return "SWITCHING[L:" + this.a.c() + ", R:" + this.b.c() + "]";
    }

    @Override // androidx.gridlayout.widget.GridLayout.h
    public final int d(View view, int i) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        return (!(om3.e.c(view) == 1) ? this.a : this.b).d(view, i);
    }
}
