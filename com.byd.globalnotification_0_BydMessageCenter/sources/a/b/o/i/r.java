package a.b.o.i;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class r extends g implements SubMenu {
    public g A;
    public i B;

    public r(Context context, g gVar, i iVar) {
        super(context);
        this.A = gVar;
        this.B = iVar;
    }

    @Override // a.b.o.i.g
    public boolean d(i iVar) {
        return this.A.d(iVar);
    }

    @Override // a.b.o.i.g
    public boolean e(g gVar, MenuItem menuItem) {
        return super.e(gVar, menuItem) || this.A.e(gVar, menuItem);
    }

    @Override // a.b.o.i.g
    public boolean f(i iVar) {
        return this.A.f(iVar);
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.B;
    }

    @Override // a.b.o.i.g
    public String j() {
        i iVar = this.B;
        int i = iVar != null ? iVar.f185a : 0;
        if (i == 0) {
            return null;
        }
        return "android:menu:actionviewstates:" + i;
    }

    @Override // a.b.o.i.g
    public g k() {
        return this.A.k();
    }

    @Override // a.b.o.i.g
    public boolean m() {
        return this.A.m();
    }

    @Override // a.b.o.i.g
    public boolean n() {
        return this.A.n();
    }

    @Override // a.b.o.i.g
    public boolean o() {
        return this.A.o();
    }

    @Override // a.b.o.i.g, android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.A.setGroupDividerEnabled(z);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i) {
        x(0, null, i, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i) {
        x(i, null, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        x(0, null, 0, null, view);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i) {
        this.B.setIcon(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.B.setIcon(drawable);
        return this;
    }

    @Override // a.b.o.i.g, android.view.Menu
    public void setQwertyMode(boolean z) {
        this.A.setQwertyMode(z);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        x(0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        x(0, charSequence, 0, null, null);
        return this;
    }
}
