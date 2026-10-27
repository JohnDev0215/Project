package defpackage;

import android.app.Activity;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;
import defpackage.vg2;

/* JADX INFO: compiled from: SearchUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ug2 implements SearchView.OnQueryTextListener {
    public final /* synthetic */ MenuItem a;
    public final /* synthetic */ Activity b;
    public final /* synthetic */ vg2.a c;

    public ug2(MenuItem menuItem, FragmentActivity fragmentActivity, ke0 ke0Var) {
        this.a = menuItem;
        this.b = fragmentActivity;
        this.c = ke0Var;
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public final void onQueryTextChange(String str) {
        vg2.a(this.a, str, this.b);
        ((ke0) this.c).t(str);
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public final void a() {
    }
}
