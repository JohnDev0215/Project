package p;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ey0 implements vn2 {
    public final /* synthetic */ View a;
    public final /* synthetic */ ArrayList b;

    public ey0(View view, ArrayList arrayList) {
        this.a = view;
        this.b = arrayList;
    }

    @Override // p.vn2
    public final void a(wn2 wn2Var) {
        wn2Var.z(this);
        this.a.setVisibility(8);
        ArrayList arrayList = this.b;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((View) arrayList.get(i)).setVisibility(0);
        }
    }

    @Override // p.vn2
    public final void b() {
    }

    @Override // p.vn2
    public final void c() {
    }

    @Override // p.vn2
    public final void d() {
    }

    @Override // p.vn2
    public final void e() {
    }
}
