package q;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import q.g;

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

    @Override // q.g
    public g F() {
        return this.B.F();
    }

    @Override // q.g
    public boolean H() {
        return this.B.H();
    }

    @Override // q.g
    public boolean I() {
        return this.B.I();
    }

    @Override // q.g
    public boolean J() {
        return this.B.J();
    }

    @Override // q.g
    public void V(g.a aVar) {
        this.B.V(aVar);
    }

    @Override // q.g
    public boolean f(i iVar) {
        return this.B.f(iVar);
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.C;
    }

    @Override // q.g
    public boolean h(g gVar, MenuItem menuItem) {
        return super.h(gVar, menuItem) || this.B.h(gVar, menuItem);
    }

    public Menu i0() {
        return this.B;
    }

    @Override // q.g
    public boolean m(i iVar) {
        return this.B.m(iVar);
    }

    @Override // q.g, android.view.Menu
    public void setGroupDividerEnabled(boolean z7) {
        this.B.setGroupDividerEnabled(z7);
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

    @Override // q.g, android.view.Menu
    public void setQwertyMode(boolean z7) {
        this.B.setQwertyMode(z7);
    }

    @Override // q.g
    public String v() {
        i iVar = this.C;
        int itemId = iVar != null ? iVar.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.v() + ":" + itemId;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i8) {
        return (SubMenu) super.Y(i8);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i8) {
        return (SubMenu) super.b0(i8);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i8) {
        this.C.setIcon(i8);
        return this;
    }
}
