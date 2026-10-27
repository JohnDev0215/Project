package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import defpackage.p1;

/* JADX INFO: compiled from: SubMenuBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class a2 extends p1 implements SubMenu {
    public p1 B;
    public r1 C;

    public a2(Context context, p1 p1Var, r1 r1Var) {
        super(context);
        this.B = p1Var;
        this.C = r1Var;
    }

    @Override // defpackage.p1
    public p1 F() {
        return this.B.F();
    }

    @Override // defpackage.p1
    public boolean H() {
        return this.B.H();
    }

    @Override // defpackage.p1
    public boolean I() {
        return this.B.I();
    }

    @Override // defpackage.p1
    public boolean J() {
        return this.B.J();
    }

    @Override // defpackage.p1
    public void V(p1.a aVar) {
        this.B.V(aVar);
    }

    @Override // defpackage.p1
    public boolean f(r1 r1Var) {
        return this.B.f(r1Var);
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.C;
    }

    @Override // defpackage.p1
    public boolean h(p1 p1Var, MenuItem menuItem) {
        return super.h(p1Var, menuItem) || this.B.h(p1Var, menuItem);
    }

    public Menu i0() {
        return this.B;
    }

    @Override // defpackage.p1
    public boolean m(r1 r1Var) {
        return this.B.m(r1Var);
    }

    @Override // defpackage.p1, android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.B.setGroupDividerEnabled(z);
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

    @Override // defpackage.p1, android.view.Menu
    public void setQwertyMode(boolean z) {
        this.B.setQwertyMode(z);
    }

    @Override // defpackage.p1
    public String v() {
        r1 r1Var = this.C;
        int itemId = r1Var != null ? r1Var.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.v() + ":" + itemId;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i) {
        return (SubMenu) super.Y(i);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i) {
        return (SubMenu) super.b0(i);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i) {
        this.C.setIcon(i);
        return this;
    }
}
