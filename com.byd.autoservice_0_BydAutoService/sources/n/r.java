package n;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import n.g;

/* JADX INFO: compiled from: SubMenuBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class r extends g implements SubMenu {
    public g B;
    public i C;

    public r(Context context, g gVar, i iVar) {
        super(context);
        this.B = gVar;
        this.C = iVar;
    }

    @Override // n.g
    public g F() {
        return this.B.F();
    }

    @Override // n.g
    public boolean H() {
        return this.B.H();
    }

    @Override // n.g
    public boolean I() {
        return this.B.I();
    }

    @Override // n.g
    public boolean J() {
        return this.B.J();
    }

    @Override // n.g
    public void V(g.a aVar) {
        this.B.V(aVar);
    }

    @Override // n.g
    public boolean f(i iVar) {
        return this.B.f(iVar);
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.C;
    }

    @Override // n.g
    public boolean h(g gVar, MenuItem menuItem) {
        return super.h(gVar, menuItem) || this.B.h(gVar, menuItem);
    }

    public Menu i0() {
        return this.B;
    }

    @Override // n.g
    public boolean m(i iVar) {
        return this.B.m(iVar);
    }

    @Override // n.g, android.view.Menu
    public void setGroupDividerEnabled(boolean z3) {
        this.B.setGroupDividerEnabled(z3);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        return (SubMenu) super.Z(drawable);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        return (SubMenu) super.c0(charSequence);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        return (SubMenu) super.d0(view);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.C.setIcon(drawable);
        return this;
    }

    @Override // n.g, android.view.Menu
    public void setQwertyMode(boolean z3) {
        this.B.setQwertyMode(z3);
    }

    @Override // n.g
    public String v() {
        i iVar = this.C;
        int itemId = iVar != null ? iVar.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.v() + ":" + itemId;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i3) {
        return (SubMenu) super.Y(i3);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i3) {
        return (SubMenu) super.b0(i3);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i3) {
        this.C.setIcon(i3);
        return this;
    }
}
